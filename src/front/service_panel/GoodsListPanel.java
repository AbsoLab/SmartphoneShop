package front.service_panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import back.Set;
import main.MainFrame;
import structure.Goods;

public class GoodsListPanel extends JPanel{
	
	private JPanel category_panel;
	private JButton [] category_button;
	private JPanel goods_list_panel;
	private PagePanel page_panel;
	
	private MainFrame mf;
	private Set set;
	
	private Goods [] goods_list;
	
	public GoodsListPanel(MainFrame mf, Set set) {
		
		this.mf = mf;
		this.set = set;
		
		setLayout(null);
		
		/*��ǰ ī�װ� ���� �г�*/
		category_panel = new JPanel();
		category_panel.setLayout(null);
		category_panel.setBounds(0, 0, 150, 670);
		category_panel.setBackground(new Color(199, 199, 199));
		add(category_panel);
		
		/*��ǰ ī�װ� ���� ��ư*/
		String [] button_name = {"��ü", "����Ʈ��", "�Ǽ��縮", "��Ÿ"};
		category_button = new JButton[4];
		for (int i=0; i<4; ++i) {
			category_button[i] = new JButton(button_name[i]);
			category_button[i].setFont(new Font("���� ���", Font.BOLD, 20));
			category_button[i].setBackground(Color.WHITE);
			category_button[i].setBounds(0, 100 + 70*i, 150, 70);
			category_button[i].addActionListener(new CategoryButtonListener());
			category_panel.add(category_button[i]);
		}
		
		/*��ǰ ��� �г�*/
		goods_list_panel = new JPanel();
		goods_list_panel.setLayout(null);
		goods_list_panel.setBounds(250, 100, 910, 370);	// width = 660 height = 270
		goods_list_panel.setBackground(Color.WHITE);
		add(goods_list_panel);
		
		/*������ �г�*/
		page_panel = new PagePanel();
		page_panel.setBounds(250, 480, 910, 20);
		add(page_panel);

		/*�ʱ�ȭ*/
		show_goods_list_page(0, 0);
	}
	
	/*��ǰ �г�*/
	class GoodsPanel extends JPanel {
		
		private Image goods_image;
		private JPanel goods_name_panel;
		private JLabel goods_name_label;
		
		private MainFrame mf;
		private Goods goods;
		
		public GoodsPanel(MainFrame mf, Goods goods, int index) {
			
			this.mf = mf;
			this.goods = goods;
			
			setLayout(null);
			
			goods_name_panel = new JPanel();
			goods_name_panel.setBounds(10, 300, 170, 35);
			
			/*��ǰ �̸� ���*/
			goods_name_label = new JLabel(goods.get_name());
			goods_name_panel.add(goods_name_label);
			add(goods_name_panel);
						
			addMouseListener(new MouseActionListener());
			setBounds(10 + index % 4 * 230, 10, 200, 350);	// width = 190, height = 340
			setBackground(Color.WHITE);
			setBorder(new LineBorder(Color.BLACK));	
		}
		
		/*���콺 �׼� �̺�������*/
		class MouseActionListener extends MouseAdapter {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				JPanel panel = (JPanel)e.getSource();
				
				panel.setBorder(new LineBorder(new Color(109, 109, 255), 3));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				JPanel panel = (JPanel)e.getSource();
				
				panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
			
		}
	}
	
	/*������ �г�*/
	class PagePanel extends JPanel {
		
		JLabel [] page_num;
		
		public PagePanel() {
			
			setBackground(Color.black);
		}
		
		public void update_page(int total_page) {
			
			removeAll();
			
			/*10���� �Ѿ ��� �� �����غ���*/
			page_num = new JLabel[total_page];
			
			for (int i=0; i<total_page; ++i) {
				page_num[i] = new JLabel(Integer.toString(i+1));
				add(page_num[i]);
			}
			
			revalidate();
			repaint();
		}
	}
	
	/*ī�װ� ��ư �̺�Ʈ ������*/
	class CategoryButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton btn = (JButton)e.getSource();
			
			switch(btn.getText()) {
			
			case "��ü":
				show_goods_list_page(0, 0);
				break;
			case "����Ʈ��":
				show_goods_list_page(1, 0);
				break;
			case "�Ǽ��縮":
				show_goods_list_page(2, 0);
				break;
			case "��Ÿ":
				show_goods_list_page(3, 0);
				break;
				
			}
		}
	}
	
	/*��ǰ ��� ����*/
	public void show_goods_list_page(int select, int page) {
		
		goods_list = set.GetGoodsList();
		
		goods_list_panel.removeAll();
		
		if (select == 0) {
			
			page_panel.update_page(goods_list.length);
			
			for (int i=0; i<4; ++i) {
				
				try {
					
					goods_list_panel.add(new GoodsPanel(mf, goods_list[i + page*4], i));
					
				} catch (ArrayIndexOutOfBoundsException e) { break; }
			}
		}
		
		goods_list_panel.revalidate();
		goods_list_panel.repaint();
	}
}
