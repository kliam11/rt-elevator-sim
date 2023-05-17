package other;

/**
 * Method used to receive a message from the Messenger.
 * 
 */
public interface MessageListener {
	/**
	 * Called by the Messenger when a message has been received.
	 */
	public void onMessageReceived(Message message);
}
