/**
 * 
 */
package gui;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author liam
 *
 */
public class FloorView extends Pane {
	
	private ArrayList<Rectangle> floors; 
	
	public FloorView(int numFloors) { 
		floors = new ArrayList<Rectangle>(); 
		
		int inc = 0; 
		for(int i=0; i<numFloors; ++i) {
			
			Rectangle floor = new Rectangle(0, inc, 25, 25);
			floor.setFill(Color.LIGHTGRAY); 
			
			inc +=30; 
			
			getChildren().addAll(floor); 
			
			floors.add(floor); 
			
		}
		Collections.reverse(floors);
	}
	
	public void onFloor(int floorNum) {
		floors.get(floorNum).setFill(Color.GREEN);
	}
	
	public void offFloor(int floorNum) {
		floors.get(floorNum).setFill(Color.LIGHTGRAY);
	}
	
	public void showError(int floorNum) {
		floors.get(floorNum).setFill(Color.RED);
	}
}