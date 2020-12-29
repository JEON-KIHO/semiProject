package kakao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/avgStudents", "/avgCourses", "/deptCount"})
public class ChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ChartDAO dao = new ChartDAO();
		switch(request.getServletPath()) {
			case "/avgStudents" :
				out.println(dao.avgStudents());
				break;
			case "/avgCourses" :
				out.println(dao.avgCourses());
				break;
			case "/deptCount" :
				out.println(dao.deptCount());
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
