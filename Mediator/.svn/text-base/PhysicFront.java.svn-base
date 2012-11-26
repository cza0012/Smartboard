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
package Mediator;


import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.BasicJoint;
import net.phys2d.raw.Body;
import net.phys2d.raw.FixedJoint;
import net.phys2d.raw.Joint;
import net.phys2d.raw.RopeJoint;
import net.phys2d.raw.SpringJoint;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.shapes.Circle;
import net.phys2d.raw.shapes.Polygon;

import CommonShape.CommonShape;
import CommonShape.EllipseShape;
import CommonShape.PolygonShape;
import CommonShape.RectangleShape;
import CommonShape.TriangleShape;
import MainInterface.RunProgram;
import PhysicDrawing.MainPhysicInterface;
import SpecialShape.ArrowShape;
import SpecialShape.FixJointShape;
import SpecialShape.JointShape;
import SpecialShape.RopeShape;
import SpecialShape.SpecialShape;
import SpecialShape.SpringShape;

public class PhysicFront implements ShapeWriter 
{
	private Pipe pipe;
	private ArrayList<Body>   allBody = new ArrayList<Body>();
	private ArrayList<Joint> allJoint = new ArrayList<Joint>();
	private ArrayList<CommonShape> commonShape = new ArrayList<CommonShape>();
	private ArrayList<SpecialShape> specialShape = new ArrayList<SpecialShape>();
	
	public void receive(ArrayList<Object> allShape)
	{	 
		float gravityX = 0.0f;
		float gravityY = 0.0f;
		
		for(int i =0;i<allShape.size();i++)
		{
			if(allShape.get(i).getClass().getSuperclass().getCanonicalName().equalsIgnoreCase("CommonShape.CommonShape"))
			{
				this.commonShape.add((CommonShape)allShape.get(i));
			}
			
			else if(allShape.get(i).getClass().getSuperclass().getCanonicalName().equalsIgnoreCase("SpecialShape.SpecialShape"))
			{
				this.specialShape.add((SpecialShape)allShape.get(i));
			}
		}
		
		for(int i=0;i<this.commonShape.size();i++)
		{
			Object shape = this.commonShape.get(i);
			
			if(shape instanceof TriangleShape)
			{
				buildTriangleBody((TriangleShape)shape);
			}
			
			else if(shape instanceof RectangleShape)
			{
				buildRectangleBody((RectangleShape)shape);
			}
			
			else if(shape instanceof EllipseShape)
			{
				buildEllipseBody((EllipseShape)shape);
			}
			
			else if(shape instanceof PolygonShape)
			{
				buildPolygonBody((PolygonShape)shape);
			}	
		}	
		
		for(int i = 0;i<this.specialShape.size();i++)
		{
			Object shape = this.specialShape.get(i);
			
			if(shape instanceof FixJointShape)
			{
				buildFixjoint((FixJointShape)shape,i);
			}
			else if(shape instanceof JointShape)
			{
				buildJointShape((JointShape)shape);
			}
			else if(shape instanceof SpringShape)
			{
				buildSpringShape((SpringShape)shape);
			}
			else if(shape instanceof RopeShape)
			{
				buildRopeShape((RopeShape)shape);
			}
			
			else if(shape instanceof ArrowShape)
			{
				ArrowShape arrow = (ArrowShape)shape;
				
				if(arrow.getDegree() == 0)
				{
					gravityX = arrow.getGravityForce();
					gravityY = 0.0f;
				}
				
				else if(arrow.getDegree() == 90)
				{
					gravityX = 0.0f;
					gravityY = -arrow.getGravityForce();
				}
				
				else if(arrow.getDegree() == 180)
				{
					gravityX = -arrow.getGravityForce();
					gravityY = 0.0f;
				}
				
				else 
				{
					gravityX = 0.0f;
					gravityY = arrow.getGravityForce();
				}	
			}
		}
		
		stimulatePhysic(gravityX,gravityY);
	}

