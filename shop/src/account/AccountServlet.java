package account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

@WebServlet(value={"/login", "/logout", "/create"})
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		switch(request.getServletPath()) {
		case "/logout" :
			HttpSession session = request.getSession();
			session.invalidate(); // session value reset
			response.sendRedirect("index.jsp");
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		AccountDAO dao = new AccountDAO();
		switch(request.getServletPath()) {
		case "/login" :
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			AccountVO vo = dao.login(id);
			if(vo.getId()==null) {
				out.print("0"); // none ID
			} else if(!vo.getPw().equals(pw)) {
				out.print("1"); // difference PW
			} else {
				out.print("2"); // login success
				HttpSession session = request.getSession();
				session.setAttribute("login", vo);
			}
			break;
		case "/create" :
			vo = new AccountVO();
			id = request.getParameter("id")==null?"user":request.getParameter("id");
			pw = request.getParameter("pw")==null?"":request.getParameter("pw");
			String name = request.getParameter("name")==null?"":request.getParameter("name");
			vo.setId(request.getParameter("id"));
			vo.setPw(request.getParameter("pw"));
			vo.setName(request.getParameter("name"));
			out.println(dao.create(vo));
			break;
		}
	}
}
