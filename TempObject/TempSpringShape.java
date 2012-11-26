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
package TempObject;


import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class TempSpringShape 
{
	private Point2D startIndex;
	private Point2D endIndex;
	private ArrayList<Line2D> shape;
	
	
	public TempSpringShape(Point2D startIndex,Point2D endIndex)
	{
		this.startIndex = startIndex;
		this.endIndex   = endIndex;
		reBuild();
	}
	
	/**
	 * Translate the shape from x and y pixels.
	 * @param x Length x
	 * @param y Length y
	 */
	public void setTranslate(int x,int y)
	{
		for(int i = 0;i<this.shape.size();i++)
		{
			this.shape.get(i).setLine(this.shape.get(i).getX1()+x,this.shape.get(i).getY1()+y,this.shape.get(i).getX2()+x,this.shape.get(i).getY2()+y);	
		}
	}
	
	/**
	 * Get spring shape
	 * @return
	 */
	public ArrayList<Line2D> getShape()
	{
		return this.shape;
	}
	
	/**
	 * Get StartIndex of spring
	 * @return
	 */
	public Point2D getStartIndex()
	{
		return startIndex;
	}

	/**
	 * Get EndIndex of spring
	 * @return
	 */
	public Point2D getEndIndex() 
	{
		return endIndex;
	}
	
	/**
	 * Set StartIndex of Spring
	 * @param startIndex
	 */
	public void setStartIndex(Point2D startIndex) 
	{
		this.startIndex = startIndex;
	}
	
	/**
	 * Set EndIndex of Spring
	 * @param endIndex
	 */
	public void setEndIndex(Point2D endIndex) 
	{
		this.endIndex = endIndex;
	}
	
	/**
	 * For rebuild the spring shape
	 *
	 */
	protected void reBuild() 
	{
		double x1 = this.getStartIndex().getX();
		double y1 = this.getStartIndex().getY();
		double x2 = this.getEndIndex().getX();
		double y2 = this.getEndIndex().getY();
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
			} 
			else 
			{
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
		
		this.shape = collectionOfLine;
	}
	

}
