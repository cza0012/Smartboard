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

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import SpecialShape.FixJointShape;
import SpecialShape.JointShape;

import CommonShape.EllipseShape;
import CommonShape.PolygonShape;
import CommonShape.RectangleShape;
import CommonShape.TriangleShape;

public class ShapeAutoGenerate 
{
	private ShapeAutoGenerate()
	{
		
	}
	
	/**
	 * Auto tool while being draw triangle
	 * @param toolStartPoint
	 * @param toolEndPoint
	 * @return
	 */
	public static TriangleShape autoGenTriangle(Point2D toolStartPoint, Point2D toolEndPoint)
	{
		ArrayList<Point2D> vertices = new ArrayList<Point2D>();
		vertices.add(new Point2D.Double(((toolStartPoint.getX()+toolEndPoint.getX())/2),toolStartPoint.getY()));
		vertices.add(new Point2D.Double(toolEndPoint.getX(),toolEndPoint.getY()));
		vertices.add(new Point2D.Double(toolStartPoint.getX(),toolEndPoint.getY()));
		
		TriangleShape autoTri = new TriangleShape(vertices);
		return autoTri;
	}
	
	/**
	 * Auto tool while being draw rectangle
	 * @param toolStartPoint
	 * @param toolEndPoint
	 * @return
	 */
	public static RectangleShape autoGenRectangle(Point2D toolStartPoint, Point2D toolEndPoint)
	{
		ArrayList<Point2D> vertices = new ArrayList<Point2D>() ;
		vertices.add(new Point2D.Double(toolStartPoint.getX(),toolStartPoint.getY()));
		vertices.add(new Point2D.Double(toolEndPoint.getX(),toolStartPoint.getY()));
		vertices.add(new Point2D.Double(toolEndPoint.getX(),toolEndPoint.getY()));
		vertices.add(new Point2D.Double(toolStartPoint.getX(),toolEndPoint.getY()));
		
		RectangleShape autoRect = new RectangleShape(vertices);
		return autoRect;
	}
	
	/**
	 * Auto tool while being draw ellipse.
	 * @param toolStartPoint
	 * @param toolEndPoint
	 * @return
	 */
	public static EllipseShape autoGenEllipse(Point2D toolStartPoint, Point2D toolEndPoint)
	{
		EllipseShape autoCir;
		
		// Quardrant 1
		if(toolStartPoint.getX()<toolEndPoint.getX()
				&&toolStartPoint.getY()>toolEndPoint.getY()){
			
			autoCir = new EllipseShape(toolStartPoint.getX(),toolEndPoint.getY(),toolStartPoint.getY()-toolEndPoint.getY());
		}
	    
		// Quardrant 2
		else if(toolStartPoint.getX()<toolEndPoint.getX()
				&&toolStartPoint.getY()<toolEndPoint.getY()){
			autoCir = new EllipseShape(toolStartPoint.getX(),toolStartPoint.getY(),toolEndPoint.getY()- toolStartPoint.getY());
		}
		
		// Quardrant 4
		else if(toolStartPoint.getX()>toolEndPoint.getX()&&toolStartPoint.getY()>toolEndPoint.getY())
		{
			autoCir = new EllipseShape(toolEndPoint.getX(),toolEndPoint.getY(),(Math.sqrt(Math.pow(toolStartPoint.getX()-toolEndPoint.getX(),2)+Math.pow(toolStartPoint.getY()-toolEndPoint.getY(), 2))) );
		}
		
		// Quardrant 3
		else
		{
			autoCir = new EllipseShape(toolEndPoint.getX(),toolStartPoint.getY(),(Math.sqrt(Math.pow(toolStartPoint.getX()-toolEndPoint.getX(),2)+Math.pow(toolEndPoint.getY()-toolStartPoint.getY(), 2))) );
		}	
		return autoCir;
	}
	
