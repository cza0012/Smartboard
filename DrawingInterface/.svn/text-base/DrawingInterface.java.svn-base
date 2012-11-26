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
package DrawingInterface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import AlgorTools.CheckShapeContainer;
import AlgorTools.ShapeProperty;
import CommonShape.CommonShape;
import CommonShape.EllipseShape;
import CommonShape.PolygonShape;
import CommonShape.RectangleShape;
import CommonShape.TriangleShape;
import Container.ContainerAllShape;
import EngineDrawing.AutoBalloonState;
import EngineDrawing.AutoCarState;
import EngineDrawing.AutoCommentState;
import EngineDrawing.AutoEllipseState;
import EngineDrawing.AutoArrowState;
import EngineDrawing.AutoFixJointState;
import EngineDrawing.AutoGenState;
import EngineDrawing.AutoJointState;
import EngineDrawing.AutoRectangleState;
import EngineDrawing.AutoRopeState;
import EngineDrawing.AutoRubberState;
import EngineDrawing.AutoSpringState;
import EngineDrawing.AutoTriangleState;
import EngineDrawing.DrawingState;
import EngineDrawing.GroupDrawingState;
import EngineDrawing.IndividualSelectState;
import EngineDrawing.ReleaseLeftState;
import EngineDrawing.ReleaseRightState;
import EngineDrawing.RotateState;
import EngineDrawing.ScaleState;
import EngineDrawing.TransformState;
import EngineDrawing.ZoomState;
import GraphicInterface.ArrowBuilder;
import GraphicInterface.CirBuilder;
import GraphicInterface.PenBuilder;
import GraphicInterface.PolygonBuilder;
import GraphicInterface.RecBuilder;
import GraphicInterface.ShapeBuilder;
import GraphicInterface.ShapeDirector;
import GraphicInterface.SpringBuilder;
import GraphicInterface.TriBuilder;
import IconMenu.IconMenu;
import IconMenu.RecycleBin;
import Memento.SmartBoardCaretaker;
import Memento.SmartBoardOriginator;
import SpecialShape.ArrowShape;
import SpecialShape.CrossShape;
import SpecialShape.FixJointShape;
import SpecialShape.JointShape;
import SpecialShape.RopeShape;
import SpecialShape.SpringShape;
import TempObject.LocationOfString;
import TempObject.TempFixJointShape;
import TempObject.TempSpringShape;
import Template.Template;
import UserInterface.Mouse;

public class DrawingInterface  extends JDesktopPane implements MouseListener,MouseMotionListener,Runnable
{
	/** GUI Panel*/
	private IconMenu[] MainMenuSmall;
	private IconMenu[] MainMenuBig;
	
	private IconMenu[] FileMenuSmall;
	private IconMenu[] FileMenuBig;
	
	private IconMenu[] DrawToolSmall;
	private IconMenu[] DrawToolBig;
	
	private IconMenu[] AutoShapeSmall;
	private IconMenu[] AutoShapeBig;
	
	private Image MenuBg;
	private Image FileBg;
	private Image DrawTBg;
	private Image AutoSBg;
	private Image[] ImgShow;
	
	private JInternalFrame vinFrame;
	
	private ShapeDirector iD ;
	private ShapeBuilder ib;
	
	private JPanel CurPanel;
	
	public enum state{Drawing,Main,File,DrawMode,AutoShape};
	public static state iState ;
	
	private IconMenu[] ToolShortcut;
	private IconMenu[] ToolSelect;
	
	private IconMenu[] EditSc;
	private IconMenu[] EditSL;
	
	/** End GUI Panel*/
	
	
	/** Drawing Variable*/
	
	/**
	 * Pen accurate default is Medium
	 * 1. Highest 	= 5
	 * 2. High  	= 11
	 * 3. Medium	= 17
	 * 4. Low		= 25
	 * 5. Lowest	= 33
	 */ 
	public int penAccurate = 17;
	
	private final int mouseLeft   = 1;
	private final int mouseMiddle = 2;
	private final int mouseRight  = 3;
	
	public Mouse mouse;
	public ContainerAllShape shapeContainer;
	public ArrayList<ArrayList<Line2D>> lineContainer;
	private ArrayList<Point2D> lineDrawing;
	private Ellipse2D lineVertex;
	
	public ArrayList<Line2D> commentContainer;

	public  Graphics2D g2;
	
	// The Shape that want to show description
	private CommonShape shapeDescription = null;
	// The description position that you must to draw.
	private Point2D 	descriptionPosition = null;
	// For count the time
	private Thread counter;
	// Time to show is correct
	private boolean show = false;
	// Re count
	private boolean recount = false;
	
	// RecycleBin
	private RecycleBin bin;
	
	// Rubber
	private Polygon rubber;
	
	// Redo Undo Memento
	public SmartBoardOriginator  currentMemento;
	public SmartBoardCaretaker	 undoTrunk;
	public SmartBoardCaretaker	 redoTrunk;
	private JInternalFrame 		 cTemplate;
	
