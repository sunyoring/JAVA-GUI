package com.sunyo.rockpaperscissors;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class 가위바위보2 extends JFrame {

	private Graphics screenGraphic; // 화면이미지를 얻어올 그래픽객체

	private ImageIcon rock = new ImageIcon("src/images/rock.png");
	private ImageIcon paper = new ImageIcon("src/images/paper.png");
	private ImageIcon scissor = new ImageIcon("src/images/scissors.png");
	private ImageIcon player2rock = new ImageIcon("src/images/rock.png");
	private ImageIcon player2paper = new ImageIcon("src/images/paper.png");
	private ImageIcon player2scissor = new ImageIcon("src/images/scissors.png");
	private ImageIcon imageException = new ImageIcon("src/images/imageException.png");

	private JButton rockButton = new JButton(rock);
	private JButton paperButton = new JButton(paper);
	private JButton scissorButton = new JButton(scissor);
	private JButton player2rockButton = new JButton(player2rock);
	private JButton player2paperButton = new JButton(player2paper);
	private JButton player2scissorButton = new JButton(player2scissor);

	private static int player2X = 350;
	private static int player2Y = 100;
	private int buttonY = 680;
	private int scissorX = 600;
	private int paperX = 350;
	private int rockX = 105;

	private boolean regame = false;
	private boolean gameing = true;
	private int num = (int) (Math.random() * 3) + 1;

	private JButton player2Button = player2Attack();

//	public ImageIcon player2attack() {
//		do {
//			int num = (int)(Math.random()*3)+1;
//			switch(num){
//			case 1 : return player2rock; 
//			case 2 : return player2paper;
//			case 3 : return player2scissor;
//			default : return imageException;
//			}	
//		}while(regame);
//	}

	public 가위바위보2() {

		setTitle("가위바위보 게임");
		setSize(1000, 1000); // 사이즈
		setLocationRelativeTo(null);// null : 실행 시 가운데에 창이 뜸
		setResizable(false); // 창의 크기를 조절할 수 없게 함.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 창을 닫을 때, 프로그램이 같이 종료됨.
		setVisible(true); // 눈에 보이게 함.
		setLayout(null);

		do {

			rockButton.setBounds(rockX, buttonY, 250, 250);
			rockButton.setBorderPainted(false);
			rockButton.setContentAreaFilled(false);
			rockButton.setFocusPainted(false);
			rockButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					rockButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서모양 변경

				}

				@Override
				public void mouseExited(MouseEvent e) {
					rockButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

				}

				@Override
				public void mousePressed(MouseEvent e) {
					scissorButton.setVisible(false);
					paperButton.setVisible(false);
					player2Button.setVisible(true);
				}
			});
			add(rockButton);

			paperButton.setBounds(paperX, buttonY, 250, 250);
			paperButton.setBorderPainted(false);
			paperButton.setContentAreaFilled(false);
			paperButton.setFocusPainted(false);
			paperButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					paperButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서모양 변경

				}

				@Override
				public void mouseExited(MouseEvent e) {
					paperButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

				}

				@Override
				public void mousePressed(MouseEvent e) {
					scissorButton.setVisible(false);
					rockButton.setVisible(false);

					player2Button.setVisible(true);
				}
			});
			add(paperButton);

			scissorButton.setBounds(scissorX, buttonY, 250, 250);
			scissorButton.setBorderPainted(false);
			scissorButton.setContentAreaFilled(false);
			scissorButton.setFocusPainted(false);
			scissorButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					scissorButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서모양 변경

				}

				@Override
				public void mouseExited(MouseEvent e) {
					scissorButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

				}

				@Override
				public void mousePressed(MouseEvent e) {
					paperButton.setVisible(false);
					rockButton.setVisible(false);
					player2Button.setVisible(true);
				}
			});
			add(scissorButton);

			player2Button.setBounds(player2X, player2Y, 250, 250);
			player2Button.setBorderPainted(false);
			player2Button.setContentAreaFilled(false);
			player2Button.setFocusPainted(false);
			player2Button.setVisible(false);
			player2Button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					player2Button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서모양 변경

				}

				@Override
				public void mouseExited(MouseEvent e) {
					player2Button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

				}

				@Override
				public void mousePressed(MouseEvent e) {
					paperButton.setVisible(true);
					rockButton.setVisible(true);
					scissorButton.setVisible(true);
					player2Button.setVisible(false);
					

				}
			});
			add(player2Button);
			
		} while (false);

	} //배열이용해서 랜덤값 반복추출 시도하기

	public void setPlayer2Button(JButton player2Button) {
		this.player2Button = player2Button;
	}

	public JButton player2Attack() {

		switch (num) {
		case 1:
			return player2rockButton = new JButton(player2rock);

		case 2:
			return player2paperButton = new JButton(player2paper);

		case 3:
			return player2scissorButton = new JButton(player2scissor);

		default:
			return null;
		}

	}

	public void setNum(int num) {
		this.num = num;
	}

	public static void main(String[] args) {
		new 가위바위보2();
	}

}
