package dev.quan.minesweeper.button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import dev.quan.minesweeper.ui.MouseMove;

public class Button {
    private int x, y;
    private int width, height;
    private String text;
    private int stringX, stringY;

    public Button(int x, int y, int stringX, int stringY, String text){
        this.x = x;
        this.y = y;
        this.stringX = stringX;
        this.stringY = stringY;
        this.text = text;
        width = 200;
        height = 70;
    }

    public boolean contains(int xC, int yC){
        return (xC>x && xC<x+width && yC>y && yC<y+height);
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
        g.setColor(Color.black);
        g.setFont(g.getFont().deriveFont(Font.BOLD,50F));
        g.drawString(text, x+stringX, y+stringY);
        if(MouseMove.mx>x && MouseMove.mx<x+width && MouseMove.my>y && MouseMove.my<y+height){
            g.setColor(Color.red);
            g.fillRect(x, y, width, height);
            g.setColor(Color.white);
            g.setFont(g.getFont().deriveFont(Font.BOLD,50F));
            g.drawString(text, x+stringX, y+stringY);
        }
    }

    // Getters and Setters

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
