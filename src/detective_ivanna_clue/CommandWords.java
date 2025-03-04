package detective_ivanna_clue;

import java.util.HashMap;
/**
 * This class is part of the "Detective Ivanna Clue: The mystical Murder" application. 
 * "Detective Ivanna Clue: The mystical Murder" is a text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Tony Schultze, Kenneth Englisch
 * @version 2019-06-24
 */

public class CommandWords
{
    // A mapping between a command word and the CommandWord associated with it.
    private HashMap<String, CommandWord> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
       validCommands = new HashMap<>();
       for (CommandWord command : CommandWord.values())
       {
           if (command != CommandWord.UNKNOWN)
           {
               validCommands.put(command.toString(), command);
           }
       }
    }

    /**
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord corresponding to commandWord, 
     *         or UNKNOWN if it is not a valid command word.
     */
    public CommandWord getCommandWord (String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if (command != null){
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    /**
     * Check wether a given String is a valid command word.
     * @return true if its is, false if it isn't
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }
    
    /**
     * Print all valid command to System.out.
     */
    public void showAll()
    {
        for (String command : validCommands.keySet())
        {
            System.out.print(command + " ");
        }
    }
}