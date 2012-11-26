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
package UserInterface;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import TempObject.LineIndex;

import AlgorTools.CloneShape;
import EngineDrawing.AutoPencilState;
import EngineDrawing.State;

/**
 * Mouse Interface for check mouse event.
 * @author Magic Board Team
 *
 */

public class Mouse implements Cloneable
{
	private State  state;
	private String previousStateName;
	private int    mouseLeft;
	private int    mouseRight;
	private int    mouseMiddle;
	private int    mouseDrag;
	private String toolButton;
	
	private int gSelect;
	private ArrayList<Integer> 	gSelectIndex;
	private ArrayList<LineIndex>  gLineSelectIndex;
	private LineIndex  iLineSelectIndex;
	private int iSelectIndex;  // Individual select index
	
	private int vertexIndex;
	private ArrayList<Integer>	touch;
	
	private Point startdrag;
	
	private ArrayList<Point2D> 	selectDrawing;
	private ArrayList<Point2D>  rotationMoving;
	private Polygon 			groupSelectPolygon;
	
	/** Temp for rotate or for autotool object*/
	private ArrayList<Object>   tempShape;
	private ArrayList<Point2D>  autoGenPoint;
	
	/** Current tool state */
	public enum ToolState{Pen,Rope,Spring,BasicJoint,FixJoint,Circle,Rectangle,Triangle,Arrow,Car,Balloon,Polygon,Comment,Rubber};
	private     ToolState 	  iToolState;
	private 	String		  backgroundPath;
	private 	Image 		  background;
	private     BufferedImage bufferBackground;
	
	public Mouse()
	{
		this.mouseLeft 	= 0;
		this.mouseRight = 0;
		this.mouseMiddle= 0;   
		this.mouseDrag	= 0;
		this.state      = new AutoPencilState();
		this.previousStateName = "Pencil";
		this.toolButton = "Pencil";
		
		this.gSelect 	  = -1;
		this.iSelectIndex = -1;
		
		this.vertexIndex = -1;
		
		this.startdrag    = new Point();
		
		this.selectDrawing		= new ArrayList<Point2D>();
		this.rotationMoving     = new ArrayList<Point2D>();
		this.gSelectIndex		= new ArrayList<Integer>();
		this.gLineSelectIndex   = new ArrayList<LineIndex>();
		this.iLineSelectIndex   = new LineIndex();
		this.touch				= new ArrayList<Integer>();
		this.groupSelectPolygon	= new Polygon();
		
		this.tempShape			= new ArrayList<Object>();
		this.autoGenPoint       = new ArrayList<Point2D>();
		
		this.iToolState			= ToolState.Pen;
		this.background			= null;
		this.bufferBackground   = null;
	}
	
	/**
	 * Set Mouse action to Left Pressed
	 *
	 */
	public void mouseLeftPressed()
	{
		if(this.mouseRight == 0)
		{
			this.mouseLeft 	= 1;
			this.mouseRight = 0;
			this.mouseDrag  = 0;
			this.iSelectIndex = -1;
			this.groupSelectPolygon.reset();
			this.gSelectIndex.clear();
			this.gLineSelectIndex.clear();
			this.iLineSelectIndex.setI(-1);
			this.iLineSelectIndex.setJ(-1);
			this.clearTempShape();
			this.state.handle(this);
		}
	}
	
	/**
	 * Set Mouse action to Right Pressed
	 *
	 */
	public void mouseRightPressed()
	{
		if(this.mouseLeft == 0)
		{
			this.mouseLeft 	= 0;
			this.mouseRight = 1;
			this.mouseDrag  = 0;
			this.iLineSelectIndex.setI(-1);
			this.iLineSelectIndex.setJ(-1);
			this.state.handle(this);
		}
	}
	
	/**
	 * Set Mouse action to Right Pressed in Physic mode
	 *
	 */
	public void mouseRightPhysicPressed()
	{
		this.mouseRight = 1;
		this.state.handle(this);
	}
	
	/**
	 * Set Mouse action to Middle Pressed
	 *
	 */
	public void mouseMiddlePressed()
	{
		if(this.mouseLeft  == 0 
		&& this.mouseRight == 0)
		{
			this.mouseDrag   = 0;
			this.mouseMiddle = 1;
			this.state.handle(this);
		}
	}
	
	/**
	 * Set Mouse action to Dragged
	 *
	 */
	public void mouseDragged()
	{
		this.mouseDrag	= 1;
		this.state.handle(this);
	}
	
