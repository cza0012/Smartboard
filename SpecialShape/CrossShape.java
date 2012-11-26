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

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import AlgorTools.CloneShape;
import AlgorTools.LineIntersection;
import CommonShape.CommonShape;

/**
 * A cross shape within the simulation, defined by its list of line 
 * and the point of intersection
 * 
 * @author Magic Board Team
 *
 */

public class CrossShape extends SpecialShape implements Cloneable
{
	/**
	 * Create instance of Cross shape by using arraylist of line2D
	 * 
	 * @param line
	 */
	public CrossShape(ArrayList<Line2D> line)
	{
		this.shape = line;
		this.type  = "cross";
		reBuild();
	}
	
	protected void reBuild() 
	{
		Point2D IntersectionPoint = LineIntersection.getIntersection(this.shape.get(0),this.shape.get(1));
		double xMinus 	= IntersectionPoint.getX() - 5;
		double xPlus 	= IntersectionPoint.getX() + 5;
		double yMinus 	= IntersectionPoint.getY() - 5;
		double yPlus 	= IntersectionPoint.getY() + 5;
		this.shape.get(0).setLine(xPlus, yMinus,xMinus,yPlus);
		this.shape.get(1).setLine(xMinus,yMinus,xPlus, yPlus);
	}
	
	/**
	 * Get cross intersection point
	 * @return
	 */
	public Point2D getIntersectionPoint()
	{
		return LineIntersection.getIntersection(this.shape.get(0),this.shape.get(1));
	}

	public void rotate() 
	{
		
	}

	public void scalling() 
	{
		
	}	
	
	/**
	 * Clone the Body to Object.
	 */
    public Object clone() 
    {	
    	CrossShape obj = (CrossShape)super.clone();
    	obj.shape		 = new ArrayList<Line2D>();
    	for(int i =0;i<this.shape.size();i++)
    	{
    		obj.shape.add((Line2D) this.shape.get(i).clone());
    	}
        return obj;	
    }

	@Override
	public void doPrepare() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNodePrepare(CommonShape startIndex, CommonShape endIndex) {
		// TODO Auto-generated method stub
		
	}
	
}
