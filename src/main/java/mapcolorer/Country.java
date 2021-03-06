package mapcolorer;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

public class Country
{
	private final String name;
	private HashMap neighbors = new HashMap<String, Country>();
	private MapColor color = null;
	private Boolean entered = false;
	private Boolean visited = false;
	
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
	
	public void unColor()
	{
		color = null;
	}
	
	public void addNeighbor(Country newNeighbor)
	{
		String key = newNeighbor.getName();
		if(!neighbors.containsKey(key) && key != this.name) {
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

	
	public ArrayList<Country> getNeighbors()
	{
		Iterator it = neighbors.entrySet().iterator();
		ArrayList<Country> neighs = new ArrayList<Country>(neighbors.size());
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			Country neighbor = (Country)pair.getValue();
			neighs.add(neighbor);
		}
		return neighs;
	}
	
	public Boolean isEntered()
	{
		return entered;
	}
	
	public void setEntered(Boolean b)
	{
		entered = b;
	}
	
	public Boolean canColor(MapColor color)
	{
		for(Country neigh : getNeighbors()) {
			if(neigh.getColor() == color) {
				return false;
			}
		}
		
		return true;
	}
	
	public ArrayList<MapColor> possibleColors()
	{
		ArrayList<MapColor> colors = new ArrayList<MapColor>();
		ArrayList<Country> neighbors = getNeighbors();
		
		for(MapColor color : MapColor.values()) {
			Boolean canInclude = true;
			for(Country neigh : neighbors) {
				if(neigh.getColor() == color) {
					canInclude = false;
					break;
				}
			}
			if(canInclude) {
				colors.add(color);
			}
		}
		
		return colors;
	}
	
	public int numberOfNeighbors()
	{
		return neighbors.size();
	}
	
	public Boolean isVisited()
	{
		return visited;
	}
	
	public void setVisited(Boolean v)
	{
		visited = v;
	}
}

























