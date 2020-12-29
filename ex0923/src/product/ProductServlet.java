package product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet(value={"/list.json", "/read", "/insert", "/update", "/delete"})
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String word = request.getParameter("word")==null?"":request.getParameter("word");
		String order = request.getParameter("order")==null?"asc":request.getParameter("order");
		int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
		int num = request.getParameter("num")==null?8:Integer.parseInt(request.getParameter("num"));
		ProductDAO dao = new ProductDAO();
		
		switch(request.getServletPath()) {
		case "/list.json" :
			JSONObject jObject = dao.list(word, order, page, num);
			out.println(jObject.toJSONString());
			break;
		case "/read" :
			String code = request.getParameter("code");
			System.out.println(code);
			jObject = dao.read(code);
			out.println(jObject.toJSONString());
			break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ProductDAO dao = new ProductDAO();
		ProductVO vo = new ProductVO();
		
		String word = request.getParameter("word")==null?"":request.getParameter("word");
		String order = request.getParameter("order")==null?"asc":request.getParameter("order");
		int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
		int num = request.getParameter("num")==null?8:Integer.parseInt(request.getParameter("num"));
		
		int price = request.getParameter("price")==null?0:Integer.parseInt(request.getParameter("price"));
		String code = request.getParameter("code");
		String pname = request.getParameter("pname");
		String image = request.getParameter("image");
		vo.setCode(code);
		vo.setPrice(price);
		vo.setPname(pname);
		vo.setImage(image);
		
		switch(request.getServletPath()) {
		case "/insert" :
			dao.insert(vo);
			response.sendRedirect("product.jsp");
			break;
		case "/update" :
			dao.update(vo);
			request.setAttribute("list", dao.list(word, order, page, num));
			response.sendRedirect("product.jsp");
			break;
		case "/delete" :
			dao.delete(code);
			break;
		}
	}

}
