import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
   private Image image;
   
   public ImagePanel(Image image) {
      this.image = image;
   }
   
   public void paintComponent(Graphics g) {
      Dimension d = getSize();
      g.drawImage(this.image,  0,  0, d.width, d.height, this);
   }
}