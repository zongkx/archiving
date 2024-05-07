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

import DAO.ICompanyDAO;


import VO.Job;
import VO.JobFair;
import VO.Student;


public class comSel extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.setCharacterEncoding("utf-8");
			HttpSession session0 =request.getSession();
			String  acc1= (String) session0.getAttribute("acc");
			Integer acc = Integer.parseInt(acc1);
		//1	查看本公司就业签约信息
			ICompanyDAO icd = new Factory().getCompanyInstance();
			
			List<Job> list = new ArrayList<Job>();
			
			List<Student> list2 = new ArrayList<Student>(); 
			
			list =icd.comJobSel(acc);
			list2 = icd.comJobSel1(acc);
			
			request.setAttribute("comJobSel1",list);
			request.setAttribute("comJobSel2", list2);
		//2	查看本公司招聘会信息
			List<JobFair> list3 = new ArrayList<JobFair>();
			list3 = icd.comJobFairSel(acc);
			request.setAttribute("comJobFairSel",list3);

			request.setAttribute("acc", acc1);
			request.getRequestDispatcher("/company.jsp").forward(request, response);
		}
//	}

}
