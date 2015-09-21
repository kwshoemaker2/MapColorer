package mapcolorer;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

public class MapGraph
{
	private HashMap countries = new HashMap<String, Country>();
	private String name = "";
	
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
		
		c1.addNeighbor(c2);
		c2.addNeighbor(c1);
	}
	
	public Boolean hasCountry(Country c)
	{
		return countries.containsKey(c.getName());
	}
	
	public Boolean hasCountry(String id)
	{
		return countries.containsKey(id);
	}
	
	public Country getCountry(String countryId)
	{
		return (Country)countries.get(countryId);
	}
	
	public ArrayList<Country> getCountries()
	{
		Iterator it = countries.entrySet().iterator();
		ArrayList<Country> nations = new ArrayList<Country>(countries.size());
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			Country country = (Country)pair.getValue();
			nations.add(country);
		}
		return nations;
	}
	
	@Override
	public String toString()
	{
		String contents = String.format("graph %s {\n", name);
		for(Country c : getCountries()) {
			String curName = c.getName();
			for(Country n : c.getNeighbors()) {
				if(!n.isEntered()) {
					contents += String.format("\t%s -- %s\n", c.getName(), n.getName());
				}
			}
			c.setEntered(true);
			if(c.getColor() != null) {
				contents += String.format("\t%s[style = filled, fillcolor = %s]\n", 
											c.getName(), c.getColor().getId());
			}
		}
		contents += "}\n";
		return contents;
	}

	public Boolean areNeighbors(String id1, String id2)
	{
		Country c1 = getCountry(id1);
		Country c2 = getCountry(id2);
		return c1.isNeighbor(c2) && c2.isNeighbor(c1);
		
	}
	
	public int size()
	{
		return countries.size();
	}
}
































