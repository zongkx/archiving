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
import DAO.ITeacherDAO;
import VO.Job;
import VO.Student;


public class teaSel extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.setCharacterEncoding("utf-8");
			
			HttpSession session0 =request.getSession();
			String  acc1= (String) session0.getAttribute("acc");
			//Integer acc = Integer.parseInt(acc1);
		//1	学生所有信息
			ITeacherDAO icd = new Factory().getTeacherInstance();
			List<Student> list = new ArrayList<Student>(); 
			list =icd.StuAllSel();
			request.setAttribute("StuAllSel",list);
			
		//2	按学院查看就业信息
			List<Job> list2 = new ArrayList<Job>();
			list2 = icd.JobAllSel();
			request.setAttribute("JobAllSel",list2);
			request.setAttribute("acc", acc1);
			request.getRequestDispatcher("/teacher.jsp").forward(request, response);
		}
//	}

}
