package back;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import structure.Order;
import structure.ShoppingKart;

public class OrderManager {

	private Statement st;
	
	public OrderManager(Statement st) {
		
		this.st = st;
	}
	
	/*주문 목록을 받아온다.*/
	public Order [] GetOrderList(String ID) {
		
		String sql = "SELECT * FROM order_info WHERE id='" + ID + "'";
		Order [] order = null;
		int index = 0;
		
		try {			
			ResultSet rs = st.executeQuery(sql);
			rs.last(); order = new Order[rs.getRow()]; rs.first();
			
			do {
				order[index] = new Order(rs.getString("name"), rs.getString("address"), rs.getString("phone_number"), rs.getString("card_corporation"), rs.getString("card_number"), rs.getInt("price"));
				order[index].set_order_num(rs.getInt("order_number"));
				index++;
			} while (rs.next());
			
		} catch (SQLException e) {}
		
		return order;
	}
	
	/*주문 번호에 해당하는 삼품 정보를 받아온다.*/
	public ShoppingKart [] GetOrderGoods(String ID, int order_num) {
		
		String sql = "SELECT * FROM order_list WHERE order_number=" + order_num;
		ShoppingKart [] kart = null;
		int index = 0;
		
		try {
			ResultSet rs = st.executeQuery(sql);
			rs.last(); kart = new ShoppingKart[rs.getRow()]; rs.first();
			
			do {
				kart[index] = new ShoppingKart(rs.getString("name"), rs.getInt("count"), rs.getInt("price"));
				index++;
			} while (rs.next());
			
		} catch (Exception e) { }
		
		return kart;
	}
	
	/*주문 목록 삭제*/
	public void DeleteOrderRecord(String name) {
		String sql = "SELECT order_number FROM order_info WHERE name='" + name + "'";
		int order_num ;
		ResultSet rs;
		try {
			rs = st.executeQuery(sql);
			rs.last();
			order_num = rs.getInt("order_number");
			
			sql = "DELETE FROM order_info WHERE order_number=" + order_num;
			st.execute(sql);
			
			sql = "DELETE FROM order_list WHERE order_number=" + order_num;
			st.execute(sql);
		} catch (Exception e) {}
	}
}
