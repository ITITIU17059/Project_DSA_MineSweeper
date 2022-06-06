package dev.quan.minesweeper.states;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

import dev.quan.minesweeper.Handler;
import dev.quan.minesweeper.button.Button;
import dev.quan.minesweeper.gfx.ImageLoader;
import java.awt.image.BufferedImage;

public class MenuState extends State{
    
    private BufferedImage menuBG = ImageLoader.loadImage("C:\\Users\\darkq\\OneDrive\\Máy tính\\2021-2022 Sem 2\\Data structure\\Project_v2\\Project_DSA_MineSweeper\\res\\textures\\menu.jpg");
    private Button startButton;
    private Button exitButton;

    public MenuState(Handler handler) {
        super(handler);
        handler.getDisplay().setWidth(1601);
		handler.getDisplay().setHeight(881);
        startButton = new Button(53,143,40,53,"Start");
        exitButton = new Button(53,246,48,53,"Exit");
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(menuBG, 0, 0, handler.getDisplay().getWidth(), handler.getDisplay().getHeight(),null);
        g.setColor(Color.gray);
        g.setFont(g.getFont().deriveFont(Font.BOLD,100F));
        String text = "MineSweeper";
        g.drawString(text, 58, 98);
        g.setColor(Color.white);
        g.drawString(text, 53, 93);
        startButton.render(g);
        exitButton.render(g);
    }

    @Override
    public void mouseLeftPressed(MouseEvent e) {
        if(startButton.contains(e.getX(), e.getY())){
            State.setState(handler.getGame().getLevelState());
            handler.getGame().getMouseClick().setState(handler.getGame().getLevelState());
        }

        if(exitButton.contains(e.getX(), e.getY()))
            System.exit(0);
    }

    @Override
    public void mouseRightPressed(MouseEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    public void setLevel(int count){

    }
    
}