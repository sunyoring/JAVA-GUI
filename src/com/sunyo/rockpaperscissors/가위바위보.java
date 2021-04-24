package com.sunyo.rockpaperscissors;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class 가위바위보 extends JFrame {

	private Image bufferImage; // 버퍼이미지 객체
	private Graphics screenGraphic; // 화면이미지를 얻어올 그래픽객체

	private Image backgroundImage;

	private ImageIcon rock = new ImageIcon("src/images/rock.png");
	private ImageIcon paper = new ImageIcon("src/images/paper.png");
	private ImageIcon scissor = new ImageIcon("src/images/scissors.png");
	private Image player2 = new ImageIcon("src/images/scissors.png").getImage();
	private JScrollPane scrollPane;
	private ImageIcon icon;

	private JButton rockButton = new JButton(rock);
	private JButton paperButton = new JButton(paper);
	private JButton scissorButton = new JButton(scissor);

	BufferedImage img = null;
	private String result;

	private int player2X = 350;
	private int player2Y = 100;
	private int buttonY = 680;
	private int scissorX = 600;
	private int paperX = 350;
	private int rockX = 105;
	

	public 가위바위보() {
		
	
		setTitle("가위바위보 게임");
	
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setSize(1000,1000);
		layeredPane.setLayout(null);


		
		try {
			img = ImageIO.read(new File("src/images/RPSMain.png"));
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null, "이미지 불러오기 실패");
			System.exit(0);
		}
		MyPanel panel = new MyPanel();
		panel.setSize(1000,1000);
		
		setLayout(null);
		
		setBounds(0,0,1000,1000);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBackground(new Color(0,0,0,0));

		rockButton.setBounds(rockX, buttonY, 250, 250);
		rockButton.setBorderPainted(false);
		rockButton.setContentAreaFilled(false);
		rockButton.setFocusPainted(false);
		rockButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
//				rockButton.setIcon(exitButtonBasicImage); // 마우스가 내려왔을 때 다시 이미지를 바꿔준다.
				rockButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서모양 변경
			}

			@Override
			public void mouseExited(MouseEvent e) {
//				rockButton.setIcon(exitButtonBasicImage); // 마우스가 내려왔을 때 다시 이미지를 바꿔준다.
				rockButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				paperButton.setVisible(false);
				scissorButton.setVisible(false);
				while(rockX < 350) {
					rockX =+10;
				}
			}
		});
		layeredPane.add(rockButton);
		
		paperButton.setBounds(paperX, buttonY, 250, 250);
		paperButton.setBorderPainted(false);
		paperButton.setContentAreaFilled(false);
		paperButton.setFocusPainted(false);
		paperButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
//				paperButton.setIcon(); // 마우스가 내려왔을 때 다시 이미지를 바꿔준다.
				paperButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서모양 변경
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
//				paperButton.setIcon(); // 마우스가 내려왔을 때 다시 이미지를 바꿔준다.
				paperButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				rockButton.setVisible(false);
				scissorButton.setVisible(false);
			}
		});
		layeredPane.add(paperButton);
		
		scissorButton.setBounds(scissorX, buttonY, 250, 250);
		scissorButton.setBorderPainted(false);
		scissorButton.setContentAreaFilled(false);
		scissorButton.setFocusPainted(false);
		scissorButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
//				scissorButton.setIcon(); // 마우스가 내려왔을 때 다시 이미지를 바꿔준다.
				scissorButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서모양 변경
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
//				scissorButton.setIcon(); // 마우스가 내려왔을 때 다시 이미지를 바꿔준다.
				scissorButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				paperButton.setVisible(false);
				rockButton.setVisible(false);

			}
		});
		layeredPane.add(scissorButton);

			
		add(layeredPane);
		layeredPane.add(panel);

		
	}
	
	class MyPanel extends JPanel {

		public void paint(Graphics g) {
			g.drawImage(img,0,0,null);
			g.drawImage(player2, player2X, player2Y, null);

		}
	}

	public void player2Attack() {
		int Atk = (int) (Math.random() * 3) + 1;
		if (Atk == 1) {
			Image player2 = new ImageIcon("src/images/rock.png").getImage();
		}
		if (Atk == 2) {
			Image player2 = new ImageIcon("src/images/paper.png").getImage();
		}
		if (Atk == 3) {
			Image player2 = new ImageIcon("src/images/scissors.png").getImage();
		}
	}


	public static void main(String[] args) {
		new 가위바위보();
	}

}
