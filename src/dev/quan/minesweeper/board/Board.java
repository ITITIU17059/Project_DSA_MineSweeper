package dev.quan.minesweeper.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import dev.quan.minesweeper.Handler;

public class Board {
	
	public static int mx, my;
	private int w = 50;
	private Handler handler;
	public static final int cols = 30;
	public static final int rows = 16;
	public static Cell[][] grid;
	private final int totalBomb = 99;
	private Random rand = new Random();

	public Board(Handler handler){
		this.handler = handler;
		setup();
	}
	
	public Cell[][] make2DArray(int cols, int rows){
		Cell[][] arr = new Cell[cols][rows];
		return arr;
	}
	
	public void tick() {
		
	}

	public void gameOver(){
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				grid[i][j].reveal();
			}
		}
	}

	public void mousePressed(MouseEvent e){
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				if(grid[i][j].contains(e.getX(), e.getY())){
					grid[i][j].reveal();
					
					if(grid[i][j].getHasBomb()){
						gameOver();
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
	}

}
