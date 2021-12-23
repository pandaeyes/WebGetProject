package webget;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class VideoWebGet extends IWebGet {
	
	private String httpUrl = null;
	private String saveFile = null;
	private HashMap<String, String> setting = null;
	private ArrayList<String> resultList = null;
	
	public VideoWebGet(String url, ArrayList<String> resultList, String saveFile, HashMap<String, String> setting) {
		this.httpUrl = url;
		this.saveFile = saveFile;
		this.setting = setting;
		this.resultList = resultList;
	}

	
	public boolean Down() {
		System.out.println("Download file:" + httpUrl);
		System.out.println("Savefile:" + saveFile);
        // 1.下载网络文件
        try {
            FileOutputStream fs = new FileOutputStream(saveFile);
            for (String line: resultList) {
            	String fullPath = httpUrl + "/" + line;
            	if (!DownBlock(fs, fullPath)) {
            		System.out.println("Download error");
            		throw new RuntimeException("Download Error");
            	}
			}
            fs.close();
        } catch (Exception e1) {
            e1.printStackTrace();
            return false;
        }
        return true;
	}
	
	public boolean DownBlock(FileOutputStream fs, String fullPath) {
		System.out.println("Download fullPath:" + fullPath);
        // 1.下载网络文件
        int byteRead;
        URL url;
        try {
            url = new URL(fullPath);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return false;
        }

        try {
            //2.获取链接
            URLConnection conn = url.openConnection();
            SetProperty(conn, setting);
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);
            //3.输入流
            InputStream inStream = conn.getInputStream();
            //3.写入文件
            byte[] buffer = new byte[1024];
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            inStream.close();
            if (conn instanceof HttpURLConnection) {
            	((HttpURLConnection)conn).disconnect();
            }
            conn = null;
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
