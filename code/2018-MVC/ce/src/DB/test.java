package DB;


import java.text.ParseException;

import java.util.ArrayList;

import java.util.List;

import DAO.AccountDAOImpl;
import DAO.AdmDAOImpl;
import DAO.CompanyDAOImpl;
import DAO.StudentDAOImpl;
import DAO.TeacherDAOImpl;
import VO.Account;
import VO.Company;
import VO.Job;
import VO.JobFair;
import VO.Student;

public class test {
	public static void main(String[] args) throws ParseException {

		 testJ();
		
		
	}
	  void testA() {
		 StudentDAOImpl s =new StudentDAOImpl();
//		 s.stuSel(111);
		
		 Student s1= new Student();
		 Integer i =111;
		 s1= s.stuSel(i);
		 System.out.println(s1.getGrade());
		 
	 }
	 static void testB() {
		 StudentDAOImpl s =new StudentDAOImpl();
		 Job j =new Job();
		 j= s.stuJobSel(111);
		 System.out.println("FFF");
		 System.out.println(j.getTime());
	 }
	 static void testC() {
		 AdmDAOImpl ad = new AdmDAOImpl();
		 List<Company> list = new ArrayList<Company>(); 
		 list= ad.ComAllSel();
		 System.out.println(list);
	 }
	 static void testD() {
		 AdmDAOImpl ad = new AdmDAOImpl();
		 List<JobFair> list = new ArrayList<JobFair>(); 
		 list= ad.JobFairAllSel();
		 System.out.println(list);
	 }
	 static void testE() {
		 AccountDAOImpl ac = new AccountDAOImpl();
		 //List<JobFair> list = new ArrayList<JobFair>(); 
//		  ad.JobFairAllSel();
		 Account a = new Account();
		 a.setAcc(111);
		 a.setTable("Teacher");
		 a.setPwd("1234");
		 ac.changeAcc(a);
		 
		
	 }
	 static void testG() {
		 CompanyDAOImpl cd = new CompanyDAOImpl();
		 List<Job> list = new ArrayList<Job>();
		 list=cd.comJobSel(111);
		 System.out.println(list);
		 
		 
	 }
	 static void testH() {
		 CompanyDAOImpl cd = new CompanyDAOImpl();
		 List<JobFair> list = new ArrayList<JobFair>();
		 list=cd.comJobFairSel(222);
		 System.out.println(list);
		 
		 
	 }
	 static void testI() throws ParseException {
		 CompanyDAOImpl cd = new CompanyDAOImpl();
		 JobFair jf =new JobFair();
		 jf.setAdd("henan");
		 jf.setDate("2017-1-1");
		 jf.setDetail("ssss");
		 cd.comJonFairAdd(111, jf);
		 
		 
		 
	 }
	 static void testJ()  {
		TeacherDAOImpl td = new TeacherDAOImpl();
		td.JobAllSel();
	 }
}
