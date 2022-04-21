package dev.quan.minesweeper.board;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class Cell {
    private boolean revealed = false;
    private boolean hasbomb;
    private int x,y,w,i,j;
    private int neighborCount;
    Random rand = new Random();
    private int flag;
    private boolean isFlag;
    
    public Cell(int i, int j, int w){
        this.i = i;
        this.j = j;
        this.x = i*w;
        this.y = j*w;
        this.w = w;
        this.neighborCount = 0;
    }

    public boolean contains(int xC, int yC){
        return (xC>x && xC<x+w && yC>y+80 && yC<y+80+w);
    }

    public void reveal(){
        revealed = true;
        if(neighborCount == 0){
            floodFill();
        }
    }

    public void checkFlag(){
        if(isFlag)
            isFlag = false;
        else if(!revealed)
            isFlag = true;
    }

    public void floodFill(){
        for(int xOff=-1; xOff<=1; xOff++){
            for(int yOff=-1; yOff<=1; yOff++){
                int a = i + xOff;
                int b = j + yOff;
                if(a>-1 && a<Board.cols && b>-1 && b<Board.rows){
                    Cell neighbor = Board.grid[a][b];
                    if(!neighbor.hasbomb && !neighbor.revealed && !neighbor.isFlag){
                        neighbor.reveal();
                    }
                    if(!neighbor.hasbomb && !neighbor.revealed && neighbor.isFlag){
                        neighbor.revealed = true;
                        Board.gameOver();
                    }
                }
            }
        }
    }
    
    public int countBombs(){
        if(hasbomb){
            neighborCount = 0;
            return neighborCount;
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

    public int countFlags(){
        if(hasbomb){
            flag = 0;
            return flag;
        }
        
        int total = 0;

        for(int xOff=-1; xOff<=1; xOff++){
            for(int yOff=-1; yOff<=1; yOff++){
                int a = i + xOff;
                int b = j + yOff;
                if(a>-1 && a<Board.cols && b>-1 && b<Board.rows){
                    Cell neighbor = Board.grid[a][b];
                    if(neighbor.isFlag)
                        total++;
                }
            }
        }
        flag = total;
        return total;
    }



    public void render(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(x, y+80, w, w);
        g.setColor(Color.white);
        g.drawRect(x, y+80, w, w);
        if(isFlag){
            g.setColor(Color.green);
            g.fillOval((int)(x + w*0.25), (int)(y+80+w*0.25), (int)(w*0.5), (int)(w*0.5));
        }
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
                if(neighborCount!=0){
                switch(neighborCount){
                    case(1):
                        g.setColor(Color.blue);
                        break;
                    case(2):
                        g.setColor(Color.green);
                        break;
                    case(3):
                        g.setColor(Color.red);
                        break;
                    case(4):
                        g.setColor(Color.CYAN);
                        break;
                    case(5):
                        g.setColor(Color.orange);
                        break;
                    default:
                        g.setColor(Color.pink);
                        break;
                }
                    g.setFont(new Font("Tahoma", 1, 30));
                    g.drawString(Integer.toString(neighborCount), (int)(x + w*0.5-8), y+80+w-13);
                }
                if(isFlag){
                    g.setColor(Color.red);
                    g.drawLine(x, y+80, x+w, y+80+w);
                    g.drawLine(x+w, y+80, x, y+80+w);
                }
            }
        }
    }

    public boolean getHasBomb(){
        return hasbomb;
    }

    public void setHasBomb(boolean hasbomb){
        this.hasbomb = hasbomb;
    }

    public boolean getIsFlag(){
        return isFlag;
    }

    public boolean getRevealed(){
        return revealed;
    }

    public int getNeighborCount(){
        return neighborCount;
    }

    public int getFlag(){
        return flag;
    }
}
