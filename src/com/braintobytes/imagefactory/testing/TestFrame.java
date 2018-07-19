package com.braintobytes.imagefactory.testing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.braintobytes.imagefactory.factoryUtils.UtilsGui;
import com.braintobytes.imagefactory.factoryUtils.factoryCoreComponents.Theme;
import com.braintobytes.imagefactory.factoryUtils.factoryCoreComponents.Theme.COMPONENTS;
import com.braintobytes.imagefactory.factoryUtils.factoryCoreComponents.ThemeComponent.BORDERS;

public class TestFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnButton;
	private JLabel lblLabel;
	private JTextField txtField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
					Theme theme = new Theme("Test");
					Color[] backgrounds = { new Color(240, 240, 0, 240), new Color(240, 240, 0, 240) };
					Color[] foregrounds = { new Color(0, 240, 240, 240) };
//					COMPONENTS.JCheckBoxMenuItem.toString(), COMPONENTS.JLabel.toString()
					theme.changeThemeComponentsColorsForeground(foregrounds, new String[]{ "JButton", "JTextField" });
					theme.changeThemeComponentsColorsBackground(backgrounds, new String[]{ "JButton", "JTextField" });
//					BorderFactory.createBevelBorder()
					System.out.println(theme.getComponentBorder(COMPONENTS.JButton));
					Object[] objs = { BevelBorder.RAISED, new Color(0, 0, 0, 240), new Color(0, 0, 0, 240), new Color(0, 0, 0, 240), new Color(0, 0, 0, 240) };
					Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(0, 0, 0, 240), new Color(0, 0, 0, 240), new Color(0, 0, 0, 240), new Color(0, 0, 0, 240));
					theme.replaceComponentBorder(COMPONENTS.JButton, border);
					System.out.println(theme.getComponentBorder(COMPONENTS.JButton));
					
					UtilsGui.convertFrame(frame, theme);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 434, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		btnButton = new JButton("Button");
		contentPane.add(btnButton, BorderLayout.EAST);
		
		lblLabel = new JLabel("Label");
		contentPane.add(lblLabel, BorderLayout.CENTER);
		
		JTextPane txtpnHello = new JTextPane();
		txtpnHello.setText("hello");
		contentPane.add(txtpnHello, BorderLayout.SOUTH);
		
		txtField = new JTextField();
		txtField.setText("Field");
		contentPane.add(txtField, BorderLayout.NORTH);
		txtField.setColumns(10);
	}
}