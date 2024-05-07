package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Factory;
import DAO.ICourseDAO;



public class deleteCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String  strdelNo =  request.getParameter("delNo");
		int delNo = Integer.parseInt(strdelNo);
		ICourseDAO cd=new  Factory().getCourseInstance();
		cd.deleteCourse(delNo);
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}

}
