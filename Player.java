/*
Player (12 tasks) Joshua doing this one
  ✅ - private instance vars for health, kickStrength, punchStrength, enemiesDefeated, Room currentRoom, Item backpack
  ✅ + NoArgsConstructor
  ✅ + int getEnemiesDefeated
  ✅ + int getHealth
  ✅ + int getKickStrength
  ✅ + int getPunchStrength
  ✅ + Room getCurrentRoom
  ✅ + Item getBackpack
  ✅ + void increaseEnemiesDefeated() // increases by 1
  ✅ + void loseHealth(int damage) // reduces health by damage
  ✅ + void setCurrentRoom(Room newRoom)
  ✅ + void setBackpack(Item newItem)
  ✅ + toString() // returns all the player stats
*/
public class Player
{
    // private instance vars go here
    private int health;
    private int kickStrength;
    private int punchStrength;
    private int enemiesDefeated;
    private Room currentRoom;
    private Item backpack;

    public Player()
    {
        health = 100;
        kickStrength = (int)(Math.random()*6+1) + (int)(Math.random()*6+1);
        punchStrength = 13 - kickStrength;
        enemiesDefeated = 0;
        currentRoom = null;
        backpack = null;
    }

    // methods go down here
    public int getEnemiesDefeated() {
        return enemiesDefeated;
    }

    public int getHealth() {
        return health;
    }

    public int getKickStrength() {
        return kickStrength;
    }

    public int getPunchStrength() {
        return punchStrength;
    }

    public Room getCurrentRoom() {
        return new Room(currentRoom);
    }

    public Item getBackpack() {
      if (backpack == null) {
        return null;
      }
        return new Item(backpack);
    }

    public void increaseEnemiesDefeated() {
        enemiesDefeated++;
    }

    public void loseHealth(int damage) {
        health -= damage;
    }

    public void setCurrentRoom(Room newRoom) {
        currentRoom = newRoom;
    }

    public void setBackpack(Item newItem) {
        backpack = newItem;
    }

    public String toString() {
        return "Health: " + health + " Kick Strength: "+kickStrength+ 
        " Punch Strength: " + punchStrength + " Enemies Defeated: " + enemiesDefeated + " Current Room: " + currentRoom + " Backpack: " + backpack;
    }





}