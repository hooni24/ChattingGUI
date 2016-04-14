package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import manager.ChattingManager;

public class ChattingClientManager implements ChattingManager {
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public ChattingClientManager(){
		
		Socket client;
		try {
			client = new Socket("127.0.0.1", 7979);
			
			while(true){
				ois = new ObjectInputStream(client.getInputStream());
				oos = new ObjectOutputStream(client.getOutputStream());
				
				while(client.isConnected()){
					Object[] request = (Object[]) ois.readObject();
					
					String cmdValue = (String) request[0];
					switch(cmdValue){
					
					case "send" :
						break;
						
					}
					
					
					
					
				}//switch
				
			}// while
			
		} catch (UnknownHostException e) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}//»ý¼ºÀÚ
	
	
	
	public Object[] sendMessage(String message){
		Object[] request = {"send", message};
		Object[] result = (Object[]) sendReturn(request);
		return result;
	}//sendMessage()
	
	public Object[] sendReturn(Object[] o){
		Object[] result = null;
		try {
			oos.writeObject(o);
			result = (Object[]) ois.readObject();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
		return result;
	}//sendReturn()
	
	

}//class
