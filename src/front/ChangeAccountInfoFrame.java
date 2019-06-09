package front;

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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import back.Set;
import main.MainFrame;
import structure.User;

/* 계정 정보를 변경하는 다이얼로그입니다.*/
public class ChangeAccountInfoFrame  extends JDialog {

	private JLabel[] info_label;
	private JTextField[] textField;
	private JButton[] button;
	private JLabel collect_pw_label;
	
	private Set set;
	
	public ChangeAccountInfoFrame(MainFrame mf, Set set) {
		
		super(mf, true);
		
		this.set = set;
		
		setTitle("계정 정보 변경");
		setLayout(null);
		
		/*사용자 정보*/
		User user = set.GetAccountInfo();
		
		/*사용자 정보  라벨 출력*/
		String [] label_name = {"ID", "PW", "PW 확인", "이름", "생년월일"};
		info_label = new JLabel[label_name.length];
		for (int i=0; i<info_label.length; ++i) {
			info_label[i] = new JLabel(label_name[i]);
			info_label[i].setFont(new Font("함초롬돋움", Font.BOLD, 18));
			info_label[i].setHorizontalAlignment(JLabel.LEFT);
			info_label[i].setBounds(10, 15 + 40 * i, 100, 30);
			add(info_label[i]);
		}
		
		/*사용자 정보 입력칸 생성*/
		JLabel ID_label = new JLabel(user.get_ID());
		ID_label.setBounds(100, 17, 120, 30);
		ID_label.setFont(new Font("함초롬돋움", Font.BOLD, 18));
		add(ID_label);
		
		String [] info_value_name = {user.get_PW(), user.get_PW(), user.get_name(), Integer.toString(user.get_birth_date())};
		textField = new JTextField[info_value_name.length];
		textField[0] = new JPasswordField();
		textField[1] = new JPasswordField();
		textField[2] = new JTextField();
		textField[3] = new JTextField();
		
		for (int i=0; i<info_value_name.length; ++i) {
			textField[i].setText(info_value_name[i]);
			textField[i].setHorizontalAlignment(JTextField.CENTER);
			textField[i].setBounds(100, 57 + 40*i, 120, 30);
			textField[i].addKeyListener(new KeyInputListener(i));
			add(textField[i]);
		}
		textField[0].getDocument().addDocumentListener(new PWEventListener());
		textField[1].getDocument().addDocumentListener(new PWEventListener());
		textField[3].addKeyListener(new LimitKeyEventListener());
		
		/*비밀번호 경고 라벨 생성*/
		collect_pw_label = new JLabel();
		collect_pw_label.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		collect_pw_label.setForeground(Color.RED);
		collect_pw_label.setBounds(230, 95, 90, 30);
		add(collect_pw_label);
		
		/*생성-취소 버튼 생성*/
		String [] button_name = {"수정", "취소"};
		button = new JButton[2];
		for (int i=0; i<2; ++i) {
			button[i] = new JButton(button_name[i]);
			button[i].setBackground(Color.DARK_GRAY);
			button[i].setForeground(Color.WHITE);
			button[i].setMargin(new Insets(0, 0, 0, 0));
			button[i].setBounds(150 + 90*i, 215, 80, 30);
			button[i].addActionListener(new BtnEventListener());
			add(button[i]);
		}
		
		setResizable(false);
		setSize(340,290);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/*버튼 동작 이벤트 리스너*/
	private class BtnEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			JButton btn = (JButton)e.getSource();
			switch(btn.getText()) {
			
			case "수정":	
				switch(CheckTextField()) {
					case 2:
						JOptionPane.showMessageDialog(null, "비밀번호를 확인 해 주세요.");
						break;
					case 3:
						JOptionPane.showMessageDialog(null, "비밀번호는 6자 이상으로 해주세요.");
						break;
					case 4:
						JOptionPane.showMessageDialog(null, "빈칸이 있습니다.");
						break;
					case 0:
						set.ChangeAccountInfo(textField[0].getText(), textField[2].getText(), textField[3].getText());
						JOptionPane.showMessageDialog(null, "수정되었습니다.");
						dispose();	
						break;			
				}
				break;
			case "취소":
				dispose();
				break;
			}
		}
		
	}

	/*비밀번호 확인 이벤트 리스너*/
	private class PWEventListener implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			PrintPWWarning();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			PrintPWWarning();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			PrintPWWarning();
		}
		
	}
	
	/*최대 입력 수 제한 이벤트 리스너*/
	private class KeyInputListener extends KeyAdapter {

		private int separator;
		public KeyInputListener(int separator) {
			// TODO Auto-generated constructor stub
			this.separator = separator;
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			JTextField text_field = (JTextField)e.getSource();
			
			int max_length = 0;
			switch(separator) {
			case 0: case 1: case 2:
				max_length = 20;
				break;
			case 3:
				max_length = 8;
				break;
			}
			
			if (text_field.getText().length() >= max_length) e.consume();
		}
	}
	
	/*키 입력 제한 이벤트 리스너*/
	private class LimitKeyEventListener extends KeyAdapter {
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			char c = e.getKeyChar();
			
			if (c < '0' || c > '9') {
				e.consume();
			}
		}	
	}
	
	
	/*검사*/
	private int CheckTextField() {
		
		if (!collect_pw_label.getText().equals("")) {
			return 2;
		}
		
		if (textField[1].getText().length() < 6) {
			return 3;
		}
			
		for (int i=0; i<textField.length; ++i) {
			if (textField[i].getText().equals("")) {
				return 4;
			}
		}
		
		return 0;
	}
	
	/*비밀번호 경고 출력*/
	private void PrintPWWarning() {
		String pw1 = textField[1].getText();
		String pw2 = textField[2].getText();
		if (pw1.equals(pw2))
			collect_pw_label.setText("");
		else 
			collect_pw_label.setText("서로 다릅니다");
	}
}
