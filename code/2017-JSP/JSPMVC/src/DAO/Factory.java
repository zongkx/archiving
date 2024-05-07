package DAO;

public class Factory {
	public ICourseDAO getCourseInstance() {
		return  new CourseDAOImpl();
	}
}
