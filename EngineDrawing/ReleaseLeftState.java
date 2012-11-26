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


import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import MainInterface.RunProgram;
import Memento.SmartBoardCaretaker;
import Memento.SmartBoardOriginator;
import SpecialShape.ArrowShape;
import SpecialShape.SpecialFactory;
import UserInterface.Mouse;

import AlgorTools.CheckDrawLine;
import AlgorTools.CloneShape;
import AlgorTools.ShapeProperty;
import AlgorTools.ShapeRegenerate;
import CommonShape.CommonShape;
import CommonShape.CommonShapeFactory;
import CommonShape.EllipseShape;
import CommonShape.PolygonShape;
import CommonShape.RectangleShape;
import CommonShape.TriangleShape;
import Container.ContainerAllShape;

/**
 * Release Left State for check all the action from mouse left that you done and execute.
 * 
 * @author Magic Board Team
 *
 */

public class ReleaseLeftState extends State
{
	public ReleaseLeftState()
	{
		this.stateName = "ReleaseLeft";
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
		
		// Individual Select State
		else if(mouse.getmouseLeft()   == 0
		&& mouse.getmouseRight()  == 1
		&& mouse.getmouseDrag()   == 0)
		{
			System.out.println("Change state to Individual select");
			mouse.setState(new IndividualSelectState());
		}
	}
	
