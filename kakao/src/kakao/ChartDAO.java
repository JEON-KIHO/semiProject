package kakao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;

import database.Database;

public class ChartDAO {
	// 학과별 학생 수
	public JSONArray deptCount() {
		JSONArray jArray = new JSONArray();
		try {
			String sql = "select dept, count(sname) ave from students group by dept order by count(sname) desc";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			JSONArray ja = new JSONArray();
			
			ja.add("dept");
			ja.add("students count");
			jArray.add(ja);
			
			while(rs.next()) {
				ja = new JSONArray();
				ja.add(rs.getString("dept"));
				ja.add(rs.getDouble("ave"));
				jArray.add(ja);
			}
			
		} catch(Exception error) {
			error.printStackTrace();
		}
		return jArray;
	}
	
	// 코스별 평균 점수
	public JSONArray avgCourses() {
		JSONArray jArray = new JSONArray();
		try {
			String sql = "select lcode, lname, avg(grade) ave from enroll group by lcode, lname order by avg(grade) desc";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			JSONArray ja = new JSONArray();
			
			ja.add("name");
			ja.add("score");
			jArray.add(ja);
			
			while(rs.next()) {
				ja = new JSONArray();
				ja.add(rs.getString("lname"));
				ja.add(rs.getDouble("ave"));
				jArray.add(ja);
			}
			
		} catch(Exception error) {
			error.printStackTrace();
		}
		return jArray;
	}
	
	// 학생별 평균 점수
	public JSONArray avgStudents() {
		JSONArray jArray = new JSONArray();
		try {
			String sql = "select scode, sname, avg(grade) ave from enroll group by scode, sname order by avg(grade) desc";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			JSONArray ja = new JSONArray();
			
			ja.add("name");
			ja.add("score");
			jArray.add(ja);
			
			while(rs.next()) {
				ja = new JSONArray();
				ja.add(rs.getString("sname"));
				ja.add(rs.getDouble("ave"));
				jArray.add(ja);
			}
			
		} catch(Exception error) {
			error.printStackTrace();
		}
		return jArray;
	}
}
