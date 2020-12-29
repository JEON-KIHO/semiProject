package orders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import database.Database;

public class OrdersDAO {
	// 주문 상품 입력
	public void insert(OrdersVO vo) {
		try {
			String sql = "insert into orders values(?, ?, ?, ?)";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getOrder_id());
			ps.setString(2, vo.getProd_id());
			ps.setInt(3, vo.getPrice());
			ps.setInt(4, vo.getQuantity());
			ps.execute();
		} catch(Exception error) {
			error.printStackTrace();
		}
	}
	
	// 주문 상품 출력
	public JSONObject list(String order_id) {
		JSONObject jobj = new JSONObject();
		try {
			String sql = "select o.*, prod_name from orders o left join product p on o.prod_id=p.prod_id where order_id = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, order_id);
			ResultSet rs = ps.executeQuery();
			JSONArray jArray = new JSONArray();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("order_id", rs.getString("order_id"));
				obj.put("prod_id", rs.getString("prod_id"));
				obj.put("prod_name", rs.getString("prod_name"));
				obj.put("price", rs.getInt("price"));
				obj.put("quantity", rs.getString("quantity"));
				int sum = rs.getInt("price") * Integer.parseInt(rs.getString("quantity"));
				obj.put("sum", sum);
				jArray.add(obj);
			}
			jobj.put("list", jArray);
		} catch (Exception error) {
			error.printStackTrace();
		}
		return jobj;
	}
}
