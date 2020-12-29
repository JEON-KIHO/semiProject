package database;

import courses.CoursesDAO;

public class test {

	public static void main(String[] args) {
		CoursesDAO dao = new CoursesDAO();
		System.out.println(dao.clist());
		
	}
}
