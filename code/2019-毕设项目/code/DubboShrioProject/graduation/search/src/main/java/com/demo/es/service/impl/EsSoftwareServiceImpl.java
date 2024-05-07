package com.demo.es.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FieldValueFactorFunctionBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.RandomScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.WeightBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.domain.ResultInfo;
import com.demo.es.entity.EsSoftware;
import com.demo.es.service.IEsSoftwareService;
import com.demo.mapper.EsSoftwareMapper;
import com.demo.respository.SoftwareRespostory;
import com.demo.utils.StringUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@com.alibaba.dubbo.config.annotation.Service(version = "${demo.service.version}", application = "${dubbo.application.id}", protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id}", timeout = 300000)
@Service
public class EsSoftwareServiceImpl extends ServiceImpl<EsSoftwareMapper, EsSoftware> implements IEsSoftwareService {

	@Autowired
	private SoftwareRespostory softwareRespostory;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private EsSoftwareMapper softwareMapper;

	// 先删除所有索引 再重新建立
	@Override
	public boolean saveAll(List<EsSoftware> list) {
		softwareRespostory.deleteAll();
		softwareRespostory.saveAll(list);
		return true;
	}

	// 模糊查询
	@Override
	public List<EsSoftware> search(String searchContent) {
		// String delete = elasticsearchTemplate.delete(Software.class, "41");//索引的id
		// BoolQueryBuilder bqb = QueryBuilders.boolQuery();
		// bqb.should(QueryBuilders.fuzzyQuery("title", qry));
		// bqb.must(QueryBuilders.termQuery("gender","male"));
		NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
		QueryBuilder qb = QueryBuilders.disMaxQuery().add(QueryBuilders.fuzzyQuery("text", searchContent)) // 查询条件
				.add(QueryBuilders.fuzzyQuery("title", searchContent)).boost(1L);
		searchQuery.withIndices("sf").withQuery(qb);
		List<EsSoftware> list = elasticsearchTemplate.queryForList(searchQuery.build(), EsSoftware.class);
		return list;
	}

	@Override
	public boolean insert(EsSoftware es) {
		boolean falg = false;
		try {
			softwareRespostory.save(es);
			falg = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return falg;
	}

	// 权重查询 , softwareId 权值来自 协同过滤算法算出来相似度最高的软件ID
	@Override
	public ResultInfo searchPage(Integer pageNumber, Integer pageSize, String searchContent, String softwareId)
			throws JsonParseException, JsonMappingException, IOException {
//		Pageable p  = PageRequest.of(pageNumber, pageSize);
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

		SearchRequestBuilder searchRequestBuilder = elasticsearchTemplate.getClient().prepareSearch("sf");// index = sf
		// 分页
		searchRequestBuilder.setFrom(pageNumber).setSize(pageSize);
		// explain为true表示根据数据相关度排序，和关键字匹配最高的排在前面
		searchRequestBuilder.setExplain(true);

		// 搜索 text字段 like的数据
		if (StringUtils.isChinese(searchContent)) {
			queryBuilder.should(QueryBuilders.matchQuery("title", searchContent));
		} else {
			queryBuilder.should(QueryBuilders.wildcardQuery("title", "*" + searchContent.toLowerCase() + "*"));
		}

		FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[3];
		// 过滤条件1：分类为：user_id 最重要 -- 权重查询Weight
		ScoreFunctionBuilder<WeightBuilder> scoreFunctionBuilder = new WeightBuilder();
		scoreFunctionBuilder.setWeight(2);
		QueryBuilder termQuery = null;
		if (!"-1".equals(softwareId)) {
			termQuery = QueryBuilders.termQuery("user_id", softwareMapper.selectById(softwareId).getId());
		} else {
			QueryWrapper<EsSoftware> qw = new QueryWrapper<>();
			qw.groupBy("user_id");
			qw.select("COUNT(user_id)");
			qw.select("id");
			termQuery = QueryBuilders.termQuery("user_id", softwareMapper.selectList(qw).get(0).getId());
		}

		FunctionScoreQueryBuilder.FilterFunctionBuilder category = new FunctionScoreQueryBuilder.FilterFunctionBuilder(
				termQuery, scoreFunctionBuilder);
		filterFunctionBuilders[0] = category;

		// 过滤条件2：得分越高越排前 --计分查询 FieldValueFactor
		ScoreFunctionBuilder<FieldValueFactorFunctionBuilder> fieldValueScoreFunction = new FieldValueFactorFunctionBuilder(
				"score");
		((FieldValueFactorFunctionBuilder) fieldValueScoreFunction).factor(1.1f);
		FunctionScoreQueryBuilder.FilterFunctionBuilder sales = new FunctionScoreQueryBuilder.FilterFunctionBuilder(
				fieldValueScoreFunction);
		filterFunctionBuilders[1] = sales;

		// 给定每个用户随机展示： --random_score
		ScoreFunctionBuilder<RandomScoreFunctionBuilder> randomScoreFilter = new RandomScoreFunctionBuilder();
		((RandomScoreFunctionBuilder) randomScoreFilter).seed(2);
		FunctionScoreQueryBuilder.FilterFunctionBuilder random = new FunctionScoreQueryBuilder.FilterFunctionBuilder(
				randomScoreFilter);
		filterFunctionBuilders[2] = random;

		// 多条件查询 FunctionScore
		FunctionScoreQueryBuilder query = QueryBuilders.functionScoreQuery(queryBuilder, filterFunctionBuilders)
				.scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);
		searchRequestBuilder.setQuery(query);

		SearchResponse response = searchRequestBuilder.execute().actionGet();
		SearchHits hits = response.getHits();
		String searchSource;
		List<EsSoftware> l = new ArrayList<EsSoftware>();
		for (SearchHit hit : hits) {
			searchSource = hit.getSourceAsString();
			ObjectMapper mapper = new ObjectMapper();
//			EsSoftware es =JSONObject.parseObject(searchSource, EsSoftware.class);//Json字符串转换为 JavaBean对象
			EsSoftware es = mapper.readValue(searchSource, EsSoftware.class);
			l.add(es);
		}
		long total = hits.getTotalHits();
		return new ResultInfo(total, l);
	}

	@Override
	public void delete(EsSoftware entity) {
		softwareRespostory.delete(entity);
	}

}
