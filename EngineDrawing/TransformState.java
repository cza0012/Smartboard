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
package EngineDrawing;

import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import IconMenu.RecycleBin;
import SpecialShape.ArrowShape;
import SpecialShape.CrossShape;
import SpecialShape.JointShape;
import SpecialShape.RopeShape;
import SpecialShape.SpringShape;
import TempObject.LineIndex;
import TempObject.TempFixJointShape;
import TempObject.TempSpringShape;
import UserInterface.Mouse;

import AlgorTools.ShapeConvert;
import AlgorTools.ShapeIntersection;
import CommonShape.CommonShape;
import CommonShape.EllipseShape;
import CommonShape.PolygonShape;
import CommonShape.RectangleShape;
import CommonShape.TriangleShape;
import Container.ContainerAllShape;

public class TransformState extends State
{

	public TransformState()
	{
		this.stateName = "Transform";
	}
	
	public void handle(Mouse mouse)
	{
		// Release Rigth State
		if(mouse.getmouseLeft()   == 0
		&& mouse.getmouseRight()  == 0
		&& mouse.getmouseDrag()   == 0)
		{
			System.out.println("Change state to Release Right State");
			mouse.setState(new ReleaseRightState());
		}
	}
	
	/**
	 * Run state for drawing point
	 * @param start
	 * @param end
	 * @param iSelectIndex
	 * @param gSelectIndex
	 * @param shapeContainer
	 */
	public void runState(Point end,ContainerAllShape shapeContainer,Mouse mouse,ArrayList<ArrayList<Line2D>> lineContainer,RecycleBin bin)
	{
		mouse.setPreviousState(this.stateName);
		
        int x = end.x - mouse.getStartDragPoint().x;
        int y = end.y - mouse.getStartDragPoint().y;
        
        if(mouse.getIndividualLineSelectIndex().getI() != -1 
        && mouse.getIndividualLineSelectIndex().getJ() != -1)
        {
        	Line2D line = lineContainer.get(mouse.getIndividualLineSelectIndex().getI()).get(mouse.getIndividualLineSelectIndex().getJ());
        	double x1 = line.getX1()+x;
    		double y1 = line.getY1()+y;
    		double x2 = line.getX2()+x;
    		double y2 = line.getY2()+y;
    		line.setLine(x1, y1, x2, y2);
        }
        
        // transform clone shape
        if(mouse.getTempShape().size() != 0)
        {
    		for(int i =0;i<mouse.getTempShape().size();i++)
    		{
        		// Translate Cross Shape
    			if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.CrossShape"))
    			{
    				CrossShape crossClone = (CrossShape)mouse.getTempShape().get(i);
        			crossClone.setTranslate(x, y);	
    			}
    			// Translate Rope Shape
    			else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.RopeShape"))
    			{
    				RopeShape ropeShape = (RopeShape)mouse.getTempShape().get(i);
    				ropeShape.setTranslate(x, y);
    			}
    			// Translate Spring Shape
    			else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("TempObject.TempSpringShape"))
    			{
    				TempSpringShape springClone = (TempSpringShape)mouse.getTempShape().get(i);
        			springClone.setTranslate(x, y);
    			}
    			// Translate fix joint shape
    			else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("TempObject.TempFixJointShape"))
    			{
    				TempFixJointShape fixJointClone = (TempFixJointShape)mouse.getTempShape().get(i);
    				fixJointClone.setTranslate(x, y);
    			}
    		}
        }
        
        // transform individual shape
        else if(mouse.getIndividualSelectIndex() != -1)
        {
        	if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.TriangleShape"))
        	{	
    			TriangleShape obj = (TriangleShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());
                
    			if(obj.canTranslate() == true)
    			{
    				obj.setTranslate(x, y);
                    mouse.setTouchShape(ShapeIntersection.shapeIntersectEdge(shapeContainer,mouse.getIndividualSelectIndex()));	    		
    			}
        	}
        	
        	else if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.RectangleShape"))
        	{

    			RectangleShape obj = (RectangleShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());
    			if(obj.canTranslate() == true)
    			{
    				obj.setTranslate(x, y);
                    mouse.setTouchShape(ShapeIntersection.shapeIntersectEdge(shapeContainer,mouse.getIndividualSelectIndex()));	    						
    			}
        	}
        	
        	else if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
        	{
    			EllipseShape obj = (EllipseShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());
    			if(obj.canTranslate() == true)
    			{
    				obj.setTranslate(x, y);
                    mouse.setTouchShape(ShapeIntersection.shapeIntersectEdge(shapeContainer,mouse.getIndividualSelectIndex()));	    						
    			}
    		}
        	
        	else if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.PolygonShape"))
        	{

    			PolygonShape obj = (PolygonShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());
    			if(obj.canTranslate() == true)
    			{
    				obj.setTranslate(x, y);
                    mouse.setTouchShape(ShapeIntersection.shapeIntersectEdge(shapeContainer,mouse.getIndividualSelectIndex()));	    						
    			}
    		}
        	
        	else if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.ArrowShape"))
        	{
        		ArrowShape obj = (ArrowShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());
        		obj.setTranslate(x, y);
        	}
        	
        	else if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.SpringShape"))
        	{
        		SpringShape obj = (SpringShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());
            	if(AlgorTools.CheckShapeContainer.isCommonShapeContainPoint2D(obj.getStartIndex(),end)== true		
            	&& obj.getSelect()==0)
            	{
            		obj.setStartPoint(end);
            	}
            	else if(AlgorTools.CheckShapeContainer.isCommonShapeContainPoint2D(obj.getEndIndex(),end) == true		
                && obj.getSelect()==1)
                {
            		obj.setEndPoint(end);
                }
        	}
        	
        	else if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.JointShape"))
        	{
        		JointShape obj = (JointShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());
        		obj.setTranslate(x, y);
        	}
        }
        
        // transform group state
        if(mouse.getGroupSelectPolygon()!=null)
        {
        	mouse.getGroupSelectPolygon().translate(x,y);      	
        	
        	for(int i =0 ; i<mouse.getGroupLineSelectIndex().size();i++)
        	{
        		LineIndex indexLine = mouse.getGroupLineSelectIndex().get(i);
        		
            	double x1 = lineContainer.get(indexLine.getI()).get(indexLine.getJ()).getX1()+x;
            	double y1 = lineContainer.get(indexLine.getI()).get(indexLine.getJ()).getY1()+y;
            	double x2 = lineContainer.get(indexLine.getI()).get(indexLine.getJ()).getX2()+x;
            	double y2 = lineContainer.get(indexLine.getI()).get(indexLine.getJ()).getY2()+y;
            	lineContainer.get(indexLine.getI()).get(indexLine.getJ()).setLine(x1, y1, x2, y2);
        	}
        	
        	for(int i =0 ; i<mouse.getGroupSelectIndex().size();i++)
        	{	
            	if(shapeContainer.getShape(mouse.getGroupSelectIndex().get(i)).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.TriangleShape"))
            	{
            		TriangleShape obj = (TriangleShape)shapeContainer.getShape(mouse.getGroupSelectIndex().get(i));
            		obj.setTranslate(x, y);
            	}
            	
            	else if(shapeContainer.getShape(mouse.getGroupSelectIndex().get(i)).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.RectangleShape"))
            	{
            		RectangleShape obj = (RectangleShape)shapeContainer.getShape(mouse.getGroupSelectIndex().get(i));
            		obj.setTranslate(x, y);
            	}
            	
            	else if(shapeContainer.getShape(mouse.getGroupSelectIndex().get(i)).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
            	{
            		EllipseShape obj = (EllipseShape)shapeContainer.getShape(mouse.getGroupSelectIndex().get(i));
            		obj.setTranslate(x, y);
            	}
            	
            	else if(shapeContainer.getShape(mouse.getGroupSelectIndex().get(i)).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.PolygonShape"))
            	{
            		PolygonShape obj = (PolygonShape)shapeContainer.getShape(mouse.getGroupSelectIndex().get(i));
            		obj.setTranslate(x, y);
            	}
            	
            	else if(shapeContainer.getShape(mouse.getGroupSelectIndex().get(i)).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.ArrowShape"))
            	{
            		ArrowShape obj = (ArrowShape)shapeContainer.getShape(mouse.getGroupSelectIndex().get(i));
            		obj.setTranslate(x, y);
            	}
            	
            	else if(shapeContainer.getShape(mouse.getGroupSelectIndex().get(i)).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.SpringShape"))
            	{
            		SpringShape obj = (SpringShape)shapeContainer.getShape(mouse.getGroupSelectIndex().get(i));
            		obj.setTranslate(x, y);
            	}
            	
            	else if(shapeContainer.getShape(mouse.getGroupSelectIndex().get(i)).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.JointShape"))
            	{
            		JointShape obj = (JointShape)shapeContainer.getShape(mouse.getGroupSelectIndex().get(i));
            		obj.setTranslate(x, y);
            	}
        	}
        	
        }
        
        // Shape individual select check over recycle bin or not for set the size of recycle bin
        if(mouse.getIndividualSelectIndex() != -1) 
		{
			if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getSuperclass().getCanonicalName().equalsIgnoreCase("CommonShape.CommonShape"))
			{
				CommonShape commonShape = (CommonShape)shapeContainer.getShape(mouse.getIndividualSelectIndex());				
				if(bin.isIntersect(commonShape) == true)
				{	
					// Bigger						
					Image img = null;
					try 
					{
						img = ImageIO.read(new File("./pic/binBig.png"));
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					bin.setImg(img);
				}
				
				else
				{	
					// Smaller
					Image img = null;
					try 
					{
						img = ImageIO.read(new File("./pic/binSmall.png"));
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					bin.setImg(img);
				}
			}
			
			else if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.JointShape"))
			{
				JointShape joint = (JointShape) shapeContainer.getShape(mouse.getIndividualSelectIndex());
				if(bin.isIntersect(joint) == true)
				{	
					// Bigger						
					Image img = null;
					try 
					{
						img = ImageIO.read(new File("./pic/binBig.png"));
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					bin.setImg(img);
				}
				
				else
				{	
					// Smaller
					Image img = null;
					try 
					{
						img = ImageIO.read(new File("./pic/binSmall.png"));
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					bin.setImg(img);
				}
			}
			
			else if(shapeContainer.getShape(mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.ArrowShape"))
			{
				ArrowShape arrow = (ArrowShape) shapeContainer.getShape(mouse.getIndividualSelectIndex());
				if(bin.isIntersect(arrow) == true)
				{	
					// Bigger						
					Image img = null;
					try 
					{
						img = ImageIO.read(new File("./pic/binBig.png"));
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					bin.setImg(img);
				}
				
				else
				{	
					// Smaller
					Image img = null;
					try 
					{
						img = ImageIO.read(new File("./pic/binSmall.png"));
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					bin.setImg(img);
				}
			}
		}
        
        // Line Selection check over recycle bin or not for set the size of recycle bin
		if(mouse.getIndividualLineSelectIndex().getI() != -1
				&&  mouse.getIndividualLineSelectIndex().getJ() != -1)
		{
			Line2D line = lineContainer.get(mouse.getIndividualLineSelectIndex().getI()).get(mouse.getIndividualLineSelectIndex().getJ());
			if(bin.isIntersect(line) == true)
			{	
				// Bigger						
				Image img = null;
				try 
				{
					img = ImageIO.read(new File("./pic/binBig.png"));
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				bin.setImg(img);
			}
			
			else
			{	
				// Smaller
				Image img = null;
				try 
				{
					img = ImageIO.read(new File("./pic/binSmall.png"));
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				bin.setImg(img);
			}
				
		}
        
        // Remove temp shape by using the recycle bin
		if(mouse.getTempShape().size() > 0)
		{
			for(int i = 0;i<mouse.getTempShape().size();i++)
			{
				if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.CrossShape"))
				{
					CrossShape cross = (CrossShape) mouse.getTempShape().get(i);
					if(bin.isIntersect(cross) == true)
					{
						// Bigger						
						Image img = null;
						try 
						{
							img = ImageIO.read(new File("./pic/binBig.png"));
						} 
						catch (IOException e)
						{
							e.printStackTrace();
						}
						bin.setImg(img);
					}
					
					else
					{	
						// Smaller
						Image img = null;
						try 
						{
							img = ImageIO.read(new File("./pic/binSmall.png"));
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
						bin.setImg(img);
					}
				}
				
				else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("TempObject.TempSpringShape"))
				{
					TempObject.TempSpringShape spring = (TempObject.TempSpringShape) mouse.getTempShape().get(i);
					if(bin.isIntersect(spring) == true)
					{
						// Bigger						
						Image img = null;
						try 
						{
							img = ImageIO.read(new File("./pic/binBig.png"));
						} 
						catch (IOException e)
						{
							e.printStackTrace();
						}
						bin.setImg(img);
					}
					
					else
					{	
						// Smaller
						Image img = null;
						try 
						{
							img = ImageIO.read(new File("./pic/binSmall.png"));
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
						bin.setImg(img);
					}
				}
				
				else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.RopeShape"))
				{
					RopeShape rope = (RopeShape) mouse.getTempShape().get(i);
					if(bin.isIntersect(rope) == true)
					{
						// Bigger						
						Image img = null;
						try 
						{
							img = ImageIO.read(new File("./pic/binBig.png"));
						} 
						catch (IOException e)
						{
							e.printStackTrace();
						}
						bin.setImg(img);
					}
					
					else
					{	
						// Smaller
						Image img = null;
						try 
						{
							img = ImageIO.read(new File("./pic/binSmall.png"));
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
						bin.setImg(img);
					}
				}
				
				else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("TempObject.TempFixJointShape"))
				{
					TempFixJointShape fixJoint = (TempFixJointShape) mouse.getTempShape().get(i);
					if(bin.isIntersect(fixJoint) == true)
					{
						// Bigger						
						Image img = null;
						try 
						{
							img = ImageIO.read(new File("./pic/binBig.png"));
						} 
						catch (IOException e)
						{
							e.printStackTrace();
						}
						bin.setImg(img);
					}
					
					else
					{	
						// Smaller
						Image img = null;
						try 
						{
							img = ImageIO.read(new File("./pic/binSmall.png"));
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
						bin.setImg(img);
					}
				}
			}
		}
        
		// Group selection check over recycle bin or not for set the size of recycle bin
		if(mouse.getGroupSelectPolygon().npoints > 0)
		{
			Polygon polygon = mouse.getGroupSelectPolygon();
			PolygonShape polygonSelect = new PolygonShape(ShapeConvert.convertPolygontoPoint2D(polygon));
			if(bin.isIntersect(polygonSelect))
			{	
				// Bigger
				Image img = null;
				try 
				{
					img = ImageIO.read(new File("./pic/binBig.png"));
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				bin.setImg(img);
			}
			else
			{
				// Smaller
				Image img = null;
				try 
				{
					img = ImageIO.read(new File("./pic/binSmall.png"));
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				bin.setImg(img);
			}
		}
        
	}
}
