package csse230project;

/**
 * Defines an edge (a connection between cities)
 * Has two cities, time and distance information between them
 */		
public class Edge {
	private City city1;
	private City city2;
	private int time;
	private int distance;
	
	/**
	 * Default constructor for file i/o
	 */
	public Edge() {
	}

	/**
	 * Constructor with both cities, time, and distance
	 */		
	public Edge(City city1, City city2, int time, int distance) {
		this.city1 = city1;
		this.city2 = city2;
		this.time = time;
		this.distance = distance;
	}

	/**
	 * Getter for city 1
	 */		
	public City getCity1() {
		return this.city1;
	}

	/**
	 * Setter for city 1
	 */	
	public void setCity1(City city1) {
		this.city1 = city1;
	}
	
	/**
	 * Getter for city 2
	 */	
	public City getCity2() {
		return this.city2;
	}
		
	/**
	 * Setter for city 2
	 */	
	public void setCity2(City city2) {
		this.city2 = city2;
	}
	
	/**
	 * Getter for time
	 */	
	public int getTime() {
		return this.time;
	}
	
	/**
	 * Setter for time
	 */	
	public void setTime(int time) {
		this.time = time;
	}
	
	/**
	 * Getter for distance
	 */		
	public int getDistance() {
		return this.distance;
	}
	
	/**
	 * Setter for distance
	 */	
	public void setDistance(int distance) {
		this.distance = distance;
	}
		
	@Override
	public String toString(){
		String s = "";
		s += "City1: " + this.city1 + "\n";
		s += "City2: " + this.city2 + "\n";
		s += "Time: " + this.time + "\n";
		s += "Distance: " + this.distance + "\n";
		return s;
	}
}