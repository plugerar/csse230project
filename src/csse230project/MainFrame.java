package csse230project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	public static MapLayeredPane mapLayeredPane;
	public static MapPanel mapPanel;
	public static InfoPanel infoPanel;
	public static CityStructure struct; 
	
	public MainFrame() throws Exception{
		struct = WriteDomain.read("usdomain.xml");
		Dimension size = new Dimension(1800, 1000);
		this.setTitle("Map");
		this.setSize(size);
		mapLayeredPane = new MapLayeredPane();
		mapPanel = mapLayeredPane.getMapPanel();
		this.infoPanel = new InfoPanel(mapPanel);
		this.add(infoPanel, BorderLayout.EAST);
	    this.add(mapLayeredPane, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
