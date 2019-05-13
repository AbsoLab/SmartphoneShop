package front;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import back.Set;
import main.MainFrame;

public class StartPanel extends JPanel {

	private JLabel[] label;
	private JTextField[] textField;
	private JButton[] button;
	
	private Vector<Component> order;	// 탭 순서 지정
	
	private MainFrame mf;
	private Container c;
	private Set set;
	
	
	public StartPanel(MainFrame mf, Set set) {
		
		this.mf = mf;
		this.c = getParent();
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
			add(button[i]);
		}
		
		/*탭 순서 지정*/
		order = new Vector<Component>(4);
		order.add(textField[0]);
		order.add(textField[1]);
		order.add(button[0]);
		order.add(button[1]);
		
		new MyFocusTraversalPolicy(order);
		
	}
	
	/*탭 순서 지정*/
	private class MyFocusTraversalPolicy extends FocusTraversalPolicy {

		private Vector<Component> order;
		
		public MyFocusTraversalPolicy(Vector<Component> order) {
			this.order = new Vector<Component>(order.size());
			this.order.addAll(order);
		}
				
		@Override
		public Component getComponentAfter(Container aContainer, Component aComponent) {
			// TODO Auto-generated method stub
			int idx = (order.indexOf(aComponent) + 1) % order.size();
			return order.get(idx);
		}
		
		@Override
		public Component getComponentBefore(Container aContainer, Component aComponent) {
			// TODO Auto-generated method stub
			int idx = order.indexOf(aComponent) - 1;
			if (idx < 0) {
				idx = order.size() - 1;
			}
			return order.get(idx);
		}

		@Override
		public Component getFirstComponent(Container aContainer) {
			// TODO Auto-generated method stub
			return order.get(0);
		}

		@Override
		public Component getLastComponent(Container aContainer) {
			// TODO Auto-generated method stub
			return order.lastElement();
		}

		@Override
		public Component getDefaultComponent(Container aContainer) {
			// TODO Auto-generated method stub
			return order.get(0);
		}
		
	}
	
	/*이벤트 리스너*/
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
					textField[0].setText("");
					textField[1].setText("");
					mf.ChangePanel("Main");
				} else {
					JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호를 잘못 입력하셨습니다.");
				}
				
				break;
				
			case "Register":
				new RegisterAccountFrame(set, c);
				
				break;			
			}				
		}
	}
}
