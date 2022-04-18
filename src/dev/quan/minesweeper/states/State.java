package dev.quan.minesweeper.states;

import java.awt.Graphics;

public abstract class State {
	
	private static State currentstate = null;
	
	public static void setState(State state) {
		currentstate = state;
	}
	
	public static State getState() {
		return currentstate;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
