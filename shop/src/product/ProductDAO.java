package product;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import database.Database;
import database.sqlVO;

public class ProductDAO {
	// 상품 삭제
	public int delete(String prod_id) {
		int cnt=0;
		try {
			String sql = "select count(*) cnt from orders where prod_id = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, prod_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) cnt=rs.getInt("cnt");
			
			if(cnt==0) {
				sql = "delete from product where prod_id = ?";
				ps = Database.CON.prepareStatement(sql);
				ps.setString(1, prod_id);
				ps.execute();
			}
		} catch(Exception error) {
			error.printStackTrace();
		}
		return cnt;
	}
	
	// 상품 업데이트
	public void update(ProductVO vo) {
		try {
			String sql = "update product set prod_name=?, mall_id=?, company=?, price1=?, price2=?, image=?, detail=?, prod_del=? where prod_id=?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getProd_name());
			ps.setString(2, vo.getMall_id());
			ps.setString(3, vo.getCompany());
			ps.setInt(4, vo.getPrice1());
			ps.setInt(5, vo.getPrice2());
			ps.setString(6, vo.getImage());
			ps.setString(7, vo.getDetail());
			ps.setString(8, vo.getProd_del());
			ps.setString(9, vo.getProd_id());
			ps.execute();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	// 상품 읽기
	public ProductVO read(String prod_id) {
		ProductVO vo = new ProductVO();
		try {
			String sql = "select p.*, m.mall_name mall_name from product p left join mall m on p.mall_id=m.mall_id where prod_id = ?";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, prod_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				vo.setProd_id(rs.getString("prod_id"));
				vo.setProd_name(rs.getString("prod_name"));
				vo.setCompany(rs.getString("company"));
				vo.setPrice1(rs.getInt("price1"));
				vo.setPrice2(rs.getInt("price2"));
				vo.setDetail(rs.getString("detail"));
				vo.setImage(rs.getString("image"));
				vo.setProd_del(rs.getString("prod_del"));
				vo.setMall_id(rs.getString("mall_id"));
				vo.setMall_name(rs.getString("mall_name"));
			}
		} catch(Exception error) {
			error.printStackTrace();
		}
		return vo;
	}
	
	// 상품 코드
	public String getCode() {
		String code = "";
		try {
			String sql = "select concat('P', SUBSTR(max(prod_id),2,3)+1) prod_id from product";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) code = rs.getString("prod_id");
		} catch(Exception error) {
			error.printStackTrace();
		}
		return code;
	}
	
	//목록 입력
	public void insert(ProductVO vo) {
		try {
			String sql = "insert into product(prod_id, prod_name, mall_id, company, price1, price2, image, detail, prod_del) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = Database.CON.prepareStatement(sql);
			ps.setString(1, vo.getProd_id());
			ps.setString(2, vo.getProd_name());
			ps.setString(3, vo.getMall_id());
			ps.setString(4, vo.getCompany());
			ps.setInt(5, vo.getPrice1());
			ps.setInt(6, vo.getPrice2());
			ps.setString(7, vo.getImage());
			ps.setString(8, vo.getDetail());
			ps.setString(9, vo.getProd_del());
			ps.execute();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	// 목록 출력
		public JSONObject list(sqlVO vo) {
			JSONObject jobj = new JSONObject();
			try {
				String sql = "{call list(?, ?, ?, ?, ?, ?, ?, ?)}";
				CallableStatement cs = Database.CON.prepareCall(sql);
				cs.setString(1, "pm");
				cs.setString(2, vo.getKey());
				cs.setString(3, vo.getWord());
				cs.setString(4, vo.getCol());
				cs.setInt(5, vo.getPage());
				cs.setInt(6, vo.getPerPage());
				cs.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
				cs.registerOutParameter(8, oracle.jdbc.OracleTypes.INTEGER);
				cs.execute();
				ResultSet rs = (ResultSet)cs.getObject(7);
				DecimalFormat df = new DecimalFormat("#,###");
				JSONArray jArray = new JSONArray();
				while(rs.next()) {
					JSONObject obj = new JSONObject();
					obj.put("prod_id", rs.getString("prod_id"));
					obj.put("prod_name", rs.getString("prod_name"));
					obj.put("mall_id", rs.getString("mall_id"));
					obj.put("company", rs.getString("company"));
					String price1 = df.format(rs.getInt("price1"));
					obj.put("price1", price1);
					obj.put("price2", rs.getInt("price2"));
					obj.put("detail", rs.getString("detail"));
					obj.put("mall_name", rs.getString("mall_name"));
					obj.put("image", rs.getString("image"));
					obj.put("prod_del", rs.getString("prod_del"));
					jArray.add(obj);
				}
			
				int lastPage = (cs.getInt(8)%vo.getPerPage())==0?(cs.getInt(8)/vo.getPerPage()):(cs.getInt(8)/vo.getPerPage())+1;
				jobj.put("cnt", cs.getInt(8));
				jobj.put("list", jArray);
				jobj.put("lastPage", lastPage);
			} catch(Exception error) {
				error.printStackTrace();
			}
			return jobj;
		}
}
