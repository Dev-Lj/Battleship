package ch.uzh.soco.group12.Battleship.Player;

import java.util.ArrayList;
import java.util.List;

import ch.uzh.soco.group12.Battleship.Boat;
import ch.uzh.soco.group12.Battleship.Grid.Grid;
import ch.uzh.soco.group12.Battleship.Grid.OceanGrid;
import ch.uzh.soco.group12.Battleship.Grid.TargetGrid;

public abstract class Player {
    protected final List<Boat> boats;
    private final Grid grid;

    /**
     * 
     * @param oceanGrid
     * @param boats
     * 
     * @pre grid != null
     * @post boats are set to oceanGrid
     */
    public Player(Grid grid, Iterable<Boat> boats) {
        assert grid != null ;
        this.grid = grid;
        this.boats = new ArrayList<Boat>();
        for (Boat boat : boats) {
            this.boats.add(boat);
        }
    }

    public Boolean hasLost() {
        for (Boat boat : boats) {
            if (boat.isAlive()) {
                return false;
            }
        }
        return true;
    }

    public abstract void playRound(TargetGrid targetGrid);

    public TargetGrid getTargetGrid() {
        return grid;
    }

    protected OceanGrid getOceanGrid() {
        return grid;
    }

    public abstract String getVictoryMessage();
}
