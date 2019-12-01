/** Transistion.java
 * 
 * Class that defines a Transition from a node to the next
 * 
 * Authors: Dylan Pascua and Paul Patrick Bellosillo
 * Date: December 2, 2019
 * 
 */
public class Transition{
    // Instance variables for 
    private Node destination;
    private char input;

    public Transition(Node dest, char in){
        destination = dest;
        input = in;
    }

    public boolean checkTransition(char in){
        if (in == input){
            return true;
        }
        return false;
    }

    public Node getDestination(){return destination;}
}