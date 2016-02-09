package csse230project;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
public class CityStructure {

	private HashMap<String,City> cityStructure = new HashMap<>();
	private ArrayList<City> cityArray=new ArrayList<>();
	private int cityStructureSize;
	
	public CityStructure()
	{
		this.cityStructureSize=0;
		
	}

	public boolean add(City c)
	{
		if(this.cityArray.contains(c))	//TODO: check for duplicate elements.
		{
			return false;
		}
		this.cityArray.add(c);
		this.cityStructure.put(c.getName(), c); //TODO: add a toString method to city
		return true;
	}
	
	public void clear() {
		this.cityStructure.clear();
		this.cityArray.clear();
		
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

	public Set<java.util.Map.Entry<String, City>> entrySet() {
		return this.cityStructure.entrySet();
	}

	public City get(Object o) {
		return this.cityStructure.get(o);
	}

	public boolean isEmpty() {
		return this.cityStructure.isEmpty();
	}

	public Set<String> keySet() {
		return this.cityStructure.keySet();
	}

	public City put(String s, City c) {
		if(this.cityStructure.containsKey(s))
		{
			return this.cityStructure.put(s, c);
		}
		this.cityArray.add(c);
		return this.cityStructure.put(s, c);
	}

	public boolean remove(String s) {
		if(this.cityStructure.containsKey(s))
		{
			City citytoremove = this.cityStructure.remove(s);
			this.cityArray.remove(citytoremove);
			return true;
		}
		return this.cityArray.remove(s);
	}

	public int size() {
		return this.cityStructureSize;
		
	}

	
	class CityIterator implements Iterator
	{
		private int currentElement;
		
		public CityIterator()
		{
			this.currentElement=0;
			
			
		}
		@Override
		public boolean hasNext() {
			if (this.currentElement<size()-1)
			{
				return true;
			}
			return false;
		}

		@Override
		public City next() {
			City c = get(this.currentElement+1);
			return c;
		}
		
		
	}

}
