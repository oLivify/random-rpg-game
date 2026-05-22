/*
Player (12 tasks) Joshua doing this one
  ✅ - private instance vars for location, previousLocation, health, kickStrength, punchStrength, enemiesDefeated, ArrayList<Item> backpack
  ✅ + NoArgsConstructor
  ✅ + attackEnemy(command, e)
  ✅ + + void displayBackpack()
  ✅ + fight(currentNpc)
  ✅ + void gainHealth()
  ✅ + int getEnemiesDefeated
  ✅ + int getHealth
  ✅ + int getKickStrength
  ✅ + int getPunchStrength
  ✅ + int[] getLocation() // returns [row, col]
  ✅ + int[] getPreviousLocation()  // returns [row, col]
  ✅ + ArrayList<Item> getBackpack 
  ✅ + void increaseEnemiesDefeated() // increases by 1
  ✅ + void loseHealth(int damage) // reduces health by damage
  void setLocation(int row, int col)
  void setLocation(String direction) // given north, south, east, west set the location
  ✅ + void setCurrentRoom(Room newRoom)
  ✅ + Item takeItem(Item newItem)
  ✅ + toString() // returns all the player stats
*/


import java.util.Scanner;

public class Player {
    // private instance vars go here
    private int health;
    private int kickStrength;
    private int punchStrength;
    private int enemiesDefeated;
    private Room currentRoom;
    private Inventory backpack;
    private int[] location; // [row, col] NOTE: game will set this to start at middle room
    private int[] previousLocation; // [row, col] NOTE: game will set this to start at middle room

    public Player() {
        health = 100;
        kickStrength = Main.rng.nextInt(6) + 1 + Main.rng.nextInt(6) + 1;
        punchStrength = 13 - kickStrength;
        enemiesDefeated = 0;
        currentRoom = null;
        location = new int[2];
        previousLocation = new int[2];
        location[0] = Map.WORLD_HEIGHT/2;
        location[1] = Map.WORLD_WIDTH/2;
        previousLocation[0] = Map.WORLD_HEIGHT/2;
        previousLocation[1] = Map.WORLD_WIDTH/2;
        backpack = new Inventory();
    }

    public int attackEnemy(String command, Enemy e) {
        Scanner input = new Scanner(System.in);
        int attack = 0;
        if (command.equals("p")) {
            Main.typewriter(5, "You used PUNCH\n");
            attack = Main.rng.nextInt(punchStrength) + Main.rng.nextInt(punchStrength) + 1;
            if (attack >= 20) {
                Main.typewriter(5, "It's SUPER effective!\n");
            }
        } else if (command.equals("k")) {
            Main.typewriter(5, "You used KICK\n");
            attack = Main.rng.nextInt(kickStrength) + Main.rng.nextInt(kickStrength) + 1;
            if (attack >= 20) {
                Main.typewriter(5, "It's SUPER effective!\n");
            }
        } else if (command.equals("u") && backpack != null) { // use item
            // which item?
            backpack.displayItems();
            Main.typewriter(5, "\nWhich item? Type either: ");
            

            Item chooseItem = backpack.getItem(input.nextInt());

            while (chooseItem == null || !(chooseItem instanceof Weapon)) {
                Main.typewriter(5, "You cannot fight with that item. Choose a different item\n");
                
                if(backpack.getSize() == 1) {
                    Main.typewriter(5, "You have no valid weapons to use!\n");
                    return 0; 
                }
                
                Main.typewriter(5, "Which item? Type either:\n");
                backpack.displayItems();
                chooseItem = backpack.getItem(input.nextInt()); 
            }
            Weapon weapon = (Weapon)(chooseItem); // changed class from Item to Weapon, cast to Weapon

            Main.typewriter(5, "You used " + weapon.getName().toUpperCase() + "\n");
            attack = weapon.getDamage(); // changed to getDamage from getStrength;
            if (weapon.getMagicType() == e.getMagicWeakness()) {
                attack = attack * 3;
            }
            if (attack >= 20) {
                Main.typewriter(50, "It's SUPER effective!\n");
            }
            weapon.weaken();
            if (weapon.isBroken()) {
                Main.typewriter(50, "Oh no! ! ! ! ! ! ! Your " + weapon.getName() + " broke! ! ! ! ! ! !\n");
                // this.setBackpack(null);
            }
        } else if (!command.equals("r")) {
            Main.typewriter(25, "Sorry, I don't know how to " + command);
            Main.typewriter(25,
                    ". Valid options: p, k, r" + (backpack == null ? "" : ", u = use an item\n"));
            return 0;
        }
        Main.typewriter(5, e.getName() + " -" + attack + " HP\n");
        return attack;
    }



   

