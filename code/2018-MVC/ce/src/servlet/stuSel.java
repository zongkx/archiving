package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import DAO.Factory;
import DAO.IAdmDAO;
import DAO.IStudentDAO;
import VO.Company;
import VO.Job;
import VO.JobFair;
import VO.Student;


public class stuSel extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.setCharacterEncoding("utf-8");
			HttpSession session0 =request.getSession();
			String  acc1= (String) session0.getAttribute("acc");
			Integer acc = Integer.parseInt(acc1);
		//1	查看学生个人信息
			IStudentDAO ad = new Factory().getStudentInstance();
			Student s= new Student();
			s =ad.stuSel(acc);
			request.setAttribute("stuSel", s);
		//2	查血个人就业信息
			Job j =new Job();
			j = ad.stuJobSel(acc);
			Company c = new Company();
			c = j.getC();
			request.setAttribute("stuJobSel1", j);
			request.setAttribute("stuJobSel2", c);
		//3	查看所有公司信息
			IAdmDAO add = new Factory().getAdmInstance();
			ArrayList<Company> list = new ArrayList<Company>(); 
			list = add.ComAllSel();
			request.setAttribute("comList", list);
		//4 查看所有招聘会信息
			List<JobFair> list2 = new ArrayList<JobFair>(); 
			list2 = add.JobFairAllSel();
			request.setAttribute("jbList", list2);
			
			request.setAttribute("acc", acc1);
			request.getRequestDispatcher("/student.jsp").forward(request, response);
		}
//	}

}
