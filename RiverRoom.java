import java.util.Scanner;



public class RiverRoom extends Room {

    public static String generateName(){
        String[] adjectives = {"Little", "Smokey", "Big", "Great", "East", "West", "North",
            "South","White","Green","Red","Blue","Saint","Winding","Black","Wild","Golden",
            "Grand","Broad","Windy"
        };
        String[] nouns = {"Fork","Snake", "Platte", "Tar", "Wind","Oak","Maple","Day","Night",
            "Spring","Summer","Winter","Falls","Branch","Thorn","Mile","Wood","Elk","Moose",
            "Bear","Fox","Field","Plains","Valley","Deer","Water","Rock","Stone","Willow",
            "Magnolia","Sycamore","Laurel","Locust","Dragon","Spruce","Pine","Balsam","Nutmeg",
            "Hickory","Birch","Cottonwood","Hemlock"
        };
        int adjIndex = Main.rng.nextInt(adjectives.length);
        String firstHalf =  nouns[ Main.rng.nextInt(nouns.length) ];
        firstHalf = firstHalf.substring(0, firstHalf.length()/2);
        String secondHalf =  nouns[ Main.rng.nextInt(nouns.length) ];
        secondHalf = secondHalf.substring(secondHalf.length()/2);
        return adjectives[adjIndex] + " " + firstHalf + secondHalf + " River";
    }

    public static final String[] descriptions = {
        "The roar of the water is deafening as you stand on the rocky bank",
        "Searing-cold spray mist against your face",
        "Mist is thrown up by a frantic, white-capped torrent carving through the stone",
        "Smooth, grey river stones shift beneath your boots as the current surges past, relentless and violent",
        "The water before you moves with a lazy, deliberate patience, its surface as smooth as dull glass",
        "Reeds and weeping willows dip softly into the muddy banks where you stand",
        "Trees by the shore casts long, dark shadows across the brown depths",
        "Occasionally, a gentle ripple fractures the reflection of the sky as a fish breaks the surface",
        "Standing on the soft, sinking mud of the shore, you look out across the expansive",
        "An opaque waterway thick with churned earth",
        "The thick, brown river sweeps debris, twigs, and fallen leaves down toward the sea", 
        "Swift moving and smelling faintly of rich soil and distant rain",
        "The water laps weakly at your feet, leaving a fine layer of silt as it recedes",
        "You look down through the water at your feet, amazed by its absolute clarity",
        "Standing on the sandy shelf, you can see every individual pebble and perhaps a glimmer of gold dust",
        "Alive with undulating patchs of river-weed and the darting shadows of minnows several feet below",
        "The surface is so placid it feels almost invisible and a perfect mirror for the overhanging forest",
        "The scent of pine and damp earth fills the air as you stand at the river's edge",
        "Beams of sunlight filters through the dense canopy of trees above and illuminate patches of the river",
        "Sparkles of light dance across the moving water",
        "The river here is a gentle, rhythmic companion, swirling into quiet whirlpools",
        "The river bends around ancient, moss-covered logs near the shore"
    };
    public static int descriptionIndex = Main.rng.nextInt(descriptions.length);

    public static String pickRandomDescription(){
        descriptionIndex++;
        return descriptions[descriptionIndex % descriptions.length];
    }


    // Constructors mirroring the superclass
    public RiverRoom() {
        super();
        super.setDescription(pickRandomDescription());
    }

    public RiverRoom(String _name) {
        super(_name);
        super.setDescription(pickRandomDescription());
    }

    public void enterRoom(Player player) {
        Scanner input = new Scanner(System.in);
        Main.typewriter(5, "\n" + getName());
        Main.typewriter(5, "\nCross the river? [y] or [n]: ");

        if(input.nextLine().toLowerCase().equals("y")){
            RiverCrossingEvent crossing = new RiverCrossingEvent(player, this);
            RiverCrossingEvent.Outcome crossingResult = crossing.execute();
            if(crossingResult == RiverCrossingEvent.Outcome.SUCCESS){
                super.enterRoom(player); // enter normal room
            }else{
                player.moveBackwards();
            }
        } 
        else{
            player.moveBackwards();
        }
        
    }

    
}