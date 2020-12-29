package address;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AddressDAO0 implements AddressInterface0{
	
	
	//데이터베이스 Connect
	   public Connection con() {
	      Connection con = null;
	      try {
	         //오라클  jdbc 드라이버설정
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "user01", "1234");
	      }catch(Exception e) {
	         System.out.println(e.toString());
	      }
	      return con;
	   }

	@Override
	public ArrayList<AddressVO0> list() throws Exception {
		ArrayList<AddressVO0> array = new ArrayList<AddressVO0>();
		Connection con = con();
		String sql="select * from tbl_address";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			AddressVO0 vo = new AddressVO0();
			vo.setSeq(rs.getInt("SEQ"));
			vo.setName(rs.getString("NAME"));
			vo.setTel(rs.getString("TEL"));
			vo.setAddress(rs.getString("ADDRESS"));
			array.add(vo);
		}
		return array;
	}

	@Override
	public AddressVO0 read(int seq) throws Exception {
		AddressVO0 vo = new AddressVO0();
		
			Connection con = con();
			String sql = "select * from tbl_address where seq=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, seq);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				vo.setSeq(rs.getInt("SEQ"));
				vo.setName(rs.getString("NAME"));
				vo.setTel(rs.getString("TEL"));
				vo.setAddress(rs.getString("ADDRESS"));
			}
		return vo;
	}

	@Override
	public void insert(AddressVO0 vo) throws Exception {
			Connection con = con();
			String sql = "insert into TBL_ADDRESS(SEQ, NAME, TEL, ADDRESS) values(SEQ_NO.nextval ,?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getTel());
			ps.setString(3, vo.getAddress());
			ps.execute();
	}

	@Override
	public void delete(int seq) throws Exception {
		Connection con = con();
		String sql = "delete from tbl_address where seq=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, seq);
		ps.execute();
	}

	@Override
	public void update(AddressVO0 vo) throws Exception {
		Connection con = con();
		String sql = "update TBL_ADDRESS set NAME=?, TEL=?, ADDRESS=? where SEQ=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, vo.getName());
		ps.setString(2, vo.getTel());
		ps.setString(3, vo.getAddress());
		ps.setInt(4, vo.getSeq());
		ps.execute();
	}
	
	
	
}
