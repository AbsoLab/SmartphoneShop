package main;

import java.awt.Container;

import javax.swing.JFrame;

import back.Set;
import front.MainPanel;
import front.StartPanel;

public class MainFrame extends JFrame {

	private StartPanel start_panel;	// 첫 화면(로그인 화면)
	private MainPanel main_panel;	// 메인 화면
	
	private Container c;
	private static Set set = new Set();
	
	public MainFrame() {
		
		setTitle("스마트폰 쇼핑");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c = getContentPane();
		
		start_panel = new StartPanel(this, set);
		
		add(start_panel);
		
		//add(main_panel);
		//setSize(1280, 720);
		
		setSize(300, 140);
		setResizable(false);				// 창크기 조절 금지
		setLocationRelativeTo(null);		// 화면 중앙 위치
		setVisible(true);
	}
	
	/*화면 전환*/
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
