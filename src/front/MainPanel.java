package front;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import back.Set;
import front.service_panel.GoodsListPanel;
import front.service_panel.KartListPanel;
import front.service_panel.OrderListPanel;
import main.MainFrame;

/*�α��� ���� �� ù ȭ���Դϴ�.*/
public class MainPanel extends JPanel {
	
	private SelectServicePanel select_service_panel;
	private JPanel service_panel;
	
	private MainFrame mf;
	private Set set;
	
	public MainPanel(MainFrame mf, Set set) {
		
		this.mf = mf;
		this.set = set;
		
		setLayout(null);
		
		/*���� ���� �г�*/
		select_service_panel = new SelectServicePanel(mf, set);
		select_service_panel.setBounds(0, 1, 1280, 40);
		add(select_service_panel);
		
		/*���� �г�*/
		service_panel = new JPanel();
		service_panel.setLayout(new GridLayout(1,1));
		service_panel.setBackground(new Color(255, 255, 255));
		service_panel.setBounds(0, 41, 1280, 679);
		add(service_panel);
		
		service_panel.add(new GoodsListPanel(mf, set));
	}
	
	/*���� ���� �г�*/
	class SelectServicePanel extends JPanel {
		
		private JButton [] button;
		
		public SelectServicePanel(MainFrame mf, Set set) {
				
			setLayout(new GridLayout(1,6, 2, 0));
			setBackground(Color.white);
			
			/*��ư ����*/
			String [] button_name = {"��ǰ���", "��ٱ���", "�ֹ����", "������", "�α׾ƿ�"};
			button = new JButton[button_name.length];
			for (int i=0; i<button_name.length; ++i) {
				button[i] = new JButton(button_name[i]);
				button[i].setFont(new Font("���� ���", Font.BOLD, 20));
				button[i].setBackground(new Color(255, 255, 255));
				button[i].setForeground(new Color(109, 109, 109));
				button[i].setMargin(new Insets(0, 0, 0, 0));
				button[i].addActionListener(new ButtonEventListener());
				add(button[i]);
			}
			button[4].setBackground(new Color(255, 207, 207));
		}	
	}

	/*���� ���� ��ư �̺�Ʈ ������*/
	class ButtonEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			JButton btn = (JButton)e.getSource();
			String text = btn.getText();
			
			if (text.equals("������")) {
				new ChangeAccountInfoFrame(mf, set);
				return;
			}
			
			service_panel.removeAll();
			
			switch(text) {
			
			case "��ǰ���":
				service_panel.add(new GoodsListPanel(mf, set));
				break;
				
			case "��ٱ���":
				service_panel.add(new KartListPanel(mf, set));
				break;
				
			case "�ֹ����":
				service_panel.add(new OrderListPanel(mf, set));
				break;
				
			case "�α׾ƿ�":
				set.Logout();
				mf.ChangePanel("Start");
				break;
			}
			
			service_panel.revalidate();
			service_panel.repaint();
		}
	}
}
