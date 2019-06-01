import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainFrameManager { //GameFrame(메인프레임)관리하는 클래스
	private GameFrame frame;
	private WinEvent w;
	private File file;
	private Scanner scn;
	
	public MainFrameManager() {
		int[] num = new int[5];
		w = new WinEvent();
		
		try {
			file = new File("data/size.txt"); //저장되어있는 사이즈값 불러와서 게임프레임을 만든다.
			scn = new Scanner(file);
			
			for (int i = 0; i < 5; i++) {
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
		
		frame = new GameFrame(num[0], num[1], num[2], num[3], num[4]);
		frame.addWindowListener(w);                    //메인 게임창이 닫힐 때의 이벤트 추가
	}
	
	
	private class WinEvent implements WindowListener {
		@Override
		public void windowClosed(WindowEvent e) {
			int[] num = new int[5];
			
			try {
				file = new File("data/size.txt");
				scn = new Scanner(file);
				
				for (int i = 0; i < 5; i++) {
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
			
			frame = new GameFrame(num[0], num[1], num[2], num[3], num[4]);
			frame.addWindowListener(w);
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// 내용 없음
		}

		@Override
		public void windowClosing(WindowEvent e) { //창 닫기 버튼을 눌러서 메인 게임 창이 닫혔을 경우에만 프로그램을 완전히 종료시킨다.
			System.exit(0);                        //그 이외의 방법으로 메인 게임창이 닫혔을 경우에는 사이즈 조절을 하는 경우로 생각하여 다시 판을 만든다.
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// 내용 없음
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// 내용 없음
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// 내용 없음
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// 내용 없음
		}
	}
}

