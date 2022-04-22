package dev.quan.minesweeper.states;

import java.awt.Graphics;

import dev.quan.minesweeper.Handler;
import dev.quan.minesweeper.board.Board;
import dev.quan.minesweeper.board.Board_Big;
import dev.quan.minesweeper.board.Board_Small;

// GAMESTATE FOR THE GAME(ADD MENUSTATE AND SCORE STATE LATER)
public class GameState extends State{
	
	private Board bigBoard, smallBoard;
	
	public GameState(Handler handler) {
		super(handler);
		bigBoard = new Board_Big(handler);
		smallBoard = new Board_Small(handler);
		Board.setBoard(bigBoard);
		handler.getGame().getMouseClick().setBoard(Board.getBoard());
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		Board.getBoard().render(g);
		if(!Board.getBoard().getResseter())
			Board.getBoard().isVictory();
	}

}
