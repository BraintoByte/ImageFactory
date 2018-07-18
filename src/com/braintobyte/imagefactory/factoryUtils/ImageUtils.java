package com.braintobyte.imagefactory.factoryUtils;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {


	/**
	 * Merges two (2) images together and returns a buffered image, uses {@link com.braintobyte.imagefactory.factoryUtils#loadImage }
	 * 
	 * @param foreImage
	 * @param backImage
	 * @return Merged images
	 * @throws IOException if either one of the images do not exist
	 */
	public static BufferedImage mergeImages(String foreImage, String backImage) throws IOException{

		BufferedImage fI = loadImage(foreImage);
		BufferedImage bI = loadImage(backImage);

		int w = Math.max(fI.getWidth(), bI.getWidth());
		int h = Math.max(fI.getHeight(), bI.getHeight());

		BufferedImage merged = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		Graphics graphic = merged.getGraphics();

		graphic.drawImage(fI, 0, 0, null);
		graphic.drawImage(bI, 0, 0, null);

		return merged;

	}

	/**
	 * Merges two (2) images together and returns a buffered image
	 * 
	 * @param foreImage
	 * @param backImage
	 * @return Merged images
	 */
	public static BufferedImage mergeImages(BufferedImage foreImage, BufferedImage backImage){

		int w = Math.max(foreImage.getWidth(), backImage.getWidth());
		int h = Math.max(foreImage.getHeight(), backImage.getHeight());

		BufferedImage merged = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		Graphics graphic = merged.getGraphics();

		graphic.drawImage(foreImage, 0, 0, null);
		graphic.drawImage(backImage, 0, 0, null);

		return merged;

	}

	/**
	 * @param Rotates and image how many degrees you want, uses {@link java.lang.Math#toRadians(double) }
	 * @param rotation
	 * @return
	 */
	public static BufferedImage rotateImage(BufferedImage image, double rotation){

		AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(rotation), image.getWidth() / 2, image.getHeight() / 2);

		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		image = op.filter(image, null);
		return image;

	}


	/**
	 * Crops an image to your desires
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param image
	 * @return
	 */
	public static BufferedImage crop(int x, int y, int w, int h, BufferedImage image){
		return image.getSubimage(x, y, w, h);
	}


	/**
	 * Imports and image from file
	 * 
	 * @param path
	 * @return imported image
	 * @throws IOException if path does not exists
	 */
	public static BufferedImage loadImage(String path) throws IOException{

		return ImageIO.read(new File(path));

		//		try{
		//			return ImageIO.read(new File(path));
		//		}catch(IOException e){
		//			e.printStackTrace();
		//		}
	}


	//WORK IN PROGRESS

	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param xIncrement
	 * @param yIncrement
	 * @param image
	 * @param xAmount
	 * @param yAmount
	 * @return
	 */
	//	public static BufferedImage[] multiCropXincrement(int x, int y, int w, int h, int xIncrement, int yIncrement, BufferedImage image, int xAmount, int yAmount){
	//
	//		int totalSequence = xAmount * yAmount;
	//		
	//
	//		BufferedImage[] sequence = new BufferedImage[totalSequence];
	//		
	//		int xOriginal = x;
	//		int xAmounts = 0;
	//		
	//		for (int i = 0; i < totalSequence; i++) {
	//
	//			sequence[totalSequence] = crop(x, y, w, h, image);
	//			x += xIncrement;
	//			xAmounts++;
	//
	//			if(xAmounts == xAmount){
	//				
	//				xAmounts = 0;
	//				x = xOriginal;
	//				y += yIncrement;
	//				
	//			}
	//		}
	//
	//		return sequence;
	//	}
}