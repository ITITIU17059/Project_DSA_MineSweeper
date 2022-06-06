package dev.quan.minesweeper.board;

import java.awt.Graphics;
import java.util.Date;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import dev.quan.minesweeper.Handler;

public class Board_Medium extends Board{
    // Smiley face variables
	private int smileyX = 375;
	private int smileyY = 5;
	private int smileyCenterX = smileyX + 35;
	private int smileyCenterY = smileyY + 35;

	//time counter variables
	private int timeX = 661;
	private int timeY = 5;

	//undo variables
	private int undoX = 807;
	private int undoY = 80;
	private int undoCenterX = undoX + 37;
	private int undoCenterY = undoY + 37;

	// home variables
	private int homeX = 697;
	private int homeY = 100;
	private int homeCenterX = homeX + 150;
	private int homeCenterY = homeY + 150;

	// level variables
	private int levelX = 695;
	private int levelY = 220;
	private int levelCenterX = levelX + 150;
	private int levelCenterY = levelY + 150;

	

	public Board_Medium(Handler handler){
		super(handler, 16, 16);
		totalBomb = 40;
		totalFlag = totalBomb;
		startDate = new Date();
		handler.getDisplay().getJFrame().setSize(901,921);
		handler.getDisplay().getCanvas().setPreferredSize(new Dimension(901,331));
		handler.getDisplay().getCanvas().setFocusable(true);
		handler.getDisplay().getJFrame().setLocationRelativeTo(null);
		setup();
	}
	
	public void tick() {

	}

	// Check if the mouse is in smiley face
	public boolean inSmile(int x, int y){
		int dif = (int)Math.sqrt(Math.abs(x-smileyCenterX)*Math.abs(x-smileyCenterX)+
		Math.abs(y-smileyCenterY)*Math.abs(y-smileyCenterY));
		if(dif<35)
			return true;
		return false;
	}

	public boolean inUndo(int x, int y){
		int dif = (int)Math.sqrt(Math.abs(x-undoCenterX)*Math.abs(x-undoCenterX)+
		Math.abs(y-undoCenterY)*Math.abs(y-undoCenterY));
		if(dif<37)
			return true;
		return false;
	}

	// Check if the mouse is in home button
	public boolean inHome(int x, int y){
		int dif = (int)Math.sqrt(Math.abs(x-homeCenterX)*Math.abs(x-homeCenterX)+
		Math.abs(y-homeCenterY)*Math.abs(y-homeCenterY));
		if(dif<35)
			return true;
		return false;
	}

	// Check if the mouse is in level button
	public boolean inLevel(int x, int y){
		int dif = (int)Math.sqrt(Math.abs(x-levelCenterX)*Math.abs(x-levelCenterX)+
		Math.abs(y-levelCenterY)*Math.abs(y-levelCenterY));
		if(dif<35)
			return true;
		return false;
	}
	
	public void render(Graphics g) {
		
		// Draw the cell
		for(int i=0; i<cols; i++){
			for(int j=0; j<rows; j++){
				if(grid[i][j]!=null)
					grid[i][j].render(g);
			}
		}

		// Smiley face painting

		g.setColor(Color.yellow);
		g.fillOval(smileyX, smileyY, 70, 70);
		g.setColor(Color.black);
		g.fillOval(smileyX+15, smileyY+20, 10, 10);
		g.fillOval(smileyX+45, smileyY+20, 10, 10);
		
		if(happiness){
			g.fillRect(smileyX+20, smileyY+50, 30, 5);
			g.fillRect(smileyX+17, smileyY+45, 5, 5);
			g.fillRect(smileyX+48, smileyY+45, 5, 5);
		}
		else{
			g.fillRect(smileyX+20, smileyY+45, 30, 5);
			g.fillRect(smileyX+17, smileyY+50, 5, 5);
			g.fillRect(smileyX+48, smileyY+50, 5, 5);
		}

		// time counter painting

		g.setColor(Color.WHITE);
		g.fillRect(timeX, timeY, 140, 70);
		if(!defeat && !victory)
			sec = (int)((new Date().getTime() - startDate.getTime()) / 1000);
		if(sec>999)
			sec = 999;
		g.setColor(Color.black);
		if(victory)
			g.setColor(Color.green);
		else if(defeat)
			g.setColor(Color.red);
		g.setFont(new Font("Tahoma", Font.PLAIN, 80));
		if(sec<10)
			g.drawString("00"+Integer.toString(sec), timeX, timeY+65);
		else if(sec<100)
			g.drawString("0"+Integer.toString(sec), timeX, timeY+65);
		else
			g.drawString(Integer.toString(sec), timeX, timeY+65);

		//Count flag painting
		
		g.setColor(Color.WHITE);
		g.fillRect(flagX, flagY, 140, 70);
		if(totalFlag>totalBomb)
			totalFlag = totalBomb;
		g.setColor(Color.black);
		g.setFont(new Font("Tahoma", Font.PLAIN, 80));
		if(totalFlag<10)
			g.drawString("00"+Integer.toString(totalFlag), flagX, flagY+65);
		else if(totalFlag<100)
			g.drawString("0"+Integer.toString(totalFlag), flagX, flagY+65);
		else
			g.drawString(Integer.toString(totalFlag), flagX, flagY+65);

		// undo button painting
		g.drawImage(undoImage,undoX, undoY, 74, 74,null);

		// home button painting
		g.drawImage(homeButton,homeX, homeY, 300, 300,null);
		
		// level button painting
		g.drawImage(levelButton,levelX, levelY, 300, 300,null);
	}
}
