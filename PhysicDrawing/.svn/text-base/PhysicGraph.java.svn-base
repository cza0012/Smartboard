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

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;

import net.phys2d.raw.Body;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * A simple demonstration application showing how to create a line chart using data from an
 * {@link XYDataset}.
 *
 */
public class PhysicGraph extends JFrame implements Runnable
{
	
	private static final long serialVersionUID = 1L;   
    
    private XYSeries distX;
    private XYSeries distY; // S - dataset
    
    private XYSeries velX;
    private XYSeries velY; // V - dataset
    
    private XYSeries accX;
    private XYSeries accY;// A - dataset
    
	/**
	 * freq to be update the graph. ....
	 * freq HIGH >> low plot
	 * freq LOW >> often plot
	 * always > 0
	 */
	private int freq = 2; // (4)
	private int count = 0;
	private long tStart = 0;
	private float xStart = 0;
	private float yStart = 0;
	
	//private float xBefore = 0;
	//private float yBefore = 0;
	
	private float vxBefore = 0;
	private float vyBefore = 0;

	private float lastSec = 0;
	
//	private float baseDT = 0.015f;	// 0.015
	
	private long tPause = 0;
	private long tRun = 0;
	
    private int maxPlotNumber = 40;
    
    public Body body; // Body that you want to showing the graph
    
    
    private boolean start = false; // Check the graph was the first run or not.
    
    
    public Thread thread; // for control thread of physic graph
    
    private int bodyIndex = -1; // For set body index
    
   
    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public PhysicGraph(String title) 
    {
        //super(title);
        
        XYDataset dsDisplacement = createDisplacementDataset();
        XYDataset dsVelocity = createVelocityDataset();
        XYDataset dsAcceleration = createAccelerationDataset();
        
        JFreeChart chartDisplacement = createDisplacementChart(dsDisplacement);
        JFreeChart chartVelocity = createVelocityChart(dsVelocity);
        JFreeChart chartAcceleration = createAccelerationChart(dsAcceleration);
                
        ChartPanel chartPanelDis = new ChartPanel(chartDisplacement);        
        ChartPanel chartPanelVel = new ChartPanel(chartVelocity);
        ChartPanel chartPanelAcc = new ChartPanel(chartAcceleration);
        
        chartPanelDis.setPreferredSize(new java.awt.Dimension(250, 250));
        chartPanelVel.setPreferredSize(new java.awt.Dimension(250, 250));
        chartPanelAcc.setPreferredSize(new java.awt.Dimension(250, 250));
      //  chartPanelAcc.
        this.setLayout(new GridLayout(1,3,3,3)); // (row , col , hGAP , vGAP)
        this.add(chartPanelDis);
        this.add(chartPanelVel);
        this.add(chartPanelAcc);
        
        thread = new Thread(this);
      
    }
    
    /**
     * Set the body that you want to show the graph
     * @param body
     */
    public void setBody(Body body)
    {
    	this.body = body;
    	String s = "X : "+this.body.getPosition().getX() + " , Y : " + this.body.getPosition().getY();
        this.setTitle("Graph Simulation ; Body references >> " + s);
        start = false;
    }
    
    /**
     * For set body index that was shown in graph
     * @param bodyIndex
     */
    public void setBodyIndex(int bodyIndex)
    {
    	this.bodyIndex = bodyIndex;
    }
    
    /**
     * Get body index that was show in graph.
     * @return
     */
    public int getBodyIndex()
    {
    	return this.bodyIndex;
    }
    
    /**
     * Get the body that was on showing the graph
     * @return
     */
    public Body getBody()
    {
    	return this.body;
    }

    /**
     * Creates a sample dataset.
     * 
     * @return a sample dataset.
     */
    private XYDataset createDisplacementDataset() 
    {
        
        distX = new XYSeries("Sx");
        distY = new XYSeries("Sy");       
               
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(distX);
        dataset.addSeries(distY);       
             
        return dataset;
    }
    
