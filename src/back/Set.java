package back;

import structure.*;

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
		temp_goods[0] = new Goods("Galaxy S8", 770000, 150, true, 108, "������");
		temp_goods[1] = new Goods("Galaxy S9", 950000, 250, true, 109, "������");
		temp_goods[2] = new Goods("Galaxy S10", 1100000, 170, true, 110, "������");
		temp_goods[3] = new Goods("Galaxy Note 8", 8500000, 50, true, 208, "��Ʈ��");
		temp_goods[4] = new Goods("Galaxy Note 9", 1200000, 130, true, 209, "��Ʈ��");
		
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
	
	/*����*/
	public boolean Purchase(/*���� ����*/) {
		
		/*��ٱ��� �����͸� ������� ���� ����*/
		return true;
	}
}
