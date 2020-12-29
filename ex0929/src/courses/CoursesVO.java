package courses;

import professors.ProfessorsVO;

public class CoursesVO extends ProfessorsVO {
	private String lcode;
	private String lname;
	private int hours;
	
	public String getLcode() {
		return lcode;
	}
	public void setLcode(String lcode) {
		this.lcode = lcode;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	
	@Override
	public String toString() {
		return "CoursesVO [lcode=" + lcode + ", lname=" + lname + ", hours=" + hours + ", getPname()=" + getPname()
				+ "]";
	}
}
