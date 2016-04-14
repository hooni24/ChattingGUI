package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChattingServer {
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	public ChattingServer() {

		try {
			ServerSocket server = new ServerSocket(7979);

			while (true) {
				Socket client = server.accept();

				oos = new ObjectOutputStream(client.getOutputStream());
				ois = new ObjectInputStream(client.getInputStream());

				while (client.isConnected()) {
					Object[] request = (Object[]) ois.readObject();

					String cmdValue = (String) request[0];
					switch (cmdValue) {

					case "send":
						break;
						
					}//switch
					
					
					

				} // inner while
			} // outter while

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}// ChattingServer()

	public Object[] sendMessage(String message) {
		Object[] request = { "send", message };
		Object[] result = (Object[]) sendReturn(request);
		return result;
	}// sendMessage()

	public Object[] sendReturn(Object[] o) {
		Object[] result = null;
		try {
			oos.writeObject(o);
			result = (Object[]) ois.readObject();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
		return result;
	}// sendReturn()

}// class
