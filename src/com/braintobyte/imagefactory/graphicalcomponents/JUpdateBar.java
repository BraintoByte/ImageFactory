package com.braintobyte.imagefactory.graphicalcomponents;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.omg.CORBA.Bounds;

public class JUpdateBar extends JPanel implements Runnable {

	private StringBuilder displayBuffer;
	private StringBuilder interBuffer;
	private StringBuilder lastBuffer;
	private String message;
	private JLabel lblAnimation;
	private Thread thread;
	private boolean running;

	private int xLblStatus;
	private int speedStatus;
	private int x, y, w, h;


	public JUpdateBar(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		init();
	}

	private void init(){
		this.setBounds(x, y, w, h);
		displayBuffer = new StringBuilder("");
		lastBuffer = new StringBuilder();
		lblAnimation = new JLabel(displayBuffer.toString());
		lblAnimation.setForeground(Color.CYAN);
		xLblStatus = this.getWidth();
		speedStatus = 20;
		lblAnimation.setBounds(-10, 1, 35, 14);
		this.add(lblAnimation);
		this.setBackground(Color.BLACK);
	}


	protected void animate(){

		try {
			Thread.sleep(speedStatus);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		xLblStatus--;
		lblAnimation.setBounds(xLblStatus, 3, lblAnimation.getPreferredSize().width, lblAnimation.getPreferredSize().height);

//		System.out.println(xLblStatus);

		if(xLblStatus % 2 == 0){
			lastBuffer.append(" ");
		}

		if(xLblStatus == (-lblAnimation.getPreferredSize().width)){

			xLblStatus = this.getWidth();

			String[] splitted = displayBuffer.toString().split("\\s+");

			displayBuffer.setLength(0);
			displayBuffer = new StringBuilder();

			for (int i = 0; i < splitted.length; i++) {
				displayBuffer.append(splitted[i] + " ");
				System.out.println(splitted[i]);
			}
		}

		if(interBuffer != null && xLblStatus > this.getWidth()/2){
			displayBuffer.append(interBuffer.toString());
			System.out.println(interBuffer.toString());
			interBuffer.setLength(0);
			lastBuffer.setLength(0);
			interBuffer = null;
			lastBuffer = new StringBuilder();
		}

		lblAnimation.setText(displayBuffer.toString());
		
	}

	//	xLblStatus--;
	//	lblAnimation.setBounds(xLblStatus, 3, lblAnimation.getPreferredSize().width, lblAnimation.getPreferredSize().height);
	//
	//	if(xLblStatus == (-lblAnimation.getPreferredSize().width)){
	//		xLblStatus = 500;
	//		if(concatBuf != null){
	//			display.setLength(0);
	//			display = new StringBuilder(concatBuf.toString());
	//			concatBuf.setLength(0);
	//			concatBuf = null;
	//		}
	//	}




	//	if(buffer != null){
	//		
	//		
	//		
	//		if(concatBuf == null){
	//
	//			concatBuf = new StringBuilder(display.toString() + " " + buffer.toString());
	//
	//		}else{
	//
	//			concatBuf.append(buffer.toString());
	//
	//		}
	//
	//		for (int i = 0; i < lblAnimation.getPreferredSize().width; i++) {
	//
	//			display.append(" ");
	//
	//		}
	//
	//		display.append(buffer.toString());
	//
	//		buffer.setLength(0);
	//		buffer = null;
	//		
	//	}

	protected void setSpeedStatus(int speedStatus) {

		if(speedStatus < 0){
			//Display error message
			return;	
		}

		this.speedStatus = speedStatus;
	}

	public void updateDisplay(String update){
		displayBuffer.setLength(0);
		displayBuffer = new StringBuilder(update);
	}


	public void appendToDisplay(String toAppend) {
		if(interBuffer == null){
			interBuffer = new StringBuilder();
			interBuffer.append(lastBuffer.toString() + toAppend);
		}else{
			if(xLblStatus < this.getWidth()/2){
				String tmp = interBuffer.toString().trim();
				interBuffer.setLength(0);
				interBuffer = new StringBuilder(interBuffer + " " + toAppend);
			}
		}
	}
	
	public String getDisplayedValue() {
		return displayBuffer.toString();
	}


	//	if(xLblStatus == (-lblAnimation.getPreferredSize().width) || displayBuffer.toString().isEmpty()){
	//
	//		this.displayBuffer.append(toAppend);
	//
	//		if(displayBuffer.toString().isEmpty()){
	//			xLblStatus = 500;
	//		}
	//		
	//	}else{
	//
	//		if(interBuffer == null){
	//			interBuffer = new StringBuilder();
	//		}
	//
	//		interBuffer.append(toAppend);
	//
	//	}

	public void refresh(){
		xLblStatus = this.getWidth();
		displayBuffer.setLength(0);
		displayBuffer = new StringBuilder();
	}



	public synchronized void start() {

		if(running){
			return;
		}

		running = true;
		this.thread = new Thread(this);
		this.thread.start();
	}

	public synchronized void stop(){

		if(!running){
			return;
		}

		running = false;

		try{
			thread.join();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(running){
			animate();
		}
	}
}