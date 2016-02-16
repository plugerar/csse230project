package csse230project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	public static MapPanel mapPanel;
	public static InfoPanel infoPanel;
	
	public MainFrame() throws Exception{
		Dimension size = new Dimension(1800, 1000);
		this.setTitle("Map");
		this.setSize(size);
		MapLayeredPane map = new MapLayeredPane();
		this.mapPanel = map.getMapPanel();
		this.infoPanel = new InfoPanel(mapPanel);
		this.add(infoPanel, BorderLayout.EAST);
	    this.add(map, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
