package com.braintobyte.imagefactory.formats;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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



	/**Makes a {@link SpriteSheet}, basically a {@link BufferedImage[]} that allows you to store images sequentially then hopefully animate them
	 * you can name it to keep it organized and easily retrieved
	 * @param path
	 * @param imagesPerX
	 * @param imagesPerY
	 * @throws IOException
	 */
	public SpriteSheet(String path, int imagesPerX, int imagesPerY) throws IOException {
		this.image = ImageUtils.loadImage(path);
		this.totalSprite = imagesPerX * imagesPerY;
		this.imagesPerX = imagesPerX;
		this.imagesPerY = imagesPerY;
		this.from = -1;
		this.to = -1;
		this.name = new File(path).getName();
	}

	/**Makes a {@link SpriteSheet}, basically a {@link BufferedImage[]} that allows you to store images sequentially then hopefully animate them
	 * you can name it to keep it organized and easily retrieved
	 * @param image
	 * @param imagesPerX
	 * @param imagesPerY
	 * @param name
	 */
	public SpriteSheet(BufferedImage image, int imagesPerX, int imagesPerY, String name) {
		this.image = image;
		this.totalSprite = imagesPerX * imagesPerY;
		this.imagesPerX = imagesPerX;
		this.imagesPerY = imagesPerY;
		this.from = -1;
		this.to = -1;
		this.name = name;
	}


	/**Makes a {@link SpriteSheet}, basically a {@link BufferedImage[]} that allows you to store images sequentially then hopefully animate them
	 * you can name it to keep it organized and easily retrieved
	 * @param path
	 * @param imagesPerX
	 * @param imagesPerY
	 * @throws IOException
	 */
	public SpriteSheet(String[] path, int imagesPerX, int imagesPerY) throws IOException {
		this.sheet = new BufferedImage[path.length];
		for (int i = 0; i < path.length; i++) {
			this.sheet[i] = ImageUtils.loadImage(path[i]);
		}
		this.totalSprite = imagesPerX * imagesPerY;
		this.imagesPerX = imagesPerX;
		this.imagesPerY = imagesPerY;
		this.from = -1;
		this.to = -1;
		this.name = "";
	}

	/**Makes a {@link SpriteSheet}, basically a {@link BufferedImage[]} that allows you to store images sequentially then hopefully animate them
	 * you can name it to keep it organized and easily retrieved
	 * @param sheet
	 * @param imagesPerX
	 * @param imagesPerY
	 * @throws IOException
	 */
	public SpriteSheet(BufferedImage[] sheet, int imagesPerX, int imagesPerY) throws IOException {
		this.sheet = sheet;
		this.totalSprite = imagesPerX * imagesPerY;
		this.imagesPerX = imagesPerX;
		this.imagesPerY = imagesPerY;
		this.from = -1;
		this.to = -1;
		this.name = "";
	}

	/**Makes a {@link SpriteSheet}, basically a {@link BufferedImage[]} that allows you to store images sequentially then hopefully animate them
	 * you can name it to keep it organized and easily retrieved
	 * @param sheet
	 * @param imagesPerX
	 * @param imagesPerY
	 * @param name
	 */
	public SpriteSheet(BufferedImage[] sheet, int imagesPerX, int imagesPerY, String name) {
		this.sheet = sheet;
		this.totalSprite = imagesPerX * imagesPerY;
		this.imagesPerX = imagesPerX;
		this.imagesPerY = imagesPerY;
		this.from = -1;
		this.to = -1;
		this.name = name;
	}


	public void addImage(BufferedImage image){
		if(image == null){
			throw new NullPointerException();
		}
		addSheet(new BufferedImage[]{image});
	}

	/**
	 * @param sheet
	 */
	public void addSheet(BufferedImage[] sheet){
		
		if(this.sheet == null){
			this.sheet = new BufferedImage[1];
		}

		if(sheet != null){

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
	}


	/**Scale each frame of the {@link SpriteSheet}
	 * @param w
	 * @param h
	 */
	public void scaleEachFrame(int w, int h) {

		for (int i = 0; i < sheet.length; i++) {
			sheet[i] = resize(sheet[i], w, h);
		}
	}

	/**Scale the last frame of the {@link SpriteSheet}
	 * @param w
	 * @param h
	 */
	public void rescaleImage(int w, int h) {
		image = resize(image, w, h);
	}


	/**Scale an image
	 * 
	 * @param img
	 * @param newW
	 * @param newH
	 * @return
	 */
	private BufferedImage resize(BufferedImage img, int newW, int newH) { 
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}


	/**Makes the {@link SpriteSheet} to be used, make sure you call it every time you put in a new image an image
	 * This is mainly used for efficiency, no need to settle the {@link BufferedImage[]} into a {@link SpriteSheet} right away
	 * do as many operations as you would like on the {@link BufferedImage[]} and them create your {@link SpriteSheet}!
	 * 
	 * <br><br>From index must be >= 0 and to index must be > 0
	 * 
	 * @param from index
	 * @param to index
	 */
	public void makeSheet(int from, int to){

		if(from >= 0 && to > 0){
			this.from = from;
			this.to = to + 1;

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
	}

	/**Gets the last image added
	 * @return {@link BufferedImage}
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**Gets your {@link SpriteSheet}
	 * @return {@link BufferedImage[]}
	 */
	public BufferedImage[] getSheet() {
		return sheet;
	}

	/**Returns the name of the sheet
	 * @return name
	 */
	protected String getName(){
		return this.name;
	}


	//For testing//

	//	/**
	//	 * @param from
	//	 * @param to
	//	 */
	//	public void setFromTo(int from, int to){
	//		if(from >= 0 && to > 0){
	//			this.from = from;
	//			this.to = to + 1;
	//		}
	//	}
}