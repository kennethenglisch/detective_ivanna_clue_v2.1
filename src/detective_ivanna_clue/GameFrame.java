package detective_ivanna_clue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

// import org.graalvm.compiler.nodes.calc.RightShiftNode;

import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollBar;

public class GameFrame {
	
	private final int FRAME_HEIGHT = 720;
	private final int FRAME_WIDTH = 1080;
	
//	public CustomOutputStream customOutputStream;
	public PrintStream printStream;
	private PrintStream standardOut = System.out;
	private PrintStream standardErr = System.err;

	private JFrame frame;
//	private Game game;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFrame window = new GameFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameFrame() {
		initialize();
		frame.setTitle("Detective Ivanna Clue");
		createGame();
		play();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		int heightTwoThird = FRAME_HEIGHT / 3 * 2; 
		int widthMid = FRAME_WIDTH / 2;
		int buttonWidth = 90;
		int buttonHeight = 28;
		int xOfMiddleButtons = widthMid - (buttonWidth / 2);
		int yOfMiddleButtons = heightTwoThird + buttonHeight + 20;

		
		// field to print out the console
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		// adding scroll bar to it
		JScrollPane scrollBar = new JScrollPane(textArea);
		scrollBar.setBounds(45, 30, 974, 422);
		frame.getContentPane().add(scrollBar);

		
//		customOutputStream = new CustomOutputStream(textArea);
		
		printStream = new PrintStream(new CustomOutputStream(textArea));
		
		// label above text
		JLabel lblConsole = new JLabel("Console:");
		lblConsole.setForeground(Color.BLACK);
		lblConsole.setBounds(45, 11, 71, 14);
		frame.getContentPane().add(lblConsole);

		// highest button
		JButton btnGoNorth = new JButton("Go North");
		btnGoNorth.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.GO, "north"));
			}
		});
		btnGoNorth.setBounds(xOfMiddleButtons, heightTwoThird, buttonWidth, buttonHeight);
		frame.getContentPane().add(btnGoNorth);
		
		// Middle Button
		JButton btnGoBack = new JButton("Back");
		btnGoBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.BACK, ""));
			}
		});
		
		btnGoBack.setBounds(xOfMiddleButtons, yOfMiddleButtons, buttonWidth, buttonHeight);
		frame.getContentPane().add(btnGoBack);
		
		// lowest button
		JButton btnGoSouth = new JButton("Go South");
		btnGoSouth.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.GO, "south"));
			}
		});
		btnGoSouth.setBounds(xOfMiddleButtons, heightTwoThird + 2 * buttonHeight + 40, buttonWidth, buttonHeight);
		frame.getContentPane().add(btnGoSouth);
		
		
		
		
		// inner right button
		JButton btnGoRight = new JButton("Go Right");
		btnGoRight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.GO, "right"));
			}
		});
		btnGoRight.setBounds(xOfMiddleButtons + buttonWidth + 20, yOfMiddleButtons, buttonWidth, buttonHeight);
		frame.getContentPane().add(btnGoRight);
		
		// outer right button
		JButton btnGoEast = new JButton("Go East");
		btnGoEast.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.GO, "east"));
			}
		});
		btnGoEast.setBounds(xOfMiddleButtons + 2 * buttonWidth + 40, yOfMiddleButtons, buttonWidth, buttonHeight);
		frame.getContentPane().add(btnGoEast);
		
		// inner left button
		JButton btnGoLeft = new JButton("Go Left");
		btnGoLeft.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.GO, "left"));
			}
		});
		btnGoLeft.setBounds(xOfMiddleButtons - buttonWidth - 20, yOfMiddleButtons, buttonWidth, buttonHeight);
		frame.getContentPane().add(btnGoLeft);
		
		
		// outer left button
		JButton btnGoWest = new JButton("Go West");
		btnGoWest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.GO, "west"));
			}
		});
		btnGoWest.setBounds(xOfMiddleButtons - 2 * buttonWidth - 40, yOfMiddleButtons, buttonWidth, buttonHeight);
		frame.getContentPane().add(btnGoWest);
		
		// look button
		JButton btnLook = new JButton("Look");
		btnLook.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
			
			processCommand(new Command(CommandWord.LOOK, ""));
			}
		});
		btnLook.setBounds(55, heightTwoThird, buttonWidth, buttonHeight);
		frame.getContentPane().add(btnLook);
		
		// ask button
		JButton btnAsk = new JButton("Ask");
		btnAsk.addActionListener(new ActionListener() {
						
			@Override
			public void actionPerformed(ActionEvent e) {
			
			processCommand(new Command(CommandWord.ASK, "guide"));
			}
		});
		btnAsk.setBounds(55, yOfMiddleButtons, buttonWidth, buttonHeight);
		frame.getContentPane().add(btnAsk);
		
		// inventory button
		JButton btnInventory = new JButton("Inventory");
		btnInventory.addActionListener(new ActionListener() {
							
			@Override
			public void actionPerformed(ActionEvent e) {
			
			processCommand(new Command(CommandWord.INVENTORY, ""));
			}
		});
		btnInventory.setBounds(924, heightTwoThird, buttonWidth, buttonHeight);
		frame.getContentPane().add(btnInventory);

		// take button
		JButton btnTake = new JButton("Take");
		btnTake.addActionListener(new ActionListener() {
						
			@Override
			public void actionPerformed(ActionEvent e) {
			
			processCommand(new Command(CommandWord.TAKE, ""));
			}
		});
		btnTake.setBounds(924, yOfMiddleButtons, buttonWidth, buttonHeight);
		frame.getContentPane().add(btnTake);
		
		// drop button
		JButton btnDrop = new JButton("Drop");
		btnDrop.addActionListener(new ActionListener() {
							
			@Override
			public void actionPerformed(ActionEvent e) {
			
			processCommand(new Command(CommandWord.DROP, ""));
			}
		});
		btnDrop.setBounds(924, heightTwoThird + 2 * buttonHeight + 40, buttonWidth, buttonHeight);
		frame.getContentPane().add(btnDrop);
		
		// clear button
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
									
			@Override
			public void actionPerformed(ActionEvent e) {
			
				textArea.setText("");
			}
		});
		btnClear.setBounds(924, 5, buttonWidth, 20);
		frame.getContentPane().add(btnClear);

		
	
		
		
		
		
	}
	
	 // reading the players input
