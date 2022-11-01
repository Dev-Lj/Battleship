package ch.uzh.soco.group12.Battleship.Grid;

import ch.uzh.soco.group12.Battleship.Boat;

public interface OceanGrid {
    public int getSize();
    public void placeBoat(String startCoordinate, String endCoordinate, Boat boat);
    public void placeBoat(int x1, int y1, int x2, int y2, Boat boat);
    public String toOceanGridString();
}
