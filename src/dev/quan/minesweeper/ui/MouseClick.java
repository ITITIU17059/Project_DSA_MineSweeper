package dev.quan.minesweeper.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import dev.quan.minesweeper.board.Board;

// A CLASS FOR MOUSE CLICKclear
public class MouseClick implements MouseListener{

	private int mouseX, mouseY;
	private Board board;

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2){
			board.mouseClicked(e);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(e.getButton() == MouseEvent.BUTTON1) // left mouse
			board.mouseLeftPressed(e);
		else if(e.getButton() == MouseEvent.BUTTON3) // right mouse
			board.mouseRightPressed(e);
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

	public void setBoard(Board board){
		this.board = board;
	}

}
