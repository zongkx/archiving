package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DBU;
import VO.Company;
import VO.Job;
import VO.JobFair;
import VO.Student;
import VO.Teacher;

public class AdmDAOImpl implements IAdmDAO{

	
	public ArrayList<Company> ComAllSel() {
		Connection con =null;

		ResultSet rs = null;
		CallableStatement cs =null;
		try {
			con = DBU.open();
			cs = con.prepareCall("{call EmploymentCenter.dbo.ComAllSel()}");
			cs.executeQuery();
			rs = cs.getResultSet();
			ArrayList<Company> list = new ArrayList<Company>(); 
			while(rs.next()) {
				Company c = new Company();
				c.setAcc(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setAdd(rs.getString(3));
				c.setTel(rs.getString(4));
				c.setDetail(rs.getString(5));
				list.add(c);
			}
			return list;
			
			
		}catch (Exception e) {
		
		}
		return null;
	}

	
	public List<JobFair> JobFairAllSel() {
		Connection con =null;

		ResultSet rs = null;
		CallableStatement cs =null;
		try {
			con = DBU.open();
			cs = con.prepareCall("{call EmploymentCenter.dbo.jobFairAllSel()}");
			cs.executeQuery();
			rs = cs.getResultSet();
			List<JobFair> list = new ArrayList<JobFair>(); 
			while(rs.next()) {
				JobFair jf = new JobFair();
				Company c = new Company();
				jf.setDate(rs.getString(1));
				jf.setAdd(rs.getString(2));
				jf.setDetail(rs.getString(3));
				c.setName(rs.getString(4));
				c.setAdd(rs.getString(5));
				c.setTel(rs.getString(6));
				c.setDetail(rs.getString(7));
				jf.setId(rs.getInt(8));
				jf.setCom(c);
				list.add(jf);
			}
			return list;
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}


	
	public List<Teacher> TeaAllSel() {
		Connection con =null;

		ResultSet rs = null;
		CallableStatement cs =null;
		try {
			con = DBU.open();
			cs = con.prepareCall("{call EmploymentCenter.dbo.TeaAllSel()}");
			cs.executeQuery();
			rs = cs.getResultSet();
			List<Teacher> list = new ArrayList<Teacher>(); 
			while(rs.next()) {
				Teacher t  =new Teacher();
				t.setAcc(rs.getInt(1));
				t.setPwd(rs.getString(2));
				t.setName(rs.getString(3));
				t.setAdd(rs.getString(4));
				t.setTel(rs.getString(5));
				t.setAca(rs.getString(6)); 
				
				list.add(t);
			}
			return list;
			
			
		}catch (Exception e) {
		
		}
		return null;
	}


	
	public List<Student> StuAllSel() {
		List< Student > list = new ArrayList<Student>();
		list =new  Factory().getTeacherInstance().StuAllSel();
		
		return list;
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
			
			while(rs.next()) {
				Job j =  new Job();
				j.setJid(rs.getInt(1));
				j.setCom_acc(rs.getInt(2));
				j.setStu_acc(rs.getInt(3));
				
				j.setTime(rs.getDate(7));
				j.setSalary(rs.getInt(8));
				j.setContract(rs.getInt(9));
				list.add(j);
			}
			
			return list;
		}catch (Exception e) {
		
		}
		return null;
	}


	
	public String ComAllUpd(Company c) {
		Connection con =null;

		ResultSet rs = null;
		CallableStatement cs =null;
		try {
			con = DBU.open();
			cs= con.prepareCall("{call EmploymentCenter.dbo.ComAllUpd(?,?,?,?,?)}");
			cs.setInt(1, c.getAcc());
			cs.setString(2, c.getName());
			cs.setString(3, c.getAdd());
			cs.setString(4, c.getTel());
			cs.setString(5, c.getDetail());
			cs.executeQuery();
			rs =cs.getResultSet();
			if(rs.next())
				{return "ok";}
		}catch (Exception e) {
		
		}
		return null;
	}
	public String TeaAllUpd(Teacher c) {
		Connection con =null;

		ResultSet rs = null;
		CallableStatement cs =null;
		try {
			con = DBU.open();
			cs= con.prepareCall("{call EmploymentCenter.dbo.TeaAllUpd(?,?,?,?,?)}");
			cs.setInt(1, c.getAcc());
			cs.setString(2, c.getName());
			cs.setString(3, c.getAdd());
			cs.setString(4, c.getTel());
			cs.setString(5, c.getAca());
			cs.executeQuery();
			rs =cs.getResultSet();
			if(rs.next())
				{return "ok";}
		}catch (Exception e) {
		
		}
		return null;
	}



	public String JobAllAdd(Job j) {
		
		Connection con =null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		CallableStatement cs =null;
		try {
			con = DBU.open();
			String sql="select stu_id,com_id from EmploymentCenter.dbo.student,EmploymentCenter.dbo.company where student.acc=? and company.acc=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, j.getStu_acc());
			ps.setInt(2, j.getCom_acc());
			
			rs=ps.executeQuery();
			Integer sid = null;
			Integer cid = null;
			if(rs.next())
			{
				sid= rs.getInt(1);
				cid= rs.getInt(2);
			}
			cs= con.prepareCall("{call EmploymentCenter.dbo.JobAllAdd(?,?,?,?,?)}");
			cs.setInt(1, sid);
			cs.setInt(2,cid);
			cs.setString(3,  j.getTime1());
			cs.setInt(4, j.getSalary());
			cs.setInt(5, j.getContract());
			cs.executeQuery();
			rs =cs.getResultSet();
			if(rs.next())
				{return "ok";}
		}catch (Exception e) {
		
		}
		return null;
	}


	
	public String JobAllDel(Integer jid) {
		Connection con =null;

		ResultSet rs = null;
		CallableStatement cs =null;
		try {
			con = DBU.open();
			cs= con.prepareCall("{call EmploymentCenter.dbo.JobAllDel(?)}");
			cs.setInt(1, jid);
			cs.executeQuery();
			rs =cs.getResultSet();
			if(rs.next())
				{return "ok";}
		}catch (Exception e) {
			return null;
		}
		return null;
		
		
		
	}


	 
	public String StuAllDel(Integer sid) {
		Connection con =null;

		ResultSet rs = null;
		CallableStatement cs =null;
		try {
			con = DBU.open();
			cs= con.prepareCall("{call EmploymentCenter.dbo.StuAllDel(?)}");
			cs.setInt(1, sid);
			cs.executeQuery();
			rs =cs.getResultSet();
			if(rs.next())
				{return "ok";}
		}catch (Exception e) {
			return null;
		}
		return null;
		
		
	}


	 
	public String JobFairDel(Integer jfid) {
		 
		Connection con =null;

		ResultSet rs = null;
		CallableStatement cs =null;
		try {
			con = DBU.open();
			cs= con.prepareCall("{call EmploymentCenter.dbo.JobFairAllDel(?)}");
			cs.setInt(1, jfid);
			cs.executeQuery();
			rs =cs.getResultSet();
			if(rs.next())
				{return "ok";}
		}catch (Exception e) {
			return null;
		}
		return null;
		
	}


	 
	
	
	
	public List<JobFair> JobFairAllSel1(Integer pageNo) {
		Connection con =null;

		ResultSet rs = null;
		 
		PreparedStatement ps = null;
		int pageSize = 5;
		int startRecno = (pageNo - 1)*pageSize;
		
		try {
			con = DBU.open();
			String sql ="select  top "+pageSize
					+"  fair_time,fair_add,fair_detail,com_name,com_add,com_tel,com_detail ,job_fair.fair_id  from  EmploymentCenter.dbo.job_fair, EmploymentCenter.dbo.company"
					+ "  where job_fair.com_id = company.com_id AND fair_id not in  (Select top "
					+startRecno+"  fair_id  from  EmploymentCenter.dbo.job_fair ) order by fair_time  DESC";
			
			ps = con.prepareStatement(sql);
			rs= ps.executeQuery();
			
			List<JobFair> list = new ArrayList<JobFair>(); 
			while(rs.next()) {
				JobFair jf = new JobFair();
				Company c = new Company();
				jf.setDate(rs.getString(1));
				jf.setAdd(rs.getString(2));
				jf.setDetail(rs.getString(3));
				c.setName(rs.getString(4));
				c.setAdd(rs.getString(5));
				c.setTel(rs.getString(6));
				c.setDetail(rs.getString(7));
				jf.setId(rs.getInt(8));
				jf.setCom(c);
				list.add(jf);
			}
			return list;
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
		 
	}
	
	public int getPageCount() throws SQLException{
		Connection conn =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int recordCount=0,t1=0,t2=0;
		try {
			conn = DBU.open();
			String sql = "select count(*) from  EmploymentCenter.dbo.job_fair";
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


	 
	public String JobFairAllUpd(JobFair jf) {
		Connection con =null;

		ResultSet rs = null;
		CallableStatement cs =null;
		try {
			con = DBU.open();
			cs= con.prepareCall("{call EmploymentCenter.dbo.JobFairAllUpd(?,?,?,?)}");
			cs.setInt(1, jf.getId());
			cs.setString(2, jf.getAdd());
			cs.setString(3, jf.getDate());
			cs.setString(4, jf.getDetail());
			
			cs.executeQuery();
			rs =cs.getResultSet();
			if(rs.next())
				{return "ok";}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null; 
	}
}
