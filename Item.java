/* Worked on by Yash (DONE)
Item (9 tasks)
  ✅ - private instance vars for name, strength, description, int magicType - Completed by Yash
  ✅ + NoArgsConstructor
  ✅ + Item(String _name, String _description)
  ✅ + int getMagicType() - Completed by Yash
  ✅ + String getName() - Completed by Yash
  ✅ + int getStrength() - Completed by Yash
  ✅ + void setDescription(String d) - Completed by Yash
  ✅ + void setName(String _name) - Completed by Yash
  ✅ + isBroken() // returns true if the strength is zero or less, otherwise returns true - Completed by Yash
  ✅ + toString() // returns the description - Completed by Yash
  ✅ + void weaken() // sets strength to be strength divided by two - Completed by Yash
*/
public class Item
{
  // instance variables go here
  // private instance vars for name, strength, description, int magicType
  private String name;
  private int strength;
  private String description;
  private int magicType;


  
  public Item()
  {
    // set magicType to a random number 1-3 (inclusive)
    name = "item name";
    strength = 50;
    description = "item description";
    magicType = (int)(Math.random()*3+1);
  }


  public Item(String _name, String _description)
  {
    name = _name;
    strength = 50;
    description = _description;
    // set magicType to 1
    magicType = 1;
  }

    public Item(Item other)
    {
      name = new String(other.name);
      strength = other.strength;
      description = new String(other.description);
      // set magicType to 1
      magicType = other.magicType;
    }


  // methods go down here
  public int getMagicType(){
    return magicType;
  }

  public String getName(){
    return name;
  }

  public int getStrength(){
    return strength;
  }

  public void setDescription(String d){
    description = d;
  }

  public void setName(String _name){
    name = _name;
  }

  public boolean isBroken(){
    return strength <= 0;
  }

  public String toString(){
    return description;
  }

  public void weaken(){
    strength /= 2;
  }

  

  



}

