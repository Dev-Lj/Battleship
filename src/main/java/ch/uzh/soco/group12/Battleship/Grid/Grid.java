package ch.uzh.soco.group12.Battleship.Grid;

import java.util.ArrayList;
import java.util.List;

import ch.uzh.soco.group12.Battleship.Cell;

public abstract class Grid {
    public static final int DEFAULT_GRID_SIZE = 10;

    private final Cell gridList[][];

    protected Grid() {
        this.gridList = new Cell[DEFAULT_GRID_SIZE][DEFAULT_GRID_SIZE];
        initializeGrid(DEFAULT_GRID_SIZE);
    }

    /**
     * 
     * @param size
     * @pre size > 0
     */
    protected Grid(int size) {
        assert size > 0;
        this.gridList = new Cell[size][size];
        initializeGrid(size);
    }

    /**
     * 
     * @param gridList
     * @pre gridList != null && gridList.length > 0;
     */
    protected Grid(Cell gridList[][]) {
        assert gridList != null && gridList.length > 0;
        this.gridList = gridList;
    }

    private void initializeGrid(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gridList[i][j] = new Cell(j, i);
            }
        }
    }

    public int getSize() {
        return gridList.length;
    }

    protected Cell[][] getGridList() {
        return this.gridList;
    }

    /**
     * @pre x >= 0 && x < gridList.length && y >= 0 && y < gridList.length
     */
    protected Cell getCell(int x, int y) {
        assert x >= 0 && x < getSize() && y >= 0 && y < getSize();
        return gridList[y][x];
    }

    protected Cell getCell(String coordinate) throws IllegalArgumentException{
        try {
            char c = coordinate.toLowerCase().charAt(0);
            int x = (int)c - (int)'a';
            int y = Integer.parseInt(coordinate.substring(1));
            return getCell(x, y);
        } catch (Exception e) {
            throw new IllegalArgumentException("Coordinate "+ coordinate + " could not be resolved.");
        }
    }

    protected List<Cell> getCellsBetween(Cell fromCell, Cell toCell) throws IllegalArgumentException {
        List<Cell> cells = new ArrayList<Cell>();

        if (fromCell.getX() == toCell.getX()) {
            // column
            if (fromCell.getY()<toCell.getY()) {
                for(int i = fromCell.getY(); i<=toCell.getY(); i++) {
                    cells.add(gridList[i][fromCell.getX()]);
                }
            } else {
                // handle fromCell > toCell
                for(int i = toCell.getY(); i<=fromCell.getY(); i++) {
                    cells.add(gridList[i][fromCell.getX()]);
                }
            }
        } else if (fromCell.getY() == toCell.getY()) {
            // row
            if (fromCell.getX()<toCell.getX()) {
                for(int i = fromCell.getX(); i<=toCell.getX(); i++) {
                    cells.add(gridList[fromCell.getY()][i]);
                }   
            } else {
                // handle fromCell > toCell
                for(int i = toCell.getX(); i<=fromCell.getX(); i++) {
                    cells.add(gridList[fromCell.getY()][i]);
                } 
            }
        } else {
            throw new IllegalArgumentException("Cannot get diagonal Cells. Please enter row or column.");
        }

        return cells;
    }

    protected List<Cell> getCellsBetween(String fromString, String toString) throws IllegalArgumentException{
        if (fromString.toLowerCase() == toString.toLowerCase()) {
            throw new IllegalArgumentException("Start coordinate must not match end coordinate.");
        }

        Cell fromCell = getCell(fromString);
        Cell toCell = getCell(toString);
        return getCellsBetween(fromCell, toCell);
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
        assert headingText.length() % 2 == 0;
        String equalDecoration = "=".repeat((2 * gridList.length + 4 - headingText.length())/2);
        String heading = equalDecoration + headingText + equalDecoration + "\n";
        return heading;
    }

    protected String gridToString(String headingText, boolean censor) {
        String gridString = generateGridHeader(headingText);
        gridString += "  " + generateColNamesString() + "\n";
        final String upperLowerBorder = "  " + "+-".repeat(gridList.length) + "+";
        gridString += upperLowerBorder + "\n";

        for (int i = 0; i < gridList.length; i++) {
            String rowString = Integer.toString(i) + (Integer.toString(i).length()>1?"":" ");
            for (Cell cell : gridList[i]) {
                rowString += "|"+ (censor ? cell.toCensoredChar() : cell.toChar());
            }
            gridString += String.format("%s|%d\n", rowString, i);
        }
        gridString += upperLowerBorder + "\n";
        gridString += "==".repeat(gridList.length + 2);
        return gridString;
    }

    public abstract String toString(); 
}
