package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DB.DBU;
import VO.Company;
import VO.Job;
import VO.Student;

public class StudentDAOImpl implements IStudentDAO {


	public Student stuSel(Integer acc) {//查看学生的个人信息
		Connection con =null;

		ResultSet rs = null;
		CallableStatement cs =null;
		try {
			
			con = DBU.open();
			
			cs= con.prepareCall("{call EmploymentCenter.dbo.stuSel(?)}");
			cs.setInt(1, acc);
			cs.executeQuery();
			rs =cs.getResultSet();
			
			Student s =new Student();
			while(rs.next()) {
				s.setName(rs.getString(1));
				s.setAge(rs.getInt(2));
				s.setGrade(rs.getBigDecimal(3));
				s.setAca(rs.getString(4));
				s.setAcc(rs.getInt(5));
				s.setPwd(rs.getString(6));
			}
			return s;
			
			
		}catch (Exception e) {
			
		}
		return null;
	}

	
	public Job stuJobSel(Integer acc) {
		Connection con =null;	
		PreparedStatement ps =null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		CallableStatement cs =null;
		Integer i = null;
		try {
			con=DBU.open();
			String sql = "select stu_id from EmploymentCenter.dbo.Student where acc="+acc;
			ps= con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				i=rs.getInt(1);
			}
			
			cs  = con.prepareCall("{call EmploymentCenter.dbo.stuJobSel(?)}");
			cs.setInt(1,i);
			
			cs.executeQuery();
			rs1 = cs.getResultSet();
			Job j = new Job();
			while(rs1.next()) {
				
				j.setTime(rs1.getDate(1));
				j.setSalary(rs1.getInt(2));
				j.setContract(rs1.getInt(3));
				Company c = new Company();
				c.setName(rs1.getString(4));
				c.setAdd(rs1.getString(5));
				c.setTel(rs1.getString(6));
				c.setDetail(rs1.getString(7));
				
				j.setC(c);
				
			}
			return j;
			
			
		}
		catch (Exception e) {
			
		}finally {
			DBU.close(con);
		}
		
		
		return null;
	}
	
	

}
