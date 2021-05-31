package com.lcourtn5.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	private static final int PORT=20003;
	
	public static void main(String[] args) throws IOException{
		 ServerSocket serverSocket = null;
		 
		 try {
			 serverSocket = new ServerSocket(PORT);
			 Socket clientSocket = null;
			 while(true) {
				 clientSocket = serverSocket.accept();
				 ClientThread thread = new ClientThread(clientSocket);
				 thread.start();
			 }
			 
		 } catch (IOException e) {
			 System.err.println("Could not listen on port: " + PORT);
			 System.err.println(e);
			 System.exit(1);
		 }
		 
	}

}
