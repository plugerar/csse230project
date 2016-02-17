package csse230project;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JPanel;

public class Layer extends JPanel{
	CityStructure layer;
	
	public Layer(){
		this.setPreferredSize(new Dimension(750, 1000));
	}
	
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;
	    try {
			this.map(g2);
		} catch (Exception e) {
			// we don't care if it breaks
		}
	}
	
	public void map(Graphics2D g2) throws Exception{
		this.layer = MainFrame.struct;
		Iterator<Entry<String, City>> cityPlace = this.layer.getCityMap().entrySet().iterator();
		while(cityPlace.hasNext()){
			double citySize = 10;
			g2.setStroke(new BasicStroke(1.0f)); 
			g2.setColor(Color.BLACK);
			City city = cityPlace.next().getValue();
			Ellipse2D.Double cityShape = new Ellipse2D.Double(city.getXCoord(), city.getYCoord(), citySize, citySize);
			Iterator<Edge> cityNeighbours = city.getNeighbors().iterator();
			drawEdges(g2, cityNeighbours, citySize);
			g2.draw(cityShape);
			g2.fill(cityShape);
		}
	}
	
	public void drawEdges(Graphics2D g2, Iterator<Edge> cityNeighbours, double citySize){
		while(cityNeighbours.hasNext()){
			Edge edge = cityNeighbours.next();
			Point2D.Double city1point = new Point2D.Double(edge.getCity1().getXCoord() + citySize/2, 
					edge.getCity1().getYCoord() + citySize/2);
			Point2D.Double city2point = new Point2D.Double(edge.getCity2().getXCoord() + citySize/2, 
					edge.getCity2().getYCoord() + citySize/2);
			Line2D.Double connecter = new Line2D.Double(city1point, city2point);
//			if(MainFrame.infoPanel.getBooValue().equals(true)){
//				if(clickedCities.contains(edge.getCity1()) && clickedCities.contains(edge.getCity2())){
//					g2.setColor(Color.RED);
//				}
//			}
			g2.draw(connecter);
			//System.out.println("entered where I want");
		}
	}

}