	/*
	 * New physic UI and
	 * input object in to physic pane
	 */	
	public void stimulatePhysic(float gravityX,float gravityY)
	{
		System.out.println("------Call-Physic-Interface------");
		System.out.println(allBody.size()+" "+allJoint.size());
		
		ArrayList<Body>   temp = new ArrayList<Body>();
		for(int i =allBody.size()-1;i>=0;i--)
		{
			temp.add(allBody.get(i));
		}
		
		this.allBody = temp;
	
		new MainPhysicInterface(allBody,allJoint,gravityX,gravityY,RunProgram.runner.drawing.mouse.getBackground());
	}

	public void send(ArrayList<Object> allShape)
	{
		// TODO Auto-generated method stub
		pipe.sendShape(allShape, this);
	}

	public void setMediator(Pipe pipe) 
	{
		// TODO Auto-generated method stub
		this.pipe = pipe;
	}
	
	/**
	 *  Method for add the Triangle Shape in the world container
	 *  in 2 type
	 *  1. static body
	 *  2. dynamic body
	 * @param triangle
	 */
	private void buildTriangleBody(TriangleShape triangle)
	{
		ArrayList<Point2D> point = triangle.getPointPolygon();
		Point2D cm = triangle.getCM();
		// The param is triangle vertex
		Vector2f[] triangleVerts = new Vector2f[3];
		triangleVerts[0] = new Vector2f((float)(point.get(0).getX()-cm.getX()),(float)(point.get(0).getY()-cm.getY()));
		triangleVerts[1] = new Vector2f((float)(point.get(1).getX()-cm.getX()),(float)(point.get(1).getY()-cm.getY()));
		triangleVerts[2] = new Vector2f((float)(point.get(2).getX()-cm.getX()),(float)(point.get(2).getY()-cm.getY()));
		
		Polygon trianglePolygon = new Polygon(triangleVerts);
		if(triangle.isFixed() == true)
		{
			/** Static body want only name and polygon */
			Body body = new StaticBody("Triangle", trianglePolygon);
			body.setPosition((float)cm.getX(),(float)cm.getY());
			
			body.setRestitution(triangle.getBounce());
			body.setMs((float) triangle.getMs());
			body.setMk((float) triangle.getMk());
			
			body.setColor(triangle.getColor());
			body.setImage(triangle.getImage());
			
			if(triangle.isBackground() == true)
			{
				body.setBackground(true);
			}
			
			allBody.add(body);
		}
		
		else
		{
			Body body = new Body(trianglePolygon,(float)triangle.getMass());
			body.setPosition((float)cm.getX(),(float)cm.getY());
			
			body.setRestitution(triangle.getBounce());
			body.setMs((float) triangle.getMs());
			body.setMk((float) triangle.getMk());
			
			body.setColor(triangle.getColor());
			body.setImage(triangle.getImage());
			
			if(triangle.isBackground() == true)
			{
				body.setBackground(true);
			}
			
			allBody.add(body);
		}  
	}
	
