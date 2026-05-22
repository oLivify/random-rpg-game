import java.util.ArrayList;


public class Map {
    public static final int WORLD_HEIGHT = 5; // num rows
    public static final int WORLD_WIDTH = 5; // num cols
    

    private Room[][] gameMap;
    private ArrayList<Room> roomList;
    private ArrayList<Item> itemList;
    

    // 5 x 5 grid, 25 rooms
    // 1 spawn point at middle of map [row 2, col 2] normal room
    // ✅ 4 temples
    // ✅ 4 keys 
    // ✅ 5 rivers
    // ✅ 2 healing rooms
    // 6 ambush rooms
    // 3 boring, normal rooms
    


    public Map(ArrayList<Room> roomList, ArrayList<Item> itemList){
        ArrayList<Item> enemyDrops = new ArrayList<Item>();
        // first 10 weapons (the 10 best) are dropped by enemies
        for (int i = 0; i < 10; i++) {
            enemyDrops.add(itemList.remove(0));
        }
        
        gameMap = new Room[WORLD_HEIGHT][WORLD_WIDTH];
        setupRiver();
        boolean oddQuadrantsHaveHealing = Main.rng.nextBoolean();
        setupQuadrant(0,WORLD_WIDTH-2, oddQuadrantsHaveHealing); // Q1
        setupQuadrant(0,0, !oddQuadrantsHaveHealing); // Q2
        setupQuadrant(WORLD_HEIGHT-2,0, oddQuadrantsHaveHealing); // Q3
        setupQuadrant(WORLD_HEIGHT-2,WORLD_WIDTH-2, !oddQuadrantsHaveHealing); // Q4
        fillAllEmptyRooms();
        // finally, ensure that spawn point is a normal room
        gameMap[WORLD_HEIGHT/2][WORLD_WIDTH/2] = new Room(Room.pickRandom(this.roomList));
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
                    gameMap[row][col] = new Room(Room.pickRandom(this.roomList));
                }
            }
        }
    }

    public void makeHealingRoom(int startingRow, int rowOffset, int startingCol, int colOffset) {
        Room healingRoom = new HealingRoom("Healing");
        gameMap[startingRow + rowOffset][startingCol + colOffset] = healingRoom;
        // just a test item for keys
        Item key1 = new Item("Golden Key", "Used to unlock fancy doors", true);
        healingRoom.setItem(key1);
    }

    public void makeAmbushRoom(int startingRow, int rowOffset, int startingCol, int colOffset) {
        Room keyRoom = new AmbushRoom("Ambush");
        Enemy keyHoldingEnemy = new Enemy();
        keyRoom.setCharacter(keyHoldingEnemy);
        gameMap[startingRow + rowOffset][startingCol + colOffset] = keyRoom;
    }

    public void makeTempleRoom(int startingRow, int rowOffset, int startingCol, int colOffset) { 
        Room tempRoom = new TempleRoom("Temple " + (startingRow*10 + startingCol)); // this SHOULD be TempleRoom
        gameMap[startingRow + rowOffset][startingCol + colOffset] = tempRoom;
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
                makeTempleRoom(startingRow,rowOffset, startingCol, colOffset);
                counter++;
            }
            if(counter == 1 && gameMap[startingRow + rowOffset][startingCol + colOffset] == null){
                // place an enemy with a key here
                makeAmbushRoom(startingRow,rowOffset, startingCol, colOffset);
                counter++;
            }
            if(counter == 2 && gameMap[startingRow + rowOffset][startingCol + colOffset] == null){
                // either odd number quadrants or even number quadrants have a healing room
                if(hasHealingRoom){
                    makeHealingRoom(startingRow,rowOffset, startingCol, colOffset);
                }
                counter++;
            }
        }
    }

     

    public void setupRiver(){
        final int RIVER_MIN_COLUMN = 1;
        final int RIVER_MAX_COLUMN = WORLD_WIDTH - 2;
        int previousCol = -1;
        // row 0 is the only truly random location
        int col = Main.rng.nextInt(RIVER_MAX_COLUMN) + RIVER_MIN_COLUMN;
        gameMap[0][col] = new RiverRoom(RiverRoom.generateName());
        previousCol = col;
        // the rest of the rows are dependant on the previous row
        for(int row = 1; row < WORLD_HEIGHT; row++){
            
            if(previousCol == RIVER_MIN_COLUMN){ 
                col = previousCol + 1;
            }
            else if(previousCol == RIVER_MAX_COLUMN){ 
                col = previousCol - 1;
            }           
            else { // either previousCol -1 or previousCol +1
                if(Main.rng.nextInt(2) == 0){
                    col = previousCol + 1;
                } else {
                    col = previousCol - 1;
                }
            }
            if(row == WORLD_HEIGHT / 2 && col == WORLD_WIDTH / 2){ // if spawn point
                if(Main.rng.nextInt(2) == 0){
                    col =  WORLD_WIDTH / 2 + 1;
                } else {
                    col =  WORLD_WIDTH / 2 - 1;
                }
            }
            gameMap[row][col] = new RiverRoom(RiverRoom.generateName());
            previousCol = col;
        }
    }
}
