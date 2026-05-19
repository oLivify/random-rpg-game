/*
Player (12 tasks) Joshua doing this one
  ✅ - private instance vars for location, previousLocation, health, kickStrength, punchStrength, enemiesDefeated, ArrayList<Item> backpack
  ✅ + NoArgsConstructor
  void gainHealth()
  ✅ + int getEnemiesDefeated
  ✅ + int getHealth
  ✅ + int getKickStrength
  ✅ + int getPunchStrength
  int[] getLocation() // returns [row, col]
  int[] getPreviousLocation()  // returns [row, col]
  ✅ + Item getBackpack
  ✅ + void increaseEnemiesDefeated() // increases by 1
  ✅ + void loseHealth(int damage) // reduces health by damage
  void setLocation(int row, int col)
  void setLocation(String direction) // given north, south, east, west set the location
  ✅ + void setCurrentRoom(Room newRoom)
  ✅ + void setBackpack(Item newItem)
  ✅ + toString() // returns all the player stats
*/

import java.util.Random;
import java.util.Scanner;

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

    public void attackEnemy(Random rng, String command, Enemy e) {
      int attack = 0;
      if (command.equals("p")) {
        Main.typewriter(50, "You used PUNCH\n");
        attack = rng.nextInt(punchStrength) + rng.nextInt(punchStrength) + 1;
        if (attack >= 12) {
          Main.typewriter(50, "It's super effective!\n");
        }
      } else if (command.equals("k")) {
        Main.typewriter(50, "You used KICK\n");
        attack = rng.nextInt(kickStrength) + rng.nextInt(kickStrength) + 1;
        if (attack >= 12) {
          Main.typewriter(50, "It's super effective!\n");
        }
      } else if (command.equals("x") && backpack != null) {
        Main.typewriter(50, "You used " + backpack.getName().toUpperCase() + "\n");
        attack = backpack.getStrength();
        if (backpack.getMagicType() == e.getMagicWeakness()) {
          attack = attack * 3;
        }
        if (attack >= 12) {
          Main.typewriter(50, "It's super effective!\n");
        }
        backpack.weaken();
        if (backpack.isBroken()) {
          Main.typewriter(50, "Oh no! ! ! ! ! ! ! Your " + backpack.getName() + " broke! ! ! ! ! ! !\n");
          // this.setBackpack(null);
        }
      } else if (!command.equals("r")) {
        Main.typewriter(50, "Sorry, I don't know how to " + command);
        Main.typewriter(50,
            ". Valid options: p, k, r" + (backpack == null ? "" : ", x = use " + backpack.getName()) + "\n");
        return;
      }
      Main.typewriter(50, e.getName() + " -" + attack + " HP\n");
      e.loseHealth(attack);
    }


    public int fight(Random rng) {
      Scanner input = new Scanner(System.in);
      Npc currentNpc = this.getCurrentRoom().getCharacter();
      if (currentNpc == null) {
        Main.typewriter(50, "There is nobody here to fight.\n");
        return this.getHealth();
      }
      if (currentNpc instanceof Enemy == false) {
        Main.typewriter(50, currentNpc.getName() + " doesn't want to fight you.\n");
        return this.getHealth();
      }
      Enemy e = (Enemy) currentNpc;
      while (this.getHealth() > 0) {
        System.out.print("FIGHT!!! p = punch, k = kick, r = run"
            + (this.getBackpack() == null ? "" : ", x = use " + this.getBackpack().getName()) + ": ");
        String command = input.next();
        command = command.toLowerCase();
        if (command.equals("r")) {
          if (rng.nextInt(2) == 0) {
            Main.typewriter(50, "You escaped... but " + e.getName() + " hits you as you run away...\n");
            e.attackPlayer(rng, this);
            return this.getHealth();
          } else {
            Main.typewriter(50, "Oof! Tried to run away, but could not escape!\n");
          }
        }
        attackEnemy(rng, command, e);
        if (this.getBackpack() != null && this.getBackpack().isBroken()) {
          this.setBackpack(null);
        }
        if (e.getHealth() > 0) {
          // enemyAttackPlayer
          e.attackPlayer(rng, this);
        } else {
          Main.typewriter(50, e.getName() + " fainted! You won the fight!\n");
          this.getCurrentRoom().setCharacter(null);
          this.increaseEnemiesDefeated();
          return this.getHealth();
        }
      }
      return this.getHealth();
    }


    public void gainHealth(){
      if(health <= 100 && health > 0){
        int temp = (int)((100 - health) * 0.5);
        Main.typewriter(5, "*** Gained +" + temp + " health ***");
        health += temp;
      }
    }
    
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

    public Room getPreviousRoom(){
        // TODO
        return null;
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

    public void takeItem() {
        if (getBackpack() != null) {
            Item temp = this.getBackpack();
            this.setBackpack(this.getCurrentRoom().getItem());
            this.getCurrentRoom().setItem(temp);
            Main.typewriter(50, "You drop " + temp + " and pick up " + this.getBackpack() + ".\n");
        } else {
            // not holding anything right now
            this.setBackpack(this.getCurrentRoom().getItem());
            this.getCurrentRoom().setItem(null);
            Main.typewriter(50, "You pick up " + this.getBackpack() + ".\n");
        }
    }

    public String toString() {
        return "Health: " + health + " Kick Strength: "+kickStrength+ 
        " Punch Strength: " + punchStrength + " Enemies Defeated: " + enemiesDefeated + " Current Room: " + currentRoom + " Backpack: " + backpack;
    }





}