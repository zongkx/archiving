package com.demo.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.domain.Json;
import com.demo.domain.ResultInfo;
import com.demo.entity.Behavior;
import com.demo.entity.Comment;
import com.demo.entity.Software;
import com.demo.entity.User;
import com.demo.entity.UserSoftwareItems;
import com.demo.service.IBehaviorService;
import com.demo.service.ICommentService;
import com.demo.service.ISoftwareService;
import com.demo.service.IUserService;
import com.demo.utils.HtmlUtils;
import com.demo.utils.Rec;

@Controller
@RequestMapping("/sf")
public class SoftwareController {

	private static final Logger log = LoggerFactory.getLogger(SoftwareController.class);
	@Reference(version = "1.0.0")
	private IUserService iUserService;

	@Reference(version = "1.0.0")
	private ISoftwareService iSoftwareService;

	@Reference(version = "1.0.0")
	private IBehaviorService iBehaviorService;

	@Reference(version = "1.0.0")
	private ICommentService iCommentService;

	@RequestMapping("/getSoftwareByScore") // 根据评分获取三个软件信息作为轮播内容
	public @ResponseBody Json getSoftwareByScore() {
		List<Software> list = this.iSoftwareService.getSoftwareByScoreI();
		return Json.succ().data(list);

	}

	@RequestMapping("/getSoftwarePage")
	public @ResponseBody ResultInfo getSoftwarePage(@RequestParam(value = "offset", required = false) String offset,
			@RequestParam(value = "limit", required = false) String limit) {
		if (!StringUtils.isNotBlank(limit) & !StringUtils.isNotBlank(offset)) {
			log.info("初始化页面信息");
			offset = "0";
			limit = "10";
		}
		Page<Software> page = new Page<Software>();
		page.setCurrent(Integer.parseInt(offset));
		page.setSize(Integer.parseInt(limit));
		IPage<Software> page1 = this.iSoftwareService.page(page);
		return new ResultInfo(page1.getTotal(), page1.getRecords());
	}

