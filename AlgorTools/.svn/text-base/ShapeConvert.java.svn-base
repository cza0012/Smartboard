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
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import CommonShape.EllipseShape;
import CommonShape.PolygonShape;
import CommonShape.RectangleShape;
import CommonShape.TriangleShape;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.shapes.Circle;
import net.phys2d.raw.shapes.ConvexPolygon;
import net.phys2d.raw.shapes.Line;


public class ShapeConvert 
{
	private ShapeConvert()
	{
		
	}
	
	
	/**
	 * For convert Point2D to polygon
	 * @param shape
	 * @return
	 */
	public static Polygon convertPoint2DtoPolygon(ArrayList<Point2D> shape)
	{
		Polygon polygon = new Polygon();
		for(int i =0;i<shape.size();i++)
		{
			polygon.addPoint((int)shape.get(i).getX(),(int)shape.get(i).getY());
		}
		return polygon;
	}
	
	/**
	 * For Convert Point2D to Line2D
	 * @param shape
	 * @return
	 */
	public static ArrayList<Line2D> convertPoint2DtoLine2D(ArrayList<Point2D> shape)
	{
		ArrayList<Line2D> lineList = new ArrayList<Line2D>();
		for(int i =0;i<shape.size()-1;i++)
		{
			lineList.add(new Line2D.Double(shape.get(i).getX(),shape.get(i).getY(),shape.get(i+1).getX(),shape.get(i+1).getY()));
		}
		return lineList;
	}
	
	/**
	 * For convert line2D to point2D with not connection line 
	 * @param shape
	 * @return list of point2D that are the couple of line (2 point == 1 line)
	 */
	/*
	public static ArrayList<Point2D> convertLine2dtoPoint2D(ArrayList<Line2D> shape)
	{
		ArrayList<Point2D> result = new ArrayList<Point2D>();
		for(int i = 0;i<shape.size();i++)
		{
			result.add(shape.get(i).getP1());
			result.add(shape.get(i).getP2());
		}
		return result;
	}
	*/
	
	/**
	 * 
	 * @param shape
	 * @author Sub7_User
	 * @version 0.1 , 25/08/2007 : 14.22
	 * @return arrayList of pt2D represents the each pt on the rope.
	 */
	public static ArrayList<Point2D> convertLine2DtoPoint2D(ArrayList<Line2D> shape) 
	{
		ArrayList<Point2D> result = new ArrayList<Point2D>();
		for(int i = 0;i<shape.size();i++)
		{
			if(i == 0)
			{
				Point2D p1 = shape.get(i).getP1();
				Point2D p2 = shape.get(i).getP2();
				result.add(p1);
				result.add(p2);				
			}
			else
			{
				Point2D p2 = shape.get(i).getP2();
				result.add(p2);
			}
			
		}
		return result;
	}
	
	/**
	 * Convert connection line to point2d.
	 * @param shape
	 * @author Sub7_User
	 * @version 0.1 , 25/08/2007 : 14.22
	 * @return arrayList of pt2D represents the each pt on the rope.
	 */
	public static ArrayList<Point2D> convertRopeLinetoPoint2D(ArrayList<Line2D> shape) 
	{
		ArrayList<Point2D> result = new ArrayList<Point2D>();
		for(int i = 0;i<shape.size();i++)
		{
			if(i == 0)
			{
				Point2D p1 = shape.get(i).getP1();
				Point2D p2 = shape.get(i).getP2();
				result.add(p1);
				result.add(p2);				
			}
			else
			{
				Point2D p2 = shape.get(i).getP2();
				result.add(p2);
			}
			
		}
		return result;
	}
	
	/**
	 * Convert polygon to Point2D
	 * @param polygon
	 * @return
	 */
	public static ArrayList<Point2D> convertPolygontoPoint2D(Polygon polygon)
	{
		ArrayList<Point2D> point = new ArrayList<Point2D>();
		for(int i =0;i<polygon.npoints;i++)
		{
			point.add(new Point2D.Double(polygon.xpoints[i],polygon.ypoints[i]));
		}
		return point;
	}
	
