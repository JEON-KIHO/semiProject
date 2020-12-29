package professors;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import database.Database;
import database.sqlVO;
import students.StudentsVO;

public class ProfessorsDAO {
	// 교수 제거
	public JSONObject delete(String pcode) {
		JSONObject jobj = new JSONObject();
		try {
			String sql ="{call del_pro(?, ?, ?)";
			CallableStatement cs = Database.CON.prepareCall(sql);
			cs.setString(1, pcode);
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.INTEGER);
			cs.registerOutParameter(3, oracle.jdbc.OracleTypes.INTEGER);
			cs.execute();
			
			jobj.put("scnt", cs.getInt(2));
			jobj.put("ccnt", cs.getInt(3));
		} catch(Exception error) {
			error.printStackTrace();
		}
		return jobj;
	}
	
	// 교수 수정
	public void update(ProfessorsVO vo) {
		try {
			String sql = "update professors set pname = ?, dept = ?, hiredate = ?, title = ?, salary = ? where pcode = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getPname());
			ps.setString(2, vo.getDept());
			ps.setDate(3, vo.getHiredate());
			ps.setString(4, vo.getTitle());
			ps.setInt(5, vo.getSalary());
			ps.setString(6, vo.getPcode());
			ps.execute();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	// 교수 입력
	public void insert(ProfessorsVO vo) {
		try {
			String sql = "insert into professors(pcode, pname, dept, hiredate, title, salary) values(?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getPcode());
			ps.setString(2, vo.getPname());
			ps.setString(3, vo.getDept());
			ps.setDate(4, vo.getHiredate());
			ps.setString(5, vo.getTitle());
			ps.setInt(6, vo.getSalary());
			ps.execute();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	// 교수 읽기
	public ProfessorsVO read(String pcode) {
		ProfessorsVO vo = new ProfessorsVO();
		try {
			String sql = "select * from professors where pcode = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, pcode);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				vo.setPcode(rs.getString("pcode"));
				vo.setPname(rs.getString("pname"));
				vo.setDept(rs.getString("dept"));
				vo.setHiredate(rs.getDate("hiredate"));
				vo.setTitle(rs.getString("title"));
				vo.setSalary(rs.getInt("salary"));
			}
		} catch(Exception error) {
			error.printStackTrace();
		}
		return vo;
	}
	// 교수가 담당하는 학생 및 과목 목록
	public JSONObject plist(String pcode) {
		JSONObject jobj = new JSONObject();
		try {
			String sql = "{call list_pro(?, ?, ?)";
			CallableStatement cs = Database.CON.prepareCall(sql);
			cs.setString(1, pcode);
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();
			
			ResultSet srs=(ResultSet)cs.getObject(2);
			ResultSet crs=(ResultSet)cs.getObject(3);
			
			JSONArray jArray = new JSONArray();
			while(srs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("scode", srs.getString("scode"));
				obj.put("sname", srs.getString("sname"));
				obj.put("dept", srs.getString("dept"));
				obj.put("year", srs.getInt("year"));
				obj.put("birthday", srs.getString("birthday"));
				jArray.add(obj);
			}
			jobj.put("slist", jArray);
			
			jArray = new JSONArray();
			while(crs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("lcode", crs.getString("lcode"));
				obj.put("lname", crs.getString("lname"));
				obj.put("hours", crs.getInt("hours"));
				obj.put("room", crs.getInt("room"));
				obj.put("instructor", crs.getInt("instructor"));
				obj.put("capacity", crs.getInt("capacity"));
				obj.put("persons", crs.getInt("persons"));
				jArray.add(obj);
			}
			jobj.put("clist", jArray);
		} catch(Exception error) {
			error.printStackTrace();
		}
		return jobj;
	}
	
	
	// 교수 목록
	public JSONObject list(sqlVO vo) {
		JSONObject jobj = new JSONObject();
		try {
			String sql = "{call list(?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement cs = Database.CON.prepareCall(sql);
			cs.setString(1, vo.getTable());
			cs.setString(2, vo.getKey());
			cs.setString(3, vo.getWord());
			cs.setInt(4, vo.getPage());
			cs.setInt(5, vo.getPerPage());
			cs.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(7, oracle.jdbc.OracleTypes.INTEGER);
			cs.execute();
			
			ResultSet rs = (ResultSet)cs.getObject(6);
			int count = cs.getInt(7);
			
			JSONArray jArray = new JSONArray();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			DecimalFormat df = new DecimalFormat("#,###");
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("rn", rs.getInt("rn"));
				obj.put("pcode", rs.getString("pcode"));
				obj.put("pname", rs.getString("pname"));
				obj.put("dept", rs.getString("dept"));
				obj.put("title", rs.getString("title"));
				
				String hiredate = "";
				if(rs.getDate("hiredate")!=null) {
					hiredate = sdf.format(rs.getDate("hiredate"));
				}
				obj.put("hiredate", hiredate);
				
				String salary = df.format(rs.getInt("salary"));
				obj.put("salary", salary);
				jArray.add(obj);
			}
			
			int lastPage = (count%vo.getPerPage()==0)?count/vo.getPerPage():count/vo.getPerPage()+1;
			jobj.put("list", jArray);
			jobj.put("count", count);
			jobj.put("lastPage", lastPage);
		} catch (Exception error) {
			error.printStackTrace();
		}
		return jobj;
	}
}
