import java.awt.*;
import javax.swing.*;

public class HowToPlayFrame extends JFrame { //게임방법 프레임
	public HowToPlayFrame() {
		makeFrame();
		
		JLabel l = new JLabel("<html>"
				+ "지뢰찾기는 제목은 지뢰찾기지만 지뢰를 누르면 터지니까 누르면 안되는 그런 게임입니다.<br><br>"
				+ "왼쪽 상단에 있는 숫자는 아직 못찾은 지뢰의 개수를 의미하고,<br>"
				+ "오른쪽 상단에 있는 숫자는 걸린 시간을 초단위로 알려줍니다.<br><br>"
				+ "좌클릭을 하면 상자가 열리고, 우클릭을 하면 열리지 않은 상자 위에 깃발이 표시됩니다.<br>"
				+ "지뢰인것 같은 곳에 깃발을 꽂으시면 되겠습니다.<br>"
				+ "상자를 열었을 때 바닥에 있는 숫자는 숫자가 쓰여있는 공간의 주변 8칸중에 있는 지뢰의 개수를 의미합니다.<br>"
				+ "예를들어, 숫자가 2가 나오면 주변 8칸 중에 지뢰가 두개가 있다는 것을 의미합니다.<br><br>"
				+ "그래서 이 숫자를 토대로 하여 지뢰인 곳은 깃발을 놓고, 지뢰가 아닌 곳은 좌클릭을 하여 열면 되겠습니다.<br>"
				+ "지뢰를 파서 터지면 게임은 지게 되고, 지뢰가 아닌 나머지 공간을 모두 열면 게임에서 승리하게 됩니다.<br><br>"
				+ "하다보면 무조건 찍어야 될 때가 나올 수 있는데 그건 그냥 찍으세요.<br>"
				+ "행운을 빕니다.</html>");
		
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		p.add(l);
		
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
		setTitle("게임방법");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width/3, screenSize.height/3);
	}
}
