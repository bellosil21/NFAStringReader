import java.util.ArrayList;

/** Node.java
 * 
 * Class to create and define a node for a NFA graph
 * 
 * Authors: Dylan Pascua and Paul Patrick Bellosillo
 * Date: December 2, 2019
 * 
 */

public class Node { 
    // Instance variables for Node
    private char id;
    private ArrayList<Transition> transitions;

    /**
     * Node constructor for first creating the NFA
     * 
     * @param id - new identifier for NFA
     */
    public Node(char id){
        this.id = id;
        transitions = new ArrayList<Transition>();
    }

    /** getNextNodes
     * 
     * Looks through this nodes list of transitions and retrieves
     * a list of the transitions with the given input
     * 
     * @return ArrayList of transitions for given input 
     */
    public ArrayList<Transition> getNextNodes(char input){
        ArrayList<Transition> toReturn = new ArrayList<Transition>();
        Transition temp = null;

        // Runs through the transitions list and finds all transitions equal to the
        // given trigger
        for (int index = 0; index < transitions.size(); index++){
            temp = transitions.get(index);
            if (temp.checkTransition(input)){
                toReturn.add(temp);
            }
        }
        return toReturn;
    }

    /** getId
     * 
     * Returns the ID for the current Node
     * 
     * @return - this node's ID
     */
    public char getId(){return id;}

    /** addTransition
     * 
     * Adds a new Transition to the nodes list of Transitions
     * 
     * @param newTransition - new Transition to be added
     */
    public void addTransition(Transition newTransition){
        transitions.add(newTransition);
    }
}