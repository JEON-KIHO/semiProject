package board;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import address.AddressVO;
import address.Database;

public class BoardDAO {
	public ArrayList<BoardVO> list(int page) { 
		ArrayList<BoardVO> array = new ArrayList<BoardVO>();
		
		try {
			String sql = "select * from (select rownum rn, tbl.* from (select * from board order by seq desc) tbl) where rn between ? and ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setInt(1, (page-1)*10 + 1);
			ps.setInt(2, page*10);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setRn(rs.getInt("rn"));
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setWdate(rs.getDate("wdate"));
				System.out.println(vo.toString());
				array.add(vo);
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
		return array;
	};
	
	public int count() {
		int count = 0;
		try {
			String sql = "select count(*) cnt from board";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt("cnt");
			}
			System.out.println(count);
		} catch (Exception error) {
			error.printStackTrace();
		}
		return count;
	}
}
