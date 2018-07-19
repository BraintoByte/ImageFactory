package com.braintobytes.imagefactory.frames;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**A easy to undecorate fram with image
 * <br><br><u>EXPERIMENTAL</u>
 */
public class Frame extends JFrame {


	private static final long serialVersionUID = -7173220345904189158L;
	private int x, y, w, h;
	private boolean decorated;
	private LinkedList<JPanel> pannels;
	private Color bgColor;
	private Color definedBgColor;
	
	/**A easy to undecorate frame with image
	 * <br><br><u>EXPERIMENTAL</u>
	 */
	public Frame(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.bgColor = new Color(0, 0, 0, 0);
		this.pannels = new LinkedList<>();
		setMinimumSize(new Dimension(w, h));
 	}
	

	public void buildFrame() {
		
		if(decorated) {
			bgColor = new Color(0, 0, 0, 0);
			setUndecorated(decorated);
			setBackground(bgColor);
		}else {
			bgColor = definedBgColor;
			setBackground(bgColor);
			setUndecorated(decorated);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(!pannels.isEmpty()){
			add(pannels.poll());
		}
		
		
		setLocation(x, y);
		setVisible(true);
		pack();
		
	}
	

	public void rebuildFrame() {

		if(decorated) {
			bgColor = new Color(0, 0, 0, 0);
			setUndecorated(decorated);
			setBackground(bgColor);
		}else {
			bgColor = definedBgColor;
			setBackground(bgColor);
			setUndecorated(decorated);
		}

		
		
		setLocation(x, y);
		setVisible(true);
		
		Iterator<JPanel> it = pannels.iterator();
		
		while(it.hasNext()){
			add(it.next());
		}
		
		pack();
		
	}
	
	
	public void addPannel(JPanel pannel){
		pannels.add(pannel);
		rebuildFrame();
	}
	
	public boolean removePannel(JPanel pannel){
		boolean removed = pannels.remove(pannel);
		rebuildFrame();
		
		return removed;
		
	}
	
	public JPanel removePannel(int index){
		JPanel temp = pannels.remove(index);
		rebuildFrame();
		
		return temp;
	}
	
	
	public void setDecorated(boolean decorated) {

		this.decorated = decorated;
		x = getX();
		y = getY();
		w = getWidth();
		h = getHeight();
		setVisible(false);

		dispose();
		rebuildFrame();
		
	}

	public boolean isDecorated() {
		return decorated;
	}
	
	public void setBgColor(Color definedBgColor) {
		this.definedBgColor = definedBgColor;
	}
}