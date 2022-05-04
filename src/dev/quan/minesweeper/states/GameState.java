package dev.quan.minesweeper.states;

import java.awt.Graphics;

import dev.quan.minesweeper.Handler;
import dev.quan.minesweeper.board.Board;
import dev.quan.minesweeper.board.Board_Big;
import dev.quan.minesweeper.board.Board_Medium;
import dev.quan.minesweeper.board.Board_Small;
import java.awt.event.MouseEvent;

// GAMESTATE FOR THE GAME(ADD MENUSTATE AND SCORE STATE LATER)
public class GameState extends State{
	
	private Board board;
	private int count = 2;
	
	public GameState(Handler handler) {
		super(handler);
		if(count==1)
			board = new Board_Big(handler);
		else if(count==2){
			board = new Board_Medium(handler);
		}
		else
			board = new Board_Small(handler);
		Board.setBoard(board);
		handler.getGame().getMouseClick().setState(this);
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

	public void mouseLeftPressed(MouseEvent e){
		Board.getBoard().mouseLeftPressed(e);
	}

	public void mouseRightPressed(MouseEvent e){
		Board.getBoard().mouseRightPressed(e);
	}

	public void mouseClicked(MouseEvent e){
		Board.getBoard().mouseClicked(e);
	}

}
