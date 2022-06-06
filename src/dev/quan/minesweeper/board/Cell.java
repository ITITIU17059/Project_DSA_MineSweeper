package dev.quan.minesweeper.board;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.Stack;

import dev.quan.minesweeper.gfx.ImageLoader;

import java.awt.image.BufferedImage;
=======
>>>>>>> master
=======
>>>>>>> master

public class Cell {

    //Cell has bomb or flag or nothing
    private boolean revealed = false;
    private boolean hasbomb;
    
    // Cell variables
    private int x,y,w,i,j;

    // Variable of total bombs that near the current cell
    private int neighborCount;
    Random rand = new Random();

    // Flag variables
    private int flag;
    private boolean isFlag;
<<<<<<< HEAD
<<<<<<< HEAD

    public static Stack<int[]> undoCell = new Stack<int[]>();
    public static int count = 0;

    private Board Board;

    // bomb image
    private BufferedImage bombImage = ImageLoader.loadImage("C:\\Users\\darkq\\OneDrive\\Máy tính\\2021-2022 Sem 2\\Data structure\\Project_v2\\Project_DSA_MineSweeper\\res\\textures\\bomb3.png");
    
    // flag image
    private BufferedImage flagImage = ImageLoader.loadImage("C:\\Users\\darkq\\OneDrive\\Máy tính\\2021-2022 Sem 2\\Data structure\\Project_v2\\Project_DSA_MineSweeper\\res\\textures\\flag.png");

=======
    
>>>>>>> master
=======
    
>>>>>>> master
    // Constructor
    public Cell(int i, int j, int w){
        this.i = i;
        this.j = j;
        this.x = i*w;
        this.y = j*w;
        this.w = w;
<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> master
=======
>>>>>>> master
        this.neighborCount = 0;
    }

    // Check if the mouse is in the cell
    public boolean contains(int xC, int yC){
        return (xC>x && xC<x+w && yC>y+80 && yC<y+80+w);
    }

