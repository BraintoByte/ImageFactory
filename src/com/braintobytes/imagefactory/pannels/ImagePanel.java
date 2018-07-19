package com.braintobytes.imagefactory.pannels;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.braintobytes.imagefactory.graphicalComponents.LoadingScreen;


/**
 * A threadded pannel for {@link LoadingScreen}, but really can be used for anything!
 * 
 */

public class ImagePanel extends JPanel implements Runnable {
	
	private int timeout;
	private int frame;
	private Thread thread;
	private boolean running;
	private BufferedImage[] images;
	
	
	/**
	 * 
	 */
	public ImagePanel() {}
	
	@Override
	protected void paintComponent(Graphics img) {
		super.paintComponent(img);
		img.drawImage(images[frame], 0, 0, this);
	}
	

	/**
	 * @param images
	 */
	public void setImages(BufferedImage[] images) {
		this.images = images;
	}
	

	/**
	 * @param timeout
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	
	/**
	 * @return
	 */
	public int getTimeout() {
		return timeout;
	}
	
	/**
	 * 
	 */
	public synchronized void start() {
		if(running){
			return;
		}
		
		running = true;
		this.thread = new Thread(this);
		this.thread.start();
	}


	/**
	 * 
	 */
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
	

	/**
	 * 
	 */
	public void stopAnimation(){
		this.running = false;
	}

	
	@Override
	public void run() {
		
		frame = 0;
		
		while(running){
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(frame >= images.length - 1){
				frame = 0;
			}
			
			frame++;
			this.repaint();
		}
	}
}