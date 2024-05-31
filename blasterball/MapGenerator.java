package blasterball;

//this makes all of the bricks

/**
Still need to fix the color issue (I wanted to randomize the color of the bricks each time the player starts a new game
without potetially giving them a seizure), but other than that the code works fine
**/

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
//import java.util.Random;

public class MapGenerator {
	public int map[][];
	public int brickWidth;
	public int brickHeight;
	//public Random r;
	//public int whichColor;
	
	public MapGenerator (int row, int col) {
		map = new int[row][col];
		//putting bricks in rows and columns
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				map[i][j] = 1; //1 = ball is not touching brick
			}
		}
		
		brickWidth = 540/col;
		brickHeight = 150/row;
	}
	
	//drawing everything
	public void draw(Graphics2D g) {
		//r = new Random(); //random colors will be assigned to the bricks
		//whichColor = 0;
		//whichColor = r.nextInt(5);
		for(int i = 0; i < map.length; i++) {
		
			for(int j = 0; j < map[0].length; j++) {
				
				if(map[i][j] > 0) {
					//whichColor = r.nextInt(5);
					//setting color based on random number
					/*
					if(whichColor == 1) {
						g.setColor(Color.red);
					}
					else if(whichColor == 2) {
						g.setColor(Color.blue);
					}
					else if(whichColor == 3) {
						g.setColor(Color.magenta);
					}
					else if(whichColor == 4) {
						g.setColor(Color.orange);
					}
					else if(whichColor == 5) {
						g.setColor(Color.pink);
					}
					else {
						g.setColor(Color.white);
					}
					*/
					
					g.setColor(Color.white);
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					
					// this is just to show separate brick, game can still run without it
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);	
					
				}
				
			}
		}
		
	}
	
	public void setBrickValue(int value, int row, int col) { //changes value of brick to 0 when the ball hits it
		map[row][col] = value;
	}
	
}