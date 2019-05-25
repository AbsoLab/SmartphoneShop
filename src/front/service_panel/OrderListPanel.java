package front.service_panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import back.Set;
import main.MainFrame;
import structure.Order;
import structure.ShoppingKart;

/*로그인한 사용자의 여태까지 주문했던 목록을 보여줍니다.*/
public class OrderListPanel extends JPanel {
	
	private JPanel order_list_panel;
	private PagePanel page_panel;
	
	private Order [] order_list;
	
	private MainFrame mf;
	private Set set;
	
	public OrderListPanel(MainFrame mf, Set set) {
		
		this.mf = mf;
		this.set = set;
		
		setLayout(null);
		setBackground(Color.WHITE);
		
		/*주문 목록 패널*/
		order_list_panel = new JPanel();
		order_list_panel.setLayout(null);
		order_list_panel.setBounds(180, 50, 900, 450);
		add(order_list_panel);
		//order_list_panel.setPreferredSize(new Dimension(750, order.length * 70));
		
		/*페이지 패널*/
		page_panel = new PagePanel();
		page_panel.setBounds(180, 510, 900, 30);
		add(page_panel);
		
		show_order_list_page(0);
	}
	
	/*주문 정보 패널*/
	private class OrderPanel extends JPanel {
		
		private JLabel order_number_label;
		private JLabel goods_name_label;
		private JLabel total_price_label;
				
		private Order order;
		
		public OrderPanel(Order order) {
				
			this.order = order;
			
			setLayout(null);
			setSize(750, 100);
			setBackground(Color.WHITE);
			setBorder(new LineBorder(Color.BLACK));
			
			/*주문 번호 출력*/
			order_number_label = new JLabel("주문번호 : " + Integer.toString(order.get_order_num()));
			order_number_label.setFont(new Font("맑은 고딕", Font.BOLD, 17));
			order_number_label.setHorizontalAlignment(JLabel.LEFT);
			order_number_label.setBounds(20,  35,  150,  30);
			add(order_number_label);
			
			/*상품 정보 출력*/
			ShoppingKart [] goods = set.GetOrderKartList(order.get_order_num());
			if (goods.length == 1) {
				goods_name_label = new JLabel("구매 상품 : " + goods[0].get_name());
			} else {				
				goods_name_label = new JLabel("구매 상품 : " + goods[0].get_name() + "외 " + goods.length + "건");
			}
			goods_name_label.setFont(new Font("맑은 고딕", Font.BOLD, 17));
			goods_name_label.setBounds(190, 35, 300, 30);
			add(goods_name_label);
			
			/*가격 출력*/
			total_price_label = new JLabel("결제 금액 : " + Integer.toString(order.get_total_price()) + "원");
			total_price_label.setFont(new Font("맑은 고딕", Font.BOLD, 17));
			total_price_label .setBounds(500, 35, 200, 30);
			add(total_price_label);
		}
		
		public Order get_order() { return order; }
	}
	
	/*페이지 패널*/
	private class PagePanel extends JPanel {
		
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
		private class MouseActionListener extends MouseAdapter {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JLabel label = (JLabel)e.getSource();
				show_order_list_page(Integer.valueOf(label.getText()) - 1);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				JLabel label = (JLabel)e.getSource();
				label.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
	}
	
	/*주문 정보 클릭 이벤트 리스너*/
	private class OrderPanelEventListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			OrderPanel panel = (OrderPanel)e.getSource();
			new OrderInfoFrame(mf, set, panel.get_order());
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			JPanel panel= (JPanel)e.getSource();
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
	
	/*주문 목록 띄위기*/
	private void show_order_list_page(int current_page_num) {
		
		order_list = set.GetOrderList();
		order_list_panel.removeAll();
		
		page_panel.update_page((order_list.length - 1) / 4 + 1);
		page_panel.select_page(current_page_num);

		for (int i=0; i<4; ++i) {
			
			try {
				OrderPanel temp_panel = new OrderPanel(order_list[i + current_page_num * 4]);
				temp_panel.setLocation(75, 10 + i * 110);
				temp_panel.addMouseListener(new OrderPanelEventListener());
				order_list_panel.add(temp_panel);
			} catch (ArrayIndexOutOfBoundsException e) { break; }
		}
		
		order_list_panel.revalidate();
		order_list_panel.repaint();
	}
}
