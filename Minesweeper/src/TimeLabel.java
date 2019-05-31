import javax.swing.JLabel;

public class TimeLabel implements Runnable {
	private Thread t;
	private JLabel label;
	private StopWatch sw;
	
	public TimeLabel(StopWatch stp) {
		sw = stp;
		label = new JLabel();
	}
	
	public void run() {
		try {
			while(!sw.checkOn()) {
				label.setText("0√ ");
				Thread.sleep(50);
			}
			while(sw.checkOn()) {
				int t = sw.getSec();
				String secString = Integer.toString(t);
				label.setText(secString + "√ ");
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
