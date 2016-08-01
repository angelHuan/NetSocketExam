package com.hand.exam_2_2.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class FileSocket extends Thread{
	
	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(12345);
			
			while(true){
				Socket socket = serverSocket.accept();
				//JOptionPane.showMessageDialog(null, "连接成功！");
				
				FileInputStream fis = new FileInputStream("target.pdf");
				byte input[] = new byte[1024*1024*1024];
				fis.read(input);
				String inputString = new String(input, "UTF-8");
				fis.close();
				socket.getOutputStream().write(inputString.getBytes("utf-8"));
				//fis.close();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
