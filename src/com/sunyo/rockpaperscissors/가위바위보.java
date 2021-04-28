package com.sunyo.rockpaperscissors;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ���������� extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private Image background = new ImageIcon("src/images/RPSMain.png").getImage();
	private Image player2 = new ImageIcon("src/images/rock.png").getImage();
	
	private ImageIcon back = new ImageIcon("src/images/back.png");
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
	private int[] randomNum = new int[20];
	private JButton[] randomBut = new JButton[20];
	private JButton player2Button;

	private boolean regame = false;
	private boolean rockB = false;
	private boolean paperB = false;
	private boolean scissorB = false;

	public ����������() {
		setUndecorated(true);
		setTitle("���������� ����");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); // �� �� ������� �������� ũ�⸦ ����ڰ� ���Ƿ� �������� ���ϰ� ��.
		setLocationRelativeTo(null); // ����â�� ��ǻ���� ���߾ӿ� �߰� ��.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����â�� �������� �� ���α׷��� ���� ����ǰ� ��. (�ʼ�)
		setVisible(true); // ����â�� ���� ���̰� ����.
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null); // ��ư�̳� JLabel���� ���� ���� �� �� ��ġ�� ���� ����.


		rockButton.setBounds(rockX, buttonY, 250, 250);
		rockButton.setBorderPainted(false);
		rockButton.setContentAreaFilled(false);
		rockButton.setFocusPainted(false);
		rockButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rockButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺 Ŀ����� ����

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
				paperButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺 Ŀ����� ����

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
				scissorButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺 Ŀ����� ����

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
				reset.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺 Ŀ����� ����

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

		for (int i = 0; i < randomNum.length; i++) {
			randomNum[i] = (int) (Math.random() * 3) + 1;
		}
	}


	public void resetGame() {
		paperButton.setVisible(true);
		rockButton.setVisible(true);
		scissorButton.setVisible(true);
		choice = false;
		ranImage();

	}

	public void paint(Graphics g) { // paint()�� JFrame�� ��ӹ��� GUI���ӿ��� ���� �� �κп� ȭ���� �׷��ִ� �޼ҵ�ν� ��ӵ� �κ��̴�.
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); //
		screenGraphic = screenImage.getGraphics(); // ��ũ�� �̹����� ���� �׷��� ��ü�� ����.
		screenDraw((Graphics) screenGraphic); // ��ũ�� �׷��ȿ� ��� �׸��� �׷��ش�.
		g.drawImage(screenImage, 0, 0, null); // ��ũ�� �̹����� (0,0)��ġ�� �׷��ش�.
	}

	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null); // background�� ��ũ�� �̹����� �׷��ش�.
		// �ܼ� �̹��� ���.

		if (choice) {
			g.drawImage(player2, player2X, player2Y, null);

		}

		paintComponents(g);
		// add�κ��� �׷�����. // �������� �̹����� ��ư�� �׷��� �� �̿�.
		this.repaint();
	} // ���� ���۸����.


	public void ranImage() {

		if (randomNum[(int) ((Math.random() * 19) + 1)] == 1) {
			player2 = new ImageIcon("src/images/rock.png").getImage();
		} else if (randomNum[(int) (Math.random() * 19) + 1] == 2) {
			player2 = new ImageIcon("src/images/paper.png").getImage();
		} else if (randomNum[(int) (Math.random() * 19) + 1] == 3) {
			player2 = new ImageIcon("src/images/scissors.png").getImage();
		} else
			player2 = new ImageIcon("src/images/scissors.png").getImage();

	}
}
