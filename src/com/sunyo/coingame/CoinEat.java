package com.sunyo.coingame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class CoinEat extends JFrame { //JFrame��ü�� ���� �����ϰų� ��ӹް� �Ͽ� ��� ����.
	private Image backgroundImage = new ImageIcon("src/images/coinMainScreen.png").getImage();
	private Image player = new ImageIcon("src/images/coinPlayer.png").getImage();
	private Image coin = new ImageIcon("src/images/coin.png").getImage();

	//player�� coin�� �浹���θ� �Ǵ��ϱ� ���� �̹����� ũ�� ����ȭ.
	private int playerX,playerY;
	private int playerWidth = player.getWidth(null);
	private int playerHeight = player.getHeight(null);
	private int coinX,coinY;
	private int coinWidth = coin.getWidth(null);
	private int coinHeight = coin.getHeight(null);
	
	private int score;//����
	
	public CoinEat() {
		
		setTitle("���� �Ա� ����"); //Ÿ��Ʋ
		setVisible(true); //���� ���̰� ��.
		setSize(500,500); //������
		setLocationRelativeTo(null);//null : ���� �� ����� â�� ��
		setResizable(false); //â�� ũ�⸦ ������ �� ���� ��.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//â�� ���� ��, ���α׷��� ���� �����.
		Init();
	}

	public void paint(Graphics g) {
		g.drawImage(backgroundImage,0,0,null);
		g.drawImage(coin, coinX, coinY, null);
		g.drawImage(player, playerX, playerY, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.BOLD,40));
		g.drawString("SCORE : "+score, 30, 80);
	}
	
	public void Init() { //���� �ʱ�ȭ �Լ�
	 score = 0;
	 playerX = (500-playerWidth)/2;
	 playerY = (500-playerHeight)/2;  //player��ǥ�� ȭ�� ���߾ӿ� ��ġ.
	 
	 coinX = (int)(Math.random()*(501-playerWidth));
	 coinY = (int)(Math.random()*(501-playerHeight-30))+30; //������Ʋ ���̸� ����� 30�� ����.
	}
	
	public static void main(String[] args) {
		new CoinEat();
	}
	

}
