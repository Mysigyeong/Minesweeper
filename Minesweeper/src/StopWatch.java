public class StopWatch implements Runnable
{
	private Thread t;
	private boolean isOn;
	private long curTime;
	private long nxtTime;
	public int sec;
	
	public StopWatch()
	{
		isOn = false;
		sec = 0;
	}
	
	public void On() {
		isOn = true;
	}
	
	public void Off() {
		isOn = false;
	}
	
	public boolean checkOn() {
		return isOn;
	}
	
	public int getSec() {
		return sec;
	}
	
	public void run() {
		try {
			while(!isOn) {
				curTime = System.currentTimeMillis();
				nxtTime = curTime + 1000;
				Thread.sleep(50);
			}
			while(isOn) {
				Thread.sleep(100);
				curTime = System.currentTimeMillis();
				if(curTime >= nxtTime) {
					sec++;
					nxtTime = curTime + 1000;
				}
			}
		}
		catch(InterruptedException e) {}
	}
	
	public void start() {
		if(t == null)
		{
			t = new Thread(this);
			t.start();
		}
	}
}