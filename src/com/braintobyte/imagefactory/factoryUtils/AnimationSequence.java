package com.braintobyte.imagefactory.factoryUtils;

import java.awt.image.BufferedImage;

public class AnimationSequence {
	
	private BufferedImage[] sheet;
	private int height = 0;
	private int width = 0;
	private int frameWidth;
	private int frameHeight;
	
	
	/**
	 * @param sheet
	 * @param width
	 * @param height
	 * @param frameWidth
	 * @param frameHeight
	 */
	public AnimationSequence(BufferedImage[] sheet, int width, int height, int frameWidth, int frameHeight){
		
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		this.frameWidth = width;
		this.frameHeight = height;
		
	}
	
	/**
	 * @param sheet
	 * @param width
	 * @param height
	 */
	public AnimationSequence(BufferedImage[] sheet, int width, int height){
		
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		this.frameWidth = width / sheet.length;
		this.frameHeight = height / sheet.length;
		
	}
	
	
	/**
	 * @param frame
	 */
	public void addFrameToSequence(BufferedImage frame){
		
		int lastIndex = sheet.length - 1;
		
		if(sheet[sheet.length - 1] != null){
			lastIndex = increaseCapacity(sheet.length * 2);
		}
		
		sheet[lastIndex] = frame;
		
	}
	
	/**
	 * @param frame
	 * @param index
	 * @return
	 */
	public BufferedImage replaceFrameAtIndex(BufferedImage frame, int index){
		
		if(index < sheet.length){
			BufferedImage temp = sheet[index];
			sheet[index] = frame;
			return temp;
		}
		return null;
	}
	
	/**
	 * @param index
	 * @return
	 */
	public BufferedImage removeFrameAtIndex(int index){
		if(index < sheet.length){
			BufferedImage temp = sheet[index];
			sheet[index] = null;
			rescale(index, index + 1);
			return temp;
		}
		return null;
	}
	
	
	
	/**
	 * @param fromI
	 * @param toI
	 */
	private void rescale(int fromI, int toI){
		
		BufferedImage[] temp = sheet;
		
		for (int i = fromI; i < sheet.length; i++) {
			sheet[i] = temp[toI];
			toI++;
		}
	}
	
	/**
	 * @param capacity
	 * @return
	 */
	private int increaseCapacity(int capacity){
		
		BufferedImage[] temp = new BufferedImage[capacity];
		int lastIndex = sheet.length - 1;
		
		for (int i = 0; i < sheet.length; i++) {
			temp[i] = sheet[i];
		}
		
		sheet = temp;
		return lastIndex;
		
	}
	
	
	/**
	 * @return
	 */
	public int getSize(){
		return sheet.length;
	}
	
	/**
	 * @return
	 */
	public int getFrameWidth(){
		return frameWidth;
	}
	
	/**
	 * @return
	 */
	public int getFrameHeight(){
		return frameHeight;
	}
	
	/**
	 * @return
	 */
	public int getTotalSizeOfSequenceSheet(){
		return frameWidth * frameHeight;
	}
	
	/**
	 * @return
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * @return
	 */
	public int getWidth() {
		return width;
	}
}