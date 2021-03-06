package mapcolorer;
import java.nio.file.*;
import java.util.List;
import java.io.IOException;

public final class MapGraphCreator
{
	private MapGraphCreator() {}
	
	public static MapGraph createMapGraphFromFile(String filename) throws IOException
	{
		Path infilePath = Paths.get(filename);
		MapGraph newMap;
		
		if(Files.exists(infilePath)) {
			newMap = MapGraphCreator.createMapGraphFromText(Files.readAllLines(infilePath));
		
		} else {
			throw new IOException(String.format("The file {0} does not exist!", filename));
		}
		return newMap;
	}
	
	public static MapGraph createMapGraphFromText(List<String> fileLines)
	{
		if(fileLines.size() < 1) {
			throw new IllegalArgumentException("The file needs some content!");
		}
		
		MapGraph newMap = new MapGraph(getName(fileLines.get(0)));
		
		for(int i = 1; i < fileLines.size(); i++) {
			String line = fileLines.get(i).replaceAll("[\\s]", "");
			String[] stuff = line.split("--");
			if(stuff.length >= 2) {
				String id1 = stuff[0];
				String id2 = stuff[1];
				newMap.addNeighbors(id1, id2);
			}
		}
		
		return newMap;
	}
	
	private static String getName(String line)
	{
		String[] stuff = line.split("graph[ ]+");
		
		if(stuff.length > 1) {
			String name = stuff[1].replaceAll(" ", "").replace("{", "");
			return name;
		}
		return "";
	}
}



















