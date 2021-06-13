package org.berthold.diagramDrawer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
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
 * Draws charts.
 * 
 * @author Berthold
 */
public class Chart {

	private DataSet dataSet;
	private ChartSettings settings;
	private int height_px, width_px;
	private int y0_px;
	double yMax, yMin, xMax;

	/**
	 * Creates a new chart.
	 * 
	 * @param dataset      The dataset to be drawn.
	 * @param height_px    Canvas height in pixels.
	 * @param width_px     Canvas width in pixels.
	 * @param padX_px      Padding left and right in pixels.
	 * @param padY_px      Padding top and bottom in pixels.
	 * @param numberFormat Numer format used for output (standart java...).
	 */
	public Chart(DataSet dataset, int width_px, int height_px, ChartSettings settings) {
		super();
		this.dataSet = dataset;
		this.height_px = height_px;
		this.width_px = width_px;
		this.settings = settings;

		// Constants defining the gfx- window
		yMax = dataSet.getAbsMax();
		yMin = dataset.getAbsMin();
		xMax = dataset.getAbsMaxX();
	}

	/**
	 * Draws the specified chart.
	 * 
	 */
	public void draw() {
		// Create an in memory Image
		int xOffset_px=settings.getPadX_px()+settings.getMarkerLength()+settings.getTextSizeAxixDesignators();
		int widthOfChartArea_px=width_px+2*xOffset_px;
		
		int yOffset_px=settings.getPadY_px()+settings.getMarkerLength()+settings.getTextSizeAxixDesignators();
		int heightOfChartArea_px=height_px+2*yOffset_px;
		
		BufferedImage img = new BufferedImage(widthOfChartArea_px, heightOfChartArea_px, BufferedImage.TYPE_INT_ARGB);

		// Grab the graphics object off the image
		Graphics2D graphics = img.createGraphics();
		graphics = assignRendieringHints(graphics);

		// Draw
		graphics = drawCartesian(graphics, dataSet,xOffset_px,yOffset_px);
		graphics = drawLineDiagram(graphics, dataSet);

		// Save to file.
		try {
			File outputfile = new File(settings.getDigramName());
			ImageIO.write(img, "png", outputfile);
		} catch (Exception e) {
		}

		fontTest();
	}

	/**
	 * Adds a cartesian coordinate system to the passed graphics object. Draws all
	 * axes, designators, etc....
	 * 
	 * @param graphics {@Graphics2D}- object.
	 * @param dataSet  The data set
	 * @return The {@Graphics2D}- object containing the coordinate system.
	 */
	private Graphics2D drawCartesian(Graphics2D graphics, DataSet dataSet,int xOffset_px,int yOffset_px) {

		graphics.setBackground(Color.WHITE);

		Stroke stroke = new BasicStroke(1f);
		graphics.setStroke(stroke);
		graphics.setColor(Color.BLACK);

		//
		// Determines the location of the zero point inside of the
		// coordinate system.
		//
		if (yMin == 0)
			y0_px = (height_px - settings.getPadY_px());

		else {
			if (Math.abs(yMin) == Math.abs(yMax))
				y0_px = ((height_px - settings.getPadY_px()) / 2);

			double f = 1;
			if (Math.abs(yMin) > Math.abs(yMax)) {
				f = Math.abs(yMax) / Math.abs(yMin);
				y0_px = (int) ((int) (height_px - settings.getPadY_px()) * f);
			}

			if (Math.abs(yMin) < Math.abs(yMax)) {
				f = Math.abs(yMin) / Math.abs(yMax);
				y0_px = (int) (height_px - (int) (height_px - settings.getPadY_px()) * f);
			}
		}

		// Draw background
		Color c1 = settings.getFillColorStart();
		Color c2 = settings.getFillColorEnd();
		GradientPaint gradient;

		if (settings.getFillDirection() == settings.TOP_DOWN)
			gradient = new GradientPaint((float) 0, 0, c1, (float) (0), height_px, c2);
		else
			gradient = new GradientPaint((float) 0, 0, c1, (float) (0), width_px, c2);

		graphics.setPaint(gradient);

		graphics.fillRect(xOffset_px,yOffset_px, width_px - settings.getPadX_px() * 2,
				height_px - settings.getPadY_px() * 2);

		// Draw x- axis
		graphics.setColor(settings.getAxisColor());
		graphics.drawLine(xOffset_px, y0_px, width_px+xOffset_px-settings.getPadX_px(), y0_px);

		// Draw y- axis
		graphics.drawLine(xOffset_px, yOffset_px, xOffset_px,
				height_px - yOffset_px);

		// Draw markers for x- axis
		graphics.setColor(settings.getAxisMarkersColor());
		stroke = new BasicStroke(0.5f);
		graphics.setStroke(stroke);

		graphics.setFont(new Font(settings.getFontName(), Font.PLAIN, settings.getTextSizeAxixDesignators()));

		for (double x = 0; x < width_px ; x = x + settings.getMainX()) {
			int xt = (int) getXT(x);
			graphics.drawLine(xt, y0_px - 10, xt, y0_px + 10);

			// No '0' or biggest value for x- axis!
			if (x != 0 && x != xMax) {
				int xc=getHorizCenterCoordinate(x+"",xt,graphics); // Centers value below marker
				graphics.drawString(x + "", xc, y0_px + settings.getTextSizeAxixDesignators()+settings.getMarkerLength());
			}
		}

		//
		//
		// Check from here..............................
		//
		
		
		
		
		// Draw markers for y-axis
		for (double y = yMin; y <= yMax; y = y + settings.getMainY()) {
			int yt = (int) getYT(y);
			graphics.drawLine(xOffset_px - 10, (int) yt+yOffset_px, xOffset_px + 10, (int) yt+yOffset_px);

			if (y != yMax) {
				int yc=getVertCenterCoordinate(settings.getTextSizeAxixDesignators(),yt);
				graphics.drawString(y + "", xOffset_px-settings.getMarkerLength()-getWidth(y+"", graphics), yc);
			}
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

		// Lines will be joined like....
		Stroke stroke = new BasicStroke(12f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		graphics.setStroke(stroke);

		// The color and the thikness...
		graphics.setColor(settings.getDataPointsColor());
		stroke = new BasicStroke(settings.getDataPointThickness());
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
	
	//
	// Algorytms....
	//
	/**
	 * Display width of a string in pixels.
	 * 
	 * @param string
	 * @param g Associated {@ling Graphics} object.
	 * @return	Width of string in pixels.
	 */
	private int getWidth(String string,Graphics g) {
		FontMetrics f=g.getFontMetrics();
		return f.stringWidth(string);
	}
	
	/**
	 * Calculates the x position of the upper left corner for a
	 * string which centers it relative to a given coordinate.
	 *  
	 * @param string The string.
	 * @param x	Coordinate relative to which the given string should be centered
	 * @param g Associated {@ling Graphics} object.
	 * @return The upper left coordinate of the centered string.
	 */
	private int getHorizCenterCoordinate(String string,int x,Graphics g) {
		int widthOfString=getWidth(string,g);
		return x-widthOfString/2;
	}
	
	private int getVertCenterCoordinate(int heightOfString,int y) {
		return y+heightOfString/2;
	}
}
