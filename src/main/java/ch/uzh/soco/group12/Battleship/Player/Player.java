package ch.uzh.soco.group12.Battleship.Player;

import java.util.ArrayList;
import java.util.List;

import ch.uzh.soco.group12.Battleship.Boat;
import ch.uzh.soco.group12.Battleship.Grid;
import ch.uzh.soco.group12.Battleship.TargetGrid;

public abstract class Player {
    protected final List<Boat> boats;
    protected final Grid oceanGrid;

    /**
     * 
     * @param oceanGrid
     * @param boats
     * 
     * @pre oceangrid != null
     * @post boats are set to oceanGrid
     */
    public Player(Grid oceanGrid, Iterable<Boat> boats) {
        assert oceanGrid != null ;
        this.oceanGrid = oceanGrid;
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

    // TODO find out if this is an antipattern
    public abstract TargetGrid getTargetGrid();

    public abstract String getVictoryMessage();
}
