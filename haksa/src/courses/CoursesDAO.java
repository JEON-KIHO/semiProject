package courses;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import database.Database;
import database.sqlVO;

public class CoursesDAO {
		
	// 강좌 제거
	public JSONObject delete(String lcode) {
			JSONObject jobj = new JSONObject();
		try {
			String sql = "{call del_cou(?, ?)";
			CallableStatement cs = Database.CON.prepareCall(sql);
			cs.setString(1, lcode);
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.INTEGER);
			cs.execute();
			
			jobj.put("cnt", cs.getInt(2));

		} catch (Exception error) {
			error.printStackTrace();
		}
		return jobj;
	}
	
	// 강좌 수정
		public void update(CoursesVO vo) {
			try {
				String sql = "update courses set lname = ?, hours = ?, room = ?, instructor = ?, capacity = ?, persons = ? where lcode = ?";
				PreparedStatement ps = Database.CON.prepareStatement(sql);
				ps.setString(1, vo.getLname());
				ps.setInt(2, vo.getHours());
				ps.setInt(3, vo.getRoom());
				ps.setInt(4, vo.getInstructor());
				ps.setInt(5, vo.getCapacity());
				ps.setInt(6, vo.getPersons());
				ps.setString(7, vo.getLcode());
				ps.execute();
			} catch (Exception error) {
				error.printStackTrace();
			}
		}

		// 강좌 입력
		public void insert(CoursesVO vo) {
			try {
				String sql = "insert into courses(lcode, lname, hours, room, instructor, capacity, persons) values(?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = Database.CON.prepareStatement(sql);
				ps.setString(1, vo.getLcode());
				ps.setString(2, vo.getLname());
				ps.setInt(3, vo.getHours());
				ps.setInt(4, vo.getRoom());
				ps.setInt(5, vo.getInstructor());
				ps.setInt(6, vo.getCapacity());
				ps.setInt(7, vo.getPersons());
				ps.execute();
			} catch (Exception error) {
				error.printStackTrace();
			}
		}

		// 강좌 읽기
		public CoursesVO read(String lcode) {
			CoursesVO vo = new CoursesVO();
			try {
				String sql = "select c.*, pname from courses c left join professors on c.instructor=pcode where lcode = ?";
				PreparedStatement ps = Database.CON.prepareStatement(sql);
				ps.setString(1, lcode);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					vo.setLcode(rs.getString("lcode"));
					vo.setLname(rs.getString("lname"));
					vo.setHours(rs.getInt("hours"));
					vo.setRoom(rs.getInt("room"));
					vo.setInstructor(rs.getInt("instructor"));
					vo.setCapacity(rs.getInt("capacity"));
					vo.setPersons(rs.getInt("persons"));
					vo.setPname(rs.getString("pname"));
				}
			} catch (Exception error) {
				error.printStackTrace();
			}
			return vo;
		}
	
	// simple 강좌 목록
	public ArrayList<CoursesVO> clist() {
		ArrayList<CoursesVO> array = new ArrayList<CoursesVO>();
		try {
			String sql = "select c.*, pname from courses c left join professors on instructor=pcode order by lname";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CoursesVO vo = new CoursesVO();
				vo.setLcode(rs.getString("lcode"));
				vo.setLname(rs.getString("lname"));
				vo.setPname(rs.getString("pname"));
				vo.setHours(rs.getInt("hours"));
				vo.setRoom(rs.getInt("room"));
				vo.setInstructor(rs.getInt("instructor"));
				vo.setCapacity(rs.getInt("capacity"));
				vo.setPersons(rs.getInt("persons"));
				array.add(vo);
			}
		} catch(Exception error) {
			error.printStackTrace();
		}
		return array;
	}
		
	// 강좌 목록
	public JSONObject list(sqlVO vo) {
		JSONObject jobj = new JSONObject();
		try {
			String sql = "{call list(?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement cs = Database.CON.prepareCall(sql);
			cs.setString(1, "cou_pro");
			cs.setString(2, vo.getKey());
			cs.setString(3, vo.getWord());
			cs.setInt(4, vo.getPage());
			cs.setInt(5, vo.getPerPage());
			cs.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(7, oracle.jdbc.OracleTypes.INTEGER);
			cs.execute();

			ResultSet rs = (ResultSet) cs.getObject(6);
			int count = cs.getInt(7);

			JSONArray jArray = new JSONArray();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("rn", rs.getInt("rn"));
				obj.put("lcode", rs.getString("lcode"));
				obj.put("lname", rs.getString("lname"));
				obj.put("room", rs.getString("room"));
				obj.put("hours", rs.getString("hours"));
				obj.put("pcode", rs.getInt("instructor"));
				obj.put("capacity", rs.getString("capacity"));
				obj.put("persons", rs.getString("persons"));
				obj.put("pname", rs.getString("pname"));
				obj.put("pdept", rs.getString("pdept"));

				jArray.add(obj);
			}

			int lastPage = (count % vo.getPerPage() == 0) ? count / vo.getPerPage() : count / vo.getPerPage() + 1;
			jobj.put("list", jArray);
			jobj.put("count", count);
			jobj.put("lastPage", lastPage);
		} catch (Exception error) {
			error.printStackTrace();
		}
		return jobj;
	}
}
