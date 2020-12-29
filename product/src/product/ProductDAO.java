package product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ProductDAO {
	
	public void insert(ProductVO vo) {
		try {
			String sql = "insert into product(code, name, price, image) values(?, ?, ?, ?)";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getCode());
			ps.setString(2, vo.getName());
			ps.setInt(3, vo.getPrice());
			ps.setString(4, vo.getImage());
			ps.execute();
		} catch(Exception error) {
			error.printStackTrace();
		}
	}
	public void delete(String code) {
		try {
			String sql = "delete from product where code = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, code);
			ps.execute();
		} catch(Exception error) {
			error.printStackTrace();
		}
	}
	
	public JSONObject read(String code) {
		JSONObject j = new JSONObject();
		try {
			String sql = "select * from product where code = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				j.put("code", rs.getString("code"));
				j.put("name", rs.getString("name"));
				DecimalFormat df = new DecimalFormat("#,###");
				j.put("price", df.format(rs.getInt("price")));
				j.put("image", rs.getString("image"));
			}
		} catch(Exception error) {
			error.printStackTrace();
		}
		return j;
	}
	
	public JSONArray list(String query, String key, String order) {
		JSONArray jArray = new JSONArray();
		try {
			String sql = "select * from product where name like ? or code like ? or price like ? order by "+key+" "+order;
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			String query1 = "%" + query + "%";
			ps.setString(1, query1);
			ps.setString(2, query1);
			ps.setString(3, query1);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				JSONObject j = new JSONObject();
				j.put("code", rs.getString("code"));
				j.put("name", rs.getString("name"));
				DecimalFormat df = new DecimalFormat("#,###");
				j.put("price", df.format(rs.getInt("price")));
				j.put("image", rs.getString("image"));
				jArray.add(j);
			}
		} catch(Exception error) {
			error.printStackTrace();
		}
		return jArray;
	}
}
