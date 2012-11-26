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
 * Interface class for all of the Special Shape
 * @author Magic Board Team
 *
 */

public abstract class SpecialShape
{
	protected String type;
	
	protected ArrayList<Line2D> shape;
	
	/**
	 * Get the type of the shape
	 * 
	 * @return type of shape
	 */
	public String getType()
	{
		return type;
	}
	
	/**
	 * Get the Shape object.
	 * 
	 * @return The ArrayList of Line2D of the Shape.
	 */
	public ArrayList<Line2D> getShape()
	{
		return this.shape;
	}
	
	/**
	 * For rebuild the shape to fix format
	 *
	 */
	protected abstract void reBuild();
	
	/**
	 * Translate the shape from x and y pixels.
	 * @param x Length x
	 * @param y Length y
	 */
	public void setTranslate(int x,int y)
	{
		for(int i = 0;i<this.shape.size();i++)
		{
			this.shape.get(i).setLine(this.shape.get(i).getX1()+x,this.shape.get(i).getY1()+y,this.shape.get(i).getX2()+x,this.shape.get(i).getY2()+y);	
		}
	}

	public abstract void scalling();
	
	
	/**
	 * Clone the Body to Object.
	 */
    public Object clone() 
    {	
        try 
        {
        	SpecialShape obj = (SpecialShape) super.clone();
        	obj.shape		 = new ArrayList<Line2D>();
        	for(int i =0;i<this.shape.size();i++)
        	{
        		obj.shape.add((Line2D) this.shape.get(i).clone());
        	}
        	return obj;
        }
        
        catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }
    
    //SAVE/LOAD
    public abstract void setNodePrepare(CommonShape startIndex,CommonShape endIndex);
    public abstract void doPrepare();
}
