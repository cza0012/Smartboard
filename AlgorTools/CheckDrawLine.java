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

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


/**
 * For check the line that you draw and generate in to a strainght line
 * @author Magic Team
 *
 */
public class CheckDrawLine 
{
	public static ArrayList<Line2D> Ldetect1 = new ArrayList<Line2D>();
	public static ArrayList<Line2D> Ldetect2 = new ArrayList<Line2D>();
	
	private CheckDrawLine()
	{
		
	}
	
	/**
	 * Get the line that you was drawing.
	 * @param AllPoint
	 * @return The list of Line2D. 
	 */
	public static ArrayList<Line2D> getLine(ArrayList<Point2D> AllPoint,double boxwidth) 
	{
		ArrayList<Line2D> LineList					= new ArrayList<Line2D>();
		ArrayList<ArrayList<Point2D>> PointList		= new ArrayList<ArrayList<Point2D>>();
		
		// 1. get point of line from 	Start --> End
		PointList = getPointOfLine(AllPoint,boxwidth);
		
		// 2. getReBuildLine(PointList);
		PointList = getReBuildLine(PointList);
		
		// 3. set all start and end point in to line
		LineList = setPointToLine(PointList);
		return LineList;
	}
	
	
	/**
	 * Set all point in to line
	 * @param PointList
	 * @return The list of Line2D
	 */
	public static ArrayList<Line2D> setPointToLine(ArrayList<ArrayList<Point2D>> PointList)
	{
		ArrayList<Line2D> LineList	= new ArrayList<Line2D>();
		for(int i = 0; i< PointList.size();i++)
		{
			ArrayList<Point2D> p = PointList.get(i);
			p = SetStoEPoint(p);
			Line2D l = new Line2D.Double(p.get(0),p.get(p.size()-1));
			LineList.add(l);
		}
		return LineList;
	}
	
	
	/**
	 * Find the overlap of the line
	 * @param PointList
	 * @return List of point2D that non overlap to another
	 */
	private static ArrayList<ArrayList<Point2D>> getReBuildLine(ArrayList<ArrayList<Point2D>> PointList)
	{
		for(int i =0;i<PointList.size()-1;i++)
		{
				ArrayList<Point2D> line1 = PointList.get(i);
				ArrayList<Point2D> line2 = PointList.get(i+1);
				
				// find L1
				int halfL1 = (int)line1.size()/2;
				double L1x1 = line1.get(0).getX();
				double L1y1 = line1.get(0).getY();
				double L1x2 = line1.get(halfL1).getX();
				double L1y2 = line1.get(halfL1).getY();
				double angleL1 = LineProperty.getLineAngle(L1x1,L1y1,L1x2,L1y2);
				Line2D L1 = new Line2D.Double(L1x1,L1y1,1500,L1y1);
				
				if(angleL1 >=90)
				{
					AffineTransform  atx = AffineTransform.getRotateInstance(Math.toRadians(180-angleL1),line1.get(0).getX(),line1.get(0).getY());
					//Take the shape object and create a rotated version
					GeneralPath s = new GeneralPath(atx.createTransformedShape(L1));
					L1x2 = s.getCurrentPoint().getX();
					L1y2 = s.getCurrentPoint().getY();
					L1 = new Line2D.Double(L1x1,L1y1,L1x2,L1y2);
					
					ArrayList<Point2D> LineDraw  = new ArrayList<Point2D>();
					LineDraw.add(line1.get(0));
					LineDraw.add(line1.get(halfL1));
					
					// เช็คว่าวาดจากขวามาซ้าย ต้อง rotateอีก 180 องศา
					if(IsStoEPoint(LineDraw) == false)
					{
						AffineTransform  atx2 = AffineTransform.getRotateInstance(Math.toRadians(180),line1.get(0).getX(),line1.get(0).getY());
						//Take the shape object and create a rotated version
						s = new GeneralPath(atx2.createTransformedShape(s));
						L1x2 = s.getCurrentPoint().getX();
						L1y2 = s.getCurrentPoint().getY();
						L1 = new Line2D.Double(L1x1,L1y1,L1x2,L1y2);
					}
					Ldetect1.add(L1);
				}
				
				
				else
				{
					AffineTransform  atx = AffineTransform.getRotateInstance(-Math.toRadians(angleL1),line1.get(0).getX(),line1.get(0).getY());
					//Take the shape object and create a rotated version
					GeneralPath s = new GeneralPath(atx.createTransformedShape(L1));
					
					L1x2 = s.getCurrentPoint().getX();
					L1y2 = s.getCurrentPoint().getY();
					L1 = new Line2D.Double(L1x1,L1y1,L1x2,L1y2);
					
					ArrayList<Point2D> LineDraw  = new ArrayList<Point2D>();
					LineDraw.add(line1.get(0));
					LineDraw.add(line1.get(halfL1));
					
					// เช็คว่าวาดจากขวามาซ้าย ต้อง rotateอีก 180 องศา
					if(IsStoEPoint(LineDraw) == false)
					{
						AffineTransform  atx2 = AffineTransform.getRotateInstance(Math.toRadians(180),line1.get(0).getX(),line1.get(0).getY());
						//Take the shape object and create a rotated version
						s = new GeneralPath(atx2.createTransformedShape(s));
						L1x2 = s.getCurrentPoint().getX();
						L1y2 = s.getCurrentPoint().getY();
						L1 = new Line2D.Double(L1x1,L1y1,L1x2,L1y2);
					}
					Ldetect1.add(L1);
				}
				
				
				//Find L2
				int halfL2 = (int)line2.size()/2;
				double L2x1 = line2.get(halfL2).getX();
				double L2y1 = line2.get(halfL2).getY();
				double L2x2 = line2.get(0).getX();
				double L2y2 = line2.get(0).getY();
				double angleL2 	= LineProperty.getLineAngle(L2x1,L2y1,L2x2,L2y2);
				Line2D L2 		= new Line2D.Double(L2x1,L2y1,1500,L2y1);
				
				if(angleL2 >=90)
				{
					AffineTransform  atx = AffineTransform.getRotateInstance(Math.toRadians(180-angleL2),line2.get(halfL2).getX(),line2.get(halfL2).getY());
					//Take the shape object and create a rotated version
					GeneralPath s = new GeneralPath(atx.createTransformedShape(L2));
					L2x2 = s.getCurrentPoint().getX();
					L2y2 = s.getCurrentPoint().getY();
					L2 = new Line2D.Double(L2x1,L2y1,L2x2,L2y2);
					
					ArrayList<Point2D> LineDraw  = new ArrayList<Point2D>();
					LineDraw.add(line2.get(halfL2));
					LineDraw.add(line2.get(0));
					
					// เช็คว่าวาดจากขวามาซ้าย ต้อง rotateอีก 180 องศา
					if(IsStoEPoint(LineDraw) == false)
					{
						AffineTransform  atx2 = AffineTransform.getRotateInstance(Math.toRadians(180),line2.get(halfL2).getX(),line2.get(halfL2).getY());
						//Take the shape object and create a rotated version
						s = new GeneralPath(atx2.createTransformedShape(s));
						L2x2 = s.getCurrentPoint().getX();
						L2y2 = s.getCurrentPoint().getY();
						L2 = new Line2D.Double(L2x1,L2y1,L2x2,L2y2);
					}
					Ldetect2.add(L2);
				}
				
				
				else
				{
					AffineTransform  atx = AffineTransform.getRotateInstance(-Math.toRadians(angleL2),line2.get(halfL2).getX(),line2.get(halfL2).getY());
					//Take the shape object and create a rotated version
					GeneralPath s = new GeneralPath(atx.createTransformedShape(L2));
					L2x2 = s.getCurrentPoint().getX();
					L2y2 = s.getCurrentPoint().getY();
					L2 = new Line2D.Double(L2x1,L2y1,L2x2,L2y2);
					
					ArrayList<Point2D> LineDraw  = new ArrayList<Point2D>();
					LineDraw.add(line2.get(halfL2));
					LineDraw.add(line2.get(0));
					
					// เช็คว่าวาดจากขวามาซ้าย ต้อง rotateอีก 180 องศา
					if(IsStoEPoint(LineDraw) == false)
					{
						AffineTransform  atx2 = AffineTransform.getRotateInstance(Math.toRadians(180),line2.get(halfL2).getX(),line2.get(halfL2).getY());
						//Take the shape object and create a rotated version
						s = new GeneralPath(atx2.createTransformedShape(L1));
						L2x2 = s.getCurrentPoint().getX();
						L2y2 = s.getCurrentPoint().getY();
						L2 = new Line2D.Double(L2x1,L2y1,L2x2,L2y2);
					}
					Ldetect2.add(L2);
				}
				
				
				//get point that line L1 and L2 was Intersection
				Point2D pointIntersection =  LineIntersection.getIntersection(L1,L2);
				
				if(pointIntersection == null)
				{
					PointList.set(i, line1);
					PointList.set(i+1, line2);	
				}
				
				else
				{
					line1.set(line1.size()-1,pointIntersection);
					line2.set(0,pointIntersection);
					
					PointList.set(i, line1);
					PointList.set(i+1, line2);	
				}
		}
		return PointList;
	}
	
