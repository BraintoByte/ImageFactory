package com.braintobyte.imagefactory.main;

import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.braintobyte.imagefactory.factoryUtils.ImageUtils;
import com.braintobyte.imagefactory.factoryUtils.UtilsGui;
import com.braintobyte.imagefactory.frames.Frame;
import com.braintobyte.imagefactory.pannels.TranslucentPannel;

public class Driver {
	public static void main(String[] args) {
		
		
//		BufferedImage imageRotation = ImageUtils.rotateImage(ImageUtils.loadImage(System.getProperty("user.dir") + "\\ImagesTest\\SecondPart.png"), 20);
//		
//		try {
//			ImageIO.write(imageRotation, "PNG", new File("K:\\EGit\\ImageFactory_V1.0\\ImagesTest\\", "test.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		BufferedImage image = ImageUtils.mergeImages(System.getProperty("user.dir") + "\\ImagesTest\\FirstPart.png", System.getProperty("user.dir") + "\\ImagesTest\\SecondPart.png");
//		
//		try {
//			ImageIO.write(image, "PNG", new File("K:\\EGit\\ImageFactory_V1.0\\ImagesTest\\", "test.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		TranslucentPannel pannel = new TranslucentPannel(200, 200);
//		pannel.setImageBackground(new ImageIcon("/home/law/git/ImageFactory_V1.0/ImagesTest/machine.png").getImage());
//		Frame frame = new Frame(0, 0, 500, 500);
//		
//		frame.setSize(400, 400);
//		frame.setLocationRelativeTo(null);
//		frame.setBgColor(Color.RED);
//		pannel.setVisible(true);
//		frame.addPannel(pannel);
//		frame.pack();
//		frame.setVisible(true);
//		
//		JFrame frame2 = new JFrame("");
//		JButton button = new JButton("click!");
//		
//		button.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				frame.setDecorated(!frame.isDecorated());
//			}
//		});
//		
//		button.setFont(UtilsGui.loadFont(System.getProperty("user.dir") + File.separator + "fonts" + File.separator + "android.ttf", 6));
//		
//		frame2.setSize(200, 200);
//		frame2.setLocationRelativeTo(null);
//		frame2.add(button);
//		frame2.pack();
//		frame2.setVisible(true);
		
	}
}