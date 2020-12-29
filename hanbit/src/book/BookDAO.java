package book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import hanbit.Database;

public class BookDAO {
	// count
	public int count(String word) {
		int count = 0;
		try {
			String sql = "select count(*) cnt from goodsinfo where code like ? or title like ? or writer like ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, "%"+word+"%");
			ps.setString(2, "%"+word+"%");
			ps.setString(3, "%"+word+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
		return count;
	}
	
	// list
	public ArrayList<BookVO> list(String word, int page) {
		ArrayList<BookVO> array = new ArrayList<BookVO>();
		try {
			String sql = "select * from(select rownum rn, tbl.* from(select * from goodsinfo where code like ? or title like ? or writer like ?) tbl) where rn between ? and ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, "%"+word+"%");
			ps.setString(2, "%"+word+"%");
			ps.setString(3, "%"+word+"%");
			ps.setInt(4, (page-1)*5 + 1);
			ps.setInt(5, page*5);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				BookVO vo = new BookVO();
				vo.setRn(rs.getInt("rn"));
				vo.setCode(rs.getString("code"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setPrice(rs.getInt("price"));
				array.add(vo);
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
		return array;
	}
}