//    private Parser parser;
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

    
    private CommandWords commandWords;
    
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
    public void createGame() 
    {
//    	parser = new Parser();
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
            printStream.println("Look, there is a treasure box in a hidden room, maybe the key fits in there?");
        }
        if(inventoryHasItem("treasurechest") && !inventoryHasItem("key"))
        {
            printStream.println("You have found a mysterious treasure chest...\nIt is locked, find something to open it!");
        }
        if(inventoryHasItem("treasurechest") && inventoryHasItem("key"))
        {
            printStream.println("You have a key and a mysterious treasure chest in your inventory...\nMaybe the key fits in the lock?");
            printStream.println("Trying to open the treasure chest...");
            printStream.println("You found a note! It is now avaible in your inventory.");
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

//        boolean finished = false;
//        while (! command) {
////            Command command = parser.getCommand();
////            finished = processCommand(command);
//        }
//        printStream.println("Thank you for playing.\nGood bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        printStream.println(guide.giveAnswer());

        printStream.println();
        printStream.println(currentRoom.getLongDescription());
        printStream.println();
        /*printStream.println();
        printStream.println("Welcome to Detective Ivanna Clue: The mystical Murder!");
        printStream.println("Detective Ivanna Clue: The mystical Murder is a new, incredibly fun to play detective game.");
        printStream.println("Type '" + CommandWord.HELP + "' if you need help.");
        printStream.println();
        printStream.println(currentRoom.getLongDescription());
        printStream.println();*/
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private void processCommand(Command command) 
    {
        boolean wantToQuit = false;
        int i = 0;
        CommandWord commandWord = command.getCommandWord();

        switch (commandWord)
        {
        
           case UNKNOWN:
                    printStream.println();
                    printStream.println("I don't know what you mean...");
                    printStream.println();
                    break;
           case HELP:
                    printStream.println();    
//                    printHelp();
                    printStream.println();
                    break;
           case GO:
                    printStream.println();
                    printStream.println();
                    recentCommands.put("step"+n, currentRoom);
                    n++;
                    goRoom(command);
                    printStream.println();
                    break;
           case QUIT:
                     printStream.println();
//                     wantToQuit = quit(command);
                     break;
           case LOOK:
                     printStream.println();
                     printStream.println(currentRoom.getLongDescription() + "\nItem/s in this room:" + currentRoom.getRoomItem() + "\n");
                     printStream.println("People in this room:" + currentRoom.getNPCString());
                     break;
           case TAKE:
                     printStream.println();
                     pickUpItem(command);
                     printStream.println();
                     break;
           case DROP:
                     printStream.println();
                     dropItem(command);
                     printStream.println();
                     break;
           case INVENTORY:
                     printStream.println();
                     printStream.println(inventory.getInventoryString() +"\n");
                     printStream.println();
                     break;         
           case ASK:
                     printStream.println();
                     askNPC(command);
                     printStream.println();
                     break;
           case BACK:
                     i = n-1;
                     printStream.println();
                     if(i<0){
                         printStream.println("There are no more steps to undo");
                         printStream.println();
                     }
                     else{
                         currentRoom = recentCommands.get("step"+i);
                         printStream.println(currentRoom.getLongDescription());
                         n--;
                     }
                     printStream.println();
                     break;          
           
        }
//	return wantToQuit;
	}

	/**
	* Print out some help information.
	* Here we print some stupid, cryptic message and a list of the 
	* command words.
	*/
//	private void printHelp() 
//	{
//		printStream.println("You are lost. You are alone. You wander");
//		printStream.println("around at the crime scene.");
//		printStream.println("Your command words are:");
//		parser.showCommands();
//	}
	
	/** 
	* Try to go to one direction. If there is an exit, enter
	* the new room, otherwise print an error message.
	*/
	private void goRoom(Command command) 
	{
		if(!command.hasSecondWord()) {
		    // if there is no second word, we don't know where to go...
		    printStream.println("Go where?");
		    return;
		}
	
		String direction = command.getSecondWord();
	
		// Try to leave current room
		Room nextRoom = currentRoom.getExit(direction);
	
		if (nextRoom == null) {
		    printStream.println("There is no door!");
		}
		else {
		    currentRoom = nextRoom;
		    printStream.println(currentRoom.getLongDescription());
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
		    printStream.println("Ask who?");
		    return;
		}
	
		String name = command.getSecondWord().toLowerCase();
	
		NPC n = currentRoom.getNPC(name);
	
		// if the user is in the same Room as the asked NPC
		if (n == null)
		{
		    printStream.println(name.substring(0,1).toUpperCase() + name.substring(1) + " is not in this room.");
		}
		/*else if (n == guide)
		{
		printStream.println();
		printStream.println(n.giveAnswerGuide());
		printStream.println();
		}*/
		else {
		    printStream.println(n.giveAnswer());
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
		    printStream.println("Pick up what?");
		    return;
		}
		
		String item = command.getSecondWord().toLowerCase();
		
		//Try to pick up the item
		Item i = currentRoom.getItem(item);
		if (i == null)
		{
		    printStream.println("There is no item in this room!");
		
		}
		else 
		{
		    inventory.setInventory(i.name, i);
		    if (inventory.pickable){
		        currentRoom.removeItem(item);
		        printStream.println("You have picked up:\n" + i.description
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
		    printStream.println("Drop what?");
		    return;
		}
		
		String item = command.getSecondWord().toLowerCase();
		
		if (item.contains("all")){
		    Set<String> keys = inventory.inventory.keySet();
		    for (String i : keys){
		        currentRoom.setItem(i, inventory.getItem(i));
		    }
		    inventory.clearAll();
		    printStream.println("You have dropped everything.");
		}
		else {
		    Item i = inventory.getItem(item);
		    if (i == null)
		    {
		        printStream.println("You can't drop something you don't have.");
		    }
		    else 
		    {
		        inventory.removeItem(item);
		        currentRoom.setItem(i.name , i);
		        printStream.println("You have dropped: " + i.name
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
		    printStream.println("You found the murder weapon!\nCongratulations, you won the game!");
		    return true;
		}
		else
		{ 
		    printStream.println("This was not the murder weapon..."); 
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
//		if(command.hasSecondWord()) {
//		    printStream.println("Quit what?");
//		    printStream.println("Your possible commands are:");
//		    parser.showCommands();
//		    return false;
//		}	
		if(mainLobby.getRoomItem() != "")
		{
		    return compareItems();
		}
		else 
		{
			printStream.println("\nThanks for playing. Good Bye");
		    return true;  // signal that we want to quit
		}
		
	}
}
