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
import Container.ContainerAllShape;

public class FixJointShape extends SpecialShape implements Cloneable
{
	private CommonShape startIndex;
	private CommonShape endIndex;
	
	public FixJointShape()
	{
		
	}
	public FixJointShape(CommonShape startIndex,CommonShape endIndex)
	{
		this.shape = new ArrayList<Line2D>();
		this.type  = "fixjoint";
		this.startIndex  = startIndex;
		this.endIndex    = endIndex;
	}
	public void setNodePrepare(CommonShape startIndex,CommonShape endIndex)
	{
		this.startIndex = startIndex;
		this.endIndex   = endIndex;
		doPrepare();
	}
	public void doPrepare()
	{
		this.shape = new ArrayList<Line2D>();
		this.type  = "fixjoint";
	}
	/**
	 * set start index of the object that struct with the joint at start point.
	 * @param startIndex
	 */
	public void setStartIndex(CommonShape startIndex)
	{
		this.startIndex = startIndex;
	}
	
	/**
	 * set end index of the object that struct with the joint at end point.
	 * @param endIndex
	 */
	public void setEndIndex(CommonShape endIndex)
	{
		this.endIndex = endIndex;
	}
	
	/**
	 * Get the start index object that struct with joint.
	 * @return index of object.
	 */
	public CommonShape getStartIndex()
	{
		return this.startIndex;
	}
	
	/**
	 * Get the end index object that struct with joint.
	 * @return index of object.
	 */
	public CommonShape getEndIndex()
	{
		return this.endIndex;
	}
	
	/**
	 * Check for this shape contain in container or not.
	 * @param container
	 * @return
	 */
	public boolean isContain(ContainerAllShape container)
	{
		if(container.isContain(this.startIndex)== false || container.isContain(this.endIndex)== false)
		{
			return false;
		}
		return true;
	}
	
	
	@Override
	protected void reBuild() {
		// TODO Auto-generated method stub
		
	}


	public void rotate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scalling() {
		// TODO Auto-generated method stub
		
	}
	
	
	public Object clone(ContainerAllShape real,ContainerAllShape clone) 
    {
		FixJointShape obj = (FixJointShape)super.clone();
		
		for(int i =0;i<real.getShapeContainerSize();i++)
		{
			if(real.getShape(i) == this.startIndex)
			{
				obj.startIndex	  = (CommonShape) clone.getShape(i);
			}
			
			else if(real.getShape(i) == this.endIndex)
			{
				obj.endIndex		= (CommonShape) clone.getShape(i);
			}
		}
		return obj;	
    }

}
