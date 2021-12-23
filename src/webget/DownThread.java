package webget;

public class DownThread extends Thread {
	
	private IWebGet wg = null;
	
	public DownThread(IWebGet wg) {
		this.wg = wg;
	}
 
	public void run(){
		this.wg.Down();
		System.out.println("Done!");
	}
}
