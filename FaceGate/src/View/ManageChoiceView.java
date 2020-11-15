package View;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ManageChoiceView extends JFrame implements ActionListener {
	
	Image _ku_logoImg, homeImg= null;
	ImageIcon homeIcon;
	
	JButton btn1 = new JButton("기존사원 정보 수정/삭제");
	JButton btn2 = new JButton("신규사원 추가");
	JButton home = new JButton();
	JLabel ManagerInfo = new JLabel("김수민");

	public ManageChoiceView() {
		super("FACEGATE"); // title
		this.setSize(800, 650);
		this.getContentPane().setBackground(Color.WHITE);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		this.setVisible(true);
	}
	
	public void init() {
		// TODO Auto-generated method stub
		
		this.setForeground(Color.WHITE);
		try {
			_ku_logoImg = ImageIO.read(new File("Img/KU_Logo.png"));
			homeImg = ImageIO.read(new File("Img/home.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fail to load image.");
			System.exit(0);
		}
		
		this.setIconImage(_ku_logoImg);
		this.setLayout(null);
		
		homeImg = homeImg.getScaledInstance(40	, 40, Image.SCALE_DEFAULT);
		homeIcon = new ImageIcon(homeImg);
		
		home.setIcon(homeIcon);
		home.setBorderPainted(false);
		home.setContentAreaFilled(false);
		home.setFocusPainted(false);
		home.setOpaque(false);

		btn1.setForeground(Color.white);
		btn1.setOpaque(true);
		btn1.setBackground(new Color(0x0E345B));
		btn1.setBorderPainted(false);
		btn1.setFocusPainted(false);
		
		btn2.setForeground(Color.white);
		btn2.setOpaque(true);
		btn2.setBackground(new Color(0x0E345B));
		btn2.setBorderPainted(false);
		btn2.setFocusPainted(false);
		
		
		ManagerInfo.setOpaque(true);
		ManagerInfo.setBackground(new Color(0xE7E6E6));
		 // Manager info 어떻게 불러올 것인지.
		// 불러와서 써넣을 건지, 아무거나 써놓을 것인지
		
		this.add(btn1);
		this.add(btn2);
		this.add(home);
		this.add(ManagerInfo);
		
		btn1.setBounds(200, 150, 400, 50);
		btn2.setBounds(200, 205, 400, 50);
		home.setBounds(5, 5, 40, 40);
		ManagerInfo.setBounds(5,550 , 100, 20);
		initListener();
	}
	
	public void initListener() {
		// TODO Auto-generated method stub
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		home.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == home) {
			this.setVisible(false);
	        new FaceRecognitionView().setVisible(true);
		}else if(e.getSource() == btn1) {
			this.setVisible(false);
	        new ExistWorkerView().setVisible(true);
		}else if(e.getSource() == btn2) {
			this.setVisible(false);
			new NewWorkerInfoView().setVisible(true);
		}
	}
}
