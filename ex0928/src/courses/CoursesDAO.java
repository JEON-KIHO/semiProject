package courses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.Database;

public class CoursesDAO {

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
