import java.awt.*;
import javax.swing.*;

import javax.swing.JFrame;

public class InfoFrame extends JFrame {
	public InfoFrame() {
		makeFrame();
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 2));
		
		JLabel l1 = new JLabel("안우솔");
		l1.setHorizontalAlignment(JLabel.CENTER);
		JLabel l2 = new JLabel("지뢰찾기 판 구현");
		l2.setHorizontalAlignment(JLabel.CENTER);
		JLabel l3 = new JLabel("우병수");
		l3.setHorizontalAlignment(JLabel.CENTER);
		JLabel l4 = new JLabel("지뢰찾기 메뉴바 구현");
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
		setLocationRelativeTo(null);
	}
}