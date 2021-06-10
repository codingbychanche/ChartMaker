package org.berthold.diagramDrawer;

import java.awt.Color;

/**
 * The global, generic settings for any diagram to be drawn.
 * 
 * @author Berthold
 *
 */
public class ChartSettings {

	// The diagram
	private String name;

	// Global text settings
	private String fontName;
	
	// GFX- ccordinates
	private int padX_px = 20;
	private int padY_px = 20;

	// Colors
	public static final int TOP_DOWN = 1, LEFT_RIGHT = 2;
	private int fillDirection;
	private Color fillColorStart, fillColorEnd;

	private Color axisColor;
	private Color dataPointsColor;
	private Color axisMarkersColor;

	// X and Y axis
	private double mainX;	// Dividers, main unit
	private double mainY;
	private int markerLength;
	private int textSizeAxixDesignators;
	private Color textColorAxisDesignators;


	// Lines
	private float dataPointThickness;

	// Values
	private String numberFormat = "%.2f";

	public ChartSettings(String name) {
		super();
		
		// Global settings
		this.name = name;
		this.fontName="SansSerif";

		// Set defaults
		// Colors
		fillDirection = TOP_DOWN;
		fillColorEnd = new Color(130, 130, 255);
		fillColorStart = new Color(220, 220, 255);
		
		dataPointsColor = new Color(0, 0, 100);
		
		axisColor = new Color(0, 0, 30);
		
		axisMarkersColor = new Color(0, 0, 0);
		
		markerLength=10;

		// Lines
		this.dataPointThickness = 2f;
		
		// Axes
		this.mainX=1;
		this.mainY=2;
		this.textSizeAxixDesignators=15;
		this.axisMarkersColor=new Color(0,0,50);

	}

	public int getPadX_px() {
		return padX_px;
	}

	public void setPadX_px(int padX_px) {
		this.padX_px = padX_px;
	}

	public int getPadY_px() {
		return padY_px;
	}

	public void setPadY_px(int padY_px) {
		this.padY_px = padY_px;
	}

	public String getNumberFormat() {
		return numberFormat;
	}

	public void setNumberFormat(String numberFormat) {
		this.numberFormat = numberFormat;
	}

	public String getDigramName() {
		return name;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public void setDiagramName(String name) {
		this.name = name;
	}

	public int getFillDirection() {
		return fillDirection;
	}

	public void setFillDirection(int fillDirection) {
		this.fillDirection = fillDirection;
	}

	public Color getFillColorStart() {
		return fillColorStart;
	}

	public void setFillColorStart(Color fillColorStart) {
		this.fillColorStart = fillColorStart;
	}

	public Color getFillColorEnd() {
		return fillColorEnd;
	}

	public void setFillColorEnd(Color fillColorEnd) {
		this.fillColorEnd = fillColorEnd;
	}

	public Color getAxisColor() {
		return axisColor;
	}

	public void setAxisColor(Color axisColor) {
		this.axisColor = axisColor;
	}	

	public int getTextSizeAxixDesignators() {
		return textSizeAxixDesignators;
	}

	public void setTextSizeAxixDesignators(int textSizeAxixDesignators) {
		this.textSizeAxixDesignators = textSizeAxixDesignators;
	}
	

	public Color getTextColorAxisDesignators() {
		return textColorAxisDesignators;
	}

	public void setTextColorAxisDesignators(Color textColorAxisDesignators) {
		this.textColorAxisDesignators = textColorAxisDesignators;
	}

	public double getMainX() {
		return mainX;
	}

	public void setMainX(double mainX) {
		this.mainX = mainX;
	}

	public double getMainY() {
		return mainY;
	}

	public int getMarkerLength() {
		return markerLength;
	}

	public void setMarkerLength(int markerLength) {
		this.markerLength = markerLength;
	}

	public void setMainY(double mainY) {
		this.mainY = mainY;
	}

	public Color getDataPointsColor() {
		return dataPointsColor;
	}

	public void setDataPointsColor(Color dataPointsColor) {
		this.dataPointsColor = dataPointsColor;
	}

	public Color getAxisMarkersColor() {
		return axisMarkersColor;
	}

	public void setAxisMarkersColor(Color axisMarkersColor) {
		this.axisMarkersColor = axisMarkersColor;
	}

	public float getDataPointThickness() {
		return dataPointThickness;
	}

	public void setDataPointThickness(float dataPointThickness) {
		this.dataPointThickness = dataPointThickness;
	}

}
