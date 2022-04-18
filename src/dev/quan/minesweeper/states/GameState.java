package dev.quan.minesweeper.states;

import java.awt.Graphics;

import dev.quan.minesweeper.board.Board;

public class GameState extends State{
	
	private Board board;
	
	public GameState() {
		board = new Board();
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		board.render(g);
	}

}
