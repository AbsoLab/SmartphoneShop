package front.service_panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import back.Set;
import main.MainFrame;
import structure.Goods;
import structure.Smartphone;

/*상품 클릭시 뜨는 상품정보 다이얼로그 입니다.*/
public class GoodsInfoFrame extends JDialog {
	
	private Image img;
	private JLabel image_label;
	private JPanel goods_info_panel;
	private JLabel show_detailed_spec_label;
	private JButton delete_button;
	private JButton [] button;
	
	private MainFrame mf;
	private Set set;
	private Goods goods;
	
	public GoodsInfoFrame(MainFrame mf, Set set, Goods goods) {
		super(mf, true);
		this.mf = mf;
		this.set = set;
		this.goods = goods;
		
		setTitle(goods.get_name() + "의 정보");
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		/*상품 이미지 출력*/
		img = set.GetImage(goods.get_name());
		img = img.getScaledInstance(180, 250, Image.SCALE_SMOOTH);
		
		image_label = new JLabel(new ImageIcon(img));
		image_label.setBounds(20, 20, 240, 320);
		add(image_label);
		
		/*기본 정보 패널*/
		goods_info_panel = new BasicInformationPanel(goods);
		goods_info_panel.setBackground(Color.WHITE);
		goods_info_panel.setBounds(280, 20, 400, 320);
		goods_info_panel.setBorder(new LineBorder(Color.BLACK));
		add(goods_info_panel);
		
		/*상세 스펙 버튼*/
		if (goods.get_category() == 0) {
			show_detailed_spec_label = new JLabel("상세 정보");
			show_detailed_spec_label.setHorizontalAlignment(JLabel.CENTER);
			show_detailed_spec_label.setBorder(new LineBorder(new Color(109, 109, 255), 2));
			show_detailed_spec_label.setBounds(620, 340, 60, 20);
			show_detailed_spec_label.addMouseListener(new MouseActionListener());
			add(show_detailed_spec_label);
		}
		
		/*상품 삭제 버튼*/
		if (set.is_admin()) {
			delete_button = new JButton("상품삭제");
			delete_button.setBackground(new Color(255, 109, 109));
			delete_button.setForeground(Color.WHITE);
			delete_button.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			delete_button.setBounds(20, 400, 120, 30);
			delete_button.addActionListener(new ButtonEventListener());
			add(delete_button);
		}
		
		/*여러 버튼 생성*/
		String [] button_name = {"장바구니 담기", "닫기"};
		button = new JButton[button_name.length];
		for (int i=0; i<button.length; ++i	 ) {
			button[i] = new JButton(button_name[i]);
			button[i].setBackground(Color.WHITE);
			button[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
			button[i].setMargin(new Insets(0, 0, 0, 0));
			button[i].setBounds(430 + 130*i, 400, 120, 30);
			button[i].addActionListener(new ButtonEventListener());
			add(button[i]);
		}
		
		setResizable(false);
		setSize(720, 480);
		setLocationRelativeTo(mf);
		setVisible(true);
	}
	
	/*기본 정보 패널*/
	private class BasicInformationPanel extends JPanel {
		
		private JLabel [] left_label;
		private JLabel [] right_label;
		private JTextArea explanation_textField;
		
		public BasicInformationPanel(Goods goods) {
			
			setLayout(null);
			
			/*왼쪽 출력*/
			String [] name = {"제품명", "가격", "상세 설명"};
			left_label = new JLabel[3];
			for (int i=0; i<3; ++i) {
				left_label[i] = new JLabel(name[i]);
				left_label[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
				left_label[i].setBounds(20, 30 + 50 * i, 80, 30);
				add(left_label[i]);
			}
			
			/*오른쪽 출력*/
			right_label = new JLabel[3];
			right_label[0] = new JLabel(goods.get_name());
			right_label[1] = new JLabel(String.valueOf(goods.get_price()));
			for (int i=0; i<2; ++i) {
				right_label[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
				right_label[i].setBounds(120, 30 + 50 * i, 300, 30);
				add(right_label[i]);
			}
			
			/*상품 설명 출력*/
			explanation_textField = new JTextArea();
			explanation_textField.setText(goods.get_explanation());
			explanation_textField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			explanation_textField.setEditable(false);
			explanation_textField.setBackground(Color.WHITE);
			explanation_textField.setBorder(null);
			explanation_textField.setLineWrap(true);
			explanation_textField.setBounds(120, 135, 250, 150);
			add(explanation_textField);
		}
	
		/*선 긋기*/
		@Override
		public void paintComponent(Graphics g) {
			g.setColor(new Color(109, 109, 109));
			g.drawLine(20, 70, 380, 70);
			g.drawLine(20, 120, 380, 120);
		}
	}
	
	/*상세 정보 패널*/
	private class DetailedSpecificationFrame extends JDialog {
		
		private JLabel [] left_label;
		private JLabel [] right_label;
		
		public DetailedSpecificationFrame(MainFrame mf, Smartphone goods) {
			
			super(mf, true);
			
			setLayout(null);
			
			String [] label_name = {"CPU", "디스플레이", "배터리 용량", "RAM", "ROM", "제조사", "출시 날짜"};
			left_label = new JLabel[label_name.length];
			for (int i=0; i<left_label.length; ++i) {
				left_label[i] = new JLabel(label_name[i]);
				left_label[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
				left_label[i].setBounds(20, 20 + 40 * i, 90, 30);
				add(left_label[i]);
			}
			
			right_label = new JLabel[left_label.length];
			right_label[0] = new JLabel(goods.get_spec().cpu);
			right_label[1] = new JLabel(goods.get_spec().display);
			right_label[2] = new JLabel(goods.get_spec().battery_size);
			right_label[3] = new JLabel(goods.get_spec().RAM);
			right_label[4] = new JLabel(goods.get_spec().ROM);
			right_label[5] = new JLabel(goods.get_manufacturing_company());
			right_label[6] = new JLabel(goods.get_release_date());
			
			for (int i=0; i<right_label.length; ++i) {
				right_label[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
				right_label[i].setBounds(130, 20 + 40 * i, 580, 30);
				add(right_label[i]);
			}
			
			setResizable(false);
			setSize(660, 360);
			setLocationRelativeTo(mf);
			setVisible(true);
		}
	}
	
	/*상세 정보 패널 출력용 이벤트 리스너*/
	private class MouseActionListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub	
			if (goods.get_category() == 0)
				new DetailedSpecificationFrame(mf, (Smartphone)goods);
		}
	}

	/*버튼 이벤트 리스너*/
	private class ButtonEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			JButton btn = (JButton)e.getSource();
			switch(btn.getText()) {
			case "상품삭제":
				set.DeleteGoods(goods);
				dispose();
				break;
			case "장바구니 담기":
				set.AddKart(goods);					
				dispose();
				JOptionPane.showMessageDialog(null, "장바구니에 담았습니다.");
				break;
			case "닫기":
				dispose();
				break;
			}
		}
		
	}
}
