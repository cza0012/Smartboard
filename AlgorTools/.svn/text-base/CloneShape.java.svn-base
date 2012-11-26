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

import TempObject.LineIndex;

public class CloneShape 
{
	private CloneShape()
	{
		
	}
	
	/**
	 * Clone the polygon object.
	 * @param polygon
	 * @return
	 */
	public static Polygon clonePolygon(Polygon polygon)
	{
		ArrayList<Point2D> pointList = ShapeConvert.convertPolygontoPoint2D(polygon);
		Polygon temp = new Polygon();
		for(int i =0;i<pointList.size();i++)
		{
			temp.addPoint((int)pointList.get(i).getX(),(int)pointList.get(i).getY());
		}
		return temp;
	}
	
	/**
	 * Clone the line container object.
	 * @return
	 */
	public static ArrayList<ArrayList<Line2D>> cloneLineContainer(ArrayList<ArrayList<Line2D>> lineContainer)
	{
		ArrayList<ArrayList<Line2D>> temp = new ArrayList<ArrayList<Line2D>>();
		for(int i =0;i<lineContainer.size();i++)
		{
			ArrayList<Line2D> temp2 = new ArrayList<Line2D>();
			for(int j=0;j<lineContainer.get(i).size();j++)
			{
				temp2.add((Line2D) lineContainer.get(i).get(j).clone());
			}
			temp.add(temp2);
		}
		return temp;
	}
	
	
	public static ArrayList<Integer> cloneArrayListInteger(ArrayList<Integer> obj)
	{
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i =0;i<obj.size();i++)
		{
			temp.add(new Integer(obj.get(i)));
		}
		return temp;
	}
	
	
	public static ArrayList<LineIndex> cloneArrayListLineIndex(ArrayList<LineIndex> obj)
	{
		ArrayList<LineIndex> temp = new ArrayList<LineIndex>();
		for(int i =0;i<obj.size();i++)
		{
			temp.add((LineIndex) obj.get(i).clone());
		}
		return temp;
	}
	
	public static ArrayList<Point2D> cloneArrayListPoint2D(ArrayList<Point2D> obj)
	{
		ArrayList<Point2D> temp = new ArrayList<Point2D>();
		for(int i =0;i<obj.size();i++)
		{
			temp.add((Point2D)obj.get(i).clone());
		}
		return temp;
	}
}








