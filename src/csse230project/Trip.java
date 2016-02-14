package csse230project;

import java.util.ArrayList;

public class Trip {
	private int totalDistance;
	private double totalTime;
	private ArrayList<City> tripCities;

	/**
	 * Default contstructor sets total time and distance to 0
	 */
	public Trip() {
		this.setTotalDistance(0);
		this.setTotalTime(0);
		this.setTripCities(new ArrayList<>());
	}
	
	/**
	 * Getter for total distance
	 */
	public int getTotalDistance() {
		return this.totalDistance;
	}

	/**
	 * Setter for total distance
	 */
	public void setTotalDistance(int totalDistance) {
		this.totalDistance = totalDistance;
	}

	/**
	 * Getter for total time
	 */
	public double getTotalTime() {
		return this.totalTime;
	}

	/**
	 * Setter for total time
	 */
	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}

	/**
	 * Getter for trip cities
	 */
	public ArrayList<City> getTripCities() {
		return this.tripCities;
	}

	/**
	 * Setter for trip citiess
	 */
	public void setTripCities(ArrayList<City> tripCities) {
		this.tripCities = tripCities;
	}
			
	@Override
	public String toString(){
		String s = "";
		s += "Total Time: " + this.totalTime + "\n";
		s += "Total Distance: " + this.totalDistance + "\n";
		s += "Cities: ";
		for (int i = 0; i < this.tripCities.size(); i++) {
			s += this.tripCities.get(i).getName();
			if (i != this.tripCities.size() - 1) {
				s += ", ";
			}
		}
		s+= "\n";
		return s;
	}
}
