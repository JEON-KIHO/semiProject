package oil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class OILDAO {
	public JSONObject list(String word) {
		JSONObject jobj = new JSONObject();
		JSONArray jArray = new JSONArray();
		try {
			String sql = "select * from tbl_oil where address like ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, "%"+word+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("code", rs.getString("code"));
				obj.put("name", rs.getString("name"));
				obj.put("address", rs.getString("address"));
				obj.put("price", rs.getInt("price"));
				obj.put("self", rs.getString("self"));
				obj.put("wdate", rs.getString("wdate"));
				jArray.add(obj);
			}
			jobj.put("list", jArray);
			
			sql = "select count(*) cnt from tbl_oil where address like ?";
			ps = Database.CON.prepareStatement(sql);
			ps.setString(1, "%"+word+"%");
			rs = ps.executeQuery();
			int count = 0;
			
			if(rs.next()) count = rs.getInt("cnt");
			
			jobj.put("count", count);
			int lastPage = count%10==0?count/10:(count/10)+1;
			jobj.put("lastPage", lastPage);
		} catch (Exception error) {
			error.printStackTrace();
		}
		return jobj;
	}
	
	public void insert(OILVO vo) {
		try {
			String sql = "insert into tbl_oil values(?, ?, ?, ?, ?, sysdate)";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getCode());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getAddres());
			ps.setInt(4, vo.getPrice());
			ps.setString(5, vo.getSelf());
			ResultSet rs = ps.executeQuery();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	public JSONObject read(String code) {
		JSONObject jobj = new JSONObject();
		JSONArray jArray = new JSONArray();
		try {
			String sql = "select * from tbl_oil where code = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("code", rs.getString("code"));
				obj.put("name", rs.getString("name"));
				obj.put("address", rs.getString("address"));
				obj.put("price", rs.getInt("price"));
				obj.put("self", rs.getString("self"));
				obj.put("wdate", rs.getString("wdate"));
				jArray.add(obj);
			}
			jobj.put("list", jArray);
		} catch (Exception error) {
			error.printStackTrace();
		}
		return jobj;
	}
}
