package professors;

import java.sql.Date;

public class ProfessorsVO {
	private int rn;
	private String pcode;
	private String pname;
	private String dept;
	private Date hiredate;
	private String title;
	private int salary;
	
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "ProfessorsVO [rn=" + rn + ", pcode=" + pcode + ", pname=" + pname + ", dept=" + dept + ", hiredate="
				+ hiredate + ", title=" + title + ", salary=" + salary + "]";
	}
	
}
