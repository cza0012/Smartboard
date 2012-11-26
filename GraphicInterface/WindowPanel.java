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
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import CommonShape.CommonShape;
import MainInterface.RunProgram;

public class WindowPanel extends JPanel 
{
	
	public WindowPanel()
	{
		setLayout(null);
	}
	
	public void SetName(String name)
	{
		vNameLabel = new JLabel(name);
		vNameLabel.setBounds(30,60,70,20);
		
		add(vNameLabel);
	}
	
	public void invokeCombo1(String name,String[] Component,boolean editable,String Initial)
	{
		list1  = new JComboBox(Component);
		list1.setEditable(editable);
		list1.setBounds(10,10, 70, 20);
		list1.setSelectedItem(Initial);
	
		
		iLabel1 = new JLabel();
		iLabel1.setText(name);
		iLabel1.setBounds(100,10,100,20);
		
		OkAction 		= new InternalEvent("Submit");
		CancelAction 	= new InternalEvent("Cancel");
		
		iOk = new JButton(OkAction);
		iOk.setText("Submit");
		iOk.setBounds(165,120,80,20);
		iCancel = new JButton(CancelAction);
		iCancel.setText("Cancel");
		iCancel.setBounds(255,120,80,20);
		
		this.add(iOk);
		this.add(iCancel);
		
		this.add(list1);
		this.add(iLabel1);
	}
	public void invokeCombo2(String name , String []Component,boolean editable,String Initial)
	{
		list2  = new JComboBox(Component);
		list2.setEditable(editable);
		list2.setBounds(10,40, 70, 20);
		list2.setSelectedItem(Initial);
		
		iLabel2 = new JLabel();
		iLabel2.setText(name);
		iLabel2.setBounds(100,40,100,20);
		
		
		this.add(list2);
		this.add(iLabel2);
		
	}
	public void invokeCombo3(String name , String []Component,boolean editable,String Initial)
	{
		list3  = new JComboBox(Component);
		list3.setEditable(editable);
		list3.setBounds(10,70, 70, 20);
		list3.setSelectedItem(Initial);
		
		iLabel3 = new JLabel();
		iLabel3.setText(name);
		iLabel3.setBounds(100,70,100,20);
		
		
		this.add(list3);
		this.add(iLabel3);
		
	}
	public void invokeCombo4(String name , String []Component,boolean editable,String Initial)
	{
		list4  = new JComboBox(Component);
		list4.setEditable(editable);
		list4.setBounds(10,100, 70, 20);
		list4.setSelectedItem(Initial);
		
		iLabel4 = new JLabel();
		iLabel4.setText(name);
		iLabel4.setBounds(100,100,100,20);
		
		
		this.add(list4);
		this.add(iLabel4);
		
	}
	
	public void colorPicker()
	{
		this.colorLabel = new JLabel();
		this.colorLabel.setText("Color:");

		this.iColorpicker = new JButton();
        CommonShape com = (CommonShape)RunProgram.runner.drawing.shapeContainer.getShape(RunProgram.runner.drawing.mouse.getIndividualSelectIndex());
		this.iColorpicker.setBackground(com.getColor());
		this.iColorpicker.setBounds(165,30,40,40);
	    Border thickBorder = new LineBorder(Color.black, 2);
	    
		this.iColorpicker.setBorder(thickBorder);
		
		this.iColorpicker.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				Color c = JColorChooser.showDialog(((Component) arg0.getSource())
			            .getParent(), "Color Bar",  Color.white);
				if(c != null)
				{
					iColorpicker.setBackground(c);
				}
				RunProgram.runner.repaint();
			}
		});
		this.add(iColorpicker);
	}
	
	public void SetImage(String FileName)
	{
		
		try
		{
			Img = ImageIO.read(new File(FileName));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void paintComponent(Graphics g)
	{
		
		super.paintComponents(g);
		g.drawImage(Img,230,0,100,100,this);
	}
	
		public static JComboBox list1;
		private JLabel iLabel1;
		
		public static JComboBox list2;
		private JLabel iLabel2;
		
		public static JComboBox list3;
		private JLabel iLabel3;
		
		public static JComboBox list4;
		private JLabel iLabel4;
		
		private JLabel colorLabel;
		
		private JButton iOk;
		private JButton iCancel;
		
		private Image Img;
		
		private JLabel vNameLabel;
		
		private Action OkAction;
		private Action CancelAction;
	
		public static JButton iColorpicker;
}
