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
package Template;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import AlgorTools.CloneShape;
import AlgorTools.ShapeAutoGenerate;
import CommonShape.CommonShape;
import CommonShape.CommonShapeFactory;
import CommonShape.EllipseShape;
import CommonShape.RectangleShape;
import Container.ContainerAllShape;
import DrawingInterface.DrawingInterface;
import MainInterface.RunProgram;
import SpecialShape.FixJointShape;
import SpecialShape.JointShape;
import SpecialShape.SpringShape;
import UserInterface.Mouse;

public class Template extends JPanel{

	private JPanel Displaypanel;
	private JList  TemplateList;
	
	private JButton Submit;
	private JButton Cancel;
	
	private Image[] Img;
	public static enum TemplateState{Default,Room,Classroom,Space,Snooker};
	public static TemplateState tState;
	private final String[] data = {"Default","Room","Classroom","Space","Snooker"};
	
	public Template()
	{
		this.TemplateList = new JList(data);
		this.TemplateList.setSelectedIndex(0);
		this.TemplateList.setBounds(0,0,300,800);
		
		this.Displaypanel = new JPanel();
		this.Displaypanel.setBounds(300,0,980,800);
		
		tState = TemplateState.Default;
		
		this.AddEvent();
	
		this.Submit = new JButton();
		this.Cancel = new JButton();
		
		this.Submit.setBounds(700,600,100,50);
		this.Cancel.setBounds(830,600,100,50);
		this.Submit.setText("Submit");
		this.Cancel.setText("Cancel");
		
		this.ButtonEvent();
		
		this.setLayout(null);
		
		this.add(Submit);
		this.add(Cancel);
		
		this.add(this.TemplateList);
		Img = new Image[6];
	
		/* add Image*/
		try
		{
			Img[0] = ImageIO.read(new File("./pic/template/DefaultEx.png")); 	//Default
			Img[1] = ImageIO.read(new File("./pic/template/roomEx.png"));		//Room
			Img[2] = ImageIO.read(new File("./pic/template/ClassroomEx.png"));	//ClassRoom
			Img[3] = ImageIO.read(new File("./pic/template/SpaceEx.png"));		//Space
			Img[4] = ImageIO.read(new File("./pic/template/SnookerEx.png"));	//Snooker
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void ButtonEvent()
	{
		this.Submit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) 
			{
				// Save to redo
				RunProgram.runner.drawing.redoTrunk.clearMemento();
				RunProgram.runner.drawing.undoTrunk.addMemento(RunProgram.runner.drawing.currentMemento.saveToSmartBoardMemento());
				
				// Clear old Template shape
				for(int i =0;i<RunProgram.runner.drawing.shapeContainer.getShapeContainerSize();i++)
				{
					if(RunProgram.runner.drawing.shapeContainer.getShape(i).getClass().getSuperclass().getCanonicalName().equalsIgnoreCase("CommonShape.CommonShape"))
					{
						CommonShape common = (CommonShape)RunProgram.runner.drawing.shapeContainer.getShape(i);
						if(common.isNormalShape() == false)
						{
							RunProgram.runner.drawing.shapeContainer.removeShapeSelectIndex(i);
							i = -1;
						}
					}
				}
				
				
				if(tState == TemplateState.Default)
				{
					/** Set Background*/
					RunProgram.runner.drawing.mouse.setBackground(null);
				}
				
				else if(tState == TemplateState.Room)
				{
					/** Ground*/
					ArrayList<Point2D> temp = new ArrayList<Point2D>();
					temp.add(new Point2D.Double(0,750));
					temp.add(new Point2D.Double(1280,750));
					temp.add(new Point2D.Double(1280,800));
					temp.add(new Point2D.Double(0,800));
					RectangleShape recGround = (RectangleShape)CommonShapeFactory.createCommonShape("Rectangle", temp);
					recGround.setBounce(1.0f);
					recGround.setImage("./pic/template/UpperBrick.png");
					recGround.setToBackground();
					RunProgram.runner.drawing.shapeContainer.addShapeLast(recGround);
					
					/** Cellar*/
					temp = new ArrayList<Point2D>();
					temp.add(new Point2D.Double(0,-10));
					temp.add(new Point2D.Double(1280,-10));
					temp.add(new Point2D.Double(1280,40));
					temp.add(new Point2D.Double(0,40));
					RectangleShape recCellar = (RectangleShape)CommonShapeFactory.createCommonShape("Rectangle", temp);
					recCellar.setBounce(1.0f);
					recCellar.setImage("./pic/template/UpperBrick.png");
					recCellar.setToBackground();
					RunProgram.runner.drawing.shapeContainer.addShapeLast(recCellar);
					
					/** Left Side Brick*/
					temp = new ArrayList<Point2D>();
					temp.add(new Point2D.Double(0,0));
					temp.add(new Point2D.Double(90,0));
					temp.add(new Point2D.Double(90,775));
					temp.add(new Point2D.Double(0,775));
					RectangleShape recLeft = (RectangleShape)CommonShapeFactory.createCommonShape("Rectangle", temp);
					recLeft.setBounce(1.0f);
					recLeft.setImage("./pic/template/SideBrick.png");
					recLeft.setToBackground();
					RunProgram.runner.drawing.shapeContainer.addShapeLast(recLeft);
				
					/** Left Side Brick*/
					temp = new ArrayList<Point2D>();
					temp.add(new Point2D.Double(1190,0));
					temp.add(new Point2D.Double(1280,0));
					temp.add(new Point2D.Double(1280,775));
					temp.add(new Point2D.Double(1190,775));
					RectangleShape recRight = (RectangleShape)CommonShapeFactory.createCommonShape("Rectangle", temp);
					recRight.setBounce(1.0f);
					recRight.setImage("./pic/template/SideBrick.png");
					recRight.setToBackground();
					RunProgram.runner.drawing.shapeContainer.addShapeLast(recRight);
					
					/** Set Background*/
					RunProgram.runner.drawing.mouse.setBackground("./pic/template/RoomBg.jpg");
				}
				
				else if(tState == TemplateState.Classroom)
				{
					/** Table Edge*/
					ArrayList<Point2D> temp = new ArrayList<Point2D>();
					temp.add(new Point2D.Double(256,497));
					temp.add(new Point2D.Double(1063,497));
					temp.add(new Point2D.Double(1063,525));
					temp.add(new Point2D.Double(256,525));
					RectangleShape tableEdge = (RectangleShape)CommonShapeFactory.createCommonShape("Rectangle", temp);
					tableEdge.setImage("./pic/template/TableEdge.png");
					tableEdge.setBounce(1.0f);
					tableEdge.setToBackground();
					RunProgram.runner.drawing.shapeContainer.addShapeLast(tableEdge);
					
					/** Table Leg*/
					temp = new ArrayList<Point2D>();
					temp.add(new Point2D.Double(605,523));
					temp.add(new Point2D.Double(699,523));
					temp.add(new Point2D.Double(699,772));
					temp.add(new Point2D.Double(605,772));
					RectangleShape tableLeg = (RectangleShape)CommonShapeFactory.createCommonShape("Rectangle", temp);
					tableLeg.setImage("./pic/template/TableLeg.png");
					tableLeg.setBounce(1.0f);
					tableLeg.setToBackground();
					RunProgram.runner.drawing.shapeContainer.addShapeLast(tableLeg);
					
					/** Set Background*/
					RunProgram.runner.drawing.mouse.setBackground("./pic/template/ClassRoomBg.png");
				}
				
				else if(tState == TemplateState.Space)
				{
					EllipseShape temp = new EllipseShape(50,50,125);
					temp.setImage("./pic/template/EarthActual.png");
					temp.setBounce(0.8f);
					RunProgram.runner.drawing.shapeContainer.addShapeLast(temp);
					
					temp = new EllipseShape(150,150,300);
					temp.setImage("./pic/template/Star2Actual.png");
					temp.setBounce(0.8f);
					RunProgram.runner.drawing.shapeContainer.addShapeLast(temp);
					
					temp = new EllipseShape(500,300,125);
					temp.setImage("./pic/template/Star3Actual.png");
					temp.setBounce(0.8f);
					RunProgram.runner.drawing.shapeContainer.addShapeLast(temp);
					
					temp = new EllipseShape(800,300,150);
					temp.setImage("./pic/template/Star4Actual.png");
					temp.setBounce(0.8f);
					RunProgram.runner.drawing.shapeContainer.addShapeLast(temp);
					
					/** Set Background*/
					RunProgram.runner.drawing.mouse.setBackground("./pic/template/SpaceBg.jpg");
				}
			
				else if(tState == TemplateState.Snooker)
				{
					/** Edge Left*/
					ArrayList<Point2D> temp = new ArrayList<Point2D>();
					temp.add(new Point2D.Double(-2,135));
					temp.add(new Point2D.Double(81,135));
					temp.add(new Point2D.Double(81,667));
					temp.add(new Point2D.Double(-2,667));
					RectangleShape leftEdge = (RectangleShape)CommonShapeFactory.createCommonShape("Rectangle", temp);
					leftEdge.setImage("./pic/template/SnookerEdgeLeft.png");
					leftEdge.setToBackground();
					leftEdge.setBounce(1);
					RunProgram.runner.drawing.shapeContainer.addShapeLast(leftEdge);
					
					/** Edge Right*/
					temp = new ArrayList<Point2D>();
					temp.add(new Point2D.Double(1189,135));
					temp.add(new Point2D.Double(1272,135));
					temp.add(new Point2D.Double(1272,667));
					temp.add(new Point2D.Double(1189,667));
					RectangleShape rightEdge = (RectangleShape)CommonShapeFactory.createCommonShape("Rectangle", temp);
					rightEdge.setImage("./pic/template/SnookerEdgeRight.png");
					rightEdge.setToBackground();
					rightEdge.setBounce(1);
					RunProgram.runner.drawing.shapeContainer.addShapeLast(rightEdge);
					
					/** Edge Upper*/
					temp = new ArrayList<Point2D>();
					temp.add(new Point2D.Double(135,0));
					temp.add(new Point2D.Double(580,0));
					temp.add(new Point2D.Double(580,81));
					temp.add(new Point2D.Double(135,81));
					RectangleShape upperEdge = (RectangleShape)CommonShapeFactory.createCommonShape("Rectangle", temp);
					upperEdge.setImage("./pic/template/SnookerEdgeUpper.png");
					upperEdge.setToBackground();
					upperEdge.setBounce(1);
					RunProgram.runner.drawing.shapeContainer.addShapeLast(upperEdge);
					
					/** Edge Upper 2*/
					temp = new ArrayList<Point2D>();
					temp.add(new Point2D.Double(135+560,0));
					temp.add(new Point2D.Double(589+560,0));
					temp.add(new Point2D.Double(589+560,82));
					temp.add(new Point2D.Double(135+560,82));
					RectangleShape upperEdge2 = (RectangleShape)CommonShapeFactory.createCommonShape("Rectangle", temp);
					upperEdge2.setImage("./pic/template/SnookerEdgeUpper2.png");
					upperEdge2.setToBackground();
					upperEdge2.setBounce(1);
					RunProgram.runner.drawing.shapeContainer.addShapeLast(upperEdge2);
					
					/** Edge Lower*/
					temp = new ArrayList<Point2D>();
					temp.add(new Point2D.Double(135,0+720));
					temp.add(new Point2D.Double(580,0+720));
					temp.add(new Point2D.Double(580,81+720));
					temp.add(new Point2D.Double(135,81+720));
					RectangleShape lowerEdge = (RectangleShape)CommonShapeFactory.createCommonShape("Rectangle", temp);
					lowerEdge.setImage("./pic/template/SnookerEdgeLower.png");
					lowerEdge.setToBackground();
					lowerEdge.setBounce(1);
					RunProgram.runner.drawing.shapeContainer.addShapeLast(lowerEdge);
					
					
					/** Edge Lower 2*/
					temp = new ArrayList<Point2D>();
					temp.add(new Point2D.Double(135+560,0+720));
					temp.add(new Point2D.Double(589+560,0+720));
					temp.add(new Point2D.Double(589+560,82+720));
					temp.add(new Point2D.Double(135+560,82+720));
					RectangleShape lowerEdge2 = (RectangleShape)CommonShapeFactory.createCommonShape("Rectangle", temp);
					lowerEdge2.setImage("./pic/template/SnookerEdgeLower2.png");
					lowerEdge2.setToBackground();
					lowerEdge2.setBounce(1);
					RunProgram.runner.drawing.shapeContainer.addShapeLast(lowerEdge2);
					
					
					ArrayList<EllipseShape> poolBall = ShapeAutoGenerate.autoGenpoolNineBall(220,370, 50);
					poolBall.get(0).setBounce(0.8f);
					poolBall.get(1).setBounce(0.8f);
					poolBall.get(2).setBounce(0.8f);
					poolBall.get(3).setBounce(0.8f);
					poolBall.get(4).setBounce(0.8f);
					poolBall.get(5).setBounce(0.8f);
					poolBall.get(6).setBounce(0.8f);
					poolBall.get(7).setBounce(0.8f);
					poolBall.get(8).setBounce(0.8f);
					
					
					poolBall.get(0).setImage("./pic/template/Snooker9.png");
					RunProgram.runner.drawing.shapeContainer.addShapeLast(poolBall.get(0));
					poolBall.get(1).setImage("./pic/template/Snooker8.png");
					RunProgram.runner.drawing.shapeContainer.addShapeLast(poolBall.get(1));
					poolBall.get(2).setImage("./pic/template/Snooker7.png");
					RunProgram.runner.drawing.shapeContainer.addShapeLast(poolBall.get(2));
					poolBall.get(3).setImage("./pic/template/Snooker4.png");
					RunProgram.runner.drawing.shapeContainer.addShapeLast(poolBall.get(3));
					poolBall.get(4).setImage("./pic/template/Snooker5.png");
					RunProgram.runner.drawing.shapeContainer.addShapeLast(poolBall.get(4));
					poolBall.get(5).setImage("./pic/template/Snooker6.png");
					RunProgram.runner.drawing.shapeContainer.addShapeLast(poolBall.get(5));
					poolBall.get(6).setImage("./pic/template/Snooker2.png");
					RunProgram.runner.drawing.shapeContainer.addShapeLast(poolBall.get(6));
					poolBall.get(7).setImage("./pic/template/Snooker3.png");
					RunProgram.runner.drawing.shapeContainer.addShapeLast(poolBall.get(7));
					poolBall.get(8).setImage("./pic/template/Snooker1.png");
					RunProgram.runner.drawing.shapeContainer.addShapeLast(poolBall.get(8));
					
					EllipseShape whiteBall = new EllipseShape(1000,370,50);
					whiteBall.setImage("./pic/template/WhiteBall.png");
					whiteBall.setBounce(0.8f);
					RunProgram.runner.drawing.shapeContainer.addShapeLast(whiteBall);
					
					/** Set Background*/
					RunProgram.runner.drawing.mouse.setBackground("./pic/template/PoolTabBg.png");
				}
				
				RunProgram.runner.drawing.templateFrame();
				DrawingInterface.iState = DrawingInterface.state.Drawing;
				RunProgram.runner.drawing.currentMemento.setState((ContainerAllShape)RunProgram.runner.drawing.shapeContainer.clone(),CloneShape.cloneLineContainer(RunProgram.runner.drawing.lineContainer),(Mouse) RunProgram.runner.drawing.mouse.clone());
				
				removeJoint(RunProgram.runner.drawing.shapeContainer);
				RunProgram.runner.drawing.repaint();
			}
			
		}
		);
		this.Cancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				RunProgram.runner.drawing.templateFrame();
			}
			
		}
		);
	}
	public void AddEvent()
	{
		this.TemplateList.addListSelectionListener(new ListSelectionListener()
		{

			public void valueChanged(ListSelectionEvent arg0) 
			{
				int Index = TemplateList.getSelectedIndex();
			
				if (Index == 0)
				{
					tState = TemplateState.Default;
			    	repaint();
				}
				
			    if(Index == 1)
			    {
			    	tState = TemplateState.Room;
			    	repaint();
			    }
			    else if( Index== 2)
			    {
			    	tState = TemplateState.Classroom;
			    	repaint();
			    }
			    
			    else if( Index ==3)
			    {
			    	tState = TemplateState.Space;
			    	repaint();
			    }
			    
			    else if( Index ==4)
			    {
			    	tState = TemplateState.Snooker;
			    	repaint();
			    }
		    
			}
			
			
		});
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		
		if(tState == TemplateState.Default)
		{
			g.drawImage(Img[0],400,80,this);
		}
		else if(tState == TemplateState.Room)
		{
			g.drawImage(Img[1],400,80,this);
		}
		else if(tState == TemplateState.Classroom)
		{
			g.drawImage(Img[2],400,80,this);
		}
		else if(tState == TemplateState.Space)
		{
			g.drawImage(Img[3],400,80,this);
		}
		else if(tState == TemplateState.Snooker)
		{
			g.drawImage(Img[4],400,80,this);
		}
	}
	
	/**
	 * Get template state
	 * @return
	 */
	public static TemplateState getTState() 
	{
		return tState;
	}
	
	/**
	 * Set template state
	 * @param state
	 */
	public static void setTState(TemplateState state) 
	{
		tState = state;
	}
	
	
	
	/**
	 * Remove the joint if the shape that joint was connect has been remove.
	 * @param shapeContainer
	 */
	private void removeJoint(ContainerAllShape shapeContainer)
	{
		// remove Basic joint
		for(int i = 0;i<shapeContainer.getShapeContainerSize();i++)
		{
			if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.JointShape"))
			{
				JointShape joint = (JointShape) shapeContainer.getShape(i);
				if(joint.isContain(shapeContainer) == false)
				{
					shapeContainer.removeShapeSelectIndex(i);
					i = i-1;
				}
			}
		}
		// remove Fix joint
		for(int i = 0;i<shapeContainer.getShapeContainerSize();i++)
		{
			if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.FixJointShape"))
			{
				FixJointShape joint = (FixJointShape) shapeContainer.getShape(i);
				if(joint.isContain(shapeContainer) == false)
				{
					shapeContainer.removeShapeSelectIndex(i);
					i = i-1;
				}
			}
		}
		
		// remove Spring joint
		for(int i =0;i<shapeContainer.getShapeContainerSize();i++)
		{
			if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.SpringShape"))
			{
				SpringShape spring = (SpringShape) shapeContainer.getShape(i);
				if(shapeContainer.isContain(spring.getStartIndex()) == false
				|| shapeContainer.isContain(spring.getEndIndex()) == false)
				{
					shapeContainer.removeShapeSelectIndex(i);
					i = i-1;
				}
			}
		}
	}
}