	/**
	 * Convert polygon to Line2D
	 * @param polygon
	 * @return
	 */
	public static ArrayList<Line2D> convertPolygontoLine2D(Polygon polygon)
	{
		ArrayList<Line2D> line = new ArrayList<Line2D>();
		for(int i =0;i<polygon.npoints;i++)
		{
			if(i == (polygon.npoints-1))
			{
				line.add(new Line2D.Double((double)polygon.xpoints[i],(double)polygon.ypoints[i],(double)polygon.xpoints[0],(double)polygon.ypoints[0]));
			}
			
			else
			{
				line.add(new Line2D.Double((double)polygon.xpoints[i],(double)polygon.ypoints[i],(double)polygon.xpoints[i+1],(double)polygon.ypoints[i+1]));
			}
		}
		return line;
	}
	
	/**
	 * Convert Ellipse2D to Body
	 * @param polygon
	 * @return
	 */
	public static Body convertEllipsetoBody(Ellipse2D polygon)
	{
		double xmax = polygon.getMaxX();
		double xmin = polygon.getMinX();
		float radius = (float)(xmax-xmin)/2;
		Circle circle = new Circle(radius);
		Body body = new Body(circle,10);
		body.setPosition((float)polygon.getCenterX(),(float)polygon.getCenterY());
		return body;
	}
	
	/**
	 * Convert Polygon to Body.
	 * @param polygon
	 * @return
	 */
	public static Body convertPolygontoBody(Polygon polygon)
	{
		ArrayList<Point2D> point = new ArrayList<Point2D>();
		for(int i = 0;i<polygon.npoints;i++)
		{
			point.add(new Point2D.Double(polygon.xpoints[i],polygon.ypoints[i]));
		}
		
		Point2D cm = ShapeProperty.getCM(point);
		// The param is polygon vertex 
		Vector2f[] polygonVerts = new Vector2f[point.size()];
		for(int i = 0;i<point.size();i++)
		{
			polygonVerts[i] = new Vector2f((float)(point.get(i).getX()-cm.getX()),(float)(point.get(i).getY()-cm.getY()));
		}
		
		ConvexPolygon polygonPolygon = new ConvexPolygon(polygonVerts);
		/** Static body want only name and polygon */
		Body body = new StaticBody("Polygon", polygonPolygon);
		body.setPosition((float)cm.getX(),(float)cm.getY());
		return body;
	}
	
