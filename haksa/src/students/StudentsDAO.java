package students;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import courses.CoursesVO;
import database.Database;
import database.sqlVO;

public class StudentsDAO {
	
	
	// 학생 제거
	public JSONObject delete(String scode) {
		JSONObject jobj = new JSONObject();
		try {
			String sql = "{call del_stu(?, ?)";
			CallableStatement cs = Database.CON.prepareCall(sql);
			cs.setString(1, scode);
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.INTEGER);
			cs.execute();

			jobj.put("cnt", cs.getInt(2));
		} catch (Exception error) {
			error.printStackTrace();
		}
		return jobj;
	}
	
	// 학생 수정
	public void update(StudentsVO vo) {
		try {
			String sql = "update students set sname = ?, dept = ?, birthday = ?, year = ?, advisor = ? where scode = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getSname());
			ps.setString(2, vo.getDept());
			ps.setDate(3, vo.getBirthday());
			ps.setInt(4, vo.getYear());
			ps.setInt(5, vo.getAdvisor());
			ps.setString(6, vo.getScode());
			ps.execute();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}

	// 학생 입력
	public void insert(StudentsVO vo) {
		try {
			String sql = "insert into Students(scode, sname, dept, birthday, year, advisor) values(?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getScode());
			ps.setString(2, vo.getSname());
			ps.setString(3, vo.getDept());
			ps.setDate(4, vo.getBirthday());
			ps.setInt(5, vo.getYear());
			ps.setInt(6, vo.getAdvisor());
			ps.execute();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}

	// 학생 읽기
	public StudentsVO read(String scode) {
		StudentsVO vo = new StudentsVO();
		try {
			String sql = "select s.*, pname from students s left join professors p on pcode=advisor where scode = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, scode);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				vo.setScode(rs.getString("scode"));
				vo.setSname(rs.getString("sname"));
				vo.setDept(rs.getString("dept"));
				vo.setBirthday(rs.getDate("birthday"));
				vo.setYear(rs.getInt("year"));
				vo.setAdvisor(rs.getInt("advisor"));
				vo.setPname(rs.getString("pname"));
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
		return vo;
	}
	
	// 수강 신청 목록
	public ArrayList<CoursesVO> list() {
		ArrayList<CoursesVO> array = new ArrayList<CoursesVO>();
		
		try {
			String sql = "select c.*, pname from courses c, professors where instructor=pcode";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CoursesVO vo = new CoursesVO();
				vo.setLcode(rs.getString("lcode"));
				vo.setLname(rs.getString("lname"));
				vo.setHours(rs.getInt("hours"));
				vo.setPname(rs.getString("pname"));
				array.add(vo);
				}
		} catch(Exception error) {
			error.printStackTrace();
		}
		return array;
	}
	
	// 학생이 수강하는 과목 목록
	public JSONObject elist(String scode) {
		JSONObject jobj = new JSONObject();
		try {
			String sql = "{call list_stu(?, ?)";
			CallableStatement cs = Database.CON.prepareCall(sql);
			cs.setString(1, scode);
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();
			
			ResultSet srs=(ResultSet)cs.getObject(2);
			
			JSONArray jArray = new JSONArray();
			while(srs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("lcode", srs.getString("lcode"));
				obj.put("scode", srs.getInt("scode"));
				obj.put("lname", srs.getString("lname"));
				obj.put("hours", srs.getInt("hours"));
				obj.put("edate", srs.getString("edate"));
				obj.put("room", srs.getInt("room"));
				obj.put("instructor", srs.getInt("instructor"));
				obj.put("pname", srs.getString("pname"));
				jArray.add(obj);
			}
			jobj.put("elist", jArray);
		} catch(Exception error) {
			error.printStackTrace();
		}
		return jobj;
	}

	// 학생 목록
	public JSONObject list(sqlVO vo) {
		JSONObject jobj = new JSONObject();
		try {
			String sql = "{call list(?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement cs = Database.CON.prepareCall(sql);
			cs.setString(1, "stu_pro");
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
				obj.put("scode", rs.getString("scode"));
				obj.put("sname", rs.getString("sname"));
				obj.put("dept", rs.getString("dept"));
				obj.put("year", rs.getString("year"));

				if (rs.getDate("birthday") != null) {
					String birthday = sdf.format(rs.getDate("birthday"));
					obj.put("birthday", birthday);
				}
				obj.put("advisor", rs.getInt("advisor"));
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
