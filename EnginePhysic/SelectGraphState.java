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

import PhysicDrawing.PhysicFrame;
import PhysicDrawing.PhysicGraph;
import UserInterface.Mouse;
import Container.ContainerAllShape;
import EngineDrawing.State;
import IconMenu.IconMenu;

public class SelectGraphState extends State
{
	public SelectGraphState()
	{
		this.stateName = "SelectGraph";
	}
	
	@Override
	public void handle(Mouse mouse) 
	{
		// Close Menu State
		if(mouse.getmouseMiddle() == 1)
		{
			System.out.println("Change state to Close Menu state");
			mouse.setState(new CloseMenuState());
		}
	}
	
	public void runState(Point e,ContainerAllShape bodyContainer,Mouse mouse,IconMenu[] statusIcon)
	{
		for(int i = 0;i < bodyContainer.getBodyContainerSize();i++)
		{					
			Body obj = bodyContainer.getBody(i);
			Shape shape = obj.getShape();
				
			if(shape.getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Polygon"))
			{
				java.awt.Polygon polygon = AlgorTools.ShapeConvert.convertBodytoPolygon(obj);	
				if(polygon.contains(e))
				{
						
					System.out.println("Select Body Polygon >> " + i);										
					initGraph(i, bodyContainer);
					
					for(int j =0;j<statusIcon.length;j++)
					{
						statusIcon[j] = null;
					}
					
					mouse.setState(new HandSelectState());
				}
			}	
			
			else if(shape.getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Circle"))
			{
				java.awt.geom.Ellipse2D circle = AlgorTools.ShapeConvert.convertCircleBodytoEllipse(obj);								
				if(circle.contains(e))
				{								
					System.out.println("Select Body Cicle >> " + i);
					initGraph(i, bodyContainer);
					
					for(int j =0;j<statusIcon.length;j++)
					{
						statusIcon[j] = null;
					}
					
					mouse.setState(new HandSelectState());
				}
			}
		}
	}
	
	
	/**
	 * Initial the physic graph
	 */
	private void initGraph(int index,ContainerAllShape bodyContainer)
	{
		if(bodyContainer != null)
		{			
			if(PhysicFrame.pg != null)// graph is still showing
			{
				PhysicFrame.pg.clearS(); // clear value before first
				Body b = bodyContainer.getBody(index);
				PhysicFrame.pg.setBody(b);
				PhysicFrame.pg.setBodyIndex(index);
				PhysicFrame.pg.setVisible(true);
				System.out.println("Old Init: Graph... Body >>  " +b);
			}
			
			else // no graph shown
			{
				PhysicFrame.pg = new PhysicGraph("Graph Simulation");	// create the new graph...
				PhysicFrame.pg.setSize(700,250);
				PhysicFrame.pg.setLocation(400, 0);
				Body b = bodyContainer.getBody(index);
				PhysicFrame.pg.setBody(b);
				PhysicFrame.pg.setBodyIndex(index);
				System.out.println("New Init: Graph... Body >>  " +b);
				PhysicFrame.pg.initGUI();
				PhysicFrame.pg.setAlwaysOnTop(true);
			}
					
		}		
	}

	
}
