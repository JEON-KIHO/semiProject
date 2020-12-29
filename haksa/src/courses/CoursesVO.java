package courses;

import java.sql.Date;

import professors.ProfessorsVO;

public class CoursesVO extends ProfessorsVO{
	private String lcode;
	private String lname;
	private int hours;
	private int room;
	private int instructor;
	private int capacity;
	private int persons;
	private String pname;
	private String pcode;
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
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public int getInstructor() {
		return instructor;
	}
	public void setInstructor(int instructor) {
		this.instructor = instructor;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getPersons() {
		return persons;
	}
	public void setPersons(int persons) {
		this.persons = persons;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	@Override
	public String toString() {
		return "CoursesVO [lcode=" + lcode + ", lname=" + lname + ", hours=" + hours + ", room=" + room
				+ ", instructor=" + instructor + ", capacity=" + capacity + ", persons=" + persons + ", pname=" + pname
				+ ", pcode=" + pcode + "]";
	}
	
}
