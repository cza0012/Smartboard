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
package PhysicDrawing;

import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import Container.ContainerAllShape;

import EnginePhysic.*;
import IconMenu.IconMenu;
import UserInterface.Mouse;

public class PhysicFrame extends JFrame implements MouseListener,MouseMotionListener
{

	private final int mouseLeft   = 1;
	private final int mouseMiddle = 2;
	private final int mouseRight  = 3;
	 
	
	private Mouse mouse;
	private ContainerAllShape bodyContainer;
	
	private int pwidth;
	private int pheight;
	
	private IconMenu[] statusIcon;
	
	/** Status of icon*/
	public String current 	  = "STOP";
	public boolean menuOpen	  = false;
	
	private int iconLocationX;
	private int iconLocationY;
	
	private int currentBodyGraph = -1;
	public static PhysicGraph pg = null;
	
	
	public PhysicFrame(ContainerAllShape allshape,Mouse mouse,IconMenu[] statusIcon)
	{
		this.setTitle("SmartBoard Simulation");
		this.setResizable(false);
		this.setIgnoreRepaint(true);
		
		calcSizes();
		this.setSize(this.pwidth,this.pheight);
		
		this.iconLocationX = (this.pwidth/2)-60;
		this.iconLocationY = (this.pheight/2);
		
		this.mouse = mouse;
		this.mouse.setState(new HandSelectState());
		this.bodyContainer = allshape;
		
		this.statusIcon = statusIcon;
		
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.setVisible(true);
		this.createBufferStrategy(2);
	}
	
	/**
	 * Calculate Screen Size
	 *
	 */
	private void calcSizes()
	{
		GraphicsConfiguration gc = getGraphicsConfiguration();
		Rectangle screenRect = gc.getBounds(); // screen dimensions
		pwidth  =  screenRect.width;
		pheight =  screenRect.height;
	}

	/**
	 * For set new Container.
	 * @param bodyContainer
	 */
	public void setContainer(ContainerAllShape bodyContainer)
	{
		this.bodyContainer = bodyContainer;
	}
	
	public void mousePressed(MouseEvent e) 
	{
		// mouse left press
		if(e.getButton() == this.mouseLeft)
		{
			mouse.mouseLeftPressed();
			if(mouse.getState().getStateName().equalsIgnoreCase("HandSelect"))
			{
				HandSelectState state = (HandSelectState) mouse.getState();
				state.runState(e.getPoint(),bodyContainer,mouse);
			}
			
			else if(mouse.getState().getStateName().equalsIgnoreCase("SelectMenu"))
			{
				SelectMenuState state = (SelectMenuState) mouse.getState();
				this.menuOpen = state.runState(mouse,e,statusIcon,iconLocationX,iconLocationY);
			}
			
			else if(mouse.getState().getStateName().equalsIgnoreCase("SelectGraph"))
			{
				SelectGraphState state = (SelectGraphState) mouse.getState();
				state.runState(e.getPoint(), bodyContainer, mouse,statusIcon);
			}
			
			else if(mouse.getState().getStateName().equalsIgnoreCase("DrawLineForce"))
			{
				DrawLineForceState state = (DrawLineForceState) mouse.getState();
				state.runState(e.getPoint(),mouse);
			}
		}
		
		// mouse right press
		else if(e.getButton() == this.mouseRight)
		{
			mouse.mouseRightPhysicPressed();
		
			if(mouse.getState().getStateName().equalsIgnoreCase("Simulation"))
			{
				SimulationState state = (SimulationState)mouse.getState();
				this.current = state.runState(this.current);
			}
		}
		
		else if(e.getButton() == this.mouseMiddle)
		{
			mouse.mouseMiddlePressed();
			System.out.println("mouse middlePressed "+mouse.getState().getStateName()+" "+mouse.getmouseMiddle());
			if(mouse.getState().getStateName().equalsIgnoreCase("OpenMenu"))
			{
				OpenMenuState state = (OpenMenuState)mouse.getState();
				state.runState(this.mouse,this.statusIcon,this.iconLocationX,this.iconLocationY);
				this.menuOpen = true;
			}
			
			else if(mouse.getState().getStateName().equalsIgnoreCase("CloseMenu"))
			{
				CloseMenuState state = (CloseMenuState)mouse.getState();
				state.runState(this.mouse,this.statusIcon);
				this.menuOpen = false;
			}
		}
	}

	public void mouseReleased(MouseEvent e) 
	{
		// mouse left release
		if(e.getButton() == this.mouseLeft)
		{
			mouse.mouseLeftRelease();
			if(mouse.getState().getStateName().equalsIgnoreCase("ReleaseLeft"))
			{
				ReleaseLeftState state = (ReleaseLeftState) mouse.getState();
				state.runState(this.bodyContainer,this.mouse);
			}
			
			else if(mouse.getState().getStateName().equalsIgnoreCase("AddForce"))
			{
				AddForceState state  = (AddForceState) mouse.getState();
				state.runState(e.getPoint(),bodyContainer,mouse);
			}
			
		}
		
		// mouse right release
		else if(e.getButton() == this.mouseRight)
		{
			mouse.mouseRightPhysicRelease();
			
		}
		
		// mouse middle release
		else if(e.getButton() == this.mouseMiddle)
		{
			System.out.println("Mouse middle release");
			mouse.mouseMiddleRelease();
		}
	}

	public void mouseDragged(MouseEvent e)
	{
		mouse.mouseDragged();
		
		if(mouse.getState().getStateName().equalsIgnoreCase("Transform"))
		{
			TransformState state = (TransformState) mouse.getState();
			state.runState(e.getPoint(),this.bodyContainer,mouse);
			this.mouse.setStartDragPoint(e.getPoint());
		}
		
		else if(mouse.getState().getStateName().equalsIgnoreCase("DrawLineForce"))
		{
			DrawLineForceState state = (DrawLineForceState) mouse.getState();
			state.runState(e.getPoint(), mouse);
		}
	}

	public void mouseMoved(MouseEvent arg0) 
	{
		if(this.menuOpen == true)
		{
			if(statusIcon[0].contains(arg0)) // mouse over
			{
				// change image is not effected, if user is choosing object to see the graph
				statusIcon[0] = new IconMenu("./pic/GraphBig.png",iconLocationX-150,iconLocationY-70,"GRAPH");
			}
			else
			{	// change image is not effected, if user is choosing object to see the graph
				statusIcon[0] = new IconMenu("./pic/GraphSmall.png",iconLocationX-130,iconLocationY-50,"GRAPH");
			}
			
			if(statusIcon[1].contains(arg0))
			{				
				statusIcon[1] = new IconMenu("./pic/ForceBig.png",iconLocationX-20,iconLocationY-70,"FORCE");
			}
		    else
		    {
		    	statusIcon[1] = new IconMenu("./pic/ForceSmall.png",iconLocationX,iconLocationY-50,"FORCE");
		    }
			
			if(statusIcon[2].contains(arg0))
			{				
				statusIcon[2] = new IconMenu("./pic/StopBig.png",iconLocationX+110,iconLocationY-70,"STOP");	
			}
		    else
		    {
		    	statusIcon[2] = new IconMenu("./pic/StopSmall.png",iconLocationX+130,iconLocationY-50,"STOP");	
		    }
			
		}
	}
	
	public void mouseClicked(MouseEvent arg0) {}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}
}