    // Make the cell revealed
<<<<<<< HEAD
<<<<<<< HEAD
    public void reveal(int rows, int cols, Cell[][] grid){
        revealed = true;
        if(neighborCount == 0){
            floodFill(rows, cols, grid);
=======
=======
>>>>>>> master
    public void reveal(){
        revealed = true;
        if(neighborCount == 0){
            floodFill();
<<<<<<< HEAD
>>>>>>> master
=======
>>>>>>> master
        }
    }

    // flag setting for right click 
    public void checkFlag(){
        if(isFlag)
            isFlag = false;
        else if(!revealed)
            isFlag = true;
    }

    // Expand the cell function
<<<<<<< HEAD
<<<<<<< HEAD
    public void floodFill(int rows, int cols, Cell[][] grid){
=======
    public void floodFill(){
>>>>>>> master
=======
    public void floodFill(){
>>>>>>> master
        for(int xOff=-1; xOff<=1; xOff++){
            for(int yOff=-1; yOff<=1; yOff++){
                int a = i + xOff;
                int b = j + yOff;
<<<<<<< HEAD
<<<<<<< HEAD
                if(a>-1 && a<cols && b>-1 && b<rows){
                    Cell neighbor = grid[a][b];
                    if(!neighbor.hasbomb && !neighbor.revealed && !neighbor.isFlag){
                        neighbor.reveal(rows, cols, grid);
                        int ar[] = {a,b};
                        undoCell.push(ar);
                        count++;
                    }
                    if(!neighbor.hasbomb && !neighbor.revealed && neighbor.isFlag){
                        neighbor.revealed = true;
                        Board.gameOver(grid);
=======
=======
>>>>>>> master
                if(a>-1 && a<Board.cols && b>-1 && b<Board.rows){
                    Cell neighbor = Board.grid[a][b];
                    if(!neighbor.hasbomb && !neighbor.revealed && !neighbor.isFlag){
                        neighbor.reveal();
                    }
                    if(!neighbor.hasbomb && !neighbor.revealed && neighbor.isFlag){
                        neighbor.revealed = true;
                        Board.gameOver();
<<<<<<< HEAD
>>>>>>> master
=======
>>>>>>> master
                    }
                }
            }
        }
    }
    
    // Return the total neighbor's bomb 
<<<<<<< HEAD
<<<<<<< HEAD
    public int countBombs(int rows, int cols, Cell[][] grid){
=======
    public int countBombs(){
>>>>>>> master
=======
    public int countBombs(){
>>>>>>> master
        if(hasbomb){
            neighborCount = 0;
            return neighborCount;
        }
        
        int total = 0;

        for(int xOff=-1; xOff<=1; xOff++){
            for(int yOff=-1; yOff<=1; yOff++){
                int a = i + xOff;
                int b = j + yOff;
<<<<<<< HEAD
<<<<<<< HEAD
                if(a>-1 && a<cols && b>-1 && b<rows){
                    Cell neighbor = grid[a][b];
=======
                if(a>-1 && a<Board.cols && b>-1 && b<Board.rows){
                    Cell neighbor = Board.grid[a][b];
>>>>>>> master
=======
                if(a>-1 && a<Board.cols && b>-1 && b<Board.rows){
                    Cell neighbor = Board.grid[a][b];
>>>>>>> master
                    if(neighbor.hasbomb)
                        total++;
                }
            }
        }
        neighborCount = total;
        return total;
    }

    // Return the total neighbor's flag 
<<<<<<< HEAD
<<<<<<< HEAD
    public int countFlags(int rows, int cols, Cell[][] grid){
=======
    public int countFlags(){
>>>>>>> master
=======
    public int countFlags(){
>>>>>>> master
        if(hasbomb){
            flag = 0;
            return flag;
        }
        
        int total = 0;

        for(int xOff=-1; xOff<=1; xOff++){
            for(int yOff=-1; yOff<=1; yOff++){
                int a = i + xOff;
                int b = j + yOff;
<<<<<<< HEAD
<<<<<<< HEAD
                if(a>-1 && a<cols && b>-1 && b<rows){
                    Cell neighbor = grid[a][b];
=======
                if(a>-1 && a<Board.cols && b>-1 && b<Board.rows){
                    Cell neighbor = Board.grid[a][b];
>>>>>>> master
=======
                if(a>-1 && a<Board.cols && b>-1 && b<Board.rows){
                    Cell neighbor = Board.grid[a][b];
>>>>>>> master
                    if(neighbor.isFlag)
                        total++;
                }
            }
        }
        flag = total;
        return total;
    }



    public void render(Graphics g){

        // Draw cells
        g.setColor(Color.blue);
        g.fillRect(x, y+80, w, w);
        g.setColor(Color.white);
        g.drawRect(x, y+80, w, w);

        // Draw flags
        if(isFlag){
            g.setColor(Color.green);
<<<<<<< HEAD
<<<<<<< HEAD
            g.drawImage(flagImage, (int)(x-8), (int)(y+72), (int)(w+20), (int)(w+20), null);
=======
            g.fillOval((int)(x + w*0.25), (int)(y+80+w*0.25), (int)(w*0.5), (int)(w*0.5));
>>>>>>> master
=======
            g.fillOval((int)(x + w*0.25), (int)(y+80+w*0.25), (int)(w*0.5), (int)(w*0.5));
>>>>>>> master
        }
        if(revealed){
            
            // Draw bombs
            if(hasbomb){
                g.setColor(Color.black);
<<<<<<< HEAD
<<<<<<< HEAD
                g.fillRect(x, y+80, w, w);
                g.drawImage(bombImage, (int)(x + w*0.05), (int)(y+80+w*0.05), (int)(w*0.9), (int)(w*0.9), null);
=======
                g.fillOval((int)(x + w*0.25), (int)(y+80+w*0.25), (int)(w*0.5), (int)(w*0.5));
>>>>>>> master
=======
                g.fillOval((int)(x + w*0.25), (int)(y+80+w*0.25), (int)(w*0.5), (int)(w*0.5));
>>>>>>> master
            }
            else{

                // Draw cells that have neighborCount = 0
                g.setColor(Color.lightGray);
                g.fillRect(x, y+80, w, w);
                g.setColor(Color.white);
                g.drawRect(x, y+80, w, w);

                // Draw numbers
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

                // Draw the cross
                if(isFlag){
                    g.setColor(Color.red);
                    g.drawLine(x, y+80, x+w, y+80+w);
                    g.drawLine(x+w, y+80, x, y+80+w);
                }
            }
        }
    }

    // Getters and Setters
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

<<<<<<< HEAD
<<<<<<< HEAD
    public void setRevealed(boolean revealed){
        this.revealed = revealed;
    }

=======
>>>>>>> master
=======
>>>>>>> master
    public int getNeighborCount(){
        return neighborCount;
    }

    public int getFlag(){
        return flag;
    }
}
