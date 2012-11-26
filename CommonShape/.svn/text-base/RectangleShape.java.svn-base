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
package CommonShape;

import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * A rectangle shape represented by a list of its vertices. 
 * Rectangle Shape property
 * 		1. transformation
 * 		2. set static (by adding cross) or dynamic body 
 * 		3. scale vertex
 * 
 * @author Magic Board Team
 * 
 */

public class RectangleShape extends CommonShape
{
	/**
	 * For create the Rectangle Shape and set the corner by point.
	 * @param point
	 */
	public RectangleShape(ArrayList<Point2D> point)
	{	
		this.name = "RECTANGLE";
		this.cm = AlgorTools.ShapeProperty.getCM(point);
		this.shape = new Polygon();
		this.vertex   = new ArrayList<Ellipse2D>();
		for(int i =0;i<point.size();i++)
		{
			this.shape.addPoint((int)point.get(i).getX(),(int)point.get(i).getY());
		}
		
		this.shape.translate((int)-this.cm.getX(),(int)-this.cm.getY());
		point = getPointPolygon();
		
		if(AlgorTools.ShapeChecking.isClockwise(point)==false)
		{
			ArrayList<Point2D> temp = new ArrayList<Point2D>();
			for(int i = point.size()-1;i>=0;i--)
			{
				temp.add(point.get(i));
			}
			point = temp;
			
			this.shape.reset();
			for(int i =0;i<point.size();i++)
			{
				this.shape.addPoint((int)point.get(i).getX(),(int)point.get(i).getY());
			}
			this.shape.translate((int)this.cm.getX(),(int)this.cm.getY());
		}
		
		else 
		{
			this.shape.translate((int)this.cm.getX(),(int)this.cm.getY());
		}
		
		
		// set the vertex
		for(int i =0;i<this.shape.npoints;i++)
		{
			this.vertex.add(i, new Ellipse2D.Double((int)this.shape.xpoints[i]-4, (int)this.shape.ypoints[i]-4, 8, 8));	
		}
		
		this.setRotatePoint();
	}
}
