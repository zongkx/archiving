package com.demo.respository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.demo.es.entity.EsSoftware;

@Component
public interface SoftwareRespostory extends ElasticsearchRepository<EsSoftware, Long> {
	/*
	 * 在Spring Data reppository支持直接按名字解析，但是起的方法名要按照一定的规则来取，解析的时候会
	 * 剥离以下的套词，然后对剩下的部分进行解析，查询的套词如下：find…By, read…By, query…By, count…By和get…By，
	 * 反正不管怎样，By后面就是实际查询条件的开始，一般都是基于实体的属性作条件，条件之间使用And或者Or来连接，比如
	 * findBookByIdAndName, findBookByNameOrAuth
	 */

//	Software findByText(String Text);
//
//	Software findById(Integer id);
//
//	Page<Software> findByTitle(String title, Pageable pageable);

}
