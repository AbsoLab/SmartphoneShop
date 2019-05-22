package front.service_panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import back.Set;
import main.MainFrame;
import structure.Order;
import structure.ShoppingKart;

public class OrderInfoFrame extends JDialog {

	private OrderListPanel order_list_panel;
	private OrderInfoPanel order_info_panel;
	private OrderPaymentPanel order_payment_panel;
		
	public OrderInfoFrame(MainFrame mf, Set set, Order order) {
		super(mf, true);
		
		setTitle("주문 정보");
		setLayout(null);

		/*주문 목록 패널*/
		order_list_panel = new OrderListPanel(set, order);
		order_list_panel.setLocation(0, 0);
		add(order_list_panel);
		
		/*주문 정보 패널*/
		order_info_panel = new OrderInfoPanel(set, order);
		order_info_panel.setLocation(0, 280);
		add(order_info_panel);
		
		/*주문 정보 패널*/
		order_payment_panel = new OrderPaymentPanel(set, order);
		order_payment_panel.setLocation(0, 440);
		add(order_payment_panel);
		
		setResizable(false);
		setSize(540, 635);
		setLocationRelativeTo(mf);
		setVisible(true);
	}
	
	/*주문 목록 패널*/
	class OrderListPanel extends JPanel {
		
		private JScrollPane scroll_pane;
		private JPanel scroll_input_panel;
		private JPanel [] order_list_panel;
		private JLabel total_price_label;
		
		private ShoppingKart [] order_list;
		
		public OrderListPanel (Set set, Order order) {
			
			setLayout(null);
			setSize(540, 280);
			setBackground(Color.WHITE);
			
			/*주문 상품 목록 받아오기*/
			order_list = set.GetOrderKartList(order.get_order_num());
			
			/*패널 이름 출력*/
			JLabel panel_name = new JLabel("주문 목록");
			panel_name.setBounds(20, 10, 100, 30);
			panel_name.setFont(new Font("맑은 고딕", Font.BOLD, 17));
			add(panel_name);
			
			/*주문 목록 출력*/
			scroll_input_panel = new JPanel();
			scroll_input_panel.setLayout(null);
			scroll_input_panel.setBackground(Color.WHITE);
			scroll_input_panel.setPreferredSize(new Dimension(460, 50 * order_list.length));
				
			order_list_panel = new JPanel[order_list.length];
			for (int i=0; i<order_list.length; ++i) {
				order_list_panel[i] = new JPanel();
				order_list_panel[i].setLayout(null);
				order_list_panel[i].setBackground(Color.WHITE);
				
				/*상품 이름 출력*/
				JLabel goods_name = new JLabel(order_list[i].get_name());
				goods_name.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				goods_name.setBounds(20, 10, 250, 30);
				order_list_panel[i].add(goods_name);
				
				/*개수 출력*/
				JLabel goods_count = new JLabel(order_list[i].get_count() + "개");
				goods_count.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				goods_count.setBounds(290, 10, 50, 30);
				order_list_panel[i].add(goods_count);
				
				/*금액 출력*/
				JLabel goods_price = new JLabel(order_list[i].get_price() * order_list[i].get_count() + "원");
				goods_price.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				goods_price.setHorizontalAlignment(JLabel.RIGHT);
				goods_price.setBounds(350, 10, 100, 30);
				order_list_panel[i].add(goods_price);
				
				order_list_panel[i].setBorder(new LineBorder(Color.BLACK));
				order_list_panel[i].setBounds(0, 50 * i, 460, 50);
				scroll_input_panel.add(order_list_panel[i]);
			}
			
			scroll_pane = new JScrollPane(scroll_input_panel);
			scroll_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scroll_pane.setBounds(20, 45, 480, 200);
			add(scroll_pane);
			
			/*총 결제 금액*/
			int total_price=0;
			for (int i=0; i<order_list.length; ++i) {
				total_price += order_list[i].get_price() * order_list[i].get_count();
			}
			total_price_label = new JLabel("총 금액 : " + total_price + "원");
			total_price_label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			total_price_label.setHorizontalAlignment(JLabel.RIGHT);
			total_price_label.setBounds(300, 250, 200, 30);
			add(total_price_label);
		}
	}

	/*주문 정보 패널*/
	class OrderInfoPanel extends JPanel {

		private JLabel [] info_name_label;
		private JLabel [] info_value_label;
		
		public OrderInfoPanel(Set set, Order order) {
			
			setLayout(null);
			setSize(540, 160);
			setBackground(Color.WHITE);
			
			/*패널 이름 출력*/
			JLabel panel_name = new JLabel("주문 정보");
			panel_name.setBounds(20, 0, 100, 30);
			panel_name.setFont(new Font("맑은 고딕", Font.BOLD, 17));
			add(panel_name);
			
			String [] info_name = {"이름", "주소", "연락처"};
			info_name_label = new JLabel[info_name.length];
			for (int i=0; i<info_name.length; ++i) {
				info_name_label[i] = new JLabel(info_name[i]);
				info_name_label[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
				info_name_label[i].setBounds(30, 30 + 40 * i, 70, 30);
				add(info_name_label[i]);
			}
			
			String [] info_value = {order.get_name(), order.get_address(), order.get_phone_num()};
			info_value_label = new JLabel[info_name.length];
			for (int i=0; i<info_name.length; ++i) {
				info_value_label[i] = new JLabel(info_value[i]);
				info_value_label[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
				info_value_label[i].setBounds(150, 30 + 40 * i, 320, 30);
				add(info_value_label[i]);
			}
		}
		
	}

	/*결제 정보 패널*/
	class OrderPaymentPanel extends JPanel {

		private JLabel [] info_name_label;
		private JLabel [] info_value_label;
		
		public OrderPaymentPanel(Set set, Order order) {

			setLayout(null);
			setSize(540, 160);
			setBackground(Color.WHITE);
			
			/*패널 이름 출력*/
			JLabel panel_name = new JLabel("결제 정보");
			panel_name.setBounds(20, 0, 100, 30);
			panel_name.setFont(new Font("맑은 고딕", Font.BOLD, 17));
			add(panel_name);
			
			String [] info_name = {"결제 수단", "카드 정보", "카드 번호"};
			info_name_label = new JLabel[info_name.length];
			for (int i=0; i<info_name.length; ++i) {
				info_name_label[i] = new JLabel(info_name[i]);
				info_name_label[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
				info_name_label[i].setBounds(30, 30 + 40 * i, 70, 30);
				add(info_name_label[i]);
			}
			
			String [] info_value = {"카드", order.get_card_corporation(), order.get_card_num()};
			info_value_label = new JLabel[info_name.length];
			for (int i=0; i<info_name.length; ++i) {
				info_value_label[i] = new JLabel(info_value[i]);
				info_value_label[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
				info_value_label[i].setBounds(150, 30 + 40 * i, 320, 30);
				add(info_value_label[i]);
			}
		}
		
	}
}

