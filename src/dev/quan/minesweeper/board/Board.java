package dev.quan.minesweeper.board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import dev.quan.minesweeper.Handler;

public class Board {
	
	// Width of the cell
	private int w = 50;

	// Handler
	private Handler handler;

	// Colums and rows of the board
	public static final int cols = 30;
	public static final int rows = 16;

	// Smiley face variables
	private int smileyX = 725;
	private int smileyY = 5;
	private int smileyCenterX = smileyX + 35;
	private int smileyCenterY = smileyY + 35;
	private static boolean happiness;

	//time counter variables
	private int timeX = 1340;
	private int timeY = 5;
	private int sec = 0;
	Date startDate = new Date();

	// array of grid
	public static Cell[][] grid;

	// Total bomb 
	private final int totalBomb = 99;

	// Flag counter variables
	private int totalFlag = totalBomb;
	private boolean fLag_mark;
	private int flagX = 5;
	private int flagY = 5;
	
	// Random variable
	private Random rand = new Random();

	// Win condition
	private boolean victory = false;
	private static boolean defeat = false;

	// Reset variable
	private boolean resetter = false;


	public Board(Handler handler){
		this.handler = handler;
		happiness = true;
		setup();
	}
	
	// Create grid array
	public Cell[][] make2DArray(int cols, int rows){
		Cell[][] arr = new Cell[cols][rows];
		return arr;
	}
	
	public void tick() {

	}

	// Game over function
	public static void gameOver(){
		happiness = false;
		defeat = true;
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				grid[i][j].reveal();
			}
		}
	}

	// Check if the mouse is in smiley face
	public boolean inSmile(int x, int y){
		int dif = (int)Math.sqrt(Math.abs(x-smileyCenterX)*Math.abs(x-smileyCenterX)+
		Math.abs(y-smileyCenterY)*Math.abs(y-smileyCenterY));
		if(dif<35)
			return true;
		return false;
	}

	// reset function
	public void resetAll(){
		resetter = true;

		startDate = new Date();

		totalFlag = totalBomb;

		happiness = true;
		victory = false;
		defeat = false;
		setup();

		resetter = false;
	}

	// win condition
	public void isVictory(){
		if(totalRevealed()+totalBomb==cols*rows){
			victory = true;
		}
	}

	// Total cell that is reveal
	public int totalRevealed(){
		int isRevealed = 0;
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				if(grid[i][j].getRevealed())
					isRevealed++;
			}
		}
		return isRevealed;
	}

	// Left mouse press
	public void mouseLeftPressed(MouseEvent e){
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				if(grid[i][j].contains(e.getX(), e.getY())){
					if(!grid[i][j].getIsFlag())						
						grid[i][j].reveal();
					
					if(grid[i][j].getHasBomb()){
						gameOver();
					}
				}
			}
		}
		if(inSmile(e.getX(), e.getY()))
			resetAll();
	}

	// Right mouse press
	public void mouseRightPressed(MouseEvent e){
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				if(grid[i][j].contains(e.getX(), e.getY())){
					grid[i][j].checkFlag();
					fLag_mark = grid[i][j].getIsFlag();
					if(fLag_mark)
						totalFlag--;
					else
						totalFlag++;
				}
			}
		}
	}

	// Mouse click
	public void mouseClicked(MouseEvent e){
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				if(grid[i][j].contains(e.getX(), e.getY())){
					grid[i][j].countFlags();
					if(grid[i][j].getRevealed() && grid[i][j].getNeighborCount()>0 && grid[i][j].getFlag()==grid[i][j].getNeighborCount()){
						grid[i][j].floodFill();
					}
				}
			}
		}
	}

	// Set up for the grid
	public void setup(){

		//add cell to grid
		grid = make2DArray(cols, rows);
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				grid[i][j] = new Cell(i, j, w);
			}
		}

		// List all the index of the cell
		ArrayList<int[]> options = new ArrayList<int[]>();
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				int[] option = {i,j};
				options.add(option);
			}
		}

		// Randomly setting the bomb to the cell
		for(int n=0; n<totalBomb; n++){
			int size = options.size();
			int index = rand.nextInt(size);
			int[] choice = options.get(index);
			int i = choice[0];
			int j = choice[1];
			options.remove(index);
			grid[i][j].setHasBomb(true);
		}

		// Check the neighbor of all the cell has bomb all not
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				grid[i][j].countBombs();
			}
		}
	}
	
	public void render(Graphics g) {
		
		// Draw the cell
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				grid[i][j].render(g);
			}
		}

		// Smiley face painting

		g.setColor(Color.yellow);
		g.fillOval(smileyX, smileyY, 70, 70);
		g.setColor(Color.black);
		g.fillOval(smileyX+15, smileyY+20, 10, 10);
		g.fillOval(smileyX+45, smileyY+20, 10, 10);
		
		if(happiness){
			g.fillRect(smileyX+20, smileyY+50, 30, 5);
			g.fillRect(smileyX+17, smileyY+45, 5, 5);
			g.fillRect(smileyX+48, smileyY+45, 5, 5);
		}
		else{
			g.fillRect(smileyX+20, smileyY+45, 30, 5);
			g.fillRect(smileyX+17, smileyY+50, 5, 5);
			g.fillRect(smileyX+48, smileyY+50, 5, 5);
		}

		// time counter painting

		g.setColor(Color.WHITE);
		g.fillRect(timeX, timeY, 140, 70);
		if(!defeat && !victory)
			sec = (int)((new Date().getTime() - startDate.getTime()) / 1000);
		if(sec>999)
			sec = 999;
		g.setColor(Color.black);
		if(victory)
			g.setColor(Color.green);
		else if(defeat)
			g.setColor(Color.red);
		g.setFont(new Font("Tahoma", Font.PLAIN, 80));
		if(sec<10)
			g.drawString("00"+Integer.toString(sec), timeX, timeY+65);
		else if(sec<100)
			g.drawString("0"+Integer.toString(sec), timeX, timeY+65);
		else
			g.drawString(Integer.toString(sec), timeX, timeY+65);

		//Count flag painting
		
		g.setColor(Color.WHITE);
		g.fillRect(flagX, flagY, 140, 70);
		if(totalFlag>totalBomb)
			totalFlag = totalBomb;
		g.setColor(Color.black);
		g.setFont(new Font("Tahoma", Font.PLAIN, 80));
		if(totalFlag<10)
			g.drawString("00"+Integer.toString(totalFlag), flagX, flagY+65);
		else if(totalFlag<100)
			g.drawString("0"+Integer.toString(totalFlag), flagX, flagY+65);
		else
			g.drawString(Integer.toString(totalFlag), flagX, flagY+65);
	}	

	// Getter and setter
	public boolean getResseter(){
		return resetter;
	}

}
