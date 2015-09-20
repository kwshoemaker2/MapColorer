package mapcolorer;

import static org.junit.Assert.assertEquals;

//import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;

public class CountryTest
{
	
	private Country c1 = new Country("C1");
	private Country c2 = new Country("C2");
	private Country c3 = new Country("C3");
	
	public CountryTest()
	{
		c1.addNeighbor(c2);
		c1.addNeighbor(c3);
		
		c2.addNeighbor(c1);
		c2.addNeighbor(c3);
		
		c3.addNeighbor(c1);
		c3.addNeighbor(c2);
	}
	
	@After
	public void unColor_All()
	{
		c1.unColor();
		c2.unColor();
		c3.unColor();
	}
	
	@Test
	public void getCostTest()
	{	
		assertEquals("c1 cost is not 2!", 2, c1.getCost());
		c2.changeColor(MapColor.RED);
		assertEquals("c3 cost is not 1!", 1, c3.getCost());
		c2.unColor();
	}
	
	@Test
	public void addNeighborTest()
	{
		assertEquals("c2 didn't get added as neighbor!", true, c1.isNeighbor(c2));
		assertEquals("c1 didn't get added as neighbor!", true, c2.isNeighbor(c1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addNeighborTwiceTest()
	{
		c1.addNeighbor(c2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addSelfAsNeighborTest()
	{
		c1.addNeighbor(c1);
	}
	
	@Test
	public void getNeighborsTest()
	{
		ArrayList<Country> c1_neighbors = c1.getNeighbors();
		assertEquals("c2 is not in the neighbors list!", true, c1_neighbors.contains(c2));
		assertEquals("c3 is not in the neighbors list!", true, c1_neighbors.contains(c3));
	}
	
	@Test
	public void canColorTest()
	{
		Boolean canColor = true;
		for(MapColor color : MapColor.values()) {
			canColor = canColor && c1.canColor(color);
		}
		
		assertEquals("c1 should be allowed to be any color", true, canColor);
		
		
		c2.changeColor(MapColor.RED);
		
		assertEquals("c1 cant be colored blue for some reason", true, c1.canColor(MapColor.BLUE));
		assertEquals("c1 can be colored red for some reason", false, c1.canColor(MapColor.RED));
		assertEquals("c2 can't be colored red for some reason", true, c2.canColor(MapColor.RED));
		
		c2.unColor();
	}
	
	@Test
	public void possibleColorsTest()
	{
		// make sure we can color any color initially
		int colorAmount = MapColor.values().length;
		assertEquals("c1 should be allowed to be any color", true, 
					 c1.possibleColors().size() == colorAmount);
		
		// make sure we now can't use two colors
		c2.changeColor(MapColor.GREEN);
		c3.changeColor(MapColor.RED);
		assertEquals("C1 should not be able to color itself two different colors", true,
					 c1.possibleColors().size() == colorAmount - 2);
		
		// make sure this all actually worked
		ArrayList<MapColor> colors = c1.possibleColors();
		assertEquals("RED and GREEN should not be possible colors", false,
					 colors.contains(MapColor.GREEN) || colors.contains(MapColor.RED));
		
		c2.unColor();
		c3.unColor();
	}
	
	@Test
	public void CountryComparatorTest()
	{
		CountryComparator countryCompr = new CountryComparator();
		assertEquals("C1 and C2 should have same cost", true, c1.getCost() == c2.getCost());
		assertEquals("Should be 0", 0, countryCompr.compare(c1, c2));
		c2.changeColor(MapColor.RED);
		assertEquals("C1 should have a lower score than C2", -1, countryCompr.compare(c1, c2));
	}
}














































