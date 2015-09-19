package mapcolorer;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

public class MapGraphTest
{
	MapGraph testMapGraph = new MapGraph("TEST_MAP_GRAPH");
	
	public MapGraphTest()
	{
		testMapGraph.addCountry("C1");
		testMapGraph.addCountry("C2");
		testMapGraph.addCountry("C3");
		testMapGraph.addNeighbors("C1", "C2");
	}
	
	public void addNeighborsCheck(String id1, String id2)
	{
		assertEquals(String.format("{0} and {1} were not added to the map", id1, id2), 
					true, 
					testMapGraph.hasCountry(id1) && testMapGraph.hasCountry(id2));
		
		assertEquals(String.format("{0} and {1} are not neighbors", id1, id2), 
					true,
					testMapGraph.areNeighbors("C4", "C5"));
	}
	
	@Test
	public void addCountryTest()
	{
		assertEquals("C1 was not added initially", true, testMapGraph.hasCountry("C1"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addCountryTestShouldThrowException()
	{
		testMapGraph.addCountry("C1");
	}
	
	@Test
	public void addNeighborsTest()
	{
		// neither country is currently in the MapGraph
		testMapGraph.addNeighbors("C4", "C5");
		addNeighborsCheck("C4", "C5");
		
		// one is currently in the MapGraph
		testMapGraph.addNeighbors("C4", "C6");
		addNeighborsCheck("C4", "C6");
		
		// both are currently in the MapGraph
		testMapGraph.addNeighbors("C2", "C3");
		addNeighborsCheck("C2", "C3");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addNeighborsTestWithNeighbors()
	{
		testMapGraph.addNeighbors("C1", "C2");
	}
	
	@Test
	public void getNeighborsTest()
	{
		assertEquals("getNeighbors().size() should equal testMapGraph.size())",
					 true,
					 testMapGraph.size() == testMapGraph.getCountries().size());
	}
	
	@Test
	public void areNeighborsTest()
	{
		assertEquals("C1 and C2 are not neighbors!", true, testMapGraph.areNeighbors("C1", "C2"));
	}
}










































