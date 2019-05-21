package back;

import structure.Goods;
import structure.Order;
import structure.ShoppingKart;
import structure.User;

public class Set {

	private String logined_id = "admin";
	private static final DatabaseManagement DBMS = new DatabaseManagement();
	
	/*아이디 중복 확인*/
	public boolean CompareID(String ID) {
		return DBMS.getAccountManager().CheckID(ID);
	}
	
	/*회원 가입*/
	public boolean RegisterAccount(String ID, String PW, String name, String birth_date) {
		return DBMS.getAccountManager().AddNewAccount(new User(ID, PW, name, birth_date));
	}
	
	/*로그인*/
	public boolean Login(String ID, String PW) {
		boolean check = DBMS.getAccountManager().Login(ID, PW);
		if (check) logined_id = ID;
		return check;
	}

	/*상품 목록 로딩*/
	public Goods [] GetGoodsList() {
		return DBMS.getGoodsManager().get_all_goods();		
	}
	
	/*장바구니 추가*/
	public boolean AddKart(Goods goods) {
		return DBMS.getShoppingKartManager().AddGoods(logined_id, goods.get_name(), goods.get_price());
	}
	
	/*장바구니 삭제*/
	public boolean DeleteKart(String name) {
		return DBMS.getShoppingKartManager().DeleteGoods(logined_id, name);
	}
	
	/*장바구니 목록 로딩*/
	public ShoppingKart [] get_kart_list() {
		return DBMS.getShoppingKartManager().GetKartList(logined_id);
	}
	
	/*장바구니 상품 개수 변경*/
	public boolean SetKartListNum(String name, int num) {
		return DBMS.getShoppingKartManager().setKartNum(logined_id, name, num);
	}
	
	/*구매*/
	public boolean Purchase(Order order) {
		
		/*장바구니 데이터를 기반으로 구매 진행*/
		DBMS.getShoppingKartManager().Purchase(logined_id, order);
		EmptyKart();
		return true;
	}
	
	/*장바구니 비우기*/
	public boolean EmptyKart() {
		return DBMS.getShoppingKartManager().EmptyKart(logined_id);
	}
}