	/**
	 *  Method for add the Rectangle Shape in the world container
	 *  in 2 type
	 *  1. static body
	 *  2. dynamic body
	 *  @param rectangle
	 */
	private void buildRectangleBody(RectangleShape rectangle)
	{
			ArrayList<Point2D> point = rectangle.getPointPolygon();
			Point2D cm = rectangle.getCM();
			// The param is rectangle vertex 
			Vector2f[] rectangleVerts = new Vector2f[4];
			rectangleVerts[0] = new Vector2f((float)(point.get(0).getX()-cm.getX()),(float)(point.get(0).getY()-cm.getY()));
			rectangleVerts[1] = new Vector2f((float)(point.get(1).getX()-cm.getX()),(float)(point.get(1).getY()-cm.getY()));
			rectangleVerts[2] = new Vector2f((float)(point.get(2).getX()-cm.getX()),(float)(point.get(2).getY()-cm.getY()));
			rectangleVerts[3] = new Vector2f((float)(point.get(3).getX()-cm.getX()),(float)(point.get(3).getY()-cm.getY()));
					
			Polygon rectanglePolygon = new Polygon(rectangleVerts);
			if(rectangle.isFixed() == true)
			{
				/** Static body want only name and polygon */
				Body body = new StaticBody("Rectangle", rectanglePolygon);
				body.setPosition((float)cm.getX(),(float)cm.getY());
				
				body.setRestitution(rectangle.getBounce());
				body.setMs((float) rectangle.getMs());
				body.setMk((float) rectangle.getMk());
				
				body.setColor(rectangle.getColor());
				body.setImage(rectangle.getImage());
				
				if(rectangle.isBackground() == true)
				{
					body.setBackground(true);
				}
				
				allBody.add(body);
			}
			
			else
			{
				/** param is Polygon and mg **/
				Body body = new Body(rectanglePolygon,(float) rectangle.getMass());
				body.setPosition((float)cm.getX(),(float)cm.getY());
				
				body.setRestitution(rectangle.getBounce());
				body.setMs((float) rectangle.getMs());
				body.setMk((float) rectangle.getMk());
				
				body.setColor(rectangle.getColor());
				body.setImage(rectangle.getImage());
				
				if(rectangle.isBackground() == true)
				{
					body.setBackground(true);
				}
				
				allBody.add(body);
			}  
	}
	
	/**
	 *  Method for add the Ellipse Shape in the world container
	 *  in 2 type
	 *  1. static body
	 *  2. dynamic body
	 * @param ellipse
	 */
	private void buildEllipseBody(EllipseShape ellipse)
	{
		Ellipse2D polygon = ellipse.getEllipse();
		double xmax = polygon.getMaxX();
		double xmin = polygon.getMinX();
		float radius = (float)(xmax-xmin)/2;
		Circle circle = new Circle(radius);
		
		if(ellipse.isFixed())
		{
			Body body = new StaticBody("Circle",circle);
			body.setPosition((float)ellipse.getCM().getX(),(float)ellipse.getCM().getY());
			
			body.setRestitution(ellipse.getBounce());
			body.setMs((float) ellipse.getMs());
			body.setMk((float) ellipse.getMk());
			
			body.setColor(ellipse.getColor());
			body.setImage(ellipse.getImage());
			
			if(ellipse.isBackground() == true)
			{
				body.setBackground(true);
			}
			
			allBody.add(body);
		}
		
		else
		{
			Body body = new Body(circle,(float) ellipse.getMass());
			body.setPosition((float)ellipse.getCM().getX(),(float)ellipse.getCM().getY());
			
			body.setRestitution(ellipse.getBounce());
			body.setMs((float) ellipse.getMs());
			body.setMk((float) ellipse.getMk());
			
			body.setColor(ellipse.getColor());
			body.setImage(ellipse.getImage());
			
			if(ellipse.isBackground() == true)
			{
				body.setBackground(true);
			}
			
			allBody.add(body);
		}
	}
	
	/**
	 *  Method for add the Polygon Shape in the world container
	 *  in 2 type
	 *  1. static body
	 *  2. dynamic body
	 * @param polygon
	 */
	private void buildPolygonBody(PolygonShape polygon)
	{
		ArrayList<Point2D> point = polygon.getPointPolygon();
		Point2D cm = polygon.getCM();
		// The param is polygon vertex 
		Vector2f[] polygonVerts = new Vector2f[point.size()];
		
		for(int i = 0;i<point.size();i++)
		{
			polygonVerts[i] = new Vector2f((float)(point.get(i).getX()-cm.getX()),(float)(point.get(i).getY()-cm.getY()));
		}

		Polygon polygonPolygon = new Polygon(polygonVerts);
		if(polygon.isFixed() == true)
		{
			/** Static body want only name and polygon */
			Body body = new StaticBody("poly", polygonPolygon);
			body.setPosition((float)cm.getX(),(float)cm.getY());
			
			body.setRestitution(polygon.getBounce());
			body.setMs((float) polygon.getMs());
			body.setMk((float) polygon.getMk());
			
			body.setColor(polygon.getColor());
			body.setImage(polygon.getImage());
			
			if(polygon.isBackground() == true)
			{
				body.setBackground(true);
			}
			
			allBody.add(body);
		}
		else
		{
			/** param is Polygon and mg **/
			Body body = new Body(polygonPolygon,(float) polygon.getMass());
			body.setPosition((float)cm.getX(),(float)cm.getY());
			
			body.setRestitution(polygon.getBounce());
			body.setMs((float) polygon.getMs());
			body.setMk((float) polygon.getMk());
			
			body.setColor(polygon.getColor());
			body.setImage(polygon.getImage());
			if(polygon.isBackground() == true)
			{
				body.setBackground(true);
			}
			
			allBody.add(body);
		}  
	}
	
