package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DB.DBU;
import VO.Account;

public class AccountDAOImpl implements IAccountDAO {
	public Account logincheck(Account a) {
		Connection con =null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBU.open();
			String sql = "select acc,pwd from EmploymentCenter.dbo."+a.getTable()+" where acc =? and pwd = ?";
			
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, a.getAcc());
			ps.setString(2, a.getPwd());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				a.setInfo("welcome");
				return a;
			}
			else {
				a.setInfo("’ ∫≈√‹¬Î”–ŒÛ");
				return a;
			}
		}catch (Exception e) {
				
		}
		return null;}

	
	public Account changeAcc(Account a) {
		Connection con =null;
		
		PreparedStatement ps = null;
		
		try {
			con = DBU.open();
			String sql ="update  EmploymentCenter.dbo."+a.getTable()+" set pwd="+a.getPwd()+" where acc= "+a.getAcc();
			ps =con.prepareStatement(sql);
			
			ps.executeUpdate();
			
				
			
		}catch (Exception e) {
			
		}
		
		
		return null;
	}


	
	public String AccAllDel(Integer acc, String table) {
		Connection con =null;
		PreparedStatement ps = null;
		try {
			con = DBU.open();
			String sql ="delete  EmploymentCenter.dbo."+table+ " where acc= "+acc;
			ps =con.prepareStatement(sql);
			
			ps.executeUpdate();
		}catch (Exception e) {
			
		}
		
		
		return null;
	}


	
	public String AccAllAdd(Integer acc, String table) {
		Connection con =null;
		PreparedStatement ps = null;
		PreparedStatement ps0 = null;
		ResultSet rs =null;
		try {
			con = DBU.open();
		
			String sql0 = "select acc from EmploymentCenter.dbo."+table+" where acc = "+acc;
			ps0=con.prepareStatement(sql0);
			rs= ps0.executeQuery();
			if(rs.next())
			{
				return "error";
			}
			else {
				String sql ="insert into   EmploymentCenter.dbo."+table+ " (acc,pwd) values(?,?)";
				ps =con.prepareStatement(sql);
				ps.setInt(1, acc);
				ps.setString(2, "123");
				ps.executeUpdate();
				return "ok";
				
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return null;
	}


	 
	public String AccAdmAdd(Account a) {
		Connection con =null;

		ResultSet rs = null;
		CallableStatement cs =null;
		try {
			con = DBU.open();
			cs= con.prepareCall("{call EmploymentCenter.dbo.AccAdmAdd(?,?,?)}");
			cs.setInt(1, a.getAcc());
			cs.setString(2, a.getPwd());
			cs.setString(3, a.getInfo());
			 
			
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
		
		

