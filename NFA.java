import java.util.ArrayList;

public class NFA{ 
    private ArrayList<Node> nodes;
    private ArrayList<Node> acceptingStates;
    private Node start;
    private char[] alphabet;

    public NFA(ArrayList<Node> inputNodes, ArrayList<Node> acceptingStates, Node start, String alphabet){
        nodes = new ArrayList<Node>();
        this.acceptingStates = acceptingStates;
        this.start = start;
        this.alphabet = alphabet.toCharArray();
    }

    public void addNode(Node newNode){
        nodes.add(newNode);
    }

    public boolean inAlphabet(char inChar){
        for (int index = 0; index < alphabet.length; index++){
            if (alphabet[index] == inChar){return true;}
        }
        return false;
    }

    /** canContinue
     * 
     * Checks if the 
     * 
     * @return boolean if NFA accepts String or not
     */
    public boolean checkAccepts(String input){
        char[] inputStringArray = input.toCharArray();

        // stack variable keeps track 
        ArrayList<Node> stack = new ArrayList<Node>();
        stack.add(start);

        for(int index = 0; index < inputStringArray.length; index++){
            if (stack.size() < 1){
                return false;
            }
        }

        for (Node node : stack) {
            for (Node nodeAccept : acceptingStates) {
                if (nodeAccept.getId() == node.getId()){
                    return true;
                }
            }
        }

        return false;
    }
}