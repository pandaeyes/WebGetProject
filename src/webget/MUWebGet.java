package webget;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class MUWebGet extends IWebGet {
	
	private String httpUrl = null;
	private HashMap<String, String> setting = null;
	private ArrayList<String> resultList = null;
	
	public MUWebGet(String url, ArrayList<String> resultList, HashMap<String, String> setting) {
		this.httpUrl = url;
		this.resultList = resultList;
		this.setting = setting;
	}

	
	public boolean Down() {
		System.out.println("Download file:" + httpUrl);
        // 1.下载网络文件
        int byteRead;
        URL url;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return false;
        }

        try {
            //2.获取链接
            URLConnection conn = url.openConnection();
            // conn.setConnectTimeout(60000);
            SetProperty(conn, setting);
            conn.setReadTimeout(60000);
            //3.输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            //3.写入文件
            // FileOutputStream fs = new FileOutputStream(saveFile);
            byte[] buffer = new byte[1024];
            String line;
            while ((line = br.readLine()) != null) {
            	if (line != null && line.endsWith(".ts")) {
            		resultList.add(line);
            	}
            }
            br.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
	}
}
