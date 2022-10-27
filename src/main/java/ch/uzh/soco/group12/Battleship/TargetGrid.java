package ch.uzh.soco.group12.Battleship;

public interface TargetGrid {
    public void placeBomb(String coordinate);
    public void placeBomb(int x, int y);
    public String toTargetGridString();
}
