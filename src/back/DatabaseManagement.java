package back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseManagement {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/SmartphoneShop";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "apmsetup";
	
	private Connection connection;
	private Statement statement;
	
	private static AccountManager account_manager; 
	
	public DatabaseManagement() {
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
		
			account_manager = new AccountManager(statement);
			
		} catch(Exception e) {
			System.out.println("½ÇÆÐ~");
		}
	}
	
	public AccountManager getAccountManager() { return account_manager; }
		
		
	/*
	statement.close();
	connection.close();
	*/
}
