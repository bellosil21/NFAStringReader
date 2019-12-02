import java.io.*;
import java.util.*;

/**
 * Main.java
 * 
 * Main Class for calling and creating a
 * 
 * Authors: Dylan Pascua and Paul Patrick Bellosillo 
 * Date: December 2, 2019
 * 
 */
public class Main {
    public static void main(String[] args) {
        String END = "end";

        // the parser portion that creates the NFA
        File file = new File(".\\Test Cases\\" + args[0]);

        // Initializes scanner and NFA objects
        Scanner scr = null;
        NFA nfa = null;

        // Attempts to open file selected by user
        try {
            scr = new Scanner(file);

        } catch (FileNotFoundException fnfe) {
            System.out.print("File is not found.");
            System.exit(0);
        } catch (Exception e) {
            System.out.print("Something wrong happened.");
            System.exit(0);
        }

        System.out.println("Creating NFA...");

        // the first line of the file should have all the names of the Nodes in the DFA
        String line1 = scr.nextLine();
        System.out.println("line1: " + line1);
        ArrayList<Node> inputNodes = new ArrayList<Node>();
        for (int i = 0; i < line1.length(); i++) {
            char c = line1.charAt(i);
            if (c != '{' && c != ',' && c != '}' && c != ' ') {
                // creates a new node based on the number
                Node node = new Node(c);
                System.out.println("Adding node with ID: " + c);
                inputNodes.add(node);
            } else if (c == '}') {
                // this is the end of the line
                break;
            }
        }

        System.out.println("=========================================");

        // the second line of the file should have the characters in the language
        String line2 = scr.nextLine();
        String alphabet = "";
        for (int i = 0; i < line2.length(); i++) {
            char c = line2.charAt(i);
            if (c != '{' && c != ',' && c != '}' && c != ' ') {
                // this means that it is a character in the language
                if (!(alphabet.contains("" + c))) {
                    System.out.println("Adding char \""+ c + "\" to alphabet");
                    alphabet = alphabet + c;
                }
            } else if (c == '}') {
                // this is the end of the line
                break;
            }
        }

        System.out.println("New Alphabet: " + alphabet);
        System.out.println("=========================================");

        // the third line of the file should have the start state
        String line3 = scr.nextLine();
        Node start = null;
        for (int i = 0; i < line3.length(); i++) {
            char c = line3.charAt(i);
            if (c != '{' && c != ',' && c != '}' && c != ' ') {
                // this means that this is the start
                for (Node node : inputNodes) {
                    if (node.getId() == c) {
                        start = node;
                        break;
                    }
                }
            } else if (c == '}') {
                // this is the end of the line
                break;
            }
        }

        System.out.println("New Start State: " + start.getId());
        System.out.println("=========================================");

        // the fourth line of the file should be the accepting state(s)
        String line4 = scr.nextLine();
        ArrayList<Node> acceptingStates = new ArrayList<Node>();
        for (int i = 0; i < line4.length(); i++) {
            char c = line4.charAt(i);
            if (c != '{' && c != ',' && c != '}' && c != ' ') {
                for (Node node : inputNodes) {
                    if (node.getId() == c) {
                        acceptingStates.add(node);
                    }
                }
            } else if (c == '}') {
                // this is the end of the line
                break;
            }
        }
        // the next line should be the transitions
        while (scr.hasNextLine()) {
            String line = scr.nextLine();
            System.out.println(line);
            int nodeCounter = 0;
            Node transitionSource = null;
            Node transitionDestination = null;
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c != '{' && c != ',' && c != '}' && c != ' ') {
                    // there should be 2 Nodes per transition line
                    if (nodeCounter == 0) {
                        for (Node node : inputNodes) {
                            if (node.getId() == c) {
                                transitionSource = node;
                            }
                        }
                        nodeCounter++;
                    } else if (nodeCounter == 1) {
                        // these are the letters that are needed for the transition
                        for (Node node : inputNodes) {
                            if (node.getId() == c) {
                                transitionDestination = node;
                            }
                        }
                        nodeCounter++;
                    } else {
                        System.out.println("Adding Transition from node " + transitionSource.getId() + " to "
                                + transitionDestination.getId() + " with input " + c);
                        Transition newTransition = new Transition(transitionDestination, c);
                        transitionSource.addTransition(newTransition);
                    }
                } else if (c == '}') {
                    // this is the end of the line
                    break;
                }
            }
        }

        System.out.println("=========================================");
        nfa = new NFA(inputNodes, acceptingStates, start, alphabet);
        System.out.println("NFA successfully completed.");
        // closes the scanner for the file
        scr.close();
        // creates a new scanner for the output
        Scanner scrString = new Scanner(System.in);
        boolean end = false;
        String input, result;
        while (end != true) {

            // Informs user and saves inputs
            System.out.println("To end the program, type in 'end'");
            System.out.println("Please type in a string to test");
            input = scrString.nextLine();
            input = input.trim();

            // Exits if user want to end program
            if (input.equalsIgnoreCase(END)) {
                end = true;
                break;
            }

            // Runs input through given NFA and checks for it accepts or rejects
            if (nfa.checkAccepts(input)) {
                result = "accepts";
            } else {
                result = "rejects";
            }

            // prints out the result
            System.out.println("The NFA " + result + " the String");
        }

        // closes the scanner for the system
        scrString.close();
        System.exit(0); // ends the program
    }
}