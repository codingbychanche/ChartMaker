package test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.berthold.diagramDrawer.DataSet;
import org.berthold.diagramDrawer.Chart;
import org.berthold.diagramDrawer.ChartSettings;

public class main {
	
	public static void main(String[] args ) {
		System.out.println("Test");
		
		//
		// Shows a line- grap using standart settings..
		//
		DataSet dataSet=new DataSet ("Sample Set....");
		
		dataSet.add(0,0, "0");
		dataSet.add(1.0,1, "1");
		dataSet.add(2,5.0, "2");
		dataSet.add(3,1.0, ".5");
		dataSet.add(4,8.0, "12");
		dataSet.add(5,-10.0, "2");
		
		dataSet.add(5.5,0, "0");
		dataSet.add(5.9,0, "1");
		dataSet.add(6,0, "2");
		dataSet.add(7,1.0, ".5");
		dataSet.add(8,8.0, "12");
		dataSet.add(10,10.0, "2");
		dataSet.add(11,10.0, "2");
		dataSet.add(12,8.0, "2");
		dataSet.add(14,5.0, "2");
		dataSet.add(15,1.0, "2");
	
		ChartSettings diagramSettings=new ChartSettings("standard.png");
		diagramSettings.setNumberFormat(".2f");
		diagramSettings.setPadX_px(10);
		diagramSettings.setPadY_px(20);
		
		Chart d=new Chart(dataSet,1200,600,diagramSettings);
		d.draw();
		
		//
		// Draw a line graph using custom settings
		//		
		Color c=new Color(255,100,100);
		diagramSettings.setFillColorStart(c);
		c=new Color (255,190,190);
		diagramSettings.setFillColorEnd(c);
		
		c=new Color (255,255,255);
		diagramSettings.setDataPointsColor(c);
		
		diagramSettings.setDataPointThickness(1f);
		
		diagramSettings=new ChartSettings("custom.png");
		diagramSettings.setNumberFormat(".2f");
		diagramSettings.setPadX_px(10);
		diagramSettings.setPadY_px(20);
		
		Chart d1=new Chart(dataSet,1200,600,diagramSettings);
		d1.draw();
		
	}
}
 