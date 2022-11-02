package ch.uzh.soco.group12.Battleship;

import java.util.Arrays;
import java.util.Random;

import ch.uzh.soco.group12.Battleship.Grid.OceanGrid;
import ch.uzh.soco.group12.Battleship.Player.ComputerPlayer;
import ch.uzh.soco.group12.Battleship.Player.HumanPlayer;
import ch.uzh.soco.group12.Battleship.Player.Player;

public class Game {
    private Player currentPlayer;
    private Player otherPlayer;

    /**
     * Define which boats a Player can have
     * @return Default Boats list as Iterable
     */
    private static Iterable<Boat> getDefaultBoats() {
        Boat[] defaultBoatList = {
            new Boat(BoatType.Carrier),
            new Boat(BoatType.Battleship),
            new Boat(BoatType.Battleship),
            new Boat(BoatType.Submarine),
            new Boat(BoatType.Submarine),
            new Boat(BoatType.Submarine),
            new Boat(BoatType.PatrolBoat),
            new Boat(BoatType.PatrolBoat),
            new Boat(BoatType.PatrolBoat),
            new Boat(BoatType.PatrolBoat),
        };
        return Arrays.asList(defaultBoatList);
    }
    
    /**
     * Here, the "rules" of the game are defined
     */
    private void initializeGame() {
        // create Grids
        OceanGrid humanGrid = new OceanGrid();
        OceanGrid computerGrid = new OceanGrid();
        
        // create Players, assign Grids and boats
        Player humanPlayer = new HumanPlayer(humanGrid, getDefaultBoats());
        Player computerPlayer = new ComputerPlayer(computerGrid, getDefaultBoats());
        // select current player randomly
        Random random = new Random();
        if (random.nextBoolean()) {
            // computer makes first move
            currentPlayer = computerPlayer;
            otherPlayer = humanPlayer;
        } else {
            // human makes first move
            currentPlayer = humanPlayer;
            otherPlayer = computerPlayer;
        }
        // Game is now initialized
    }

    private void switchPlayers() {
        Player t = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = t;
    }

    /**
     * Rules for Game have to be defined before Game is started.
     */
    public void startGame() {
        initializeGame();

        // main game loop
        while (!currentPlayer.hasLost()) {
            currentPlayer.playRound(otherPlayer.getTargetGrid());
            switchPlayers();
        }
        System.out.println(otherPlayer.getVictoryMessage());
    }
}
