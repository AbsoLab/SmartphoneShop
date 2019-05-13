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


}
