package board;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import address.Database;
import address.QueryVO;

public class BoardDAO {
	// 목록 출력
		public ArrayList<BoardVO> list(QueryVO v) {
			ArrayList<BoardVO> array = new ArrayList<BoardVO>();
			String sql = "{call list(?, ?, ?, ?, ?, ?, ?)";
			try {
				CallableStatement cs = Database.CON.prepareCall(sql);
				cs.setString(1, v.getTable());
				cs.setString(2, v.getKey());
				cs.setString(3, v.getWord());
				cs.setInt(4, v.getPerPage());
				cs.setInt(5, v.getPage());
				cs.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
				cs.registerOutParameter(7, oracle.jdbc.OracleTypes.INTEGER);
				cs.execute();
				ResultSet rs = (ResultSet)cs.getObject(6);
				while(rs.next()) {
					BoardVO vo = new BoardVO();
					vo.setRn(rs.getInt("rn"));
					vo.setSeq(rs.getInt("seq"));
					vo.setTitle(rs.getString("title"));
					vo.setWriter(rs.getString("writer"));
					vo.setWdate(rs.getDate("wdate"));
					array.add(vo);
				}
			} catch (Exception error) {
				error.printStackTrace();
			}
			return array;
		}
		
		// 검색 갯수 출력
		public int count(QueryVO v) {
			int count = 0;
			String sql = "{call list(?, ?, ?, ?, ?, ?, ?)";
			try {
				CallableStatement cs = Database.CON.prepareCall(sql);
				cs.setString(1, v.getTable());
				cs.setString(2, v.getKey());
				cs.setString(3, v.getWord());
				cs.setInt(4, v.getPerPage());
				cs.setInt(5, v.getPage());
				cs.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
				cs.registerOutParameter(7, oracle.jdbc.OracleTypes.INTEGER);
				cs.execute();
				count = cs.getInt(7);
			} catch (Exception error) {
				error.printStackTrace();
			}
			return count;
		}
		
		public int total(String key, String word) {
			int count = 0;
			try {
				String sql = "select count(*) cnt from tbl_address where " + key + " like ?";
				PreparedStatement ps = Database.CON.prepareStatement(sql);
				ps.setString(1, "%" + word + "%");
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					count = rs.getInt("cnt");
				}
			} catch (Exception error) {
				error.printStackTrace();
			}
			return count;
		}
}
