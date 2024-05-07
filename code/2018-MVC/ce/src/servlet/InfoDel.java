package servlet;

import java.io.IOException;
 


import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import DAO.Factory;
 
import DAO.IAdmDAO;
 


public class InfoDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String flag = request.getParameter("flag");
		if(flag.equals("stuDel")) {
			String  acc1= request.getParameter("stu_acc");
			Integer acc = Integer.parseInt(acc1);
			
			 
		
		
			IAdmDAO iad =    new Factory().getAdmInstance();
			iad.StuAllDel(acc);
			 
			request.getRequestDispatcher("admSel").forward(request, response);
		}
		if(flag.equals("jfDel")) {
			String  acc1= request.getParameter("jf_id");
			Integer jfid = Integer.parseInt(acc1);
			IAdmDAO iad =    new Factory().getAdmInstance();
			iad.JobFairDel(jfid);
			 
			request.getRequestDispatcher("admSel").forward(request, response);
		}
		 
		 
		
	}

}
