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
package Rotation;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import AlgorTools.LineProperty;


public class PolygonRotationStrategy
{

	public Shape rotate(Point2D p1, Point2D p2, Point2D cm,Shape objectShape) {
		ArrayList<Point2D> point2DList = new ArrayList<Point2D>();
		Polygon currentPolygon = (Polygon) objectShape;
		int[] aryX = currentPolygon.xpoints;
		int[] aryY = currentPolygon.ypoints;

		for (int n = 0; n < currentPolygon.npoints; n++) {
			point2DList.add(new Point2D.Double(aryX[n], aryY[n]));
		}

		Line2D line1 = new Line2D.Double(p1, cm);
		Line2D line2 = new Line2D.Double(p2, cm);

		double degree = LineProperty
				.findDegreeBetweenLines(line1, line2);
		System.out.println("degree:" + LineProperty.esstimate(degree));
		AffineTransform atxV = null;
		double x180 = findYValueFromXValue(line1, p2.getY());
		if (x180 > p2.getX()) {
			atxV = AffineTransform.getRotateInstance(Math
					.toRadians(0 - LineProperty.esstimate(degree)), cm
					.getX(), cm.getY());
			System.out.println("Anti Clock wise p1X = " + p1.getX()
					+ ", p2X = " + p2.getX());
		} else {
			atxV = AffineTransform.getRotateInstance(Math
					.toRadians(0 + LineProperty.esstimate(degree)), cm
					.getX(), cm.getY());
			System.out.println("Clock wise p1X = " + p1.getX() + ", p2X = "
					+ p2.getX());
		}

		return atxV.createTransformedShape(objectShape);
	}

	public double findYValueFromXValue(Line2D l1, Double y) {
		double x1 = l1.getX1();
		double y1 = l1.getY1();
		double x2 = l1.getX2();
		double y2 = l1.getY2();
		double m = 0;
		double c = 0;
		double newX = x1;

		if (Math.abs(x1 - x2) != 0) {
			m = (y2 - y1) / (x2 - x1);
			c = y1 - (m * x1);
			newX = (y - c) / m;
		}

		return newX;

	}
}
