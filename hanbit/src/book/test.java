package book;

import bbs.BBSDAO;

public class test {

	public static void main(String[] args) {
		BBSDAO dao = new BBSDAO();
		System.out.println(dao.list("java", 1));

	}

}