    /**
     * Creates a sample dataset.
     * 
     * @return a sample dataset.
     */
    private XYDataset createVelocityDataset() {
        
        velX = new XYSeries("Vx");
        velY = new XYSeries("Vy");      
               
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(velX);
        dataset.addSeries(velY);
                    
        return dataset;
        
    }
    
    private XYDataset createAccelerationDataset() {
        
	 	accX = new XYSeries("Ax");
        accY = new XYSeries("Ay");      
             
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(accX);
        dataset.addSeries(accY);       
             
        return dataset;
        
    }
    
    /**
     * Creates a chart.
     * 
     * @param dataset  the data for the chart.
     * 
     * @return a chart.
     */
    private JFreeChart createDisplacementChart(XYDataset dataset) {
        
        // create the chart...
         JFreeChart chart = ChartFactory.createXYLineChart(
            "Displacement",     // chart title
            "X - Time (s)",     // x axis label
            "Y - S (px)",         // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            true                      // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);
            
        // get a reference to the plot for further customisation...
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        // plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white); // vertical line 
        plot.setRangeGridlinePaint(Color.white);  // horizontal line 
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);  // (index , value)
        renderer.setSeriesLinesVisible(1, true);
       // renderer.setSeriesShapesVisible(1, true);
       // renderer.setSeriesFillPaint(2, Color.black);
        renderer.setSeriesPaint(0, Color.RED, true);
        renderer.setSeriesPaint(1, Color.black, true);
        renderer.setSeriesPaint(2, Color.white, true);
        renderer.setSeriesShape(0, new Rectangle(1,1));
        renderer.setSeriesShape(1, new Rectangle(1,1));
       
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.
                
        return chart;
        
    }

    /**
     * Creates a chart.
     * 
     * @param dataset  the data for the chart.
     * 
     * @return a chart.
     */
    private JFreeChart createVelocityChart(XYDataset dataset) {
        
        // create the chart...
    	JFreeChart chart = ChartFactory.createXYLineChart(
                "Velocity",                          // chart title
                "X - Time (s)",                      // x axis label
                "Y - V (px/s)",           // y axis label
                dataset,                                   // data
                PlotOrientation.VERTICAL,
                true,                                      // include legend
                true,                                      // tooltips
                false                                      // urls
            ); 

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);
            
