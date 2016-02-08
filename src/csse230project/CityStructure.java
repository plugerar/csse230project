package csse230project;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
public class CityStructure<K,V> implements Map<K,V>  {

	private HashMap cityStructure;
	private ArrayList cityArray=new ArrayList();
	
	public CityStructure()
	{
		
		
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
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub.
		
	}

	@Override
	public boolean containsKey(Object arg0) {
		// TODO Auto-generated method stub.
		return false;
	}

	@Override
	public boolean containsValue(Object arg0) {
		// TODO Auto-generated method stub.
		return false;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub.
		return null;
	}

	@Override
	public V get(Object arg0) {
		// TODO Auto-generated method stub.
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub.
		return false;
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub.
		return null;
	}

	@Override
	public V put(K arg0, V arg1) {
		// TODO Auto-generated method stub.
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> arg0) {
		// TODO Auto-generated method stub.
		
	}

	@Override
	public V remove(Object arg0) {
		// TODO Auto-generated method stub.
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub.
		return 0;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub.
		return null;
	}
	
	private class CityIterator<City> implements Iterator<City>
	{
		private int currentElement;
		
		public CityIterator()
		{
			this.currentElement=0;
			
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub.
			return false;
		}

		@Override
		public City next() {
			// TODO Auto-generated method stub.
			return null;
		}
		
		
	}

}
