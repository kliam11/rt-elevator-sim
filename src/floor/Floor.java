package floor;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import other.*;
import scheduler.Scheduler;

/**
 * Class to represent a floor
 */
public class Floor implements Runnable {
	
	//Variables
	private Hashtable<Integer, FloorDoor> doors;
	private int people;
	private int floorNum;
	private FloorSystem floorSystem;
	private Message msg;
	private ArrayList<String> messages;
	
		
	/**
	 * Constructor for Floor class
	 */
	public Floor(FloorSystem floorSys, int floorNum, int numElev) {
		this.floorSystem = floorSys;
		this.floorNum = floorNum;
		this.people = 0;
		this.messages = new ArrayList<String>();
		
		//create the doors 
		doors = new Hashtable<Integer, FloorDoor>();
		for(int i = 1; i < numElev + 1; i++) {
			doors.put(i, new FloorDoor());
		}
	}

	/**
	* Method to run commands
	*/
	@Override
	public void run() {
		
		String inputFile = "input.txt";
		List<String> inputs = new ArrayList<String>();
		try {
			inputs = readFile(inputFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Long> timeStamps = new ArrayList<Long>();
		
		for(String line : inputs) {
			if(line.trim().isEmpty()) {
				continue;
			}
			
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss.S");
			LocalDateTime currentTime = LocalDateTime.now();  
			String formatedTime = dateFormat.format(currentTime);
			
			String[] input = null;
			input = line.split(",");
			
			if(formatedTime == input[0]) {
				System.out.println(input[0]);
				System.out.println();
			}
			
			messages.add(line + '\n');
			timeStamps.add(System.currentTimeMillis() + (long) 100 * messages.size());
		}
		
		
		Thread messageSending = new Thread(new Runnable() {
			public void run() {
				for(String m : messages) {
					System.out.println("Floor: there are still more inputs");
					System.out.println("Floor: addding message to outbound: " + m);
					floorSystem.addOutBoundMessage(new Message(MessageType.FLOOR, m));
				}
				messages.clear();
			}
		});
		
		messageSending.start();
		
		while(true) {
			processMessage();
		}
	}
		
	
	/**
	 *  Process the message to floor system
	 */
	public synchronized void processMessage() {
		while (msg == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Floor: Processing message received from elevator...");
		System.out.println("Floor: " + msg.getBody());
		
		this.floorSystem.addOutBoundMessage(msg);
		
		this.msg = null;
		

		notifyAll();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for floor to do work specified by message
	 * 
	 * @param msg Message with work for elevator to do
	 */
	public synchronized void request(Message msg) {

		while (this.msg != null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Floor: setting message  to floor with message: " + msg.getBody());
		this.msg = msg;
		notifyAll();
	}
	

	/**
	 * Reads the file and adds each line to the list
	 */
	private List<String> readFile(String inputFile) throws FileNotFoundException {
		
		ArrayList<String> inputs = new ArrayList<String>();
		
		Scanner scanner = new Scanner(Floor.class.getResourceAsStream(inputFile));

		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			inputs.add(line);
		}
		
		return inputs;
	}

	/**
	 * Method to obtain the number of doors
	 * 
	 */
	public int numOfDoors() {
		return doors.size();
	}

	/**
	 * Method to obtain the number of people on the floor
	 * 
	 */
	public int getPeople() {
		return people;
	}

	/**
	 * Method to set the number of people on the floor
	 * 
	 */
	public void setPeople(int people) {
		this.people = people;
	}

	/**
	 * Method to obtain the floor number
	 * 
	 */
	public int getFloorNum() {
		return floorNum;
	}
	
	
	/**
	 * Get the message of the floor
	 * 
	 */
	public Message getMessage() {
		return msg;
	}
	
	/**
	 * Get the list of messages stored on this floor
	 */
	public ArrayList<String> getMessages() {
		return messages;
	}

}