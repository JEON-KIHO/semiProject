package professors;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.sqlVO;

@WebServlet(value={"/professors/list.json", "/professors/plist.json", "/professors/insert", "/professors/chkCode", "/professors/read", "/professors/update", "/professors/delete"})
public class ProfessorsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ProfessorsDAO pdao = new ProfessorsDAO();
		switch(request.getServletPath()) {
		case "/professors/list.json" :
			sqlVO svo = new sqlVO();
			String table = request.getParameter("table")==null?"professors":request.getParameter("table");
			svo.setTable(table);
			String key = request.getParameter("key")==null?"pcode":request.getParameter("key");
			svo.setKey(key);
			String word = request.getParameter("word")==null?"":request.getParameter("word");
			svo.setWord(word);
			int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
			svo.setPage(page);
			int perPage = request.getParameter("perPage")==null?2:Integer.parseInt(request.getParameter("perPage"));
			svo.setPerPage(perPage);
			
			out.println(pdao.list(svo));
			break;
		case "/professors/plist.json" :
			String pcode = request.getParameter("pcode");
			out.println(pdao.plist(pcode));
			break;
		case "/professors/insert" :
			
			break;
		case "/professors/chkCode" :
			ProfessorsVO vo = pdao.read(request.getParameter("pcode"));
			if(vo.getPcode()==null) {
				out.println("0");
			} else {
				out.println("1");
			}
			break;
		case "/professors/read" :
			request.setAttribute("vo", pdao.read(request.getParameter("pcode")));
			out.println(pdao.plist(request.getParameter("pcode")));
			RequestDispatcher rd = request.getRequestDispatcher("read.jsp");
			rd.forward(request, response);
			break;
		case "/professors/delete" :
			out.println(pdao.delete(request.getParameter("pcode")));
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ProfessorsDAO pdao = new ProfessorsDAO();
		ProfessorsVO vo = new ProfessorsVO();
		vo.setPcode(request.getParameter("pcode"));
		vo.setPname(request.getParameter("pname"));
		vo.setDept(request.getParameter("dept"));
		vo.setTitle(request.getParameter("title"));
		int salary = request.getParameter("salary")==null?0:Integer.parseInt(request.getParameter("salary"));
		vo.setSalary(salary);
		
		String yy = request.getParameter("yy");
		String mm = request.getParameter("mm");
		String dd = request.getParameter("dd");
		String strDate = yy +"-"+ mm +"-"+ dd;
		try {
			java.sql.Date hiredate = Date.valueOf(strDate);
			vo.setHiredate(hiredate);
		} catch (Exception error) {
			error.printStackTrace();
		}
		
		switch(request.getServletPath()) {
		case "/professors/insert" :
			pdao.insert(vo);
			response.sendRedirect("list.jsp");
			break;
		case "/professors/update" :
			pdao.update(vo);
			response.sendRedirect("list.jsp");
			break;
		}
	}

}
