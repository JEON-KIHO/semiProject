package calendar;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/insert")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CalendarDAO dao = new CalendarDAO();
		CalendarVO vo = new CalendarVO();
		GregorianCalendar cal = new GregorianCalendar();
		switch(request.getServletPath()) {
		case "/insert" :
			for (int y = 2020; y <= 2021; y++) {
				cal.set(GregorianCalendar.YEAR, y);
				for (int m = 0; m < 12; m++) {
					cal.set(GregorianCalendar.MONTH, m);
					cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
					int maxDay = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
					for (int d = 1; d <=maxDay; d++) {
						cal.set(GregorianCalendar.DAY_OF_MONTH, d);
						int dow = cal.get(GregorianCalendar.DAY_OF_WEEK);
						int wom = cal.get(GregorianCalendar.WEEK_OF_MONTH);
						vo.setYear(y);
						vo.setMonth(m);
						vo.setWom(wom);
						vo.setDow(dow);
						vo.setDay(d);
						dao.insert(vo);
					}
				}
			}
			break;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
