package bbs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import hanbit.Database;

public class BBSDAO {
	
	public JSONObject list(String word, int page) {
		JSONObject jObject = new JSONObject();
		try {
			String sql = "select * from(select rownum rn, tbl.* from(select * from bbs where content like ? or title like ? or writer like ? order by seq desc) tbl) where rn between ? and ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, "%"+word+"%");
			ps.setString(2, "%"+word+"%");
			ps.setString(3, "%"+word+"%");
			ps.setInt(4, (page-1)*5 + 1);
			ps.setInt(5, page*5);
			ResultSet rs = ps.executeQuery();
			
			JSONArray jArray = new JSONArray();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("rn", rs.getInt("rn"));
				obj.put("seq", rs.getInt("seq"));
				obj.put("title", rs.getString("title"));
				obj.put("writer", rs.getString("writer"));
				obj.put("content", rs.getString("content"));
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				String strDate = sf.format(rs.getDate("wdate"));
				obj.put("wdate", strDate);
				jArray.add(obj);
			}
			jObject.put("list", jArray);
			
			sql = "select count(*) cnt from bbs where title like ? or writer like ? or content like ?";
			ps = Database.CON.prepareStatement(sql);
			ps.setString(1, "%"+word+"%");
			ps.setString(2, "%"+word+"%");
			ps.setString(3, "%"+word+"%");
			rs = ps.executeQuery();
			int count = 0;
			
			if(rs.next()) count = rs.getInt("cnt");
			
			jObject.put("count", count);
			int lastPage = count%5==0?count/5:(count/5)+1;
			jObject.put("lastPage", lastPage);
		} catch (Exception error) {
			error.printStackTrace();
		}
		return jObject;
	}
}
