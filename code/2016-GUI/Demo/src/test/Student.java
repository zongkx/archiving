package test;
import java.io.Serializable;
public class Student implements Serializable{
	private static final long serialVersionUID =4558876142427402513L;
	private String  name;
	private long id;
	private double fractionOS;
	private double fractionJava;
	private double fractionMath;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getFractionOS() {
		return fractionOS;
	}
	public void setFractionOS(double fractionOS) {
		this.fractionOS = fractionOS;
	}
	public double getFractionJava() {
		return fractionJava;
	}
	public void setFractionJava(double fractionJava) {
		this.fractionJava = fractionJava;
	}
	public double getFractionMath() {
		return fractionMath;
	}
	public void setFractionMath(double fractionMath) {
		this.fractionMath = fractionMath;
	}
	
	public String toString() {
		return (name+"    "+id+"  Java:"+fractionJava+
				"  Math:"+fractionMath+"  OS:"+fractionOS);
	}
	
	
	
}
