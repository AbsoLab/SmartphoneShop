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
	private static GoodsManager goods_manager;
	private static ShoppingKartManager shopping_kart_manager; 
	private static OrderManager order_manager;
	
	public DatabaseManagement() {
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
		
			account_manager = new AccountManager(statement);
			goods_manager = new GoodsManager(statement);
			shopping_kart_manager = new ShoppingKartManager(statement);
			order_manager = new OrderManager(statement);
			
		} catch(Exception e) {
			System.out.println("½ÇÆÐ~");
		}
	}
	
	public AccountManager getAccountManager() { return account_manager; }
	public GoodsManager getGoodsManager() { return goods_manager; }
	public ShoppingKartManager getShoppingKartManager() { return shopping_kart_manager; }
	public OrderManager getOrderManager() { return order_manager; }
	
	/*
	statement.close();
	connection.close();
	*/
}
