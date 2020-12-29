package address;

public class DAOtest0 {

	public static void main(String[] args) {
		AddressDAO0 dao = new AddressDAO0();
		try {/*
			// 입력
			AddressVO vo = new AddressVO();
			vo.setName("사아자");
			vo.setTel("010-5555-6666");
			vo.setAddress("인천시 동구");
			dao.insert(vo);
			
			// 출력
			dao.list();
			
			// 삭제
			dao.delete(6);
			
			// 출력
			dao.list();
			
			// 업데이트
			vo.setSeq(1);
			vo.setName("차카타");
			vo.setTel("010-7777-8888");
			vo.setAddress("인천시 남동구");
			dao.update(vo);
			*/
			// 출력
			dao.list();
			/*
			// 불러오기
			dao.read(8);
			*/		
		} catch (Exception error) {
			error.printStackTrace();
		}
		
		
	}

}
