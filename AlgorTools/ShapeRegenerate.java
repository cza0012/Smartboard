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

import net.phys2d.raw.SpringJoint;

import SpecialShape.ArrowShape;
import SpecialShape.SpringShape;

/**
 * Regenerate the Shape that you draw in to the automate drawing
 * 
 * @author Magic Board Team
 *
 */
public class ShapeRegenerate 
{
	private ShapeRegenerate()
	{
		
	}
	
	/**
	 * For regenerate croos that you draw in the automate drawing
	 * 
	 * @param ArrayList of Line2D
	 * @return ArrayList of Line2D that contain 2 line for draw the cross that line have 5 pixels.
	 */
	public static ArrayList<Line2D> regenerateCross(ArrayList<Line2D> cross)
	{
		Point2D IntersectionPoint = LineIntersection.getIntersection(cross.get(0),cross.get(1));
		double xMinus 	= IntersectionPoint.getX() - 5;
		double xPlus 	= IntersectionPoint.getX() + 5;
		double yMinus 	= IntersectionPoint.getY() - 5;
		double yPlus 	= IntersectionPoint.getY() + 5;
		cross.set(0,new Line2D.Double(xPlus, yMinus,xMinus,yPlus));
		cross.set(1,new Line2D.Double(xMinus,yMinus,xPlus, yPlus));
		return cross;
	}
	
	/**
	 * For regenerate spring that you draw in the automate drawing
	 * 
	 * @param x1 at start point of sping
	 * @param y1 at start point of sping
	 * @param x2 at end point of sping
	 * @param y2 at end point of sping
	 * @return ArrayList of Line2D that contain line for draw the spring in period of (x1,y1) to (x2,y2).
	 */
	public static ArrayList<Line2D> regenerateSpring(double x1,double y1,double x2,double y2)
	{
		ArrayList<Line2D> collectionOfLine = new ArrayList<Line2D>();
		double m = (y2 - y1) / (x2 - x1);
		double c = y1 - (m * x1);
		double number = 10;
		double newY1 = 0;
		double newX1 = 0;
		double ymax = 0;
		double ymin = 0;
		double xmax = 0;
		double xmin = 0;

		if (y2 > y1) {
			ymax = y2;
			ymin = y1;
		} else {
			ymax = y1;
			ymin = y2;
		}

		if (x2 > x1) {
			xmax = x2;
			xmin = x1;
		} else {
			xmax = x1;
			xmin = x2;
		}

		if (Math.abs(x1 - x2) >= Math.abs(y1 - y2)) {
			int count = -1;
			while (xmin < xmax) {
				double xOriginal = xmin;
				xmin += 10;
				if (count % 2 == 0) {
					newY1 = AlgorTools.LineProperty.esstimate((m * xmin) + c + number);
				} else {
					newY1 = AlgorTools.LineProperty.esstimate((m * xmin) + c - number);
				}

				if (count == -1) {
					if (xOriginal == x1) {
						collectionOfLine.add(new Line2D.Double(x1, y1, xmin,
								newY1));
						count++;
					} else {
						collectionOfLine.add(new Line2D.Double(x2, y2, xmin,
								newY1));
						count++;
					}

				} else {
					if (xmin < xmax) {
						collectionOfLine.add(new Line2D.Double(collectionOfLine
								.get(count).getX2(), collectionOfLine
								.get(count).getY2(), xmin, newY1));
					} else {
						if (xmax == x1) {
							collectionOfLine
									.add(new Line2D.Double(
											collectionOfLine.get(count).getX2(),
											collectionOfLine.get(count).getY2(),
											x1, y1));
						} else {
							collectionOfLine
									.add(new Line2D.Double(
											collectionOfLine.get(count).getX2(),
											collectionOfLine.get(count).getY2(),
											x2, y2));
						}
					}
					count++;
				}

			}
			newY1 = AlgorTools.LineProperty.esstimate((m * x1) + c + number);

		} else {
			if (Math.abs(x1 - x2) != 0) {
				int count = -1;
				while (ymin < ymax) {
					double yOriginal = ymin;
					ymin += 10;
					if (count % 2 == 0) {
						newX1 = AlgorTools.LineProperty.esstimate(((ymin - c) / m)
								+ number);
					} else {
						newX1 = AlgorTools.LineProperty.esstimate(((ymin - c) / m)
								- number);
					}

					if (count == -1) {
						if (yOriginal == y1) {
							collectionOfLine.add(new Line2D.Double(x1, y1,
									newX1, ymin));
							count++;
						} else {
							collectionOfLine.add(new Line2D.Double(x2, y2,
									newX1, ymin));
							count++;
						}

					} else {
						if (ymin < ymax) {
							collectionOfLine.add(new Line2D.Double(
									collectionOfLine.get(count).getX2(),
									collectionOfLine.get(count).getY2(), newX1,
									ymin));
						} else {
							if (ymax == y1) {
								collectionOfLine.add(new Line2D.Double(
										collectionOfLine.get(count).getX2(),
										collectionOfLine.get(count).getY2(),
										x1, y1));
							} else {
								collectionOfLine.add(new Line2D.Double(
										collectionOfLine.get(count).getX2(),
										collectionOfLine.get(count).getY2(),
										x2, y2));
							}
						}
						count++;
					}

				}
			} else {
				int count = -1;
				while (ymin < ymax) {
					double yOriginal = ymin;
					ymin += 10;
					if (count % 2 == 0) {
						newX1 = AlgorTools.LineProperty.esstimate(x1 + number);
					} else {
						newX1 = AlgorTools.LineProperty.esstimate(x1 - number);
					}

					if (count == -1) {
						if (yOriginal == y1) {
							collectionOfLine.add(new Line2D.Double(x1, y1,
									newX1, ymin));
							count++;
						} else {
							collectionOfLine.add(new Line2D.Double(x2, y2,
									newX1, ymin));
							count++;
						}

					} else {
						if (ymin < ymax) {
							collectionOfLine.add(new Line2D.Double(
									collectionOfLine.get(count).getX2(),
									collectionOfLine.get(count).getY2(), newX1,
									ymin));
						} else {
							if (ymax == y1) {
								collectionOfLine.add(new Line2D.Double(
										collectionOfLine.get(count).getX2(),
										collectionOfLine.get(count).getY2(),
										x1, y1));
							} else {
								collectionOfLine.add(new Line2D.Double(
										collectionOfLine.get(count).getX2(),
										collectionOfLine.get(count).getY2(),
										x2, y2));
							}
						}
						count++;
					}

				}
				if (ymax == y1) {
					collectionOfLine.add(new Line2D.Double(collectionOfLine
							.get(count).getX2(), collectionOfLine.get(count)
							.getY2(), x1, y1));
				} else {
					collectionOfLine.add(new Line2D.Double(collectionOfLine
							.get(count).getX2(), collectionOfLine.get(count)
							.getY2(), x2, y2));
				}
			}
		}
		return collectionOfLine;
	}
	
