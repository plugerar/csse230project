package csse230project;

import java.util.Comparator;

/**
 * Compares two cities based on their interest levels.
 * Used for the priority queue list of cities.
 */
public class CityComparator implements Comparator<City>{

	@Override
	public int compare(City city1, City city2) {
		return city2.getInterestLevel().compareTo(city1.getInterestLevel());
	}

}
