package com.sunyo.rockpaperscissors;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class 가위바위보 extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private Image background = new ImageIcon("src/images/RPSMain.png").getImage();
	private Image player2rockImage = new ImageIcon("src/images/rock.png").getImage();
	private Image player2scissorImage = new ImageIcon("src/images/scissors.png").getImage();
	private Image player2paperImage = new ImageIcon("src/images/paper.png").getImage();

	private ImageIcon back = new ImageIcon("src/images/back.png");
	private ImageIcon rock = new ImageIcon("src/images/rock.png");
	private ImageIcon paper = new ImageIcon("src/images/paper.png");
	private ImageIcon scissor = new ImageIcon("src/images/scissors.png");
	private ImageIcon player2rock = new ImageIcon("src/images/rock.png");
	private ImageIcon player2paper = new ImageIcon("src/images/paper.png");
	private ImageIcon player2scissor = new ImageIcon("src/images/scissors.png");
	private ImageIcon imageException = new ImageIcon("src/images/imageException.png");
	private ImageIcon exitButtonImage = new ImageIcon("src/images/exitButton.png");

	private JButton rockButton = new JButton(rock);
	private JButton paperButton = new JButton(paper);
	private JButton scissorButton = new JButton(scissor);
	private JButton exitButton = new JButton(exitButtonImage);

	private JButton reset = new JButton(back);

	private JButton player2rockButton = new JButton(player2rock);
	private JButton player2paperButton = new JButton(player2paper);
	private JButton player2scissorButton = new JButton(player2scissor);

	private static int player2X = 350;
	private static int player2Y = 100;
	private int buttonY = 680;
	private int scissorX = 600;
	private int paperX = 350;
	private int rockX = 105;
	private boolean choice = false;
	private int[] randomNum = new int[30];
	private JButton[] randomBut = new JButton[20];
	private JButton player2Button;

	private boolean regame = false;
	private boolean rockB = false;
	private boolean paperB = false;
	private boolean scissorB = false;
	private String result = "WIN !!";
	private Image player2;

	public 가위바위보() {
		setUndecorated(true);
		setTitle("가위바위보 게임");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); // 한 번 만들어진 게임장의 크기를 사용자가 임의로 수정하지 못하게 함.
		setLocationRelativeTo(null); // 게임창이 컴퓨터의 정중앙에 뜨게 함.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임창을 종료했을 때 프로그램이 같이 종료되게 함. (필수)
		setVisible(true); // 게임창이 눈에 보이게 해줌.
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null); // 버튼이나 JLabel같은 것을 넣을 때 제 위치에 들어가게 해줌.

		player2 = ranImage();

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
				choice = true;
				if (player2 == player2scissorImage) {
					result = "WIN !!";
				} else if (player2 == player2paperImage) {
					result = "LOSE";
				} else if (player2 == player2rockImage) {
					result = "DRAW";
				}

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
				choice = true;
				if (player2 == player2scissorImage) {
					result = "LOSE";
				} else if (player2 == player2paperImage) {
					result = "DRAW";
				} else if (player2 == player2rockImage) {
					result = "WIN !!";
				}

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
				choice = true;
				if (player2 == player2scissorImage) {
					result = "DRAW";
				} else if (player2 == player2paperImage) {
					result = "WIN !!";
				} else if (player2 == player2rockImage) {
					result = "LOSE";
				}

			}
		});

		add(scissorButton);

		reset.setBounds(50, 450, 70, 60);
		reset.setBorderPainted(false);
		reset.setContentAreaFilled(false);
		reset.setFocusPainted(false);
		reset.setVisible(true);
		reset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				reset.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서모양 변경

			}

			@Override
			public void mouseExited(MouseEvent e) {
				reset.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				resetGame();

			}
		});
		add(reset);

		exitButton.setBounds(850, 10, 150, 40);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서모양 변경

			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);

			}
		});
		add(exitButton);

		for (int i = 0; i < randomNum.length; i++) {
			randomNum[i] = (int) (Math.random() * 6) + 1;
		}
	}

	public void resetGame() {
		paperButton.setVisible(true);
		rockButton.setVisible(true);
		scissorButton.setVisible(true);
		choice = false;
		ranImage();

	}

	public void paint(Graphics g) { // paint()는 JFrame을 상속받은 GUI게임에서 가장 앞 부분에 화면을 그려주는 메소드로써 약속된 부분이다.
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); //
		screenGraphic = screenImage.getGraphics(); // 스크린 이미지를 통해 그래픽 객체를 얻어옴.
		screenDraw((Graphics2D) screenGraphic); // 스크린 그래픽에 어떠한 그림을 그려준다.
		g.drawImage(screenImage, 0, 0, null); // 스크린 이미지를 (0,0)위치에 그려준다.
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null); // background를 스크린 이미지에 그려준다.
		// 단순 이미지 출력.
		
		g.setFont(new Font("Arial", Font.ITALIC, 35));
		g.setColor(Color.BLACK);
		g.drawString("ROCK SCISSOR PAPER", 20, 40);

		if (choice == true) {
			g.drawImage(player2, player2X, player2Y, null);
			g.setFont(new Font("Arial", Font.BOLD, 100));
			g.setColor(Color.DARK_GRAY);
			g.drawString(result, paperX, 500);
			
			

		}

		paintComponents(g);
		// add부분이 그려진다. // 고정적인 이미지나 버튼을 그려줄 때 이용.
		this.repaint();
	} // 더블 버퍼링기법.

	public Image ranImage() {

		if (randomNum[(int) ((Math.random() * 29) + 1)] == 1) {

			return player2 = player2rockImage;
		} else if (randomNum[(int) (Math.random() * 29) + 1] == 2) {

			return player2 = player2paperImage;
		} else if (randomNum[(int) (Math.random() * 29) + 1] == 3) {

			return player2 = player2scissorImage;

		} else if (randomNum[(int) ((Math.random() * 29) + 1)] == 4) {

			return player2 = player2rockImage;
		} else if (randomNum[(int) (Math.random() * 29) + 1] == 5) {

			return player2 = player2paperImage;
		} else if (randomNum[(int) (Math.random() * 29) + 1] == 6) {

			return player2 = player2scissorImage;

		}
		return player2paperImage;
	}
}
