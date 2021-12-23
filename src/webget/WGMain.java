package webget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WGMain {

	public static void main(String[] args) {
		String muUrl = null;
		String saveFile = null;
		if (args != null && args.length == 3) {
			muUrl = args[1];
			saveFile = args[2];
		} else {
			throw new RuntimeException("args error");
		}
		String httpUrl = GetPath(muUrl);
		HashMap<String, String> setting = new HashMap<String, String>();
		ArrayList<String> resultList = new ArrayList<String>();
		setting.put("accept", "*/*");
		setting.put("referer", "https://");
		IWebGet muwg = new MUWebGet(muUrl, resultList, setting);
		muwg.Down();
		IWebGet vwg = new VideoWebGet(httpUrl, resultList, saveFile, setting);
		for (String line : resultList) {
			System.out.println(line);
		}
		DownThread dth = new DownThread(vwg);
		dth.start();
	} 
	
	private static String GetPath(String url) {
		String pattern = "(.*)/.*m3u8";
	    Pattern r = Pattern.compile(pattern);
	    Matcher m = r.matcher(url);
	    if (m.find( )) { 
	    	String p = m.group(1) ;
	    	System.out.println("root: " + p);
	    	return p;
	    } else {
	    	throw new RuntimeException("Not Found value");
	    }
	}
}
