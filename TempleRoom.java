
import java.util.Scanner;

public class TempleRoom extends Room{
    private String templeName;
    private boolean hasBeenUnlocked;

    public static String generateName(){
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
            if (player.getBackpack().findKey() != -1) {
                //ask player if they want to unlock the temple
                //maybe get a reward from it?
                Main.typewriter(5, "Do you want to unlock the temple? Type [y]es or [n]o: ");
                String command = input.nextLine();
                if(command.equals("y") || command.equals("yes")) {
                    //unlock the temple not sure how
                    player.fullHeal();
                    Main.typewriter(5, "The temple heals you to full health!");
                    player.getBackpack().removeItem(player.getBackpack().findKey());
                    hasBeenUnlocked = true; //does this need to be a method or can we just change the instance variable?
                } else {
                    Main.typewriter(5, "You choose not to unlock the temple yet.");
                }

            } else {
                Main.typewriter(5, "You do not have the required key to enter!");
                //then what? does it move them out of the room or does it let them stay then move?
                //if let them move then what code is that?
            }
        } 
        super.enterRoom(player);
    }
    

}