	/**
	 * Get all of point in array of line
	 * @param AllPoint
	 * @return List of point2D
	 */
	private static ArrayList<ArrayList<Point2D>> getPointOfLine(ArrayList<Point2D> AllPoint,double boxwidth)
	{
		// for find the line by start -->> end point
		ArrayList<ArrayList<Point2D>> PointList = new ArrayList<ArrayList<Point2D>>();
		int start = 0;
		for(int i =1;i<AllPoint.size();i++)
		{
			Point2D startpos 	= AllPoint.get(start);
			Point2D endpos		= AllPoint.get(i);
			
			ArrayList<Point2D> StoEPoint = new ArrayList<Point2D>();
			StoEPoint.add(startpos);
			StoEPoint.add(endpos);
			StoEPoint = SetStoEPoint(StoEPoint);
			
			startpos = StoEPoint.get(0);
			endpos 	 = StoEPoint.get(1);
	
			double x1 = startpos.getX();
			double y1 = startpos.getY();
			double x2 = endpos.getX();
			double y2 = endpos.getY();
			double degree = LineProperty.getLineAngle(x1,y1,x2,y2);
			double Linewidth = Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
			
			Shape rectangle;
			Rectangle2D rec = new Rectangle2D.Double(x1-1, y1-4, Linewidth+2,boxwidth);
			if(degree >=90)
			{
				//angle รอบจุดposx,posy
				AffineTransform  atx = AffineTransform.getRotateInstance(Math.toRadians(180-degree),x1,y1);
				//Take the shape object and create a rotated version
				rectangle = atx.createTransformedShape(rec);
			}
			
			else
			{
				//angle รอบจุดposx,posy
				AffineTransform  atx = AffineTransform.getRotateInstance(-Math.toRadians(degree),x1,y1);
				//Take the shape object and create a rotated version
				rectangle = atx.createTransformedShape(rec);
				//CheckDrawLine.RectangleList.add(rectangle);
			}
			
			ArrayList<Point2D> LineCheckPoint = new ArrayList<Point2D>();
			// เอาค่าpoint ในช่วง start ถึง end ใส่ array
			for(int k = start;k<=i;k++)
			{
				LineCheckPoint.add(AllPoint.get(k));
			}
			
			// Checking the point are in rectangle
			boolean IsLine = CheckInRectangle(LineCheckPoint,rectangle);
			
			if(IsLine == false)
			{
				StoEPoint = new ArrayList<Point2D>();
				for(int j =start;j<i;j++)
				{
					StoEPoint.add(AllPoint.get(j));
				}
				
				PointList.add(StoEPoint);
				start = i-1;
				i = i-1;
			}
			
			else if(IsLine == true && i == AllPoint.size()-1)
			{
				StoEPoint = new ArrayList<Point2D>();
				for(int j =start;j<AllPoint.size();j++)
				{
					StoEPoint.add(AllPoint.get(j));
				}
												
				PointList.add(StoEPoint);
				
			}
		}
		return PointList;
	}
	
	
	/**
	 * Check the start and end point that you draw
	 * @param AllPoint
	 * @return true or false
	 */
	private static boolean IsStoEPoint(ArrayList<Point2D> StoEPoint)
	{
		Point2D startpos = StoEPoint.get(0);
		Point2D endpos	 = StoEPoint.get(StoEPoint.size()-1);
		
		/**
		*  check ว่าวาดจากขวาไปซ้ายหรือซ้ายไปขวา บนลงล่างหรือล่างขึ้นบน
		*/
		// 1. xเท่า  y ต้องจากน้อยไปมาก
		if(startpos.getX() == endpos.getX())
		{
			if(startpos.getY() > endpos.getY())
			{
				return false;
			}
		}
		
		// 2. yเท่า  x ต้องจากน้อยไปมาก
		else if(startpos.getY() == endpos.getY())
		{
			if(startpos.getX() > endpos.getX())
			{
				return false;
			}
		}
		
		// 3. X1 < X2 
		else if(startpos.getX() > endpos.getX())
		{
			return false;
		}
		
		return true;
	}
	
	
	
	
	
	
	/**
	 * set the start and end point that you draw
	 * @param AllPoint
	 * @return List of start to end point2D
	 */
	private static ArrayList<Point2D> SetStoEPoint(ArrayList<Point2D> StoEPoint)
	{
		Point2D startpos = StoEPoint.get(0);
		Point2D endpos	 = StoEPoint.get(StoEPoint.size()-1);
		
		/**
		*  check ว่าวาดจากขวาไปซ้ายหรือซ้ายไปขวา บนลงล่างหรือล่างขึ้นบน
		*/
		// 1. xเท่า  y ต้องจากน้อยไปมาก
		if(startpos.getX() == endpos.getX())
		{
			if(startpos.getY() > endpos.getY())
			{
				ArrayList<Point2D> temp = new ArrayList<Point2D>();
				for(int i = StoEPoint.size()-1;i >=0;i--)
				{
					temp.add(StoEPoint.get(i));
				}
				StoEPoint = temp;
			}
		}
		
		// 2. yเท่า  x ต้องจากน้อยไปมาก
		else if(startpos.getY() == endpos.getY())
		{
			if(startpos.getX() > endpos.getX())
			{
				ArrayList<Point2D> temp = new ArrayList<Point2D>();
				for(int i = StoEPoint.size()-1;i >=0;i--)
				{
					temp.add(StoEPoint.get(i));
				}
				
				StoEPoint = temp;
			}
		}
		
		// 3. X1 < X2 
		else if(startpos.getX() > endpos.getX())
		{
				ArrayList<Point2D> temp = new ArrayList<Point2D>();
				for(int i = StoEPoint.size()-1;i >=0;i--)
				{
					temp.add(StoEPoint.get(i));
				}
				StoEPoint = temp;
		}
		return StoEPoint;
	}
	
	/**
	 * Check for allpoint in line or not by check it in rectangle
	 * @param AllPoint
	 * @return true or false
	 */
	private static boolean CheckInRectangle(ArrayList<Point2D> AllPoint,Shape rectangle)
	{
		for(int i=0;i<AllPoint.size();i++)
		{
			if(!rectangle.contains(AllPoint.get(i)))
			{
				return false;
			}
		}
		return true;
	}
	
	
}
