package mapcolorer;

import java.util.Map;
import java.util.HashMap;

public class MapGraph
{
	private HashMap countries = new HashMap<String, Country>();
	private String name;
	
	public MapGraph(String name)
	{
		this.name = name;
	}
	
	public void addCountry(String countryId)
	{
		if(!countries.containsKey(countryId)) {
			Country newCountry = new Country(countryId);
			countries.put(countryId, newCountry);
		
		} else {
			throw new IllegalArgumentException(
			String.format(
				"the country {0} already belongs to this MapGraph", countryId));
		}
	}
	
	public void addNeighbors(String countryId, String neighId)
	{
		if(!countries.containsKey(countryId)) {
			addCountry(countryId);
		}
		if(!countries.containsKey(neighId)) {
			addCountry(neighId);
		}
		
		Country c1 = (Country)countries.get(countryId);
		Country c2 = (Country)countries.get(neighId);
		
		if( !(c1.isNeighbor(c2) || c2.isNeighbor(c1)) ) { 
			c1.addNeighbor(c2);
			c2.addNeighbor(c1);
		
		} else {
			throw new IllegalArgumentException(
				String.format(
					"{0} and {1} are already neighbors!", countryId, neighId));
		}
	}
	
	public Boolean hasCountry(Country c)
	{
		return countries.containsKey(c.getName());
	}
	
	public Country getCountry(String countryId)
	{
		return (Country)countries.get(countryId);
	}
}
































