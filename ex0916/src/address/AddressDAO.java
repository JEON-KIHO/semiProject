package address;

import java.sql.*;
import java.util.ArrayList;

public class AddressDAO {
	
	public Connection con() {
		Connection con = null;
		try {
			// 오라클 jdbc 드라이버 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "user02", "1234");
		} catch (Exception error) {
			error.printStackTrace();
		}
		return con;
	}
	
	
	// 검색 데이터 갯수 구하기
	public int total(String key, String word) {
		int count = 0;
		try {
			Connection con = con();
			String sql = "select count(*) cnt from tbl_address where " + key + " like ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + word + "%");
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
		return count;
	}
	
	// 목록 출력
	public ArrayList<AddressVO> list(String key, String word, int perPage, int page) {
		ArrayList<AddressVO> array = new ArrayList<AddressVO>();
		try {
			Connection con = con();
			String sql = "select * from ";
			sql += "(select rownum rn, tbl.* from ";
			sql += "(select * ";
			sql += "from tbl_address ";
			sql += "where " + key + " like ? ";
			sql += "order by seq desc) tbl) ";
			sql += "where rn between ? and ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, "%" + word + "%");
			int start = (page-1)*perPage+1;
			int end = (page*perPage);
			ps.setInt(2, start);
			ps.setInt(3, end);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				AddressVO vo = new AddressVO();
				vo.setRow(rs.getInt("rn"));
				vo.setSeq(rs.getInt("seq"));
				vo.setName(rs.getString("name"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
				System.out.println(vo.toString());
				array.add(vo);
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
		return array;
	}
}
