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
	
	public static int mx, my;
	private int w = 50;
	private Handler handler;
	public static final int cols = 30;
	public static final int rows = 16;
	private int smileyX = 725;
	private int smileyY = 5;
	private int smileyCenterX = smileyX + 35;
	private int smileyCenterY = smileyY + 35;
	private int timeX = 1340;
	private int timeY = 5;
	private int sec = 0;
	Date startDate = new Date();
	private static boolean happiness;
	public static Cell[][] grid;
	private final int totalBomb = 10;
	private Random rand = new Random();
	private boolean victory = false;
	private static boolean defeat = false;
	private int noBomb = cols*rows - totalBomb;
	private boolean resetter = false;


	public Board(Handler handler){
		this.handler = handler;
		happiness = true;
		setup();
	}
	
	public Cell[][] make2DArray(int cols, int rows){
		Cell[][] arr = new Cell[cols][rows];
		return arr;
	}
	
	public void tick() {

	}

	public static void gameOver(){
		happiness = false;
		defeat = true;
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				grid[i][j].reveal();
			}
		}
	}

	public boolean inSmile(int x, int y){
		int dif = (int)Math.sqrt(Math.abs(x-smileyCenterX)*Math.abs(x-smileyCenterX)+
		Math.abs(y-smileyCenterY)*Math.abs(y-smileyCenterY));
		if(dif<35)
			return true;
		return false;
	}

	public void resetAll(){
		resetter = true;

		startDate = new Date();

		happiness = true;
		victory = false;
		defeat = false;
		setup();

		resetter = false;
	}

	public void isVictory(){
		if(totalRevealed()+totalBomb==cols*rows){
			victory = true;
		}
	}

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

	public void mouseLeftPressed(MouseEvent e){
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				if(grid[i][j].contains(e.getX(), e.getY())){
					grid[i][j].reveal();
					isVictory();
					
					if(grid[i][j].getHasBomb()){
						gameOver();
					}
				}
			}
		}
		if(inSmile(e.getX(), e.getY()))
			resetAll();
	}

	public void mouseRightPressed(MouseEvent e){
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				if(grid[i][j].contains(e.getX(), e.getY())){
					grid[i][j].checkFlag();
				}
			}
		}
	}

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

	public void setup(){
		grid = make2DArray(cols, rows);
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				grid[i][j] = new Cell(i, j, w);
			}
		}

		ArrayList<int[]> options = new ArrayList<int[]>();
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				int[] option = {i,j};
				options.add(option);
			}
		}

		for(int n=0; n<totalBomb; n++){
			int size = options.size();
			int index = rand.nextInt(size);
			int[] choice = options.get(index);
			int i = choice[0];
			int j = choice[1];
			options.remove(index);
			grid[i][j].setHasBomb(true);
		}


		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				grid[i][j].countBombs();
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				grid[i][j].render(g);
			}
		}
		g.setColor(Color.yellow);
		g.fillOval(smileyX, smileyY, 70, 70);
		g.setColor(Color.black);
		g.fillOval(smileyX+15, smileyY+20, 10, 10);
		g.fillOval(smileyX+45, smileyY+20, 10, 10);

		// Smiley face painting
		
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
	}

	public boolean getResseter(){
		return resetter;
	}

}
