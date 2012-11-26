/*
 * SmartBoard - a freehand drawing and simulation in physic2D on the    
 * work of SmartBoard Team.
 * original source remains:
 * 
 * Copyright (c) 2008 SmartBoard http://sourceforge.net/projects/smart-board/
 * 
 * This source is provided under the terms of the BSD License.
 * 
 * Copyright (c) 2008, SmartBoard
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 *  * Redistributions of source code must retain the above 
 *    copyright notice, this list of conditions and the 
 *    following disclaimer.
 *  * Redistributions in binary form must reproduce the above 
 *    copyright notice, this list of conditions and the following 
 *    disclaimer in the documentation and/or other materials provided 
 *    with the distribution.
 *  * Neither the name of the SmartBoard/New Dawn Software nor the names of 
 *    its contributors may be used to endorse or promote products 
 *    derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS 
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, 
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, 
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY 
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR 
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT 
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY 
 * OF SUCH DAMAGE.
 */
package HelpFrame;

import java.awt.Dimension;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import javax.swing.tree.TreeSelectionModel;




public class TestHelp extends JFrame implements TreeSelectionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8323880967123674054L;
	private JTree tree;
	private EventHelp eh;
	private BufferStrategy buff;
	private Graphics2D g;
	
	/**
	 * TreeView >> 240
	 * Help Content 700 - 240 = 460
	 * @param name
	 */
	public TestHelp(String name)
	{
		super(name);
		this.setAlwaysOnTop(true);
		this.setBounds(0, 0, 860, 600);
		createTree();
		
//		Create the scroll pane and add the tree to it. 
        JScrollPane treeView = new JScrollPane(tree);		
		
		eh = new EventHelp();		
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(treeView);
		splitPane.setRightComponent(eh);
		
		treeView.setMinimumSize(new Dimension(300,300));
		
		add(splitPane);
		
	}
	
	public void createTree()
	{
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("SmartBoard Topics");
        createNodes(top);
		
		tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
         //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);
	}
	
	private void createDrawingMode(DefaultMutableTreeNode top)
	{
		DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode cont = null;
        
        category = new DefaultMutableTreeNode(new ContentInfo
        		("Drawing Mode",
        		"./pic/help/Help_DrawingMode.png"));
        top.add(category);       
        
        //original Tutorial
        cont = new DefaultMutableTreeNode("Using File Menu ");
        createHelpFileMenu(cont);
        category.add(cont);       
        
        cont = new DefaultMutableTreeNode("Using Properties Menu ");
        createHelpPropertiesMenu(cont);
        category.add(cont);
               
        cont = new DefaultMutableTreeNode("Using AutoShape Menu ");
        createAutoShapeMenu(cont);
        category.add(cont);
        
        cont = new DefaultMutableTreeNode("Using Drawing Tools Menu ");
        createDrawingToolsMenu(cont);
        category.add(cont);
        
        cont = new DefaultMutableTreeNode("Object Interaction " +
        		"");
        createTransformationMenu(cont);
        category.add(cont);
        
        cont = new DefaultMutableTreeNode(new ContentInfo
        		("Undo/Redo your works",
        		"./pic/help/Help_UndoRedo.png"));
        category.add(cont);
        
        cont = new DefaultMutableTreeNode(new ContentInfo
        		("Using Template",
        		"./pic/help/Help_UsingTemplate.png"));
        category.add(cont);
        
        cont = new DefaultMutableTreeNode(new ContentInfo
        		("Using Help",
        		"./pic/help/Help_UsingHelp.png"));
        category.add(cont);
        
        cont = new DefaultMutableTreeNode(new ContentInfo
        		("How to start the simulation",
        		"./pic/help/Help_HowToStartTheSimulation.png"));
        category.add(cont);
	}
	
	private void createTransformationMenu(DefaultMutableTreeNode top) {
		DefaultMutableTreeNode cont = null;
        
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Selecting the object(s)",
				"./pic/help/Help_SelectTheObject.png"));
		top.add(cont);
		
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Rotating the selected object",
				"./pic/help/Help_RotateTheObject.png"));
		top.add(cont);
		
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Editing the selected object",
				"./pic/help/Help_EditTheObject.png"));
		top.add(cont);
		
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Move the selected object(s)",
				"./pic/help/Help_MoveTheObject.png"));
		top.add(cont);
		
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Delete the selected object(s)",
				"./pic/help/Help_DeleteTheObject.png"));
		top.add(cont);
		
	}

	private void createDrawingToolsMenu(DefaultMutableTreeNode top) {
		DefaultMutableTreeNode cont = null;
        
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Using Pen Tools",
				"./pic/help/Help_UsingPenTools.png"));
		createUsingPenToolsMenu(cont);		
		top.add(cont);
		
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Add rope to your works",
				"./pic/help/Help_AddRope.png"));
		top.add(cont);
		
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Add a Spring to your works",
				"./pic/help/Help_AddSpring.png"));
		top.add(cont);
		
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Add a Fixed Joint to your works",
				"./pic/help/Help_AddFixedJoint.png"));
		top.add(cont);
		
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Add a Basic Joint to your works",
				"./pic/help/Help_AddBasicJoint.png"));
		top.add(cont);
		
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Set the gravity direction to your works",
				"./pic/help/Help_SetTheGravityDirection.png"));
		top.add(cont);
	}

	private void createUsingPenToolsMenu(DefaultMutableTreeNode top) 
	{
		DefaultMutableTreeNode cont = null;
        
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Draw shapes",
				"./pic/help/Help_DrawShape.png"));
		top.add(cont);
		
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Draw a cross to an object",
				"./pic/help/Help_DrawCross.png"));
		top.add(cont);
		
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Draw an arrow (Gravity direction)",
				"./pic/help/Help_DrawArrow.png"));
		top.add(cont);
		
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Draw a spring",
				"./pic/help/Help_DrawASpring.png"));
		top.add(cont);
	}

	private void createAutoShapeMenu(DefaultMutableTreeNode top) {
		DefaultMutableTreeNode cont = null;
        
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Draw a basic shape",
				"./pic/help/Help_DrawABasicShape.png"));
		top.add(cont);
		
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Draw a balloon/car",
				"./pic/help/Help_DrawABalloonCar.png"));
		top.add(cont);
		
	}

	private void createHelpPropertiesMenu(DefaultMutableTreeNode top) {
		DefaultMutableTreeNode cont = null;
        
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Set the pen's accuracy",
				"./pic/help/Help_SetThePenAccuracy.png"));
		top.add(cont);
		
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Set the spring constant(k)",
				"./pic/help/Help_SetTheSpringConstant.png"));
		top.add(cont);
		
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Set the properties for selected object",
				"./pic/help/Help_SetObjectProperties.png"));
		top.add(cont);
		
	}

	private void createHelpEditMenu(DefaultMutableTreeNode top) {
		DefaultMutableTreeNode cont = null;
        
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Copy the selected object",
				"./pic/help/help01.png"));
		top.add(cont);
		
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Paste the selected object",
				"./pic/help/help01.png"));
		top.add(cont);
	}

	private void createHelpFileMenu(DefaultMutableTreeNode top) {
		
        DefaultMutableTreeNode cont = null;
        
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Make a new file",
				"./pic/help/Help_MakeNewFile.png"));
		top.add(cont);
		
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Load an existing file",
				"./pic/help/Help_LoadFile.png"));
		top.add(cont);
		
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Save your works",
				"./pic/help/Help_SaveFile.png"));
		top.add(cont);
		
		cont = new DefaultMutableTreeNode(new ContentInfo
				("Save as your works",
				"./pic/help/Help_SaveAsFile.png"));
		top.add(cont);
		
	}

	private void createPhysicsMode(DefaultMutableTreeNode top)
	{
		DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode book = null;
		
		  category = new DefaultMutableTreeNode(new ContentInfo
	        		("Physics Mode",
	        		"./pic/help/Help_PhysicsMode.png"));
	        top.add(category);

	        //VM
	        book = new DefaultMutableTreeNode(new ContentInfo
	            ("How to use Play/Pause/Stop ",
	             "./pic/help/Help_HowToPlayPauseStop.png"));
	        category.add(book);

	        //Language Spec
	        book = new DefaultMutableTreeNode(new ContentInfo
	            ("How to see the graph ",
	             "./pic/help/Help_HowToSeeTheGraph.png"));
	        category.add(book);
	        
	        book = new DefaultMutableTreeNode(new ContentInfo
		         ("How to add Force ",
		             "./pic/help/Help_HowToAddForce.png"));
		    category.add(book);
		    
		    book = new DefaultMutableTreeNode(new ContentInfo
			     ("How to interact with the object ",
			         "./pic/help/Help_HowToInteractWithObject.png"));
			category.add(book);
	}
	
	private void createLicenseAgreement(DefaultMutableTreeNode top) 
	{
		DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode cont = null;
        
        category = new DefaultMutableTreeNode(new ContentInfo
        		("License Agreement",
        		"./pic/help/Help_LiecenseAgreement.png"));
        top.add(category);   
	}
	
	private void createNodes(DefaultMutableTreeNode top) 
	{                
       this.createDrawingMode(top);
       this.createPhysicsMode(top);
       this.createLicenseAgreement(top);
    }
	
	public static void main(String []args)
	{
		TestHelp p = new TestHelp("SmartBoard Help V1.0");
		p.setVisible(true);
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

	public void valueChanged(TreeSelectionEvent arg0) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)
        		tree.getLastSelectedPathComponent();

			if (node == null) return;
		
		Object nodeInfo = node.getUserObject();
		// if node is set to have some its own contents
		if (nodeInfo.getClass().getCanonicalName().equals("HelpFrame.ContentInfo")) 
		{			
			ContentInfo c = (ContentInfo)nodeInfo;
			buff = this.getBufferStrategy();
			
			g = (Graphics2D) buff.getDrawGraphics();
			eh.showHelp(c);
			super.paintComponents(g);
		}
		else // if that node doesn't have any content ... use default page
		{
			
			buff = this.getBufferStrategy();			
			g = (Graphics2D) buff.getDrawGraphics();
			eh.showDefaultHelp();
			super.paintComponents(g);
		}


		
	}
	
}