	private void buildFixjoint(FixJointShape fixjoint,int jointIndex)
	{	
		String vertexN1 = fixjoint.getStartIndex().getClass().getCanonicalName();
		String vertexN2 = fixjoint.getEndIndex().getClass().getCanonicalName();
		Object v1 = fixjoint.getStartIndex();
		Object v2 = fixjoint.getEndIndex();
		
		
		
		Body body1 = allBody.get(allBody.size()-2);
		Body body2 = allBody.get(allBody.size()-1);
		int quit=0;
		for(int i=0;i<commonShape.size();i++)
		{
			if(v1==commonShape.get(i)){
				body1 = allBody.get(i);
				quit++;
			}
			if(v2==commonShape.get(i)){
				body2 = allBody.get(i);
				quit++;
			}
			if(quit==2) break;
			
		}
		FixedJoint fj = new FixedJoint(body1,body2);
		allJoint.add(fj);
	}
	
	private void buildJointShape(JointShape jointshape)
	{
		String vertexN1 = jointshape.getStartIndex().getClass().getCanonicalName();
		String vertexN2 = jointshape.getEndIndex().getClass().getCanonicalName();
		Object v1 = jointshape.getStartIndex();
		Object v2 = jointshape.getEndIndex();

		Body body1 = allBody.get(allBody.size()-2);
		Body body2 = allBody.get(allBody.size()-1);
		Point2D rodPoint = jointshape.getRotatePoint();
		Vector2f v = new Vector2f((float)rodPoint.getX(),(float)rodPoint.getY());
		int quit=0;
		for(int i=0;i<commonShape.size();i++)
		{
			if(v1==commonShape.get(i)){
				body1 = allBody.get(i);
				quit++;
			}
			if(v2==commonShape.get(i)){
				body2 = allBody.get(i);
				quit++;
			}
			if(quit==2) break;
			
		}
		BasicJoint bj = new BasicJoint(body1,body2,v);
		allJoint.add(bj);
	}
	
	/**
	 * For build SpringShape
	 * @param shape
	 */
	private void buildSpringShape(SpringShape shape) 
	{
		Body bodyStart = null;
		Body bodyEnd   = null;
		
		for(int i =0;i<this.commonShape.size();i++)
		{
			if(this.commonShape.get(i) == shape.getStartIndex())
			{
				bodyStart = this.allBody.get(i);
			}
			
			if(this.commonShape.get(i) == shape.getEndIndex())
			{
				bodyEnd = this.allBody.get(i);
			}
		}
		
		float xStart = (float)(shape.getStartIndex().getCM().getX() - shape.getStartPoint().getX());
		float yStart = (float)(shape.getStartIndex().getCM().getY() - shape.getStartPoint().getY());
		
		float xEnd = (float)(shape.getEndIndex().getCM().getX() - shape.getEndPoint().getX());
		float yEnd = (float)(shape.getEndIndex().getCM().getY() - shape.getEndPoint().getY());
		
		SpringJoint springjoint = new SpringJoint(bodyStart,bodyEnd,new Vector2f(xStart,yStart),new Vector2f(xEnd,yEnd));
		springjoint.setCompressedSpringConst(shape.getKSpring());
		springjoint.setStretchedSpringConst(shape.getKSpring());
		springjoint.setNumberSpring(shape.getNumberSpring());
		allJoint.add(springjoint);
	}
	
