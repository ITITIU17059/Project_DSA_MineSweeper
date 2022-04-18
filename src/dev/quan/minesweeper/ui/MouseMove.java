package dev.quan.minesweeper.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import dev.quan.minesweeper.board.Board;

public class MouseMove implements MouseMotionListener{

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Board.mx = e.getX();
		Board.my = e.getY();
	}

}
