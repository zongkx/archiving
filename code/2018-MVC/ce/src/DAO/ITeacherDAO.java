package DAO;

import java.util.List;

import VO.Job;
import VO.Student;

public interface ITeacherDAO {
	public abstract List<Student> StuAllSel();
	public abstract List<Job> JobAllSel();
	public abstract String StuAllUpd(Student s);
}
