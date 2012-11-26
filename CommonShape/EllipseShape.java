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
 * A ellipse shape represented by a list of its width and height. 
 * Ellipse Shape property
 * 		1. transformation
 * 		2. set static (by adding cross) or dynamic body 
 * 		3. scale vertex
 * 
 * @author Magic Board Team
 * 
 */

public class EllipseShape extends CommonShape 
{	
	/**
	 * For create the Ellipse Shape and set the width and legth.
	 * @param point
	 */
	public EllipseShape(ArrayList<Point2D> point)
	{
		this.name = "CIRCLE";
		this.shape = new Polygon();
		this.rotatePoint = new Point2D.Double();
		this.vertex   = new ArrayList<Ellipse2D>();
		
		this.cm = AlgorTools.ShapeProperty.getCM(point);
		int     diameter = AlgorTools.ShapeProperty.Getdiameter(point);
		double x = (this.cm.getX()-(diameter/2));
		double y = (this.cm.getY()-(diameter/2));
		this.ellipse = new Ellipse2D.Double(x,y,diameter,diameter);
		this.vertex.add(new Ellipse2D.Double((int)(this.ellipse.getX()+this.ellipse.getWidth())-4,(int)(this.ellipse.getY()+this.ellipse.getHeight())-4 , 8, 8));
	}
	
	public EllipseShape(double x,double y,double diameter)
	{
		this.name = "CIRCLE";
		this.shape = new Polygon();
		this.rotatePoint = new Point2D.Double();
		this.vertex   = new ArrayList<Ellipse2D>();
		
		this.ellipse  = new Ellipse2D.Double(x,y,diameter,diameter);
		this.cm = new Point2D.Double(ellipse.getCenterX(),ellipse.getCenterY());
		this.vertex.add(new Ellipse2D.Double((int)(this.ellipse.getX()+this.ellipse.getWidth())-4,(int)(this.ellipse.getY()+this.ellipse.getHeight())-4 , 8, 8));
	}
	
	/**
	 * Get the diameter of the circle
	 */
	public double getDiameter()
	{
		return this.ellipse.getWidth();
	}
	
	/**
	 * Get center of the ellipse
	 * @return
	 */
	public Point2D getCenter()
	{
		return cm;
	}

	
	/**
	 * Get the Ellipse2D object.
	 * 
	 * @return The object of the Ellipse2D.
	 */
	public Ellipse2D getEllipse()
	{
		return this.ellipse;
	}
	
	public void setTranslate(int x,int y)
	{
		double xnew = x+this.ellipse.getX();
		double ynew = y+this.ellipse.getY();
			
		this.ellipse.setFrame(xnew,ynew,this.ellipse.getWidth(),this.ellipse.getHeight());
	    vertex.set(0,new Ellipse2D.Double((int)(this.ellipse.getX()+this.ellipse.getWidth())-4,(int)(this.ellipse.getY()+this.ellipse.getHeight())-4 , 8, 8));
	    this.cm.setLocation(this.ellipse.getCenterX(),this.ellipse.getCenterY());	
	    
	    if(this.cross != null)
	    {
	    	this.cross.setTranslate(x, y);
	    }
		
	}
	
	public void setTranslateGroup(int x,int y)
	{
		double xnew = x+this.ellipse.getX();
		double ynew = y+this.ellipse.getY();
			
		this.ellipse.setFrame(xnew,ynew,this.ellipse.getWidth(),this.ellipse.getHeight());
	    vertex.set(0,new Ellipse2D.Double((int)(this.ellipse.getX()+this.ellipse.getWidth())-4,(int)(this.ellipse.getY()+this.ellipse.getHeight())-4 , 8, 8));
	    this.cm.setLocation(this.ellipse.getCenterX(),this.ellipse.getCenterY());
	    if(this.cross != null)
	    {
	    	this.cross.setTranslate(x, y);
	    }
	}
	
	/**
	 * Translate the selected vertex(index) of the polygon form x and y pixels. 
	 * @param index
	 * @param x
	 * @param y
	 */
	public void translateVertex(int index,int x,int y)
	{
		double w = x-this.ellipse.getX();
		double h = y-this.ellipse.getY();
		
		if (w >0 && h >0)
		{
			if(w > h)
			{
				this.ellipse.setFrame(this.ellipse.getX(),this.ellipse.getY(),h,h);
			}
			else
			{
				this.ellipse.setFrame(this.ellipse.getX(),this.ellipse.getY(),w,w);	
			}
		}
		else if (w < 0 && h >= 0){}
		else if(w < 0&& h < 0) {}
		else if (w >0 && h< 0){}
	
        this.vertex.set(0,new Ellipse2D.Double((int)(this.ellipse.getX()+this.ellipse.getWidth())-4,(int)(this.ellipse.getY()+this.ellipse.getHeight())-4 , 8, 8));
		this.cm.setLocation(this.ellipse.getCenterX(),this.ellipse.getCenterY());
	}
}
