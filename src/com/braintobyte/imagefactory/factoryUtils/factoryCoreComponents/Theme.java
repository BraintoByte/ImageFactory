package com.braintobyte.imagefactory.factoryUtils.factoryCoreComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.io.File;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import java.util.TreeMap;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRootPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.JToolTip;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.JWindow;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import javax.swing.text.JTextComponent;

import com.braintobyte.imagefactory.exceptions.NoSuchFontException;
import com.braintobytes.serialspeed.serialmania.SerialUtil;

public class Theme {

	public enum COMPONENTS{
		JApplet,
		JColorChooser,
		JFormattedTextField,
		JOptionPane,
		JPasswordField,
		JSpinner,
		JTextPane,
		JDesktopPane,
		JEditorPane,
		JFileChooser,
		JRadioButtonMenuItem,
		JSlider,
		JToolTip,
		JTree,
		JTableHeader,
		JProgressBar,
		JCheckBox,
		JTabbedPane,
		JRadioButton,
		JTextArea,
		JTextField,
		JTextComponent,
		JViewport,
		JScrollPane,
		JList,
		JScrollBar,
		JSeparator,
		JPopupMenu,
		JMenuBar,
		JToggleButton,
		JSplitPane,
		JToolBar,
		JDialog,
		JWindow,
		JButton,
		JCheckBoxMenuItem,
		JMenu,
		JMenuItem,
		JComboBox,
		JTable,
		JInternalFrame,
		JLayeredPane,
		JPanel,
		JRootPane,
		JLabel,
		JComponent,
		JFrame
	}


	private boolean changed;
	private final String name;
	private TreeMap<String, ThemeComponent> components;

	/**A cool theme class, you can literally change every single component in one big swoop!
	 * Make sure you get the components and then serialize all theme components
	 * <u>We will add some functionality in the future for serialization</u>
	 * @param name
	 */
	public Theme(String name) {
		this.name = name;
		this.components = new TreeMap<>();
		makeDefaultColorComponents();
	}


	/**Mainly made to ease use with states
	 * @return
	 */
	public boolean isChanged() {
		return changed;
	}

	/**Mainly made to ease use with states
	 * @param changed
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	/**
	 * @param componentName
	 * @param component
	 */
	public void changeComponent(String componentName, ThemeComponent component){
		components.replace(componentName, component);
	}

	/**
	 * @param componentName
	 * @param component
	 */
	public void changeComponent(COMPONENTS componentName, ThemeComponent component){
		components.replace(componentName.toString(), component);
	}

	/**
	 * @param componentName
	 * @param border
	 */
	public void replaceComponentBorder(String componentName, Border border){
		ThemeComponent tmp = components.get(componentName);
		tmp.addBorder(border);
		components.replace(componentName, tmp);
	}

	/**
	 * @param componentName
	 * @param border
	 */
	public void replaceComponentBorder(COMPONENTS componentName, Border border){
		ThemeComponent tmp = components.get(componentName.toString());
		tmp.addBorder(border);
		components.replace(componentName.toString(), tmp);
	}

	/**
	 * @param componentName
	 * @return
	 */
	public Class<?> getComponentClassType(String componentName){
		return components.get(componentName).getClassType();
	}

	/**
	 * @param componentName
	 * @param component
	 * @return
	 */
	public boolean getVerifyComponentClassType(String componentName, Object component){
		return components.get(componentName).classTypeMatch(component);
	}

	/**
	 * @param componentName
	 * @return
	 */
	public Color getComponentBackground(String componentName){
		return components.get(componentName).getColors().get("background");
	}

	/**
	 * @param componentName
	 * @return
	 */
	public Color getComponentForeground(String componentName){
		return components.get(componentName).getColors().get("foreground");
	}

	/**
	 * @param componentName
	 * @return
	 */
	public Font getComponentFont(String componentName){
		return components.get(componentName).getFont();
	}

	/**
	 * @param componentName
	 * @return
	 */
	public Border getComponentBorder(String componentName){
		return components.get(componentName).getBorder();
	}

