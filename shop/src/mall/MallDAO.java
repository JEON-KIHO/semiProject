package mall;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import database.Database;
import database.sqlVO;

public class MallDAO {
	// 목록 출력
	public JSONObject list(sqlVO vo) {
		JSONObject jobj = new JSONObject();
		try {
			String sql = "{call list(?, ?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement cs = Database.CON.prepareCall(sql);
			cs.setString(1, "mall");
			cs.setString(2, vo.getKey());
			cs.setString(3, vo.getWord());
			cs.setString(4, vo.getCol());
			cs.setInt(5, vo.getPage());
			cs.setInt(6, vo.getPerPage());
			cs.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(8, oracle.jdbc.OracleTypes.INTEGER);
			cs.execute();
			ResultSet rs = (ResultSet)cs.getObject(7);
			JSONArray jArray = new JSONArray();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("mall_id", rs.getString("mall_id"));
				obj.put("mall_name", rs.getString("mall_name"));
				obj.put("manager", rs.getString("manager"));
				obj.put("address", rs.getString("address"));
				obj.put("tel", rs.getString("tel"));
				obj.put("email", rs.getString("email"));
				obj.put("detail", rs.getString("detail"));
				jArray.add(obj);
			}
		
			int lastPage = (cs.getInt(8)%vo.getPerPage())==0?(cs.getInt(8)/vo.getPerPage()):(cs.getInt(8)/vo.getPerPage())+1;
			jobj.put("cnt", cs.getInt(8));
			jobj.put("list", jArray);
			jobj.put("lastPage", lastPage);
		} catch(Exception error) {
			error.printStackTrace();
		}
		return jobj;
	}
}
