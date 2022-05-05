import java.io.File;

public class Main {
	
	public static Frame frame;
	
	public static boolean saved = true;
	public static int zoom = 100;
	public static String fileName = "----";
	public static File file;
	public static int currentLine = 0;
	
	public static void main(String[] args) {
		
		frame = new Frame(new int[] {600, 1000}, new int[] {0, 0});
		
		//Input.txtFile.chooseFile();
		Input.txtFile.readContent();
		
		Frame.displayFile(zoom, Input.txtFile.content(), currentLine);
	}
	
}