	/**
	 * This method will build the rope shape and attach any 2 bodies if have
	 * For rope, The recieved pts must be really closed together
	 * If its not >> add more pts between them
	 * ..- updated >> move the boxStart/boxEnd position not too close to the last/first
	 * 	element of rope
	 * ..- updated >> add excluded body(rope element) for each boxStart/boxEnd
	 * 
	 * @param shape : each shape of rope..
	 * @author Sub7_User
	 * @version 0.5 , 01/11/2007 : 14.22
	 */
	private void buildRopeShape(RopeShape shape) 
	{
		float radiusElementRope = 1.11f;  // (1.11) / 1.81
		float mRope = 0.00f;
		float fElement = 0.8f;
		int spElement = 0; //(8) / 15
		float jointRelax = 0.8f; // Default = 1f (0.8)
		Body boxStart = null;
		Body boxEnd = null;		
		
		if(shape.getStartIndex() != null)
		{
			for(int i =0;i<this.commonShape.size();i++)
			{
				if(this.commonShape.get(i) == shape.getStartIndex())
				{
					boxStart = this.allBody.get(i);
				}
			}
		}
		
		if(shape.getEndIndex() != null)
		{
			for(int i =0;i<this.commonShape.size();i++)
			{
				if(this.commonShape.get(i) == shape.getEndIndex())
				{
					boxEnd = this.allBody.get(i);
				//	Body b = new Body(new )
				}
			}
		}		
	
				
		mRope = setmRope(boxStart,boxEnd);
		System.out.println("mRope "+mRope);
		ArrayList<Point2D> listLine_raw = AlgorTools.ShapeConvert.convertLine2DtoPoint2D(shape.getShape());
		
		// call arrangePoint() to add more pt if received pt is not really closed
		// together enough
	
		ArrayList<Point2D> listLine = arrangePoint(listLine_raw);
		
		
		ArrayList<Body> listeRope = new ArrayList<Body>();
		ArrayList<RopeJoint> listRopeJoint = new ArrayList<RopeJoint>();
		if(boxStart != null) // remove the pt which is inside the boxStart
		{
			CommonShape c = shape.getStartIndex();
			while(c.getPolygon().contains(listLine.get(0)))
			{
				listLine.remove(0);				
			}
		}
		if(boxEnd != null) // remove the pt which is inside the boxEnd
		{
			CommonShape c = shape.getEndIndex();
			while(c.getPolygon().contains(listLine.get(listLine.size()-1)))
			{
				listLine.remove(listLine.size()-1);				
			}
		}
		spElement = setSpElement(listLine.size());
		int count = 0;
		for(int i=0;i<listLine.size();i++)
		{			
		//	System.out.println("R: " + i+" "+(float) listLine.get(i).getX() + " " + (float)listLine.get(i).getY());
		
			if(i == 0) // first element
			{
				Body b = new Body(new Circle(radiusElementRope),mRope);
				float x = (float) listLine.get(i).getX();
				float y = (float )listLine.get(i).getY();
				b.setPosition(x, y);
				b.setFriction(fElement);
				listeRope.add(b); // add to the list of rope elements.
				
				count++; // count the number of body on arraylist
				
			}
			else if(i%spElement == 0 && i != listLine.size() -1)// not the last pt
			{				
				Body b = new Body(new Circle(radiusElementRope),mRope);
				float x = (float) listLine.get(i).getX(); // get only 1 pt from every 8 pt
				float y = (float )listLine.get(i).getY();// get only 1 pt from every 8 pt
				b.setPosition(x , y);
				b.setFriction(fElement);
				listeRope.add(b);
								
				Body bBefore = (Body) listeRope.get(count - 1);
				float oldX = bBefore.getPosition().getX();
				float oldY = bBefore.getPosition().getY();
			//	RopeJoint j = new RopeJoint(b,bBefore,new Vector2f(oldX,oldY));				
				RopeJoint j = new RopeJoint(b,bBefore,new Vector2f(oldX,oldY));	
				j.setRelaxation(jointRelax);
				
				listRopeJoint.add(j);
				count++;// count the number of body on arraylist				
			}			
			else if(i == listLine.size() -1) // add last pt.
			{				
				int mid = spElement/2; // mid value of spElement
			//	System.out.println(i%spElement+ " << >> "+ mid);
				if(i%spElement > mid || i%spElement == 0) // if last element % by spElement is more than mid value >> add pt.
				{
					Body b = new Body(new Circle(radiusElementRope),mRope);
					float x = (float) listLine.get(listLine.size() -1).getX(); // get only 1 pt from every 8 pt
					float y = (float )listLine.get(listLine.size() -1).getY();// get only 1 pt from every 8 pt
					b.setPosition(x , y);
					b.setFriction(fElement);
					listeRope.add(b);				
					
					Body bBefore = (Body) listeRope.get(count - 1);
					float oldX = bBefore.getPosition().getX();
					float oldY = bBefore.getPosition().getY();
					//RopeJoint j = new RopeJoint(b,bBefore,new Vector2f(oldX,oldY));				
					RopeJoint j = new RopeJoint(b,bBefore,new Vector2f(oldX,oldY));
					j.setRelaxation(jointRelax);
					
					listRopeJoint.add(j);
					count++;// count the number of body on arraylist
				}				
			}
			
		}	// end of for loop	
		
		
		if(boxStart != null)
		{			
			Body eRopeFirst = (Body)listeRope.get(0);
			
			float x = eRopeFirst.getPosition().getX();
			float y = eRopeFirst.getPosition().getY();
			Line2D l1 = new Line2D.Double();
			// l1, ..p1 >> first rope element, p2 >> boxStart position
			l1.setLine(eRopeFirst.getPosition().getX(), eRopeFirst.getPosition().getY(),
					boxStart.getPosition().getX(), boxStart.getPosition().getY());
			Point2D p1 = findNextPt(l1,spElement/2);
//			 adjust the boxStart position
			boxStart.setPosition((float)p1.getX(),(float) p1.getY());
			RopeJoint jSt = new RopeJoint(boxStart, eRopeFirst, new Vector2f(x,y));
			allJoint.add(jSt);
			for(int i=0;i<listeRope.size();i++) // avoid rope contacts with the boxStart
			{
				boxStart.addExcludedBody(listeRope.get(i));
			}
		}
		
		if(boxEnd != null)
		{			
			Body eRopeEnd = (Body)listeRope.get(listeRope.size()-1);
			
			float x = eRopeEnd.getPosition().getX();
			float y = eRopeEnd.getPosition().getY();
			Line2D l1 = new Line2D.Double();
			// l1, ..p1 >> last rope element, p2 >> boxEnd position
			l1.setLine(eRopeEnd.getPosition().getX(), eRopeEnd.getPosition().getY(),
					boxEnd.getPosition().getX(), boxEnd.getPosition().getY());
			Point2D p1 = findNextPt(l1,spElement/2);
		
			// adjust the boxEnd position
			boxEnd.setPosition((float)p1.getX(),(float) p1.getY()); 
			
			RopeJoint jEn = new RopeJoint(boxEnd, eRopeEnd, new Vector2f(x,y));
			allJoint.add(jEn);
			for(int i=0;i<listeRope.size();i++) // avoid rope to contacts with the boxEnd
			{
				boxEnd.addExcludedBody(listeRope.get(i));
			}
		}
		///////////// ADD TO PULBIC ARRAYLIST ///////////////eRopeFirst
		for(int i = 0;i<listeRope.size();i++)
		{
			allBody.add(listeRope.get(i));
		}
		for(int i = 0;i<listRopeJoint.size();i++)
		{
			allJoint.add(listRopeJoint.get(i));
		}
	}
	
