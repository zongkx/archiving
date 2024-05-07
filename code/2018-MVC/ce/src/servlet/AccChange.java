package servlet;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Factory;
import DAO.IAccountDAO;

import VO.Account;


public class AccChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String flag = request.getParameter("flag");
		if(flag.equals("AccChange")) {
		
		
		String  acc1= request.getParameter("acc");
		Integer acc = Integer.parseInt(acc1);
		String pwd = request.getParameter("pwd");
		String table = request.getParameter("table");
		
		Account a  =new Account();
		a.setAcc(acc);
		a.setTable(table);
		a.setPwd(pwd);
		IAccountDAO ad = new Factory().getAccountInstance();
		ad.changeAcc(a);
		
		
		request.getRequestDispatcher("/first.jsp").forward(request, response);}
		
		
		if(flag.equals("AccAdd")) {
			
			
			String  acc1= request.getParameter("acc");
			Integer acc = Integer.parseInt(acc1);
			String pwd = request.getParameter("pwd");
			 
			String name = request.getParameter("name");
			
			Account a  =new Account();
			a.setAcc(acc);
			a.setInfo(name);
			a.setPwd(pwd);
			IAccountDAO ad = new Factory().getAccountInstance();
			ad.AccAdmAdd(a);
			
			
			request.getRequestDispatcher("/first.jsp").forward(request, response);}
		
	}

}
