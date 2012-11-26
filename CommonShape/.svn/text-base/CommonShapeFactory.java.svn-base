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
package CommonShape;

import java.awt.geom.Point2D;
import java.util.ArrayList;


/**
 * Factory class for create common shape by using name and ArrayList of Point2D
 * 
 * @author Magic Board Team
 *
 */
public class CommonShapeFactory
{
	public static final String TRIANGLE   = "triangle";
	public static final String triangle   = "triangle";
	
	public static final String RECTANGLE  = "rectangle";
	public static final String rectangle  = "rectangle";
	
	public static final String ELLIPSE    = "ellipse";
	public static final String ellipse    = "ellipse";
	
	public static final String POLYGON    = "polygon";
	public static final String polygon    = "polygon";
	
	private CommonShapeFactory()
	{
		
	}
	
	/**
	 * Creates a new instance of CommonShape by order
	 * 		1. order equalsIgnoreCase "Triangle"  --> TriangleShape
	 * 		2. order equalsIgnoreCase "Rectangle" --> RectangleShape
	 * 		3. order equalsIgnoreCase "Ellipse"   --> EllipseShape
	 * 		4. order equalsIgnoreCase "Polygon"   --> PolygonShape
	 * @param order String common shape that you want to create non specific case.
	 * @param point ArrayList of point2D that get from point of intersection of Line2D.
	 * @return Object of CommonShape that you want to create.
	 */
	public static CommonShape createCommonShape(String order,ArrayList<Point2D> point)
	{
		if(order.equalsIgnoreCase("triangle"))
		{
			return new TriangleShape(point);
		}
		
		else if(order.equalsIgnoreCase("rectangle"))
		{
			return new RectangleShape(point);
		}
		
		else if(order.equalsIgnoreCase("ellipse"))
		{
			return new EllipseShape(point);
		}
		
		else if(order.equalsIgnoreCase("polygon"))
		{
			return new PolygonShape(point);
		}
		
		else 
		{
			return null;
		}
	}
}
