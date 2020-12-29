import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {
	public Connection con() {
		Connection con = null;
		try {
			// 오라클 jdbc 드라이버 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "web", "1234");
		} catch (Exception error) {
			error.printStackTrace();
		}
		return con;
	}
	
	// 사용자 목록
	public ArrayList<UserVO> list() {
		ArrayList<UserVO> array = new ArrayList<UserVO>();
		try {
			Connection con = con(); // 데이터베이스 접속
			String sql = "select * from tbl";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(); // sql 실행
			while(rs.next()) {
				UserVO vo = new UserVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setPassword(rs.getString("password"));
				array.add(vo);
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
			
		return array;
	}
	
	// 사용자 등록 method
	public void insert(UserVO vo) {
		try {
			Connection con = con();
			String sql = "insert into tbl(id, name, password) values(? ,?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getPassword());
			ps.execute();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	// 사용자 정보 읽기
	public UserVO read(String id) {
		UserVO vo = new UserVO();
		try {
			Connection con = con();
			String sql = "select * from tbl where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setPassword(rs.getString("password"));
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
		return vo;
	}
	
	// 사용자 정보 수정
	public void update(UserVO vo) {
		try {
			Connection con = con();
			String sql = "update tbl set name=?, password=? where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPassword());
			ps.setString(3, vo.getId());
			ps.execute();			
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	// 사용자 정보 삭제
	public void delete(String id) {
		try {
			Connection con = con();
			String sql = "delete from tbl where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.execute();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	
}