	/**
	 * For regenerate spring that you physical paint
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param spring
	 * @return
	 */
	public static ArrayList<Line2D> regenerateSpring(double x1,double y1,double x2,double y2,SpringJoint spring)
	{
		ArrayList<Line2D> collectionOfLine = new ArrayList<Line2D>();

		double m = (y2 - y1) / (x2 - x1);
		double c = y1 - (m * x1);
		double number = 10;
		double newY1 = 0;
		double newX1 = 0;
		double ymax = 0;
		double ymin = 0;
		double xmax = 0;
		double xmin = 0;
		int numberSpring = spring.getNumberSpring();

		if (y2 > y1) {
			ymax = y2;
			ymin = y1;
		} else {
			ymax = y1;
			ymin = y2;
		}

		if (x2 > x1) {
			xmax = x2;
			xmin = x1;
		} else {
			xmax = x1;
			xmin = x2;
		}

		if (Math.abs(x1 - x2) >= Math.abs(y1 - y2)) {
			int count = -1;
			for (int n = 0; n <= numberSpring - 2; n++) {
				double xOriginal = xmin;
				xmin += Math.abs(x1 - x2) / numberSpring;
				if (count % 2 == 0) {
					newY1 = AlgorTools.LineProperty.esstimate((m * xmin) + c
							+ number);
				} else {
					newY1 = AlgorTools.LineProperty.esstimate((m * xmin) + c
							- number);
				}

				if (count == -1) {
					if (xOriginal == x1) {
						collectionOfLine.add(new Line2D.Double(x1, y1, xmin,
								newY1));
						count++;
					} else {
						collectionOfLine.add(new Line2D.Double(x2, y2, xmin,
								newY1));
						count++;
					}

				} else {
					if (n != numberSpring - 2) {
						collectionOfLine.add(new Line2D.Double(collectionOfLine
								.get(count).getX2(), collectionOfLine
								.get(count).getY2(), xmin, newY1));
					} else {
						if (xmax == x1) {
							collectionOfLine
									.add(new Line2D.Double(
											collectionOfLine.get(count).getX2(),
											collectionOfLine.get(count).getY2(),
											x1, y1));
						} else {
							collectionOfLine
									.add(new Line2D.Double(
											collectionOfLine.get(count).getX2(),
											collectionOfLine.get(count).getY2(),
											x2, y2));
						}
					}
					count++;
				}

			}
			newY1 = AlgorTools.LineProperty.esstimate((m * x1) + c + number);

		} else {
			if (Math.abs(x1 - x2) != 0) {
				int count = -1;
				for (int n = 0; n <= numberSpring - 2; n++) {
					double yOriginal = ymin;
					ymin += Math.abs(y1 - y2) / numberSpring;
					if (count % 2 == 0) {
						newX1 = AlgorTools.LineProperty.esstimate(((ymin - c) / m)
								- number);
					} else {
						newX1 = AlgorTools.LineProperty.esstimate(((ymin - c) / m)
								+ number);
					}

					if (count == -1) {
						if (yOriginal == y1) {
							collectionOfLine.add(new Line2D.Double(x1, y1,
									newX1, ymin));
							count++;
						} else {
							collectionOfLine.add(new Line2D.Double(x2, y2,
									newX1, ymin));
							count++;
						}

					} else {
						if (n != numberSpring - 2) {
							collectionOfLine.add(new Line2D.Double(
									collectionOfLine.get(count).getX2(),
									collectionOfLine.get(count).getY2(), newX1,
									ymin));
						} else {
							if (ymax == y1) {
								collectionOfLine.add(new Line2D.Double(
										collectionOfLine.get(count).getX2(),
										collectionOfLine.get(count).getY2(),
										x1, y1));
							} else {
								collectionOfLine.add(new Line2D.Double(
										collectionOfLine.get(count).getX2(),
										collectionOfLine.get(count).getY2(),
										x2, y2));
							}
						}
						count++;
					}

				}
			} else {
				int count = -1;
				for (int n = 0; n <= numberSpring - 2; n++) {
					double yOriginal = ymin;
					ymin += Math.abs(y1 - y2) / numberSpring;
					if (count % 2 == 0) {
						newX1 =AlgorTools.LineProperty.esstimate(x1 + number);
					} else {
						newX1 = AlgorTools.LineProperty.esstimate(x1 - number);
					}

					if (count == -1) {
						if (yOriginal == y1) {
							collectionOfLine.add(new Line2D.Double(x1, y1,
									newX1, ymin));
							count++;
						} else {
							collectionOfLine.add(new Line2D.Double(x2, y2,
									newX1, ymin));
							count++;
						}

					} else {
						if (n != numberSpring - 2) {
							collectionOfLine.add(new Line2D.Double(
									collectionOfLine.get(count).getX2(),
									collectionOfLine.get(count).getY2(), newX1,
									ymin));
						} else {
							if (ymax == y1) {
								collectionOfLine.add(new Line2D.Double(
										collectionOfLine.get(count).getX2(),
										collectionOfLine.get(count).getY2(),
										x1, y1));
							} else {
								collectionOfLine.add(new Line2D.Double(
										collectionOfLine.get(count).getX2(),
										collectionOfLine.get(count).getY2(),
										x2, y2));
							}
						}
						count++;
					}

				}
			}
		}
		return collectionOfLine;
	}

	
	/**
	 * Regenerate arrow for the axis direction
	 * @param arrow
	 * @return
	 */
	public static ArrowShape regenerateArrow(ArrayList<Polygon> arrow,Point2D intersect)
	{
		Polygon centerBlock = arrow.get(0);
		ArrayList<Point2D> pointList = ShapeConvert.convertPolygontoPoint2D(centerBlock);
		
		pointList.remove(pointList.size()-1);
		
		Point2D tailLeft  = null;
		Point2D tailRight = null;
		
		int select = 0;
		
		for(int i =0;i<pointList.size();i++)
		{
			if(tailLeft == null)
			{
				tailLeft = pointList.get(i);
				select   = i;
			}
			
			else if(LineProperty.findMagnitudeOfLine(new Line2D.Double(intersect.getX(),intersect.getY(),tailLeft.getX(),tailLeft.getY())) 
				  < LineProperty.findMagnitudeOfLine(new Line2D.Double(intersect.getX(),intersect.getY(),pointList.get(i).getX(),pointList.get(i).getY())))
			{
				tailLeft = pointList.get(i);
				select   = i;
			}
		}
		
		pointList.remove(select);
		
		for(int i =0;i<pointList.size();i++)
		{
			if(tailRight == null)
			{
				tailRight = pointList.get(i);
				select = 0;
			}
			
			else if(LineProperty.findMagnitudeOfLine(new Line2D.Double(intersect.getX(),intersect.getY(),tailRight.getX(),tailRight.getY())) 
				  < LineProperty.findMagnitudeOfLine(new Line2D.Double(intersect.getX(),intersect.getY(),pointList.get(i).getX(),pointList.get(i).getY())))
			{
				tailRight = pointList.get(i);
				select = i;
			}
		}
		
		double x = (double)(tailLeft.getX() + tailRight.getX())/2;
		double y = (double)(tailLeft.getY() + tailRight.getY())/2;
		Point2D tailPoint = new Point2D.Double(x,y);

		Line2D cenLine = new Line2D.Double(intersect.getX(),intersect.getY(),tailPoint.getX(),tailPoint.getY());
		double degree = LineProperty.getForceLineAngle(cenLine.getX1(),cenLine.getY1(), cenLine.getX2(), cenLine.getY2());
		
		// degree == 0
		if( (degree >=0 && degree <= 45)|| (degree >= 315 && degree <= 360))
		{
			ArrayList<Line2D> line = new ArrayList<Line2D>();
			
			line.add(new Line2D.Double(0,30,30,30));
			line.add(new Line2D.Double(30,30,25,25));
			line.add(new Line2D.Double(30,30,25,35));
			ArrowShape reArrow = new ArrowShape(line);
			
			Point2D newIntersect =reArrow.getPointIntersection();
			
			double xnew = intersect.getX() - newIntersect.getX();
			double ynew = intersect.getY() - newIntersect.getY();
			
			reArrow.setTranslate((int)xnew,(int)ynew);
			reArrow.setDegree(0);
			return reArrow;
		}
		
		// degree == 90
		else if(degree >= 45 && degree <= 135)
		{
			ArrayList<Line2D> line = new ArrayList<Line2D>();
			
			line.add(new Line2D.Double(30,30,30,60));
			line.add(new Line2D.Double(30,30,25,35));
			line.add(new Line2D.Double(30,30,35,35));
			ArrowShape reArrow = new ArrowShape(line);
			
			Point2D newIntersect =reArrow.getPointIntersection();
			
			double xnew = intersect.getX() - newIntersect.getX();
			double ynew = intersect.getY() - newIntersect.getY();
			
			reArrow.setTranslate((int)xnew,(int)ynew);
			reArrow.setDegree(90);
			return reArrow;
		}
	
		// degree == 180
		else if(degree >= 135 && degree <= 225)
		{
			ArrayList<Line2D> line = new ArrayList<Line2D>();
			
			line.add(new Line2D.Double(30,30,60,30));
			line.add(new Line2D.Double(30,30,35,25));
			line.add(new Line2D.Double(30,30,35,35));
			ArrowShape reArrow = new ArrowShape(line);
			
			Point2D newIntersect =reArrow.getPointIntersection();
			
			double xnew = intersect.getX() - newIntersect.getX();
			double ynew = intersect.getY() - newIntersect.getY();
			
			reArrow.setTranslate((int)xnew,(int)ynew);
			reArrow.setDegree(180);
			return reArrow;
		}
		
		// degree == 270
		else
		{
			ArrayList<Line2D> line = new ArrayList<Line2D>();
			
			line.add(new Line2D.Double(30,30,30,60));
			line.add(new Line2D.Double(30,60,25,55));
			line.add(new Line2D.Double(30,60,35,55));
			ArrowShape reArrow = new ArrowShape(line);
			
			Point2D newIntersect = reArrow.getPointIntersection();
			
			double xnew = intersect.getX() - newIntersect.getX();
			double ynew = intersect.getY() - newIntersect.getY();
			
			reArrow.setTranslate((int)xnew,(int)ynew);
			reArrow.setDegree(270);
			return reArrow;
		}
	}

}
