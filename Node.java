import java.util.ArrayList;

public class Node { 
    private char id;
    private ArrayList<Transition> transitions;

    public Node(char id){
        this.id = id;
        transitions = new ArrayList<Transition>();
    }

    public ArrayList<Transition> getNextNodes(char input){
        ArrayList<Transition> toReturn = new ArrayList<Transition>();
        Transition temp = null;

        for (int index = 0; index < transitions.size(); index++){
            temp = transitions.get(index);
            if (temp.checkTransition(input)){
                toReturn.add(temp);
            }
        }
        return toReturn;
    }

    public char getId(){return id;}

    public void addTransition(Transition newTransition){
        transitions.add(newTransition);
    }
}