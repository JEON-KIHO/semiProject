package database;

import account.AccountDAO;
import account.AccountVO;

public class DBT {
	
	public static void main(String[] args) {
		AccountDAO adao = new AccountDAO();
		System.out.println(adao.login("db4784"));
	}
}
