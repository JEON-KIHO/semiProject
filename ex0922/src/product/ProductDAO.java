package product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductDAO {
	// count
	public int count(String word, String order) {
		int count = 0;
		try {
			String sql = "select count(*) cnt from tbl_product where code like ? or pname like ? order by price " +order;
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, '%'+word+'%');
			ps.setString(2, '%'+word+'%');
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
	public ArrayList<ProductVO> list(int page, String word, String order) {
		ArrayList<ProductVO> array = new ArrayList<ProductVO>();
		try {
			String sql = "select * from (select rownum rn, tbl.* from (select * from tbl_product where code like ? or pname like ? order by price "+order+") tbl) where rn between ? and ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, '%'+word+'%');
			ps.setString(2, '%'+word+'%');
			ps.setInt(3, (page-1)*12 + 1);
			ps.setInt(4, page*12);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setRn(rs.getInt("rn"));
				vo.setCode(rs.getString("code"));
				vo.setPname(rs.getString("pname"));
				vo.setPrice(rs.getInt("price"));
				vo.setImage(rs.getString("image"));
				array.add(vo);
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
		return array;
	}
	
	// insert
	public void insert(ProductVO vo) {
		try {
		String sql = "";
		PreparedStatement ps = Database.CON.prepareStatement(sql);
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	// update
	public void update(ProductVO vo) {
		try {
			String sql = "";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			} catch (Exception error) {
				error.printStackTrace();
			}
	}
	
	// delete
	public void delete(String code) {
		try {
			String sql = "";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			} catch (Exception error) {
				error.printStackTrace();
			}
}
}
