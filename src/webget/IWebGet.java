package webget;

import java.net.URLConnection;
import java.util.HashMap;

public abstract class IWebGet {

	public abstract boolean Down();
	
	public void SetProperty(URLConnection conn, HashMap<String, String> setting) {
		for(String key : setting.keySet()) {
			conn.setRequestProperty(key, setting.get(key));
        }
	}
}
