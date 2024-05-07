package cc.job.service;

import java.util.List;

import cc.common.service.IService;
import cc.job.domain.JobLog;

public interface JobLogService extends IService<JobLog>{

	List<JobLog> findAllJobLogs(JobLog jobLog);

	void saveJobLog(JobLog log);
	
	void deleteBatch(String jobLogIds);
}
