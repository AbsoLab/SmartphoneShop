package front.service_panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import back.Set;
import main.MainFrame;
import structure.Goods;

/*상품 목록을 띄워주는 패널입니다.*/
public class GoodsListPanel extends JPanel{
	
	private JPanel category_panel;
	private JButton [] category_button;
	private JPanel goods_list_panel;
	private PagePanel page_panel;
	private JButton add_goods_button;
	
	private MainFrame mf;
	private Set set;
	
	private Goods [] goods_list;
	private int select = 0;
	
	public GoodsListPanel(MainFrame mf, Set set) {
		
		this.mf = mf;
		this.set = set;
		
		setLayout(null);
		
		/*상품 카테고리 선택 패널*/
		category_panel = new JPanel();
		category_panel.setLayout(null);
		category_panel.setBounds(0, 0, 150, 670);
		category_panel.setBackground(new Color(199, 199, 199));
		add(category_panel);
		
		/*상품 카테고리 선택 버튼*/
		String [] button_name = {"전체", "스마트폰", "악세사리", "기타"};
		category_button = new JButton[4];
		for (int i=0; i<4; ++i) {
			category_button[i] = new JButton(button_name[i]);
			category_button[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			category_button[i].setBackground(Color.WHITE);
			category_button[i].setBounds(0, 100 + 70*i, 150, 70);
			category_button[i].addActionListener(new CategoryButtonListener());
			category_panel.add(category_button[i]);
		}
		
		/*상품 목록 패널*/
		goods_list_panel = new JPanel();
		goods_list_panel.setLayout(null);
		goods_list_panel.setBounds(250, 100, 910, 370);	// width = 660 height = 270
		goods_list_panel.setBackground(Color.WHITE);
		add(goods_list_panel);
		
		/*페이지 패널*/
		page_panel = new PagePanel();
		page_panel.setBounds(250, 480, 910, 30);
		add(page_panel);

		/*상품 추가 버튼 (관리자만 뜬다)*/
		if (set.is_admin()) {
			add_goods_button = new JButton("상품추가");
			add_goods_button.setBounds(1060, 520, 100, 30);
			add_goods_button.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			add_goods_button.setMargin(new Insets(0, 0, 0, 0));
			add_goods_button.setBackground(Color.WHITE);
			add_goods_button.addActionListener(new CategoryButtonListener());
			add(add_goods_button);
		}
		
		/*초기화*/
		show_goods_list_page(0, 0);
	}
	
	/*상품 패널*/
	private class GoodsPanel extends JPanel {
		
		private Image img;
		private JLabel goods_image_label;
		private JLabel goods_name_label;
		
		private MainFrame mf;
		private Goods goods;
		
		public GoodsPanel(MainFrame mf, Goods goods) {
			
			this.mf = mf;
			this.goods = goods;
			
			setLayout(null);
			
			/*상품 이미지 출력*/
			img = set.GetImage(goods.get_name());
			img = img.getScaledInstance(180, 250, Image.SCALE_SMOOTH);
			
			goods_image_label = new JLabel(new ImageIcon(img));
			goods_image_label.setBounds(10, 10, 180, 250);
			add(goods_image_label);
			
			/*상품 이름 출력*/
			goods_name_label = new JLabel(goods.get_name());
			goods_name_label.setFont(new Font("맑은 고딕", Font.BOLD, 17));
			goods_name_label.setHorizontalAlignment(JLabel.LEFT);
			goods_name_label.setBounds(15, 300, 170, 35);
			add(goods_name_label);
			
			addMouseListener(new MouseActionListener());
			
			setSize(200, 350);
			setBackground(Color.WHITE);
			setBorder(new LineBorder(Color.BLACK));	
		}
		
		/*마우스 액션 이벤리스터*/
		class MouseActionListener extends MouseAdapter {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				new GoodsInfoFrame(mf, set, goods);
				show_goods_list_page(select, 0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				JPanel panel = (JPanel)e.getSource();
				panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				panel.setBorder(new LineBorder(new Color(109, 109, 219), 3));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				JPanel panel = (JPanel)e.getSource();
				panel.setBorder(new LineBorder(Color.BLACK));
			}
			
		}
	}
	
	/*페이지 패널*/
	private class PagePanel extends JPanel {
		
		private JLabel [] page_num_label;

		public PagePanel() {
			
			setBackground(Color.white);
		}
		
		/*페이지 개수 갱신*/
		public void update_page(int total_page_num) {
			
			removeAll();
			
			/*10개가 넘어갈 경우 도 생각해보자*/
			page_num_label = new JLabel[total_page_num];
			
			for (int i=0; i<total_page_num; ++i) {
				page_num_label[i] = new JLabel(Integer.toString(i+1));
				page_num_label[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
				page_num_label[i].addMouseListener(new MouseActionListener());
				add(page_num_label[i]);
			}
			
			revalidate();
			repaint();
		}
		
		public void select_page(int page) {
			page_num_label[page].setForeground(Color.BLUE);
		}
		
		/*페이지 숫자 이벤트 리스너*/
		class MouseActionListener extends MouseAdapter {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JLabel label = (JLabel)e.getSource();
				show_goods_list_page(select, Integer.valueOf(label.getText()) - 1);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				JLabel label = (JLabel)e.getSource();
				label.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
	}
	
	/*카테고리 버튼 이벤트 리스너*/
	private class CategoryButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton btn = (JButton)e.getSource();
			
			switch(btn.getText()) {
			
			case "전체":
				select = 0;
				show_goods_list_page(0, 0);
				break;
			case "스마트폰":
				select = 1;
				show_goods_list_page(1, 0);
				break;
			case "악세사리":
				select = 2;
				show_goods_list_page(2, 0);
				break;
			case "기타":
				select = 3;
				show_goods_list_page(3, 0);
				break;
			case "상품추가":
				new AddGoodsFrame(mf, set);
				show_goods_list_page(select, 0);
				break;
				
			}
		}
	}
	
	/*상품 목록 갱신*/
	private void show_goods_list_page(int select, int current_page_num) {
		
		switch(select) {
		case 0:
			goods_list = set.GetGoodsList();
			break;
		case 1:
			goods_list = set.GetSmartphoneList();
			break;
		case 2:
			goods_list = set.GetAccessoryList();
			break;
		case 3:
			goods_list = set.GetEtcList();
			break;
		}
		

		goods_list_panel.removeAll();
			
		page_panel.update_page( (goods_list.length - 1) / 4 + 1);
		page_panel.select_page(current_page_num);
		
		for (int i=0; i<4; ++i) {
			
			try {
				GoodsPanel temp_panel = new GoodsPanel(mf, goods_list[i + current_page_num*4]);
				temp_panel.setLocation(10 + i * 230, 10);
				goods_list_panel.add(temp_panel);
			} catch (ArrayIndexOutOfBoundsException e) { break; }
		}

		goods_list_panel.revalidate();
		goods_list_panel.repaint();
	}
}
