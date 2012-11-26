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

import PhysicDrawing.MainPhysicInterface;
import UserInterface.Mouse;
import EngineDrawing.State;

public class SimulationState extends State
{
	public SimulationState()
	{
		this.stateName = "Simulation";
	}

	@Override
	public void handle(Mouse mouse) 
	{
		// HandSelect State
		if(mouse.getIndividualSelectIndex() == -1
		&& mouse.getmouseLeft()   == 1
		&& mouse.getmouseRight()  == 0
		&& (mouse.getmouseDrag()  == 0 || mouse.getmouseDrag()  == 0))
		{
			System.out.println("Change state to HandSelect state");
			mouse.setState(new HandSelectState());
		}
		
		// Transform State
		else if(mouse.getIndividualSelectIndex() != -1
		&& mouse.getmouseLeft()   == 1
		&& mouse.getmouseRight()  == 0
		&& (mouse.getmouseDrag()  == 0 || mouse.getmouseDrag()  == 1))
		{
			System.out.println("Change state to Transform state");
			mouse.setState(new TransformState());
		}
		
		// Open Menu State
		else if(mouse.getmouseMiddle() == 1)
		{
			System.out.println("Change state to Open Menu state");
			mouse.setState(new OpenMenuState());
		}
	}
	
	public String runState(String current)
	{
		// if current = pause / stop ... play it.. 
		if(current.equals("PAUSE") || current.equals("STOP"))				
		{ 
			MainPhysicInterface.startAnimation();
			current = "PLAY";
		}
		
		// if current = play ... pause it
		else
		{ 
			MainPhysicInterface.pauseAnimation();
			current = "PAUSE";
		}
		
		return current;
	}

}
