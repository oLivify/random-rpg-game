/* Worked on by Yash & ______
Room (9 tasks)
✅ - private instance vars for name, description, character, roomItem, Room north, Room south, Room east, Room west - Completed by Yash
✅ + NoArgsConstructor
✅ + Room(String _name)
✅ + Npc getCharacter() - Completed by Yash
✅ + Item getItem() - Completed by Yash
✅ + Room getLocationTo(String direction)
✅ + String getName() - Completed by Yash
✅ + String getPossibleDirections()

✅ + void setCharacter(Npc character)
✅ + void setDescription(String d) DONE
✅ + void setItem(Item i)
✅ + void setName(String _name)
✅ + toString() // returns the description DONE
*/

import java.util.Random;
import java.util.Scanner;

public class Room {

  /*
   * fight with an enemy. returns the new player.getHealth().
   */
  
 
  private String name;
  private String description;
  private Npc character;
  private Item roomItem;
  // private instance vars go here

  public Room() {
    name = "room name";
    description = "room description";
    character = null;
    roomItem = null;
  }

  public Room(String _name) {
    name = _name;
    description = "room description";
    character = null;
    roomItem = null;
  }

  public Room(Room other) {
    name = other.name;
    description = other.description;
    character = other.character;
    roomItem = other.roomItem;
  }

  public void enterRoom(Player player, Random rng) {
    Scanner input = new Scanner(System.in);
    Npc roomNpc = getCharacter();
    Item roomItem = getItem();
    // describe the current room
    Main.typewriter(5, getName() + "\n");
    Main.typewriter(5, toString() + "\n");
    // check if there is a character and/or item in current room
    if (roomNpc != null) {
      Main.typewriter(5, "There is " + roomNpc.toString() + " here.\n");
    }
    if (roomItem != null) {
      Main.typewriter(5, "There is " + roomItem.toString() + " here.\n");
    }
    if (player.getBackpack().getSize() > 0) {
      Main.typewriter(5, "You are holding " + player.getBackpack().getSize() + " items.\n");
    }
    // prompt
    Main.typewriter(50,
        "\nWHAT NEXT? " + getPossibleDirections(player)
            + (getItem() == null ? "" : "take, ")
            + (getCharacter() == null ? "" : "talk, fight, ") + "or quit: ");
    // get user input
    String command = input.next();
    command = command.toLowerCase();
    if (command.equals("north") || command.equals("south") || command.equals("east") || command.equals("west")) {
      player.setLocation(command);
    } else if (command.equals("take")) {
      player.takeItem(roomItem);
    } else if (command.equals("talk")) {
      if (roomNpc != null) {
        Main.typewriter(50, roomNpc.getName() + ": \"" + roomNpc.getSpeech() + "\"\n");
      } else {
        Main.typewriter(50, "There is nobody here to talk\n");
      }
    } else if (command.equals("fight")) {
      FightEvent fight = new FightEvent(rng, player, roomNpc);
      FightEvent.Outcome fightResult = fight.execute();

    } else if (command.equals("quit")) {
      Main.typewriter(50, "Thanks for playing\n");
    } else {
      Main.typewriter(50, "I don't know how to " + command);
      Main.typewriter(50,
          ". Valid options include: " + this.getPossibleDirections(player)
              + (this.getItem() == null ? "" : "take, ")
              + (this.getCharacter() == null ? "" : "talk, fight, ") + "or quit.\n");
    }
    input.close();
  }


  public String getPossibleDirections(Player player) {
    int[] myLocation = player.getLocation();
    int row = myLocation[0];
    int col = myLocation[1];
    String possibleDirections = "Type either: ";
    if (row > 0) {
      possibleDirections += "north, ";
    }
    if (row < Map.WORLD_HEIGHT - 1) {
      possibleDirections += "south, ";
    }
    if (col < Map.WORLD_WIDTH - 1) {
      possibleDirections += "east, ";
    }
    if (col > 0) {
      possibleDirections += "west, ";
    }

    return possibleDirections;
  }


  // methods go down here
  public Npc getCharacter() {
    return character;
  }

  public Item getItem() {
    return roomItem;
  }

  public String getName() {
    return name;
  }

  public void setCharacter(Npc _character) {
    character = _character;
  }

  public void setDescription(String d) {
    description = d;
  }

  public void setItem(Item i) {
    roomItem = i;
  }

  public void setName(String _name) {
    name = _name;
  }

  public String toString() {
    return description;
  }
}
