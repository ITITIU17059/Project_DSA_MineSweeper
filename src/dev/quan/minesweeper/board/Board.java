package dev.quan.minesweeper.board;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.awt.event.MouseEvent;
import java.awt.Graphics;

import dev.quan.minesweeper.Handler;

public abstract class Board {
    protected int w = 50;
    protected static boolean happiness;
    protected int sec = 0;
    protected static Cell[][] grid;
    protected boolean fLag_mark;
	protected int flagX = 5;
	protected int flagY = 5;
    protected Random rand = new Random();
    protected static int cols;
    protected static int rows;
    protected int totalBomb;
    protected int totalFlag;
    protected Date startDate;
    private static Board currentBoard = null;

    // Handler
	protected Handler handler;

    	// Win condition
	protected boolean victory = false;
	protected static boolean defeat = false;

    	// Reset variable
	protected boolean resetter = false;

    public Board(Handler handler, int cols, int rows){
        this.handler = handler;
        Board.rows = rows;
        Board.cols = cols;
        happiness = true;
    }

    // Create grid array
    public Cell[][] make2DArray(int cols, int rows){
		Cell[][] arr = new Cell[cols][rows];
		return arr;
	}

    // Check if the mouse is in smiley face
    public abstract boolean inSmile(int x, int y);

    public abstract void tick();
    
    public abstract void render(Graphics g);

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

    // Getter and setter
	public boolean getResseter(){
		return resetter;
	}

    public static void setBoard(Board board){  
        currentBoard = board;
    }

    public static Board getBoard(){
        return currentBoard;
    }

    public int getCols(){
        return cols;
    }
    
    public int getRows(){
        return rows;
    }

}
