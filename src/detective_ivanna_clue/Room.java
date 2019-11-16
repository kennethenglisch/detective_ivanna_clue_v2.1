package detective_ivanna_clue;

import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an detective game.
 *
 * This class is part of the "Detective Ivanna Clue: The mystical Murder" application. 
 * "Detective Ivanna Clue: The mystical Murder" is a text based detective game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west, left and right.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Tony Schultze, Kenneth Englisch
 * @version 2019-07-03
 */
public class Room 
{
    public String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Item> items;
    private HashMap<String, NPC> npcs;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new HashMap<>();
        npcs = new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param direction A String with a direction (e.g. north).
     * @param neighbor A Room that is the neighbour in the given direction.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Get the exits of the current room.
     * @return A String presenting the exits of the room
     */
    public String getExitString()
    {
        String exitString = "Exits:";
        Set<String> keys = exits.keySet();
        
        for (String exit : keys) {
            exitString += " " + exit;
        }
        return exitString;
    }
    
    /**
     * Return the room that is reached if we go from this room in the given direction.
     * If there is no room, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Return a description of the room in the form:
     *      You have entered the ...
     *      Exits: ...
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You have entered the " + description + "\n" + getExitString();
    }
    
    /**
     * Putting an item into the HashMap.
     * @param itemName A string representing the item's name.
     * @param item The actual object of the item.
     */
    public void setItem(String itemName, Item item)
    {
        items.put(itemName, item);
    }
    
    /**
     * Pulling a specific Item out of the HashMap.
     * @param item A string that represents the item's name.
     * @return The item matching to the name.
     */
    public Item getItem(String item)
    {
        return items.get(item);
    }
    
    /**
     * Removing a specific item out of the HashMap.
     * @param item A String with the name of the item we want to remove.
     */
    public void removeItem(String item)
    {
        items.remove(item);
    }
    
    /**
     * Getting all Items out of the room and putting it into a string.
     * @return A string with all items inside of this room.
     */
    public String getRoomItem()
    {
        String itemString = "";
        Set<String> item = items.keySet();
        
        for (String i : item)
        {
            itemString += " " + i + " - " + items.get(i).description + 
            " (weight category: " + items.get(i).weight + " of " + items.get(i).MAX_WEIGHT + ")\n";
        }
        
        return itemString;
    }
    
    /**
     * Checks if the room contains at least one item.
     * @return True if there is no item in this Room.
     *         False if there is at least on item.
     */
    public boolean noItems()
    {
        return items.isEmpty();
    }
    
    /**
     * Setting an NPC inside of this room. (Putting it into the HashMap)
     * @param name A string representing the name of the NPC.
     * @param npc The actual object of the NPC.
     */
    public void setNPC(String name, NPC npc)
    {
        npcs.put(name, npc);
    }
    
    /**
     * Pulling a specific NPC out of the HashMap.
     * @param npc A string representing the npc's name to pull him out.
     * @return The object of this NPC.
     */
    public NPC getNPC(String npc)
    {
        return npcs.get(npc);
    }
    
    /**
     * Getting all Items out of the room and putting it into a string.
     * @return A string with all items inside of this room.
     */
    public String getNPCString()
    {
        String roomNPC = "";
        Set<String> keys = npcs.keySet();
        
        for (String npc : keys)
        {
            roomNPC += "\n" + npc.substring(0,1).toUpperCase() + npc.substring(1);
        }
        
        return roomNPC;
    }
}
