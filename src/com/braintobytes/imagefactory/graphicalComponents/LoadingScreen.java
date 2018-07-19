package com.braintobytes.imagefactory.graphicalComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.braintobytes.imagefactory.factoryUtils.ImageUtils;
import com.braintobytes.imagefactory.factoryUtils.UtilsGui;
import com.braintobytes.imagefactory.factoryUtils.factoryCoreComponents.Theme;
import com.braintobytes.imagefactory.formats.SpriteSheet;
import com.braintobytes.imagefactory.pannels.ImagePanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.FlowLayout;

public class LoadingScreen extends JFrame {


	private JProgressBar pbar;
	private JPanel contentPane;
	private final int min;
	private final int max;
	private Theme theme;
	private JLabel lblLoadingMessage;
	private JPanel pnlMainStructure;
	private ImagePanel pnlLoadingImage;
	private boolean undecorated;

	public static String[] LOADINGPHRASES = { "Getting dependencies ready", "Making things happen", "More ", "More More", "More More More",
			"More More More and ", "More More More and More", "More More More and More.", "More More More and More..", "More More More and More...", 
			"Look at the fan, it's fun!", "We love our servers", "Getting wonderful things done", "More work", "More work.", "More work..", "More work..." };

	/**Makes a cool loading screen that can contain animation, get the {@link JFrame} to change various attributes
	 * <br><br><u>Resizable is set to false by default</u>
	 * 
	 * @param min
	 * @param max
	 * @param undecorated
	 * @throws IOException 
	 */
	public LoadingScreen(int min, int max, boolean undecorated, Color barColor) throws IOException {
		this.min = min;
		this.max = max;
		pnlLoadingImage = new ImagePanel();
		pnlLoadingImage.setBounds(202, 0, 90, 60);
		pnlLoadingImage.setLayout(new BorderLayout(0, 0));
		this.undecorated = undecorated;
		
		if(barColor == null){
			barColor = Color.GREEN;
		}
		
		makeComponents(barColor);
	}


	/**Makes a cool loading screen that can contain animation, get the {@link JFrame} to change various attributes
	 * <br><br><u>Resizable is set to false by default</u>
	 * 
	 * @param min
	 * @param max
	 * @param image
	 * @param undecorated
	 * @throws IOException 
	 */
	public LoadingScreen(int min, int max, BufferedImage[] image, boolean undecorated, Color barColor) throws IOException {
		this.min = min;
		this.max = max;
		pnlLoadingImage = new ImagePanel();
		setLoadingImage(image);
		pnlLoadingImage.setBounds(202, (this.getHeight()/2) + 20, image[0].getWidth(), image[0].getHeight());
		pnlLoadingImage.setLayout(new BorderLayout(0, 0));
		this.undecorated = undecorated;
		
		if(barColor == null){
			barColor = Color.GREEN;
		}
		
		makeComponents(barColor);
	}


	/**
	 * @throws IOException
	 */
	private void makeComponents(Color barColor) throws IOException{

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if(undecorated){
			setBounds(100, 100, 305, 120);
			setUndecorated(undecorated);
		}else{
			setBounds(100, 100, 322, 162);
		}

		setIconImage(ImageUtils.loadImage(System.getProperty("user.dir") + File.separator + "images" + File.separator + "BunkerIcon.png"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.pbar = new JProgressBar();
		contentPane.setLayout(new BorderLayout(0, 0));
		pnlMainStructure = new JPanel();
		pnlMainStructure.setLayout(null);
		lblLoadingMessage = new JLabel("");
		lblLoadingMessage.setBounds(10, 27, 226, 14);
		pbar.setMinimum(min);
		pbar.setMaximum(max);
		pbar.setValue(min);
		UIManager.put("nimbusOrange", new Color(0, 255, 182));
		pbar.setBounds(10, 64, 276, 38);
		setContentPane(contentPane);
		pnlMainStructure.add(lblLoadingMessage);
		pnlMainStructure.add(pbar);
		contentPane.add(pnlMainStructure);
		pnlMainStructure.add(pnlLoadingImage);

		if(theme != null){
			applyTheme();
		}
		
		setResizable(false);
		pnlLoadingImage.start();

	}

	/*
	 * Default:
	 * 
	 * 202, 0	or   202, (this.getHeight()/2) + 20
	 * 
	 */

	/**Set {@link SpriteSheet} position
	 * @param x
	 * @param y
	 */
	public void setXnYImage(int x, int y) {
		pnlLoadingImage.setBounds(x, y, pnlLoadingImage.getWidth(), pnlLoadingImage.getHeight());
	}

	/**
	 * 
	 */
	public void applyTheme(){
		UtilsGui.convertFrame(this, theme);
	}

	/**Set loading {@link SpriteSheet}
	 * @param images
	 */
	public void setLoadingImage(BufferedImage[] images){
		pnlLoadingImage.setImages(images);
	}
	
	/**Set loading {@link SpriteSheet}
	 * @param images
	 */
	public void setLoadingImage(SpriteSheet sheet){
		pnlLoadingImage.setImages(sheet.getSheet());
	}

	/**Update loading message
	 * @param message
	 */
	public void setLoadingMessage(String message){
		this.lblLoadingMessage.setText("");
		this.lblLoadingMessage.setText(message);
	}

	/**Get last loading message
	 * @return
	 */
	public String getLastLoadingMessage() {
		return lblLoadingMessage.getText();
	}

	/**Get the message label
	 * @return
	 */
	public JLabel getloadingMessageLabel(){
		return lblLoadingMessage;
	}

	/**Set the animation timeout of the image
	 * @param timeout
	 */
	public void setLoadingTimeout(int timeout){
		pnlLoadingImage.setTimeout(timeout);
	}

	/**Change the progress of the bar
	 * @param progress
	 */
	public void updateProgress(int progress){
		if(progress >= min && progress <= max){
			pbar.setValue(progress);
		}
	}

	/**
	 * @return
	 */
	public int getUpdatedProgress(){
		return pbar.getValue();
	}

	/**
	 * @return
	 */
	public ImagePanel getPnlLoadingImage() {
		return pnlLoadingImage;
	}

	/**
	 * @param theme
	 */
	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	/**
	 * @return
	 */
	public Theme getTheme() {
		return theme;
	}

	/**
	 * @return
	 */
	public int getLoadingTimeOut(){
		return pnlLoadingImage.getTimeout();
	}

	/**
	 * 
	 */
	public void disposeIt(){
		this.setVisible(false);
		pnlLoadingImage.stopAnimation();
	}
}