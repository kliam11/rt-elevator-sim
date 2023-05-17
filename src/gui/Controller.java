/**
 * 
 */
package gui;

import javafx.application.Platform;
import other.Message;
import other.MessageListener;
import other.Messenger;
import other.Ports;

/**
 * @author liam
 *
 */
public class Controller implements Runnable, MessageListener {
	private Messenger messenger;
	GUI g; 
	
	public Controller(GUI g) {
		messenger = Messenger.getMessenger(); 
		this.g = g; 
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		messenger.receive(Ports.GUI_PORT, this); 
	}

	@Override
	public void onMessageReceived(Message message) {
		String[] s = message.getBody().split("\\s+"); 
		int elevNum = Integer.parseInt(s[0]);
		int currFloor = Integer.parseInt(s[1]); 
		String msg = s[2]; 
		int error = Integer.parseInt(s[3]); 
		
		
		Platform.runLater(() -> {
			g.update(elevNum, currFloor, error); 
			g.updateInfo(msg); 
		});
	}

}
