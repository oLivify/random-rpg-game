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

    private Random rng;
    private Player player;
    private Room room;

    public RiverCrossingEvent(Random rng, Player player, Room room){
        this.rng = rng;
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
                "You must navigate the treacherous waters. Reach exactly 21 yards or force the river current to bust!");

        // --- Player's Turn ---
        while (playing) {
            System.out.print("Do you want to [row] or [stop]? ");
            String choice = input.nextLine().trim().toLowerCase();

            if (choice.equals("row")) {
                int playerRoll = rng.nextInt(6) + 1; // 1-6 yards
                playerYards += playerRoll;
                Main.typewriter(5,
                        "You rowed hard and gained " + playerRoll + " yards. Total: " + playerYards + " yards.");

                if (playerYards == 21) {
                    Main.typewriter(5, "You reached exactly 21 yards!");
                    Main.typewriter(5, "Success!");
                    return RiverCrossingEvent.Outcome.SUCCESS;
                } else if (playerYards > 21) {
                    Main.typewriter(5, "Bust! You over-rowed and lost control at " + playerYards + " yards.");
                    if(player.getBackpack().getSize() > 0){
                        // lose a random item
                        Item lostItem = player.loseRandomItem(rng);
                        if(lostItem != null){
                            Main.typewriter(5, "Your " + lostItem + " washed away in with the current!");
                        }
                    }
                    Main.typewriter(5, "The current washes you back to where you came from...");
                    return RiverCrossingEvent.Outcome.PLAYER_BUSTS;
                } else {
                    // current's turn if currentYards < 17
                    if(currentYards < 17){
                        int currentRoll = rng.nextInt(6) + 1; // 1-6 yards
                        currentYards += currentRoll;
                        Main.typewriter(5, "The river current surges forward " + currentRoll + " yards. River Total: "
                                + currentYards + " yards.");
                        if(currentYards > 21){
                            Main.typewriter(5, "The currents turns and helps push you to the opposite shore.");
                            Main.typewriter(5, "Success!");
                            return RiverCrossingEvent.Outcome.SUCCESS;
                        } else if(currentYards == 21){
                            Main.typewriter(5, "The current washes you back to where you came from...");
                            return RiverCrossingEvent.Outcome.FAILURE;
                        }
                    } else {
                        Main.typewriter(5, "The current holds steady..." + currentYards);
                    }

                    if (playerYards >= currentYards) {
                        Main.typewriter(5, "You're rowing at an excellent pace!");
                    } else if (playerYards < currentYards) {
                        Main.typewriter(5, "The current is overpowering you!");
                    }
                }
            } else if (choice.equals("stop")) {
                playing = false;
            } else {
                Main.typewriter(5, "Invalid choice. Please type 'row' or 'stop'.");
            }
        }

        // If player stopped, the river takes multiple turns (unless player
        // already hit 21)
        if (playerYards == 21) {
            Main.typewriter(5, "Success! You have safely crossed the river.");
            return RiverCrossingEvent.Outcome.SUCCESS;
        }

        // --- River's Turn ---
        Main.typewriter(5, "\nYou stop rowing. The river current pushes back...");
        while (currentYards <= playerYards && currentYards < 21) {
            int currentRoll = rng.nextInt(6) + 1; // 1-6 yards
            currentYards += currentRoll;
            Main.typewriter(5, "The river current surges forward " + currentRoll + " yards. River Total: "
                    + currentYards + " yards.");
        }

        // --- Resolution ---
        if (currentYards > 21) {
            Main.typewriter(5,
                    "The river current pushes you " + currentYards + " yards! You managed to hold your ground.");
            Main.typewriter(5, "Success! You safely navigate through the " + room.getName() + ".");
            return RiverCrossingEvent.Outcome.SUCCESS;
        } else {
            Main.typewriter(5, "The river current reached " + currentYards + " yards, overpowering your " + playerYards
                    + " yards.");
            Main.typewriter(5, "You stopped too soon! The current sweeps you back to your previous room.");
            return RiverCrossingEvent.Outcome.FAILURE;
        }
        
    }
}
