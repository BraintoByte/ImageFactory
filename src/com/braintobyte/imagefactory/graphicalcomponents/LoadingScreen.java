package com.braintobyte.imagefactory.graphicalcomponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import com.braintobyte.imagefactory.factoryUtils.ImageUtils;
import com.braintobyte.imagefactory.factoryUtils.Theme;
import com.braintobyte.imagefactory.factoryUtils.UtilsGui;
import com.braintobyte.imagefactory.pannels.ImagePanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
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

	public LoadingScreen(int min, int max, boolean undecorated) {
		this.min = min;
		this.max = max;
		pnlLoadingImage = new ImagePanel();
		pnlLoadingImage.setBounds(202, 0, 90, 60);
		pnlLoadingImage.setLayout(new BorderLayout(0, 0));
		this.undecorated = undecorated;
		makeComponents();
	}


	public LoadingScreen(int min, int max, BufferedImage[] image, boolean undecorated) {
		this.min = min;
		this.max = max;
		pnlLoadingImage = new ImagePanel();
		setLoadingImage(image);
		pnlLoadingImage.setBounds(202, (this.getHeight()/2) + 20, image[0].getWidth(), image[0].getHeight());
		pnlLoadingImage.setLayout(new BorderLayout(0, 0));
		this.undecorated = undecorated;
		makeComponents();
	}


	public void makeComponents(){

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

	public void setXnYImage(int x, int y) {
		pnlLoadingImage.setBounds(x, y, pnlLoadingImage.getWidth(), pnlLoadingImage.getHeight());
	}

	public void applyTheme(){
		UtilsGui.convertFrame(this, theme);
	}

	public void setLoadingImage(BufferedImage[] images){
		pnlLoadingImage.setImages(images);
	}

	public void setLoadingMessage(String message){
		this.lblLoadingMessage.setText("");
		this.lblLoadingMessage.setText(message);
	}

	public String getLastLoadingMessage() {
		return lblLoadingMessage.getText();
	}

	public JLabel getloadingMessageLabel(){
		return lblLoadingMessage;
	}

	public void setLoadingTimeout(int timeout){
		pnlLoadingImage.setTimeout(timeout);
	}

	public void updateProgress(int progress){
		if(progress >= min && progress <= max){
			pbar.setValue(progress);
		}
	}

	public int getUpdateProgress(){
		return pbar.getValue();
	}

	public ImagePanel getPnlLoadingImage() {
		return pnlLoadingImage;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public Theme getTheme() {
		return theme;
	}

	public int getLoadingTimeOut(){
		return pnlLoadingImage.getTimeout();
	}

	public void disposeIt(){
		this.setVisible(false);
		pnlLoadingImage.stopAnimation();
	}
}