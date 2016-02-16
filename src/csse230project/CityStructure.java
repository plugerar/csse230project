package csse230project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import csse230project.City.EdgeIterator;

/**
 * Class for all data (hashmap of cities, priority queue of
 * cities in terms of interest level)
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
		this.cityNames= new ArrayList();
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
	public void clearPredecessors();
	public ArrayList<Edge> calculateRoute(ArrayList<City> cities)
	{

		ArrayList<Edge> tempArray=new ArrayList<>();
		for(int i=0;i<cities.size()-1;i++)
		{
			ArrayList<Edge> temp = calculateRouteHelper(cities.get(i),cities.get(i+1));
			tempArray.addAll(tempArray.size(), temp);
			temp.get(temp.size()-1).getCity2().clearPredecessors();
		}
		
		return tempArray;
	}
	public ArrayList<Edge> calculateRouteHelper(City start,City end)
	{
		//int totalDistance=calculateLineDistance(start,end);
		PriorityQueue<Edge> openList=new PriorityQueue<>(start.new edgeComparator());
		
		ArrayList<City> closedList=new ArrayList<>();
		 //creates an iterator
		City currentCity = start; //this is the starting city
		EdgeIterator e = currentCity.getEdgeIterator();
		Edge lowest=null;
		Edge Previous=null;
		int pathDistance=0;
		while(openList!=null && currentCity!=end)
		{
			
			//loop and push other nodes onto open list
			while(e.hasNext()&&!currentCity.equals(end))
			{
				Edge nextChild = e.next();
				if(!closedList.contains(nextChild.getCity2())) //make sure that it is not already traversed and add.
				{
					if(currentCity.getPredecessor()!=null)
					{
						nextChild.setPathDistance(currentCity.getPredecessor().getPathDistance()+nextChild.getDistance());
					}
					openList.offer(nextChild);
				}
			//
			}
			//grab the lowest cost edge
			Previous=lowest;
			lowest= openList.poll();//we need to check for total distance as well
			while(lowest!=null && (closedList.contains(lowest.getCity2())))
			lowest= openList.poll();

			closedList.add(lowest.getCity1());
			//set total path here
			
			//if(currentCity.getPredecessor()!=null)
			//lowest.setPathDistance(lowest.getDistance()+currentCity.getPredecessor().getPathDistance()); //add the total distance up by accessing the previous citys distance value

			//move on to the lowest cost city
			currentCity=lowest.getCity2();
			e=currentCity.getEdgeIterator();
			//set pre edge
			currentCity.setPredecessor(lowest); //set predecessor edge we are traveling from
		}
		ArrayList<Edge> nodeList= new ArrayList<>();
		Edge temp=currentCity.getPredecessor();
		while(temp!=null)
		{
			nodeList.add(0,temp);
			temp=temp.getCity1().getPredecessor();
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
	
	public int calculateLineDistance(City c1,City c2)
	{
		int xDist=Math.abs(c1.getXCoord()-c2.getXCoord());
		int yDist=Math.abs(c1.getYCoord()-c2.getYCoord());
		int total= (int) Math.sqrt(Math.pow(xDist,2)+Math.pow(yDist, 2));
		return (int) (total*2.3);
	}
	//62.8
	public City getLowestCostNeighbor(City c)
	{
		int low=c.getNeighbors().get(0).getDistance();
		City lowcity=c.getNeighbors().get(0).getCity2();
		for(int i=0;i<c.getNeighbors().size();i++)
		{
			int newdistance=c.getNeighbors().get(i).getDistance();
			if(newdistance<low)
			{
				lowcity=c.getNeighbors().get(i).getCity2();
				low=newdistance;
			}
		}
		return lowcity;
	}
}

//ffitr	
//while(!goal)
//{
//	City currentNode=findBestNode();
//	if(currentNode.compareTo(goal)==0)
//	{
//		return openList;
//	}
//	else
//	{
//		closedList.add(currentNode);
//		ArrayList<Edge> edges = currentNode.getNeighbors();
//		
//		for(int i=0;i<edges.size();i++)
//		{
//			Edge currentEdge=edges.get(i);
//			int currentEdgeCost=currentEdge.getDistance();
//           if (closedList.contains(currentEdge) && currentCost<currentEdgeCost) {
//               update the neighbor with the new, lower, g value 
//               change the neighbor's parent to our current node
//           }
//           else if (openList.contains(currentEdge) && currentCost<currentEdgeCost) {
//               update the neighbor with the new, lower, g value 
//               change the neighbor's parent to our current node
//           }
//		}
//	}
//	
//}
//return null;
