package blasterball;

//This is the main method for the game

/**
A lot of the code in Gameplay.java and MapGenerator.java needs to be finished up, but
the game is still playable as is. Just compile everything in the "blasterball" folder
and then run the Main.java file
**/

import javax.swing.JFrame;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, FileNotFoundException {
		JFrame frame = new JFrame();
		Gameplay gp = new Gameplay();
		
		frame.setBounds(200, 100, 700, 600);
		frame.setTitle("Blasterball");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(gp);
		frame.setVisible(true);
	}
}