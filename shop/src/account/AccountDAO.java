package account;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONObject;

import database.Database;

public class AccountDAO {
	// create account
	public JSONObject create(AccountVO vo) {
		JSONObject jobj = new JSONObject();
		try {
			String sql = "{call create_account(?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement cs = Database.CON.prepareCall(sql);
			cs.setString(1, vo.getId());
			cs.setString(2, vo.getPw());
			cs.setString(3, vo.getName());
			cs.setInt(4, vo.getCode());;
			cs.setString(5, vo.getTitle());
			cs.registerOutParameter(6, oracle.jdbc.OracleTypes.INTEGER);
			cs.registerOutParameter(7, oracle.jdbc.OracleTypes.INTEGER);
			cs.execute();
			
			jobj.put("IC", cs.getInt(6));
			jobj.put("CC", cs.getInt(7));
			
		} catch (Exception error) {
			error.printStackTrace();
		}
		return jobj;
	}
	
	// login check
	public AccountVO login(String id) {
		AccountVO vo = new AccountVO();
		
		try {
			String sql = "select * from account where id = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				vo.setId(rs.getString("id"));
				vo.setPw(rs.getString("pw"));
				vo.setName(rs.getString("name"));
			}
		} catch(Exception error) {
			error.printStackTrace();
		}
		
		return vo;
	}
}
