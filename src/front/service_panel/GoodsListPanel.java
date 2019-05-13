package front.service_panel;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	
	private MainFrame mf;
	private Set set;
	
	
	public GoodsListPanel(MainFrame mf, Set set) {
		
		this.mf = mf;
		this.set = set;
		
		setBackground(new Color(123, 0, 0));
		
		/*상품 카테고리 선택 패널*/
		category_panel = new JPanel();
		category_panel.setLayout(null);
		category_panel.setBounds(0, 0, 150, 670);
		category_panel.setBackground(new Color(109, 177, 109));
		add(category_panel);
		
		/*상품 카테고리 선택 버튼*/
		String [] button_name = {"전체", "스마트폰", "악세사리", "기타"};
		category_button = new JButton[4];
		for (int i=0; i<4; ++i) {
			category_button[i] = new JButton(button_name[i]);
			category_button[i].setBounds(0, 100 + 70*i, 150, 70);
			category_button[i].addActionListener(new CategoryButtonListener());
			category_panel.add(category_button[i]);
		}
		
		
	}	

	/*상품 패널*/
	class GoodsPanel extends JPanel {
		
		private Image goods_image;
		private JPanel goods_name_panel;
		private JLabel goods_name_label;
		
		private MainFrame mf;
		private Goods goods;
		
		public GoodsPanel(MainFrame mf, Goods goods) {
			
			this.mf = mf;
			this.goods = goods;
			setLayout(null);
			
			goods_name_panel = new JPanel();
			goods_name_label = new JLabel(goods.get_name());
			goods_name_panel.add(goods_name_label);
			
			setBorder(new LineBorder(Color.BLACK));	
		}
		
		/*마우스 액션 이벤리스터*/
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
	
	class CategoryButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton btn = (JButton)e.getSource();
			
			switch(btn.getText()) {
			
			case "전체":
				break;
			case "스마트폰":
				break;
			case "악세사리":
				break;
			case "기타":
				break;
				
			}
		}
	}
}
