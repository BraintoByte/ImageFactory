package com.braintobytes.imagefactory.pannels;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import com.braintobytes.imagefactory.factoryUtils.ImageUtils;
import com.braintobytes.imagefactory.factoryUtils.UtilsGui;
import com.braintobytes.imagefactory.graphicalComponents.ScrollBarMod;

public class TreePanel extends JPanel {

	public JTree tree;
	public JScrollPane treeScrollPane;

	public DefaultTreeModel model;
	public DefaultMutableTreeNode root;


	/**Creates a customized {@link JTree}
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param rootName
	 * @throws IOException 
	 */
	public TreePanel(int x, int y, int w, int h, String rootName) throws IOException {
		this.setBounds(x, y, w, h);
		root = new DefaultMutableTreeNode(rootName);
		model = new DefaultTreeModel(root);

		tree = new JTree(model);
		tree.setBounds(x, y, w, h);
		treeScrollPane = new JScrollPane(tree);
		treeScrollPane.getVerticalScrollBar().setUI(new ScrollBarMod());
		treeScrollPane.setBounds(389, 288, 215, 159);

		treeScrollPane.setFont(UtilsGui.loadFont(System.getProperty("user.dir") + File.separator + "fonts" + File.separator + "Ubuntu-B.ttf", 8));
		
		this.add(tree);
	}


	/**
	 * @param node
	 */
	public void addNode(String node){

		DefaultMutableTreeNode toAdd = new DefaultMutableTreeNode(node);
		DefaultMutableTreeNode parent = findNode(node.split(":")[0]);

		if(parent == null){
			parent = new DefaultMutableTreeNode(node.split(":")[0]);
			model.insertNodeInto(parent, (MutableTreeNode) model.getRoot(), ((MutableTreeNode) model.getRoot()).getChildCount());
		}

		model.insertNodeInto(toAdd, parent, parent.getChildCount());
	}


	/**
	 * @param node
	 */
	public void removeNode(String node){
		DefaultMutableTreeNode tmpNode = findNode(node);
		if(tmpNode != null){
			doRemoveNode(tmpNode);
		}
	}

	/**
	 * @param node
	 */
	public void doRemoveNode(DefaultMutableTreeNode node){

		if(node == null){
			return;
		}

		MutableTreeNode parent = (MutableTreeNode) (node.getParent());

		if(parent == null){
			return;
		}

		model.removeNodeFromParent(node);
	}


	/**
	 * @param node
	 * @return
	 */
	public MutableTreeNode getSibling(DefaultMutableTreeNode node){
		MutableTreeNode sibling = (MutableTreeNode) node.getPreviousSibling();
		if(sibling == null){
			sibling = (MutableTreeNode) node.getNextSibling();
		}
		return sibling;
	}

	/**
	 * @param node
	 * @return
	 */
	public DefaultMutableTreeNode findNode(String node){
		DefaultMutableTreeNode result = null;
		Enumeration e = root.breadthFirstEnumeration();
		while(e.hasMoreElements()){
			result = (DefaultMutableTreeNode) e.nextElement();
			if(node.equals(result.getUserObject().toString())){
				return result;
			}
		}
		return null;
	}
	
	/**Set image of tree when node is opened uses {@link ImageUtils#loadImage(String)}
	 * @param path
	 * @throws IOException
	 */
	public void setImageOpen(String path) throws IOException{
		DefaultTreeCellRenderer treeR = (DefaultTreeCellRenderer) tree.getCellRenderer();
		treeR.setOpenIcon(new ImageIcon(ImageUtils.loadImage(path)));
	}
	
	/**Set image of tree when node is closed uses {@link ImageUtils#loadImage(String)}
	 * @param path
	 * @throws IOException
	 */
	public void setImageClosed(String path) throws IOException{
		DefaultTreeCellRenderer treeR = (DefaultTreeCellRenderer) tree.getCellRenderer();
		treeR.setClosedIcon(new ImageIcon(ImageUtils.loadImage(path)));
	}
	
	/**Set image of tree when node is leaf uses {@link ImageUtils#loadImage(String)}
	 * @param path
	 * @throws IOException
	 */
	public void setImageLeaf(String path) throws IOException{
		DefaultTreeCellRenderer treeR = (DefaultTreeCellRenderer) tree.getCellRenderer();
		treeR.setLeafIcon(new ImageIcon(ImageUtils.loadImage(path)));
	}
	
	/**Set image of tree when node is opened
	 * @param image
	 * @throws IOException
	 */
	public void setImageOpen(BufferedImage image) throws IOException{
		DefaultTreeCellRenderer treeR = (DefaultTreeCellRenderer) tree.getCellRenderer();
		treeR.setOpenIcon(new ImageIcon(image));
	}
	
	/**Set image of tree when node is closed
	 * @param image
	 * @throws IOException
	 */
	public void setImageClosed(BufferedImage image) throws IOException{
		DefaultTreeCellRenderer treeR = (DefaultTreeCellRenderer) tree.getCellRenderer();
		treeR.setClosedIcon(new ImageIcon(image));
	}
	
	/**Set image of tree when node is leaf
	 * @param image
	 * @throws IOException
	 */
	public void setImageLeaf(BufferedImage image) throws IOException{
		DefaultTreeCellRenderer treeR = (DefaultTreeCellRenderer) tree.getCellRenderer();
		treeR.setLeafIcon(new ImageIcon(image));
	}
}