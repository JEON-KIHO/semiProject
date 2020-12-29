package students;

import java.sql.Date;

import professors.ProfessorsVO;

public class StudentsVO extends ProfessorsVO{
	private String scode;
	private String sname;
	private String dept;
	private int year;
	private Date birthday;
	private int advisor;
	
	public String getScode() {
		return scode;
	}
	public void setScode(String scode) {
		this.scode = scode;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getAdvisor() {
		return advisor;
	}
	public void setAdvisor(int advisor) {
		this.advisor = advisor;
	}
	
	@Override
	public String toString() {
		return "StudentsVO [scode=" + scode + ", sname=" + sname + ", dept=" + dept + ", year=" + year + ", birthday="
				+ birthday + ", advisor=" + advisor + "]";
	}
	
}
