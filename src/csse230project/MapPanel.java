package csse230project;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JComponent;

public class MapPanel extends JComponent implements MouseListener{
	CityStructure map;
	City currentCity = null;
	InfoPanel infoPanel;
	ArrayList<City> clickedCities;
	
	public MapPanel(){
		this.setPreferredSize(new Dimension(750, 1000));
		this.addMouseListener(this);
		clickedCities = new ArrayList<City>();
	}
	
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;
	    try {
			this.map(g2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void map(Graphics2D g2) throws Exception{
		map = WriteDomain.read("usdomain.xml");
		Iterator<Entry<String, City>> cityPlace = map.getCityMap().entrySet().iterator();
		while(cityPlace.hasNext()){
			double citySize = 10;
			g2.setStroke(new BasicStroke(1.0f)); 
			g2.setColor(Color.BLACK);
			City city = cityPlace.next().getValue();
			Ellipse2D.Double cityShape = new Ellipse2D.Double(city.getXCoord(), city.getYCoord(), citySize, citySize);
			Iterator<Edge> cityNeighbours = city.getNeighbors().iterator();
			while(cityNeighbours.hasNext()){
				Edge edge = cityNeighbours.next();
//				double xsquare = Math.abs((Math.pow((edge.getCity1().getXCoord())- edge.getCity2().getXCoord(), 2)));
//				double ysquare = Math.abs((Math.pow((edge.getCity1().getYCoord())- edge.getCity2().getYCoord(), 2)));
//				double distance = Math.sqrt(xsquare + ysquare);
				Point2D.Double city1point = new Point2D.Double(edge.getCity1().getXCoord() + citySize/2, 
						edge.getCity1().getYCoord() + citySize/2);
				Point2D.Double city2point = new Point2D.Double(edge.getCity2().getXCoord() + citySize/2, 
						edge.getCity2().getYCoord() + citySize/2);
				Line2D.Double connecter = new Line2D.Double(city1point, city2point);
				g2.draw(connecter);
			}
			
			g2.draw(cityShape);
			g2.fill(cityShape);
		}
	}
	
	public City getCurrentCity(){
		return currentCity;
	}


	public CityStructure getMap(){
		return map;
	}
	
	public void setCurrentCity(City c){
		this.currentCity = c;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
        for (City c : this.getMap().getCityInterestList()) {
            if (c.contains(e.getX(),e.getY())) {
            	clickedCities.add(c);
            	this.setCurrentCity(c);
            	if(MainFrame.infoPanel.getCurrentCard().equals("pointsOfInterest")){
	            	try {
						MainFrame.infoPanel.createPointsOfInterest(MainFrame.infoPanel.getCardPointsOfInterest());
						clickedCities.clear();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}
            	else if(MainFrame.infoPanel.getCurrentCard().equals("calculateRoute")){
            		CityStructure struct;
					try {
						struct = WriteDomain.read("usdomain.xml");
	            		struct.calculateRoute(clickedCities);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            		clickedCities.clear();
            	}
                   System.out.println("mouse clicked on " + c.getName());
            }
     }

	}
	
	public ArrayList<City> getClickedCities(){
		return clickedCities;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
//=======
//package csse230project;
//
//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.geom.Ellipse2D;
//import java.awt.geom.Line2D;
//import java.awt.geom.Point2D;
//import java.util.Iterator;
//import java.util.Map.Entry;
//
//import javax.swing.JComponent;
//
//public class MapPanel extends JComponent implements MouseListener{
//	CityStructure map;
//	
//	public MapPanel(){
//		this.setPreferredSize(new Dimension(750, 1000));
//		this.addMouseListener(this);
//	}
//	
//	@Override
//	public void paintComponent (Graphics g) {
//		super.paintComponent(g);
//	    Graphics2D g2 = (Graphics2D) g;
//	    try {
//			this.map(g2);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void map(Graphics2D g2) throws Exception{
//		map = WriteDomain.read("usdomain.xml");
//		Iterator<Entry<String, City>> cityPlace = map.getCityMap().entrySet().iterator();
//		while(cityPlace.hasNext()){
//			double citySize = 10;
//			g2.setStroke(new BasicStroke(1.0f)); 
//			g2.setColor(Color.BLACK);
//			City city = cityPlace.next().getValue();
//			Ellipse2D.Double cityShape = new Ellipse2D.Double(city.getXCoord(), city.getYCoord(), citySize, citySize);
//			Iterator<Edge> cityNeighbours = city.getNeighbors().iterator();
//			while(cityNeighbours.hasNext()){
//				Edge edge = cityNeighbours.next();
////				double xsquare = Math.abs((Math.pow((edge.getCity1().getXCoord())- edge.getCity2().getXCoord(), 2)));
////				double ysquare = Math.abs((Math.pow((edge.getCity1().getYCoord())- edge.getCity2().getYCoord(), 2)));
////				double distance = Math.sqrt(xsquare + ysquare);
//				Point2D.Double city1point = new Point2D.Double(edge.getCity1().getXCoord() + citySize/2, 
//						edge.getCity1().getYCoord() + citySize/2);
//				Point2D.Double city2point = new Point2D.Double(edge.getCity2().getXCoord() + citySize/2, 
//						edge.getCity2().getYCoord() + citySize/2);
//				Line2D.Double connecter = new Line2D.Double(city1point, city2point);
//				g2.draw(connecter);
//			}
//			
//			g2.draw(cityShape);
//			g2.fill(cityShape);
//		}
//	}
//
//	@Override
//	public void mouseClicked(MouseEvent e) {
//        for (City c : this.map.getCityInterestList()) {
//            if (c.contains(e.getX(),e.getY())) {
//                System.out.println("mouse clicked on " + c.getName());
//            }
//        }
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseExited(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mousePressed(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//}
