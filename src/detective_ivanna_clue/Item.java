package detective_ivanna_clue;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class Item - an item in an detective game.
 * 
 * An "Item" represents one usable item in the game. (e.g. weapons)
 * It is connected to a room.
 *
 * @author Tony Schultze, Kenneth Englisch
 * @version 2019-06-25
 */
public class Item
{
    public String description;
    public String name;
    public int weight;
    
    public final int MAX_WEIGHT = 30;
    public final int MIN_WEIGHT = 1;

    /**
     * Create an Item described "description". 
     * Initially, it is not connected to a room.
     * "description" is something like "a map" or "a key for the room door"
     * @param description The item's description.
     */
    public Item(String name, String description, int weight)
    {
        this.name = name;
        this.description = description;
        
        if (weight <= MIN_WEIGHT)
            this.weight = MIN_WEIGHT;
        else if (weight >= MAX_WEIGHT)
            this.weight = MAX_WEIGHT;
        else
            this.weight = weight;
    }
    
    public String getDescription()
    {
        return description;
    }
}