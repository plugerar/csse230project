package csse230project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Class for all data (hashmap of cities, priority queue of
 * cities in terms of interest level)
 */
public class CityStructure {
	private HashMap<String, City> cityStructure;
	private PriorityQueue<City> cityInterestList;
	private Comparator<City> comparator;
	
	/**
	 * Default constructor for file i/o
	 */
	public CityStructure() {
		this.cityStructure = new HashMap<>();
		this.comparator = new CityComparator();
		this.cityInterestList = new PriorityQueue<>(this.comparator);
	}

	/**
	 * Getter for city list
	 */
	public HashMap<String, City> getcityStructure() {
		return this.cityStructure;
	}

	/**
	 * Setter for city list
	 */
	public void setcityStructure(HashMap<String, City> cityStructure) {
		this.cityStructure = cityStructure;
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
		return this.cityStructure.get(cityName);
	}
	
	/**
	 * Add a city with name, x and y coordinates, and POI list
	 */		
	public boolean addCity(String cityName, int xCoord, int yCoord, ArrayList<Attraction> pointsOfInterest) {
		if(this.cityStructure.containsKey(cityName))	//TODO: check for duplicate elements.
		{
			return false;
		}
		City citytoadd = new City(cityName,xCoord,yCoord);
		citytoadd.setPointsOfInterest(pointsOfInterest);
		this.cityInterestList.add(citytoadd);
		this.cityStructure.put(cityName, citytoadd); //TODO: add a toString method to city		
		return true;
	}

	/**
	 * Add a city with name, x and y coordinates
	 * @return 
	 */			
	public boolean addCity(String cityName, int xCoord, int yCoord) {
		if(this.cityStructure.containsKey(cityName))	//TODO: check for duplicate elements.
		{
			return false;
		}
		City citytoadd = new City(cityName,xCoord,yCoord);
		this.cityInterestList.add(citytoadd);
		this.cityStructure.put(cityName, citytoadd); //TODO: add a toString method to city		
		return true;
	}
	public boolean containsKey(String s) {
		if (this.cityStructure.containsKey(s))
			return true;
		return false;
	}

	public boolean containsValue(Object o) {
		if(this.cityStructure.containsValue(o))
			return true;
		return false;
	}
	public boolean isEmpty() {
		return this.cityStructure.isEmpty();
	}

	public Set<String> keySet() {
		return this.cityStructure.keySet();
	}
	public int size() {
		return this.cityStructure.size();
		
	}
	/**
	 * Link two cities together with time and distance
	 */		
	public void linkCity(String city1, String city2, int time, int distance) {
		Edge edge = new Edge(this.cityStructure.get(city1), this.cityStructure.get(city2), time, distance);
		this.cityStructure.get(city1).addNeighbor(edge);
		this.cityStructure.get(city2).addNeighbor(edge);
	}
}

