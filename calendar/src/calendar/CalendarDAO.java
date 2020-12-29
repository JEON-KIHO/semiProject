package calendar;

import java.sql.PreparedStatement;

public class CalendarDAO {
	public void insert(CalendarVO vo) {
		try {
			String sql = "insert into day values(seq_day.nextval, ?, ?, ?, ?, ?)";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setInt(1, vo.getYear());
			ps.setInt(2, vo.getMonth());
			ps.setInt(3, vo.getWom());
			ps.setInt(4, vo.getDow());
			ps.setInt(5, vo.getDay());
			ps.execute();
			ps.close();
		} catch(Exception error) {
			error.printStackTrace();
		}
	}
	
	public void yearInsert(int year) {
		try {
			String sql = "insert into year values(?)";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setInt(1, year);
			ps.execute();
			ps.close();
		} catch(Exception error) {
			error.printStackTrace();
		}
	}
	
	public void monthInsert(int year, int month) {
		try {
			String sql = "insert into month values(?, ?)";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setInt(1, year);
			ps.setInt(2, month);
			ps.execute();
			ps.close();
		} catch(Exception error) {
			error.printStackTrace();
		}
	}
	
	public void dayInsert(int year, int month, int day, int wom, int dow) {
		try {
			String sql = "insert into day values(?, ?, ?, ?, ?)";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setInt(1, year);
			ps.setInt(2, month);
			ps.setInt(3, day);
			ps.setInt(4, wom);
			ps.setInt(5, dow);
			ps.execute();
			ps.close();
		} catch(Exception error) {
			error.printStackTrace();
		}
	}
}
