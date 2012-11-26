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


import java.awt.geom.Line2D;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import SpecialShape.ArrowShape;
import SpecialShape.SpringShape;
import Template.Template;
import UserInterface.Mouse;
import XMLConnection.Externalize;
import XMLConnection.Internalize;

import AlgorTools.CloneShape;
import CommonShape.CommonShape;
import Container.ContainerAllShape;
import DrawingInterface.DrawingInterface;
import EngineDrawing.AutoGenState;
import EngineDrawing.ChangeToolState;
import HelpFrame.TestHelp;
import MainInterface.RunProgram;



public class MenuEvent 
{
	private String Menu;
	
	//Load and Save file Instance
	private Internalize 	loadFile = new Internalize();
	private Externalize 	saveFile = new Externalize();
	private JFileChooser 	vChooser;
	private static String	Filepath = "";
	private static TestHelp helpFrame = null;

	public MenuEvent(String MenuType)
	{
		Menu = MenuType;
	}
	
	public String getName()
	{
		return Menu;
	}
	
	public void HandleEvent(DrawingInterface interfacePanel)
	{	
		// Menu Action
		if(Menu.equalsIgnoreCase("File"))
		{
			interfacePanel.iState = DrawingInterface.state.File;
		}
		
		else if(Menu.equalsIgnoreCase("Help"))
		{
			System.out.println("Help");
			if(helpFrame == null)
			{
				helpFrame = new TestHelp("SmartBoard Help V1.0");
				helpFrame.setVisible(true);	
			}
			
			else
			{
				helpFrame.setVisible(true);
			}
			
			interfacePanel.iState = DrawingInterface.state.Drawing;
		}
		
		else if(Menu.equalsIgnoreCase("DrawMode"))
		{
			System.out.println("DrawMode");
			interfacePanel.iState = DrawingInterface.state.DrawMode;
		}
		
		else if(Menu.equalsIgnoreCase("AutoShape"))
		{
			interfacePanel.iState = DrawingInterface.state.AutoShape;
		}
		
		else if(Menu.equalsIgnoreCase("Properties"))
		{
			System.out.println("Properties");
			if(RunProgram.runner.drawing.mouse.getIndividualSelectIndex() != -1)
			{	
				RunProgram.runner.drawing.setIntFVIsible();
				DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
				
				if(RunProgram.runner.drawing.shapeContainer.getShape(RunProgram.runner.drawing.mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.TriangleShape"))
				{					
					CommonShape commonShape = (CommonShape)RunProgram.runner.drawing.shapeContainer.getShape(RunProgram.runner.drawing.mouse.getIndividualSelectIndex());
				
					Float mass 		= commonShape.getMass();
					Float us 		= commonShape.getMs();
					Float uk 		= commonShape.getMk();
					Float bounce 	= commonShape.getBounce();
					
					interfacePanel.ShapeHandle("Triangle",""+df2.format(mass),""+df2.format(us),""+df2.format(uk),""+df2.format(bounce));
				}
				
				else if(RunProgram.runner.drawing.shapeContainer.getShape(RunProgram.runner.drawing.mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.RectangleShape"))
				{
					CommonShape commonShape = (CommonShape)RunProgram.runner.drawing.shapeContainer.getShape(RunProgram.runner.drawing.mouse.getIndividualSelectIndex());
					
					Float mass 		= commonShape.getMass();
					Float us 		= commonShape.getMs();
					Float uk 		= commonShape.getMk();
					Float bounce 	= commonShape.getBounce();
					
					interfacePanel.ShapeHandle("Rectangle",""+df2.format(mass),""+df2.format(us),""+df2.format(uk),""+df2.format(bounce));
				}
				
				else if(RunProgram.runner.drawing.shapeContainer.getShape(RunProgram.runner.drawing.mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
				{
					CommonShape commonShape = (CommonShape)RunProgram.runner.drawing.shapeContainer.getShape(RunProgram.runner.drawing.mouse.getIndividualSelectIndex());
					
					Float mass 		= commonShape.getMass();
					Float us 		= commonShape.getMs();
					Float uk 		= commonShape.getMk();
					Float bounce 	= commonShape.getBounce();
					
					interfacePanel.ShapeHandle("Circle",""+df2.format(mass),""+df2.format(us),""+df2.format(uk),""+df2.format(bounce));
				}
				
				else if(RunProgram.runner.drawing.shapeContainer.getShape(RunProgram.runner.drawing.mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.PolygonShape"))
				{
					CommonShape commonShape = (CommonShape)RunProgram.runner.drawing.shapeContainer.getShape(RunProgram.runner.drawing.mouse.getIndividualSelectIndex());
					
					Float mass 		= commonShape.getMass();
					Float us 		= commonShape.getMs();
					Float uk 		= commonShape.getMk();
					Float bounce 	= commonShape.getBounce();
					
					interfacePanel.ShapeHandle("Polygon",""+df2.format(mass),""+df2.format(us),""+df2.format(uk),""+df2.format(bounce));
				}
				
				else if(RunProgram.runner.drawing.shapeContainer.getShape(RunProgram.runner.drawing.mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.SpringShape"))
				{
					SpringShape springShape = (SpringShape)RunProgram.runner.drawing.shapeContainer.getShape(RunProgram.runner.drawing.mouse.getIndividualSelectIndex());
					
					Float kSpring   = springShape.getKSpring();
					
					interfacePanel.ShapeHandle("Spring",""+df2.format(kSpring),null,null,null);
				}
				
				else if(RunProgram.runner.drawing.shapeContainer.getShape(RunProgram.runner.drawing.mouse.getIndividualSelectIndex()).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.ArrowShape"))
				{
					ArrowShape arrowShape = (ArrowShape)RunProgram.runner.drawing.shapeContainer.getShape(RunProgram.runner.drawing.mouse.getIndividualSelectIndex());
					
					Float gravity	= arrowShape.getGravityForce();
					
					interfacePanel.ShapeHandle("Arrow",""+df2.format(gravity),null,null,null);
				}
			}
			
			// Set pen accurulate
			else
			{
				System.out.println(RunProgram.runner.drawing.penAccurate);
				if(RunProgram.runner.drawing.penAccurate == 5)
				{
					interfacePanel.ShapeHandle("Main","Highest",null,null,null);	
				}
				
				else if(RunProgram.runner.drawing.penAccurate == 11)
				{
					interfacePanel.ShapeHandle("Main","High",null,null,null);	
				}
				
				else if(RunProgram.runner.drawing.penAccurate == 17)
				{
					interfacePanel.ShapeHandle("Main","Medium",null,null,null);	
				}
				
				else if(RunProgram.runner.drawing.penAccurate == 25)
				{
					interfacePanel.ShapeHandle("Main","Low",null,null,null);	
				}
				
				else if(RunProgram.runner.drawing.penAccurate == 33)
				{
					interfacePanel.ShapeHandle("Main","Lowest",null,null,null);	
				}
				
				else
				{
					interfacePanel.ShapeHandle("Main","Medium",null,null,null);	
				}
			}
			
		}
		
		else if(Menu.equalsIgnoreCase("Template"))
		{
			System.out.println("Template");
			RunProgram.runner.drawing.templateFrame();
		}

		else if(Menu.equalsIgnoreCase("Run"))
		{
			interfacePanel.iState = DrawingInterface.state.Drawing;
			
			System.out.println("---------Prepare-Physic----------");
			Mediator.DrawFront draw = new Mediator.DrawFront();
			Mediator.PhysicFront phy = new Mediator.PhysicFront();
			Mediator.Pipe mediator = new Mediator.Pipe(draw,phy);
			draw.setMediator(mediator);
			ArrayList<Object> container = new ArrayList<Object>();
			for(int i =0;i<RunProgram.runner.drawing.getContainerAllShape().getShapeContainerSize();i++)
			{
				container.add(RunProgram.runner.drawing.getContainerAllShape().getShape(i));
			}
			System.out.println(RunProgram.runner.drawing.getContainerAllShape().getShapeContainerSize());
			System.out.println("---------Go-Physics---------");
			draw.send(container);
		}
		
		else if(Menu.equalsIgnoreCase("Back"))
		{
			interfacePanel.iState = DrawingInterface.state.Main;
		}
		
		else if(Menu.equalsIgnoreCase("New"))
		{
			// Clear redo and undo trunk
			interfacePanel.redoTrunk.clearMemento();
			interfacePanel.undoTrunk.clearMemento();
			RunProgram.runner.drawing.mouse.setBackground(null);
			
			System.out.println("New");
			MenuEvent.Filepath = "";
			RunProgram.runner.drawing.getContainerAllShape().removeShapeall();
			RunProgram.runner.drawing.getLineContainer().clear();
			RunProgram.runner.drawing.getLineDrawing().clear();
			RunProgram.runner.drawing.commentContainer.clear();
			
			interfacePanel.iState = DrawingInterface.state.Drawing;
			
			// Set the current memenoto
			interfacePanel.currentMemento.setState((ContainerAllShape)interfacePanel.shapeContainer.clone(),CloneShape.cloneLineContainer(interfacePanel.lineContainer),(Mouse) interfacePanel.mouse.clone());
			Template.setTState(Template.getTState().Default);
			
			RunProgram.runner.repaint();
		}
		
		else if(Menu.equalsIgnoreCase("Load"))
		{
			System.out.println("Load");
			vChooser = new JFileChooser();
			vChooser.setCurrentDirectory(new File("."));
			FileFilter filter1 = new FileFilter()
			{
				@Override
				public String getDescription() 
				{
					return "Xml (.xml) File";
				}

				@Override
				public boolean accept(File f) {
					
				if (f.isDirectory())return true;
				if (!f.isFile()) return false;
				String ext = getExtension(f);
				if(ext.equals("xml")) return true;
				
					return false;
				}
				
				String getExtension(File f)
				{
					
					String filename = f.getName();
					int k = filename.lastIndexOf('.');
					if ( k <= 0 || k >= filename.length() -1 ) return "";
					else return filename.substring(k+1).toLowerCase();
					
				}
			};
			vChooser.setFileFilter(filter1);
			
			int result = vChooser.showOpenDialog(RunProgram.runner);
		
			// Load File name
			if ( result == JFileChooser.APPROVE_OPTION ) 
			{
				// Clear redo and undo trunk
				interfacePanel.redoTrunk.clearMemento();
				interfacePanel.undoTrunk.clearMemento();
				
				
				MenuEvent.Filepath = vChooser.getSelectedFile().getAbsolutePath();
				System.out.println("Load file at "+vChooser.getSelectedFile().getAbsolutePath());
				RunProgram.runner.drawing.mouse.setIndividualSelectIndex(-1);
				RunProgram.runner.drawing.mouse.getGroupSelectPolygon().reset();
				RunProgram.runner.drawing.mouse.getGroupSelectIndex().clear();
				RunProgram.runner.drawing.mouse.getGroupLineSelectIndex().clear();
				RunProgram.runner.drawing.mouse.getIndividualLineSelectIndex().setI(-1);
				RunProgram.runner.drawing.mouse.getIndividualLineSelectIndex().setJ(-1);
				RunProgram.runner.drawing.mouse.getTempShape().clear();
				RunProgram.runner.drawing.commentContainer.clear();
				
				RunProgram.runner.drawing.getContainerAllShape().setShape(loadFile.readXMLsaveFile(vChooser.getSelectedFile().getAbsolutePath()));
				interfacePanel.iState = DrawingInterface.state.Drawing;
				
				// Set the current memenot
				interfacePanel.currentMemento.setState((ContainerAllShape)interfacePanel.shapeContainer.clone(),CloneShape.cloneLineContainer(interfacePanel.lineContainer),(Mouse) interfacePanel.mouse.clone());
				
				RunProgram.runner.repaint();
			}
		}
		
		else if(Menu.equalsIgnoreCase("Save"))
		{
			System.out.println("Save");
			
			if(this.Filepath.length() > 0)
			{
				saveFile.writeSave(RunProgram.runner.drawing.getContainerAllShape()
						,this.Filepath
						,RunProgram.runner.drawing.getShortNote());
				interfacePanel.iState = DrawingInterface.state.Drawing;
				RunProgram.runner.repaint();
			}
			
			else
			{
				vChooser = new JFileChooser();
				vChooser.setCurrentDirectory(new File("."));
				FileFilter filter1 = new FileFilter()
				{
					@Override
					public String getDescription() 
					{
						return "Xml (.xml) File";
					}

					@Override
					public boolean accept(File f) {
						
					if (f.isDirectory())return true;
					if (!f.isFile()) return false;
					String ext = getExtension(f);
					if(ext.equals("xml")) return true;
					
						return false;
					}
					
					String getExtension(File f)
					{
						
						String filename = f.getName();
						int k = filename.lastIndexOf('.');
						if ( k <= 0 || k >= filename.length() -1 ) return "";
						else return filename.substring(k+1).toLowerCase();
					}
				};
				vChooser.setFileFilter(filter1);
				
				int result = vChooser.showSaveDialog(RunProgram.runner);
			
				// Save File name
				if ( result == JFileChooser.APPROVE_OPTION) 
				{
					
					if((vChooser.getSelectedFile().getAbsolutePath().charAt(vChooser.getSelectedFile().getAbsolutePath().length()-1) == 'l' ||
						vChooser.getSelectedFile().getAbsolutePath().charAt(vChooser.getSelectedFile().getAbsolutePath().length()-1) == 'L')&&
					   (vChooser.getSelectedFile().getAbsolutePath().charAt(vChooser.getSelectedFile().getAbsolutePath().length()-2) == 'm' ||
						vChooser.getSelectedFile().getAbsolutePath().charAt(vChooser.getSelectedFile().getAbsolutePath().length()-2) == 'M')&&
					   (vChooser.getSelectedFile().getAbsolutePath().charAt(vChooser.getSelectedFile().getAbsolutePath().length()-3) == 'x' ||
						vChooser.getSelectedFile().getAbsolutePath().charAt(vChooser.getSelectedFile().getAbsolutePath().length()-3) == 'X') &&
					    vChooser.getSelectedFile().getAbsolutePath().charAt(vChooser.getSelectedFile().getAbsolutePath().length()-4) == '.')
					{
						MenuEvent.Filepath = vChooser.getSelectedFile().getAbsolutePath();
						System.out.println("Save file at "+this.Filepath);
						saveFile.writeSave(RunProgram.runner.drawing.getContainerAllShape()
								,vChooser.getSelectedFile().getAbsolutePath()
								,RunProgram.runner.drawing.getShortNote());
						interfacePanel.iState = DrawingInterface.state.Drawing;
						RunProgram.runner.repaint();
					}
					
					else
					{
						MenuEvent.Filepath = vChooser.getSelectedFile().getAbsolutePath()+".xml";
						System.out.println("Save file at "+this.Filepath);
						saveFile.writeSave(RunProgram.runner.drawing.getContainerAllShape()
								,vChooser.getSelectedFile().getAbsolutePath()+".xml"
								,RunProgram.runner.drawing.getShortNote());
						interfacePanel.iState = DrawingInterface.state.Drawing;
						RunProgram.runner.repaint();	
					}
				}
			}
		}
		
		else if(Menu.equalsIgnoreCase("SaveAs"))
		{
			System.out.println("SaveAs");
			
			vChooser = new JFileChooser();
			vChooser.setCurrentDirectory(new File("."));
			FileFilter filter1 = new FileFilter()
			{
				@Override
				public String getDescription() 
				{
					return "Xml (.xml) File";
				}

				@Override
				public boolean accept(File f) {
					
				if (f.isDirectory())return true;
				if (!f.isFile()) return false;
				String ext = getExtension(f);
				if(ext.equals("xml")) return true;
				
					return false;
				}
				
				String getExtension(File f)
				{
					
					String filename = f.getName();
					int k = filename.lastIndexOf('.');
					if ( k <= 0 || k >= filename.length() -1 ) return "";
					else return filename.substring(k+1).toLowerCase();
				}
			};
			vChooser.setFileFilter(filter1);
			
			int result = vChooser.showSaveDialog(RunProgram.runner);
		
			// Save File name
			if ( result == JFileChooser.APPROVE_OPTION) 
			{
				
				if((vChooser.getSelectedFile().getAbsolutePath().charAt(vChooser.getSelectedFile().getAbsolutePath().length()-1) == 'l' ||
					vChooser.getSelectedFile().getAbsolutePath().charAt(vChooser.getSelectedFile().getAbsolutePath().length()-1) == 'L')&&
				   (vChooser.getSelectedFile().getAbsolutePath().charAt(vChooser.getSelectedFile().getAbsolutePath().length()-2) == 'm' ||
					vChooser.getSelectedFile().getAbsolutePath().charAt(vChooser.getSelectedFile().getAbsolutePath().length()-2) == 'M')&&
				   (vChooser.getSelectedFile().getAbsolutePath().charAt(vChooser.getSelectedFile().getAbsolutePath().length()-3) == 'x' ||
					vChooser.getSelectedFile().getAbsolutePath().charAt(vChooser.getSelectedFile().getAbsolutePath().length()-3) == 'X') &&
				    vChooser.getSelectedFile().getAbsolutePath().charAt(vChooser.getSelectedFile().getAbsolutePath().length()-4) == '.')
				{
					MenuEvent.Filepath = vChooser.getSelectedFile().getAbsolutePath();
					System.out.println("Save file at "+this.Filepath);
					saveFile.writeSave(RunProgram.runner.drawing.getContainerAllShape()
							,vChooser.getSelectedFile().getAbsolutePath()
							,RunProgram.runner.drawing.getShortNote());
					interfacePanel.iState = DrawingInterface.state.Drawing;
					RunProgram.runner.repaint();
				}
				
				else
				{
					MenuEvent.Filepath = vChooser.getSelectedFile().getAbsolutePath()+".xml";
					System.out.println("Save file at "+this.Filepath);
					saveFile.writeSave(RunProgram.runner.drawing.getContainerAllShape()
							,vChooser.getSelectedFile().getAbsolutePath()+".xml"
							,RunProgram.runner.drawing.getShortNote());
					interfacePanel.iState = DrawingInterface.state.Drawing;
					RunProgram.runner.repaint();
					
				}
			}
		}
		
		else if(Menu.equalsIgnoreCase("Pen"))
		{
			RunProgram.runner.drawing.getMouseInterface().setautoTool("Pencil");
			RunProgram.runner.drawing.getMouseInterface().setState(new ChangeToolState());
			ChangeToolState state = (ChangeToolState)RunProgram.runner.drawing.getMouseInterface().getState();
			state.runState(RunProgram.runner.drawing.getMouseInterface());
			interfacePanel.iState = DrawingInterface.state.Drawing;
			
			interfacePanel.mouse.setiToolState(interfacePanel.mouse.getiToolState().Pen);
		}
		
		else if(Menu.equalsIgnoreCase("Comment"))
		{
			RunProgram.runner.drawing.getMouseInterface().setautoTool("Comment");
			RunProgram.runner.drawing.getMouseInterface().setState(new AutoGenState());
			AutoGenState state = (AutoGenState)RunProgram.runner.drawing.getMouseInterface().getState();
			interfacePanel.iState = DrawingInterface.state.Drawing;
			
			interfacePanel.mouse.setiToolState(interfacePanel.mouse.getiToolState().Comment);
		}
		
		else if(Menu.equalsIgnoreCase("Rubber"))
		{
			RunProgram.runner.drawing.getMouseInterface().setautoTool("Rubber");
			RunProgram.runner.drawing.getMouseInterface().setState(new ChangeToolState());
			ChangeToolState state = (ChangeToolState)RunProgram.runner.drawing.getMouseInterface().getState();
			state.runState(RunProgram.runner.drawing.getMouseInterface());
			interfacePanel.iState = DrawingInterface.state.Drawing;
			interfacePanel.mouse.setiToolState(interfacePanel.mouse.getiToolState().Rubber);
		}
		
		else if(Menu.equalsIgnoreCase("Rope"))
		{
			RunProgram.runner.drawing.getMouseInterface().setautoTool("Rope");
			RunProgram.runner.drawing.getMouseInterface().setState(new ChangeToolState());
			ChangeToolState state = (ChangeToolState)RunProgram.runner.drawing.getMouseInterface().getState();
			state.runState(RunProgram.runner.drawing.getMouseInterface());
			interfacePanel.iState = DrawingInterface.state.Drawing;
			
			interfacePanel.mouse.setiToolState(interfacePanel.mouse.getiToolState().Rope);
		}
		
		else if(Menu.equalsIgnoreCase("BasicJoint"))
		{
			RunProgram.runner.drawing.getMouseInterface().setautoTool("Joint");
			RunProgram.runner.drawing.getMouseInterface().setState(new ChangeToolState());
			ChangeToolState state = (ChangeToolState)RunProgram.runner.drawing.getMouseInterface().getState();
			state.runState(RunProgram.runner.drawing.getMouseInterface());
			interfacePanel.iState = DrawingInterface.state.Drawing;
			
			interfacePanel.mouse.setiToolState(interfacePanel.mouse.getiToolState().BasicJoint);
		}
		
		else if(Menu.equalsIgnoreCase("FixJoint"))
		{
			RunProgram.runner.drawing.getMouseInterface().setautoTool("FixJoint");
			RunProgram.runner.drawing.getMouseInterface().setState(new ChangeToolState());
			ChangeToolState state = (ChangeToolState)RunProgram.runner.drawing.getMouseInterface().getState();
			state.runState(RunProgram.runner.drawing.getMouseInterface());
			interfacePanel.iState = DrawingInterface.state.Drawing;
			
			interfacePanel.mouse.setiToolState(interfacePanel.mouse.getiToolState().FixJoint);
		}
		
		else if(Menu.equalsIgnoreCase("Spring"))
		{
			RunProgram.runner.drawing.getMouseInterface().setautoTool("Spring");
			RunProgram.runner.drawing.getMouseInterface().setState(new ChangeToolState());
			ChangeToolState state = (ChangeToolState)RunProgram.runner.drawing.getMouseInterface().getState();
			state.runState(RunProgram.runner.drawing.getMouseInterface());
			interfacePanel.iState = DrawingInterface.state.Drawing;
			
			interfacePanel.mouse.setiToolState(interfacePanel.mouse.getiToolState().Spring);
		}
		
		else if(Menu.equalsIgnoreCase("Arrow"))
		{
			RunProgram.runner.drawing.getMouseInterface().setautoTool("Arrow");
			RunProgram.runner.drawing.getMouseInterface().setState(new ChangeToolState());
			ChangeToolState state = (ChangeToolState)RunProgram.runner.drawing.getMouseInterface().getState();
			state.runState(RunProgram.runner.drawing.getMouseInterface());
			interfacePanel.iState = DrawingInterface.state.Drawing;
			
			interfacePanel.mouse.setiToolState(interfacePanel.mouse.getiToolState().Arrow);
		}
		
		else if(Menu.equalsIgnoreCase("Triangle"))
		{
			RunProgram.runner.drawing.getMouseInterface().setautoTool("Triangle");
			RunProgram.runner.drawing.getMouseInterface().setState(new ChangeToolState());
			ChangeToolState state = (ChangeToolState)RunProgram.runner.drawing.getMouseInterface().getState();
			state.runState(RunProgram.runner.drawing.getMouseInterface());
			interfacePanel.iState = DrawingInterface.state.Drawing;
			
			interfacePanel.mouse.setiToolState(interfacePanel.mouse.getiToolState().Triangle);
		}
		
		else if(Menu.equalsIgnoreCase("Rectangle"))
		{
			RunProgram.runner.drawing.getMouseInterface().setautoTool("Rectangle");
			RunProgram.runner.drawing.getMouseInterface().setState(new ChangeToolState());
			ChangeToolState state = (ChangeToolState)RunProgram.runner.drawing.getMouseInterface().getState();
			state.runState(RunProgram.runner.drawing.getMouseInterface());
			interfacePanel.iState = DrawingInterface.state.Drawing;
			
			interfacePanel.mouse.setiToolState(interfacePanel.mouse.getiToolState().Rectangle);
		}
		
		else if(Menu.equalsIgnoreCase("Circle"))
		{
			RunProgram.runner.drawing.getMouseInterface().setautoTool("Ellipse");
			RunProgram.runner.drawing.getMouseInterface().setState(new ChangeToolState());
			ChangeToolState state = (ChangeToolState)RunProgram.runner.drawing.getMouseInterface().getState();
			state.runState(RunProgram.runner.drawing.getMouseInterface());
			interfacePanel.iState = DrawingInterface.state.Drawing;
			
			interfacePanel.mouse.setiToolState(interfacePanel.mouse.getiToolState().Circle);
		}
		
		else if(Menu.equalsIgnoreCase("Car"))
		{
			RunProgram.runner.drawing.getMouseInterface().setautoTool("Car");
			RunProgram.runner.drawing.getMouseInterface().setState(new ChangeToolState());
			ChangeToolState state = (ChangeToolState)RunProgram.runner.drawing.getMouseInterface().getState();
			state.runState(RunProgram.runner.drawing.getMouseInterface());
			interfacePanel.iState = DrawingInterface.state.Drawing;
			
			interfacePanel.mouse.setiToolState(interfacePanel.mouse.getiToolState().Car);
		}
		
		else if(Menu.equalsIgnoreCase("Balloon"))
		{
			RunProgram.runner.drawing.getMouseInterface().setautoTool("Balloon");
			RunProgram.runner.drawing.getMouseInterface().setState(new ChangeToolState());
			ChangeToolState state = (ChangeToolState)RunProgram.runner.drawing.getMouseInterface().getState();
			state.runState(RunProgram.runner.drawing.getMouseInterface());
			interfacePanel.iState = DrawingInterface.state.Drawing;
			
			interfacePanel.mouse.setiToolState(interfacePanel.mouse.getiToolState().Balloon);
		}
		
		else if(Menu.equalsIgnoreCase("Redo"))
		{
			Object old = interfacePanel.redoTrunk.getMemento();
			if(old != null)
			{
				System.out.println("Redo");
				interfacePanel.undoTrunk.addMemento(interfacePanel.currentMemento.saveToSmartBoardMemento());
				interfacePanel.currentMemento.restoreFromMemento(old);
				interfacePanel.shapeContainer = (ContainerAllShape) interfacePanel.currentMemento.getContainer().clone();
				interfacePanel.lineContainer  = (ArrayList<ArrayList<Line2D>>) CloneShape.cloneLineContainer(interfacePanel.currentMemento.getLinecontainer());
				interfacePanel.mouse		  = (Mouse) interfacePanel.currentMemento.getMouse().clone();
				interfacePanel.mouse.getAutoGenPoint().clear();
				interfacePanel.mouse.getTempShape().clear();
			}
		}
		
		else if(Menu.equalsIgnoreCase("Undo"))
		{
			Object old = interfacePanel.undoTrunk.getMemento();
			if(old != null)
			{
				System.out.println("Undo");
				interfacePanel.redoTrunk.addMemento(interfacePanel.currentMemento.saveToSmartBoardMemento());
				interfacePanel.currentMemento.restoreFromMemento(old);
				interfacePanel.shapeContainer = (ContainerAllShape) interfacePanel.currentMemento.getContainer().clone();
				interfacePanel.lineContainer  = (ArrayList<ArrayList<Line2D>>) CloneShape.cloneLineContainer(interfacePanel.currentMemento.getLinecontainer());
				interfacePanel.mouse		  = (Mouse) interfacePanel.currentMemento.getMouse().clone();
				interfacePanel.mouse.getAutoGenPoint().clear();
				interfacePanel.mouse.getTempShape().clear();
			}
		}
	}
}
