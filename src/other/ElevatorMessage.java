package other;

import other.ElevatorState;
import other.Message;

/**
 * Wrapper for an Elevator message.
 *
 */
public class ElevatorMessage {
	private String time;

	private int currentFloor;
	private ElevatorState state;
	private int elevatorNum;
	
	/**
	 * Constructs a new ElevatorMessage.
	 */
	public ElevatorMessage(Message message) {
		parse(message);
	}
	
	
	/**
	 * Parses an elevator message.
	 */
	private void parse(Message message) {
		String[] mArr = message.getBody().split(",");
		
		time = mArr[0];
		currentFloor = Integer.parseInt(mArr[1]);
		state = ElevatorState.valueOf(mArr[2]);
		elevatorNum = Integer.parseInt(mArr[3]);
	}
	
	/**
	 * Method to obtain the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Method to obtain the current floor that the elevator is on
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * Method to obtain the elevator's state
	 */
	public ElevatorState getState() {
		return state;
	}

	/**
	 * Method to obtain the elevator's number
	 */
	public int getElevatorNum() {
		return elevatorNum;
	}
	
	/**
	 * Method obtain a message based on the elevator
	 */
	public Message toMessage() {
		return new Message(MessageType.ELEVATOR, time + "," + currentFloor + "," + state + ","+ elevatorNum);
	}
}
