package ch.uzh.soco.group12.Battleship.Player;

import java.util.ArrayList;
import java.util.List;

import ch.uzh.soco.group12.Battleship.Boat;
import ch.uzh.soco.group12.Battleship.Grid.OceanGrid;
import ch.uzh.soco.group12.Battleship.Grid.TargetGrid;

public abstract class Player {
    protected final List<Boat> boats;
    private final OceanGrid oceanGrid;

    /**
     * 
     * @param oceanGrid
     * @param boats
     * 
     * @pre grid != null
     * @post boats are set to oceanGrid
     */
    public Player(OceanGrid oceanGrid, Iterable<Boat> boats) {
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

    public TargetGrid getTargetGrid() {
        return oceanGrid.toTargetGrid();
    }

    protected OceanGrid getOceanGrid() {
        return oceanGrid;
    }

    public abstract String getVictoryMessage();
}
