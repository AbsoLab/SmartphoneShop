package front;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import back.Set;
import main.MainFrame;
import structure.Goods;
import structure.Smartphone;

public class AddGoodsFrame extends JDialog{
	
	JLabel [] goods_info_name_label;
	JTextField [] goods_info_value_textField;
	JButton img_input_button;
	JRadioButton [] goods_category_radio_button;
	ButtonGroup category_button_group;
	JTextArea goods_explanation_textArea;
	
	JLabel [] smartphone_info_name_label;
	JTextField [] smartphone_info_value_textField;
	JLabel guide_label;
	JLabel [] unit_label;
	
	JButton [] button;

	Goods goods;
	int selected_category = -1;
	
	private Set set;
	
	public AddGoodsFrame(MainFrame mf, Set set) {
		super(mf, true);
		
		this.set = set;
		
		setTitle("상품 추가");
		setLayout(null);
		
		/*상품 정보 라벨*/
		String [] goods_name = {"제품명", "이미지", "가격", "카테고리", "설명"};
		goods_info_name_label = new JLabel[goods_name.length];
		for (int i=0; i<goods_name.length; ++i) {
			goods_info_name_label[i] = new JLabel(goods_name[i]);
			goods_info_name_label[i].setFont(new Font("함초롬돋움", Font.BOLD, 13));
			goods_info_name_label[i].setBounds(10, 10+30*i, 70, 30);
			add(goods_info_name_label[i]);
		}
		
		/*상품 정보 입력 칸*/
		goods_info_value_textField = new JTextField[3];
		for (int i=0; i<goods_info_value_textField.length; ++i) {
			goods_info_value_textField[i] = new JTextField();
			goods_info_value_textField[i].setFont(new Font("함초롬돋움", Font.BOLD, 13));
			goods_info_value_textField[i].setLocation(110, 12+30*i);
			add(goods_info_value_textField[i]);
		}
		goods_info_value_textField[0].setSize(200, 25);
		goods_info_value_textField[1].setSize(70, 25);
		goods_info_value_textField[2].setSize(70, 25);
		
		goods_info_value_textField[2].addKeyListener(new LimitKeyInputListener());
		
		/*이미지 첨부 버튼 생성*/
		img_input_button = new JButton("첨부");
		img_input_button.setFont(new Font("함초롬돋움", Font.BOLD, 13));
		img_input_button.setMargin(new Insets(0, 0, 0, 0));
		img_input_button.setBackground(Color.WHITE);
		img_input_button.setBounds(200, 43, 50, 24);
		img_input_button.addActionListener(new ButtonEventListener());
		add(img_input_button);
		
		/*카테고리 선택 라디오버튼*/
		String [] radio_button_name = {"스마트폰", "악세사리", "기타"};
		category_button_group = new ButtonGroup();
		goods_category_radio_button = new JRadioButton[3];
		for (int i=0; i<goods_category_radio_button.length; ++i) {
			goods_category_radio_button[i] = new JRadioButton(radio_button_name[i]);
			goods_category_radio_button[i].setFont(new Font("함초롬돋움", Font.BOLD, 13));
			goods_category_radio_button[i].setBounds(110 + 90*i, 100, 80, 30);
			goods_category_radio_button[i].addActionListener(new RadioButtonEventListener());
			category_button_group.add(goods_category_radio_button[i]);
			add(goods_category_radio_button[i]);
		}
		
		/*설명 입력칸 생성*/
		goods_explanation_textArea = new JTextArea();
		goods_explanation_textArea.setFont(new Font("함초롬돋움", Font.BOLD, 13));
		goods_explanation_textArea.setBounds(110, 130, 300, 90);
		goods_explanation_textArea.setLineWrap(true);
		goods_explanation_textArea.setBorder(new LineBorder(Color.BLACK));
		add(goods_explanation_textArea);
		
		/*스마트폰 정보 이름 라벨*/
		String [] smartphone_info_name = {"제조사", "제조일자", "CPU", "Display", "Battery", "RAM", "ROM"};
		smartphone_info_name_label = new JLabel[smartphone_info_name.length];
		for (int i=0; i<smartphone_info_name.length; ++i) {
			smartphone_info_name_label[i] = new JLabel(smartphone_info_name[i]);
			smartphone_info_name_label[i].setFont(new Font("함초롬돋움", Font.BOLD, 13));
			smartphone_info_name_label[i].setBounds(10, 220 + 30*i, 80, 30);
			add(smartphone_info_name_label[i]);
		}
		
		/*스마트폰 정보 입력칸 생성*/
		smartphone_info_value_textField = new JTextField[smartphone_info_name.length];
		for (int i=0; i<smartphone_info_name.length; ++i) {
			smartphone_info_value_textField[i] = new JTextField();
			smartphone_info_value_textField[i].setFont(new Font("함초롬돋움", Font.BOLD, 13));
			smartphone_info_value_textField[i].setLocation(110, 223 + 30*i);
			add(smartphone_info_value_textField[i]);
		}
		smartphone_info_value_textField[0].setSize(200, 24);
		smartphone_info_value_textField[1].setSize(70, 24);
		smartphone_info_value_textField[2].setSize(200, 24);
		smartphone_info_value_textField[3].setSize(200, 24);
		smartphone_info_value_textField[4].setSize(70, 24);
		smartphone_info_value_textField[5].setSize(70, 24);
		smartphone_info_value_textField[6].setSize(70, 24);
		
		smartphone_info_value_textField[1].setHorizontalAlignment(JTextField.CENTER);
		smartphone_info_value_textField[4].setHorizontalAlignment(JTextField.CENTER);
		smartphone_info_value_textField[5].setHorizontalAlignment(JTextField.CENTER);
		smartphone_info_value_textField[6].setHorizontalAlignment(JTextField.CENTER);
		
		smartphone_info_value_textField[1].addKeyListener(new LimitKeyInputListener());
		smartphone_info_value_textField[4].addKeyListener(new LimitKeyInputListener());
		smartphone_info_value_textField[5].addKeyListener(new LimitKeyInputListener());
		smartphone_info_value_textField[6].addKeyListener(new LimitKeyInputListener());
		
		/*생산년도 안내*/
		guide_label = new JLabel("YYYYMMDD");
		guide_label.setFont(new Font("함초롬돋움", Font.BOLD, 11));
		guide_label.setForeground(Color.RED);
		guide_label.setBounds(190, 255, 70, 20);
		add(guide_label);
		
		/*단위 출력*/
		String [] unit_str = {"mAh", "GB", "GB"};
		unit_label = new JLabel[3];
		for (int i=0; i<3; ++i) {
			unit_label[i] = new JLabel(unit_str[i]);
			unit_label[i].setFont(new Font("함초롬돋움", Font.BOLD, 13));
			unit_label[i].setBounds(185, 340 + 30*i, 50, 30);
			add(unit_label[i]);
		}
		
		/*버튼 생성*/
		String [] button_name = {"추가", "취소"};
		button = new JButton[button_name.length];
		for (int i=0; i<button_name.length; ++i) {
			button[i] = new JButton(button_name[i]);
			button[i].setFont(new Font("함초롬돋움", Font.BOLD, 13));
			button[i].setBackground(Color.WHITE);
			button[i].setBounds(250 + 90*i, 430, 70, 25);
			button[i].addActionListener(new ButtonEventListener());
			add(button[i]);
		}
		
		/*
		Image img = set.GetImage("tmp_name");
		Image resizeImage = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		JLabel label = new JLabel(new ImageIcon(resizeImage));
		label.setBounds(40, 40, 100, 100);
		add(label);
		*/
		
		setResizable(false);
		setSize(440,500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	
	/*라디오 버튼 이벤트*/
	class RadioButtonEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JRadioButton btn = (JRadioButton)e.getSource();
			switch(btn.getText()) {
			case "스마트폰":
				selected_category = 0;
				lock_textField(true);
				break;
			case "악세사리":
				selected_category = 1;
				lock_textField(false);
				break;
			case "기타":
				selected_category = 2;
				lock_textField(false);
				break;
			}
		}
	}
	
