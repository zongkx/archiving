package test;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;

public class MyJFrame {

	 JFrame frame=new JFrame("Demo");;

	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void init() {
		frame.setVisible(true);
		frame.setBounds(100, 100, 570, 474);
		
		//JPanel
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 255));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	
		
		//Panel X  Y
		JPanel panel_x = new JPanel();
		panel_x.setBackground(new Color(153, 204, 255));
		panel_x.setBounds(0, 0, 141, 435);
		panel.add(panel_x);
		panel_x.setLayout(null);
		
		
		JPanel panel_y = new JPanel();
		panel_y.setBackground(new Color(255, 255, 255));
		panel_y.setBounds(140, 0, 414, 435);
		panel.add(panel_y);
		
		//功能按钮
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		JButton b0 = new JButton("Home");
		b0.setHorizontalAlignment(SwingConstants.LEADING);
		b0.setBackground(new Color(255, 255, 255));
		b0.setBounds(34, 21, 66, 23);
		panel_x.add(b0);
		
		
		JButton b1 = new JButton("\u663E\u793A");
		b1.setBackground(new Color(255, 255, 255));
		b1.setBounds(22, 66, 93, 23);
		panel_x.add(b1);
		
		
		
		JButton b2 = new JButton("\u67E5\u627E(ID)");
		b2.setBackground(new Color(255, 255, 255));
		b2.setBounds(22, 112, 93, 23);
		b2.setMargin(new Insets(0, 0, 0, 0));
		panel_x.add(b2);
		
		JButton b3 = new JButton("\u67E5\u627E(Name)");
		b3.setBackground(new Color(255, 255, 255));
		b3.setBounds(22, 157, 93, 23);
		b3.setMargin(new Insets(0, 0, 0, 0));
		panel_x.add(b3);
		
		JButton b4 = new JButton("\u5220\u9664");
		b4.setBackground(new Color(255, 255, 255));
		b4.setBounds(22, 200, 93, 23);
		panel_x.add(b4);
		
		JButton b5 = new JButton("\u6392\u5E8F");
		b5.setBounds(22, 243, 93, 23);
		b5.setBackground(new Color(255, 255, 255));
		panel_x.add(b5);
		
		JButton b6 = new JButton("\u6DFB\u52A0");
		b6.setBounds(22, 290, 93, 23);
		b6.setBackground(new Color(255, 255, 255));
		panel_x.add(b6);
		
		JButton b7 = new JButton("\u66F4\u65B0");
		b7.setBounds(22, 334, 93, 23);
		b7.setBackground(new Color(255, 255, 255));
		panel_x.add(b7);
		
		JButton bExit = new JButton("\u9000\u51FA");
		bExit.setBounds(22, 378, 93, 23);
		bExit.setBackground(new Color(255, 255, 255));
		panel_x.add(bExit);
		
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		
		
		
		
		
//=============================================================
		CardLayout cl_panel_y=new CardLayout();//卡片JPanel
		panel_y.setLayout(cl_panel_y);
		
		JPanel panel_0 = new JPanel();//欢迎界面
		panel_0.setBackground(new Color(153, 204, 255));
		panel_y.add(panel_0, "c0");
		panel_0.setLayout(null);
		
		JLabel l1 = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u5B66\u751F\u4FE1\u606F\u7BA1\u7406\u7A0B\u5E8F");
		l1.setFont(new Font("宋体", Font.PLAIN, 16));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(37, 33, 317, 66);
		panel_0.add(l1);
		
		JPanel panel_1 = new JPanel();//显示
		panel_y.add(panel_1, "c1");
		
		
		
		
		JPanel panel_2 = new JPanel();//查找
		panel_y.add(panel_2, "c2");
		
		
		JPanel panel_3 = new JPanel();//查找
		panel_y.add(panel_3, "c3");
		//MyJPanel.init3(panel_3);
		
		JPanel panel_4 = new JPanel();//删除
		panel_y.add(panel_4, "c4");
	//	MyJPanel.init4(panel_4);
		
		JPanel panel_5 = new JPanel();//排序
		panel_y.add(panel_5, "c5");
		//MyJPanel.init5(panel_5);
		
		JPanel panel_6 = new JPanel();//添加
		panel_y.add(panel_6, "c6");
		
		JPanel panel_7 = new JPanel();
		panel_y.add(panel_7, "c7");
		//MyJPanel.init6(panel_6);
//==============================================================
		
		//按钮事件
//==============================================================
		b0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_panel_y.show(panel_y, "c0");
				
			}
		});
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MyJPanel.init1(panel_1);
				cl_panel_y.show(panel_y, "c1");
				
			}
		});
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MyJPanel.init2(panel_2);
				cl_panel_y.show(panel_y, "c2");
				
			}
		});
		b3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MyJPanel.init3(panel_3);
				cl_panel_y.show(panel_y, "c3");
				
			}
		});
		b4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MyJPanel.init4(panel_4);
				cl_panel_y.show(panel_y, "c4");
				
			}
		});
		b5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MyJPanel.init5(panel_5);
				cl_panel_y.show(panel_y, "c5");
				
			}
		});
		b6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MyJPanel.init6(panel_6);
				cl_panel_y.show(panel_y, "c6");

			}
		});
		b7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MyJPanel.init7(panel_7);
				cl_panel_y.show(panel_y, "c7");

			}
		});
		bExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
	}
}
