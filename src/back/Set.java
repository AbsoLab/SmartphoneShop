package back;

import structure.Goods;
import structure.Order;
import structure.ShoppingKart;
import structure.User;

public class Set {

	private String logined_id = null;
	private static final DatabaseManagement DBMS = new DatabaseManagement();
	
	/*���̵� �ߺ� Ȯ��*/
	public boolean CompareID(String ID) {
		return DBMS.getAccountManager().CheckID(ID);
	}
	
	/*ȸ�� ����*/
	public boolean RegisterAccount(String ID, String PW, String name, String birth_date) {
		return DBMS.getAccountManager().AddNewAccount(new User(ID, PW, name, birth_date));
	}
	
	/*�α���*/
	public boolean Login(String ID, String PW) {
		boolean check = DBMS.getAccountManager().Login(ID, PW);
		if (check) logined_id = ID;
		return check;
	}

	/*��ǰ ��� �ε�*/
	public Goods [] GetGoodsList() {
		return DBMS.getGoodsManager().get_all_goods();		
	}
	
	/*��ٱ��� �߰�*/
	public boolean AddKart(Goods goods) {
		return DBMS.getShoppingKartManager().AddGoods(logined_id, goods.get_name(), goods.get_price());
	}
	
	/*��ٱ��� ����*/
	public boolean DeleteKart(String name) {
		return DBMS.getShoppingKartManager().DeleteGoods(logined_id, name);
	}
	
	/*��ٱ��� ��� �ε�*/
	public ShoppingKart [] get_kart_list() {
		return DBMS.getShoppingKartManager().GetKartList(logined_id);
	}
	
	/*��ٱ��� ��ǰ ���� ����*/
	public boolean SetKartListNum(String name, int num) {
		return DBMS.getShoppingKartManager().setKartNum(logined_id, name, num);
	}
	
	/*����*/
	public boolean Purchase(Order order) {
		DBMS.getShoppingKartManager().Purchase(logined_id, order);
		EmptyKart();
		return true;
	}
	
	/*��ٱ��� ����*/
	public boolean EmptyKart() {
		return DBMS.getShoppingKartManager().EmptyKart(logined_id);
	}
	
	/*�ֹ� ��� �ҷ�����*/
	public Order [] GetOrderList() {
		return DBMS.getOrderManager().get_order_list(logined_id);
	}
	
	/*�ֹ� ��ȣ�� �ش��ϴ� ��ǰ��� �ҷ�����*/
	public ShoppingKart [] GetOrderKartList(int order_num) {
		return DBMS.getOrderManager().get_order_kart_list(logined_id, order_num);
	}
}
