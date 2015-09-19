package mapcolorer;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.AfterClass;
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
	
	@Test
	public void getCostTest()
	{	
		assertEquals("c1 cost is not 2!", 2, c1.getCost());
		c2.changeColor(MapColor.RED);
		assertEquals("c3 cost is not 1!", 1, c3.getCost());
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
}














































