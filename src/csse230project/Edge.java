package csse230project;

/**
 * Defines an edge (a connection between cities)
 * Has two cities, time and distance information between them
 */		
public class Edge {
	private City city1;
	private City city2;
	private double time;
	private int distance;
	public Edge parent;
	public int pathDistance;
	private double pathTime;
	private int goalDistance;
	
	/**
	 * Default constructor for file i/o
	 */
	public Edge() {
	}
	public Edge getParent()
	{
		return this.parent;
	}
	public void setParent(Edge e)
	{
		this.parent=e;
	}

	/**
	 * Constructor with both cities, time, and distance
	 */		
	public Edge(City city1, City city2, int distance, double time) {
		this.city1 = city1;
		this.city2 = city2;
		this.distance = distance;
		this.time = time;
		this.pathDistance = distance;
		this.pathTime=time;
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
	public void setPathDistance(int n)
	{
		this.pathDistance=n;
	}
	public int getPathDistance()
	{
		return this.pathDistance;
	}
	public double getPathTime()
	{
		return this.pathTime;
	}
	public void setPathTime(double d)
	{
		this.pathTime=d;
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
	public double getTime() {
		return this.time;
	}
	
	/**
	 * Setter for time
	 */	
	public void setTime(double time) {
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
		this.pathDistance=distance;
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
	public void setGoal(City end) {
		// TODO Auto-generated method stub.
		
	}
	public void setGoalDistance(int d) {
		this.goalDistance=d;
		
	}
	public int getGoalDistance()
	{
		return this.goalDistance;
	}

}