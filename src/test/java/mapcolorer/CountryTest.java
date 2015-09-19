package mapcolorer;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.HashMap;

public class CountryTest
{	
	@Test
	public void getCostTest()
	{
		Country c1 = new Country("C1");
		Country c2 = new Country("C2");
		Country c3 = new Country("C3");
		
		c1.addNeighbor(c2);
		c1.addNeighbor(c3);
		
		c2.addNeighbor(c1);
		c2.addNeighbor(c3);
		
		c3.addNeighbor(c1);
		c3.addNeighbor(c2);
		
		assertEquals("c1 cost is not 2!", 2, c1.getCost());
		c2.changeColor(MapColor.RED);
		assertEquals("c3 cost is not 1!", 1, c3.getCost());
	}
}














































