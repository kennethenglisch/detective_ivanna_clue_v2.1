package detective_ivanna_clue;

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Set;

/**
 *  This class is the main class of the "Detective Ivanna Clue: The mysterical Murder" application. 
 *  "Detective Ivanna Clue: The mysterical Murder" is a very simple, text based detective game. 
 *  Users can walk around some scenery. 
 *  
 *  This game is like a singleplayer version of the famous game "Cluedo"
 *  but has some important changes.
 *  
 *  Later on Items and People will be added.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game. It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Tony Schultze, Kenneth Englisch
 * @version 2019-07-03
 */

public class Game 
{
    // reading the players input
    private Parser parser;
    // the Room the player is currently inside
    public Room currentRoom;
    // the inventory of the player
    private Inventory inventory;

    private boolean first = false;
    private Random random;
    // ArrayList keeping track of all items
    private ArrayList<Item> items;
    // ArrayList keeping track of all items that are weapons 
    private ArrayList<Item> weapons;
    // ArrayList keeping track of all items that are secret
    private ArrayList<Item> secretItems;
    // ArrayList keeping track of all rooms
    private ArrayList<Room> rooms;
    // ArrayList keeping track of all weapons that are placed in a room
    private ArrayList<Item> placedWeapons;
    // HashMap keeping track of the rooms that have items in it
    private HashMap<Item, Room> roomsWithItem;
    // HashMap keeping track of the rooms that have secret items in it
    private HashMap<Item, Room> roomsWithSecretItem;
    // a String representing the murder weapon
    private String murderWeapon;
    // HashMap keeping track of the steps recently made
    private HashMap<String, Room> recentCommands;
    // used as a step counter
    private int n=0;

    
    // the percentage chance that the treasure chest is being created
    private double SPAWN_CHANCE = 0.10;

    // defining the fields of NPCs
    NPC blue, guide, green;

    // defining the fields of Rooms
    Room hall, mainLobby, lounge, diningRoom, kitchen, lobby, billiardRoom, library, study, ballRoom, conservatory, treasury, crimeRoom;

    // defining the fields of Items
    Item map, key, treasureChest, knife, pistol, candleholder, rope, mace, axe, barbell, trophy, poison, note;

    /**
     * Create the game and initialise its internal map, items and npcs.
     */
    public static void main(String args[]) 
    {
        Game game = new Game();
        
        game.createGame();
        game.play();
    }
    
    public void createGame() 
    {
    	parser = new Parser();
        inventory = new Inventory();
        weapons = new ArrayList<>();
        items = new ArrayList<>();
        rooms = new ArrayList<>();
        placedWeapons = new ArrayList<>();
        roomsWithItem = new HashMap<>();
        secretItems = new ArrayList<>();
        roomsWithSecretItem = new HashMap<>();
        random = new Random();
        recentCommands = new HashMap<>();
        createRooms();
        createItems(); 
        randomMurder();
        createNPC();
    }

    /**
     * Create all the rooms and link their exits together.
     * Adding the rooms that will contain weapons into a HashMap. 
     * Setting the starting point if the player
     */
    private void createRooms()
    {
        // create the rooms
        hall = new Room("hall");
        mainLobby = new Room("main lobby");
        lounge = new Room("lounge");
        diningRoom = new Room("dining room");        
        kitchen = new Room("kitchen");
        lobby = new Room("lobby");        
        billiardRoom = new Room("billiard room");        
        library = new Room("library");        
        study = new Room("study");        
        ballRoom = new Room("ball room");
        conservatory = new Room("conservatory");

        // add the rooms that gonna have weapons inside to an ArrayList
        rooms.add(lounge);
        rooms.add(diningRoom);
        rooms.add(kitchen);
        rooms.add(billiardRoom);
        rooms.add(library);
        rooms.add(study);
        rooms.add(ballRoom);
        rooms.add(conservatory);
        Collections.shuffle(rooms);

        // initialise room exits
        hall.setExit("north", mainLobby);
        hall.setExit("west", lounge);

        lounge.setExit("north", diningRoom);
        lounge.setExit("east", hall);

        mainLobby.setExit("north", ballRoom);
        mainLobby.setExit("east", lobby);
        mainLobby.setExit("south", hall);
        mainLobby.setExit("west", diningRoom);

        diningRoom.setExit("north", kitchen);
        diningRoom.setExit("east", mainLobby);
        diningRoom.setExit("south", lounge);

        kitchen.setExit("south", diningRoom);

        lobby.setExit("west", mainLobby);
        lobby.setExit("left", billiardRoom);
        lobby.setExit("right", library);

        billiardRoom.setExit("west", lobby);

        library.setExit("south", study);
        library.setExit("west", lobby);

        study.setExit("north", library);

        ballRoom.setExit("east", conservatory);
        ballRoom.setExit("south", mainLobby);

        conservatory.setExit("west", ballRoom);

        currentRoom = hall;  // start game in the hall
    }

