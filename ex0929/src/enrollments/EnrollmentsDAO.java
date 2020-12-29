package enrollments;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import courses.CoursesVO;
import database.Database;

public class EnrollmentsDAO {
	
	// 수정
	public void update(EnrollmentsVO vo) {
		try {
			String sql = "update enrollments set grade=? where lcode=? and scode=?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setInt(1, vo.getGrade());
			ps.setString(2, vo.getLcode());
			ps.setString(3, vo.getScode());
			ps.execute();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	
	
	// 삭제
		public void delete(String lcode, String scode) {
			try {
				String sql = "delete from enrollments where lcode = ? and scode = ?";
				PreparedStatement ps = Database.CON.prepareStatement(sql);
				ps.setString(2, scode);
				ps.setString(1, lcode);
				ps.execute();
			} catch (Exception error) {
				error.printStackTrace();
			}
			
		}
	
	
	// 입력
	public int insert(String lcode, String scode) {
		int count = 1;
		try {
			String sql = "{call in_enroll(?, ?, ?)";
			CallableStatement cs = Database.CON.prepareCall(sql);
			cs.setString(1, scode);
			cs.setString(2, lcode);
			cs.registerOutParameter(3, oracle.jdbc.OracleTypes.INTEGER);
			cs.execute();
			count = cs.getInt(3);
		} catch (Exception error) {
			error.printStackTrace();
		}
		return count;
	}
	
	
	// 목록
	public ArrayList<EnrollmentsVO> list(String scode) {
		ArrayList<EnrollmentsVO> array = new ArrayList<EnrollmentsVO>();
		
		try {
			String sql = "select * from enroll where scode = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, scode);
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
	
	
	// 목록
		public ArrayList<EnrollmentsVO> slist(String lcode) {
			ArrayList<EnrollmentsVO> array = new ArrayList<EnrollmentsVO>();
			
			try {
				String sql = "select * from enroll where lcode = ?";
				PreparedStatement ps = Database.CON.prepareStatement(sql);
				ps.setString(1, lcode);
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
