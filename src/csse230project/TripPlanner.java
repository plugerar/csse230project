package csse230project;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * TripPlanner class plans a series of trips for a given time or distance 
 */
public class TripPlanner {
	
	/**
	 * Default constructor doesn't do anything special
	 */
	TripPlanner() {
		
	}
	
	/**
	 * Plans some trips for a given city that is about the length of the cost function
	 * 
	 * @param initialCity - city to center the trip around
	 * @param costFunction - length of time/distance to plan trip for
	 * @param distance - true means use distance measure and false means use time measure
	 * 
	 * @return ArrayList<Trip> - a list of trips you could take
	 */
	public ArrayList<Trip> planTrip(City initialCity, int costFunction, boolean distance) {
		ArrayList<Trip> trips = new ArrayList<>();
		Queue<City> queueCities = new LinkedList<>();
		int wiggleRoom = 1;
		if (distance) wiggleRoom = 100;
		double tempTime;
		int tempDistance;
		boolean tripFound;
		for (int i = 0; i < initialCity.getNeighbors().size(); i++) {
			Trip tripI = new Trip();
			tempTime = 0;
			tempDistance = 0;
			tripFound = false;
			queueCities.offer(initialCity);
			Edge neighborEdge = initialCity.getNeighbors().get(i);
			City neighborCity = neighborCity(initialCity, neighborEdge);
			queueCities.offer(neighborCity);
			while (!tripFound) {
				Edge tempEdge = neighborCity.getNeighbors().get(0);
				City tempCity = neighborCity(neighborCity, tempEdge);
				Edge mostInterestingEdge = null;
				City mostInterestingCity = null;
				for (int j = 0; j < neighborCity.getNeighbors().size(); j++) {
					if ((!queueCities.contains(tempCity))
						&& (mostInterestingCity == null || tempCity.getInterestLevel().compareTo(mostInterestingCity.getInterestLevel()) > 0)
						&& ((distance && (tempEdge.getDistance() + tempDistance) < (costFunction + wiggleRoom))
						|| (!distance && (tempEdge.getTime() + tempTime) < (costFunction + wiggleRoom)))) {
						mostInterestingCity = tempCity;
						mostInterestingEdge = tempEdge;
					}
					tempEdge = neighborCity.getNeighbors().get(j);
					tempCity = neighborCity(neighborCity, tempEdge);
				}
				if (mostInterestingEdge != null && mostInterestingCity != null) {
					tempDistance += mostInterestingEdge.getDistance();
					tempTime += mostInterestingEdge.getTime();
					neighborCity = mostInterestingCity;
					queueCities.offer(mostInterestingCity);
				} else {
					finalizeTrip(queueCities, tripI, tempDistance, tempTime);
					tripFound = true;
				}
			}
			if (distance) {
				if (Math.abs(costFunction - tripI.getTotalDistance()) < wiggleRoom) {
					trips.add(tripI);					
				}
			} else {
				if (Math.abs(costFunction - tripI.getTotalTime()) < wiggleRoom) {
					trips.add(tripI);					
				}
			}			
		}
		return trips;
	}
	/**
	 * Finds the appropriate neighbor city by the edge
	 */
	private City neighborCity(City currentCity, Edge edge) {
		if (edge.getCity1().equals(currentCity)) {
			return edge.getCity2();
		}
		return edge.getCity1();
	}
	
	/**
	 * Sets the appropriate cities to the trip
	 */
	private void finalizeTrip(Queue<City> queueCities, Trip tripI, int tempDistance, double tempTime) {
		tripI.setTotalDistance(tempDistance);
		tripI.setTotalTime(tempTime);
		ArrayList<City> tripCities = new ArrayList<>();
		while (!queueCities.isEmpty()) {
			tripCities.add(queueCities.poll());
		}
		tripI.setTripCities(tripCities);		
	}
}