	/*버튼 이벤트*/
	class ButtonEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton btn = (JButton)e.getSource();
			switch(btn.getText()) {
			case "첨부":
				open_file();
				break;
			case "추가":
				generate_goods();
				add_func();
				break;
			case "취소":
				dispose();
				break;
			}
		}
		
	}
	
	/*키 입력 제한*/
	class LimitKeyInputListener extends KeyAdapter {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			char key = e.getKeyChar();
			if (key < '0' || key > '9')
				e.consume();
			
		}	
	}
	
	/*스마트폰 정보 입력 허용-잠금*/
	private void lock_textField(boolean bool) {
		for (int i=0; i<smartphone_info_value_textField.length; ++i) {
			smartphone_info_value_textField[i].setEnabled(bool);
		}
	}
	
	/*textField값을 기반으로 객체 생성*/
	private void generate_goods() {
		JRadioButton btn = goods_category_radio_button[selected_category];
		if (btn.getText().equals("스마트폰")) {
			goods = new Smartphone(goods_info_value_textField[0].getText(), Integer.valueOf(goods_info_value_textField[2].getText()), 1, goods_explanation_textArea.getText());
			((Smartphone)goods).set_manufacturing_compay(smartphone_info_value_textField[0].getText());
			((Smartphone)goods).set_release_date(smartphone_info_value_textField[1].getText());
			((Smartphone)goods).set_spec(smartphone_info_value_textField[2].getText(), smartphone_info_value_textField[3].getText(), smartphone_info_value_textField[4].getText()+"mAh", smartphone_info_value_textField[5].getText()+"GB", smartphone_info_value_textField[6].getText()+"GB");
		} else {
			goods = new Goods(goods_info_value_textField[0].getText(), Integer.valueOf(goods_info_value_textField[2].getText()), selected_category, goods_explanation_textArea.getText());		
		}
	}
	
	/*파일 첨부 버튼 클릭*/
	private void open_file() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new FileNameExtensionFilter("jpg", "jpeg", "png"));
        if(jfc.showSaveDialog(null) == 0) {
            goods_info_value_textField[1].setText(jfc.getSelectedFile().toString());
        }
	}

	
	private void add_func() {
		System.out.println(goods);
	}
}
