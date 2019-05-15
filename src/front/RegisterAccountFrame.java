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
		super(mf, true);	// ���� â ���� �Ұ�
		
		this.set = set;
		
		setTitle("Register Account");
		setLayout(null);
		
		/*����� ����  �� ���*/
		String [] label_name = {"ID", "PW", "�̸�", "�������"};
		label = new JLabel[label_name.length];
		for (int i=0; i<label.length; ++i) {
			label[i] = new JLabel(label_name[i]);
			label[i].setFont(new Font("���ʷҵ���", Font.BOLD, 18));
			label[i].setHorizontalAlignment(JLabel.CENTER);
			label[i].setBounds(0, 15 + 50 * i, 100, 30);
			add(label[i]);
		}
		
		/*����� ���� �Է�ĭ ����*/
		textField = new JTextField[4];
		textField[0] = new JTextField();
		textField[1] = new JPasswordField();
		textField[2] = new JTextField();
		textField[3] = new JTextField();
		for (int i=0; i<4; ++i) {
			textField[i].setHorizontalAlignment(JTextField.CENTER);	// ��� ����
			textField[i].setBounds(100, 17 + 50*i, 120, 30);
			add(textField[i]);
		}
		
		/*�ߺ� Ȯ�� ��ư ����*/
		duplicate_check_btn = new JButton("�ߺ� Ȯ��");
		duplicate_check_btn.setFont(new Font("���ʷҵ���", Font.BOLD, 12));
		duplicate_check_btn.setBackground(Color.DARK_GRAY);
		duplicate_check_btn.setForeground(Color.WHITE);
		duplicate_check_btn.setBounds(230, 17, 90, 29);
		duplicate_check_btn.addActionListener(new BtnEventListener());
		add(duplicate_check_btn);
		
		/*���� ��� ��ư ����*/
		String [] button_name = {"����", "���"};
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
	
	/*��ư ���� �̺�Ʈ ������*/
	private class BtnEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			JButton btn = (JButton)e.getSource();
			switch(btn.getText()) {
			
			case "�ߺ� Ȯ��":
				
				String ID = textField[0].getText();
				if (ID.length() < 6) {
					JOptionPane.showMessageDialog(null, "�ּ� 6�� �̻� �����ּ���.");
				} else if (set.CompareID(ID)) {
					JOptionPane.showMessageDialog(null, "�̹� ������� ���̵��Դϴ�.");
				} else {
					JOptionPane.showMessageDialog(null, "��� ������ ���̵��Դϴ�.");
					check_duplication = true;
				}
				break;
			case "����":
				if (!check_duplication) {
					JOptionPane.showMessageDialog(null, "�ߺ�Ȯ���� ���ּ���.");
				} else {
					set.RegisterAccount(textField[0].getText(), textField[1].getText(), textField[2].getText(), textField[3].getText());
					JOptionPane.showMessageDialog(null, "ȸ�����ԵǼ̽��ϴ�.");
					dispose();					
				}
				break;
			case "���":
				dispose();
				break;
			}
		}
		
	}

}
