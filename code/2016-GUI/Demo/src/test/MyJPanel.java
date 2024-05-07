package test;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
class MyJPanel{
	
	
//	��ʾ
	static void init1(JPanel panel){
		
		
		//JPanel panel=new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 373, 382);
		
		panel.setLayout(null);
		
		JLabel l = new JLabel("       ===\u4E0B\u5217\u662F\u6240\u6709\u67E5\u770B\u7684\u6240\u6709\u6570\u636E\u4FE1\u606F===");//Label
		l.setFont(new Font("����", Font.PLAIN, 14));
		l.setBounds(15, 10, 336, 29);
		panel.add(l);
		
		JTextArea textArea = new JTextArea();//TextArea
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setBackground(new Color(255, 255, 255));
		textArea.setBounds(25, 49, 326, 311);
		
		List<Student> list=new ArrayList<Student>();//ת��ΪString
		list=Sql.disAll();
		
		textArea.append(Sql.listToString(list,'\n'));
		panel.add(textArea);
		panel.repaint();
		
	}
	
//	����ID
	static void init2(JPanel panel){
	//	JPanel panel=new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 373, 202);
		panel.setLayout(null);
		
		JLabel l1 = new JLabel("������ҵ�ID:");
		l1.setBounds(15, 39, 201, 35);
		l1.setFont(new Font("����", Font.PLAIN, 15));
		panel.add(l1);
		
		JLabel l2=new JLabel("ѧ��     ����    Java    Math    OS");
		l2.setBounds(47, 80, 290, 31);
		l2.setFont(new Font("����", Font.PLAIN, 15));
		panel.add(l2);
		
		JTextField textField = new JTextField();
		textField.setBounds(208, 39, 72, 35);
		textField.setColumns(10);
		panel.add(textField);
		
		
		
		JLabel l0 = new JLabel();
		l0.setFont(new Font("����", Font.PLAIN, 14));
		l0.setBounds(21, 120, 331, 35);
		
		
		
		
		JButton b = new JButton("Y");
		b.setFont(new Font("����", Font.PLAIN, 10));
		b.setHorizontalAlignment(SwingConstants.LEFT);
		b.setBackground(new Color(255, 255, 255));
		b.setBounds(301, 39, 45, 35);
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				String s=textField.getText();
				long id=Long.parseLong(s);		
				Sql.FindByID(id,l0);
			}
				});
		panel.add(b);
		panel.add(l0);
		
		
		
	
		
	}
		
//	����Name
	static void init3(JPanel panel) {
		panel.setBounds(0, 0, 373, 202);
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(null);
		
		JLabel l1 = new JLabel("����Ҫ���ҵ�����:");
		l1.setBounds(15, 39, 201, 35);
		l1.setFont(new Font("����", Font.PLAIN, 15));
		panel.add(l1);
		
		JLabel l2=new JLabel("ѧ��     ����    Java    Math    OS");
		l2.setBounds(47, 80, 290, 31);
		l2.setFont(new Font("����", Font.PLAIN, 15));
		panel.add(l2);
		
		
		JTextField textField = new JTextField();
		textField.setBounds(208, 39, 72, 35);
		textField.setColumns(10);
		panel.add(textField);
		
		
		
		JLabel l0 = new JLabel();
		l0.setFont(new Font("����", Font.PLAIN, 14));
		l0.setBounds(21, 120, 331, 35);
		panel.add(l0);
		
		
		
		JButton b = new JButton("Y");
		b.setBounds(301, 39, 45, 35);
		b.setBackground(new Color(255, 255, 255));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				String s=textField.getText();
				Sql.FindByName(s,l0);
			}
				});
		panel.add(b);
		
		
		
	}

