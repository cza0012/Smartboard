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

import CommonShape.CommonShape;
import Container.ContainerAllShape;

/**
 * 
 * @author Magic Team
 *
 */
public class ShapeTranslate 
{
	/**
	 * For translate object to the surface of the another object.
	 * @param shapeContainer
	 * @param index1 object that you want to translate.
	 * @param index2 object that was referrence to object index1.
	 */
	public static void translateToSurface(ContainerAllShape shapeContainer,int move,int fix)
	{
		if(shapeContainer.getShape(move).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape") 
		&& shapeContainer.getShape(fix).getClass().getCanonicalName().equalsIgnoreCase("CommonShape"))
		{
			
		}
		
		else if(shapeContainer.getShape(move).getClass().getCanonicalName().equalsIgnoreCase("CommonShape") 
		&& shapeContainer.getShape(fix).getClass().getCanonicalName().equalsIgnoreCase("CommonShape"))
    	{
			CommonShape common1 = (CommonShape)shapeContainer.getShape(move);
			CommonShape common2 = (CommonShape)shapeContainer.getShape(fix);
			Point2D cm1  = common1.getCM();
			Point2D cm2  = common2.getCM();
			double angle = LineProperty.getLineAngle(cm1.getX(),cm1.getY(),cm2.getX(),cm2.getY());
			
			while(true)
			{
				double distance = LineProperty.findMagnitudeOfLine(new Line2D.Double(cm1,cm2));
				double x1 = (distance + 1)*(Math.sin(angle)) + cm2.getX();
				double y1 = (distance + 1)*(Math.sin(angle)) + cm2.getY();
				common1.setTranslate((int)(x1-common1.getCM().getX()),(int)(y1-common1.getCM().getY()));
			}
			//shapeContainer.setShapeIndex(move,common1);
    	}
	}
	
}
