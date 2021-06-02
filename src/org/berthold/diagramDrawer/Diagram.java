package org.berthold.diagramDrawer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Draws diagrams
 * 
 * @author Berthold
 */
public class Diagram {

	private DataSet dataSet;
	private DiagramSettings settings;
	private int height_px, width_px;
	private int y0_px;
	double yMax, yMin, xMax;

	// Axis division......
	double mainX = 1;
	double mainY = 0.1;

	/**
	 * Creates a new diagram.
	 * 
	 * @param dataset      The dataset to be drawn.
	 * @param height_px    Canvas height in pixels.
	 * @param width_px     Canvas width in pixels.
	 * @param padX_px      Padding left and right in pixels.
	 * @param padY_px      Padding top and bottom in pixels.
	 * @param numberFormat Numer format used for output (standart java...).
	 */
	public Diagram(DataSet dataset, int width_px,int height_px, DiagramSettings settings) {
		super();
		this.dataSet = dataset;
		this.height_px = height_px;
		this.width_px = width_px;
		this.settings=settings;

		// Constants defining the gfx- window
		yMax = dataSet.getAbsMax();
		yMin = dataset.getAbsMin();
		xMax = dataset.getAbsMaxX();
	}

	/**
	 * Draws the specified diagram.
	 * 
	 */
	public void draw() {
		// Create an in memory Image
		BufferedImage img = new BufferedImage(width_px, height_px, BufferedImage.TYPE_INT_ARGB);

		// Grab the graphics object off the image
		Graphics2D graphics = img.createGraphics();
		graphics = assignRendieringHints(graphics);

		// Draw 
		graphics=drawCartesian(graphics,dataSet);
		graphics = drawLineDiagram(graphics, dataSet); 
		
		// Save to file.
		try {
			File outputfile = new File("Diagram" + ".png");
			ImageIO.write(img, "png", outputfile);
		} catch (Exception e) {
		}

		fontTest();
	}
	
	/**
	 * Adds a cartesian coordinate system to the passed graphics object.
	 * Draws all axes, designators, etc....
	 * 
	 * @param graphics  {@Graphics2D}- object.
	 * @param dataSet	The data set 
	 * @return			The {@Graphics2D}- object containing the coordinate system.
	 */
	private Graphics2D drawCartesian(Graphics2D graphics, DataSet dataSet) {
		
		graphics.setBackground(Color.WHITE);

		Stroke stroke = new BasicStroke(1f);
		graphics.setStroke(stroke);
		graphics.setColor(Color.BLACK);
		
		//
		// Determines the location of the zero point inside of the
		// coordinate system.
		//
		if (yMin == 0)
			y0_px = (height_px - 2 * settings.getPadY_px());

		else {
			if (Math.abs(yMin) == Math.abs(yMax))
				y0_px = ((height_px - 2 * settings.getPadY_px()) / 2);

			double f = 1;
			if (Math.abs(yMin) > Math.abs(yMax)) {
				f = Math.abs(yMax) / Math.abs(yMin);
				y0_px = (int) ((int) (height_px - 2 * settings.getPadY_px()) * f);
			}

			if (Math.abs(yMin) < Math.abs(yMax)) {
				f = Math.abs(yMin) / Math.abs(yMax);
				y0_px = (int) (height_px - (int) (height_px - 2 * settings.getPadY_px()) * f);
			}
		}

		// X- axis
		graphics.drawLine(settings.getPadX_px(), y0_px, width_px - settings.getPadX_px(), y0_px);

		// Y- axis
		graphics.drawLine(settings.getPadX_px(), settings.getPadY_px(), settings.getPadX_px(), height_px - settings.getPadY_px());

		// Draw markers for x- axis
		graphics.setColor(Color.GRAY);
		stroke = new BasicStroke(0.5f);
		graphics.setStroke(stroke);

		for (double x = 0; x < width_px - 2 * settings.getPadX_px(); x = x + mainX) {
			int xt = (int) getXT(x);

			graphics.drawLine(xt, y0_px - 10, xt, y0_px + 10);
		}

		// Draw markers for y-axis
		for (double yt = getYT(yMin); yt <= getYT(yMax); yt = yt + mainY) {
			graphics.drawLine(settings.getPadX_px() - 10, (int) yt, settings.getPadX_px() + 10, (int) yt);
		}
		
		return graphics;
	}

	/**
	 * Draw's the data set as a basic line diagram.
	 * 
	 * @param graphics {@Graphics2D}- object to which the diagram is to be added.
	 * @param dataSet  The {@DataSet}- object containing the data to be displayed.
	 * @return {@Graphics2D}- object containing the diagram.
	 */
	private Graphics2D drawLineDiagram(Graphics2D graphics, DataSet dataSet) {

		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("SansSerif", Font.PLAIN, 15));
		graphics.drawString(settings.getDigramName(), 2*settings.getPadX_px(),2*settings.getPadX_px());

		// Lines will be joined like....
		Stroke stroke3 = new BasicStroke(12f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND);
		
		// The color and the thikness...
		graphics.setColor(Color.RED);
		Stroke stroke = new BasicStroke(2f);
		graphics.setStroke(stroke);

		// Draw values
		int prevX = (int) getXT(dataSet.get(0).getX());
		int prevY = (int) getYT((dataSet.get(0).getY()));

		for (DataSetValue v : dataSet.getDatSet()) {
			int x = (int) getXT(v.getX());
			int y = (int) getYT(v.getY());

			graphics.drawLine(prevX, prevY, x, y);

			prevX = x;
			prevY = y;

		}
		return graphics;
	}

	/**
	 * Adds rendering hints for the graphics display.
	 * 
	 * @param graphics The {@Graphics2D}- object to which the rendering settings are
	 *                 to be added.
	 * @return The {@Graphics2D}- object containing the new rendering settings.
	 */
	private Graphics2D assignRendieringHints(Graphics2D graphics) {
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		graphics.setRenderingHints(rh);

		return graphics;
	}

	/*
	 * Shows all available system fonts.
	 */
	private void fontTest() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font[] fonts = ge.getAllFonts();

		for (Font font : fonts) {
			System.out.print(font.getFontName() + " : ");
			System.out.println(font.getFamily());
		}

	}

	/**
	 * Transforms a y- koordinate.
	 * 
	 * @param y y- koordinate
	 * @return Transformed y- koordinate.
	 */
	private double getYT(double y) {

		if (y > 0)
			return y0_px - ((y0_px - settings.getPadY_px()) / yMax) * y;
		if (y < 0)
			return y0_px + ((height_px - settings.getPadY_px() - y0_px) / yMin) * y;

		return (double) y0_px;
	}

	/**
	 * Transforms a x- koordinate.
	 * 
	 * @param x
	 * @return Transformed x- koordinate.
	 */
	private double getXT(double x) {
		return x * ((width_px - 2 * settings.getPadX_px()) / xMax) + settings.getPadX_px();
	}
}
