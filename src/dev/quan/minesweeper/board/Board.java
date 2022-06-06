package dev.quan.minesweeper.board;

<<<<<<< HEAD
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Stack;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.quan.minesweeper.Handler;
import dev.quan.minesweeper.gfx.ImageLoader;
import dev.quan.minesweeper.states.State;

public abstract class Board {
    protected int w = 50;
    protected static boolean happiness;
    protected int sec = 0;
    protected Cell[][] grid;
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
	protected int[] arrGrid = new int[2];
	protected Stack<int[]> stackGrid;
	protected Stack<Integer> stackList;

	// undo image
	 protected BufferedImage undoImage = ImageLoader.loadImage("C:\\Users\\darkq\\OneDrive\\Máy tính\\2021-2022 Sem 2\\Data structure\\Project_v2\\Project_DSA_MineSweeper\\res\\textures\\undo1.png");

    // Handler
	protected Handler handler;

    // Win condition
	protected boolean victory = false;
	protected static boolean defeat = false;

    // Reset variable
	protected boolean resetter = false;

	// button
	protected BufferedImage homeButton = ImageLoader.loadImage("C:\\Users\\darkq\\OneDrive\\Máy tính\\2021-2022 Sem 2\\Data structure\\Project_v2\\Project_DSA_MineSweeper\\res\\textures\\home.png");
	protected BufferedImage levelButton = ImageLoader.loadImage("C:\\Users\\darkq\\OneDrive\\Máy tính\\2021-2022 Sem 2\\Data structure\\Project_v2\\Project_DSA_MineSweeper\\res\\textures\\level.png");

    public Board(Handler handler, int cols, int rows){
        this.handler = handler;
        Board.rows = rows;
        Board.cols = cols;
		stackGrid = new Stack<int[]>();
		stackList = new Stack<Integer>();
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

	public abstract boolean inUndo(int x, int y);

	public abstract boolean inHome(int x, int y);

	public abstract boolean inLevel(int x, int y);

   	// Game over function
	public static void gameOver(Cell[][] grid){
		happiness = false;
		defeat = true;
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				grid[i][j].reveal(rows, cols, grid);
			}
		}
    }

    public void resetAll(){
		resetter = true;

		startDate = new Date();

		totalFlag = totalBomb;

		happiness = true;
		victory = false;
		defeat = false;
		stackGrid.clear();
		stackList.clear();

		setup();

		resetter = false;
=======
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
=======
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
>>>>>>> master
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
<<<<<<< HEAD
>>>>>>> master
	}
	

    // win condition
	public void isVictory(){
		if(totalRevealed()+totalBomb==cols*rows){
			victory = true;
		}
=======
>>>>>>> master
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

	public void undo(){
		if(stackGrid.empty())
			return;
		int a[] = stackGrid.pop();
		int count = stackList.pop();
		grid[a[0]][a[1]].setRevealed(false);
		for(int i=0; i<count; i++){
			int b[] = Cell.undoCell.pop();
			grid[b[0]][b[1]].setRevealed(false);
		}
	}

	public void changeState(int i){
		handler.getDisplay().getJFrame().setSize(1601,881);
		handler.getDisplay().getCanvas().setPreferredSize(new Dimension(901,331));
		handler.getDisplay().getCanvas().setFocusable(true);
		handler.getDisplay().getJFrame().setLocationRelativeTo(null);
		if(i==1){
			State.setState(handler.getGame().getMenuState());
        	handler.getGame().getMouseClick().setState(handler.getGame().getMenuState());
		}
		else{
			State.setState(handler.getGame().getLevelState());
        	handler.getGame().getMouseClick().setState(handler.getGame().getLevelState());
		}
	}

    // Left mouse press
	public void mouseLeftPressed(MouseEvent e){
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				if(grid[i][j].contains(e.getX(), e.getY())){
					if(!grid[i][j].getIsFlag()){			
						grid[i][j].reveal(rows, cols, grid);
						stackList.push(Cell.count);
						Cell.count = 0;
						int[] a = {i,j};
					    stackGrid.push(a);
					}
					if(grid[i][j].getHasBomb() && !grid[i][j].getIsFlag()){
						gameOver(grid);
						stackGrid.clear();
						stackList.clear();
					}
				}
			}
		}
		if(inSmile(e.getX(), e.getY()))
			resetAll();
		
		if(inUndo(e.getX(), e.getY()))
			undo();

		if(inHome(e.getX(), e.getY()))
			changeState(1);

		if(inLevel(e.getX(), e.getY()))
			changeState(2);
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
					grid[i][j].countFlags(rows, cols, grid);
					if(grid[i][j].getRevealed() && grid[i][j].getNeighborCount()>0 && grid[i][j].getFlag()==grid[i][j].getNeighborCount()){
						grid[i][j].floodFill(rows, cols, grid);
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
				grid[i][j].countBombs(rows, cols, grid);
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
