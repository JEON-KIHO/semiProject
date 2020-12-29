package address;

public class DAOtest {
	
	public static void main(String[] args) {
		AddressDAO dao = new AddressDAO();
		dao.list("name", "홍", 5, 2);
	}
}