	/**
	 * This method will add more pts if any 2 pts are too far
	 * if any 2 pts are more than 1 pixels far >> add more pts between them until every pt are 1 pixels far
	 * 
	 * @param listLine_raw
	 * @return
	 */
	private ArrayList<Point2D> arrangePoint(ArrayList<Point2D> listLine_raw) {
		
		ArrayList<Point2D> ret = new ArrayList<Point2D>();
		for(int i = 0;i<listLine_raw.size();i++)
		{
			if(i == 0) // first element
			{
				ret.add(listLine_raw.get(i));
			}
			else
			{
				Line2D l = new Line2D.Double();
				l.setLine(listLine_raw.get(i-1), listLine_raw.get(i));
				double d = AlgorTools.LineProperty.findMagnitudeOfLine(l);
				if(d <= 1.0) // those 2 pt are closed enough
					ret.add(listLine_raw.get(i));
				else
				{					
					
					while(true)// add pt until every pt are 1 px distance
					{
						Line2D inner = new Line2D.Double();
						
						inner.setLine(ret.get(ret.size()-1),listLine_raw.get(i));
						if(AlgorTools.LineProperty.findMagnitudeOfLine(inner) >= 1 )
						{
							Point2D p = addPoints(inner);
							ret.add(p); // add the pt between any 2 pts
						
						}
						else
						{
							ret.add(listLine_raw.get(i)); // add the end pts of any 2 pt
							break;
						}
					}
				}
				
			}
		}
		
		return ret;
	}
	
