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
	private HashMap<String, City> cityMap;
	private PriorityQueue<City> cityInterestList;
	private Comparator<City> comparator;
	ArrayList<String> cityNames;
	
	/**
	 * Default constructor for file i/o
	 */
	public CityStructure() {
		this.cityMap = new HashMap<>();
		this.comparator = new CityComparator();
		this.cityInterestList = new PriorityQueue<>(this.comparator);
		this.cityNames = new ArrayList<String>();
	}

	/**
	 * Getter for city list
	 */
	public HashMap<String, City> getCityMap() {
		return this.cityMap;
	}

	/**
	 * Setter for city list
	 */
	public void setCityMap(HashMap<String, City> cityMap) {
		this.cityMap = cityMap;
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
	 * Form an array list of the interest list
	 */
	public ArrayList<City> cityInterestToArrayList() {
		ArrayList<City> iterableCityInterestList = new ArrayList<>();
		PriorityQueue<City> temp = new PriorityQueue<>(this.comparator);
		while (this.cityInterestList.peek() != null) {
			City nextCity = this.cityInterestList.poll();
			String name = nextCity.getName();
			this.cityNames.add(name);
			iterableCityInterestList.add(nextCity);
			temp.add(nextCity);
		}
		this.cityInterestList = temp;
		return iterableCityInterestList;		
	}
	
	public ArrayList<String> getCityNames(){
		return cityNames;
	}
	
	/**
	 * Getter for city by name
	 */	
	public City getCity(String cityName) {
		return this.cityMap.get(cityName);
	}
	
	/**
	 * Add a city with name, x and y coordinates, and POI list
	 */		
	public boolean addCity(String cityName, int xCoord, int yCoord, ArrayList<Attraction> pointsOfInterest) {
		if(this.cityMap.containsKey(cityName)) {
			return false;
		}
		City citytoadd = new City(cityName,xCoord,yCoord);
		citytoadd.setPointsOfInterest(pointsOfInterest);
		this.cityInterestList.add(citytoadd);
		this.cityMap.put(cityName, citytoadd);		
		return true;
	}

	/**
	 * Add a city with name, x and y coordinates
	 * @return 
	 */			
	public boolean addCity(String cityName, int xCoord, int yCoord) {
		if(this.cityMap.containsKey(cityName)) {
			return false;
		}
		City citytoadd = new City(cityName,xCoord,yCoord);
		this.cityInterestList.add(citytoadd);
		this.cityMap.put(cityName, citytoadd);		
		return true;
	}

	/**
	 * Link two cities together with time and distance
	 */		
	public void linkCity(String city1, String city2, int distance, double time) {
		Edge edge = new Edge(this.cityMap.get(city1), this.cityMap.get(city2), distance, time);
		this.cityMap.get(city1).addNeighbor(edge);
		this.cityMap.get(city2).addNeighbor(edge);
	}
}