        // get a reference to the plot for further customisation...
         XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        // plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white); // vertical line 
        plot.setRangeGridlinePaint(Color.white); // horizontal line 
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true); // (index , value)
        renderer.setSeriesLinesVisible(1, true);
        // renderer.setSeriesShapesVisible(1, true);
        // renderer.setSeriesFillPaint(2, Color.black);
        renderer.setSeriesPaint(0, Color.RED, true);
        renderer.setSeriesPaint(1, Color.black, true);
        renderer.setSeriesPaint(2, Color.white, true);
        renderer.setSeriesShape(0, new Rectangle(1,1));
        renderer.setSeriesShape(1, new Rectangle(1,1));
       
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.
                
        return chart;
        
    }
    
    private JFreeChart createAccelerationChart(XYDataset dataset) {
        
        // create the chart...
    	JFreeChart chart = ChartFactory.createXYLineChart(
                "Acceleration",                      // chart title
                "X - Time (s)",                      // x axis label
                "Y - A (px/s^2)",     // y axis label
                dataset,                                   // data
                PlotOrientation.VERTICAL,
                true,                                      // include legend
                true,                                      // tooltips
                false                                      // urls
            ); 

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);
            
        // get a reference to the plot for further customisation...
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        // plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white); // vertical line 
        plot.setRangeGridlinePaint(Color.white); // horizontal line 
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true); // (index , value)
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesPaint(0, Color.RED, true);
        renderer.setSeriesPaint(1, Color.black, true);
        renderer.setSeriesPaint(2, Color.white, true);
        renderer.setSeriesShape(0, new Rectangle(1,1));
        renderer.setSeriesShape(1, new Rectangle(1,1));
       
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.
                
        return chart;
        
    }
    
    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) 
    {
    	//PhysicGraph dd = new PhysicGraph("Test Graph component");
		///dd.initGUI();
		//dd.setLocation(0, 150);
    }
    
    public void initGUI()
    {
    	//this.pack();
    	//RefineryUtilities.centerFrameOnScreen(this);
    	this.setVisible(true);
    	thread.start();
    	thread.suspend();
    }    
   /*
    public void Start()
    {
    	thread.start();
    }*/
    
    public void pause()
    {
    //	this.freq = Integer.MAX_VALUE;    	
    	thread.suspend();
    	tPause = System.currentTimeMillis();
    	
    	
    }
    public void resume()
    {
    	//this.freq = 4;
    	tRun = System.currentTimeMillis();
    	/**
    	 * Set tStart when users have PAUSED the graph.. So the graph will not be shifted
    	 * 
    	 *  If it's called when at 1st time.... 
    	 *  	It will do this + tStart = System.currentTimeMillis
    	 *  If it's called when user PAUSE
    	 *      It will do this only .. 
    	 */
    	tStart = tStart + (tRun - tPause); 
    	thread.resume();
    	
    }
    
    public void updateDisplacementValue(float sx , float sy, float time) 
    {
    	if(distX.getItemCount() == this.maxPlotNumber)
    	{
    		distX.remove(0);
    	}
    	if(distY.getItemCount() == this.maxPlotNumber)
    	{
    		distY.remove(0);
    	}
    	distX.add(time,(double)sx);
    	distY.add(time,(double)sy);		    		
	}

    public void clearS()
    {
    	distX.clear();
    	distY.clear();
    	velX.clear();
    	velY.clear();
    	accX.clear();
    	accY.clear();
    	//start = false;
    	
    }
    
    public void updateVelocityValue(float vx , float vy, float time) 
    {		   	
    	if(velX.getItemCount() == this.maxPlotNumber)
    	{
    		velX.remove(0);
    	}
    	if(velY.getItemCount() == this.maxPlotNumber)
    	{
    		velY.remove(0);
    	}
    	velX.add(time,(double)vx);
    	velY.add(time,(double)vy);		
	}

	public void updateAccelerationValue(float ax, float ay, float time)
	{		
		if(accX.getItemCount() == this.maxPlotNumber)
    	{
			accX.remove(0);
    	}
    	if(accY.getItemCount() == this.maxPlotNumber)
    	{
    		accY.remove(0);
    	}
		accX.add(time,(double)ax);
		accY.add(time,(double)ay);
			
	}
	
	
	public float getSx()
	{
		float xUpdate = body.getPosition().getX() - xStart;
		return xUpdate;
	}
	
	public float getSy()
	{		
		float yUpdate =  yStart - body.getPosition().getY();
		return yUpdate;
	}
	
	/*
	 *
	
	public float getVx(float xUpdate, float xBefore, float dt)
	{
		if(dt == 0)
			dt = 3.7f * 0.33f;
		
		float ret = (xUpdate - xBefore) /(3.7f*dt);	
		return ret;
	}
	
	public float getVy(float yUpdate, float yBefore, float dt)
	{		
		if(dt == 0)
			dt = 3.7f * 0.33f;	
		
		float ret =  (yUpdate - yBefore) / (3.7f*dt);
		return ret;
	}
	 */
	public float getAx(float vxUpdate, float vxBefore, float dt)
	{
		if(dt == 0)
			dt = 0.35f;
		
		float ret = (vxUpdate - vxBefore) / (4.3f * dt); // 4.3
	//	System.out.println("diff vx "+vxUpdate +" " +vxBefore + " "+(vxUpdate - vxBefore));
		return ret;
	}
	
	public float getAy(float vyUpdate, float vyBefore, float dt)
	{
		if(dt == 0)
			dt = 0.35f;
		
		float ret = (vyUpdate - vyBefore) / (4.3f * dt); // 4.3
		return ret;
	}
	
	/**
	 * Run for get the value of the Object for displaying.
	 */
	public void run() 
	{
		while(true)
		{
			try 
			{	
			//	System.out.println("pg : "+this.isVisible());	
				if(this.isVisible() == false)
				{
					System.out.println("thread stop");	
					
					thread.stop();				
					thread.destroy();
					//this.dispose();			
					//this = null;
				}			
				
				Thread.sleep(160);			// 160 , 360	
				if(this.body != null)
				{
					// this block will be called when the graph if first initiated
					if(!start)
					{
						/**
						 * For the x,y displacement
						 */
						xStart   = body.getPosition().getX();
						yStart   = body.getPosition().getY();
						tStart   = System.currentTimeMillis();
						vxBefore = 0;
						vyBefore = 0;
						start    = true;
					}	
					
					if(count%freq == 0) // the frequency to update
					{
						float sec = (float)(System.currentTimeMillis() - tStart) / 1000;
						
						float xUpdate = getSx();
						float yUpdate =  getSy();					
						// * 1.0795
						float vxUpdate = body.getVelocity().getX()* 1.0795f;					
						float vyUpdate = body.getVelocity().getY()* 1.0795f;
					//	System.out.println("dt : "+(sec-lastSec));
					
						//float axUpdate = getAx(vxBefore,sec-lastSec);
						//float ayUpdate = getAy(vyBefore,sec-lastSec);
						//float axUpdate = (body.getVelocity().getX() - vxStart) / (baseDT * freq);				
						//float ayUpdate = (body.getVelocity().getY() - vyStart) / (baseDT * freq);
					//	System.out.println("cc "+(vyUpdate-vyBefore));
						float axUpdate = getAx(vxUpdate,vxBefore ,(sec-lastSec));
						float ayUpdate = getAy(vyUpdate,vyBefore ,(sec-lastSec));
											
						updateDisplacementValue(xUpdate,yUpdate,sec);						
						updateVelocityValue(vxUpdate, vyUpdate, sec);						
						updateAccelerationValue(axUpdate,ayUpdate,sec);							
			
						vxBefore = vxUpdate;
						vyBefore = vyUpdate;
						lastSec = sec;				
				
					}
					count++;
				}
			} 
			
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
}

/*
 * public void run() 
	{
		while(true)
		{
			try 
			{	
			//	System.out.println("pg : "+this.isVisible());	
				if(this.isVisible() == false)
				{
					System.out.println("thread stop");	
					
					thread.stop();				
					thread.destroy();
					//this.dispose();			
					//this = null;
				}			
				
				Thread.sleep(160);				
				if(this.body != null)
				{
					if(!start)
					{
						xStart   = body.getPosition().getX();
						yStart   = body.getPosition().getY();
						tStart   = System.currentTimeMillis();
						vxStart  = body.getVelocity().getX();
						vyStart  = body.getVelocity().getY();
						start    = true;
					}	
					
					if(count%freq == 0) // the frequency to update
					{
						float sec = (float)(System.currentTimeMillis() - tStart) / 1000;
						
						float xUpdate = body.getPosition().getX() - xStart;
						float yUpdate = yStart - body.getPosition().getY();		
						
						float axUpdate = (body.getVelocity().getX() - vxStart) / (baseDT * freq);				
						float ayUpdate = (body.getVelocity().getY() - vyStart) / (baseDT * freq);
					
						updateDisplacementValue(xUpdate,yUpdate,sec);
						updateVelocityValue(body.getVelocity().getX(), body.getVelocity().getY(), sec);
						updateAccelerationValue(axUpdate,ayUpdate,sec);	
							
						vxStart = body.getVelocity().getX();
						vyStart = body.getVelocity().getY();
						
						//this.repaint();
					}
					count++;
				}
			} 
			
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
 */
