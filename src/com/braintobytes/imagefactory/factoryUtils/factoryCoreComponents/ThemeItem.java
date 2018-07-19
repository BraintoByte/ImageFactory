package com.braintobytes.imagefactory.factoryUtils.factoryCoreComponents;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;

import javax.swing.border.Border;

public class ThemeItem implements Serializable {

	
	private static final long serialVersionUID = 3192137873698238675L;
	private final String name;
	private String directory;
	private Color[] colors;
	private String[] colorsKeyset;
	private Font font;
	private Border border;
	private Class<?> classType;
	
	
	
	
	public ThemeItem(String name, Color[] colors, String[] colorsKeyset, Font font, Border border, String directory, Class<?> classType) {
		this.name = name;
		this.colors = colors;
		this.colorsKeyset = colorsKeyset;
		this.font = font;
		this.border = border;
		this.directory = directory;
		this.classType = classType;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
	public Color[] getColors() {
		return colors;
	}

	public void setColors(Color[] colors) {
		this.colors = colors;
	}
	
	public String[] getColorsKeyset() {
		return colorsKeyset;
	}
	
	public void setColorsKeyset(String[] colorsKeyset) {
		this.colorsKeyset = colorsKeyset;
	}
	
	public Font getFont() {
		return font;
	}
	
	public void setFont(Font font) {
		this.font = font;
	}

	public Border getBorder() {
		return border;
	}
	public void setBorder(Border border) {
		this.border = border;
	}

	public String getName() {
		return name;
	}
	
	public void setClassType(Class<?> classType) {
		this.classType = classType;
	}
	
	public Class<?> getClassType() {
		return classType;
	}
}