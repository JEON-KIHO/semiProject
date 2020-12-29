package calendar;

public class CalendarVO {
	private int year;
	private int month;
	private int wom;
	private int dow;
	private int day;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getWom() {
		return wom;
	}
	public void setWom(int wom) {
		this.wom = wom;
	}
	public int getDow() {
		return dow;
	}
	public void setDow(int dow) {
		this.dow = dow;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	@Override
	public String toString() {
		return "CalendarVO [year=" + year + ", month=" + month + ", wom=" + wom + ", dow=" + dow + ", day=" + day + "]";
	}
	
}
