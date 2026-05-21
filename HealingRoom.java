import java.util.Random;
import java.util.Scanner;


public class HealingRoom extends Room {
    
    public HealingRoom() {
        super();
    }

    public HealingRoom(String _name) {
        super(_name);
    }

    public void enterRoom(Player player) {
        player.gainHealth(); // Mr. Riley adding
    }
}
