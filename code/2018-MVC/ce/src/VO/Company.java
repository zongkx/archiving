package VO;

public class Company {
	Integer acc = null;
	public Integer getAcc() {
		return acc;
	}
	public void setAcc(Integer acc) {
		this.acc = acc;
	}

	String name = null;
	String add = null;
	String tel = null;
	String detail = null;
	
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public String toString() {
		return  name + "   " + add + "   " + tel + "   " + detail + "  ";
	}
	
}