	/**
	 * This method will return pt which its distance from pt2 in Line2D l is >= 1 pixels
	 * @param l
	 * @return
	 */
	private Point2D addPoints(Line2D l)
	{
		Point2D p = new Point2D.Double();	
		if(l.getX2() > l.getX1()) // writing left >> right
		{
			double m = ( l.getY2() - l.getY1() ) / (l.getX2() - l.getX1());		
			double c = l.getY1() - ( m * l.getX1());
			double x = l.getX1() + 0.1;
			while(true)
			{
				double y =  ( m * x )+ c;				
				Line2D l1 = new Line2D.Double();
				l1.setLine(x, y, l.getX1(), l.getY1()); // compare the distance with the first pt
				double dist = AlgorTools.LineProperty.findMagnitudeOfLine(l1);
				if(dist >= 1)
				{
					p.setLocation(x, y);
					break;
				}
				else
				{
					x = x + 0.1;
				}
			}
		}
		else if(l.getX1() > l.getX2()) // writing right >> left
		{
			double m = ( l.getY2() - l.getY1() ) / (l.getX2() - l.getX1());		
			double c = l.getY1() - ( m * l.getX1());
			double x = l.getX1() - 0.1;
			while(true)
			{
				double y =  ( m * x )+ c;				
				Line2D l1 = new Line2D.Double();
				l1.setLine(x, y, l.getX1(), l.getY1()); // compare the distance with the first pt
				double dist = AlgorTools.LineProperty.findMagnitudeOfLine(l1);
				if(dist >= 1)
				{
					p.setLocation(x, y);
					break;
				}
				else
				{
					x = x - 0.1;
				}
			}
		}
		else if(l.getX1() == l.getX2())
		{
			if(l.getY1() < l.getY2())		// write up >> down	
				p.setLocation(l.getX1(),l.getY1()+1);
			else if(l.getY2() < l.getY1()) // write down >> up
				p.setLocation(l.getX1(),l.getY1()-1);
		}
		

		return p;
	}
	
	/**
	 *
	 * @author Sub7_User
	 * @version 0.1 , 25/08/2007 : 14.22
	 * @version 0.2 , 22/10/2007 : 14.22
	 * @param mBoxL >> Box start
	 * @param mBoxR >> Box End
	 * @return (float) mass of each element of the rope
	 */
	private float setmRope(Body boxStart,Body boxEnd) {
		float mBoxL = 0f;
		float mBoxR = 0f;
		
		float mRope = 0.0f;
		float BASE_mRope = 0.05f;
		float BASE_mBox = 10f;
		
		if(boxStart != null)
			mBoxL = boxStart.getMass();
		if(boxEnd != null)
			mBoxR = boxEnd.getMass();
		
		if(mBoxL == 0 || mBoxR == 0) // boxStart = null , boxEnd = null
		{
			mRope = BASE_mRope;
		}		
		else
		{
			if(mBoxL < mBoxR)
			{
				mRope = (mBoxL/BASE_mBox) * BASE_mRope;
			}
			else
			{
				mRope = (mBoxR/BASE_mBox) * BASE_mRope;
			}
	
		}
	    return mRope;
	}
	
