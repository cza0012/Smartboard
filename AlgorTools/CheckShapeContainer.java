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
package AlgorTools;

import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import CommonShape.CommonShape;
import CommonShape.EllipseShape;
import Container.ContainerAllShape;

public class CheckShapeContainer 
{
	private CheckShapeContainer()
	{
		
	}
	
	/**
	 * For get the shape that point are contain in
	 * @param shapeContainer
	 * @param point
	 * @return
	 */
	public static CommonShape getShapeContainPoint2D(ContainerAllShape shapeContainer,Point2D point)
	{
		for(int i = 0;i<shapeContainer.getShapeContainerSize();i++)
		{
			if(shapeContainer.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
			{
				EllipseShape shape = (EllipseShape) shapeContainer.getShape(i);
				if(shape.getEllipse().contains(point))
				{
					return (CommonShape)shapeContainer.getShape(i);
				}
			}
			
			else if(shapeContainer.getShape(i).getClass().getPackage().getName().equalsIgnoreCase("CommonShape"))
			{
				CommonShape shape = (CommonShape)shapeContainer.getShape(i);
				if(shape.getPolygon().contains(point))
				{
					return (CommonShape)shapeContainer.getShape(i);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * For check CommonShape contain point2D or not
	 * @param shape
	 * @param point
	 * @return
	 */
	public static boolean isCommonShapeContainPoint2D(CommonShape shape , Point2D point)
	{
		if(shape.getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
		{
			EllipseShape ellipse = (EllipseShape) shape;
			if(ellipse.getEllipse().contains(point))
			{
				return true;
			}
		}
		
		else if(shape.getPolygon().contains(point))
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * For check polygon p1 was contain all edge point of polygon p2
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static boolean isPolygonContainPolygon(Polygon p1,Polygon p2)
	{
		ArrayList<Point2D> point = ShapeConvert.convertPolygontoPoint2D(p2);
		
		for(int i =0;i<point.size();i++)
		{
			if(p1.contains(point.get(i))== false)
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * For check polygon p1 was contain all edge point of ellipse e2
	 * @param p1
	 * @param e2
	 * @return
	 */
	public static boolean isPolygonContainEllipse(Polygon p1,Ellipse2D e2)
	{
		if(p1.contains(e2.getMaxX(),e2.getCenterY()) == true
		&& p1.contains(e2.getCenterX(),e2.getMaxY()) == true		
		&& p1.contains(e2.getMinX(),e2.getCenterY()) == true
		&& p1.contains(e2.getCenterX(),e2.getMinY()) == true)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * For check polygon p1 was contain all point of line l2
	 * @param p1
	 * @param l2
	 * @return
	 */
	public static boolean isPolygonContainLine(Polygon p1,ArrayList<Line2D> l2)
	{
		for(int i =0;i<l2.size();i++)
		{
			if(p1.contains(l2.get(i).getP1())== false || p1.contains(l2.get(i).getP2())== false)
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * For check ellipse e1 was contain all edge point of polygon p2
	 * @param e1
	 * @param p2
	 * @return
	 */
	public static boolean isEllipseContainPolygon(Ellipse2D e1,Polygon p2)
	{
		ArrayList<Point2D> point = ShapeConvert.convertPolygontoPoint2D(p2);
		
		for(int i =0;i<point.size();i++)
		{
			if(e1.contains(point.get(i))== false)
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * For check ellipse e1 was contain all edge point of ellipse e2
	 * @param e1
	 * @param e2
	 * @return
	 */
	public static boolean isEllipseContainEllipse(Ellipse2D e1,Ellipse2D e2)
	{
		if(e1.contains(e2.getMaxX(),e2.getCenterY()) == true
		&& e1.contains(e2.getCenterX(),e2.getMaxY()) == true		
		&& e1.contains(e2.getMinX(),e2.getCenterY()) == true
		&& e1.contains(e2.getCenterX(),e2.getMinY()) == true)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * For check Ellipse2D e1 was contain all point of line l2
	 * @param e1
	 * @param l2
	 * @return
	 */
	public static boolean isEllipseContainLine(Ellipse2D e1,ArrayList<Line2D> l2)
	{
		for(int i =0;i<l2.size();i++)
		{
			if(e1.contains(l2.get(i).getP1())== false || e1.contains(l2.get(i).getP2())== false)
			{
				return false;
			}
		}
		return true;
	}
	
}
