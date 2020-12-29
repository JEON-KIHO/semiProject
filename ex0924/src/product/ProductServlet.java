package product;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet(value={"/", "/list.json", "/read", "/insert", "/update", "/delete", "/chkCode"})
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
		case "/chkCode" :
			ProductVO vo = dao.read(request.getParameter("code"));
			JSONObject obj = new JSONObject();
			if(vo.getCode()==null) {
				obj.put("code", 0);
			} else {
				obj.put("code", 1);
			}
			out.println(obj.toJSONString());
			break;
		case "/insert" :
			RequestDispatcher rd = request.getRequestDispatcher("insert.jsp");
			rd.forward(request, response);
			break;
		case "/" :
			rd = request.getRequestDispatcher("product.jsp");
			rd.forward(request, response);
			break;
		case "/list.json" :
			JSONObject jObject = dao.list(word, order, page, num);
			out.println(jObject.toJSONString());
			break;
		case "/read" :
			request.setAttribute("vo", dao.read(request.getParameter("code")));
			rd = request.getRequestDispatcher("read.jsp");
			rd.forward(request, response);
			break;
		case "/delete" :
			// delete image
			String code = request.getParameter("code");
			ProductVO oldVO = dao.read(code);
			if(oldVO.getImage()!=null) {
				String path = "c:" + File.separator + "image" + File.separator;
				File file = new File(path + oldVO.getImage());
				file.delete();
			}
			// delete data
			dao.delete(code);
			response.sendRedirect("/");
			break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ProductDAO dao = new ProductDAO();
		ProductVO vo = new ProductVO();
		// file upload
		String path = "c:" + File.separator + "image" + File.separator;
		MultipartRequest multi = new MultipartRequest(
				request,
				path,
				1024*1024*10,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		int price = multi.getParameter("price")==null?0:Integer.parseInt(multi.getParameter("price"));
		String code = multi.getParameter("code")==null?"":multi.getParameter("code");
		String pname = multi.getParameter("pname")==null?"":multi.getParameter("pname");
		String image = multi.getFilesystemName("image")==null?"":multi.getFilesystemName("image");
		vo.setCode(code);
		vo.setPrice(price);
		vo.setPname(pname);
		vo.setImage(image);
		
		switch(request.getServletPath()) {
		case "/insert" :
			dao.insert(vo);
			response.sendRedirect("/");
			break;
		case "/update" :
			ProductVO oldVO = dao.read(vo.getCode());
			if(multi.getFilesystemName("image")==null) { // 바꿀 이미지가 없을 때
				vo.setImage(oldVO.getImage());
			} else if(oldVO.getImage()!=null) { // 바꿀 이미지가 있으며, 이전의 이미지가 있을 때
				File file = new File(path + oldVO.getImage());
				file.delete();
			}
			dao.update(vo);
			response.sendRedirect("/");
			break;
		}
	}

}
