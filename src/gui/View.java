/**
 * 
 */
package gui;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * @author liam
 *
 */
public class View extends Pane {
	private ElevView elevView; 
	private ListView<String> infoPane; 
	
	public View(int numElevs, int numFloors) {
		
		elevView = new ElevView(numElevs, numFloors); 
		elevView.relocate(0, 0);
		
		infoPane = new ListView<String>(); 
		infoPane.relocate(550, 0);
		infoPane.setPrefSize(400, 700);
		infoPane.setBackground(getBackground().fill(Color.WHITE));
		
		getChildren().addAll(infoPane, elevView); 
		
	}
	
	public void update(int elevNum, int newFloor, int prevFloor, int error) {
		elevView.onFloor(elevNum, newFloor);
		elevView.offFloor(elevNum, prevFloor);
		elevView.showError(elevNum, newFloor, error, "");
		
	}
	
	public void updateInfo(ArrayList<String> updates) {
		infoPane.setItems(FXCollections.observableArrayList(updates));
	}
	
}