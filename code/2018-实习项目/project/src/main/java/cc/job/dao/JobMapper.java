package cc.job.dao;

import java.util.List;

import cc.common.config.MyMapper;
import cc.job.domain.Job;

public interface JobMapper extends MyMapper<Job> {
	
	List<Job> queryList();
}