package enrollments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import courses.CoursesVO;
import database.Database;

public class EnrollmentsDAO {
	
	public ArrayList<EnrollmentsVO> list(String scode) {
		ArrayList<EnrollmentsVO> array = new ArrayList<EnrollmentsVO>();
		
		try {
			String sql = "select * from enroll where scode = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setNString(1, scode);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				EnrollmentsVO vo = new EnrollmentsVO();
				vo.setScode(rs.getString("scode"));
				vo.setSname(rs.getString("sname"));
				vo.setLcode(rs.getString("lcode"));
				vo.setLname(rs.getString("lname"));
				vo.setGrade(rs.getInt("grade"));
				vo.setEdate(rs.getDate("edate"));
				array.add(vo);
				}
		} catch(Exception error) {
			error.printStackTrace();
		}
		return array;
	}
}
