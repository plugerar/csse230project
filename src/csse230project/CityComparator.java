package csse230project;

import java.util.Comparator;

public class CityComparator implements Comparator<City>{

	@Override
	public int compare(City city1, City city2) {
		return city2.getInterestLevel().compareTo(city1.getInterestLevel());
	}

}
