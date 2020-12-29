package address;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/list")
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key")==null?"address":request.getParameter("key");
		String word = request.getParameter("word")==null?"":request.getParameter("word");
		String strPage = request.getParameter("perPage")==null?"10":request.getParameter("perPage");
		String btnPage = request.getParameter("page")==null?"1":request.getParameter("page");
		int page = Integer.parseInt(btnPage);
		int perPage = Integer.parseInt(strPage);
		System.out.println("key" + key + " word" + word + " perPage" + perPage);
		
		AddressDAO dao = new AddressDAO();
		request.setAttribute("list", dao.list(key, word, perPage, page));
		request.setAttribute("key", key);
		request.setAttribute("word", word);
		request.setAttribute("perPage", perPage);
		request.setAttribute("page", page); // 현재 페이지
		
		int total = dao.total(key, word); // 검색 갯수
		int totalPage = (total % perPage)==0?total/perPage:(total/perPage)+1; // 패이지 갯수
		request.setAttribute("total", total);
		request.setAttribute("totalPage", totalPage);
		
		RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
