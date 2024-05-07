package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DB.DBU;
import VO.Job;
import VO.Student;

public class TeacherDAOImpl implements ITeacherDAO{

	
	public List<Student> StuAllSel() {
		Connection con =null;

		ResultSet rs = null;
		CallableStatement cs =null;
		try {
			con = DBU.open();
			cs= con.prepareCall("{call EmploymentCenter.dbo.StuAllSel()}");
			cs.executeQuery();
			rs =cs.getResultSet();
			List< Student > list = new ArrayList<Student>();
			
			while(rs.next()) {
				Student s =new Student();
				s.setAcc(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setAge(rs.getInt(3));
				s.setGrade(rs.getBigDecimal(4));
				s.setAca(rs.getString(5));
				list.add(s);
				 
			}
		
			return list;
		}catch (Exception e) {
			
		}finally {
			DBU.close(con);
		}
		
		return null;
	}

	
	public List<Job> JobAllSel() {
		Connection con =null;

		ResultSet rs = null;
		CallableStatement cs =null;
		try {
			con = DBU.open();
			cs= con.prepareCall("{call EmploymentCenter.dbo.JobAllSel()}");
			cs.executeQuery();
			rs =cs.getResultSet();
			List< Job > list = new ArrayList<Job>();
			System.out.println(list);
			while(rs.next()) {
				 
				Job j =  new Job();
				j.setStu_acc(rs.getInt(3));
				j.setJobd(rs.getString(4)+rs.getString(5)+rs.getString(6));
				j.setTime(rs.getDate(7));
				j.setSalary(rs.getInt(8));
				j.setContract(rs.getInt(9));
				 
				list.add(j);
				
			}
			System.out.println(list);
			return list;
		}catch (Exception e) {
			
		}finally {
			DBU.close(con);
		}
		
		return null;
	}


	
	public String StuAllUpd(Student s) {
		Connection con =null;

		ResultSet rs = null;
		CallableStatement cs =null;
		try {
			con = DBU.open();
			cs= con.prepareCall("{call EmploymentCenter.dbo.StuAllUpd(?,?,?,?,?)}");
			cs.setInt(1, s.getAcc());
			cs.setString(2, s.getName());
			cs.setInt(3	,s.getAge());
			cs.setBigDecimal(4, s.getGrade());
			cs.setString(5, s.getAca());
			cs.executeQuery();
			rs =cs.getResultSet();
			
			if(rs.next()) {
				return "ok";
			}
		
			
		}catch (Exception e) {
			
		}finally {
			DBU.close(con);
		}
		
		return null;
	}
	
}
