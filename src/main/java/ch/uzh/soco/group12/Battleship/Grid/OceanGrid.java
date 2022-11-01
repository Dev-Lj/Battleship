package ch.uzh.soco.group12.Battleship.Grid;

import java.util.List;

import ch.uzh.soco.group12.Battleship.Boat;
import ch.uzh.soco.group12.Battleship.Cell;

public class OceanGrid extends Grid{

    public OceanGrid() {
        super();
    }

    private void placeBoat(List<Cell> cells, Boat boat) {
        if (cells.size() != boat.getLength()) {
            throw new IllegalArgumentException("Length of coordinates (" + cells.size() + ") do not match length of boat ("+ boat.getLength() +").");
        }
        // check if a boat is already placed in row/column
        for (Cell cell : cells) {
            if (cell.hasBoat()) {
                throw new IllegalArgumentException("Cell already contains boat. Select different coordinates.");
            }
        }

        for (Cell cell : cells) {
            cell.setBoat(boat);
        }
    }

    public void placeBoat(String startCoordinate, String endCoordinate, Boat boat) {
        List<Cell> cells = getCellsBetween(startCoordinate, endCoordinate);
        placeBoat(cells, boat);
    }

    /**
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * 
     * @pre xn < grid.length && xn >= 0 && yn < grid.length && yn >= 0
     */
    public void placeBoat(int x1, int y1, int x2, int y2, Boat boat) {
        assert x1 < getSize() && x1 >= 0 && x2 < getSize() && x2 >= 0;
        assert y1 < getSize() && y1 >= 0 && y2 < getSize() && y2 >= 0;

        Cell fromCell = getCell(x1, y1);
        Cell toCell = getCell(x2, y2);
        List<Cell> cells = getCellsBetween(fromCell, toCell);
        placeBoat(cells, boat);
    }

    public TargetGrid toTargetGrid() {
        return new TargetGrid(getGridList());
    }

    @Override
    public String toString() {
        return gridToString(" Ocean  Grid ", false);
    }
    
}
