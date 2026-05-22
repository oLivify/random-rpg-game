

import java.util.Scanner;

public class AmbushRoom extends Room {
    //player.fight(); and e.fightPlayer();
    public AmbushRoom() {
        super();
    }

    public AmbushRoom(String name_) {
        super(name_);
    }

    public void enterRoom(Player player) {
        Scanner input = new Scanner(System.in);
        Main.typewriter(5, "You get ambushed by an enemy!");
        ((Enemy)getCharacter()).attackPlayer(player);
        // construct a new FightEvent
        FightEvent fight = new FightEvent(player, getCharacter());
        // execute the fight and it returns an outcome
        FightEvent.Outcome fightResult = fight.execute();
        if(fightResult == FightEvent.Outcome.PLAYER_WIN){
            // enemy died
            super.setCharacter(null);
            // enter the normal room
            super.enterRoom(player);
        }
        else if(fightResult == FightEvent.Outcome.PLAYER_FLED){
            player.moveBackwards();
        }
    }



}
