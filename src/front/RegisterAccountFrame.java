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

/*ȸ������ ���� �� ������ ���̾�α��Դϴ�.*/
public class RegisterAccountFrame extends JDialog {

	private JLabel[] info_label;
	private JTextField[] textField;
	private JButton[] button;
	private JButton duplicate_check_btn;
	private JLabel collect_pw_label;
	
	private Set set;
	private boolean check_duplication = false;		// �ߺ�Ȯ���� �������� Ȯ���ϴ� �뵵
	
	public RegisterAccountFrame(MainFrame mf, Set set) {
		super(mf, true);	// ���� â ���� �Ұ�
		
		this.set = set;
		
		setTitle("Register Account");
		setLayout(null);
		
		/*����� ����  �� ���*/
		String [] label_name = {"ID", "PW", "PW Ȯ��", "�̸�", "�������"};
		info_label = new JLabel[label_name.length];
		for (int i=0; i<info_label.length; ++i) {
			info_label[i] = new JLabel(label_name[i]);
			info_label[i].setFont(new Font("���ʷҵ���", Font.BOLD, 18));
			info_label[i].setHorizontalAlignment(JLabel.LEFT);
			info_label[i].setBounds(10, 15 + 40 * i, 100, 30);
			add(info_label[i]);
		}
		
		/*����� ���� �Է�ĭ ����*/
		textField = new JTextField[5];
		textField[0] = new JTextField();
		textField[1] = new JPasswordField();
		textField[2] = new JPasswordField();
		textField[3] = new JTextField();
		textField[4] = new JTextField();
		for (int i=0; i<5; ++i) {
			textField[i].setHorizontalAlignment(JTextField.CENTER);	// ��� ����
			textField[i].setBounds(100, 17 + 40*i, 120, 30);
			textField[i].addKeyListener(new KeyInputListener(i));
			add(textField[i]);
		}
		textField[1].getDocument().addDocumentListener(new IDEventListener() );
		textField[1].getDocument().addDocumentListener(new PWEventListener());
		textField[2].getDocument().addDocumentListener(new PWEventListener());
		
		/*�ߺ� Ȯ�� ��ư ����*/
		duplicate_check_btn = new JButton("�ߺ� Ȯ��");
		duplicate_check_btn.setFont(new Font("���ʷҵ���", Font.BOLD, 12));
		duplicate_check_btn.setBackground(Color.DARK_GRAY);
		duplicate_check_btn.setForeground(Color.WHITE);
		duplicate_check_btn.setBounds(230, 17, 90, 29);
		duplicate_check_btn.addActionListener(new BtnEventListener());
		add(duplicate_check_btn);
		
		/*��й�ȣ ��� �� ����*/
		collect_pw_label = new JLabel();
		collect_pw_label.setFont(new Font("���ʷҵ���", Font.BOLD, 12));
		collect_pw_label.setForeground(Color.RED);
		collect_pw_label.setBounds(230, 95, 90, 30);
		add(collect_pw_label);
		
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
				
				switch(CheckRegister()) {
					case 1:
						JOptionPane.showMessageDialog(null, "�ߺ�Ȯ���� ���ּ���.");
						break;
					case 2:
						JOptionPane.showMessageDialog(null, "��й�ȣ�� Ȯ�� �� �ּ���.");
						break;
					case 3:
						JOptionPane.showMessageDialog(null, "��й�ȣ�� 6�� �̻����� ���ּ���.");
						break;
					case 4:
						JOptionPane.showMessageDialog(null, "��ĭ�� �ֽ��ϴ�.");
						break;
					case 0:
						set.RegisterAccount(textField[0].getText(), textField[1].getText(), textField[3].getText(), textField[4].getText());
						JOptionPane.showMessageDialog(null, "ȸ�����ԵǼ̽��ϴ�.");
						dispose();	
						break;			
				}
				break;
			case "���":
				dispose();
				break;
			}
		}
		
	}

	/*���̵� ���� Ȯ�� �̺�Ʈ ������*/
	private class IDEventListener implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			check_duplication = false;
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			check_duplication = false;
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			check_duplication = false;
		}
		
	}
	
	/*��й�ȣ Ȯ�� �̺�Ʈ ������*/
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
	
	/*�ִ� �Է� �� ���� �̺�Ʈ ������*/
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
			case 0: case 1: case 2: case 3:
				max_length = 20;
				break;
			case 4:
				max_length = 8;
				break;
			}
			
			if (text_field.getText().length() >= max_length) e.consume();
		}
	}
	
	/*���� �� �˻�*/
	private int CheckRegister() {
		
		if (!check_duplication)
			return 1;
		
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
	
	/*��й�ȣ ��� ���*/
	private void PrintPWWarning() {
		String pw1 = textField[1].getText();
		String pw2 = textField[2].getText();
		if (pw1.equals(pw2))
			collect_pw_label.setText("");
		else 
			collect_pw_label.setText("���� �ٸ��ϴ�");
	}
}
