package main;

import java.awt.Container;

import javax.swing.JFrame;

import back.Set;
import front.MainPanel;
import front.StartPanel;

public class MainFrame extends JFrame {

	private StartPanel start_panel;	// ù ȭ��(�α��� ȭ��)
	private MainPanel main_panel;	// ���� ȭ��
	
	private Container c;
	private static Set set = new Set();
	
	public MainFrame() {
		
		setTitle("����Ʈ�� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c = getContentPane();
		
		start_panel = new StartPanel(this, set);
		
		add(start_panel);
		
		//add(main_panel);
		//setSize(1280, 720);
		
		setSize(300, 140);
		setResizable(false);				// âũ�� ���� ����
		setLocationRelativeTo(null);		// ȭ�� �߾� ��ġ
		setVisible(true);
	}
	
	/*ȭ�� ��ȯ*/
	public void ChangePanel(String panelName) {
		if (panelName.equals("Start")) {
			c.removeAll();
			setSize(300, 140);
			setLocationRelativeTo(null);
			add(new StartPanel(this, set));
			revalidate();
			repaint();
		} else if (panelName.contentEquals("Main")) {
			c.removeAll();
			setSize(1280, 720);
			setLocationRelativeTo(null);
			add(new MainPanel(this, set));
			revalidate();
			repaint();
		}
	}
	
	public static void main(String args[]) {
		
		new MainFrame();
	}
}
