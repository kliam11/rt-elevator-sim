/**
 * 
 */
package gui;

/**
 * @author liam
 *
 */
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import other.Message;
import other.MessageListener;
import other.Messenger;
import other.Ports;

/**
 * @author liam
 *
 */
public class GUI extends Application {
	
	private Model model; 
	private View view;
	private Controller control; 
	
	private int floors; 
	private int elevs; 
	
	public GUI() {
		model = new Model(22, 4); 
		floors = 22; 
		elevs = 4; 
		//control = new Controller(this); 
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Pane root = new Pane(); 
		view = new View(4, 22); 
		root.getChildren().add(view);  
		
		stage.setTitle("SYSC 3303 C Group 5 Elevator Simulation Demonstration"); 
		stage.setScene(new Scene(root, 900, 750));
		stage.show();
		
		for(int i=0; i<elevs; ++i) {
			update(i, 1, 0); 
			update(i, 0, 0); 
		} 
	}
	
	public static void main(String[] args) {
		launch(args); 
	}
	
	public void update(int elevNum, int newFloor, int error) {
		int prevFloor = model.update(elevNum, newFloor, error);
		view.update(elevNum, newFloor, prevFloor, error); 
	}
	
	public void updateInfo(String update) {
		model.updateInfo(update); 
		view.updateInfo(model.getUpdates());
	}

}