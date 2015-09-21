package mapcolorer;
import java.nio.file.*;
import java.io.IOException;

public class Main
{
	public static void main(String [] args)
	{
		String infileName = "";
		String outfileName = "";
		if(args.length < 1) {
			System.out.printf("USAGE: ./filename.jar inFile.dot [outFile.dot]");
			return;
		
		} else if(args.length >= 1) {
			infileName = args[0];
			Path p = Paths.get(infileName);
			if(p.getParent() != null) {
				outfileName = p.getParent().toString();
			}
			outfileName += "colored_" + p.getFileName().toString();
		}
		
		if(args.length >= 2) {
			outfileName = args[1];
		}
		
		try {
			MapGraph ourMap = MapGraphCreator.createMapGraphFromFile(infileName);
			MapColorer.colorMap(ourMap);
			Path outfilePath = Paths.get(outfileName);
			Files.write(outfilePath, ourMap.toString().getBytes());
		
		} catch (IllegalArgumentException | IOException e) {
			System.out.println(e.getMessage());
			return;
		}
	}
}














