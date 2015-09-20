package mapcolorer;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;

public class MapColorerTest
{
	ArrayList<MapGraph> testGraphs = new ArrayList<MapGraph>();
	
	public MapColorerTest()
	{
		MapGraph mg1 = new MapGraph("HAWAII");
		mg1.addCountry("HI");
		
		MapGraph mg2 = new MapGraph("Pacific_NW");
		mg2.addNeighbors("ID", "MT");
		mg2.addNeighbors("ID", "OR");
		mg2.addNeighbors("ID", "WA");
		mg2.addNeighbors("OR", "WA");
	}
	
	@Test
	public void colorMapTest()
	{
		for(MapGraph testMapGraph : testGraphs) {
			for(Country country : testMapGraph.getCountries()) {
				MapColor countryColor = country.getColor();
				assertEquals("The country should have a color", true, countryColor != null);
				assertEquals("The country should be able to actually be this color",
							 true,
							 country.canColor(countryColor));
			}
		}
	}
}