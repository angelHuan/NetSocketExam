package com.hand.exam_2_2_2.server;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Server extends Thread{
	
	private Socket socket;
	public Server(Socket socket){
		this.socket = socket;
	}
	
	
	@Override
	public void run() {
		try {
			
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			FileInputStream fis = new FileInputStream("target.pdf");
			byte[] b = new byte[1024];
			int length = 0;
			while((length = fis.read(b))!=-1){
				dos.write(b,0,length);
				dos.flush();
			}
			
			dos.close();
			fis.close();
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
}
