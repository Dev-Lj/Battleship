package ch.uzh.soco.group12.Battleship.Grid;

import ch.uzh.soco.group12.Battleship.Cell;

public class TargetGrid extends Grid{

    protected TargetGrid(Cell gridList[][]) {
        super(gridList);
    }

    public void placeBomb(String coordinate) {
        Cell cell = getCell(coordinate);
        cell.placeBomb();
    }

    /**
     * @pre x >= 0 && x < gridList.length && y >= 0 && y < gridList.length
     */
    public void placeBomb(int x, int y) {
        assert x >= 0 && x < getSize() && y >= 0 && y < getSize();
        Cell cell = getCell(x, y);
        cell.placeBomb();
    }  

    @Override
    public String toString() {
        return gridToString(" Target Grid ", true);
    }

}
