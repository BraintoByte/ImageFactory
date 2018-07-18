package com.braintobyte.imagefactory.formats;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.TreeMap;

import com.braintobyte.imagefactory.factoryUtils.ImageUtils;

public class Assets {
	
	private static TreeMap<String, SpriteSheet> assets = new TreeMap<>();
	
	
	/**
	 * Adds a {@link SpriteSheet} to the assets
	 * 
	 * @param name
	 * @param sheet
	 * @return true if successful
	 */
	public static boolean addSheet(String name, SpriteSheet sheet){
		return assets.put(name, sheet) == null;
	}
	
	/**Adds a {@link SpriteSheet} to the assets
	 * 
	 * @param sheet
	 * @return true if successful
	 */
	public static boolean addSheet(SpriteSheet sheet){
		return assets.put(sheet.getName(), sheet) == null;
	}
	
	/**Gets a {@link SpriteSheet} to the assets
	 * @param name
	 * @return null if {@link SpriteSheet} does not exists
	 */
	public static SpriteSheet getSheet(String name){
		return assets.get(name);
	}
	
	/**Change name to {@link SpriteSheet} inside the Assets
	 * @param oldName
	 * @param newName
	 * @return
	 */
	public static boolean changeName(String oldName, String newName){
		SpriteSheet temp = assets.remove(oldName);
		return assets.put(oldName, temp) == null;
	}
	
	/**Rotates an image sequentially, this is not so different from {@link ImageUtils#rotateImage(BufferedImage, double)} but it allows
	 * for a sequential multiple rotation of the image it self producing {@link BufferedImage[]}
	 * @param path
	 * @param amounts
	 * @param rotationIncrement
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage[] rotatedSequence(String path, int amounts, double rotationIncrement) throws IOException{
		
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