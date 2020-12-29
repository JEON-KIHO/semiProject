package product;

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

@WebServlet(value={"/list", "/list.json", "/insert", "/update", "/delete"})
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ProductDAO dao = new ProductDAO();
		String word = request.getParameter("word")==null?"":request.getParameter("word");
		String order = request.getParameter("order")==null?"asc":request.getParameter("order");
		int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
		int total = dao.count(word, order);
		int lastPage = (total%12==0)?total/12:(total/12)+1;
		
		PrintWriter out = response.getWriter();
		
		switch(request.getServletPath()) {
		case "/list" :
			request.setAttribute("order", order);
			request.setAttribute("word", word);
			request.setAttribute("total", total);
			request.setAttribute("list", dao.list(page, word, order));
			request.setAttribute("page", page);
			request.setAttribute("lastPage", lastPage);
			RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
			rd.forward(request, response);
			break;
		case "/list.json" :
			response.setContentType("text/html; charset=UTF-8");
			ArrayList<ProductVO> array = dao.list(page, word, order);
			JSONObject jObject = new JSONObject();
			jObject.put("total", total);
			jObject.put("lastPage", lastPage);
			
			JSONArray jArray = new JSONArray();
			for(ProductVO vo:array) {
				JSONObject obj = new JSONObject();
				obj.put("rn", vo.getRn());
				obj.put("code", vo.getCode());
				obj.put("pname", vo.getPname());
				obj.put("price", vo.getPrice());
				obj.put("image", vo.getImage());
				jArray.add(obj);
			}
			jObject.put("list", jArray);
			out.println(jObject.toJSONString());
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
