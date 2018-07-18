package com.braintobyte.imagefactory.pannels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TranslucentPannel extends JPanel {

	private static final long serialVersionUID = 3250333240641999424L;

	private int w, h;
	private boolean sizeInitialized;
	private Image bgImg;


	public TranslucentPannel(int w, int h) {
		this.w = w;
		this.h = h;
		this.sizeInitialized = true;
	}

	public TranslucentPannel(){}


	public void initialize(){

		setOpaque(false);
		setLayout(null);

		if(sizeInitialized){
			setSize(w, h);
		}
	}

	public void paintComponent(Graphics g){
		if(bgImg != null){
			g.drawImage(bgImg, 0, 0, null);
		}
	}

	public void setImageBackground(Image img){
		this.bgImg = img;
	}

	public void setWidth(int w) {
		this.w = w;
	}

	public void setHeight(int h) {
		this.h = h;
	}
	
	public void disposePannel() {

		removeAll();
		setVisible(false);

	}	
}





//	private void createTestingEnvironmnent() {
	//		setOpaque(false);
//		setLayout(new GridBagLayout());
//		GridBagConstraints constrains = new GridBagConstraints();
//		JPanel panel1 = new JPanel();
//		constrains.gridx = 0;
//		constrains.gridy = 0;
//		constrains.insets = new Insets(4, 4, 4, 4);
//		JLabel label1 = new JLabel("Hello1");
//		JLabel label2 = new JLabel("Hello2");
//		JLabel label3 = new JLabel("Hello3");
//		JLabel label4 = new JLabel("Hello4");
//		label1.setBackground(Color.RED);
//		label1.setForeground(Color.RED);
//		label2.setBackground(Color.BLUE);
//		label2.setForeground(Color.BLUE);
//		label3.setBackground(Color.CYAN);
//		label3.setForeground(Color.CYAN);
//		label4.setBackground(Color.GREEN);
//		label4.setForeground(Color.GREEN);
//
//		panel1.add(label1);
//		constrains.gridy++;
//
//		panel1.add(label2);
//		constrains.gridy++;
//
//		panel1.add(label3);
//		constrains.gridy++;
//
//		panel1.add(label4);
//		constrains.gridy++;
//
//		constrains.fill = GridBagConstraints.HORIZONTAL;
//
//		JPanel panel2 = new JPanel();
//		panel1.setOpaque(false);
//		panel2.setOpaque(false);
//
//		add(panel1);
//
//		for (int i = 0; i < 4; i++) {
//
//
//			JButton button;
//
//			if(i >= 0 && i < 2) {
//				button = shapes.makeButton(ShapeHandler.SHAPE.TRIANGLE, Rotation.DOWN, 50, 50);
//			}else {
//				button = shapes.makeButton(SHAPE.SQUARE, Rotation.NONE, 50, 50);
//			}
//			button.setBackground(Color.RED);
//			panel2.add(button);
//		}
//		constrains.gridy++;
//		constrains.gridx++;
//		constrains.fill = GridBagConstraints.HORIZONTAL;
//		constrains.gridwidth = GridBagConstraints.REMAINDER;
//		add(panel2);
//
//	}
