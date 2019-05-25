import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameFrame extends JFrame {	
	private JButton[][] button;
	private int sizeX;
	private int sizeY;
	private int numOfMine;
	
	public GameFrame(int x, int y, int mine) {
		sizeX = x;
		sizeY = y;
		numOfMine = mine;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
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
		item[0].addActionListener(new ButtonClickListener());
		item[1].setActionCommand("beginner");
		item[1].addActionListener(new ButtonClickListener());
		item[2].setActionCommand("intermediate");
		item[2].addActionListener(new ButtonClickListener());
		item[3].setActionCommand("expert");
		item[3].addActionListener(new ButtonClickListener());
		item[4].setActionCommand("custom");
		item[4].addActionListener(new ButtonClickListener());
		item[5].setActionCommand("sound");
		item[5].addActionListener(new ButtonClickListener());
		item[6].setActionCommand("best");
		item[6].addActionListener(new ButtonClickListener());
		
		item[7].setActionCommand("how");
		item[7].addActionListener(new ButtonClickListener());
		item[8].setActionCommand("maker");
		item[8].addActionListener(new ButtonClickListener());
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
		p2.setLayout(new GridLayout(x, y));
		p3.setLayout(grid2);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		
		JButton bLeft = new JButton("1");
		JButton resetButton = new JButton("2");
		JButton bRight = new JButton("3");
		bLeft.setPreferredSize(new Dimension(50, 30));
		resetButton.setPreferredSize(new Dimension(30, 30));
		bRight.setPreferredSize(new Dimension(50, 30));
		
		p3.add(resetButton);
		
		p1.add("West", bLeft);
		p1.add("Center", p3);
		p1.add("East", bRight);
		
		addCom(mp, c, grid, p1, 0, 0, 1, 1);
		
		button = new JButton[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
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
				
			}
			else if (command.equals("beginner")) {
				
			}
			else if (command.equals("intermediate")) {
				
			}
			else if (command.equals("expert")) {
				
			}
			else if (command.equals("custom")) {
				new CustomFrame();
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
}