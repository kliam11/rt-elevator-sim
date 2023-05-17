/**
 * 
 */
package gui;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * @author liam
 *
 */
public class ElevView extends Pane {
	private ArrayList<FloorView> elevs; 
	private ArrayList<Label> errors; 
	
	public ElevView(int numElevs, int numFloors) {
		elevs = new ArrayList<FloorView>();
		errors = new ArrayList<Label>(); 
		
		int sep = 0; 
		for(int i=0; i<numElevs; ++i) {
			Label lbl = new Label("Elevator " + (i+1)); 
			lbl.relocate(sep, 0);
			
			FloorView fv = new FloorView(numFloors); 
			fv.relocate(sep, 25);
			
			Label errLbl = new Label(""); 
			errLbl.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
			errLbl.setMaxWidth(125);
			errLbl.setWrapText(true);
			errLbl.relocate(sep, 700);
			
			sep += 150; 
			
			getChildren().addAll(fv, lbl, errLbl); 
			
			elevs.add(fv); 
			errors.add(errLbl); 
		}
	}
	
	public void onFloor(int elevNum, int floorNum) { 
		elevs.get(elevNum).onFloor(floorNum);
	}
	
	public void offFloor(int elevNum, int floorNum) { 
		elevs.get(elevNum).offFloor(floorNum);
	}
	
	public void showError(int elevNum, int floorNum, int errNum, String errDesc) {
		if(errNum>0) {
			errors.get(elevNum).setText("ERR #" + errNum + ": " + errDesc);
			elevs.get(elevNum).showError(floorNum); 
		}
	}

}