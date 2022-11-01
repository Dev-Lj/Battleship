package ch.uzh.soco.group12.Battleship.Grid;

import java.util.ArrayList;
import java.util.List;

import ch.uzh.soco.group12.Battleship.Boat;
import ch.uzh.soco.group12.Battleship.Cell;

public class Grid implements TargetGrid,OceanGrid{
    public static final int DEFAULT_GRID_SIZE = 10;

    private final Cell gridList[][];

    public Grid() {
        this.gridList = new Cell[DEFAULT_GRID_SIZE][DEFAULT_GRID_SIZE];
        initializeGrid(DEFAULT_GRID_SIZE);
    }

    public Grid(int size) {
        this.gridList = new Cell[size][size];
        initializeGrid(size);
    }

    private void initializeGrid(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gridList[i][j] = new Cell(j, i);
            }
        }
    }

    @Override
    public int getSize() {
        return gridList.length;
    }

    private Cell getCellByCoordinate(String coordinate) {
        try {
            char c = coordinate.toLowerCase().charAt(0);
            int x = (int)c - (int)'a';
            int y = Integer.parseInt(coordinate.substring(1));
            return gridList[y][x];
        } catch (Exception e) {
            throw new IllegalArgumentException("Coordinate "+ coordinate + "could not be resolved.");
        }
    }

    private List<Cell> getCellsBetween(Cell fromCell, Cell toCell) {
        List<Cell> cells = new ArrayList<Cell>();

        if (fromCell.getX() == toCell.getX()) {
            // column
            for(int i = fromCell.getY(); i<=toCell.getY(); i++) {
                cells.add(gridList[i][fromCell.getX()]);
            }
        } else if (fromCell.getY() == toCell.getY()) {
            // row
            for(int i = fromCell.getX(); i<=toCell.getX(); i++) {
                cells.add(gridList[fromCell.getY()][i]);
            }
        } else {
            throw new IllegalArgumentException("Cannot get diagonal Cells. Please enter row or column.");
        }

        return cells;
    }

    private List<Cell> getCellsByCoordinates(String fromString, String toString) {
        if (fromString.toLowerCase() == toString.toLowerCase()) {
            throw new IllegalArgumentException("Start coordinate must not match end coordinate.");
        }

        Cell fromCell = getCellByCoordinate(fromString);
        Cell toCell = getCellByCoordinate(toString);
        return getCellsBetween(fromCell, toCell);
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

    @Override
    public void placeBoat(String startCoordinate, String endCoordinate, Boat boat) {
        List<Cell> cells = getCellsByCoordinates(startCoordinate, endCoordinate);
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
    @Override
    public void placeBoat(int x1, int y1, int x2, int y2, Boat boat) {
        assert x1 < gridList.length && x1 >= 0 && x2 < gridList.length && x2 >= 0;
        assert y1 < gridList.length && y1 >= 0 && y2 < gridList.length && y2 >= 0;

        Cell fromCell = gridList[y1][x1];
        Cell toCell = gridList[y2][x2];
        List<Cell> cells = getCellsBetween(fromCell, toCell);
        placeBoat(cells, boat);
    }

    private String generateColNamesString() {
        String colNames = "";
        for (int i = 0; i < gridList.length; i++) {
            colNames += String.format(" %c", (char)((int)'A' + i));
        }
        return colNames;
    }

    /**
     * 
     * @param headingText
     * @pre headingText.length() % 2 = 1 (To ensure even amount of = characters)
     * @return heading String with decoration
     */
    private String generateGridHeader(String headingText) {
        assert headingText.length() % 2 == 1;
        String equalDecoration = "=".repeat((2 * gridList.length + 3 - headingText.length())/2);
        String heading = equalDecoration + headingText + equalDecoration + "\n";
        return heading;
    }

    private String gridToString(String headingText, boolean censor) {
        String gridString = generateGridHeader(headingText);
        gridString += " " + generateColNamesString() + "\n";
        final String upperLowerBorder = " " + "+-".repeat(gridList.length) + "+";
        gridString += upperLowerBorder + "\n";

        for (int i = 0; i < gridList.length; i++) {
            String rowString = Integer.toString(i);
            for (Cell cell : gridList[i]) {
                rowString += "|"+ (censor ? cell.toCensoredChar() : cell.toChar());
            }
            gridString += String.format("%s|%d\n", rowString, i);
        }
        gridString += upperLowerBorder;
        return gridString;
    }

    @Override
    public String toOceanGridString() {
        return gridToString(" Ocean  Grid ", false);
    }

    @Override
    public String toTargetGridString() {
        return gridToString(" Target Grid ", true);
    }

    @Override
    public void placeBomb(String coordinate) {
        Cell cell = getCellByCoordinate(coordinate);
        cell.placeBomb();
    }

    /**
     * @pre x >= 0 && x < gridList.length && y >= 0 && y < gridList.length
     */
    @Override
    public void placeBomb(int x, int y) {
        assert x >= 0 && x < gridList.length && y >= 0 && y < gridList.length;
        Cell cell = gridList[y][x];
        cell.placeBomb();
    }   
}
