package com.hand.exam_2_2.cilent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;

public class FileCilent {
	public static void main(String[] args) {
		/*HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://localhost:12345");*/

		try {
			/*HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			String res = EntityUtils.toString(entity,"utf-8");
			//byte[] array = EntityUtils.toByteArray(entity);
			//下载到本地
			FileOutputStream fos = new FileOutputStream("target2.pdf");
			OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");
			//char[] charArray = res.toCharArray();
			osw.write(res);
			//osw.write(charArray);
			osw.flush();
			osw.close();
			fos.close();*/
			
			Socket socket = new Socket("127.0.0.1", 12345);
			//BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
			//URL url = new URL("http://localhost:12345");
			//InputStream is = socket.getInputStream();
			BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			byte[] b = new byte[1024*1024*1024];
			int len;
			OutputStream bos = new FileOutputStream("target2.pdf");
			bos.write(is.read());
			bos.flush();
			bos.close();
			is.close();   



			//打开文件
			FileInputStream fis = new FileInputStream("target.pdf");
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);

			String line = null;
			while((line = br.readLine()) != null){
				System.out.println(line);
			}
			br.close();
			isr.close();
			fis.close();
			socket.close();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
