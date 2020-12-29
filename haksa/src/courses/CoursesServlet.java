package courses;

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
import students.StudentsDAO;
import students.StudentsVO;

@WebServlet(value={"/courses/list.json", "/courses/chkCode", "/courses/insert", "/courses/delete", "/courses/read", "/courses/update"})
public class CoursesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		CoursesDAO cdao = new CoursesDAO();
		switch(request.getServletPath()) {
		case "/courses/list.json" :
			sqlVO svo = new sqlVO();
			String key = request.getParameter("key")==null?"lcode":request.getParameter("key");
			svo.setKey(key);
			String word = request.getParameter("word")==null?"":request.getParameter("word");
			svo.setWord(word);
			int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
			svo.setPage(page);
			int perPage = request.getParameter("perPage")==null?2:Integer.parseInt(request.getParameter("perPage"));
			svo.setPerPage(perPage);
			
			out.println(cdao.list(svo));
			break;
		case "/courses/chkCode" :
			CoursesVO vo = cdao.read(request.getParameter("lcode"));
			if(vo.getLcode()==null) {
				out.println("0");
			} else {
				out.println("1");
			}
			break;
		case "/courses/read" :
			request.setAttribute("vo", cdao.read(request.getParameter("lcode")));
			RequestDispatcher rd = request.getRequestDispatcher("read.jsp");
			rd.forward(request, response);
			break;
		case "/courses/delete" :
			out.println(cdao.delete(request.getParameter("lcode")));
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		CoursesDAO cdao = new CoursesDAO();
		CoursesVO vo = new CoursesVO();
		vo.setLcode(request.getParameter("lcode"));
		vo.setLname(request.getParameter("lname"));
		vo.setHours(Integer.parseInt(request.getParameter("hours")));
		vo.setRoom(Integer.parseInt(request.getParameter("room")));
		vo.setInstructor(Integer.parseInt(request.getParameter("pcode")));
		vo.setCapacity(Integer.parseInt(request.getParameter("capacity")));
		vo.setPersons(Integer.parseInt(request.getParameter("persons")));
		
		switch(request.getServletPath()) {
		case "/courses/insert" :
			cdao.insert(vo);
			response.sendRedirect("list.jsp");
			break;
		case "/courses/update" :
			System.out.println(vo.getLcode()+"/"+vo.getLname()+"/"+vo.getHours()+"/"+vo.getRoom()+"/"+vo.getInstructor()+"/"+vo.getCapacity()+"/"+vo.getPersons());
			cdao.update(vo);
			response.sendRedirect("read.jsp");
			break;
		}
	}

}
