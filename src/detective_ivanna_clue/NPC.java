package detective_ivanna_clue;

import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class NPC here.
 *
 * @author  Tony Schultze, Kenneth Englisch
 * @version 2019-07-03
 */
public class NPC
{
    public String name;
    public ArrayList<String> answers; 
    private Random random;
    private int lastAnswer = 1;

    /**
     * Constructor for objects of class NPC
     * @param name A string that is representing the name of the NPC.
     */
    public NPC(String name)
    {
        this.name = name;
        answers = new ArrayList<>();
        random = new Random();
    }
    
    /**
     * Filling the ArrayList of the NPC with possible answers.
     */
    public void fillAnswers(String answer)
    {
        answers.add(answer);
    }
    
    /**
     * Giving out ransdom answers out of the ArrayList.
     * @return A string representing one of the answers.
     */
    public String giveAnswer()
    {
        // the String that is given later
        String answerString = name.substring(0,1).toUpperCase() + name.substring(1) + ": " ;
        // a random chosen int
        int i = random.nextInt(answers.size());
        // getting the value at i in the list and adding it to our answerString
        // if the list contains only one item, we are skipping this part
        while (i == lastAnswer & answers.size() > 1)
        { 
            i = random.nextInt(answers.size());
        }
        answerString += answers.get(i);
        // remembering the lastanswer
        lastAnswer = i;
        // returning the String
        return answerString;
    }
    
    
}