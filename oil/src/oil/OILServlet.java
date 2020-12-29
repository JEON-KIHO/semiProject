package oil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/list.json", "/insert", "/read"})
public class OILServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		OILDAO dao = new OILDAO();
		
		switch(request.getServletPath()) {
		case "/list.json" :
			String word = request.getParameter("word")==null?"":request.getParameter("word");
			out.println(dao.list(word));
			break;
		case "/insert" :
			RequestDispatcher rd = request.getRequestDispatcher("insert.jsp");
			rd.forward(request, response);
			break;
		case "/read" :
			String code = request.getParameter("code");
			out.println(dao.read(code));
			break;
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		OILDAO dao = new OILDAO();
		OILVO vo = new OILVO();
		String code = request.getParameter("code")==null?"":request.getParameter("code");
		String name = request.getParameter("name")==null?"":request.getParameter("name");
		String address = request.getParameter("address")==null?"":request.getParameter("address");
		int price = request.getParameter("price")==null?0:Integer.parseInt(request.getParameter("price"));
		String self = request.getParameter("self")==null?"O":request.getParameter("self");
		vo.setCode(code);
		vo.setName(name);
		vo.setAddres(address);
		vo.setPrice(price);
		vo.setSelf(self);
		
		switch(request.getServletPath()) {
		case "/insert" :
			dao.insert(vo);
			response.sendRedirect("insert.jsp");
			break;
		}
	}

}