	/**
	 * Auto tool while being draw Balloon.
	 * @param toolStartPoint
	 * @param toolEndPoint
	 * @return
	 */
	public static ArrayList<Object> autoGenBalloon(Point2D toolStartPoint, Point2D toolEndPoint)
	{
		if(toolStartPoint.getX()<toolEndPoint.getX()&&toolStartPoint.getY()<toolEndPoint.getY())
		{
			Line2D lh = new Line2D.Double((int)toolStartPoint.getX(), (int)toolStartPoint.getY()
					, (int)toolEndPoint.getX(), (int)toolStartPoint.getY());
			int horizontalLength = (int)Math.abs(lh.getX1()-lh.getX2());
			double verticalLength = ((horizontalLength/3)*6);
			
			ArrayList<Object> component = new ArrayList<Object>();
			//Basket Polygon
			// CCW
			ArrayList<Point2D> point = new ArrayList<Point2D>();
			//0 TopLeft
			point.add(new Point2D.Double((toolStartPoint.getX()+((horizontalLength/3)*0.75))
					,(toolStartPoint.getY()+verticalLength-(horizontalLength/3))));
			//3 TopRight
			point.add(new Point2D.Double((toolStartPoint.getX()+((horizontalLength/3)*2.25))
					,(toolStartPoint.getY()+verticalLength-(horizontalLength/3))));
			//2 BottomRight
			point.add(new Point2D.Double((toolStartPoint.getX()+((horizontalLength/3)*2.25))
					,(toolStartPoint.getY()+verticalLength)));
			//1 ButtomLeft
			point.add(new Point2D.Double((toolStartPoint.getX()+((horizontalLength/3)*0.75))
					,(toolStartPoint.getY()+verticalLength)));
			PolygonShape basket = new PolygonShape(point);
			//Create BalloonShape
			component.add(basket);
			
			EllipseShape ball = new EllipseShape(
					(toolStartPoint.getX())
					,(toolStartPoint.getY())
					,horizontalLength);
			component.add(ball);
			FixJointShape j2 = new FixJointShape(ball,basket);
			JointShape j1 = new JointShape(ball,basket);
			j1.setRotatePoint(new Point2D.Double(toolStartPoint.getX()
					,toolStartPoint.getY()+(horizontalLength/2)));
			JointShape j3 = new JointShape(ball,basket);
			j3.setRotatePoint(new Point2D.Double(toolEndPoint.getX()
					,toolStartPoint.getY()+(horizontalLength/2)));
			component.add(j1);
			component.add(j2);
			component.add(j3);
			return component;
		}
		return null;
	}
	
	/**
	 * Auto tool while being draw car.
	 * @param toolStartPoint
	 * @param toolEndPoint
	 * @return
	 */
	public static ArrayList<Object> autoGenCar(Point2D toolStartPoint, Point2D toolEndPoint)
	{
		if(toolStartPoint.getX()<toolEndPoint.getX()&&toolStartPoint.getY()<toolEndPoint.getY())
		{
			Line2D lh = new Line2D.Double((int)toolStartPoint.getX(), (int)toolStartPoint.getY()
					, (int)toolEndPoint.getX(), (int)toolStartPoint.getY());
			int horizontalLength = (int)Math.abs(lh.getX1()-lh.getX2());
			double verticalLength = (((horizontalLength/2)/14)*17);

				
			///////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////
			ArrayList<Point2D> point = new ArrayList<Point2D>();
			//B right down R
			point.add(new Point2D.Double(
				(toolEndPoint.getX()-(4*verticalLength/17)+(4*verticalLength/17))
				,(toolStartPoint.getY()+(14*verticalLength/17)) ));
			//B rigt top
			point.add(new Point2D.Double((toolEndPoint.getX()-(4*verticalLength/17)) 
				,toolStartPoint.getY()));
			//B left top
			point.add(new Point2D.Double(toolStartPoint.getX() 
				,toolStartPoint.getY()));
			//B left down L
			point.add(new Point2D.Double(toolStartPoint.getX()
				,(toolStartPoint.getY())+(int)(14*verticalLength/17)));
			//B left down R
			point.add(new Point2D.Double(((toolStartPoint.getX())+(4*verticalLength/17)) 
				,(toolStartPoint.getY())+(int)(14*verticalLength/17) ));
			//Body left
			point.add(new Point2D.Double(((toolStartPoint.getX())+(4*verticalLength/17)) 
				,(toolStartPoint.getY()+(10*verticalLength/17))));
			//Body right
			point.add(new Point2D.Double((toolEndPoint.getX()-(4*verticalLength/17))
				,(toolStartPoint.getY()+(10*verticalLength/17))));
			//B right down L
			point.add(new Point2D.Double((toolEndPoint.getX()-(4*verticalLength/17))
				,(toolStartPoint.getY()+(14*verticalLength/17))));
			PolygonShape carBody = new PolygonShape(point);
				
			EllipseShape wheel1 = new EllipseShape(
					((toolStartPoint.getX())+(4*verticalLength/17)+(1*verticalLength/17))
					,((toolStartPoint.getY())+(14*verticalLength/17)-(3*verticalLength/17))
					,(6*verticalLength/17));
			EllipseShape wheel2 = new EllipseShape(
					(toolEndPoint.getX()-(4*verticalLength/17)-(3*verticalLength/17)-(4*verticalLength/17))
					,((toolStartPoint.getY())+(14*verticalLength/17)-(3*verticalLength/17))
					,(6*verticalLength/17));

			JointShape j1 = new JointShape(carBody,wheel1);
				j1.setRotatePoint(wheel1.getCM());
			JointShape j2 = new JointShape(carBody,wheel2);
				j2.setRotatePoint(wheel2.getCM());
				
			ArrayList<Object> component = new ArrayList<Object>();
			component.add(carBody);
			component.add(wheel1);
			component.add(j1);
			component.add(wheel2);
			component.add(j2);
			return component;
		}
		return null;
	}
	
