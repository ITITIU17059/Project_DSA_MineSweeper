package dev.quan.minesweeper;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.quan.minesweeper.display.Display;
import dev.quan.minesweeper.states.GameState;
import dev.quan.minesweeper.states.State;
import dev.quan.minesweeper.ui.MouseClick;
import dev.quan.minesweeper.ui.MouseMove;

public class Game implements Runnable{
	
	private Display display;
	
	private String title;
	private int width, height;
	
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private State gameState;
	
	private MouseMove mouseMove;
	private MouseClick mouseClick;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		
		mouseMove = new MouseMove();
		mouseClick = new MouseClick();
	}

	public void init() {
		display = new Display(title,width,height);
		display.getJFrame().addMouseMotionListener(mouseMove);
		display.getJFrame().addMouseListener(mouseClick);
		display.getCanvas().addMouseMotionListener(mouseMove);
		display.getCanvas().addMouseListener(mouseClick);
		
		gameState = new GameState();
		State.setState(gameState);
	}

	@Override
	public void run() {
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		long ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta>=1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000) {
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
		
	}
	
	public void tick() {
		
	}
	
	public void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs==null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, width, height);
		
		if(State.getState() != null)
			State.getState().render(g);
		
		bs.show();
		g.dispose();
	}
	
	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		try {
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Getter and Setter
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}	

}
