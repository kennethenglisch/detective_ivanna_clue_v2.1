package detective_ivanna_clue;

/**
 * Representing all valid command words for the game
 * along with a string in a particular language.
 *
 * @author  Tony Schultze, Kenneth Englisch
 * @version 2019-07-03
 */
public enum CommandWord
{
    // A value for each command word along with it's
    // corresponding user interface string.
    GO("go"), LOOK("look"), TAKE("take"), DROP("drop"), INVENTORY("inventory"), QUIT("quit"), HELP("help"),  UNKNOWN("?"), ASK("ask"), BACK("back");
    
    //The command string.
    private String commandString;
    
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    public String toString()
    {
        return commandString;
    }
}