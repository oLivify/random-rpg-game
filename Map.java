public class Map {
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
        gameMap = new Room[5][5];
        
    }
}
