/**
 * 
 */
package gui;

/**
 * @author liam
 *
 */
public class ElevModel {

	private int currFloor; 
	private int prevFloor; 
	private int error; 
	
	public ElevModel() {
		currFloor = 0; 
		prevFloor = 0;
		error = 0; 
	}
	
	public int getPrevFloor() {
		return prevFloor; 
	}
	
	public int getCurrFloor() {
		return currFloor; 
	}
	
	public int getError() {
		return error; 
	}
	
	public void setCurrFloor(int newFloor) {
		prevFloor = currFloor; 
		currFloor = newFloor;
	}
	
	public void setError(int error) {
		this.error = error; 
	}
}