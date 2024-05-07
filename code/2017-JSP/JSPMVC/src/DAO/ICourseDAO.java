package DAO;

import java.util.List;

import VO.Course;

public interface ICourseDAO {
	public List<Course> listCourse(int pageNo) throws Exception;
	public int getPageCount()throws Exception;
	
	public void deleteCourse(int did);
	public void changeCourse(Course c);
	public void addCourse(Course  c);
}
