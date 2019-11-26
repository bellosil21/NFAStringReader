import java.util.ArrayList;

public class Node { 
    private String id;
    private ArrayList<Transition> transitions;

    public Node(String id, Transition[] transArray){
        this.id = id;
        for (int index = 0; index < transArray.length; index++){
            transitions.add(transArray[index]);
        }
    }

    public ArrayList<Transition> checkNodes(String input){

    }
}