	/**
	 * This method used to find the appropriate pts for the boxStart/boxEnd
	 * calculate from the distance between 
	 * 
	 * first elelment , boxStart  OR
	 * last element , boxEnd
	 * 
	 * With spElement
	 * @param l
	 * @param spElement
	 * @return
	 */
	private Point2D findNextPt(Line2D l,float spElement)
	{
		Point2D p = new Point2D.Double();	
		if(l.getX2() > l.getX1()) // writing left >> right
		{
			double m = ( l.getY2() - l.getY1() ) / (l.getX2() - l.getX1());		
			double c = l.getY1() - ( m * l.getX1());
			double x = l.getX2() + 0.1;
			while(true)
			{
				double y =  ( m * x )+ c;				
				Line2D l1 = new Line2D.Double();
				l1.setLine(x, y, l.getX2(), l.getY2()); // compare the distance with the first pt
				double dist = AlgorTools.LineProperty.findMagnitudeOfLine(l1);
				//System.out.println("dist1 >> "+dist);
				if(dist >= spElement)
				{
					p.setLocation(x, y);
					break;
				}
				else
				{
					x = x + 0.1;
				}
			}		
			
		}
		else if(l.getX1() > l.getX2()) // writing right >> left
		{
			double m = ( l.getY2() - l.getY1() ) / (l.getX2() - l.getX1());		
			double c = l.getY1() - ( m * l.getX1());
			double x = l.getX2() - 0.1;
			while(true)
			{
				double y =  ( m * x )+ c;				
				Line2D l1 = new Line2D.Double();
				l1.setLine(x, y, l.getX2(), l.getY2()); // compare the distance with the first pt
				double dist = AlgorTools.LineProperty.findMagnitudeOfLine(l1);
				//System.out.println("dist2 >> "+dist);
				if(dist >= spElement)
				{
					p.setLocation(x, y);
					break;
				}
				else
				{
					x = x - 0.1;
				}
			}				
			
		}
		else if(l.getX1() == l.getX2())
		{
			if(l.getY1() < l.getY2())		// write up >> down	
				p.setLocation(l.getX1(),l.getY1() + spElement/2);
			else if(l.getY2() < l.getY1()) // write down >> up
				p.setLocation(l.getX1(),l.getY1()- spElement/2);
		}
		

		return p;
	}
	
	/**
	 * This method is to find the appropriate spElement from the overall pts that
	 * users have written
	 * 		More wrote points >> More SpElement >> less rope elemet
	 * 		Less wrote points >> Less SpElement >> More rope element
	 * 		Due to... if using constant spElement
	 * 		The problem will occur if wrote pts is a lot >> a lot of rope element >> Slow !!
	 * 		
	 * @param i
	 * @return
	 */
	private int setSpElement(int i) {
		//System.out.println("len >> "+i);
			
		if(i < 400)
			return 10;
		else if(i < 600)
			return 12;
		else if(i < 1200)
			return 15;
		else
			return 18;
		//return 0;
	}



	private void shapeBuilder(Object obj)
	{
		
		if(obj instanceof TriangleShape)
		{
			buildTriangleBody((TriangleShape)obj);
		}
		
		else if(obj instanceof RectangleShape)
		{
			buildRectangleBody((RectangleShape)obj);
		}
		
		else if(obj instanceof EllipseShape)
		{
			buildEllipseBody((EllipseShape)obj);
		}
		
		else if(obj instanceof PolygonShape)
		{
			buildPolygonBody((PolygonShape)obj);
		}	
	}
}