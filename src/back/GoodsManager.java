package back;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			
		} catch (Exception e) {System.out.println("하아");}
		
		return goods_list;
	}

	/*스마트폰만 받아온다.*/
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
			
		} catch (Exception e) {System.out.println("하아");}
		
		return smartphone_list;
	}
	
	/*악세사리만 받아온다.*/
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
			
		} catch (Exception e) {System.out.println("하아");}
		
		return goods_list;
	}
	
	/*기타만 받아온다.*/
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
			
		} catch (Exception e) {System.out.println("하아");}
		
		return goods_list;
	}
	
	/*상품 추가*/
	public boolean add_new_goods(Goods goods, String img_url) {
		try {
			
			String sql = "insert into goods values('" + goods.get_name() + "', " + goods.get_price() + ", " + goods.get_category() + ", '" + goods.get_explanation() +"')";
			st.execute(sql);
			
			if (goods.get_category() == 1) {
				Smartphone ph = (Smartphone)goods;
				sql = "insert into smartphone values('" + ph.get_name() + "', '" + ph.get_manufacturing_company() + "', '" + ph.get_release_date() + "', '" + ph.get_spec().cpu + "', '" + ph.get_spec().display + "', '" + ph.get_spec().battery_size + "', '" + ph.get_spec().RAM + "', '" + ph.get_spec().RAM + "')";
				st.execute(sql);
			}
			
			/*이미지 저장*/
			File imgfile = new File(img_url);
			FileInputStream fin = new FileInputStream(imgfile);

			PreparedStatement pre = connection.prepareStatement("insert into image_table values ('tmp_name', ?)");
			pre.setBinaryStream(1, fin, (int)imgfile.length());
			pre.executeUpdate();
			pre.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/*이미지 받아오기*/
	public Image get_image(String name) {
		
		String sql = "select IMAGE from IMAGE_TABLE where name='" + name + "'";
		ResultSet rs;
		Image img = null;
		try {
			rs = st.executeQuery(sql);
			if (rs.first()) {
				InputStream is = rs.getBinaryStream("IMAGE");
				img = ImageIO.read(is);				
			}
		} catch (Exception e) {}
		
		return img;			
	}
	
	/*상품 삭제*/
}
