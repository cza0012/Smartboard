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

import java.awt.Container;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import Container.ContainerAllShape;

/**
 * For checking the line . Checking the line what is shape type
 */
public class ShapeChecking {

	private ShapeChecking() {

	}

	public static boolean isTriangle(ArrayList<Point2D> Point) {
		if (Point.size() == 3) {
			for (int i = 0; i < Point.size(); i++) {
				for (int j = 0; j < Point.size(); j++) {
					if (i == j) {

					} else if (Point.get(i).getX() == Point.get(j).getX()
							&& Point.get(i).getY() == Point.get(j).getY()) {
						return false;
					}
				}
			}

			return true;
		}
		return false;
	}

	public static boolean isRectangle(ArrayList<Point2D> Point) {
		if (Point.size() == 4) {
			for (int i = 0; i < Point.size(); i++) {
				for (int j = 0; j < Point.size(); j++) {
					if (i == j) {

					} else if (Point.get(i).getX() == Point.get(j).getX()
							&& Point.get(i).getY() == Point.get(j).getY()) {
						return false;
					}
				}
			}

			return true;
		}
		return false;
	}

	public static boolean isEllipse(ArrayList<Point2D> Point) {
		if (Point.size() >= 6) {
			ArrayList<Line2D> L = new ArrayList<Line2D>();
			Polygon cir = new Polygon();
			for (int i = 0; i < Point.size(); i++) {
				int k = i + 1;
				Line2D l = new Line2D.Double();
				if (i == Point.size() - 1) {
					k = 0;
				}
				l.setLine(Point.get(i), Point.get(k));
				L.add(l);
				cir.addPoint((int) Point.get(i).getX(), (int) Point.get(i)
						.getY());
			}

			int checker = 0;
			// Point2D north,south,east,west;//Y min,Y max,X max,X min
			double yMin = Double.MAX_VALUE, yMax = 0, xMax = 0, xMin = Double.MAX_VALUE;

			for (int u = 0; u < L.size(); u++) {
				int h = u + 1;
				if (u == L.size() - 1) {
					h = 0;
				}
				double pX = (L.get(u).getX1() + L.get(h).getX2()) / 2;
				double pY = (L.get(u).getY1() + L.get(h).getY2()) / 2;
				Point2D pc = new Point2D.Double(pX, pY);

				if (cir.contains(pc)) {
					checker++;
				}
				if (Point.get(u).getX() > xMax)
					xMax = Point.get(u).getX();
				if (Point.get(u).getY() > yMax)
					yMax = Point.get(u).getY();
				if (Point.get(u).getX() < xMin)
					xMin = Point.get(u).getX();
				if (Point.get(u).getY() < yMin)
					yMin = Point.get(u).getY();
			}

			if (checker == Point.size()
					&& isCircleEdge(xMin, xMax, yMin, yMax) == true) {
				return true;
			} else {
				return false;
			}
		}

		else {
			return false;
		}

	}

