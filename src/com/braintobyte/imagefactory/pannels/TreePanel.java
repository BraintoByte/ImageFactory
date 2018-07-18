package com.braintobyte.imagefactory.pannels;

import java.awt.Color;
import java.io.File;
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

import com.braintobyte.imagefactory.factoryUtils.ImageUtils;
import com.braintobyte.imagefactory.factoryUtils.UtilsGui;
import com.braintobyte.imagefactory.graphicalcomponents.ScrollBarMod;

public class TreePanel extends JPanel {

	public JTree tree;
	public JScrollPane treeScrollPane;

	public DefaultTreeModel model;
	public DefaultMutableTreeNode root;


	public TreePanel(int x, int y, int w, int h, String rootName) {
		this.setBounds(x, y, w, h);
		root = new DefaultMutableTreeNode(rootName);
		model = new DefaultTreeModel(root);

		tree = new JTree(model);
		DefaultTreeCellRenderer treeR = (DefaultTreeCellRenderer) tree.getCellRenderer();
		treeR.setClosedIcon(new ImageIcon(ImageUtils.loadImage(System.getProperty("user.dir") + File.separator + "images" + File.separator + "serverClosed.png")));
		treeR.setOpenIcon(new ImageIcon(ImageUtils.loadImage(System.getProperty("user.dir") + File.separator + "images" + File.separator + "serverOpened.png")));
		treeR.setLeafIcon(new ImageIcon(ImageUtils.loadImage(System.getProperty("user.dir") + File.separator + "images" + File.separator + "serverLeaf.png")));

		tree.setBounds(x, y, w, h);
		treeScrollPane = new JScrollPane(tree);
		treeScrollPane.getVerticalScrollBar().setUI(new ScrollBarMod());
		treeScrollPane.setBounds(389, 288, 215, 159);

		treeScrollPane.setFont(UtilsGui.loadFont(System.getProperty("user.dir") + File.separator + "fonts" + File.separator + "Ubuntu-B.ttf", 8));
		
		this.add(tree);
	}


	public void addNode(String node){

		DefaultMutableTreeNode toAdd = new DefaultMutableTreeNode(node);
		DefaultMutableTreeNode parent = findNode(node.split(":")[0]);

		if(parent == null){
			parent = new DefaultMutableTreeNode(node.split(":")[0]);
			model.insertNodeInto(parent, (MutableTreeNode) model.getRoot(), ((MutableTreeNode) model.getRoot()).getChildCount());
		}

		model.insertNodeInto(toAdd, parent, parent.getChildCount());
	}


	public void removeNode(String node){
		DefaultMutableTreeNode tmpNode = findNode(node);
		if(tmpNode != null){
			doRemoveNode(tmpNode);
		}
	}

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


	public MutableTreeNode getSibling(DefaultMutableTreeNode node){
		MutableTreeNode sibling = (MutableTreeNode) node.getPreviousSibling();
		if(sibling == null){
			sibling = (MutableTreeNode) node.getNextSibling();
		}
		return sibling;
	}

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
}