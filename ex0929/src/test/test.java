package test;

import enrollments.EnrollmentsDAO;

public class test {

	public static void main(String[] args) {
		EnrollmentsDAO dao = new EnrollmentsDAO();
		System.out.println(dao.slist("C301"));

	}

}
