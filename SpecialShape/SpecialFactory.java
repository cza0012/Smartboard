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
package SpecialShape;

import java.awt.geom.Line2D;
import java.util.ArrayList;

import CommonShape.CommonShape;

/**
 * Factory class for create special shape by using name and ArrayList of Line2D
 * @author Magic Board Team
 *
 */

public class SpecialFactory 
{
	public static final String CROSS   = "cross";
	public static final String cross   = "cross";
	public static final String SPRING  = "spring";
	public static final String spring  = "spring";
	public static final String ARROW   = "arrow";
	public static final String arrow   = "arrow";
	public static final String ROPE	   = "rope";
	public static final String rope	   = "rope";
	
	
	private SpecialFactory()
	{
		
	}
	
	/**
	 * For create special shape
	 *  1. cross
	 *  2. arrow
	 *  3. rope
	 * @param order
	 * @param line
	 * @return
	 */
	public static SpecialShape createSpecialShape(String order,ArrayList<Line2D> line)
	{
		if(order.equalsIgnoreCase("cross"))
		{
			return new CrossShape(line);
		}
		
		else if(order.equalsIgnoreCase("arrow"))
		{
			return new ArrowShape(line);
		}
		
		else if(order.equalsIgnoreCase("rope"))
		{
			return new RopeShape(line);
		}
		
		else
		{
			return null;
		}
	}
	
	/**
	 * For create the special joint shape
	 *  1. spring
	 *  2. joint
	 *  3. fix joint
	 * @param order
	 * @param line
	 * @return
	 */
	public static SpecialShape createSpecialJointShape(String order,CommonShape startIndex,CommonShape endIndex)
	{
		if(order.equalsIgnoreCase("spring"))
		{
			return new SpringShape(startIndex,endIndex);
		}
		
		else if(order.equalsIgnoreCase("joint"))
		{
			return new JointShape(startIndex,endIndex);
		}
		
		else if(order.equalsIgnoreCase("fixjoint"))
		{
			return new FixJointShape(startIndex,endIndex);
		}
		
		else
		{
			return null;	
		}
	}
	

}
