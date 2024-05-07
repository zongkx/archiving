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

import VO.Company;
import VO.Job;
import VO.JobFair;
import VO.Student;
import VO.Teacher;


public class admSel extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.setCharacterEncoding("utf-8");
			HttpSession session0 =request.getSession();
			String  acc1= (String) session0.getAttribute("acc");
			//Integer acc = Integer.parseInt(acc1);
		//1	查看所有学生信息
			IAdmDAO add = new Factory().getAdmInstance();
			List< Student > list = new ArrayList<Student>();
			list =new  Factory().getTeacherInstance().StuAllSel();
			request.setAttribute("StuAllSel", list);
		//2	查看所有教师信息
			List<Teacher> list2= new ArrayList<Teacher>();
			list2 = add.TeaAllSel();
			request.setAttribute("teaList", list2);
		//3	查看所有公司信息
			
			ArrayList<Company> list3 = new ArrayList<Company>(); 
			list3 = add.ComAllSel();
			request.setAttribute("comList", list3);
		//4 查看所有招聘会信息
//			List<JobFair> list4 = new ArrayList<JobFair>(); 
//			list4 = add.JobFairAllSel();
//			request.setAttribute("jbList", list4);
			int pageNo  =1;
			
			String strPageNo = request .getParameter("pageNo");
			if(strPageNo!= null) {
				pageNo = Integer.parseInt(strPageNo);
				
			}
			
			List<JobFair> list4 = new ArrayList<JobFair>(); 
			list4 = add.JobFairAllSel1(pageNo);
			
			Integer pageCount;
			try {
				request.setAttribute("jbList", list4);
				pageCount = new Integer(add.getPageCount());
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("pageNo", pageNo);
			} catch (Exception e) {
				 
				e.printStackTrace();
			}
			
			
		//5查看所有就业信息
			List< Job > list5 = new ArrayList<Job>();
			list5 = add.JobAllSel();
			request.setAttribute("jobList", list5);
			
			request.setAttribute("acc", acc1);
			request.getRequestDispatcher("/adm.jsp").forward(request, response);
		}
//	}

}
