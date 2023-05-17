/**
 * 
 */
package gui;

import java.util.ArrayList;

/**
 * @author liam
 *
 */
public class Model {
	private int numFloors; 
	private int numElevs; 
	
	private ArrayList<ElevModel> elevModels;
	
	private ArrayList<String> updates; 
	
	public Model(int numFloors, int numElevs) {
		this.numFloors = numFloors; 
		this.numElevs = numElevs; 
		
		elevModels = new ArrayList<ElevModel>(); 
		for(int i=0; i<numElevs; ++i) {
			elevModels.add(new ElevModel()); 
		}
		
		updates = new ArrayList<String>(); 
	}
	
	public ArrayList<String> getUpdates() {
		return updates; 
	}
	
	public int update(int elevNum, int newFloor, int error) {
		elevModels.get(elevNum).setCurrFloor(newFloor);
		elevModels.get(elevNum).setError(error);
		
		return elevModels.get(elevNum).getPrevFloor(); 
	}
	
	public void updateInfo(String update) {
		if(!update.equals("")) updates.add(update); 
	}

}