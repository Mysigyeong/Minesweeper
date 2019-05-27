import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CustomFrame extends JFrame { //사용자지정 프레임
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private int x = -1;
	private int y = -1;
	private int numOfMine = -1;
	
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
			
			if (command.equals("setting")) { //설정버튼 눌렀을 때
				int tempX = 0;
				int tempY = 0;
				int tempMine = 0;
				
				try {
					tempX = Integer.parseInt(t1.getText());
					tempY = Integer.parseInt(t2.getText());
					tempMine = Integer.parseInt(t3.getText());
				}
				catch (NumberFormatException ex) { //수가 아닌 문자가 들어왔을 경우 그냥 창을 닫는다.
					dispose();
				}
				
				if (tempX > 30) { //가로값은 9~30, 세로값은 9~24, 지뢰개수는 10~667 범위 초과하면 최대 또는 최솟값을 입력한다.
					tempX = 30;
				}
				else if (tempX < 9) {
					tempX = 9;
				}
				
				if (tempY > 24) {
					tempY = 24;
				}
				else if (tempY < 9) {
					tempY = 9;
				}
				
				if (tempMine < 10) {
					tempMine = 10;
				}
				else if (tempMine > 667) {
					tempMine = 667;
				}
				
				x = tempX;
				y = tempY;
				numOfMine = tempMine;
				
				dispose();
			}
			else if (command.equals("close")) {
				dispose();
			}
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getMine() {
		return numOfMine;
	}
}
