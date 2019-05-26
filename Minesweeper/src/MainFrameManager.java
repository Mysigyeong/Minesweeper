import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainFrameManager {
	private GameFrame frame;
	private WinEvent w;
	private File file;
	private Scanner scn;
	
	public MainFrameManager() {
		int[] num = new int[3];
		w = new WinEvent();
		
		try {
			file = new File("data/size.txt");
			scn = new Scanner(file);
			
			for (int i = 0; i < 3; i++) {
				num[i] = Integer.parseInt(scn.nextLine());
			}
			
			scn.close();
		}
		catch (FileNotFoundException e) {
			System.exit(1);
		}
		catch (NumberFormatException ex) {
			System.exit(1);
		}
		
		frame = new GameFrame(num[0], num[1], num[2]);
		frame.addWindowListener(w);
	}
	
	
	private class WinEvent implements WindowListener {
		@Override
		public void windowClosed(WindowEvent e) {
			int[] num = new int[3];
			
			try {
				file = new File("data/size.txt");
				scn = new Scanner(file);
				
				for (int i = 0; i < 3; i++) {
					num[i] = Integer.parseInt(scn.nextLine());
				}
				
				scn.close();
			}
			catch (FileNotFoundException exc) {
				System.exit(1);
			}
			catch (NumberFormatException ex) {
				System.exit(1);
			}
			
			frame = new GameFrame(num[0], num[1], num[2]);
			frame.addWindowListener(w);
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}

