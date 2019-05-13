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


}
