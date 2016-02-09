package csse230project;

import java.util.ArrayList;

public class City {
	private String name;
	private ArrayList<Edge> neighbors;
	private ArrayList<Attraction> pointsOfInterest;
	private int xCoord, yCoord;
	private Integer interestLevel;
	
	public City(String name) {
		this.name = name;
		this.xCoord = 0;
		this.yCoord = 0;
		this.neighbors = new ArrayList<>();
		this.pointsOfInterest = new ArrayList<>();
		this.interestLevel = new Integer(0);	
		}
	
	public City(String name, int xCoord, int yCoord) {
		this.name = name;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.neighbors = new ArrayList<>();
		this.pointsOfInterest = new ArrayList<>();
		this.interestLevel = new Integer(0);	
		}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Edge> getNeighbors() {
		return this.neighbors;
	}

	public void addNeighbor(Edge neighbor) {
		this.neighbors.add(neighbor);
	}
	
	public int getXCoord() {
		return this.xCoord;
	}
	
	public int getYCoord() {
		return this.yCoord;
	}

	public ArrayList<Attraction> getPointsOfInterest() {
		return this.pointsOfInterest;
	}

	public void setPointsOfInterest(ArrayList<Attraction> pointsOfInterest) {
		this.pointsOfInterest = pointsOfInterest;
		for (int i = 0; i < pointsOfInterest.size(); i++) {
			this.interestLevel += pointsOfInterest.get(i).getInterestLevel();
		}
	}
	
	public void setPointOfInterest(Attraction pointOfInterest) {
		this.pointsOfInterest.add(pointOfInterest);
		this.interestLevel += pointOfInterest.getInterestLevel();
	}

	public Integer getInterestLevel() {
		return this.interestLevel;
	}
	
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