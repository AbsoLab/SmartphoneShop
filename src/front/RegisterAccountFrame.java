package front;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import back.Set;
import main.MainFrame;


public class RegisterAccountFrame extends JDialog {

	private JLabel[] label;
	private JTextField[] textField;
	private JButton[] button;
	private JButton duplicate_check_btn;
	
	private Set set;
	private boolean check_duplication = false;
	
	public RegisterAccountFrame(MainFrame mf, Set set) {
		super(mf, true);	// 기존 창 선택 불가
		
		this.set = set;
		
		setTitle("Register Account");
		setLayout(null);
		
		/*사용자 정보  라벨 출력*/
		String [] label_name = {"ID", "PW", "이름", "생년월일"};
		label = new JLabel[label_name.length];
		for (int i=0; i<label.length; ++i) {
			label[i] = new JLabel(label_name[i]);
			label[i].setFont(new Font("함초롬돋움", Font.BOLD, 18));
			label[i].setHorizontalAlignment(JLabel.CENTER);
			label[i].setBounds(0, 15 + 50 * i, 100, 30);
			add(label[i]);
		}
		
		/*사용자 정보 입력칸 생성*/
		textField = new JTextField[4];
		textField[0] = new JTextField();
		textField[1] = new JPasswordField();
		textField[2] = new JTextField();
		textField[3] = new JTextField();
		for (int i=0; i<4; ++i) {
			textField[i].setHorizontalAlignment(JTextField.CENTER);	// 가운데 정렬
			textField[i].setBounds(100, 17 + 50*i, 120, 30);
			add(textField[i]);
		}
		
		/*중복 확인 버튼 생성*/
		duplicate_check_btn = new JButton("중복 확인");
		duplicate_check_btn.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		duplicate_check_btn.setBackground(Color.DARK_GRAY);
		duplicate_check_btn.setForeground(Color.WHITE);
		duplicate_check_btn.setBounds(230, 17, 90, 29);
		duplicate_check_btn.addActionListener(new BtnEventListener());
		add(duplicate_check_btn);
		
		/*생성 취소 버튼 생성*/
		String [] button_name = {"가입", "취소"};
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
			
			case "중복 확인":
				
				String ID = textField[0].getText();
				if (ID.length() < 6) {
					JOptionPane.showMessageDialog(null, "최소 6자 이상 적어주세요.");
				} else if (set.CompareID(ID)) {
					JOptionPane.showMessageDialog(null, "이미 사용중인 아이디입니다.");
				} else {
					JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
					check_duplication = true;
				}
				break;
			case "가입":
				if (!check_duplication) {
					JOptionPane.showMessageDialog(null, "중복확인을 해주세요.");
				} else {
					set.RegisterAccount(textField[0].getText(), textField[1].getText(), textField[2].getText(), textField[3].getText());
					JOptionPane.showMessageDialog(null, "회원가입되셨습니다.");
					dispose();					
				}
				break;
			case "취소":
				dispose();
				break;
			}
		}
		
	}

}
