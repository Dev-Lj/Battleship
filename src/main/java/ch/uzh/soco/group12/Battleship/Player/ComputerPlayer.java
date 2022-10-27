package ch.uzh.soco.group12.Battleship.Player;

import java.util.Random;

import ch.uzh.soco.group12.App;
import ch.uzh.soco.group12.Battleship.Boat;
import ch.uzh.soco.group12.Battleship.Grid;
import ch.uzh.soco.group12.Battleship.TargetGrid;

public class ComputerPlayer extends Player{

    public ComputerPlayer(Grid oceanGrid, Iterable<Boat> boats) {
        super(oceanGrid, boats);
        placeBoats();
        if (App.DEBUG_MODE) {
            System.out.println(oceanGrid.toOceanGridString());
        }
    }
    
    /**
     * Place boats using brute force
     */
    private void placeBoats() {
        Random random = new Random();
        Boolean hasError;
        int counter;
        for (Boat boat : boats) {
            hasError = true;
            counter = 0;
            while (hasError) {
                try {
                    Boolean vertically = random.nextBoolean();
                    if (vertically) {
                        placeBoatV(random, boat);
                        hasError = false;
                    } else {
                        placeBoatH(random, boat);
                        hasError = false;
                    }
                } catch (Exception e) {
                    counter++;
                    if (counter > 1e6) {
                        throw new RuntimeException(String.format("Failed to place %s, aborted after %d attempts", boat, counter));
                    }
                }
            }
        }
    }

    /**
     * Place a boat vertically at random position
     * @param boat
     */
    private void placeBoatV(Random random, Boat boat) {
        int x = random.nextInt(oceanGrid.getSize());
        int y = random.nextInt(oceanGrid.getSize()-boat.getLength() + 1);
        oceanGrid.placeBoat(x, y, x, y+boat.getLength() - 1, boat);
    }

    /**
     * Place a boat horizontally at random position
     * @param boat
     */
    private void placeBoatH(Random random, Boat boat) {
        int x = random.nextInt(oceanGrid.getSize()-boat.getLength() + 1);
        int y = random.nextInt(oceanGrid.getSize());
        oceanGrid.placeBoat(x, y, x+boat.getLength() - 1, y, boat);
    }
    

    @Override
    public void playRound(TargetGrid targetGrid) {
        // TODO Improve this
        Random random = new Random();
        int x = random.nextInt(oceanGrid.getSize());
        int y = random.nextInt(oceanGrid.getSize());
        targetGrid.placeBomb(x, y);
    }

    @Override
    public String getVictoryMessage() {
        return "No shame in loosing against a machine...\nBetter luck next time :)";
    }


    @Override
    public TargetGrid getTargetGrid() {
        return super.oceanGrid;
    }
    
}
