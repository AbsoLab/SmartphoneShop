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
	
	/*��ü ��ǰ ����� �޾ƿ´�.*/
	public Goods [] get_all_goods() {
		
		Goods [] goods_list = null;
		int index = 0;		
		String sql = "select * from GOODS as g left join SMARTPHONE as s on g.name = s.name";
		
		ResultSet rs;
		
		try {
		
			rs = st.executeQuery(sql);
			rs.last(); goods_list = new Goods[rs.getRow()]; rs.first();
		
			do {
				
				if (rs.getInt("category") == 1) {
					goods_list[index] = new Smartphone(rs.getString("name"), rs.getInt("price"), rs.getInt("category"), rs.getString("explanation"));
					((Smartphone)goods_list[index]).set_manufacturing_compay(rs.getString("company"));
					((Smartphone)goods_list[index]).set_release_date(rs.getString("release_date"));
					((Smartphone)goods_list[index]).set_spec(rs.getString("cpu"), rs.getString("display"), rs.getString("battery_size"), rs.getString("RAM"), rs.getString("ROM"));
				} else {
					goods_list[index] = new Goods(rs.getString("name"), rs.getInt("price"), rs.getInt("category"), rs.getString("explanation"));
				}
				index++;
				
			} while(rs.next());

			rs.close();
			
		} catch (Exception e) {System.out.println("�Ͼ�");}
		
		return goods_list;
	}

	/*����Ʈ���� �޾ƿ´�.*/
	public Smartphone [] get_smartphone() {
		
		Smartphone [] smartphone_list = null;
		
		int index = 0;		
		String sql = " select * from GOODS natural join SMARTPHONE";
		
		ResultSet rs;
		
		try {
		
			rs = st.executeQuery(sql);
			rs.last(); smartphone_list = new Smartphone[rs.getRow()]; rs.first();
		
			do {
				smartphone_list[index] = new Smartphone(rs.getString("name"), rs.getInt("price"), rs.getInt("category"), rs.getString("explanation"));
				smartphone_list[index].set_manufacturing_compay(rs.getString("company"));
				smartphone_list[index].set_release_date(rs.getString("release_date"));
				smartphone_list[index].set_spec(rs.getString("cpu"), rs.getString("display"), rs.getString("battery_size"), rs.getString("RAM"), rs.getString("ROM"));

				index++;
				
			} while(rs.next());

			rs.close();
			
		} catch (Exception e) {System.out.println("�Ͼ�");}
		
		return smartphone_list;
	}
	
	/*�Ǽ��縮�� �޾ƿ´�.*/
	public Goods [] get_accessory() {
		Goods [] goods_list = null;
		int index = 0;		
		String sql = "select * from GOODS where category=2";
		
		ResultSet rs;
		
		try {
		
			rs = st.executeQuery(sql);
			rs.last(); goods_list = new Goods[rs.getRow()]; rs.first();
		
			do {
				goods_list[index] = new Goods(rs.getString("name"), rs.getInt("price"), rs.getInt("category"), rs.getString("explanation"));
				index++;
				
			} while(rs.next());

			rs.close();
			
		} catch (Exception e) {System.out.println("�Ͼ�");}
		
		return goods_list;
	}
	
	/*��Ÿ�� �޾ƿ´�.*/
	public Goods [] get_etc() {
		Goods [] goods_list = null;
		int index = 0;		
		String sql = "select * from GOODS where category=3";
		
		ResultSet rs;
		
		try {
		
			rs = st.executeQuery(sql);
			rs.last(); goods_list = new Goods[rs.getRow()]; rs.first();
		
			do {
				goods_list[index] = new Goods(rs.getString("name"), rs.getInt("price"), rs.getInt("category"), rs.getString("explanation"));
				index++;
				
			} while(rs.next());

			rs.close();
			
		} catch (Exception e) {System.out.println("�Ͼ�");}
		
		return goods_list;
	}
	
	/*��ǰ �߰�*/
	
	/*��ǰ ����*/
}
