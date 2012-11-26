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




public class LineProperty 
{
	
	private LineProperty()
	{
		
	}
	
	/**
	 * Check that your point are near in radian of bigining point
	 * @param AllLine
	 * @param exceptPoint
	 * @param AtPoint
	 * @return
	 */
	public static Point2D InLineDistance(ArrayList<ArrayList<Line2D>> AllLine,Point2D exceptPoint, Point2D AtPoint)
	{
		Point2D NearPoint = null;
		double distance = -1;
		
		for(int i = 0;i<AllLine.size();i++)
		{
			for(int j = 0;j<AllLine.get(i).size();j++)
			{
				Line2D L = AllLine.get(i).get(j);
				
				if(L.getP1() != exceptPoint && Line2D.ptSegDist(AtPoint.getX(), AtPoint.getY(), AtPoint.getX(), AtPoint.getY(), L.getX1(), L.getY1()) <= 4)
				{
					double newdistance = Line2D.ptSegDist(AtPoint.getX(), AtPoint.getY(), AtPoint.getX(), AtPoint.getY(), L.getX1(), L.getY1());
					if(distance == -1)
					{
						distance  = newdistance;
						NearPoint = AllLine.get(i).get(j).getP1(); 
					}
					
					else if(distance > newdistance)
					{
						distance  = newdistance;
						NearPoint = AllLine.get(i).get(j).getP1();
					}
				}
				
				else if(L.getP2() != exceptPoint && Line2D.ptSegDist(AtPoint.getX(), AtPoint.getY(), AtPoint.getX(), AtPoint.getY(), L.getX2(), L.getY2()) <= 4)
				{
					double newdistance = Line2D.ptSegDist(AtPoint.getX(), AtPoint.getY(), AtPoint.getX(), AtPoint.getY(), L.getX2(), L.getY2());
					if(distance == -1)
					{
						distance  = newdistance;
						NearPoint = AllLine.get(i).get(j).getP2(); 
					}
					
					else if(distance > newdistance)
					{
						distance  = newdistance;
						NearPoint = AllLine.get(i).get(j).getP2();
					}
				}
				
			}
		}
		
		return NearPoint;
	}
	
	/**
	 * It used to eliminate decimal points of a double number.
	 * @param numberInput The number will be esstimate.
	 * @return The esstimate result.
	 */
	public static double esstimate(double numberInput) 
	{
		double numberFloor = Math.floor(numberInput);
		double r1 = Math.floor(numberInput * 10);
		double m1 = r1 % 10;

		double r2 = Math.floor(numberInput * 100);
		double m2 = r2 % 10;

		if (5 <= m2) 
		{
			m1 += 1;
		}
		
		if (5 <= m1) 
		{
			numberFloor += 1;
		}

		return numberFloor;
	}
	
	/**
	 * Find the magnitude of line
	 * @param l1
	 * @return
	 */
	public static double findMagnitudeOfLine(Line2D l1) 
	{
		double x1 = l1.getX1();
		double y1 = l1.getY1();
		double x2 = l1.getX2();
		double y2 = l1.getY2();
		// Equation of line1
		double il1 = (x2 - x1);
		double jl1 = (y2 - y1);
		return Math.sqrt(Math.pow(il1, 2) + Math.pow(jl1, 2));
	}
	
	/**
	 * Fine the degree between two line.
	 * @param l1
	 * @param l2
	 * @return
	 */
	public static double findDegreeBetweenLines(Line2D l1, Line2D l2) {

		if (l1.getP1().equals(l2.getP2())) {
			l2 = new Line2D.Double(l2.getP2(), l2.getP1());
		} else if (l1.getP2().equals(l2.getP1())) {
			l1 = new Line2D.Double(l1.getP2(), l1.getP1());
		}

		double x1 = l1.getX1();
		double y1 = l1.getY1();
		double x2 = l1.getX2();
		double y2 = l1.getY2();
		// Equation of line1
		double il1 = (x2 - x1);
		double jl1 = (y2 - y1);
		// double magnitudeL1 = Math.sqrt(Math.pow(il1, 2) + Math.pow(jl1, 2));

		double magnitudeL1 = LineProperty.findMagnitudeOfLine(l1);

		// Begin and finish point of line2
		double x1l2 = l2.getX1();
		double y1l2 = l2.getY1();
		double x2l2 = l2.getX2();
		double y2l2 = l2.getY2();
		// Equation of line2
		double il2 = (x2l2 - x1l2);
		double jl2 = (y2l2 - y1l2);
		// double magnitudeL2 = Math.sqrt(Math.pow(il2, 2) + Math.pow(jl2, 2));

		double magnitudeL2 = LineProperty.findMagnitudeOfLine(l2);

		double dotResult = (il1 * il2) + (jl1 * jl2);
		double radious = Math.toDegrees(Math.acos(dotResult
				/ (magnitudeL1 * magnitudeL2)));

		return radious;

	}
	