    /**
     * Creating the NPCs, giving them specific answers and putting them into a HashMap
     */
    public void createNPC()
    {
        // creating new NPCs
        // ("name")
        blue = new NPC("tony");
        guide = new NPC("guide");
        green = new NPC("kenneth");

        // giving the NPCs answers
        blue.fillAnswers("The murderer used the " + murderWeapon + " to kill.");
        blue.fillAnswers("You are dumb.");
        blue.fillAnswers("Get on my level!");

        guide.fillAnswers("Hello Detective Ivanna Clue. Nice to meet you. Something awful happened.\nSomeone was murdered. You have to find the murderer." 
            + "\nYou can walk through the house with the command: " + CommandWord.GO + ". If you need help just type '" + CommandWord.HELP + "'.");

        green.fillAnswers("I bims " + green.name + "!");
        
        // filling the answers of the npcs with tips based on the actual murder weapon.
        switch (murderWeapon){
                   case "knife": guide.fillAnswers("I have to cut some tomatoes for todays dinner.");
                                        break;
                   case "axe": guide.fillAnswers("I wanted to split some wood today.");
                                        break;
                   case "poison": guide.fillAnswers("Did I just saw a snake?");
                                        break;                     
                   case "pistol": guide.fillAnswers("Kaboooom!");
                                        break;
                   case "candleholder": guide.fillAnswers("It smells like wax in here.");
                                        break;
                   case "rope": guide.fillAnswers("Can we play some rope war?");
                                        break;
                   case "mace": guide.fillAnswers("Wanna play some baseball?");
                                        break;
                   case "barbell": guide.fillAnswers("I want lift some weights.");
                                        break;                     
                   case "trophy": guide.fillAnswers("Did you know that the dead person had won a champion ship?");
                                        break;
        }

        // putting the NPCs into the HashMap
        kitchen.setNPC(blue.name, blue);
        hall.setNPC(guide.name, guide);
        mainLobby.setNPC(green.name, green);
    }

    /**
     * Creating the hidden rooms and exits.
     * With a chance of 10% a hidden room will be spawned with a game winning item (inside of a chest that must be opened with a key)
     */
    public void createHiddenRooms()
    {
        // setting the new Exits
        kitchen.setExit("west", study);
        study.setExit("east", kitchen);
        conservatory.setExit("east", lounge);
        lounge.setExit("west", conservatory);

        // rolling the dice whether we are creating the secret room or not
        double rand = random.nextDouble();
        if (rand <= SPAWN_CHANCE)
        {
            treasury = new Room("hidden treasury");
            treasury.setItem(treasureChest.name, treasureChest);
            ballRoom.setExit("north", treasury);
            treasury.setExit("south",ballRoom);
        }
    }

    /**
     * Checks if the inventory has a specific item in it
     * @return True if the player has the item in it's iventory.
     *         False if not.
     */
    public boolean inventoryHasItem(String item)
    {
        return inventory.getInventoryString().contains(item) ? true : false;
    }

