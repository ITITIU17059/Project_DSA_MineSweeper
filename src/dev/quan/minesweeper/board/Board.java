package dev.quan.minesweeper.board;

import java.awt.Color;
import java.awt.Graphics;

// DEMO OF THE BOARD
public class Board {
	
	public static int mx, my;
	private final int spacing = 5;
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 1280, 800);
		// CREATE A BOARD WITH SIZE 16X9
		for(int i=0; i<=16;i++)
			for(int j=0; j<=9; j++) {
				g.setColor(Color.GRAY);
				// CHECK IF THE MOUSE IS IN THE BOX OR NOT
				if(mx>=spacing+i*80 && mx<i*80+80-spacing && my>=spacing+j*80+80 && my<j*80+80+80-spacing) {
					g.setColor(Color.red);
				}
				g.fillRect(spacing+i*80, spacing+j*80+80, 80-2*spacing, 80-2*spacing);
			}
	}

}
