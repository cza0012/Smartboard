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

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Rotation.PolygonRotationStrategy;

import AlgorTools.CloneShape;
import AlgorTools.LineIntersection;
import AlgorTools.LineProperty;
import AlgorTools.ShapeConvert;
import AlgorTools.ShapeProperty;
import CommonShape.CommonShape;

public class ArrowShape extends SpecialShape implements Cloneable
{
	/**
	 * rectangle 0 is the straingth line
	 */
	private ArrayList<Polygon> 	rectangle = new ArrayList<Polygon>();
	private Point2D			   	pointIntersec;
	private Point2D				rotatePoint;
	
	private float				gravityForce = 10;
	private int					degree		 = 0;

	/**
	 * Create instance of ArrowShape shape by using arraylist of line2D
	 * 
	 * @param line
	 */
	public ArrowShape(ArrayList<Line2D> line)
	{
		pointIntersec = LineIntersection.getIntersection(line.get(0),line.get(1));
		
		// set the line at the intersec point to point 2 of the line
		if(line.get(0).getP2().getX() != pointIntersec.getX() || line.get(0).getP2().getY() != pointIntersec.getY())
		{
			Point2D temp = line.get(0).getP2();
			line.get(0).setLine(temp, pointIntersec);
		}
		
		if(line.get(1).getP2().getX() != pointIntersec.getX() || line.get(1).getP2().getY() != pointIntersec.getY())
		{
			Point2D temp = line.get(1).getP2();
			line.get(1).setLine(temp, pointIntersec);
		}
		
		if(line.get(2).getP2().getX() != pointIntersec.getX() || line.get(2).getP2().getY() != pointIntersec.getY())
		{
			Point2D temp = line.get(2).getP2();
			line.get(2).setLine(temp, pointIntersec);
		}
		
		this.shape = line;
		reBuild(pointIntersec);
		
		this.setRotatePoint();
	}
	
	public ArrowShape(Polygon polygon, Polygon polygon2, Polygon polygon3, Point2D p2d) 
	{
		ArrayList<Polygon> p = new ArrayList<Polygon>();
		this.shape = new ArrayList<Line2D>();
		this.shape.add(new Line2D.Double(0,0,0,0));
		this.shape.add(new Line2D.Double(0,0,0,0));
		this.shape.add(new Line2D.Double(0,0,0,0));
		p.add(polygon); 
		p.add(polygon2); 
		p.add(polygon3);
		this.setBoundingBox(p);
		this.pointIntersec = p2d;
		this.setRotatePoint();
	}
	
	/**
	 * For set the rotate point of shape.
	 *
	 */
	private void setRotatePoint() 
	{
		this.rotatePoint = ShapeProperty.pointOfRotatePolygon(this.rectangle.get(0),ShapeProperty.getCM(ShapeConvert.convertPolygontoPoint2D(this.rectangle.get(0))));
	}

	/**
	 * Overloading from method reBuild()
	 * 
	 * @param point
	 */
	protected void reBuild(Point2D point)
	{
		Line2D l0 = this.shape.get(0);
		Line2D l1 = this.shape.get(1);
		Line2D l2 = this.shape.get(2);
		
		double degreeOfAngle1 = LineProperty.findDegreeBetweenLinesArrow(l0, l1);
		double degreeOfAngle2 = LineProperty.findDegreeBetweenLinesArrow(l1, l2);
		double degreeOfAngle3 = LineProperty.findDegreeBetweenLinesArrow(l0, l2);
		double maxAngle = degreeOfAngle1;
		ArrayList<Shape> collectionOfRectangles = new ArrayList<Shape>();
		Point2D newPointV = new Point2D.Double(point.getX() + 100, point.getY());
		Line2D newLineV = new Line2D.Double(point, newPointV);

		Line2D vector = l0;

		Line2D line1 = null;

		Line2D line2 = null;

		if (maxAngle < degreeOfAngle2) {
			maxAngle = degreeOfAngle2;
		}
		if (maxAngle < degreeOfAngle3) {
			maxAngle = degreeOfAngle3;
		}

		if (maxAngle == degreeOfAngle1) {
			vector = l2;
			line1 = l0;
			line2 = l1;
		}
		if (maxAngle == degreeOfAngle2) {
			vector = l0;
			line1 = l1;
			line2 = l2;
		}
		if (maxAngle == degreeOfAngle3) {
			vector = l1;
			line1 = l0;
			line2 = l2;
		}

		Point2D p2 = null;

		if (!vector.getP1().equals(point)) {
			p2 = vector.getP1();
		} else {
			p2 = vector.getP2();
		}
		double ydirection = point.getY() - p2.getY();

		if (ydirection >= 0) {

			double degreeV = LineProperty.esstimate(LineProperty
					.findDegreeBetweenLines(vector, newLineV));

			AffineTransform atxV = AffineTransform.getRotateInstance(Math
					.toRadians(360 - degreeV), point.getX(), point.getY());

			Rectangle2D rectangleV = new Rectangle2D.Double(point.getX(), point
					.getY() - 2, 100, 4);

			Shape sV = atxV.createTransformedShape(rectangleV);

			collectionOfRectangles.add(sV);

			AffineTransform atxL2 = AffineTransform.getRotateInstance(Math
					.toRadians(405 - degreeV), point.getX(), point.getY());

			Rectangle2D rectangleL = new Rectangle2D.Double(point.getX(), point
					.getY() - 2, 25, 4);

			Shape sL2 = atxL2.createTransformedShape(rectangleL);

			collectionOfRectangles.add(sL2);

			AffineTransform atxL1 = AffineTransform.getRotateInstance(Math
					.toRadians(315 - degreeV), point.getX(), point.getY());

			Shape sL1 = atxL1.createTransformedShape(rectangleL);

			collectionOfRectangles.add(sL1);
		} else {

			double degreeV = LineProperty.esstimate(LineProperty
					.findDegreeBetweenLines(vector, newLineV));

			AffineTransform atxV = AffineTransform.getRotateInstance(Math
					.toRadians(360 + degreeV), point.getX(), point.getY());

			Rectangle2D rectangleV = new Rectangle2D.Double(point.getX(), point
					.getY() - 2, 100, 4);
			
			Shape sV = atxV.createTransformedShape(rectangleV);

			collectionOfRectangles.add(sV);

			AffineTransform atxL2 = AffineTransform.getRotateInstance(Math
					.toRadians(405 + degreeV), point.getX(), point.getY());

			Rectangle2D rectangleL = new Rectangle2D.Double(point.getX(), point
					.getY() - 2, 25, 4);

			Shape sL2 = atxL2.createTransformedShape(rectangleL);

			collectionOfRectangles.add(sL2);

			AffineTransform atxL1 = AffineTransform.getRotateInstance(Math
					.toRadians(315 + degreeV), point.getX(), point.getY());
			Shape sL1 = atxL1.createTransformedShape(rectangleL);

			collectionOfRectangles.add(sL1);
		}
		
		for(int i =0;i<collectionOfRectangles.size();i++)
		{
			FlatteningPathIterator pi = new FlatteningPathIterator(collectionOfRectangles.get(i).getPathIterator(null), 100);
			rectangle.add(ShapeConvert.convertShapetoPolygon(pi));
		}
	}
	