    /**
     * Creating all items (weapons and secret items)
     * Adding the items into their specific ArrayList (weapons or secretItems)
     */
    private void createItems()
    {
        //items
        map = new Item("map" , "A map that leads you the way through the crime scene.\n" 
            + "You can now have a closer look at the rooms and maybe discover something new", 1);
        key = new Item("key" , "In which lock does this key fit...?", 1);       
        treasureChest = new Item("treasurechest", "A chest with a note inside that tells you the solution of the puzzle", 5);        
        knife = new Item("knife", "An very sharp knife", 1);        
        axe = new Item("axe", "A very sharp and heavy axe", 5);        
        poison = new Item("poison", "Very effective to kill someone without any injurys", 1);        
        pistol = new Item("pistol", "A Colt M1911 pistol, very dangerous but a loud weapon.", 2);        
        candleholder = new Item("candleholder", "A beautiful old candleholder made out of silver", 3);        
        rope = new Item("rope", "Just a long rope", 1);        
        mace = new Item("mace", "A heavy mace, very primitive weapon", 30);       
        barbell = new Item("barbell", "A barbel made out of steel, very heavy", 15);        
        trophy = new Item("trophy", "A golden trophy", 10);
        note = new Item("note", "The note tells you the murder weapon!", 1);

        // add all weapons to an ArrayList
        weapons.add(knife);
        weapons.add(axe);
        weapons.add(poison);
        weapons.add(pistol);
        weapons.add(candleholder);
        weapons.add(rope);
        weapons.add(mace);
        weapons.add(barbell);
        weapons.add(trophy);
        Collections.shuffle(weapons);

        // add the secret items to an ArrayList
        secretItems.add(map);
        secretItems.add(key);

        randomSpawnsWeapons();

    }

    /**
     * Putting the items into random rooms
     */
    public void randomSpawnsWeapons()
    {
        for(Room room: rooms)
        {
            //create a random integer that is used to choose an weapon
            int i = random.nextInt(weapons.size());
            //guard that makes sure that every item only got placed once
            while(roomsWithItem.containsKey(weapons.get(i)))
            {
                i = random.nextInt(weapons.size()); // create a new random to choose a weapon
            }
            //place the weapons in the room
            room.setItem(weapons.get(i).name, weapons.get(i));
            //keep track which weapon is placed in which room
            roomsWithItem.put(weapons.get(i), room);
            //ArrayList with only the weapons that where placed in a room
            placedWeapons.add(weapons.get(i));
        }
    }

    /**
     * Putting the secret items into random rooms
     */
    public void randomSpawnsSecretItems()
    {
        for(Room room: rooms)
        {
            if(roomsWithSecretItem.size() == secretItems.size())
            {
                //decline the method
            }
            else{
                //create a random integer that is used to choose an secret item
                int i = random.nextInt(secretItems.size());
                //guard that makes sure that every item only got placed once
                while(roomsWithSecretItem.containsKey(secretItems.get(i)))
                {
                    i = random.nextInt(secretItems.size()); // create a new random to choose a secret item
                }   
                //place the secret item in the room
                room.setItem(secretItems.get(i).name, secretItems.get(i));
                //keep track which secret item is placed in which room
                roomsWithSecretItem.put(secretItems.get(i), room);
            }
        }
    }

    /**
     * Choosing a random murder weapon
     */
    public void randomMurder()
    {
        //set a random weapon as murder weapon
        int i = random.nextInt(rooms.size());
        //murderWeapon is a String that gets a String from the method getRoomItem. 
        //getRoomItem got called from the Room that was returned by the HashMap using the weapon that is placed in the room as the key
        
        murderWeapon = placedWeapons.get(i).name;
        //murderWeapon = item.getItem(i);
        //murderWeapon = roomsWithItem.get(placedWeapons.get(i)).getRoomItem();
        //set a random room as crime room
        Collections.shuffle(rooms);
        i = random.nextInt(rooms.size());
        crimeRoom = rooms.get(i);
        randomSpawnsSecretItems();
    }

