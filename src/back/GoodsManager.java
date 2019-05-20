package back;

import java.sql.ResultSet;
import java.sql.Statement;

import structure.Goods;
import structure.Smartphone;

public class GoodsManager {
	
	private Statement st;
	
	public GoodsManager(Statement st) {
		
		this.st = st;
	}
	
	/*전체 상품 목록을 받아온다.*/
	public Goods [] get_all_goods() {
		
		Goods [] goods_list = null;
		int index = 0;		
		String sql = "select * from GOODS as g left join SMARTPHONE as s on g.name = s.name";
		
		ResultSet rs;
		
		try {
		
			rs = st.executeQuery(sql);
			rs.last(); goods_list = new Goods[rs.getRow()]; rs.first();
		
			do {
				
				if (rs.getBoolean("is_smartphone")) {
					goods_list[index] = new Smartphone(rs.getString("name"), rs.getInt("price"), rs.getBoolean("is_smartphone"), rs.getString("explanation"));
					((Smartphone)goods_list[index]).set_manufacturing_compay(rs.getString("company"));
					((Smartphone)goods_list[index]).set_release_date(rs.getString("release_date"));
					((Smartphone)goods_list[index]).set_spec(rs.getString("cpu"), rs.getString("display"), rs.getString("battery_size"), rs.getString("RAM"), rs.getString("ROM"));
				} else {
					goods_list[index] = new Goods(rs.getString("name"), rs.getInt("price"), rs.getBoolean("is_smartphone"), rs.getString("explanation"));
				}
				index++;
				
			} while(rs.next());

			rs.close();
			
		} catch (Exception e) {System.out.println("하아");}
		
		return goods_list;
	}

	/*상품 추가*/
	
	/*상품 삭제*/
}
