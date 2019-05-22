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
	}
	
	/*���� ���� �г�*/
	class SelectServicePanel extends JPanel {
		
		private JButton [] button;
		
		private MainFrame mf;
		private Set set;
		
		public SelectServicePanel(MainFrame mf, Set set) {
		
			this.mf = mf;
			this.set = set;
			
			setLayout(new GridLayout(1,6, 2, 0));
			setBackground(Color.white);
			
			
			/*��ư ����*/
			String [] button_name = {"��ǰ���", "��ٱ���", "�ֹ����", "������", "������", "�α׾ƿ�"};
			button = new JButton[6];
			for (int i=0; i<6; ++i) {
				button[i] = new JButton(button_name[i]);
				button[i].setFont(new Font("���� ���", Font.BOLD, 20));
				button[i].setBackground(new Color(255, 255, 255));
				button[i].setForeground(new Color(109, 109, 109));
				button[i].setMargin(new Insets(0, 0, 0, 0));
				button[i].addActionListener(new ButtonEventListener());
				add(button[i]);
			}
			button[5].setBackground(new Color(255, 207, 207));
		}	
	}

	/*���� ���� ��ư �̺�Ʈ ������*/
	class ButtonEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			JButton btn = (JButton)e.getSource();
			
			service_panel.removeAll();
			
			switch(btn.getText()) {
			
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
				break;
			}
			
			service_panel.revalidate();
			service_panel.repaint();
		}
	}
}
