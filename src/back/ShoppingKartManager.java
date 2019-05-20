package back;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import structure.Goods;
import structure.ShoppingKart;

public class ShoppingKartManager {

	private Statement st;

	public ShoppingKartManager(Statement st) {
		
		this.st = st;
	}
	
	/*장바구니 상품 목록*/
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
	
	/*장바구니에 상품을 추가한다.*/
	public boolean AddGoods(String ID, String name, int price) {
		
		String sql = "insert into SHOPPING_KART values(\"" + ID + "\", \"" + name + "\", 1, " + price + ")";
		boolean result = false;
		try {
			result = st.execute(sql);
		} catch (Exception e) {}
		
		return result;
	}
	
	/*장바구니에 삼품을 제거한다.*/
	public boolean DeleteGoods(String ID, String name) {
		
		String sql = "delete from SHOPPING_KART where id=\"" + ID + "\" and name=\"" + name + "\"";
		boolean result = false;
		try {
			result = st.execute(sql);
		} catch (Exception e) {}
		
		return result;
	}
	
	/*상품의 개수를 바꾼다.*/
	public boolean setKartNum(String ID, String name, int num) {
		
		String sql = "update SHOPPING_KART set count=" + num + " where id=\"" + ID + "\" and name=\"" + name + "\"";
		boolean result = false;
		try {
			result = st.execute(sql);
		} catch (Exception e) {}
		
		return result;
	}

	/*장바구니 구매*/
	public boolean Purchase(String ID) {
		
		return true;
	}
	
	/*장바구니 비우기*/
	public boolean EmptyKart(String ID) {
		
		String sql = "delete from SHOPPING_KART where id=\"" + ID + "\"";
		boolean result = false;
		try {
			result = st.execute(sql);
		} catch (SQLException e) {}
		return result;
	}

}