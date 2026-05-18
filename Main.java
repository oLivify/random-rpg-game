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
    Random rng = new Random();
    // create world now please
    // create rooms
    Room kitchen = new Room("Kitchen");
    Room diningHall = new Room("Dining Hall");
    Room ballroom = new Room("Ballroom");
    Room masterBedroom = new Room("Master Bedroom");
    Room grandFoyer = new Room("Grand Foyer");
    Room mainHallway = new Room("Main Hallway");
    Room theStudy = new Room("The Study");
    Room billiardsRm = new Room("The Billiards Room");
    Room theBasement = new Room("The Basement");
    // descriptions
    kitchen.setDescription("A dank and dirty room buzzing with flies. Cobwebs hang from the ceiling.");
    diningHall.setDescription("A large room with ornate golden decorations on each wall. The kitchen is to the west.");
    ballroom.setDescription("A vast room with a shiny wooden floor. Huge candlesticks guard the entrance.");
    masterBedroom.setDescription("A large room with a massive bed in the middle of it.");
    grandFoyer.setDescription(
        "The entrance to the dark mansion. Designed to astonish guests. The hallway is north and there are large, double-doors to the west.");
    mainHallway.setDescription("A long dark hall that has creepy paintings on the walls.");
    theStudy.setDescription(
        "A small room with a large wooden desk. The walls are covered in book shelves filled with books.");
    billiardsRm.setDescription(
        "A small cramped room with a large pool table in the middle. The table is in bad shape with ripped felt.");
    theBasement.setDescription(
        "Unlike the rest of the house, the basement is very clean and tidy. There are canned vegetables on a shelf. Has someone been living down here?");
    // link rooms together. don't forget to link the rooms in both directions.
    grandFoyer.linkRoom(mainHallway, "north");
    mainHallway.linkRoom(grandFoyer, "south");
    grandFoyer.linkRoom(ballroom, "west");
    ballroom.linkRoom(grandFoyer, "east");
    ballroom.linkRoom(diningHall, "north");
    diningHall.linkRoom(ballroom, "south");
    kitchen.linkRoom(diningHall, "east");
    diningHall.linkRoom(kitchen, "west");
    diningHall.linkRoom(billiardsRm, "north");
    billiardsRm.linkRoom(diningHall, "south");
    diningHall.linkRoom(mainHallway, "east");
    mainHallway.linkRoom(diningHall, "west");
    mainHallway.linkRoom(theBasement, "north");
    theBasement.linkRoom(mainHallway, "south");
    mainHallway.linkRoom(theStudy, "east");
    theStudy.linkRoom(mainHallway, "west");
    theStudy.linkRoom(masterBedroom, "north");
    masterBedroom.linkRoom(theStudy, "south");
    billiardsRm.linkRoom(theBasement, "east");
    theBasement.linkRoom(billiardsRm, "west");
    theBasement.linkRoom(masterBedroom, "east");
    masterBedroom.linkRoom(theBasement, "west");
    // create characters
    // Ava
    Enemy ava = new Enemy("Ava", "a beautiful chicken");
    ava.setSpeech("cluck... cluck... cluck...");
    ava.setAttackName("PECK");
    if (rng.nextInt(2) == 0) {
      kitchen.setCharacter(ava);
    } else {
      billiardsRm.setCharacter(ava);
    }
    // Becky
    Enemy becky = new Enemy("Becky", "a wicked witch");
    becky.setSpeech("Yahaha! You found me!");
    ava.setAttackName("BAD BREATH");
    if (rng.nextInt(2) == 0) {
      theBasement.setCharacter(becky);
    } else {
      diningHall.setCharacter(becky);
    }
    // Catrina
    Npc catrina = new Npc("Catrina", "a friendly skeleton");
    catrina.setSpeech("Why hello there.");
    if (rng.nextInt(2) == 0) {
      mainHallway.setCharacter(catrina);
    } else {
      ballroom.setCharacter(catrina);
    }
    // Dave
    Enemy dave = new Enemy("Dave", "a smelly zombie");
    dave.setSpeech("Brrlgrh... rhrhl... brains...");
    ava.setAttackName("BITE");
    if (rng.nextInt(2) == 0) {
      masterBedroom.setCharacter(dave);
    } else {
      theStudy.setCharacter(dave);
    }
    // create items
    Item staff = new Item("staff", "a weird old wizard's staff");
    if (rng.nextInt(2) == 0) {
      kitchen.setItem(staff);
    } else {
      diningHall.setItem(staff);
    }
    Item sword = new Item("sword", "a well-decorated sword");
    if (rng.nextInt(2) == 0) {
      masterBedroom.setItem(sword);
    } else {
      theStudy.setItem(sword);
    }
    Item wand = new Item("wand", "a strange, glowing wand");
    if (rng.nextInt(2) == 0) {
      billiardsRm.setItem(wand);
    } else {
      theBasement.setItem(wand);
    }
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
