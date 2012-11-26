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
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import net.phys2d.raw.Body;
import net.phys2d.raw.Contact;
import net.phys2d.raw.collide.CircleCircleCollider;
import net.phys2d.raw.collide.LineCircleCollider;
import net.phys2d.raw.collide.PolygonCircleCollider;
import net.phys2d.raw.collide.PolygonPolygonCollider;
import CommonShape.EllipseShape;
import Container.ContainerAllShape;

public class ShapeIntersection 
{
	private ShapeIntersection()
	{
		
	}
	
	/**
	 * For checking two of Commonshape was intersect or not.
	 * @param move
	 * @param fix
	 * @return
	 */
	public static boolean shapeIntersectIndividual(Object move,Object fix)
	{
		Body selectPolygon = ShapeConvert.convertObjtoBody(move);
		Body fixPolygon    = ShapeConvert.convertObjtoBody(fix);
			
		// trunk and circle collide
		if(move.getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
		{
			Contact[] contacts = new Contact[] {new Contact(), new Contact()};
			if(checkPolygonCircleCollider(contacts,fixPolygon,selectPolygon))
			{
				System.out.println("Intersect trunk and circle collide");
				return true;
			}
		}
		
		// trunk and polygon collide
		else if((move.getClass().getCanonicalName().equalsIgnoreCase("CommonShape.PolygonShape")
		|| move.getClass().getCanonicalName().equalsIgnoreCase("CommonShape.TriangleShape")
		|| move.getClass().getCanonicalName().equalsIgnoreCase("CommonShape.RectangleShape")))				
		{
			Contact[] contacts = new Contact[] {new Contact(), new Contact()};
			if(checkPolygonPolygonCollider(contacts,fixPolygon,selectPolygon))
			{
				System.out.println("Intersect trunk and polygon collide");
				return true;
			}
		}
		
		else if(move.getClass().getCanonicalName().equalsIgnoreCase("java.awt.Polygon"))
		{
			Contact[] contacts = new Contact[] {new Contact(), new Contact()};
			if(checkPolygonPolygonCollider(contacts,fixPolygon,selectPolygon))
			{
				System.out.println("Intersect trunk and polygon collide");
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * For checking the normal shape was intersect to another or not.
	 * @param shapeContainer
	 * @param polygon
	 * @return -1 if not intersect or return >= 0 for index of shape that intersect.
	 */
	public static ArrayList<Integer> shapeIntersectEdge(ContainerAllShape selectShape,int index)
	{
		ArrayList<Integer> touch = new ArrayList<Integer>();
		
		// 1. get the shape of the object that you was selected.
		Body selectPolygon = ShapeConvert.convertObjtoBody(selectShape.getShape(index));
		
		// 2. check the shape that you select is intersect to another or not
		for(int i = 0;i<selectShape.getShapeContainerSize();i++)
		{
			if(i == index)
			{
				
			}
			
			else
			{
				Body containPolygon = ShapeConvert.convertObjtoBody(selectShape.getShape(i));
				// circle and circle collide 
				if(selectShape.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape")
				&& selectShape.getShape(index).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
				{
					Contact[] contacts = new Contact[] {new Contact(), new Contact()};
					if(checkCircleCircleCollider(contacts,selectPolygon,containPolygon))
					{
						touch.add(i);
					}
				}
				
				// polygon and circle collide
				else if((selectShape.getShape(index).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.PolygonShape")
				|| selectShape.getShape(index).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.TriangleShape")
				|| selectShape.getShape(index).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.RectangleShape"))		
				&& selectShape.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
				{
					Contact[] contacts = new Contact[] {new Contact(), new Contact()};
					if(checkPolygonCircleCollider(contacts,selectPolygon,containPolygon))
					{
						touch.add(i);
					}
				}
				
				// circle and polygon collide
				else if(selectShape.getShape(index).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape")
				&& (selectShape.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.PolygonShape")
				|| selectShape.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.TriangleShape")
				|| selectShape.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.RectangleShape")))
				{
					Contact[] contacts = new Contact[] {new Contact(), new Contact()};
					if(checkPolygonCircleCollider(contacts,containPolygon,selectPolygon))
					{
						touch.add(i);
					}
				}
				
				// polygon and polygon collide
				else if((selectShape.getShape(index).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.PolygonShape")
				|| selectShape.getShape(index).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.TriangleShape")
				|| selectShape.getShape(index).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.RectangleShape"))				
				&& (selectShape.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.PolygonShape")
				|| selectShape.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.TriangleShape")
				|| selectShape.getShape(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.RectangleShape")))
				{
					Contact[] contacts = new Contact[] {new Contact(), new Contact()};
					if(checkPolygonPolygonCollider(contacts,selectPolygon,containPolygon))
					{
						touch.add(i);
					}
				}
			}
		}
		return touch;
	}
	
	/**
	 * For check polygon and polygon are collision or not.
	 * @param contacts
	 * @param bodyA
	 * @param bodyB
	 * @return
	 */
	public static boolean checkPolygonPolygonCollider(Contact[] contacts, Body bodyA, Body bodyB)
	{
		PolygonPolygonCollider collider = new PolygonPolygonCollider();
		int count = collider.collide(contacts, bodyA, bodyB);	
		if(count > 0)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * For check polygon and circle are collision or not.
	 * @param contacts
	 * @param bodyA
	 * @param bodyB
	 * @return
	 */
	public static boolean checkPolygonCircleCollider(Contact[] contacts, Body bodyA, Body bodyB)
	{
		PolygonCircleCollider collider = new PolygonCircleCollider();
		int count = collider.collide(contacts, bodyA, bodyB);
		if(count > 0)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * For check circle and circle are collision or not.
	 * @param contacts
	 * @param staticCircle
	 * @param dynamicCircle
	 * @return
	 */
	public static boolean checkCircleCircleCollider(Contact[] contacts,Body staticCircle,Body dynamicCircle)
	{
		CircleCircleCollider collider = new CircleCircleCollider();
		int count = collider.collide(contacts, staticCircle, dynamicCircle);	
		if(count > 0)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * For get point of line and Ellipse2D are intersect.
	 * @param line
	 * @param shape
	 * @return
	 */
	public static Point2D getLineCircleCollider(Line2D line,EllipseShape shape)
	{
		LineCircleCollider collider = new LineCircleCollider();
		Contact[] contacts = new Contact[] {new Contact(), new Contact()};
		Body body1 = ShapeConvert.convertObjtoBody(line);
		Body body2 = ShapeConvert.convertObjtoBody(shape);
		int count = collider.collide(contacts, body1, body2);
		if(count > 0)
		{	
			return new Point2D.Double(contacts[0].getPosition().getX(),contacts[0].getPosition().getY());
		}
		else
		{
			return null;	
		}
	}
	
	/**
	 * For get point of line and shape are intersect.
	 * @param line
	 * @param shape
	 * @return
	 */
	public static Point2D getLinePolygonCollider(Line2D line,Polygon shape)
	{
		ArrayList<Line2D> lineList = ShapeConvert.convertPolygontoLine2D(shape);
		for(int i =0;i<lineList.size();i++)
		{
			Point2D pointIntersect = LineIntersection.getIntersection(line,lineList.get(i));
			if(pointIntersect != null)
			{
				return pointIntersect;
			}
		}
	
		return null;
	}
	
	/**
	 * Get list of point2D that list of line and polygon was collide.
	 * @param line
	 * @param shape
	 * @return
	 */
	public static ArrayList<Point2D> getListLinePolygonCollider(Line2D line,Polygon shape)
	{
		ArrayList<Point2D> intersect = new ArrayList<Point2D>();
		ArrayList<Line2D> lineList = ShapeConvert.convertPolygontoLine2D(shape);	
		for(int i =0;i<lineList.size();i++)
		{
			Point2D pointIntersect = LineIntersection.getIntersection(line,lineList.get(i));
			
			if(pointIntersect != null)
			{
				intersect.add(pointIntersect);
			}
		}
	
		return intersect;
	}
	
	
	/**
	 * For check Line and circle are intersect or not
	 * @param contacts
	 * @param bodyA
	 * @param bodyB
	 * @return
	 */
	public static boolean checkLineCircleCollider(Contact[] contacts, Body bodyA, Body bodyB)
	{
		LineCircleCollider collider = new LineCircleCollider();
		int count = collider.collide(contacts, bodyA, bodyB);
		if(count > 0)
		{
			return true;
		}
		return false;
	}
	
	
	public static double nearest = 0.0;
	public static double getNearest()
	{
		return nearest;
	}
	
	
	/**
	 * Check line polygon near start point
	 * @param line
	 * @param shape
	 * @param startP
	 * @return
	 */
	public static Point2D checkLinePolygonNearestCollider(Line2D line,Polygon shape,Point2D startP)
	{
		ArrayList<Line2D> lineList = ShapeConvert.convertPolygontoLine2D(shape);
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		double length = Double.MAX_VALUE;
		ArrayList<Double> allL = new ArrayList<Double>();
		for(int i =0;i<lineList.size();i++)
		{
			Point2D pointI = LineIntersection.getIntersection(line,lineList.get(i));
			if(pointI != null)
			{
				points.add(pointI);
				double l = Math.sqrt(Math.pow((pointI.getX()-startP.getX()), 2)+Math.pow((pointI.getY()-startP.getY()),2));
				if(l<length){
					length = l;
				}
				allL.add(l);
			}
		}
		for(int i=0;i<allL.size();i++)
		{
			if(length==allL.get(i)){
				nearest = length;
				return points.get(i);
			}
		}
		return null;
	}
}
