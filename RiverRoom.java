import java.util.Scanner;
import java.util.Random;

public class RiverRoom extends Room {

    public static String generateRiverName(){
        String[] adjectives = {"Little", "Smokey", "Big", "Great", "East", "West", "North",
            "South","White","Green","Red","Blue","Saint","Winding","Black","Wild","Golden",
            "Grand","Broad","Windy",""
        };
        String[] nouns = {"Fork","Snake", "Platte", "Tar", "Wind","Oak","Maple","Day","Night",
            "Spring","Summer","Winter","Falls","Branch","Thorn","Mile","Wood","Elk","Moose",
            "Bear","Fox","Field","Plains","Valley","Deer","Water","Rock","Stone",""
        };
        int adjIndex = (int)(Math.random() * adjectives.length);
        int nounIndex =  (int)(Math.random() * nouns.length);
        return adjectives[adjIndex] + " " + nouns[nounIndex] + " River";
    }

    // Constructors mirroring the superclass
    public RiverRoom() {
        super();
    }

    public RiverRoom(String _name) {
        super(_name);
    }

    public void enterRoom(Player player, Random rng) {
        Scanner input = new Scanner(System.in);
        Main.typewriter(5, "\n" + getName());
        Main.typewriter(5, "\nCross the river? y or n");

        if(input.nextLine().toLowerCase().equals("y") && playBlackjack(player)){
            super.enterRoom(player, rng);
        } 
        else{
            player.setCurrentRoom(player.getPreviousRoom());
            player.getCurrentRoom().enterRoom(player, rng);
        }
        
        
        input.close();
    }

    /**
     * Plays the blackjack-style rowing minigame.
     * * @param previousRoom The room the player just came from.
     * 
     * @return true if win, false if lose).
     */
    public boolean playBlackjack(Player player) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        int playerYards = 0;
        int currentYards = 0;
        boolean playing = true;

        
        Main.typewriter(5,
                "You must navigate the treacherous waters. Reach exactly 21 yards or force the river current to bust!");

        // --- Player's Turn ---
        while (playing) {
            System.out.print("Do you want to [Row] or [Stop]? ");
            String choice = input.nextLine().trim().toLowerCase();

            if (choice.equals("row")) {
                int playerRoll = rand.nextInt(6) + 1; // 1-6 yards
                playerYards += playerRoll;
                Main.typewriter(5,
                        "You rowed hard and gained " + playerRoll + " yards. Total: " + playerYards + " yards.");

                if (playerYards == 21) {
                    Main.typewriter(5, "Perfect! You reached exactly 21 yards!");
                    playing = false;
                } else if (playerYards > 21) {
                    Main.typewriter(5, "Bust! You over-rowed and lost control at " + playerYards + " yards.");
                    Main.typewriter(5, "The current washes you back to where you came from...");
                    return false;
                } else {
                    // current's turn
                    int currentRoll = rand.nextInt(6) + 1; // 1-6 yards
                    currentYards += currentRoll;
                    Main.typewriter(5, "The river current surges forward " + currentRoll + " yards. River Total: "
                            + currentYards + " yards.");
                    if (playerYards >= currentYards) {
                        Main.typewriter(5, "You're rowing at an excellent pace!");
                    } else if (playerYards < currentYards) {
                        Main.typewriter(5, "The current is overpowering you!");
                    }
                }
            } else if (choice.equals("stop")) {
                playing = false;
            } else {
                Main.typewriter(5, "Invalid choice. Please type 'Row' or 'Stop'.");
            }
        }

        // If player stopped or got exactly 21, the river takes its turn (unless player
        // already hit 21)
        if (playerYards == 21) {
            Main.typewriter(5, "Success! You have safely crossed the river.");
            return true;
        }

        // --- River's Turn ---
        Main.typewriter(5, "\nYou stop rowing. The river current pushes back...");
        while (currentYards <= playerYards && currentYards < 21) {
            int currentRoll = rand.nextInt(6) + 1; // 1-6 yards
            currentYards += currentRoll;
            Main.typewriter(5, "The river current surges forward " + currentRoll + " yards. River Total: "
                    + currentYards + " yards.");
        }

        // --- Resolution ---
        if (currentYards > 21) {
            Main.typewriter(5,
                    "The river current pushes you " + currentYards + " yards! You managed to hold your ground.");
            Main.typewriter(5, "Success! You safely navigate through the " + getName() + ".");
            return true;
        } else {
            Main.typewriter(5, "The river current reached " + currentYards + " yards, overpowering your " + playerYards
                    + " yards.");
            Main.typewriter(5, "You stopped too soon! The current sweeps you back to your previous room.");
            return false;
        }
        
    }
}