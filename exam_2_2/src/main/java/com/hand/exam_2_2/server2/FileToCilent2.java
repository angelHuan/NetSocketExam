package com.hand.exam_2_2.server2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class FileToCilent2 extends Thread{
	
	private Socket socket;
	public FileToCilent2(Socket socket){
		this.socket = socket;
	}
	
	
	@Override
	public void run() {
		
		try {
			FileInputStream fis = new FileInputStream("target.pdf");
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			
			
			StringBuffer sb = new StringBuffer();
			String line = null;
			while((line = br.readLine())!= null){
				sb.append(line+"\n");
			}
			
			PrintStream out = new PrintStream(socket.getOutputStream());
			//System.out.println(sb.toString());
			out.write(sb.toString().getBytes());
			br.close();
			isr.close();
			fis.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	
	}
}
