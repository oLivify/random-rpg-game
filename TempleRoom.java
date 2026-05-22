
import java.util.Scanner;

public class TempleRoom extends Room{
    private String templeName;
    private boolean hasBeenUnlocked;

    public static String generateName(){
        return "";
    }

    public TempleRoom() {
        this("Temple", new Enemy());
    }

    public TempleRoom(String _name, Enemy dragon) {
        super(_name);
        hasBeenUnlocked = false;
        super.setCharacter(dragon);
        Item crystal = new Item("Crystal", "a glowing shard of power");
        super.setItem(crystal);
    }

    public void enterRoom(Player player) {
        Scanner input = new Scanner(System.in);
        if(!hasBeenUnlocked) {
            if (player.getBackpack().findKey() != -1) {
                //ask player if they want to unlock the temple
                //maybe get a reward from it?
                Main.typewriter(5, getName() + "\n");
                Main.typewriter(5, toString() + "\n");
                Main.typewriter(5, "Do you want to unlock the temple? Type [y]es or [n]o: ");
                String command = input.nextLine();
                if(command.equals("y") || command.equals("yes")) {
                    //unlock the temple not sure how
                    
                    player.removeItem(player.getBackpack().findKey());
                    Main.typewriter(5, "The key and the door crumble before you.\n");
                    Main.typewriter(5, "As the dust settles, you seen something massive moving in the dungeon ahead...\n");
                    hasBeenUnlocked = true; //does this need to be a method or can we just change the instance variable?
                } else {
                    Main.typewriter(5, "You choose not to unlock the temple yet.\n");
                    player.moveBackwards();
                }

            } else {
                Main.typewriter(5, "You do not have the required key to enter!\n");
                player.moveBackwards();
                //then what? does it move them out of the room or does it let them stay then move?
                //if let them move then what code is that?
            }
        } 

        // fight dragon
        if(hasBeenUnlocked && getCharacter() != null){
            Main.typewriter(5, getName() + "\n");
            Main.typewriter(5, toString() + "\n");
            FightEvent fight = new FightEvent(player, getCharacter());
            FightEvent.Outcome result = fight.execute();
            if(result == FightEvent.Outcome.PLAYER_WIN){
                super.setCharacter(null);
                // temple heals you, you get a CRYSTAL
                player.fullHeal();
                Main.typewriter(5, "* * * The temple heals you to full health! * * *\n");
            }
            else if(result == FightEvent.Outcome.PLAYER_FLED){
                player.moveBackwards();
            }
        }

        if(getCharacter() == null){
            super.enterRoom(player);
        }
    }
    

}
