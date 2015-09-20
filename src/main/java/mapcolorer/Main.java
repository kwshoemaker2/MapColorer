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
		}
		
		if(args.length >= 2) {
			outfileName = args[1];
		}
		
		try {
			MapGraph ourMap = MapGraphCreator.createMapGraphFromFile(infileName);
		
		} catch (IllegalArgumentException | IOException e) {
			System.out.println(e.getMessage());
			return;
		}
	}
}














