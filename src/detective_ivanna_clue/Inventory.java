package detective_ivanna_clue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

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
    
    private Stack<Item> inv; 

    /**
     * Creates the Inventory
     */
    public Inventory()
    {
       inventory = new HashMap<>();
       inv = new Stack<Item>();
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
    
    public void setAnyInventory(Item item) 
    {
    	inv.add(item);
        inventoryWeight += item.weight;
     
    }
    
    public boolean canBePickedUp(Item item) 
    { 
    	boolean pickUp;
    	if (inventoryWeight + item.weight <= MAX_INVENTORY) pickUp = true;
    	else pickUp = false;
    	return pickUp;
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
    
    public String getAnyInventoryString()
    {
        String inventoryString = "Inventory:";
        Set<String> keys = inventory.keySet();
        
        for (int i = 0; i < inv.size(); i++) 
    	{
    		Item it = inv.get(i);
    		
    		inventoryString += " " + it.name;
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
    
    public Item getAnyItem() 
    {
    	Item i = inv.pop();
    	inventoryWeight -= i.weight;
    	return i;
    }
    
    public boolean isEmpty() 
    {
    	return inv.isEmpty();
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
    /*
     * not using that bc we delete when we pop
     */
    public void removeAnyItem(Item item) 
    {
    	int i = inv.indexOf(item);
    	inventoryWeight -= inv.get(i).weight;
    	inv.remove(i);
    }
    
    /**
     * Clears the HashMap and sets the weight to 0
     */
    public void clearAll()
    {
        inventory.clear();
        inventoryWeight = 0;
    }
    
    public void clearInv() 
    {
    	inv.clear();
    	inventoryWeight = 0;
    }
}