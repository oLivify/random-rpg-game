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
  private Item reward;
  // enemy can drop sword or scythe

  

  public Enemy(){
    super();
    health = 100;
    magicWeakness = 1;
    attackName = "slap";
    enemyCounter++;
  }
  // constructor that has enemy drop an item
  public Enemy(Item _reward){
    super();
    health = 100;
    magicWeakness = 1;
    attackName = "slap";
    enemyCounter++;
    reward = _reward;
  }

  public Enemy(String enemyName, String enemyDescription){
    super(enemyName,enemyDescription);
    health = 100;
    magicWeakness = 1;
    attackName = "slap";
    enemyCounter++;
  }

  public Enemy(String enemyName, String enemyDescription, Item _reward){
    super(enemyName,enemyDescription);
    health = 100;
    magicWeakness = 1;
    attackName = "slap";
    reward = _reward;
    enemyCounter++;
  }

   public int attackPlayer(Player player) {
    int attack = Main.rng.nextInt(6) + Main.rng.nextInt(6) + 2;
    Main.typewriter(5, " > > > " + this.getName() + " uses " + this.getAttackName() + "\n");
    Main.typewriter(5, " > > > -" + attack + " HP\n");
    return attack;
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
  public Weapon dropReward() { // drops weapon when defeated
    return new Weapon((Weapon)reward); //new copy
  }


}

