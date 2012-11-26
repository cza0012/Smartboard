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
package EnginePhysic;

import java.awt.Point;

import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Shape;
import Container.ContainerAllShape;
import EngineDrawing.State;
import UserInterface.Mouse;

public class HandSelectState extends State
{

	public HandSelectState()
	{
		this.stateName = "HandSelect";
	}
	
	public void handle(Mouse mouse)
	{
		// Transform State
		if(mouse.getIndividualSelectIndex() != -1
		&& mouse.getmouseLeft()   == 1
		&& mouse.getmouseRight()  == 0
		&& (mouse.getmouseDrag()  == 0 || mouse.getmouseDrag()  == 1))
		{
			System.out.println("Change state to Transform state");
			mouse.setState(new TransformState());
		}
		
		// Simulation State
		else if(mouse.getmouseRight()  	== 1)
		{
			System.out.println("Change state to Simulation state");
			mouse.setState(new SimulationState());
		}
		
		// Open Menu State
		else if(mouse.getmouseMiddle() == 1)
		{
			System.out.println("Change state to Open Menu state");
			mouse.setState(new OpenMenuState());
		}
		
		// Release Left State
		else if(mouse.getmouseLeft()    == 0
		&& mouse.getmouseDrag()  		== 0)
		{
			System.out.println("Change state to ReleaseLeft state");
			mouse.setState(new ReleaseLeftState());
		}
	}
	
	/**
	 * Run state for check Individual select
	 * @param e
	 * @param shapeContainer
	 */
	public void runState(Point e,ContainerAllShape bodyContainer,Mouse mouse)
	{	
		// check for select new shape
		boolean iselect = false;
		for(int i = bodyContainer.getBodyContainerSize()-1;i >= 0;i--)
		{
			if(mouse.getGroupSelectIndex().contains(i))
			{
				
			}
			
			else
			{
				Body obj = bodyContainer.getBody(i);
				Shape shape = obj.getShape();
				
				if(shape.getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Polygon"))
				{
					java.awt.Polygon polygon = AlgorTools.ShapeConvert.convertBodytoPolygon(obj);	
					if(polygon.contains(e)&& obj.isBackground() == false)
					{
						iselect = true;
						System.out.println("Select Body Polygon");
						mouse.getGroupSelectPolygon().reset();
						mouse.setIndividualSelectIndex(i);
						mouse.setStartDragPoint(e);
						obj.setMovementBody(false);
					}
				}
				
				else if(shape.getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Circle"))
				{
					java.awt.geom.Ellipse2D circle = AlgorTools.ShapeConvert.convertCircleBodytoEllipse(obj);
					
					if(circle.contains(e)&& obj.isBackground() == false)
					{
						iselect = true;
						System.out.println("Select Body Cicle");
						mouse.getGroupSelectPolygon().reset();
						mouse.setIndividualSelectIndex(i);
						mouse.setStartDragPoint(e);
						obj.setMovementBody(false);
					}
				}
			}
			
		}
		
		if(iselect == false)
		{
			mouse.setIndividualSelectIndex(-1);
		}
	}
}
