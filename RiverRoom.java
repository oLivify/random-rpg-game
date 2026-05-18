import java.util.Scanner;
import java.util.Random;

public class RiverRoom extends Room {
    
    // Constructors mirroring the superclass
    public RiverRoom() {
        super();
    }

    public RiverRoom(String _name) {
        super(_name);
    }

    /**
     * Plays the blackjack-style rowing minigame.
     * * @param previousRoom The room the player just came from.
     * @return The Room object the player lands in (this room if win, previousRoom if lose).
     */
    public Room playBlackjack(Room previousRoom) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        
        int playerYards = 0;
        int currentYards = 0;
        boolean playing = true;
        
        System.out.println("\n--- Welcome to the River! ---");
        System.out.println("You must navigate the treacherous waters. Reach exactly 21 yards or force the river current to bust!");
        
        // --- Player's Turn ---
        while (playing) {
            System.out.print("Do you want to [Row] or [Stop]? ");
            String choice = input.nextLine().trim().toLowerCase();
            
            if (choice.equals("row")) {
                int playerRoll = rand.nextInt(6) + 1; // 1-6 yards
                playerYards += playerRoll;
                System.out.println("You rowed hard and gained " + playerRoll + " yards. Total: " + playerYards + " yards.");
                
                if (playerYards == 21) {
                    System.out.println("Perfect! You reached exactly 21 yards!");
                    playing = false;
                } else if (playerYards > 21) {
                    System.out.println("Bust! You over-rowed and lost control at " + playerYards + " yards.");
                    System.out.println("The current washes you back to where you came from...");
                    return previousRoom; 
                }
            } else if (choice.equals("stop")) {
                playing = false;
            } else {
                System.out.println("Invalid choice. Please type 'Row' or 'Stop'.");
            }
        }
        
        // If player stopped or got exactly 21, the river takes its turn (unless player already hit 21)
        if (playerYards == 21) {
            System.out.println("Success! You have safely crossed the river.");
            return this;
        }
        
        // --- River's Turn ---
        System.out.println("\nYou stop rowing. The river current pushes back...");
        while (currentYards <= playerYards && currentYards < 21) {
            int currentRoll = rand.nextInt(6) + 1; // 1-6 yards
            currentYards += currentRoll;
            System.out.println("The river current surges forward " + currentRoll + " yards. River Total: " + currentYards + " yards.");
        }
        
        // --- Resolution ---
        if (currentYards > 21) {
            System.out.println("The river current busted at " + currentYards + " yards! You managed to hold your ground.");
            System.out.println("Success! You safely navigate through the " + getName() + ".");
            return this; 
        } else {
            System.out.println("The river current reached " + currentYards + " yards, overpowering your " + playerYards + " yards.");
            System.out.println("You stopped too soon! The current sweeps you back to your previous room.");
            return previousRoom; 
        }
    }
}