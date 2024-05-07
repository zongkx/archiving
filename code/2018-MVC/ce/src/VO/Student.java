package VO;

import  java.math.BigDecimal  ;

public class Student {
	
	Integer id =null;
	Integer acc  = null;
	String pwd = null;
	String name =null;
	Integer age = null;
	BigDecimal grade ;
	String aca = null;
	
	
	
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAcc() {
		return acc;
	}
	public void setAcc(Integer acc) {
		this.acc = acc;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public BigDecimal getGrade() {
		return grade;
	}
	public void setGrade(BigDecimal bigDecimal) {
		this.grade = bigDecimal;
	}
	public String getAca() {
		return aca;
	}
	public void setAca(String aca) {
		this.aca = aca;
	}
	
	
	
}
