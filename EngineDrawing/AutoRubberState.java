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
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import AlgorTools.LineIntersection;
import UserInterface.Mouse;

public class AutoRubberState extends State
{

	public AutoRubberState()
	{
		this.stateName = "Rubber";
	}
	
	public void handle(Mouse mouse)
	{
		// Individual Select State
		if(mouse.getmouseLeft()   == 0
		&& mouse.getmouseRight()  == 1
		&& mouse.getmouseDrag()   == 0)
		{
			System.out.println("Change state to Individual select");
			mouse.setState(new IndividualSelectState());
		}
	}
	
	/**
	 * Run state for drawing point
	 * @param p
	 * @param lineDrawing
	 */
	public void runState(Point p,ArrayList<Line2D> commentContainer,boolean mustRemove,Polygon rubber)
	{	
		
		double PosX = p.getX();
		double PosY = p.getY();

		rubber.reset();
		rubber.addPoint((int)PosX,(int)PosY);
		rubber.addPoint((int)PosX+30,(int)PosY);
		rubber.addPoint((int)PosX+30,(int)PosY+30);
		rubber.addPoint((int)PosX,(int)PosY+30);
		
		if(mustRemove == true)
		{
			ArrayList<Line2D> temp = LineIntersection.checkRemoveLinebyPolygon(commentContainer,rubber);
			commentContainer.clear();
			
			for(int i=temp.size()-1;i>=0;i--)
			{
				commentContainer.add(temp.get(i));
			}
		}

	}
}