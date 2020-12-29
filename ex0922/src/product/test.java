package product;

public class test {

	public static void main(String[] args) {
		ProductDAO dao = new ProductDAO();
		
		System.out.println(dao.count("가구", "asc"));
		System.out.println(dao.list(1, "가구", "asc"));
		

	}

}
