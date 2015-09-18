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
			outfileName = "colored_" + infileName;
			
		}
		if(args.length >= 2) {
			outfileName = args[1];
		}
		
		Path infilePath = Paths.get(infileName);
		Path outfilePath = Paths.get(outfileName);
		MapGraph ourMap;
		if(Files.exists(infilePath)) {
			try {
				ourMap = MapGraphCreator.createMapGraphFromText(Files.readAllLines(infilePath));
				Files.write(outfilePath, ourMap.toString().getBytes());
				
			} catch(IOException io_e) {
				System.out.printf("An IOException occurred: %s", io_e);
				return;
			}
		
		} else {
			System.out.printf("The file %s does not exist!", infileName);
			return;
		}	
	}
}














