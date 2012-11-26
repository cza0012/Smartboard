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
package IconMenu;

import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import SpecialShape.ArrowShape;
import SpecialShape.CrossShape;
import SpecialShape.JointShape;
import SpecialShape.RopeShape;
import TempObject.TempFixJointShape;

import AlgorTools.ShapeIntersection;
import CommonShape.CommonShape;
import CommonShape.CommonShapeFactory;
import CommonShape.EllipseShape;
import CommonShape.PolygonShape;

public class RecycleBin 
{
	private Image img;
	private int _x;
	private int _y;
	
	public RecycleBin(String fileName,int x, int y)
	{
		try 
		{
			img = ImageIO.read(new File(fileName));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		_x = x;
		_y = y;
	}
	
	public int getX()
	{
		return _x; 
	}
	
	public int getY()
	{
		return _y;
	}
	
	/**
	 * The method returns
	 * TRUE >> Mouse is inside the this.img IconMenu, 
	 * FALSE >> Mouse is outside the this.img IconMenu
	 * @param e : Current MouseEvent
	 * @return
	 */
	public boolean contains(MouseEvent e)
	{
		return ( e.getX() > _x && e.getX() < (_x + img.getWidth(null)) &&
				 e.getY() > _y && e.getY() < (_y + img.getHeight(null)));
	}
	
	/**
	 * This method returns
	 * TRUE >> Shape obj is INSIDE or INTERSECT this.img IconMenu
	 * FALSE >> Shape obj is OUTSIDE this.img IconMenu
	 * @param obj : Current selected shape
	 * @return
	 */
	public boolean isIntersect(Object obj)
	{
		ArrayList<Point2D> pointList = new ArrayList<Point2D>();
		pointList.add(new Point2D.Double(_x, _y));
		pointList.add(new Point2D.Double(_x + img.getWidth(null), _y));
		pointList.add(new Point2D.Double(_x + img.getWidth(null), _y + img.getHeight(null)));
		pointList.add(new Point2D.Double(_x, _y + img.getHeight(null)));
		
		PolygonShape trunk = (PolygonShape)CommonShapeFactory.createCommonShape(CommonShapeFactory.POLYGON, pointList);
		
		if(obj.getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
		{
			EllipseShape commonShape = (EllipseShape)obj;
			if(ShapeIntersection.shapeIntersectIndividual(commonShape,trunk)
					|| AlgorTools.CheckShapeContainer.isPolygonContainEllipse( trunk.getPolygon(),commonShape.getEllipse()))
			{
				return true;
			}
		}
		
		else if(obj.getClass().getSuperclass().getCanonicalName().equalsIgnoreCase("CommonShape.CommonShape"))
		{
			CommonShape commonShape = (CommonShape)obj;
			if(ShapeIntersection.shapeIntersectIndividual(commonShape,trunk)
				|| AlgorTools.CheckShapeContainer.isPolygonContainPolygon( trunk.getPolygon(),commonShape.getPolygon())
				|| AlgorTools.CheckShapeContainer.isPolygonContainPolygon( commonShape.getPolygon(),trunk.getPolygon()))
			{
				return true;
			}
		}
		
		else if(obj.getClass().getCanonicalName().equalsIgnoreCase("java.awt.geom.Line2D.Double"))
		{
			Line2D line = (Line2D)obj;
			ArrayList<Line2D> lineList = new ArrayList<Line2D>();
			lineList.add(line);
			if(ShapeIntersection.getLinePolygonCollider(line,trunk.getPolygon()) != null
			|| AlgorTools.CheckShapeContainer.isPolygonContainLine(trunk.getPolygon(),lineList) == true)
			{
				return true;
			}
		}
		
		else if(obj.getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.CrossShape"))
		{
			CrossShape cross = (CrossShape)obj;
			ArrayList<Line2D> line = cross.getShape();
			Polygon polygon = new Polygon();
			polygon.addPoint(_x, _y);
			polygon.addPoint(_x + img.getWidth(null), _y);
			polygon.addPoint(_x + img.getWidth(null), _y + img.getHeight(null));
			polygon.addPoint(_x, _y + img.getHeight(null));
			if(polygon.contains(cross.getIntersectionPoint()) == true)
			{
				return true;
			}
			
			for(int i =0;i<line.size();i++)
			{
				if(ShapeIntersection.getLinePolygonCollider(line.get(i),trunk.getPolygon()) != null)
				{
					return true;
				}
			}
		}
		
		else if(obj.getClass().getCanonicalName().equalsIgnoreCase("TempObject.TempSpringShape"))
		{
			TempObject.TempSpringShape spring = (TempObject.TempSpringShape)obj;
			ArrayList<Line2D> line = spring.getShape();
			for(int i =0;i<line.size();i++)
			{
				if(ShapeIntersection.getLinePolygonCollider(line.get(i),trunk.getPolygon()) != null
				||  AlgorTools.CheckShapeContainer.isPolygonContainLine(trunk.getPolygon(),line) == true)
				{
					return true;
				}
			}
		}
		
		else if(obj.getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.JointShape"))
		{
			JointShape joint = (JointShape)obj;
			if(ShapeIntersection.shapeIntersectIndividual(joint.getRotateShape(),trunk.getPolygon())
			||  AlgorTools.CheckShapeContainer.isPolygonContainEllipse(trunk.getPolygon(), joint.getRotateShape()))
			{
				return true;
			}
			
		}
		
		else if(obj.getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.RopeShape"))
		{
			RopeShape rope = (RopeShape)obj;
			ArrayList<Line2D> line = rope.getShape();
			for(int i =0;i<line.size();i++)
			{
				if(ShapeIntersection.getLinePolygonCollider(line.get(i),trunk.getPolygon()) != null
				||  AlgorTools.CheckShapeContainer.isPolygonContainLine(trunk.getPolygon(),line) == true)
				{
					return true;
				}
			}
		}
		
		else if(obj.getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.ArrowShape"))
		{
			ArrowShape arrow = (ArrowShape)obj;
			ArrayList<Polygon> bound = arrow.getBondingBox();

			for(int i =0;i<bound.size();i++)
			{
				if(ShapeIntersection.shapeIntersectIndividual(trunk.getPolygon(), bound.get(i)))
				{
					return true;
				}
			}
		}
		
		else if(obj.getClass().getCanonicalName().equalsIgnoreCase("TempObject.TempFixJointShape"))
		{
			TempFixJointShape joint = (TempFixJointShape)obj;
			Line2D line = joint.getFixJoint();
			ArrayList<Line2D> lineList = new ArrayList<Line2D>();
			lineList.add(line);
			if(ShapeIntersection.getLinePolygonCollider(line,trunk.getPolygon()) != null
			|| AlgorTools.CheckShapeContainer.isPolygonContainLine(trunk.getPolygon(),lineList) == true)
			{
				return true;
			}
		}
		
		return false;
	}

	public void setImg(Image img) 
	{
		if(img != null)
		{
			this.img = img;		
		}
	}

	public Image getImg() {
		return img;
	}
}
