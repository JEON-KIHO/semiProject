package students;

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

import courses.CoursesDAO;
import enrollments.EnrollmentsDAO;
import enrollments.EnrollmentsVO;

@WebServlet(value={"/students/list", "/students/read", "/students/enroll.json", "/students/in_enroll", "/students/del_enroll"})
public class StudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentsDAO dao = new StudentsDAO();
		StudentsVO vo = new StudentsVO();
		switch(request.getServletPath()) {
		case "/students/list" :
			String key = request.getParameter("key")==null?"scode":request.getParameter("key");
			String word = request.getParameter("word")==null?"":request.getParameter("word");
			request.setAttribute("key", key);
			request.setAttribute("word", word);
			request.setAttribute("list", dao.list(key, word));
			RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
			rd.forward(request, response);
			break;
		case "/students/read" :
			CoursesDAO cdao = new CoursesDAO();
			request.setAttribute("clist", cdao.list());
			
			String scode = request.getParameter("scode");
			request.setAttribute("vo", dao.read(scode));
			rd = request.getRequestDispatcher("read.jsp");
			rd.forward(request, response);
			break;
		case "/students/enroll.json" :
			EnrollmentsDAO edao = new EnrollmentsDAO();
			scode = request.getParameter("scode");
			ArrayList<EnrollmentsVO> array = edao.list(scode);
			
			JSONArray jArray = new JSONArray();
			for(EnrollmentsVO evo:array) {
				JSONObject obj = new JSONObject();
				obj.put("lcode", evo.getLcode());
				obj.put("lname", evo.getLname());
				obj.put("scode", evo.getScode());
				obj.put("sname", evo.getSname());
				obj.put("grade", evo.getGrade());
				obj.put("edate", evo.getEdate().toString());
				jArray.add(obj);
			}
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(jArray.toJSONString());
			break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnrollmentsDAO dao = new EnrollmentsDAO();
		String lcode = request.getParameter("lcode");
		String scode = request.getParameter("scode");
		PrintWriter out = response.getWriter();
		
		switch(request.getServletPath()) {
		case "/students/in_enroll" :
			int count = dao.insert(lcode, scode);
			out.println(count);
			break;
		case "/students/del_enroll" :
			dao.delete(lcode, scode);
			break;
		}
	}

}
