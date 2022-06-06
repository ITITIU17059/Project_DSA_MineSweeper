package dev.quan.minesweeper.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

<<<<<<< HEAD
import dev.quan.minesweeper.states.State;

// A CLASS FOR MOUSE CLICK
public class MouseClick implements MouseListener{

	private State state;
=======
import dev.quan.minesweeper.board.Board;

// A CLASS FOR MOUSE CLICKclear
public class MouseClick implements MouseListener{

	private int mouseX, mouseY;
	private Board board;
>>>>>>> master

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2){
<<<<<<< HEAD
			state.mouseClicked(e);
=======
			board.mouseClicked(e);
>>>>>>> master
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
<<<<<<< HEAD
		if(e.getButton() == MouseEvent.BUTTON1) // left mouse
			state.mouseLeftPressed(e);
		else if(e.getButton() == MouseEvent.BUTTON3) // right mouse
			state.mouseRightPressed(e);
=======
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(e.getButton() == MouseEvent.BUTTON1) // left mouse
			board.mouseLeftPressed(e);
		else if(e.getButton() == MouseEvent.BUTTON3) // right mouse
			board.mouseRightPressed(e);
>>>>>>> master
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

<<<<<<< HEAD
	public void setState(State state){
		this.state = state;
=======
	public void setBoard(Board board){
		this.board = board;
>>>>>>> master
	}

}
