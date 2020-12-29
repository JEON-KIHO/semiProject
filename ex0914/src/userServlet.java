import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = {"/list", "/insert", "/read", "/update", "/delete"})
public class userServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO dao = new UserDAO();
		
		switch(request.getServletPath()) {
		case "/list" :
			request.setAttribute("list", dao.list());
			RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
			rd.forward(request, response);
			break;
		case "/read" :
			String id = request.getParameter("id");
			UserVO vo = dao.read(id);
			request.setAttribute("vo", vo);
			rd=request.getRequestDispatcher("read.jsp");
			rd.forward(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserVO vo = new UserVO();
		UserDAO dao = new UserDAO();
		vo.setId(request.getParameter("id"));
		vo.setName(request.getParameter("name"));
		vo.setPassword(request.getParameter("password"));
		
		switch(request.getServletPath()) {
		case "/insert" :
			dao.insert(vo);
			break;
		case "/update" :
			dao.update(vo);
			break;
		case "/delete" :
			dao.delete(vo.getId());
			break;
		}
		response.sendRedirect("list");
	}

}
