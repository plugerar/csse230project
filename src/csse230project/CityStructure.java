package csse230project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import csse230project.City.EdgeIterator;
import csse230project.City.TimeIterator;

/**
 * Class for all data (hashmap of cities, priority queue of cities in terms of
 * interest level). Also implements the A* algorithm for traversing through the map.
 */
public class CityStructure {
	private HashMap<String, City> cityMap;
	private PriorityQueue<City> cityInterestList;
	private Comparator<City> comparator;
	private ArrayList<String> cityNames;

	/**
	 * Default constructor for file i/o
	 */
	public CityStructure() {
		this.cityMap = new HashMap<>();
		this.comparator = new CityComparator();
		this.cityInterestList = new PriorityQueue<>(this.comparator);

		this.cityNames = new ArrayList<>();
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

	/**
	 * Returns a list of city names
	 */
	public ArrayList<String> getCityNames() {
		return this.cityNames;
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
		if (this.cityMap.containsKey(cityName)) {
			return false;
		}
		City citytoadd = new City(cityName, xCoord, yCoord);
		citytoadd.setPointsOfInterest(pointsOfInterest);
		this.cityInterestList.add(citytoadd);
		this.cityMap.put(cityName, citytoadd);
		return true;
	}

	/**
	 * Add a city with name, x and y coordinates
	 * 
	 * @return
	 */
	public boolean addCity(String cityName, int xCoord, int yCoord) {
		if (this.cityMap.containsKey(cityName)) {
			return false;
		}
		City citytoadd = new City(cityName, xCoord, yCoord);
		this.cityInterestList.add(citytoadd);
		this.cityMap.put(cityName, citytoadd);
		return true;
	}

	/**
	 * Calculate route. Calculates the shortest path of a route with at least a
	 * defined start and end point. Additional intermediate waypoints are
	 * factored in as well if the user includes them in the array list. This
	 * method calls calculateRouteHelper for each set of 2 cities and appends
	 * the arrayLists together.
	 * 
	 * @param cities
	 *            ArrayList of cities in desired order of travel
	 * @param distanceFlag
	 *            true for calculating based on distance, and false for time.
	 * @return ArrayList of cities in order from start to end with all waypoints
	 *         in-between.
	 */
	public ArrayList<Edge> calculateRoute(ArrayList<City> cities, boolean distanceFlag) {

		ArrayList<Edge> tempArray = new ArrayList<>();
		for (int i = 0; i < cities.size() - 1; i++) {
			ArrayList<Edge> temp = calculateRouteHelper(cities.get(i), cities.get(i + 1), distanceFlag);
			tempArray.addAll(tempArray.size(), temp);
			temp.get(temp.size() - 1).getCity2().clearPredecessors();
		}
		return tempArray;
	}

	/**
	 * Calculate route helper. This method calculates the shortest path between
	 * 2 cities
	 * 
	 * @param start
	 *            the start city
	 * @param end
	 *            the destination city
	 * @param distanceFlag
	 *            true for distance calculation false for shortest time
	 *            calculation
	 * @return ArrayList of cities in order from start to end with waypoints in
	 *         between
	 */
	public ArrayList<Edge> calculateRouteHelper(City start, City end, boolean distanceFlag) {
		PriorityQueue<Edge> openList = null;
		if (distanceFlag)
			openList = new PriorityQueue<>(start.new edgeComparator());
		else
			openList = new PriorityQueue<>(start.new timeComparator());

		ArrayList<City> closedList = new ArrayList<>();
		// creates an iterator
		City currentCity = start; // this is the starting city
		EdgeIterator e = currentCity.getEdgeIterator();
		TimeIterator t = currentCity.getTimeIterator();

		Edge lowest = null;
		while (currentCity != end) {

			// loop and push other nodes onto open list
			boolean hasnext;
			///////////////
			if (distanceFlag)
				hasnext = e.hasNext();
			else
				hasnext = t.hasNext();
			///////////////
			while (hasnext && !currentCity.equals(end)) {
				Edge nextChild = null;
				if (distanceFlag)
					nextChild = e.next();
				else
					nextChild = t.next();
				if (!closedList.contains(nextChild.getCity2())) // make sure
																// that it is
																// not already
																// traversed and
																// add.
				{
					if (currentCity.getPredecessor() != null) {
						if (distanceFlag)
							nextChild.setPathDistance(
									currentCity.getPredecessor().getPathDistance() + nextChild.getDistance());
						else
							nextChild.setPathTime(currentCity.getPredecessor().getPathTime() + nextChild.getTime());
						nextChild.setGoalDistance(this.calculateLineDistance(nextChild.getCity2(), end, distanceFlag));
					}
					openList.offer(nextChild);
				}
				///////////////
				if (distanceFlag)
					hasnext = e.hasNext();
				else
					hasnext = t.hasNext();
				///////////////
			}
			lowest = openList.poll();// we need to check for total distance as
										// well
			while (lowest != null && (closedList.contains(lowest.getCity2())))
				lowest = openList.poll();

			closedList.add(lowest.getCity1());
			// set total path here

			// if(currentCity.getPredecessor()!=null)
			// lowest.setPathDistance(lowest.getDistance()+currentCity.getPredecessor().getPathDistance());
			// //add the total distance up by accessing the previous citys
			// distance value

			// move on to the lowest cost city
			currentCity = lowest.getCity2();
			e = currentCity.getEdgeIterator();
			t = currentCity.getTimeIterator();
			// set pre edge
			currentCity.setPredecessor(lowest); // set predecessor edge we are
												// traveling from
		}
		ArrayList<Edge> nodeList = new ArrayList<>();
		Edge temp = currentCity.getPredecessor();
		while (temp != null) {
			nodeList.add(0, temp);
			temp = temp.getCity1().getPredecessor();
		}
		return nodeList;

	}

	/**
	 * Link two cities together with time and distance
	 */
	public void linkCity(String city1, String city2, int distance, double time) {
		Edge edge1 = new Edge(this.cityMap.get(city1), this.cityMap.get(city2), distance, time);
		Edge edge2 = new Edge(this.cityMap.get(city2), this.cityMap.get(city1), distance, time);
		this.cityMap.get(city1).addNeighbor(edge1);
		this.cityMap.get(city2).addNeighbor(edge2);
	}

	public int calculateLineDistance(City c1, City c2, boolean distanceFlag) {
		int xDist = Math.abs(c1.getXCoord() - c2.getXCoord());
		int yDist = Math.abs(c1.getYCoord() - c2.getYCoord());
		int total = (int) Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
		if (distanceFlag)
			return (int) (total * 2.3);
		return (int) (total * 62.8);
	}
}