package other;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

/**
 * This class provides an abstraction over the java UDP sockets
 * and packets.
 *
 */
public class Messenger {
	private static Messenger messenger;
	private static int MESSAGE_LENGTH = 200; // in bytes
	
	private Set<Integer> ports;
	/**
	 * This is a singleton class
	 */
	private Messenger() {
		ports = new HashSet<Integer>();
	};
	
	/**
	 * Returns an instance of the Messgenger class.
	 */
	public static Messenger getMessenger() {
		if(messenger == null) {
			messenger = new Messenger();
		}
		
		return messenger;
	}
	
	/**
	 * Creates an receive socket on the specified port. The socket 
	 * will listen continuously.
	 */
	public boolean receive(int port, MessageListener messageListener) {
		if(isPortReceiving(port)) {
			return false;
		}
		
		ports.add(port); // Mark the port as occupied
		
		try {
			DatagramSocket socket = new DatagramSocket(port);
			
			Thread receiveThread = new Thread(new Runnable() {
				public void run() {
					while(isPortReceiving(port)) {
						byte[] message = new byte[MESSAGE_LENGTH];
						DatagramPacket packet = new DatagramPacket(message, MESSAGE_LENGTH);
						
						try {
							socket.receive(packet);
							
							Message msg = (Message) deserializeObject(packet.getData());
							
							Thread messageWriter = new Thread(new Runnable() {
								public void run() {
									messageListener.onMessageReceived(msg);
								}
							});
							
							messageWriter.start();
							
						} catch (IOException e) {
							System.out.println("Problem occured while receiving packet");
							e.printStackTrace();
							
							break;
						}
					}
					
					socket.close();
				}
			});
			
			receiveThread.start();
		} catch (Exception e) {
			// Socket creation failed
			ports.remove(port);
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Sends a message to the specified port and address.
	 */
	public boolean send(Message message, int port, InetAddress address) {
		DatagramSocket socket = null;
		
		try {
			socket = new DatagramSocket();
			
			byte[] msg = serializeObject(message);
			
			DatagramPacket packet = new DatagramPacket(msg, msg.length, address, port);
			
			socket.send(packet);
		} catch (Exception e) {
			e.printStackTrace();
			
			return false;
		} finally {
			if(socket != null) {
				socket.close();
			}
		}
		
		return true;
	}
	
	/**
	 * Controls the receive sockets that are currently open
	 */
	public boolean stopReceive(int port) {
		synchronized (ports) {
			if(!ports.contains(port)) {
				return false;
			}
			
			ports.remove(port);
		}
		
		return true;
	}
	
	/**
	 * Returns the ports of the receiving sockets. Returns a copy
	 * of the ports.
	 * 
	 */
	public Set<Integer> getPorts() {
		return new HashSet<Integer>(ports);
	}
	
	/**
	 * Returns true if the receive socket on the specified port is still open.
	 * 
	 */
	private boolean isPortReceiving(int port) {
		synchronized (ports) {
			return ports.contains(port);
		}
	}
	
	/**
	 * Serialize an object to byte array.
	 */
	private byte[] serializeObject(Object object) {
		ByteArrayOutputStream in = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		byte[] result = null;
		
		try {
			out = new ObjectOutputStream(in);   
			out.writeObject(object);
			out.flush();
			result = in.toByteArray();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	/**
	 * Deserialize an object from byte array.
	 */
	private Object deserializeObject(byte[] object) {
		ByteArrayInputStream in = new ByteArrayInputStream(object);
		ObjectInput out = null;
		Object result = null;
		
		try {
			out = new ObjectInputStream(in);
			result = out.readObject(); 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		  try {
			  if (out != null) {
				  out.close();
			  }
		  	} catch (IOException e) {
			  e.printStackTrace();
		  	}
		}
		
		return result;
	}
}
