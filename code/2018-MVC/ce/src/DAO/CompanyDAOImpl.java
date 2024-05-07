package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DB.DBU;
import VO.Job;
import VO.JobFair;
import VO.Student;

public class CompanyDAOImpl implements ICompanyDAO{

	
	public List<Job> comJobSel(Integer acc) {
		Connection con =null;

		ResultSet rs = null;
		CallableStatement cs =null;
		try {
			
			con = DBU.open();
			cs= con.prepareCall("{call EmploymentCenter.dbo.comJobSel(?)}");
			cs.setInt(1, acc);
			cs.executeQuery();
			rs = cs.getResultSet();
			List<Job> list = new ArrayList<Job>();
			while(rs.next()) {
				Job j  =new Job();
				
				j.setTime(rs.getDate(1));
				j.setSalary(rs.getInt(2));
				j.setContract(rs.getInt(3));
				
				
				
				list.add(j);
				
			}
			return list;
		}catch (Exception e) {
				
			}finally {
				DBU.close(con);
			}
		return null;
		
	}
	public List<Student> comJobSel1(Integer acc) {
		Connection con =null;
		ResultSet rs = null;
		CallableStatement cs =null;
		try {
			con = DBU.open();
			cs= con.prepareCall("{call EmploymentCenter.dbo.comJobSel(?)}");
			cs.setInt(1, acc);
			cs.executeQuery();
			rs = cs.getResultSet();
			List<Student> list = new ArrayList<Student>();
			while(rs.next()) {
				
				Student s = new Student();
				
				s.setName(rs.getString(4));
				s.setAge(rs.getInt(5));
				s.setGrade(rs.getBigDecimal(6));
				s.setAca(rs.getString(7));
				
				
				list.add(s);
				
			}
			return list;
		}catch (Exception e) {
				
			}finally {
				DBU.close(con);
			}
		return null;
		
	}
	
	public List<JobFair> comJobFairSel(Integer acc) {
		Connection con =null;
		ResultSet rs = null;
		CallableStatement cs =null;
		try {
			con = DBU.open();
			cs= con.prepareCall("{call EmploymentCenter.dbo.comJobFairSel(?)}");
			cs.setInt(1, acc);
			cs.executeQuery();
			rs = cs.getResultSet();
			List<JobFair> list = new ArrayList<JobFair>();
			
			while(rs.next()) {
				
				JobFair jf = new JobFair();
				jf.setDate(rs.getString(1));
				jf.setAdd(rs.getString(2));
				jf.setDetail(rs.getString(3));
				list.add(jf);
				
			}
			return list;
		}catch (Exception e) {
				
			}finally {
				DBU.close(con);
			}
		return null;
}
	
	public String comJonFairAdd(Integer acc,JobFair jf) {
		Connection con =null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs =null;
		Integer id = null;
		try {
			con = DBU.open();
			String sql = "select com_id from EmploymentCenter.dbo.company where acc= ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, acc);
			rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt(1);
			}
			cs = con.prepareCall("{call EmploymentCenter.dbo.comJobFairAdd(?,?,?,?)}");
			cs.setInt(1, id);
			cs.setString(2, jf.getDate());
			cs.setString(3, jf.getAdd());
			cs.setString(4, jf.getDetail());
			cs.executeUpdate();
		}catch (Exception e) {
				
			}finally {
				DBU.close(con);
			}
		
		return null;
	}
	





}
