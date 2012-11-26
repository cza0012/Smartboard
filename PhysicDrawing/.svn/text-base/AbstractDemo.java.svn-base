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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import AlgorTools.LineProperty;
import AlgorTools.ShapeProperty;
import Container.ContainerAllShape;
import EnginePhysic.HandSelectState;
import IconMenu.IconMenu;
import TempObject.LocationOfString;
import UserInterface.Mouse;

import net.phys2d.math.MathUtil;
import net.phys2d.math.Matrix2f;
import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.BasicJoint;
import net.phys2d.raw.Body;
import net.phys2d.raw.BodyList;
import net.phys2d.raw.Contact;
import net.phys2d.raw.RopeJoint;
import net.phys2d.raw.SpringJoint;
import net.phys2d.raw.FixedJoint;
import net.phys2d.raw.Joint;
import net.phys2d.raw.JointList;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.*;
import net.phys2d.raw.strategies.QuadSpaceStrategy;

/**
 * A common demo box super class.
 * 
 * @author Kevin Glass
 */
public abstract class AbstractDemo extends Thread
{
	/** The frame displaying the demo */
	public static PhysicFrame physicFrame;
	/** The container for container the body of physic.*/
	protected ContainerAllShape bodyContainer = new ContainerAllShape();
	/** The Interface for interaction to user */
	protected Mouse mouse = new Mouse();
	
	/** The title of the current demo */
	protected String title;
	/** The world containing the physics model */
	protected World world = new World(new Vector2f(0.0f, 10.0f), 10, new QuadSpaceStrategy(20,5));
	
	/** True if the simulation is running */
	private boolean running = true;
	/** True if we should reset the demo on the next loop */
	protected static boolean needsReset;
	/** True if the animation is pausing */
	protected static boolean pause = false;
	
	/** The rendering strategy */
	private BufferStrategy strategy;
	
	/** True if we should render normals */
	private static boolean normals = true;
	/** True if we should render contact points */
	private boolean contacts = true;
	
	/** Background image*/
	protected BufferedImage background = null;
	
	/** Icon for show user Status*/
	private IconMenu[] statusIcon = new IconMenu[3];
	
	/** Gravity Force */
	protected float gravityX = 0.0f;
	protected float gravityY = 0.0f;
	
	/**
	 * Create a new demo
	 * 
	 * @param title The title of the demo
	 */
	public AbstractDemo(String title) 
	{
		this.title = title;
	}
	
	/** 
	 * Retrieve the title of the demo
	 * 
	 * @return The title of the demo
	 */
	public String getTitle() 
	{
		return title;
	}
	
	/**
	 * Notification that a key was pressed
	 * 
	 * @param c The character of key hit
	 */
	protected void keyHit(char c) 
	{
		System.out.println("Key hit");
	}
	
