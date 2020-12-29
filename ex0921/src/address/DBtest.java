package address;

import board.BoardDAO;

public class DBtest {
	public static void main(String[] args) {
		
		AddressDAO dao = new AddressDAO();
		/*
		dao.list(3);
		dao.count();
		
		AddressVO vo = new AddressVO();
		vo.setName("텀블러");
		vo.setTel("010-0123-4567");
		vo.setAddress("인천광역시 미추홀구 주안동");
		dao.insert(vo);
		*/
		
		BoardDAO bdao = new BoardDAO();
		bdao.list(1);
		bdao.count();
	}
}
