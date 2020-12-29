package address;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/list","/insert","/delete", "/update"})
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		AddressDAO dao = new AddressDAO();
		PrintWriter out = response.getWriter();
		AddressVO vo = new AddressVO();
		switch(request.getServletPath()) {
		case "/list" :
			String query = request.getParameter("query")==null?"":request.getParameter("query");
			out.println(dao.list(query));
			break;
		case "/delete" :
			int seq = Integer.parseInt(request.getParameter("seq"));
			dao.delete(seq);
			break;
		case "/insert" :
			vo.setName(request.getParameter("name"));
			vo.setAddr(request.getParameter("addr"));
			vo.setTel(request.getParameter("tel"));
			dao.insert(vo);
			break;
		case "/update" :
			vo.setName(request.getParameter("name"));
			vo.setAddr(request.getParameter("addr"));
			vo.setTel(request.getParameter("tel"));
			vo.setSeq(Integer.parseInt(request.getParameter("seq")));
			dao.update(vo);
			break;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
