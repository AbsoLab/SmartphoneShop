package back;

import structure.Goods;
import structure.Smartphone;
import structure.User;

public class Set {

	private int login_account_pid = -1;
	private static final DatabaseManagement DBMS = new DatabaseManagement();
	
	/*���̵� �ߺ� Ȯ��*/
	public boolean CompareID(String ID) {
		return DBMS.getAccountManager().CheckID(ID);
	}
	
	/*ȸ�� ����*/
	public boolean RegisterAccount(String ID, String PW, String name, String birth_date) {
		return DBMS.getAccountManager().AddNewAccount(new User(ID, PW, name, birth_date, -1));
	}
	
	/*�α���*/
	public boolean Login(String ID, String PW) {
		
		login_account_pid = DBMS.getAccountManager().Login(ID, PW);
		
		if (login_account_pid == -1) {
			return false;
		} else {
			return true;
		}
	}

	/*��ǰ ��� �ε�*/
	public Goods [] GetGoodsList() {
		
		/*����Ÿ���̽��� ��ǰ����� �޾ƿ´�.*/
		Goods [] temp_goods = new Goods[5];
		temp_goods[0] = new Smartphone("Galaxy S8", 770000, 150, true, 108, "������");
		((Smartphone)temp_goods[0]).set_manufacturing_compay("Samsung");
		((Smartphone)temp_goods[0]).set_spec("Snap Dragon 765", "OLED+", "6600", "4", "64");
		((Smartphone)temp_goods[0]).set_release_date("170502");
		temp_goods[1] = new Smartphone("Galaxy S9", 950000, 250, true, 109, "������");
		((Smartphone)temp_goods[1]).set_manufacturing_compay("Samsung");
		((Smartphone)temp_goods[1]).set_spec("Snap Dragon 765", "OLED+", "6600", "4", "64");
		((Smartphone)temp_goods[1]).set_release_date("180502");
		temp_goods[2] = new Smartphone("Galaxy S10", 1100000, 170, true, 110, "������");
		((Smartphone)temp_goods[2]).set_manufacturing_compay("Samsung");
		((Smartphone)temp_goods[2]).set_spec("Snap Dragon 765", "OLED+", "6600", "4", "64");
		((Smartphone)temp_goods[2]).set_release_date("190502");
		temp_goods[3] = new Goods("Galaxy Note 8", 8500000, 50, false, 208, "��Ʈ��");
		temp_goods[4] = new Goods("Galaxy Note 9", 1200000, 130, false, 209, "��Ʈ��");
		
		return temp_goods;
	}
	
	/*��ٱ��� �߰�*/
	public boolean AddKart(int goods_id, int goods_num) {
		
		/*��ٱ��� ���̺� ����Ÿ �߰�*/
		
		return true;
	}
	
	/*��ٱ��� ����*/
	public boolean DeleteKart(int goods_id, int goods_num) {
		
		/*��ٱ��� ���̺��� ������ ����*/
		
		return true;
	}
	
	/*��ٱ��� ��� �ε�*/
	public Goods [] get_kart_list() {
		return null;
	}
	
	/*����*/
	public boolean Purchase(/*���� ����*/) {
		
		/*��ٱ��� �����͸� ������� ���� ����*/
		return true;
	}
}
