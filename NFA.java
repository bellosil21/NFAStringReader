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
        System.out.println("Alphabet: " + alphabet);
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
        String alphabetString = new String(alphabet);

        // stack variable keeps track 
        ArrayList<Node> stack = new ArrayList<Node>();
        stack.add(start);

        boolean allInAlphabet = true;
        // System.out.println(alphabet.toString());
        for(int index = 0; index < inputStringArray.length; index++){
            if(!(alphabetString.contains("" + inputStringArray[index]))){
                allInAlphabet = false;
                break;
            }
        }

        if (!allInAlphabet){return false;}

        for (int index = 0; index < inputStringArray.length; index++){
            ArrayList<Node> stackCopy = new ArrayList<Node>(stack);
            System.out.println("=========================== \n Stack: ");
            for (Node node : stackCopy) {
                System.out.println(node.getId() + ", ");
            }
            
            stack.clear();
            for (Node node : stackCopy) {
                System.out.println("Current : " + inputStringArray[index]);
                ArrayList<Transition> toAdd = node.getNextNodes(inputStringArray[index]);
                for (Transition transition : toAdd) {
                    System.out.println("Adding Node: " + transition.getDestination().getId());
                    stack.add(transition.getDestination());
                }
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