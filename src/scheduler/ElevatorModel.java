package scheduler;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import javafx.application.Platform;
import other.ElevatorState;
import other.FloorMessage;

/**
 * An internal model used to store information about an information. Used by the scheduler.
 *
 */
public class ElevatorModel {
	private int elevatorNum;
	private ElevatorState state;
	private ElevatorState prevState;
	private int currentFloor;

	private Queue<FloorMessage> upQueue;
	private Queue<FloorMessage> downQueue;
	
	public ElevatorModel(int elevatorNum, ElevatorState state, int currentFloor) {
		this.elevatorNum = elevatorNum;
		this.state = state;
		this.currentFloor = currentFloor;
		this.prevState = null;
		
		upQueue = new PriorityQueue<FloorMessage>(10, new Comparator<FloorMessage>() {

			@Override
			public int compare(FloorMessage x, FloorMessage y) {
				
				// In the up queue lower floor number has high priority
				if(x.getFloorNum() < y.getFloorNum()) {
					return 1;
				} else if(x.getFloorNum() > y.getFloorNum()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		downQueue = new PriorityQueue<FloorMessage>(10, new Comparator<FloorMessage>() {

			@Override
			public int compare(FloorMessage x, FloorMessage y) {
				
				// In the down queue higher floor number has high priority
				if(x.getFloorNum() > y.getFloorNum()) {
					return 1;
				} else if(x.getFloorNum() < y.getFloorNum()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
	}
	
	/**
	 * Method to obtain the elevator number
	 */
	public int getElevatorNum() {
		return elevatorNum;
	}

	/**
	 * Method to obtain the state
	 */
	public ElevatorState getState() {
		return state;
	}
	
	/**
	 * Method to set the state
	 */
	public void setState(ElevatorState state) {
		if(this.state == ElevatorState.MOVINGUP || this.state == ElevatorState.MOVINGDOWN) {
			this.prevState = this.state;
		}
		this.state = state;
	}

	/**
	 * Method to obtain the queue with floor messages that request to go up
	 *
	 */
	public Queue<FloorMessage> getUpQueue() {
		return upQueue;
	}

	/**
	 * Method to obtain the queue with floor messages that request to go down
	 * 	 
	 */
	public Queue<FloorMessage> getDownQueue() {
		return downQueue;
	}
	
	/**
	 * Method to obtain the current floor
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * Method to set the current floor
	 */
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
	
	/**
	 * @return previous state of the elevator
	 */
	public ElevatorState getPrevState() {
		return prevState;
	}
}
