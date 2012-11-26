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

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import SpecialShape.ArrowShape;
import SpecialShape.CrossShape;
import SpecialShape.FixJointShape;
import SpecialShape.JointShape;
import SpecialShape.RopeShape;
import SpecialShape.SpringShape;
import TempObject.TempFixJointShape;
import TempObject.TempSpringShape;
import UserInterface.Mouse;

import AlgorTools.CheckDrawLine;
import AlgorTools.LineProperty;
import AlgorTools.ShapeConvert;
import AlgorTools.ShapeIntersection;
import CommonShape.*;
import Container.ContainerAllShape;


public class IndividualSelectState extends State
{
	public IndividualSelectState()
	{
		this.stateName = "IndividualSelect";
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
		
		// Transform State
		else if((mouse.getIndividualSelectIndex() != -1 
		|| (mouse.getIndividualLineSelectIndex().getI() != -1 && mouse.getIndividualLineSelectIndex().getJ() != -1) 
		|| mouse.getTempShape().size() != 0)
		&& mouse.getmouseLeft()   == 0
		&& mouse.getmouseRight()  == 1
		&& (mouse.getmouseDrag()  == 0 || mouse.getmouseDrag()  == 1))
		{
			System.out.println("Change state to Transform state");
			mouse.setState(new TransformState());
		}
		
		// Release Rigth State
		else if(!mouse.getPreviousStateName().equalsIgnoreCase("GroupSelect")
		&& mouse.getmouseLeft()  == 0
		&& mouse.getmouseRight() == 0
		&& mouse.getmouseDrag()  == 0)
		{
			System.out.println("Change state to Release Right State");
			mouse.setState(new ReleaseRightState());
		}
		
	}
	
