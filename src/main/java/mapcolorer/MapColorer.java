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
		
		Country curCountry = findLargest(m);
		
		if(m.size() == 1) { // the algorithm doesn't run at all for some reason in this case...
			curCountry.changeColor(MapColor.RED);
			return;
		}
		curCountry.setVisited(true);
		queue.add(curCountry);
		
		while(queue.size() > 0) {
			curCountry = queue.poll();
			ArrayList<Country> neighbors = curCountry.getNeighbors();
			for(Country neigh : neighbors) {
				if(neigh.isVisited() == false && neigh.getColor() == null) {
					queue.add(neigh);
					neigh.setVisited(true);
				}
			}
			ArrayList<MapColor> possibleColors = curCountry.possibleColors();
			if(possibleColors.size() > 0) {
				curCountry.changeColor(possibleColors.get(0));
			
			} else {
				System.out.println(String.format(
									"country %s has no available colors", curCountry.getName()));
			}
		}
	}
	
	private static Country findLargest(MapGraph m)
	{
		ArrayList<Country> countries = m.getCountries();
		Country largest = countries.get(0);
		CountryComparator cc = new CountryComparator();
		for(Country country : countries) {
			if(cc.compare(largest, country) == 1) {
				largest = country;
			}
		}
		
		return largest;
	}
	
}

class CountryComparator implements Comparator<Country>
{
	/** The heuristic is designed to color countries that border a lot of others first
	  * since the problem with the old heuristic, which did the opposite, was that
	  * the countries with the most borders ran out of colors to use
	  */
	@Override
	public int compare(Country c1, Country c2)
	{
		int c1Cost = c1.numberOfNeighbors();
		int c2Cost = c2.numberOfNeighbors();
		
		if(c1Cost < c2Cost) {
			return 1;
		
		} else if(c1Cost > c2Cost) {
			return -1;
		
		// if they're both the same then go with the one that has fewer available colors to use
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






























