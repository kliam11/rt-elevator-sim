package other;

import java.io.Serializable;

/**
 * Class for messages that are sent between floor system and scheduler, also elevator system and scheduler
 */
public class Message implements Serializable {

	//Variables
	private static final long serialVersionUID = 6263179524640426377L;
	private MessageType type;
	private String body;
	
	/**
	 * Constructor for a message
	 */
	public Message(MessageType type, String body) {
		this.type = type;
		this.body = body;
	}

	/**
	 * Method to obtain the method's type
	 */
	public MessageType getType() {
		return type;
	}
	
	/**
	 * Method to change the method's type
	 */
	public boolean setType(MessageType type) {
		if(this.type.equals(type)) {
			return false;
		}
		this.type = type;
		return true;
	}

	/**
	 * Returns the string stored in message
	 */
	public String getBody() {
		return body;
	}
}
