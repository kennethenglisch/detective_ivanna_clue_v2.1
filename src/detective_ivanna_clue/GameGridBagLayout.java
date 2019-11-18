package detective_ivanna_clue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;

public class GameGridBagLayout {

//	private String str = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
	
	private JFrame frame;
	
	public PrintStream printStream;
	
	int buttonWidth = 90;
	int buttonHeight = 28;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameGridBagLayout window = new GameGridBagLayout();
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
	public GameGridBagLayout() {
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
		frame.setBounds(100, 100, 1080, 720);
		frame.setMinimumSize(new Dimension(800, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weighty = 0.5;
		gbc.weightx = 0.5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(15, 15, 15, 15);
		
		JPanel panel = new JPanel(new GridBagLayout());
		JPanel panel2 = new JPanel(new GridBagLayout());
		JPanel panel3 = new JPanel(new GridBagLayout());
		JPanel panel4 = new JPanel(new GridBagLayout());
		JPanel panelSouth = new JPanel(new BorderLayout());
		
		panel.setBackground(Color.BLACK);
		panel2.setBackground(Color.BLACK);
		panel3.setBackground(Color.BLACK);
		panel4.setBackground(Color.BLACK);
//		panelSouth.setBackground(Color.BLACK);
		
		// field to print out the console
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setMargin(gbc.insets);
//		textArea.setText(str);
		
		// add scroll bars to textArea
		JScrollPane scrollBar = new JScrollPane(textArea);
		scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		gbc.gridx = 0;
		gbc.gridy = 0;
			
		panel.add(scrollBar, gbc); 
		printStream = new PrintStream(new CustomOutputStream(textArea));
		
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.insets = new Insets(15, 15, 15, 15);
		gbc2.fill = GridBagConstraints.BOTH;
		
		JButton btnGoNorth = new JButton("Go North");
		
		btnGoNorth.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.GO, "north"));
			}
		});
		
		gbc2.gridx = 2;
		gbc2.gridy = 0;
		
		panel2.add(btnGoNorth, gbc2);

		JButton btnGoSouth = new JButton("Go South");
		
		btnGoSouth.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.GO, "south"));
			}
		});
		
		gbc2.gridx = 2;
		gbc2.gridy = 2;
		
		panel2.add(btnGoSouth, gbc2);

		JButton btnGoWest = new JButton("Go West");
		
		btnGoWest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.GO, "west"));
			}
		});
		
		gbc2.gridx = 1;
		gbc2.gridy = 1;
		
		panel2.add(btnGoWest, gbc2);
		
		JButton btnGoLeft = new JButton("Go Left");
		
		btnGoLeft.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.GO, "left"));
			}
		});
		
		gbc2.gridx = 0;
		gbc2.gridy = 1;
		
		panel2.add(btnGoLeft, gbc2);
		
		JButton btnGoEast = new JButton("Go East");
		
		btnGoEast.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.GO, "east"));
			}
		});
		
		gbc2.gridx = 3;
		gbc2.gridy = 1; 
		
		panel2.add(btnGoEast, gbc2);
		
		JButton btnGoRight = new JButton("Go Right");
		
		btnGoRight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.GO, "right"));
			}
		});
		
		gbc2.gridx = 4;
		gbc2.gridy = 1;
		
		panel2.add(btnGoRight, gbc2);
		
		JButton btnBack = new JButton("Back");
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.BACK, ""));
			}
		});
		
		gbc2.gridx = 2;
		gbc2.gridy = 1;
		
		panel2.add(btnBack, gbc2);
		
		JButton btnAsk = new JButton("Ask");
		
		btnAsk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.ASK, "guide"));
			}
		});
		
		gbc2.gridx = 0;
		gbc2.gridy = 1;
		
		panel3.add(btnAsk, gbc2);
		
		JButton btnLook = new JButton("Look");
		
		btnLook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.LOOK, ""));
			}
		});
		
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		
		panel3.add(btnLook, gbc2);
		
		int p4_width = panel4.getWidth();
		int p4_height = panel4.getHeight();
		
		int heightTwoThird = p4_height / 3 * 2;
		
		JButton btnInventory = new JButton("Inventory");
		
		btnInventory.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.INVENTORY, ""));
			}
		});

		gbc2.insets = new Insets(0, 15, 10, 15);
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		
		panel4.add(btnInventory, gbc2);
		
		JButton btnTake = new JButton("Take");
		
		btnTake.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.TAKE, ""));
			}
		});


		gbc2.gridx = 0;
		gbc2.gridy = 1;
		
		panel4.add(btnTake, gbc2);
		
		JButton btnDrop = new JButton("Drop");
		
		btnDrop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.DROP, ""));
			}
		});
		
		gbc2.gridx = 0;
		gbc2.gridy = 2;
		
		panel4.add(btnDrop, gbc2);
		
		JButton btnDropAll = new JButton("Drop all");
		btnDropAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				processCommand(new Command(CommandWord.DROP, "all"));
			}
		});
		
		gbc2.gridx = 0;
		gbc2.gridy = 3;

		
		panel4.add(btnDropAll, gbc2);

		panelSouth.add(panel3, BorderLayout.WEST);
		panelSouth.add(panel2, BorderLayout.CENTER);
		panelSouth.add(panel4, BorderLayout.EAST);
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
	}

	// the Room the player is currently inside
    public Room currentRoom;
    // the inventory of the player
    private Inventory inventory;

    private boolean first = false;
    private Random random;
    // ArrayList keeping track of all items
