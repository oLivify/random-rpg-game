/*
!!! USE THIS COMMENT SECTION TO CALL DIBS ON TASKS !!!

List of other classes and methods that we need to create (43 total tasks)

Enemy extends Npc (6 tasks) Alex Can do This one 
  ✅ - private instance vars for health, int magicWeakness, attackName
  ✅ + NoArgsConstructor DIBS MR. RILEY
  ✅ + Enemy(String _name, String _description) DIBS MR. RILEY
  ✅ + String getAttackName()
  ✅ + int getHealth
  ✅ + int getMagicWeakness()
  ✅ + void loseHealth(int h)
  ✅ + void setAttackName()

Item (9 tasks) Yash finished this
  ✅ - private instance vars for name, strength, description, int magicType
  ✅ + NoArgsConstructor DIBS MR. RILEY
  ✅ + Item(String _name, String _description) DIBS MR. RILEY
  ✅ + int getMagicType()
  ✅ + String getName()
  ✅ + int getStrength()
  ✅ + void setDescription(String d)
  ✅ + void setName(String _name)
  ✅ + isBroken() // returns true if the strength is zero or less, otherwise returns true
  ✅ + toString() // returns the description
  ✅ + void weaken() // sets strength to be strength divided by two

Npc (7 tasks) Alex Can do this one 
  ✅ - private instance vars for name, speech, description
  ✅ + NoArgsConstructor DIBS MR. RILEY
  ✅ + Npc(String _name, String _description) DIBS MR. RILEY
  ✅ + String getName
  ✅ + String getSpeech
  ✅ + void setDescription(String d)
  ✅ + void setName(String _name)
  ✅ + void setSpeech
  ✅ + toString() // returns the description

Player (12 tasks) Joshua can do this one
  ✅ - private instance vars for health, kickStrength, punchStrength, enemiesDefeated, Room currentRoom, Item backpack
  ✅ + NoArgsConstructor DIBS MR. RILEY
  ✅ + int getEnemiesDefeated
  ✅ + int getHealth
  ✅ + int getKickStrength
  ✅ + int getPunchStrength
  ✅ + Room getCurrentRoom
  ✅ + Item getBackpack
  ✅ + void increaseEnemiesDefeated // increases by 1
  ✅ + void loseHealth // reduces health by the amount in the arg
  ✅ + void setCurrentRoom
  ✅ + void setBackpack
  ✅ + toString() // returns all the player stats

Room (9 tasks)
  ✅ - private instance vars for name, description, character, roomItem, Room north, Room south, Room east, Room west Finished by Yash
  ✅ + NoArgsConstructor DIBS MR. RILEY
  ✅ + Room(String _name) DIBS MR. RILEY
  ✅ + Npc getCharacter() Finished by Yash
  ✅ + Item getItem() Finished by Yash
  ✅ + Room getLocationTo(String direction) DIBS MR. RILEY
  ✅ + String getName() Finished by Yash
  ✅ + String getPossibleDirections() DIBS MR. RILEY
  ✅ + void linkRoom(Room r, String direction) DIBS MR. RILEY
  ✅ + void setCharacter(Npc character)
  ✅ + void setDescription(String d)
  ✅ + void setItem(Item i)
  ✅ + void setName(String _name)
  ✅ + toString() // returns the description 


Map:
|                |   billiardsRm    |   theBasement     |   masterBedroom   |
|    kitchen     |   diningHall     |   mainHallway     |   theStudy        |
|                |   ballroom       |   grandFoyer      |                   |
*/

import java.util.Scanner;
import java.util.Random;

public class Main {

  private static boolean isGameWon = false;

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Main.typewriter(5, "Please type a random seed number:");
    Random rng = new Random(input.nextInt());
    // create world now please
    // create rooms
   
    // descriptions
    
    // create characters
   
    // player variables
    Player player = new Player();
    player.setCurrentRoom(grandFoyer);
    // the game loop
    while (true) {
      typewriter(50, "\n- - -\n");
      player.getCurrentRoom().enterRoom(player, rng);

      if (player.getBackpack() != null && player.getBackpack().isBroken()) {
        player.setBackpack(null);
      }
      if (isGameWon == true) {
        break;
      }
      if (player.getHealth() <= 0) {
        typewriter(50, "You died. Game over.\n");
        break;
      }
    } // close while loop
    input.close();
  } // close main method


 

  

  

  public static void typewriter(int delay, String s) {
    try {
      for (char c : s.toCharArray()) {
        System.out.print(c); // print characters without newline
        Thread.sleep(delay); // wait for some milli seconds
      }
    } catch (InterruptedException e) {
    }
    // System.out.print("\n"); // finally, add a line break
  }
} // close the class
