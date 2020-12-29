package courses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.Database;

public class CoursesDAO {
	// 강좌 정보
	public CoursesVO read(String lcode) {
		CoursesVO vo = new CoursesVO();
		try {
			String sql = "select c.*, pname, dept from courses c left join professors on instructor=pcode where lcode = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, lcode);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				vo.setLcode(rs.getString("lcode"));
				vo.setLname(rs.getString("lname"));
				vo.setHours(rs.getInt("hours"));
				vo.setPname(rs.getString("pname"));
				vo.setDept(rs.getString("dept"));
				}
		} catch (Exception error) {
			error.printStackTrace();
		}
		return vo;
	}
	
	
	// 강좌 조건 목록
	public ArrayList<CoursesVO> list(String key,String word) {
		ArrayList<CoursesVO> array = new ArrayList<CoursesVO>();
		try {
			String sql = "select c.*, pname, dept from courses c left join professors on instructor=pcode where " +key+ " like ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, "%"+word+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CoursesVO vo = new CoursesVO();
				vo.setLcode(rs.getString("lcode"));
				vo.setLname(rs.getString("lname"));
				vo.setHours(rs.getInt("hours"));
				vo.setPname(rs.getString("pname"));
				vo.setDept(rs.getString("dept"));
				array.add(vo);
				}
		} catch(Exception error) {
			error.printStackTrace();
		}
		return array;
	}
	
	
	// 강좌 목록
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
}
