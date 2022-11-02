package ch.uzh.soco.group12.Battleship.Player;

import ch.uzh.soco.group12.App;
import ch.uzh.soco.group12.Battleship.Boat;
import ch.uzh.soco.group12.Battleship.Grid.OceanGrid;
import ch.uzh.soco.group12.Battleship.Grid.TargetGrid;

public class HumanPlayer extends Player{

    public HumanPlayer(OceanGrid oceanGrid, Iterable<Boat> boats) {
        super(oceanGrid, boats);
    }

    @Override
    protected void placeBoats() {
        if (App.DEBUG_MODE) {
            placeBoatsDebug();
        } else {
            placeBoatsInput();
        }
    }

    /**
     * Place boats on predefined positions to save time while debuging
     */
    private void placeBoatsDebug() {
        super.getOceanGrid().placeBoat("B1", "B6", boats.get(0)); // Carrier
        super.getOceanGrid().placeBoat("F1", "I1", boats.get(1)); // Battleship
        super.getOceanGrid().placeBoat("A9", "D9", boats.get(2)); // Battleship
        super.getOceanGrid().placeBoat("A7", "C7", boats.get(3)); // Submarine
        super.getOceanGrid().placeBoat("A8", "C8", boats.get(4)); // Submarine
        super.getOceanGrid().placeBoat("E9", "G9", boats.get(5)); // Submarine
        super.getOceanGrid().placeBoat("J0", "J1", boats.get(6)); // Patrolboat
        super.getOceanGrid().placeBoat("J2", "J3", boats.get(7)); // Patrolboat
        super.getOceanGrid().placeBoat("G5", "G6", boats.get(8)); // Patrolboat
        super.getOceanGrid().placeBoat("H9", "I9", boats.get(9)); // Patrolboat
    }

    /**
     * Place boats according to user input
     */
    private void placeBoatsInput() {
        Boolean hasError;
        for (Boat boat : super.boats) {
            hasError = true;
            while (hasError) {
                try {
                    System.out.println(super.getOceanGrid());
                    placeBoat(boat);
                    hasError = false;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    hasError = true;
                }
            }
        }
    }

    private void placeBoat(Boat boat) {
        System.out.print(String.format("Enter coordinates for %s (%d blocks): ", boat, boat.getLength()));
        String input = System.console().readLine();
        String[] coordinates = input.split(",");
        if (coordinates.length != 2) {
            throw new IllegalArgumentException("Please enter two coordinates separated by a comma (e.g. A1,A2).");
        }
        super.getOceanGrid().placeBoat(coordinates[0], coordinates[1], boat);
    }

    @Override
    public void playRound(TargetGrid targetGrid) {
        System.out.println(targetGrid);
        System.out.println(super.getOceanGrid());
        Boolean hasError = true;
        while (hasError) {
            try {
                System.out.print("Coordinate to shoot Bomb at: ");
                String input = System.console().readLine();
                targetGrid.placeBomb(input);
                hasError = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                hasError = true;
            }
        }
    }

    @Override
    public String getVictoryMessage() {
        return "Congrats you won :)";
    } 
}
