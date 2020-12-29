package book;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/book/list"})
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO dao = new BookDAO();
		BookVO vo = new BookVO();
		switch(request.getServletPath()) {
		case "/book/list" :
			String word = request.getParameter("word")==null?"":request.getParameter("word");
			int count = dao.count(word);
			int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
			int lastPage = (count%5==0)?count/5:(count/5)+1;
			
			request.setAttribute("page", page);
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("count", count);
			request.setAttribute("word", word);
			request.setAttribute("list", dao.list(word, page));
			RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
			rd.forward(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
