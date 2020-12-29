package calendar;

import java.util.GregorianCalendar;

public class Calendar2 {

	public static void main(String[] args) {
		GregorianCalendar cal = new GregorianCalendar();
		int dow;
		int m = 0;

		
		cal.set(GregorianCalendar.YEAR, 2020);
		while (m < 12) {
			cal.set(GregorianCalendar.MONTH, m);
			System.out.println((m + 1) + "월");
			cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
			int maxday = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
			for (int d = 1; d <= maxday; d++) {
				cal.set(GregorianCalendar.DAY_OF_MONTH, d);
				dow = cal.get(GregorianCalendar.DAY_OF_WEEK);
				int wod = cal.get(GregorianCalendar.WEEK_OF_MONTH);

				if (cal.get(GregorianCalendar.DAY_OF_MONTH) == 1) {
					if (dow == 1) {
						System.out.print(d + "\t");
					} else if (dow > 1 && dow < 7) {
						for (int i = 1; i < dow; i++) {
							System.out.print("\t");
						}
						System.out.print(d + "\t");
					} else if (dow == 7){
						for (int i = 1; i < dow; i++) {
							System.out.print("\t");
						}
						System.out.print(d + "\n");
					}
				} else {
					if (dow == 7) {
						System.out.print(d + "\n");
					} else {
						System.out.print(d + "\t");
					}
				}
			}
			System.out.println("\n");
			m++;
		}
	}
}
