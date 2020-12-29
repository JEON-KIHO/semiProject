package address;

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

import board.BoardDAO;
import board.BoardVO;

@WebServlet(value={"/address", "/list.json", "/insert", "/delete" ,"/update", "/board.json"})
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		AddressDAO dao = new AddressDAO();
		PrintWriter out = response.getWriter();
		
		switch (request.getServletPath()) {
		case "/address" :
			RequestDispatcher rd = request.getRequestDispatcher("address.jsp");
			rd.forward(request, response);
			break;
			
		case "/list.json" :
			int page = Integer.parseInt(request.getParameter("page"));
			int total = dao.count();
			
			ArrayList<AddressVO> list = dao.list(page);
			JSONObject jObject = new JSONObject();
			
			jObject.put("total", total);
			
			JSONArray jArray = new JSONArray();
			for(AddressVO vo:list) {
				JSONObject obj = new JSONObject();
				obj.put("rn", vo.getRn());
				obj.put("seq", vo.getSeq());
				obj.put("name", vo.getName());
				obj.put("tel", vo.getTel());
				obj.put("address", vo.getAddress());
				jArray.add(obj);
			}
			jObject.put("list", jArray);
			int lastPage = (total%5==0)?total/5:total/5+1;
			jObject.put("lastPage", lastPage);
			out.print(jObject.toJSONString());
			break;
			
		case "/board.json" :
			BoardDAO bdao = new BoardDAO();
			page = request.getParameter("page")==null?0:Integer.parseInt(request.getParameter("page"));
			total = bdao.count();
			
			ArrayList<BoardVO> blist = bdao.list(page);
			jObject = new JSONObject();
			
			jObject.put("total", total);
			
			jArray = new JSONArray();
			for(BoardVO vo:blist) {
				JSONObject obj = new JSONObject();
				obj.put("rn", vo.getRn());
				obj.put("seq", vo.getSeq());
				obj.put("title", vo.getTitle());
				obj.put("writer", vo.getWriter());
				jArray.add(obj);
			}
			jObject.put("list", jArray);
			int blastPage = (total%10==0)?total/10:total/10+1;
			jObject.put("lastPage", blastPage);
			out.print(jObject.toJSONString());
			break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		AddressDAO dao = new AddressDAO();
		AddressVO vo = new AddressVO();
		
		vo.setName(request.getParameter("name"));
		vo.setTel(request.getParameter("tel"));
		vo.setAddress(request.getParameter("address"));
		
		switch (request.getServletPath()) {
		case "/insert" :
			dao.insert(vo);
			break;
		case "/update" :
			int seq = Integer.parseInt(request.getParameter("seq"));
			vo.setSeq(seq);
			dao.update(vo);
			break;
		case "/delete" :
			seq = Integer.parseInt(request.getParameter("seq"));
			dao.delete(seq);
			break;
		}
	}

}
