import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Frame {
	static JFrame mainFrame = null;
	
	Frame(int[] size, int[] location){
		mainFrame = new JFrame("Jedit");
		mainFrame.setSize(size[0], size[1]);
		mainFrame.setLocation(location[0], location[1]);
		mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	closeNonSavedFile();
		    }
		});
		
		mainFrame.setVisible(true);
	}
	
	public String chooseFile() {
		JFileChooser chooser = new JFileChooser();
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return(chooser.getSelectedFile().getAbsolutePath());
        }
        return("");
	}
	
	static void closeNonSavedFile() {
		if(Main.saved) System.exit(0);
    	
        if(JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to close this file ?\nit isn't saved", "Close Window?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            System.exit(0);
        }
	}
	
	public static void displayFile() {
		
	}
	
	
}
