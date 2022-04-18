package dev.quan.minesweeper;

/* HANDLER CLASS THAT CONTAIN ALL THE STUFF IN THE GAME CLASS
THAT WE WILL USE IN THE FUTURE
*/ 
public class Handler {
    private Game game;

    public Handler(Game game){
        this.game = game;
    }

    public Game getGame(){
        return game;
    }

    public void setGame(Game game){
        this.game = game;
    }

    public int getWidth(){
        return game.getWidth();
    }

    public int getHeight(){
        return game.getHeight();
    }
    
}
