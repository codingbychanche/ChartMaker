package org.berthold.diagramDrawer;

public class DataSetValue {
	
	private double x,y;
	private String name;
	
	public DataSetValue(double x, double y, String name) {
		super();
		this.x = x;
		this.y = y;
		this.name = name;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
