package enrollments;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import database.Database;

public class EnrollmentsDAO {
	// 점수 수정
	public void update(EnrollmentsVO evo) {
		try {
			String sql = "update Enrollments set grade=? where scode=? and lcode=?";
				PreparedStatement ps = Database.CON.prepareStatement(sql);
				ps.setInt(1, evo.getGrade());
				ps.setString(2, evo.getScode());
				ps.setString(3, evo.getLcode());
				ps.execute();
		}catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	// 수강 제거
	public void delete(String scode, String lcode) {
		try {
			String sql = "delete from enrollments where scode=? and lcode=?";
				PreparedStatement ps = Database.CON.prepareStatement(sql);
				ps.setString(1, scode);
				ps.setString(2, lcode);
				ps.execute();
		}catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	// 수강 입력
	public int insert(String scode, String lcode) {
		int count = 0;
		try {
			String sql = "{call in_enroll(?, ?, ?)";
				CallableStatement cs = Database.CON.prepareCall(sql);
				cs.setString(1, scode);
				cs.setString(2, lcode);
				cs.registerOutParameter(3, oracle.jdbc.OracleTypes.INTEGER);
				cs.execute();
				count = cs.getInt(3);
		}catch (Exception error) {
			error.printStackTrace();
		}
		return count;
	}
	
	// 수강 신청 목록
	public JSONObject list(String code) {
		JSONObject jobj = new JSONObject();
		try {
			String sql = "select * from llist where scode = ? or lcode = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, code);
			ps.setString(2, code);
			ResultSet rs = ps.executeQuery();
			
			JSONArray jArray = new JSONArray();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("lcode", rs.getString("lcode"));
				obj.put("lname", rs.getString("lname"));
				obj.put("scode", rs.getString("scode"));
				obj.put("sname", rs.getString("sname"));
				obj.put("grade", rs.getInt("grade"));
				obj.put("hours", rs.getInt("hours"));
				obj.put("pname", rs.getString("pname"));
				obj.put("year", rs.getInt("year"));
				obj.put("room", rs.getInt("room"));
				obj.put("dept", rs.getString("dept"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String strDate = sdf.format(rs.getDate("edate"));
				obj.put("edate", strDate);
				jArray.add(obj);
			}
			jobj.put("list", jArray);
		} catch (Exception error) {
			error.printStackTrace();
		}
		return jobj;
	}
}
