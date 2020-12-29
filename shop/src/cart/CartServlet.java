package cart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import product.ProductDAO;
import product.ProductVO;

@WebServlet(value={"/cart/insert", "/cart/update", "/cart/delete"})
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("UTF-8");
		HttpSession session = request.getSession();
		ArrayList<CartVO> listCart = (ArrayList<CartVO>)session.getAttribute("listCart");
		if(listCart==null) listCart=new ArrayList<CartVO>();
		String prod_id = request.getParameter("prod_id");
		switch(request.getServletPath()) {
		case "/cart/insert" :
			boolean isFind = false;
			for(CartVO vo: listCart) {
				if(prod_id.equals(vo.getProd_id())) { // 장바구니에 해당 상품이 있을 때
					isFind = true;
					vo.setQuantity(vo.getQuantity()+1);
					vo.setSum(vo.getQuantity() * vo.getPrice1());
				}
			}
			if(!isFind) { // 장바구니에 해당 상품이 없을 때
				CartVO cartvo =  new CartVO();
				cartvo.setProd_id(prod_id);
				ProductDAO pdao = new ProductDAO();
				ProductVO pvo = pdao.read(prod_id);
				cartvo.setProd_name(pvo.getProd_name());
				cartvo.setPrice1(pvo.getPrice1());
				cartvo.setQuantity(1);
				cartvo.setSum(pvo.getPrice1());
				listCart.add(cartvo);
			}
			session.setAttribute("listCart", listCart);
			break;
		case "/cart/update" :
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			for(CartVO vo:listCart) {
				if(vo.getProd_id().equals(prod_id)) {
					vo.setQuantity(quantity);
					vo.setSum(vo.getQuantity() * vo.getPrice1());
				}
			}
			session.setAttribute("listCart", listCart);
			break;
		case "/cart/delete" :
			for(CartVO vo:listCart) {
				if(prod_id.equals(vo.getProd_id())) {
					listCart.remove(vo);
					break;
				}
			}
			session.setAttribute("listCart", listCart);
			break;
		}
	}
}
