package ch.uzh.soco.group12.Battleship.Grid;

public interface TargetGrid {
    public int getSize();
    public void placeBomb(String coordinate);
    public void placeBomb(int x, int y);
    public String toTargetGridString();
}
