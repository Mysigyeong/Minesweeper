import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class StopWatch implements Runnable
{
	private Thread t;
	private boolean isOn;
	private long curTime;
	private long nxtTime;
	public int sec;
	
	private boolean soundEnable;
	private AudioInputStream ais;
	private Clip clip;
	private File f;
	
	public StopWatch(boolean sound)
	{
		isOn = false;
		sec = 0;
		
		soundEnable = sound;
		f = new File("data/clock.wav");
		
		try {
			ais = AudioSystem.getAudioInputStream(f);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}
		catch (Exception e) {
			e.getStackTrace();
		}
		
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
					
					if (soundEnable) {
						clip.setFramePosition(0);
						clip.start();
					}
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
	
	public void setSound(boolean sound) {
		soundEnable = sound;
	}
}