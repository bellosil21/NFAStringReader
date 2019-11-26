import java.util.ArrayList;

public class Node { 
    private String id;
    private ArrayList<Transition> transitions;

    public Node(String id, Transition[] transArray){
        this.id = id;
        transitions = new ArrayList<Transition>();
        for (int index = 0; index < transArray.length; index++){
            transitions.add(transArray[index]);
        }
    }

    public ArrayList<Transition> checkNodes(String input){
        ArrayList<Transition> toReturn = new ArrayList<Transition>();
        Transition temp = null;

        for (int index = 0; index < transitions.size(); index++){
            temp = transitions.get(index);
            if (temp.check(input)){
                toReturn.add(temp);
            }
        }
        return toReturn;
    }

    public String getId(){return id;}
}