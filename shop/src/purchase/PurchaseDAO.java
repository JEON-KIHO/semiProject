package purchase;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DecimalFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import database.Database;
import database.sqlVO;

public class PurchaseDAO {
	//구매 정보 입력
	public String insert(PurchaseVO vo) {
		String order_id = "";
		try {
			String sql = "insert into purchase(order_id, name, address, email, tel, pdate, payType) values('R'||seq_order.nextval, ?, ?, ?, ?, sysdate, ?)";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getAddress());
			ps.setString(3, vo.getEmail());
			ps.setString(4, vo.getTel());
			ps.setInt(5, vo.getPayType());
			ps.execute();
			
			//주문 아이디 구하기
			sql = "select 'R'||seq_order.currval order_id from dual";
			ps = Database.CON.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) order_id = rs.getString("order_id");
			
		} catch(Exception error) {
			error.printStackTrace();
		}
		return order_id;
	}
	
	// 구매 정보 출력
	public PurchaseVO read(String order_id) {
		PurchaseVO vo = new PurchaseVO();
		try {
			String sql = "select * from purchase where order_id = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, order_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				vo.setOrder_id(rs.getString("order_id"));
				vo.setName(rs.getString("name"));
				vo.setAddress(rs.getString("address"));
				vo.setEmail(rs.getString("email"));
				vo.setTel(rs.getString("tel"));
				vo.setPdate(rs.getDate("pdate"));
				vo.setPayType(rs.getInt("payType"));
				vo.setStatus(rs.getInt("status"));
			}
		} catch(Exception error) {
			error.printStackTrace();
		}
		return vo;
	}
	// 구매 목록 출력
	public JSONObject list(sqlVO vo) {
		JSONObject jobj = new JSONObject();
		try {
			String sql = "{call list(?, ?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement cs = Database.CON.prepareCall(sql);
			cs.setString(1, "purchase");
			cs.setString(2, vo.getKey());
			cs.setString(3, vo.getWord());
			cs.setString(4, vo.getCol());
			cs.setInt(5, vo.getPage());
			cs.setInt(6, vo.getPerPage());
			cs.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(8, oracle.jdbc.OracleTypes.INTEGER);
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(7);
			JSONArray jArray = new JSONArray();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("order_id", rs.getString("order_id"));
				obj.put("name", rs.getString("name"));
				obj.put("address", rs.getString("address"));
				obj.put("tel", rs.getString("tel"));
				jArray.add(obj);
			}

			int lastPage = (cs.getInt(8) % vo.getPerPage()) == 0 ? (cs.getInt(8) / vo.getPerPage()) : (cs.getInt(8) / vo.getPerPage()) + 1;
			jobj.put("cnt", cs.getInt(8));
			jobj.put("list", jArray);
			jobj.put("lastPage", lastPage);
		} catch (Exception error) {
			error.printStackTrace();
		}
		return jobj;
	}
}
