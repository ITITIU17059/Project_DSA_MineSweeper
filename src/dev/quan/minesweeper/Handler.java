package dev.quan.minesweeper;

import dev.quan.minesweeper.display.Display;

/* HANDLER CLASS THAT CONTAIN ALL THE STUFF IN THE GAME CLASS
THAT WE WILL USE IN THE FUTURE
*/ 
public class Handler {
    private Game game;
    private Display display;

    public Handler(Game game){
        this.game = game;
    }

    public Game getGame(){
        return game;
    }

    public void setGame(Game game){
        this.game = game;
    }

    public Display getDisplay(){
        return display;
    }

    public void setDisplay(Display display){
        this.display = display;
    }
    
}
