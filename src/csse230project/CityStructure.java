<<<<<<< HEAD
package csse230project;

public class CityStructure {

}
=======
package csse230project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class CityStructure {
	private HashMap<String, City> cityList;
	private PriorityQueue<City> cityInterestList;
	Comparator<City> comparator;
	
	public CityStructure() {
		this.cityList = new HashMap<>();
		this.comparator = new CityComparator();
		this.cityInterestList = new PriorityQueue<>(this.comparator);
	}

	public HashMap<String, City> getCityList() {
		return this.cityList;
	}

	public City getCity(String cityName) {
		return this.cityList.get(cityName);
	}
	
	public PriorityQueue<City> getCityInterestList() {
		return this.cityInterestList;
	}
	
	public void addCity(String cityName, int xCoord, int yCoord, ArrayList<Attraction> pointsOfInterest) {
		this.cityList.put(cityName, new City(cityName,xCoord,yCoord));
		this.cityList.get(cityName).setPointsOfInterest(pointsOfInterest);
		this.cityInterestList.add(this.cityList.get(cityName));
	}
	
	public void addCity(String cityName, int xCoord, int yCoord) {
		this.cityList.put(cityName, new City(cityName,xCoord,yCoord));
		this.cityInterestList.add(this.cityList.get(cityName));
	}
	
	public void linkCity(String city1, String city2, int time, int distance) {
		Edge edge = new Edge(this.cityList.get(city1), this.cityList.get(city2), time, distance);
		this.cityList.get(city1).addNeighbor(edge);
		this.cityList.get(city2).addNeighbor(edge);
	}
}
>>>>>>> Allison
