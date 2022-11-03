package ch.uzh.soco.group12.Battleship;

import java.util.Optional;

public class Cell {
    private Optional<Boat> boat;
    private boolean bombed = false;
    private final int x;
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        boat = Optional.empty();
    }

    public char toChar() {
        if (boat.isPresent()) {
            if (bombed) {
                return 'X';
            } else {
                return boat.get().getTypeChar();
            }
        } else {
            if (bombed) {
                return 'O';
            } else {
                return ' ';
            }
        }
    }

    public char toCensoredChar() {
        if (bombed) {
            if (boat.isPresent()) {
                if (boat.get().isAlive()) {
                    return 'X';
                } else {
                    return boat.get().getTypeChar();
                }
            } else {
                return 'O';
            }
        } else {
            return ' ';
        }
    }

    public boolean hasBoat(){
        return boat.isPresent();
    }

    public boolean isBombed() {
        return this.bombed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void placeBomb() throws IllegalArgumentException{
        if (bombed) {
            throw new IllegalArgumentException("Cell has already been bombed. Choose different Cell.");
        }

        bombed = true;
        if (boat.isPresent()) {
            boat.get().receiveHit();
        }
    }

    /**
     * 
     * @param boat
     * @pre boat != null && !this.boat.isPresent
     */
    public void setBoat(Boat boat){
        assert boat != null && !this.boat.isPresent();
        this.boat = Optional.of(boat);
    }
}
