package dev.quan.minesweeper.states;

import java.awt.Graphics;

import dev.quan.minesweeper.Handler;
import dev.quan.minesweeper.board.Board;

// GAMESTATE FOR THE GAME(ADD MENUSTATE AND SCORE STATE LATER)
public class GameState extends State{
	
	private Board board;
	
	public GameState(Handler handler) {
		super(handler);
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
