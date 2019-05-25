package front;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import back.Set;
import main.MainFrame;

/*프로그램 시작 시 처음 보이는 화면입니다.*/
public class StartPanel extends JPanel {

	private JLabel[] label;
	private JTextField[] textField;
	private JButton[] button;
	
	private MainFrame mf;
	private Set set;
	
	public StartPanel(MainFrame mf, Set set) {
		
		this.mf = mf;
		this.set =set;
		
		setLayout(null);
		
		/*ID PW 라벨 출력*/
		String[] label_name = {" I D", "PW"};
		label = new JLabel[2];
		for (int i=0; i<2; ++i) {
			label[i] = new JLabel(label_name[i]);
			label[i].setFont(new Font("Arial", Font.BOLD, 18));
			label[i].setBounds(10, 13 + 42 * i, 40, 30);
			add(label[i]);
		}
		
		/*ID PW 입력칸 생성*/
		textField= new JTextField[2];
		textField[0] = new JTextField();
		textField[1] = new JPasswordField();		// 비밀번호는 보이지 않도록
		for(int i=0; i<2; ++i) {
			textField[i].setHorizontalAlignment(JTextField.CENTER);	// 가운데 정렬
			textField[i].setBounds(50, 15 + 40*i, 120, 30);
			textField[i].addActionListener(new EnterKeyEventListenr());
			add(textField[i]);
		}
		
		/*버튼 생성*/
		String[] button_name = {"Login", "Register"};
		button = new JButton[2];
		for (int i=0; i<2; ++i) {
			button[i] = new JButton(button_name[i]);
			button[i].setFont(new Font("Arial", Font.BOLD, 18));
			button[i].setBackground(Color.DARK_GRAY);
			button[i].setForeground(Color.WHITE);
			button[i].setMargin(new Insets(0, 0, 0, 0));
			button[i].setBounds(180, 10 + 40*i, 100, 40);
			button[i].addActionListener(new BtnEventListener());
			button[i].setFocusable(false);
			add(button[i]);
		}	
	}
		
	/*버튼 이벤트 리스너*/
	private class BtnEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			JButton btn = (JButton)e.getSource();			
			switch(btn.getText()) {
	
			case "Login":	
				String ID = textField[0].getText();
				String PW = textField[1].getText();
				
				if (set.Login(ID, PW)) {
					mf.ChangePanel("Main");
				} else {
					textField[1].setText("");
					JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호를 잘못 입력하셨습니다.");
				}
				break;
				
			case "Register":
				new RegisterAccountFrame(mf, set);
				break;			
			}				
		}
	}

	/*엔터키로 로그인*/
	private class EnterKeyEventListenr implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String ID = textField[0].getText();
			String PW = textField[1].getText();
			
			if (set.Login(ID, PW)) {
				mf.ChangePanel("Main");
			} else {
				textField[1].setText("");
				JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호를 잘못 입력하셨습니다.");
			}
		}
		
	}
}