	private Color	penColor			= Color.WHITE;
	private Color 	lecColor			= Color.RED;
	private Color	iselectColor		= Color.WHITE;
	private Color	gSelectColor		= Color.WHITE;
	
	
	/** End Drawing Variable*/

	
	public DrawingInterface(int pwidth,int pheight)
	{
		
		/** GUI Panel*/
		this.iState = state.Drawing;
		this.setSize(pwidth,pheight);
		
		/*
		 * Create array of obj IconMenu 
		 * Icon Menu works likely button. It has contains method and actionperformed 
		 * The following parameters is define in constructor
		 * ("Filename"(String),Assign  x pos (top left),y (too),"Event Name"(String))
		 */
		this.MainMenuBig = new IconMenu[7];
		this.MainMenuSmall = new IconMenu[7];
		
		this.FileMenuSmall = new IconMenu[5];
		this.FileMenuBig = new IconMenu[5];
		 
		this.DrawToolSmall = new IconMenu[7];
		this.DrawToolBig = new IconMenu[7];
		
		this.AutoShapeSmall = new IconMenu[6];
		this.AutoShapeBig = new IconMenu[6];
		
		/*
		 * Create array of obj IconMenu 
		 * Icon Menu works likely button. It has contains method and actionperformed 
		 * The following parameters is define in constructor
		 * ("Filename"(String),Assign  x pos (top left),y (too),"Event Name"(String))
		 */
		MainMenuBig = new IconMenu[7];
		MainMenuSmall = new IconMenu[7];
		
		FileMenuSmall = new IconMenu[5];
		FileMenuBig = new IconMenu[5];
		 
		DrawToolSmall = new IconMenu[7];
		DrawToolBig = new IconMenu[7];
		
		AutoShapeSmall = new IconMenu[6];
		AutoShapeBig = new IconMenu[6];
		
		//ToolShortcut 	= new IconMenu[7];
		//ToolSelect 		= new IconMenu[7];
		
		ToolShortcut 	= new IconMenu[9];
		ToolSelect 		= new IconMenu[9];
		
		EditSc = new IconMenu[2];
		EditSL = new IconMenu[2];
		 
		//Main Page Icon Loading 
		MainMenuSmall[0] = new IconMenu("./pic/FileSmall.png",this.getWidth()/2-100,this.getHeight()/2-250,"File");
		MainMenuSmall[1] =  new IconMenu("./pic/HelpSmall.png",this.getWidth()/2+100,this.getHeight()/2-150,"Help");
		MainMenuSmall[2] = new IconMenu("./pic/DrawToolSmall.png",this.getWidth()/2-300,this.getHeight()/2-150,"DrawMode");
		MainMenuSmall[3] = new IconMenu("./pic/AutoShapeSmall.png",this.getWidth()/2-300,this.getHeight()/2+50,"AutoShape");
		MainMenuSmall[4] = new IconMenu("./pic/PropSmall.png",this.getWidth()/2+100,this.getHeight()/2+50,"Properties");
		MainMenuSmall[5] = new IconMenu("./pic/TemplateSmall.png",this.getWidth()/2-100,this.getHeight()/2+150,"Template");
		MainMenuSmall[6] = new IconMenu("./pic/RunSmall.png",this.getWidth()/2-100,this.getHeight()/2-50,"Run");
									
		MainMenuBig[0] = new IconMenu("./pic/FileBig.png",this.getWidth()/2-100,this.getHeight()/2-250,"File");
		MainMenuBig[1] =  new IconMenu("./pic/HelpBig.png",this.getWidth()/2+100,this.getHeight()/2-150,"Help");
		MainMenuBig[2] = new IconMenu("./pic/DrawToolBig.png",this.getWidth()/2-300,this.getHeight()/2-150,"DrawMode");
		MainMenuBig[3] = new IconMenu("./pic/AutoShapeBig.png",this.getWidth()/2-300,this.getHeight()/2+50,"AutoShape");
		MainMenuBig[4] = new IconMenu("./pic/PropBig.png",this.getWidth()/2+100,this.getHeight()/2+50,"Properties");
		MainMenuBig[5] = new IconMenu("./pic/TemplateBig.png",this.getWidth()/2-100,this.getHeight()/2+150,"Template");
		MainMenuBig[6] = new IconMenu("./pic/RunBig.png",this.getWidth()/2-100,this.getHeight()/2-50,"Run");
	
		//File Menu ( Open ,new ,save ,save as) Small and Big Icon Load
		FileMenuSmall[0] =  new IconMenu("./pic/NewSmall.png", this.getWidth()/2-250, this.getHeight()/2-200,"New");
		FileMenuSmall[1] = new IconMenu("./pic/LoadSmall.png",this.getWidth()/2-50,this.getHeight()/2-200,"Load");
		FileMenuSmall[2] = new IconMenu("./pic/SaveSmall.png",this.getWidth()/2-250,this.getHeight()/2,"Save");
		FileMenuSmall[3] = new IconMenu("./pic/SaveAsSmall.png",this.getWidth()/2-50,this.getHeight()/2,"SaveAs");
		FileMenuSmall[4] = new IconMenu("./pic/BackSmall.png",this.getWidth()/2+150,this.getHeight()/2-250,"Back");
		
		FileMenuBig[0] =  new IconMenu("./pic/NewBig.png",this.getWidth()/2-250,this.getHeight()/2-200,"New");
		FileMenuBig[1] = new IconMenu("./pic/LoadBig.png",this.getWidth()/2-50,this.getHeight()/2-200,"Load");
		FileMenuBig[2] = new IconMenu("./pic/SaveBig.png",this.getWidth()/2-250,this.getHeight()/2,"Save");
		FileMenuBig[3] = new IconMenu("./pic/SaveAsBig.png",this.getWidth()/2-50,this.getHeight()/2,"SaveAs");
		FileMenuBig[4]= new IconMenu("./pic/BackBig.png",this.getWidth()/2+150,this.getHeight()/2-250,"Back");
		
		//Drawing tool small and big Icon Create
		DrawToolSmall[0] =  new IconMenu("./pic/PenSmall.png",this.getWidth()/2-100,this.getHeight()/2-200,"Pen");
		DrawToolSmall[1] = new IconMenu("./pic/RopeSmall.png",this.getWidth()/2+50,this.getHeight()/2-200,"Rope");
		DrawToolSmall[2] = new IconMenu("./pic/BasicJointSmall.png",this.getWidth()/2-250,this.getHeight()/2-50,"BasicJoint");
		DrawToolSmall[3] = new IconMenu("./pic/FixJointSmall.png",this.getWidth()/2-250,this.getHeight()/2-200,"FixJoint");
		DrawToolSmall[4] = new IconMenu("./pic/SpringSmall.png",this.getWidth()/2-100,this.getHeight()/2-50,"Spring");
		DrawToolSmall[5] = new IconMenu("./pic/ArrowSmall.png",this.getWidth()/2+50,this.getHeight()/2-50,"Arrow");
		DrawToolSmall[6] = new IconMenu("./pic/BackSmall.png",this.getWidth()/2+150,this.getHeight()/2-250,"Back");
		
		DrawToolBig[0] =  new IconMenu("./pic/PenBig.png",this.getWidth()/2-100,this.getHeight()/2-200,"Pen");
		DrawToolBig[1] = new IconMenu("./pic/RopeBig.png",this.getWidth()/2+50,this.getHeight()/2-200,"Rope");
		DrawToolBig[2] = new IconMenu("./pic/BasicJointBig.png",this.getWidth()/2-250,this.getHeight()/2-50,"BasicJoint");
		DrawToolBig[3] = new IconMenu("./pic/FixJointBig.png",this.getWidth()/2-250,this.getHeight()/2-200,"FixJoint");
		DrawToolBig[4] = new IconMenu("./pic/SpringBig.png",this.getWidth()/2-100,this.getHeight()/2-50,"Spring");
		DrawToolBig[5] = new IconMenu("./pic/ArrowBig.png",this.getWidth()/2+50,this.getHeight()/2-50,"Arrow");
		DrawToolBig[6] = new IconMenu("./pic/BackBig.png",this.getWidth()/2+150,this.getHeight()/2-250,"Back");
		
		
		//Auto Shape Small and Big Icon Create 
		AutoShapeSmall[0] = new IconMenu("./pic/RectangleSmall.png",this.getWidth()/2,this.getHeight()/2,"Rectangle");
		AutoShapeSmall[1] =  new IconMenu("./pic/TriangleSmall.png",this.getWidth()/2-80,this.getHeight()/2+90,"Triangle");
		AutoShapeSmall[2] = new IconMenu("./pic/CircleSmall.png",this.getWidth()/2-160,this.getHeight()/2,"Circle");
		AutoShapeSmall[3] = new IconMenu("./pic/CarSmall.png",this.getWidth()/2,this.getHeight()/2-150,"Car");
		AutoShapeSmall[4] = new IconMenu("./pic/BalloonSmall.png",this.getWidth()/2-160,this.getHeight()/2-150,"Balloon");
		AutoShapeSmall[5] = new IconMenu("./pic/BackSmall.png",this.getWidth()/2+150,this.getHeight()/2-250,"Back");
	
									
		AutoShapeBig[0] = new IconMenu("./pic/RectangleBig.png",this.getWidth()/2,this.getHeight()/2,"Rectangle");
		AutoShapeBig[1] =  new IconMenu("./pic/TriangleBig.png",this.getWidth()/2-80,this.getHeight()/2+90,"Triangle");
		AutoShapeBig[2] = new IconMenu("./pic/CircleBig.png",this.getWidth()/2-160,this.getHeight()/2,"Circle");
		AutoShapeBig[3] = new IconMenu("./pic/CarBig.png",this.getWidth()/2,this.getHeight()/2-150,"Car");
		AutoShapeBig[4] = new IconMenu("./pic/BalloonBig.png",this.getWidth()/2-160,this.getHeight()/2-150,"Balloon");
		AutoShapeBig[5] = new IconMenu("./pic/BackBig.png",this.getWidth()/2+150,this.getHeight()/2-250,"Back");
		
		this.ToolShortcut[0] = new IconMenu("./pic/PropertySc.png",this.getWidth()-400,0,"Properties");
		this.ToolShortcut[1] = new IconMenu("./pic/PenSC.png",this.getWidth()-360,0,"Pen");
		this.ToolShortcut[2] = new IconMenu("./pic/RopeSC.png",this.getWidth()-320,0,"Rope");
		this.ToolShortcut[3] = new IconMenu("./pic/SpringSC.png",this.getWidth()-280,0,"Spring");
		this.ToolShortcut[4] = new IconMenu("./pic/FixJointSC.png",this.getWidth()-240,0,"FixJoint");
		this.ToolShortcut[5] = new IconMenu("./pic/BasicJointSC.png",this.getWidth()-200,0,"BasicJoint");
		this.ToolShortcut[6] = new IconMenu("./pic/ArrowSc.png",this.getWidth()-160,0,"Arrow");
		this.ToolShortcut[7] = new IconMenu("./pic/PencilSc.png",this.getWidth()-200,40,"Comment");
		this.ToolShortcut[8] = new IconMenu("./pic/EraserSc.png",this.getWidth()-160,40,"Rubber");
		
		this.ToolSelect[0] = new IconMenu("./pic/PropertySL.png",this.getWidth()-400,0,"Properties");
		this.ToolSelect[1] = new IconMenu("./pic/PenSL.png",this.getWidth()-360,0,"Pen");
		this.ToolSelect[2] = new IconMenu("./pic/RopeSL.png",this.getWidth()-320,0,"Rope");
		this.ToolSelect[3] = new IconMenu("./pic/SpringSL.png",this.getWidth()-280,0,"Spring");
		this.ToolSelect[4] = new IconMenu("./pic/FixJointSL.png",this.getWidth()-240,0,"FixJoint");
		this.ToolSelect[5] = new IconMenu("./pic/BasicJointSL.png",this.getWidth()-200,0,"BasicJoint");
		this.ToolSelect[6] = new IconMenu("./pic/ArrowSL.png",this.getWidth()-160,0,"Arrow");
		this.ToolSelect[7] = new IconMenu("./pic/PencilSl.png",this.getWidth()-200,40,"Comment");
		this.ToolSelect[8] = new IconMenu("./pic/EraserSl.png",this.getWidth()-160,40,"Rubber");
		
		this.EditSc[0] = new IconMenu("./pic/RedoPic.png",this.getWidth()-70,130,"Redo");
		this.EditSc[1] = new IconMenu("./pic/UndoPic.png",this.getWidth()-120,130,"Undo");
		this.EditSL[0] = new IconMenu("./pic/RedoSLPic.png",this.getWidth()-70,130,"Redo");
		this.EditSL[1] = new IconMenu("./pic/UndoSLPic.png",this.getWidth()-120,130,"Undo");

		ImgShow = new Image[14];
		// Load Image for background (transperent in each Menu)
		try 
		{
			MenuBg	=	ImageIO.read(new File("./pic/MenuBg.png"));
			FileBg 	= 	ImageIO.read(new File("./pic/FileBg.png"));
			AutoSBg =  	ImageIO.read(new File("./pic/AutoSBg.png"));
			DrawTBg =  	ImageIO.read(new File("./pic/DrawTBg.png"));
			
			ImgShow[0] = ImageIO.read(new File("./pic/ArrowMini.png"));
			ImgShow[1] = ImageIO.read(new File("./pic/BalloonMini.png"));
			ImgShow[2] = ImageIO.read(new File("./pic/BasicJointMini.png"));
			ImgShow[3] = ImageIO.read(new File("./pic/FixJointMini.png"));
			ImgShow[4] = ImageIO.read(new File("./pic/SpringMini.png"));
			ImgShow[5] = ImageIO.read(new File("./pic/PenMini.png"));
			ImgShow[6] = ImageIO.read(new File("./pic/RopeMini.png"));
			ImgShow[7] = ImageIO.read(new File("./pic/TriangleMini.png"));
			ImgShow[8] = ImageIO.read(new File("./pic/RectangleMini.png"));
			ImgShow[9] = ImageIO.read(new File("./pic/CarMini.png"));
			ImgShow[10] = ImageIO.read(new File("./pic/CircleMini.png"));
			ImgShow[11] = ImageIO.read(new File("./pic/hand01.png"));
			ImgShow[12] = ImageIO.read(new File("./pic/PencilMini.png"));
			ImgShow[13] = ImageIO.read(new File("./pic/EraserMini.png"));

		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		
		}
		
		this.cTemplate = new JInternalFrame("Template");
		this.cTemplate.setSize(1280,800);
		this.cTemplate.add(new Template());
		this.add(cTemplate);
		
		vinFrame = new JInternalFrame("Property");
		vinFrame.setSize(350,180);
		
		iD = new ShapeDirector();
		
		this.CurPanel = new JPanel();
		vinFrame.add(CurPanel);
		this.add(vinFrame);
		
		this.setOpaque(true);
		/** End GUI Panel*/
		
		
		/** Drawing Panel*/
		
		this.mouse	       		= new Mouse();
		this.shapeContainer 	= new ContainerAllShape();

		this.lineContainer 		= new ArrayList<ArrayList<Line2D>>();
		this.lineDrawing    	= new ArrayList<Point2D>();
		this.lineVertex     	= new Ellipse2D.Double();
		
		this.commentContainer	= new ArrayList<Line2D>();
		
		this.bin				= new RecycleBin("./pic/binSmall.png",5,5);
		
		this.currentMemento 	= new SmartBoardOriginator();
		this.undoTrunk   		= new SmartBoardCaretaker();
		this.redoTrunk	 		= new SmartBoardCaretaker();
		this.currentMemento.setState((ContainerAllShape) this.shapeContainer.clone(),(ArrayList<ArrayList<Line2D>>) this.lineContainer.clone(),(Mouse) this.mouse.clone());
		
		/** End Drawing Panel*/
		
		rubber = new Polygon();
		rubber.addPoint(0+3000,0+3000);
		rubber.addPoint(0+3000,30+3000);
		rubber.addPoint(30+3000,30+3000);
		rubber.addPoint(30+3000,0+3000);
		
		
		this.setVisible(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.counter = new Thread(this);
		this.counter.start();
	}
	
	/**
	 * Get the mouse interface
	 * @return
	 */
	public Mouse getMouseInterface()
	{
		return this.mouse;
	}
	
	/**
	 * Get the line container
	 * @return
	 */
	public ArrayList<ArrayList<Line2D>> getLineContainer()
	{
		return this.lineContainer;
	}
	
	/**
	 * Get the line drawing
	 * @return
	 */
	public ArrayList<Point2D> getLineDrawing()
	{
		return this.lineDrawing;
	}
	
	/**
	 * Get the container of all shape
	 * @return
	 */
	public ContainerAllShape getContainerAllShape()
	{
		return this.shapeContainer;
	}
	
	/**
	 * Get the short note container
	 * @return
	 */
	public ArrayList<Line2D> getShortNote()
	{
		return this.commentContainer;
	}
	
	/**
	 * Set the comment container
	 * @param commentContainer
	 */
	public void setNote(ArrayList<Line2D> commentContainer)
	{
		this.commentContainer = commentContainer;
	}
	
	public void paintComponent(Graphics g)
	{	
		setDrawColor();
		
        g.setColor(Color.WHITE);
        g.fillRect(0,0,getWidth(), getHeight());
        g.setColor(this.penColor);
        g2 = (Graphics2D) g;
        
        if(iState == state.Drawing)
        {
        	// Draw the background
        	if(this.mouse.getBackground() != null)
        	{
        		g2.drawImage(this.mouse.getBackground(),0,0,null);
        	}
        	
            // Draw the old shape
            for(int i = this.shapeContainer.getShapeContainerSize()-1;i >=0;i--)
            {
            	Object obj = this.shapeContainer.getShape(i);
            	
            	if(obj.getClass().getCanonicalName().equalsIgnoreCase("CommonShape.TriangleShape"))
            	{
            		drawTriangle(g2,obj,i);
            	}
            	
            	else if(obj.getClass().getCanonicalName().equalsIgnoreCase("CommonShape.RectangleShape"))
            	{
            		drawRectangle(g2,obj,i);
            	}
            	
            	else if(obj.getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
            	{
            		drawEllipse(g2,obj,i);
            	}
            	
            	else if(obj.getClass().getCanonicalName().equalsIgnoreCase("CommonShape.PolygonShape"))
            	{
            		drawPolygon(g2,obj,i);
            	}
            	
            	else if(obj.getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.ArrowShape"))
            	{
            		drawArrow(g2,obj,i);
            	}
            	
            	else if(obj.getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.SpringShape"))
            	{
            		drawSpring(g2,obj,i);
            	}
            	
            	else if(obj.getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.RopeShape"))
            	{
            		drawRope(g2,obj,i);
            	}
            	
            	else if(obj.getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.JointShape"))
            	{
            		drawJoint(g2,obj,i);
            	}
            	
            	else if(obj.getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.FixJointShape"))
            	{
            		drawFixJoint(g2,obj,i); 
            	}
            }
            
            // draw interaction user
            drawLineDrawing(g2);
            drawLineContainer(g2);
            drawSelectLine(g2);
            drawGroupPolygon(g2);
            drawLineVertex(g2);
            drawShapeDescription(g2);
            
            // draw temp shape
            drawTempShape(g2);	
            // Draw recycle bin
            drawRecycleBin(g2);
        }
        
        else
        {
            // Draw Menu Icon
            drawMenuIcon(g2);
        }
        
        drawState(g2);
        drawShortCut(g2);
        drawEdit(g2);
	}
	
	private void drawShortCut(Graphics2D g2)
	{
		if(iState == state.Drawing 
		&& (mouse.getmouseLeft()  != 1 && mouse.getmouseRight() != 1) || (mouse.getmouseDrag() == 0))
		{
			for(int i = 0; i<ToolShortcut.length;i++)
		    {
		    	   if(ToolShortcut[i].getState() )
		           {
		    		   g2.drawImage(this.ToolShortcut[i].getImg(), this.ToolShortcut[i].getX(),this.ToolShortcut[i].getY(), null);
		           }
		    	   else
		    	   {
		    		  g2.drawImage(this.ToolSelect[i].getImg(), this.ToolSelect[i].getX() ,this.ToolSelect[i].getY(), null);
		           }
		   }	
		}
	}
	
	private void drawEdit(Graphics2D g2)
	{
		for(int i = 0; i<this.EditSc.length;i++)
	       {
			
			 if(EditSc[i].getState() )
	           {
	           g2.drawImage(this.EditSc[i].getImg(), this.EditSc[i].getX(),this.EditSc[i].getY(), null);
	           }
	    	   else
	    	   {
	    		  g2.drawImage(this.EditSL[i].getImg(), this.EditSL[i].getX() ,this.EditSL[i].getY(), null);
	           }
	       }
	}

	private void drawLineDrawing(Graphics2D g2)
	{
		if(this.mouse.getautoTool().equalsIgnoreCase("Rope"))
		{
			g2.setColor(Color.GREEN);
		}
		
		else if(this.mouse.getautoTool().equalsIgnoreCase("Joint"))
		{
			g2.setColor(Color.GREEN);
		}
		
		else if(this.mouse.getautoTool().equalsIgnoreCase("FixJoint"))
		{
			g2.setColor(Color.GREEN);
		}
		
		else if(this.mouse.getautoTool().equalsIgnoreCase("Spring"))
		{
			g2.setColor(Color.GREEN);
		}
		
		else if(this.mouse.getautoTool().equalsIgnoreCase("Comment"))
		{
			g2.setColor(Color.RED);
			g2.setStroke(new BasicStroke(3.0f)); 
			
			
			for(int i =0;i<mouse.getAutoGenPoint().size()-1;i++)
			{
				g2.drawLine((int)mouse.getAutoGenPoint().get(i).getX(),(int)mouse.getAutoGenPoint().get(i).getY(),(int)mouse.getAutoGenPoint().get(i+1).getX(),(int)mouse.getAutoGenPoint().get(i+1).getY());
			}
			
			for(int i =0;i<this.commentContainer.size();i++)
			{
				g2.drawLine((int)this.commentContainer.get(i).getX1(),(int)this.commentContainer.get(i).getY1(),(int)this.commentContainer.get(i).getX2(),(int)this.commentContainer.get(i).getY2());
			}
			
			g2.setStroke(new BasicStroke(1.0f)); 
			g2.setColor(this.penColor);
		}
		
		else if(this.mouse.getautoTool().equalsIgnoreCase("Rubber"))
		{
			g2.setColor(Color.RED);
			g2.setStroke(new BasicStroke(3.0f)); 
			
			for(int i =0;i<mouse.getAutoGenPoint().size()-1;i++)
			{
				g2.drawLine((int)mouse.getAutoGenPoint().get(i).getX(),(int)mouse.getAutoGenPoint().get(i).getY(),(int)mouse.getAutoGenPoint().get(i+1).getX(),(int)mouse.getAutoGenPoint().get(i+1).getY());
			}
			
			for(int i =0;i<this.commentContainer.size();i++)
			{
				g2.drawLine((int)this.commentContainer.get(i).getX1(),(int)this.commentContainer.get(i).getY1(),(int)this.commentContainer.get(i).getX2(),(int)this.commentContainer.get(i).getY2());
			}
			
			g2.setStroke(new BasicStroke(1.0f));
			g2.setColor(Color.GREEN);
			g2.drawPolygon(rubber);
			
			g2.setColor(this.penColor);
		}
		
        for(int i = 0;i<lineDrawing.size()-1;i++)
        {
        	g2.drawLine((int)lineDrawing.get(i).getX(),(int)lineDrawing.get(i).getY(),(int)lineDrawing.get(i+1).getX(),(int)lineDrawing.get(i+1).getY());        		
        }
        g2.setColor(this.penColor);
	}
	
	private void drawLineContainer(Graphics2D g2)
	{
        for(int i =0;i<this.lineContainer.size();i++)
        {
        	if(mouse.getGroupLineSelectIndex().contains(i))
        	{
        		g2.setColor(this.gSelectColor);
        	}
        	ArrayList<Line2D> line = this.lineContainer.get(i);
        	for(int j=0;j<line.size();j++)
        	{
        		if(mouse.getIndividualLineSelectIndex().getI() == i
        		&& mouse.getIndividualLineSelectIndex().getJ() == j)
            	{
            		g2.setColor(this.iselectColor);
            	}
        		
        		for(int k=0;k<mouse.getGroupLineSelectIndex().size();k++)
        		{
               		if(mouse.getGroupLineSelectIndex().get(k).getI() == i
                    && mouse.getGroupLineSelectIndex().get(k).getJ() == j)
                    {
                        g2.setColor(this.gSelectColor);
                    }
        		}
        		
        		g2.drawLine((int)line.get(j).getX1(),(int)line.get(j).getY1(),(int)line.get(j).getX2(),(int)line.get(j).getY2());
        		g2.setColor(this.penColor);
        	}
        	g2.setColor(this.penColor);
        }
	}
	
	private void drawSelectLine(Graphics2D g2)
	{
		g2.setColor(Color.RED);
        for(int i = 0;i<this.mouse.getSelectDrawing().size()-1;i++)
        {
        	g2.drawLine((int)this.mouse.getSelectDrawing().get(i).getX(),(int)this.mouse.getSelectDrawing().get(i).getY(),(int)this.mouse.getSelectDrawing().get(i+1).getX(),(int)this.mouse.getSelectDrawing().get(i+1).getY());	
        }
        g2.setColor(this.penColor);
	}
	
	private void drawGroupPolygon(Graphics2D g2)
	{
		g2.setColor(this.gSelectColor);
		g2.drawPolygon(this.mouse.getGroupSelectPolygon());
		g2.setColor(this.penColor);
	}
	
	private void drawLineVertex(Graphics2D g2)
	{
		g2.setColor(Color.GREEN);
		g2.draw(this.lineVertex);
		g2.setColor(this.penColor);
	}
	
	private void drawLineMagnitude(Graphics2D g2,Polygon shape)
	{
		g2.setColor(Color.RED);
		ArrayList<LocationOfString> locationOfString = ShapeProperty.findDrawPointMagnitude(shape);
		
		for(int k = 0; k < locationOfString.size(); k++)
		{
			g2.drawString(locationOfString.get(k).getValue(), 
					     (int)locationOfString.get(k).getX(),
		                 (int)locationOfString.get(k).getY());
		}
		g2.setColor(Color.BLUE);
	}
	
	private void drawEllipseMagnitude(Graphics2D g2,EllipseShape shape)
	{
		g2.setColor(Color.RED);
		// Draw Magnitude of Ellipse
		g2.drawString(""+(int)shape.getDiameter()/2,(int)shape.getCM().getX(),(int)shape.getCM().getY());
		g2.setColor(Color.BLUE);
	}
	
	private void drawTempShape(Graphics2D g2)
	{
		g2.setColor(Color.GREEN);
		
		for(int i=0;i<mouse.getTempShape().size();i++)
		{	
			if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("java.awt.geom.GeneralPath"))
			{
				Shape shape = (Shape) mouse.getTempShape().get(i);
				g2.draw(shape);
			}
			
			else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("java.awt.geom.Path2D.Double"))
			{
				Shape shape = (Shape) mouse.getTempShape().get(i);
				g2.draw(shape);
			}
			
			else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("java.awt.Polygon"))
			{
				java.awt.Polygon shape = (java.awt.Polygon) mouse.getTempShape().get(i);
				g2.draw(shape);
			}
			
			
			else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.TriangleShape"))
        	{
				TriangleShape shape = (TriangleShape) mouse.getTempShape().get(i);
				g2.draw(shape.getPolygon());
        	}
        	
        	else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.RectangleShape"))
        	{
        		RectangleShape shape = (RectangleShape) mouse.getTempShape().get(i);
				g2.draw(shape.getPolygon());
        	}
        	
        	else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.EllipseShape"))
        	{
        		EllipseShape shape = (EllipseShape) mouse.getTempShape().get(i);
				g2.draw(shape.getEllipse());
        	}
			
        	else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("CommonShape.PolygonShape"))
        	{
        		PolygonShape shape = (PolygonShape) mouse.getTempShape().get(i);
        		g2.draw(shape.getPolygon());
        	}
        	
        	else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.ArrowShape"))
        	{
        		ArrowShape shape = (ArrowShape) mouse.getTempShape().get(i);
        		for(int j = 0;j<shape.getBondingBox().size();j++)
        		{
        			g2.draw(shape.getBondingBox().get(j));
        		}
        	}
			
        	else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.CrossShape"))
        	{
        		CrossShape shape = (CrossShape) mouse.getTempShape().get(i);
    			ArrayList<Line2D> cross = shape.getShape();
    			for(int j = 0 ; j< cross.size();j++)
    			{
    				g2.drawLine((int)cross.get(j).getX1(),(int)cross.get(j).getY1(),(int)cross.get(j).getX2(),(int)cross.get(j).getY2());
    			}
        	}
        	
        	else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.SpringShape"))
        	{
        		SpringShape shape = (SpringShape) mouse.getTempShape().get(i);
    			ArrayList<Line2D> spring = shape.getShape();
    			for(int j = 0 ; j< spring.size();j++)
    			{
    				g2.drawLine((int)spring.get(j).getX1(),(int)spring.get(j).getY1(),(int)spring.get(j).getX2(),(int)spring.get(j).getY2());
    			}
        		
        	}
			
        	else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("TempObject.TempSpringShape"))
        	{
        		TempSpringShape shape = (TempSpringShape) mouse.getTempShape().get(i);
    			ArrayList<Line2D> spring = shape.getShape();
    			for(int j = 0 ; j< spring.size();j++)
    			{
    				g2.drawLine((int)spring.get(j).getX1(),(int)spring.get(j).getY1(),(int)spring.get(j).getX2(),(int)spring.get(j).getY2());
    			}
        		
        	}
        	else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.RopeShape"))
        	{
        		RopeShape shape = (RopeShape)mouse.getTempShape().get(i);
        		for(int j = 0;j<shape.getShape().size();j++)
        		{
        			ArrayList<Line2D> rope = shape.getShape();
        			g2.drawLine((int)rope.get(j).getX1(),(int)rope.get(j).getY1(),(int)rope.get(j).getX2(),(int)rope.get(j).getY2());
        		}
        	}
        	
        	else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.JointShape"))
        	{
        		JointShape shape = (JointShape)mouse.getTempShape().get(i);
        		g2.drawLine((int)shape.getStartIndex().getCM().getX(),(int)shape.getStartIndex().getCM().getY(),(int)shape.getRotatePoint().getX(),(int)shape.getRotatePoint().getY());
        		g2.drawLine((int)shape.getRotatePoint().getX(),(int)shape.getRotatePoint().getY(),(int)shape.getEndIndex().getCM().getX(),(int)shape.getEndIndex().getCM().getY());
        	}
			
        	else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("SpecialShape.FixJointShape"))
        	{
        		FixJointShape shape = (FixJointShape)mouse.getTempShape().get(i);
        		g2.drawLine((int)shape.getStartIndex().getCM().getX(),(int)shape.getStartIndex().getCM().getY(),(int)shape.getEndIndex().getCM().getX(),(int)shape.getEndIndex().getCM().getY());
        	}
			
        	else if(mouse.getautoTool().equalsIgnoreCase("Arrow"))
        	{
        		ArrayList<Line2D> axis = (ArrayList<Line2D>)mouse.getTempShape().get(i);
    			for(int j = 0 ; j< axis.size();j++)
    			{
    				g2.drawLine((int)axis.get(j).getX1(),(int)axis.get(j).getY1(),(int)axis.get(j).getX2(),(int)axis.get(j).getY2());
    			}
    			
    			if(mouse.getAutoGenPoint().size() == 2)
    			{
    				g2.drawLine((int)mouse.getAutoGenPoint().get(0).getX(),(int)mouse.getAutoGenPoint().get(0).getY(),(int)mouse.getAutoGenPoint().get(1).getX(),(int)mouse.getAutoGenPoint().get(1).getY());	    				
    			}
        	}
			
        	else if(mouse.getTempShape().get(i).getClass().getCanonicalName().equalsIgnoreCase("TempObject.TempFixJointShape"))
        	{
        		TempFixJointShape shape = (TempFixJointShape)mouse.getTempShape().get(i);
        		g2.drawLine((int)shape.getFixJoint().getX1(),(int)shape.getFixJoint().getY1(),(int)shape.getFixJoint().getX2(),(int)shape.getFixJoint().getY2());
        	}
		}
		g2.setColor(this.penColor);
	}
	
	private void drawShapeDescription(Graphics2D g2)
	{
		if(this.shapeDescription != null && this.descriptionPosition != null && this.show == true)
		{
			g2.setColor(Color.ORANGE);
			g2.drawString(this.shapeDescription.getCommonShapeName(),(int)descriptionPosition.getX(),(int)descriptionPosition.getY()+30);
			g2.setColor(this.penColor);
		}
	}
	
	private void drawRecycleBin(Graphics2D g2)
	{
		if((this.mouse.getIndividualLineSelectIndex().getI()!= -1 && this.mouse.getIndividualLineSelectIndex().getJ() != -1)
		|| this.mouse.getGroupSelectIndex().size() > 0
		|| this.mouse.getIndividualSelectIndex() != -1
		|| this.mouse.getGroupLineSelectIndex().size() > 0)
		{
			g2.drawImage(this.bin.getImg(),this.bin.getX(),this.bin.getY(),this);
		}
	}
	

	private void drawState(Graphics2D g22) 
	{
	       if(this.mouse.getiToolState() == this.mouse.getiToolState().Pen)
	       {
	    	   g2.drawImage(ImgShow[5],this.getWidth()-100,20,null);
	       }
	       else if(this.mouse.getiToolState() == this.mouse.getiToolState().Arrow)
	       {
	    	   g2.drawImage(ImgShow[0],this.getWidth()-100,20,null);
	       }
	       else if(this.mouse.getiToolState() == this.mouse.getiToolState().Rope)
	       {
	    	   g2.drawImage(ImgShow[6],this.getWidth()-100,20,null);
	       }
	       else if(this.mouse.getiToolState() == this.mouse.getiToolState().BasicJoint)
	       {
	    	   g2.drawImage(ImgShow[2],this.getWidth()-100,20,null);
	       }
	       else if(this.mouse.getiToolState() == this.mouse.getiToolState().FixJoint)
	       {
	    	   g2.drawImage(ImgShow[3],this.getWidth()-100,20,null);
	       }
	       else if(this.mouse.getiToolState() == this.mouse.getiToolState().Spring)
	       {
	    	   g2.drawImage(ImgShow[4],this.getWidth()-100,20,null);
	       }
	       else if(this.mouse.getiToolState() == this.mouse.getiToolState().Rectangle)
	       {
	    	   g2.drawImage(ImgShow[8],this.getWidth()-100,20,null);
	       }
	       else if(this.mouse.getiToolState() == this.mouse.getiToolState().Triangle)
	       {
	    	   g2.drawImage(ImgShow[7],this.getWidth()-100,20,null);
	       }
	       else if(this.mouse.getiToolState() == this.mouse.getiToolState().Circle)
	       {
	    	   g2.drawImage(ImgShow[10],this.getWidth()-100,20,null);
	       }
	       else if(this.mouse.getiToolState() == this.mouse.getiToolState().Car)
	       {
	    	   g2.drawImage(ImgShow[9],this.getWidth()-100,20,null);
	       }
	       else if(this.mouse.getiToolState() == this.mouse.getiToolState().Balloon)
	       {
	    	   g2.drawImage(ImgShow[1],this.getWidth()-100,20,null);
	       }
	       else if(this.mouse.getiToolState() == this.mouse.getiToolState().Comment)
	       {
	    	   g2.drawImage(ImgShow[12],this.getWidth()-100,20,null);
	       }
	       else if(this.mouse.getiToolState() == this.mouse.getiToolState().Rubber)
	       {
	    	   g2.drawImage(ImgShow[13],this.getWidth()-100,20,null);
	       }
	       
	       g2.drawImage(ImgShow[11],this.getWidth()-110,90,null);
	}
	
	private void drawMenuIcon(Graphics2D g2) 
	{
		if(iState == state.Main)
	    {
			g2.drawImage(MenuBg, this.getWidth()/2-300, this.getHeight()/2-250, null);   
	       for(int i = 0; i<MainMenuSmall.length;i++)
	       {
	    	   if(MainMenuSmall[i].getState() )
	           {
	    		   g2.drawImage(MainMenuSmall[i].getImg(), MainMenuSmall[i].getX(), MainMenuSmall[i].getY(),MainMenuSmall[i].getImg().getWidth(null),MainMenuSmall[i].getImg().getHeight(null),  null);
	           }
	           else
	           {
	        	   g2.drawImage(MainMenuBig[i].getImg(),  MainMenuBig[i].getX()-20,  MainMenuSmall[i].getY()-20,MainMenuBig[i].getImg().getWidth(null),MainMenuBig[i].getImg().getWidth(null),  null);   
	           }
	       }
	    }
		
	    else if(iState == state.File)
	    {
	    	g2.drawImage(FileBg, this.getWidth()/2-300, this.getHeight()/2-250, null);   
	    	for(int i = 0; i<FileMenuSmall.length;i++)
	        {
	        	 
	    		if(FileMenuSmall[i].getState() )   
	    		{
	    			g2.drawImage(FileMenuSmall[i].getImg(), FileMenuSmall[i].getX(), FileMenuSmall[i].getY(),FileMenuSmall[i].getImg().getWidth(null),FileMenuSmall[i].getImg().getHeight(null),  null);
	            }
	            else
	            {
	               g2.drawImage(FileMenuBig[i].getImg(),  FileMenuBig[i].getX()-20,  FileMenuBig[i].getY()-20,FileMenuBig[i].getImg().getWidth(null),FileMenuBig[i].getImg().getHeight(null),  null);   
	            }   	    
	         }
	     }
		
	     else if(iState == state.AutoShape)
	     {
	    	 g2.drawImage(AutoSBg, this.getWidth()/2-250, this.getHeight()/2-250, null);
	    	 for(int i = 0; i<AutoShapeSmall.length;i++)
	         {
	        	 if(AutoShapeSmall[i].getState() )
	             {	    
	        		  g2.drawImage(AutoShapeSmall[i].getImg(), AutoShapeSmall[i].getX(), AutoShapeSmall[i].getY(),AutoShapeSmall[i].getImg().getWidth(null),AutoShapeSmall[i].getImg().getHeight(null),  null);
	             }
	             else
	             {
	            	  g2.drawImage(AutoShapeBig[i].getImg(),  AutoShapeBig[i].getX()-20,  AutoShapeBig[i].getY()-20,AutoShapeBig[i].getImg().getWidth(null),AutoShapeBig[i].getImg().getHeight(null),  null);   
	              }   	 
	         }	 
	     }
	       
	     else if(iState == state.DrawMode) 
	     {
	    	 g2.drawImage(DrawTBg, this.getWidth()/2-300, this.getHeight()/2-250, null);
	    	 for(int i = 0; i<DrawToolSmall.length;i++)
	    	 {
	    		 if(DrawToolSmall[i].getState() )
	    		 {
	    			 g2.drawImage(DrawToolSmall[i].getImg(), DrawToolSmall[i].getX(), DrawToolSmall[i].getY(),DrawToolSmall[i].getImg().getWidth(null),DrawToolSmall[i].getImg().getHeight(null),  null);
	    		 }
	              
	    		 else
	    		 {
	    			 g2.drawImage(DrawToolBig[i].getImg(),  DrawToolBig[i].getX()-20,  DrawToolBig[i].getY()-20,DrawToolBig[i].getImg().getWidth(null),DrawToolBig[i].getImg().getHeight(null),  null);   
	    		 }
	    	 }
	     }
	}
	
	private void drawTriangle(Graphics2D g2,Object obj,int index)
	{
		TriangleShape shape = (TriangleShape) obj;
		
		// 1. draw Individual select
		if(mouse.getIndividualSelectIndex() == index)
		{
			g2.setColor(this.iselectColor);
		}
		
		// 2. check group select
		else if(mouse.getGroupSelectIndex().contains(index) == true)
		{
			g2.setColor(this.gSelectColor);
		}
		
		// 3. check objecct has been touch.
		if(mouse.getTouchShape().contains(index) == true)
		{
			g2.setColor(Color.RED);
		}
		
		// 4. draw Triangle
			if(shape.getImage() != null)
			{
				g2.drawImage(shape.getBufferImage(),shape.getPolygon().getBoundingBox().x,shape.getPolygon().getBoundingBox().y,null);  
				// Draw Border
				g2.drawPolygon(shape.getPolygon());	
			}
			else
			{
				//  Draw Inside Color
				Color temp = g2.getColor();
				g2.setColor(shape.getColor());
				g2.fillPolygon(shape.getPolygon());
				g2.setColor(temp);
				// Draw Border
				g2.drawPolygon(shape.getPolygon());	
			}
		
		// 5. draw CM
		Point2D CM = shape.getCM();
		g2.drawLine((int)CM.getX(),(int)CM.getY(),(int)CM.getX(),(int)CM.getY());
		
		// 6. draw cross
		if(shape.isFixed() == true && shape.isBackground() == false)
		{
			Color color = shape.getColor();
			if(shape.getColor() == Color.WHITE || 
					(shape.getColor().getRed() == 255 &&
					 shape.getColor().getGreen()== 255&&
					 shape.getColor().getBlue() == 255)		
					)
			{
				g2.setColor(Color.RED);
			}
			
			else
			{
				int r = Math.abs(255-color.getRed());
				int g = Math.abs(255-color.getGreen());
				int b = Math.abs(255-color.getBlue());
				g2.setColor(new Color(r,g,b));	
			}
			
			ArrayList<Line2D> cross = shape.getLineCross();
			for(int j = 0 ; j< cross.size();j++)
			{
				g2.drawLine((int)cross.get(j).getX1(),(int)cross.get(j).getY1(),(int)cross.get(j).getX2(),(int)cross.get(j).getY2());
			}
		}
		
		// 7. draw Vertex
		if(mouse.getIndividualSelectIndex() == index && shape.isNormalShape() == true)
		{
			g2.setColor(Color.GREEN);
			//1.1 draw vertex
			ArrayList<Ellipse2D> vertex = shape.getPolygonVertex();
			for(int i =0;i<vertex.size();i++)
			{
				g2.draw(vertex.get(i));
			}
			
			//1.2 draw rotation Point
			g2.draw(shape.getRotateShape());
			
			//1.3 draw scaleing point
			g2.draw(shape.getScaleShape());
			g2.setColor(Color.BLUE);
			
			//1.4 draw magnitude of line
			drawLineMagnitude(g2,shape.getPolygon());
		}
		
		g2.setColor(this.penColor);
	}
	
	private void drawRectangle(Graphics2D g2,Object obj,int index)
	{
		RectangleShape shape = (RectangleShape) obj;
		
		// 1. draw Individual select
		if(mouse.getIndividualSelectIndex() == index)
		{
			g2.setColor(this.iselectColor);
		}
		
		// 2. check group select
		else if(mouse.getGroupSelectIndex().contains(index) == true)
		{
			g2.setColor(this.gSelectColor);
		}
		
		// 3. check objecct has been touch.
		if(mouse.getTouchShape().contains(index) == true)
		{
			g2.setColor(Color.RED);
		}
		
		// 4. draw Rectangle
			if(shape.getImage() != null)
			{
				g2.drawImage(shape.getBufferImage(),shape.getPolygon().getBoundingBox().x,shape.getPolygon().getBoundingBox().y,null);  
				// Draw Border
				g2.drawPolygon(shape.getPolygon());	
			}
			else
			{
				//  Draw Inside Color
				Color temp = g2.getColor();
				g2.setColor(shape.getColor());
				g2.fillPolygon(shape.getPolygon());
				g2.setColor(temp);
				// Draw Border
				g2.drawPolygon(shape.getPolygon());	
			}
		
		// 5. draw CM
		Point2D CM = shape.getCM();
		g2.drawLine((int)CM.getX(),(int)CM.getY(),(int)CM.getX(),(int)CM.getY());
		
		// 6. draw cross
		if(shape.isFixed() == true && shape.isBackground() == false)
		{
			Color color = shape.getColor();
			if(shape.getColor() == Color.WHITE || 
			(shape.getColor().getRed() == 255 &&
			 shape.getColor().getGreen()== 255&&
			 shape.getColor().getBlue() == 255)		
			)
			{
				g2.setColor(Color.RED);
			}
			
			else
			{
				int r = Math.abs(255-color.getRed());
				int g = Math.abs(255-color.getGreen());
				int b = Math.abs(255-color.getBlue());
				g2.setColor(new Color(r,g,b));	
			}
			
			ArrayList<Line2D> cross = shape.getLineCross();
			for(int j = 0 ; j< cross.size();j++)
			{
				g2.drawLine((int)cross.get(j).getX1(),(int)cross.get(j).getY1(),(int)cross.get(j).getX2(),(int)cross.get(j).getY2());
			}
		}
		
		// 7. draw Vertex
		if(mouse.getIndividualSelectIndex() == index && shape.isNormalShape() == true)
		{
			g2.setColor(Color.GREEN);
			//1.1 draw vertex
			ArrayList<Ellipse2D> vertex = shape.getPolygonVertex();
			for(int i =0;i<vertex.size();i++)
			{
				g2.draw(vertex.get(i));
			}
			
			//1.2 draw rotation Point
			g2.draw(shape.getRotateShape());
			
			//1.3 draw scaleing point
			g2.draw(shape.getScaleShape());
			g2.setColor(Color.BLUE);
			
			//1.4 draw magnitude of line
			drawLineMagnitude(g2,shape.getPolygon());
		}
		
		g2.setColor(this.penColor);
	}
	
	private void drawEllipse(Graphics2D g2,Object obj,int index)
	{
		EllipseShape shape = (EllipseShape) obj;
		
		// 1. draw Individual select
		if(mouse.getIndividualSelectIndex() == index)
		{
			g2.setColor(this.iselectColor);
		}
		
		// 2. check group select
		else if(mouse.getGroupSelectIndex().contains(index) == true)
		{
			g2.setColor(this.gSelectColor);
		}
		
		// 3. check objecct has been touch.
		if(mouse.getTouchShape().contains(index) == true)
		{
			g2.setColor(Color.RED);
		}
		
		// 4. draw Ellipse
		
		if(shape.getImage() != null)
		{
			g2.drawImage(shape.getBufferImage(),shape.getEllipse().getBounds().x,shape.getEllipse().getBounds().y,null);  
			// Draw Border
			g2.drawPolygon(shape.getPolygon());
		}
		else
		{
			// Draw Inside Color
			Color temp = g2.getColor();
			g2.setColor(shape.getColor());
			g2.fill(shape.getEllipse());
			g2.setColor(temp);
		}
		
		// Draw Border
		g2.draw(shape.getEllipse());
		// Draw Magnitude of Ellipse
		g2.drawLine((int)shape.getCM().getX(),(int)shape.getCM().getY(),(int) ((int)shape.getCM().getX()+shape.getDiameter()/2),(int)shape.getCM().getY());
		
		// 5. draw CM
		Point2D CM = shape.getCM();
		g2.drawLine((int)CM.getX(),(int)CM.getY(),(int)CM.getX(),(int)CM.getY());
		
		// 6. draw cross
		if(shape.isFixed() == true && shape.isBackground() == false)
		{
			Color color = shape.getColor();
			
			if(shape.getColor() == Color.WHITE || 
					(shape.getColor().getRed() == 255 &&
					 shape.getColor().getGreen()== 255&&
					 shape.getColor().getBlue() == 255)		
					)
			{
				g2.setColor(Color.RED);
			}
			
			else
			{
				int r = Math.abs(255-color.getRed());
				int g = Math.abs(255-color.getGreen());
				int b = Math.abs(255-color.getBlue());
				g2.setColor(new Color(r,g,b));	
			}
			ArrayList<Line2D> cross = shape.getLineCross();
			for(int j = 0 ; j< cross.size();j++)
			{
				g2.drawLine((int)cross.get(j).getX1(),(int)cross.get(j).getY1(),(int)cross.get(j).getX2(),(int)cross.get(j).getY2());
			}
		}
		
		// 7. draw Vertex
		if(mouse.getIndividualSelectIndex() == index && shape.isNormalShape() == true)
		{
			g2.setColor(Color.GREEN);
			ArrayList<Ellipse2D> vertex = shape.getPolygonVertex();
			for(int i =0;i<vertex.size();i++)
			{
				g2.draw(vertex.get(i));
			}
			
			drawEllipseMagnitude(g2,shape);
		}
		
		g2.setColor(this.penColor);
	}

	private void drawPolygon(Graphics2D g2,Object obj,int index)
	{
		PolygonShape shape = (PolygonShape) obj;
		
		//  1. draw Individual select
		if(mouse.getIndividualSelectIndex() == index)
		{
			g2.setColor(this.iselectColor);
		}
		
		// 2. check group select
		else if(mouse.getGroupSelectIndex().contains(index) == true)
		{
			g2.setColor(this.gSelectColor);
		}
		
		// 3. check objecct has been touch.
		if(mouse.getTouchShape().contains(index) == true)
		{
			g2.setColor(Color.RED);
		}
		
		// 4. draw Polygon
		
			if(shape.getImage() != null)
			{
				g2.drawImage(shape.getBufferImage(),shape.getPolygon().getBoundingBox().x,shape.getPolygon().getBoundingBox().y,null);  
				// Draw Border
				g2.drawPolygon(shape.getPolygon());	
			}
			
			else
			{
				//Draw Inside Color
				Color temp = g2.getColor();
				g2.setColor(shape.getColor());
				g2.fillPolygon(shape.getPolygon());
				g2.setColor(temp);
				// Draw Border
				g2.drawPolygon(shape.getPolygon());
			}
			
		
		// 5. draw CM
		Point2D CM = shape.getCM();
		g2.drawLine((int)CM.getX(),(int)CM.getY(),(int)CM.getX(),(int)CM.getY());
		
		// 6. draw cross
		if(shape.isFixed() == true && shape.isBackground() == false)
		{
			Color color = shape.getColor();
			
			if(shape.getColor() == Color.WHITE || 
					(shape.getColor().getRed() == 255 &&
					 shape.getColor().getGreen()== 255&&
					 shape.getColor().getBlue() == 255)		
					)
			{
				g2.setColor(Color.RED);
			}
			
			else
			{
				int r = Math.abs(255-color.getRed());
				int g = Math.abs(255-color.getGreen());
				int b = Math.abs(255-color.getBlue());
				g2.setColor(new Color(r,g,b));	
			}	
			ArrayList<Line2D> cross = shape.getLineCross();
			for(int j = 0 ; j< cross.size();j++)
			{
				g2.drawLine((int)cross.get(j).getX1(),(int)cross.get(j).getY1(),(int)cross.get(j).getX2(),(int)cross.get(j).getY2());
			}
		}
		
		// 7. draw Vertex
		if(mouse.getIndividualSelectIndex() == index && shape.isNormalShape() == true)
		{
			g2.setColor(Color.GREEN);
			//1.1 draw vertex
			ArrayList<Ellipse2D> vertex = shape.getPolygonVertex();
			for(int i =0;i<vertex.size();i++)
			{
				g2.draw(vertex.get(i));
			}
			
			//1.2 draw rotation Point
			g2.draw(shape.getRotateShape());
			
			//1.3 draw scaleing point
			g2.draw(shape.getScaleShape());
			g2.setColor(Color.BLUE);
			
			//1.4 draw magnitude of line
			drawLineMagnitude(g2,shape.getPolygon());
		}
		
		g2.setColor(this.penColor);
	}
	
	/**
	 * Draw arrow
	 * @param g22
	 * @param obj
	 * @param index
	 */
	private void drawArrow(Graphics2D g22, Object obj, int index) 
	{
		ArrowShape shape = (ArrowShape) obj;
		
		//  1. draw Individual select
		if(mouse.getIndividualSelectIndex() == index)
		{
			g2.setColor(Color.BLUE);
			//1.1 draw rotation Point
			g2.draw(shape.getRotateShape());
			g2.setColor(Color.BLUE);
		}
		
		// 2. check group select
		else if(mouse.getGroupSelectIndex().contains(index) == true)
		{
			g2.setColor(Color.BLUE);
		}
		
		// 3. draw Arrow
		for(int i = 0;i<shape.getBondingBox().size();i++)
		{
			Color tempColor = g2.getColor();
			g2.setColor(Color.WHITE);
			g2.fillPolygon(shape.getBondingBox().get(i));
			g2.setColor(tempColor);
			g2.draw(shape.getBondingBox().get(i));
		}
		g2.setColor(this.penColor);
	}
	
	/**
	 * Draw Spring
	 * @param g22
	 * @param obj
	 * @param i
	 */
	private void drawSpring(Graphics2D g22, Object obj, int index) 
	{
		SpringShape shape = (SpringShape) obj;
		
		//  1. draw Individual select
		if(mouse.getIndividualSelectIndex() == index)
		{
			g2.setColor(Color.GREEN);
		}
		
		// 2. check group select
		else if(mouse.getGroupSelectIndex().contains(index) == true)
		{
			g2.setColor(Color.GREEN);
		}
		
		else
		{
			g2.setColor(Color.BLUE);
		}
		
		
		// 3. draw Line2D
		for(int i = 0;i<shape.getShape().size();i++)
		{
			ArrayList<Line2D> spring = shape.getShape();
			for(int j = 0 ; j< spring.size();j++)
			{
				g2.drawLine((int)spring.get(j).getX1(),(int)spring.get(j).getY1(),(int)spring.get(j).getX2(),(int)spring.get(j).getY2());
			}
		}
		
		// 4. draw Select point
		g2.draw(shape.getStartBound());
		g2.draw(shape.getEndBound());
		
		g2.setColor(this.penColor);
	}
	
	
	/**
	 * Draw Spring
	 * @param g22
	 * @param obj
	 * @param i
	 */
	private void drawRope(Graphics2D g22, Object obj, int index) 
	{
		RopeShape shape = (RopeShape) obj;
		
		g2.setColor(Color.BLUE);
		
		//  1. draw Individual select
		if(mouse.getIndividualSelectIndex() == index)
		{
			g2.setColor(Color.GREEN);
		}
		
		// 2. check group select
		else if(mouse.getGroupSelectIndex().contains(index) == true)
		{
			g2.setColor(Color.GREEN);
		}
		
		
		// 3. draw Line2D
		for(int i = 0;i<shape.getShape().size();i++)
		{
			ArrayList<Line2D> rope = shape.getShape();
			g2.drawLine((int)rope.get(i).getX1(),(int)rope.get(i).getY1(),(int)rope.get(i).getX2(),(int)rope.get(i).getY2());
		}
		
		
		// 4. draw Line2D connect to shape
		if(shape.getStartIndex() != null && this.shapeContainer.isContain(shape.getStartIndex()) == true)
		{
			Line2D start = shape.getShape().get(0);
			g2.drawLine((int)shape.getStartIndex().getCM().getX(),(int)shape.getStartIndex().getCM().getY(),(int)start.getX1(),(int)start.getY1());
		}
		
		if(shape.getEndIndex() != null && this.shapeContainer.isContain(shape.getEndIndex()) == true)
		{
			Line2D start = shape.getShape().get(shape.getShape().size()-1);
			g2.drawLine((int)shape.getEndIndex().getCM().getX(),(int)shape.getEndIndex().getCM().getY(),(int)start.getX1(),(int)start.getY1());
		}
		
		
		
		g2.setColor(this.penColor);
	} 
	
	/**
	 * Draw joint and rotate point of joint
	 * @param g22
	 * @param obj
	 * @param index
	 */
	private void drawJoint(Graphics2D g22, Object obj, int index) 
	{
		JointShape shape = (JointShape) obj;
		g2.setColor(Color.BLUE);
		
		//  1. draw Individual select
		if(mouse.getIndividualSelectIndex() == index)
		{
			g2.setColor(Color.GREEN);
		}
		
		// 2. check group select
		else if(mouse.getGroupSelectIndex().contains(index) == true)
		{
			g2.setColor(Color.GREEN);
		}
		
		// 3. draw Line2D
		g2.drawLine((int)shape.getStartIndex().getCM().getX(),(int)shape.getStartIndex().getCM().getY(),(int)shape.getRotatePoint().getX(),(int)shape.getRotatePoint().getY());
		g2.drawLine((int)shape.getRotatePoint().getX(),(int)shape.getRotatePoint().getY(),(int)shape.getEndIndex().getCM().getX(),(int)shape.getEndIndex().getCM().getY());
		
		// 4. draw rotate point
		g2.draw(shape.getRotateShape());
		g2.setColor(this.penColor);
	}
	
	/**
	 * Draw Fix joint and rotate point of joint
	 * @param g22
	 * @param obj
	 * @param index
	 */
	private void drawFixJoint(Graphics2D g22, Object obj, int index) 
	{
		FixJointShape shape = (FixJointShape) obj;
		g2.setColor(Color.BLUE);
		
		//  1. draw Individual select
		if(mouse.getIndividualSelectIndex() == index)
		{
			g2.setColor(Color.GREEN);
		}
		
		// 2. check group select
		else if(mouse.getGroupSelectIndex().contains(index) == true)
		{
			g2.setColor(Color.GREEN);
		}
		
		// 3. draw Line2D
		g2.drawLine((int)shape.getStartIndex().getCM().getX(),(int)shape.getStartIndex().getCM().getY(),(int)shape.getEndIndex().getCM().getX(),(int)shape.getEndIndex().getCM().getY());
		g2.setColor(this.penColor);
	}
	
	
	public void mousePressed(MouseEvent e) 
	{	
		if(vinFrame.isVisible() == true)
		{
			setIntFVIsible();
		}

		System.out.println("Mouse press "+e.getButton());
		if(e.getButton() == this.mouseMiddle && mouse.getmouseLeft() == 0 && mouse.getmouseRight() == 0)
		{
			if(iState != state.Drawing)
			{
				iState = state.Drawing;
			}
			else
			{
				iState = state.Main;
			}
		}
		
		else if(iState == state.Main)
	    {
			for(int i = 0; i<MainMenuSmall.length;i++)
			{
				if(MainMenuSmall[i].contains(e))
			 	{ 
				 	MainMenuBig[i].actionPerformed(this);
			 	}
			}
	    }
		
		else if(iState == state.File)
		{
			for(int i = 0; i<FileMenuSmall.length;i++)
		    {
				 if(FileMenuSmall[i].contains(e))
				 { 
					 FileMenuBig[i].actionPerformed(this);
				 }
		    }
		}
		else if(iState == state.DrawMode)
		{
			for(int i = 0; i<DrawToolSmall.length;i++)
		    {
				 if(DrawToolSmall[i].contains(e))
				 { 
					 DrawToolBig[i].actionPerformed(this);
				 }
		    }
		}
		
		else if(iState == state.AutoShape)
		{
			for(int i = 0; i<AutoShapeSmall.length;i++)
		    {
				 if(AutoShapeSmall[i].contains(e))
				 { 
					 AutoShapeBig[i].actionPerformed(this);	
				 }
		    }
		}
		
		else if(iState == state.Drawing)
		{
			// set non show description
			this.shapeDescription 	 = null;
			this.descriptionPosition = null;
			this.show                = false;
			
			// Change the tool by using tool shortcut
			for(int i = 0; i<this.ToolSelect.length;i++)
		    {
				 if(ToolSelect[i].contains(e))
				 { 
					 ToolSelect[i].actionPerformed(this);
					 this.repaint();
					 return;
				 }
		    }
			
			// Redo or Undo by using redo undo shortcut
			for(int i = 0;i<this.EditSL.length;i++)
			{
				if(EditSL[i].contains(e))
				{
					EditSL[i].actionPerformed(this);
					this.repaint();
					return;
				}
			}
			
			// mouse left press
			if(e.getButton() == this.mouseLeft)
			{
				mouse.mouseLeftPressed();
				
				if(mouse.getState().getStateName().equalsIgnoreCase("Drawing"))
				{
					DrawingState state = (DrawingState) mouse.getState();
					state.runState(e.getPoint(),this.lineVertex,this.lineDrawing,this.lineContainer);
				}
				
				else if(mouse.getState().getStateName().equalsIgnoreCase("Rubber"))
				{
					AutoRubberState state = (AutoRubberState) mouse.getState();
					state.runState(e.getPoint(),commentContainer,true,rubber);
				}
			}
			
			// mouse right press
			else if(e.getButton() == this.mouseRight)
			{
				mouse.mouseRightPressed();
				if(mouse.getState().getStateName().equalsIgnoreCase("IndividualSelect"))
				{
					IndividualSelectState state = (IndividualSelectState) mouse.getState();
					state.runState(e.getPoint(), this.shapeContainer, mouse,this.lineContainer);
				}
			}
			this.repaint();
		}
	}

	public void mouseReleased(MouseEvent e) 
	{
		if(iState == state.Drawing)
		{
			// set non show description
			// For set Shape Description
			CommonShape shape = CheckShapeContainer.getShapeContainPoint2D(this.shapeContainer,e.getPoint());
			// recount
			if(shape != null)
			{
				this.recount = true;
				this.shapeDescription    = shape;
				this.descriptionPosition = e.getPoint();
			}

			// mouse left release
			if(e.getButton() == this.mouseLeft)
			{
				mouse.mouseLeftRelease();
				if(mouse.getState().getStateName().equalsIgnoreCase("ReleaseLeft"))
				{
					ReleaseLeftState state = (ReleaseLeftState) mouse.getState();
					state.runState(this.shapeContainer,this.lineVertex,this.lineContainer,this.lineDrawing,this.redoTrunk,this.undoTrunk,this.currentMemento,this.mouse);
				}
				
				else if(mouse.getState().getStateName().equalsIgnoreCase("Rope"))
				{
					AutoRopeState state = (AutoRopeState) mouse.getState();
					state.runState(shapeContainer,lineContainer,lineDrawing,mouse,redoTrunk,undoTrunk,currentMemento);
				}
				
				else if(mouse.getState().getStateName().equalsIgnoreCase("Joint"))
				{
					AutoJointState state = (AutoJointState) mouse.getState();
					state.runState(shapeContainer,lineContainer,lineDrawing,mouse,redoTrunk,undoTrunk,currentMemento);
				}
				
				else if(mouse.getState().getStateName().equalsIgnoreCase("FixJoint"))
				{
					AutoFixJointState state = (AutoFixJointState) mouse.getState();
					state.runState(shapeContainer,lineContainer,lineDrawing,mouse,redoTrunk,undoTrunk,currentMemento);
				}
				
				else if(mouse.getState().getStateName().equalsIgnoreCase("Spring"))
				{
					AutoSpringState state = (AutoSpringState) mouse.getState();
					state.runState(shapeContainer,lineContainer,lineDrawing,mouse,redoTrunk,undoTrunk,currentMemento);
				}
				
				else if(mouse.getState().getStateName().equalsIgnoreCase("AutoGen"))
				{
					AutoGenState state = (AutoGenState) mouse.getState();
					state.runState(shapeContainer,lineContainer,commentContainer,mouse,redoTrunk,undoTrunk, currentMemento);
				}
			}
			
			// mouse right release
			else if(e.getButton() == this.mouseRight)
			{
				mouse.mouseRightRelease();
				if(mouse.getState().getStateName().equalsIgnoreCase("ReleaseRight"))
				{
					ReleaseRightState state = (ReleaseRightState) mouse.getState();
					state.runState(this.shapeContainer,this.mouse,this.lineContainer,this.bin,redoTrunk,undoTrunk,currentMemento);
				}
			}
		}
		
		this.repaint();
	}

	public void mouseDragged(MouseEvent e) 
	{
		if(iState  == state.Drawing)
		{
			// set non show description
			this.shapeDescription 	 = null;
			this.descriptionPosition = null;
			this.show                = false;
			
			mouse.mouseDragged();
			if(mouse.getState().getStateName().equalsIgnoreCase("Drawing"))
			{
				DrawingState state = (DrawingState) mouse.getState();
				state.runState(e.getPoint(),this.lineVertex,this.lineDrawing,this.lineContainer);
			}
			
			else if(mouse.getState().getStateName().equalsIgnoreCase("GroupDraw"))
			{
				GroupDrawingState state = (GroupDrawingState) mouse.getState();
				state.runState(e.getPoint(),mouse);
			}
			
			else if(mouse.getState().getStateName().equalsIgnoreCase("Transform"))
			{
				TransformState state = (TransformState) mouse.getState();
				state.runState(e.getPoint(),this.shapeContainer,mouse,this.lineContainer,this.bin);
				this.mouse.setStartDragPoint(e.getPoint());
			}
			
			else if(mouse.getState().getStateName().equalsIgnoreCase("Scale"))
			{
				ScaleState state = (ScaleState) mouse.getState();
				state.runState(e.getPoint(), shapeContainer, mouse);
			}
			
			else if(mouse.getState().getStateName().equalsIgnoreCase("Rotate"))
			{
				RotateState state = (RotateState) mouse.getState();
				state.runState(e.getPoint(), shapeContainer, mouse);
			}
			
			else if(mouse.getState().getStateName().equalsIgnoreCase("Zoom"))
			{
				ZoomState state = (ZoomState) mouse.getState();
				state.runState(e.getPoint(), shapeContainer, mouse);
			}
			
			else if(mouse.getState().getStateName().equalsIgnoreCase("Triangle"))
			{
				AutoTriangleState state = (AutoTriangleState) mouse.getState();
				state.runState(e.getPoint(),this.mouse);
			}
			
			else if(mouse.getState().getStateName().equalsIgnoreCase("Rectangle"))
			{
				AutoRectangleState state = (AutoRectangleState) mouse.getState();
				state.runState(e.getPoint(),this.mouse);
			}
			
			else if(mouse.getState().getStateName().equalsIgnoreCase("Arrow"))
			{
				AutoArrowState state = (AutoArrowState) mouse.getState();
				state.runState(e.getPoint(),this.mouse);
			}
			
			else if(mouse.getState().getStateName().equalsIgnoreCase("Ellipse"))
			{
				AutoEllipseState state = (AutoEllipseState) mouse.getState();
				state.runState(e.getPoint(),this.mouse);
			}
			
			else if(mouse.getState().getStateName().equalsIgnoreCase("Balloon"))
			{
				AutoBalloonState state = (AutoBalloonState) mouse.getState();
				state.runState(e.getPoint(),this.mouse);
			}
			
			else if(mouse.getState().getStateName().equalsIgnoreCase("Car"))
			{
				AutoCarState state = (AutoCarState) mouse.getState();
				state.runState(e.getPoint(),this.mouse);
			}
			
			else if(mouse.getState().getStateName().equalsIgnoreCase("Comment"))
			{
				AutoCommentState state = (AutoCommentState) mouse.getState();
				state.runState(e.getPoint(),this.mouse);
			}
			
			else if(mouse.getState().getStateName().equalsIgnoreCase("Rubber"))
			{
				AutoRubberState state = (AutoRubberState) mouse.getState();
				state.runState(e.getPoint(),commentContainer,true,rubber);
			}
			
			this.repaint();	
		}
	}

	public void mouseMoved(MouseEvent e) 
	{
		if(iState == state.Main)
	    {
			for(int i = 0; i<MainMenuSmall.length;i++)
			{
				 	if(MainMenuSmall[i].contains(e))
					{ 
				 		if(MainMenuSmall[i].getState())
				 		{
				 			MainMenuSmall[i].switchSize();
				 		}
					}
					else if(!MainMenuSmall[i].getState())
					{
						MainMenuSmall[i].switchSize();
					}
		     }
			repaint();
		}
		
		else if(iState == state.File)
		{
			 for(int i = 0; i<FileMenuSmall.length;i++)
		     {
				if(FileMenuSmall[i].contains(e))
				{ 
					if(FileMenuSmall[i].getState())
					{
						 FileMenuSmall[i].switchSize();
					}
						
				}
				else if(!FileMenuSmall[i].getState())
				{
					FileMenuSmall[i].switchSize();	
				}
		      }
			 repaint();
		}
		
		else if(iState == state.AutoShape)
		{
			 for(int i = 0; i<AutoShapeSmall.length;i++)
		     {
				 if(AutoShapeSmall[i].contains(e))
				 { 
					if(AutoShapeSmall[i].getState())
					{
						 AutoShapeSmall[i].switchSize();
					}	
				 }
				 else if(!AutoShapeSmall[i].getState())
				 {
					 AutoShapeSmall[i].switchSize();
						
				 } 
		      }
			 repaint();
		}
		
		else if(iState == state.DrawMode)
		{
			 for(int i = 0; i<DrawToolSmall.length;i++)
		     {
				 if(DrawToolSmall[i].contains(e))
				 { 
					 if(DrawToolSmall[i].getState())
					 {
						 DrawToolSmall[i].switchSize();	
					 }
						
				 }
				 
				 else if(!DrawToolSmall[i].getState())
				 {
					 DrawToolSmall[i].switchSize();
				 }
		      }
			 repaint();
		}
		
		else if(iState == state.Drawing)
		{
			// Change the picture of tool shortcut
			for(int i = 0; i<this.ToolShortcut.length;i++)
		    {
				 if(ToolShortcut[i].contains(e))
				 { 
					 if(ToolShortcut[i].getState())
					 {
						 ToolShortcut[i].switchSize();	
					 }
						
				 }
				 
				 else if(!ToolShortcut[i].getState())
				 {
					 ToolShortcut[i].switchSize();
				 }
		    }
			
			// Change the picture of redo undo shortcut
			for(int i = 0; i<this.EditSc.length;i++)
		    {
				 if(EditSc[i].contains(e))
				 { 
					 if(EditSc[i].getState())
					 {
						 EditSc[i].switchSize();	
					 }
						
				 }
				 
				 else if(!EditSc[i].getState())
				 {
					 EditSc[i].switchSize();
				 }
		      }
			this.repaint();
			
			
			// For set Shape Description
			CommonShape shape = CheckShapeContainer.getShapeContainPoint2D(this.shapeContainer,e.getPoint());

			// recount
			if(shape != null && this.shapeDescription == shape && this.show == false)
			{
				this.recount = true;
				this.shapeDescription    = shape;
				this.descriptionPosition = e.getPoint();
			}
			
			// show the same don't do anything
			else if(shape != null && this.shapeDescription == shape && this.show == true)
			{
				
			}
			
			// first time inside new shape
			else if(shape != null)
			{
				this.shapeDescription    = shape;
				this.descriptionPosition = e.getPoint();
				this.recount = true;
			}
			
			// move from in shape to out
			else if(this.shapeDescription != null && this.descriptionPosition != null 
				&&  shape == null)
			{
				this.shapeDescription = null;
				this.descriptionPosition = null;
				this.show = false;
				this.repaint();
			}
			
			// not in any shape
			else
			{
				this.shapeDescription    = null;
				this.descriptionPosition = null;
				this.show                = false;
			}
			
			
			
			
			if(mouse.getState().getStateName().equalsIgnoreCase("Rubber"))
			{
				AutoRubberState state = (AutoRubberState) mouse.getState();
				state.runState(e.getPoint(),commentContainer,false,rubber);
			}
		}
	}
	
	public void mouseClicked(MouseEvent e) 
	{
		
	}
	
	public void mouseEntered(MouseEvent e) 
	{
		// TODO Auto-generated method stub
	}
	
	public void mouseExited(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	// Thread for checking description
	public void run() 
	{
		while(true)
		{
			try 
			{
				for(int i =0;i<3;i++)
				{
					Thread.sleep(300);
					if(recount == true)
					{
						recount = false;
						break;
					}
					
					else if(this.shapeDescription != null 
					&& this.descriptionPosition != null
					&& i == 2)
					{
						this.show = true;	
						this.repaint();
					}	
				}
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}	
		}
	}
	
	/*
	 * Handle the event from the Properties button in the main menu it's invoke the Internal
	 * frame
	 */
	public void ShapeHandle(String Name,String val1,String val2,String val3,String val4)
	{	
		if(Name.equals("Main") )
		{
			this.ib = new PenBuilder();
			this.iD.setShapeBuilder(ib);
			iD.Construct(val1, val2, val3,val4);
		}
		
		else if(Name.equals("Triangle"))
		{
			this.ib = new TriBuilder();
			this.iD.setShapeBuilder(ib);
			iD.Construct(val1, val2, val3,val4);
		}
		else if(Name.equals("Rectangle"))
		{
			this.ib = new RecBuilder();
			this.iD.setShapeBuilder(ib);
			iD.Construct(val1, val2, val3,val4);
		}
		
		else if(Name.equals("Circle"))
		{
			this.ib = new CirBuilder();
			this.iD.setShapeBuilder(ib);
			iD.Construct(val1, val2, val3,val4);
		}
		
		else if(Name.equals("Polygon"))
		{
			this.ib = new PolygonBuilder();
			this.iD.setShapeBuilder(ib);
			iD.Construct(val1, val2, val3,val4);
		}
		
		else if(Name.equals("Spring"))
		{
			this.ib = new SpringBuilder();
			this.iD.setShapeBuilder(ib);
			iD.Construct(val1, val2, val3,val4);
		}
		else if(Name.equals("Arrow"))
		{
			this.ib = new ArrowBuilder();
			this.iD.setShapeBuilder(ib);
			iD.Construct(val1, val2, val3,val4);
		}

		this.InvokeInternal();

	}
	
	/**
	 * invoke the internalframe by getting the specify panel from director.
	 *
	 */
	private void InvokeInternal()
	{
		vinFrame.remove(this.CurPanel);
		CurPanel = iD.getShape();
		vinFrame.add(CurPanel);
		
		vinFrame.show();
		// Set location for make vinFrame repaint
		vinFrame.setLocation((int)vinFrame.getLocation().getX(),(int)vinFrame.getLocation().getY());
	}
	
	/**
	 * Set invisible or visible for InternalFrame
	 *
	 */
	public void setIntFVIsible()
	{
		if(vinFrame.isVisible())
		{
			this.vinFrame.setVisible(false);
		}
		else
		{
			this.vinFrame.setVisible(true);
		}
	}
	
	/**
	 * Set invisible or visible for TemplateFrame
	 *
	 */
	public void templateFrame()
	{
		if(this.cTemplate.isVisible())
		{
			this.cTemplate.setVisible(false);
		}
		else
		{
			this.cTemplate.setVisible(true);
		}
	}
	
	/**
	 * For set drawing color depend on Template
	 *
	 */
	private void setDrawColor()
	{
		if(Template.getTState().equals(Template.getTState().Default))
		{
			this.penColor 	 		 = Color.BLACK;
			this.iselectColor 		 = Color.BLUE;
			this.gSelectColor 	 	 = Color.RED;
		}
		
		else if(Template.getTState().equals(Template.getTState().Room))
		{
			this.penColor 	 		 = Color.YELLOW;
			this.iselectColor 		 = Color.BLUE;
			this.gSelectColor 	 	 = Color.RED;
		}
		
		else if(Template.getTState().equals(Template.getTState().Classroom))
		{
			this.penColor 	 		 = Color.BLACK;
			this.iselectColor 		 = Color.BLUE;
			this.gSelectColor 	 	 = Color.RED;
		}
		
		else if(Template.getTState().equals(Template.getTState().Space))
		{
			this.penColor 	 		 = Color.YELLOW;
			this.iselectColor 		 = Color.BLUE;
			this.gSelectColor 	 	 = Color.RED;
		}
		
		else if(Template.getTState().equals(Template.getTState().Snooker))
		{
			this.penColor 	 		 = Color.BLACK;
			this.iselectColor 		 = Color.YELLOW;
			this.gSelectColor 	 	 = Color.RED;
		}
		
	}
}
