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

/*���α׷� ���� �� ó�� ���̴� ȭ���Դϴ�.*/
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
		
		/*ID PW �� ���*/
		String[] label_name = {" I D", "PW"};
		label = new JLabel[2];
		for (int i=0; i<2; ++i) {
			label[i] = new JLabel(label_name[i]);
			label[i].setFont(new Font("Arial", Font.BOLD, 18));
			label[i].setBounds(10, 13 + 42 * i, 40, 30);
			add(label[i]);
		}
		
		/*ID PW �Է�ĭ ����*/
		textField= new JTextField[2];
		textField[0] = new JTextField();
		textField[1] = new JPasswordField();		// ��й�ȣ�� ������ �ʵ���
		for(int i=0; i<2; ++i) {
			textField[i].setHorizontalAlignment(JTextField.CENTER);	// ��� ����
			textField[i].setBounds(50, 15 + 40*i, 120, 30);
			textField[i].addActionListener(new EnterKeyEventListenr());
			add(textField[i]);
		}
		
		/*��ư ����*/
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
		
	/*��ư �̺�Ʈ ������*/
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
					JOptionPane.showMessageDialog(null, "���̵� Ȥ�� ��й�ȣ�� �߸� �Է��ϼ̽��ϴ�.");
				}
				break;
				
			case "Register":
				new RegisterAccountFrame(mf, set);
				break;			
			}				
		}
	}

	/*����Ű�� �α���*/
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
				JOptionPane.showMessageDialog(null, "���̵� Ȥ�� ��й�ȣ�� �߸� �Է��ϼ̽��ϴ�.");
			}
		}
		
	}
}
