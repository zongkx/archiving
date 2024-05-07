package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DB.DBU;
//import DB.DBUtils;
//import DB.DBUtils;
import VO.Course;



public class CourseDAOImpl implements ICourseDAO {
	public int getPageCount()throws Exception{
		Connection conn =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int recordCount=0,t1=0,t2=0;
		try {
			conn = DBU.open();
			String sql = "select count(*) from course";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			recordCount = rs.getInt(1);
			t1 = recordCount%5;
			t2 = recordCount/5;
			
			
		}finally {
			DBU.close(conn);
		}
		
		return t1==0?t2:t2+1;
		
	}
	public List<Course> listCourse(int pageNo) throws Exception{
		Connection conn =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int pageSize = 5;
		int startRecno = (pageNo - 1)*pageSize;
		String s =String.valueOf(startRecno);
		
		//String s1 =String.valueOf(pageSize);
		ArrayList<Course> courseList = new ArrayList<Course>();
		try {
			conn = DBU.open();
			//String sql = "select * from course order by CourseID limit ?,?";
			String sql1 ="select top " +pageSize+"  * from course where CourseID not in (select top "+s+"  CourseID from course)";
//			System.out.println(sql1);
//			String sql2 = "select top 5  CourseID from course where CourseID not in (select top 5  CourseID from course)";
			ps = conn.prepareStatement(sql1);
			//ps.setInt(1, startRecno);
			//ps.setInt(2, pageSize);
			//ps.setInt(1, pageSize);
			//ps.setInt(2, startRecno);
			rs = ps.executeQuery();
			while(rs.next()) {
				Course  c = new Course();
				c.setId(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setDate(rs.getString(3));
				rs.getLong(4);
				
				//c.setTeacher(a);
				courseList.add(c);
				
			}
			
			
		}finally {
			DBU.close(conn);
		}
		
		return courseList;
		
		
		
	}
	
	public void deleteCourse(int did) {
		Connection conn =null;
		PreparedStatement ps = null;
		//ResultSet rs = null;
		try {
			conn = DBU.open();
			String sql  = "delete from course where CourseID= ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, did);
			ps.executeUpdate();
			
			
		}catch (Exception e) {
			
		}
		
		finally {
			DBU.close(conn);
		}
		
	}
	
	public void changeCourse(Course c) {
		Connection conn =null;
		PreparedStatement ps = null;
		//ResultSet rs = null;
		try {
			conn = DBU.open();
			String sql  = "update course set courseName= ?,courseDate=?,courseTeacher=? where courseID=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, c.getName());
			ps.setString(2, c.getDate());
			ps.setLong(3, c.getTeacher());
			ps.setInt(4, c.getId());
			
			ps.executeUpdate();
			
			
			
		}catch (Exception e) {
			
		}
		
		finally {
			DBU.close(conn);
		}
		
	}

	public void addCourse(Course c) {
		Connection conn =null;
		PreparedStatement ps = null;
		//ResultSet rs = null;
		try {
			conn = DBU.open();
			String sql  = "insert course value(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, c.getId());
			ps.setString(2, c.getName());
			ps.setString(3, c.getDate());
			ps.setLong(4, c.getTeacher());
			
			
			ps.executeUpdate();
			
			
			
		}catch (Exception e) {
			
		}
		
		finally {
			DBU.close(conn);
		}
		
	}
	
}
