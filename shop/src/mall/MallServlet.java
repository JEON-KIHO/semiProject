package mall;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.sqlVO;

@WebServlet(value={"/mall/list.json"})
public class MallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		MallDAO mdao = new MallDAO();
		sqlVO svo = new sqlVO();
		switch(request.getServletPath()) {
		case "/mall/list.json" :
			String key = request.getParameter("key")==null?"mall_id":request.getParameter("key");
			svo.setKey(key);
			String word = request.getParameter("word")==null?"":request.getParameter("word");
			svo.setWord(word);
			String col = request.getParameter("col")==null?"mall_id":request.getParameter("col");
			svo.setCol(col);
			int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
			svo.setPage(page);
			int perPage = request.getParameter("perPage")==null?2:Integer.parseInt(request.getParameter("perPage"));
			svo.setPerPage(perPage);
			out.println(mdao.list(svo));
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
