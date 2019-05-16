package back;

import structure.Goods;
import structure.Smartphone;
import structure.User;

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
		temp_goods[0] = new Smartphone("Galaxy S8", 770000, 150, true, 108, "에스팔");
		((Smartphone)temp_goods[0]).set_manufacturing_compay("Samsung");
		((Smartphone)temp_goods[0]).set_spec("Snap Dragon 765", "OLED+", "6600", "4", "64");
		((Smartphone)temp_goods[0]).set_release_date("170502");
		temp_goods[1] = new Smartphone("Galaxy S9", 950000, 250, true, 109, "에스구");
		((Smartphone)temp_goods[1]).set_manufacturing_compay("Samsung");
		((Smartphone)temp_goods[1]).set_spec("Snap Dragon 765", "OLED+", "6600", "4", "64");
		((Smartphone)temp_goods[1]).set_release_date("180502");
		temp_goods[2] = new Smartphone("Galaxy S10", 1100000, 170, true, 110, "에스십");
		((Smartphone)temp_goods[2]).set_manufacturing_compay("Samsung");
		((Smartphone)temp_goods[2]).set_spec("Snap Dragon 765", "OLED+", "6600", "4", "64");
		((Smartphone)temp_goods[2]).set_release_date("190502");
		temp_goods[3] = new Goods("Galaxy Note 8", 8500000, 50, false, 208, "노트팔");
		temp_goods[4] = new Goods("Galaxy Note 9", 1200000, 130, false, 209, "노트구");
		
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
	
	/*장바구니 목록 로딩*/
	public Goods [] get_kart_list() {
		return null;
	}
	
	/*구매*/
	public boolean Purchase(/*구매 정보*/) {
		
		/*장바구니 데이터를 기반으로 구매 진행*/
		return true;
	}
}
