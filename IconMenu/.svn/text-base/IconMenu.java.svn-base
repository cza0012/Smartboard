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
package IconMenu;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import DrawingInterface.DrawingInterface;
import GraphicInterface.MenuEvent;


public class IconMenu 
{
	private Image img;
	private int _x;
	private int _y;
	
	private MenuEvent iEvent;
	
	private boolean isSmall ;
	
	/**
	 * 
	 * @param fileName : path of the picture icon
	 * @param x : init x pos.
	 * @param y : init y pos.
	 */
	public IconMenu(String fileName,int x, int y,String MenuType)
	{
		try 
		{
			img = ImageIO.read(new File(fileName));
		} 
		
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_x = x;
		_y = y;
		
		iEvent = new MenuEvent(MenuType);
		isSmall = true;
	}
	
	public void setXY(int x,int y)
	{
		_x = x;
		_y = y;
	}
	
	public int getX()
	{
		return _x; 
	}
	
	public int getY()
	{
		return _y;
	}
	public boolean getState()
	{
		return isSmall;
	}
	public MenuEvent getMenu()
	{
		return iEvent;
	}
	public void switchSize()
	{
		if(isSmall)
		{
			
			isSmall = false;
		}
		else
		{
			
			isSmall = true;
		}
	}
	
	/**
	 * The method returns
	 * TRUE >> Mouse is inside the this.img IconMenu, 
	 * FALSE >> Mouse is outside the this.img IconMenu
	 * @param e : Current MouseEvent
	 * @return
	 */
	public boolean contains(MouseEvent e)
	{
		return ( e.getX() > _x && e.getX() < (_x + img.getWidth(null)) &&
				 e.getY() > _y && e.getY() < (_y + img.getHeight(null)));
	}
	
	public void actionPerformed(DrawingInterface interfacePanel)
	{
		iEvent.HandleEvent(interfacePanel);
	}

	public void setImg(Image img) 
	{
		this.img = img;
	}

	public Image getImg() 
	{
		return img;
	}
}
