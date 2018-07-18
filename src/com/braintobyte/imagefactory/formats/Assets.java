package com.braintobyte.imagefactory.formats;

import java.awt.image.BufferedImage;
import java.util.TreeMap;

import com.braintobyte.imagefactory.factoryUtils.ImageUtils;

public class Assets {
	
	private static TreeMap<String, SpriteSheet> assets = new TreeMap<>();
	
	
	public static boolean addSheet(String name, SpriteSheet sheet){
		return assets.put(name, sheet) == null;
	}
	
	public static boolean addSheet(SpriteSheet sheet){
		return assets.put(sheet.getName(), sheet) == null;
	}
	
	public static SpriteSheet getSheet(String name){
		return assets.get(name);
	}
	
	public static boolean changeName(String oldName, String newName){
		SpriteSheet temp = assets.remove(oldName);
		return assets.put(oldName, temp) == null;
	}
	
	public static BufferedImage[] rotatedSequence(String path, int amounts, double rotationIncrement){
		
		BufferedImage image = ImageUtils.loadImage(path);
		BufferedImage[] images = new BufferedImage[amounts];
		double rotation = 0;
		
		for (int i = 0; i < images.length; i++) {
			rotation = rotation + rotationIncrement;
			images[i] = ImageUtils.rotateImage(image, rotation);
		}
		
		return images;
	
	}
}