	/**
	 * Get bounding box of the arrow
	 * @return polygon in rectangle boxex.
	 */
	public ArrayList<Polygon> getBondingBox()
	{
		return this.rectangle;
	}
	
	/**
	 * Set bounding box of the arrow
	 *
	 */
	public void setBoundingBox(ArrayList<Polygon> polygon)
	{
		this.rectangle = polygon;
		this.setRotatePoint();
	}
	
	/**
	 * Get the rotate shape (Ellipse2D).
	 * @return
	 */
	public Ellipse2D getRotateShape()
	{
		return new Ellipse2D.Double(this.rotatePoint.getX() - 4,this.rotatePoint.getY() - 4, 8, 8);
	}
	
	/**
	 * Get Intersection Point
	 * @return
	 */
	public Point2D getPointIntersection()
	{
		return this.pointIntersec;
	}
	
	@Override
	public void setTranslate(int x,int y)
	{	
		for(int i =0;i<rectangle.size();i++)
		{
			this.shape.get(i).setLine(this.shape.get(i).getX1()+x,this.shape.get(i).getY1()+y,this.shape.get(i).getX2()+x,this.shape.get(i).getY2()+y);	
			this.rectangle.get(i).translate(x,y);
		}
		this.setRotatePoint();
		this.pointIntersec.setLocation(this.pointIntersec.getX()+x,this.pointIntersec.getY()+y);
	}
	
	@Override
	protected void reBuild() 
	{
		
	}
	
	public ArrayList<Polygon> rotate(Point2D p1, Point2D p2,Point2D intersectionPoint, ArrayList<Polygon> arrayPolygon) 
	{
		ArrayList<Polygon> collectionOfRectangles = new ArrayList<Polygon>();
		PolygonRotationStrategy rotationArrow = new PolygonRotationStrategy();
		for(int i = 0; i < arrayPolygon.size(); i++)
		{
	
			Shape tempShape = rotationArrow.rotate(p1, p2, intersectionPoint, arrayPolygon.get(i));
			FlatteningPathIterator pi = new FlatteningPathIterator(tempShape
					.getPathIterator(null), 100);
			Polygon polygon = ShapeConvert.convertShapetoPolygon(pi);
			collectionOfRectangles.add(polygon);
		}
		return collectionOfRectangles;
	}
	
	@Override
	public void scalling() 
	{
		// TODO Auto-generated method stub
		
	}

	public float getGravityForce() 
	{
		return gravityForce;
	}

	public void setGravityForce(float gravityForce) 
	{
		this.gravityForce = gravityForce;
	}

	public int getDegree() 
	{
		return degree;
	}

	public void setDegree(int degree) 
	{
		this.degree = degree;
	}
	
	
	public Object clone() 
    {	
       
        ArrowShape obj 	 = (ArrowShape)super.clone();
        obj.shape		 = new ArrayList<Line2D>();
        for(int i =0;i<this.shape.size();i++)
        {
        	obj.shape.add((Line2D) this.shape.get(i).clone());
        }
        obj.rectangle  = new ArrayList<Polygon>();
        for(int i =0;i<this.rectangle.size();i++)
        {
        	obj.rectangle.add(CloneShape.clonePolygon(this.rectangle.get(i)));
        }
        obj.pointIntersec 	= (Point2D) this.pointIntersec.clone();
        obj.rotatePoint		= (Point2D) this.rotatePoint.clone();
    
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
