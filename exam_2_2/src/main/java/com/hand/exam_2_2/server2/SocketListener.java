package com.hand.exam_2_2.server2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketListener extends Thread{
	
	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(12345);
			
			while(true){
				Socket socket = serverSocket.accept();
				
				System.out.println("链接成功！！！");
				
				new FileToCilent2(socket).start();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
