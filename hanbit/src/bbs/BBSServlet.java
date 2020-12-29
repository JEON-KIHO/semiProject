package bbs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bbs/list.json")
public class BBSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		BBSDAO dao = new BBSDAO();
		switch(request.getServletPath()) {
		case "/bbs/list.json" :
			String word = request.getParameter("word")==null?"":request.getParameter("word");
			int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
			
			out.println(dao.list(word, page));
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
