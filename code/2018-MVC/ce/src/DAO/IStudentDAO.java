package DAO;

import VO.Job;
import VO.Student;

public interface IStudentDAO {
	abstract public Student stuSel(Integer acc );
	abstract public Job stuJobSel(Integer acc);
}
