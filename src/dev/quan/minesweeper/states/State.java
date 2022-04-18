package dev.quan.minesweeper.states;

import java.awt.Graphics;

import dev.quan.minesweeper.Handler;

// ABSTRACT CLASS FOR THE STATE
public abstract class State {
	
	private static State currentstate = null;

	protected Handler handler;

	public State(Handler handler){
		this.handler = handler;
	}
	
	public static void setState(State state) {
		currentstate = state;
	}
	
	public static State getState() {
		return currentstate;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
