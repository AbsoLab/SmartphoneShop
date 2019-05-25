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
		sql = "INSERT INTO account VALUES('" + user.get_ID() + "', '" + user.get_PW() + "', '" + user.get_name() + "', " + user.get_birth_date() + ")";	
		try { st.executeUpdate(sql); } catch (Exception e) { return false; }
		
		return true;
	}
	
	/*�����ϴ� ID���� Ȯ��*/
	public boolean CheckID(String ID) {
		boolean result = true;
		String sql = "SELECT id FROM ACCOUNT WHERE id='" + ID + "'";
		try {
			ResultSet rs = st.executeQuery(sql);
			rs.last();
			if (rs.getRow() == 0) {
				result = false;
			}
			rs.close();
		} catch (Exception e) { result = false; }
		
		return result;
	}
	
	/*ID�� �ش��ϴ� �������� �޾ƿ���*/
	public User GetAccount(String ID) {
		
		User user = null;
		String sql = "SELECT * FROM account WHERE id='" + ID + "'";
		ResultSet rs;
		
		try {
			rs = st.executeQuery(sql);
			rs.last();
			user = new User(rs.getString("id"), rs.getString("pw"), rs.getString("name"), rs.getInt("birth_date"));
			rs.close();
			
		} catch (Exception e) {}
		
		return user;
	}
	
	/*ID PW�� �Ѵ� �´� �� Ȯ��*/
	public boolean Login(String ID, String PW) {
		
		boolean result = false;
		String sql = "SELECT * FROM account WHERE id='" + ID + "' AND pw='" + PW + "'";
		ResultSet rs;
		
		try {
			rs = st.executeQuery(sql);
			rs.last();
			if (rs.getRow() == 1) {
				result = true;
			}
			rs.close();
		} catch (Exception e) { }
		
		return result;
		
	}

	/*���� ����*/
	public boolean ChangeAccountInfo(User user) {
		String sql = "UPDATE account SET pw='" + user.get_PW() +"', name='" + user.get_name() + "', birth_date=" + user.get_birth_date() + " where id='" + user.get_ID() + "'";
		try {
			return st.execute(sql);
		} catch (SQLException e) {
			return false;
		}
	}
}
