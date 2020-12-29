package product;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import oracle.jdbc.proxy.annotation.Pre;

public class ProductDAO {
	// delete
	public void delete(String code) {
		try {
			String sql = "delete from tbl_product where code=?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, code);
			ps.execute();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	// update
	public void update(ProductVO vo) {
		try {
			String sql = "update tbl_product set pname=?, price=?, image=? where code=?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getPname());
			ps.setInt(2, vo.getPrice());
			ps.setString(3, vo.getImage());
			ps.setString(4, vo.getCode());
			ps.execute();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	// insert
	public void insert(ProductVO vo) {
		try {
			String sql = "insert into tbl_product(code,pname,price,image) values(?, ?, ?, ?)";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getCode());
			ps.setString(2, vo.getPname());
			ps.setInt(3, vo.getPrice());
			ps.setString(4, vo.getImage());
			ps.execute();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	// list
	public JSONObject list(String word, String order, int page, int num) {
		JSONObject jObject = new JSONObject();
		try {
			String sql = "{call list_product(?, ?, ?, ?, ?, ?)}";
			CallableStatement cs = Database.CON.prepareCall(sql);
			cs.setString(1, word);
			cs.setString(2, order);
			cs.setInt(3, page);
			cs.setInt(4, num);
			cs.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(6, oracle.jdbc.OracleTypes.INTEGER);
			cs.execute();
			
			ResultSet rs = (ResultSet)cs.getObject(5);
			int count = cs.getInt(6);
			int lastPage = count%num==0?count/num:count/num+1;
			JSONArray jArray = new JSONArray();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("rn", rs.getInt("rn"));
				obj.put("code", rs.getString("code"));
				obj.put("pname", rs.getString("pname"));
				obj.put("price", rs.getInt("price"));
				obj.put("image", rs.getString("image"));
				jArray.add(obj);
			}
			jObject.put("count", count);
			jObject.put("lastPage", lastPage);
			jObject.put("list", jArray);
		} catch (Exception error) {
			error.printStackTrace();
		}
		return jObject;
	}
	
	// read
	public ProductVO read(String code) {
		ProductVO vo = new ProductVO();
		try {
			String sql = "select * from tbl_product where code = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				vo.setCode(rs.getString("code"));
				vo.setPname(rs.getString("pname"));
				vo.setPrice(rs.getInt("price"));
				vo.setImage(rs.getString("image"));
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
		return vo;
	}
	
	
	
}
