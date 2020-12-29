package account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.Database;

public class AccountDAO {
	
	
	public AccountVO read(String id) {
		AccountVO avo = new AccountVO();
		try {
			String sql ="select * from account where id=?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				avo.setId(rs.getString("id"));
				avo.setPw(rs.getString("pw"));
				avo.setCode(rs.getInt("code"));	
				avo.setName(rs.getString("name"));
				avo.setNickName(rs.getString("nickName"));
			}
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		return avo;
	}
	//정보수정??
	public void update(AccountVO avo) {
		try {
			String sql ="update account set id=?, pw=?, code=?, name=?, nickname=? where id=?";
			PreparedStatement ps =Database.CON.prepareStatement(sql);
			ps.setString(1, avo.getId());
			ps.setString(2, avo.getPw());
			ps.setInt(3, avo.getCode());
			ps.setString(4, avo.getName());
			ps.setString(5, avo.getNickName());
			ps.execute();
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	//회원가입?
	public void insert(AccountVO avo) {
		try {
			String sql ="insert into account(id, pw, code, name, nickname) values(?,?,?,?,?)";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, avo.getId());
			ps.setString(2, avo.getPw());
			ps.setInt(3, avo.getCode());
			ps.setString(4, avo.getName());
			ps.setString(5, avo.getNickName());
			ps.execute();
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	
	public AccountVO login(String id) {
		AccountVO avo = new AccountVO();
		try {
			String sql ="select * from account where id=?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				avo.setId(rs.getString("id"));
				avo.setPw(rs.getString("pw"));

			}
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		return avo;
	}
}