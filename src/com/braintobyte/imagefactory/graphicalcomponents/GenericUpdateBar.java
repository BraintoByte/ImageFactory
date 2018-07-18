package com.braintobyte.imagefactory.graphicalcomponents;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.omg.CORBA.Bounds;

public class GenericUpdateBar extends JPanel implements Runnable {

	private StringBuilder displayBuffer;
	private StringBuilder interBuffer;
	private StringBuilder lastBuffer;
	private JLabel lblAnimation;
	private Thread thread;
	private boolean running;
	private boolean changed;

	private int xLblStatus;
	private int speedStatus;
	private int x, y, w, h;


	public GenericUpdateBar(int x, int y, int w, int h) {
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
		xLblStatus = this.getWidth();
		speedStatus = 20;
		lblAnimation.setBounds(-10, 1, 35, 14);
		this.add(lblAnimation);
	}


	public void animate(){

		try {
			Thread.sleep(speedStatus);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		xLblStatus--;
		lblAnimation.setBounds(xLblStatus, 3, lblAnimation.getPreferredSize().width, lblAnimation.getPreferredSize().height);


		if(xLblStatus % 2 == 0){
			lastBuffer.append(" ");
		}

		if(xLblStatus == (-lblAnimation.getPreferredSize().width)){

			xLblStatus = this.getWidth();


			if(changed){

				String[] splitted = displayBuffer.toString().split("\\s+");

				displayBuffer.setLength(0);
				displayBuffer = new StringBuilder();

				for (int i = 0; i < splitted.length; i++) {
					displayBuffer.append(splitted[i] + " ");
				}

				changed = false;

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}


		if(interBuffer != null && xLblStatus > this.getWidth()/2){
			displayBuffer.append(interBuffer.toString());
			interBuffer.setLength(0);
			lastBuffer.setLength(0);
			interBuffer = null;
			lastBuffer = new StringBuilder();
		}

		lblAnimation.setText(displayBuffer.toString());
		lblAnimation.setBounds(xLblStatus, 3, lblAnimation.getPreferredSize().width, lblAnimation.getPreferredSize().height);

	}

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
		changed = true;
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
		changed = true;
	}

	public String getDisplayedValue() {
		return displayBuffer.toString();
	}


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