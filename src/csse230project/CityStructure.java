package csse230project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Class for all data (hashmap of cities, priority queue of
 * cities in terms of interest level)
 */
public class CityStructure {
	private HashMap<String, City> cityList;
	private PriorityQueue<City> cityInterestList;
	private Comparator<City> comparator;
	
	/**
	 * Default constructor for file i/o
	 */
	public CityStructure() {
		this.cityList = new HashMap<>();
		this.comparator = new CityComparator();
		this.cityInterestList = new PriorityQueue<>(this.comparator);
	}

	/**
	 * Getter for city list
	 */
	public HashMap<String, City> getCityList() {
		return this.cityList;
	}

	/**
	 * Setter for city list
	 */
	public void setCityList(HashMap<String, City> cityList) {
		this.cityList = cityList;
	}
	
	/**
	 * Getter for city interest list
	 */
	public PriorityQueue<City> getCityInterestList() {
		return this.cityInterestList;
	}
	
	/**
	 * Setter for city interest list
	 */
	public void setCityInterestList(PriorityQueue<City> cityInterestList) {
		this.cityInterestList = cityInterestList;
	}
		
	/**
	 * Getter for city by name
	 */	
	public City getCity(String cityName) {
		return this.cityList.get(cityName);
	}

	/**
	 * Add a city with name, x and y coordinates, and POI list
	 */		
	public void addCity(String cityName, int xCoord, int yCoord, ArrayList<Attraction> pointsOfInterest) {
		this.cityList.put(cityName, new City(cityName,xCoord,yCoord));
		this.cityList.get(cityName).setPointsOfInterest(pointsOfInterest);
		this.cityInterestList.add(this.cityList.get(cityName));
	}

	/**
	 * Add a city with name, x and y coordinates
	 */			
	public void addCity(String cityName, int xCoord, int yCoord) {
		this.cityList.put(cityName, new City(cityName,xCoord,yCoord));
		this.cityInterestList.add(this.cityList.get(cityName));
	}

	/**
	 * Link two cities together with time and distance
	 */		
	public void linkCity(String city1, String city2, int time, int distance) {
		Edge edge = new Edge(this.cityList.get(city1), this.cityList.get(city2), time, distance);
		this.cityList.get(city1).addNeighbor(edge);
		this.cityList.get(city2).addNeighbor(edge);
	}
}

