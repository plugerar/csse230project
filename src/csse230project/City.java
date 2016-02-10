package csse230project;

import java.util.ArrayList;

/**
 * Defines a city object
 * Has a name, a list of neighbors, a list of attractions,
 * x and y coordinates, and an interest level
 */
public class City {
	private String name;
	private ArrayList<Edge> neighbors;
	private ArrayList<Attraction> pointsOfInterest;
	private int xCoord, yCoord;
	private Integer interestLevel;
	
	/**
	 * Default constructor for file i/o
	 */
	public City() {
		this.neighbors = new ArrayList<>();
		this.pointsOfInterest = new ArrayList<>();
		}

	/**
	 * Constructor for city with a name at (0,0)
	 */
	public City(String name) {
		this.name = name;
		this.xCoord = 0;
		this.yCoord = 0;
		this.neighbors = new ArrayList<>();
		this.pointsOfInterest = new ArrayList<>();
		this.interestLevel = new Integer(0);	
		}

	/**
	 * Constructor for a city with a name at (xCoord, yCoord)
	 */
	public City(String name, int xCoord, int yCoord) {
		this.name = name;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.neighbors = new ArrayList<>();
		this.pointsOfInterest = new ArrayList<>();
		this.interestLevel = new Integer(0);	
		}

	/**
	 * Getter for name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter for name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for neighbors
	 */
	public ArrayList<Edge> getNeighbors() {
		return this.neighbors;
	}
	
	/**
	 * Setter for neighbors
	 */
	public void setNeighbors(ArrayList<Edge> neighbors) {
		this.neighbors = neighbors;
	}
	
	/**
	 * Getter for x coordinate
	 */		
	public int getXCoord() {
		return this.xCoord;
	}
	
	/**
	 * Setter for x coordinate
	 */
	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	
	/**
	 * Getter for y coordinate
	 */	
	public int getYCoord() {
		return this.yCoord;
	}
	
	/**
	 * Setter for y coordinate
	 */
	public void setYCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
	/**
	 * Getter for POI
	 */
	public ArrayList<Attraction> getPointsOfInterest() {
		return this.pointsOfInterest;
	}
	
	/**
	 * Setter for POI
	 */
	public void setPointsOfInterest(ArrayList<Attraction> pointsOfInterest) {
		this.pointsOfInterest = pointsOfInterest;
		for (int i = 0; i < pointsOfInterest.size(); i++) {
			this.interestLevel += pointsOfInterest.get(i).getInterestLevel();
		}
	}
	
	/**
	 * Setter for only one POI
	 */	
	public void setPointOfInterest(Attraction pointOfInterest) {
		this.pointsOfInterest.add(pointOfInterest);
		this.interestLevel += pointOfInterest.getInterestLevel();
	}

	/**
	 * Getter for interest level
	 */
	public Integer getInterestLevel() {
		return this.interestLevel;
	}

	/**
	 * Setter for interest level
	 */
	public void setInterestLevel(Integer interestLevel) {
		this.interestLevel = interestLevel;
	}
	
	/**
	 * Add a neighbor (edge) to list of neighbors
	 */
	public void addNeighbor(Edge neighbor) {
		this.neighbors.add(neighbor);
	}
		
	@Override
	public String toString(){
		String s = "";
		s += "\n";
		s += "City: " + this.name + "\n";
		s += "XCoord: " + this.xCoord + "\n";
		s += "YCoord: " + this.yCoord + "\n";
		s += "InterestLevel: " + this.interestLevel + "\n";
		s += "Neighbors: ";
		for (int i = 0; i < this.neighbors.size(); i++) {
			if (this.neighbors.get(i).getCity1().equals(this)) {
				s += this.neighbors.get(i).getCity2().getName();
			} else {
				s += this.neighbors.get(i).getCity1().getName();				
			}
			if (i != this.neighbors.size() - 1) {
				s += ", ";
			}
		}
		s+= "\n";
		s += "POI: ";
		for (int i = 0; i < this.pointsOfInterest.size(); i++) {
			s += this.pointsOfInterest.get(i).getName();
			if (i != this.pointsOfInterest.size() - 1) {
				s += ", ";
			}
		}
		s+= "\n";
		return s;
	}
	
}