//	ɾ��
	static void init4(JPanel panel) {
		panel.setBounds(0, 0, 373, 202);
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(null);
		
		
		JLabel l1 = new JLabel("��������Ҫɾ����ID:");
		l1.setBounds(15, 39, 201, 35);
		l1.setFont(new Font("����", Font.PLAIN, 15));
		panel.add(l1);
		
		JTextField textField = new JTextField();
		textField.setBounds(95, 84, 72, 35);
		
		textField.setColumns(10);
		panel.add(textField);
		
		
		
		JLabel l0 = new JLabel();
		l0.setFont(new Font("����", Font.PLAIN, 14));
		l0.setBounds(15, 137, 331, 35);
		panel.add(l0);
		
		
		JButton b = new JButton("Y");
		b.setBackground(new Color(255, 255, 255));
		b.setHorizontalAlignment(SwingConstants.LEFT);
		b.setBounds(199, 84, 62, 35);
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				String s=textField.getText();
				long id=Long.parseLong(s);
				Sql.DeleteByID(id);
			}
				});
		panel.add(b);
		
		
		
		
		
	}
	
//	����
	static void init5(JPanel p){  
		//JPanel  p = new JPanel();
		p.setBackground(new Color(255, 255, 255));
		
		
		JTextArea a1= new JTextArea();
		a1.setFont(new Font("Monospaced", Font.PLAIN, 15));
		JTextArea a2= new JTextArea();
    	a2.setFont(new Font("Monospaced", Font.PLAIN, 15));
    	JTextArea a3= new JTextArea();
    	a3.setFont(new Font("Monospaced", Font.PLAIN, 15));
    	 
        CardLayout card = new CardLayout(3,3);  
        JPanel pane = new JPanel(card);  
        pane.setBackground(new Color(255, 255, 255));
        pane.setBounds(45, 65, 360, 500);
       
        
        JButton b1_1;
        JButton b2_1;
        JButton b3_1;
        b1_1 = new JButton("��Java����");  
        b1_1.setBackground(new Color(255, 255, 255));
        b1_1.setBounds(84, 20, 93, 35);
        b2_1 = new JButton("��Math����");  
        b2_1.setBackground(new Color(255, 255, 255));
        b2_1.setBounds(172, 20, 93, 35);
        b3_1 = new JButton("��OS����");  
        b3_1.setBackground(new Color(255, 255, 255));
        b3_1.setBounds(264, 20, 87, 35);
       
        
        p.setLayout(null);
        p.add(b1_1);  
        p.add(b2_1);  
        p.add(b3_1);  
        p.add(pane);
 
        pane.add(a1,"p1");  
        pane.add(a2,"p2");  
        pane.add(a3,"p3");  
      
       
        b1_1.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
            	Sql.Sort(a1,1);
            	card.show(pane,"p1");
                
            }  
        });  
        b2_1.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
            	Sql.Sort(a2,2);
            	card.show(pane,"p2");  
            }  
        });  
        b3_1.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
            	Sql.Sort(a3,3);
                card.show(pane,"p3");  
            }  
        });  
		
		
	}
	
	
