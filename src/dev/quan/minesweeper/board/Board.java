package dev.quan.minesweeper.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import dev.quan.minesweeper.Handler;

public class Board {
	
	public static int mx, my;
	private int w = 50;
	private Handler handler;
	public static final int cols = 30;
	public static final int rows = 16;
	public static Cell[][] grid;

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

	public void mousePressed(MouseEvent e){
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				if(grid[i][j].contains(e.getX(), e.getY())){
					grid[i][j].reveal();
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