    /**
     * Checks if the player has any secret items in his inventory
     */
    public void carrySecretItems()
    {
      //  boolean hiddenRooms = false;
        if(inventoryHasItem("map"))
        {
            //wird mehrfach durchgefuehrt, begrenzen, wenn einmal ausgefuehrt dann nicht mehr true!
            createHiddenRooms();
            //hiddenRooms = true;
        }
        if(inventoryHasItem("key") && inventoryHasItem("map") && !first)
        {
            first = true;
            System.out.println("Look, there is a treasure box in a hidden room, maybe the key fits in there?");
        }
        if(inventoryHasItem("treasurechest") && !inventoryHasItem("key"))
        {
            System.out.println("You have found a mysterious treasure chest...\nIt is locked, find something to open it!");
        }
        if(inventoryHasItem("treasurechest") && inventoryHasItem("key"))
        {
            System.out.println("You have a key and a mysterious treasure chest in your inventory...\nMaybe the key fits in the lock?");
            System.out.println("Trying to open the treasure chest...");
            System.out.println("You found a note! It is now avaible in your inventory.");
            inventory.setInventory(note.name, note);
        }
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.\nGood bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println(guide.giveAnswer());

        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
        /*System.out.println();
        System.out.println("Welcome to Detective Ivanna Clue: The mystical Murder!");
        System.out.println("Detective Ivanna Clue: The mystical Murder is a new, incredibly fun to play detective game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        System.out.println();*/
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        int i = 0;
        CommandWord commandWord = command.getCommandWord();

        switch (commandWord)
        {
        
           case UNKNOWN:
                    System.out.println();
                    System.out.println("I don't know what you mean...");
                    System.out.println();
                    break;
           case HELP:
                    System.out.println();    
                    printHelp();
                    System.out.println();
                    break;
           case GO:
                    System.out.println();
                    System.out.println();
                    recentCommands.put("step"+n, currentRoom);
                    n++;
                    goRoom(command);
                    System.out.println();
                    break;
           case QUIT:
                     System.out.println();
                     wantToQuit = quit(command);
                     break;
           case LOOK:
                     System.out.println();
                     System.out.println(currentRoom.getLongDescription() + "\nItem/s in this room:" + currentRoom.getRoomItem() + "\n");
                     System.out.println("People in this room:" + currentRoom.getNPCString());
                     break;
           case TAKE:
                     System.out.println();
                     pickUpItem(command);
                     System.out.println();
                     break;
           case DROP:
                     System.out.println();
                     dropItem(command);
                     System.out.println();
                     break;
           case INVENTORY:
                     System.out.println();
                     System.out.println(inventory.getInventoryString() +"\n");
                     System.out.println();
                     break;         
           case ASK:
                     System.out.println();
                     askNPC(command);
                     System.out.println();
                     break;
           case BACK:
                     i = n-1;
                     System.out.println();
                     if(i<0){
                         System.out.println("There are no more steps to undo");
                         System.out.println();
                     }
                     else{
                         currentRoom = recentCommands.get("step"+i);
                         System.out.println(currentRoom.getLongDescription());
                         n--;
                     }
                     System.out.println();
                     break;          
           
        }
	return wantToQuit;
	}

	/**
	* Print out some help information.
	* Here we print some stupid, cryptic message and a list of the 
	* command words.
	*/
	private void printHelp() 
	{
		System.out.println("You are lost. You are alone. You wander");
		System.out.println("around at the crime scene.");
		System.out.println("Your command words are:");
		parser.showCommands();
	}
	
	/** 
	* Try to go to one direction. If there is an exit, enter
	* the new room, otherwise print an error message.
	*/
	private void goRoom(Command command) 
	{
		if(!command.hasSecondWord()) {
		    // if there is no second word, we don't know where to go...
		    System.out.println("Go where?");
		    return;
		}
	
		String direction = command.getSecondWord();
	
		// Try to leave current room
		Room nextRoom = currentRoom.getExit(direction);
	
		if (nextRoom == null) {
		    System.out.println("There is no door!");
		}
		else {
		    currentRoom = nextRoom;
		    System.out.println(currentRoom.getLongDescription());
		}
	}
	
