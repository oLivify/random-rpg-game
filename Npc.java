/*
Npc (7 tasks)
✅ - private instance vars for name, speech, description
✅ + NoArgsConstructor
✅ + Npc(String _name, String _description)
✅ + String getName
✅ + String getSpeech
✅ + void setDescription(String d)
✅ + void setName(String _name)
✅ + void setSpeech
✅ + toString() // returns the description
*/
public class Npc
{
  // instance variable go here
  // private instance vars for name, speech, description
  private String name;
  private String speech;
  private String description;

  public Npc()
  {
    name = "James Charles Bartholemule II";
    speech = "Why hello there traveler... I've been expecting a main character such as yourself to cross paths with me...";
    description = "James Charles was his original name until he was banished from his village for doing questionable acts, he was raised by a wise tree named Bartholemule...";
  }

  public Npc(String newName, String newDescription)
  {
    name = newName;
    description = newDescription;
    speech = "Why hello there traveler... I've been expecting a main character such as yourself to cross paths with me...";
  }

  public Npc(Npc other)
    {
      name = new String(other.name);
      speech = new String(other.speech);
      description = new String(other.description);
    }

  public String getName(){
    return name;
  }
  public String getSpeech(){
    return speech;
  }
  public void setDescription(String d){
    description = d;
  }
  public void setName(String _name){
    name = _name;
  }
  public  void setSpeech(String _speech){
    speech = _speech;
  }
  public String toString(){
    return name + " " + description;
  }
  // methods go down here

  


}
