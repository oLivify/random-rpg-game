import java.util.Random;
import java.util.Scanner;

public class RiverCrossingEvent {
    
    // An enum is a special datatype that represents a group of constants
    // (unchangeable variables, like final variables).
    public enum Outcome {
        SUCCESS,
        FAILURE,
        PLAYER_BUSTS // lose a random item
    }


    private Player player;
    private Room room;

    public RiverCrossingEvent(Player player, Room room){
        this.player = player;
        this.room = room;
    }

    /**
     * Plays the blackjack-style rowing minigame.
     * 
     * 
     * @return a RiverCrossingOutcome either SUCCESS, FAILURE, PLAYER_BUSTS
     */
    public RiverCrossingEvent.Outcome execute() {
        Scanner input = new Scanner(System.in);

        int playerYards = 0;
        int currentYards = 0;
        boolean playing = true;

        
        Main.typewriter(5,
                "You must navigate the treacherous waters.\nReach exactly 21 yards or force the river current to bust!\n");

        // --- Player's Turn ---
        while (playing) {
            System.out.print("Do you want to [row] or [stop]: ");
            String choice = input.nextLine().trim().toLowerCase();

            if (choice.equals("row")) {
                int playerRoll = Main.rng.nextInt(6) + 1; // 1-6 yards
                playerYards += playerRoll;
                Main.typewriter(5,
                        "You rowed hard and gained " + playerRoll + " yards. Total: " + playerYards + " yards.\n");

                if (playerYards == 21) {
                    Main.typewriter(5, "You reached exactly 21 yards!\n");
                    Main.typewriter(5, "Success!\n");
                    return RiverCrossingEvent.Outcome.SUCCESS;
                } else if (playerYards > 21) {
                    Main.typewriter(5, "Bust! You over-rowed and lost control at " + playerYards + " yards.\n");
                    if(player.getBackpack().getSize() > 0){
                        // lose a random item
                        Item lostItem = player.loseRandomItem();
                        if(lostItem != null){
                            Main.typewriter(5, "Your " + lostItem + " washed away in with the current!\n");
                        }
                    }
                    Main.typewriter(5, "The current washes you back to where you came from...\n");
                    return RiverCrossingEvent.Outcome.PLAYER_BUSTS;
                } else {
                    // current's turn if currentYards < 17
                    if(currentYards < 17){
                        int currentRoll = Main.rng.nextInt(6) + 1; // 1-6 yards
                        currentYards += currentRoll;
                        Main.typewriter(5, "The river current surges forward " + currentRoll + " yards. River Total: "
                                + currentYards + " yards.\n");
                        if(currentYards > 21){
                            Main.typewriter(5, "The currents turns and helps push you to the opposite shore.\n");
                            Main.typewriter(5, "Success!\n");
                            return RiverCrossingEvent.Outcome.SUCCESS;
                        }
                    } else {
                        Main.typewriter(5, "The current holds steady..." + currentYards + "\n");
                    }

                    if (playerYards >= currentYards) {
                        Main.typewriter(5, "You're rowing at an excellent pace!\n");
                    } else if (playerYards < currentYards) {
                        Main.typewriter(5, "The current is overpowering you!\n");
                    }
                }
            } else if (choice.equals("stop")) {
                playing = false;
            } else {
                Main.typewriter(5, "Invalid choice. Please type [row] or [stop]: ");
            }
        }

        // If player stopped, the river takes multiple turns (unless player
        // already hit 21)
        if (playerYards == 21) {
            Main.typewriter(5, "Success! You have safely crossed the river.\n");
            return RiverCrossingEvent.Outcome.SUCCESS;
        }

        // --- River's Turn ---
        Main.typewriter(5, "You stop rowing. The river current pushes back...\n");
        while (currentYards <= playerYards && currentYards < 21) {
            int currentRoll = Main.rng.nextInt(6) + 1; // 1-6 yards
            currentYards += currentRoll;
            Main.typewriter(5, "The river current surges forward " + currentRoll + " yards. River Total: "
                    + currentYards + " yards.\n");
        }

        // --- Resolution ---
        if (currentYards > 21) {
            Main.typewriter(5,
                    "The river current pushes you " + currentYards + " yards! You managed to hold your ground.\n");
            Main.typewriter(5, "Success! You safely navigate through the " + room.getName() + ".\n");
            return RiverCrossingEvent.Outcome.SUCCESS;
        } else {
            Main.typewriter(5, "The river current reached " + currentYards + " yards, overpowering your " + playerYards
                    + " yards.\n");
            Main.typewriter(5, "You stopped too soon! The current sweeps you back from whence you came.\n");
            return RiverCrossingEvent.Outcome.FAILURE;
        }
        
    }
}