	/**
	* Try to ask a NPC. If the NPC the player wants to ask is in this room, 
	* giving the player answers, otherwise print out an error message.
	*/
	private void askNPC(Command command)
	{
		if (!command.hasSecondWord())
		{
		    System.out.println("Ask who?");
		    return;
		}
	
		String name = command.getSecondWord().toLowerCase();
	
		NPC n = currentRoom.getNPC(name);
	
		// if the user is in the same Room as the asked NPC
		if (n == null)
		{
		    System.out.println(name.substring(0,1).toUpperCase() + name.substring(1) + " is not in this room.");
		}
		/*else if (n == guide)
		{
		System.out.println();
		System.out.println(n.giveAnswerGuide());
		System.out.println();
		}*/
		else {
		    System.out.println(n.giveAnswer());
		}
	
	}
	
	/**
	* Try to pick up an item.
	* If there is an item, pick it up, else print out an error message.
	*/
	private void pickUpItem(Command command)
	{
		if (!command.hasSecondWord())
		{
		    // if there is no second word, we don't know what to pick up
		    System.out.println("Pick up what?");
		    return;
		}
		
		String item = command.getSecondWord().toLowerCase();
		
		//Try to pick up the item
		Item i = currentRoom.getItem(item);
		if (i == null)
		{
		    System.out.println("There is no item in this room!");
		
		}
		else 
		{
		    inventory.setInventory(i.name, i);
		    if (inventory.pickable){
		        currentRoom.removeItem(item);
		        System.out.println("You have picked up:\n" + i.description
		            + ".\nIt is now available in your inventory.");
		    }
		}
		carrySecretItems();
	}
	
	/**
	* Trying to drop an item. If the player has the item he wants to drop, drop it, otherwise print out an error message.
	*/
	private void dropItem(Command command)
	{
		if (!command.hasSecondWord())
		{
		    System.out.println("Drop what?");
		    return;
		}
		
		String item = command.getSecondWord().toLowerCase();
		
		if (item.contains("all")){
		    Set<String> keys = inventory.inventory.keySet();
		    for (String i : keys){
		        currentRoom.setItem(i, inventory.getItem(i));
		    }
		    inventory.clearAll();
		    System.out.println("You have dropped everything.");
		}
		else {
		    Item i = inventory.getItem(item);
		    if (i == null)
		    {
		        System.out.println("You can't drop something you don't have.");
		    }
		    else 
		    {
		        inventory.removeItem(item);
		        currentRoom.setItem(i.name , i);
		        System.out.println("You have dropped: " + i.name
		            + "\nIt is now placed in the current room.");
		    }
		}
	}
	
	/**
	* Checks if the player dropped the murder weapon or the secret note in the main lobby. If he does, he won, if not, he didn't.
	*/
	public boolean compareItems()
	{
		if(mainLobby.getRoomItem().contains(murderWeapon) | mainLobby.getItem("note") != null)
		{
		    System.out.println("You found the murder weapon!\nCongratulations, you won the game!");
		    return true;
		}
		else
		{ 
		    System.out.println("This was not the murder weapon..."); 
		    return true;
		}
	}
	
	/** 
	* "Quit" was entered. Check the rest of the command to see
	* whether we really quit the game.
	* @return true, if this command quits the game, false otherwise.
	*/
	private boolean quit(Command command) 
	{
		if(command.hasSecondWord()) {
		    System.out.println("Quit what?");
		    System.out.println("Your possible commands are:");
		    parser.showCommands();
		    return false;
		}	
		else if(mainLobby.getRoomItem() != "")
		{
		    return compareItems();
		}
		else 
		{
		    return true;  // signal that we want to quit
		}
		
	}
}