	/**
	 * Set Mouse action to mouse Left Release
	 *
	 */
	public void mouseLeftRelease()
	{
		if(this.mouseLeft == 1)
		{
			this.mouseLeft 	= 0;
			this.mouseRight = 0;
			this.mouseDrag	= 0;
			this.state.handle(this);
		}
	}
	
	/**
	 * Set Mouse action to mouse Right Release Physic mode
	 *
	 */
	public void mouseRightPhysicRelease()
	{
		this.mouseRight = 0;
		this.state.handle(this);
	}
	
	/**
	 * Set Mouse action to mouse Right Release
	 *
	 */
	public void mouseRightRelease()
	{
		if(this.mouseRight == 1)
		{
			this.mouseLeft 	= 0;
			this.mouseRight = 0;
			this.mouseDrag	= 0;
			this.touch		= new ArrayList<Integer>();
			this.state.handle(this);
		}
	}
	
	public void mouseMiddleRelease()
	{
		if(this.mouseMiddle == 1)
		{
			this.mouseLeft   = 0;
			this.mouseRight  = 0;
			this.mouseDrag   = 0;
			this.mouseMiddle = 0;
			this.state.handle(this);
		}
	}
	
	/**
	 * Set the name of autoTool that you want to use
	 * default is pen
	 * @param toolButton
	 */
	public void setautoTool(String toolButton)
	{
		this.toolButton = toolButton;
	}
	
	/**
	 * Get mouse Left status
	 * @return mouse left
	 */
	public int getmouseLeft()
	{
		return this.mouseLeft;
	}
	
	/**
	 * Get mouse Right status
	 * @return mouse Right
	 */
	public int getmouseRight()
	{
		return this.mouseRight;
	}
	
	/**
	 * Get mouse Drag status
	 * @return mouse Drag
	 */
	public int getmouseDrag()
	{
		return this.mouseDrag;
	}
	
	/**
	 * Get mouse middle status
	 * @return
	 */
	public int getmouseMiddle()
	{
		return this.mouseMiddle;
	}
	
	/**
	 * Get autoTool name
	 * @return tool name
	 */
	public String getautoTool()
	{
		return this.toolButton;
	}
	
	/**
	 * Set the stat
	 * @param state
	 */
	public void setState(State state)
	{
		this.state = state;
	}
	
	/**
	 * Set previous State name
	 * @param stateName
	 */
	public void setPreviousState(String stateName)
	{
		this.previousStateName = stateName;
	}
	
	/**
	 * Get the state
	 * @return state object
	 */
	public State getState()
	{
		return this.state;
	}
	
	/**
	 * Get the previous State Name
	 * @return prvious state name
	 */
	public String getPreviousStateName()
	{
		return this.previousStateName;
	}
	
	/**
	 * set Group select 
	 * if equal -1 is none 
	 * if equal  1 is select
	 * @param gSelect
	 */
	public void setGroupSelect(int gSelect)
	{
		this.gSelect = gSelect;
	}
	

	/**
	 * set Individual select 
	 * if equal -1 is none 
	 * if equal  >-1 is select
	 * @param iSelectIndex
	 */
	public void setIndividualSelectIndex(int iSelectIndex)
	{
		this.iSelectIndex = iSelectIndex;
	}
	
	/**
	 * Get group select 
	 * if equal -1 is none 
	 * if equal  1 is select
	 * @return get group select
	 */
	public int getGroupSelect()
	{
		return this.gSelect;
	}
	
	/**
	 * Get individual select
	 * if equal -1 is none 
	 * @return get individual select
	 */
	public int getIndividualSelectIndex()
	{
		return this.iSelectIndex;
	}
	
	/**
	 * Set start point for start drag.
	 * @param startdrag
	 */
	public void setStartDragPoint(Point startdrag)
	{
		this.startdrag = startdrag;
	}
	
	/**
	 * Get start point that you start drag.
	 * @return start point
	 */
	public Point getStartDragPoint()
	{
		return this.startdrag;
	}
	
	/**
	 * Get group select index
	 * @return array list of select index
	 */
	public ArrayList<Integer> getGroupSelectIndex()
	{
		return this.gSelectIndex;
	}
	
	/**
	 * Get group line select index
	 * @return array list of select index
	 */
	public ArrayList<LineIndex> getGroupLineSelectIndex()
	{
		return this.gLineSelectIndex;
	}
	
	/**
	 * Get individual line select index
	 * @return
	 */
	public LineIndex getIndividualLineSelectIndex()
	{
		return this.iLineSelectIndex;
	}
	
