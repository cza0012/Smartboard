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
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class LineIntersection
{

	private static ArrayList<ArrayList<Integer>> LineShape;
	private static int GroundLine;
	
	
	private LineIntersection()
	{
		
	}
	
	/**
	 * For get the shape of the line
	 * @param AllLine
	 * @return List of all line
	 */
	public static ArrayList<ArrayList<Point2D>> getLineShape(ArrayList<Line2D> AllLine)
	{
		TimeCheck time = new TimeCheck(180);
		ArrayList<Integer>[] IntersectLine = getListInterSect(AllLine);
		LineShape = new ArrayList<ArrayList<Integer>>();
		for(int i =0 ; i < IntersectLine.length;i++)
		{
			if(time.isOnTime() == true)
			{
				LineShape.clear();
				AllLine.clear();
				return getShapeLine(LineShape,AllLine);
			}
			GroundLine 	  = i;
			ArrayList<Integer> ResultList = new ArrayList<Integer>();
			getShapeLoop(i,i,ResultList,IntersectLine,time);
		}
		LineShape = removeRedundancy(LineShape);
		return removeCoverRedundancy(getShapeLine(LineShape,AllLine));
	}
	
	
	/**
	 * mapping the line and the number and get the point of intersection
	 * @param lineshape
	 * @param AllLine
	 * @return
	 */
	private static ArrayList<ArrayList<Point2D>> getShapeLine(ArrayList<ArrayList<Integer>> lineshape,ArrayList<Line2D> AllLine)
	{
		ArrayList<ArrayList<Point2D>> shapePoint = new ArrayList<ArrayList<Point2D>>();
		for(int i = 0;i<lineshape.size();i++)
		{
			ArrayList<Point2D> Pointlist = new ArrayList<Point2D>();
			
			for(int j =0;j<lineshape.get(i).size();j++)
			{
				if( j == lineshape.get(i).size()-1)
				{
					Line2D p1 = AllLine.get(lineshape.get(i).get(j));
					Line2D p2 = AllLine.get(lineshape.get(i).get(0));
					Pointlist.add(getIntersection(p1,p2));
				}
				
				else
				{
					Line2D p1 = AllLine.get(lineshape.get(i).get(j));
					Line2D p2 = AllLine.get(lineshape.get(i).get(j+1));
					Pointlist.add(getIntersection(p1,p2));	
				}
				
			}
			shapePoint.add(Pointlist);
		}
		return shapePoint;
	}
	
	/**
	 * Remove shape  perimeter redundancy.
	 * @param pointList
	 * @return
	 */
	private static ArrayList<ArrayList<Point2D>> removeCoverRedundancy(ArrayList<ArrayList<Point2D>> pointList)
	{
		for(int i =0;i<pointList.size();i++)
		{
			for(int j =0;j<pointList.size();j++)
			{
				if(i == j)
				{
					
				}
				
				else
				{
					ArrayList<Point2D> cover  =  pointList.get(i);
					ArrayList<Point2D> little =  pointList.get(j);
					ArrayList<Point2D> notCover = new ArrayList<Point2D>();
					

					for(int l=0;l<little.size();l++)
					{
						for(int k=0;k<cover.size();k++)
						{
							if(cover.get(k).getX() == little.get(l).getX()
							 &&cover.get(k).getY() == little.get(l).getY())
							{
								break;
							}
							
							else if(k == cover.size()-1)
							{
								notCover.add(little.get(l));
							}
						}
					}
					if(notCover.size() >= 2)
					{
						for(int p =0;p<notCover.size()-1;p++)
						{
							ArrayList<Point2D> lineList = new ArrayList<Point2D>();
							lineList.add(notCover.get(p));
							lineList.add(notCover.get(p+1));
							if(ShapeConvert.convertPoint2DtoPolygon(cover).contains(ShapeProperty.getCM(lineList))
							&& p == notCover.size()-2)
							{
								pointList.remove(i);
								i = -1;
								j = pointList.size();
								break;
							}
							
							else if(ShapeConvert.convertPoint2DtoPolygon(cover).contains(ShapeProperty.getCM(lineList)))
							{
								
							}
							
							else
							{
								break;
							}
							
						}
					}
				}	
			}
		}
		
		return pointList;
	}
	
	/**
	 * Remove the redundancy of the lineshape and take only the subset of list not the mother set
	 * @param lineshape
	 * @return
	 */
	private static ArrayList<ArrayList<Integer>> removeRedundancy(ArrayList<ArrayList<Integer>> lineshape)
	{
		ArrayList<ArrayList<Integer>> redundancy = new ArrayList<ArrayList<Integer>>();
		
		// remove the same set
		for(int i = 0;i<lineshape.size();i++)
		{
			if(i == 0)
			{
				redundancy.add(lineshape.get(i));
			}
			
			else
			{
				for(int j = 0;j<redundancy.size();j++)
				{
					if(redundancy.get(j).containsAll(lineshape.get(i)) == true&&lineshape.get(i).containsAll(redundancy.get(j)) == false)
					{
						redundancy.remove(j);
						redundancy.add(lineshape.get(i));
						break;
					}
					
					else if(redundancy.get(j).containsAll(lineshape.get(i)) == true)
					{
						break;
					}
					
					else if(redundancy.get(j).containsAll(lineshape.get(i)) == false && j == redundancy.size()-1)
					{
						redundancy.add(lineshape.get(i));
						break;
					}
				}
			}
		}
		
		// Remove mother set keep only child set
		for(int i =0 ;i <redundancy.size();i++)
		{
			for(int j = 0;j<redundancy.size();j++)
			{
				// ตัวเดียวกัน ไม่ต้อง check
				if(i == j)
				{
					
				}
				
				// Is mother set must be romove
				else if(redundancy.get(i).containsAll(redundancy.get(j)) == true)
				{
					redundancy.remove(i);
					i--;
					break;
				}
			}
		}
		
		
		return redundancy;
	}
	
	/**
	 * Loop for get the close line
	 * @param startLine
	 * @param beyondLine
	 * @param Resultlist
	 * @param IntersectLine
	 * @param time 
	 */
	private static void getShapeLoop(int startLine,int beyondLine,ArrayList<Integer> Resultlist,ArrayList<Integer>[] IntersectLine, TimeCheck time)
	{
		for(int i =0;i<IntersectLine[startLine].size();i++)
		{
			if(time.isOnTime()== true)
			{

			}
			// ไม่เอา line ซ้ำกับก่อนหน้า
			else if(IntersectLine[startLine].get(i) == beyondLine)
			{
				
			}

			// result that we want
			else if(IntersectLine[startLine].get(i) == GroundLine && Resultlist.contains(IntersectLine[startLine].get(i)) == false)
			{
				ArrayList<Integer> Temp = new ArrayList<Integer>();
				Temp = Resultlist;
				Temp.add(IntersectLine[startLine].get(i));
				LineShape.add(Temp);
			}
			
			else if(Resultlist.contains(IntersectLine[startLine].get(i)) == false)
			{
				ArrayList<Integer> Temp = (ArrayList<Integer>) Resultlist.clone();
				Temp.add(IntersectLine[startLine].get(i));
				getShapeLoop(IntersectLine[startLine].get(i),startLine,Temp,IntersectLine,time);
			}
			
		}
	}
	
	/**
	 * For generate the list of intersection line
	 * @param AllLine
	 * @return
	 */
	private static ArrayList<Integer>[] getListInterSect(ArrayList<Line2D> AllLine)
	{
		ArrayList<Integer>[] Intersect = new ArrayList[AllLine.size()];
		for(int i =0;i<Intersect.length;i++)
		{
			Intersect[i] = new ArrayList();
		}
		for(int i =0;i<AllLine.size();i++)
		{
			for(int j =0;j<AllLine.size();j++)
			{
				// เส้นเดียวกันไม่ต้อง check
				if(i == j)
				{
					
				}		
				else if(AllLine.get(i).intersectsLine(AllLine.get(j)))
				{
					Intersect[i].add(j);
				}
			}
		}
		return Intersect;
	}
	
	
	/**
	 * For check the list of line are intersection or not
	 * @param L1
	 * @param L2
	 * @return if intersect return true, if not return false
	 */
	public static boolean CheckListIntersection(ArrayList<Line2D> L1,ArrayList<Line2D> L2)
	{
		for(int i = 0;i<L1.size();i++)
		{
			for(int j = 0;j<L2.size();j++)
			{
				if(L1.get(i).intersectsLine(L2.get(j)))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * For check the list of polygon and Line2D are intersection or not
	 * @param polygon
	 * @param line
	 * @return if intersect return true, if not return false
	 */
	public static boolean checkRemoveIntersection(ArrayList<Line2D> L1,ArrayList<Line2D> L2)
	{
		int count = 0;
		for(int i = 0;i<L1.size();i++)
		{
			for(int j = 0;j<L2.size();j++)
			{
				if(L1.get(i).intersectsLine(L2.get(j)))
				{
					count ++;
				}
			}
		}
		
		if(count >= 2)
		{
			return true;
		}
		
		else
		return false;
	}
	
	
	/**
	 * Get the intersection of 2 point form 2 line
	 * @param line1
	 * @param line2
	 * @return  - if one intersection point return first line
	 * 		 	- if many intersection point return first point of intersection ,
	 * 			- if non intersectionpoint return null
	 */
	public static Point2D getIntersection(Line2D line1, Line2D line2)
	{
		double dyline1, dxline1;
		double dyline2, dxline2, e, f;
		double x1line1, y1line1, x2line1, y2line1;
		double x1line2, y1line2, x2line2, y2line2;
	
		if (!line1.intersectsLine(line2))
			return null;
	
		x1line1 = (double)line1.getX1();
		y1line1 = (double)line1.getY1();
		x2line1 = (double)line1.getX2();
		y2line1 = (double)line1.getY2();
	
		x1line2 = (double)line2.getX1();
		y1line2 = (double)line2.getY1();
		x2line2 = (double)line2.getX2();
		y2line2 = (double)line2.getY2();
	
		if ((x1line1 == x1line2) && (y1line1 == y1line2))
		{
			return (new Point2D.Double(x1line1,y1line1));
		}
		if ((x1line1 == x2line2) && (y1line1 == y2line2))
		{
			return (new Point2D.Double(x1line1,y1line1));
		}
		if ((x2line1 == x1line2) && (y2line1 == y1line2))
		{
			return (new Point2D.Double(x2line1,y2line1));
		}
		if ((x2line1 == x2line2) && (y2line1 == y2line2))
		{
			return (new Point2D.Double(x2line1,y2line1));
		}
	
		dyline1 = - (y2line1 - y1line1);
		dxline1 = x2line1 - x1line1;
	
		dyline2 = - (y2line2 - y1line2);
		dxline2 = x2line2 - x1line2;
	
		e = - (dyline1 * x1line1) - (dxline1 * y1line1);
		f = - (dyline2 * x1line2) - (dxline2 * y1line2);
	
		if ((dyline1 * dxline2 - dyline2 * dxline1) == 0)
		{
			return null;
		}
		
		return new Point2D.Double(
				 (
					- (e * dxline2 - dxline1 * f)
						/ (dyline1 * dxline2 - dyline2 * dxline1)),
				 (
					- (dyline1 * f - dyline2 * e)
						/ (dyline1 * dxline2 - dyline2 * dxline1)));
	}
	
	
	/**
	 * Check line for remove by polygon work like robber
	 * @param LineList
	 * @param Eraser
	 * @return
	 */
	public static ArrayList<Line2D> checkRemoveLinebyPolygon(ArrayList<Line2D> LineList,Polygon Eraser)
	{
		ArrayList<Line2D> TempLinecollector = new ArrayList<Line2D>();
		
		for(int i =  0; i< LineList.size(); i++)
		{
		ArrayList<Point2D> IntersectPoints = ShapeIntersection.getListLinePolygonCollider(LineList.get(i),Eraser);
		
		if(IntersectPoints.size()==2||IntersectPoints.size()==4)
		{
			Line2D newLine = new Line2D.Double(LineList.get(i).getX1(),LineList.get(i).getY1(),IntersectPoints.get(0).getX(),IntersectPoints.get(0).getY());
			Line2D newLine2 = new Line2D.Double(LineList.get(i).getX1(),LineList.get(i).getY1(),IntersectPoints.get(1).getX(),IntersectPoints.get(1).getY());
		     
			double Line1 = AlgorTools.LineProperty.findMagnitudeOfLine( newLine );
			double Line2 = AlgorTools.LineProperty.findMagnitudeOfLine( newLine2 );
			if((int)IntersectPoints.get(1).getX() == (int)IntersectPoints.get(0).getX()&& (int)IntersectPoints.get(1).getY() == (int)IntersectPoints.get(0).getY())
			{
				if(Eraser.contains(LineList.get(i).getP2()))
				{
					 newLine = new Line2D.Double(LineList.get(i).getX1(),LineList.get(i).getY1(),IntersectPoints.get(0).getX(),IntersectPoints.get(0).getY());
				}
				else
				{
					newLine = new Line2D.Double(LineList.get(i).getX2(),LineList.get(i).getY2(),IntersectPoints.get(0).getX(),IntersectPoints.get(0).getY());
				}
				TempLinecollector.add(newLine);
			}
			else if(Line1< Line2)//Use line1 with P1
			{
				 newLine = new Line2D.Double(LineList.get(i).getX1(),LineList.get(i).getY1(),IntersectPoints.get(0).getX(),IntersectPoints.get(0).getY());
				 newLine2 = new Line2D.Double(IntersectPoints.get(1).getX(),IntersectPoints.get(1).getY(),LineList.get(i).getX2(),LineList.get(i).getY2());
					TempLinecollector.add(newLine);
					TempLinecollector.add(newLine2);
			}
			else
			{
				 newLine = new Line2D.Double(LineList.get(i).getX2(),LineList.get(i).getY2(),IntersectPoints.get(0).getX(),IntersectPoints.get(0).getY());
				 newLine2 = new Line2D.Double(IntersectPoints.get(1).getX(),IntersectPoints.get(1).getY(),LineList.get(i).getX1(),LineList.get(i).getY1());
				 TempLinecollector.add(newLine);
				 TempLinecollector.add(newLine2);
			}
		}
		
		else if(IntersectPoints.size()==1)
		{
			Line2D newLine ;
			if(Eraser.contains(LineList.get(i).getP1()))
			{
				newLine = new Line2D.Double(IntersectPoints.get(0).getX(),IntersectPoints.get(0).getY(),LineList.get(i).getX2(),LineList.get(i).getY2());
				TempLinecollector.add(newLine);
			}
			else if(Eraser.contains(LineList.get(i).getP2()))
			{
				newLine = new Line2D.Double(LineList.get(i).getX1(),LineList.get(i).getY1(),IntersectPoints.get(0).getX(),IntersectPoints.get(0).getY());
				TempLinecollector.add(newLine);
			}
			else
			{
				TempLinecollector.add(LineList.get(i));
			}
		}
		
		else
		{
			ArrayList<Line2D>  LineAnalyze = new ArrayList<Line2D>();
			LineAnalyze.add(LineList.get(i));
			// Bug เกิดจากที่เอา lineAnalyze ไปแปลง
			ArrayList<Point2D>  PointSet = AlgorTools.ShapeConvert.convertLine2DtoPoint2D(LineAnalyze);
			boolean Ishave = true;
			for(int j = 0 ; j< PointSet.size();j++)
			{
				if(Eraser.contains(PointSet.get(j)) == true && j == PointSet.size()-1)
				{
					Ishave = false;
				}	
				
				else if(Eraser.contains(PointSet.get(j)) == false)
				{
					Ishave = true;
					break;
				}
			}
			
			if(Ishave)
			{
				TempLinecollector.add(LineList.get(i));
			}
		}
		
		}
		return TempLinecollector;
	}
		
	
}
