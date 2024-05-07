package servlet;

import java.io.IOException;
import java.math.BigDecimal;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import DAO.Factory;
import DAO.IAccountDAO;
import DAO.IAdmDAO;
import DAO.ICompanyDAO;
import DAO.ITeacherDAO;
import VO.Company;
import VO.Job;
import VO.JobFair;
import VO.Student;
import VO.Teacher;


public class InfoChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String flag = request.getParameter("flag");
		if(flag.equals("Com1")) {
			String  acc1= request.getParameter("acc");
			Integer acc = Integer.parseInt(acc1);
		
			JobFair jf = new JobFair();
			jf.setAdd(request.getParameter("add"));
			jf.setDate(request.getParameter("date"));
			jf.setDetail(request.getParameter("detail"));
		
		
			ICompanyDAO  ic = new Factory().getCompanyInstance();
			ic.comJonFairAdd(acc, jf);
			System.out.println("ok");
			request.getRequestDispatcher("comSel").forward(request, response);
		}
		if(flag.equals("changeStu")) {
			String  acc1= request.getParameter("stu_acc");
			Integer acc = Integer.parseInt(acc1);
			String  name= request.getParameter("stu_name");
			String  age1= request.getParameter("stu_age");
			Integer age = Integer.parseInt(age1);
			String  grade1= request.getParameter("stu_grade");
			double grade2=Double.valueOf(grade1).doubleValue(); 
			BigDecimal grade = BigDecimal.valueOf(grade2);//£¨2£©   
			
			String  aca= request.getParameter("stu_aca");
			Student s  = new Student();
			s.setAcc(acc);
			s.setAge(age);
			s.setGrade(grade);
			s.setAca(aca);
			s.setName(name);
			ITeacherDAO itd =  new Factory().getTeacherInstance();
			itd.StuAllUpd(s);
			request.getRequestDispatcher("admSel").forward(request, response);
		}
		if(flag.equals("changeStu1")) {
			String  acc1= request.getParameter("stu_acc");
			Integer acc = Integer.parseInt(acc1);
			String  name= request.getParameter("stu_name");
			String  age1= request.getParameter("stu_age");
			Integer age = Integer.parseInt(age1);
			String  grade1= request.getParameter("stu_grade");
			Double g2 =Double.valueOf(grade1).doubleValue();
			
			
			BigDecimal grade= BigDecimal.valueOf(g2);
			
			String  aca= request.getParameter("stu_aca");
			 
			Student s =new Student();
			s.setAcc(acc);
			s.setAge(age);
			s.setAca(aca);
			s.setName(name);
			s.setGrade(grade);
			ITeacherDAO iad  = new Factory().getTeacherInstance();
			iad.StuAllUpd(s);
			request.getRequestDispatcher("teaSel").forward(request, response);
		}

		if(flag.equals("changeCom")) {
			String  acc1= request.getParameter("com_acc");
			Integer acc = Integer.parseInt(acc1);
			String  name= request.getParameter("com_name");
			String  add= request.getParameter("com_add");
			String  tel= request.getParameter("com_tel");
			String  detail= request.getParameter("com_detail");
			Company c = new Company();
			c.setAcc(acc);
			c.setName(name);
			c.setAdd(add);
			c.setDetail(detail);
			c.setTel(tel);
			IAdmDAO iad  = new Factory().getAdmInstance();
			iad.ComAllUpd(c);
			request.getRequestDispatcher("admSel").forward(request, response);
		}
		if(flag.equals("changeTea")) {
			String  acc1= request.getParameter("tea_acc");
			Integer acc = Integer.parseInt(acc1);
			String  name= request.getParameter("tea_name");
			String  add= request.getParameter("tea_add");
			String  tel= request.getParameter("tea_tel");
			String  aca= request.getParameter("tea_aca");
			Teacher t =new Teacher();
			t.setAcc(acc);
			t.setName(name);
			t.setAdd(add);
			t.setTel(tel);
			
			t.setAca(aca);
			IAdmDAO iad  = new Factory().getAdmInstance();
			iad.TeaAllUpd(t);
			request.getRequestDispatcher("admSel").forward(request, response);
			
		}
		if(flag.equals("changeJobFair")) {
			String  acc1= request.getParameter("jf_id");
			Integer jf_id = Integer.parseInt(acc1);
		 
			String  add= request.getParameter("add");
			String  date= request.getParameter("date");
			String  detail= request.getParameter("detail");
			JobFair jf =new JobFair();
			jf.setAdd(add);
			jf.setId(jf_id);
			jf.setDate(date);
			jf.setDetail(detail);
		 
			 
			IAdmDAO iad  = new Factory().getAdmInstance();
			 iad.JobFairAllUpd(jf);
			request.getRequestDispatcher("admSel").forward(request, response);
			
		}
		if(flag.equals("addJob")) {
			String  acc1= request.getParameter("job_sacc");
			Integer sacc = Integer.parseInt(acc1);
			String  acc2= request.getParameter("job_cacc");
			Integer cacc = Integer.parseInt(acc2);
			String  time= request.getParameter("job_time");
			String  s1= request.getParameter("job_salary");
			Integer salary = Integer.parseInt(s1);
			String  c1= request.getParameter("job_contract");
			Integer contract = Integer.parseInt(c1);
			Job j = new Job();
			j.setStu_acc(sacc);
			j.setCom_acc(cacc);
			j.setSalary(salary);
			j.setContract(contract);
			j.setTime1(time);
			
			IAdmDAO iad  = new Factory().getAdmInstance();
			iad.JobAllAdd(j);
			
			request.getRequestDispatcher("admSel").forward(request, response);
			
		}
		if(flag.equals("delJob")) {
			String  jid1= request.getParameter("jid");
			Integer jid = Integer.parseInt(jid1);
			IAdmDAO iad  = new Factory().getAdmInstance();
			iad.JobAllDel(jid);
			request.getRequestDispatcher("admSel").forward(request, response);
			
		}
		if(flag.equals("addAcc")) {
			String flag1 = request. getParameter("flag1");
			if(flag1.equals("AccDel")) {
				
				String  acc1= request.getParameter("acc");
				String table = request. getParameter("table");
				Integer acc = Integer.parseInt(acc1);
				IAccountDAO iad  = new Factory().getAccountInstance();
				iad.AccAllDel(acc, table);
				request.getRequestDispatcher("admSel").forward(request, response);
				
			}
			if(flag1.equals("AccAdd")) {
				String  acc1= request.getParameter("acc");
				String table = request. getParameter("table");
				Integer acc = Integer.parseInt(acc1);
				IAccountDAO iad  = new Factory().getAccountInstance();
				System.out.println("11");
				iad.AccAllAdd(acc, table);
				request.getRequestDispatcher("admSel").forward(request, response);
			}
			
			
			
		}
		
	}

}