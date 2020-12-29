package calendar;

import java.util.GregorianCalendar;

public class Calendar {

	public static void main(String[] args) {
//		GregorianCalendar cal = new GregorianCalendar();
//		cal.getCalendarType();
//		
//		String date = "20190215";
//		int y = Integer.parseInt(date.substring(0,4));
//		int m = Integer.parseInt(date.substring(4,6));
//		int d = Integer.parseInt(date.substring(6,8));
//		System.out.println(y +"/"+ m +"/"+ d);
//		
//		int year = cal.get(GregorianCalendar.YEAR);
//		int month = cal.get(GregorianCalendar.MONTH)+1;
//		int week = cal.get(GregorianCalendar.WEEK_OF_MONTH);
//		int day = cal.get(GregorianCalendar.DAY_OF_MONTH);
//		int hourOfDay = cal.get(GregorianCalendar.HOUR_OF_DAY);
//		int hour = cal.get(GregorianCalendar.HOUR);
//		int min = cal.get(GregorianCalendar.MINUTE);
//		int sec = cal.get(GregorianCalendar.SECOND);
//		int DOW = cal.get(GregorianCalendar.DAY_OF_WEEK);
//		
//		cal.set(GregorianCalendar.YEAR, y);
//		cal.set(GregorianCalendar.MONTH, m-1);
//		cal.set(GregorianCalendar.DAY_OF_MONTH, d);
//		int year = cal.get(GregorianCalendar.YEAR);
//		int month = cal.get(GregorianCalendar.MONTH)+1;
//		int day = cal.get(GregorianCalendar.DAY_OF_MONTH);
//		int week = cal.get(GregorianCalendar.WEEK_OF_MONTH);
//		int maxDay = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
//		int DOW = cal.get(GregorianCalendar.DAY_OF_WEEK);
//		
//		System.out.println(year +"/"+ month +"/"+ day +"/"+ week +"/"+ maxDay);
//		System.out.println(hourOfDay +"/"+ hour +"/"+ min +"/"+ sec);
//		System.out.println(DOW);
		
		
		
		System.out.println("\t\t      2020년  11월");
		
		String[] aaa = {"일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"};
		for(int i = 0; i <7; i++) {
			switch(i) {
			case 0 : System.out.print(aaa[i] + "\t"); break;
			case 1 : System.out.print(aaa[i] + "\t"); break;
			case 2 : System.out.print(aaa[i] + "\t"); break;
			case 3 : System.out.print(aaa[i] + "\t"); break;
			case 4 : System.out.print(aaa[i] + "\t"); break;
			case 5 : System.out.print(aaa[i] + "\t"); break;
			case 6 : System.out.print(aaa[i] + "\n"); break;
			}
		}
		
		for(int wom = 1; wom <= 6; wom++) {
			String strWom = wom+"";
			for(int dow = 1; dow <= 7; dow++) {
				String strDow = dow+"";
				String primary = strWom + strDow;
				if(dow==7) {
					System.out.print(primary);
					System.out.println();
				} else {
					System.out.print(primary + "\t");
				}
			}
		}
		
	}
}
