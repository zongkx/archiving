package DB;

import DAO.CourseDAOImpl;

public class mai {
	public static void main(String[] args) throws Exception {
		DBU.open();
//		int a= new CourseDAOImpl().getPageCount();
//		System.out.println(a);
		new CourseDAOImpl().listCourse(2);
		//System.out.println(new CourseDAOImpl().listCourse(2));
		
	}
}
