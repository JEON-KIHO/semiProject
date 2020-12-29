package book;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet(value={"/book", "/list.json", "/insert", "/update", "/delete"})
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO dao = new BookDAO();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); // 브라우저 출력객체
		
		switch(request.getServletPath()) {
		case "/book" :
			RequestDispatcher rd = request.getRequestDispatcher("book.jsp");
			rd.forward(request, response);
			break;
		case "/list.json" : // REST API
			ArrayList<BookVO> array = dao.list();
			JSONArray jArray = new JSONArray();
			for(BookVO vo:array) {
				JSONObject obj = new JSONObject();
				obj.put("code", vo.getCode());
				obj.put("title", vo.getTitle());
				obj.put("writer", vo.getWriter());
				obj.put("price", vo.getPrice());
				jArray.add(obj);
			}
			out.print(jArray.toJSONString());
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		BookVO vo = new BookVO();
		vo.setCode(request.getParameter("code"));
		vo.setTitle(request.getParameter("title"));
		vo.setWriter(request.getParameter("writer"));
		BookDAO dao = new BookDAO();
		
		switch(request.getServletPath()) {
		case "/insert" :
			vo.setPrice(Integer.parseInt(request.getParameter("price")));
			dao.insert(vo);
			break;
		case "/update" :
			vo.setPrice(Integer.parseInt(request.getParameter("price")));
			dao.update(vo);
			break;
		case "/delete" :
			dao.delete(vo.getCode());
			break;
		}
	}

}