    public void gainHealth() {
        if (health <= 100 && health > 0) {
            int temp = (int) ((100 - health) * 0.5);
            Main.typewriter(5, "*** Gained +" + temp + " health ***\n");
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

    public int[] getLocation() {
        return location.clone();
    }

    public int[] getPreviousLocation() {
        return previousLocation.clone();
    }

    public int getPunchStrength() {
        return punchStrength;
    }

    public Inventory getBackpack() {
        // return a COPY so the real backpack object stays private
        return backpack.clone();
    }

    public void increaseEnemiesDefeated() {
        enemiesDefeated++;
    }

    public void loseHealth(int damage) {
        health -= damage;
    }

    public Item loseRandomItem(){
        return backpack.loseRandomItem();
    }


    public void moveBackwards(){
        // swap
        int tempRow = location[0];
        int tempCol = location[1];
        location[0] = previousLocation[0];
        location[1] = previousLocation[1];
        previousLocation[0] = tempRow;
        previousLocation[1] = tempCol;
    }

    public void setLocation(int row, int col) {
        //System.out.println("setLocation "+location[0]+" "+location[1]);
        if(row >= 0 && row < Map.WORLD_HEIGHT && col >= 0 && col < Map.WORLD_WIDTH){
            previousLocation[0] = location[0];
            previousLocation[1] = location[1];
            location[0] = row;
            location[1] = col;
        }
        //System.out.println("setLocation "+row+" "+col);
    }

    public void setLocation(String direction) {
        if(direction.equalsIgnoreCase("north")){
            setLocation(location[0] - 1, location[1]);
        }
        else if(direction.equalsIgnoreCase("south")){
            setLocation(location[0] + 1, location[1]);
        }
        else if(direction.equalsIgnoreCase("east")){
            setLocation(location[0], location[1] + 1);
        }
        else if(direction.equalsIgnoreCase("west")){
            setLocation(location[0], location[1] - 1);
        }
    }

    /**
     * @param newItem The item being taken
     * @return A dropped item or null if nothing was dropped
     */
    public Item takeItem(Item newItem) {
        Item result = null;
        if (backpack.getSize() > 0) {
            Scanner input = new Scanner(System.in);
            backpack.displayItems();
            Main.typewriter(5, "\n   -1 = Keep everything ");
            Main.typewriter(5, "\nWould you like to drop something? Type either: ");
            
            int userNumber = input.nextInt();
            if (userNumber >= 0 && userNumber < backpack.getSize()) {
                Item dropped = backpack.removeItem(userNumber);
                Main.typewriter(50, "You drop " + dropped + " and pick up " + newItem + ".\n");
                result = dropped;
            } else {
                Main.typewriter(25, "You pick up " + newItem + ".\n");
            }
            backpack.addItem(newItem);

        } else {
            // not holding anything right now
            backpack.addItem(newItem);
            Main.typewriter(25, "You pick up " + newItem + ".\n");
        }
        return result;
    }

    public String toString() {
        return "Health: " + health + " Kick Strength: " + kickStrength +
                " Punch Strength: " + punchStrength + " Enemies Defeated: " + enemiesDefeated + " Current Room: "
                + currentRoom + " Backpack: " + backpack;
    }

    public void fullHeal() {
        this.health = 100;
    }

}