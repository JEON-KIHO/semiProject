package product;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet(value={"/list", "/read", "/delete", "/insert"})
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ProductDAO dao = new ProductDAO();
		switch(request.getServletPath()) {
		case "/list" :
			String query = request.getParameter("query")==null?"":request.getParameter("query");
			String key = request.getParameter("key")==null?"code":request.getParameter("key");
			String order = request.getParameter("order")==null?"asc":request.getParameter("order");
			out.println(dao.list(query, key, order));
			break;
		case "/delete" :
			String code = request.getParameter("code");
			JSONObject obj = dao.read(code);
			String image = obj.get("image").toString();
			
			if(!image.equals("")) {
				File file = new File("c:/image/" + image);
				if(file.exists()) file.delete();
			}
			dao.delete(code);
			break;
		case "/read" :
			code = request.getParameter("code");
			out.println(dao.read(code));
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ProductDAO dao = new ProductDAO();
		switch(request.getServletPath()) {
		case "/insert" :
			// file upload
			MultipartRequest multi = new MultipartRequest( request, "c:/image", 1024*1024*10, "UTF-8", new DefaultFileRenamePolicy() );
			
			// insert to table
			ProductVO vo = new ProductVO();
			vo.setCode(multi.getParameter("code"));
			vo.setName(multi.getParameter("name"));
			vo.setPrice(Integer.parseInt(multi.getParameter("price")));
			vo.setImage(multi.getFilesystemName("image"));
			dao.insert(vo);
			break;
		}
	}

}
