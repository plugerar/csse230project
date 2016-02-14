package csse230project;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TripPlanner {
	public static void main(String[] args) throws Exception {
		CityStructure map = WriteDomain.read("usdomain.xml");
		ArrayList<Trip> trip = planTrip(map.getCity("Terre Haute"), 500, true);
		System.out.println(trip.toString());
		trip = planTrip(map.getCity("Seattle"), 12, false);
		System.out.println(trip.toString());
		trip = planTrip(map.getCity("Washington D.C."), 12, false);
		System.out.println(trip.toString());
	}
	
	public static ArrayList<Trip> planTrip(City initialCity, int costFunction, boolean distance) {
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
			trips.add(tripI);
		}
		return trips;
	}
	
	private static City neighborCity(City currentCity, Edge edge) {
		if (edge.getCity1().equals(currentCity)) {
			return edge.getCity2();
		}
		return edge.getCity1();
	}
	
	private static void finalizeTrip(Queue<City> queueCities, Trip tripI, int tempDistance, double tempTime) {
		tripI.setTotalDistance(tempDistance);
		tripI.setTotalTime(tempTime);
		ArrayList<City> tripCities = new ArrayList<>();
		while (!queueCities.isEmpty()) {
			tripCities.add(queueCities.poll());
		}
		tripI.setTripCities(tripCities);		
	}
}