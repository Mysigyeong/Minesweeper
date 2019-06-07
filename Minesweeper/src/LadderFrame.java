import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LadderFrame extends JFrame{
	private String[] dataSet = new String[60];
	
	public LadderFrame(String raw) {
		makeFrame();
		
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		p.setLayout(new GridLayout(12, 6));
		
		dataSet = raw.split("\n");
		
		JLabel l = new JLabel("초급");
		l.setHorizontalAlignment(JLabel.CENTER);
		
		p.add(l);
		p.add(new JLabel(" "));
		
		l = new JLabel("중급");
		l.setHorizontalAlignment(JLabel.CENTER);
		
		p.add(l);
		p.add(new JLabel(" "));
		
		l = new JLabel("고급");
		l.setHorizontalAlignment(JLabel.CENTER);
		
		p.add(l);
		p.add(new JLabel(" "));
		
		for (int i = 0; i < 3; i++) {
			l = new JLabel("이름");
			l.setHorizontalAlignment(JLabel.CENTER);
			p.add(l);
			
			l = new JLabel("시간");
			l.setHorizontalAlignment(JLabel.CENTER);
			p.add(l);
		}
		
		for (int i = 0; i < 20; i += 2) {
			l = new JLabel(dataSet[i]);
			l.setHorizontalAlignment(JLabel.CENTER);
			p.add(l);
			
			l = new JLabel(dataSet[i + 1]);
			l.setHorizontalAlignment(JLabel.CENTER);
			p.add(l);
			
			l = new JLabel(dataSet[i + 20]);
			l.setHorizontalAlignment(JLabel.CENTER);
			p.add(l);
			
			l = new JLabel(dataSet[i + 21]);
			l.setHorizontalAlignment(JLabel.CENTER);
			p.add(l);
			
			l = new JLabel(dataSet[i + 40]);
			l.setHorizontalAlignment(JLabel.CENTER);
			p.add(l);
			
			l = new JLabel(dataSet[i + 41]);
			l.setHorizontalAlignment(JLabel.CENTER);
			p.add(l);
		}
		
		Container c = getContentPane();
		c.add(p);
		
		pack();
		setVisible(true);
		setResizable(false);
	}
	
	private void makeFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("data/icon.jpg");
		setIconImage(img);
		setTitle("세계기록");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width/3, screenSize.height/3);
	}
}
