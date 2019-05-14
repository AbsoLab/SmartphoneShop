package back;

import structure.*;

public class Set {

	private int login_account_pid = -1;
	private static final DatabaseManagement DBMS = new DatabaseManagement();
	
	/*아이디 중복 확인*/
	public boolean CompareID(String ID) {
		return DBMS.getAccountManager().CheckID(ID);
	}
	
	/*회원 가입*/
	public boolean RegisterAccount(String ID, String PW, String name, String birth_date) {
		return DBMS.getAccountManager().AddNewAccount(new User(ID, PW, name, birth_date, -1));
	}
	
	/*로그인*/
	public boolean Login(String ID, String PW) {
		
		login_account_pid = DBMS.getAccountManager().Login(ID, PW);
		
		if (login_account_pid == -1) {
			return false;
		} else {
			return true;
		}
	}

	/*상품 목록 로딩*/
	public Goods [] GetGoodsList() {
		
		/*데이타베이스의 상품목록을 받아온다.*/
		Goods [] temp_goods = new Goods[5];
		temp_goods[0] = new Goods("Galaxy S8", 770000, 150, true, 108, "에스팔");
		temp_goods[1] = new Goods("Galaxy S9", 950000, 250, true, 109, "에스구");
		temp_goods[2] = new Goods("Galaxy S10", 1100000, 170, true, 110, "에스십");
		temp_goods[3] = new Goods("Galaxy Note 8", 8500000, 50, true, 208, "노트팔");
		temp_goods[4] = new Goods("Galaxy Note 9", 1200000, 130, true, 209, "노트구");
		
		return temp_goods;
	}
	
	/*장바구니 추가*/
	public boolean AddKart(int goods_id, int goods_num) {
		
		/*장바구니 테이블에 데이타 추가*/
		
		return true;
	}
	
	/*장바구니 삭제*/
	public boolean DeleteKart(int goods_id, int goods_num) {
		
		/*장바구니 테이블의 데이터 삭제*/
		
		return true;
	}
	
	/*구매*/
	public boolean Purchase(/*구매 정보*/) {
		
		/*장바구니 데이터를 기반으로 구매 진행*/
		return true;
	}
}
