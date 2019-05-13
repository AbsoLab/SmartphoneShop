package back;

import java.sql.ResultSet;
import java.sql.Statement;

import structure.User;

public class AccountManager {

	private Statement st;
	
	public AccountManager(Statement st) {
		
		this.st = st;
	}
	
	public boolean AddNewAccount(User user) {
		
		String sql;
		sql = "insert into ACCOUNT values(\"" + user.get_ID() + "\", \"" + user.get_PW() + "\", \"" + user.get_name() + "\", " + user.get_birth_date() + ", " + 3 + ")";

		try {
			st.executeUpdate(sql);

		} catch (Exception e) { return false; }
		
		return true;
	}
	
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
	
	public User GetAccount(String ID) {
		
		String sql;
		sql = "SELECT * FROM ACCOUNT";
		
		ResultSet rs;
		try {
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				if (ID.equals(rs.getString("id"))) {
					return new User(rs.getString("id"), rs.getString("pw"), rs.getString("name"), rs.getInt("birth_date"), rs.getInt("pid"));
				}
			}
			rs.close();
		} catch (Exception e) { }
		
		return null;
	}
	
	public int Login(String ID, String PW) {
		
		String sql;
		sql = "SELECT * FROM ACCOUNT";
		ResultSet rs;
		
		try {
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				
				if (ID.equals(rs.getString("id"))) {
					
					if (PW.equals(rs.getString("pw"))) {
						
						return rs.getInt("pid");
						
					} else {
						
						return -1;
					}
				}
			}
			
			rs.close();
		} catch (Exception e) { }
		
		return -1;
		
	}
}
