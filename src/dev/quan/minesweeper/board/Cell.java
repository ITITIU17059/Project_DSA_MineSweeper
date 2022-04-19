package dev.quan.minesweeper.board;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class Cell {
    private boolean revealed = true;
    private boolean hasbomb;
    private int x,y,w,i,j;
    private int neighborCount;
    Random rand = new Random();
    
    public Cell(int i, int j, int w){
        this.i = i;
        this.j = j;
        this.x = i*w;
        this.y = j*w;
        this.w = w;
        this.neighborCount = 0;
        hasBomb();
    }

    public void hasBomb(){
        if(rand.nextInt(100)<20)
            hasbomb = true;
        else
            hasbomb = false;
    }

    public boolean contains(int xC, int yC){
        return (xC>x && xC<x+w && yC>y+80 && yC<y+80+w);
    }

    public void reveal(){
        revealed = true;
    }
    
    public int countBombs(){
        if(hasbomb){
            return -1;
        }
        
        int total = 0;

        for(int xOff=-1; xOff<=1; xOff++){
            for(int yOff=-1; yOff<=1; yOff++){
                int a = i + xOff;
                int b = j + yOff;
                if(a>-1 && a<Board.cols && b>-1 && b<Board.rows){
                    Cell neighbor = Board.grid[a][b];
                    if(neighbor.hasbomb)
                        total++;
                }
            }
        }
        neighborCount = total;
        return total;
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
            else{
                g.setColor(Color.lightGray);
                g.fillRect(x, y+80, w, w);
                g.setColor(Color.white);
                g.drawRect(x, y+80, w, w);
                g.setFont(new Font("Courier New", 1, 17));
                g.setColor(Color.red);
                g.drawString(Integer.toString(neighborCount), (int)(x + w*0.5-1), y+80+w-20);
            }
        }
    }
}
