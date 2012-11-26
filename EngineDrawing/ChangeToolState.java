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


import UserInterface.Mouse;

/**
 * Change tool state for change state when you change tools before change state to new state
 * you must to runState of Change tool state for clear the environment before.
 * @author Magic Board Team
 *
 */

public class ChangeToolState extends State
{
	public ChangeToolState()
	{
		this.stateName = "Change";
	}
	
	public void handle(Mouse mouse) 
	{
		// Auto Pencil State
		if(mouse.getautoTool().equalsIgnoreCase("Pencil"))
		{
			System.out.println("Change state to Pencil state");
			mouse.setState(new AutoPencilState());
		}
		
		// Auto Triangle State
		else if(mouse.getautoTool().equalsIgnoreCase("Triangle"))
		{
			System.out.println("Change state to Triangle state");
			mouse.setState(new AutoTriangleState());
		}
		
		// Auto Rectangle State 
		else if(mouse.getautoTool().equalsIgnoreCase("Rectangle"))
		{
			System.out.println("Change state to Rectangle state");
			mouse.setState(new AutoRectangleState());
		}
		
		// Auto Ellipse State 
		else if(mouse.getautoTool().equalsIgnoreCase("Ellipse"))
		{
			System.out.println("Change state to Ellipse state");
			mouse.setState(new AutoEllipseState());
		}
		
		// Auto Arrow State
		else if(mouse.getautoTool().equalsIgnoreCase("Arrow"))
		{
			System.out.println("Change state to Force state");
			mouse.setState(new AutoArrowState());
		}
		
		// Auto Balloon State
		else if(mouse.getautoTool().equalsIgnoreCase("Balloon"))
		{
			System.out.println("Change state to Balloon state");
			mouse.setState(new AutoBalloonState());
		}
		
		// Auto Car State
		else if(mouse.getautoTool().equalsIgnoreCase("Car"))
		{
			System.out.println("Change state to Car state");
			mouse.setState(new AutoCarState());
		}
		
		// Auto Rope State 
		else if(mouse.getautoTool().equalsIgnoreCase("Rope"))
		{
			System.out.println("Change state to Rope state");
			mouse.setState(new AutoRopeState());
		}
		
		// Auto Joint State
		else if(mouse.getautoTool().equalsIgnoreCase("Joint"))
		{
			System.out.println("Change state to Joint state");
			mouse.setState(new AutoJointState());
		}
		
		// Auto Fix Joint State
		else if(mouse.getautoTool().equalsIgnoreCase("FixJoint"))
		{
			System.out.println("Change state to Fix Joint state");
			mouse.setState(new AutoFixJointState());
		}
		
		// Auto Spring Joint State
		else if(mouse.getautoTool().equalsIgnoreCase("Spring"))
		{
			System.out.println("Change state to Spring Joint state");
			mouse.setState(new AutoSpringState());
		}
		
		// Auto Comment State
		else if(mouse.getautoTool().equalsIgnoreCase("Comment"))
		{
			System.out.println("Change state to AutoComment state");
			mouse.setState(new AutoCommentState());
		}
		
		// Auto Rubber State
		else if(mouse.getautoTool().equalsIgnoreCase("Rubber"))
		{
			System.out.println("Change state to AutoRubber state");
			mouse.setState(new AutoRubberState());
		}
	}
	
	public void runState(Mouse mouse)
	{
		mouse.setPreviousState(this.stateName);
		
		// For clear the Environment before change State.
		mouse.getGroupSelectPolygon().reset();
		mouse.getGroupLineSelectIndex().clear();
		mouse.getGroupSelectIndex().clear();
		mouse.setGroupSelect(-1);
		mouse.setIndividualSelectIndex(-1);
		
		this.handle(mouse);
	}
}