	/**
	 * Check for the size of differnt for verticle and horizontal must not too
	 * different.
	 * 
	 * @param xMin
	 * @param xMax
	 * @param yMin
	 * @param yMax
	 * @return
	 */
	public static boolean isCircleEdge(Double xMin, Double xMax, Double yMin,
			Double yMax) {
		double vLength = yMax - yMin;
		double hLength = xMax - xMin;
		double max;
		double diff = Math.abs(vLength - hLength);
		if (hLength > vLength) {
			max = hLength;
		} else {
			max = vLength;
		}
		double percent = max * 50 / 100;
		if (diff < percent) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isCross(ArrayList<Line2D> Line) {
		if (Line.size() != 2) {
			return false;
		}

		Point2D IntersecPoint = AlgorTools.LineIntersection.getIntersection(
				Line.get(0), Line.get(1));
		
		if(IntersecPoint == null)
		{
			return false;
		}
		
		else if (IntersecPoint.getX() != Line.get(0).getX1()
				&& IntersecPoint.getX() != Line.get(0).getX2()
				&& IntersecPoint.getY() != Line.get(1).getY1()
				&& IntersecPoint.getY() != Line.get(1).getY2()) {
			double lengthOfLine1 = LineProperty
					.findMagnitudeOfLine(Line.get(0));
			double lengthOfLine2 = LineProperty
					.findMagnitudeOfLine(Line.get(1));

			double section1OfLine1 = Line.get(0).getP1()
					.distance(IntersecPoint);
			double section2OfLine1 = Line.get(0).getP2()
					.distance(IntersecPoint);
			double section1OfLine2 = Line.get(1).getP1()
					.distance(IntersecPoint);
			double section2OfLine2 = Line.get(1).getP2()
					.distance(IntersecPoint);

			double percentOfSec1L1 = section1OfLine1 / lengthOfLine1;
			double percentOfSec2L1 = section2OfLine1 / lengthOfLine1;
			double percentOfSec1L2 = section1OfLine2 / lengthOfLine2;
			double percentOfSec2L2 = section2OfLine2 / lengthOfLine2;
			double max = 0;
			double min = 0;

			if (lengthOfLine1 < lengthOfLine2) {
				max = lengthOfLine2;
				min = lengthOfLine1;
			}

			else {
				max = lengthOfLine1;
				min = lengthOfLine2;
			}

			double ratio = min / max;

			if (0.5 <= ratio) {
				if (0.25 <= percentOfSec1L1 && percentOfSec1L1 <= 0.75
						&& 0.25 <= percentOfSec2L1 && percentOfSec2L1 <= 0.75
						&& 0.25 <= percentOfSec1L2 && percentOfSec1L2 <= 0.75
						&& 0.25 <= percentOfSec2L2 && percentOfSec2L2 <= 0.75) {
					return true;
				}

				else {
					return false;
				}
			}

			else {
				return false;
			}
		}

		else {
			return false;
		}
	}

	public static boolean isSpring(ArrayList<Line2D> list) 
	{
		if (list.size() <= 6) 
		{
			return false;
		}
		
		for (int n = 0; n < list.size() - 1; n++) {

			/*double magnitudeL1 = LineProperty.findMagnitudeOfLine(list.get(n));
			double magnitudeL2 = LineProperty.findMagnitudeOfLine(list
					.get(n + 1));
			double max = 0;
			double min = 0;

			if (magnitudeL1 > magnitudeL2) {
				max = magnitudeL1;
				min = magnitudeL2;
			} else {
				max = magnitudeL2;
				min = magnitudeL1;
			}

			double ratio = min / max;
			double degree = LineProperty.findDegreeBetweenLines(list.get(n),
					list.get(n + 1));
			if (ratio > 0.4) {
				if (!(60 >= degree) && !(180 >= degree || degree >= 135)) {

					return false;
				}
			} else {
				return false;
			}*/
			double degree = LineProperty.findDegreeBetweenLines(list.get(n),
					list.get(n + 1));
			if (!(60 >= degree) && !(180 >= degree || degree >= 135)) 
			{
				return false;
			}
		}
		return true;
	}

	public static boolean isArrow(ArrayList<Line2D> line) {
		if (line.size() != 3) 
		{
			return false;
		}
		
		Line2D l0 = line.get(0);
		Line2D l1 = line.get(1);
		Line2D l2 = line.get(2);
		
		
		// They must be cut at the same Point
		if(l0.getX1() == l1.getX1() && l0.getY1() == l1.getY1())
		{
			if(l0.getX1() == l2.getX1() && l0.getY1() == l2.getY1())
			{
				
			}
			
			else if(l0.getX1() == l2.getX2() && l0.getY1() == l2.getY2())
			{
				
			}
			
			else
			{
				return false;
			}
			
		}
		
		else if(l0.getX1() == l1.getX2() && l0.getY1() == l1.getY2())
		{
			if(l0.getX1() == l2.getX1() && l0.getY1() == l2.getY1())
			{
				
			}
			
			else if(l0.getX1() == l2.getX2() && l0.getY1() == l2.getY2())
			{
				
			}
			
			else
			{
				return false;
			}
		}
		
		else if(l0.getX2() == l1.getX1() && l0.getY2() == l1.getY1())
		{
			if(l0.getX2() == l2.getX1() && l0.getY2() == l2.getY1())
			{
				
			}
			
			else if(l0.getX2() == l2.getX2() && l0.getY2() == l2.getY2())
			{
				
			}
			
			else
			{
				return false;
			}
		}
		
		else if(l0.getX2() == l1.getX2() && l0.getY2() == l1.getY2())
		{
			if(l0.getX2() == l2.getX1() && l0.getY2() == l2.getY1())
			{
				
			}
			
			else if(l0.getX2() == l2.getX2() && l0.getY2() == l2.getY2())
			{
				
			}
			
			else
			{
				return false;
			}
		}
		
		else
		{
			return false;
		}
		
		
		
		double degreeOfAngle1 = LineProperty.findDegreeBetweenLines(l0, l1);
		double degreeOfAngle2 = LineProperty.findDegreeBetweenLines(l1, l2);
		double degreeOfAngle3 = LineProperty.findDegreeBetweenLines(l0, l2);
		double maxAngle = degreeOfAngle1;
		double compositeAngle1 = 0;
		double compositeAngle2 = 0;

		Line2D vectorOfArrow = null;

		Line2D line1 = null;

		Line2D line2 = null;

		if (maxAngle < degreeOfAngle2) {
			maxAngle = degreeOfAngle2;
		}
		if (maxAngle < degreeOfAngle3) {
			maxAngle = degreeOfAngle3;
		}

		if (maxAngle == degreeOfAngle1) {
			vectorOfArrow = l2;
			line1 = l0;
			line2 = l1;
			compositeAngle1 = degreeOfAngle2;
			compositeAngle2 = degreeOfAngle3;
		}
		if (maxAngle == degreeOfAngle2) {
			vectorOfArrow = l0;
			line1 = l1;
			line2 = l2;
			compositeAngle1 = degreeOfAngle1;
			compositeAngle2 = degreeOfAngle3;
		}
		if (maxAngle == degreeOfAngle3) {
			vectorOfArrow = l1;
			line1 = l0;
			line2 = l2;
			compositeAngle1 = degreeOfAngle2;
			compositeAngle2 = degreeOfAngle1;
		}

		if (compositeAngle1 < 90 && compositeAngle2 < 90 && maxAngle < 140) 
		{
			double magnitudeOfVector = LineProperty
					.findMagnitudeOfLine(vectorOfArrow);
			double magnitudeOfl1 = LineProperty.findMagnitudeOfLine(line1);
			double magnitudeOfl2 = LineProperty.findMagnitudeOfLine(line2);

			double l1CompareVector = magnitudeOfl1 / magnitudeOfVector;
			double l2CompareVector = magnitudeOfl2 / magnitudeOfVector;

			if (l1CompareVector < 0.6 && l2CompareVector < 0.6) {
				return true;
			}
		}
		return false;
	}

	/**
	 * For check the shape is clock wise or not
	 * 
	 * @param allPoint
	 * @return
	 */
	public static boolean isClockwise(ArrayList<Point2D> allPoint) 
	{	
		int quadrantFirst;
		int quadrantSecond;
		Point2D firstPoint;
		Point2D secondPoint;
		
		int counterClockwise = 0;
		int counter			 = 0;
		
		for (int i = 0; i < allPoint.size(); i++) 
		{
			// 1. Get the point at stay and next one.
			if (i == (allPoint.size() - 1)) 
			{
				firstPoint = allPoint.get(i);
				secondPoint = allPoint.get(0);
				quadrantFirst = PointChecking.checkQuadrant(allPoint.get(i));
				quadrantSecond = PointChecking.checkQuadrant(allPoint.get(0));
			}

			else 
			{
				firstPoint = allPoint.get(i);
				secondPoint = allPoint.get((i + 1));
				quadrantFirst = PointChecking.checkQuadrant(allPoint.get(i));
				quadrantSecond = PointChecking.checkQuadrant(allPoint
						.get((i + 1)));
			}

			// 2. Check the Quadrant for find the clock wise or not
			if (quadrantFirst == 1 && quadrantSecond == 2) 
			{
				counterClockwise++;
				counter++;
			}

			else if (quadrantFirst == 1 && quadrantSecond == 3) 
			{
				// Is's has a problem because it's can be
				// 1 ->2 ->3 it's false.
				// 1 ->4 ->3 it's true.
				double intersectX = LineIntersection.getIntersection(
						new Line2D.Double(firstPoint, secondPoint),
						new Line2D.Double(-10000, 0, 100000, 0)).getX();
				double intersectY = LineIntersection.getIntersection(
						new Line2D.Double(firstPoint, secondPoint),
						new Line2D.Double(0, -10000, 0, 100000)).getY();

				int quadrantSum = PointChecking
						.checkQuadrant(new Point2D.Double(intersectX,
								intersectY));

				if (quadrantSum == 2) 
				{
					counterClockwise++;
				}
				counter++;
			}

			else if (quadrantFirst == 1 && quadrantSecond == 4) 
			{
				// It's ok
				counter++;
			}

			else if (quadrantFirst == 2 && quadrantSecond == 1) 
			{
				// It's ok
				counter++;
			}

			else if (quadrantFirst == 2 && quadrantSecond == 3) 
			{
				counterClockwise++;
				counter++;
			}

			else if (quadrantFirst == 2 && quadrantSecond == 4) 
			{
				// Is's has a problem because it's can be
				// 2 ->3 ->4 it's false.
				// 2 ->1 ->4 it's true.
				double intersectX = LineIntersection.getIntersection(
						new Line2D.Double(firstPoint, secondPoint),
						new Line2D.Double(-10000, 0, 100000, 0)).getX();
				double intersectY = LineIntersection.getIntersection(
						new Line2D.Double(firstPoint, secondPoint),
						new Line2D.Double(0, -10000, 0, 100000)).getY();

				int quadrantSum = PointChecking
						.checkQuadrant(new Point2D.Double(intersectX,
								intersectY));

				if (quadrantSum == 3) 
				{
					counterClockwise++;
				}
				counter++;
			}

			else if (quadrantFirst == 3 && quadrantSecond == 1) 
			{
				// Is's has a problem because it's can be
				// 3 ->4 ->1 it's false.
				// 3 ->2 ->1 it's true.
				double intersectX = LineIntersection.getIntersection(
						new Line2D.Double(firstPoint, secondPoint),
						new Line2D.Double(-10000, 0, 100000, 0)).getX();
				double intersectY = LineIntersection.getIntersection(
						new Line2D.Double(firstPoint, secondPoint),
						new Line2D.Double(0, -10000, 0, 100000)).getY();

				int quadrantSum = PointChecking
						.checkQuadrant(new Point2D.Double(intersectX,
								intersectY));

				if (quadrantSum == 4) 
				{
					counterClockwise++;
				}
				counter++;
			}

			else if (quadrantFirst == 3 && quadrantSecond == 2) 
			{
				// It's ok
				counter++;
			}

			else if (quadrantFirst == 3 && quadrantSecond == 4) 
			{
				counterClockwise++;
				counter++;
			}

			else if (quadrantFirst == 4 && quadrantSecond == 1) 
			{
				counterClockwise++;
				counter++;
			}

			else if (quadrantFirst == 4 && quadrantSecond == 2) 
			{
				// Is's has a problem because it's can be
				// 4 ->1 ->2 it's false.
				// 4 ->3 ->2 it's true.
				double intersectX = LineIntersection.getIntersection(
						new Line2D.Double(firstPoint, secondPoint),
						new Line2D.Double(-10000, 0, 100000, 0)).getX();
				double intersectY = LineIntersection.getIntersection(
						new Line2D.Double(firstPoint, secondPoint),
						new Line2D.Double(0, -10000, 0, 100000)).getY();

				int quadrantSum = PointChecking
						.checkQuadrant(new Point2D.Double(intersectX,
								intersectY));

				if (quadrantSum == 1) 
				{
					counterClockwise++;
				}
				counter++;
			}

			else if (quadrantFirst == 4 && quadrantSecond == 3) 
			{
				// It's ok
				counter++;
			}
		}
		if(counterClockwise >= (int)counter/2)
		{
			return false;
		}
		return true;
	}
}
