import java.util.ArrayList;

/**
 * NFA.java
 * 
 * Class to create and defines a NFA graph
 * 
 * Authors: Dylan Pascua and Paul Patrick Bellosillo Date: December 2, 2019
 * 
 */

public class NFA {

    // Instance variables for NFA
    private ArrayList<Node> nodes;
    private ArrayList<Node> acceptingStates;
    private Node start;
    private char[] alphabet;
    private final char EMPTY = '~';

    /**
     * NFA
     * 
     * public constructor for making a new NFA
     * 
     * @param inputNodes      - new list of all nodes residing in NFA
     * @param acceptingStates - list of all accepting nodes in NFA
     * @param start           - new starting node
     * @param alphabet        - valid set of chars to be used for transitions
     */
    public NFA(ArrayList<Node> inputNodes, ArrayList<Node> acceptingStates, Node start, String alphabet) {
        nodes = new ArrayList<Node>();
        this.acceptingStates = acceptingStates;
        this.start = start;
        System.out.println("Alphabet: " + alphabet);
        this.alphabet = alphabet.toCharArray();
    }

    /**
     * addNode
     * 
     * Adds new node to NFA
     * 
     * @param newNode - node to be added
     */
    public void addNode(Node newNode) {
        nodes.add(newNode);
    }

    /**
     * inAlphabet
     * 
     * Checks if given input is a valid char to be used in NFA
     * 
     * @param inChar - input char to be checked
     * @return true or false if input is a valid char for NFA
     */
    public boolean inAlphabet(char inChar) {
        for (int index = 0; index < alphabet.length; index++) {
            if (alphabet[index] == inChar) {
                return true;
            }
        }
        return false;
    }

    /**
     * checkAccepts
     * 
     * Checks if the given input is accepted by the NFA
     * 
     * @param input - String to run through and check if NFA accepts it
     * @return true or false depending on whether the NFA accepts the given String
     */
    public boolean checkAccepts(String input) {
        char[] inputStringArray = input.toCharArray();
        String alphabetString = new String(alphabet);

        // stack variable keeps track
        ArrayList<Node> stack = new ArrayList<Node>();
        stack.add(start);

        // Runs through input to check if all characters are valid for this NFA
        boolean allInAlphabet = true;
        for (int index = 0; index < inputStringArray.length; index++) {
            if (!(alphabetString.contains("" + inputStringArray[index]))) {
                allInAlphabet = false;
                break;
            }
        }

        // Returns false if at least one character is not in the alphabet
        if (!allInAlphabet) {
            return false;
        }

        // Check case when input String is just an empty String
        if (inputStringArray.length == 0) {
            ArrayList<Node> stackCopy = new ArrayList<Node>(stack);
            for (Node node : stackCopy) {
                ArrayList<Transition> toAdd = node.getNextNodes(EMPTY);
                for (Transition transition : toAdd) {
                    System.out.println("Adding Node: " + transition.getDestination().getId());
                    stack.add(transition.getDestination());
                }
            }
        }

        // Runs through input and adds node to stack until the end of the String
        for (int index = 0; index < inputStringArray.length; index++) {
            ArrayList<Node> stackCopy = new ArrayList<Node>(stack);
            for (Node node : stackCopy) {
                System.out.println(node.getId() + ", ");
            }

            // Checks and looks through the stack and retrieves all nodes in which
            // transition to
            // next node is triggered by current char
            stack.clear();
            for (Node node : stackCopy) {
                System.out.println("Current : " + inputStringArray[index]);
                ArrayList<Transition> toAdd = node.getNextNodes(inputStringArray[index]);
                for (Transition transition : toAdd) {
                    System.out.println("Adding Node: " + transition.getDestination().getId());
                    stack.add(transition.getDestination());
                }
            }

            // Adds nodes for when next transition is triggered by epsilon (a.k.a: e)
            stackCopy.clear();
            stackCopy = new ArrayList<Node>(stack);
            for (Node node : stackCopy) {
                ArrayList<Transition> toAdd = node.getNextNodes(EMPTY);
                for (Transition transition : toAdd) {
                    System.out.println("Adding Node: " + transition.getDestination().getId());
                    stack.add(transition.getDestination());
                }
            }
        }

        // Runs through the stack and check if at least one accepting Node is still in
        // stack
        for (Node node : stack) {
            for (Node nodeAccept : acceptingStates) {
                if (nodeAccept.getId() == node.getId()) {
                    return true;
                }
            }
        }
        return false;
    }
}