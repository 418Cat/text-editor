import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Input {
	
	public static class txtFile {
		
		private static String fileName = "/home/418cat/compte.txt";
		private static ArrayList<String> fileContent = new ArrayList<>();
		private static File file = null;
		
		/*
		 * Read the content of the file of path fileName
		 */
		public static void readContent() {
			file = new File(fileName);
			Scanner reader = null;
			try {
				reader = new Scanner(file);
			} catch(Exception e) {
				System.out.println(e.toString());
			}
			
			while(reader.hasNextLine()) {
				fileContent.add(reader.nextLine());
			}
		}
		
		public static ArrayList<String> content(){
			return(fileContent);
		}
		
		/*
		 * Show the popup to choose a file
		 */
		public static String chooseFile() {
			JFileChooser chooser = new JFileChooser();
			String name = "";
	        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	            name = chooser.getSelectedFile().getAbsolutePath();
	        }
	        fileName = name;
	        return(name);
		}
		
	}
	
	public static class mouseWheel implements MouseWheelListener {
		
		private static void scroll(int notches) {
			if(notches > 0) {
				Main.currentLine+= notches;
			} else if(Main.currentLine+notches < 0) {
				Main.currentLine = 0;
			} else {
				Main.currentLine+= notches;
			}
			Frame.displayFile(Main.zoom, Input.txtFile.content(), Main.currentLine);
		}
		
		private static void zoom(int notches) {
			if(Main.zoom-notches < 30) {
				Main.zoom = 30;
			} else if(Main.zoom+notches > 500){
				Main.zoom = 500;
			} else if(Frame.fontSize - notches > 0){
				Main.zoom-= notches/2;
				Frame.fontSize-=notches;
			}
			Frame.displayFile(Main.zoom, Input.txtFile.content(), Main.currentLine);
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			if(keyboard.ctrl()) zoom(e.getWheelRotation());
			else scroll(e.getWheelRotation());
			
		}
		
	}
	
	public static class keyboard implements KeyListener {
		
		private static boolean ctrl = false;
		
		public static boolean ctrl() {
			return(ctrl);
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			System.out.println(e.getModifiersEx());
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.isControlDown()) ctrl = true;
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(!e.isControlDown()) ctrl = false;
			
		}
		
	}
	
}
