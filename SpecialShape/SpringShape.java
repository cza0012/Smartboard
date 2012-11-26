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
package SpecialShape;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import CommonShape.CommonShape;
import Container.ContainerAllShape;

/**
 * 
 * @author VAIO
 *
 */
public class SpringShape extends SpecialShape implements Cloneable
{
	private CommonShape startIndex;
	private CommonShape endIndex;
	/** distance beteween spring point and cm of shape that contain spring **/
	private Point2D     startPoint;
	private Point2D     endPoint;
	
	private int         numberSpring; 
	private float		kSpring = 100; // ค่าความยืดของสปริง
	
	private int setSelect = -1;
  
	/**
	* Create instance of SpringShape shape by using arraylist of line2D
	* 
	* @param line
	*/
	public SpringShape()
	{
		
	}
	public SpringShape(CommonShape startIndex,CommonShape endIndex)
	{
		this.type   = "spring";
		this.startIndex = startIndex;
		this.endIndex   = endIndex;
		this.startPoint = new Point2D.Double(0,0);
		this.endPoint   = new Point2D.Double(0,0);
		
		this.shape  = new ArrayList<Line2D>();
		this.shape.add(new Line2D.Double(
		new Point2D.Double(this.startIndex.getCM().getX()-this.startPoint.getX(),this.startIndex.getCM().getY()-this.startPoint.getY()),
	    new Point2D.Double(this.endIndex.getCM().getX()-this.endPoint.getX(),this.endIndex.getCM().getY()-this.endPoint.getY())));
		reBuild();
	}
	public void setNodePrepare(CommonShape startIndex,CommonShape endIndex)
	{
		this.startIndex = startIndex;
		this.endIndex   = endIndex;
		doPrepare();
	}
	public void doPrepare()
	{
		this.type   = "spring";
		
		this.startPoint = new Point2D.Double(0,0);
		this.endPoint   = new Point2D.Double(0,0);
		
		this.shape  = new ArrayList<Line2D>();
		this.shape.add(new Line2D.Double(
		new Point2D.Double(this.startIndex.getCM().getX()-this.startPoint.getX(),this.startIndex.getCM().getY()-this.startPoint.getY()),
	    new Point2D.Double(this.endIndex.getCM().getX()-this.endPoint.getX(),this.endIndex.getCM().getY()-this.endPoint.getY())));
		reBuild();
	}
	/**
	 * Get the stretch value of Spring
	 * @return
	 */
	public float getKSpring() 
	{
		return kSpring;
	}

	/**
	 * Set the stretch value of Spring
	 * @param spring
	 */
	public void setKSpring(float spring) {
		kSpring = spring;
	}

	
	/**
	 * set the select vertex
	 *  -1 if none
	 *   0 if start index
	 *   1 if end index
	 * @param i
	 */
	public void setSelect(int i)
	{
		this.setSelect = i;
	}
	
	/**
	 * get the select vertex
	 *  -1 if none
	 *   0 if start index
	 *   1 if end index 
	 * @return
	 */
	public int getSelect()
	{
		return this.setSelect;
	}
	
	/**
	 * Get Start bound for check the selection
	 * @return
	 */
	public Ellipse2D getStartBound()
	{	
		return new Ellipse2D.Double((int)(this.startIndex.getCM().getX()-this.startPoint.getX())-4, (int)(this.startIndex.getCM().getY()-this.startPoint.getY())-4, 8, 8);
	}
	
	/**
	 * Get end bound for check the selection
	 * @return
	 */
	public Ellipse2D getEndBound()
	{	
		return new Ellipse2D.Double((int)(this.endIndex.getCM().getX()-this.endPoint.getX())-4, (int)(this.endIndex.getCM().getY()-this.endPoint.getY())-4, 8, 8);
	}
	
	/**
	 * Get the start Point object that struct with spring.
	 * @return index of object.
	 */
	public Point2D getStartPoint()
	{
		return this.startPoint;
	}
	
	/**
	 * Get the end Point object that struct with spring.
	 * @return index of object.
	 */
	public Point2D getEndPoint()
	{
		return this.endPoint;
	}
	
	/**
	 * set start Point of the object that struct with the spring at start point.
	 * @param startIndex
	 */
	public void setStartPoint(Point2D startPoint)
	{
		double x = this.startIndex.getCM().getX() - startPoint.getX();
		double y = this.startIndex.getCM().getY() - startPoint.getY();
		this.startPoint.setLocation(x, y);
	}
	
