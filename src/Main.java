public class Main {
	
	public static boolean saved = false;
	public static int zoom = 100;
	public static String file = "----";
	
	public static void main(String[] args) {
		Frame frame = new Frame(new int[] {600, 600}, new int[] {0, 0});
		
		file = frame.chooseFile();
	}
	
}
