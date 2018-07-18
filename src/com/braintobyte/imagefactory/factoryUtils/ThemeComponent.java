package com.braintobyte.imagefactory.factoryUtils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import com.braintobyte.imagefactory.exceptions.NoSuchFontException;
import com.braintobyte.imagefactory.factoryUtils.Theme.COMPONENTS;

public class ThemeComponent {
	
	public enum BORDERS{
		createSoftBevelBorder,
		createRaisedBevelBorder,
		createMatteBorder,
		createEtchedBorder,
		createEmptyBorder,
		createRaisedSoftBevelBorder,
		createCompoundBorder,
		createLoweredBevelBorder,
		createLoweredSoftBevelBorder,
		createStrokeBorder,
		createDashedBorder,
		createTitledBorder,
		createBevelBorder,
		createLineBorder
	}
	

	private HashMap<String, Color> colors;
	private Font font;
	private Border border;
	private final String name;
	private final Class<?> classType;

	public ThemeComponent(String name, Class<?> classType) {
		this.colors = new HashMap<>();
		this.name = name;
		this.classType = classType;
	}

	public void addColor(String partName, Color color){
		colors.put(partName, color);
	}

	public void removeColor(String partName, Color color){
		colors.put(partName, color);
	}

	public void replaceColor(String partName, Color color){
		colors.replace(partName, color);
	}

	public HashMap<String, Color> getColors() {
		return colors;
	}

	public Border getBorder() {
		return border;
	}
	
	public void addBorder(Border border){
		this.border = border;
	}

	public void addBorder(String border) {

		switch(border){
		case "RaisedBevelBorder":
			this.border = BorderFactory.createRaisedBevelBorder();
			break;
		case "RaisedSoftBevelBorder":
			this.border = BorderFactory.createRaisedSoftBevelBorder();
			break;
		case "CompoundBorder":
			this.border = BorderFactory.createCompoundBorder();
			break;
		case "LoweredBevelBorder":
			this.border = BorderFactory.createLoweredBevelBorder();
			break;
		case "LoweredSoftBevelBorder":
			this.border = BorderFactory.createLoweredSoftBevelBorder();
			break;
		case "EmptyBorder":
			this.border = BorderFactory.createEmptyBorder();
			break;
		default:
			border = null;
			break;
		}
	}


	public void addBorder(String border, Paint arg0){

		switch(border){
		case "DashedBorder":
			this.border = BorderFactory.createDashedBorder(arg0);
			break;
		}
	}

	public void addBorder(String border, int arg0){
		switch(border){
		case "BevelBorder":
			this.border = BorderFactory.createBevelBorder(arg0);
			break;
		}
	}

	public void addBorder(String border, BasicStroke arg0, Paint arg1){
		switch(border){
		case "StrokeBorder":
			this.border = BorderFactory.createStrokeBorder(arg0, arg1);
			break;

		}
	}

	public void addBorder(String border, int arg0, Color arg1, Color arg2){

		switch(border){
		case "SoftBevelBorder":
			this.border = BorderFactory.createSoftBevelBorder(arg0, arg1, arg2);
			break;

		case "EtchedBorder":
			this.border = BorderFactory.createEtchedBorder(arg0, arg1, arg2);
			break;

		case "LineBorder":
			this.border = BorderFactory.createLineBorder(arg1);
			break;
		}
	}

	public void addBorder(String border, int arg0, int arg1, int arg2, int arg3){

		switch(border){
		case "EmptyBorder":
			this.border = BorderFactory.createEmptyBorder(arg0, arg1, arg2, arg3);
			break;
		}
	}

	public void addBorder(String border, int arg0, int arg1, int arg2, int arg3, Color arg4){

		switch(border){
		case "MatteBorder":
			this.border = BorderFactory.createMatteBorder(arg0, arg1, arg2, arg3, arg4);
			break;
		}
	}

	public String getName() {
		return name;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Class<?> getClassType() {
		return classType;
	}

	public Font getFont() throws NoSuchFontException {

		if(font == null){
			throw new NoSuchFontException(name);
		}

		return font;
	}

	public boolean classTypeMatch(Object component){
		return component.getClass().getName().equals(classType.getName());
	}
	
	public static String[] getComponentsEnumerated(){
		return Arrays.stream(COMPONENTS.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}
}