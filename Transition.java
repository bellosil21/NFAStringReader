public class Transition{
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