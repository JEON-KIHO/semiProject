package account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value={"/login","/logout","/signup"})
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch(request.getServletPath()) {
		case "/logout":
			HttpSession session =request.getSession();
			session.invalidate();	//세션값이 지워지는 부분
			response.sendRedirect("index.jsp");
			break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		AccountDAO dao = new AccountDAO();
		AccountVO avo = new AccountVO();
		
		switch(request.getServletPath()) {
		
		case "/signup":
			String id = request.getParameter("id");
			AccountVO vo = dao.read(id);
			boolean run = false;
			
			if(vo.getId() == null){
				run = true;
				out.println(run);
				avo.setId(id);
				avo.setPw(request.getParameter("pw"));
				avo.setCode(Integer.parseInt(request.getParameter("code")));
				avo.setName(request.getParameter("name"));
				avo.setNickName(request.getParameter("nickName"));
				dao.insert(avo);
			}else {
				run = false;
				out.println(run);
			}
			break;
			
		case "/login":
			avo = dao.login(request.getParameter("id"));
			String pw =request.getParameter("pw");
			if(avo.getId()==null) {
				out.println("0");		//아이디없음
			}else if(!avo.getPw().equals(pw)) {
				out.println("1");		//비번틀림
			}else {
				out.println("2");		//로그인성공
				HttpSession session =request.getSession();
				session.setAttribute("AccountVO", avo);
			}
			break;
	
		}
	}
}
