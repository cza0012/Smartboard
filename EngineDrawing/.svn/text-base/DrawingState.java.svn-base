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
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import UserInterface.Mouse;


/**
 * Drawing State for run when you drawing.
 * 
 * @author Magic Board Team
 *
 */

public class DrawingState extends State
{

	public DrawingState()
	{
		this.stateName = "Drawing";
	}
	
	public void handle(Mouse mouse)
	{
		// Release Left State
		if(mouse.getautoTool().equalsIgnoreCase("Pencil")
		&& mouse.getmouseLeft()   == 0
		&& mouse.getmouseRight()  == 0
		&& mouse.getmouseDrag()   == 0)
		{
			System.out.println("Change state to Release Left State");
			mouse.setState(new ReleaseLeftState());
		}
		
		// Auto Rope State
		else if(mouse.getautoTool().equalsIgnoreCase("Rope")
		&& mouse.getmouseLeft()   == 0
		&& mouse.getmouseRight()  == 0
		&& mouse.getmouseDrag()   == 0)
		{
			System.out.println("Change state to Auto Rope State");
			mouse.setState(new AutoRopeState());
		}
		
		// Auto Joint State
		else if(mouse.getautoTool().equalsIgnoreCase("Joint")
		&& mouse.getmouseLeft()   == 0
		&& mouse.getmouseRight()  == 0
		&& mouse.getmouseDrag()   == 0)
		{
			System.out.println("Change state to Joint State");
			mouse.setState(new AutoJointState());
		}
		
		// Auto Fix Joint State
		else if(mouse.getautoTool().equalsIgnoreCase("FixJoint")
		&& mouse.getmouseLeft()   == 0
		&& mouse.getmouseRight()  == 0
		&& mouse.getmouseDrag()   == 0)
		{
			System.out.println("Change state to Fix Joint State");
			mouse.setState(new AutoFixJointState());
		}
		
		// Auto Spring Joint State
		else if(mouse.getautoTool().equalsIgnoreCase("Spring")
		&& mouse.getmouseLeft()   == 0
		&& mouse.getmouseRight()  == 0
		&& mouse.getmouseDrag()   == 0)
		{
			System.out.println("Change state to Spring Joint State");
			mouse.setState(new AutoSpringState());
		}
					
	}
	
	/**
	 * Run state for drawing point
	 * @param p
	 * @param lineDrawing
	 */
	public void runState(Point p,Ellipse2D lineVertex,ArrayList<Point2D> lineDrawing,ArrayList<ArrayList<Line2D>> lineContainer)
	{
		lineDrawing.add(p);
		
		Point2D nearPoint = AlgorTools.LineProperty.InLineDistance(lineContainer,lineDrawing.get(0),p);
		
		if(nearPoint != null)
		{
			lineVertex.setFrame(nearPoint.getX()-2,nearPoint.getY()-2,4,4);
		}
		
		else
		{
			lineVertex.setFrame(0,0,0,0);
		}
	}
}
