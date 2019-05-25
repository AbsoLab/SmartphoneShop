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
	
	/*장바구니 상품 목록*/
	public ShoppingKart [] GetKartList(String ID) {
		
		String sql = "SELECT * FROM shopping_kart WHERE id='" + ID  + "'";
		ShoppingKart [] kart_list = null;
		int index = 0;
		
		try {
			ResultSet rs = st.executeQuery(sql);
			rs.last(); kart_list = new ShoppingKart[rs.getRow()]; rs.first();
			
			do {
				kart_list[index++] = new ShoppingKart(rs.getString("name"), rs.getInt("count"), rs.getInt("price"));
			} while (rs.next());
		} catch (SQLException e) {}
		
		return kart_list;
	}
	
	/*장바구니에 상품을 추가한다.*/
	public boolean AddGoods(String ID, String name, int price) {
		
		boolean result = false;
		String sql = "INSERT INTO shopping_kart VALUES('" + ID + "', '" + name + "', 1, " + price + ")";
		
		try {
			result = st.execute(sql);
		} catch (Exception e) {}
		
		return result;
	}
	
	/*장바구니에 삼품을 제거한다.*/
	public boolean DeleteGoods(String ID, String name) {
		
		boolean result = false;
		String sql = "DELETE FROM shopping_kart WHERE id='" + ID + "' AND name='" + name + "'";
		
		try {
			result = st.execute(sql);
		} catch (Exception e) {}
		
		return result;
	}
	
	/*상품의 개수를 바꾼다.*/
	public boolean SetKartNum(String ID, String name, int num) {
		
		boolean result = false;
		String sql = "UPDATE shopping_kart SET count=" + num + " WHERE id='" + ID + "' AND name='" + name + "'";
		
		try {
			result = st.execute(sql);
		} catch (Exception e) {}
		
		return result;
	}

	/*장바구니 구매*/
	public boolean Purchase(String ID, Order order) {
		
		String sql;
		ResultSet rs;
		int order_num = 0;
		
		try {
			/*주문 번호를 얻어온다.*/
			sql = "SELECT * FROM meta_data";
			rs = st.executeQuery(sql);	
			rs.first();
			order_num = rs.getInt("order_number");
			order_num++;
			
			/*주문 번호 갱신*/
			sql = "UPDATA meta_data SET order_number=" + (order_num);
			st.execute(sql);
			
			sql = "INSERT INTO order_info VALUES('" + ID + "', '" + order.get_name() + "', '" + order.get_address() + "', '" + order.get_phone_num() + "', '" + order.get_card_corporation() + "', '" + order.get_card_num() + "', " + order.get_total_price() + ", " + order_num + ")";
			st.execute(sql);
			ShoppingKart [] kart = GetKartList(ID);
			
			for (int i=0; i<kart.length; ++i) {
				sql = "INSERT INTO order_list VALUES(" + order_num + ", '" + kart[i].get_name() + "', " + kart[i].get_count() + ", " + kart[i].get_price() + ")";
				st.execute(sql);
			}
		} catch (SQLException e) {return false;}
		
		return true;		
	}
	
	/*장바구니 비우기*/
	public boolean EmptyKart(String ID) {
		
		boolean result = false;
		String sql = "DELETE FROM shopping_kart WHERE id='" + ID + "'";
		try {
			result = st.execute(sql);
		} catch (SQLException e) {}
		return result;
	}

}