package DAO;

import java.util.List;

import VO.Job;
import VO.JobFair;
import VO.Student;

public interface ICompanyDAO {
	public abstract List<Job> comJobSel(Integer acc);
	public abstract List<Student> comJobSel1(Integer acc);
	public abstract List<JobFair> comJobFairSel(Integer acc);
	public abstract String comJonFairAdd(Integer acc,JobFair jf);
}	