//	���
	static void init6(JPanel p){
		
		//JPanel p=new JPanel();
		p.setBackground(new Color(255, 255, 255));
		
		JLabel l0,l1,l2,l3,l4,l5;//����   ����   id   java  math O s
		JTextField t1,t2,t3,t4,t5;//����   id   java  math O s
		JButton b=new JButton("Y");//ȷ��
		
		
		
		
		p.setLayout(null);
		l0=new  JLabel("�����������Ϣ:");
		l0.setFont(new Font("����", Font.PLAIN, 14));
		l0.setBounds(0,0,126,30);
		
		l1=new JLabel("Name:");
		l1.setFont(new Font("����", Font.PLAIN, 14));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(30,30,80,30);
		t1=new JTextField();//name
		t1.setBounds(144,30,80,30);
		
		l2=new JLabel("   ID:");
		l2.setFont(new Font("����", Font.PLAIN, 14));
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setBounds(30,70,80,30);
		t2=new JTextField();//id
		t2.setBounds(144,70,80,30);
		
		l3=new JLabel("   Java:");
		l3.setFont(new Font("����", Font.PLAIN, 14));
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		l3.setBounds(30,110,80,30);
		t3=new JTextField();//id
		t3.setBounds(144,110,80,30);
		
		l4=new JLabel("   Math:");
		l4.setFont(new Font("����", Font.PLAIN, 14));
		l4.setHorizontalAlignment(SwingConstants.CENTER);
		l4.setBounds(30,150,80,30);
		t4=new JTextField();//id
		t4.setBounds(144,150,80,30);
		
		l5=new JLabel("   OS:");
		l5.setFont(new Font("����", Font.PLAIN, 14));
		l5.setHorizontalAlignment(SwingConstants.CENTER);
		l5.setBounds(30,190,80,30);
		t5=new JTextField();//id
		t5.setBounds(144,190,80,30);
		
		b.setBounds(300,260,50,30);
		b.setBackground(new Color(255, 255, 255));
		
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String n,id,j,m,o;
				id=t2.getText();long id1 = (long)Integer.parseInt(id);//��String ת����I n t
				n=t1.getText();
				j=t3.getText();double j1 =(double) Integer.parseInt(j);//��String ת����I n t
				m=t4.getText();double m1 =(double) Integer.parseInt(m);
				o=t5.getText();double o1 =(double) Integer.parseInt(o);
				
				Student stu=new Student();
				stu.setId(id1);
				stu.setName(n);
				stu.setFractionJava(j1);
				stu.setFractionMath(m1);
				stu.setFractionOS(o1);
				Sql.add(stu);
				JOptionPane.showMessageDialog(null, "��ӳɹ���", "WO", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		p.add(l0);
		p.add(l1);
		p.add(t1);
		p.add(l2);
		p.add(t2);
		p.add(l3);
		p.add(t3);
		p.add(l4);
		p.add(t4);
		p.add(l5);
		p.add(t5);
		p.add(b);

		
		
		
		
		
	}

//  ����	
	static void init7(JPanel panel){
		//JPanel p=new JPanel();
		//JPanel panel = new JPanel();
		panel.setBounds(0, 0, 373, 202);
		panel.setBackground(new Color(255, 255, 255));
		//frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("��������Ҫ����ѧ����ID:");
		lblNewLabel_1.setBounds(15, 39, 201, 35);
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 15));
		panel.add(lblNewLabel_1);
		
		JTextField textField = new JTextField();
		textField.setBounds(208, 39, 72, 35);
		textField.setColumns(10);
		panel.add(textField);
		
					
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(15, 149, 55, 35);
		panel.add(textField_1);
		textField_1.setColumns(10);
					
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(80, 149, 45, 35);
		panel.add(textField_2);
		textField_2.setColumns(10);
					
		JTextField textField_3 = new JTextField();
		textField_3.setBounds(136, 149, 45, 35);
		panel.add(textField_3);
		textField_3.setColumns(10);
					
		JTextField textField_4 = new JTextField();
		textField_4.setBounds(194, 149, 45, 35);
		panel.add(textField_4);
		textField_4.setColumns(10);
					
		
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 14));
		lblNewLabel.setBounds(15, 94, 331, 35);
		panel.add(lblNewLabel);
		
		
		
		JButton Find = new JButton("Y");
		Find.setBackground(new Color(255, 255, 255));
		Find.setBounds(301, 39, 45, 35);
		panel.add(Find);
		
		JButton Sure = new JButton("Y");
		Sure.setBounds(291, 148, 55, 36);
		panel.add(Sure);
		Sure.setBackground(new Color(255, 255, 255));
		
		
		
		
		Find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				String s=textField.getText();
				long id=Long.parseLong(s);
				Sql.FindByID(id,lblNewLabel);
				if(lblNewLabel.getText().equals("~~~~~~~���޴��ˣ�~~~~~~~~")){
					
					lblNewLabel.setText("���Ǹ������ID������");
				}
				else
					lblNewLabel.setText("��������Name  FractionJava   FractionMath  FractionOS");

			}
				});
		
		
		
		Sure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student stu=new Student();
				stu.setId(Long.parseLong(textField.getText()));
				stu.setName(textField_1.getText());
				stu.setFractionJava(Double.valueOf(textField_2.getText()).doubleValue());
				stu.setFractionMath(Double.valueOf(textField_3.getText()).doubleValue());
				stu.setFractionOS(Double.valueOf(textField_4.getText()).doubleValue());
				Sql.UpdateStudent(stu);
				JOptionPane.showMessageDialog(null, "�޸ĳɹ���", "WO", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		
		
		
		
		
		
		
		
		
				
		
		
		
		
		
		
		
		
		
		
		
	}

}









