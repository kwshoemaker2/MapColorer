package mapcolorer;

import java.util.Comparator;
import java.util.PriorityQueue;

public final class MapColorer
{
	private MapColorer() {}
	
	public static void colorMap(MapGraph m)
	{
		PriorityQueue<Country> queue = new PriorityQueue<Country>(
			m.size(), new CountryComparator());
		
		
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






























