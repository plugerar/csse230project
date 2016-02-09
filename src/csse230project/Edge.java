package csse230project;

public class Edge {
	private City city1;
	private City city2;
	private int time;
	private int distance;
	
	public Edge(City city1, City city2, int time, int distance) {
		this.city1 = city1;
		this.city2 = city2;
		this.time = time;
		this.distance = distance;
	}
	
	public City getCity1() {
		return this.city1;
	}
	
	public City getCity2() {
		return this.city2;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public int getDistance() {
		return this.distance;
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