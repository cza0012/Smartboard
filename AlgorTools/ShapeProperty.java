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
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import TempObject.LocationOfString;


/**
 * เป็น static class เพื่อไว้เช็ค property ต่างๆๆของ shape
 *
 */
public class ShapeProperty 
{
	
	private ShapeProperty()
	{
		
	}
	
	/**
	 * Get CM of the Shape
	 * @param intersectPoint
	 * @return Point2D (CM)
	 */
	public static Point2D getCM(ArrayList<Point2D> intersectPoint)
	{
		double xCen = 0.0;
		double yCen = 0.0;
		for(int k=0;k<intersectPoint.size();k++)
		{
			xCen+= intersectPoint.get(k).getX();
			yCen+= intersectPoint.get(k).getY();
		}
		Point2D CM = new Point2D.Double((int)(xCen/intersectPoint.size()),(int)(yCen/intersectPoint.size()));
		return CM;
	}
	
	/**
	 * Get Point for draw magnitude of shape.
	 * @param object
	 * @return
	 */
	public static ArrayList<LocationOfString> findDrawPointMagnitude(Polygon shape) 
	{
		ArrayList<Point2D> arrayPoint = ShapeConvert
		.convertPolygontoPoint2D(shape);
		ArrayList<LocationOfString> arrayResult = new ArrayList<LocationOfString>();
		
		for (int i = 0; i < arrayPoint.size(); i++) {
			double manitudeOfLine = 0;
			double locationPaintX = 0;
			double locationPaintY = 0;
			Line2D vertax = null;
		
			if (i == arrayPoint.size() - 1) {
				manitudeOfLine = (int) LineProperty.findMagnitudeOfLine(new Line2D.Double(arrayPoint.get(i), arrayPoint.get(0)));
				locationPaintX = (int) (arrayPoint.get(i).getX() + arrayPoint
						.get(0).getX()) / 2;
				locationPaintY = (int) (arrayPoint.get(i).getY() + arrayPoint
						.get(0).getY()) / 2;
				vertax = new Line2D.Double(arrayPoint.get(i), arrayPoint.get(0));
			} else {
				manitudeOfLine = (int) LineProperty.findMagnitudeOfLine(new Line2D.Double(
						arrayPoint.get(i), arrayPoint.get(i + 1)));
				locationPaintX = (int) (arrayPoint.get(i).getX() + arrayPoint
						.get(i + 1).getX()) / 2;
				locationPaintY = (int) (arrayPoint.get(i).getY() + arrayPoint
						.get(i + 1).getY()) / 2;
				vertax = new Line2D.Double(arrayPoint.get(i), arrayPoint
						.get(i + 1));
			}
		if(manitudeOfLine > 12){
			String stringLenght = "" + manitudeOfLine;
			int wide = stringLenght.length() * 6;
			double testDistance = 5.0;
			double realDistance = 10.0;
		
			Line2D testLineResultPlus = LineProperty.getParallelLine(
					vertax, testDistance);
			Point2D pointPlus = new Point2D.Double((testLineResultPlus.getP1()
					.getX() + testLineResultPlus.getP2().getX()) / 2,
					(testLineResultPlus.getP1().getY() + testLineResultPlus
							.getP2().getY()) / 2);
			if (!shape.contains(pointPlus)) {
				Line2D lineResult = LineProperty.getParallelLine(
						vertax, realDistance);
				Point2D pointResult = realLocationOfStringLineMagnitude(shape,
						lineResult, wide);
				locationPaintX = pointResult.getX();
				locationPaintY = pointResult.getY();
			} else {
				Line2D lineResult = LineProperty.getParallelLine(
						vertax, -realDistance);
				Point2D pointResult = realLocationOfStringLineMagnitude(shape,
						lineResult, wide);
				locationPaintX = pointResult.getX();
				locationPaintY = pointResult.getY();
			}
				arrayResult.add(new LocationOfString(locationPaintX,
						locationPaintY, "" + manitudeOfLine));
			}
		}
		
		
		
		return arrayResult;

	}
	
