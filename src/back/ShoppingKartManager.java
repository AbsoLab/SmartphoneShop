package back;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import structure.Order;
import structure.ShoppingKart;

public class ShoppingKartManager {

	private Statement st;

	public ShoppingKartManager(Statement st) {
		
		this.st = st;
	}
	
	/*��ٱ��� ��ǰ ���*/
	public ShoppingKart [] GetKartList(String ID) {
		
		String sql = "select * from SHOPPING_KART where id=\"" + ID  + "\"";
		ShoppingKart [] kart_list = null;
		ResultSet rs;
		int index = 0;
		
		try {
			rs = st.executeQuery(sql);
			rs.last();
			kart_list = new ShoppingKart[rs.getRow()];
			rs.first();
			
			do {
				kart_list[index++] = new ShoppingKart(rs.getString("name"), rs.getInt("count"), rs.getInt("price"));
			} while (rs.next());
		} catch (SQLException e) {}
		
		return kart_list;
	}
	
	/*��ٱ��Ͽ� ��ǰ�� �߰��Ѵ�.*/
	public boolean AddGoods(String ID, String name, int price) {
		
		String sql = "insert into SHOPPING_KART values(\"" + ID + "\", \"" + name + "\", 1, " + price + ")";
		boolean result = false;
		try {
			result = st.execute(sql);
		} catch (Exception e) {}
		
		return result;
	}
	
	/*��ٱ��Ͽ� ��ǰ�� �����Ѵ�.*/
	public boolean DeleteGoods(String ID, String name) {
		
		String sql = "delete from SHOPPING_KART where id=\"" + ID + "\" and name=\"" + name + "\"";
		boolean result = false;
		try {
			result = st.execute(sql);
		} catch (Exception e) {}
		
		return result;
	}
	
	/*��ǰ�� ������ �ٲ۴�.*/
	public boolean setKartNum(String ID, String name, int num) {
		
		String sql = "update SHOPPING_KART set count=" + num + " where id=\"" + ID + "\" and name=\"" + name + "\"";
		boolean result = false;
		try {
			result = st.execute(sql);
		} catch (Exception e) {}
		
		return result;
	}

	/*��ٱ��� ����*/
	public boolean Purchase(String ID, Order order) {
		
		String sql = "select * from META_DATA";
		ResultSet rs;
		int order_num = 0;
		try {
			rs = st.executeQuery(sql);
			rs.first();
			order_num = rs.getInt("order_number");
			order_num++;
			sql = "update META_DATA set order_number=" + (order_num);
			st.execute(sql);
			sql = "insert into ORDER_INFO values('" + ID + "', '" + order.get_name() + "', '" + order.get_address() + "', '" + order.get_phone_num() + "', '" + order.get_card_corporation() + "', '" + order.get_card_num() + "', " + order.get_total_price() + ", " + order_num + ")";
			st.execute(sql);
			ShoppingKart [] kart = GetKartList(ID);
			
			for (int i=0; i<kart.length; ++i) {
				sql = "insert into ORDER_LIST values(" + order_num + ", '" + kart[i].get_name() + "', " + kart[i].get_count() + ", " + kart[i].get_price() + ")";
				st.execute(sql);
			}
		} catch (SQLException e) {System.out.println("����~ : " + sql); return false;}
		
		return true;		
	}
	
	/*��ٱ��� ����*/
	public boolean EmptyKart(String ID) {
		
		String sql = "delete from SHOPPING_KART where id=\"" + ID + "\"";
		boolean result = false;
		try {
			result = st.execute(sql);
		} catch (SQLException e) {}
		return result;
	}

}