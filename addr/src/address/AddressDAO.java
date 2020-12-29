package address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AddressDAO {
	
	public void update(AddressVO vo) {
		try {
			String sql = "update address set name = ?, tel = ?, addr = ? where seq = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getTel());
			ps.setString(3, vo.getAddr());
			ps.setInt(4, vo.getSeq());
			ps.execute();
		} catch(Exception error) {
			error.printStackTrace();
		}
	}
	
	public void delete(int seq) {
		try {
			String sql = "delete from address where seq = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setInt(1, seq);
			ps.execute();
		} catch(Exception error) {
			error.printStackTrace();
		}
	}
	
	public void insert(AddressVO vo) {
		try {
			String sql = "insert into address(seq, name, tel, addr) values(seq_add.nextval, ?, ?, ?)";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getTel());
			ps.setString(3, vo.getAddr());
			ps.execute();
		} catch(Exception error) {
			error.printStackTrace();
		}
	}
	
	public JSONArray list(String query) {
		JSONArray jArray = new JSONArray();
		try {
			String sql = "select * from address where name like ? or addr like ? or tel like ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, "%"+query+"%");
			ps.setString(2, "%"+query+"%");
			ps.setString(3, "%"+query+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				JSONObject j = new JSONObject();
				j.put("seq", rs.getInt("seq"));
				j.put("name", rs.getString("name"));
				j.put("tel", rs.getString("tel"));
				j.put("addr", rs.getString("addr"));
				jArray.add(j);
			}
		} catch(Exception error) {
			error.printStackTrace();
		}
		return jArray;
	}
}
