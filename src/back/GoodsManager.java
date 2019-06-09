package back;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;

import structure.Goods;
import structure.Smartphone;

public class GoodsManager {
	
	private Connection connection;
	private Statement st;
	
	public GoodsManager(Connection connection, Statement st) {
		
		this.connection = connection;
		this.st = st;
	}
	
	/*전체 상품 목록을 받아온다.*/
	public Goods [] GetAllGoods() {
		
		Goods [] goods_list = null;
		int index = 0;
		
		String sql = "SELECT * FROM goods AS g LEFT JOIN smartphone AS s ON g.name = s.name";
		
		try {
			ResultSet rs = st.executeQuery(sql);
			rs.last(); goods_list = new Goods[rs.getRow()]; rs.first();
			do {
				
				if (rs.getInt("category") == 0) {
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
			
		} catch (Exception e) {}
		
		return goods_list;
	}

	/*스마트폰만 받아온다.*/
	public Smartphone [] GetSmartphones() {
		
		Smartphone [] smartphone_list = null;
		int index = 0;		
		
		String sql = "SELECT * FROM goods NATURAL JOIN smartphone";

		try {
			ResultSet rs = st.executeQuery(sql);
			rs.last(); smartphone_list = new Smartphone[rs.getRow()]; rs.first();
		
			do {
				smartphone_list[index] = new Smartphone(rs.getString("name"), rs.getInt("price"), rs.getInt("category"), rs.getString("explanation"));
				smartphone_list[index].set_manufacturing_compay(rs.getString("company"));
				smartphone_list[index].set_release_date(rs.getString("release_date"));
				smartphone_list[index].set_spec(rs.getString("cpu"), rs.getString("display"), rs.getString("battery_size"), rs.getString("RAM"), rs.getString("ROM"));

				index++;		
			} while(rs.next());

			rs.close();
			
		} catch (Exception e) {}
		
		return smartphone_list;
	}
	
	/*악세사리만 받아온다.*/
	public Goods [] GetAccessories() {
		
		Goods [] goods_list = null;
		int index = 0;		
		
		String sql = "SELECT * FROM goods WHERE category=1";
		
		try {
			ResultSet rs = st.executeQuery(sql);
			rs.last(); goods_list = new Goods[rs.getRow()]; rs.first();
		
			do {
				goods_list[index] = new Goods(rs.getString("name"), rs.getInt("price"), rs.getInt("category"), rs.getString("explanation"));
				index++;	
			} while(rs.next());

			rs.close();
			
		} catch (Exception e) {}
		
		return goods_list;
	}
	
	/*기타만 받아온다.*/
	public Goods [] GetEtc() {
		
		Goods [] goods_list = null;
		int index = 0;		
		
		String sql = "SELECT * FROM goods WHERE category=2";
	
		try {
			ResultSet rs = st.executeQuery(sql);
			rs.last(); goods_list = new Goods[rs.getRow()]; rs.first();
		
			do {
				goods_list[index] = new Goods(rs.getString("name"), rs.getInt("price"), rs.getInt("category"), rs.getString("explanation"));
				index++;
			} while(rs.next());

			rs.close();
			
		} catch (Exception e) {}
		
		return goods_list;
	}
	
	/*상품 추가*/
	public boolean add_new_goods(Goods goods, String img_url) {
		try {	
			/*이미지 저장*/
			File imgfile = new File(img_url);
			FileInputStream fin = new FileInputStream(imgfile);
			
			PreparedStatement pre = connection.prepareStatement("INSERT INTO image_table VALUES('" + goods.get_name() + "', ?)");
			pre.setBinaryStream(1, fin, (int)imgfile.length());
			pre.executeUpdate();
			pre.close();

			
			String sql = "INSERT INTO goods VALUES('" + goods.get_name() + "', " + goods.get_price() + ", " + goods.get_category() + ", '" + goods.get_explanation() +"')";
			st.execute(sql);
			
			if (goods.get_category() == 0) {
				Smartphone ph = (Smartphone)goods;
				sql = "INSERT INTO smartphone VALUES('" + ph.get_name() + "', '" + ph.get_manufacturing_company() + "', '" + ph.get_release_date() + "', '" + ph.get_spec().cpu + "', '" + ph.get_spec().display + "', '" + ph.get_spec().battery_size + "', '" + ph.get_spec().RAM + "', '" + ph.get_spec().ROM + "')";
				st.execute(sql);
			}
			

		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	/*상품명 중복 확인*/
	public boolean check_goods_naem(String name) {

		boolean result = true;
		
		String sql = "SELECT * FROM goods WHERE name='" + name + "'";
		try {
			ResultSet rs = st.executeQuery(sql);
			rs.last();
			if (rs.getRow() == 0) {
				result = false;
			}
			rs.close();
		} catch (SQLException e) {result = false;}
		return result;
	}
	
	/*이미지 받아오기*/
	public Image get_image(String name) {
		
		String sql = "SELECT image FROM image_table WHERE name='" + name + "'";
		Image img = null;
		
		try {
			ResultSet rs = st.executeQuery(sql);
			if (rs.first()) {
				InputStream is = rs.getBinaryStream("IMAGE");
				img = ImageIO.read(is);				
			}
		} catch (Exception e) {}
		
		return img;			
	}
	
	/*상품 삭제*/
	public boolean delete_goods(String name, int category) {
		String sql; 
		try {
			sql = "DELETE FROM goods WHERE name='" + name + "'";
			st.execute(sql);
			sql = "DELETE FROM image_table WHERE name='" + name + "'";
			st.execute(sql);
			if (category == 0) {
				sql = "DELETE FROM smartphone WHERE name='" + name + "'";
				st.execute(sql);	
			}
		} catch (Exception e) {
			
			return false;
		}
		return true;
	}
}