	/**
	 * 
	 * @param object
	 * @param line
	 * @param stringLenght
	 * @return
	 */
	public static Point2D realLocationOfStringLineMagnitude(Polygon object,Line2D line, int stringLenght) 
	{
		double locationPaintX = (line.getP1().getX() + line.getP2().getX()) / 2;
		double locationPaintY = (line.getP1().getY() + line.getP2().getY()) / 2;
		double stringHeight = 12.0;

		Point2D point1 = new Point2D.Double(locationPaintX, locationPaintY);
		Point2D point2 = new Point2D.Double(locationPaintX, locationPaintY
				- stringHeight);
		Point2D point3 = new Point2D.Double(locationPaintX + stringLenght,
				locationPaintY - stringHeight);
		Point2D point4 = new Point2D.Double(locationPaintX + stringLenght,
				locationPaintY);
		Rectangle stringRectangle = new Rectangle((int) locationPaintX,
				(int) (locationPaintY - stringHeight), (int) stringLenght,
				(int) stringHeight);

		if (!(object.contains(point1) || object.contains(point2)
				|| object.contains(point3) || object.contains(point4) || object
				.intersects(stringRectangle))) 
		{
			return point1;
		} 
		else 
		{
			point2 = new Point2D.Double(locationPaintX, locationPaintY);
			point1 = new Point2D.Double(locationPaintX, locationPaintY
					+ stringHeight);
			point4 = new Point2D.Double(locationPaintX + stringLenght,
					locationPaintY + stringHeight);
			point3 = new Point2D.Double(locationPaintX + stringLenght,
					locationPaintY);
			stringRectangle = new Rectangle((int) locationPaintX,
					(int) locationPaintY, (int) stringLenght,
					(int) stringHeight);
			if (!(object.contains(point1) || object.contains(point2)
					|| object.contains(point3) || object.contains(point4) || object
					.intersects(stringRectangle))) 
			{
				return point1;
			} 
			else 
			{
				point3 = new Point2D.Double(locationPaintX, locationPaintY);
				point4 = new Point2D.Double(locationPaintX, locationPaintY
						+ stringHeight);
				point1 = new Point2D.Double(locationPaintX - stringLenght,
						locationPaintY + stringHeight);
				point2 = new Point2D.Double(locationPaintX - stringLenght,
						locationPaintY);
				stringRectangle = new Rectangle(
						(int) (locationPaintX - stringLenght),
						(int) locationPaintY, (int) stringLenght,
						(int) stringHeight);
				if (!(object.contains(point1) || object.contains(point2)
						|| object.contains(point3) || object.contains(point4) || object
						.intersects(stringRectangle))) 
				{
					return point1;
				} 
				else 
				{
					point4 = new Point2D.Double(locationPaintX, locationPaintY);
					point3 = new Point2D.Double(locationPaintX, locationPaintY
							- stringHeight);
					point2 = new Point2D.Double(locationPaintX - stringLenght,
							locationPaintY - stringHeight);
					point1 = new Point2D.Double(locationPaintX - stringLenght,
							locationPaintY);
					stringRectangle = new Rectangle(
							(int) (locationPaintX - stringLenght),
							(int) (locationPaintY - stringHeight),
							(int) stringLenght, (int) stringHeight);
					if (!(object.contains(point1) || object.contains(point2)
							|| object.contains(point3)
							|| object.contains(point4) || object
							.intersects(stringRectangle))) {
						return point1;
					}
				}
			}
		}
		return new Point2D.Double(locationPaintX, locationPaintY);
	}
	
	/**
	 * Get Point of rotation of the polygon
	 * @param selectedObject
	 * @return
	 */
	public static Point2D pointOfRotatePolygon(Polygon selectedObject,Point2D cm) 
	{
		ArrayList<Point2D> arrayPoint = ShapeConvert.convertPolygontoPoint2D(selectedObject);
		double maxY = 0;
		for (int i = 0; i < arrayPoint.size(); i++) 
		{
			if(i == 0)
			{
				maxY = arrayPoint.get(0).getY();
			}
			
		    else if (arrayPoint.get(i).getY() < maxY) 
			{
				maxY = arrayPoint.get(i).getY();
			}
		}
		return new Point2D.Double(cm.getX(),(maxY-10));
	}
	
