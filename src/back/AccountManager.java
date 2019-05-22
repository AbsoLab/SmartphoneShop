package back;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import structure.User;

public class AccountManager {

	private Statement st;
	
	public AccountManager(Statement st) {
		
		this.st = st;
	}
	
	/*�ű� ���� ����*/
	public boolean AddNewAccount(User user) {
		
		String sql;
		sql = "insert into ACCOUNT values(\"" + user.get_ID() + "\", \"" + user.get_PW() + "\", \"" + user.get_name() + "\", " + user.get_birth_date() + ")";
		
		try { st.executeUpdate(sql); } catch (Exception e) { return false; }
		
		return true;
	}
	
	/*�����ϴ� ID���� Ȯ��*/
	public boolean CheckID(String ID) {
		
		String sql;
		sql = "SELECT id FROM ACCOUNT";
		ResultSet rs;
		
		try {
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				if (ID.equals(rs.getString("id"))) {
					return true;
				}
			}
			rs.close();
		} catch (Exception e) { }
		
		return false;
	}
	
	/*ID�� �ش��ϴ� �������� �޾ƿ���*/
	public User GetAccount(String ID) {
		
		String sql = "SELECT * FROM ACCOUNT";
		
		ResultSet rs;
		
		try {
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				if (ID.equals(rs.getString("id"))) {
					return new User(rs.getString("id"), rs.getString("pw"), rs.getString("name"), rs.getInt("birth_date"));
				}
			}
			rs.close();
			
		} catch (Exception e) { }
		
		return null;
	}
	
	/*ID PW�� �Ѵ� �´� �� Ȯ��*/
	public boolean Login(String ID, String PW) {
		
		String sql;
		sql = "SELECT * FROM ACCOUNT";
		ResultSet rs;
		
		try {
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				
				if (ID.equals(rs.getString("id"))) {
					
					if (PW.equals(rs.getString("pw"))) {
						
						return true;
						
					} else {
						
						return false;
					}
				}
			}
			rs.close();
		} catch (Exception e) { }
		
		return false;
		
	}

	/*���� ����*/
	public boolean ChangeAccountInfo(User user) {
		String sql = "update ACCOUNT set pw='" + user.get_PW() +"', name='" + user.get_name() + "', birth_date=" + user.get_birth_date() + " where id='" + user.get_ID() + "'";
		try {
			return st.execute(sql);
		} catch (SQLException e) {
			return false;
		}
	}
}
