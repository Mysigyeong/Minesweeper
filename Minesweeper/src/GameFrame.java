import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class GameFrame extends JFrame {	
	private JButton[][] button;
	private CustomFrame cf;
	private ButtonClickListener bcl;
	
	public GameFrame(int x, int y, int mine) {
		bcl = new ButtonClickListener();
		
		makeFrame();
		makeMenuBar();
		makeBoard(x, y);
		pack();
		setResizable(false);
		setVisible(true);
		
	}
	private void makeFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("data/icon.jpg");
		setIconImage(img);
		setTitle("지뢰찾기");
		setLocationRelativeTo(null);
	}
	private void makeMenuBar() {
		JMenuBar mb = new JMenuBar();
		
		JMenuItem[] item = new JMenuItem[9];
		item[0] = new JMenuItem("새 게임");
		item[1] = new JMenuItem("초급");
		item[2] = new JMenuItem("중급");
		item[3] = new JMenuItem("고급");
		item[4] = new JMenuItem("사용자 지정");
		item[5] = new JCheckBoxMenuItem("소리", true);
		item[6] = new JMenuItem("최고기록");
		
		item[7] = new JMenuItem("게임 방법");
		item[8] = new JMenuItem("만든이");
		
		JMenu menu = new JMenu("게임");
		menu.add(item[0]);
		menu.addSeparator();
		menu.add(item[1]);
		menu.add(item[2]);
		menu.add(item[3]);
		menu.add(item[4]);
		menu.addSeparator();
		menu.add(item[5]);
		menu.addSeparator();
		menu.add(item[6]);
		
		JMenu menu2 = new JMenu("도움말");
		menu2.add(item[7]);
		menu2.add(item[8]);
		
		menuBarMouseAction(item);
		
		mb.add(menu);
		mb.add(menu2);
		mb.add(new JMenu("온라인"));
		setJMenuBar(mb);
	}
	private void menuBarMouseAction(JMenuItem[] item) {
		item[0].setActionCommand("reset");
		item[0].addActionListener(bcl);
		item[1].setActionCommand("beginner");
		item[1].addActionListener(bcl);
		item[2].setActionCommand("intermediate");
		item[2].addActionListener(bcl);
		item[3].setActionCommand("expert");
		item[3].addActionListener(bcl);
		item[4].setActionCommand("custom");
		item[4].addActionListener(bcl);
		item[5].setActionCommand("sound");
		item[5].addActionListener(bcl);
		item[6].setActionCommand("best");
		item[6].addActionListener(bcl);
		
		item[7].setActionCommand("how");
		item[7].addActionListener(bcl);
		item[8].setActionCommand("maker");
		item[8].addActionListener(bcl);
	}
	private void makeBoard(int x, int y) {
		GridBagConstraints c = new GridBagConstraints();
		GridBagLayout grid = new GridBagLayout();
		GridBagLayout grid2 = new GridBagLayout();
		
		JPanel mp = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		
		mp.setLayout(grid);
		
		p1.setLayout(new BorderLayout());
		p2.setLayout(new GridLayout(y, x));
		p3.setLayout(grid2);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		
		JButton bLeft = new JButton("1");
		JButton resetButton = new JButton(new ImageIcon("data/yes.png"));
		JButton bRight = new JButton("3");
		bLeft.setPreferredSize(new Dimension(50, 30));
		resetButton.setPreferredSize(new Dimension(30, 30));
		bRight.setPreferredSize(new Dimension(50, 30));
		
		resetButton.setActionCommand("reset");
		resetButton.addActionListener(bcl);
		p3.add(resetButton);
		
		p1.add("West", bLeft);
		p1.add("Center", p3);
		p1.add("East", bRight);
		
		addCom(mp, c, grid, p1, 0, 0, 1, 1);
		
		button = new JButton[y][x];
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				button[i][j] = new JButton();
				button[i][j].setPreferredSize(new Dimension(20, 20));
				p2.add(button[i][j]);
			}
		}
		
		addCom(mp, c, grid, p2, 0, 1, 1, 4);
		
		Container con = getContentPane();
		
		con.add(mp);
	}
	private void addCom(JPanel mp, GridBagConstraints c, GridBagLayout grid, Component com, int x, int y, int w, int h) {
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = w;
		c.gridheight = h;
		grid.setConstraints(com, c);
		mp.add(com);
	}
	
	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
			if (command.equals("reset")) {
				dispose();
			}
			else if (command.equals("beginner")) {
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter("data/size.txt"));
					
					out.write("9");
					out.newLine();
					out.write("9");
					out.newLine();
					out.write("10");
					out.newLine();
					
					out.close();
				}
				catch (IOException ex) {
					System.exit(1);
				}
				
				dispose();
			}
			else if (command.equals("intermediate")) {
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter("data/size.txt"));
					
					out.write("16");
					out.newLine();
					out.write("16");
					out.newLine();
					out.write("40");
					out.newLine();
					
					out.close();
				}
				catch (IOException ex) {
					System.exit(1);
				}
				
				dispose();
			}
			else if (command.equals("expert")) {
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter("data/size.txt"));
					
					out.write("30");
					out.newLine();
					out.write("16");
					out.newLine();
					out.write("99");
					out.newLine();
					
					out.close();
				}
				catch (IOException ex) {
					System.exit(1);
				}
				
				dispose();
			}
			else if (command.equals("custom")) {
				cf = new CustomFrame();
				cf.addWindowListener(new WinEvent());
			}
			else if (command.equals("sound")) {
				
			}
			else if (command.equals("best")) {
				new BestFrame();
			}
			else if (command.equals("how")) {
				new HowToPlayFrame();
			}
			else if (command.equals("maker")) {
				new InfoFrame();
			}
		}
	}
	
	private class WinEvent implements WindowListener {
		@Override
		public void windowClosed(WindowEvent e) {
			if (cf.getX() != -1) {
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter("data/size.txt"));
					
					out.write(Integer.toString(cf.getX()));
					out.newLine();
					out.write(Integer.toString(cf.getY()));
					out.newLine();
					out.write(Integer.toString(cf.getMine()));
					out.newLine();
					
					out.close();
				}
				catch (IOException ex) {
					System.exit(1);
				}
			}
			dispose();
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			dispose();
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