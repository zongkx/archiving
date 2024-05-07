package servlet;

import java.io.IOException;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import DAO.Factory;
import DAO.ICourseDAO;
import VO.Course;


public class changeCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String  scNo =  request.getParameter("cid");
		int cNo = Integer.parseInt(scNo);
		
		String  name =  request.getParameter("cname");
		String  date =  request.getParameter("cdate");
		String  teacher =  request.getParameter("cteacher");
		long t  =Integer.parseInt(teacher);
		Course c  =  new Course();
		c.setId(cNo);
		c.setName(name);
		c.setDate(date);
		c.setTeacher(t);
		
		
		ICourseDAO cd=new  Factory().getCourseInstance();
		cd.changeCourse(c);
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

