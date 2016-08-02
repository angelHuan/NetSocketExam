package com.hand.exam_2_2_2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener extends Thread{
	@Override
	public void run() {

		try {
			ServerSocket ss = new ServerSocket(12345);
			while(true){
				Socket socket = ss.accept();
				
				System.out.println("链接成功！！！！");
				
				new Server(socket).start();
				
			}

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		new Listener().start();
	}
	
	
}
