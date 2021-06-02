package org.berthold.diagramDrawer;

/**
 * The global, generic settings for any diagram to be drawn.
 * 
 * @author Berthold
 *
 */
public class DiagramSettings {
	
	// The diagram
	private String name;
	
	// GFX
	private int padX_px=20;
	private int padY_px=20;
	
	// Values
	private String numberFormat="%.2f";

	public DiagramSettings(String name) {
		super();
		this.name=name;
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
	
	
	
}
