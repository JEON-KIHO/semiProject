package database;

import purchase.PurchaseDAO;

public class TEST {

	public static void main(String[] args) {
		/*MallDAO dao = new MallDAO();*/
		sqlVO vo = new sqlVO();
		vo.setKey("order_id");
		vo.setWord("");
		vo.setPage(1);
		vo.setPerPage(4);
		vo.setCol("order_id");
		/*System.out.println(dao.list(vo));
		ProductDAO dao = new ProductDAO();
		System.out.println(dao.read("P101"));*/
		PurchaseDAO dao = new PurchaseDAO();
		System.out.println(dao.list(vo));

	}

}
