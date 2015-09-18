package mapcolorer;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class Country
{
	private final String name;
	private HashMap neighbors = new HashMap<String, Country>();
	private MapColor color = null;
	
	public Country(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public MapColor getColor()
	{
		return color;
	}
	
	public void changeColor(MapColor newColor)
	{
		color = newColor;
	}
	
	/* Cost function for the country
	 * Generated by simply counting up the number of adjacent countries that 
	 * aren't colored yet
	 */
	public int getCost()
	{
		int cost = 0;
		Iterator it = neighbors.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			Country neighbor = (Country)pair.getValue();
			if(neighbor.getColor() == null) {
				cost += 1;
			}
		}
		
		return cost;
	}
	
	public void addNeighbor(Country newNeighbor)
	{
		String key = newNeighbor.getName();
		if(!neighbors.containsKey(key)) {
			neighbors.put(key, newNeighbor);
		
		} else {
			throw new IllegalArgumentException(
			String.format(
				"this country already has a neighbor by the name of {0}", key));
		}
	}
	
	public Boolean isNeighbor(Country c)
	{
		return neighbors.containsKey(c.getName());
	}
}

























