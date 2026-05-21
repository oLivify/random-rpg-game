import java.util.Scanner;

import java.util.Random;

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

    // Constructors mirroring the superclass
    public RiverRoom() {
        super();
    }

    public RiverRoom(String _name) {
        super(_name);
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