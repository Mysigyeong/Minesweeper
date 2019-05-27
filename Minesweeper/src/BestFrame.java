import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BestFrame extends JFrame { //최고기록 프레임
	private JLabel[] l;
	
	public BestFrame() {
		makeFrame();
		
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		p.setLayout(new GridLayout(5, 3));
		
		String[] str = new String[12];
		l = new JLabel[9];
		
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
		
		for (int i = 0; i < 3; i++) {
			l[3 * i] = new JLabel(str[4 * i]);
			l[3 * i + 1] = new JLabel(str[4 * i + 1]);
			l[3 * i + 2] = new JLabel(str[4 * i + 2] + str[4 * i + 3]);
		}
		
		p.add(new JLabel("난이도"));
		p.add(new JLabel("이름"));
		p.add(new JLabel("시간"));
		for (int i = 0; i < 9; i++) {
			p.add(l[i]);
		}
		
		JButton button1 = new JButton("기록 지우기");
		button1.setActionCommand("delete");
		button1.addActionListener(new ButtonClickListener());
		
		p.add(new JLabel(""));
		p.add(button1);
		
		Container c = getContentPane();
		c.add(p);
		
		pack();
		setVisible(true);
		setResizable(false);
	}
	private void makeFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("data/icon.jpg"); //프레임창에 아이콘 넣기
		setIconImage(img);
		setTitle("최고기록");
		setLocationRelativeTo(null); //화면 중앙에 띄우기
	}
	
	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			String[] str = new String[12];
			
			if (command.equals("delete")) { //기록들 다시 초기화시키는 작업
				try {
					File file = new File("data/best.txt");
					Scanner scn = new Scanner(file);
					
					for (int i = 0; i < 12; i++) {
						str[i] = scn.nextLine();
					}
					
					for (int i = 0; i < 3; i++) {
						str[4 * i + 1] = "익명";
						str[4 * i + 2] = "999";
					}
					
					BufferedWriter out = new BufferedWriter(new FileWriter("data/best.txt"));
					
					for (int i = 0; i < 12; i++) {
						out.write(str[i]);
						out.newLine();
					}
					
					scn.close();
					out.close();
				}
				catch (FileNotFoundException ex) {
					System.exit(1);
				}
				catch (IOException ex2) {
					System.exit(1);
				}
				
				for (int i = 0; i < 3; i++) {
					l[3 * i].setText(str[4 * i]);
					l[3 * i + 1].setText(str[4 * i + 1]);
					l[3 * i + 2].setText(str[4 * i + 2] + str[4 * i + 3]);
				}
			}
		}
	}
}
