package front.service_panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import back.Set;
import main.MainFrame;
import structure.Order;
import structure.ShoppingKart;

/*구매 클릭시 나타나는 다이얼로그입니다.*/
public class PurchaseFrame extends JDialog {
	
	private JLabel delivery_label;
	private JLabel payment_label;
	private JLabel [] info_label;
	private JTextField [] info_textField;
	private JLabel total_price_label;
	private JButton [] button;
	
	private int total_price;

	private Set set;
	private Order order = null;
	
	public PurchaseFrame(MainFrame mf, Set set) {
		super(mf, true);
	
		this.set = set;
		setLayout(null);
		
		/*총 합 금액 계산*/
		total_price = 0;
		ShoppingKart [] kart = set.get_kart_list();
		for (int i=0; i<kart.length; ++i) {
			total_price += kart[i].get_price() * kart[i].get_count();
		}
		
		/*소제목*/
		delivery_label = new JLabel("배송 정보");
		delivery_label.setBounds(10, 10, 80, 30);
		delivery_label.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		add(delivery_label);
		
		payment_label = new JLabel("결제 정보");
		payment_label.setBounds(10, 180, 80, 30);
		payment_label.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		add(payment_label);
		
		/*정보 이름*/
		String [] label_name = {"이름", "배송지", "전화번호", "카드사", "카드 번호", "카드 비밀번호", "보안번호"};
		info_label = new JLabel[label_name.length];
		for (int i=0; i<3; ++i) {
			info_label[i] = new JLabel(label_name[i]);
			info_label[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
			info_label[i].setForeground(new Color(109, 109, 219));
			info_label[i].setBounds(40, 50 + 40 * i, 110, 30);
			add(info_label[i]);
		}
		for (int i=3; i<7; ++i) {
			info_label[i] = new JLabel(label_name[i]);
			info_label[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
			info_label[i].setForeground(new Color(109, 109, 219));
			info_label[i].setBounds(40, 100 + 40 * i, 110, 30);
			add(info_label[i]);
		}
		
		/*정보를 입력받을 칸*/
		info_textField = new JTextField[label_name.length];
		for (int i=0; i<3; ++i) {
			info_textField[i] = new JTextField();
			info_textField[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
			info_textField[i].setLocation(150, 50 + 40 * i);
			info_textField[i].setHorizontalAlignment(JTextField.CENTER);
			info_textField[i].addKeyListener(new LimitLengthEvnetListener(i));
			add(info_textField[i]);
		}
		info_textField[1].setHorizontalAlignment(JTextField.LEFT);
		
		for (int i=3; i<7; ++i) {
			if (i==5 || i==6) {
				info_textField[i]= new JPasswordField();
				info_textField[i].addKeyListener(new LimitInputEventListener());
			} else {				
				info_textField[i]= new JTextField();
			}
			info_textField[i].setHorizontalAlignment(JTextField.CENTER);
			info_textField[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
			info_textField[i].setLocation(150, 100 + 40 * i);
			info_textField[i].addKeyListener(new LimitLengthEvnetListener(i));
			add(info_textField[i]);
		}
		
		info_textField[0].setSize(90, 30);
		info_textField[1].setSize(400, 30);
		info_textField[2].setSize(200, 30);
		info_textField[3].setSize(90, 30);
		info_textField[4].setSize(200, 30);
		info_textField[5].setSize(90, 30);
		info_textField[6].setSize(90, 30);
		
		info_textField[2].addKeyListener(new LimitInputEventListener());
		info_textField[4].addKeyListener(new LimitInputEventListener());
		
		/*최종 결제 금액*/
		total_price_label = new JLabel("총 " + Integer.toString(total_price) + "원 입니다.");
		total_price_label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		total_price_label.setBounds(20, 395, 280, 30);
		add(total_price_label);
		
		/*구매, 취소 버튼*/
		String [] button_name = {"구매", "취소"};
		button = new JButton[2];
		for (int i=0; i<2; ++i) {
			button[i] = new JButton(button_name[i]);
			button[i].setBackground(Color.WHITE);
			button[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
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
	
	/*입력 개수 제한*/
	private class LimitLengthEvnetListener extends KeyAdapter {

		private int separator;
		
		public LimitLengthEvnetListener(int separator) {
			this.separator = separator;
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			JTextField text_field = (JTextField)e.getSource();
			
			int max_length = 0;
			switch(separator) {
			case 0:
				max_length = 40;
				break;
			case 1:
				max_length = 60;
				break;
			case 2:
				max_length = 11;
				break;
			case 3:
				max_length = 20;
				break;
			case 4:
				max_length = 16;
				break;
			case 5:
				max_length = 4;
				break;
			case 6:
				max_length = 3;
				break;
			}
			
			if (text_field.getText().length() >= max_length) e.consume();
		}	
	}
	
	/*입력 제한*/
	private class LimitInputEventListener extends KeyAdapter {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			char c = e.getKeyChar();
			
			if (c < '0' || c > '9') {
				e.consume();
			}
		}	
		
	}
	
	/*구매, 취소 버튼 이벤트 리스너*/
	private class ButtonEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton btn = (JButton)e.getSource();
			
			switch(btn.getText()) {
			case "구매":
				if (check_purchase()) {
					set.Purchase(order);
					dispose();
				} else {
					// 오류 문구 출력
					JOptionPane.showMessageDialog(null, "빈칸이 있습니다.");
				}
				break;
			case "취소":
				dispose();
				break;
			}
		}
		
	}
	
	/*빈칸 확인*/
	private boolean check_purchase() {
		
		String [] value = new String[info_textField.length];
		for (int i=0; i<info_textField.length; ++i) {
			String temp;
			if ((temp = info_textField[i].getText()).equals("")) return false;
			value[i] = temp;
		}
		order = new Order(value[0], value[1], value[2], value[3], value[4], total_price);
		return true;
	}
}
