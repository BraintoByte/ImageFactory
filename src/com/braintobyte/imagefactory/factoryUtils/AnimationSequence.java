package com.braintobyte.imagefactory.factoryUtils;

import java.awt.image.BufferedImage;

public class AnimationSequence {
	
	private BufferedImage[] sheet;
	private int height = 0;
	private int width = 0;
	private int frameWidth;
	private int frameHeight;
	
	
	public AnimationSequence(BufferedImage[] sheet, int width, int height, int frameWidth, int frameHeight){
		
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		this.frameWidth = width;
		this.frameHeight = height;
		
	}
	
	public AnimationSequence(BufferedImage[] sheet, int width, int height){
		
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		this.frameWidth = width / sheet.length;
		this.frameHeight = height / sheet.length;
		
	}
	
	
	public void addFrameToSequence(BufferedImage frame){
		
		int lastIndex = sheet.length - 1;
		
		if(sheet[sheet.length - 1] != null){
			lastIndex = increaseCapacity(sheet.length * 2);
		}
		
		sheet[lastIndex] = frame;
		
	}
	
	public BufferedImage replaceFrameAtIndex(BufferedImage frame, int index){
		
		if(index < sheet.length){
			BufferedImage temp = sheet[index];
			sheet[index] = frame;
			return temp;
		}
		return null;
	}
	
	public BufferedImage removeFrameAtIndex(int index){
		if(index < sheet.length){
			BufferedImage temp = sheet[index];
			sheet[index] = null;
			rescale(index, index + 1);
			return temp;
		}
		return null;
	}
	
	
	
	private void rescale(int fromI, int toI){
		
		BufferedImage[] temp = sheet;
		
		for (int i = fromI; i < sheet.length; i++) {
			sheet[i] = temp[toI];
			toI++;
		}
	}
	
	private int increaseCapacity(int capacity){
		
		BufferedImage[] temp = new BufferedImage[capacity];
		int lastIndex = sheet.length - 1;
		
		for (int i = 0; i < sheet.length; i++) {
			temp[i] = sheet[i];
		}
		
		sheet = temp;
		return lastIndex;
		
	}
	
	
	public int getSize(){
		return sheet.length;
	}
	
	public int getFrameWidth(){
		return frameWidth;
	}
	
	public int getFrameHeight(){
		return frameHeight;
	}
	
	public int getTotalSizeOfSequenceSheet(){
		return frameWidth * frameHeight;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
}