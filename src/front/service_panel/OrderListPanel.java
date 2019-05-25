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

/*�α����� ������� ���±��� �ֹ��ߴ� ����� �����ݴϴ�.*/
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
		
		/*�ֹ� ��� �г�*/
		order_list_panel = new JPanel();
		order_list_panel.setLayout(null);
		order_list_panel.setBounds(180, 50, 900, 450);
		add(order_list_panel);
		//order_list_panel.setPreferredSize(new Dimension(750, order.length * 70));
		
		/*������ �г�*/
		page_panel = new PagePanel();
		page_panel.setBounds(180, 510, 900, 30);
		add(page_panel);
		
		show_order_list_page(0);
	}
	
	/*�ֹ� ���� �г�*/
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
			
			/*�ֹ� ��ȣ ���*/
			order_number_label = new JLabel("�ֹ���ȣ : " + Integer.toString(order.get_order_num()));
			order_number_label.setFont(new Font("���� ���", Font.BOLD, 17));
			order_number_label.setHorizontalAlignment(JLabel.LEFT);
			order_number_label.setBounds(20,  35,  150,  30);
			add(order_number_label);
			
			/*��ǰ ���� ���*/
			ShoppingKart [] goods = set.GetOrderKartList(order.get_order_num());
			if (goods.length == 1) {
				goods_name_label = new JLabel("���� ��ǰ : " + goods[0].get_name());
			} else {				
				goods_name_label = new JLabel("���� ��ǰ : " + goods[0].get_name() + "�� " + goods.length + "��");
			}
			goods_name_label.setFont(new Font("���� ���", Font.BOLD, 17));
			goods_name_label.setBounds(190, 35, 300, 30);
			add(goods_name_label);
			
			/*���� ���*/
			total_price_label = new JLabel("���� �ݾ� : " + Integer.toString(order.get_total_price()) + "��");
			total_price_label.setFont(new Font("���� ���", Font.BOLD, 17));
			total_price_label .setBounds(500, 35, 200, 30);
			add(total_price_label);
		}
		
		public Order get_order() { return order; }
	}
	
	/*������ �г�*/
	private class PagePanel extends JPanel {
		
		private JLabel [] page_num_label;

		public PagePanel() {
			
			//setBackground(Color.white);
		}
		
		/*������ ���� ����*/
		public void update_page(int total_page_num) {
			
			removeAll();
			
			/*10���� �Ѿ ��� �� �����غ���*/
			page_num_label = new JLabel[total_page_num];
			
			for (int i=0; i<total_page_num; ++i) {
				page_num_label[i] = new JLabel(Integer.toString(i+1));
				page_num_label[i].setFont(new Font("���� ���", Font.BOLD, 15));
				page_num_label[i].addMouseListener(new MouseActionListener());
				add(page_num_label[i]);
			}
			
			revalidate();
			repaint();
		}
		
		public void select_page(int page) {
			page_num_label[page].setForeground(Color.BLUE);
		}
		
		/*������ ���� �̺�Ʈ ������*/
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
	
	/*�ֹ� ���� Ŭ�� �̺�Ʈ ������*/
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
	
	/*�ֹ� ��� ������*/
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
