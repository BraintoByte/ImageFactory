package com.braintobyte.imagefactory.formats;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import com.braintobyte.imagefactory.factoryUtils.ImageUtils;

public class SpriteSheet {

	private BufferedImage image;
	private BufferedImage[] sheet;
	private int totalSprite;
	private int imagesPerX;
	private int imagesPerY;
	private int from;
	private int to;
	private String name;



	public SpriteSheet(String path, int imagesPerX, int imagesPerY) {
		this.image = ImageUtils.loadImage(path);
		this.totalSprite = imagesPerX * imagesPerY;
		this.imagesPerX = imagesPerX;
		this.imagesPerY = imagesPerY;
		this.from = -1;
		this.to = -1;
		this.name = new File(path).getName();
	}
	
	public SpriteSheet(BufferedImage image, int imagesPerX, int imagesPerY, String name) {
		this.image = image;
		this.totalSprite = imagesPerX * imagesPerY;
		this.imagesPerX = imagesPerX;
		this.imagesPerY = imagesPerY;
		this.from = -1;
		this.to = -1;
		this.name = name;
	}
	

	public void setFromTo(int from, int to){
		if(from >= 0 && to > 0){
			this.from = from;
			this.to = to + 1;
		}
	}
	
	public void addSheet(BufferedImage[] sheet){
		
		BufferedImage[] combined = new BufferedImage[this.sheet.length + sheet.length];
		
		
		for (int i = 0; i < this.sheet.length; i++) {
			
			combined[i] = this.sheet[i];

		}
		
		int j = 0;
		
		for (int i = this.sheet.length; i < combined.length; i++) {
			
			combined[i] = sheet[j];
			j++;
			
		}
		
		this.sheet = combined;
		
	}
	
	
	public void scaleEachFrame(int w, int h) {
		
		for (int i = 0; i < sheet.length; i++) {
			sheet[i] = resize(sheet[i], w, h);
		}
	}
	
	public void rescaleImage(int w, int h) {
		image = resize(image, w, h);
	}
	
	
	private BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
	
	
	public void makeSheet(){
		
		int x = 0;
		int y = 0;
		int indY = image.getHeight() / imagesPerY;
		int indX = image.getWidth() / imagesPerX;

		if((from + to) != -2 && (to - from) != totalSprite){
			totalSprite = to - from;
			
			for (int i = from; i < to; i++) {
				x = x + indX;
				
				if(x == image.getWidth()){
					x = 0;
					y = y + indY;
				}
			}
		}

		sheet = new BufferedImage[totalSprite];


		for (int i = 0; i < sheet.length; i++) {
			sheet[i] = ImageUtils.crop(x, y, indX, indY, image);
			x = x + indX;

			if(x == image.getWidth()){
				x = 0;
				y = y + indY;
			}
		}
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public BufferedImage[] getSheet() {
		return sheet;
	}
	
	protected String getName(){
		return this.name;
	}
}