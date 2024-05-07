package servlet;

import java.io.IOException;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import DAO.Factory;
import DAO.ICourseDAO;
import VO.Course;


public class demo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageNo  =1;
		
		String strPageNo = request .getParameter("pageNo");
		if(strPageNo!= null) {
			pageNo = Integer.parseInt(strPageNo);
			
		}
		ICourseDAO cd=new  Factory().getCourseInstance();
		
		try {
			List<Course> courseList = cd.listCourse(pageNo);
			request.setAttribute("courseList", courseList);
			Integer pageCount  = new Integer(cd.getPageCount());
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageNo", pageNo);

			RequestDispatcher rd = request.getRequestDispatcher("/demo.jsp");
			rd.forward(request, response);
		}catch (Exception e) {
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
