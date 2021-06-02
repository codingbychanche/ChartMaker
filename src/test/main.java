package test;

import java.util.ArrayList;
import java.util.List;

import org.berthold.diagramDrawer.DataSet;
import org.berthold.diagramDrawer.Diagram;
import org.berthold.diagramDrawer.DiagramSettings;

public class main {
	
	public static void main(String[] args ) {
		System.out.println("Test");
		
		DataSet dataSet=new DataSet ("Sample Set....");
		
		dataSet.add(0,0, "0");
		dataSet.add(1.0,1, "1");
		dataSet.add(2,5.0, "2");
		dataSet.add(3,1.0, ".5");
		dataSet.add(4,8.0, "12");
		dataSet.add(5,10.0, "2");
		
		dataSet.add(5.5,0, "0");
		dataSet.add(5.9,1, "1");
		dataSet.add(6,0, "2");
		dataSet.add(7,1.0, ".5");
		dataSet.add(8,8.0, "12");
		dataSet.add(10,10.0, "2");
	
		DiagramSettings diagramSettings=new DiagramSettings("Sample Diagram.....");
		diagramSettings.setNumberFormat(".2f");
		diagramSettings.setPadX_px(10);
		diagramSettings.setPadY_px(20);
		
		Diagram d=new Diagram(dataSet,1200,600,diagramSettings);
		d.draw();
		
	}
}
 