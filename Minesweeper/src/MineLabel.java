import javax.swing.JLabel;

public class MineLabel implements Runnable {
	private Thread t;
	private JLabel label;
	private int remainedMine;
	private StopWatch sw;
	
	public MineLabel(StopWatch stp, int mineCnt) {
		sw = stp;
		remainedMine = mineCnt;
		label = new JLabel();
	}
	
	public void decMine() {
		remainedMine--;
	}
	public void incMine() {
		remainedMine++;
	}
	
	public void run() {
		try {
			while(!sw.checkOn()) {
				label.setText(Integer.toString(remainedMine) + "°³");
				Thread.sleep(50);
			}
			while(sw.checkOn()) {
				label.setText(Integer.toString(remainedMine) + "°³");				
				Thread.sleep(50);
			}
		}
		catch(InterruptedException e) {}
	}
	
	public void start() {
		if(t == null) {
			t = new Thread(this);
			t.start();
		}
	}
	
	public JLabel getLabel() {
		return label;
	}

}
