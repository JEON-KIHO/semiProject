package orders;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/orders/insert")
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdersDAO odao = new OrdersDAO();
		OrdersVO ovo = new OrdersVO();
		ovo.setOrder_id(request.getParameter("order_id"));
		ovo.setProd_id(request.getParameter("prod_id"));
		ovo.setPrice(Integer.parseInt(request.getParameter("price")));
		ovo.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		switch(request.getServletPath()) {
		case "/orders/insert" :
			odao.insert(ovo);
			break;
		}
	}

}
