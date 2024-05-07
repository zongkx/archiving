package VO;



public class JobFair {
	Integer id =null;
	String add = null;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setCom(String com) {
		this.com = com;
	}
	String date = null;
	String detail = null;
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	String com = null;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public String getCom() {
		return com;
	}
	public void setCom(Company com) {
		this.com = com.toString();
	}
	
}
