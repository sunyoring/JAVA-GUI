package com.sunyo.calculator;

import javax.swing.JFrame;

public class Calculator extends JFrame {
	
	public Calculator() {
		setTitle("����");
		setSize(400,600);
		setVisible(true);
		setLocationRelativeTo(null);// null : ���� �� ����� â�� ��
		setResizable(false); // â�� ũ�⸦ ������ �� ���� ��.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
}