	/**
	 * Convert object of CommonShape to Body
	 * @param obj
	 * @return
	 */
	public static Body convertObjtoBody(Object obj)
	{		
		if(obj instanceof Line2D)
		{			
			Line2D line = (Line2D) obj;
			return new Body(new Line((int)line.getX1(),(int)line.getY1(),(int)line.getX2(),(int)line.getY2()), 1f);
		}
		
		else if(obj.getClass().getCanonicalName().equalsIgnoreCase("CommonShape.TriangleShape"))
		{
			TriangleShape triangle = (TriangleShape)obj;
			ArrayList<Point2D> point = triangle.getPointPolygon();
			Point2D cm = triangle.getCM();
			// The param is triangle vertex 
			Vector2f[] triangleVerts = {
					  new Vector2f((float)(point.get(0).getX()-cm.getX()),(float)(point.get(0).getY()-cm.getY()))
					, new Vector2f((float)(point.get(1).getX()-cm.getX()),(float)(point.get(1).getY()-cm.getY()))
					, new Vector2f((float)(point.get(2).getX()-cm.getX()),(float)(point.get(2).getY()-cm.getY()))};
			ConvexPolygon trianglePolygon = new ConvexPolygon(triangleVerts);
			if(triangle.isFixed() == true)
			{
				/** Static body want only name and polygon */
				Body body = new StaticBody("triangle", trianglePolygon);
				body.setPosition((float)cm.getX(),(float)cm.getY());
				return body;
			}
			else
			{
				/** param is Polygon and mg */
				Body body = new Body(trianglePolygon,10);
				body.setPosition((float)cm.getX(),(float)cm.getY());
				return body;
			}
		}
    	
    	else if(obj.getClass().getCanonicalName().equalsIgnoreCase("CommonShape.RectangleShape"))
    	{
    		RectangleShape rectangle = (RectangleShape)obj;
    		
    		ArrayList<Point2D> point = rectangle.getPointPolygon();
			Point2D cm = rectangle.getCM();
			// The param is rectangle vertex 
			Vector2f[] rectangleVerts = {
			  new Vector2f((float)(point.get(0).getX()-cm.getX()),(float)(point.get(0).getY()-cm.getY()))
			, new Vector2f((float)(point.get(1).getX()-cm.getX()),(float)(point.get(1).getY()-cm.getY()))
			, new Vector2f((float)(point.get(2).getX()-cm.getX()),(float)(point.get(2).getY()-cm.getY()))
			, new Vector2f((float)(point.get(3).getX()-cm.getX()),(float)(point.get(3).getY()-cm.getY()))};
			
			ConvexPolygon rectanglePolygon = new ConvexPolygon(rectangleVerts);
			if(rectangle.isFixed() == true)
			{
				/** Static body want only name and polygon */
				Body body = new StaticBody("Rectangle", rectanglePolygon);
				body.setPosition((float)cm.getX(),(float)cm.getY());
				return body;
			}
			else
			{
				/** param is Polygon and mg */
				Body body = new Body(rectanglePolygon,10);
				body.setPosition((float)cm.getX(),(float)cm.getY());
				return body;
			}
    	}
    	
    	else if(obj.getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
    	{
    		EllipseShape ellipse = (EllipseShape)obj;
			Ellipse2D polygon = ellipse.getEllipse();
			double xmax = polygon.getMaxX();
			double xmin = polygon.getMinX();
			float radius = (float)(xmax-xmin)/2;
			Circle circle = new Circle(radius);
			
			if(ellipse.isFixed())
			{
				Body body = new StaticBody("Circle",circle);
				body.setPosition((float)ellipse.getCM().getX(),(float)ellipse.getCM().getY());
				return body;
			}
			
			else
			{
				Body body = new Body(circle,10);
				body.setPosition((float)ellipse.getCM().getX(),(float)ellipse.getCM().getY());
				return body;
			}
    		
    	}
    	
    	else if(obj.getClass().getCanonicalName().equalsIgnoreCase("CommonShape.PolygonShape"))
    	{
    		PolygonShape polygon = (PolygonShape)obj;
    		
    		ArrayList<Point2D> point = polygon.getPointPolygon();
			Point2D cm = polygon.getCM();
			// The param is polygon vertex 
			Vector2f[] polygonVerts = new Vector2f[point.size()];
			for(int i = 0;i<point.size();i++)
			{
				polygonVerts[i] = new Vector2f((float)(point.get(i).getX()-cm.getX()),(float)(point.get(i).getY()-cm.getY()));
			}
			
			ConvexPolygon polygonPolygon = new ConvexPolygon(polygonVerts);
			if(polygon.isFixed() == true)
			{
				/** Static body want only name and polygon */
				Body body = new StaticBody("Polygon", polygonPolygon);
				body.setPosition((float)cm.getX(),(float)cm.getY());
				return body;
			}
			else
			{
				/** param is Polygon and mg */
				Body body = new Body(polygonPolygon,10);
				body.setPosition((float)cm.getX(),(float)cm.getY());
				return body;
			}
    		
    	}
		
    	else if(obj.getClass().getCanonicalName().equalsIgnoreCase("java.awt.Polygon"))
    	{
    		java.awt.Polygon polygon = (Polygon)obj;
    		
    		ArrayList<Point2D> point = ShapeConvert.convertPolygontoPoint2D(polygon);
			Point2D cm = ShapeProperty.getCM(point);
			// The param is polygon vertex 
			Vector2f[] polygonVerts = new Vector2f[point.size()];
			for(int i = 0;i<point.size();i++)
			{
				polygonVerts[i] = new Vector2f((float)(point.get(i).getX()-cm.getX()),(float)(point.get(i).getY()-cm.getY()));
			}
			ConvexPolygon polygonPolygon = new ConvexPolygon(polygonVerts);
			
			/** param is Polygon and mg */
			Body body = new Body(polygonPolygon,10);
			body.setPosition((float)cm.getX(),(float)cm.getY());
			return body;
    	}
		
    	else
    	{
    		return null;
    	}
		
	}
	
	/**
	 * Convert Polygon body to polygon
	 * @param obj
	 * @return
	 */
	public static Polygon convertBodytoPolygon(Body obj)
	{
		net.phys2d.raw.shapes.Polygon body = (net.phys2d.raw.shapes.Polygon) obj.getShape();
		ROVector2f position = obj.getPosition();
		ROVector2f[] vertexBody = body.getVertices();
		Polygon polygon = new Polygon();
		for(int j = 0;j<vertexBody.length;j++)
		{
			polygon.addPoint((int)(vertexBody[j].getX()+position.getX()),(int)(vertexBody[j].getY()+position.getY()));
		}
		
		return polygon;
	}
	
	/**
	 * Convert Circle body to Ellipse2D
	 * @param obj
	 * @return
	 */
	public static Ellipse2D convertCircleBodytoEllipse(Body obj)
	{
		net.phys2d.raw.shapes.Circle body = (net.phys2d.raw.shapes.Circle) obj.getShape();
		ROVector2f position = obj.getPosition();
		Ellipse2D circle = new Ellipse2D.Double((position.getX()-body.getRadius()),(position.getY()-body.getRadius()), body.getRadius()*2, body.getRadius()*2);
		return circle;
	}
	
	/**
	 * For convert from shape to polygon.
	 * @param pi
	 * @return
	 */
	public static Polygon convertShapetoPolygon(FlatteningPathIterator pi)
	{
		Polygon polygonTemp = new Polygon();
		while (pi.isDone() == false) {
			int[]pointTemp = describeCurrentSegment(pi);
			polygonTemp.addPoint(pointTemp[0], pointTemp[1]);
			pi.next();
		}
		polygonTemp.npoints = polygonTemp.npoints-1;
		return polygonTemp;
	}
	
	/**
	 * PathIterator
	 * @param pi
	 */
	private static void describeCurrentSegment(PathIterator pi) 
	{
		double[] coordinates = new double[6];
		int type = pi.currentSegment(coordinates);
		switch (type) {
		case PathIterator.SEG_MOVETO:
			System.out.println("move to " + coordinates[0] + ", "
					+ coordinates[1]);
			break;
		case PathIterator.SEG_LINETO:
			System.out.println("line to " + coordinates[0] + ", "
					+ coordinates[1]);
			break;
		case PathIterator.SEG_QUADTO:
			System.out.println("quadratic to " + coordinates[0] + ", "
					+ coordinates[1] + ", " + coordinates[2] + ", "
					+ coordinates[3]);
			break;
		case PathIterator.SEG_CUBICTO:
			System.out.println("cubic to " + coordinates[0] + ", "
					+ coordinates[1] + ", " + coordinates[2] + ", "
					+ coordinates[3] + ", " + coordinates[4] + ", "
					+ coordinates[5]);
			break;
		case PathIterator.SEG_CLOSE:
			System.out.println("close");
			break;
		default:
			break;
		}
	}

	/**
	 * FlatteningPathIterator
	 * @param pi
	 * @return
	 */
	private static int[] describeCurrentSegment(FlatteningPathIterator pi) 
	{
		int[] aryResult = new int[2];
		double[] coordinates = new double[6];
		int type = pi.currentSegment(coordinates);
		switch (type) {
		case PathIterator.SEG_MOVETO:
			System.out.println("move to " + coordinates[0] + ", "
					+ coordinates[1]);
			//Test only need accurate one
			aryResult[0] = (int)coordinates[0];
			aryResult[1] = (int)coordinates[1];
			break;
		case PathIterator.SEG_LINETO:
			System.out.println("line to " + coordinates[0] + ", "
					+ coordinates[1]);
			aryResult[0] = (int)coordinates[0];
			aryResult[1] = (int)coordinates[1];
			break;
		case PathIterator.SEG_QUADTO:
			System.out.println("quadratic to " + coordinates[0] + ", "
					+ coordinates[1] + ", " + coordinates[2] + ", "
					+ coordinates[3]);
			break;
		case PathIterator.SEG_CUBICTO:
			System.out.println("cubic to " + coordinates[0] + ", "
					+ coordinates[1] + ", " + coordinates[2] + ", "
					+ coordinates[3] + ", " + coordinates[4] + ", "
					+ coordinates[5]);
			break;
		case PathIterator.SEG_CLOSE:
			System.out.println("close");
			break;
		default:
			break;
		}
		return aryResult;
	}
}
