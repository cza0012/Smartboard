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

import java.awt.event.MouseEvent;

import PhysicDrawing.MainPhysicInterface;
import UserInterface.Mouse;
import EngineDrawing.State;
import IconMenu.IconMenu;

public class SelectMenuState extends State
{
	public SelectMenuState()
	{
		this.stateName = "SelectMenu";
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
	
	public boolean runState(Mouse mouse,MouseEvent e,IconMenu[] statusIcon,int iconLocationX,int iconLocationY)
	{
		if(statusIcon[0].contains(e)) // users click graph
		{
			System.out.println("MENU - GRAPH");		
			// change the image...
			statusIcon[0] = new IconMenu("./pic/GraphSelect.png",iconLocationX-100,iconLocationY-50,"GRAPH");
			System.out.println("Change state to Select Graph state");
			mouse.setState(new SelectGraphState());
			return false;
		}
		
		else if(statusIcon[1].contains(e)) //users click Force
		{
			System.out.println("MENU - Force");
			for(int j =0;j<statusIcon.length;j++)
			{
				statusIcon[j] = null;
			}
			
			System.out.println("Change state to Draw Line Force State");
			mouse.setState(new DrawLineForceState());
			return false;
		}
		
		else if(statusIcon[2].contains(e))  // users click stop
		{
			System.out.println("MENU - STOP");
			
			for(int j =0;j<statusIcon.length;j++)
			{
				statusIcon[j] = null;
			}
			
			MainPhysicInterface.stopAnimation();
			mouse.setState(new HandSelectState());
			return false;
		}
		
		return true;
	}

}
