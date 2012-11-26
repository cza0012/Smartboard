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
package CommonShape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;



import AlgorTools.CloneShape;
import AlgorTools.ShapeConvert;
import AlgorTools.ShapeProperty;
import SpecialShape.CrossShape;
import SpecialShape.SpecialFactory;

/**
 * Interface class for all of the Common Shape
 * @author Magic Board Team
 *
 */
public abstract class CommonShape implements Cloneable
{	
	protected Ellipse2D 			ellipse;
	protected Polygon 				shape;
	protected ArrayList<Ellipse2D> 	vertex;
	protected Point2D				cm;
	protected Point2D				rotatePoint;
	protected CrossShape    		cross;
	
	//Shape Property.
	protected String				name;
	protected float					mass   = 10.0f; //mass
	protected float					ms     = 0.2f; 	//Friction for ms
	protected float					mk     = 0.2f; 	//Friction for mk
	protected float					bounce = 0.0f;  //Bounce
	protected Color					color  = Color.WHITE;//Color internal shape
	
	// Theme Image
	protected String				imagePath	= "";
	protected Image					image  		= null;	 // Set image to object
	protected boolean				background  = false; // Set object to add at background
	protected BufferedImage			bufferImage = null;
	
	/**
	 * Check for shape is background or not. 
	 */
	public boolean isBackground() 
	{
		return background;
	}
	
	/**
	 * Set the shape background
	 *  if background == true
	 *      	1. Can't remove
	 *      	2. Can't transform
	 *      	3. Can't SelectGroup
	 *      	4. Can't rotate
	 *      	5. Can't Add Cross
	 * @param background
	 */
	public void setToBackground() 
	{
		this.background = true;
	}
	
	/**
	 *  Get the Shape Image
	 *  if image != null
	 *      	1. Can't remove
	 *      	2. Can't SelectGroup
	 *      	3. Can't rotate
	 *      	4. Can't Add Cross
	 * @return
	 */
	public Image getImage() 
	{
		return image;
	}
	
	/**
	 * Get image Path
	 * @return
	 */
	public String getImagePath()
	{
		return this.imagePath;
	}
	
	/**
	 * Get the buffer of the image
	 * @return
	 */
	public BufferedImage getBufferImage()
	{
		return this.bufferImage;
	}
	
