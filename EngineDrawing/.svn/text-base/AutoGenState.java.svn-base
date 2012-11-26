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


import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import AlgorTools.CloneShape;
import AlgorTools.ShapeRegenerate;
import Container.ContainerAllShape;
import Memento.SmartBoardCaretaker;
import Memento.SmartBoardOriginator;
import SpecialShape.ArrowShape;
import SpecialShape.SpecialFactory;
import UserInterface.Mouse;

public class AutoGenState extends State
{
	public AutoGenState()
	{
		this.stateName = "AutoGen";
	}

	@Override
	public void handle(Mouse mouse) 
	{
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
		
		// Auto Arrow state
		else if(mouse.getautoTool().equalsIgnoreCase("Arrow")
		&& mouse.getmouseLeft()   == 1
		&& mouse.getmouseRight()  == 0
		&& mouse.getmouseDrag()   == 0)
		{
			System.out.println("Change state to Auto Arrow State");
			mouse.setState(new AutoArrowState());
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
		
		// Auto Comment State
		else if(mouse.getautoTool().equalsIgnoreCase("Comment")
		&& mouse.getmouseLeft()   == 1
		&& mouse.getmouseRight()  == 0
		&& mouse.getmouseDrag()   == 0)
		{
			System.out.println("Change state to Auto Comment State");
			mouse.setState(new AutoCommentState());
		}
		
		// Individual Select State
		else if(mouse.getmouseLeft() == 0
		&& mouse.getmouseRight()  	 == 1
		&& mouse.getmouseDrag()  	 == 0)
		{
			System.out.println("Change state to Individual select");
			mouse.setState(new IndividualSelectState());
		}
	}
	
	public void runState(ContainerAllShape shapeContainer,ArrayList<ArrayList<Line2D>> lineContainer,ArrayList<Line2D> commentContainer,Mouse mouse,SmartBoardCaretaker redoTrunk,SmartBoardCaretaker undoTrunk,SmartBoardOriginator currentMemento)
	{
		mouse.setPreviousState(this.stateName);
		
		// Check that point is equal 2 for make the shape
		if(mouse.getAutoGenPoint().size()==2)
		{	
			if(mouse.getautoTool().equalsIgnoreCase("Triangle"))
			{
				redoTrunk.clearMemento();
				undoTrunk.addMemento(currentMemento.saveToSmartBoardMemento());
				shapeContainer.addShape(AlgorTools.ShapeAutoGenerate.autoGenTriangle(mouse.getAutoGenPoint().get(0),mouse.getAutoGenPoint().get(1)));
				currentMemento.setState((ContainerAllShape)shapeContainer.clone(),CloneShape.cloneLineContainer(lineContainer),(Mouse) mouse.clone());
			}
			
			else if(mouse.getautoTool().equalsIgnoreCase("Rectangle"))
			{
				redoTrunk.clearMemento();
				undoTrunk.addMemento(currentMemento.saveToSmartBoardMemento());
				shapeContainer.addShape(AlgorTools.ShapeAutoGenerate.autoGenRectangle(mouse.getAutoGenPoint().get(0),mouse.getAutoGenPoint().get(1)));
				currentMemento.setState((ContainerAllShape)shapeContainer.clone(),CloneShape.cloneLineContainer(lineContainer),(Mouse) mouse.clone());

			}
			
			else if(mouse.getautoTool().equalsIgnoreCase("Ellipse"))
			{
				redoTrunk.clearMemento();
				undoTrunk.addMemento(currentMemento.saveToSmartBoardMemento());
				shapeContainer.addShape(AlgorTools.ShapeAutoGenerate.autoGenEllipse(mouse.getAutoGenPoint().get(0),mouse.getAutoGenPoint().get(1)));
				currentMemento.setState((ContainerAllShape)shapeContainer.clone(),CloneShape.cloneLineContainer(lineContainer),(Mouse) mouse.clone());
			}

			else if(mouse.getautoTool().equalsIgnoreCase("Arrow"))
			{
				redoTrunk.clearMemento();
				undoTrunk.addMemento(currentMemento.saveToSmartBoardMemento());
				
				Point2D startPoint = mouse.getAutoGenPoint().get(0);
				Point2D endPoint   = mouse.getAutoGenPoint().get(mouse.getAutoGenPoint().size()-1);
				ArrayList<Line2D> arrow = new ArrayList<Line2D>();
				
				arrow.add(new Line2D.Double(endPoint.getX(),endPoint.getY(),startPoint.getX(),startPoint.getY()));
				arrow.add(new Line2D.Double(endPoint.getX(),endPoint.getY(),startPoint.getX(),startPoint.getY()));
				arrow.add(new Line2D.Double(endPoint.getX(),endPoint.getY(),startPoint.getX(),startPoint.getY()));
				
				for(int i =0;i<shapeContainer.getShapeContainerSize();i++)
				{
					if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.ArrowShape"))
					{
						shapeContainer.removeShapeSelectIndex(i);
						i = -1;
					}
				}
				
				ArrowShape arrowshape = (ArrowShape) SpecialFactory.createSpecialShape("Arrow",arrow);
				arrowshape = ShapeRegenerate.regenerateArrow(arrowshape.getBondingBox(),arrowshape.getPointIntersection());
				shapeContainer.addShape(arrowshape);
				currentMemento.setState((ContainerAllShape)shapeContainer.clone(),CloneShape.cloneLineContainer(lineContainer),(Mouse) mouse.clone());
			}
			
			else if(mouse.getautoTool().equalsIgnoreCase("Balloon"))
			{
				redoTrunk.clearMemento();
				undoTrunk.addMemento(currentMemento.saveToSmartBoardMemento());
				
				for(int i =0;i<mouse.getTempShape().size();i++)
				{
					shapeContainer.addShape(mouse.getTempShape().get(i));
				}
				currentMemento.setState((ContainerAllShape)shapeContainer.clone(),CloneShape.cloneLineContainer(lineContainer),(Mouse) mouse.clone());
			}
			
			else if(mouse.getautoTool().equalsIgnoreCase("Car"))
			{
				redoTrunk.clearMemento();
				undoTrunk.addMemento(currentMemento.saveToSmartBoardMemento());
				
				for(int i =0;i<mouse.getTempShape().size();i++)
				{
					shapeContainer.addShape(mouse.getTempShape().get(i));
				}
				currentMemento.setState((ContainerAllShape)shapeContainer.clone(),CloneShape.cloneLineContainer(lineContainer),(Mouse) mouse.clone());
			}
			
			// For clear the object
			mouse.getAutoGenPoint().clear();
			mouse.getTempShape().clear();
		}
		
		else if(mouse.getAutoGenPoint().size() != 0)
		{
			if(mouse.getautoTool().equalsIgnoreCase("Comment"))
			{
				redoTrunk.clearMemento();
				undoTrunk.addMemento(currentMemento.saveToSmartBoardMemento());
				
				for(int i =0;i<mouse.getAutoGenPoint().size()-1;i++)
				{
					commentContainer.add(new Line2D.Double(mouse.getAutoGenPoint().get(i).getX(),mouse.getAutoGenPoint().get(i).getY(),mouse.getAutoGenPoint().get(i+1).getX(),mouse.getAutoGenPoint().get(i+1).getY()));
				}
				currentMemento.setState((ContainerAllShape)shapeContainer.clone(),CloneShape.cloneLineContainer(lineContainer),(Mouse) mouse.clone());
			}
			
			// For clear the object
			mouse.getAutoGenPoint().clear();
			mouse.getTempShape().clear();
		}
		
		else
		{
			mouse.getAutoGenPoint().clear();
			mouse.getTempShape().clear();
		}
	}
	
}
