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
package EngineDrawing;

import java.awt.Image;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import net.phys2d.raw.Body;
import net.phys2d.raw.Contact;

import AlgorTools.CheckDrawLine;
import AlgorTools.CheckShapeContainer;
import AlgorTools.CloneShape;
import AlgorTools.LineIntersection;
import AlgorTools.ShapeConvert;
import AlgorTools.ShapeIntersection;
import AlgorTools.ShapeProperty;
import AlgorTools.ShapeRegenerate;
import CommonShape.CommonShape;
import CommonShape.CommonShapeFactory;
import CommonShape.EllipseShape;
import CommonShape.PolygonShape;
import CommonShape.RectangleShape;
import CommonShape.TriangleShape;
import Container.ContainerAllShape;
import IconMenu.RecycleBin;
import Memento.SmartBoardCaretaker;
import Memento.SmartBoardOriginator;
import SpecialShape.ArrowShape;
import SpecialShape.CrossShape;
import SpecialShape.FixJointShape;
import SpecialShape.JointShape;
import SpecialShape.RopeShape;
import SpecialShape.SpringShape;
import TempObject.LineIndex;
import TempObject.TempFixJointShape;
import TempObject.TempSpringShape;
import UserInterface.Mouse;

/**
 * Release Left State for check all the action from mouse left that you done and execute.
 * 
 * @author Magic Board Team
 *
 */

public class ReleaseRightState  extends State
{
	public ReleaseRightState()
	{
		this.stateName = "ReleaseRight";
	}
	
	public void handle(Mouse mouse)
	{	
		// Drawing State
		if(mouse.getautoTool().equalsIgnoreCase("Pencil")
		&& mouse.getmouseLeft()   == 1
		&& mouse.getmouseRight()  == 0
		&& (mouse.getmouseDrag()  == 0 || mouse.getmouseDrag()  == 1))
		{
			System.out.println("Change state to Drawing state");
			mouse.setState(new DrawingState());
		}
		
		// Comment State
		else if(mouse.getautoTool().equalsIgnoreCase("Comment")
		&& mouse.getmouseLeft()   == 1
		&& mouse.getmouseRight()  == 0
		&& (mouse.getmouseDrag()  == 0 || mouse.getmouseDrag()  == 1))
		{
			System.out.println("Change state to Comment state");
			mouse.setState(new AutoCommentState());
		}
		
		// Auto Joint State
		else if(mouse.getautoTool().equalsIgnoreCase("Joint")
		&& mouse.getmouseLeft()   == 1
		&& mouse.getmouseRight()  == 0
		&& (mouse.getmouseDrag()  == 0 || mouse.getmouseDrag()  == 1))
		{
			System.out.println("Change state to Joint state");
			mouse.setState(new AutoJointState());
		}
		
		// Auto Fix Joint State
		else if(mouse.getautoTool().equalsIgnoreCase("FixJoint")
		&& mouse.getmouseLeft()   == 1
		&& mouse.getmouseRight()  == 0
		&& (mouse.getmouseDrag()  == 0 || mouse.getmouseDrag()  == 1))
		{
			System.out.println("Change state to Fix Joint state");
			mouse.setState(new AutoFixJointState());
		}
		
		// Auto Spring Joint State
		else if(mouse.getautoTool().equalsIgnoreCase("Spring")
		&& mouse.getmouseLeft()   == 1
		&& mouse.getmouseRight()  == 0
		&& (mouse.getmouseDrag()  == 0 || mouse.getmouseDrag()  == 1))
		{
			System.out.println("Change state to Spring Joint state");
			mouse.setState(new AutoSpringState());
		}
		
		// Auto Triangle state
		if(mouse.getautoTool().equalsIgnoreCase("Triangle")
		&& mouse.getmouseLeft()   == 1
		&& mouse.getmouseRight()  == 0
		&& mouse.getmouseDrag()   == 0)
		{
			System.out.println("Change state to Auto Triangle State");
			mouse.setState(new AutoTriangleState());
		}
		
		// Auto Rectangle state
		else if(mouse.getautoTool().equalsIgnoreCase("Rectangle")
		&& mouse.getmouseLeft()   == 1
		&& mouse.getmouseRight()  == 0
		&& mouse.getmouseDrag()   == 0)
		{
			System.out.println("Change state to Auto Rectangle State");
			mouse.setState(new AutoRectangleState());
		}
		
		// Auto Ellipse state
		else if(mouse.getautoTool().equalsIgnoreCase("Ellipse")
		&& mouse.getmouseLeft()   == 1
		&& mouse.getmouseRight()  == 0
		&& mouse.getmouseDrag()   == 0)
		{
			System.out.println("Change state to Auto Ellipse State");
			mouse.setState(new AutoEllipseState());
		}
		
		// Auto Balloon state
		else if(mouse.getautoTool().equalsIgnoreCase("Balloon")
		&& mouse.getmouseLeft()   == 1
		&& mouse.getmouseRight()  == 0
		&& mouse.getmouseDrag()   == 0)
		{
			System.out.println("Change state to Auto Balloon State");
			mouse.setState(new AutoBalloonState());
		}
		
		// Auto Car state
		else if(mouse.getautoTool().equalsIgnoreCase("Car")
		&& mouse.getmouseLeft()   == 1
		&& mouse.getmouseRight()  == 0
		&& mouse.getmouseDrag()   == 0)
		{
			System.out.println("Change state to Auto Car State");
			mouse.setState(new AutoCarState());
		}
		
		// Auto Rope state
		else if(mouse.getautoTool().equalsIgnoreCase("Rope")
		&& mouse.getmouseLeft()   == 1
		&& mouse.getmouseRight()  == 0
		&& mouse.getmouseDrag()   == 0)
		{
			System.out.println("Change state to Auto Rope State");
			mouse.setState(new AutoRopeState());
		}
		
		// Individual Select State
		else if(mouse.getmouseLeft()  == 0
		&& mouse.getmouseRight() == 1
		&& mouse.getmouseDrag()  == 0)
		{
			System.out.println("Change state to Individual select");
			mouse.setState(new IndividualSelectState());
		}
	}
	
