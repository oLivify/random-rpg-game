/*
Enemy extends Npc (6 tasks) Alex Can do these 
  ✅ - private instance vars for health, int magicWeakness, attackName
  ✅ + NoArgsConstructor
  ✅ + Enemy(String _name, String _description)
  ✅ + String getAttackName()
  ✅ + int getHealth
  ✅ + int getMagicWeakness()
  ✅ + void loseHealth(int h)
  ✅ + void setAttackName()
*/

import java.util.Random;

public class Enemy extends Npc
{
  // class variables
  private static int enemyCounter = 0;
 

  // class methods
  public static int getEnemyCounter()
  {
    return 0;
  }

  // instance variables go here
  // private instance vars for health, int magicWeakness, attackName
   private int health;
  private int magicWeakness;
  private String attackName;

  public Enemy(){
    super();
    health = 100;
    magicWeakness = 1;
    attackName = "slap";
    enemyCounter++;
  }

  public Enemy(String enemyName, String enemyDescription){
    super(enemyName,enemyDescription);
    health = 100;
    magicWeakness = 1;
    attackName = "slap";
    enemyCounter++;
  }

   public int attackPlayer(Random rng, Player player) {
    int attack = rng.nextInt(6) + rng.nextInt(6) + 2;
    Main.typewriter(50, " > > > " + this.getName() + " uses " + this.getAttackName() + "\n");
    Main.typewriter(50, " > > > -" + attack + " HP\n");
    player.loseHealth(attack);
    Main.typewriter(50, "You have " + player.getHealth() + " HP remaining\n");
    return player.getHealth();
  }



  public String getAttackName(){
    return attackName;
  }

  public int getHealth(){
    return health;
  }
  public int getMagicWeakness(){
    return magicWeakness;
  }
  public void loseHealth(int h){
    health = health- h;
  }
  public  void setAttackName(String _attackName){
    attackName = _attackName;
  }

  // methods go down here


}

