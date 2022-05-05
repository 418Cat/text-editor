import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Frame {
	
	static JFrame mainFrame = null;
	public static int fontSize = 14;
	public static Graphics g = null;
	public static boolean darkTheme = true;
	
	/*
	 * Initialize the frame
	 */
	Frame(int[] size, int[] location){
		mainFrame = new JFrame("Jedit");
		mainFrame.setSize(size[0], size[1]);
		mainFrame.setLocation(location[0], location[1]);
		mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		/*
		 * Show the "file not saved" popup window on close
		 */
		mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	closeNonSavedFile();
		    }
		});
		
		mainFrame.addMouseWheelListener(new Input.mouseWheel());
		mainFrame.addKeyListener(new Input.keyboard());
		
		mainFrame.setVisible(true);
		g = mainFrame.getGraphics();
		g.setColor(Color.black);
	}
	
	/*
	 * Generate the "file not saved" popup window
	 */
	static void closeNonSavedFile() {
		if(Main.saved) System.exit(0);
    	
        if(JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to close this file ?\nit isn't saved", "Close Window?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            System.exit(0);
        }
	}
	
	/*
	 * Display a string arraylist onto the window	
	 */
	public static void displayFile(int zoom, ArrayList<String> content, int startLine) {
		/*
		 * if darktheme, black background and white writing, else inverse
		 */
		if(darkTheme) g.setColor(Color.black);
		else g.setColor(Color.white);
		g.fillRect(0, 0, mainFrame.getWidth(), mainFrame.getHeight());
		if(darkTheme) g.setColor(Color.white);
		else g.setColor(Color.black);
		g.setFont(new Font("",0,  fontSize)); // set correct font size
		
		int inLines = 0;
		int height = (inLines-startLine)*(int)(fontSize*(float)zoom/100) + (mainFrame.getHeight()-mainFrame.getContentPane().getHeight()); //calculate the y coordinate of each line

		for(int i = startLine; i < content.size() && height <= mainFrame.getHeight(); i++) {
			height = (inLines+i-startLine)*(int)(fontSize*(float)zoom/100) + (mainFrame.getHeight()-mainFrame.getContentPane().getHeight());
			String line = content.get(i);
			
			String beforeLine = String.valueOf(i) + " ".repeat(4-String.valueOf(i).length()) + "|";
			
			g.drawString(beforeLine, 0, height);
			
			int lastInline = 0;
			// for each character, display it and make sure to go inline if needed
			for(int j = 0; j < line.length(); j++) {
				
				// If the character gets over the frame width, add a line
				if(lastInline < j*fontSize/mainFrame.getWidth()) {
					inLines++;
					lastInline++;
					height = (inLines+i-startLine)*(int)(fontSize*(float)zoom/100) + (mainFrame.getHeight()-mainFrame.getContentPane().getHeight());
				}
				g.drawString(String.valueOf(line.charAt(j)), beforeLine.length()*fontSize+Math.floorMod(j*fontSize, mainFrame.getWidth()), height);
			}
			//g.drawString(line, fontSize*2 + 2, height);
		}
	}
}
