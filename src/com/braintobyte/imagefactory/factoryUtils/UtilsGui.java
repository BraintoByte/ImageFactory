package com.braintobyte.imagefactory.factoryUtils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import com.braintobyte.imagefactory.factoryUtils.factoryCoreComponents.Theme;
import com.braintobyte.imagefactory.graphicalcomponents.JUpdateBar;


public class UtilsGui {

	public static void convertFrame(JFrame frame, Theme theme){

		Component[] comp = frame.getContentPane().getComponents();

		for (int i = 0; i < comp.length; i++) {

			String classTypeStr = getClassTypeInString(comp[i]);

			if(theme.getVerifyComponentClassType(classTypeStr, comp[i])){

				Color back = theme.getComponentBackground(classTypeStr);
				Color fore = theme.getComponentForeground(classTypeStr);
				Font font = theme.getComponentFont(classTypeStr);

				if(back != null){
					comp[i].setBackground(back);
				}

				if(fore != null){
					comp[i].setForeground(fore);
				}

				if(font != null){
					comp[i].setFont(font);
				}
			}
		}
	}
	
	public static Font loadFont(String path, float size){

		try{

//			GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
//			InputStream in = UtilsGui.class.getClass().getResourceAsStream(path);		//Use file separator to make it an absolute resource in front of path!
//			Font font = Font.createFont(Font.TRUETYPE_FONT, in).deriveFont(Font.PLAIN, size);
//			e.registerFont(font);
//
//
//			return font;
			
			return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size);


		}catch(FontFormatException | IOException e){
			e.printStackTrace();
		}

		return null;
	}


	public static ImageIcon loadImage(String path){
		URL icon = UtilsGui.class.getResource(path);
		return new ImageIcon(icon);
	}

	private static String getClassTypeInString(Object component){
		String[] tmp = component.getClass().getName().split("\\.");
		return tmp[tmp.length - 1];
	}

	public static Theme getDefaultTheme(){
		return new Theme("DEFAULT");
	}
}