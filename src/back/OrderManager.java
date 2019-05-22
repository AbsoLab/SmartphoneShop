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
	
	/*�ֹ� ����� �޾ƿ´�.*/
	public Order [] get_order_list(String ID) {
		
		String sql = "select * from ORDER_INFO where id='" + ID + "'";
		Order [] order = null;
		ResultSet rs;
		int index = 0;
		
		try {			
			rs = st.executeQuery(sql);
			rs.last();
			order = new Order[rs.getRow()];
			rs.first();
			
			do {
				order[index] = new Order(rs.getString("name"), rs.getString("address"), rs.getString("phone_number"), rs.getString("card_corporation"), rs.getString("card_number"), rs.getInt("price"));
				order[index].set_order_num(rs.getInt("order_number"));
				index++;
			} while (rs.next());
			
		} catch (SQLException e) { System.out.println(sql);} catch (Exception e) {System.out.println("�Ͼ�");}
		
		return order;
	}
	
	/*�ֹ� ��ȣ�� �ش��ϴ� */
	public ShoppingKart [] get_order_kart_list(String ID, int order_num) {
		
		String sql = "select * from ORDER_LIST where order_number=" + order_num;
		ResultSet rs;
		
		ShoppingKart [] kart = null;
		
		try {
			rs = st.executeQuery(sql);
			
			rs.last();
			kart = new ShoppingKart[rs.getRow()];
			rs.first();
			int index = 0;
			
			do {
				kart[index] = new ShoppingKart(rs.getString("name"), rs.getInt("count"), rs.getInt("price"));
				index++;
			} while (rs.next());
		} catch (Exception e) {System.out.println(sql);}
		
		return kart;
	}
}
