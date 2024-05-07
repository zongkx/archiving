package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Sql {
	
	public static String listToString(List<Student> list, char separator) {//使list返回String类型并加入控制符（换行）    
		 StringBuilder sb = new StringBuilder();   
		 for (int i = 0; i < list.size(); i++) {        
			 sb.append(list.get(i)).append(separator);   
		 }   
		 return sb.toString().substring(0,sb.toString().length()-1);
}
	
	public static List<Student>  disAll() {//打印显示所有信息

		
		//查询 
		Connection conn=DBUtil.open();
		String sql="select id,name,frctionJava,fractionMath,fractionOS from studentTbl";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			List<Student> list=new ArrayList<Student>();
		//遍历操作
			while(rs.next()){
				int id=rs.getInt(1);
				String name=rs.getString(2);
				double a=rs.getDouble(3);
				double b=rs.getDouble(4);
				double c=rs.getDouble(5);
				Student u=new Student();
				u.setId(id);
				u.setName(name);
				u.setFractionJava(a);
				u.setFractionMath(b);
				u.setFractionOS(c);
				list.add(u);
				}
			conn.close();
			
			return list;   
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
		
	}
	
	public static void FindByID(long id ,JLabel lblNewLabel ){//按ID查找
		//long id=Long.parseLong(s);		
		Connection conn=DBUtil.open();
		ResultSet rs = null;
		try {
			String sql="select * from StudentTbl where id=?";
			PreparedStatement sts=conn.prepareStatement(sql);
			sts.setLong(1, id);
			rs=sts.executeQuery();
	
			while(rs.next()){
				int i=rs.getInt(1);
				String name=rs.getString(2);
				double a=rs.getDouble(3);
				double b=rs.getDouble(4);
				double c=rs.getDouble(5);
				lblNewLabel.setText("  | "+i+"  |  "+name+"  |  "+a+"   |  "+b+"  | "+c);
		
					}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs.first()!=true)
					lblNewLabel.setText("~~~~~~~查无此人！~~~~~~~~");	
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
   
		}
	}

	
	public static void FindByName(String s, JLabel lblNewLabel) {//按Name查找
		Connection conn=DBUtil.open();
		ResultSet rs = null;
		try {
			String sql="select * from StudentTbl where name=?";
			PreparedStatement sts=conn.prepareStatement(sql);
			sts.setString(1, s);
			rs=sts.executeQuery();
			
			while(rs.next())
			{
				int id=rs.getInt(1);
				String name=rs.getString(2);
				double a=rs.getDouble(3);
				double b=rs.getDouble(4);
				double c=rs.getDouble(5);
				lblNewLabel.setText("  | "+id+"  |  "+name+"  |  "+a+"   |  "+b+"  | "+c);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(rs.first()!=true)
					lblNewLabel.setText("~~~~~~~查无此人！~~~~~~~~");	
	 				
			
	 		} catch (SQLException e) 
	 			{
				e.printStackTrace();
	 			}

		}
		
	}
	
	public static void DeleteByID(long id){//删除

		Connection conn=DBUtil.open();
		PreparedStatement sts=null;
		
		int a=0;
		try {
			sts=conn.prepareStatement("delete  from StudentTbl where id=?");
			sts.setLong(1, id);
			a=sts.executeUpdate();
			if(a>0)
				JOptionPane.showMessageDialog(null, "删除成功！", "WO", JOptionPane.INFORMATION_MESSAGE);
			if(a==0)
				JOptionPane.showMessageDialog(null, "无此人！", "WO", JOptionPane.INFORMATION_MESSAGE);
			}catch(Exception e) {
				e.printStackTrace();
			}
			DBUtil.close(conn);
	}

	

	public static void Sort(JTextArea ta,int index) {//排序

			
			//查询 
			Connection conn=DBUtil.open();
			String sql="select id,name,frctionJava,fractionMath,fractionOS from studentTbl";
			Statement stmt;
			try {
				stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery(sql);
				List<Student> list=new ArrayList<Student>();
			//遍历操作
				while(rs.next()){
					int id=rs.getInt(1);
					String name=rs.getString(2);
					double a=rs.getDouble(3);
					double b=rs.getDouble(4);
					double c=rs.getDouble(5);
					Student u=new Student();
					u.setId(id);
					u.setName(name);
					u.setFractionJava(a);
					u.setFractionMath(b);
					u.setFractionOS(c);
					list.add(u);
					}
				
				
				
		    	Collections.sort(list,new Comparator<Student>() 
					{  
						 public int compare(Student o1, Student o2) 
						 {  
							 int i = 1 ;
							 if(index==1)
								 {i= (int) (o2.getFractionJava() - o1.getFractionJava());  } 
							 if(index==2){
								  i = (int) (o2.getFractionMath() - o1.getFractionMath());
							 }
							 if(index==3){
								 i = (int) (o2.getFractionOS() - o1.getFractionOS());
							 }
							 return i; 
						 }  
				});
			
				String s=listToString(list, '\n');
				ta.setText(s);
		    	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			DBUtil.close(conn);
			
			
		}  
	
	public static void add(Student stu) {//添加
		Connection conn=DBUtil.open();
		PreparedStatement sts;
		try {
			sts = conn.prepareStatement("insert into StudentTbl values(?,?,?,?,?)");
			sts.setLong(1, stu.getId());
			sts.setString(2, stu.getName());
			sts.setDouble(3, stu.getFractionJava());
			sts.setDouble(4, stu.getFractionMath());
			sts.setDouble(5, stu.getFractionOS());
			sts.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		
		DBUtil.close(conn);}

    public static void UpdateStudent(Student stu){//更新

		
		Connection conn=DBUtil.open();
		
		try {
			String sql="update studenttbl set name='"+stu.getName()+"',FrctionJava='"+ stu.getFractionJava()+"',FractionMath='"+stu.getFractionMath()+"',FractionOS='"+stu.getFractionOS()+"'  where id='"+stu.getId()+"'";
			
			PreparedStatement sts=conn.prepareStatement(sql);
			
			sts.executeUpdate();
	
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}	