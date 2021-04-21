package com.sunyo.coingame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class CoinEat extends JFrame { // JFrame객체를 직접 생성하거나 상속받게 하여 사용 가능.
	private Image bufferImage; // 버퍼이미지 객체
	private Graphics screenGraphic; // 화면이미지를 얻어올 그래픽객체

	private Clip clip;

	private Image backgroundImage = new ImageIcon("src/images/coinMainScreen.png").getImage();
	private Image player = new ImageIcon("src/images/coinPlayer.png").getImage();
	private Image coin = new ImageIcon("src/images/coin.png").getImage();

	// player와 coin의 충돌여부를 판단하기 위해 이미지의 크기 변수화.
	private int playerX, playerY;
	private int playerWidth = player.getWidth(null);
	private int playerHeight = player.getHeight(null);
	private int coinX, coinY;
	private int coinWidth = coin.getWidth(null);
	private int coinHeight = coin.getHeight(null);

	private int score;// 점수
	private boolean up, down, left, right; // 움직임 변수 초기값: false
	// 두 개의 키 입력을 동시에 받아들이기 위해 boolean변수를 이용한다.(대각선 이동)

	public CoinEat() {

		setTitle("동전 먹기 게임"); // 타이틀
		setVisible(true); // 눈에 보이게 함.
		setSize(500, 500); // 사이즈
		setLocationRelativeTo(null);// null : 실행 시 가운데에 창이 뜸
		setResizable(false); // 창의 크기를 조절할 수 없게 함.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 창을 닫을 때, 프로그램이 같이 종료됨.
		addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					left = true;
					break;
				case KeyEvent.VK_RIGHT:
					right = true;
					break;
				case KeyEvent.VK_UP:
					up = true;
					break;
				case KeyEvent.VK_DOWN:
					down = true;
					break;
				}

			}

			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					left = false;
					break;
				case KeyEvent.VK_RIGHT:
					right = false;
					break;
				case KeyEvent.VK_UP:
					up = false;
					break;
				case KeyEvent.VK_DOWN:
					down = false;
					break;
				}

			}

		});
		Init();
		while (true) {
			try { // 대기시간없이 계속 반복하였을 때 무리가 가는 것을 대비하여 대기시간을 준다.
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			keyProcess();
			crashCheck();
		}
	}

	public void Init() { // 게임 초기화 함수
		score = 0;
		playerX = (500 - playerWidth) / 2;
		playerY = (500 - playerHeight) / 2; // player좌표가 화면 정중앙에 위치.

		coinX = (int) (Math.random() * (501 - playerWidth));
		coinY = (int) (Math.random() * (501 - playerHeight - 30)) + 30; // 프레임틀 길이를 고려해 30을 빼줌.
	
		playSound("src/audio/backgroundMusic.wav",true);

	}

	public void keyProcess() {
		if (up && playerY - 3 > 30)
			playerY -= 5;
		if (down && playerY + playerHeight + 3 < 500)
			playerY += 5;
		if (left && playerX - 3 > 0)
			playerX -= 5;
		if (right && playerX + playerWidth + 3 < 500)
			playerX += 5;
	} // 플레이어 움직임

	public void crashCheck() {
		if ((playerX + playerWidth > coinX) && (coinX + coinWidth > playerX) && (playerY + playerHeight > coinY)
				&& (coinY + coinHeight > playerY)) {
			score += 100;
			playSound("src/audio/getCoin.wav",false);

			
			coinX = (int) (Math.random() * (501 - playerWidth));
			coinY = (int) (Math.random() * (501 - playerHeight - 30)) + 30;
		}
	} // 플레이어와 코인 충돌 체크

	public void playSound(String pathName, boolean isLoop) {
		try {
			clip = AudioSystem.getClip();
			File audioFile = new File(pathName);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			clip.open(audioStream); //클립에 오디오스트림을 담아준다.
			clip.start(); // 오디오 실행
			if(isLoop)
				clip.loop(clip.LOOP_CONTINUOUSLY); //해당 오디오 무한반복.
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		bufferImage = createImage(500, 500); // 화면크기의 버퍼이미지 생성
		screenGraphic = bufferImage.getGraphics(); // 그래픽을 받아옴.
		screenDraw(screenGraphic); // 버퍼이미지를 화면에 그려줌.
		g.drawImage(bufferImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) { // 더블 버퍼링 기법
		g.drawImage(backgroundImage, 0, 0, null);
		g.drawImage(coin, coinX, coinY, null);
		g.drawImage(player, playerX, playerY, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString("SCORE : " + score, 30, 80);
		this.repaint();
	}

	public static void main(String[] args) {
		new CoinEat();
	}

}
