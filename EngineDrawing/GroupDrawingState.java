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

import UserInterface.Mouse;


public class GroupDrawingState extends State
{

	public GroupDrawingState()
	{
		this.stateName = "GroupDraw";
	}
	
	public void handle(Mouse mouse)
	{
		// Release Rigth State
		if(!mouse.getPreviousStateName().equalsIgnoreCase("GroupSelect")
		&& mouse.getmouseLeft()  == 0
		&& mouse.getmouseRight() == 0
		&& mouse.getmouseDrag()  == 0)
		{
			System.out.println("Change state to Release Right State");
			mouse.setState(new ReleaseRightState());
		}	
	}
	
	
	/**
	 * Run state for draw selection line
	 * @param e
	 * @param lineDrawing
	 */
	public void runState(Point e,Mouse mouse)
	{
		mouse.setPreviousState(this.stateName);
		
		mouse.getSelectDrawing().add(e);
	}
	
}