	/**
	 * @param componentName
	 * @param component
	 * @return
	 */
	protected boolean getVerifyComponentClassType(COMPONENTS componentName, Object component){
		return components.get(componentName.toString()).classTypeMatch(component);
	}

	/**
	 * @param componentName
	 * @return
	 */
	public Color getComponentBackground(COMPONENTS componentName){
		return components.get(componentName.toString()).getColors().get("background");
	}

	/**
	 * @param componentName
	 * @return
	 */
	public Color getComponentForeground(COMPONENTS componentName){
		return components.get(componentName.toString()).getColors().get("foreground");
	}

	/**
	 * @param componentName
	 * @return
	 */
	public Font getComponentFont(COMPONENTS componentName){
		return components.get(componentName.toString()).getFont();
	}

	/**
	 * @param componentName
	 * @return
	 */
	public Border getComponentBorder(COMPONENTS componentName){
		return components.get(componentName.toString()).getBorder();
	}

	/**Changes the background colors all at once, make sure that the colors sequentially match the component names
	 * <u>otherwise just put in one color, and all components specified will change with that color</u>
	 * @param backgrounds
	 * @param componentsNames
	 */
	public void changeThemeComponentsColorsBackground(Color[] backgrounds, String ... componentsNames){

		int bL = backgrounds != null ? backgrounds.length : 0;

		if(bL != componentsNames.length && bL != 1){
			return;
		}

		for (int i = 0; i < componentsNames.length; i++) {

			Color color;

			if(backgrounds.length == componentsNames.length){
				color = backgrounds[i];
			}else{
				color = backgrounds[0];
			}

			components.get(componentsNames[i]).replaceColor("background", color);
		}
	}


	/**Changes the foreground colors all at once, make sure that the colors sequentially match the component names
	 * <u>otherwise just put in one color, and all components specified will change with that color</u>
	 * @param foregrounds
	 * @param componentsNames
	 */
	public void changeThemeComponentsColorsForeground(Color[] foregrounds, String ... componentsNames){

		int fL = foregrounds != null ? foregrounds.length : 0;

		if(fL != componentsNames.length && fL != 1){
			return;
		}

		for (int i = 0; i < componentsNames.length; i++) {

			Color color;

			if(foregrounds.length == componentsNames.length){
				color = foregrounds[i];
			}else{
				color = foregrounds[0];
			}

			components.get(componentsNames[i]).replaceColor("foreground", color);

		}
	}

	/**Changes fonts all at once, make sure that the fonts sequentially match the component names
	 * <u>otherwise just put in one font, and all components specified will change with that font</u>
	 * @param fonts
	 * @param componentsNames
	 */
	public void changeThemeComponentsFont(Font[] fonts, String ... componentsNames){

		int fL = fonts != null ? fonts.length : 0;

		if(fL != componentsNames.length && fL != 1){
			return;
		}

		for (int i = 0; i < componentsNames.length; i++) {

			Font font;

			if(fonts.length == componentsNames.length){
				font = fonts[i];
			}else{
				font = fonts[0];
			}

			components.get(componentsNames[i]).setFont(font);

		}
	}