	/**
	 * Initialise the GUI 
	 */
	private void initGUI() 
	{
		mouse.setState(new HandSelectState());
		physicFrame = new PhysicFrame(bodyContainer,mouse,statusIcon);
		physicFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				running = false;
			}
		});
		
		physicFrame.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				keyHit(e.getKeyChar());
			}
			
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 27) {
					System.exit(0);
				}
			}
			
		});
		strategy = physicFrame.getBufferStrategy();
	}
	
	/**
	 * Start the simulation running
	 */
	public void startSimulation()
	{
		this.pause = true;
		initGUI();
		initDemo();
	}
	
	public void run()
	{
		float target = 1000 / 60.0f;
		float frameAverage = target;
		long lastFrame = System.currentTimeMillis();
		float yield = 10000f;
		float damping = 0.1f;
		
		long renderTime = 0;
		long logicTime = 0;
		
		while (running) 
		{
			// adaptive timing loop from Master Onyx
			long timeNow = System.currentTimeMillis();
			frameAverage = (frameAverage * 10 + (timeNow - lastFrame)) / 11;
			lastFrame = timeNow;
			
			yield+=yield*((target/frameAverage)-1)*damping+0.05f;
			for(int i=0;i<yield;i++) 
			{
				Thread.yield();
			}
			
			// render
			long beforeRender = System.currentTimeMillis();
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.white);
			// เป็นตัวที่ทำให้เราวาดได้ เท่าไหร่ 0 ,0 xwidth,ywidth
			g.fillRect(0,0,this.physicFrame.getWidth(),this.physicFrame.getHeight());
			
			draw(g);
			g.setColor(Color.black);
			g.drawString("FAv: "+frameAverage,10,50);
			g.drawString("FPS: "+(int) (1000 / frameAverage),10,70);
			g.drawString("Yield: "+yield,10,90);
			g.drawString("Arbiters: "+world.getArbiters().size(),10,110);
			g.drawString("Bodies: "+world.getBodies().size(),10,130);
			g.drawString("R: "+renderTime,10,150);
			g.drawString("L: "+logicTime,10,170);
			g.drawString("Energy: "+world.getTotalEnergy(),10,190);
			g.dispose();
			
			strategy.show();
			renderTime = System.currentTimeMillis() - beforeRender;
			
			// update data model
			long beforeLogic = System.currentTimeMillis();
			
			if(this.background == null || frameAverage < 50)
			{
				for (int i=0;i<4;i++) 
				{
					world.step();
				}	
			}
			
			else
			{
				for (int i=0;i<8;i++) 
				{
					world.step();
				}
			}
			
			logicTime = System.currentTimeMillis() - beforeLogic;
			update();
			
			while (pause == true)
			{
				if(pause == false)
				{
					yield = 10000f;
					frameAverage = target;
					lastFrame = System.currentTimeMillis();
					
					// Draw line Force
					drawLineForce(g);
					
					break;
				}

				if (needsReset == true) 
				{
					world.clear();
					initDemo();
					needsReset = false;
					frameAverage = target;
					yield = 10000f;
					lastFrame = System.currentTimeMillis();
					
					if(physicFrame.pg != null)
					{
						BodyList bodies = world.getBodies();
						physicFrame.pg.setBody(bodies.get(physicFrame.pg.getBodyIndex()));	
					}
					
					// Draw line Force
					drawLineForce(g);
					break;
				}
				try 
				{
					Thread.sleep(80);
					g = (Graphics2D) strategy.getDrawGraphics();
					g.setColor(Color.white);
					g.fillRect(0,0,this.physicFrame.getWidth(),this.physicFrame.getHeight());
					draw(g);
					
					// Draw line Force
					drawLineForce(g);
					strategy.show();
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Update the demo - just in case we want to add anything over
	 * the top
	 */
	protected void update() 
	{
		
	}
	
	/**
	 * Draw a specific contact point determined from the simulation.
	 * @param g The graphics context on which to draw
	 * @param contact The contact to draw
	 */
	protected void drawContact(Graphics2D g, Contact contact) 
	{
		int x = (int) contact.getPosition().getX();
		int y = (int) contact.getPosition().getY();
		if (contacts) {
			g.setColor(Color.blue);
			g.fillOval(x-3,y-3,6,6);
		}
		
		if (normals) {
			int dx = (int) (contact.getNormal().getX() * 10);
			int dy = (int) (contact.getNormal().getY() * 10);
			g.setColor(Color.darkGray);
			g.drawLine(x,y,x+dx,y+dy);
		}
	}
	
	/**
	 * Draw a body 
	 * 
	 * @param g The graphics contact on which to draw
	 * @param body The body to be drawn
	 */
	protected void drawBody(Graphics2D g, Body body) 
	{
		if (body.getShape() instanceof Box) 
		{
			drawBoxBody(g,body,(Box) body.getShape());
		}
		
		if (body.getShape() instanceof Circle) 
		{
			drawCircleBody(g,body,(Circle) body.getShape());
		}
		
		if (body.getShape() instanceof Line) 
		{
			drawLineBody(g,body,(Line) body.getShape());
		}
		
		if (body.getShape() instanceof Polygon) 
		{
			drawPolygonBody(g,body,(Polygon) body.getShape());
		}
		
		g.setColor(Color.BLACK);
	}
	
	/**
	 * Draw a polygon into the demo
	 * 
	 * @param g The graphics to draw the poly onto
	 * @param body The body describing the poly's position
	 * @param poly The poly to be drawn
	 */
	protected void drawPolygonBody(Graphics2D g, Body body, Polygon poly) 
	{
		ROVector2f[] verts = poly.getVertices(body.getPosition(), body.getRotation());
		
		java.awt.Polygon shape = new java.awt.Polygon();
		for(int i=0;i<verts.length;i++)
		{
			shape.addPoint((int) (0.5f + verts[i].getX()), (int) (0.5f + verts[i].getY()));
		}
		
		Color temp = g.getColor();
		
		if(body.getImage()!=null)
		{
			g.drawImage(body.getBufferedImage(),shape.getBoundingBox().x,shape.getBoundingBox().y,null);
		}
		else
		{
			// Draw internal color
			Color color = body.getColor();
			g.setColor(color);
			g.fill(shape);	
		}
		
		// Draw border color
		g.setColor(temp);
		for ( int i = 0, j = verts.length-1; i < verts.length; j = i, i++ ) 
		{			
			g.drawLine(
					(int) (0.5f + verts[i].getX()),
					(int) (0.5f + verts[i].getY()), 
					(int) (0.5f + verts[j].getX()),
					(int) (0.5f + verts[j].getY()));
		}
	}

	/**
	 * Draw a line into the demo
	 * 
	 * @param g The graphics to draw the line onto
	 * @param body The body describing the line's position
	 * @param line The line to be drawn
	 */
	protected void drawLineBody(Graphics2D g, Body body, Line line) 
	{
		Vector2f[] verts = line.getVertices(body.getPosition(), body.getRotation());
		g.drawLine(
				(int) verts[0].getX(),
				(int) verts[0].getY(), 
				(int) verts[1].getX(),
				(int) verts[1].getY());
	}
	
	/**
	 * Draw a circle in the world
	 * 
	 * @param g The graphics contact on which to draw
	 * @param body The body to be drawn
	 * @param circle The shape to be drawn
	 */
	protected void drawCircleBody(Graphics2D g, Body body, Circle circle) 
	{
		float x = body.getPosition().getX();
		float y = body.getPosition().getY();
		float r = circle.getRadius();
		float rot = body.getRotation();
		float xo = (float) (Math.cos(rot) * r);
		float yo = (float) (Math.sin(rot) * r);
		
		Color temp = g.getColor();
		
		// Draw internal color
		if(body.getImage()!=null)
		{
			AffineTransform atxV = AffineTransform.getRotateInstance(body.getRotation(),x, y);
			g.setTransform(atxV);
			g.drawImage(body.getBufferedImage(),(int)(x-r),(int)(y-r),null);
			AffineTransform atxV2 = AffineTransform.getRotateInstance(0,0, 0);
			g.setTransform(atxV2);
		}
			
		else
		{
			Color color = body.getColor();
			g.setColor(color);
			g.fillOval((int) (x-r),(int) (y-r),(int) (r*2),(int) (r*2));	
		}
		
		// Draw border color
		g.setColor(temp);
		g.drawOval((int) (x-r),(int) (y-r),(int) (r*2),(int) (r*2));
		g.drawLine((int) x,(int) y,(int) (x+xo),(int) (y+yo));
	}
	
	/**
	 * Draw a box in the world
	 * 
	 * @param g The graphics contact on which to draw
	 * @param body The body to be drawn
	 * @param box The shape to be drawn
	 */
	protected void drawBoxBody(Graphics2D g, Body body, Box box) {
		Vector2f[] pts = box.getPoints(body.getPosition(), body.getRotation());
		
		Vector2f v1 = pts[0];
		Vector2f v2 = pts[1];
		Vector2f v3 = pts[2];
		Vector2f v4 = pts[3];
		
		g.drawLine((int) v1.x,(int) v1.y,(int) v2.x,(int) v2.y);
		g.drawLine((int) v2.x,(int) v2.y,(int) v3.x,(int) v3.y);
		g.drawLine((int) v3.x,(int) v3.y,(int) v4.x,(int) v4.y);
		g.drawLine((int) v4.x,(int) v4.y,(int) v1.x,(int) v1.y);
	}

	/**
	 * Draw a joint 
	 * 
	 * @param g The graphics contact on which to draw
	 * @param j The joint to be drawn
	 */
	public void drawJoint(Graphics2D g, Joint j) 
	{
		if (j instanceof FixedJoint) 
		{
			FixedJoint joint = (FixedJoint) j;
			
			g.setColor(Color.red);
			float x1 = joint.getBody1().getPosition().getX();
			float x2 = joint.getBody2().getPosition().getX();
			float y1 = joint.getBody1().getPosition().getY();
			float y2 = joint.getBody2().getPosition().getY();
			
			g.drawLine((int) x1,(int) y1,(int) x2,(int) y2);
		}
		if (j instanceof BasicJoint) 
		{
			BasicJoint joint = (BasicJoint) j;
			
			Body b1 = joint.getBody1();
			Body b2 = joint.getBody2();
	
			Matrix2f R1 = new Matrix2f(b1.getRotation());
			Matrix2f R2 = new Matrix2f(b2.getRotation());
	
			ROVector2f x1 = b1.getPosition();
			Vector2f p1 = MathUtil.mul(R1,joint.getLocalAnchor1());
			p1.add(x1);
	
			ROVector2f x2 = b2.getPosition();
			Vector2f p2 = MathUtil.mul(R2,joint.getLocalAnchor2());
			p2.add(x2);
	
			g.setColor(Color.red);
			g.drawLine((int) x1.getX(), (int) x1.getY(), (int) p1.x, (int) p1.y);
			g.drawLine((int) p1.x, (int) p1.y, (int) x2.getX(), (int) x2.getY());
			g.drawLine((int) x2.getX(), (int) x2.getY(), (int) p2.x, (int) p2.y);
			g.drawLine((int) p2.x, (int) p2.y, (int) x1.getX(), (int) x1.getY());
		}
		
		if (j instanceof SpringJoint) 
		{
			SpringJoint joint = (SpringJoint) j;
			
			Body b1 = joint.getBody1();
			Body b2 = joint.getBody2();
			
			Matrix2f R1 = new Matrix2f(b1.getRotation());
			Matrix2f R2 = new Matrix2f(b2.getRotation());
			
			ROVector2f x1 = b1.getPosition();
			Vector2f p1 = MathUtil.mul(R1, joint.getLocalAnchor1());
			p1.add(x1);
			
			ROVector2f x2 = b2.getPosition();
			Vector2f p2 = MathUtil.mul(R2, joint.getLocalAnchor2());
			p2.add(x2);

			// TODO Paint spring to new picture.
			g.setColor(Color.RED);
			ArrayList<Line2D> lineOfSpring = AlgorTools.ShapeRegenerate.regenerateSpring((double) p1.x,
					(double) p1.y, (double) p2.getX(), (double) p2.getY(),joint);
			for (int n = 0; n < lineOfSpring.size(); n++) 
			{
				g.drawLine((int) lineOfSpring.get(n).getX1(),
						(int) lineOfSpring.get(n).getY1(), (int) lineOfSpring
								.get(n).getX2(), (int) lineOfSpring.get(n)
								.getY2());
			}
		}
		
		if (j instanceof RopeJoint) {
			RopeJoint joint = (RopeJoint) j;
			
			Body b1 = joint.getBody1();
			Body b2 = joint.getBody2();
	
			Matrix2f R1 = new Matrix2f(b1.getRotation());
			Matrix2f R2 = new Matrix2f(b2.getRotation());
	
			ROVector2f x1 = b1.getPosition();
			Vector2f p1 = MathUtil.mul(R1,joint.getLocalAnchor1());
			p1.add(x1);
	
			ROVector2f x2 = b2.getPosition();
			Vector2f p2 = MathUtil.mul(R2,joint.getLocalAnchor2());
			p2.add(x2);
	
			g.setColor(Color.black);
			g.drawLine((int) x1.getX(), (int) x1.getY(), (int) p1.x, (int) p1.y);
			g.drawLine((int) p1.x, (int) p1.y, (int) x2.getX(), (int) x2.getY());
			g.drawLine((int) x2.getX(), (int) x2.getY(), (int) p2.x, (int) p2.y);
			g.drawLine((int) p2.x, (int) p2.y, (int) x1.getX(), (int) x1.getY());
		}
	}
	
	/**
	 * Draw the Straingth that you want to add force.
	 * @param g
	 */
	protected void drawLineForce(Graphics2D g)
	{
		if(mouse.getSelectDrawing().size() >= 2)
		{
			g.setColor(Color.BLUE);
			java.awt.Polygon lineForce = new java.awt.Polygon();
			lineForce.addPoint((int)mouse.getSelectDrawing().get(0).getX(),(int)mouse.getSelectDrawing().get(0).getY());
			lineForce.addPoint((int)mouse.getSelectDrawing().get(mouse.getSelectDrawing().size()-1).getX(),(int)mouse.getSelectDrawing().get(mouse.getSelectDrawing().size()-1).getY());
			ArrayList<LocationOfString> resultLine = ShapeProperty.findDrawPointMagnitude(lineForce);
			g.draw(lineForce);
			
			g.setColor(Color.RED);
			if(resultLine.size() != 0)
			{
				g.drawString(resultLine.get(0).getValue(), (int) resultLine
						.get(0).getX(), (int) resultLine.get(0).getY());
				g.setColor(Color.RED);
				drawForceAngle(g 
						  ,new Point2D.Double((int)mouse.getSelectDrawing().get(0).getX(),(int)mouse.getSelectDrawing().get(0).getY())   
				          ,new Point2D.Double((int)mouse.getSelectDrawing().get(mouse.getSelectDrawing().size()-1).getX(),(int)mouse.getSelectDrawing().get(mouse.getSelectDrawing().size()-1).getY())) ;
			}
			g.setColor(Color.BLACK);
		}
	}
	
	/**
	 * For draw the icon for show user status
	 * @param g
	 */
	private void renderUserState(Graphics2D g) 
	{
		// draw user status
		for(int i = 0;i<statusIcon.length;i++)
		{
			if(statusIcon[i] != null)
			{
				g.drawImage(statusIcon[i].getImg(),statusIcon[i].getX(),statusIcon[i].getY(),null);
			}
		}
	}
	
	/**
	 * Render the menu Icon
	 * @param g
	 */
	private void renderIcon(Graphics2D g) 
	{	
		int frameHeight = this.physicFrame.getHeight();
		int frameWidth = this.physicFrame.getWidth();
		
		if(this.physicFrame.current.equals("STOP"))
		{ 
			IconMenu icon =  new IconMenu("./pic/stop.png",frameWidth - 80,30,"STOP");
			g.drawImage(icon.getImg(),icon.getX(),icon.getY(),null);
		}
		
		else if(this.physicFrame.current.equals("PAUSE"))
		{
			IconMenu icon =  new IconMenu("./pic/pause.png",frameWidth - 80,30,"PAUSE");
			g.drawImage(icon.getImg(),icon.getX(),icon.getY(),null);
		}
		
		else if(this.physicFrame.current.equals("PLAY"))
		{
			IconMenu icon =  new IconMenu("./pic/play.png",frameWidth - 80,30,"PLAY");
			g.drawImage(icon.getImg(),icon.getX(),icon.getY(),null);
		}
		
		IconMenu hand =  new IconMenu("./pic/hand01.png",frameWidth - 95,90,"STATUS");
		g.drawImage(hand.getImg(),hand.getX(),hand.getY(),null);
	}
	
	/**
	 * For draw Force angle
	 * @param g
	 * @param startPoint
	 * @param endPoint
	 */
	public static void drawForceAngle(Graphics2D g, Point2D startPoint,Point2D endPoint) 
	{
		g.setColor(Color.BLUE);
		Line2D tempForceVector = new Line2D.Double(startPoint, endPoint);
		Line2D tempXAxis = new Line2D.Double(startPoint.getX(), startPoint
				.getY(), startPoint.getX() - 100, startPoint.getY());
		Point2D controlPoint = new Point2D.Double(startPoint.getX() - 30,
				startPoint.getY());
		Point2D startCurvePoint = new Point2D.Double(startPoint.getX() - 20,
				startPoint.getY());
		Point2D endCurvePoint = new Point2D.Double(startPoint.getX() - 20,
				startPoint.getY());

		java.awt.Polygon controlPointLine = new java.awt.Polygon();
		controlPointLine.addPoint((int) startPoint.getX(), (int) startPoint
				.getY());
		java.awt.Polygon startPointLine = new java.awt.Polygon();
		startPointLine.addPoint((int) startPoint.getX(), (int) startPoint
				.getY());

		Stroke previousStroke = g.getStroke();
		g.draw(tempForceVector);
		Stroke stroke = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE,
				BasicStroke.JOIN_MITER, 10f, new float[] { 4.0f, 8.0f }, 0.0f);
		g.setStroke(stroke);
		if (startPoint.getY() < endPoint.getY()) {
			g.draw(new Line2D.Double(startPoint.getX(), startPoint.getY(),
					startPoint.getX(), startPoint.getY() + 100));
		} else {
			g.draw(new Line2D.Double(startPoint.getX(), startPoint.getY(),
					startPoint.getX(), startPoint.getY() - 100));
		}
		if (startPoint.getX() < endPoint.getX()) {
			tempXAxis = new Line2D.Double(startPoint.getX(), startPoint.getY(),
					startPoint.getX() + 100, startPoint.getY());
			startCurvePoint = new Point2D.Double(startPoint.getX() + 20,
					startPoint.getY());
			endCurvePoint = new Point2D.Double(startPoint.getX() + 20,
					startPoint.getY());
			controlPoint = new Point2D.Double(startPoint.getX() + 30,
					startPoint.getY());
			g.draw(tempXAxis);
		} else {
			g.draw(tempXAxis);
		}
		double degree = LineProperty.esstimate(LineProperty
				.findDegreeBetweenLines(tempXAxis, tempForceVector));
		
		g.setColor(Color.RED);
		
		char simble = 176;
		String stringAngle = " " + LineProperty.esstimate(degree)
				+ String.valueOf(simble);
		int stringLenght = stringAngle.length();
		int stringSize = stringLenght * 6;
		if(startPoint.getX() < endPoint.getX()){
			if (startPoint.getY() < endPoint.getY()) {
				g.drawString(stringAngle, (int) startPoint.getX() - stringSize, (int) startPoint
						.getY());
			} else {
				g.drawString(stringAngle, (int) startPoint.getX() - stringSize, (int) startPoint
						.getY() + 12);
			}
		}else{
			if (startPoint.getY() < endPoint.getY()) {
				g.drawString(stringAngle, (int) startPoint.getX(), (int) startPoint
						.getY());
			} else {
				g.drawString(stringAngle, (int) startPoint.getX(), (int) startPoint
						.getY() + 12);
			}
		}
		g.setStroke(previousStroke);
		g.setColor(Color.BLUE);
		
		controlPoint = ShapeProperty.pointRotation(degree/ 2, startPoint, endPoint,
				controlPoint, controlPointLine);
		startCurvePoint = ShapeProperty.pointRotation(degree, startPoint, endPoint,
				startCurvePoint, startPointLine);
		GeneralPath curve = new GeneralPath();
		curve.moveTo((float) startCurvePoint.getX(), (float) startCurvePoint
				.getY());
		curve.quadTo((float) controlPoint.getX(), (float) controlPoint.getY(),
				(float) endCurvePoint.getX(), (float) endCurvePoint.getY());
		g.draw(curve);
		
		g.setColor(Color.BLACK);
	}
	
	public void drawBackground(Graphics2D g) 
	{
		if(this.background != null)
		{
			g.drawImage(this.background,0,0,null);
		}
	}
	
	/**
	 * Draw the whole simulation
	 * 
	 * @param g The graphics context on which to draw
	 */
	protected void draw(Graphics2D g) 
	{	
		// Draw Background
		drawBackground(g);
		
		BodyList bodies = world.getBodies();
		for (int i=0;i<bodies.size();i++) 
		{
			if(mouse.getIndividualSelectIndex() == i)
			{
				g.setColor(Color.GREEN);
			}
			else
			{
				g.setColor(Color.BLACK);
			}
			Body body = bodies.get(i);
			drawBody(g, body);
		}
		
		JointList joints = world.getJoints();
		
		for (int i=0;i<joints.size();i++) 
		{
			Joint joint = joints.get(i);
			
			drawJoint(g, joint);
		}
		
		/*
		// Show the intersection point
		ArbiterList arbs = world.getArbiters();
		
		for (int i=0;i<arbs.size();i++) 
		{
			Arbiter arb = arbs.get(i);
			Contact[] contacts = arb.getContacts();
			int numContacts = arb.getNumContacts();
			for (int j=0;j<numContacts;j++) 
			{
				drawContact(g, contacts[j]);
			}
		}
		*/
		
		renderIcon(g);
		renderUserState(g);
		
		if(this.physicFrame.pg != null) // graph must not be null
		{
			if(!this.physicFrame.pg.isVisible()) // if graph is closed by user
			{
				this.physicFrame.pg.dispose();
				this.physicFrame.pg = null; // call resetGraph... remove all graph involved things.
			}
		}
		if(!this.physicFrame.isVisible()) // if physics frame is closed
		{
			if(this.physicFrame.pg != null) // graph is still showing..
			{
				this.physicFrame.pg.dispose();
				this.physicFrame.pg = null; 
			}
		}
	}
	
	/**
	 * Initialise the demo - clear the world
	 */
	public final void initDemo() 
	{
		world.clear();
		world.setGravity(gravityX,gravityY);
		init(world);
	}
	
	/**
	 * Should be implemented by the demo, add the bodies/joints
	 * to the world.
	 * 
	 * @param world The world in which the simulation is going to run
	 */
	protected abstract void init(World world);
	
}
