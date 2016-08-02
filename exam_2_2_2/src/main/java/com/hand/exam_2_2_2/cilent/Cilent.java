package com.hand.exam_2_2_2.cilent;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cilent {
	public static void main(String[] args) {
		
		try {
			Socket socket = new Socket("localhost",12345);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			
			FileOutputStream fos = new FileOutputStream(new File("target2.pdf"));
			byte[] b = new byte[1024];
			int len = 0;
			
			System.out.println("开始传输文件！");
			while((len = dis.read(b))!= -1){
				fos.write(b, 0, len);
				fos.flush();
			}
			
			System.out.println("文件传输结束！");
			
			
			fos.close();
			dis.close();
		
		
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
