package org.berthold.diagramDrawer;

import java.util.ArrayList;
import java.util.List;
/**
 * This represents the data set for a diagram.
 * 
 * @author Berthold
 *
 */
public class DataSet {

	List<DataSetValue> dataSet = new ArrayList<>();
	String name;

	/**
	 * Creates a new dataset of values.
	 * 
	 * @param dataSet
	 * @param name
	 */
	public DataSet(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Gets the dataset.
	 * 
	 * @return A list of {@DataSetValue}- objects.
	 */
	public List <DataSetValue>getDatSet() {
		return this.dataSet;
	}

	/**
	 * Adds a new value to the dataset.
	 * 
	 * @param x
	 * @param y
	 * @param name
	 */
	public void add(double x, double y, String name) {
		DataSetValue v = new DataSetValue(x, y, name);
		dataSet.add(v);
	}
	
	/**
	 * Dataset value at specified index.
	 * 
	 * @param index
	 * @return A {@link DataSetValue}.
	 */
	public DataSetValue get(int index) {
		return dataSet.get(index);
	}

	/**
	 * Determines the biggest value in this table.
	 * 
	 * @return Biggest stress resultant of this table.
	 */

	public double getAbsMax() {
		double max = 0;
		double currentMax;

		for (DataSetValue v : dataSet) {
			currentMax = v.getY();
			if (currentMax > max)
				max = currentMax;
		}
		return max;
	}

	/**
	 * Determines the smallest value in this table.
	 * 
	 * @return The smallest stress resultant of this table.
	 */
	public double getAbsMin() {
		double min = 0;
		double currentMin;

		for (DataSetValue v : dataSet) {
			currentMin = v.getY();
			if (currentMin < min)
				min = currentMin;
		}
		return min;
	}

	/**
	 * Determines the biggest x- value
	 * 
	 * @return Biggest x- value.
	 */
	public double getAbsMaxX() {
		double max = 0;
		double currentMax;

		for (DataSetValue v : dataSet) {
			currentMax = v.getX();
			if (currentMax > max)
				max = currentMax;
		}
		return max;
	}
}
