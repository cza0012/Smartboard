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

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import CommonShape.CommonShape;
import CommonShape.EllipseShape;
import CommonShape.PolygonShape;
import CommonShape.RectangleShape;
import CommonShape.TriangleShape;
import Container.ContainerAllShape;
import SpecialShape.FixJointShape;
import SpecialShape.JointShape;
import SpecialShape.RopeShape;
import SpecialShape.SpringShape;

public class LineSpliter 
{
	private LineSpliter()
	{
		
	}
	
	/**
	 * For spliter the list of line 2D to be a JointShape
	 * @param pointList
	 * @param shapeContainer
	 * @return
	 */
	public static ArrayList<JointShape> jointSplitter(ArrayList<Point2D> pointList,ContainerAllShape shapeContainer)
	{
		ArrayList<JointShape> jointList = new ArrayList<JointShape>();
		int startIndex = -1;
		int endIndex   = -1;
		
		for(int i =0;i<pointList.size();i++)
		{
			for(int j =0;j<shapeContainer.getShapeContainerSize();j++)
			{
				if(shapeContainer.getShape(j).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
				{
					EllipseShape obj = (EllipseShape)shapeContainer.getShape(j);
					if(obj.getEllipse().contains(pointList.get(i)) && startIndex == -1)
					{
						startIndex = j;
						break;
					}
					else if(obj.getEllipse().contains(pointList.get(i)) && endIndex == -1 && startIndex != j)
					{
						jointList.add(new JointShape((CommonShape)shapeContainer.getShape(startIndex),(CommonShape)shapeContainer.getShape(j)));
						startIndex = j;
						endIndex   = -1;
						break;
					}
				}
				
				else if(shapeContainer.getShape(j).getClass().getPackage().getName().equalsIgnoreCase("CommonShape"))
	        	{
					CommonShape obj = (CommonShape)shapeContainer.getShape(j);
					if(obj.getPolygon().contains(pointList.get(i)) && startIndex == -1)
					{
						startIndex = j;
						break;
					}
					else if(obj.getPolygon().contains(pointList.get(i)) && endIndex == -1 && startIndex != j)
					{
						jointList.add(new JointShape((CommonShape)shapeContainer.getShape(startIndex),(CommonShape)shapeContainer.getShape(j)));
						startIndex = j;
						endIndex   = -1;
						break;
					}
	        	}
			}
		}
		return jointList;
	}
	
	/**
	 * For spliter the list of line 2D to be a FixJointShape
	 * @param pointList
	 * @param shapeContainer
	 * @return
	 */
	public static ArrayList<FixJointShape> fixJointSplitter(ArrayList<Point2D> pointList,ContainerAllShape shapeContainer)
	{
		ArrayList<FixJointShape> jointList = new ArrayList<FixJointShape>();
		int startIndex = -1;
		int endIndex   = -1;
		
		for(int i =0;i<pointList.size();i++)
		{
			for(int j =0;j<shapeContainer.getShapeContainerSize();j++)
			{
				if(shapeContainer.getShape(j).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
				{
					EllipseShape obj = (EllipseShape)shapeContainer.getShape(j);
					if(obj.getEllipse().contains(pointList.get(i)) && startIndex == -1)
					{
						startIndex = j;
						break;
					}
					else if(obj.getEllipse().contains(pointList.get(i)) && endIndex == -1 && startIndex != j)
					{
						jointList.add(new FixJointShape((CommonShape)shapeContainer.getShape(startIndex),(CommonShape)shapeContainer.getShape(j)));
						startIndex = j;
						endIndex   = -1;
						break;
					}
				}
				
				else if(shapeContainer.getShape(j).getClass().getPackage().getName().equalsIgnoreCase("CommonShape"))
	        	{
					CommonShape obj = (CommonShape)shapeContainer.getShape(j);
					if(obj.getPolygon().contains(pointList.get(i)) && startIndex == -1)
					{
						startIndex = j;
						break;
					}
					else if(obj.getPolygon().contains(pointList.get(i)) && endIndex == -1 && startIndex != j)
					{
						jointList.add(new FixJointShape((CommonShape)shapeContainer.getShape(startIndex),(CommonShape)shapeContainer.getShape(j)));
						startIndex = j;
						endIndex   = -1;
						break;
					}
	        	}
			}
		}
		
		return jointList;
	}
	
	/**
	 * For spliter the list of line 2D to be a FixJointShape
	 * @param pointList
	 * @param shapeContainer
	 * @return
	 */
	public static ArrayList<SpringShape> springJointSplitter(ArrayList<Point2D> pointList,ContainerAllShape shapeContainer)
	{
		ArrayList<SpringShape> jointList = new ArrayList<SpringShape>();
		int startIndex = -1;
		int endIndex   = -1;
		
		for(int i =0;i<pointList.size();i++)
		{
			for(int j =0;j<shapeContainer.getShapeContainerSize();j++)
			{
				if(shapeContainer.getShape(j).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
				{
					EllipseShape obj = (EllipseShape)shapeContainer.getShape(j);
					if(obj.getEllipse().contains(pointList.get(i)) && startIndex == -1)
					{
						startIndex = j;
						break;
					}
					else if(obj.getEllipse().contains(pointList.get(i)) && endIndex == -1 && startIndex != j)
					{
						jointList.add(new SpringShape((CommonShape)shapeContainer.getShape(startIndex),(CommonShape)shapeContainer.getShape(j)));
						startIndex = j;
						endIndex   = -1;
						break;
					}
				}
				
				else if(shapeContainer.getShape(j).getClass().getPackage().getName().equalsIgnoreCase("CommonShape"))
	        	{
					CommonShape obj = (CommonShape)shapeContainer.getShape(j);
					if(obj.getPolygon().contains(pointList.get(i)) && startIndex == -1)
					{
						startIndex = j;
						break;
					}
					else if(obj.getPolygon().contains(pointList.get(i)) && endIndex == -1 && startIndex != j)
					{
						jointList.add(new SpringShape((CommonShape)shapeContainer.getShape(startIndex),(CommonShape)shapeContainer.getShape(j)));
						startIndex = j;
						endIndex   = -1;
						break;
					}
	        	}
			}
		}
		return jointList;
	}
	
	
	/**
	 * For spliter the list of line 2D to be a RopeShape
	 * @param Line
	 * @param shapeContainer
	 * @return
	 */
	public static ArrayList<RopeShape> ropeSplitter(ArrayList<Point2D> Line,ContainerAllShape shapeContainer)
	{
	    ArrayList<RopeShape> RopeCollecter =  new ArrayList<RopeShape>();
	    ArrayList<Line2D> NewLine = new ArrayList<Line2D>();
	    ArrayList<Integer> removeList = new ArrayList<Integer>();
	     
	    boolean hasChange  = false;
	    int Countable = 0;
	    boolean RemoveOnce = false;
	    
	    boolean StartNull = false;
	    
	    boolean noAddRope = false;
	    
	    boolean CheckContainer = false; // เป็น true เมื่อ พบว่าจุดๆนั้นอยู่ในกล่องใดๆ
	    
	    int ShapeStart = 0;
	    int CurrentBox = 0; 
	    
	    int InBound = 0 ;
	    boolean StartFind = true;	
	    Point2D  iIntersect2 = new Point2D.Double();
	    Point2D  iIntersect1 =new Point2D.Double(); 
	    
	    boolean Collector = false;
	    
	    boolean BoxEv = false;
	     
		for(int i = 0; i<Line.size()-1;i++)
		{
			Point2D StartIndex = Line.get(i);
				for(int j = 0 ; j< shapeContainer.getShapeContainerSize();j++)
				{
					if(shapeContainer.getShape(j).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.TriangleShape"))
		        	{
		        		TriangleShape obj = (TriangleShape)shapeContainer.getShape(j);
						if(obj.getPolygon().contains(StartIndex))
						{
							CheckContainer = true;
							if(StartFind)
							{
								/////Add indexpoint
								if(!StartNull)
								{
								ShapeStart = j;
								}
								StartFind = false;
								//////////
							}
							CurrentBox = j;
						}
		        	}
		        	
		        	else if(shapeContainer.getShape(j).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.RectangleShape"))
		        	{
		        		RectangleShape obj = (RectangleShape)shapeContainer.getShape(j);
		        		if(obj.getPolygon().contains(StartIndex))
						{
							CheckContainer = true;
							if(StartFind)
							{
								/////Add indexpoint
								if(!StartNull)
								{
								ShapeStart = j;
								}
								StartFind = false;
								//////////
							}
							CurrentBox = j;
						}
		        	}
		        	
		        	else if(shapeContainer.getShape(j).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
		        	{
		        		EllipseShape obj = (EllipseShape)shapeContainer.getShape(j);
		        		if(obj.getEllipse().contains(StartIndex))
						{
							CheckContainer = true;
							if(StartFind)
							{
								/////Add indexpoint
								System.out.println("Define end"+ j);
								if(!StartNull)
								{
								ShapeStart = j;
								}
								StartFind = false;
								//////////
							}
							CurrentBox = j;
						}
		        	}
		        	
		        	else if(shapeContainer.getShape(j).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.PolygonShape"))
		        	{
		        		PolygonShape obj = (PolygonShape)shapeContainer.getShape(j);
		        		if(obj.getPolygon().contains(StartIndex))
						{
							CheckContainer = true;
							if(StartFind)
							{
								/////Add indexpoint
								if(!StartNull)
								{
								ShapeStart = j;
								}
								StartFind = false;
								//////////
							}
							CurrentBox = j;
						}
		        	}
				}
					
				if(CheckContainer && removeList.contains(Countable) == false )// point that contains in box
				{
					
						// จากนอกเข้าไปใน วัตถุ
						if(hasChange) // Change Box Occured
						{
							if(i == 0)
							{
								
							}
							
							else if(shapeContainer.getShape(CurrentBox).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
							{
								NewLine.remove(NewLine.size() -1 );
								Line2D iLine1 	 = new Line2D.Double((int)Line.get(i-1).getX(),(int)Line.get(i-1).getY(),(int)Line.get(i).getX(),(int)Line.get(i).getY());
								EllipseShape obj = (EllipseShape)shapeContainer.getShape(CurrentBox);
								iIntersect2 	 = 	 AlgorTools.ShapeIntersection.getLineCircleCollider(iLine1,obj);
								Line2D l2d = new Line2D.Double((int)iIntersect2.getX(),(int)iIntersect2.getY(),(int)Line.get(i-1).getX(),(int)Line.get(i-1).getY());
								NewLine.add(l2d);
								Collector = false;	
							}
							
							else if(shapeContainer.getShape(CurrentBox).getClass().getSuperclass().getCanonicalName().equalsIgnoreCase("CommonShape.CommonShape"))
				        	{
								NewLine.remove(NewLine.size() -1 );
								Line2D iLine1 	= new Line2D.Double((int)Line.get(i-1).getX(),(int)Line.get(i-1).getY(),(int)Line.get(i).getX(),(int)Line.get(i).getY());
								CommonShape obj = (CommonShape)shapeContainer.getShape(CurrentBox);
								iIntersect2 	= AlgorTools.ShapeIntersection.getLinePolygonCollider(iLine1,obj.getPolygon());
								Line2D l2d 		= new Line2D.Double((int)iIntersect2.getX(),(int)iIntersect2.getY(),(int)Line.get(i-1).getX(),(int)Line.get(i-1).getY());
								NewLine.add(l2d);
								Collector = false;	
				        	}
							RopeShape Ropepiece = new RopeShape(NewLine);
							if(!StartNull)
							{
								
							Ropepiece.setStartIndex((CommonShape)shapeContainer.getShape(ShapeStart));
							}
							Ropepiece.setEndIndex((CommonShape)shapeContainer.getShape(CurrentBox));
						
						
							RopeCollecter.add(Ropepiece);
							
							
							StartFind = true;
							hasChange = false;
							BoxEv = true; // ไว้เพื่อบอกว่า มีการ add rope เกิดขึ้นแล้ว
							
							NewLine = new ArrayList<Line2D>();
						}
						
						// จาก ในไปนอก
						else
						{
							
							RemoveOnce = true;
							CheckContainer = false;	
							
							
						}
						
					}					
					else // not container ( mean line ที่ต้องการ add เก็บไว้)
					{
						if(StartFind)
						{
							/////Add indexpoint
							ShapeStart = -1;
							StartNull = true;
							//StartFind = false;
							
							//////////
						}
					  if(RemoveOnce)
					  {
						
						removeList.add(Countable);
						Countable++;
						 RemoveOnce = false;
						 Line2D iLine1 = new Line2D.Double((int)Line.get(i-1).getX(),(int)Line.get(i-1).getY(),(int)Line.get(i).getX(),(int)Line.get(i).getY());
						 
						 if(shapeContainer.getShape(CurrentBox).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
						{
							 EllipseShape obj = (EllipseShape)shapeContainer.getShape(CurrentBox);
							 iIntersect1 = 	 AlgorTools.ShapeIntersection.getLineCircleCollider(iLine1,obj);
							 if(iIntersect1 == null)
							 {
									RopeCollecter.clear();
									return RopeCollecter;	
							 }
							 else
							 {
							 Line2D l2d = new Line2D.Double((int)iIntersect1.getX(),(int)iIntersect1.getY(),(int)Line.get(i).getX(),(int)Line.get(i).getY());
							 NewLine.add(l2d);
							 Collector = true;
							 }
						}
						 
						 else if(shapeContainer.getShape(CurrentBox).getClass().getSuperclass().getCanonicalName().equalsIgnoreCase("CommonShape.CommonShape"))
						 {
							 CommonShape obj = (CommonShape)shapeContainer.getShape(CurrentBox);
							  iIntersect1 = 	 AlgorTools.ShapeIntersection.getLinePolygonCollider(iLine1,obj.getPolygon());
							 if(iIntersect1 == null)
							 {
								  // fast mouse move dont' draw rope
								RopeCollecter.clear();
								return RopeCollecter;	
							 }
							 else
							 {
							  Line2D l2d = new Line2D.Double((int)iIntersect1.getX(),(int)iIntersect1.getY(),(int)Line.get(i).getX(),(int)Line.get(i).getY());
							
							  NewLine.add(l2d);
							  Collector = true;
							 }
							
						 }
					  }
						hasChange = true;
						Line2D l2d = new Line2D.Double((int)Line.get(i).getX(),(int)Line.get(i).getY(),(int)Line.get(i+1).getX(),(int)Line.get(i+1).getY());
						NewLine.add(l2d);
					}	
			}
		
		if(BoxEv == false) // rope ไม่มีการถูก add เลย แสดงว่าเป็น เชือก ที่ไม่ผ่าน กล่องใดๆ
		{
			
			RopeShape Ropepiece = new RopeShape(NewLine);
			if(ShapeStart != -1)
			{
				Ropepiece.setStartIndex((CommonShape) shapeContainer.getShape(ShapeStart));	
			}
			//Ropepiece.setEndIndex((CommonShape) shapeContainer.getShape(CurrentBox));		
			
			RopeCollecter.add(Ropepiece);
			
			
			
		}
		
		
		return RopeCollecter;	
	}

}
