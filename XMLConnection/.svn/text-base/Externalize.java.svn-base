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

import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;

import MainInterface.RunProgram;
import SpecialShape.ArrowShape;
import SpecialShape.FixJointShape;
import SpecialShape.JointShape;
import SpecialShape.RopeShape;
import SpecialShape.SpringShape;
import AlgorTools.ShapeConvert;
import CommonShape.*;
import Container.ContainerAllShape;

public class Externalize extends CommunicatorXML {
	// private ContainerAllShape containerNew = new ContainerAllShape();
	private ContainerAllShape container = new ContainerAllShape();
	private ArrayList<Line2D> shortNotes;

	public void writeSave(ContainerAllShape container, String fileName, ArrayList<Line2D> shortNotes) {
		this.container = container;
		this.shortNotes = shortNotes;
		try {
			OutputStream fout = new FileOutputStream(fileName);
			OutputStream bout = new BufferedOutputStream(fout);
			OutputStreamWriter out = new OutputStreamWriter(bout, "8859_1");
			out.write(header + '\n');

			out
					.write(template_s + Template.Template.tState + template_e
							+ '\n');
			System.out.print(RunProgram.runner.drawing.mouse
					.getBackgroundPath());
			out.write(bgImage_s
					+ RunProgram.runner.drawing.mouse.getBackgroundPath()
					+ bgImage_e + '\n');

			out.write(shape_s + '\n');

			out.write(quantity_s);
			out.write("" + container.getShapeContainerSize());
			out.write(quantity_e + '\n');

			for (int i = 0; i < container.getShapeContainerSize(); i++) {
				if (container.getShape(i).getClass().getCanonicalName().equals(
						"CommonShape.TriangleShape")) {
					this.writeTriangle(out, (TriangleShape) container
							.getShape(i));
					// containerNew.addShape(container.getShape(i));
				} else if (container.getShape(i).getClass().getCanonicalName()
						.equals("CommonShape.RectangleShape")) {
					this.writeRectangle(out, (RectangleShape) container
							.getShape(i));
					// containerNew.addShape(container.getShape(i));
				} else if (container.getShape(i).getClass().getCanonicalName()
						.equals("CommonShape.PolygonShape")) {
					this
							.writePolygon(out, (PolygonShape) container
									.getShape(i));
					// containerNew.addShape(container.getShape(i));
				} else if (container.getShape(i).getClass().getCanonicalName()
						.equals("CommonShape.EllipseShape")) {
					this
							.writeEllipse(out, (EllipseShape) container
									.getShape(i));
					// containerNew.addShape(container.getShape(i));
				} else if (container.getShape(i).getClass().getCanonicalName()
						.equals("SpecialShape.FixJointShape")) {
					this.writeFixJoint(out, (FixJointShape) container
							.getShape(i));
				} else if (container.getShape(i).getClass().getCanonicalName()
						.equals("SpecialShape.JointShape")) {
					this.writeJointShape(out, (JointShape) container
							.getShape(i));
				} else if (container.getShape(i).getClass().getCanonicalName()
						.equals("SpecialShape.SpringShape")) {
					this.writeSpringShape(out, (SpringShape) container
							.getShape(i));
				} else if (container.getShape(i).getClass().getCanonicalName()
						.equals("SpecialShape.RopeShape")) {
					this.writeRopeShape(out, (RopeShape) container.getShape(i));
				} else if (container.getShape(i).getClass().getCanonicalName()
						.equals("SpecialShape.ArrowShape")) {
					this.writeArrow(out, (ArrowShape) container.getShape(i));
				}

			}

			out.write(shape_e+'\n');
			writeShortNote(out);
			out.close();
		} catch (UnsupportedEncodingException e) {
			System.out
					.println("This VM does not support the Latin-1 character set.");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void writeArrow(OutputStreamWriter out, ArrowShape stuff) {
		try {
			out.write(arrow_s + '\n');
			ArrayList<Polygon> bBox = stuff.getBondingBox();
			// /Write Point2D///
			out.write(point2d_s + stuff.getPointIntersection().getX() + ","
					+ stuff.getPointIntersection().getY() + point2d_e + '\n');
			for (int k = 0; k < 3; k++) {
				int[] xP = bBox.get(k).xpoints;
				int[] yP = bBox.get(k).ypoints;
				for (int i = 0; i < 4; i++) {
					out.write(point2d_s + xP[i] + "," + yP[i] + point2d_e
							+ '\n');
				}
			}

			// System.out.println("Num points = "+points.size());
			out.write(g_s + stuff.getGravityForce() + "," + stuff.getDegree()
					+ g_e);
			out.write(arrow_e + '\n');
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeRopeShape(OutputStreamWriter out, RopeShape stuff) {
		try {
			out.write(rope_s + '\n');
			ArrayList<Line2D> lines = stuff.getShape();
			// System.out.println("Before >> "+lines.size());
			ArrayList<Point2D> points = ShapeConvert
					.convertRopeLinetoPoint2D(lines);
			// System.out.println("After >> "+points.size());
			// /Write Start & End index
			/*
			 * if(stuff.getStartIndex()!=null) { for(int i=0;i<containerNew.getShapeContainerSize();i++) {
			 * if(containerNew.getShape(i)==stuff.getStartIndex()) {
			 * out.write(startindex_s+i+startindex_e+'\n'); break; } } } else{
			 * out.write(startindex_s+-1+startindex_e+'\n'); }
			 * 
			 * if(stuff.getEndIndex()!=null) { for(int i=0;i<containerNew.getShapeContainerSize();i++) {
			 * if(containerNew.getShape(i)==stuff.getEndIndex()) {
			 * out.write(endindex_s+i+endindex_e+'\n'); break; } } } else{
			 * out.write(endindex_s+-1+endindex_e+'\n'); }
			 */
			if (stuff.getStartIndex() != null) {
				for (int i = 0; i < container.getShapeContainerSize(); i++) {
					if (container.getShape(i) == stuff.getStartIndex()) {
						out.write(startindex_s + i + startindex_e + '\n');
						break;
					}
				}
			} else {
				out.write(startindex_s + -1 + startindex_e + '\n');
			}

			if (stuff.getEndIndex() != null) {
				for (int i = 0; i < container.getShapeContainerSize(); i++) {
					if (container.getShape(i) == stuff.getEndIndex()) {
						out.write(endindex_s + i + endindex_e + '\n');
						break;
					}
				}
			} else {
				out.write(endindex_s + -1 + endindex_e + '\n');
			}
			// /Write Point2D
			out.write(quantity_s + points.size() + quantity_e + '\n');
			for (int i = 0; i < points.size(); i++) {
				out.write(point2d_s + points.get(i).getX() + ","
						+ points.get(i).getY() + point2d_e + '\n');
			}

			out.write(rope_e + '\n');
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeSpringShape(OutputStreamWriter out, SpringShape stuff) {
		try {
			out.write(spring_s + '\n');
			// /WRITE POLYGON POINTS///
			for (int i = 0; i < container.getShapeContainerSize(); i++) {
				if (container.getShape(i) == stuff.getStartIndex()) {
					out.write(startindex_s + i + startindex_e + '\n');
					break;
				}
			}
			for (int i = 0; i < container.getShapeContainerSize(); i++) {
				if (container.getShape(i) == stuff.getEndIndex()) {
					out.write(endindex_s + i + endindex_e + '\n');
					break;
				}
			}
			out.write(k_s + stuff.getKSpring() + k_e + '\n');
			/*
			 * out.write(startpoint_s+stuff.getStartPoint().getX()+","+
			 * stuff.getStartPoint().getY()+startpoint_e+'\n');
			 * out.write(endpoint_s+stuff.getEndPoint().getX()+","+
			 * stuff.getEndPoint().getY()+endpoint_e+'\n');
			 */
			out.write(spring_e + '\n');
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeJointShape(OutputStreamWriter out, JointShape stuff) {
		try {
			out.write(joint_s + '\n');
			// /WRITE POLYGON POINTS///
			for (int i = 0; i < container.getShapeContainerSize(); i++) {
				if (container.getShape(i) == stuff.getStartIndex()) {
					out.write(startindex_s + i + startindex_e + '\n');
					break;
				}
			}
			for (int i = 0; i < container.getShapeContainerSize(); i++) {
				if (container.getShape(i) == stuff.getEndIndex()) {
					out.write(endindex_s + i + endindex_e + '\n');
					break;
				}
			}
			out.write(rotatepoint_s + stuff.getRotatePoint().getX() + ","
					+ stuff.getRotatePoint().getY() + rotatepoint_e + '\n');
			out.write(joint_e + '\n');
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeFixJoint(OutputStreamWriter out, FixJointShape stuff) {
		try {
			out.write(fixjoint_s + '\n');
			// /WRITE POLYGON POINTS///
			for (int i = 0; i < container.getShapeContainerSize(); i++) {
				if (container.getShape(i) == stuff.getStartIndex()) {
					out.write(startindex_s + i + startindex_e + '\n');
					break;
				}
			}
			for (int i = 0; i < container.getShapeContainerSize(); i++) {
				if (container.getShape(i) == stuff.getEndIndex()) {
					out.write(endindex_s + i + endindex_e + '\n');
					break;
				}
			}

			out.write(fixjoint_e + '\n');
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeEllipse(OutputStreamWriter out, EllipseShape stuff) {
		try {
			out.write(ellipse_s + '\n');
			// /WRITE POLYGON POINTS///
			Point2D cm = stuff.getCenter();

			out.write(cm_s + cm.getX() + "," + cm.getY() + cm_e + '\n');
			out.write(diameter_s + stuff.getDiameter() + diameter_e + '\n');
			// /WRITE POLYGON STATS///
			out.write(mass_s + stuff.getMass() + mass_e + '\n');
			out.write(uS_s + stuff.getMs() + uS_e + '\n');
			out.write(uK_s + stuff.getMk() + uK_e + '\n');
			out.write(isFix_s + stuff.isFixed() + isFix_e + '\n');
			if (stuff.isFixed() && stuff.isBackground() == false) {
				out.write(cross_s + stuff.getCenterCross().getX() + ","
						+ stuff.getCenterCross().getY() + cross_e + '\n');
			}
			out.write(bounce_s + stuff.getBounce() + bounce_e + '\n');
			String C = stuff.getColor().toString();
			int st = C.indexOf("=");
			int ed = C.indexOf(",");
			String color = C.substring(st + 1, ed) + ",";
			C = C.substring(ed + 1);
			st = C.indexOf("=");
			ed = C.indexOf(",");
			color += C.substring(st + 1, ed) + ",";
			C = C.substring(ed + 1);
			st = C.indexOf("=");
			ed = C.indexOf("]");
			color += C.substring(st + 1, ed);
			out.write(color_s + color + color_e + '\n');
			out.write(isBg_s + stuff.isBackground() + isBg_e + '\n');
			if (stuff.getImage() != null) {
				out.write(image_s + stuff.getImagePath() + image_e + '\n');
			} else {
				out.write(image_s + "null" + image_e + '\n');
			}
			out.flush();

			// /END POLYGON STATS///
			out.write(ellipse_e + '\n');
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeTriangle(OutputStreamWriter out, TriangleShape stuff) {
		try {
			out.write(triangle_s + '\n');
			// /WRITE POLYGON POINTS///
			ArrayList<Point2D> points = new ArrayList<Point2D>();
			points = stuff.getPointPolygon();
			out.write(point2d_s + points.get(0).getX() + ","
					+ points.get(0).getY() + point2d_e + '\n');
			out.write(point2d_s + points.get(1).getX() + ","
					+ points.get(1).getY() + point2d_e + '\n');
			out.write(point2d_s + points.get(2).getX() + ","
					+ points.get(2).getY() + point2d_e + '\n');
			// /WRITE POLYGON STATS///

			out.write(mass_s + stuff.getMass() + mass_e + '\n');
			out.write(uS_s + stuff.getMs() + uS_e + '\n');
			out.write(uK_s + stuff.getMk() + uK_e + '\n');
			out.write(isFix_s + stuff.isFixed() + isFix_e + '\n');
			if (stuff.isFixed() && stuff.isBackground() == false) {
				out.write(cross_s + stuff.getCenterCross().getX() + ","
						+ stuff.getCenterCross().getY() + cross_e + '\n');
			}
			out.write(bounce_s + stuff.getBounce() + bounce_e);
			String C = stuff.getColor().toString();
			int st = C.indexOf("=");
			int ed = C.indexOf(",");
			String color = C.substring(st + 1, ed) + ",";
			C = C.substring(ed + 1);
			st = C.indexOf("=");
			ed = C.indexOf(",");
			color += C.substring(st + 1, ed) + ",";
			C = C.substring(ed + 1);
			st = C.indexOf("=");
			ed = C.indexOf("]");
			color += C.substring(st + 1, ed);
			out.write(color_s + color + color_e + '\n');
			out.write(isBg_s + stuff.isBackground() + isBg_e + '\n');
			if (stuff.getImage() != null) {
				out.write(image_s + stuff.getImagePath() + image_e + '\n');
			} else {
				out.write(image_s + "null" + image_e + '\n');
			}
			out.flush();
			// /END POLYGON STATS///
			out.write(triangle_e + '\n');
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeRectangle(OutputStreamWriter out, RectangleShape stuff) {
		try {
			out.write(rectangle_s + '\n');
			// /WRITE POLYGON POINTS///
			ArrayList<Point2D> points = new ArrayList<Point2D>();
			points = stuff.getPointPolygon();
			out.write(point2d_s + points.get(0).getX() + ","
					+ points.get(0).getY() + point2d_e + '\n');
			out.write(point2d_s + points.get(1).getX() + ","
					+ points.get(1).getY() + point2d_e + '\n');
			out.write(point2d_s + points.get(2).getX() + ","
					+ points.get(2).getY() + point2d_e + '\n');
			out.write(point2d_s + points.get(3).getX() + ","
					+ points.get(3).getY() + point2d_e + '\n');
			// /WRITE POLYGON STATS///
			out.write(mass_s + stuff.getMass() + mass_e + '\n');
			out.write(uS_s + stuff.getMs() + uS_e + '\n');
			out.write(uK_s + stuff.getMk() + uK_e + '\n');
			out.write(isFix_s + stuff.isFixed() + isFix_e + '\n');
			if (stuff.isFixed() && stuff.isBackground() == false) {
				out.write(cross_s + stuff.getCenterCross().getX() + ","
						+ stuff.getCenterCross().getY() + cross_e + '\n');
			}
			out.write(bounce_s + stuff.getBounce() + bounce_e);
			String C = stuff.getColor().toString();
			int st = C.indexOf("=");
			int ed = C.indexOf(",");
			String color = C.substring(st + 1, ed) + ",";
			C = C.substring(ed + 1);
			st = C.indexOf("=");
			ed = C.indexOf(",");
			color += C.substring(st + 1, ed) + ",";
			C = C.substring(ed + 1);
			st = C.indexOf("=");
			ed = C.indexOf("]");
			color += C.substring(st + 1, ed);
			out.write(color_s + color + color_e + '\n');
			out.write(isBg_s + stuff.isBackground() + isBg_e + '\n');
			if (stuff.getImage() != null) {
				out.write(image_s + stuff.getImagePath() + image_e + '\n');
			} else {
				out.write(image_s + "null" + image_e + '\n');
			}
			out.flush();
			// /END POLYGON STATS///

			out.write(rectangle_e + '\n');
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writePolygon(OutputStreamWriter out, PolygonShape stuff) {
		try {
			out.write(polygon_s + '\n');
			ArrayList<Point2D> points = new ArrayList<Point2D>();
			points = stuff.getPointPolygon();
			int numPoint = points.size();
			out.write(quantity_s + numPoint + quantity_e + '\n');
			for (int i = 0; i < numPoint; i++) {
				out.write(point2d_s + points.get(i).getX() + ","
						+ points.get(i).getY() + point2d_e + '\n');
			}
			// /WRITE POLYGON STATS///
			out.write(mass_s + stuff.getMass() + mass_e + '\n');
			out.write(uS_s + stuff.getMs() + uS_e + '\n');
			out.write(uK_s + stuff.getMk() + uK_e + '\n');
			out.write(isFix_s + stuff.isFixed() + isFix_e + '\n');
			if (stuff.isFixed() && stuff.isBackground() == false) {
				out.write(cross_s + stuff.getCenterCross().getX() + ","
						+ stuff.getCenterCross().getY() + cross_e + '\n');
			}
			out.write(bounce_s + stuff.getBounce() + bounce_e);
			String C = stuff.getColor().toString();
			int st = C.indexOf("=");
			int ed = C.indexOf(",");
			String color = C.substring(st + 1, ed) + ",";
			C = C.substring(ed + 1);
			st = C.indexOf("=");
			ed = C.indexOf(",");
			color += C.substring(st + 1, ed) + ",";
			C = C.substring(ed + 1);
			st = C.indexOf("=");
			ed = C.indexOf("]");
			color += C.substring(st + 1, ed);
			out.write(color_s + color + color_e + '\n');
			out.write(isBg_s + stuff.isBackground() + isBg_e + '\n');
			if (stuff.getImage() != null) {
				out.write(image_s + stuff.getImagePath() + image_e + '\n');
			} else {
				out.write(image_s + "null" + image_e + '\n');
			}
			out.flush();
			// /END POLYGON STATS///
			out.write(polygon_e + '\n');
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeShortNote(OutputStreamWriter out) {
		try{
			out.write(note_s+'\n');
			if(shortNotes == null)
			{
				out.write(quantity_s);
				out.write("0");
				out.write(quantity_e+'\n');
			}else{
				out.write(quantity_s);
				out.write(""+shortNotes.size());
				out.write(quantity_e+'\n');
				for(int i=0;i<shortNotes.size();i++)
				{
					out.write(startpoint_s);
					out.write(""+shortNotes.get(i).getX1()+","+shortNotes.get(i).getY1());
					out.write(startpoint_e+'\n');
					out.write(endpoint_s);
					out.write(""+shortNotes.get(i).getX2()+","+shortNotes.get(i).getY2());
					out.write(endpoint_e+'\n');
				}
			}
			
			out.write(note_e+'\n');
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
