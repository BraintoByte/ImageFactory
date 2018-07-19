package com.braintobytes.imagefactory.factoryUtils.factoryCoreComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import com.braintobytes.imagefactory.exceptions.NoSuchFontException;
import com.braintobytes.imagefactory.factoryUtils.factoryCoreComponents.Theme.COMPONENTS;

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

	/**Components of a theme, contains colors with various specifications, such as background foreground etc...
	 * Insert the component name to keep it organized!
	 * @param name
	 * @param classType
	 */
	public ThemeComponent(String name, Class<?> classType) {
		this.colors = new HashMap<>();
		this.name = name;
		this.classType = classType;
	}

	/**
	 * @param partName
	 * @param color
	 */
	public void addColor(String partName, Color color){
		colors.put(partName, color);
	}

	/**
	 * @param partName
	 * @param color
	 */
	public void removeColor(String partName, Color color){
		colors.put(partName, color);
	}

	/**
	 * @param partName
	 * @param color
	 */
	public void replaceColor(String partName, Color color){
		colors.replace(partName, color);
	}

	/**
	 * @return
	 */
	public HashMap<String, Color> getColors() {
		return colors;
	}

	/**
	 * @return
	 */
	public Border getBorder() {
		return border;
	}
	
	/**
	 * @param border
	 */
	public void addBorder(Border border){
		this.border = border;
	}

	/**
	 * @param border
	 */
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


	/**
	 * @param border
	 * @param arg0
	 */
	public void addBorder(String border, Paint arg0){

		switch(border){
		case "DashedBorder":
			this.border = BorderFactory.createDashedBorder(arg0);
			break;
		}
	}

	/**
	 * @param border
	 * @param arg0
	 */
	public void addBorder(String border, int arg0){
		switch(border){
		case "BevelBorder":
			this.border = BorderFactory.createBevelBorder(arg0);
			break;
		}
	}

	/**
	 * @param border
	 * @param arg0
	 * @param arg1
	 */
	public void addBorder(String border, BasicStroke arg0, Paint arg1){
		switch(border){
		case "StrokeBorder":
			this.border = BorderFactory.createStrokeBorder(arg0, arg1);
			break;

		}
	}

	/**
	 * @param border
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
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

	/**
	 * @param border
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public void addBorder(String border, int arg0, int arg1, int arg2, int arg3){

		switch(border){
		case "EmptyBorder":
			this.border = BorderFactory.createEmptyBorder(arg0, arg1, arg2, arg3);
			break;
		}
	}

	/**
	 * @param border
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 */
	public void addBorder(String border, int arg0, int arg1, int arg2, int arg3, Color arg4){

		switch(border){
		case "MatteBorder":
			this.border = BorderFactory.createMatteBorder(arg0, arg1, arg2, arg3, arg4);
			break;
		}
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param font
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	/**
	 * @return
	 */
	public Class<?> getClassType() {
		return classType;
	}

	/**
	 * @return
	 * @throws NoSuchFontException
	 */
	public Font getFont() {

		return font;
	}

	/**
	 * @param component
	 * @return
	 */
	public boolean classTypeMatch(Object component){
		return component.getClass().getName().equals(classType.getName());
	}
	
	public void load(String[] keyValueSet, Color[] colors){
		
		if(keyValueSet.length != colors.length){
			return;
		}
		
		for (int i = 0; i < colors.length; i++) {
			replaceColor(keyValueSet[i], colors[i]);
		}
	}
	
	/**
	 * @return
	 */
	public static String[] getComponentsEnumerated(){
		return Arrays.stream(COMPONENTS.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}
	
	public Color[] getColorsValuesToArray(){
		return colors.values().toArray(new Color[colors.size()]);
	}
	
	public String[] getColorsKeysToArray(){
		return colors.keySet().toArray(new String[colors.size()]);
	}
}