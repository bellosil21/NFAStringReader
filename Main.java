import java.io.*;
import java.util.*;

/**
 * main.java
 * 
 * Main Class for calling and creating a
 * 
 * Authors: Dylan Pascua and Paul Patrick Bellosillo Date: December 2, 2019
 * 
 */
public class Main {
    public static void main(String[] args) {

        // the parser portion that creates the NFA
        File file = new File("\\Test Cases\\Test_Case_1.txt");

        // PUT THE CONSTRUCTOR FOR THE NFA HERE
        NFA nfa = null;

        // scans the file for data
        try {
            Scanner scr = new Scanner(file);

            // the first line of the file should have all the names of the Nodes in the DFA
            String line1 = scr.nextLine();
            ArrayList<Node> inputNodes = new ArrayList<Node>();
            for (int i = 0; i <= line1.length(); i++) {
                char c = line1.charAt(i);
                if (c != '{' || c != ',' || c != '}' || c != ' ') {
                    // creates a new node based on the number
                    Node node = new Node(line1.charAt(i));
                    inputNodes.add(node);
                } else if (c == '}') {
                    // this is the end of the line
                    break;
                }
            }

            // the second line of the file should have the characters in the language
            String line2 = scr.nextLine();
            String alphabet = "";
            for (int i = 0; i <= line2.length(); i++) {
                char c = line2.charAt(i);
                if (c != '{' || c != ',' || c != '}' || c != ' ') {
                    // this means that it is a character in the language
                    if (!(alphabet.contains(""+c))) {
                        alphabet = alphabet + c;
                    }
                } else if (c == '}') {
                    // this is the end of the line
                    break;
                }
            }

            // the third line of the file should have the start state
            String line3 = scr.nextLine();
            Node start = null;
            for (int i = 0; i <= line3.length(); i++) {
                char c = line3.charAt(i);
                if (c != '{' || c != ',' || c != '}' || c != ' ') {
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

            // the fourth line of the file should be the accepting state(s)
            String line4 = scr.nextLine();
            ArrayList<Node> acceptingStates = new ArrayList<Node>();
            for (int i = 0; i <= line4.length(); i++) {
                char c = line4.charAt(i);
                if (c != '{' || c != ',' || c != '}' || c != ' ') {
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

            // REMEMBER TO MAKE ERROR CASES IF THERE ARE LESS THAN 5 LINES

            // the next line should be the transitions
            while (scr.hasNextLine()) {
                String line = scr.nextLine();
                int nodeCounter = 0;
                for (int i = 0; i <= line.length(); i++) {
                    char c = line1.charAt(i);
                    if (c != '{' || c != ',' || c != '}') {
                        // there should be 2 Nodes per transition line
                        if (nodeCounter < 2) {
                            // these are nodes
                            // TODO
                            nodeCounter++;
                        } else {
                            // these are the letters that are needed for the transition
                            // TODO
                        }
                    } else if (c == '}') {
                        // this is the end of the line
                        break;
                    }
                }
            }

            nfa = new NFA(inputNodes, acceptingStates, start, alphabet);
            // closes the scanner for the file
            scr.close();

        } catch (FileNotFoundException fnfe){
            System.out.print("File not here or made fam.");
        } 
        catch (Exception e) {
            // TODO: handle exception
            System.out.print("Something went wrong fam.");
        }

        // creates a new scanner for the output
        Scanner scrString = new Scanner(System.in);
        boolean end = false;
        String input;
        boolean result = false;
        while (end != true) {

            System.out.println("To end the program, type in 'end'");
            System.out.println("Please type in a string to test");

            input = scrString.nextLine();
            input = input.trim();

            if (input == "end") {
                end = true;
            }
            // TODO:
            // METHOD THAT RUNS THE INPUT THROUGH THE NFA
            result = nfa.checkAccepts(input);

            // prints out the result
            System.out.println("The NFA " + result + "s the String");
        }

        // closes the scanner for the system
        scrString.close();
        System.exit(0); // ends the program
    }
}