	/**
	 * Run State for check the draw line for new shape
	 * @param shapeContainer
	 * @param lineContainer
	 * @param lineDrawing
	 */
	public void runState(ContainerAllShape shapeContainer,Ellipse2D lineVertex,ArrayList<ArrayList<Line2D>> lineContainer,ArrayList<Point2D> lineDrawing,SmartBoardCaretaker redoTrunk,SmartBoardCaretaker undoTrunk,SmartBoardOriginator currentMemento,Mouse mouse)
	{
		mouse.setPreviousState(this.stateName);
		
		if(lineDrawing.size() <= 1)
		{
			lineDrawing.clear();
		}
		
		else if(lineDrawing.size() > 1)
		{
			//Save to redo
			redoTrunk.clearMemento();
			undoTrunk.addMemento(currentMemento.saveToSmartBoardMemento());
			
			// เป็นตัวเช็คว่าเราจะวาดรูปวงกลมไหม เพราะวงกลมต้องใช้การวาดที่ต่างจากวัตถุอื่นๆ
			boolean drawcircle = true;
			
			// เป็นตัวเช็คว่าเกิดรูปไหมถ้าใช่ก็ไม่ต้องเอากลับไปใส่ใน all line ถ้าไม่ใช่ให้ นำกลับไปใส่ allline ทั้งหมด
			boolean addOrnot = false;
			
			// ต่อเส้นกับจุด vertex ของอีกเส้น
			if(lineVertex.getWidth()!= 0 && lineVertex.getHeight() != 0)
			{
				Point2D Start = lineDrawing.get(0);
				Point2D End	  = new Point2D.Double(lineVertex.getCenterX(),lineVertex.getCenterY());
				lineDrawing.clear();
				lineDrawing.add(Start);
				lineDrawing.add(End);
				lineVertex.setFrameFromDiagonal(0, 0, 0, 0);
				drawcircle = false;
			}
			
			else
			{
				lineVertex.setFrameFromDiagonal(0, 0, 0, 0);
			}
			
			if(drawcircle == true)
			{
				ArrayList<Line2D> line = CheckDrawLine.getLine(lineDrawing,RunProgram.runner.drawing.penAccurate);
				ArrayList<ArrayList<Point2D>> lineShape = AlgorTools.LineIntersection.getLineShape(line);
				lineShape = ShapeProperty.removenonArea(lineShape);
				
				if(lineShape.size() == 1)
				{
					if(AlgorTools.ShapeChecking.isEllipse(lineShape.get(0)))
					{
						addOrnot = true;
						System.out.println("ADD  Ellipse");
						shapeContainer.addShape(CommonShapeFactory.createCommonShape(CommonShapeFactory.ELLIPSE,lineShape.get(0)));
					}
					
					else
					{
						drawcircle = false;
					}
					
				}
				
				else
				{
					drawcircle = false;
				}
				
			}
			
			if(drawcircle == false)
			{
				ArrayList<Line2D> line = CheckDrawLine.getLine(lineDrawing,RunProgram.runner.drawing.penAccurate);
				
				// ตรวจสอบว่า line ใหม่ทั้งหมดไป ทับกับ line เก่าหรือเปล่า 
				// ถ้าตัดกันให้นำ line เก่า ไปรวมเป็นชุดเดียวกับ line ใหม่ด้วย แล้วนำไปเช็คทีเดียวว่าตัดรวมแล้วกีจุด
				for(int i =0;i<lineContainer.size();i++)
				{
					for(int j =0;j<lineContainer.get(i).size();j++)
					{
						for(int k =0;k<line.size();k++)
						{
							if(line.get(k).intersectsLine(lineContainer.get(i).get(j))==true)
							{
								line.add(lineContainer.get(i).get(j));
								lineContainer.get(i).remove(j);
								j = -1; // For break loop back to loop i
								i = -1;
								break;
							}
						}
						
						if(i == -1)
						{
							break;
						}
					}
				}

				// เป็นตัวเช็คพวกที่เกิด shape มาจากจุดๆเดียว
				ArrayList<ArrayList<Point2D>> lineShape = AlgorTools.LineIntersection.getLineShape(line);
				lineShape = ShapeProperty.removenonArea(lineShape);
				
				
				// Open Shape
				if(lineShape.size() == 0)
				{
					for(int j =0;j<line.size()-1;j++)
					{
						ArrayList<Line2D> lineList = new ArrayList<Line2D>();
						lineList.add(line.get(j));
						lineList.add(line.get(j+1));

						if(addOrnot == true)
						{
							break;
						}
						// Cross Checking
						else if(AlgorTools.ShapeChecking.isCross(lineList) == true)
						{
							// Check cross are contain in
							for(int i = 0;i<shapeContainer.getShapeContainerSize();i++)
							{	
								if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.TriangleShape"))
								{
									TriangleShape triangle = (TriangleShape)shapeContainer.getShape(i);
									if(triangle.getPolygon().contains(lineList.get(0).getP1()) == true
									 &&triangle.getPolygon().contains(lineList.get(0).getP2()) == true
									 &&triangle.getPolygon().contains(lineList.get(1).getP2()) == true
									 &&triangle.getPolygon().contains(lineList.get(1).getP2()) == true
									 &&triangle.isBackground() == false)
									{
										addOrnot = true;
										triangle.generateCross(lineList);
										shapeContainer.setShapeIndex(i,triangle);
										System.out.println("ADD  Cross");
										break;
									}
								}
								
								else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.RectangleShape"))
								{
									RectangleShape rectangle = (RectangleShape)shapeContainer.getShape(i);
									if(rectangle.getPolygon().contains(lineList.get(0).getP1()) == true
									 &&rectangle.getPolygon().contains(lineList.get(0).getP2()) == true
									 &&rectangle.getPolygon().contains(lineList.get(1).getP2()) == true
									 &&rectangle.getPolygon().contains(lineList.get(1).getP2()) == true
									 &&rectangle.isBackground() == false)
									{
										addOrnot = true;
										rectangle.generateCross(lineList);
										shapeContainer.setShapeIndex(i,rectangle);
										System.out.println("ADD  Cross");
										break;
									}
								}
								
								else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
								{
									EllipseShape ellipse = (EllipseShape)shapeContainer.getShape(i);
									if(ellipse.getEllipse().contains(lineList.get(0).getP1()) == true
									 &&ellipse.getEllipse().contains(lineList.get(0).getP2()) == true
									 &&ellipse.getEllipse().contains(lineList.get(1).getP2()) == true
									 &&ellipse.getEllipse().contains(lineList.get(1).getP2()) == true
									 &&ellipse.isBackground() == false)
									{
										addOrnot = true;
										ellipse.generateCross(lineList);
										shapeContainer.setShapeIndex(i,ellipse);
										System.out.println("ADD  Cross");
										break;
									}
								}
								
								else if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.PolygonShape"))
								{
									PolygonShape polygon = (PolygonShape)shapeContainer.getShape(i);
									if(polygon.getPolygon().contains(lineList.get(0).getP1()) == true
									 &&polygon.getPolygon().contains(lineList.get(0).getP2()) == true
									 &&polygon.getPolygon().contains(lineList.get(1).getP2()) == true
									 &&polygon.getPolygon().contains(lineList.get(1).getP2()) == true
									 &&polygon.isBackground() == false)
									{
										addOrnot = true;
										polygon.generateCross(lineList);
										shapeContainer.setShapeIndex(i,polygon);
										System.out.println("ADD  Cross");
										break;
									}
								}
							}
						}
						
					}
					
					// Arrow Checking
					if(AlgorTools.ShapeChecking.isArrow(line) == true)
					{
						addOrnot = true;
						
						for(int i =0;i<shapeContainer.getShapeContainerSize();i++)
						{
							if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.ArrowShape"))
							{
								shapeContainer.removeShapeSelectIndex(i);
								i = -1;
							}
						}
						ArrowShape arrowshape = (ArrowShape)SpecialFactory.createSpecialShape(SpecialFactory.ARROW,line);
						arrowshape = ShapeRegenerate.regenerateArrow(arrowshape.getBondingBox(),arrowshape.getPointIntersection());
						shapeContainer.addShape(arrowshape);
						System.out.println("ADD  Arrow");
					}
					
					// Spring Checking
					else if(AlgorTools.ShapeChecking.isSpring(line) == true)
					{
						//Check the point of start and end index of spring are in shape or not.
						CommonShape startIndex = AlgorTools.CheckShapeContainer.getShapeContainPoint2D(shapeContainer,lineDrawing.get(0));
						CommonShape endIndex   = AlgorTools.CheckShapeContainer.getShapeContainPoint2D(shapeContainer,lineDrawing.get(lineDrawing.size()-1));
						System.out.println("Add spring 1");
						if(startIndex != null && endIndex != null && startIndex != endIndex)
						{
							System.out.println("Add spring 2");
							addOrnot = true;
							shapeContainer.addShape(SpecialFactory.createSpecialJointShape(SpecialFactory.SPRING,startIndex,endIndex));
							System.out.println("ADD  Spring");	
						}	
					}
				}
				
				// Close Shape
				else
				{
					for(int i =0;i<lineShape.size();i++)
					{	
						if(AlgorTools.ShapeChecking.isTriangle(lineShape.get(i)))
						{
							addOrnot = true;
							System.out.println("ADD  Triangle");
							shapeContainer.addShape(CommonShapeFactory.createCommonShape(CommonShapeFactory.TRIANGLE,lineShape.get(i)));
						}
						
						else if(AlgorTools.ShapeChecking.isRectangle(lineShape.get(i)))
						{
							addOrnot = true;
							System.out.println("ADD  Rectangle");
							shapeContainer.addShape(CommonShapeFactory.createCommonShape(CommonShapeFactory.RECTANGLE,lineShape.get(i)));
						}

						else
						{
							addOrnot = true;
							System.out.println("ADD Polygon");
							shapeContainer.addShape(CommonShapeFactory.createCommonShape(CommonShapeFactory.POLYGON,lineShape.get(i)));
						}
					}
				}
				
				if(addOrnot == false)
				{
					lineContainer.add(line);
				}
			}	
			lineDrawing.clear();
			currentMemento.setState((ContainerAllShape)shapeContainer.clone(),CloneShape.cloneLineContainer(lineContainer),(Mouse) mouse.clone());
		}
	}
}