	/**
	 * set end Point of the object that struct with the spring at end point.
	 * @param endIndex
	 */
	public void setEndPoint(Point2D endPoint)
	{
		double x = this.endIndex.getCM().getX() - endPoint.getX();
		double y = this.endIndex.getCM().getY() - endPoint.getY();
		this.endPoint.setLocation(x, y);
	}
	

	/**
	 * Get the start index object that struct with spring.
	 * @return index of object.
	 */
	public CommonShape getStartIndex()
	{
		return this.startIndex;
	}
	
	/**
	 * Get the end index object that struct with spring.
	 * @return index of object.
	 */
	public CommonShape getEndIndex()
	{
		return this.endIndex;
	}
	
	/**
	 * set start index of the object that struct with the spring at start point.
	 * @param startIndex
	 */
	public void setStartIndex(CommonShape startIndex)
	{
		this.startIndex = startIndex;
	}
	
	/**
	 * set end index of the object that struct with the spring at end point.
	 * @param endIndex
	 */
	public void setEndIndex(CommonShape endIndex)
	{
		this.endIndex = endIndex;
	}
	
	/**
	 * Translate Start Point of Spring
	 * @param x
	 * @param y
	 */
	public void setTranslateStartPoint(int x,int y)
	{
		this.startPoint.setLocation(this.startPoint.getX()+x,this.startPoint.getY()+y);
	}
	
	/**
	 * Translate End Point of Spring
	 * @param x
	 * @param y
	 */
	public void setTranslateEndPoint(int x,int y)
	{
		this.endPoint.setLocation(this.endPoint.getX()+x,this.endPoint.getY()+y);
	}
	
	/**
	 * Get number of joint that connect to spring
	 * @return
	 */
	public int getNumberSpring() 
	{
		double x1 = this.startIndex.getCM().getX() - startPoint.getX();
		double y1 = this.startIndex.getCM().getY() - startPoint.getY();
		double x2 = this.endIndex.getCM().getX() - endPoint.getX();
		double y2 = this.endIndex.getCM().getY() - endPoint.getY();
		if(Math.abs(x1 - x2) >= Math.abs(y1 - y2))
		{
			this.numberSpring = (int) (Math.abs(x1 - x2) / 10);
		}
		else
		{
			this.numberSpring = (int) (Math.abs(y1 - y2) / 10);
		}
		return numberSpring;
	}
	
	/**
	 * Set number of joint that connect to spring
	 * @param numberSpring
	 */
	public void setNumberSpring(int numberSpring) 
	{
		this.numberSpring = numberSpring;
	}

	
	@Override
	public ArrayList<Line2D> getShape()
	{
		this.shape  = new ArrayList<Line2D>();
		this.shape.add(new Line2D.Double(this.startPoint,this.endPoint));
		reBuild();
		return this.shape;
	}
	
	@Override
	public void setTranslate(int x,int y)
	{

	}
	
	@Override
	protected void reBuild() 
	{
		double x1 = this.getStartIndex().getCM().getX()-this.shape.get(0).getX1();
		double y1 = this.getStartIndex().getCM().getY()-this.shape.get(0).getY1();
		double x2 = this.getEndIndex().getCM().getX()-this.shape.get(this.shape.size()-1).getX2();
		double y2 = this.getEndIndex().getCM().getY()-this.shape.get(this.shape.size()-1).getY2();
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
		
		this.shape = collectionOfLine;
	}
	

	public void rotate() 
	{
		
	}
	
	@Override
	public void scalling() 
	{
		
	}
	
	public Object clone(ContainerAllShape real,ContainerAllShape clone) 
    {
		SpringShape obj = (SpringShape)super.clone();
		
		for(int i =0;i<real.getShapeContainerSize();i++)
		{
			if(real.getShape(i) == this.startIndex)
			{
				obj.startIndex	  = (CommonShape) clone.getShape(i);
			}
			
			else if(real.getShape(i) == this.endIndex)
			{
				obj.endIndex		= (CommonShape) clone.getShape(i);
			}
		}
		obj.startPoint  = (Point2D) this.startPoint.clone();
		obj.endPoint	= (Point2D) this.endPoint.clone();
		return obj;	
    }
	
}
