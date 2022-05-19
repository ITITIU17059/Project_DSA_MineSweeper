package dev.quan.minesweeper.states;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

import dev.quan.minesweeper.Handler;
import dev.quan.minesweeper.button.Button;
import dev.quan.minesweeper.gfx.ImageLoader;
import java.awt.image.BufferedImage;

public class LevelState extends State{
    private BufferedImage menuBG = ImageLoader.loadImage("C:\\Users\\darkq\\OneDrive\\Máy tính\\2021-2022 Sem 2\\Data structure\\Project_v2\\Project_DSA_MineSweeper\\res\\textures\\menu.jpg");
    private Button easyButton, normalButton, hardButton;

    public LevelState(Handler handler) {
        super(handler);
        handler.getDisplay().setWidth(1601);
		handler.getDisplay().setHeight(881);
        easyButton = new Button(53, 143, 40, 53, "Easy");
        normalButton = new Button(53, 246, 12, 53, "Normal");
        hardButton = new Button(53, 349, 40, 53, "Hard");
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(menuBG, 0, 0, handler.getDisplay().getWidth(), handler.getDisplay().getHeight(),null);
        g.setColor(Color.gray);
        g.setFont(g.getFont().deriveFont(Font.BOLD,100F));
        String text = "Select Levels";
        g.drawString(text, 58, 98);
        g.setColor(Color.white);
        g.drawString(text, 53, 93);
        easyButton.render(g);
        normalButton.render(g);
        hardButton.render(g);
    }

    @Override
    public void mouseLeftPressed(MouseEvent e) {
        if(easyButton.contains(e.getX(), e.getY())){
            setButton(easyButton, 0);
        }
        if(normalButton.contains(e.getX(), e.getY())){
            setButton(normalButton, 2);
        }
        if(hardButton.contains(e.getX(), e.getY())){
            setButton(hardButton, 1);
        }
    }

    public void setButton(Button b, int count){
        handler.getGame().getGameState().setLevel(count);
        State.setState(handler.getGame().getGameState());
        handler.getGame().getMouseClick().setState(handler.getGame().getGameState());
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
