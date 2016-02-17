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
	String prevCard;
	
	public MapPanel(){
		this.setPreferredSize(new Dimension(750, 1000));
		this.addMouseListener(this);
		this.clickedCities = new ArrayList<>();
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
		this.map = MainFrame.struct;
		Iterator<Entry<String, City>> cityPlace = this.map.getCityMap().entrySet().iterator();
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
			g2.draw(connecter);
		}
	}
	
	public City getCurrentCity(){
		return this.currentCity;
	}


	public CityStructure getMap(){
		return this.map;
	}
	
	public void setCurrentCity(City c){
		this.currentCity = c;
	}
	
	public void setClickedCities(ArrayList<City> cities){
		this.clickedCities = cities;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
        for (City c : this.getMap().getCityInterestList()) {
            if (c.contains(e.getX(),e.getY())) {
            	this.clickedCities.add(c);        		
            	if(this.prevCard!=null && !this.prevCard.equals(MainFrame.infoPanel.getCurrentCard())){
            		City last = this.clickedCities.get(this.clickedCities.size()-1);
            		this.clickedCities = new ArrayList<City>();
            		this.clickedCities.add(last);
            	}
            	if(MainFrame.infoPanel.getCurrentCard() != null) {
	            	if(MainFrame.infoPanel.getCurrentCard().equals("Points Of Interest")){
	            		this.prevCard = "Points Of Interest";
		            	try {
							MainFrame.infoPanel.createPointsOfInterest(MainFrame.infoPanel.getCardPointsOfInterest());
						} catch (Exception e1) {
							// we don't care if it breaks	
						}
	            	} else if(MainFrame.infoPanel.getCurrentCard().equals("Calculate Route")){
	            		this.prevCard = "Calculate Route";
						try {
							MainFrame.infoPanel.createCalculateRoute(MainFrame.infoPanel.getCardCalulcateRoute());
						} catch (Exception e1) {
							// we don't care if it breaks	
						}
	            	} else if(MainFrame.infoPanel.getCurrentCard().equals("City Descriptions")){
	            		this.prevCard = "City Descriptions";
						try {
							System.out.println(MainFrame.infoPanel.getCurrentCard());
							MainFrame.infoPanel.createCityDescriptions(MainFrame.infoPanel.getCardCityDescriptions());
						} catch (Exception e1) {
							// we don't care if it breaks	
						}

	                   System.out.println("mouse clicked on " + c.getName());
	            	} else if(MainFrame.infoPanel.getCurrentCard().equals("Trip Planner")){
						try {
							System.out.println(MainFrame.infoPanel.getCurrentCard());
							MainFrame.infoPanel.createTripPlanner(MainFrame.infoPanel.getCardTripPlanner());
						} catch (Exception e1) {
							// we don't care if it breaks	
						}
					}
				}
			}
		}
	}
	
	public ArrayList<City> getClickedCities(){
		return this.clickedCities;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// unnecessary		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// unnecessary		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// unnecessary		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// unnecessary		
	}

}
