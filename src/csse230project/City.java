package csse230project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Defines a city object Has a name, a list of neighbors, a list of attractions,
 * x and y coordinates, and an interest level
 */
public class City {
	private String name;
	private ArrayList<Edge> neighbors;
	private ArrayList<Attraction> pointsOfInterest;
	private int xCoord, yCoord;
	private Integer interestLevel;
	private Boolean isSorted;
	private Edge predecessor = null;
	private int totalDistance;

	/**
	 * Default constructor for file i/o
	 */
	public City() {
		this.neighbors = new ArrayList<>();
		this.pointsOfInterest = new ArrayList<>();
		isSorted = true;
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
		isSorted = true;
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
		isSorted = true;
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
	public ArrayList<Edge> getNeighbors()
	{
		return this.neighbors;
	}
	/**
	 * Getter for neighbors
	 */
	public ArrayList<Edge> getNeighbors(Comparator<Edge> c) {
		if (!isSorted)
			this.SortNeighbors(c);
		return this.neighbors;
	}

	public void SortNeighbors(Comparator<Edge> c) {
		this.neighbors.sort(c);
		isSorted = true;
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
		this.isSorted = false;
	}

	/**
	 * Returns true if point is contained within city bounds
	 */
	public boolean contains(int x, int y) {
		if ((x > this.xCoord - 10) && (x < this.xCoord + 10)
				&& (y > this.yCoord - 10) && (y < this.yCoord + 10)) {
			return true;
		}
		return false;
	}
	
	public EdgeIterator getEdgeIterator() {
		return new EdgeIterator(this);
	}
	
	public TimeIterator getTimeIterator() {
		return new TimeIterator(this);
	}

	public class edgeComparator implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			if ((o1.getPathDistance()+o1.getGoalDistance()) > (o2.getPathDistance()+o2.getGoalDistance())) {
				return 1;
			} else if ((o1.getPathDistance()+o1.getGoalDistance()) < (o2.getPathDistance()+o2.getGoalDistance())) {
				return -1;
			} else {
				return 0;
			}
		}

	}
	public class timeComparator implements Comparator<Edge>
	{
		@Override
		public int compare(Edge o1, Edge o2) {
			if ((o1.getPathTime()+o1.getGoalDistance()) > (o2.getPathTime()+o2.getGoalDistance())) {
				return 1;
			} else if ((o1.getPathTime()+o1.getGoalDistance()) < (o2.getPathTime()+o2.getGoalDistance())) {
				return -1;
			} else {
				return 0;
			}
		}
	}


	public class EdgeIterator implements Iterator<Edge> {
		City currentCity;
		edgeComparator e= new edgeComparator();
		public EdgeIterator(City c) {
			currentCity = c;
		}

		int currentIndex = 0;

		@Override
		public boolean hasNext() {
			if (currentCity.neighbors.size() > currentIndex) {
				return true;
			}
			return false;
		}

		@Override
		public Edge next() {
			if (!isSorted)
				currentCity.SortNeighbors(e);
			Edge e = currentCity.neighbors.get(currentIndex);
			currentIndex++;
			return e;
		}

	}
	public class TimeIterator implements Iterator<Edge> {
		City currentCity;
		timeComparator c = new timeComparator();
		public TimeIterator(City c) {
			currentCity = c;
		}

		int currentIndex = 0;

		@Override
		public boolean hasNext() {
			if (currentCity.neighbors.size() > currentIndex) {
				return true;
			}
			return false;
		}

		@Override
		public Edge next() {
			if (!isSorted)
				currentCity.SortNeighbors(c);
			Edge e = currentCity.neighbors.get(currentIndex);
			currentIndex++;
			return e;
		}

	}

	@Override
	public String toString() {
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
		s += "\n";
		s += "POI: ";
		for (int i = 0; i < this.pointsOfInterest.size(); i++) {
			s += this.pointsOfInterest.get(i).getName();
			if (i != this.pointsOfInterest.size() - 1) {
				s += ", ";
			}
		}
		s += "\n";
		return s;
	}

	public void setPredecessor(Edge e) {
		this.predecessor = e;
	}

	public Edge getPredecessor() {
		return this.predecessor;
	}

	public int getTotalDistance() {
		return this.totalDistance;
	}
	
	@SuppressWarnings("null")
	public void clearPredecessors()
	{
		Edge previous=this.predecessor;
		Edge next=previous;
		while(next!=null)
		{
			previous=next;
			next.getCity2().predecessor=null;
			next.setGoalDistance(0);
			next=previous.getCity1().predecessor;
			
			
			
		}
	}

	public void setTotalDistance(int totalDistance) {
		this.totalDistance = totalDistance;
	}

}

