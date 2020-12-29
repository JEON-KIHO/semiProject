package product;

public class test {
	
	public static void main(String[] args) {
		ProductDAO dao = new ProductDAO();
		System.out.println(dao.list("", "desc", 1, 5));
	}
}
