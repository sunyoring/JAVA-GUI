package com.sunyo.coingame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class CoinEat extends JFrame { //JFrame객체를 직접 생성하거나 상속받게 하여 사용 가능.
	private Image backgroundImage = new ImageIcon("src/images/coinMainScreen.png").getImage();
	private Image player = new ImageIcon("src/images/coinPlayer.png").getImage();
	private Image coin = new ImageIcon("src/images/coin.png").getImage();

	//player와 coin의 충돌여부를 판단하기 위해 이미지의 크기 변수화.
	private int playerX,playerY;
	private int playerWidth = player.getWidth(null);
	private int playerHeight = player.getHeight(null);
	private int coinX,coinY;
	private int coinWidth = coin.getWidth(null);
	private int coinHeight = coin.getHeight(null);
	
	private int score;//점수
	
	public CoinEat() {
		
		setTitle("동전 먹기 게임"); //타이틀
		setVisible(true); //눈에 보이게 함.
		setSize(500,500); //사이즈
		setLocationRelativeTo(null);//null : 실행 시 가운데에 창이 뜸
		setResizable(false); //창의 크기를 조절할 수 없게 함.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//창을 닫을 때, 프로그램이 같이 종료됨.
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
	
	public void Init() { //게임 초기화 함수
	 score = 0;
	 playerX = (500-playerWidth)/2;
	 playerY = (500-playerHeight)/2;  //player좌표가 화면 정중앙에 위치.
	 
	 coinX = (int)(Math.random()*(501-playerWidth));
	 coinY = (int)(Math.random()*(501-playerHeight-30))+30; //프레임틀 길이를 고려해 30을 빼줌.
	}
	
	public static void main(String[] args) {
		new CoinEat();
	}
	

}
