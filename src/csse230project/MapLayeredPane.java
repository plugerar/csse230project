package csse230project;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 * Layers the map and the cities/edges on top of each other
 */
public class MapLayeredPane extends JLayeredPane{
	MapPanel mapPanel;

	public MapLayeredPane() throws Exception{
		this.setSize(new Dimension(1700, 1000));
		JLabel label = mappng();
	    this.add(label, new Integer(0));

	    this.mapPanel = new MapPanel();
	    this.add(this.mapPanel, new Integer(1));

	    label.setBounds(0, 0, 1700, 1000);
	    this.mapPanel.setBounds(195, 95, 1700, 1000);
	    this.setVisible(true);
	}
	
	private JLabel mappng() throws IOException{
		String path = "USbackground800.png";
	    File file = new File(path);
	    BufferedImage image = ImageIO.read(file);
		ImageIcon photo = new ImageIcon(image);
		JLabel label = new JLabel(photo);
		return label;
	}
	
	public MapPanel getMapPanel(){
		return this.mapPanel;
	}
}