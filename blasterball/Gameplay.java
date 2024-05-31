package blasterball;

//where everything gets defined and drawn and stuff

/**
I plan on adding sounds effects for when the ball hits a brick/wall/paddle/etc.
Everything else is good to go
**/

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JPanel;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.lang.NullPointerException;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Gameplay extends JPanel implements ActionListener, KeyListener {
	
	private boolean play = false;
	private int score = 0;
	private int totalbricks = 36;
	
	//Timer
	private Timer timer;
	private int delay = 8;
	
	//position of paddle
	private int playerX = 310;
	
	//position of ball
	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	private MapGenerator map;
	
	//stuff for audioString name;
	String name2;
	Long currFrame;
	//AudioInputStream ais;
	AudioInputStream ais2;
    //Clip c;
	Clip c2;
	//File temp;
	
	public Gameplay() throws IOException, UnsupportedAudioFileException, LineUnavailableException, FileNotFoundException {
		//audio stuff first
		name2 = "blasterball/BossFightTheme.wav";
		ais2 = AudioSystem.getAudioInputStream(new File(name2).getAbsoluteFile());
		c2 = AudioSystem.getClip();
		
		map = new MapGenerator(4,9);
        addKeyListener(this); //detect keys
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start(); //starts game cycle
		
		
	}
	
	//paint everything
	public void paint(Graphics g) {
		//background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		//drawing map
		map.draw((Graphics2D) g);
		
		//window border
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(681, 0, 3, 592);
		
		//paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		//ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		//scores
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("" + score, 590, 30);
		
		//win
		if(totalbricks <= 0) {
			play = false;
			ballXdir = 0;
		    ballYdir = 0;
		    g.setColor(Color.RED);
		    g.setFont(new Font("serif", Font.BOLD, 30));
		    g.drawString("You Won", 260, 300);
		             
		    g.setColor(Color.RED);
		    g.setFont(new Font("serif", Font.BOLD, 20));           
		    g.drawString("Press (Enter) to Restart", 230, 350);
			
			try {
				System.out.println("Closing clip (win)");
				c2.close();
				System.out.println("Resetting (win)");
				resetClip2();
			}
			catch(Exception ex4) {
				System.out.println(ex4);
			}
			//c2.close();
			//resetClip2();
		}
		
		//lose
		if(ballposY > 570) {
			play = false;
			ballXdir = 0;
		    ballYdir = 0;
		    g.setColor(Color.RED);
		    g.setFont(new Font("serif", Font.BOLD, 30));
		    g.drawString("Game Over, Scores: " + score, 190, 300);
		             
		    g.setColor(Color.RED);
		    g.setFont(new Font("serif", Font.BOLD, 20));           
		    g.drawString("Press (Enter) to Restart", 230, 350);
			
			try {
				System.out.println("Closing clip (lose)");
				c2.close();
				System.out.println("Resetting (lose)");
				resetClip2();
			}
			catch(Exception ex4) {
				System.out.println(ex4);
			}
		}
		
		g.dispose();

	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) { //press right key, go right
			if(playerX >= 600) { //making sure the player is still in bounds
				playerX = 600;
			}
			else {
				moveRight();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) { //press left key, go left
			if(playerX < 10) { //making sure the player is still in bounds
				playerX = 10;
			}
			else {
				moveLeft();
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) { //press enter to restart
		System.out.println("You pressed enter");
		
			if(!play) {
				//restarting game
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				totalbricks = 36;
				map = new MapGenerator(4, 9);
				
				try {
					c2.start();
					System.out.println("Opening clip");
					c2.open(ais2);
					System.out.println("Looping");
					c2.loop(Clip.LOOP_CONTINUOUSLY);
					//System.out.println("Resetting");
					//resetClip2();
				}
				catch(Exception ex) {
					System.out.println(ex);
					System.exit(0);
				}
			
				repaint(); 
			}
		}
	}
	
	public void resetClip2() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        ais2 = AudioSystem.getAudioInputStream(new File(name2).getAbsoluteFile());
		System.out.println("audio system reset");
		c2 = AudioSystem.getClip();
		System.out.println("clip reset");
    }
	
	//move paddle left/right
	public void moveLeft() {
		play = true;
		playerX -=15;		
	}

	public void moveRight() {
		play = true;
		playerX += 15;
	}
	
	public void actionPerformed(ActionEvent e) { //time to get the ball moving
		timer.start();
		if(play) {
			
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) { //for ball and paddle collision
				ballYdir = -ballYdir;
			}
			
			//checking map collision with ball (I have no clue what's going on here)
			A: for(int i = 0; i < map.map.length; i++) {
				for(int j = 0; j < map.map[0].length; j++) {
					
					if(map.map[i][j] > 0) {
						//for intersection we need to first detect the position of ball and brick with respect to height and width of the brick (???)
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);					
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							score+=5;	
							totalbricks--;
							
							// when ball hits right or left side of brick
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
							}
							else { //or when the ball hits the bottom of the brick
								ballYdir = -ballYdir;
							}
							
							break A;
						}
					}
				}
			}
			
			//move the ball once the game starts
			ballposX += ballXdir;
			ballposY += ballYdir;
			
			if(ballposX < 0) { //"turn around"
				ballXdir = -ballXdir;
			}
			if(ballposY < 0) {
				ballYdir = -ballYdir;
			}
			if(ballposX > 670) {
				ballXdir = -ballXdir;
			}
			
			repaint();
		}
		
		
	}
	
	
}