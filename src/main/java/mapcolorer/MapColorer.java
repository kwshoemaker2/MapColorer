package mapcolorer;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayList;

public final class MapColorer
{
	private MapColorer() {}
	
	public static void colorMap(MapGraph m)
	{
		PriorityQueue<Country> queue = new PriorityQueue<Country>(
			m.size(), new CountryComparator());
		
		Country curCountry;
		queue.add(findSmallest(m));
		
		while(queue.size() > 0) {
			curCountry = queue.poll();
			ArrayList<Country> neighbors = curCountry.getNeighbors();
			for(Country neigh : neighbors) {
				if( !(queue.contains(neigh) || neigh.getColor() != null)) {
					queue.add(neigh);
				}
			}
			
			ArrayList<MapColor> possibleColors = curCountry.possibleColors();
			if(possibleColors.size() > 0) {
				curCountry.changeColor(possibleColors[0]);
			
			} else {
				System.out.println("This algorithm doesn't work!!!");
				break;
			}
		}
	}
	
	private static Country findSmallest(MapGraph m)
	{
		ArrayList<Country> countries = m.getCountries();
		Country smallest = countries[0];
		for(Country country : countries) {
			if(country.getCost() < smallest.getCost()) {
				smallest = country;
			}
		}
		
		return smallest;
	}
	
	
}

class CountryComparator implements Comparator<Country>
{
	@Override
	public int compare(Country c1, Country c2)
	{
		int c1Cost = c1.getCost();
		int c2Cost = c2.getCost();
		
		if(c1Cost < c2Cost) {
			return -1;
		
		} else if(c1Cost > c2Cost) {
			return 1;
		
		} else {
			int c1ColorsSize = c1.possibleColors().size();
			int c2ColorsSize = c2.possibleColors().size();
			if(c1ColorsSize < c2ColorsSize) {
				return -1;
			
			} else if(c1ColorsSize > c2ColorsSize) {
				return 1;
			
			} else {
				return 0;
			}
		}
	}
}






























