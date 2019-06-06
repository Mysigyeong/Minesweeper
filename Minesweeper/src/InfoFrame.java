import java.awt.*;
import javax.swing.*;

import javax.swing.JFrame;

public class InfoFrame extends JFrame { //만든이 프레임
	public InfoFrame() {
		makeFrame();
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 2));
		
		JLabel l1 = new JLabel("안우솔");
		l1.setHorizontalAlignment(JLabel.CENTER);
		JLabel l2 = new JLabel("<html><center>지뢰찾기 판 구현<br>"
							+ "시계, 남은개수 구현</html>");
		l2.setHorizontalAlignment(JLabel.CENTER);
		JLabel l3 = new JLabel("우병수");
		l3.setHorizontalAlignment(JLabel.CENTER);
		JLabel l4 = new JLabel("<html><center>지뢰찾기 메뉴바 구현<br>"
							+ "소리 출력 구현</html>");
		l4.setHorizontalAlignment(JLabel.CENTER);
		
		p.add(l1);
		p.add(l2);
		p.add(l3);
		p.add(l4);
		
		Container con = getContentPane();
		con.add(p);
		
		setSize(300, 160);
		setResizable(false);
		setVisible(true);
	}
	
	private void makeFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("data/icon.jpg");
		setIconImage(img);
		setTitle("만든이");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width/3, screenSize.height/3);
	}
}