package com.sunyo.coingame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class CoinEat extends JFrame { //JFrame��ü�� ���� �����ϰų� ��ӹް� �Ͽ� ��� ����.
	private Image bufferImage; //�����̹��� ��ü
	private Graphics screenGraphic; //ȭ���̹����� ���� �׷��Ȱ�ü
	
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
	private boolean up, down, left, right; //������ ���� �ʱⰪ: false
	//�� ���� Ű �Է��� ���ÿ� �޾Ƶ��̱� ���� boolean������ �̿��Ѵ�.(�밢�� �̵�)
	
	public CoinEat() {
		
		setTitle("���� �Ա� ����"); //Ÿ��Ʋ
		setVisible(true); //���� ���̰� ��.
		setSize(500,500); //������
		setLocationRelativeTo(null);//null : ���� �� ����� â�� ��
		setResizable(false); //â�� ũ�⸦ ������ �� ���� ��.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//â�� ���� ��, ���α׷��� ���� �����.
		addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT :
					left = true;
					break;
				case KeyEvent.VK_RIGHT :
					right = true;
					break;
				case KeyEvent.VK_UP :
					up = true;
					break;
				case KeyEvent.VK_DOWN:
					down = true;
					break;
				} 
				
			}
			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT :
					left = false;
					break;
				case KeyEvent.VK_RIGHT :
					right = false;
					break;
				case KeyEvent.VK_UP :
					up = false;
					break;
				case KeyEvent.VK_DOWN :
					down = false;
					break;
				} 
				
			}
			
		});
		Init();
		while(true) {
			try { //���ð����� ��� �ݺ��Ͽ��� �� ������ ���� ���� ����Ͽ� ���ð��� �ش�.
				Thread.sleep(20);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			keyProcess();
			crashCheck();
		}
	}


	
	public void Init() { //���� �ʱ�ȭ �Լ�
	 score = 0;
	 playerX = (500-playerWidth)/2;
	 playerY = (500-playerHeight)/2;  //player��ǥ�� ȭ�� ���߾ӿ� ��ġ.
	 
	 coinX = (int)(Math.random()*(501-playerWidth));
	 coinY = (int)(Math.random()*(501-playerHeight-30))+30; //������Ʋ ���̸� ����� 30�� ����.
	}
	
	public void keyProcess() {
		if (up && playerY - 3 > 30) playerY-=5;
		if (down && playerY + playerHeight + 3 < 500) playerY+=5;
		if (left && playerX - 3 > 0) playerX-=5;
		if (right && playerX + playerWidth + 3 < 500) playerX+=5;
	}	// �÷��̾� ������
	public void crashCheck() {
		if ((playerX + playerWidth > coinX) && (coinX + coinWidth > playerX) 
				&& (playerY + playerHeight > coinY) && (coinY + coinHeight > playerY)) {
			score+=100;
			coinX = (int)(Math.random()*(501-playerWidth));
			coinY = (int)(Math.random()*(501-playerHeight-30))+30;
		}
	}	// �÷��̾�� ���� �浹 üũ
	
	public void paint(Graphics g) {
		bufferImage = createImage(500,500); //ȭ��ũ���� �����̹��� ����
		screenGraphic = bufferImage.getGraphics(); // �׷����� �޾ƿ�.
		screenDraw(screenGraphic); //�����̹����� ȭ�鿡 �׷���.
		g.drawImage(bufferImage, 0, 0, null);
	}
	public void screenDraw(Graphics g) { //���� ���۸� ���
		g.drawImage(backgroundImage,0,0,null);
		g.drawImage(coin, coinX, coinY, null);
		g.drawImage(player, playerX, playerY, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.BOLD,40));
		g.drawString("SCORE : "+score, 30, 80);
		this.repaint();
	}
	
	public static void main(String[] args) {
		new CoinEat();
	}
	

}