	/**
	 * Run state for check Individual select
	 * @param e
	 * @param shapeContainer
	 */
	public void runState(Point e,ContainerAllShape shapeContainer,Mouse mouse,ArrayList<ArrayList<Line2D>> lineContainer)
	{	
		mouse.setPreviousState(this.stateName);
		
		if(mouse.getIndividualSelectIndex() != -1)
		{	
			/**check for select rotation point and select vertex */
			if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.TriangleShape"))
			{
				TriangleShape obj = (TriangleShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());
				
				if(obj.isBackground() == false)
				{
					System.out.println("Normal shape");
					/** Check for select rotation point.*/
					if(obj.getRotateShape().contains(e) == true && obj.isNormalShape() == true)
					{
						mouse.setState(new RotateState());
						System.out.println("Change Sub state to Rotate state.");
						return;
					}
					
					/** Check for select scaling point*/
					if(obj.getScaleShape().contains(e) == true && obj.isNormalShape() == true)
					{
						mouse.setState(new ZoomState());
						System.out.println("Change Sub state to Zoom state.");
						return;
					}
					
					/** Check for select cross*/
					else if(obj.isFixed() == true)
					{
						ArrayList<Line2D> lineCross = obj.getLineCross();
						for(int i =0;i<lineCross.size();i++)
						{
							Line2D line = lineCross.get(i);
							double x1 = line.getX1();
							double y1 = line.getY1();
							double x2 = line.getX2();
							double y2 = line.getY2();
							double degree = LineProperty.getLineAngle(x1,y1,x2,y2);
							double Linewidth = Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
							
							Shape rectangle;
							Rectangle2D rec = new Rectangle2D.Double(x1-1, y1-4, Linewidth+2,11);
							if(degree >=90)
							{
								//angle √Õ∫®ÿ¥posx,posy
								AffineTransform  atx = AffineTransform.getRotateInstance(Math.toRadians(180-degree),x1,y1);
								//Take the shape object and create a rotated version
								rectangle = atx.createTransformedShape(rec);
							}
							else
							{
								//angle √Õ∫®ÿ¥posx,posy
								AffineTransform  atx = AffineTransform.getRotateInstance(-Math.toRadians(degree),x1,y1);
								//Take the shape object and create a rotated version
								rectangle = atx.createTransformedShape(rec);
							}
							if(rectangle.contains(e))
							{
								System.out.println("Select Cross");
								ArrayList<Line2D> lineCrossList = new ArrayList<Line2D>();
								lineCrossList.add((Line2D)lineCross.get(0).clone());
								lineCrossList.add((Line2D)lineCross.get(1).clone());
								mouse.addTempShape(new CrossShape(lineCrossList));
								mouse.setStartDragPoint(e);
								return;
							}
						}
					}
					
					/** Check for select vertex*/
					ArrayList<Ellipse2D> vertex = obj.getPolygonVertex();
					for(int i = 0;i<vertex.size();i++)
					{	
						if(vertex.get(i).contains(e) == true && obj.isNormalShape() == true)
						{
							mouse.setState(new ScaleState());
							mouse.setVertexIndex(i);
							System.out.println("Change Sub state to Scale state.");
							return;
						}
					}
				}
			}
			
			else if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.RectangleShape"))
			{
				RectangleShape obj = (RectangleShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());
				
				if(obj.isBackground() == false)
				{
					/** Check for select rotation point.*/
					if(obj.getRotateShape().contains(e) == true && obj.isNormalShape() == true)
					{
						mouse.setState(new RotateState());
						System.out.println("Change Sub state to Rotate state.");
						return;
					}
					
					/** Check for select scaling point*/
					if(obj.getScaleShape().contains(e) == true && obj.isNormalShape() == true)
					{
						mouse.setState(new ZoomState());
						System.out.println("Change Sub state to Zoom state.");
						return;
					}
					
					/** Check for select cross*/
					else if(obj.isFixed() == true)
					{
						ArrayList<Line2D> lineCross = obj.getLineCross();
						for(int i =0;i<lineCross.size();i++)
						{
							Line2D line = lineCross.get(i);
							double x1 = line.getX1();
							double y1 = line.getY1();
							double x2 = line.getX2();
							double y2 = line.getY2();
							double degree = LineProperty.getLineAngle(x1,y1,x2,y2);
							double Linewidth = Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
							
							Shape rectangle;
							Rectangle2D rec = new Rectangle2D.Double(x1-1, y1-4, Linewidth+2,11);
							if(degree >=90)
							{
								//angle √Õ∫®ÿ¥posx,posy
								AffineTransform  atx = AffineTransform.getRotateInstance(Math.toRadians(180-degree),x1,y1);
								//Take the shape object and create a rotated version
								rectangle = atx.createTransformedShape(rec);
							}
							else
							{
								//angle √Õ∫®ÿ¥posx,posy
								AffineTransform  atx = AffineTransform.getRotateInstance(-Math.toRadians(degree),x1,y1);
								//Take the shape object and create a rotated version
								rectangle = atx.createTransformedShape(rec);
							}
							if(rectangle.contains(e))
							{
								System.out.println("Select Cross");
								ArrayList<Line2D> lineCrossList = new ArrayList<Line2D>();
								lineCrossList.add((Line2D)lineCross.get(0).clone());
								lineCrossList.add((Line2D)lineCross.get(1).clone());
								mouse.addTempShape(new CrossShape(lineCrossList));
								mouse.setStartDragPoint(e);
								return;
							}
						}
					}
					
					/** Check for select vertex*/
					ArrayList<Ellipse2D> vertex = obj.getPolygonVertex();
					for(int i = 0;i<vertex.size();i++)
					{
						if(vertex.get(i).contains(e) == true && obj.isNormalShape() == true)
						{
							mouse.setState(new ScaleState());
							mouse.setVertexIndex(i);
							System.out.println("Change Sub state to Scale state.");
							return;
						}
					}
				}
			}
			
			else if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
			{
				EllipseShape obj = (EllipseShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());
				
				if(obj.isBackground() == false)
				{
					/** Check for select cross*/
					if(obj.isFixed() == true)
					{
						ArrayList<Line2D> lineCross = obj.getLineCross();
						for(int i =0;i<lineCross.size();i++)
						{
							Line2D line = lineCross.get(i);
							double x1 = line.getX1();
							double y1 = line.getY1();
							double x2 = line.getX2();
							double y2 = line.getY2();
							double degree = LineProperty.getLineAngle(x1,y1,x2,y2);
							double Linewidth = Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
							
							Shape rectangle;
							Rectangle2D rec = new Rectangle2D.Double(x1-1, y1-4, Linewidth+2,11);
							if(degree >=90)
							{
								//angle √Õ∫®ÿ¥posx,posy
								AffineTransform  atx = AffineTransform.getRotateInstance(Math.toRadians(180-degree),x1,y1);
								//Take the shape object and create a rotated version
								rectangle = atx.createTransformedShape(rec);
							}
							else
							{
								//angle √Õ∫®ÿ¥posx,posy
								AffineTransform  atx = AffineTransform.getRotateInstance(-Math.toRadians(degree),x1,y1);
								//Take the shape object and create a rotated version
								rectangle = atx.createTransformedShape(rec);
							}
							if(rectangle.contains(e))
							{
								System.out.println("Select Cross");
								ArrayList<Line2D> lineCrossList = new ArrayList<Line2D>();
								lineCrossList.add((Line2D)lineCross.get(0).clone());
								lineCrossList.add((Line2D)lineCross.get(1).clone());
								mouse.addTempShape(new CrossShape(lineCrossList));
								mouse.setStartDragPoint(e);
								return;
							}
						}
					}
					ArrayList<Ellipse2D> vertex = obj.getPolygonVertex();
					for(int i = 0;i<vertex.size();i++)
					{
						if(vertex.get(i).contains(e) == true && obj.isNormalShape() == true)
						{
							mouse.setState(new ScaleState());
							mouse.setVertexIndex(i);
							System.out.println("Change Sub state to Scale state");
							return;
						}
					}
				}
			}
			
			else if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.PolygonShape"))
			{
				PolygonShape obj = (PolygonShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());
				
				if(obj.isBackground() == false)
				{
					/** Check for select rotation point.*/
					if(obj.getRotateShape().contains(e) == true && obj.isNormalShape() == true)
					{
						mouse.setState(new RotateState());
						System.out.println("Change Sub state to Rotate state.");
						return;
					}
					
					/** Check for select scaling point*/
					if(obj.getScaleShape().contains(e) == true && obj.isNormalShape() == true)
					{
						mouse.setState(new ZoomState());
						System.out.println("Change Sub state to Zoom state.");
						return;
					}
					
					/** Check for select cross*/
					if(obj.isFixed() == true)
					{
						ArrayList<Line2D> lineCross = obj.getLineCross();
						for(int i =0;i<lineCross.size();i++)
						{
							Line2D line = lineCross.get(i);
							double x1 = line.getX1();
							double y1 = line.getY1();
							double x2 = line.getX2();
							double y2 = line.getY2();
							double degree = LineProperty.getLineAngle(x1,y1,x2,y2);
							double Linewidth = Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
							
							Shape rectangle;
							Rectangle2D rec = new Rectangle2D.Double(x1-1, y1-4, Linewidth+2,11);
							if(degree >=90)
							{
								//angle √Õ∫®ÿ¥posx,posy
								AffineTransform  atx = AffineTransform.getRotateInstance(Math.toRadians(180-degree),x1,y1);
								//Take the shape object and create a rotated version
								rectangle = atx.createTransformedShape(rec);
							}
							else
							{
								//angle √Õ∫®ÿ¥posx,posy
								AffineTransform  atx = AffineTransform.getRotateInstance(-Math.toRadians(degree),x1,y1);
								//Take the shape object and create a rotated version
								rectangle = atx.createTransformedShape(rec);
							}
							if(rectangle.contains(e))
							{
								System.out.println("Select Cross");
								ArrayList<Line2D> lineCrossList = new ArrayList<Line2D>();
								lineCrossList.add((Line2D)lineCross.get(0).clone());
								lineCrossList.add((Line2D)lineCross.get(1).clone());
								mouse.addTempShape(new CrossShape(lineCrossList));
								mouse.setStartDragPoint(e);
								return;
							}
						}
					}
					
					/** Check for select vertex*/
					ArrayList<Ellipse2D> vertex = obj.getPolygonVertex();
					for(int i = 0;i<vertex.size();i++)
					{
						if(vertex.get(i).contains(e) == true && obj.isNormalShape() == true)
						{
							mouse.setState(new ScaleState());
							mouse.setVertexIndex(i);
							System.out.println("Change Sub state to Scale state");
							return;
						}
					}
				}
			}
			
			else if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.ArrowShape"))
			{
				ArrowShape obj = (ArrowShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());
				/** Check for select rotation point.*/
				if(obj.getRotateShape().contains(e) == true)
				{
					mouse.setState(new RotateState());
					System.out.println("Change Sub state to Rotate state.");
					return;
				}
			}
		}
		
		// check for select new shape
		boolean iselect = false;
		if(mouse.getGroupSelectIndex().size() == 0 && mouse.getGroupLineSelectIndex().size()==0)
		{	
			// select new line  ArrayList<ArrayList<Line2D>> lineContainer)
			for(int i = 0;i<lineContainer.size();i++)
			{
				for(int j =0;j<lineContainer.get(i).size();j++)
				{
					Line2D line = lineContainer.get(i).get(j);
					double x1 = line.getX1();
					double y1 = line.getY1();
					double x2 = line.getX2();
					double y2 = line.getY2();
					double degree = LineProperty.getLineAngle(x1,y1,x2,y2);
					double Linewidth = Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
					
					Shape rectangle;
					Rectangle2D rec = new Rectangle2D.Double(x1-1, y1-4, Linewidth+2,11);
					if(degree >=90)
					{
						//angle √Õ∫®ÿ¥posx,posy
						AffineTransform  atx = AffineTransform.getRotateInstance(Math.toRadians(180-degree),x1,y1);
						//Take the shape object and create a rotated version
						rectangle = atx.createTransformedShape(rec);
					}
					else
					{
						//angle √Õ∫®ÿ¥posx,posy
						AffineTransform  atx = AffineTransform.getRotateInstance(-Math.toRadians(degree),x1,y1);
						//Take the shape object and create a rotated version
						rectangle = atx.createTransformedShape(rec);
					}
					
					if(rectangle.contains(e))
					{
						iselect = true;
						mouse.setIndividualSelectIndex(-1);
						mouse.getIndividualLineSelectIndex().setI(i);
						mouse.getIndividualLineSelectIndex().setJ(j);
						mouse.setStartDragPoint(e);
						return;
					}
				}
			}
			
			if(iselect == false)
			{
				//	 select new shape
				for(int i = shapeContainer.getShapeContainerSize()-1;i >= 0;i--)
				{
					if(mouse.getGroupSelectIndex().contains(i))
					{
						
					}
					
					else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.TriangleShape"))
					{
						TriangleShape obj = (TriangleShape)shapeContainer.getShape(i);
						if(obj.getPolygon().contains(e) == true)
						{
							iselect = true;
							System.out.println("Select Triangle");
							mouse.getGroupSelectPolygon().reset();
							mouse.setIndividualSelectIndex(i);
							mouse.setStartDragPoint(e);
							mouse.getIndividualLineSelectIndex().setI(-1);
							mouse.getIndividualLineSelectIndex().setJ(-1);
							mouse.setTouchShape(ShapeIntersection.shapeIntersectEdge(shapeContainer,mouse.getIndividualSelectIndex()));
						}
					}
					
					else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.RectangleShape"))
					{
						RectangleShape obj = (RectangleShape)shapeContainer.getShape(i);
						if(obj.getPolygon().contains(e) == true)
						{
							iselect = true;
							System.out.println("Select Rectangle");
							mouse.getGroupSelectPolygon().reset();
							mouse.setIndividualSelectIndex(i);
							mouse.setStartDragPoint(e);
							mouse.getIndividualLineSelectIndex().setI(-1);
							mouse.getIndividualLineSelectIndex().setJ(-1);
							mouse.setTouchShape(ShapeIntersection.shapeIntersectEdge(shapeContainer,mouse.getIndividualSelectIndex()));
			        	}
					}
					
					else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
					{
						EllipseShape obj = (EllipseShape)shapeContainer.getShape(i);
						if(obj.getEllipse().contains(e) == true)
						{
							iselect = true;
							System.out.println("Select Ellipse");
							mouse.getGroupSelectPolygon().reset();
							mouse.setIndividualSelectIndex(i);
							mouse.setStartDragPoint(e);
							mouse.getIndividualLineSelectIndex().setI(-1);
							mouse.getIndividualLineSelectIndex().setJ(-1);
							mouse.setTouchShape(ShapeIntersection.shapeIntersectEdge(shapeContainer,mouse.getIndividualSelectIndex()));
			        	}
					}
					
					else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.PolygonShape"))
					{
						PolygonShape obj = (PolygonShape)shapeContainer.getShape(i);
						if(obj.getPolygon().contains(e) == true)
						{
							iselect = true;
							System.out.println("Select Polygon");
							mouse.getGroupSelectPolygon().reset();
							mouse.setIndividualSelectIndex(i);
							mouse.setStartDragPoint(e);
							mouse.getIndividualLineSelectIndex().setI(-1);
							mouse.getIndividualLineSelectIndex().setJ(-1);
							mouse.setTouchShape(ShapeIntersection.shapeIntersectEdge(shapeContainer,mouse.getIndividualSelectIndex()));
			        	}
					}
					
					else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.ArrowShape"))
					{
						ArrowShape obj = (ArrowShape)shapeContainer.getShape(i);
						for(int j =0;j<obj.getBondingBox().size();j++)
						{
							if(obj.getBondingBox().get(j).contains(e) == true)
							{
								iselect = true;
								System.out.println("Select Arrow");
								mouse.getGroupSelectPolygon().reset();
								mouse.setIndividualSelectIndex(i);
								mouse.setStartDragPoint(e);
								mouse.getIndividualLineSelectIndex().setI(-1);
								mouse.getIndividualLineSelectIndex().setJ(-1);
								break;
							}
						}
					}
					
					else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.SpringShape"))
					{
						SpringShape obj = (SpringShape)shapeContainer.getShape(i);
						// Select spring at head
						if(obj.getStartBound().contains(e) == true)
						{
							obj.setSelect(0);
							iselect = true;
							System.out.println("Select Spring");
							mouse.getGroupSelectPolygon().reset();
							mouse.setIndividualSelectIndex(i);
							mouse.setStartDragPoint(e);
							mouse.getIndividualLineSelectIndex().setI(-1);
							mouse.getIndividualLineSelectIndex().setJ(-1);
							break;
						}
						
						// Select spring at tail
						else if(obj.getEndBound().contains(e) == true)
						{
							obj.setSelect(1);
							iselect = true;
							System.out.println("Select Spring");
							mouse.getGroupSelectPolygon().reset();
							mouse.setIndividualSelectIndex(i);
							mouse.setStartDragPoint(e);
							mouse.getIndividualLineSelectIndex().setI(-1);
							mouse.getIndividualLineSelectIndex().setJ(-1);
							break;
						}
						
						else
						{
							ArrayList<Point2D> point = new ArrayList<Point2D>();
							point.add(new Point2D.Double(obj.getStartBound().getCenterX(),obj.getStartBound().getCenterY()));
							point.add(new Point2D.Double(obj.getEndBound().getCenterX(),obj.getEndBound().getCenterY()));
							
							Line2D line = CheckDrawLine.getLine(point,5).get(0);
							
							double x1 = line.getX1();
							double y1 = line.getY1();
							double x2 = line.getX2();
							double y2 = line.getY2();
							double degree = LineProperty.getLineAngle(x1,y1,x2,y2);
							double Linewidth = Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
							
							Shape rectangle;
							Rectangle2D rec = new Rectangle2D.Double(x1-1, y1-4, Linewidth+2,11);
							
							if(degree >=90)
							{
								//angle √Õ∫®ÿ¥posx,posy
								AffineTransform  atx = AffineTransform.getRotateInstance(Math.toRadians(180-degree),x1,y1);
								//Take the shape object and create a rotated version
								rectangle = atx.createTransformedShape(rec);
							}
							
							else
							{
								//angle √Õ∫®ÿ¥posx,posy
								AffineTransform  atx = AffineTransform.getRotateInstance(-Math.toRadians(degree),x1,y1);
								//Take the shape object and create a rotated version
								rectangle = atx.createTransformedShape(rec);
							}
							
							if(rectangle.contains(e))
							{
								System.out.println("Select Spring");
								TempSpringShape spring = new TempSpringShape(
														 new Point2D.Double(obj.getStartBound().getCenterX(),obj.getStartBound().getCenterY()),
														 new Point2D.Double(obj.getEndBound().getCenterX(),obj.getEndBound().getCenterY()));
								mouse.addTempShape(spring);
								mouse.setStartDragPoint(e);
								mouse.setIndividualSelectIndex(i);
								return;
							}
						}
					}
					
					else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.JointShape"))
					{
						JointShape obj = (JointShape)shapeContainer.getShape(i);
						if(obj.getRotateShape().contains(e) == true)
						{
							iselect = true;
							System.out.println("Select Joint");
							mouse.getGroupSelectPolygon().reset();
							mouse.setIndividualSelectIndex(i);
							mouse.setStartDragPoint(e);
							mouse.getIndividualLineSelectIndex().setI(-1);
							mouse.getIndividualLineSelectIndex().setJ(-1);
							break;
						} 
					}
					
					else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.FixJointShape"))
					{
						FixJointShape obj = (FixJointShape)shapeContainer.getShape(i);
						ArrayList<Point2D> point = new ArrayList<Point2D>();
						point.add(obj.getStartIndex().getCM());
						point.add(obj.getEndIndex().getCM());
						ArrayList<Line2D> lineFixJoint = CheckDrawLine.getLine(point,5);
						
						for(int j =0;j<lineFixJoint.size();j++)
						{
							Line2D line = lineFixJoint.get(j);
							double x1 = line.getX1();
							double y1 = line.getY1();
							double x2 = line.getX2();
							double y2 = line.getY2();
							double degree = LineProperty.getLineAngle(x1,y1,x2,y2);
							double Linewidth = Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
							
							Shape rectangle;
							Rectangle2D rec = new Rectangle2D.Double(x1-1, y1-4, Linewidth+2,11);
							if(degree >=90)
							{
								//angle √Õ∫®ÿ¥posx,posy
								AffineTransform  atx = AffineTransform.getRotateInstance(Math.toRadians(180-degree),x1,y1);
								//Take the shape object and create a rotated version
								rectangle = atx.createTransformedShape(rec);
							}
							else
							{
								//angle √Õ∫®ÿ¥posx,posy
								AffineTransform  atx = AffineTransform.getRotateInstance(-Math.toRadians(degree),x1,y1);
								//Take the shape object and create a rotated version
								rectangle = atx.createTransformedShape(rec);
							}
							
							if(rectangle.contains(e))
							{
								System.out.println("Select Fix Joint");
								mouse.addTempShape(new TempFixJointShape((Line2D)lineFixJoint.get(0).clone()));
								mouse.setStartDragPoint(e);
								mouse.setIndividualSelectIndex(i);
								return;
							}
							
						}
					}
					
					else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.RopeShape"))
					{
						RopeShape obj = (RopeShape)shapeContainer.getShape(i);
						ArrayList<Point2D> point = ShapeConvert.convertRopeLinetoPoint2D(obj.getShape());
						ArrayList<Line2D> line = CheckDrawLine.getLine(point,5);
						
						for(int j = 0;j<line.size();j++)
						{
							double x1 = line.get(j).getX1();
							double y1 = line.get(j).getY1();
							double x2 = line.get(j).getX2();
							double y2 = line.get(j).getY2();
							
							
							
							double degree = LineProperty.getLineAngle(x1,y1,x2,y2);
							double Linewidth = Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
							
							Shape rectangle;
							Rectangle2D rec = new Rectangle2D.Double(x1-1, y1-4, Linewidth+2,11);
							
							if(degree >=90)
							{
								//angle √Õ∫®ÿ¥posx,posy
								AffineTransform  atx = AffineTransform.getRotateInstance(Math.toRadians(180-degree),x1,y1);
								//Take the shape object and create a rotated version
								rectangle = atx.createTransformedShape(rec);
							}
							
							else
							{
								//angle √Õ∫®ÿ¥posx,posy
								AffineTransform  atx = AffineTransform.getRotateInstance(-Math.toRadians(degree),x1,y1);
								//Take the shape object and create a rotated version
								rectangle = atx.createTransformedShape(rec);
							}
							
							
							if(rectangle.contains(e))
							{
								System.out.println("Select Rope");
								RopeShape rope = new RopeShape(ShapeConvert.convertPoint2DtoLine2D(point));
								mouse.addTempShape(rope);
								mouse.setStartDragPoint(e);
								mouse.setIndividualSelectIndex(i);
								return;
							}
						}
						
					}
				}
		
			}
		}
		
		if(iselect == false)
		{
			mouse.setIndividualSelectIndex(-1);
			// non individual select then change state to 
			mouse.setState(new GroupSelectState());
			System.out.println("Change Sub state to Group Select state");
			GroupSelectState state = (GroupSelectState)mouse.getState();
			state.runState(e,mouse);
		}
	}
}
