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
package XMLConnection;



import java.awt.Color;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

import MainInterface.RunProgram;
import SpecialShape.ArrowShape;
import SpecialShape.FixJointShape;
import SpecialShape.JointShape;
import SpecialShape.RopeShape;
import SpecialShape.SpecialShape;
import SpecialShape.SpringShape;
import Template.Template;

import AlgorTools.ShapeConvert;
import CommonShape.*;

public class Internalize extends CommunicatorXML{
	
	private LinkedList<Object> container;
	private ArrayList<SpecialShape> specialContainer;
	private ArrayList<Integer> sIndexs;
	private ArrayList<Integer> eIndexs;
	public LinkedList<Object> readXMLsaveFile(String fName)
	{
		specialContainer = new ArrayList<SpecialShape>();
		sIndexs = new ArrayList<Integer>();
		eIndexs = new ArrayList<Integer>();
		
		try 
		{
			container = new LinkedList<Object>();
			
			//InputStream fin = new FileInputStream("SmartBoardSave.xml");
			InputStream fin = new FileInputStream(fName);
			InputStream in = new BufferedInputStream(fin);
			//InputStreamReader in = new InputStreamReader(bin,"8859_1");
			Reader reader = new InputStreamReader(in,"8859_1");
			StringBuffer sb = new StringBuffer();
			int c;
			while((c=in.read())!=-1)
			{
				sb.append((char)c);
			}
			
			String document = sb.toString();
			int start ;//= document.indexOf(header) + header.length();
			int end;
			
			start = document.indexOf(template_s)+template_s.length();
			end = document.indexOf(template_e);
			String tempS = document.substring(start,end);
			
			if(tempS.equalsIgnoreCase("Default"))
			{
				Template.tState = Template.tState.Default;	
			}
			
			else if(tempS.equalsIgnoreCase("Room"))
			{
				Template.tState = Template.tState.Room;	
			}
			
			else if(tempS.equalsIgnoreCase("Classroom"))
			{
				Template.tState = Template.tState.Classroom;	
			}
			
			else if(tempS.equalsIgnoreCase("Space"))
			{
				Template.tState = Template.tState.Space;	
			}
			
			else if(tempS.equalsIgnoreCase("Snooker"))
			{
				Template.tState = Template.tState.Snooker;	
			}
			
			
			start = document.indexOf(bgImage_s)+bgImage_s.length();
			end = document.indexOf(bgImage_e);
			tempS = document.substring(start, end);
			if(!tempS.equals("null")){
				RunProgram.runner.drawing.mouse.setBackground(tempS);
			}
			
			
			
			start = document.indexOf(shape_s)+shape_s.length();
			//end = document.indexOf(shape_e);
			document = document.substring(start);
			
			String startTag = quantity_s;
			String endTag = quantity_e;
			
			start = document.indexOf(startTag)+startTag.length();
			end = document.indexOf(endTag);	
			int numShape = Integer.parseInt(document.substring(start,end));
			System.out.println("Shape Quantities = "+numShape);
			//document which contains only all shape//
			document = document.substring(end+endTag.length());
			int st = document.indexOf("<");
			int special = 0;
			int common = 0;
			for(int i=0;i<numShape;i++)
			{
				int tmp = document.indexOf(">")+1;
				String shapeTag = document.substring(st,tmp);
				shapeTag.trim();
				
				
				if(shapeTag.equals(triangle_s))
				{
					start = document.indexOf(triangle_s)+triangle_s.length();
					end = document.indexOf(triangle_e);
					String shapeData = document.substring(start,end);
					this.buildTriangle(shapeData);
					document = document.substring(end+triangle_e.length());
					System.out.println("triangle->"+i);
					common++;
				}
				else if(shapeTag.equals(rectangle_s))
				{
					start = document.indexOf(rectangle_s)+rectangle_s.length();
					end = document.indexOf(rectangle_e);
					String shapeData = document.substring(start,end);
					this.buildRectangle(shapeData);
					document = document.substring(end+rectangle_e.length());
					System.out.println("rectangle->"+i);
					common++;
				}
				else if(shapeTag.equals(polygon_s))
				{
					start = document.indexOf(polygon_s)+polygon_s.length();
					end = document.indexOf(polygon_e);
					String shapeData = document.substring(start,end);
					this.buildPolygon(shapeData);
					document = document.substring(end+polygon_e.length());
					System.out.println("polygon->"+i);
					common++;
				}
				else if(shapeTag.equals(ellipse_s))
				{
					start = document.indexOf(ellipse_s)+ellipse_s.length();
					end = document.indexOf(ellipse_e);
					String shapeData = document.substring(start,end);
					this.buildEllipse(shapeData);
					document = document.substring(end+ellipse_e.length());
					System.out.println("ellipse->"+i);
					common++;
				}	
				else if(shapeTag.equals(fixjoint_s))
				{
					start = document.indexOf(fixjoint_s)+fixjoint_s.length();
					end = document.indexOf(fixjoint_e);
					String shapeData = document.substring(start,end);
					this.buildFixJoint(shapeData,common);
					document = document.substring(end+fixjoint_e.length());
					System.out.println("fixjoint->"+i);
				
				}
				
				else if(shapeTag.equals(joint_s))
				{
					start = document.indexOf(joint_s)+joint_s.length();
					end = document.indexOf(joint_e);
					String shapeData = document.substring(start,end);
					this.buildJoint(shapeData,common);
					document = document.substring(end+joint_e.length());
					System.out.println("joint->"+i);
				
				}
				
				else if(shapeTag.equals(spring_s))
				{
					start = document.indexOf(spring_s)+spring_s.length();
					end = document.indexOf(spring_e);
					String shapeData = document.substring(start,end);
					this.buildSpring(shapeData,common);
					document = document.substring(end+spring_e.length());
					System.out.println("spring->"+i);
				
				}
				
				else if(shapeTag.equals(rope_s))
				{
					start = document.indexOf(rope_s)+rope_s.length();
					end = document.indexOf(rope_e);
					String shapeData = document.substring(start,end);
					this.buildRope(shapeData,common);
					document = document.substring(end+rope_e.length());
					System.out.println("rope->"+i);
				
				}
				
				else if(shapeTag.equals(arrow_s))
				{
					start = document.indexOf(arrow_s)+arrow_s.length();
					end = document.indexOf(arrow_e);
					String shapeData = document.substring(start,end);
					this.buildArrow(shapeData);
					document = document.substring(end+arrow_e.length());
					System.out.println("arrow->"+i);
				
				}
				
			}
			start = document.indexOf(note_s)+note_s.length();
			end = document.indexOf(note_e);
			String Notes = document.substring(start, end);
			//System.out.println(Notes);
			buildNotes(Notes);
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		LinkedList<Object> tempContainer = new LinkedList<Object>();
		for(int i = this.container.size()-1;i>=0;i--)
		{
			tempContainer.add(this.container.get(i));
		}
		setNode();
		return this.container;
	}
	public void buildNotes(String Notes)
	{
		int start = Notes.indexOf(quantity_s)+quantity_s.length();
		int end = Notes.indexOf(quantity_e);
		int q = Integer.parseInt(Notes.substring(start,end));
		Notes = Notes.substring(end+quantity_e.length());
		//System.out.println(Notes);
		if(q>0){
			ArrayList<Line2D> noteArr = new ArrayList<Line2D>();
			String tmp;
			for(int i = 0;i<q;i++)
			{
				start = Notes.indexOf(startpoint_s)+startpoint_s.length();
				end = Notes.indexOf(startpoint_e);
				tmp = Notes.substring(start,end);
				int sep = tmp.indexOf(",");
				double sx = Double.parseDouble(tmp.substring(0,sep));
				double sy = Double.parseDouble(tmp.substring(sep+1,tmp.length()));
				
				Notes = Notes.substring(end+startpoint_e.length());
				
				start = Notes.indexOf(endpoint_s)+endpoint_s.length();
				end = Notes.indexOf(endpoint_e);
				tmp = Notes.substring(start,end);
				sep = tmp.indexOf(",");
				double ex = Double.parseDouble(tmp.substring(0,sep));
				double ey = Double.parseDouble(tmp.substring(sep+1,tmp.length()));
				
				Line2D l = new Line2D.Double(sx,sy,ex,ey);
				noteArr.add(l);
				//System.out.println(sx+","+sy+"-"+ex+","+ey);
				RunProgram.runner.drawing.setNote(noteArr);
			}
		}
	}
	public void buildFixJoint(String shapeData,int common)
	{
		int start;
		int end;
		///Joint///
			start = shapeData.indexOf(startindex_s)+startindex_s.length();
			end = shapeData.indexOf(startindex_e);
			String point = shapeData.substring(start,end);
			int sIndex = Integer.parseInt(point);
			shapeData = shapeData.substring(end+startindex_e.length());
			
			start = shapeData.indexOf(endindex_s)+endindex_s.length();
			end = shapeData.indexOf(endindex_e);
			point = shapeData.substring(start,end);
			int eIndex = Integer.parseInt(point);
			
			sIndexs.add(sIndex);
			eIndexs.add(eIndex);
			
			shapeData = shapeData.substring(end+endindex_e.length());
			
			FixJointShape fj = new FixJointShape();
			
			container.add(fj);
			specialContainer.add(fj);
	}
	public void buildArrow(String shapeData)
	{
		int start;
		int end;
		start = shapeData.indexOf(point2d_s)+point2d_s.length();
		end = shapeData.indexOf(point2d_e);
		String point = shapeData.substring(start,end);
		int tmp = point.indexOf(",");
		double x = Double.parseDouble(point.substring(0,tmp));
		double y = Double.parseDouble(point.substring(tmp+1,point.length()));
		shapeData = shapeData.substring(end+point2d_e.length());
		//System.out.println(x+"....."+y);
		Point2D p2d = new Point2D.Double(x,y); 
		
		ArrayList<Polygon> polygons = new ArrayList<Polygon>();
		for(int i=0;i<3;i++)
		{
			java.awt.Polygon box = new Polygon();
			for(int k=0;k<4;k++)
			{
				start = shapeData.indexOf(point2d_s)+point2d_s.length();
				end = shapeData.indexOf(point2d_e);
				point = shapeData.substring(start,end);
				tmp = point.indexOf(",");
				int xx = Integer.parseInt(point.substring(0,tmp));
				int yy = Integer.parseInt(point.substring(tmp+1,point.length()));
				//System.out.println(x+"....."+y);
				shapeData = shapeData.substring(end+point2d_e.length());
				box.addPoint(xx,yy);
			}
			polygons.add(box);
		}
		
/*		for(int k=0;k<lines.size();k++)
		{
			System.out.println("Arrow line "+k+" is "+lines.get(k).getX1()+","+lines.get(k).getY1()+" to "+lines.get(k).getX2()+","+lines.get(k).getY2());
		}*/
		start = shapeData.indexOf(g_s)+g_s.length();
		end = shapeData.indexOf(g_e);
		String data = shapeData.substring(start,end);
		int temp = data.indexOf(",");
		float gForce = Float.parseFloat(data.substring(0,temp));
		data = data.substring(temp+1);
		int degree = Integer.parseInt(data.substring(0));
		
		ArrowShape arrow = new ArrowShape(polygons.get(0),polygons.get(1),polygons.get(2),p2d);
		arrow.setDegree(degree);
		arrow.setGravityForce(gForce);
		container.add(arrow);
	}
	public void buildRope(String shapeData,int common)
	{
		int start;
		int end;
		///Rope///
		
			///Start&End index
			start = shapeData.indexOf(startindex_s)+startindex_s.length();
			end = shapeData.indexOf(startindex_e);
			String point = shapeData.substring(start,end);
			int sIndex = Integer.parseInt(point);
			shapeData = shapeData.substring(end+startindex_e.length());
			
			start = shapeData.indexOf(endindex_s)+endindex_s.length();
			end = shapeData.indexOf(endindex_e);
			point = shapeData.substring(start,end);
			int eIndex = Integer.parseInt(point);
			
			shapeData = shapeData.substring(end+endindex_e.length());
			///point quantity
			start = shapeData.indexOf(quantity_s)+quantity_s.length();
			end = shapeData.indexOf(quantity_e);
			int quantity = Integer.parseInt(shapeData.substring(start,end));
			shapeData = shapeData.substring(end+quantity_e.length());
		
			///points
			ArrayList<Point2D> points = new ArrayList<Point2D>();
			for(int i=0;i<quantity;i++)
			{
				start = shapeData.indexOf(point2d_s)+point2d_s.length();
				end = shapeData.indexOf(point2d_e);
				point = shapeData.substring(start,end);
				int tmp = point.indexOf(",");
				double x = Double.parseDouble(point.substring(0,tmp));
				double y = Double.parseDouble(point.substring(tmp+1,point.length()));
				//System.out.println(x+"....."+y);
				Point2D p2d = new Point2D.Double(x,y); 
				points.add(p2d);
				shapeData = shapeData.substring(end+point2d_e.length());		
			}
			//System.out.println("LOAD Points >> "+points.size());
			//ArrayList<Line2D> lines = new ArrayList<Line2D>();
			ArrayList<Line2D> lines = ShapeConvert.convertPoint2DtoLine2D(points);
					
		//	System.out.println("Convert Line >> "+lines.size());
			RopeShape rope = new RopeShape(lines);
			
			if(sIndex!=-1){
				sIndexs.add(sIndex);
			}
			if(eIndex!=-1){
				eIndexs.add(eIndex);
			}
			
			container.add(rope);
			specialContainer.add(rope);
	}
	public void buildSpring(String shapeData,int common)
	{
		int start;
		int end;
		///Spring///
			start = shapeData.indexOf(startindex_s)+startindex_s.length();
			end = shapeData.indexOf(startindex_e);
			String point = shapeData.substring(start,end);
			int sIndex = Integer.parseInt(point);
			shapeData = shapeData.substring(end+startindex_e.length());
			
			start = shapeData.indexOf(endindex_s)+endindex_s.length();
			end = shapeData.indexOf(endindex_e);
			point = shapeData.substring(start,end);
			int eIndex = Integer.parseInt(point);
			
//			sIndex = common-1-sIndex;
//			eIndex = common-1-eIndex;
			sIndexs.add(sIndex);
			eIndexs.add(eIndex);
			
			shapeData = shapeData.substring(end+endindex_e.length());
			
			start = shapeData.indexOf(k_s)+k_s.length();
			end = shapeData.indexOf(k_e);
			point = shapeData.substring(start,end);
			
			//SpringShape s = new SpringShape((CommonShape)container.get(sIndex),(CommonShape)container.get(eIndex));
			SpringShape s = new SpringShape();
			s.setKSpring(Float.parseFloat(point));
			container.add(s);
			specialContainer.add(s);
	}
	
	public void buildJoint(String shapeData,int common)
	{
		int start;
		int end;
		///Joint///
			start = shapeData.indexOf(startindex_s)+startindex_s.length();
			end = shapeData.indexOf(startindex_e);
			String point = shapeData.substring(start,end);
			int sIndex = Integer.parseInt(point);
			shapeData = shapeData.substring(end+startindex_e.length());
			
			start = shapeData.indexOf(endindex_s)+endindex_s.length();
			end = shapeData.indexOf(endindex_e);
			point = shapeData.substring(start,end);
			int eIndex = Integer.parseInt(point);
			
			sIndexs.add(sIndex);
			eIndexs.add(eIndex);
			
			shapeData = shapeData.substring(end+endindex_e.length());
			
			start = shapeData.indexOf(rotatepoint_s)+rotatepoint_s.length();
			end = shapeData.indexOf(rotatepoint_e);
			point = shapeData.substring(start,end);
			int tmp = point.indexOf(",");
			double x = Double.parseDouble(point.substring(0,tmp));
			double y = Double.parseDouble(point.substring(tmp+1,point.length()));
			
			JointShape j = new JointShape();
			j.setRotatePoint(new Point2D.Double(x,y));
			container.add(j);
			specialContainer.add(j);
	}
	
	public void buildEllipse(String shapeData)
	{
		int start;
		int end;
		///Ellipse///
			start = shapeData.indexOf(cm_s)+cm_s.length();
			end = shapeData.indexOf(cm_e);
			String point = shapeData.substring(start,end);
			int tmp = point.indexOf(",");
			double x = Double.parseDouble(point.substring(0,tmp));
			double y = Double.parseDouble(point.substring(tmp+1,point.length()));
			//System.out.println(x+"....."+y);
			
		shapeData = shapeData.substring(end+cm_e.length());
		start = shapeData.indexOf(diameter_s)+diameter_s.length();
		end = shapeData.indexOf(diameter_e);
		point = shapeData.substring(start,end);
		double diameter = Double.parseDouble(point);
		
		EllipseShape ellipse = new EllipseShape(x-(diameter/2),y-(diameter/2),diameter);
		
		///POLYGON STATS///
		setShapeProperty(ellipse, shapeData);
		///END POLYGON STATS///
		container.add(ellipse);	
	}
	
	public void buildTriangle(String shapeData)
	{
		int start;
		int end;
		///POLYGON POINTS///
		TriangleShape triangle;
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		for(int i=0;i<3;i++)
		{
			start = shapeData.indexOf(point2d_s)+point2d_s.length();
			end = shapeData.indexOf(point2d_e);
			String point = shapeData.substring(start,end);
			int tmp = point.indexOf(",");
			double x = Double.parseDouble(point.substring(0,tmp));
			double y = Double.parseDouble(point.substring(tmp+1,point.length()));
			//System.out.println(x+"....."+y);
			Point2D p2d = new Point2D.Double(x,y); 
			points.add(p2d);
			shapeData = shapeData.substring(end+point2d_e.length());
		}
		triangle = new TriangleShape(points);
		///POLYGON STATS///
		setShapeProperty(triangle, shapeData);
		///END POLYGON STATS///
		container.add(triangle);	
	}
	
	public void buildRectangle(String shapeData)
	{
		int start;
		int end;
		RectangleShape rectangle;
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		for(int i=0;i<4;i++)
		{
			start = shapeData.indexOf(point2d_s)+point2d_s.length();
			end = shapeData.indexOf(point2d_e);
			String point = shapeData.substring(start,end);
			int tmp = point.indexOf(",");
			double x = Double.parseDouble(point.substring(0,tmp));
			double y = Double.parseDouble(point.substring(tmp+1,point.length()));
			Point2D p2d = new Point2D.Double(x,y); 
			points.add(p2d);
			shapeData = shapeData.substring(end+point2d_e.length());
		}
		rectangle = new RectangleShape(points);
		///POLYGON STATS///
		setShapeProperty(rectangle, shapeData);
		///END POLYGON STATS///
		container.add(rectangle);
	}
	
	public void buildPolygon(String shapeData)
	{
		int start,end;
		PolygonShape polygon;
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		
		start = shapeData.indexOf(quantity_s)+quantity_s.length();
		end = shapeData.indexOf(quantity_e);
		String quantity = shapeData.substring(start,end);
		int v = Integer.parseInt(quantity);
		System.out.println("......."+v);
		shapeData = shapeData.substring(quantity_e.length());
		for(int i=0;i<v;i++)
		{
			start = shapeData.indexOf(point2d_s)+point2d_s.length();
			end = shapeData.indexOf(point2d_e);
			String point = shapeData.substring(start,end);
			int tmp = point.indexOf(",");
			double x = Double.parseDouble(point.substring(0,tmp));
			double y = Double.parseDouble(point.substring(tmp+1,point.length()));
			System.out.println("X = "+x+", Y = "+y);
			Point2D p2d = new Point2D.Double(x,y); 
			points.add(p2d);
			shapeData = shapeData.substring(end+point2d_e.length());
		}
		polygon = new PolygonShape(points);System.out.println("fuk = "+points.size());
		///POLYGON STATS///
		setShapeProperty(polygon, shapeData);
		///END POLYGON STATS///
		container.add(polygon);
	}
	public void setShapeProperty(CommonShape shape,String shapeData)
	{
//		/POLYGON STATS///
		int start = shapeData.indexOf(mass_s)+mass_s.length();
		int end = shapeData.indexOf(mass_e);
		String data = shapeData.substring(start,end);
		float mass = Float.parseFloat(data);
		shape.setMass(mass);
		
		shapeData = shapeData.substring(end+mass_e.length());
		start = shapeData.indexOf(uS_s)+uS_s.length();
		end = shapeData.indexOf(uS_e);
		data = shapeData.substring(start,end);
		float mu_S = Float.parseFloat(data);
		shape.setMs(mu_S);
		
		shapeData = shapeData.substring(end+uS_e.length());
		start = shapeData.indexOf(uK_s)+uK_s.length();
		end = shapeData.indexOf(uK_e);
		data = shapeData.substring(start,end);
		float mu_K = Float.parseFloat(data);
		shape.setMk(mu_K);
		
		shapeData = shapeData.substring(end+uK_e.length());
		start = shapeData.indexOf(isFix_s)+isFix_s.length();
		end = shapeData.indexOf(isFix_e);
		data = shapeData.substring(start,end);
		shapeData = shapeData.substring(end+isFix_e.length());
		if(data.equalsIgnoreCase("true")&&(shapeData.indexOf(cross_s)!=-1))
		{
			start = shapeData.indexOf(cross_s)+cross_s.length();
			end = shapeData.indexOf(cross_e);
			data = shapeData.substring(start,end);
			int tmp = data.indexOf(",");
			double x = Double.parseDouble(data.substring(0,tmp));
			double y = Double.parseDouble(data.substring(tmp+1,data.length()));
			Point2D p2d = new Point2D.Double(x,y); 
			shape.generateCross(p2d);
			shapeData = shapeData.substring(end+cross_e.length());	
		}
		start = shapeData.indexOf(bounce_s)+bounce_s.length();
		end = shapeData.indexOf(bounce_e);
		data = shapeData.substring(start,end);
		shape.setBounce(Float.parseFloat(data));
		
		start = shapeData.indexOf(color_s)+color_s.length();
		end = shapeData.indexOf(color_e);
		data = shapeData.substring(start,end);
		int r = 0, g = 0, b = 0;
		int tmp = data.indexOf(",");
		r = Integer.parseInt(data.substring(0,tmp));
		data = data.substring(tmp+1);
		tmp = data.indexOf(",");
		g = Integer.parseInt(data.substring(0,tmp));
		data = data.substring(tmp+1);
		b = Integer.parseInt(data);
		shape.setColor(new Color(r,g,b));
		
		start = shapeData.indexOf(isBg_s)+isBg_s.length();
		end = shapeData.indexOf(isBg_e);
		data = shapeData.substring(start,end);
		if(data.equalsIgnoreCase("true")){
			shape.setToBackground();
		}
		
		start = shapeData.indexOf(image_s)+image_s.length();
		end = shapeData.indexOf(image_e);
		data = shapeData.substring(start,end);
		if(!data.equalsIgnoreCase("null")){
			shape.setImage(data);
		}
		shapeData = shapeData.substring(end+image_e.length());
		///END POLYGON STATS///
		
	}
	public void setNode()
	{
	
		for(int i = 0; i<specialContainer.size();i++)
		{
			specialContainer.get(i).setNodePrepare((CommonShape)container.get(sIndexs.get(i)),
					(CommonShape)container.get(eIndexs.get(i)));
		}
	}


}