//    private ArrayList<Item> items;
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
    public void createGame() 
    {
//    	parser = new Parser();
        inventory = new Inventory();
        weapons = new ArrayList<>();
//        items = new ArrayList<>();
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
//        kitchen.setNPC(blue.name, blue);
//        hall.setNPC(guide.name, guide);
//        mainLobby.setNPC(green.name, green);
        
        kitchen.setAnyNPC(blue);
        hall.setAnyNPC(guide);
        mainLobby.setAnyNPC(green);
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
        return inventory.getAnyInventoryString().contains(item) ? true : false;
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
//            room.setItem(weapons.get(i).name, weapons.get(i));
            room.setAnyWeapon(weapons.get(i));
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
//                room.setItem(secretItems.get(i).name, secretItems.get(i));
                room.setAnyWeapon(secretItems.get(i));
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
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        printStream.println(guide.giveAnswer());

//        printStream.println();
        printStream.println("\n" + currentRoom.getLongDescription());
//        printStream.println();
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
           case GO:
                    printStream.println();
                    recentCommands.put("step"+n, currentRoom);
                    n++;
                    goRoom(command);
                    break;
           case LOOK:
                     printStream.println();
                     printStream.println(currentRoom.getLongDescription() + "\n\nItem/s in this room:" + currentRoom.getAnyRoomItem() + "\n");
                     printStream.println("People in this room:" + currentRoom.getAnyNPCString());
                     break;
           case TAKE:
                     printStream.println();
                     pickUpItem(command);
                     break;
           case DROP:
                     printStream.println();
                     dropItem(command);
                     break;
           case INVENTORY:
                     printStream.println();
                     printStream.println(inventory.getAnyInventoryString() +"\n");
                     break;         
           case ASK:
                     printStream.println();
                     askNPC(command);
                     break;
           case BACK:
                     i = n-1;
                     printStream.println();
                     if(i<0){
                         printStream.println("There are no more steps to undo");
                     }
                     else{
                         currentRoom = recentCommands.get("step"+i);
                         printStream.println(currentRoom.getLongDescription());
                         n--;
                     }
                     break;          
           
        }
	}
	
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
	
		if (currentRoom.isEmptyNPC())
			printStream.println("There is no one in this room except you!");
		else {
			NPC n = currentRoom.getAnyNPC();
		    printStream.println(n.giveAnswer());
		}
	
	}
	
	/**
	* Try to pick up an item.
	* If there is an item, pick it up, else print out an error message.
	*/
	private void pickUpItem(Command command)
	{
		if (currentRoom.isEmptyItems())
		{
		    printStream.println("There is no item in this room!");
		
		}
		else 
		{
			Item i = currentRoom.getAnyWeapon();
		    inventory.setAnyInventory(i);
		    if (inventory.pickable){
//		        currentRoom.removeAnyItem(i);
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
//		if (!command.hasSecondWord())
//		{
//		    printStream.println("Drop what?");
//		    return;
//		}
		if(inventory.isEmpty())
		{
			printStream.println("You can't drop something you don't have.");
		}
		else
		{
			String item = command.getSecondWord().toLowerCase();
			
			if (item.contains("all"))
			{
					while(!inventory.isEmpty()) 
					{
						currentRoom.setAnyWeapon(inventory.getAnyItem());
					}
					printStream.println("You have dropped everything.");
			}
		    else 
		    {
		    	Item i = inventory.getAnyItem();
		        currentRoom.setAnyWeapon(i);
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
//	private boolean quit(Command command) 
//	{
////		if(command.hasSecondWord()) {
////		    printStream.println("Quit what?");
////		    printStream.println("Your possible commands are:");
////		    parser.showCommands();
////		    return false;
////		}	
//		if(mainLobby.getRoomItem() != "")
//		{
//		    return compareItems();
//		}
//		else 
//		{
//			printStream.println("\nThanks for playing. Good Bye");
//		    return true;  // signal that we want to quit
//		}
//		
//	}
	
}
