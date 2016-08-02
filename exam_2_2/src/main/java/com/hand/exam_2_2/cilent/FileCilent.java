package com.hand.exam_2_2.cilent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.apache.http.client.ClientProtocolException;

public class FileCilent {
	public static void main(String[] args) {
	
		
		try{
			/*Socket socket = new Socket("127.0.0.1", 12345);
			BufferedReader buf =  new BufferedReader(new InputStreamReader(socket.getInputStream()));  
			
			FileOutputStream fos = new FileOutputStream("target2.pdf");
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
			BufferedWriter bw = new BufferedWriter(osw);
			
			String line = null;
			System.out.println(buf.readLine());
			while((line = buf.readLine()) != null){
				System.out.println(line);
				osw.write(line);//这里有问题，并没有写进pdf文件中
			}
			bw.flush();
			bw.close();
			osw.close();
			fos.close();
            buf.close();*/
			
			
			Socket socket = new Socket("127.0.0.1", 12345);
			InputStream is = socket.getInputStream();
            
			FileOutputStream fos = new FileOutputStream("target2.pdf");
			
			
			byte[] b = new byte[1024*1024];
			
			is.read(b);
			fos.write(b);
			
			fos.close();
			is.close();
            
            
            System.out.println("aaaaa");
            
            
            FileInputStream fis = new FileInputStream("target2.pdf");
            InputStreamReader isr = new InputStreamReader(fis);
            
            BufferedReader br = new BufferedReader(isr);
            String line2 = null;
            int count = 0;
            while((line2 = br.readLine())!= null){
            	System.out.println("第"+ count++ +"行");
            }
            
            br.close();
            isr.close();
            fis.close();
            
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
