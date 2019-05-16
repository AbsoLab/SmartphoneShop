package front.service_panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import back.Set;
import main.MainFrame;
import structure.Goods;
import structure.Smartphone;

public class GoodsInfoFrame extends JDialog {
	
	private JPanel image_panel;
	private JPanel goods_info_panel;
	private JLabel show_detailed_spec_label;
	private JButton [] button;
	
	private MainFrame mf;
	private Set set;
	private Goods goods;
	
	public GoodsInfoFrame(MainFrame mf, Set set, Goods goods) {
		super(mf, true);
		this.mf = mf;
		this.set = set;
		this.goods = goods;
		
		setTitle(goods.get_name() + "�� ����");
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		/*�̹��� �г�*/
		image_panel = new JPanel();
		image_panel.setBounds(20, 20, 240, 320);
		image_panel.setBorder(new LineBorder(Color.BLACK));
		add(image_panel);

		/*�⺻ ���� �г�*/
		goods_info_panel = new BasicInformationPanel(goods);
		goods_info_panel.setBackground(Color.WHITE);
		goods_info_panel.setBounds(280, 20, 400, 320);
		goods_info_panel.setBorder(new LineBorder(Color.BLACK));
		add(goods_info_panel);
		
		/*�� ���� ��ư*/
		if (goods.is_smartphone()) {
			show_detailed_spec_label = new JLabel("�� ����");
			show_detailed_spec_label.setHorizontalAlignment(JLabel.CENTER);
			show_detailed_spec_label.setBorder(new LineBorder(new Color(109, 109, 255), 2));
			show_detailed_spec_label.setBounds(620, 340, 60, 20);
			show_detailed_spec_label.addMouseListener(new MouseActionListener());
			add(show_detailed_spec_label);
		}
		
		/*���� ��ư ����*/
		String [] button_name = {"��ٱ��� ���", "�ݱ�"};
		button = new JButton[button_name.length];
		for (int i=0; i<button.length; ++i	 ) {
			button[i] = new JButton(button_name[i]);
			button[i].setBackground(Color.WHITE);
			button[i].setFont(new Font("���� ���", Font.BOLD, 15));
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
	
	/*�⺻ ���� �г�*/
	class BasicInformationPanel extends JPanel {
		
		private JLabel [] left_label;
		private JLabel [] right_label;
		
		public BasicInformationPanel(Goods goods) {
			
			setLayout(null);
			
			/*���� ���*/
			String [] name = {"��ǰ��", "����", "�� ����"};
			left_label = new JLabel[3];
			for (int i=0; i<3; ++i) {
				left_label[i] = new JLabel(name[i]);
				left_label[i].setFont(new Font("���� ���", Font.BOLD, 15));
				left_label[i].setBounds(20, 30 + 50 * i, 80, 30);
				add(left_label[i]);
			}
			
			/*������ ���*/
			right_label = new JLabel[3];
			right_label[0] = new JLabel(goods.get_name());
			right_label[1] = new JLabel(String.valueOf(goods.get_price()));
			right_label[2] = new JLabel(goods.get_explanation());
			for (int i=0; i<3; ++i) {
				right_label[i].setFont(new Font("���� ���", Font.BOLD, 15));
				right_label[i].setBounds(120, 30 + 50 * i, 300, 30);
				add(right_label[i]);
			}
		}
	
		/*�� �߱�*/
		@Override
		public void paintComponent(Graphics g) {
			g.setColor(new Color(109, 109, 109));
			g.drawLine(20, 70, 380, 70);
			g.drawLine(20, 120, 380, 120);
		}
	}
	
	/*�� ���� �г�*/
	class DetailedSpecificationFrame extends JDialog {
		
		private JLabel [] left_label;
		private JLabel [] right_label;
		
		public DetailedSpecificationFrame(MainFrame mf, Smartphone goods) {
			
			super(mf, true);
			
			setLayout(null);
			
			String [] label_name = {"CPU", "���÷���", "���͸� �뷮", "RAM", "ROM", "������", "��� ��¥"};
			left_label = new JLabel[label_name.length];
			for (int i=0; i<left_label.length; ++i) {
				left_label[i] = new JLabel(label_name[i]);
				left_label[i].setFont(new Font("���� ���", Font.BOLD, 13));
				left_label[i].setBounds(20, 20 + 30 * i, 90, 30);
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
				right_label[i].setFont(new Font("���� ���", Font.BOLD, 13));
				right_label[i].setBounds(120, 20 + 30 * i, 120, 30);
				add(right_label[i]);
			}
			
			setResizable(false);
			setSize(320, 320);
			setLocationRelativeTo(mf);
			setVisible(true);
		}
	}
	
	/*�� ���� �г� ��¿� �̺�Ʈ ������*/
	class MouseActionListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub	
			if (goods.is_smartphone())
				new DetailedSpecificationFrame(mf, (Smartphone)goods);
		}
	}

	/*��ư �̺�Ʈ ������*/
	class ButtonEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			JButton btn = (JButton)e.getSource();
			switch(btn.getText()) {
			case "��ٱ��� ���":
				set.AddKart(goods.get_goods_id(), 1);
				break;
			case "�ݱ�":
				dispose();
				break;
			}
		}
		
	}
}
