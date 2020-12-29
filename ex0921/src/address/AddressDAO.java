package address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AddressDAO {
	// data count
	public int count() {
		int count = 0;
		try {
			String sql = "select count(*) cnt from tbl_address";
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
	
	// list
	public ArrayList<AddressVO> list(int page) { 
		ArrayList<AddressVO> array = new ArrayList<AddressVO>();
		
		try {
			String sql = "select * from (select rownum rn, tbl.* from (select * from tbl_address order by seq desc) tbl) where rn between ? and ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setInt(1, (page-1)*5 + 1);
			ps.setInt(2, page*5);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AddressVO vo = new AddressVO();
				vo.setRn(rs.getInt("rn"));
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
	};
	
	// read
	
	
	// insert
	public void insert(AddressVO vo) {
		try {
			String sql = "insert into tbl_address(seq, name, tel, address) values(seq_address.nextval, ?, ?, ?)";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getTel());
			ps.setString(3, vo.getAddress());
			ps.execute();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	// update
	public void update(AddressVO vo) {
		try {
			String sql = "update tbl_address set name=?, tel=?, address=? where seq=?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getTel());
			ps.setString(3, vo.getAddress());
			ps.setInt(4, vo.getSeq());
			ps.execute();
		} catch(Exception error) {
			error.printStackTrace();
		}
	}

	
	// delete
	public void delete(int seq) {
		try {
			String sql = "delete from tbl_address where seq = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setInt(1, seq);
			ps.execute();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
}
