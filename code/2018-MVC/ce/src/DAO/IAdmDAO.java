package DAO;

import java.util.ArrayList;
import java.util.List;

import VO.Company;
import VO.Job;
import VO.JobFair;
import VO.Student;
import VO.Teacher;

public interface IAdmDAO {
	abstract public ArrayList<Company> ComAllSel();
	
	abstract public List<JobFair> JobFairAllSel();
	
	public abstract List<Teacher> TeaAllSel();
	public abstract List<Student> StuAllSel();
	public abstract List<Job> JobAllSel();
	
	
	
	public abstract String ComAllUpd(Company c);
	public abstract String TeaAllUpd(Teacher t);
	
	
	
	public abstract String JobAllAdd(Job j);
	
	
	public abstract String JobAllDel(Integer jid);
	
	
	public abstract String StuAllDel(Integer sid);
	
	public abstract String JobFairDel(Integer jfid);
	
	
	abstract public List<JobFair> JobFairAllSel1(Integer  PageNo);

	public abstract int getPageCount() throws Exception;
	
	public abstract  String JobFairAllUpd(JobFair jf);
}
