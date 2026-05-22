import java.util.ArrayList;


public class Inventory {
    /*
     * Inventory
     * 
     */
    private ArrayList<Item> storage;

    public Inventory() {
        storage = new ArrayList<Item>();
    }

    public void addItem(Item newItem){
        removeBrokenItems();
        storage.add(newItem);
    }

    public Inventory clone(){
        removeBrokenItems();
        Inventory copy = new Inventory();
        copy.storage = new ArrayList<Item>(storage);
        return copy;
    }

    public void displayItems(){
        for(int i=0; i<storage.size();i++){
            Main.typewriter(5, "\n   " + i + " = " + storage.get(i));
        }
    }

    public Item getItem(int index){
        if(index >= 0 && index < storage.size()){
            return storage.get(index);
        }
        return null;
    }

    public int getSize(){
        removeBrokenItems();
        return storage.size();
    }

    public int findKey() {
        System.out.println(storage);
        for(int i=0; i<storage.size();i++){
            if(storage.get(i).isKey()) {
                return i;
            }
        }
        return -1;
    }

    public Item loseRandomItem(){
        if(storage.size() == 0){
            return null;
        }
        return storage.remove(Main.rng.nextInt(storage.size()));
    }

    public void removeBrokenItems(){
        for(int i=storage.size()-1; i>=0; i--){
            if(storage.get(i).isBroken()){
                storage.remove(i);
            }
        }
    }

    public Item removeItem(int index){
        Item result = null;
        removeBrokenItems();
        if(index >= 0 && index < storage.size()){
            result = storage.remove(index);
        }
        return result;
    }

    
}
