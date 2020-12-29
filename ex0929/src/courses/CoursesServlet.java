package courses;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import enrollments.EnrollmentsDAO;
import enrollments.EnrollmentsVO;

@WebServlet(value={"/courses/list", "/courses/read", "/courses/enroll.json", "/courses/up_enroll"})
public class CoursesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CoursesDAO dao = new CoursesDAO();
		
		switch(request.getServletPath()) {
		case "/courses/list" :
			String key = request.getParameter("key")==null?"lcode":request.getParameter("key");
			String word = request.getParameter("word")==null?"":request.getParameter("word");
			request.setAttribute("key", key);
			request.setAttribute("word", word);
			request.setAttribute("list", dao.list(key, word));
			
			RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
			rd.forward(request, response);
			break;
		case "/courses/read" :
			String lcode = request.getParameter("lcode");
			request.setAttribute("vo", dao.read(lcode));
			rd = request.getRequestDispatcher("read.jsp");
			rd.forward(request, response);
			break;
		case "/courses/enroll.json" :
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			EnrollmentsDAO edao = new EnrollmentsDAO();
			lcode = request.getParameter("lcode");
			ArrayList<EnrollmentsVO> array = edao.slist(lcode);
			
			JSONArray jArray = new JSONArray();
			for(EnrollmentsVO vo:array) {
				JSONObject obj = new JSONObject();
				obj.put("scode", vo.getScode());
				obj.put("sname", vo.getSname());
				obj.put("lcode", vo.getLcode());
				obj.put("lname", vo.getLname());
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
				String edate = sdf.format(vo.getEdate());
				obj.put("edate", edate);
				obj.put("grade", vo.getGrade());
				jArray.add(obj);
			}
			out.println(jArray.toJSONString());
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnrollmentsDAO dao = new EnrollmentsDAO();
		switch(request.getServletPath()) {
		case "/courses/up_enroll" :
			EnrollmentsVO vo = new EnrollmentsVO();
			vo.setScode(request.getParameter("scode"));
			vo.setLcode(request.getParameter("lcode"));
			vo.setGrade(Integer.parseInt(request.getParameter("grade")));
			dao.update(vo);
			break;
		}
	}

}
