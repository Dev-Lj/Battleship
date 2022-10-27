package ch.uzh.soco.group12;

import ch.uzh.soco.group12.Battleship.Game;

public class App 
{
    public static final Boolean DEBUG_MODE = true;

    // TODO Test rigorously
    // TODO Add javaDoc above all methods
    public static void main( String[] args )
    {
        Game game = new Game();
        game.startGame();
    }
}
