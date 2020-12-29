package address;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/address/list")
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key")==null?"address":request.getParameter("key");
		String word = request.getParameter("word")==null?"":request.getParameter("word");
		String strPage = request.getParameter("page")==null?"1":request.getParameter("page");
		String strPerPage = request.getParameter("perPage")==null?"10":request.getParameter("perPage");
		// int page = Integer.parseInt(strPage);
		int perPage = Integer.parseInt(strPerPage);
		
		AddressDAO dao = new AddressDAO();
		QueryVO qv = new QueryVO();
		qv.setTable("tbl_address");
		qv.setKey(key);
		qv.setWord(word);
		qv.setPage(Integer.parseInt(strPage));
		qv.setPerPage(Integer.parseInt(strPerPage));
		
		request.setAttribute("list", dao.list(qv));
		request.setAttribute("total", dao.count(qv));
		request.setAttribute("key", key);
		request.setAttribute("word", word);
		request.setAttribute("page", strPage);
		request.setAttribute("perPage", strPerPage);
		
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
