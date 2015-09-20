package mapcolorer;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.Ignore;

public class MapColorerTest
{
	@Test
	@Ignore("Not implemented yet")
	public void colorMapTest()
	{
		MapGraph testMapGraph = new MapGraph("THIS_IS_A_TEST");
		
		for(Country country : testMapGraph.getCountries()) {
			MapColor countryColor = country.getColor();
			assertEquals("The country should have a color", true, countryColor != null);
			assertEquals("The country should be able to actually be this color",
						 true,
						 country.canColor(countryColor));
		}
	}
}