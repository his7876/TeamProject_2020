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

public class NewWorkerView extends JFrame implements ActionListener{
	Image _ku_logoImg, homeImg, prevImg = null;
	ImageIcon homeIcon, prevIcon;
	
	JButton home = new JButton();
	JButton prev = new JButton();
	JLabel ManagerInfo = new JLabel("김수민");
	JButton btn1 = new JButton("완료");
	
	public NewWorkerView() {
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
			prevImg = ImageIO.read(new File("Img/prev.png"));
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
		
	
		prevImg = prevImg.getScaledInstance(40	, 40, Image.SCALE_DEFAULT);
		prevIcon = new ImageIcon(prevImg);
		
		prev.setIcon(prevIcon);
		prev.setBorderPainted(false);
		prev.setContentAreaFilled(false);
		prev.setFocusPainted(false);
		prev.setOpaque(false);
		
		ManagerInfo.setOpaque(true);
		ManagerInfo.setBackground(new Color(0xE7E6E6));
		
		
		btn1.setForeground(Color.white);
		btn1.setOpaque(true);
		btn1.setBackground(new Color(0x0E345B));
		btn1.setBorderPainted(false);
		btn1.setFocusPainted(false);
		
		this.add(home);
		this.add(prev);
		this.add(ManagerInfo);
		this.add(btn1);
		
		home.setBounds(5, 5, 50, 40);
		ManagerInfo.setBounds(5,550 , 100, 20);
		prev.setBounds(60, 5, 40, 40);
		btn1.setBounds(350,500 , 100, 50);
		
		initListener();
	}
	
	public void initListener() {
//		// TODO Auto-generated method stub
		btn1.addActionListener(this);
		home.addActionListener(this);
		prev.addActionListener(this);
	}

	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == home) {
			System.exit(0);
		}else if(e.getSource() == prev) {
			this.setVisible(false);
	        new NewWorkerInfoView().setVisible(true);
		}else if(e.getSource() == btn1) {
			this.setVisible(false);
			new ManageChoiceView().setVisible(true);
			//사원 DB로 사진이 넘어가야함.
		}
	}

}
