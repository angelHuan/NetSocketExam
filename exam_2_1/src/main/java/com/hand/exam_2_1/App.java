package com.hand.exam_2_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://files.saas.hand-china.com/java/target.pdf");

		try {
			/*HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			String res = EntityUtils.toString(entity,"utf-8");
			//byte[] array = EntityUtils.toByteArray(entity);
			//下载到本地
			FileOutputStream fos = new FileOutputStream("target.pdf");
			OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");
			char[] charArray = res.toCharArray();
			//osw.write(res.getBytes());
			osw.write(charArray);
			osw.flush();
			osw.close();
			fos.close();*/

			URL url = new URL("http://files.saas.hand-china.com/java/target.pdf");
			InputStream is = url.openStream();
			byte[] b = new byte[1024*1024];
			int len;
			String fileName = "target.pdf";
			OutputStream bos = new FileOutputStream(new File(fileName));
			while ((len = is.read(b)) != -1) {
				bos.write(b, 0, len);
			}
			bos.flush();
			bos.close();
			is.close();   



			//打开文件
			FileInputStream fis = new FileInputStream("target.pdf");
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);

			String line = null;
			System.out.println("读取到的pdf文件为乱码!!!");
			while((line = br.readLine()) != null){
				System.out.println(line);
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
