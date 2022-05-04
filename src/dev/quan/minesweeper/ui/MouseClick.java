package dev.quan.minesweeper.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import dev.quan.minesweeper.states.State;

// A CLASS FOR MOUSE CLICK
public class MouseClick implements MouseListener{

	private State state;

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2){
			state.mouseClicked(e);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) // left mouse
			state.mouseLeftPressed(e);
		else if(e.getButton() == MouseEvent.BUTTON3) // right mouse
			state.mouseRightPressed(e);
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

	public void setState(State state){
		this.state = state;
	}

}
