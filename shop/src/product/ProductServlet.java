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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import database.sqlVO;

@WebServlet(value={"/product/list.json", "/product/insert", "/product/read", "/product/update", "/product/delete"})
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ProductDAO pdao = new ProductDAO();
		sqlVO svo = new sqlVO();
		switch(request.getServletPath()) {
		case "/product/list.json" :
			String key = request.getParameter("key")==null?"prod_id":request.getParameter("key");
			svo.setKey(key);
			String word = request.getParameter("word")==null?"":request.getParameter("word");
			svo.setWord(word);
			String col = request.getParameter("col")==null?"prod_id":request.getParameter("col");
			svo.setCol(col);
			int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
			svo.setPage(page);
			int perPage = request.getParameter("perPage")==null?2:Integer.parseInt(request.getParameter("perPage"));
			svo.setPerPage(perPage);
			out.println(pdao.list(svo));
			break;
		case "/product/insert" :
			request.setAttribute("code", pdao.getCode());
			RequestDispatcher rd = request.getRequestDispatcher("insert.jsp");
			rd.forward(request, response);
			break;
		case "/product/read" :
			request.setAttribute("vo", pdao.read(request.getParameter("prod_id")));
			rd = request.getRequestDispatcher("read.jsp");
			rd.forward(request, response);
			break;
		case "/product/delete" :
			ProductVO oldVO = pdao.read(request.getParameter("prod_id"));
			int cnt = pdao.delete(request.getParameter("prod_id"));
			out.println(cnt);
			
			if(cnt==0) {
				if(oldVO.getImage()!=null) {
					String delImage="C:/image/" + oldVO.getImage();
					File file = new File(delImage); // 지우고자 하는 파일 선택
					file.delete(); // 선택한 파일 지우기
				}
			}
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ProductDAO pdao = new ProductDAO();
		
		String path="C:/image";
		MultipartRequest multi = new MultipartRequest(
			request,
			path,
			1024*1024*10,
			"UTF-8",
			new DefaultFileRenamePolicy()
		);
		ProductVO pvo = new ProductVO();
		pvo.setProd_id(multi.getParameter("prod_id"));
		pvo.setProd_name(multi.getParameter("prod_name"));
		pvo.setMall_id(multi.getParameter("mall_id"));
		pvo.setCompany(multi.getParameter("company"));
		pvo.setPrice1(Integer.parseInt(multi.getParameter("price1")));
		pvo.setPrice2(Integer.parseInt(multi.getParameter("price2")));
		pvo.setImage(multi.getFilesystemName("image"));
		pvo.setDetail(multi.getParameter("detail"));
		pvo.setProd_del(multi.getParameter("prod_del"));
		switch(request.getServletPath()) {
		case "/product/insert" :
			System.out.println(pvo.toString());
			pdao.insert(pvo);
			response.sendRedirect("../index.jsp");
			break;
		case "/product/update" :
			System.out.println(pvo.toString());
			ProductVO oldVO = pdao.read(pvo.getProd_id());
			if(multi.getFilesystemName("image")==null) { // 수정할 이미지 선택 안 했을 때
				pvo.setImage(oldVO.getImage());
			} else { // 수정할 이미지 선택 했을 때
				if(oldVO.getImage()!=null) { // 예전 이미지가 있을 경우
					String delImage = path + File.separator + oldVO.getImage();
					File file = new File(delImage); // 지우고자 하는 파일 선택
					file.delete(); // 선택한 파일 지우기
				}
			}
			pdao.update(pvo);
			response.sendRedirect("list.jsp");
			break;
		}
	}
}
