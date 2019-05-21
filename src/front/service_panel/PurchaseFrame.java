package front.service_panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import back.Set;
import main.MainFrame;
import structure.Order;
import structure.ShoppingKart;

public class PurchaseFrame extends JDialog{
	
	private JLabel delivery_label;
	private JLabel payment_label;
	private JLabel [] info_label;
	private JTextField [] info_textfield;
	private JLabel total_price_label;
	private JButton [] button;
	
	private int total_price;

	private Set set;
	private Order order = null;
	
	public PurchaseFrame(MainFrame mf, Set set) {
		super(mf, true);
	
		this.set = set;
		
		setLayout(null);
		
		/*�� �� �ݾ� ���*/
		total_price = 0;
		ShoppingKart [] kart = set.get_kart_list();
		for (int i=0; i<kart.length; ++i) {
			total_price += kart[i].get_price() * kart[i].get_count();
		}
		
		/*������*/
		delivery_label = new JLabel("��� ����");
		delivery_label.setBounds(10, 10, 80, 30);
		delivery_label.setFont(new Font("���� ���", Font.BOLD, 17));
		add(delivery_label);
		
		payment_label = new JLabel("���� ����");
		payment_label.setBounds(10, 180, 80, 30);
		payment_label.setFont(new Font("���� ���", Font.BOLD, 17));
		add(payment_label);
		
		/*���� �̸�*/
		String [] label_name = {"�̸�", "�����", "��ȭ��ȣ", "ī���", "ī�� ��ȣ", "ī�� ��й�ȣ", "���ȹ�ȣ"};
		info_label = new JLabel[label_name.length];
		for (int i=0; i<3; ++i) {
			info_label[i] = new JLabel(label_name[i]);
			info_label[i].setFont(new Font("���� ���", Font.BOLD, 15));
			info_label[i].setForeground(new Color(109, 109, 219));
			info_label[i].setBounds(40, 50 + 40 * i, 110, 30);
			add(info_label[i]);
		}
		for (int i=3; i<7; ++i) {
			info_label[i] = new JLabel(label_name[i]);
			info_label[i].setFont(new Font("���� ���", Font.BOLD, 15));
			info_label[i].setForeground(new Color(109, 109, 219));
			info_label[i].setBounds(40, 100 + 40 * i, 110, 30);
			add(info_label[i]);
		}
		
		/*������ �Է¹��� ĭ*/
		info_textfield = new JTextField[7];
		for (int i=0; i<3; ++i) {
			info_textfield[i] = new JTextField();
			info_textfield[i].setFont(new Font("���� ���", Font.BOLD, 15));
			info_textfield[i].setLocation(150, 50 + 40 * i);
			add(info_textfield[i]);
		}
		for (int i=3; i<7; ++i) {
			if (i==5 || i==6) {
				info_textfield[i]= new JPasswordField();
			} else {				
				info_textfield[i]= new JTextField();
			}
			info_textfield[i].setFont(new Font("���� ���", Font.BOLD, 15));
			info_textfield[i].setLocation(150, 100 + 40 * i);
			add(info_textfield[i]);
		}
		info_textfield[0].setSize(90, 30);
		info_textfield[1].setSize(400, 30);
		info_textfield[2].setSize(150, 30);
		info_textfield[3].setSize(90, 30);
		info_textfield[4].setSize(200, 30);
		info_textfield[5].setSize(90, 30);
		info_textfield[6].setSize(90, 30);
		
		/*���� ���� �ݾ�*/
		total_price_label = new JLabel("�� " + Integer.toString(total_price) + "�� �Դϴ�.");
		total_price_label.setFont(new Font("���� ���", Font.BOLD, 15));
		total_price_label.setBounds(20, 395, 280, 30);
		add(total_price_label);
		
		/*����, ��� ��ư*/
		String [] button_name = {"����", "���"};
		button = new JButton[2];
		for (int i=0; i<2; ++i) {
			button[i] = new JButton(button_name[i]);
			button[i].setBackground(Color.WHITE);
			button[i].setFont(new Font("���� ���", Font.BOLD, 15));
			button[i].setMargin(new Insets(0, 0, 0, 0));
			button[i].setBounds(430 + 100*i, 400, 80, 30);
			button[i].addActionListener(new ButtonEventListener());
			add(button[i]);
		}
		
		setResizable(false);
		setSize(650, 480);
		setLocationRelativeTo(mf);
		setVisible(true);
	}
	
	class ButtonEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton btn = (JButton)e.getSource();
			
			switch(btn.getText()) {
			case "����":
				if (check_purchase()) {
					set.Purchase(order);
					dispose();
				} else {
					// ���� ���� ���
				}
				break;
			case "���":
				dispose();
				break;
			}
		}
		
	}
	
	private boolean check_purchase() {
		
		String [] value = new String[info_textfield.length];
		for (int i=0; i<info_textfield.length; ++i) {
			String temp;
			if ((temp = info_textfield[i].getText()).equals("")) return false;
			value[i] = temp;
		}
		order = new Order(value[0], value[1], value[2], value[3], value[4], total_price);
		return true;
	}
}
