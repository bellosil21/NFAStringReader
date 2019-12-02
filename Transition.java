/** Transistion.java
 * 
 * Class that defines a Transition from a node to the next
 * 
 * Authors: Dylan Pascua and Paul Patrick Bellosillo
 * Date: December 2, 2019
 * 
 */
public class Transition{
    // Instance variables for Transitions
    private Node destination;
    private char input;

    /** Transition
     * 
     * Constructor for creating a new Transition
     * 
     * @param dest - Node transition is pointing to
     * @param in - char trigger for going through transition
     */
    public Transition(Node dest, char in){
        destination = dest;
        input = in;
    }

    /** checkTransition
     * 
     * Checks if the Transition's trigger is the same as given input
     * 
     * @param in - char input to check
     * @return true if transition is triggered by given input. Else, False
     */
    public boolean checkTransition(char in){
        if (in == input){
            return true;
        }
        return false;
    }

    /** getDestination
     * 
     * Retrieves the destination for this Transition object
     * 
     * @return the destination for this Transition
     */
    public Node getDestination(){return destination;}
}