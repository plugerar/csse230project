package csse230project;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JPanel;



public class ColoredPath extends JPanel{
	private JPanel map;
	
	public ColoredPath(){
		this.setPreferredSize(new Dimension(750, 1000));
	}
	
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;
	    try {
			this.coloredPath(g2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void coloredPath(Graphics2D g2){
		ArrayList<Edge> path = MainFrame.infoPanel.getPath();
		Iterator<Edge> i = path.iterator();
		g2.setStroke(new BasicStroke(1.0f)); 
		g2.setColor(Color.RED);
		while(i.hasNext()){
			Edge edge =  i.next();
			Point2D.Double city1point = new Point2D.Double(edge.getCity1().getXCoord() + 10/2, 
					edge.getCity1().getYCoord() + 10/2);
			Point2D.Double city2point = new Point2D.Double(edge.getCity2().getXCoord() + 10/2, 
					edge.getCity2().getYCoord() + 10/2);
			Line2D.Double connecter = new Line2D.Double(city1point, city2point);
//			if(MainFrame.infoPanel.getBooValue().equals(true)){
//				if(clickedCities.contains(edge.getCity1()) && clickedCities.contains(edge.getCity2())){
//					g2.setColor(Color.RED);
//				}
//			}
			g2.draw(connecter);
			
		}
	}
}
