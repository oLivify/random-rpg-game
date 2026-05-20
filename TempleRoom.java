import java.util.Random;
import java.util.Scanner;

public class TempleRoom extends Room{
    private String templeName;
    private boolean hasBeenUnlocked;

    private static String generateRoomName(){
        return "";
    }

    public TempleRoom() {
        super();
        hasBeenUnlocked = false;
    }

    public TempleRoom(String _name) {
        super(_name);
        hasBeenUnlocked = false;
    }

    public void enterRoom(Player player) {
        Scanner input = new Scanner(System.in);
        if(!hasBeenUnlocked) {
            for (int i = 0; i < player.getBackpack().getSize(); i++) {
                if (player.getBackpack().getItem(i).isKey() == true) {
                    //ask player if they want to unlock the temple
                    //maybe get a reward from it?
                    Main.typewriter(5, "Do you want to unlock the temple? Type yes or no: ");
                    String command = input.nextLine();
                    if(command.equals("yes")) {
                        //unlock the temple not sure how
                        player.fullHeal();
                        Main.typewriter(5, "The temple heals you to full health!");
                        player.getBackpack().removeItem(i);
                        hasBeenUnlocked = true; //does this need to be a method or can we just change the instance variable?
                        break;
                    } else {
                        Main.typewriter(5, "You choose not to unlock the temple yet.");
                        break;
                    }

                } else {
                    Main.typewriter(5, "You do not have the required key to enter!");
                    //then what? does it move them out of the room or does it let them stay then move?
                    //if let them move then what code is that?
                }
            }
        } else {

            Main.typewriter(5, "You have already unlocked this temple! Go find another one!");
        }
        
    }

}
