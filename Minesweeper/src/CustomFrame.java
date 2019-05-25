import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CustomFrame extends JFrame {
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	
	public CustomFrame() {
		makeFrame();
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 3, 3, 3));
		p.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		
		JLabel l1 = new JLabel("가로(9 ~ 30)");
		JLabel l2 = new JLabel("세로(9 ~ 24)");
		JLabel l3 = new JLabel("지뢰(10 ~ 667)");
		t1 = new JTextField(5);
		t2 = new JTextField(5);
		t3 = new JTextField(5);
		JButton b1 = new JButton("설정");
		JButton b2 = new JButton("취소");
		
		b1.setActionCommand("setting");
		b1.addActionListener(new ButtonClickListener());
		b2.setActionCommand("close");
		b2.addActionListener(new ButtonClickListener());
		
		p.add(l1);
		p.add(t1);
		p.add(b1);
		p.add(l2);
		p.add(t2);
		p.add(b2);
		p.add(l3);
		p.add(t3);
		
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
		setTitle("사용자 지정");
		setLocationRelativeTo(null);
	}
	
	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
			if (command.equals("setting")) {
				int x = 0;
				int y = 0;
				int mine = 0;
				
				try {
					x = Integer.parseInt(t1.getText());
					y = Integer.parseInt(t2.getText());
					mine = Integer.parseInt(t3.getText());
				}
				catch (NumberFormatException ex) {
					dispose();
				}
				
				if (x > 30) {
					x = 30;
				}
				else if (x < 9) {
					x = 9;
				}
				
				if (y > 24) {
					y = 24;
				}
				else if (x < 9) {
					y = 9;
				}
				
				if (mine < 10) {
					mine = 10;
				}
				else if (mine > 667) {
					mine = 667;
				}
			}
			else if (command.equals("close")) {
				dispose();
			}
		}
	}
}
