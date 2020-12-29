package students;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.Database;
import professors.ProfessorsVO;

public class StudentsDAO {
	
	public ArrayList<StudentsVO> list(String key, String word) {
		ArrayList<StudentsVO> array = new ArrayList<StudentsVO>();
		
		try {
			String sql = "select s.*, pname from students s, professors p where advisor=pcode and " +key+ " like ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, "%"+word+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				StudentsVO vo = new StudentsVO();
				vo.setScode(rs.getString("scode"));
				vo.setSname(rs.getString("sname"));
				vo.setDept(rs.getString("dept"));
				vo.setBirthday(rs.getDate("birthday"));
				vo.setPname(rs.getString("pname"));
				array.add(vo);
				}
		} catch(Exception error) {
			error.printStackTrace();
		}
		return array;
	}
	
	public StudentsVO read(String scode) {
		StudentsVO vo = new StudentsVO();
		try {
			String sql = "select s.*, pname from students s, professors where advisor=pcode and scode = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, scode);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				vo.setScode(rs.getString("scode"));
				vo.setSname(rs.getString("sname"));
				vo.setDept(rs.getString("dept"));
				vo.setBirthday(rs.getDate("birthday"));
				vo.setPname(rs.getString("pname"));
				}
		} catch(Exception error) {
			error.printStackTrace();
		}
		return vo;
	}
}
