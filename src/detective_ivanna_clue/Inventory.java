package detective_ivanna_clue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Class Inventory - the inventory of the player in an detective game.
 * 
 * The Inventory represents the list of items a player has.
 *
 * @author Tony Schultze, Kenneth Englisch
 * @version 2019-06-29
 */
public class Inventory
{
    public HashMap<String, Item> inventory;
    public static final int MAX_INVENTORY =  40;
    public int inventoryWeight = 0;
    public boolean pickable = false;

    /**
     * Creates the Inventory
     */
    public Inventory()
    {
       inventory = new HashMap<>();
    }

    /**
     * Putting an item into the HashMap (inventory).
     * @param name The item's name.
     * @param item The item's object.
     */
    public void setInventory(String name, Item item)
    {
        if (inventoryWeight + item.weight <= MAX_INVENTORY) {
            inventory.put(name, item);
            inventoryWeight += item.weight;
            pickable = true;
        }
        else if (inventoryWeight + item.weight > MAX_INVENTORY){
            System.out.println("Your inventory is full and you can't handle any more items.");
            pickable = false;
        }
    }
    
    /**
     * Creating and returning the inventory string. It is representing all items inside of the inventory.
     * @return A String with all items of the inventory.
     */
    public String getInventoryString()
    {
        String inventoryString = "Inventory:";
        Set<String> keys = inventory.keySet();
        
        for (String item : keys)
        {
            inventoryString += " " + item;
        }
        
        inventoryString += "\nInventory used: " + inventoryWeight + " / " + MAX_INVENTORY;
        
        return inventoryString;
    }
    
    /**
     * Pulling an item out of the inventory.
     * @param item The item's name.
     * @return The item's object.
     */
    public Item getItem(String item)
    {
        return inventory.get(item);
    }
    
    /**
     * Removing an item from the inventory.
     * @param item The item's name.
     */
    public void removeItem(String item)
    {
        inventoryWeight -= inventory.get(item).weight;
        inventory.remove(item);
    }
    
    /**
     * Clears the HashMap and sets the weight to 0
     */
    public void clearAll()
    {
        inventory.clear();
        inventoryWeight = 0;
    }
}