	/**
	 * Set the shape image
	 * @param image
	 */
	public void setImage(String imageFileName) 
	{
		try 
		{
			this.imagePath 	= imageFileName;
			this.image 		= ImageIO.read(new File(imageFileName));
			
			this.bufferImage = new BufferedImage(this.image.getWidth(null),this.image.getHeight(null),BufferedImage.TYPE_INT_ARGB);
			Graphics g2 = bufferImage.getGraphics();
			// Position of image
			g2.drawImage(this.image,0,0,null);
			g2.dispose();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean canTranslate()
	{
		if(this.background == true)
		{
			return false;
		}
		return  true;
	}
	
	public boolean isNormalShape()
	{
		if(this.background == true || this.image != null)
		{
			return false;
		}
		return true;
	}
	/** End Theme Property*/
	
	
	
	/**
	 * Get the shape color
	 * @return
	 */
	public Color getColor() 
	{
		return color;
	}

	/**
	 * Set the shape color
	 * @param color
	 */
	public void setColor(Color color) 
	{
		this.color = color;
	}
	
	
	/**
	 * Get shape name
	 * @return
	 */
	public String getCommonShapeName()
	{
		return name;
	}
	
	/**
	 * Get the mole gravity of the shape.
	 * @return
	 */
	public float getMass() 
	{
		return mass;
	}

	/**
	 * Set the mole gravity of the shape.
	 * @param mg
	 */
	public void setMass(float mass) 
	{
		this.mass = mass;
	}

	/**
	 * Get the ms (ms is the friction value befor body movement)
	 * @return
	 */
	public float getMs()
	{
		return this.ms;
	}
	
	/**
	 * Get the mk (mk is the friction value after body movement)
	 * @return
	 */
	public float getMk()
	{
		return this.mk;
	}
	
	/**
	 * Set the ms (ms is the friction value befor body movement)
	 * @param ms
	 */
	public void setMs(float ms)
	{
		this.ms = ms;
	}
	
	/**
	 * Set the mk (mk is the friction value after body movement)
	 * @param mk
	 */
	public void setMk(float mk)
	{
		this.mk = mk;
	}
	
	/**
	 * Get the bounce value
	 * @return
	 */
	public float getBounce() 
	{
		return bounce;
	}
	
	/**
	 * Set the bounce value for make the energy absoub
	 * @param bounce
	 */
	public void setBounce(float bounce) 
	{
		this.bounce = bounce;
	}
	
	/**
	 * For generate Cross in the Shape for set the shape in static body.
	 * @param Line
	 */
	public void generateCross(ArrayList<Line2D> line)
	{
		this.cross = (CrossShape) SpecialFactory.createSpecialShape(SpecialFactory.CROSS,line);
	}
	
	/**
	 * Get the object croos that the object was contain
	 * @return ArrayList of Line2D.
	 */
	public ArrayList<Line2D> getLineCross()
	{
		return this.cross.getShape();
	}
	
	/**
	 * For get the center point of Cross
	 * @return
	 */
	public Point2D getCenterCross()
	{
		if(this.cross == null)
		{
			return null;
		}
		return AlgorTools.LineIntersection.getIntersection(this.cross.getShape().get(0),this.cross.getShape().get(1));
	}
	
	/**
	 * For remove Cross in the Shape for set the shape in dynamic body.
	 *
	 */
	public void removeCross()
	{
		this.cross = null;
	}
	
	/**
	 * Generate Crosss
	 * @param point
	 */
	public void generateCross(Point2D point)
	{
		ArrayList<Line2D> line = new ArrayList<Line2D>();
		line.add(new Line2D.Double((int)point.getX()-2,(int)point.getY()-2,(int)point.getX()+2,(int)point.getY()+2));
		line.add(new Line2D.Double((int)point.getX()-2,(int)point.getY()+2,(int)point.getX()+2,(int)point.getY()-2));
		this.cross = (CrossShape) SpecialFactory.createSpecialShape(SpecialFactory.CROSS, line);
	}
	
	
	/**
	 * Get the Polygon object.
	 * 
	 * @return The object of the Polygon.
	 */
	public Polygon getPolygon()
	{
		return this.shape;
	}
	
	/**
	 * Set the Polygon object.
	 * @param shape
	 */
	public void setPolygon(Polygon shape)
	{
		this.vertex.clear();
		this.shape = shape;
		ArrayList<Point2D> point = getPointPolygon();
		this.cm = AlgorTools.ShapeProperty.getCM(point);
		this.setRotatePoint();
		for(int i =0;i<point.size();i++)
		{	
			this.vertex.add(i,new Ellipse2D.Double((int)point.get(i).getX()-4, (int)point.get(i).getY()-4, 8, 8));
		}
	}

	/**
	 * Get the all point of the polygon
	 * 
	 * @return Arraylist of object in Point2D.
	 */
	public ArrayList<Point2D> getPointPolygon()
	{
		ArrayList<Point2D> point = new ArrayList<Point2D>();
		
		int[] xp = this.shape.xpoints;
		int[] yp = this.shape.ypoints;
		
		for(int i =0;i<(this.shape.npoints);i++)
		{
			point.add(new Point2D.Double(xp[i],yp[i]));
		}
		return point;
	}
	
	/**
	 * Get the center of mass of the polygon
	 * @return Center of mass in Point2D coordinate.
	 */
	public Point2D getCM()
	{
		return this.cm;
	}
	
	/**
	 * For set the rotate point of shape.
	 *
	 */
	protected void setRotatePoint()
	{
		this.rotatePoint = ShapeProperty.pointOfRotatePolygon(this.getPolygon(),this.getCM());
	}
	
	/**
	 * Get the rotate Point2D
	 * @return
	 */
	public Point2D getRotationPoint()
	{
		return this.rotatePoint;
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
	 * Get the scale shape (Rectangle).
	 * @return
	 */
	public Rectangle2D getScaleShape()
	{	
		return new Rectangle2D.Double((double)(this.rotatePoint.getX() - 4),(double)(this.rotatePoint.getY() - 15),(int)8,(int)8);
	}
	
	/**
	 * Get the circle at the corner of the polygon.
	 * @return Array of Ellipse2D at the vertex point. 
	 */
	public ArrayList<Ellipse2D> getPolygonVertex()
	{
		return this.vertex;
	}
	
	/**
	 * Check that point that was in the circle of at the vertex of Polygon.
	 * @param point
	 * @return true if in the circle or flase if not.
	 */
	public boolean IsSelectVertex(Point point)
	{
		for(int i =0 ;i<this.vertex.size();i++)
		{
			if(this.vertex.get(i).contains(point))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get the vertex index of the polygon. if not have return -1
	 * @param point
	 * @return vertex index that you was selected
	 */
	public int getSelectVertex(Point point)
	{
		for(int i =0 ;i<this.vertex.size();i++)
		{
			if(this.vertex.get(i).contains(point))
			{
				return i;
			}
		}
		
		return -1;
	}
		
	
	/**
	 * Check the polygon was fixed or not.
	 * @return true if fixed or false if none fixed.
	 */
	public boolean isFixed()
	{
		if(this.cross == null && isBackground() == false)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * Translate the polygon from x and y pixels.
	 * @param x
	 * @param y
	 */
	public void setTranslate(int x,int y)
	{
		this.shape.translate(x,y);
		ArrayList<Point2D> P = getPointPolygon();
		for(int i=0;i<this.vertex.size();i++)
		{
			this.vertex.set(i, new Ellipse2D.Double((int)P.get(i).getX()-4, (int)P.get(i).getY()-4, 8, 8));
		}
		this.cm.setLocation(this.cm.getX()+x , this.cm.getY()+y);
		if(this.cross!= null)
		{
			this.cross.setTranslate(x, y);
		}
		this.setRotatePoint();
	}
	
	/**
	 * Translate the selected vertex(index) of the polygon form x and y pixels. 
	 * @param index
	 * @param x
	 * @param y
	 */
	public void translateVertex(int index,int x,int y)
	{
		int[] xpts = this.shape.xpoints;
        int[] ypts = this.shape.ypoints;
        xpts[index] = x;
        ypts[index] = y;
        this.shape.invalidate();
		this.vertex.set(index,new Ellipse2D.Double((int)x-4, (int)y-4, 8, 8));
		this.cm = AlgorTools.ShapeProperty.getCM(getPointPolygon());
		this.setRotatePoint();
	}
	
	/**
	 * For re scale the Shape.
	 *
	 */
	public Polygon scalling(Point2D p1, Point2D p2)
	{
		Shape objectShape = (Shape) (Polygon) AlgorTools.CloneShape.clonePolygon(shape);

		Point2D cm = ShapeProperty.getCM(ShapeConvert.convertPolygontoPoint2D(shape));
		AffineTransform scale = new AffineTransform();
		double scaleValue = 1 + ((p1.getY() - p2.getY()) * 0.01);
		
		if( 0.25 < scaleValue)
		{
			if(scaleValue < 4)
			{
				scale.scale(scaleValue, scaleValue);
			}
			else
			{
				scale.scale( 4, 4);
			}
		}
		else
		{
			scale.scale( 0.25, 0.25);
		}
		
		Shape tempShape = scale.createTransformedShape(objectShape);
		FlatteningPathIterator pi = new FlatteningPathIterator(tempShape
				.getPathIterator(null), 100);
		Polygon polygon = ShapeConvert.convertShapetoPolygon(pi);
		Point2D cmnew = ShapeProperty.getCM(ShapeConvert
				.convertPolygontoPoint2D(polygon));
		polygon.translate((int) -(cmnew.getX() - cm.getX()), (int) -(cmnew
				.getY() - cm.getY()));
		return polygon;
	}
	
	
	/**
	 * Rotate the Shape
	 * @return the clone of the shape that was rotated.
	 *
	 */
	public Shape rotate(Point2D p1, Point2D p2)
	{	
		ArrayList<Point2D> point2DList = new ArrayList<Point2D>();
		Polygon currentPolygon = (Polygon) AlgorTools.CloneShape.clonePolygon(shape);
		int[] aryX = currentPolygon.xpoints;
		int[] aryY = currentPolygon.ypoints;

		for (int n = 0; n < currentPolygon.npoints; n++) 
		{
			point2DList.add(new Point2D.Double(aryX[n], aryY[n]));
		}

		Point2D cm = ShapeProperty.getCM(point2DList);

		Line2D line1 = new Line2D.Double(p1, cm);
		Line2D line2 = new Line2D.Double(p2, cm);

		double degree = AlgorTools.LineProperty.findDegreeBetweenLines(line1, line2);
		System.out.println("degree:" + AlgorTools.LineProperty.esstimate(degree));
		AffineTransform atxV = null;
		double x180 = AlgorTools.LineProperty.findYValueFromXValue(line1, p2.getY());
		
		if (x180 > p2.getX()) 
		{
			atxV = AffineTransform.getRotateInstance(Math
					.toRadians(0 - AlgorTools.LineProperty.esstimate(degree)), cm
					.getX(), cm.getY());
			System.out.println("Anti Clock wise p1X = " + p1.getX()
					+ ", p2X = " + p2.getX());
		}
		
		else 
		{
			atxV = AffineTransform.getRotateInstance(Math
					.toRadians(0 + AlgorTools.LineProperty.esstimate(degree)), cm
					.getX(), cm.getY());
			System.out.println("Clock wise p1X = " + p1.getX() + ", p2X = "
					+ p2.getX());
		}

		return atxV.createTransformedShape(currentPolygon);
	}
	
	/**
	 * Clone the Body to Object.
	 */
    public Object clone() 
    {	
        try 
        {
        	CommonShape obj = (CommonShape)super.clone();
        	obj.cm			= (Point2D) this.cm.clone();
        	
        	if(this.ellipse != null)
        	{
        		obj.ellipse		= (Ellipse2D) this.ellipse.clone();
        	}
        	if(this.cross != null)
        	{
        		obj.cross		=  (CrossShape) this.cross.clone();
        	}
        	obj.rotatePoint	= (Point2D) this.rotatePoint.clone();
        	obj.shape		= CloneShape.clonePolygon(this.shape);
        	obj.vertex  	= new ArrayList<Ellipse2D>();
        	for(int i =0;i<this.vertex.size();i++)
        	{
        		obj.vertex.add((Ellipse2D) this.vertex.get(i).clone());
        	}
            return obj;	
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }
}
