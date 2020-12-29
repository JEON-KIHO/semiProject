package address;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/list", "/insert", "/read", "/delete", "/update"})
public class AddressServlet0 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AddressServlet0() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddressDAO0 dao = new AddressDAO0();
		
		try {
			switch(request.getServletPath()) {
			case "/read" :
				String strSeq=request.getParameter("seq");
				int seq=Integer.parseInt(strSeq);
				request.setAttribute("vo", dao.read(seq));
				RequestDispatcher rd = request.getRequestDispatcher("read.jsp");
				rd.forward(request, response);
				break;
			case "/list" :
				request.setAttribute("list", dao.list());
				rd = request.getRequestDispatcher("list.jsp");
				rd.forward(request, response);
				break;
			case "/insert" :
				rd = request.getRequestDispatcher("insert.jsp");
				rd.forward(request, response);
				break;
			
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  request.setCharacterEncoding("UTF-8");
	      AddressDAO0 dao = new AddressDAO0();
	      
	      AddressVO0 vo=new AddressVO0();
	      
	      vo.setName(request.getParameter("name"));
	      vo.setTel(request.getParameter("tel"));
	      vo.setAddress(request.getParameter("address"));
	      
	      try {
	         switch(request.getServletPath()) {
	         case "/insert":
	            dao.insert(vo);
	            break;
	         case "/delete":
	        	String strSeq=request.getParameter("seq");
	        	int seq=Integer.parseInt(strSeq);
	        	vo.setSeq(seq);
	            dao.delete(seq);
	            break;
	         case "/update":
	        	strSeq=request.getParameter("seq");
	        	seq=Integer.parseInt(strSeq);
	        	vo.setSeq(seq);
	            dao.update(vo);
	            break;
	         }
	         response.sendRedirect("list");
	      } catch (Exception e) {   
	         e.printStackTrace();
	      }
	      
	   }

	}