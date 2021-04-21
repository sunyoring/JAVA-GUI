package com.sunyo.rockpaperscissors;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class 가위바위보 extends JFrame{

	private Image backgroundImage = new ImageIcon("src/images/RPSMain.png").getImage();
	private Image rock = new ImageIcon("src/images/rock.png").getImage();
	private Image paper = new ImageIcon("src/images/paper.png").getImage();
	private Image scissor = new ImageIcon("src/images/scissors.png").getImage();
	
	private int rockX,rockY,paperX,paperY,scissorX,scissorY;
	
	public 가위바위보() {
		
		setTitle("가위바위보 게임");
		setVisible(true);
		setSize(1000,1000);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void inIt() {
		
	}
	
	public void choiceAttack() {
		
	} 
	public void paint(Graphics g) { // 더블 버퍼링 기법
		g.drawImage(backgroundImage, 0, 0, null);
		g.drawImage(rock, 135, 710, null);
		g.drawImage(paper, 375, 710, null);
		g.drawImage(scissor, 610, 710, null);

//		g.setColor(Color.BLACK);
//		g.setFont(new Font("Arial", Font.BOLD, 40));
//		g.drawString(result, 500,485);
	}

	public static void main(String[] args) {
		new 가위바위보();
	}
	


}
