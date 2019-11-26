public class Transition{
    private Node destination;
    private String input;

    public Transition(Node dest, String in){
        destination = dest;
        input = in;
    }

    public boolean check(String dest, String in){
        if (in.equals(input)){
            return true;
        }
        return false;
    }

    public Node getDestination(){return destination;}
}