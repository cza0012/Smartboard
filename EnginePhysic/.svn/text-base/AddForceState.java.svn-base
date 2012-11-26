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
package EnginePhysic;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.Contact;
import net.phys2d.raw.shapes.Line;
import net.phys2d.raw.shapes.Shape;

import UserInterface.Mouse;
import AlgorTools.LineProperty;
import Container.ContainerAllShape;
import EngineDrawing.State;

public class AddForceState extends State
{
	public AddForceState()
	{
		this.stateName = "AddForce";
	}
	
	@Override
	public void handle(Mouse mouse) 
	{
		// Draw Line Force State
		if(mouse.getmouseLeft() == 1)
		{
			System.out.println("Change state to Draw Line Force state");
			mouse.setState(new DrawLineForceState());
		}
		
		// Simulation State
		else if(mouse.getmouseRight() == 1)
		{
			System.out.println("Change state to Simulation state");
			mouse.setState(new SimulationState());
		}
		
		// Open Menu State
		else if(mouse.getmouseMiddle() == 1)
		{
			System.out.println("Change state to Open Menu state");
			mouse.setState(new OpenMenuState());
		}
	}
	
	/**
	 * Add force to body.
	 * @param e
	 * @param bodyContainer
	 * @param mouse
	 */
	boolean isInside = false;
	Point2D intersectionPoint;
	public void runState(Point e,ContainerAllShape bodyContainer,Mouse mouse)
	{
		isInside = false;
		if(mouse.getSelectDrawing().size() >= 2)
		{
			mouse.getSelectDrawing().add(e);
			Line2D  line 	= new Line2D.Double(mouse.getSelectDrawing().get(0).getX(),mouse.getSelectDrawing().get(0).getY(),mouse.getSelectDrawing().get(mouse.getSelectDrawing().size()-1).getX(),mouse.getSelectDrawing().get(mouse.getSelectDrawing().size()-1).getY());			
			Point2D startP 	= new Point2D.Double(mouse.getSelectDrawing().get(0).getX(),mouse.getSelectDrawing().get(0).getY());
			double lengthOfforce = Math.sqrt(Math.pow((mouse.getSelectDrawing().get(0).getX()-mouse.getSelectDrawing().get(mouse.getSelectDrawing().size()-1).getX()), 2)+Math.pow((mouse.getSelectDrawing().get(0).getY()-mouse.getSelectDrawing().get(mouse.getSelectDrawing().size()-1).getY()),2));
			///
			String shapeToForce = "";
			Double near = Double.MAX_VALUE;
			int shapeIndex = -1;
			java.awt.Polygon nearestPolygon = null;
			java.awt.geom.Ellipse2D nearestCircle = null;
			///
			for(int i = 0;i<bodyContainer.getBodyContainerSize();i++){
				Shape shape = bodyContainer.getBody(i).getShape();
				
				if(shape.getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Polygon"))
				{
					java.awt.Polygon polygon = AlgorTools.ShapeConvert.convertBodytoPolygon(bodyContainer.getBody(i));
					intersectionPoint = AlgorTools.ShapeIntersection.checkLinePolygonNearestCollider(line, polygon,startP);
					
					///Check wheater if force was add from inside a circle and intersect a circle border///
					if(polygon.contains(new Point2D.Double(line.getX1(),line.getY1()))
							&&!polygon.contains(new Point2D.Double(line.getX2(),line.getY2()))){
						near = Double.MIN_VALUE;
						shapeIndex = i;
						nearestPolygon = polygon;
						shapeToForce = "polygon";
						isInside = true;
						break;
					}
					///Force source inside a polygon but no intersection poit, so no force to apply
					else if(polygon.contains(new Point2D.Double(line.getX1(),line.getY1()))
							&&polygon.contains(new Point2D.Double(line.getX2(),line.getY2()))){
						break;
					}
					else if(intersectionPoint != null)
					{
						double nearest = AlgorTools.ShapeIntersection.getNearest();
						if(nearest<near){
							near = nearest;
							shapeIndex = i;
							nearestPolygon = polygon;
							shapeToForce = "polygon";
						}					
					}
				}
			
				else if(shape.getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Circle"))
				{
					java.awt.geom.Ellipse2D circle = AlgorTools.ShapeConvert.convertCircleBodytoEllipse(bodyContainer.getBody(i));
					///Check wheater if force was add from inside a circle and intersect a circle border///
					if(circle.contains(new Point2D.Double(line.getX1(),line.getY1()))
							&&!(circle.contains(line.getX2(),line.getY2())))
					{
						near = Double.MIN_VALUE;
						shapeIndex = i;
						shapeToForce = "circle";
						nearestCircle = circle;
						break;
					}
					///Force source inside a circle but no intersection poit, so no force to apply///
					else if(circle.contains(new Point2D.Double(line.getX1(),line.getY1()))
							&&(circle.contains(line.getX2(),line.getY2()))){
						break;
					}
					Contact[] contacts = new Contact[] {new Contact(), new Contact()};
					Line  L = new net.phys2d.raw.shapes.Line((float)line.getX1(),(float)line.getY1(),(float)line.getX2(),(float)line.getY2());
					///Force applying from outside a circle///
					if( AlgorTools.ShapeIntersection.checkLineCircleCollider( contacts, new Body(L,1.0f), bodyContainer.getBody(i) ) )		
					{
						Line2D tmpL = new Line2D.Double((double)line.getX1(),
								(double)line.getY1(),(double)circle.getCenterX(),(double)circle.getCenterY());
						net.phys2d.raw.shapes.Circle cBody = (net.phys2d.raw.shapes.Circle) bodyContainer.getBody(i).getShape();
						Double tmpLength = AlgorTools.LineProperty.findMagnitudeOfLine(tmpL) - cBody.getRadius();
						if(tmpLength<near)
						{
							near = tmpLength;
							shapeIndex = i;
							shapeToForce = "circle";
							nearestCircle = circle;
						}
					}
				}
			}
			///End For Loop///
			if(shapeIndex!=-1)
			{
				if(shapeToForce.equals("polygon")){
					int[] nX = nearestPolygon.xpoints; 
					int[] nY = nearestPolygon.ypoints;
					ArrayList<Point2D> points = new ArrayList<Point2D>();
					for(int k=0;k<nearestPolygon.npoints;k++)
					{
						points.add(new Point2D.Double(nX[k],nY[k])); 
					}
					Point2D CM = AlgorTools.ShapeProperty.getCM(points);
					
					
					addForce(new Point2D.Double(line.getX2(),line.getY2()),
							new Point2D.Double(line.getX1(),line.getY1()) ,CM,lengthOfforce*50,bodyContainer.getBody(shapeIndex));
					double torque = setTorque(new Point2D.Double(line.getX2(),line.getY2())
					,new Point2D.Double(line.getX1(),line.getY1()),CM,lengthOfforce/10);
					bodyContainer.getBody(shapeIndex).setTorque((float)torque);
				}else{//Add force to an Ellipse
					addForce(new Point2D.Double(line.getX2(),line.getY2()),
							new Point2D.Double(line.getX1(),line.getY1()) ,new Point2D.Double(nearestCircle.getCenterX(),nearestCircle.getY())
							,lengthOfforce*100,bodyContainer.getBody(shapeIndex));
				}
			}
		}
		mouse.getSelectDrawing().clear();
	}
	

	private void addForce(Point2D head,Point2D tail,Point2D CM,double force,Body body)
	{
		Line2D tempForceVector = new Line2D.Double(head, tail);
		Line2D tempXAxis = new Line2D.Double(head.getX(), head
				.getY(), head.getX() - 100, head.getY());
		double zeta = LineProperty.esstimate(LineProperty.findDegreeBetweenLines(tempXAxis, tempForceVector));
		double cos = Math.cos(Math.toRadians(zeta));
		double sin = Math.sin(Math.toRadians(zeta));		
		double fX = Math.abs(force*cos);
		double fY = Math.abs(force*sin);
		
		System.out.println(fX+" "+fY);
		
		if(head.getX()>tail.getX()&&head.getY()>tail.getY())
		{
			
		}
		///North East
		else if(head.getX()>tail.getX()&&head.getY()<tail.getY()){
			fY = -1*fY;
			System.out.println("North East");
		}
		///North West
		else if(head.getX()<tail.getX()&&head.getY()<tail.getY()){
			fX = -1*fX;
			fY = -1*fY;
			System.out.println("North West");
		}
		///South West
		else if(head.getX()<tail.getX()&&head.getY()>tail.getY()){
			fX= -1*fX;
			System.out.println("South West");
		}
		///West
		else if(head.getX()<tail.getX()){
			fX= -1*fX;
			System.out.println("East");
		///South
		}else if(head.getY()>tail.getY()){
			System.out.println("South");
		}
		///North
		else if(head.getY()<tail.getY()){
			fY = -1 * fY;
			System.out.println("North");
		}
		
		// remove all force of this body
		body.setForce(-body.getForce().getX(),-body.getForce().getY());
		
		body.addForce(new Vector2f((float)fX,(float)fY));
	}
	
	private double setTorque(Point2D head,Point2D tail,Point2D CM,double force)
	{
		double m = -1 * (head.getY()-tail.getY())/( (head.getX()-tail.getX()) );
		double zeta = Math.atan(m);
		double cos = Math.cos(zeta);
		double sin = Math.sin(zeta);
		double torqueX = force*Math.abs(CM.getY()-intersectionPoint.getY())*Math.abs(cos);
		double torqueY = force*Math.abs(CM.getX()-intersectionPoint.getX())*Math.abs(sin);

		if(!isInside)
		{
			if(head.getX()>tail.getX()&&head.getY()>tail.getY()){/*/SE/*/
				if(intersectionPoint.getX()<CM.getX()&&intersectionPoint.getY()<CM.getY()){//Q2
					torqueY = -1*torqueY;
				}
				else if(intersectionPoint.getX()<CM.getX()&&intersectionPoint.getY()>CM.getY()){//Q3
					torqueY = -1*torqueY;
					torqueX = -1*torqueX;
				}
				else if(intersectionPoint.getX()<CM.getX()&&intersectionPoint.getY()>CM.getY()){//Q4
					
				}
			}
			else if(head.getX()>tail.getX()&&head.getY()<tail.getY()){/*/NE/*/
				if(intersectionPoint.getX()>CM.getX()&&intersectionPoint.getY()>CM.getY()){//Q4
					torqueX = -1*torqueX; 
					torqueY = -1*torqueY;
				}
				else if(intersectionPoint.getX()<CM.getX()&&intersectionPoint.getY()>CM.getY()){//Q3
					torqueX = -1*torqueX;
				}

			}
			else if(head.getX()<tail.getX()&&head.getY()<tail.getY()){/*/NW/*/
				if(intersectionPoint.getX()>CM.getX()&&intersectionPoint.getY()<CM.getY()){//Q1
					torqueX = -1*torqueX;
					torqueY = -1*torqueY;
				}
				else if(intersectionPoint.getX()>CM.getX()&&intersectionPoint.getY()>CM.getY()){//Q4
					torqueY = -1*torqueY;
				} 
			}
			else if(head.getX()<tail.getX()&&head.getY()>tail.getY()){/*/SW/*/

				if(intersectionPoint.getX()<CM.getX()&&intersectionPoint.getY()<CM.getY()){//Q2
					torqueX = -1*torqueX;
					torqueY = -1*torqueY;
				}
				else if(intersectionPoint.getX()>CM.getX()&&intersectionPoint.getY()<CM.getY()){//Q1
					torqueX = -1*torqueX;
				}
			}
			
		}
		else{
			if(head.getX()>tail.getX()&&head.getY()>tail.getY()){/*/SE/*/
				if( intersectionPoint.getX()>CM.getX()&&intersectionPoint.getY()>CM.getY() ){//Q4
					torqueX = -1*torqueX;
				}
				else if( intersectionPoint.getX()<CM.getX()&&intersectionPoint.getY()>CM.getY() ){//Q3
					torqueY = -1*torqueY;
					torqueX = -1*torqueX;
				}
			}
			else if(head.getX()>tail.getX()&&head.getY()<tail.getY()){/*/NE/*/
				if(intersectionPoint.getX()>CM.getX()&&intersectionPoint.getY()<CM.getY()){//Q1
					torqueY = -1*torqueY;
				}
				else if(intersectionPoint.getX()>CM.getX()&&intersectionPoint.getY()>CM.getY()){//Q4
					torqueX = -1*torqueX; 
					torqueY = -1*torqueY;
				}
				else if(intersectionPoint.getX()<CM.getX()&&intersectionPoint.getY()<CM.getY()){//Q2
					
				}
			}
			else if(head.getX()<tail.getX()&&head.getY()<tail.getY()){/*/NW/*/
				if(intersectionPoint.getX()<CM.getX()&&intersectionPoint.getY()<CM.getY()){//Q2
					torqueX = -1*torqueX;
				}
				else if(intersectionPoint.getX()>CM.getX()&&intersectionPoint.getY()<CM.getY()){//Q1
					torqueX = -1*torqueX; 
					torqueY = -1*torqueY;
				}
				else if(intersectionPoint.getX()>CM.getX()&&intersectionPoint.getY()>CM.getY()){//Q4
					
				}
			}
			else if(head.getX()<tail.getX()&&head.getY()>tail.getY()){/*/SW/*/
				if(intersectionPoint.getX()<CM.getX()&&intersectionPoint.getY()<CM.getY()){//Q2
					torqueX = -1*torqueX; 
					torqueY = -1*torqueY;
				}
				else if(intersectionPoint.getX()>CM.getX()&&intersectionPoint.getY()>CM.getY()){//Q4
					
				}
				else if( intersectionPoint.getX()<CM.getX()&&intersectionPoint.getY()>CM.getY() ){//Q3
					torqueY = -1*torqueY;
				}
			}
			
			
		}
		return (torqueX+torqueY)*50;	
	}
	
	
}