	public static double findDegreeBetweenLinesArrow(Line2D l1, Line2D l2) 
	{
		double x1 = l1.getX1();
		double y1 = l1.getY1();
		double x2 = l1.getX2();
		double y2 = l1.getY2();
		// Equation of line1
		double il1 = (y2 - y1);
		double jl1 = (x2 - x1);
		// double magnitudeL1 = Math.sqrt(Math.pow(il1, 2) + Math.pow(jl1, 2));

		double magnitudeL1 = LineProperty.findMagnitudeOfLine(l1);

		// Begin and finish point of line2
		double x1l2 = l2.getX1();
		double y1l2 = l2.getY1();
		double x2l2 = l2.getX2();
		double y2l2 = l2.getY2();
		// Equation of line2
		double il2 = (y2l2 - y1l2);
		double jl2 = (x2l2 - x1l2);
		// double magnitudeL2 = Math.sqrt(Math.pow(il2, 2) + Math.pow(jl2, 2));

		double magnitudeL2 = LineProperty.findMagnitudeOfLine(l2);

		double dotResult = (il1 * il2) + (jl1 * jl2);
		double radious = Math.toDegrees(Math.acos(dotResult
				/ (magnitudeL1 * magnitudeL2)));

		return radious;

	}
	
	
	/**
	 * Calculate the angle of the line
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return angle of the line
	 */
	public static double getLineAngle(double x1,double y1,double x2,double y2)
	{
		/**
		 *  Horizontal line
		 *  angle = 0
		 *  m	  = 0
		 */
		if(y2 - y1 == 0)
		{
			return 0;
		}
		
		/**
		 *  Verticle line
		 *  angle	= 90
		 *  m 		= can't tell
		 */
		else if(x2 - x1 == 0)
		{
			return 90;
		}
		
		else
		{
			double m = (y1-y2)/(x2-x1);
		
			// m<0 then angle is 90<angle<180
			if(m < 0)
			{ 
				m = Math.abs(m);
				double atan   = Math.atan(m);
				double degree = 180-((180/Math.PI)*atan);
				
				return degree;
				
			}
			else
			{
				m = Math.abs(m);
				double atan   = Math.atan(m);
				double degree = (180/Math.PI)*atan;
				
				return degree;
			}	
		}
	}
	
	
	/**
	 * Calculate the force angle of the line
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return angle of the line
	 */
	public static double getForceLineAngle(double x1,double y1,double x2,double y2)
	{
		/**
		 *  Horizontal line
		 *  angle = 0
		 *  m	  = 0
		 */
		if(y2 - y1 == 0)
		{
			if(x1 > x2)
			{
				return 0;
			}
			
			else
				return 180;
		}
		
		/**
		 *  Verticle line
		 *  angle	= 90
		 *  m 		= can't tell
		 */
		else if(x2 - x1 == 0)
		{
			if(y1 > y2)
			{
				return 270;
			}
			
			else
				return 90;
		}
		
		else
		{
			double m = (y1-y2)/(x2-x1);
		
			// m<0 then angle is 90<angle<180
			if(m < 0)
			{ 
				m = Math.abs(m);
				double atan   = Math.atan(m);
				double degree = 180-((180/Math.PI)*atan);
				
				if(y1 > y2)
				{
					return degree+180;
				}
				
				else
					return degree;
				
			}
			
			else
			{
				m = Math.abs(m);
				double atan   = Math.atan(m);
				double degree = (180/Math.PI)*atan;
				
				if(y1 > y2)
				{
					return degree+180;
				}
				
				else
					return degree;
			}
			
		}
	}
	
	
	
	/**
	 * For find Y value from x value.
	 * @param l1
	 * @param y
	 * @return
	 */
	public static double findYValueFromXValue(Line2D l1, Double y) 
	{
		double x1 = l1.getX1();
		double y1 = l1.getY1();
		double x2 = l1.getX2();
		double y2 = l1.getY2();
		double m = 0;
		double c = 0;
		double newX = x1;

		if (Math.abs(x1 - x2) != 0) 
		{
			m = (y2 - y1) / (x2 - x1);
			c = y1 - (m * x1);
			newX = (y - c) / m;
		}
		return newX;
	}
	
	/**
	 * Get the parallel line by set original line and distance. 
	 * @param l1
	 * @param number
	 * @return
	 */
	public static Line2D getParallelLine(Line2D l1, Double number) {
		double x1 = l1.getX1();
		double y1 = l1.getY1();
		double x2 = l1.getX2();
		double y2 = l1.getY2();
		double m = (y2 - y1) / (x2 - x1);
		double c = y1 - (m * x1);
		double newY1 = 0;
		double newY2 = 0;
		double newX1 = 0;
		double newX2 = 0;

		if (Math.abs(x1 - x2) >= Math.abs(y1 - y2)) {
			newY1 = esstimate((m * x1) + c + number);
			newY2 = esstimate((m * x2) + c + number);
			return new Line2D.Double(x1, newY1, x2, newY2);
		} else {
			if (Math.abs(x1 - x2) != 0) {
				newX1 = esstimate(((y1 - c) / m) + number);
				newX2 = esstimate(((y2 - c) / m) + number);
			} else {
				newX1 = esstimate(x1 + number);
				newX2 = esstimate(x2 + number);
			}
			return new Line2D.Double(newX1, y1, newX2, y2);
		}

	}
}