	@RequestMapping("/getSoftwarePageByType")
	public @ResponseBody ResultInfo getSoftwarePageByType(
			@RequestParam(value = "offset", required = false) String offset,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "type", required = false) String type) {
		if (!StringUtils.isNotBlank(limit) & !StringUtils.isNotBlank(offset)) {
			log.info("初始化页面信息");
			offset = "0";
			limit = "10";
		}
		return this.iSoftwareService.getSoftwarePageByType(offset, limit, type);

	}

	@RequestMapping("/getUserCollection")
	public @ResponseBody ResultInfo getUserCollection(@RequestParam(value = "offset", required = false) String offset,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "nickname", required = false) String nickname) {
		if (!StringUtils.isNotBlank(limit) & !StringUtils.isNotBlank(offset)) {
			log.info("初始化页面信息");
			offset = "0";
			limit = "10";
		}
		return this.iSoftwareService.getUserCollection(offset, limit, nickname);

	}

	@RequestMapping("/addSoftware")
	public @ResponseBody Json addS(@RequestBody Software software) {
		software.setDate(LocalDateTime.now());
		software.setScore(0L);
		String pic = HtmlUtils.getImgStrOne(software.getText());
		String style = " style='height:inherit;width:100% ' ";
		String pic0 = "<img  src='" + pic + "'" + style + ">";
		software.setPhoto(pic0);
		this.iSoftwareService.save(software);
		return Json.succ();

	}

	@RequestMapping("/getSoftwareAllType")
	public @ResponseBody Json getSoftwareAllType() {
		log.info("getSoftwareAllType ");
		return Json.succ().data(this.iSoftwareService.getSfAlltype());
	}

	@RequestMapping("/delSoftwareById")
	public @ResponseBody Json delSoftware(@RequestParam(value = "id", required = false) String id) {
		String oper = "del Software";
		this.iSoftwareService.removeById(id);
		return Json.succ(oper);
	}

	@RequestMapping("/doRate")
	public @ResponseBody Json doRate(@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "score", required = true) float score,
			@RequestParam(value = "id", required = false) String id) {
		String oper = "do Rate";
		Software sf = new Software();
		sf = this.iSoftwareService.getById(id);
		String oldScoreLog = sf.getScoreLog();
		float oldScore = sf.getScore();
		boolean flag = true;
		if (StringUtils.isNotBlank(oldScoreLog)) {
			for (String s : StringUtils.split(oldScoreLog, ",")) {
				if (nickname.equals(s)) {
					flag = false;
					break;
				}
			}
			if (!flag) {
				return Json.fail(oper).data("已经评过分了");
			} else {
				float newScore = (oldScore + score) / 2;
				sf.setScore(newScore);
				sf.setScoreLog(StringUtils.join(oldScoreLog, "," + nickname));
				this.iSoftwareService.updateById(sf);

				// 评分行为记录
				Behavior b = new Behavior();
				b.setSoftwareId(Long.parseLong(id));
				b.setScore(score);
				b.setTime(LocalTime.now());
				b.setNickname(nickname);
				this.iBehaviorService.save(b);

				return Json.succ().data(sf);
			}
		} else {
			sf.setScore(score);
			sf.setScoreLog(StringUtils.join(oldScoreLog, nickname));
			this.iSoftwareService.updateById(sf);
			// 评分行为记录
			Behavior b = new Behavior();
			b.setSoftwareId(Long.parseLong(id));
			b.setScore(score);
			b.setTime(LocalTime.now());
			b.setNickname(nickname);
			this.iBehaviorService.save(b);

			return Json.succ().data(sf);
		}
	}

	@RequestMapping("/allowDoRate") // 进入页面后 先进行判断 确认当前用户是否可以评分
	public @ResponseBody Json AllowdoRate(@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "id", required = false) String id) {
		String oper = "allow do Rate";
		Software sf = this.iSoftwareService.getById(id);
		String oldScoreLog = sf.getScoreLog();
		boolean flag = true;
		if (StringUtils.isNotBlank(oldScoreLog)) {
			for (String s : StringUtils.split(oldScoreLog, ",")) {
				if (nickname.equals(s)) {
					flag = false;
					break;
				}
			}
			if (!flag) {
				return Json.fail(oper).data("已经评过分了");
			} else {
				return Json.succ(oper);// 可以评分
			}
		} else {// scoreLog 为空 则可以进行评分
			return Json.succ(oper);
		}
	}

	@RequestMapping("/getSoftwareById")
	public @ResponseBody Json getSoftware(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "aid", required = false) String aid) {
		Software sf = iSoftwareService.getById(aid);
		iCommentService.readed(id, "1");
		return Json.succ().data(sf);
	}

	@RequestMapping("/getCF")
	public @ResponseBody Json get(@RequestParam(value = "nickname", required = false) String nickname) {

		List<User> list = iUserService.list();// 所有用户

		List<UserSoftwareItems> userSoftwareItemsList = new ArrayList<UserSoftwareItems>(); // 用户:软件 = 1:n
		for (User b : list) {
			UserSoftwareItems usi = new UserSoftwareItems();
			List<Long> listLong = iBehaviorService.getSoftwareId(b.getNickname());
			if (listLong.size() < 1) {
				continue;
			}
			usi.setNickname(b.getNickname());
			usi.setSoftwareId(listLong);

			userSoftwareItemsList.add(usi);
		}
		boolean flag = false;
		List<Software> rlist = new ArrayList<>();// 返回的结果集
		Long mostSid = 0L;
		if (userSoftwareItemsList.size() < 1) { // 没有行为记录则 返回评论最高的三条
			List<Comment> commentList = iCommentService.getMostCommentsSoftwareIds();
			for (Comment c : commentList) {
				if ("1".equals(c.getType())) {
					rlist.add(this.iSoftwareService.getById(c.getAid()));
				}
			}
		} else {
			for (UserSoftwareItems usi : userSoftwareItemsList) {
				if (nickname.equals(usi.getNickname())) {// 如果能在行为记录中找到该用户信息,则计算
					flag = true;
					break;
				}
			}
			if (flag) {
				Map<Long, Double> map = Rec.cf(userSoftwareItemsList, nickname, 5);
				if (!map.isEmpty()) {
					for (Map.Entry<Long, Double> entry : map.entrySet()) {
						rlist.add(this.iSoftwareService.getById(entry.getKey()));
						if(mostSid == 0L) {
							mostSid=entry.getKey();
						}
					}
				} else {// 如果行为推荐并不产生数据,那么返回默认的三条数据
					List<Comment> commentList = iCommentService.getMostCommentsSoftwareIds();
					for (Comment c : commentList) {
						if ("1".equals(c.getType())) {
							rlist.add(this.iSoftwareService.getById(c.getAid()));
						}
					}
				}
			} else {// 否则返回默认的三条
				List<Comment> commentList = iCommentService.getMostCommentsSoftwareIds();
				for (Comment c : commentList) {
					if ("1".equals(c.getType())) {
						rlist.add(this.iSoftwareService.getById(c.getAid()));
					}
				}
			}
			
		}
		return Json.succ().data(rlist).msg(String.valueOf(mostSid));
	}
	
	
	
	@RequestMapping("/getRec5")
	public @ResponseBody Json getRec5() {
		List<Map<String, String>> rlist = new ArrayList<>();// 返回的结果集
		List<Comment> commentList = iCommentService.getMostCommentsSoftwareIds();
		for (Comment c : commentList) {
			Map<String, String> map = new HashMap<String, String>();
			if ("1".equals(c.getType())) {
				map.put("title", this.iSoftwareService.getById(c.getAid()).getTitle());
			}
			rlist.add(map);
		}
		return Json.succ("none").data(rlist);
		
		
	}
	

	@RequestMapping("/getSoftwarePageByAid")
	public @ResponseBody ResultInfo getSoftwarePageByAid(
			@RequestParam(value = "offset", required = false) String offset,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "qryStr", required = false) String qryStr,
			@RequestParam(value = "aid", required = false) String aid) {
		if (!StringUtils.isNotBlank(limit) & !StringUtils.isNotBlank(offset)) {
			log.info("初始化页面信息");
			offset = "0";
			limit = "10";
		}
		return iSoftwareService.getSoftwarePageByTitleAndAid(offset, limit, qryStr, aid);
	}

	@RequestMapping("/modifySoftware")
	public @ResponseBody Json modifySf(@RequestBody Software software) {
		software.setDate(LocalDateTime.now());
		String pic = HtmlUtils.getImgStrOne(software.getText());
		String style = " style='height:inherit;width:100% ' ";
		String pic0 = "<img  src='" + pic + "'" + style + ">";
		software.setPhoto(pic0);
		this.iSoftwareService.updateById(software);
		return Json.succ();

	}

	@RequestMapping("/checkDoCollection")
	public @ResponseBody Json checkDoCollection(@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "sid", required = false) String sid) {

		return Json.succ().data(iBehaviorService.checkDoCollection(sid, nickname));

	}

	@RequestMapping("/doCollection")
	public @ResponseBody Json doCollection(@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "sid", required = false) String sid) {
		Behavior b = new Behavior();
		b.setNickname(nickname);
		b.setSoftwareId(Long.parseLong(sid));
		b.setScore(Float.parseFloat("-1"));
		this.iBehaviorService.save(b);
		return Json.succ();

	}

	@RequestMapping("/deCollection")
	public @ResponseBody Json deCollection(@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "sid", required = false) String sid) {

		return Json.succ().data(iBehaviorService.deCollection(sid, nickname));

	}
}
