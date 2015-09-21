package mapcolorer;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MapColorerTest
{
	ArrayList<MapGraph> testGraphs = new ArrayList<MapGraph>();
	private final String [] testFileNames = {"europe.dot", "iberia.dot", "pacific_nw.dot", "mideast.dot",
							   "us.dot"};
	
	public MapColorerTest()
	{
		MapGraph m1 = new MapGraph("Hawaii");
		m1.addCountry("HI");
		testGraphs.add(m1);
		for(String fileName : testFileNames) {
			MapGraph newMap = MapGraphCreator.createMapGraphFromText(readTestFile(fileName));
			testGraphs.add(newMap);
		}
	}
	
	public ArrayList<String> readTestFile(String fileName)
	{
		ArrayList<String> result = new ArrayList<String>();

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.add(line);
			}

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return result;
	}
	
	@Test
	public void colorMapTest()
	{
		for(MapGraph testMapGraph : testGraphs) {
			MapColorer.colorMap(testMapGraph);
			for(Country country : testMapGraph.getCountries()) {
				MapColor countryColor = country.getColor();
				assertEquals("The country " + country.getName() + " should have a color", 
							true, countryColor != null);
							
				assertEquals("The country should be able to actually be this color",
							 true,
							 country.canColor(countryColor));
			}
		}
	}
}