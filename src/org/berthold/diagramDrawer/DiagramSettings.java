package org.berthold.diagramDrawer;

import java.awt.Color;

/**
 * The global, generic settings for any diagram to be drawn.
 * 
 * @author Berthold
 *
 */
public class DiagramSettings {
	
	// The diagram
	private String name;
	
	// GFX- ccordinates
	private int padX_px=20;
	private int padY_px=20;
	
	// Colors
	private Color fillColor;
	private Color axisColor;
	private Color dataPointsColor;
	private Color axisMarkersColor;
	
	// Lines
	private float dataPointThickness;
	
	// Values
	private String numberFormat="%.2f";

	public DiagramSettings(String name) {
		super();
		this.name=name;
		
		// Set defaults
		// Colors
		fillColor=new Color(220,220,255);
		dataPointsColor=new Color(0,0,100);
		axisColor=new Color(0,0,30);
		axisMarkersColor=new Color(0,0,0);
		
		// Lines
		this.dataPointThickness=2f;
				
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

	public void setDiagramName(String name) {
		this.name = name;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public Color getAxisColor() {
		return axisColor;
	}

	public void setAxisColor(Color axisColor) {
		this.axisColor = axisColor;
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
