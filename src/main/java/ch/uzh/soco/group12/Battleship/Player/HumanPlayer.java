package ch.uzh.soco.group12.Battleship.Player;

import ch.uzh.soco.group12.App;
import ch.uzh.soco.group12.Battleship.Boat;
import ch.uzh.soco.group12.Battleship.Grid;
import ch.uzh.soco.group12.Battleship.TargetGrid;

public class HumanPlayer extends Player{
    /**
     * 
     * @param oceanGrid
     * @param targetGrid
     * @param boats
     * 
     * @pre oceangrid != null && targetGrid != null
     */
    public HumanPlayer(Grid oceanGrid, Iterable<Boat> boats) {
        super(oceanGrid, boats);
        if (App.DEBUG_MODE) {
            placeBoatsDebug();
        } else {
            placeBoats();
        }
    }

    private void placeBoatsDebug() {
        oceanGrid.placeBoat("B1", "B6", boats.get(0)); // Carrier
        oceanGrid.placeBoat("F1", "I1", boats.get(1)); // Battleship
        oceanGrid.placeBoat("A9", "D9", boats.get(2)); // Battleship
        oceanGrid.placeBoat("A7", "C7", boats.get(3)); // Submarine
        oceanGrid.placeBoat("A8", "C8", boats.get(4)); // Submarine
        oceanGrid.placeBoat("E9", "G9", boats.get(5)); // Submarine
        oceanGrid.placeBoat("J0", "J1", boats.get(6)); // Patrolboat
        oceanGrid.placeBoat("J2", "J3", boats.get(7)); // Patrolboat
        oceanGrid.placeBoat("G5", "G6", boats.get(8)); // Patrolboat
        oceanGrid.placeBoat("H9", "I9", boats.get(9)); // Patrolboat
    }

    private void placeBoats() {
        Boolean hasError;
        for (Boat boat : super.boats) {
            hasError = true;
            while (hasError) {
                try {
                    System.out.println(oceanGrid);
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
        super.oceanGrid.placeBoat(coordinates[0], coordinates[1], boat);
    }

    @Override
    public void playRound(TargetGrid targetGrid) {
        System.out.println(targetGrid.toTargetGridString());
        System.out.println(oceanGrid.toOceanGridString());
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

    @Override
    public TargetGrid getTargetGrid() {
        return super.oceanGrid;
    }
    
}
