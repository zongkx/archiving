package VO;

import java.util.Date;

public class Job {
	Date time =null;
	String time1 = null;
	Integer jid = null;
	
			
			
	public Integer getJid() {
		return jid;
	}
	public void setJid(Integer jid) {
		this.jid = jid;
	}
	public String getTime1() {
		return time1;
	}
	public void setTime1(String time1) {
		this.time1 = time1;
	}
	Integer salary = null;
	Integer contract =null;
	Integer stu_acc =null;
	Integer com_acc =null;
	public Integer getCom_acc() {
		return com_acc;
	}
	public void setCom_acc(Integer com_acc) {
		this.com_acc = com_acc;
	}
	String jobd =null;
	
	
	
	public Integer getStu_acc() {
		return stu_acc;
	}
	public void setStu_acc(Integer stu_acc) {
		this.stu_acc = stu_acc;
	}
	public String getJobd() {
		return jobd;
	}
	public void setJobd(String jobd) {
		this.jobd = jobd;
	}
	Student s = null;
	Company c =null;
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public Integer getContract() {
		return contract;
	}
	public void setContract(Integer contract) {
		this.contract = contract;
	}
	
	public Company getC() {
		return c;
	}
	public void setC(Company c) {
		this.c = c;
	}
	public Student getS() {
		return s;
	}
	public void setS(Student s) {
		this.s = s;
	}
}