	public void runState(ContainerAllShape shapeContainer,Mouse mouse,ArrayList<ArrayList<Line2D>> lineContainer,RecycleBin bin,SmartBoardCaretaker redoTrunk,SmartBoardCaretaker undoTrunk,SmartBoardOriginator currentMemento)
	{	
		boolean save	= false;
		
		boolean selectG = false;

		Polygon temp = new Polygon();
		ArrayList<Integer> gtemp = new ArrayList<Integer>();
		ArrayList<LineIndex> ltemp = new ArrayList<LineIndex>();
		
		// Check for Remove object with recycle bin (Individual mode)
		if(mouse.getIndividualSelectIndex() != -1) 
		{
			if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getSuperclass().getCanonicalName().equalsIgnoreCase("CommonShape.CommonShape"))
			{
				CommonShape commonShape = (CommonShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());				
				if(bin.isIntersect(commonShape) == true && commonShape.isBackground() == false)
				{	
					shapeContainer.removeShapeSelectIndex(mouse.getIndividualSelectIndex());
					mouse.setIndividualSelectIndex(-1);
					removeJoint(shapeContainer);
					Image img = null;
					try 
					{
						img = ImageIO.read(new File("./pic/binSmall.png"));
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					bin.setImg(img);
				}
			}
			
			else if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.JointShape"))
			{
				JointShape joint = (JointShape) shapeContainer.getShape(mouse.getIndividualSelectIndex());
				if(bin.isIntersect(joint) == true)
				{	
					shapeContainer.removeShapeSelectIndex(mouse.getIndividualSelectIndex());
					mouse.setIndividualSelectIndex(-1);
					Image img = null;
					try 
					{
						img = ImageIO.read(new File("./pic/binSmall.png"));
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					bin.setImg(img);
				}
			}
			
			else if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.ArrowShape"))
			{
				ArrowShape arrow = (ArrowShape) shapeContainer.getShape(mouse.getIndividualSelectIndex());
				if(bin.isIntersect(arrow) == true)
				{	
					shapeContainer.removeShapeSelectIndex(mouse.getIndividualSelectIndex());
					mouse.setIndividualSelectIndex(-1);
					Image img = null;
					try 
					{
						img = ImageIO.read(new File("./pic/binSmall.png"));
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					bin.setImg(img);
				}
			}
			
		}
		
		if(mouse.getTempShape().size() > 0)
		{
			for(int i = mouse.getTempShape().size()-1;i>=0;i--)
			{
				if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.CrossShape"))
				{
					CrossShape cross = (CrossShape) mouse.getTempShape().get(i);
					if(bin.isIntersect(cross) == true)
					{
						CommonShape shape = (CommonShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());
						shape.removeCross();
						mouse.getTempShape().remove(i);
						Image img = null;
						try 
						{
							img = ImageIO.read(new File("./pic/binSmall.png"));
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
						bin.setImg(img);
					}
				}
				
				else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("TempObject.TempSpringShape"))
				{
					TempObject.TempSpringShape spring = (TempObject.TempSpringShape) mouse.getTempShape().get(i);
					if(bin.isIntersect(spring) == true && mouse.getIndividualSelectIndex() != -1)
					{
						shapeContainer.removeShapeSelectIndex(mouse.getIndividualSelectIndex());
						mouse.setIndividualSelectIndex(-1);
						mouse.getTempShape().remove(i);
						removeJoint(shapeContainer);

						Image img = null;
						try 
						{
							img = ImageIO.read(new File("./pic/binSmall.png"));
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
						bin.setImg(img);
					}
				}
				
				else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.RopeShape"))
				{
					RopeShape rope = (RopeShape) mouse.getTempShape().get(i);
					if(bin.isIntersect(rope) == true && mouse.getIndividualSelectIndex() != -1)
					{
						shapeContainer.removeShapeSelectIndex(mouse.getIndividualSelectIndex());
						mouse.setIndividualSelectIndex(-1);
						mouse.getTempShape().remove(i);
						Image img = null;
						try 
						{
							img = ImageIO.read(new File("./pic/binSmall.png"));
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
						bin.setImg(img);
					}
				}
				
				else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("TempObject.TempFixJointShape"))
				{
					TempFixJointShape fixJoint = (TempFixJointShape)mouse.getTempShape().get(i);
					if(bin.isIntersect(fixJoint) == true && mouse.getIndividualSelectIndex() != -1)
					{
						shapeContainer.removeShapeSelectIndex(mouse.getIndividualSelectIndex());
						mouse.setIndividualSelectIndex(-1);
						mouse.getTempShape().remove(i);
						Image img = null;
						try 
						{
							img = ImageIO.read(new File("./pic/binSmall.png"));
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
						bin.setImg(img);
					}
				}
			}
		}
		
		// Check for Remove Line with recycle bin (Individual select mode)
		if( mouse.getIndividualLineSelectIndex().getI() != -1
	    &&  mouse.getIndividualLineSelectIndex().getJ() != -1)
		{
			Line2D line = lineContainer.get(mouse.getIndividualLineSelectIndex().getI()).get(mouse.getIndividualLineSelectIndex().getJ());
			if(bin.isIntersect(line) == true)
			{	
				lineContainer.get(mouse.getIndividualLineSelectIndex().getI()).remove(mouse.getIndividualLineSelectIndex().getJ());
				mouse.getIndividualLineSelectIndex().setI(-1);
				mouse.getIndividualLineSelectIndex().setJ(-1);
				mouse.setIndividualSelectIndex(-1);
				Image img = null;
				try 
				{
					img = ImageIO.read(new File("./pic/binSmall.png"));
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				bin.setImg(img);
			}
			
		}
		
		//	 Check for Remove object with recycle bin (Group select mode)
		if(mouse.getGroupSelectPolygon().npoints > 0 || mouse.getGroupLineSelectIndex().size() > 0)
		{
			Polygon polygon = mouse.getGroupSelectPolygon();
			PolygonShape polygonSelect = new PolygonShape(ShapeConvert.convertPolygontoPoint2D(polygon));
			if(bin.isIntersect(polygonSelect))
			{
				removeSelectShape(shapeContainer,mouse);
				removeMouseSelectIndex(mouse,lineContainer);
				removeJoint(shapeContainer);
				
				mouse.getSelectDrawing().clear();
				mouse.getGroupSelectPolygon().reset();
				mouse.getGroupSelectIndex().clear();
				mouse.getGroupLineSelectIndex().clear();
				mouse.getTempShape().clear();
				
				Image img = null;
				try 
				{
					img = ImageIO.read(new File("./pic/binSmall.png"));
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				bin.setImg(img);
			}
		}
		
		// check for selection
		if(mouse.getSelectDrawing().size()>0)
		{	 
			ArrayList<Line2D> selectLine = CheckDrawLine.getLine(mouse.getSelectDrawing(),9);
			ArrayList<ArrayList<Point2D>> selectPoint = AlgorTools.LineIntersection.getLineShape(selectLine);
			
			// for breaking the group selection checking because it's not close shape
			if(selectPoint.size() > 0)
			{
				// Save to redo
				save = true;
				redoTrunk.clearMemento();
				undoTrunk.addMemento(currentMemento.saveToSmartBoardMemento());
				
				
				for(int i = 0;i<selectPoint.size();i++)
				{
					for(int j =0;j<selectPoint.get(i).size();j++)
					{
						temp.addPoint((int)selectPoint.get(i).get(j).getX(),(int)selectPoint.get(i).get(j).getY());
					}
				}
				
				for(int i = 0;i<lineContainer.size();i++)
				{
					for(int j = 0;j<lineContainer.get(i).size();j++)
					{
						if(temp.contains(lineContainer.get(i).get(j).getP1()) 
						&& temp.contains(lineContainer.get(i).get(j).getP2()))
						{
							System.out.println("Add Array of Line in Select Group.");
							LineIndex indexline = new LineIndex();
							indexline.setI(i);
							indexline.setJ(j);
							ltemp.add(indexline);
							selectG = true;
						}
					}
				}
				
				for(int i =0;i<shapeContainer.getShapeContainerSize();i++)
				{
					if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.TriangleShape"))
					{
						TriangleShape obj = (TriangleShape)shapeContainer.getShape(i);
						Contact[] contacts = new Contact[] {new Contact(), new Contact()};
						Body bodyA = ShapeConvert.convertPolygontoBody(temp);
						Body bodyB = ShapeConvert.convertObjtoBody(obj);
						
						if(CheckShapeContainer.isPolygonContainPolygon(temp, obj.getPolygon()) == true
						&& ShapeIntersection.checkPolygonPolygonCollider(contacts,bodyA,bodyB) == false
						&& obj.isNormalShape() == true)
						{
							System.out.println("Add Triangle in Select Group.");
							gtemp.add(i);
							selectG = true;
						}
					}
					
					else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.RectangleShape"))
					{
						RectangleShape obj = (RectangleShape)shapeContainer.getShape(i);
						Contact[] contacts = new Contact[] {new Contact(), new Contact()};
						Body bodyA = ShapeConvert.convertPolygontoBody(temp);
						Body bodyB = ShapeConvert.convertObjtoBody(obj);
						
						if(CheckShapeContainer.isPolygonContainPolygon(temp, obj.getPolygon()) == true
						&& ShapeIntersection.checkPolygonPolygonCollider(contacts,bodyA,bodyB) == false
						&& obj.isNormalShape() == true)
						{
							System.out.println("Add Rectangle in Select Group.");
							gtemp.add(i);
							selectG = true;
						}
					}
					
					else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
					{
						EllipseShape obj = (EllipseShape)shapeContainer.getShape(i);
						Contact[] contacts = new Contact[] {new Contact(), new Contact()};
						Body bodyA = ShapeConvert.convertPolygontoBody(temp);
						Body bodyB = ShapeConvert.convertObjtoBody(obj);
						
						if(CheckShapeContainer.isPolygonContainEllipse(temp, obj.getEllipse()) == true
						&& ShapeIntersection.checkPolygonCircleCollider(contacts,bodyA,bodyB) == false
						&& obj.isNormalShape() == true)
						{
							System.out.println("Add Ellipse in Select Group.");
							gtemp.add(i);
							selectG = true;
						}
					}
					
					else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.PolygonShape"))
					{
						PolygonShape obj = (PolygonShape)shapeContainer.getShape(i);
						Contact[] contacts = new Contact[] {new Contact(), new Contact()};
						Body bodyA = ShapeConvert.convertPolygontoBody(temp);
						Body bodyB = ShapeConvert.convertObjtoBody(obj);
						
						if(CheckShapeContainer.isPolygonContainPolygon(temp, obj.getPolygon()) == true
						&& ShapeIntersection.checkPolygonPolygonCollider(contacts,bodyA,bodyB) == false
						&& obj.isNormalShape() == true)
						{
							System.out.println("Add Polygon in Select Group.");
							gtemp.add(i);
							selectG = true;
						}
					}
					
					else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.ArrowShape"))
					{
						ArrowShape obj = (ArrowShape)shapeContainer.getShape(i);
						if(CheckShapeContainer.isPolygonContainPolygon(temp,obj.getBondingBox().get(0))== true
						 &&CheckShapeContainer.isPolygonContainPolygon(temp,obj.getBondingBox().get(1))== true
						 &&CheckShapeContainer.isPolygonContainPolygon(temp,obj.getBondingBox().get(2))== true)
						{
							System.out.println("Add Arrow in Select Group.");
							gtemp.add(i);
							selectG = true;
						}
					}
					
					else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.SpringShape"))
					{
						SpringShape obj = (SpringShape)shapeContainer.getShape(i);
						if(CheckShapeContainer.isPolygonContainLine(temp,obj.getShape()) == true)
						{
							System.out.println("Add Spring in Select Group.");
							gtemp.add(i);
							
							TempSpringShape spring = new TempSpringShape(
									 new Point2D.Double(obj.getStartBound().getCenterX(),obj.getStartBound().getCenterY()),
									 new Point2D.Double(obj.getEndBound().getCenterX(),obj.getEndBound().getCenterY()));
							mouse.addTempShape(spring);
							selectG = true;
						}
					}
					
					else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.JointShape"))
					{
						JointShape obj = (JointShape)shapeContainer.getShape(i);
						if(temp.contains(obj.getRotatePoint()) == true)
						{
							System.out.println("Add Joint in Select Group.");
							gtemp.add(i);
							selectG = true;
						}
					}
					
					else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.FixJointShape"))
					{
						FixJointShape obj = (FixJointShape)shapeContainer.getShape(i);
						ArrayList<Line2D> fixLine = new ArrayList<Line2D>();
						fixLine.add(new Line2D.Double(obj.getStartIndex().getCM().getX(),obj.getStartIndex().getCM().getY(),obj.getEndIndex().getCM().getX(),obj.getEndIndex().getCM().getY()));
						if(AlgorTools.CheckShapeContainer.isPolygonContainLine(temp,fixLine) == true)
						{
							gtemp.add(i);
							
							TempFixJointShape fixJoint = new TempFixJointShape(new Line2D.Double(obj.getStartIndex().getCM(),obj.getEndIndex().getCM()));
							mouse.addTempShape(fixJoint);
							selectG = true;
						}
					}
					
					else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.RopeShape"))
					{
						RopeShape obj = (RopeShape)shapeContainer.getShape(i);
						for(int j =0;j<obj.getShape().size();j++)
						{
							if(temp.contains(obj.getShape().get(j).getP1()) == true
							&& temp.contains(obj.getShape().get(j).getP2()) == true	
							&& j == obj.getShape().size()-1)
							{
								System.out.println("Add Rope in Select Group.");
								gtemp.add(i);
								
								ArrayList<Point2D> point = ShapeConvert.convertRopeLinetoPoint2D(obj.getShape());
								RopeShape rope = new RopeShape(ShapeConvert.convertPoint2DtoLine2D(point));
								mouse.addTempShape(rope);
								selectG = true;
							}
							
							else if(temp.contains(obj.getShape().get(j).getP1()) == true
							&& temp.contains(obj.getShape().get(j).getP2()) == true	)
							{
								
							}
							
							else
							{
								break;
							}
						}
					}
				}
			
			}
		}
		
		// check for remove Group line
		if(selectG == false && mouse.getSelectDrawing().size() > 0 && mouse.getGroupSelectPolygon().npoints > 0)
		{
			mouse.clearTempShape();
			System.out.println("Remove");
			int[] x = mouse.getGroupSelectPolygon().xpoints;
			int[] y = mouse.getGroupSelectPolygon().ypoints;
			ArrayList<Line2D> l1 = new ArrayList<Line2D>();
			ArrayList<Line2D> l2 = CheckDrawLine.getLine(mouse.getSelectDrawing(),9);
			
			for(int i = 0;i<mouse.getGroupSelectPolygon().npoints;i++)
			{
				if(i == mouse.getGroupSelectPolygon().npoints-1)
				{
					l1.add(new Line2D.Double(x[i],y[i],x[0],y[0]));
				}
				
				else
				{
					l1.add(new Line2D.Double(x[i],y[i],x[i+1],y[i+1]));
				}
			}
			
			if(LineIntersection.checkRemoveIntersection(l1,l2) == true)
			{
				// Save to redo
				save = true;
				redoTrunk.clearMemento();
				undoTrunk.addMemento(currentMemento.saveToSmartBoardMemento());
				
				removeSelectShape(shapeContainer,mouse);
				removeMouseSelectIndex(mouse,lineContainer);
				removeJoint(shapeContainer);
				
				mouse.getSelectDrawing().clear();
				mouse.getGroupSelectPolygon().reset();
				mouse.getGroupSelectIndex().clear();
				mouse.getGroupLineSelectIndex().clear();
			}
		}
		
		// set the select index
		if(selectG == true)
		{
			mouse.getGroupSelectPolygon().reset();
			mouse.getGroupSelectIndex().clear();
			mouse.getGroupLineSelectIndex().clear();
			
			int[] x = temp.xpoints;
			int[] y = temp.ypoints;
			for(int i = 0;i<temp.npoints;i++)
			{
				mouse.getGroupSelectPolygon().addPoint(x[i],y[i]);
			}
			
			for(int i =0;i<gtemp.size();i++)
			{
				mouse.getGroupSelectIndex().add(gtemp.get(i));
			}
			
			for(int i = 0;i<ltemp.size();i++)
			{
				mouse.getGroupLineSelectIndex().add(ltemp.get(i));
			}
		}
		mouse.getSelectDrawing().clear();
		
		// for check shape rotate and Check for Transform Clone From individual select state
		if(mouse.getIndividualSelectIndex() != -1 && mouse.getTempShape().size() > 0)
		{
			if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getSuperclass().getCanonicalName().equalsIgnoreCase("CommonShape.CommonShape"))
			{
				CommonShape shape = (CommonShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());
				
				// translate cross for remove it's
				if(mouse.getTempShape().get(0).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.CrossShape"))
				{
					mouse.clearTempShape();
				}
				
				// rotate shape
				else
				{
					FlatteningPathIterator pi = new FlatteningPathIterator(((Shape)mouse.getTempShape().get(0)).getPathIterator(null), 100);
					shape.setPolygon(AlgorTools.ShapeConvert.convertShapetoPolygon(pi));
					mouse.getRotationMoving().clear();
					mouse.clearTempShape();	
				}
			}
			
			else if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.ArrowShape"))
			{
				ArrowShape shape = (ArrowShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());;
				ArrayList<Polygon>  polygon = new ArrayList<Polygon>();
				polygon.add((Polygon)mouse.getTempShape().get(0));
				polygon.add((Polygon)mouse.getTempShape().get(1));
				polygon.add((Polygon)mouse.getTempShape().get(2));
				shape.setBoundingBox(polygon);
				mouse.getRotationMoving().clear();
				mouse.clearTempShape();
				
				ArrowShape arrowshape = shape;
				arrowshape = ShapeRegenerate.regenerateArrow(arrowshape.getBondingBox(),arrowshape.getPointIntersection());
				shape.setBoundingBox(arrowshape.getBondingBox());
				shape.setDegree(arrowshape.getDegree());
			}
			
			else if(mouse.getTempShape().get(0).getClass().getCanonicalName().equalsIgnoreCase("TempObject.TempSpringShape"))
			{
				mouse.clearTempShape();
			}	
			
			else if(mouse.getTempShape().get(0).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.RopeShape"))
			{
				mouse.clearTempShape();
			}
			
			else if(mouse.getTempShape().get(0).getClass().getCanonicalName().equalsIgnoreCase("TempObject.TempFixJointShape"))
			{
				mouse.clearTempShape();
			}
		}
		
		// for check shape re scale
		if(mouse.getIndividualSelectIndex() != -1)
		{
			if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.TriangleShape"))
			{
	        	TriangleShape obj = (TriangleShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());
	        	reCheckShape(shapeContainer,mouse,obj.getPointPolygon(), redoTrunk, undoTrunk, currentMemento);
			}
			
			else if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.RectangleShape"))
			{
	        	RectangleShape obj = (RectangleShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());
	        	reCheckShape(shapeContainer,mouse,obj.getPointPolygon(), redoTrunk, undoTrunk, currentMemento);
			}
			
			else if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.PolygonShape"))
			{
				PolygonShape obj = (PolygonShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());
				reCheckShape(shapeContainer,mouse,obj.getPointPolygon(), redoTrunk, undoTrunk, currentMemento);
			}
		}
		
		// recheck Line for intersect to new shape
		reCheckLine(lineContainer,mouse,shapeContainer);
		
		
		
		if(save == false &&
		(mouse.getPreviousStateName().equalsIgnoreCase("Transform")
		||mouse.getPreviousStateName().equalsIgnoreCase("Scale")
		||mouse.getPreviousStateName().equalsIgnoreCase("Rotate")))
		{
			// Save to redo
			redoTrunk.clearMemento();
			undoTrunk.addMemento(currentMemento.saveToSmartBoardMemento());
		}
		
		mouse.setPreviousState(this.stateName);
		currentMemento.setState((ContainerAllShape)shapeContainer.clone(),CloneShape.cloneLineContainer(lineContainer),(Mouse) mouse.clone());
	}
	
	/**
	 * Remove the Shape that has been selected.
	 * @param shapeContainer
	 * @param mouse
	 */
	private void removeSelectShape(ContainerAllShape shapeContainer,Mouse mouse)
	{
		for(int i = mouse.getGroupSelectIndex().size()-1;i >= 0;i--)
		{
			shapeContainer.removeShapeSelectIndex(mouse.getGroupSelectIndex().get(i));
		}
	}
	
	/**
	 * Remove the mouse select index of shape.
	 * @param mouse
	 */
	private void removeMouseSelectIndex(Mouse mouse,ArrayList<ArrayList<Line2D>> lineContainer)
	{
		for(int i = mouse.getGroupLineSelectIndex().size()-1;i >= 0;i--)
		{
			LineIndex lineIndex = mouse.getGroupLineSelectIndex().get(i);
			lineContainer.get(lineIndex.getI()).remove(lineIndex.getJ());
		}
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
	
	/**
	 * For check the shape that was re scale are change the shape type or not
	 * @param shapeContainer
	 * @param mouse
	 * @param obj
	 */
	private void reCheckShape(ContainerAllShape shapeContainer,Mouse mouse,ArrayList<Point2D> obj,SmartBoardCaretaker redoTrunk,SmartBoardCaretaker undoTrunk,SmartBoardOriginator currentMemento)
	{	
    	//Check if Shape change to another shape type
    	ArrayList<Line2D> lineList = new ArrayList<Line2D>();
    	for(int i =0;i<obj.size();i++)
    	{
    		if(i == obj.size()-1)
    		{
    			lineList.add(new Line2D.Double(obj.get(i).getX(),obj.get(i).getY(),obj.get(0).getX(),obj.get(0).getY()));	
    		}
    		else
    		{
    			lineList.add(new Line2D.Double(obj.get(i).getX(),obj.get(i).getY(),obj.get(i+1).getX(),obj.get(i+1).getY()));	
    		}
    	}
		
		// เป็นตัวเช็คพวกที่เกิด shape มาจากจุดๆเดียว
		ArrayList<ArrayList<Point2D>> lineShape = AlgorTools.LineIntersection.getLineShape(lineList);
		lineShape = ShapeProperty.removenonArea(lineShape);
		
		if(lineShape.size() == 1)
		{
			
		}
		
		else
		{
			shapeContainer.removeShapeSelectIndex(mouse.getIndividualSelectIndex());
			mouse.setIndividualSelectIndex(-1);
			
			for(int i =0;i<lineShape.size();i++)
			{	
				if(AlgorTools.ShapeChecking.isTriangle(lineShape.get(i)))
				{
					System.out.println("ADD  Triangle");
					shapeContainer.addShape(CommonShapeFactory.createCommonShape(CommonShapeFactory.TRIANGLE,lineShape.get(i)));
				}
				
				else if(AlgorTools.ShapeChecking.isRectangle(lineShape.get(i)))
				{
					System.out.println("ADD  Rectangle");
					shapeContainer.addShape(CommonShapeFactory.createCommonShape(CommonShapeFactory.RECTANGLE,lineShape.get(i)));
				}
				
				else
				{
					System.out.println("ADD Polygon");
					shapeContainer.addShape(CommonShapeFactory.createCommonShape(CommonShapeFactory.POLYGON,lineShape.get(i)));
				}
			}
		}
		// remove if joint shape that connect was re to another shape
		removeJoint(shapeContainer);
	}
		
	/**
	 * For check the line for intersect to new shape
	 * @param lineContainer
	 * @param mouse
	 * @param shapeContainer
	 */
	private void reCheckLine(ArrayList<ArrayList<Line2D>> lineContainer,Mouse mouse,ContainerAllShape shapeContainer)
	{
		// Re check Individual Line
		if(mouse.getIndividualLineSelectIndex().getI() != -1
		&& mouse.getIndividualLineSelectIndex().getJ() != -1)
		{
			ArrayList<Line2D> lineList = new ArrayList<Line2D>();
			lineList.add(lineContainer.get(mouse.getIndividualLineSelectIndex().getI()).get(mouse.getIndividualLineSelectIndex().getJ()));
			lineContainer.get(mouse.getIndividualLineSelectIndex().getI()).remove(mouse.getIndividualLineSelectIndex().getJ());
			// Get the line intersection
			for(int i =0;i<lineContainer.size();i++)
			{
				for(int j =0;j<lineContainer.get(i).size();j++)
				{
					for(int k =0;k<lineList.size();k++)
					{
						if(lineList.get(k).intersectsLine(lineContainer.get(i).get(j))==true)
						{
							lineList.add(lineContainer.get(i).get(j));
							lineContainer.get(i).remove(j);
							i = 0;
							j = -1;
							mouse.getIndividualLineSelectIndex().setI(-1);
							mouse.getIndividualLineSelectIndex().setJ(-1);
							break;
						}
					}
				}
			}
			// Check the Close Shape
			ArrayList<ArrayList<Point2D>> lineShape = AlgorTools.LineIntersection.getLineShape(lineList);
			// Non create new shape then set line in linContainer and set new select
			if(lineShape.size() == 0)
			{
				lineContainer.add(lineList);
				mouse.getIndividualLineSelectIndex().setI(lineContainer.size()-1);
				mouse.getIndividualLineSelectIndex().setJ(0);
			}
			// Create new shape
			else
			{
				for(int i =0;i<lineShape.size();i++)
				{
					if(AlgorTools.ShapeChecking.isTriangle(lineShape.get(i)))
					{
						System.out.println("ADD  Triangle");
						shapeContainer.addShape(CommonShapeFactory.createCommonShape(CommonShapeFactory.TRIANGLE,lineShape.get(i)));
					}
					else if(AlgorTools.ShapeChecking.isRectangle(lineShape.get(i)))
					{
						System.out.println("ADD  Rectangle");
						shapeContainer.addShape(CommonShapeFactory.createCommonShape(CommonShapeFactory.RECTANGLE,lineShape.get(i)));
					}
					else
					{
						System.out.println("ADD Polygon");
						shapeContainer.addShape(CommonShapeFactory.createCommonShape(CommonShapeFactory.POLYGON,lineShape.get(i)));
					}	
				}
			}
		}
	}
}