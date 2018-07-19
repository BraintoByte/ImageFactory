package com.braintobytes.imagefactory.testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.io.File;
import java.nio.file.NoSuchFileException;
import java.security.SecureRandom;
import java.util.Stack;

import javax.swing.JButton;

import org.junit.Test;

import com.braintobytes.imagefactory.factoryUtils.factoryCoreComponents.Theme;
import com.braintobytes.imagefactory.factoryUtils.factoryCoreComponents.ThemeComponent;
import com.braintobytes.imagefactory.factoryUtils.factoryCoreComponents.Theme.COMPONENTS;

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
			theme.load(System.getProperty("user.dir") + File.separator + "TestSerialization" + File.separator + "To serialize", "serial");
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

	@Test
	public void testAutoSerializationExtensive(){

		Theme[] themes = new Theme[200];

		for (int i = 0; i < themes.length; i++) {
			themes[i] = new Theme(i + "");
		}

		SecureRandom rand = new SecureRandom();
		int r = rand.nextInt(199);

		ThemeComponent tc = new ThemeComponent(COMPONENTS.JButton.toString(), JButton.class);
		tc.addColor("background", new Color(22, 225, 224, 250));
		tc.addColor("foreground", new Color(22, 225, 224, 250));
		tc.setFont(themes[r].getComponentFont(COMPONENTS.JButton));
		themes[r].changeComponent(COMPONENTS.JButton, tc);
		
		System.out.println("creating compoenents");

		for (int i = 0; i < themes.length; i++) {
			themes[i].save(System.getProperty("user.dir") + File.separator + "TestSerialization", "serial");
		}

		Stack<ThemeComponent[]> comps = new Stack<>();

		for (int i = 0; i < themes.length; i++) {
			try {
				themes[i].load(System.getProperty("user.dir") + File.separator + "TestSerialization" + File.separator + "To serialize", "serial");
				comps.push(themes[i].getAllComponentsForSerialization());
			} catch (NoSuchFileException e) {
				e.printStackTrace();
			}
		}
		
		int surprise = 0;

		while(!comps.isEmpty()){
			


			ThemeComponent[] modified = comps.pop();
			ThemeComponent[] original = comps.pop();
			
			assertTrue(modified.length == original.length);
			
			if(surprise == r){
				assertFalse(areEqual(original, modified));
			}else{
				assertTrue(areEqual(original, modified));
			}

			assertTrue(areEqual(modified, modified));
			assertTrue(areEqual(original, original));

			
			surprise = surprise + 2;
		}
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
					if(originalC[j].getRGB() != modifiedC[j].getRGB()){
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