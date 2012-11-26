/*
 * Phys2D - a 2D physics engine based on the work of Erin Catto. The
 * original source remains:
 * 
 * Copyright (c) 2006 Erin Catto http://www.gphysics.com
 * 
 * This source is provided under the terms of the BSD License.
 * 
 * Copyright (c) 2006, Phys2D
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
 *  * Neither the name of the Phys2D/New Dawn Software nor the names of 
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
package PhysicDrawing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.BodyList;
import net.phys2d.raw.Contact;
import net.phys2d.raw.FixedJoint;
import net.phys2d.raw.Joint;
import net.phys2d.raw.RopeJoint;
import net.phys2d.raw.World;
import net.phys2d.raw.collide.CircleCircleCollider;
import net.phys2d.raw.collide.PolygonCircleCollider;
import net.phys2d.raw.collide.PolygonPolygonCollider;

public class MainPhysicInterface extends AbstractDemo
{
	/** For keep the original of all shape*/
	private ArrayList<Body> allshape;
	/** For keep the orifinal of all joint*/
	private ArrayList<Joint> alljoint;
	
	public MainPhysicInterface(ArrayList<Body> allshape,ArrayList<Joint> alljoint,float gravityX,float gravityY,Image background)
	{
		super("SmartBoard Simulation");
		
		this.gravityX = gravityX;
		this.gravityY = gravityY;
		
		if(background != null)
		{
			this.background = new BufferedImage(background.getWidth(null),background.getHeight(null),BufferedImage.TYPE_INT_ARGB);
			
			Graphics g2 = this.background.getGraphics();
			// Position of image
			g2.drawImage(background,0,0,null);
			g2.dispose();	
		}
		
		this.allshape = allshape;
		this.alljoint = alljoint;
		this.startSimulation();
		this.start();
	}
	
	/**
	 * Notification that a key was pressed
	 * 
	 * @param c The character of key hit
	 */
	protected void keyHit(char c) 
	{
		
	}
	
	/**
	 * Running the physic animation
	 * 
	 */
	public static void startAnimation()
	{
		pause = false;
		physicFrame.current = "PLAY";
		if(physicFrame.pg != null)
		{
			physicFrame.pg.resume();		// pause the thread..
		}
	}
	
	/**
	 * Pause the physic animation
	 *
	 */
	public static void pauseAnimation()
	{
		pause = true;
		physicFrame.current = "PAUSE";
		if(physicFrame.pg != null)
		{
			physicFrame.pg.pause();		// pause the thread..
		}
	}
	
	/**
	 * Stop the physic animation
	 * 
	 */
	public static void stopAnimation()
	{
		needsReset = true;
		pause      = true;
		physicFrame.current = "STOP";
		if(physicFrame.pg != null)
		{
			physicFrame.pg.clearS();	
		}
		
	}

	@Override
	protected void init(World world) 
	{
		System.out.println("--------------------INIT WORLD--------------");
		this.bodyContainer.removeBodyall();
		
		for(int i=0;i<this.allshape.size();i++)
		{
			Body obj = (Body)(this.allshape.get(i).clone());
			this.bodyContainer.addBody(obj);
			this.world.add(obj);
		}
		
		for(int i =0;i<this.alljoint.size();i++)
		{
			Joint obj = (Joint)(this.alljoint.get(i).clone());
			
			// re set the joint connect for connect to the shape clone.
			for(int j=0;j<this.allshape.size();j++)
			{
							
				if(obj.getBody1()==this.allshape.get(j))
				{
					obj.setBody1(this.bodyContainer.getBody(j));
				}
				
				else if(obj.getBody2()==this.allshape.get(j))
				{
					obj.setBody2(this.bodyContainer.getBody(j));
				}
			}
			
			if(obj.getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.FixedJoint"))
			{
				obj = new FixedJoint(obj.getBody1(),obj.getBody2());
			}
			
			this.world.add(obj);
		}
		physicFrame.setContainer(bodyContainer);
	}
	
	
	@Override
	protected void update() 
	{
		// For check friction force
		runFriction(); 
		
		
		for(int i =0;i<this.allshape.size();i++)
		{
			BodyList listTouch = this.allshape.get(i).getTouching();
			if(listTouch.size() > 0) // it touches sth, otherwise it is in the air
			{
				double rad = ((double)listTouch.get(listTouch.size() - 1).getRotation());
		
				float Fs 	= this.allshape.get(i).getMs() * this.allshape.get(i).getMass()* 10 * (float)Math.cos(rad);
				float Fmg 	= this.allshape.get(i).getMass()* 10 * (float)Math.sin(rad);
				float diff 	= Fmg - Fs;
				
				// Has a little constraints
				// This function will be called to set only first time
				// Only if Fmg > Fs >> Move suddenly
				// Fmg < Fs >> It won't move forever
				if(diff > 0 && listTouch.size() > 0)// if cos(angle) less than mS >> it will never move
				{
					this.allshape.get(i).setMoving(true);
				}
				
				else 
				{ 
					this.allshape.get(i).setMoving(false);
					if(this.allshape.get(i).getVelocityMagnitude() > 0)
					{
						Vector2f v = new Vector2f(-this.allshape.get(i).getVelocity().getX(),-this.allshape.get(i).getVelocity().getY());
						this.allshape.get(i).adjustVelocity(v);
					}	
				}
			}
		}
		
		
		// Add to fix the body if it's was select (make the body set to the old position).
		if(this.mouse.getIndividualSelectIndex() != -1)
		{
			Body obj = bodyContainer.getBody(this.mouse.getIndividualSelectIndex());
			obj.setMovementBody(false);
			obj.setPosition((int)this.mouse.getStartDragPoint().getX(),(int)this.mouse.getStartDragPoint().getY());
		}
	}
	
	/**
	 * For add Force tho Physic Body
	 * @param head
	 * @param tail
	 * @param CM
	 * @param force
	 * @param body
	 */
	private void addForcetoBody(Point2D head,Point2D tail,Point2D CM,double force,Body body)
	{
		double m = -1 * (head.getY()-tail.getY())/( (head.getX()-tail.getX()) );
		//y=mx+c , m = sin/cos = tan
		//double c = tail.getY() - (m*tail.getX());
		double zeta = Math.atan(m); zeta = Math.toDegrees(zeta);
		double cos = Math.cos(zeta);
		double sin = Math.sin(zeta);
		
		double torque = setTorque(head,tail,CM,force);
		double fX = force*Math.abs(cos);double fY = force*Math.abs(sin);
		body.setTorque((float)torque);
		if(head.getX()>tail.getX()&&head.getY()>tail.getY()){
			
		}
		else if(head.getX()>tail.getX()&&head.getY()<tail.getY()){
			fY = -1*fY;
		}
		else if(head.getX()<tail.getX()&&head.getY()<tail.getY()){
			fX = -1*fX;
			fY = -1*fY;
		}
		else if(head.getX()<tail.getX()&&head.getY()>tail.getY()){
			fX= -1*fX;
		}
		body.addForce(new Vector2f((float)fX,(float)fY));
	}
	
	/**
	 * Set Touque to body
	 * @param head
	 * @param tail
	 * @param CM
	 * @param force
	 * @return
	 */
	private double setTorque(Point2D head,Point2D tail,Point2D CM,double force)
	{
		double m = -1 * (head.getY()-tail.getY())/( (head.getX()-tail.getX()) );
		double zeta = Math.atan(m);
		double cos = Math.cos(zeta);
		double sin = Math.sin(zeta);
		double torqueX = force*Math.abs(CM.getY()-head.getY())*Math.abs(cos);
		double torqueY = force*Math.abs(CM.getX()-head.getX())*Math.abs(sin);
		
		if(head.getX()>tail.getX()&&head.getY()>tail.getY()){/*/SE/*/
			if(head.getX()<CM.getX()&&head.getY()<CM.getY()){//Q2
				torqueY = -1*torqueY;
			}
			else if(head.getX()<CM.getX()&&head.getY()>CM.getY()){//Q3
				torqueY = -1*torqueY;
				torqueX = -1*torqueX;
			}
		}
		else if(head.getX()>tail.getX()&&head.getY()<tail.getY()){/*/NE/*/
			if(head.getX()>CM.getX()&&head.getY()>CM.getY()){//Q4
				torqueX = -1*torqueX; 
				torqueY = -1*torqueY;
			}
			else if(head.getX()<CM.getX()&&head.getY()>CM.getY()){//Q3
				torqueX = -1*torqueX;
			}
		}
		else if(head.getX()<tail.getX()&&head.getY()<tail.getY()){/*/NW/*/
			if(head.getX()>CM.getX()&&head.getY()<CM.getY()){//Q1
				torqueX = -1*torqueX;
				torqueY = -1*torqueY;
			}
			else if(head.getX()>CM.getX()&&head.getY()>CM.getY()){//Q4
				torqueY = -1*torqueY;
			}
		}
		else if(head.getX()<tail.getX()&&head.getY()>tail.getY()){/*/SW/*/

			if(head.getX()<CM.getX()&&head.getY()<CM.getY()){//Q2
				torqueX = -1*torqueX;
				torqueY = -1*torqueY;
			}
			else if(head.getX()>CM.getX()&&head.getY()<CM.getY()){//Q1
				torqueX = -1*torqueX;
			}
		}
		return torqueX+torqueY;
	}
	
	private void runFriction()
	{
		for(int i = 0;i<this.bodyContainer.getBodyContainerSize();i++)
		{
			Body bStatic = this.bodyContainer.getBody(i);				
			if(bStatic.isStatic()) // find the static body		
			{				
				ArrayList<Body> list = checkShapeTouching(bStatic); // find the touching bodies
			//	System.out.println("size "+list.size());
				if(list.size() > 0) // bStatic must have sth touch with it
				{
					
					for(int j = 0;j<list.size();j++) // for each touching bodies
					{
						Body b = list.get(j);					
						
						float Ms = 0;
						float Mk = 0;
						// switch Ms ...  pick the most highest one .. if equals >> choose bStatic
						if(bStatic.getMs() >= b.getMs()) 
							Ms = bStatic.getMs();
						else
							Ms = b.getMs();
//						 switch Mk ...  pick the most highest one .. if equals >> choose bStatic
						if(bStatic.getMk() >= b.getMk())
							Mk = bStatic.getMk();
						else
							Mk = b.getMk();
						// only positive radians
						// find the radians between b,bStatic , + only
						double rad = getRad(b,bStatic); 		
						
						if(rad != -1)
						{										
								float N = b.getMass()* 10 * (float)Math.cos(rad);								
								float Fs = Ms * N;
								float Fmg = b.getMass()* 10 * (float)Math.sin(rad);							
								float Fkx = 0;	
								float Fky = 0;
								float dx = 1; // direction - x 
								float dy = 1; // direction - y
								// if Ms = 0 >> There're will be only Fmg force >> object will run backward!!
								if(rad != 0)
								{
									if(Fmg > Fs)							
									{								
										//Fk = b.getMk() * b.getMass()*10 * (float)Math.cos(rad) ;							
										Fkx = Mk * N * (float)Math.cos(rad); // applied to x-axis
										Fky = Mk * N * (float)Math.sin(rad); // applied to y-axis
										
										// find direction.. 
										if(b.getVelocity().getX() != 0)
										 dx = b.getVelocity().getX() / Math.abs(b.getVelocity().getX());
										if(b.getVelocity().getY() != 0)
										 dy = b.getVelocity().getY() / Math.abs(b.getVelocity().getY());
//										
										Fkx =  -1 * dx * Fkx; // set the direction
										Fky =  -1 * dy * Fky;
//										 add 4 times >> for more realistic when add more Mk	
										b.addForce(new Vector2f(Fkx*4, Fky*4));
									
									}								
									else // force to stop it... can't move
									{	
										if(b.getVelocityMagnitude() > 0)
										{
											Vector2f v = new Vector2f(-b.getVelocity().getX(),-b.getVelocity().getY());
											b.adjustVelocity(v);
										}
										
									}	
								}
								else // rad == 0 .. horizontal
								{
									Fkx = Mk * N * (float)Math.cos(rad); // applied to x-axis
									Fky = Mk * N * (float)Math.sin(rad); // applied to y-axis
									
									// find direction.. 
									if(b.getVelocity().getX() != 0)
									 dx = b.getVelocity().getX() / Math.abs(b.getVelocity().getX());
									if(b.getVelocity().getY() != 0)
									 dy = b.getVelocity().getY() / Math.abs(b.getVelocity().getY());
//									
									Fkx =  -1 * dx * Fkx; // set the direction
									Fky =  -1 * dy * Fky;
//									 add 4 times >> for more realistic when add more Mk	
									b.addForce(new Vector2f(Fkx*4, Fky*4));
								}
								
								//print(b,rad,Fs,Fkx,Fky,Fmg);							
						}
					}					
				}
			}
		}	
	}
	/**
	 * For check the shape that are touching or not
	 * @param b
	 * @return
	 */
	private ArrayList<Body> checkShapeTouching(Body b)
	{
		ArrayList<Body> touching = new ArrayList<Body>();
		
		for(int i =0;i<world.getBodies().size();i++)
		{
			if(world.getBodies().get(i) != b)
			{
						if(b.getShape().getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Polygon")
						&& world.getBodies().get(i).getShape().getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Polygon"))
						{
							Contact[] contacts = new Contact[] {new Contact(), new Contact()};
							PolygonPolygonCollider collide = new PolygonPolygonCollider();
							if(collide.collide(contacts,b,world.getBodies().get(i)) > 0)
							{
								touching.add(world.getBodies().get(i));
							}
						}
						
						else if(b.getShape().getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Polygon")
						&& world.getBodies().get(i).getShape().getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Circle"))
						{
							Contact[] contacts = new Contact[] {new Contact(), new Contact()};
							PolygonCircleCollider collide = new PolygonCircleCollider();
							if(collide.collide(contacts,b,world.getBodies().get(i)) > 0)
							{
								touching.add(world.getBodies().get(i));
							}
						}
						
						else if(b.getShape().getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Circle")
						&& world.getBodies().get(i).getShape().getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Polygon"))
						{
							Contact[] contacts = new Contact[] {new Contact(), new Contact()};
							PolygonCircleCollider collide = new PolygonCircleCollider();
							if(collide.collide(contacts,world.getBodies().get(i),b) > 0)
							{
								touching.add(world.getBodies().get(i));
							}				
						}
						
						else if(b.getShape().getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Circle")
						&& world.getBodies().get(i).getShape().getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Circle"))
						{
							Contact[] contacts = new Contact[] {new Contact(), new Contact()};
							CircleCircleCollider collide = new CircleCircleCollider();
							if(collide.collide(contacts,b,world.getBodies().get(i)) > 0)
							{
								touching.add(world.getBodies().get(i));
							}		
						}
			}
			
		}
		
		
		return touching;
	}
	
	
	/**
	 * For get rad of the touching body
	 * @param b
	 * @param bStatic
	 * @return
	 */
	private double getRad(Body b, Body bStatic)
	{
			int minContactLength = 3;
				if(b.getShape().getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Polygon")
				&& bStatic.getShape().getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Polygon"))
				{
					Contact[] contacts = new Contact[] {new Contact(), new Contact()};
					PolygonPolygonCollider collide = new PolygonPolygonCollider();
					// if b , bStatic collides and its contacts more than 0
					if(collide.collide(contacts,b,bStatic) > 0)
					{									
						Line2D l1 = new Line2D.Double();
						l1.setLine(contacts[0].getPosition().getX(), 
								contacts[0].getPosition().getY(),
								contacts[1].getPosition().getX(), 
								contacts[1].getPosition().getY());
						// its length between 2 contacts must more than minContactLength
						if(AlgorTools.LineProperty.findMagnitudeOfLine(l1) > minContactLength)
						{
							double angle =  AlgorTools.LineProperty.getLineAngle(
									l1.getX1(), 
									l1.getY1(),
									l1.getX2(), 
									l1.getY2());
							// this angle measured from -X >> +X for only 180 degrees
							// and returns radians... 
							if(angle > 90)															
								return (180-angle) * Math.PI / 180;												
							else								
								return angle * Math.PI / 180;						
						}					
						else return -1;
						
					}
					else return -1;
					
				}
				
				else if(b.getShape().getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Polygon")
				&& bStatic.getShape().getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Circle"))
				{
					Contact[] contacts = new Contact[] {new Contact(), new Contact()};
					PolygonCircleCollider collide = new PolygonCircleCollider();
					if(collide.collide(contacts,b,bStatic) > 0)
					{
						Line2D l1 = new Line2D.Double();
						l1.setLine(contacts[0].getPosition().getX(), 
								contacts[0].getPosition().getY(),
								contacts[1].getPosition().getX(), 
								contacts[1].getPosition().getY());
						
						if(AlgorTools.LineProperty.findMagnitudeOfLine(l1) > minContactLength)
						{
							double angle =  AlgorTools.LineProperty.getLineAngle(
									l1.getX1(), 
									l1.getY1(),
									l1.getX2(), 
									l1.getY2());
							
							if(angle > 90)															
								return (180-angle) * Math.PI / 180;												
							else								
								return angle * Math.PI / 180;						
						}					
						else return -1;
					}
					else return -1;
				}
				
				else if(b.getShape().getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Circle")
				&& bStatic.getShape().getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Polygon"))
				{
					Contact[] contacts = new Contact[] {new Contact(), new Contact()};
					PolygonCircleCollider collide = new PolygonCircleCollider();
					if(collide.collide(contacts,bStatic,b) > 0)
					{
						Line2D l1 = new Line2D.Double();
						l1.setLine(contacts[0].getPosition().getX(), 
								contacts[0].getPosition().getY(),
								contacts[1].getPosition().getX(), 
								contacts[1].getPosition().getY());
					
						if(AlgorTools.LineProperty.findMagnitudeOfLine(l1) > minContactLength)
						{
							double angle =  AlgorTools.LineProperty.getLineAngle(
									l1.getX1(), 
									l1.getY1(),
									l1.getX2(), 
									l1.getY2());
							
							if(angle > 90)															
								return (180-angle) * Math.PI / 180;												
							else								
								return angle * Math.PI / 180;						
						}					
						else return -1;
					}		
					else return -1;
				}
				
				else if(b.getShape().getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Circle")
				&& bStatic.getShape().getClass().getCanonicalName().equalsIgnoreCase("net.phys2d.raw.shapes.Circle"))
				{
					Contact[] contacts = new Contact[] {new Contact(), new Contact()};
					CircleCircleCollider collide = new CircleCircleCollider();
					if(collide.collide(contacts,b,bStatic) > 0)
					{
						Line2D l1 = new Line2D.Double();
						l1.setLine(contacts[0].getPosition().getX(), 
								contacts[0].getPosition().getY(),
								contacts[1].getPosition().getX(), 
								contacts[1].getPosition().getY());
					
						if(AlgorTools.LineProperty.findMagnitudeOfLine(l1) > minContactLength)
						{
							double angle =  AlgorTools.LineProperty.getLineAngle(
									l1.getX1(), 
									l1.getY1(),
									l1.getX2(), 
									l1.getY2());
							
							if(angle > 90)															
								return (180-angle) * Math.PI / 180;												
							else								
								return angle * Math.PI / 180;						
						}					
						else return -1;
					}		
					else return -1;
				}
		return -1;
	}
	
}
