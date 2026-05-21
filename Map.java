import java.util.Random;

public class Map {
    public static final int WORLD_HEIGHT = 5; // num rows
    public static final int WORLD_WIDTH = 5; // num cols
    

    private Room[][] gameMap;
    

    // 5 x 5 grid, 25 rooms
    // 1 spawn point at middle of map [row 2, col 2] normal room
    // ✅ 4 temples
    // ✅ 4 keys 
    // ✅ 5 rivers
    // ✅ 2 healing rooms
    // 6 ambush rooms
    // 3 boring, normal rooms

    public Map(){
        
        gameMap = new Room[WORLD_HEIGHT][WORLD_WIDTH];
        setupRiver();
        boolean oddQuadrantsHaveHealing = Main.rng.nextBoolean();
        setupQuadrant(0,WORLD_WIDTH-2, oddQuadrantsHaveHealing);
        setupQuadrant(0,0, !oddQuadrantsHaveHealing);
        setupQuadrant(WORLD_HEIGHT-2,0, oddQuadrantsHaveHealing);
        setupQuadrant(WORLD_HEIGHT-2,WORLD_WIDTH-2, !oddQuadrantsHaveHealing);
        fillAllEmptyRooms();
        // finally, ensure that spawn point is a normal room
        gameMap[WORLD_HEIGHT/2][WORLD_WIDTH/2] = new Room(Room.generateName());
        displayMap(); // for debugging only
    }

    public void displayMap(){
        for(int row = 0; row < WORLD_HEIGHT; row++){
            for(int col = 0; col < WORLD_WIDTH; col++){
                String roomName = gameMap[row][col].getName();
                String spaceString = "                      |  ";
                if(roomName.length() < spaceString.length()){
                    spaceString = spaceString.substring(roomName.length());
                }
                System.out.print( roomName + spaceString);
            }
            System.out.println();
        }
    }

    public Room getLocation(int[] coordinates){
        int row = coordinates[0];
        int col = coordinates[1];
        if(row >= 0 && row < Map.WORLD_HEIGHT && col >= 0 && col < Map.WORLD_WIDTH){
            return gameMap[row][col];
        }
        return null;
    }

    public void fillAllEmptyRooms(){
        for(int row = 0; row < WORLD_HEIGHT; row++){
            for(int col = 0; col < WORLD_WIDTH; col++){
                if(gameMap[row][col] == null){
                    gameMap[row][col] = new Room(Room.generateName());
                }
            }
        }
    }

    public void setupQuadrant(int startingRow, int startingCol, boolean hasHealingRoom){
        int[] spots = new int[Map.WORLD_HEIGHT/2 * Map.WORLD_WIDTH/2];
        // make offset values
        int index = 0;
        for(int row = 0; row < Map.WORLD_HEIGHT/2; row++){
            for(int col = 0; col < Map.WORLD_WIDTH/2; col++){
                spots[index] = row * 10 + col;
                index++;
            }
        }
        Main.shuffleArray(spots);
        // there are 4 spots in this quadrant
        int counter = 0;
        for(int i = 0; i < spots.length; i++){
            int rowOffset = spots[i] / 10;
            int colOffset = spots[i] % 10;
            if(counter == 0 && gameMap[startingRow + rowOffset][startingCol + colOffset] == null){
                // place a temple here
                Room tempRoom = new TempleRoom("Temple " + (startingRow*10 + startingCol)); // this SHOULD be TempleRoom
                gameMap[startingRow + rowOffset][startingCol + colOffset] = tempRoom;
                counter++;
            }
            if(counter == 1 && gameMap[startingRow + rowOffset][startingCol + colOffset] == null){
                // place an enemy with a key here
                Room keyRoom = new AmbushRoom("Ambush");
                Enemy keyHoldingEnemy = new Enemy();
                keyRoom.setCharacter(keyHoldingEnemy);
                gameMap[startingRow + rowOffset][startingCol + colOffset] = keyRoom;
                counter++;
            }
            if(counter == 2 && gameMap[startingRow + rowOffset][startingCol + colOffset] == null){
                // either odd number quadrants or even number quadrants have a healing room
                if(hasHealingRoom){
                    Room healingRoom = new HealingRoom("Healing");
                    gameMap[startingRow + rowOffset][startingCol + colOffset] = healingRoom;
                }
                counter++;
            }
        }
    }

    public void setupRiver(){
        for(int row = 0; row < WORLD_HEIGHT; row++){
            // col 1, 2, or 3
            int col = Main.rng.nextInt(3) + WORLD_HEIGHT / 2 - 1;
            // if spawn point
            if(row == WORLD_HEIGHT / 2 && col == WORLD_WIDTH / 2){
                if(Main.rng.nextInt(2) == 0){
                    col++;
                } else {
                    col--;
                }
            }
            gameMap[row][col] = new RiverRoom(RiverRoom.generateName());
        }
    }
}
