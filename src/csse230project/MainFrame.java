package csse230project;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Main frame to place the map panel and info panel on
 */
public class MainFrame extends JFrame{
	public static MapLayeredPane mapLayeredPane;
	public static MapPanel mapPanel;
	public static InfoPanel infoPanel;
	public static CityStructure struct; 
	
	/**
	 * Constructs a main frame to put the map panel and info panel onto
	 */
	public MainFrame() throws Exception{
		struct = WriteDomain.read("usdomain.xml");
		Dimension size = new Dimension(1800, 1000);
		this.setTitle("Map");
		this.setSize(size);
		mapLayeredPane = new MapLayeredPane();
		mapPanel = mapLayeredPane.getMapPanel();
		infoPanel = new InfoPanel(mapPanel);
		this.add(infoPanel, BorderLayout.EAST);
	    this.add(mapLayeredPane, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