	/**
	 * Auto tools while drawing force for generate axis of force.
	 * @return
	 */
	public static ArrayList<Line2D> autoGenAxis(Point2D point)
	{
        // Vertical line
        Line2D l1 = new Line2D.Double((int)point.getX()-5,(int)point.getY(),(int)point.getX()+5,(int)point.getY());
        // Horizontal line
        Line2D l2 = new Line2D.Double((int)point.getX(),(int)point.getY()-5,(int)point.getX(),(int)point.getY()+5);
        ArrayList<Line2D> axis = new ArrayList<Line2D>();
        axis.add(l1);
        axis.add(l2);
		return axis;
	}
	
	/**
	 * Auto tools gennerate pool nine ball for template.
	 * @param x position of 9 ball
	 * @param y position of 9 ball
	 * @param diameter
	 * @return 	9
	 * 			8
	 * 			7
	 * 			4
	 * 			5
	 * 			6
	 * 			2
	 * 			3
	 * 			1
	 */
	public static ArrayList<EllipseShape> autoGenpoolNineBall(double x,double y, double diameter) 
	{
		ArrayList<EllipseShape> resultLocation = new ArrayList<EllipseShape>();
		EllipseShape number9 = new EllipseShape(x, y, diameter);
		resultLocation.add(number9);
		EllipseShape number8 = new EllipseShape(x + diameter, y
				- (diameter / 2), diameter);
		resultLocation.add(number8);
		EllipseShape number7 = new EllipseShape(x + diameter, y
				+ (diameter / 2), diameter);
		resultLocation.add(number7);
		EllipseShape number4 = new EllipseShape(x + (diameter * 2), y
				- diameter, diameter);
		resultLocation.add(number4);
		EllipseShape number5 = new EllipseShape(x + (diameter * 2),
				y, diameter);
		resultLocation.add(number5);
		EllipseShape number6 = new EllipseShape(x + (diameter * 2), y
				+ diameter, diameter);
		resultLocation.add(number6);
		EllipseShape number2 = new EllipseShape(x + (diameter * 3), y
				- (diameter / 2), diameter);
		resultLocation.add(number2);
		EllipseShape number3 = new EllipseShape(x + (diameter * 3), y
				+ (diameter / 2), diameter);
		resultLocation.add(number3);
		EllipseShape number1 = new EllipseShape(x + (diameter * 4),
				y, diameter);
		resultLocation.add(number1);
		
		return resultLocation;
	}
}
