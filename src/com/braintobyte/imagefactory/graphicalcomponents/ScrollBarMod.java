package com.braintobyte.imagefactory.graphicalcomponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

import com.braintobyte.imagefactory.factoryUtils.UtilsGui;
import com.braintobyte.imagefactory.factoryUtils.factoryCoreComponents.Theme;

public class ScrollBarMod extends BasicScrollBarUI {

	private final Dimension d = new Dimension();
	private Theme theme;

	@Override
	protected JButton createDecreaseButton(int orientation) {
		return new JButton() {
			@Override
			public Dimension getPreferredSize() {
				return d;
			}
		};
	}

	@Override
	protected JButton createIncreaseButton(int orientation) {
		return new JButton() {
			@Override
			public Dimension getPreferredSize() {
				return d;
			}
		};
	}

	//Background

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
		
		if(theme == null){
			theme = UtilsGui.getDefaultTheme();
		}
		
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Color color = new Color(58, 58, 58);
		JScrollBar sb = (JScrollBar) c;
		g2.setPaint(color);
		g2.fillRoundRect(r.x, r.y, r.width, r.height, 10, 10);
		g2.setPaint(new Color(58, 58, 58));
		g2.drawRoundRect(r.x, r.y, r.width, r.height, 10, 10);
		g2.dispose();
	}
	
	
	//Foreground
	
	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Color color = null;
		JScrollBar sb = (JScrollBar) c;
		
		if(theme == null){
			theme = UtilsGui.getDefaultTheme();
		}
		
		if (!sb.isEnabled() || r.width > r.height) {
			return;
		} else if (isDragging) {
			color = new Color(0, 255, 8);
		} else if (isThumbRollover()) {
			color = new Color(117, 255, 121);
			//200, 249, 167
		} else {
			color = new Color(0, 255, 157);
			//191, 255, 227
		}
		g2.setPaint(color);
		g2.fillRoundRect(r.x, r.y, r.width, r.height, 5, 5);
		g2.setPaint(new Color(58, 58, 58));
		g2.drawRoundRect(r.x, r.y, r.width, r.height, 5, 5);
		g2.dispose();
	}

	@Override
	protected void setThumbBounds(int x, int y, int width, int height) {
		super.setThumbBounds(x, y, width, height);
		scrollbar.repaint();
	}
}