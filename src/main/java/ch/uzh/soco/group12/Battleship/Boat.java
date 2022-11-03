package ch.uzh.soco.group12.Battleship;

enum BoatType{
    Carrier(6, 'C'),
    Battleship(4, 'B'),
    Submarine(3, 'S'),
    PatrolBoat(2, 'P');

    private int length;
    private char typeChar;
    BoatType(int length, char typeChar) {
        this.length = length;
        this.typeChar = typeChar;
    }

    public int getLength() {
        return length;
    }

    public char getTypeChar() {
        return typeChar;
    }
}

public class Boat {
    private final BoatType type;
    private int lives;

    /**
     * 
     * @param type
     * @pre type != null
     */
    public Boat(BoatType type) {
        assert type != null;
        this.type = type;
        this.lives = type.getLength();
    }

    public Boolean isAlive() {
        return lives != 0;
    }

    public void receiveHit() {
        this.lives--;
    }

    public char getTypeChar() {
        return this.type.getTypeChar();
    }

    public int getLength() {
        return this.type.getLength();
    }

    @Override
    public String toString() {
        return this.type.name();
    }
}
