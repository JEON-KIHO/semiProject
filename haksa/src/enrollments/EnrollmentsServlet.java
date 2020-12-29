package enrollments;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/enrollments/list", "/enrollments/insert", "/enrollments/delete", "/enrollments/update"})
public class EnrollmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		EnrollmentsDAO edao = new EnrollmentsDAO();
		switch(request.getServletPath()) {
		case "/enrollments/list" :
			out.println(edao.list(request.getParameter("code")));
			break;
		case "/enrollments/insert" :
			int cnt = edao.insert(request.getParameter("scode"), request.getParameter("lcode"));
			out.println(cnt);
			break;
		case "/enrollments/delete" :
			edao.delete(request.getParameter("scode"), request.getParameter("lcode"));
			break;
		case "/enrollments/update" :
			EnrollmentsVO evo = new EnrollmentsVO();
			evo.setGrade(Integer.parseInt(request.getParameter("grade")));
			evo.setScode(request.getParameter("scode"));
			evo.setLcode(request.getParameter("lcode"));
			edao.update(evo);
			break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnrollmentsDAO edao = new EnrollmentsDAO();
		switch(request.getServletPath()) {
		case "/enrollments/update" :
			EnrollmentsVO evo = new EnrollmentsVO();
			evo.setGrade(request.getParameter("grade")==null?0:Integer.parseInt(request.getParameter("grade")));
			evo.setScode(request.getParameter("scode"));
			evo.setLcode(request.getParameter("lcode"));
			edao.update(evo);
			break;
		}
	}

}
