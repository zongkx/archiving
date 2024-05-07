package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.taglibs.standard.lang.jstl.test.beans.Factory;

//import DAO.AccountDAOImpl;
import DAO.Factory;
import DAO.IAccountDAO;
import VO.Account;



public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		request.setCharacterEncoding("utf-8");
		
		String  acc1= request.getParameter("acc");
		Integer acc = Integer.parseInt(acc1);
		String pwd = request.getParameter("pwd");
		String table = request.getParameter("table");
		System.out.println(table);
		
		IAccountDAO ad = new Factory().getAccountInstance();

		Account a = new Account();
		a.setAcc(acc);
		a.setPwd(pwd);
		a.setTable(table);
		Account a2  = new Account();
		a2 =ad.logincheck(a);
		
		HttpSession session =request.getSession();
        if(a2.getInfo()=="welcome") {
			
			
			if(table.equals("Teacher"))
				{
				session.setAttribute("acc", acc1);
				request.getRequestDispatcher("/teaSel").forward(request, response);}
			if(table.equals("Student")) {
			
				session.setAttribute("acc", acc1);
				request.getRequestDispatcher("/stuSel").forward(request, response);}
			if(table.equals("Company")) {
				
				session.setAttribute("acc", acc1);
				request.getRequestDispatcher("/comSel").forward(request, response);}
			if(table.equals("Adm")) {
				
				session.setAttribute("acc", acc1);
				request.getRequestDispatcher("/admSel").forward(request, response);}
		}
		else {
			request.setAttribute("info",a2.getInfo());
			request.getRequestDispatcher("/first.jsp").forward(request, response);
		}
		
		
	
	}

}
