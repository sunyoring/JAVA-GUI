package com.sunyo.calculator;

import javax.swing.JFrame;

public class Calculator extends JFrame {
	
	public Calculator() {
		setTitle("계산기");
		setSize(400,600);
		setVisible(true);
		setLocationRelativeTo(null);// null : 실행 시 가운데에 창이 뜸
		setResizable(false); // 창의 크기를 조절할 수 없게 함.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
}
