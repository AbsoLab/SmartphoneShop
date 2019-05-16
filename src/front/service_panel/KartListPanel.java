package front.service_panel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import back.Set;
import main.MainFrame;
import structure.Goods;

public class KartListPanel extends JPanel{
	
	private JPanel kart_list_panel;
	private PagePanel page_panel;
	
	private MainFrame mf;
	private Set set;
	
	private Goods [] kart_list;
	
	public KartListPanel(MainFrame mf, Set set) {
		
		this.mf = mf;
		this.set = set;
		
		setLayout(null);

		add(new KartPanel(mf, null));
		
		
	}
	
	/*상품 패널*/
	class KartPanel extends JPanel {
		
		private JPanel image_panel;
		private JPanel goods_name_panel;
		private JLabel goods_name_label;
		private JLabel goods_count_label;
		private JLabel total_price_label;
		private JButton [] count_button;
		private JButton delete_button;
		
		private MainFrame mf;
		private Goods goods;
		
		public KartPanel(MainFrame mf, Goods goods) {
			
			this.mf = mf;
			this.goods = goods;
			
			setLayout(null);
			
			/*상품 이미지 패널*/
			
			
			/*상품 이름 패널*/
			
			
			/*상품 이름 출력*/
			
			/*상품 개수 출력*/
			
			/*상품 개수 조정*/
			
			/*상품 가격 출력*/
			
			/*삭제 버튼*/
			
			
			setBackground(Color.WHITE);
			setSize(600, 100);
			setBorder(new LineBorder(Color.BLACK));
		}
		
	}

	/*페이지 패널*/
	class PagePanel extends JPanel {
		
	}
	
	/*장바구니 목록 갱신*/
	private void show_kart_list_page(int current_page_num) {
		
		kart_list = set.get_kart_list();
		
		kart_list_panel.removeAll();
		
		//page_panel.update_page(kart_list.length / 4 + 1);
		//page_panel.select_page(current_page_num);
		
		
		kart_list_panel.revalidate();
		kart_list_panel.repaint();
	}
}
