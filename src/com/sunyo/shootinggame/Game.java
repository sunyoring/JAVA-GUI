package com.sunyo.shootinggame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
	private int delay = 20;
	private long pretime;
	private int cnt; // 이벤트 발생 주기를 컨트롤하는 변수

	private Image player = new ImageIcon("src/images/player.png").getImage();

	private int playerX, playerY;
	private int playerWidth = player.getWidth(null);
	private int playerHeight = player.getHeight(null);

	private int playerSpeed = 10; // 키 입력이 한 번 인식됐을 때, 플레이어가 이동할 거리
	private int playerHp = 30;

	private boolean up, down, left, right, shooting;

	private ArrayList<PlayerAttack> playerAttackList = new ArrayList<PlayerAttack>();
	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	private ArrayList<EnemyAttack> enemyAttackList = new ArrayList<EnemyAttack>();

	private PlayerAttack playerAttack;
	private Enemy enemy;
	private EnemyAttack enemyAttack;

	@Override
	public void run() {
		cnt = 0;
		playerX = 10;
		playerY = (Main.SCREEN_HEIGHT - playerHeight) / 2;

		while (true) {
			pretime = System.currentTimeMillis();
			if (System.currentTimeMillis() - pretime < delay) { // 현재시간 - (cnt가 증가하기 전 시간) < delay
				try {

					Thread.sleep(delay - System.currentTimeMillis() + pretime);
					keyProcess();
					enemyAppearProcess();
					enemyMoveProcess();
					playerAttackProcess();
					enemyAttackProcess();
					cnt++;
				} catch (InterruptedException e) { // Thread.sleep 예외처리 필수
					e.printStackTrace();
				}
			}
		}
	}

	private void keyProcess() {
		if (up && playerY - playerSpeed > 0) {
			playerY -= playerSpeed;

		}
		if (down && playerY + playerHeight + playerSpeed < Main.SCREEN_HEIGHT) {
			playerY += playerSpeed;

		}
		if (left && playerX - playerSpeed > 0) {
			playerX -= playerSpeed;

		}
		if (right && playerX + playerWidth + playerSpeed < Main.SCREEN_WIDTH) {
			playerX += playerSpeed;

		}
		if (shooting && cnt % 15 == 0) { // cnt는 0.02초마다 올라감. 즉, 0.3초마다 미사일 발사
			playerAttack = new PlayerAttack(playerX + 222, playerY + 25);
			playerAttackList.add(playerAttack);

		}
	}

	private void playerAttackProcess() {
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			playerAttack.fire();
			for (int j = 0; j < enemyList.size(); j++) {
				enemy = enemyList.get(j);
				if (playerAttack.x > enemy.x && playerAttack.x < enemy.x + enemy.width && playerAttack.y > enemy.y
						&& playerAttack.y < enemy.y + enemy.height) {
					enemy.hp -= playerAttack.attack;
					playerAttackList.remove(playerAttack);
				}
				if (enemy.hp <= 0) {
					enemyList.remove(enemy);
				}
			}
		}
	}

	private void enemyAttackProcess() {
		if (cnt % 50 == 0) {
			enemyAttack = new EnemyAttack(enemy.x - 79, enemy.y + 35);
			enemyAttackList.add(enemyAttack);
		}
		for (int i = 0; i <enemyAttackList.size(); i++) {
			enemyAttack = enemyAttackList.get(i);
			enemyAttack.fire();
			if(enemyAttack.x > playerX && enemyAttack.x < playerX + playerWidth 
					&& enemyAttack.y > playerY && enemyAttack.y < playerY+ playerHeight) {
				playerHp -= enemyAttack.attack;
				enemyAttackList.remove(enemyAttack);
			}
		}
		
	}

	private void enemyAppearProcess() {
		if (cnt % 80 == 0) {
			enemy = new Enemy(1120, (int) (Math.random() * 620));
			enemyList.add(enemy);
		}

	}

	private void enemyMoveProcess() {
		for (int i = 0; i < enemyList.size(); i++) {
			enemy = enemyList.get(i);
			enemy.move();
		}
	}

	public void gameDraw(Graphics g) {
		playerDraw(g);
		enemyDraw(g);
	}

	public void enemyDraw(Graphics g) {
		for (int i = 0; i < enemyList.size(); i++) {
			enemy = enemyList.get(i);
			g.drawImage(enemy.image, enemy.x, enemy.y, null);
			g.setColor(Color.red);
			g.fillRect(enemy.x -1,enemy.y -40,enemy.hp*15,20);
		}
		for (int i = 0; i < enemyAttackList.size(); i++) {
			enemyAttack = enemyAttackList.get(i);
			g.drawImage(enemyAttack.image, enemyAttack.x, enemyAttack.y, null);
		}
	}

	public void playerDraw(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(playerX -1,playerY -40,playerHp*6,20);
		g.drawImage(player, playerX, playerY, null);
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			g.drawImage(playerAttack.image, playerAttack.x, playerAttack.y, null);
		}

	}

	public void setUp(boolean up) {
		this.up = up;

	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

}
