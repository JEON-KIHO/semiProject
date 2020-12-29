package book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookDAO {
	// 도서 목록
	public ArrayList<BookVO> list() {
		ArrayList<BookVO> array = new ArrayList<BookVO>();
		String sql = "select * from tbl_book order by code";
		try {
		PreparedStatement ps = Database.CON.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			BookVO vo = new BookVO();
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
	
	// 도서 읽기
	public BookVO read(String code) {
		BookVO vo = new BookVO();
		String sql = "select * from tbl_book where code=?";
		try {
		PreparedStatement ps = Database.CON.prepareStatement(sql);
		ps.setString(1, code);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			vo.setCode(rs.getString("code"));
			vo.setTitle(rs.getString("title"));
			vo.setWriter(rs.getString("writer"));
			vo.setPrice(rs.getInt("price"));
		}
		} catch (Exception error) {
			error.printStackTrace();
		}
		return vo;
	}
	
	// 도서 입력
	public void insert(BookVO vo) {
		try {
			String sql = "insert into tbl_book(code, title, writer, price) values(?, ?, ?, ?)";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getCode());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getWriter());
			ps.setInt(4, vo.getPrice());
			ps.execute();
		} catch(Exception error) {
			error.printStackTrace();
		}
	}
	
	// 도서 수정
	public void update(BookVO vo) {
		try {
			String sql = "update tbl_book set title=?, writer=?, price=? where code=?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getWriter());
			ps.setInt(3, vo.getPrice());
			ps.setString(4, vo.getCode());
			ps.execute();
		} catch(Exception error) {
			error.printStackTrace();
		}
	}
	
	// 도서 삭제
	public void delete(String code) {
		try {
			String sql = "delete from tbl_book where code=?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, code);
			ps.execute();
		} catch(Exception error) {
			error.printStackTrace();
		}
	}
	
}
