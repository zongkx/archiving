package VO;



public class Course {
	int id;
	String name;
	String date;
	Long teacher;
	public Course(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getTeacher() {
		return teacher;
	}
	public void setTeacher(Long teacher) {
		this.teacher = teacher;
	}
	
}
