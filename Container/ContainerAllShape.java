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
package Container;

import java.util.ArrayList;
import java.util.LinkedList;

import SpecialShape.ArrowShape;
import SpecialShape.FixJointShape;
import SpecialShape.JointShape;
import SpecialShape.RopeShape;
import SpecialShape.SpecialShape;
import SpecialShape.SpringShape;

import CommonShape.CommonShape;

import net.phys2d.raw.Body;

/**
 * Container class for contain the all shape.
 * @author Magic Board Team
 *
 */

public class ContainerAllShape implements Cloneable
{
	private LinkedList<Object> shapecontainer;
	private ArrayList<Body> bodyContainer;
	
	public ContainerAllShape()
	{
		this.shapecontainer = new LinkedList<Object>();
		this.bodyContainer  = new ArrayList<Body>();
	}
	
	/**
	 * Set body container
	 * @param bodyContainer
	 */
	public void setBody(ArrayList<Body> bodyContainer)
	{
		this.bodyContainer = bodyContainer;
	}
	
	/**
	 * Set shape container
	 * @param shapecontainer
	 */
	public void setShape(LinkedList<Object> shapecontainer)
	{
		this.shapecontainer = shapecontainer;
	}
	
	/**
	 * Add Object in to the link list.
	 * @param obj
	 */
	public void addShape(Object obj)
	{
		this.shapecontainer.addFirst(obj);
	}
	
	/**
	 * Add Object in to the link list at the last.
	 * @param obj
	 */
	public void addShapeLast(Object obj)
	{
		this.shapecontainer.addLast(obj);
	}
	
	/**
	 * Add Body object in to array list.
	 * @param obj
	 */
	public void addBody(Body obj)
	{
		this.bodyContainer.add(obj);
	}
	
	/**
	 * Get object shape form the link list.
	 * @param index
	 * @return Object of the shape
	 */
	public Object getShape(int index)
	{
		return this.shapecontainer.get(index);
	}
	
	/**
	 * Get Object body form the array list. 
	 * @param index
	 * @return
	 */
	public Body getBody(int index)
	{
		return this.bodyContainer.get(index);
	}
	
	/**
	 * Remove shape object form select index
	 * @param index
	 */
	public void removeShapeSelectIndex(int index)
	{
		this.shapecontainer.remove(index);
	}
	
	/**
	 * Remove body object form select index
	 * @param index
	 */
	public void removeBodySelectIndex(int index)
	{
		this.bodyContainer.remove(index);
	}
	
	/**
	 * Remove body all object.
	 *
	 */
	public void removeBodyall()
	{
		while(this.bodyContainer.size() > 0)
		{
			this.bodyContainer.remove(0);
		}
	}
	
	public void removeShapeall()
	{
		while(this.shapecontainer.size()>0)
		{
			this.shapecontainer.remove(0);
		}
	}
	
	/**
	 * Set index of the Shape.
	 * @param index
	 * @param obj
	 */
	public void setShapeIndex(int index,Object obj)
	{
		this.shapecontainer.set(index,obj);
	}
	
	/**
	 * Get size of shape container
	 * @return Container size
	 */
	public int getShapeContainerSize()
	{
		return this.shapecontainer.size();
	}
	
	/**
	 * Get size of body container
	 * @return
	 */
	public int getBodyContainerSize()
	{
		return this.bodyContainer.size();
	}
	
	/**
	 * Check the Container are contain shape or not
	 * @param shape
	 * @return
	 */
	public boolean isContain(Object shape)
	{
		return this.shapecontainer.contains(shape);
	}
	
	/**
	 * Check the Container are contain body or not
	 * @param body
	 * @return
	 */
	public boolean isContainBody(Body body)
	{
		return this.bodyContainer.contains(body);
	}

	
	/**
	 * Clone the Body to Object.
	 */
    public Object clone() 
    {	
        try 
        {
        	ContainerAllShape obj = (ContainerAllShape)super.clone();
        	obj.bodyContainer     = (ArrayList<Body>) this.bodyContainer.clone();
        	obj.shapecontainer = new LinkedList<Object>();
        	for(int i =0;i<this.shapecontainer.size();i++)
        	{
        		if(this.shapecontainer.get(i).getClass().getSuperclass().getCanonicalName().equalsIgnoreCase("CommonShape.CommonShape"))
        		{
        			CommonShape com = (CommonShape)this.shapecontainer.get(i);
            		obj.shapecontainer.addLast(com.clone());
        		}
        		
        		else if(this.shapecontainer.get(i).getClass().getSuperclass().getCanonicalName().equalsIgnoreCase("SpecialShape.SpecialShape"))
        		{
        			SpecialShape spe = (SpecialShape) this.shapecontainer.get(i);
        			obj.shapecontainer.addLast(spe.clone());
        		}
    
        	}
        	
        	for(int i =0;i<this.shapecontainer.size();i++)
        	{
        		if(this.shapecontainer.get(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.ArrowShape"))
        		{
        			ArrowShape spe = (ArrowShape) this.shapecontainer.get(i);
        			obj.shapecontainer.set(i, spe.clone());
        		}
        		
        		else if(this.shapecontainer.get(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.FixJointShape"))
        		{
        			FixJointShape spe = (FixJointShape) this.shapecontainer.get(i);
        			obj.shapecontainer.set(i, spe.clone(this,obj));
        		}
        		
        		else if(this.shapecontainer.get(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.JointShape"))
        		{
        			JointShape spe = (JointShape) this.shapecontainer.get(i);
        			obj.shapecontainer.set(i,spe.clone(this,obj));
        		}
        		
        		else if(this.shapecontainer.get(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.RopeShape"))
        		{
        			RopeShape spe = (RopeShape) this.shapecontainer.get(i);
        			obj.shapecontainer.set(i,spe.clone(this,obj));
        		}
        		
        		else if(this.shapecontainer.get(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.SpringShape"))
        		{
        			SpringShape spe = (SpringShape) this.shapecontainer.get(i);
        			obj.shapecontainer.set(i,spe.clone(this,obj));
        		}
        	}
            return obj;
        }
        
        catch (CloneNotSupportedException e) 
        {
            throw new InternalError(e.toString());
        }
    }
	
}
