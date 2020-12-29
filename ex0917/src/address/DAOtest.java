package address;

public class DAOtest {

	public static void main(String[] args) {
		AddressDAO dao = new AddressDAO();
		QueryVO v = new QueryVO();
		v.setTable("tbl_address");
		v.setKey("address");
		v.setWord("인천");
		v.setPage(2);
		v.setPerPage(10);
		dao.list(v);
		dao.count(v);
	}

}
