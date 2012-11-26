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
package GraphicInterface;




import java.awt.Color;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

import javax.swing.AbstractAction;
import javax.swing.Action;

import AlgorTools.CloneShape;
import CommonShape.CommonShape;
import Container.ContainerAllShape;
import DrawingInterface.DrawingInterface;
import MainInterface.RunProgram;
import SpecialShape.ArrowShape;
import SpecialShape.SpringShape;
import UserInterface.Mouse;

public class InternalEvent extends  AbstractAction
{

	public InternalEvent(String name)
	{
		super(name);
	}
	public void actionPerformed(ActionEvent arg0) 
	{
		
		if(getValue(Action.NAME).equals("Submit"))
		{
			System.out.println("Click Submit Buton");
			
			if(RunProgram.runner.drawing.mouse.getIndividualSelectIndex() != -1)
			{
				// Save to redo
				RunProgram.runner.drawing.redoTrunk.clearMemento();
				RunProgram.runner.drawing.undoTrunk.addMemento(RunProgram.runner.drawing.currentMemento.saveToSmartBoardMemento());
				
				
				Object obj = RunProgram.runner.drawing.shapeContainer.getShape(RunProgram.runner.drawing.mouse.getIndividualSelectIndex());
				
				// Submit Triangle , Rectangle , Ellipse , Polygon
				if(obj.getClass().getSuperclass().getCanonicalName().equalsIgnoreCase("CommonShape.CommonShape"))
				{
					CommonShape common = (CommonShape)obj;
					
					DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
					
					// Set mass
					Float mass = Float.parseFloat(WindowPanel.list1.getSelectedItem().toString());
					common.setMass(new Float(df2.format(mass)).floatValue());
					
					// Set Us
					Float us = Float.parseFloat(WindowPanel.list2.getSelectedItem().toString());
					common.setMs(new Float(df2.format(us)).floatValue());
					
					// Set Uk
					Float uk = Float.parseFloat(WindowPanel.list3.getSelectedItem().toString());
					common.setMk(new Float(df2.format(uk)).floatValue());
					
					// Set Bounce
					Float bounce = Float.parseFloat(WindowPanel.list4.getSelectedItem().toString());
					common.setBounce(new Float(df2.format(bounce)).floatValue());
					
					// Set Color
					Color color = WindowPanel.iColorpicker.getBackground();
					common.setColor(color);
					
					RunProgram.runner.drawing.setIntFVIsible();
				}
				
				// Submit Spring 
				else if(obj.getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.SpringShape"))
				{
					SpringShape spring = (SpringShape)obj;
					
					DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
					
					//Set Spring Constant
					Float kSpring = Float.parseFloat(WindowPanel.list1.getSelectedItem().toString());
					spring.setKSpring(new Float(df2.format(kSpring)).floatValue());
					RunProgram.runner.drawing.setIntFVIsible();
				}
				
				// Submit Arrow
				else if(obj.getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.ArrowShape"))
				{
					ArrowShape arrow = (ArrowShape)obj;
					
					DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
					
					//Set gravity force
					Float gravity = Float.parseFloat(WindowPanel.list1.getSelectedItem().toString());
					arrow.setGravityForce(new Float(df2.format(gravity)).floatValue());
					RunProgram.runner.drawing.setIntFVIsible();
				}
				
				RunProgram.runner.drawing.currentMemento.setState((ContainerAllShape)RunProgram.runner.drawing.shapeContainer.clone(),CloneShape.cloneLineContainer(RunProgram.runner.drawing.lineContainer),(Mouse) RunProgram.runner.drawing.mouse.clone());
			}
			
			else
			{
				// Submit Pen for set accurate
				System.out.println(WindowPanel.list1.getSelectedItem().toString());
				if(WindowPanel.list1.getSelectedItem().toString().equalsIgnoreCase("Highest"))
				{
					RunProgram.runner.drawing.penAccurate = 5;
				}
				
				else if(WindowPanel.list1.getSelectedItem().toString().equalsIgnoreCase("High"))
				{
					RunProgram.runner.drawing.penAccurate = 11;
				}
				
				else if(WindowPanel.list1.getSelectedItem().toString().equalsIgnoreCase("Medium"))
				{
					RunProgram.runner.drawing.penAccurate = 17;
				}
				
				else if(WindowPanel.list1.getSelectedItem().toString().equalsIgnoreCase("Low"))
				{
					RunProgram.runner.drawing.penAccurate = 25;
				}
				
				else if(WindowPanel.list1.getSelectedItem().toString().equalsIgnoreCase("Lowest"))
				{
					RunProgram.runner.drawing.penAccurate = 33;
				}
				System.out.println(RunProgram.runner.drawing.penAccurate);
				RunProgram.runner.drawing.setIntFVIsible();
			}
			
			System.out.println("Set to DrawMode");
			DrawingInterface.iState = DrawingInterface.state.Drawing;
			RunProgram.runner.drawing.repaint();
		}
		
		else if(getValue(Action.NAME).equals("Cancel"))
		{
			System.out.println("Click Cancel Buton");
			RunProgram.runner.drawing.setIntFVIsible();
		}
	}

}
