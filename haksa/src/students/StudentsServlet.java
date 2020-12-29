package students;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courses.CoursesDAO;
import database.sqlVO;

@WebServlet(value={"/students/list.json", "/students/elist.json", "/students/cdelete", "/students/chkCode", "/students/insert", "/students/read", "/students/update", "/students/delete"})
public class StudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		StudentsDAO sdao = new StudentsDAO();
		CoursesDAO cdao = new CoursesDAO();
		switch(request.getServletPath()) {
		case "/students/list.json" :
			sqlVO svo = new sqlVO();
			String key = request.getParameter("key")==null?"scode":request.getParameter("key");
			svo.setKey(key);
			String word = request.getParameter("word")==null?"":request.getParameter("word");
			svo.setWord(word);
			int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
			svo.setPage(page);
			int perPage = request.getParameter("perPage")==null?2:Integer.parseInt(request.getParameter("perPage"));
			svo.setPerPage(perPage);
			
			out.println(sdao.list(svo));
			break;
		case "/students/elist.json" :
			out.println(sdao.elist(request.getParameter("scode")));
			break;
		case "/students/chkCode" :
			StudentsVO vo = sdao.read(request.getParameter("scode"));
			if(vo.getScode()==null) {
				out.println("0");
			} else {
				out.println("1");
			}
			break;
		case "/students/read" :
			request.setAttribute("clist", cdao.clist());
			
			request.setAttribute("vo", sdao.read(request.getParameter("scode")));
			RequestDispatcher rd = request.getRequestDispatcher("read.jsp");
			rd.forward(request, response);
			break;
		case "/students/delete" :
			out.println(sdao.delete(request.getParameter("scode")));
			break;
		case "/students/cdelete" :
			String lcode = request.getParameter("lcode")==null?"0000":request.getParameter("lcode");
			int scode = request.getParameter("scode")==null?00000000:Integer.parseInt(request.getParameter("scode"));
			System.out.println(lcode +"/"+ scode);
			response.sendRedirect("read.jsp");
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		StudentsDAO sdao = new StudentsDAO();
		StudentsVO vo = new StudentsVO();
		vo.setScode(request.getParameter("scode"));
		vo.setSname(request.getParameter("sname"));
		vo.setDept(request.getParameter("dept"));
		vo.setAdvisor(Integer.parseInt(request.getParameter("pcode")));
		vo.setYear(Integer.parseInt(request.getParameter("year")));
		String yy = request.getParameter("yy");
		String mm = request.getParameter("mm");
		String dd = request.getParameter("dd");
		String strDate = yy+"-"+mm+"-"+dd;
		try {
			java.sql.Date birthday = Date.valueOf(strDate);
			vo.setBirthday(birthday);
		} catch(Exception error) {
			error.printStackTrace();
		}
		switch(request.getServletPath()) {
		case "/students/insert" :
			sdao.insert(vo);
			response.sendRedirect("list.jsp");
			break;
		case "/students/update" :
			sdao.update(vo);
			response.sendRedirect("list.jsp");
			break;
		}
	}

}
