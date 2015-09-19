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
	
	@Test
	public void addNeighborsTest()
	{
		testMapGraph.addNeighbors("C4", "C5");
		assertEquals("C4 and C5 were not added to the map", true, 
						testMapGraph.hasCountry("C4") && testMapGraph.hasCountry("C5"));
	}
}










































