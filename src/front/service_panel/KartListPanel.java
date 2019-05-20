package front.service_panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import back.Set;
import main.MainFrame;
import structure.ShoppingKart;

public class KartListPanel extends JPanel{
	
	private JPanel kart_list_panel;
	private PagePanel page_panel;
	private JButton [] kart_button;
	
	private MainFrame mf;
	private Set set;
	
	private ShoppingKart [] kart_list;
	
	public KartListPanel(MainFrame mf, Set set) {
		
		this.mf = mf;
		this.set = set;
		
		setLayout(null);
		setBackground(Color.WHITE);
		
		/*장바구니 목록 패널*/
		kart_list_panel = new JPanel();
		kart_list_panel.setLayout(null);
		kart_list_panel.setBounds(180, 50, 900, 450);
		add(kart_list_panel);
		
		/*페이지 패널*/
		page_panel = new PagePanel();
		page_panel.setBounds(180, 510, 900, 30);
		add(page_panel);
		
		/*구매 비우기 버튼*/
		String [] button_name = {"구매", "비우기"};
		kart_button = new JButton[2];
		for (int i=0; i<2; ++i) {
			kart_button[i] = new JButton(button_name[i]);
			kart_button[i].setBackground(Color.WHITE);
			kart_button[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
			kart_button[i].setMargin(new Insets(0, 0, 0, 0));
			kart_button[i].setBounds(850 + 120 * i, 550, 100, 30);
			kart_button[i].addActionListener(new ButtonEventListener());
			add(kart_button[i]);
		}
		
		show_kart_list_page(0);

	}
	
	/*상품 패널*/
	class KartPanel extends JPanel {
		
		private JLabel kart_name_label;
		private JLabel kart_count_label;
		private JButton [] count_button;
		private JLabel total_price_label;
		private JButton delete_button;
		
		private ShoppingKart kart;
		
		private int total_price;
		
		public KartPanel(ShoppingKart kart) {
			
			this.kart = kart;
			
			setLayout(null);

			/*상품 이름 출력*/
			kart_name_label = new JLabel(kart.get_name());
			kart_name_label.setFont(new Font("맑은 고딕", Font.BOLD, 17));
			kart_name_label.setHorizontalAlignment(JLabel.LEFT);
			kart_name_label.setBounds(20,  35,  250,  30);
			add(kart_name_label);
			
			/*상품 개수 출력*/
			kart_count_label = new JLabel(Integer.toString(kart.get_count()));
			kart_count_label.setFont(new Font("맑은 고딕", Font.BOLD, 17));
			kart_count_label.setBounds(450, 35, 50, 30);
			kart_count_label.setHorizontalAlignment(JLabel.CENTER);
			add(kart_count_label);
			
			/*상품 개수 조정*/
			String [] button_name = {"+", "-"};
			count_button = new JButton[2];
			for (int i=0; i<2; ++i) {
				count_button[i] = new JButton(button_name[i]);
				count_button[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
				count_button[i].setBackground(Color.WHITE);
				count_button[i].setMargin(new Insets(0, 0, 0, 0));
				count_button[i].setBounds(410 + 100 * i, 35, 30, 30);
				count_button[i].addActionListener(new KartButtonEventListener());
				add(count_button[i]);
			}
			
			/*상품 가격 출력*/
			total_price = kart.get_price();
			total_price_label = new JLabel("총 " + Integer.toString(total_price) + "원");
			total_price_label.setFont(new Font("맑은 고딕", Font.BOLD, 17));
			total_price_label.setHorizontalAlignment(JLabel.RIGHT);
			total_price_label.setBounds(550, 35, 120, 30);
			add(total_price_label);
			
			/*삭제 버튼*/
			delete_button = new JButton("X");
			delete_button.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			delete_button.setForeground(Color.RED);
			delete_button.setBackground(Color.WHITE);
			delete_button.setMargin(new Insets(0, 0, 0, 0));
			delete_button.setBounds(700, 35, 30, 30);
			delete_button.addActionListener(new KartButtonEventListener());
			add(delete_button);
			
			setBackground(Color.WHITE);
			setSize(750, 100);
			setBorder(new LineBorder(Color.BLACK));
		}
		
		/*개수 조정*/
		private void set_count(int num) {
			
			int count = kart.get_count() + num;
			if (count < 1) {
				return;
			}
			
			kart_count_label.setText(Integer.toString(count));
			total_price_label.setText("총 " + Integer.toString(count * kart.get_price()) + "원");
			
			set.SetKartListNum(kart.get_name(), count);
			kart.set_count(count);
		}
		
		/*개수 증가 감소 버튼 이벤트 리스너*/
		class KartButtonEventListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton btn = (JButton)e.getSource();
				
				switch(btn.getText()) {
				case "+":
					set_count(1);
					break;
				case "-":
					set_count(-1);
					break;
				case "X":
					delete_kart(kart.get_name());
					break;
				}
			}
			
		}
	}

	/*페이지 패널*/
	class PagePanel extends JPanel {
		
		private JLabel [] page_num_label;

		public PagePanel() {
			
			//setBackground(Color.white);
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
				show_kart_list_page(Integer.valueOf(label.getText()) - 1);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				JLabel label = (JLabel)e.getSource();
				label.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
	}
	
	/*구매 비우기 버튼 이벤트 리스너*/
	class ButtonEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			JButton btn = (JButton)e.getSource();
			switch(btn.getText()) {
			case "구매":
				break;
			case "비우기":
				empty_kart();
				break;
			}
		}
		
	}
	
	/*장바구니 목록 갱신*/
	private void show_kart_list_page(int current_page_num) {
		
		kart_list = set.get_kart_list();
		kart_list_panel.removeAll();		
		
		page_panel.update_page(kart_list.length / 4 + 1);
		page_panel.select_page(current_page_num);

		for (int i=0; i<4; ++i) {
			
			try {
				KartPanel temp_panel = new KartPanel(kart_list[i + current_page_num * 4]);
				temp_panel.setLocation(75, 10 + i * 110);
				kart_list_panel.add(temp_panel);
			} catch (ArrayIndexOutOfBoundsException e) { break; }
		}
		
		kart_list_panel.revalidate();
		kart_list_panel.repaint();
	}

	/*장바구니 상품 지우기*/
	private void delete_kart(String name) {
		set.DeleteKart(name);
		show_kart_list_page(0);
	}
	
	/*구매*/
	private void purchase() {
		
	}
	
	/*장바구니 비우기*/
	private void empty_kart() {
		set.EmptyKart();
		show_kart_list_page(0);
	}
}
