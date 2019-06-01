import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.*;

public class GameFrame extends JFrame {	//게임을 하는 메인프레임
	private JButton[][] button;
	private CustomFrame cf;
	private ButtonClickListener bcl;
	private JButton resetButton;
	
	private int[][] xy;
	private boolean[][] check;
	private int clickedCnt;
	private final int mineCnt;
	
	private StopWatch stopwatch;
	private TimeLabel tl;
	
	private final int x;
	private final int y;
	private final int difficulty;
	
	private AudioInputStream ais;
	private Clip clip;
	private boolean soundEnable;
	
	public GameFrame(int x, int y, int mine, int di, int sound) {
		bcl = new ButtonClickListener();
		check = new boolean[y][x];
		clickedCnt = 0;
		mineCnt = mine;
		
		this.x = x;
		this.y = y;
		difficulty = di;
		
		try {
			ais = AudioSystem.getAudioInputStream(new File("data/lose.wav"));
			clip = AudioSystem.getClip();
			clip.open(ais);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		if (sound == 1) {
			soundEnable = true;
		}
		else {
			soundEnable = false;
		}
		
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				check[i][j] = false;
			}
		}
		
		makeMap();
		makeFrame();
		makeMenuBar();
		makeBoard();
		pack();
		setResizable(false);
		setVisible(true);
	}
	private void makeMap() {
		//initializing
		xy = new int[y][x];
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				xy[i][j] = 0;
			}
		}
		//shuffle
		int[][] lst = new int[y * x][2];
		Random rand = new Random();
		int cnt = 0;
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				lst[cnt][0] = i;
				lst[cnt][1] = j;
				cnt++;
			}
		}
		for(int i = 0; i < y * x; i++) {
			int idx = rand.nextInt(y * x);
			int tmp = lst[i][0];
			lst[i][0] = lst[idx][0];
			lst[idx][0] = tmp;
			tmp = lst[i][1];
			lst[i][1] = lst[idx][1];
			lst[idx][1] = tmp;
		}
		for(int i = 0; i < mineCnt; i++) {
			xy[lst[i][0]][lst[i][1]] = -1;
		}
		//setting map
		int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
		int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
		for(int y = 0; y < this.y; y++) {
			for(int x = 0; x < this.x; x++) {
				if(xy[y][x] == -1) {
					continue;
				}
				for(int i = 0; i < 8; i++) {
					if(y + dy[i] < 0 || y + dy[i] >= this.y || x + dx[i] < 0 || x + dx[i] >= this.x) {
						continue;
					}
					if(xy[y + dy[i]][x + dx[i]] == -1) {
						xy[y][x]++;
					}
				}
			}
		}		
	}
	private void makeFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("data/icon.jpg"); //프레임 상단에 아이콘 넣기
		setIconImage(img);
		setTitle("지뢰찾기");
		setLocationRelativeTo(null); //프레임의 초기위치를 화면 중앙으로 한다.
	}
	private void makeMenuBar() { //메뉴바 만들기
		JMenuBar mb = new JMenuBar();
		
		JMenuItem[] item = new JMenuItem[9];
		item[0] = new JMenuItem("새 게임");
		item[1] = new JMenuItem("초급");
		item[2] = new JMenuItem("중급");
		item[3] = new JMenuItem("고급");
		item[4] = new JMenuItem("사용자 지정");
		item[5] = new JCheckBoxMenuItem("소리", soundEnable);
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
	private void menuBarMouseAction(JMenuItem[] item) { //메뉴바의 각 버튼에 커맨드 입력
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
	private void makeBoard() {
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
		
		c.fill = GridBagConstraints.BOTH; //그리드백레이아웃 크기가 맞지 않을 경우 가로세로 확장
		c.insets = new Insets(5, 5, 5, 5); //그리드백레이아웃 격자 간격조정
		
		JButton bLeft = new JButton("1");
		resetButton = new JButton(new ImageIcon("data/yes.png"));
		
		stopwatch = new StopWatch(soundEnable);
		tl = new TimeLabel(stopwatch);
		stopwatch.start();
		tl.start();
		JLabel time = tl.getLabel();
		Font f1 = new Font("돋움", Font.BOLD, 15);
		time.setFont(f1);

		bLeft.setPreferredSize(new Dimension(50, 30));
		resetButton.setPreferredSize(new Dimension(30, 30));
		time.setPreferredSize(new Dimension(50,30));
		time.setHorizontalAlignment(JLabel.RIGHT);
		
		resetButton.setActionCommand("reset");
		resetButton.addActionListener(bcl);
		p3.add(resetButton);
		
		p1.add("West", bLeft);
		p1.add("Center", p3);
		p1.add("East",time);
		
		addCom(mp, c, grid, p1, 0, 0, 1, 1);
		
		button = new JButton[y][x];
		ImageIcon icon;
		ImagePanel ipanel;
		
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				button[i][j] = new JButton();
				JButton b = button[i][j];
				b.setIcon(new ImageIcon("data/button.png"));
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						checkMine(e.getSource());
						b.setEnabled(false);
						b.setVisible(false);
					}
				});
				
				icon = new ImageIcon("data/"+Integer.toString(xy[i][j])+".png");
				ipanel = new ImagePanel(icon.getImage());
				ipanel.setPreferredSize(new Dimension(20,20));
				ipanel.setLayout(new GridLayout(1,1));
				ipanel.add(button[i][j]);
				p2.add(ipanel);
			}
		}
		
		addCom(mp, c, grid, p2, 0, 1, 1, 4);
		
		Container con = getContentPane();
		
		con.add(mp);
	}
	private void addCom(JPanel mp, GridBagConstraints c, GridBagLayout grid, Component com, int x, int y, int w, int h) { //그리드백레이아웃 관련 함수
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = w;
		c.gridheight = h;
		grid.setConstraints(com, c);
		mp.add(com);
	}
	private void checkMine(Object o)
	{
		int y = -1;
		int x = -1;
		
		clickedCnt++;
		if(clickedCnt == 1) {
			stopwatch.On();
		}
		
		for(int i = 0; i < this.y; i++) {
			for(int j = 0; j < this.x; j++) {
				if(o.equals(button[i][j])) {
					y = i;
					x = j;
					break;
				}
			}
			if(y != -1) {
				break;
			}
		}
		
		if(xy[y][x] == -1) {
			resetButton.setIcon(new ImageIcon("data/no.png"));
			
			if (soundEnable) {
				try {
					ais = AudioSystem.getAudioInputStream(new File("data/lose.wav"));
					clip = AudioSystem.getClip();
					clip.open(ais);
					clip.start();
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
			for(int i = 0; i < this.y; i++) {
				for(int j = 0; j < this.x; j++) {
					button[i][j].setEnabled(false);
					
					if(xy[i][j] != -1) {
						button[i][j].setIcon(new ImageIcon("data/button.png"));
						button[i][j].setDisabledIcon(new ImageIcon("data/button.png"));
						
						continue;
					}
					
					button[i][j].setVisible(false);
				}
			}
			stopwatch.Off();
			return;
		}
		
		int[][] queue = new int[this.y * this.x + 5][2];
		int f = -1;
		int r = 0;
		
		queue[0][0] = y;
		queue[0][1] = x;
		
		check[y][x] = true;
		
		int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
		int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
		
		if(xy[y][x] == 0) {
			do {
				clickedCnt++;
				f++;
				
				int i = queue[f][0];
				int j = queue[f][1];
				
				button[i][j].setEnabled(false);
				button[i][j].setVisible(false);
				
				if(xy[i][j] == 0) {
					for(int k = 0; k < 8; k++) {
						if(i + dy[k] < 0 || i + dy[k] >= this.y || j + dx[k] < 0 || j + dx[k] >= this.x) {
							continue;
						}
						
						if(check[i + dy[k]][j + dx[k]] == true) {
							continue;
						}
						
						r++;
						
						queue[r][0] = i + dy[k];
						queue[r][1] = j + dx[k];
						check[i + dy[k]][j + dx[k]] = true;
					}
				}
			}while(f < r);
			
			clickedCnt--;
		}
		
		//game clear
		if(this.y * this.x - clickedCnt == mineCnt) {
			resetButton.setIcon(new ImageIcon("data/yeah.png"));
			stopwatch.Off();
			
			if (soundEnable) {
				try {
					ais = AudioSystem.getAudioInputStream(new File("data/win.wav"));
					clip = AudioSystem.getClip();
					clip.open(ais);
					clip.start();
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
			for(int i = 0; i < this.y; i++) {
				for(int j = 0; j < this.x; j++) {
					if(xy[i][j] == -1) {
						button[i][j].setIcon(new ImageIcon("data/flag.png"));
						button[i][j].setDisabledIcon(new ImageIcon("data/flag.png"));
						button[i][j].setEnabled(false);
					}
				}
			}
			
			if (difficulty > 0 && difficulty < 4) {
				String[] str = new String[12];
				int time = stopwatch.getSec();
				
				try {
					File file = new File("data/best.txt");    //저장되어있는 기록들 불러오기
					Scanner scn = new Scanner(file);
					
					for (int i = 0; i < 12; i++) {
						str[i] = scn.nextLine();
					}
					
					scn.close();
				}
				catch (FileNotFoundException e) {
					System.exit(1);
				}
				
				if (difficulty == 1 && Integer.parseInt(str[2]) > time) {
					new GetBestNameFrame(time, difficulty);
				}
				else if (difficulty == 2 && Integer.parseInt(str[6]) > time) {
					new GetBestNameFrame(time, difficulty);
				}
				else if (difficulty == 3 && Integer.parseInt(str[10]) > time) {
					new GetBestNameFrame(time, difficulty);
				}
			}
		}
	}
	
	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
			if (command.equals("reset")) {
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter("data/size.txt"));
					
					out.write(Integer.toString(x));
					out.newLine();
					out.write(Integer.toString(y));
					out.newLine();
					out.write(Integer.toString(mineCnt));
					out.newLine();
					out.write(Integer.toString(difficulty));
					out.newLine();
					
					if (soundEnable) {
						out.write("1");
						out.newLine();
					}
					else {
						out.write("0");
						out.newLine();
					}
					
					out.close();
				}
				catch (IOException ex) {
					System.exit(1);
				}

				if (soundEnable && clip.isRunning()) {
					clip.stop();
				}

				stopwatch.Off();
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
					out.write("1");
					out.newLine();
					
					if (soundEnable) {
						out.write("1");
						out.newLine();
					}
					else {
						out.write("0");
						out.newLine();
					}
					
					out.close();
				}
				catch (IOException ex) {
					System.exit(1);
				}
				
				stopwatch.Off();
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
					out.write("2");
					out.newLine();
					
					if (soundEnable) {
						out.write("1");
						out.newLine();
					}
					else {
						out.write("0");
						out.newLine();
					}
					
					out.close();
				}
				catch (IOException ex) {
					System.exit(1);
				}
				
				stopwatch.Off();
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
					out.write("3");
					out.newLine();
					
					if (soundEnable) {
						out.write("1");
						out.newLine();
					}
					else {
						out.write("0");
						out.newLine();
					}
					
					out.close();
				}
				catch (IOException ex) {
					System.exit(1);
				}
				
				stopwatch.Off();
				dispose();
			}
			else if (command.equals("custom")) {
				cf = new CustomFrame();
				cf.addWindowListener(new WinEvent()); //사용자지정 프레임이 닫힐 때 이벤트 설정
			}
			else if (command.equals("sound")) {
				soundEnable = !soundEnable;
				stopwatch.setSound(soundEnable);
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
		public void windowClosed(WindowEvent e) { //창이 닫혔을 때
			if (cf.getX() != -1) {
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter("data/size.txt"));
					
					out.write(Integer.toString(cf.getX()));
					out.newLine();
					out.write(Integer.toString(cf.getY()));
					out.newLine();
					out.write(Integer.toString(cf.getMine()));
					out.newLine();
					out.write("4");
					out.newLine();
					
					if (soundEnable) {
						out.write("1");
						out.newLine();
					}
					else {
						out.write("0");
						out.newLine();
					}
					
					out.close();
				}
				catch (IOException ex) {
					System.exit(1);
				}
			}
			
			stopwatch.Off();
			dispose();
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// 내용 없음
		}

		@Override
		public void windowClosing(WindowEvent e) { //창닫기 버튼을 눌렀을 때
			stopwatch.Off();
			dispose();
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