	/**
	 * Get the diameter of Ellipse
	 *
	 */
	public static int Getdiameter(ArrayList<Point2D> Point)
	{
		Point2D refPoint = Point.get(0);
		double distance = 0.0;
		for(int k =1;k<Point.size();k++)
		{
			double tmp = refPoint.distance(Point.get(k));
			if(tmp>distance)
			{
				distance = tmp;
			}
		}
		
		return (int)distance;
	}
	
	
	/**
	 * For remove the shape that create from the same as point
	 * then it's not have area it's only be a point
	 * @param lineShape
	 */
	public static ArrayList<ArrayList<Point2D>> removenonArea(ArrayList<ArrayList<Point2D>> lineShape)
	{
		for(int i = lineShape.size() -1;i >= 0;i--)
		{
			ArrayList<Point2D> vertexpoint = lineShape.get(i);
			Point2D startpoint = new Point2D.Double();
			for(int j=0;j<vertexpoint.size();j++)
			{
				if(j == 0)
				{
					startpoint = vertexpoint.get(j);
				}
				
				else if(startpoint.getX() == vertexpoint.get(j).getX() 
					 && startpoint.getY() == vertexpoint.get(j).getY()
					 && j == vertexpoint.size()-1)
				{
					lineShape.remove(i);
				}
				
				else if(startpoint.getX() == vertexpoint.get(j).getX() 
					 && startpoint.getY() == vertexpoint.get(j).getY())
				{
					
				}
				
				else
				{
					break;
				}
			}
		}
		return lineShape;
	}
	
	/**
	 * sorting the vertex of triangle
	 * @param vertex
	 * @return Array of point2D that has been sorted for display on physic animation
	 */
	public static ArrayList<Point2D> sortvertexTriangle(ArrayList<Point2D> vertex)
	{
		ArrayList<Point2D> sortvertex = new ArrayList<Point2D>();
		
		Point2D p = new Point2D.Double();
		int remove1 = 0;
		// 1. get the least of x if have 2 then should the x that y point less
		for(int i =0;i<vertex.size();i++)
		{
			if(i == 0)
			{
				p = vertex.get(i);
				remove1 = 0;
			}
			
			else if(p.getX() == vertex.get(i).getX())
			{
				if(p.getY() > vertex.get(i).getY())
				{
					p = vertex.get(i);
					remove1 = i;
				}
			}
			
			else if(p.getX() > vertex.get(i).getX())
			{
				p = vertex.get(i);
				remove1 = i;
			}
		}
		sortvertex.add(p);

		p = null;
		int remove2 = 0;
		// 2. get the less of y
		for(int i =0;i<vertex.size();i++)
		{
			if(i == remove1)
			{
				
			}
			
			else if(p == null)
			{
				p = vertex.get(i);
				remove2 = i;
			}
			
			else if(p.getY() > vertex.get(i).getY())
			{
				p = vertex.get(i);
				remove2 = i;
			}
		}
		
		sortvertex.add(vertex.get(remove2));
		
		// 3. get the remain
		for(int i =0;i<vertex.size();i++)
		{
			if(i == remove1||i == remove2)
			{
				
			}
			
			else
			{
				sortvertex.add(vertex.get(i));
			}
		}
		return sortvertex;
	}
	
