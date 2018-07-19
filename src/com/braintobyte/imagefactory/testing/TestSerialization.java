package com.braintobyte.imagefactory.testing;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.File;
import java.nio.file.NoSuchFileException;

import javax.swing.JButton;

import org.junit.Test;

import com.braintobyte.imagefactory.factoryUtils.GenUtils;
import com.braintobyte.imagefactory.factoryUtils.UtilsGui;
import com.braintobyte.imagefactory.factoryUtils.factoryCoreComponents.Theme;
import com.braintobyte.imagefactory.factoryUtils.factoryCoreComponents.ThemeComponent;
import com.braintobyte.imagefactory.factoryUtils.factoryCoreComponents.Theme.COMPONENTS;

public class TestSerialization {
	
	
	@Test
	public void testAutoSerialization(){
		
		Theme theme = new Theme("To serialize");
		
		ThemeComponent tc = new ThemeComponent(COMPONENTS.JButton.toString(), JButton.class);
		tc.addColor("background", new Color(22, 225, 224, 250));
		tc.addColor("foreground", new Color(22, 225, 224, 250));
		tc.setFont(theme.getComponentFont(COMPONENTS.JButton));
		theme.changeComponent(COMPONENTS.JButton, tc);
		
		theme.save(System.getProperty("user.dir") + File.separator + "TestSerialization", "serial");
		
		Theme clone = new Theme("To serialize");
		
		try {
			theme.load(System.getProperty("user.dir") + File.separator + "TestSerialization", "serial");
		} catch (NoSuchFileException e) {
			e.printStackTrace();
		}
		
		ThemeComponent[] modified = theme.getAllComponentsForSerialization();
		ThemeComponent[] original = clone.getAllComponentsForSerialization();
		
		assertTrue(modified.length == original.length);
		assertFalse(areEqual(original, modified));
		assertTrue(areEqual(modified, modified));
		assertTrue(areEqual(original, original));
	}
	
	
	public boolean areEqual(ThemeComponent[] original, ThemeComponent[] modified){
		
		System.out.println("\t\t\t\t\t\t\t\tThese values are not equal:");
		
		for (int i = 0; i < original.length; i++) {
			
			if(original[i] != modified[i]){
				
				Color[] originalC = original[i].getColorsValuesToArray();
				Color[] modifiedC = modified[i].getColorsValuesToArray();
				
				if(originalC.length != modifiedC.length){
					System.out.println("Original: " + original[i].getName() + " modified: " + modified[i].getName());
					System.out.println("Original: " + originalC.length + " modified: " + modifiedC.length);
					return false;
				}
				
				
				for (int j = 0; j < originalC.length; j++) {
					if(originalC[i].getRGB() != modifiedC[i].getRGB()){
						System.out.println("Original: " + original[i].getName() + " modified: " + modified[i].getName());
						System.out.println("Original: " + originalC[i].getRGB() + " modified: " + modifiedC[i].getRGB());
						return false;
					}
				}
			}
			
			if(original[i].getBorder() != modified[i].getBorder()){
				System.out.println("Original: " + original[i].getName() + " modified: " + modified[i].getName());
				System.out.println("Original: " + original[i].getBorder() + " modified: " + modified[i].getBorder());
			}
			
			if(original[i].getFont().getFontName() != modified[i].getFont().getFontName() && original[i].getFont().getSize() != modified[i].getFont().getSize()){
				System.out.println("Original: " + original[i].getName() + " modified: " + modified[i].getName());
				System.out.println("Original: " + original[i].getFont() + " modified: " + modified[i].getFont());
			}
		}
		return true;
	}
}