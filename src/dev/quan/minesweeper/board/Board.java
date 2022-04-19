package dev.quan.minesweeper.board;

import java.awt.Color;
import java.awt.Graphics;

import dev.quan.minesweeper.Handler;

public class Board {
	
	public static int mx, my;
	private int w = 50;
	private Handler handler;
	private int cols = 30;
	private int rows = 16;
	private Cell[][] grid;

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

	public void setup(){
		grid = make2DArray(cols, rows);
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				grid[i][j] = new Cell(i*w, j*w, w);
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
