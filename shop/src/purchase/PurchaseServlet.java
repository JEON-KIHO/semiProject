package purchase;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import database.sqlVO;
import orders.OrdersDAO;


@WebServlet(value={"/purchase/list.json", "/purchase/read.json", "/purchase/plist.json", "/purchase/insert"})
public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		PurchaseDAO pdao = new PurchaseDAO();
		sqlVO svo = new sqlVO();
		
		switch(request.getServletPath()) {
		case "/purchase/list.json" :
			String key = request.getParameter("key")==null?"order_id":request.getParameter("key");
			svo.setKey(key);
			String word = request.getParameter("word")==null?"":request.getParameter("word");
			svo.setWord(word);
			String col = request.getParameter("col")==null?"order_id":request.getParameter("col");
			svo.setCol(col);
			int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
			svo.setPage(page);
			int perPage = request.getParameter("perPage")==null?5:Integer.parseInt(request.getParameter("perPage"));
			svo.setPerPage(perPage);
			out.println(pdao.list(svo));
			break;
		case "/purchase/read.json" :
			JSONObject obj = new JSONObject();
			PurchaseVO vo = pdao.read(request.getParameter("order_id"));
			obj.put("order_id", vo.getOrder_id());
			obj.put("name", vo.getName());
			obj.put("address", vo.getAddress());
			obj.put("email", vo.getEmail());
			obj.put("tel", vo.getTel());
			obj.put("pdate", vo.getPdate().toString());
			obj.put("payType", vo.getPayType());
			obj.put("status", vo.getStatus());
			if(vo.getPayType()==0) {
				obj.put("payType", "무통장입금");
			} else {
				obj.put("payType", "카드결제");
			}
			if(vo.getStatus()==0) {
				obj.put("status", "처리중입니다");
			} else {
				obj.put("status", "처리완료!");
			}
			
			out.println(obj.toJSONString());
			break;
		case "/purchase/plist.json" :
			OrdersDAO odao = new OrdersDAO();
			out.println(odao.list(request.getParameter("order_id")));
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PurchaseDAO pdao = new PurchaseDAO();
		PurchaseVO pvo = new PurchaseVO();
		pvo.setName(request.getParameter("name"));
		pvo.setAddress(request.getParameter("address"));
		pvo.setEmail(request.getParameter("email"));
		pvo.setTel(request.getParameter("tel"));
		pvo.setPayType(Integer.parseInt(request.getParameter("payType")));
		switch(request.getServletPath()) {
		case "/purchase/insert" :
			String order_id = pdao.insert(pvo);
			PrintWriter out = response.getWriter();
			out.println(order_id);
			break;
		}
	}

}
