package com.hand.exam_2_3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import net.sf.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://hq.sinajs.cn/list=sz300170");
		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			String res = EntityUtils.toString(entity,"utf-8");
			
			//var hq_str_sz300170="汉得信息,13.910,14.550,13.500,14.040,13.270,  13.490,13.500,47476165,644210802.870,46400,13.490,116100,13.480,4600,13.470,16700,13.460,62300,13.450,190966,13.500,228000,13.510,16300,13.520,80600,13.530,34800,13.540,2016-08-01,15:05:03,00";
			String content = res.substring(21, 60);
			String[] strings = content.split(",");
			
			
			//保存到xml中
			Document doc = DocumentHelper.createDocument();
			//doc.addProcessingInstruction("xml-stylesheet", "type='text/xsl href='students.xsl'");
			Element root = doc.addElement("xml");
			Element stock = root.addElement("stock");
			
			Element name = stock.addElement("name");
			name.setText(strings[0]);
			
			Element open = stock.addElement("open");
			open.setText(strings[1]);
			
			Element close = stock.addElement("close");
			close.setText(strings[2]);
			
			Element current = stock.addElement("current");
			current.setText(strings[3]);
			
			Element high = stock.addElement("high");
			high.setText(strings[4]);
			
			Element low = stock.addElement("low");
			low.setText(strings[5]);
			
			writer(doc);
			
			
			//保存到为json格式
			JSONObject jo = new JSONObject();
			jo.put("name", strings[0]);
			jo.put("open", strings[1]);
			jo.put("close", strings[2]);
			jo.put("current", strings[3]);
			jo.put("high", strings[4]);
			jo.put("low", strings[5]);
			//System.out.println(jo);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("result.json")));
			bw.write(jo.toString());
			bw.close();
		
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	public static void writer(Document document) throws Exception {  
        // 紧凑的格式  
        // OutputFormat format = OutputFormat.createCompactFormat();  
        // 排版缩进的格式  
        OutputFormat format = OutputFormat.createPrettyPrint();  
        // 设置编码  
        format.setEncoding("UTF-8");  
        // 创建XMLWriter对象,指定了写出文件及编码格式  
        // XMLWriter writer = new XMLWriter(new FileWriter(new  
        // File("src//a.xml")),format);  
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(  
                new FileOutputStream(new File("result.xml")), "UTF-8"), format);  
        // 写入  
        writer.write(document);  
        // 立即写入  
        writer.flush();  
        // 关闭操作  
        writer.close();  
    }  
}
