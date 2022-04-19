package dev.quan.minesweeper.board;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Cell {
    private boolean revealed = true;
    private boolean hasbomb;
    private int x,y,w;
    Random rand = new Random();
    
    public Cell(int x, int y, int w){
        this.x = x;
        this.y = y;
        this.w = w;
        hasBomb();
    }

    public void hasBomb(){
        if(rand.nextInt(100)<20)
            hasbomb = true;
        else
            hasbomb = false;
    }

    public void render(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(x, y+80, w, w);
        g.setColor(Color.white);
        g.drawRect(x, y+80, w, w);
        if(revealed){
            if(hasbomb){
                g.setColor(Color.black);
                g.fillOval((int)(x + w*0.25), (int)(y+80+w*0.25), (int)(w*0.5), (int)(w*0.5));
            }
        }
    }
}