	/**
	 * Get select drawing point
	 * @return arraylist of select drawing point.
	 */
	public ArrayList<Point2D> getSelectDrawing()
	{
		return this.selectDrawing;
	}
	
	/**
	 * Get rotation Moving point
	 * @return arraylist of rotaion moving point.
	 */
	public ArrayList<Point2D> getRotationMoving()
	{
		return this.rotationMoving;
	}
	
	
	/**
	 * Get group select polygon
	 * @return Group select polygon.
	 */
	public Polygon getGroupSelectPolygon()
	{
		return this.groupSelectPolygon;
	}
	
	/**
	 * Set vertex index
	 * @param index
	 */
	public void setVertexIndex(int index)
	{
		this.vertexIndex = index;
	}
	
	/**
	 * get vertex index
	 * @return vertex index
	 */
	public int getVertexIndex()
	{
		return this.vertexIndex;
	}
	
	/**
	 * Set touch shape form you individual selection shape.
	 * @param touch
	 */
	public void setTouchShape(ArrayList<Integer> touch)
	{
		this.touch = touch;
	}
	
	/**
	 * get touch shape form your individual selection shape.
	 * @return
	 */
	public ArrayList<Integer> getTouchShape()
	{
		return this.touch;
	}
	
	/**
	 * Add temp shape.
	 * @param obj
	 */
	public void addTempShape(Object obj)
	{
		this.tempShape.add(obj);
	}
	
	/**
	 * remove temp shape form index.
	 * @param i
	 */
	public void removeTempShape(int i)
	{
		this.tempShape.remove(i);
	}
	
	/**
	 * Clear temp shape.
	 *
	 */
	public void clearTempShape()
	{
		this.tempShape.clear();
	}
	
	/**
	 * Get shape temp.
	 * @return
	 */
	public ArrayList<Object> getTempShape()
	{
		return this.tempShape;
	}
	
	/**
	 * Get Auto Gen Point.
	 * @return
	 */
	public ArrayList<Point2D> getAutoGenPoint()
	{
		return this.autoGenPoint;
	}
	
	public ToolState getiToolState()
	{
		return this.iToolState;
	}
	
	public void setiToolState(ToolState state)
	{
		this.iToolState = state;
	}
	
	public BufferedImage getBackground() 
	{
		return this.bufferBackground;
	}
	
	public String getBackgroundPath()
	{
		return this.backgroundPath;
	}

	public void setBackground(String imageFileName) 
	{
		if(imageFileName == null)
		{
			this.backgroundPath   = imageFileName;
			this.background 	  = null;
			this.bufferBackground = null;
		}
		
		else
		{
			try 
			{
				this.backgroundPath = imageFileName;
				this.background = ImageIO.read(new File(imageFileName));
				this.bufferBackground = new BufferedImage(this.background.getWidth(null),this.background.getHeight(null),BufferedImage.TYPE_INT_ARGB);
				Graphics g2 = bufferBackground.getGraphics();
				// Position of image
				g2.drawImage(this.background,0,0,null);
				g2.dispose();
			} 
			
			catch (IOException e) 
			{
				e.printStackTrace();
			}	
		}
	}
	
	/**
	 * Clone the Mouse to Object.
	 */
    public Object clone() 
    {	
        try 
        {
        	Mouse obj = (Mouse)super.clone();
        	obj.state = (State)this.state.clone();
        	obj.previousStateName = ""+this.previousStateName;
        	obj.toolButton		  = ""+this.toolButton;
        	obj.gSelectIndex  	  = CloneShape.cloneArrayListInteger(this.gSelectIndex);
        	obj.gLineSelectIndex  = CloneShape.cloneArrayListLineIndex(this.gLineSelectIndex);
        	obj.iLineSelectIndex  = (LineIndex) iLineSelectIndex.clone();
        	
        	obj.touch			  = CloneShape.cloneArrayListInteger(touch);
        	obj.startdrag		  = (Point) this.startdrag.clone();
        	
        	obj.selectDrawing	  = CloneShape.cloneArrayListPoint2D(this.selectDrawing);
        	obj.rotationMoving	  = CloneShape.cloneArrayListPoint2D(this.rotationMoving);
        	obj.groupSelectPolygon= CloneShape.clonePolygon(this.groupSelectPolygon);
        	
        	obj.tempShape		  = (ArrayList<Object>) this.tempShape.clone();
        	obj.autoGenPoint	  = CloneShape.cloneArrayListPoint2D(this.autoGenPoint);
        
            return obj;
        }
        
        catch (CloneNotSupportedException e) 
        {
            throw new InternalError(e.toString());
        }
    }
}
  