	/**Returns enumeration in {@link String} form
	 * @return
	 */
	public static String[] getComponentsEnumerated(){
		return Arrays.stream(COMPONENTS.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}

	public void save(String where, String extension) {

		ThemeComponent[] tmpComps = getAllComponentsForSerialization();
		ThemeItem[] themeIt = new ThemeItem[tmpComps.length];

		for (int i = 0; i < tmpComps.length; i++) {
			themeIt[i] = new ThemeItem(tmpComps[i].getName(), tmpComps[i].getColorsValuesToArray(), tmpComps[i].getColorsKeysToArray(), 
					tmpComps[i].getFont(), tmpComps[i].getBorder(), where + File.separator + this.name, tmpComps[i].getClassType());
		}
		
		File dir = new File(where + File.separator + this.name);
		
		if(!dir.exists()){
			dir.mkdir();
		}

		for (int i = 0; i < themeIt.length; i++) {
			SerialUtil.serializeObject(where + File.separator + this.name, tmpComps[i].getName(), extension, themeIt[i]);
		}
	}
	
	public void load(String where, String extension) throws NoSuchFileException{
		
		File dir = new File(where);
		
//		if(!dir.exists()){
//			throw new NoSuchFileException(where);
//		}
		
		File[] files = dir.listFiles();
		if(files.length == 1){
			
			dir = new File(files[0].getAbsolutePath());
			
			files = dir.listFiles();
			
		}
		
		ThemeItem[] themeIt = new ThemeItem[files.length];
		
		for (int i = 0; i < files.length; i++) {
			themeIt[i] = (ThemeItem) SerialUtil.deserializeObject(files[i].getAbsolutePath());
		}
		
		ThemeComponent[] tc = new ThemeComponent[files.length];
		
		for (int i = 0; i < themeIt.length; i++) {
			tc[i] = new ThemeComponent(themeIt[i].getName(), themeIt[i].getClassType());
			tc[i].load(themeIt[i].getColorsKeyset(), themeIt[i].getColors());
		}
		
		loadAllComponents(tc);
	}
	
	/**Get all theme components for serialization
	 * If you want a good serialization-deserialization API we suggest to visit <a href="http://www.braintobytes.com">Brain to Bytes website!</a>
	 * The API lets you also encrypt and decrypt serialized objects
	 * 
	 * @return {@link ThemeComponent[]}
	 */
	public ThemeComponent[] getAllComponentsForSerialization(){
		return components.values().toArray(new ThemeComponent[components.size()]);
	}

	/**Get all theme components for serialization
	 * If you want a good serialization-deserialization API we suggest to visit <a href="http://www.braintobytes.com">Brain to Bytes website!</a>
	 * The API lets you also encrypt and decrypt serialized objects
	 * 
	 * @return {@link Stack<ThemeComponent>}
	 */
	public Stack<ThemeComponent> getAllComponentsForSerializationInStack(){

		Stack<ThemeComponent> tmpStack = new Stack<>();
		Iterator<ThemeComponent> it = components.values().iterator();

		while(it.hasNext()){
			tmpStack.push(it.next());
		}

		return tmpStack;
	}

	/**Load all components at once, uses {@link Theme#changeComponent(String, ThemeComponent)}
	 * If you want a good serialization-deserialization API we suggest to visit <a href="http://www.braintobytes.com">Brain to Bytes website!</a>
	 * The API lets you also encrypt and decrypt serialized objects
	 * 
	 */
	public void loadAllComponents(ThemeComponent[] comps){
		for (int i = 0; i < comps.length; i++) {
			changeComponent(comps[i].getClassType().getName(), comps[i]);
		}
	}

	/**Load all components at once, uses {@link Theme#changeComponent(String, ThemeComponent)}
	 * If you want a good serialization-deserialization API we suggest to visit <a href="http://www.braintobytes.com">Brain to Bytes website!</a>
	 * The API lets you also encrypt and decrypt serialized objects
	 * 
	 */
	public void loadAllComponents(Stack<ThemeComponent> comps){
		while(!comps.isEmpty()){
			ThemeComponent comp = comps.pop();
			changeComponent(comp.getClassType().getName(), comp);
		}
	}


	/**Makes a default theme
	 */
	private void makeDefaultColorComponents(){

		//For customization purposes, we are working on default theamage

		ThemeComponent[] tmpArr = new ThemeComponent[47];

		tmpArr[0] = new ThemeComponent("JApplet",JApplet.class);
		tmpArr[0].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[0].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[0].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[1] = new ThemeComponent("JColorChooser",JColorChooser.class);
		tmpArr[1].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[1].addBorder("DEFAULT");
		tmpArr[1].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[1].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[2] = new ThemeComponent("JFormattedTextField",JFormattedTextField.class);
		tmpArr[2].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[2].addBorder("DEFAULT");
		tmpArr[2].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[2].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[3] = new ThemeComponent("JOptionPane",JOptionPane.class);
		tmpArr[3].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[3].addBorder("DEFAULT");
		tmpArr[3].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[3].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[4] = new ThemeComponent("JPasswordField",JPasswordField.class);
		tmpArr[4].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[4].addBorder("DEFAULT");
		tmpArr[4].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[4].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[5] = new ThemeComponent("JSpinner",JSpinner.class);
		tmpArr[5].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[5].addBorder("DEFAULT");
		tmpArr[5].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[5].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[6] = new ThemeComponent("JTextPane",JTextPane.class);
		tmpArr[6].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[6].addBorder("DEFAULT");
		tmpArr[6].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[6].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[7] = new ThemeComponent("JDesktopPane",JDesktopPane.class);
		tmpArr[7].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[7].addBorder("DEFAULT");
		tmpArr[7].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[7].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[8] = new ThemeComponent("JEditorPane",JEditorPane.class);
		tmpArr[8].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[8].addBorder("DEFAULT");
		tmpArr[8].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[8].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[9] = new ThemeComponent("JFileChooser",JFileChooser.class);
		tmpArr[9].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[9].addBorder("DEFAULT");
		tmpArr[9].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[9].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[10] = new ThemeComponent("JRadioButtonMenuItem",JRadioButtonMenuItem.class);
		tmpArr[10].addBorder("DEFAULT");
		tmpArr[10].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[10].addBorder("DEFAULT");
		tmpArr[10].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[10].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[11] = new ThemeComponent("JSlider",JSlider.class);
		tmpArr[11].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[11].addBorder("DEFAULT");
		tmpArr[11].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[11].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[12] = new ThemeComponent("JToolTip",JToolTip.class);
		tmpArr[12].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[12].addBorder("DEFAULT");
		tmpArr[12].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[12].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[13] = new ThemeComponent("JTree",JTree.class);
		tmpArr[13].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[13].addBorder("DEFAULT");
		tmpArr[13].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[13].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[14] = new ThemeComponent("JTableHeader",JTableHeader.class);
		tmpArr[14].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[14].addBorder("DEFAULT");
		tmpArr[14].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[14].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[15] = new ThemeComponent("JProgressBar",JProgressBar.class);
		tmpArr[15].addBorder("DEFAULT");
		tmpArr[15].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[15].addBorder("DEFAULT");
		tmpArr[15].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[15].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[16] = new ThemeComponent("JCheckBox",JCheckBox.class);
		tmpArr[16].addBorder("DEFAULT");
		tmpArr[16].addBorder("DEFAULT");
		tmpArr[16].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[16].addBorder("DEFAULT");
		tmpArr[16].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[16].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[17] = new ThemeComponent("JTabbedPane",JTabbedPane.class);
		tmpArr[17].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[17].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[17].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[17].addBorder("DEFAULT");
		tmpArr[17].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[17].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[18] = new ThemeComponent("JRadioButton",JRadioButton.class);
		tmpArr[18].addBorder("DEFAULT");
		tmpArr[18].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[18].addBorder("DEFAULT");
		tmpArr[18].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[18].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[19] = new ThemeComponent("JTextArea",JTextArea.class);
		tmpArr[19].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[19].addBorder("DEFAULT");
		tmpArr[19].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[19].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[20] = new ThemeComponent("JTextField",JTextField.class);
		tmpArr[20].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[20].addBorder("DEFAULT");
		tmpArr[20].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[20].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[21] = new ThemeComponent("JTextComponent",JTextComponent.class);
		tmpArr[21].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[21].addBorder("DEFAULT");
		tmpArr[21].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[21].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[22] = new ThemeComponent("JViewport",JViewport.class);
		tmpArr[22].addBorder("DEFAULT");
		tmpArr[22].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[22].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[22].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[23] = new ThemeComponent("JScrollPane",JScrollPane.class);
		tmpArr[23].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[23].addBorder("DEFAULT");
		tmpArr[23].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[23].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[24] = new ThemeComponent("JList",JList.class);
		tmpArr[24].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[24].addBorder("DEFAULT");
		tmpArr[24].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[24].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[25] = new ThemeComponent("JScrollBar",JScrollBar.class);
		tmpArr[25].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[25].addBorder("DEFAULT");
		tmpArr[25].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[25].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[26] = new ThemeComponent("JSeparator",JSeparator.class);
		tmpArr[26].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[26].addBorder("DEFAULT");
		tmpArr[26].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[26].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[27] = new ThemeComponent("JPopupMenu",JPopupMenu.class);
		tmpArr[27].addBorder("DEFAULT");
		tmpArr[27].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[27].addBorder("DEFAULT");
		tmpArr[27].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[27].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[28] = new ThemeComponent("JMenuBar",JMenuBar.class);
		tmpArr[28].addBorder("DEFAULT");
		tmpArr[28].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[28].addBorder("DEFAULT");
		tmpArr[28].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[28].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[29] = new ThemeComponent("JToggleButton",JToggleButton.class);
		tmpArr[29].addBorder("DEFAULT");
		tmpArr[29].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[29].addBorder("DEFAULT");
		tmpArr[29].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[29].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[30] = new ThemeComponent("JSplitPane",JSplitPane.class);
		tmpArr[30].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[30].addBorder("DEFAULT");
		tmpArr[30].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[30].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[31] = new ThemeComponent("JToolBar",JToolBar.class);
		tmpArr[31].addBorder("DEFAULT");
		tmpArr[31].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[31].addBorder("DEFAULT");
		tmpArr[31].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[31].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[32] = new ThemeComponent("JDialog",JDialog.class);
		tmpArr[32].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[32].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[32].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[33] = new ThemeComponent("JWindow",JWindow.class);
		tmpArr[33].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[33].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[33].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[34] = new ThemeComponent("JButton",JButton.class);
		tmpArr[34].addBorder("DEFAULT");
		tmpArr[34].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[34].addBorder("DEFAULT");
		tmpArr[34].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[34].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[35] = new ThemeComponent("JCheckBoxMenuItem",JCheckBoxMenuItem.class);
		tmpArr[35].addBorder("DEFAULT");
		tmpArr[35].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[35].addBorder("DEFAULT");
		tmpArr[35].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[35].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[36] = new ThemeComponent("JMenu",JMenu.class);
		tmpArr[36].addBorder("DEFAULT");
		tmpArr[36].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[36].addBorder("DEFAULT");
		tmpArr[36].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[36].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[37] = new ThemeComponent("JMenuItem",JMenuItem.class);
		tmpArr[37].addBorder("DEFAULT");
		tmpArr[37].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[37].addBorder("DEFAULT");
		tmpArr[37].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[37].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[38] = new ThemeComponent("JComboBox",JComboBox.class);
		tmpArr[38].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[38].addBorder("DEFAULT");
		tmpArr[38].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[38].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[39] = new ThemeComponent("JTable",JTable.class);
		tmpArr[39].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[39].addBorder("DEFAULT");
		tmpArr[39].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[39].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[40] = new ThemeComponent("JInternalFrame",JInternalFrame.class);
		tmpArr[40].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[40].addBorder("DEFAULT");
		tmpArr[40].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[40].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[41] = new ThemeComponent("JLayeredPane",JLayeredPane.class);
		tmpArr[41].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[41].addBorder("DEFAULT");
		tmpArr[41].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[41].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[42] = new ThemeComponent("JPanel",JPanel.class);
		tmpArr[42].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[42].addBorder("DEFAULT");
		tmpArr[42].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[42].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[43] = new ThemeComponent("JRootPane",JRootPane.class);
		tmpArr[43].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[43].addBorder("DEFAULT");
		tmpArr[43].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[43].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[44] = new ThemeComponent("JLabel",JLabel.class);
		tmpArr[44].setFont(new Font("Bank Gothic", Font.PLAIN, 15));
		tmpArr[44].addBorder("DEFAULT");
		tmpArr[44].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[44].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[45] = new ThemeComponent("JComponent",JComponent.class);
		tmpArr[45].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[45].addBorder("DEFAULT");
		tmpArr[45].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[45].addColor("foreground", new Color(0, 240, 0, 240));
		tmpArr[46] = new ThemeComponent("JFrame",JFrame.class);
		tmpArr[46].addColor("background", new Color(0, 0, 0, 240));
		tmpArr[46].setFont(new Font("Bank Gothic", Font.PLAIN, 12));
		tmpArr[46].addColor("foreground", new Color(0, 240, 0, 240));


		for (int i = 0; i < tmpArr.length; i++) {
			components.put(tmpArr[i].getName(), tmpArr[i]);
		}
	}
}