	/**
	 * sorting the vertex of Polygon in clockwise
	 * @param vertex
	 * @return Array of point2D that has been sorted for display on physic animation
	 */
	public static ArrayList<Point2D> sortvertexPolygon(ArrayList<Point2D> vertex)
	{
		ArrayList<Point2D> sortvertex    = new ArrayList<Point2D>();
		
		Point2D p   = new Point2D.Double();
		int remove1 = 0;
		for(int i =0;i<vertex.size();i++)
		{
			if(i == 0)
			{
				p = vertex.get(i);
				remove1 = i;
			}
			
			else if(p.getX() == vertex.get(i).getX())
			{
				if(p.getY() > vertex.get(i).getY())
				{
					p = vertex.get(i);
					remove1 = i;
				}
			}
			
			else if(p.getX() > vertex.get(i).getX())
			{
				p = vertex.get(i);
				remove1 = i;
			}
		}
		
		sortvertex.add(p);
		
		int index1 = remove1 + 1;
		int index2 = remove1 - 1; 
		if(index1 < 0)
		{
			index1 = vertex.size()-1;
		}
		
		else if(index1 >= vertex.size())
		{
			index1 = 0;
		}
		
		if(index2 < 0)
		{
			index2 = vertex.size()-1;
		}
		
		else if(index2 >= vertex.size())
		{
			index2 = 0;
		}
		
		int secondindex = -1;
		Point2D p1 = vertex.get(index1);
		Point2D p2 = vertex.get(index2);
		
		if(p1.getY() > p2.getY())
		{
			secondindex = index2;
		}
		
		else
		{
			secondindex = index1;
		}
		
		// if secondindex > firstindex (remove1) then go plus
		if(secondindex > remove1)
		{
			for(int i =0;i<vertex.size()-1;i++)
			{
				sortvertex.add(vertex.get(secondindex));
				secondindex--;
				if(secondindex == vertex.size())
				{
					secondindex = 0;
				}
			}
		}
		
		// if secondindex < firstindex (remove1) then go down
		else if(secondindex < remove1)
		{
			for(int i =0;i<vertex.size()-1;i++)
			{
				sortvertex.add(vertex.get(secondindex));
				secondindex++;
				if(secondindex == vertex.size())
				{
					secondindex = 0;
				}
			}
		}
		return sortvertex;
	}
	
	/**
	 * Get Point for draw magnitude of Force.
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static LocationOfString findDrawForceMagnitude(Point2D startingPoint,Point2D endPoint) 
	{
		LocationOfString forceMagnitude = new LocationOfString();
		String result = "" + startingPoint.distance(endPoint);
		forceMagnitude.setValue(result);
		Polygon linePolygon = new Polygon();
		linePolygon.addPoint((int) startingPoint.getX(), (int) startingPoint
				.getY());
		linePolygon.addPoint((int) endPoint.getX(), (int) endPoint.getY());
		ArrayList<LocationOfString> resultString = findDrawPointMagnitude(linePolygon);
		forceMagnitude.setLocation(resultString.get(0).getX(), resultString
				.get(0).getX());
		return forceMagnitude;
	}

	/**
	 * Get Point rotation
	 * @param degree
	 * @param startPoint
	 * @param endPoint
	 * @param point
	 * @param line
	 * @return
	 */
	public static Point2D pointRotation(double degree, Point2D startPoint,
			Point2D endPoint, Point2D point, java.awt.Polygon line) 
	{
		AffineTransform atxControlPoint = null;
		if(startPoint.getX() < endPoint.getX()){
			if (startPoint.getY() < endPoint.getY()) {
				atxControlPoint = AffineTransform.getRotateInstance(Math
						.toRadians(0 + degree), startPoint.getX(), startPoint
						.getY());
			} else {
				atxControlPoint = AffineTransform.getRotateInstance(Math
						.toRadians(0 - degree), startPoint.getX(), startPoint
						.getY());
			}
		}else{
			if (startPoint.getY() < endPoint.getY()) {
				atxControlPoint = AffineTransform.getRotateInstance(Math
						.toRadians(0 - degree), startPoint.getX(), startPoint
						.getY());
			} else {
				atxControlPoint = AffineTransform.getRotateInstance(Math
						.toRadians(0 + degree), startPoint.getX(), startPoint
						.getY());
			}
		}
		
		line.addPoint((int) point.getX(), (int) point.getY());
		Shape conTrolPointLineShape = atxControlPoint
				.createTransformedShape(line);
		FlatteningPathIterator pi = new FlatteningPathIterator(
				conTrolPointLineShape.getPathIterator(null), 100);
		java.awt.Polygon controlPointPolygon = ShapeConvert.convertShapetoPolygon(pi);
		ArrayList<Point2D> controlPointArrayList = ShapeConvert
				.convertPolygontoPoint2D(controlPointPolygon);
		return controlPointArrayList.get(1);
	}
}
