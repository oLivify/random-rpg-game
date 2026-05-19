public class Map {
    public static final int WORLD_HEIGHT = 5; // num rows
    public static final int WORLD_WIDTH = 5; // num cols
    

    private Room[][] gameMap;

    // 5 x 5 grid, 25 rooms
    // 1 spawn point at middle of map [row 2, col 2]
    // 4 temples
    // 4 keys
    // 5 rivers
    // 2 healing rooms
    // 6 ambush rooms
    // 3 boring, nothing rooms

    public Map(){
        gameMap = new Room[WORLD_HEIGHT][WORLD_WIDTH];

    }

    public Room getLocation(int[] coordinates){
        int row = coordinates[0];
        int col = coordinates[1];
        if(row >= 0 && row < Map.WORLD_HEIGHT && col >= 0 && col < Map.WORLD_WIDTH){
            return gameMap[row][col];
        }
        return null;
    }
}
