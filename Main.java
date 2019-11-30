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
    String END_CASE = "end";

    public static void main(String[] args) {

        // the parser portion that creates the NFA
        File file = new File("C:\\Users\\Paul P T Bellosillo\\Documents\\GitHub\\NFAStringReader\\Test Cases\\Test_Case_1.txt");

        // PUT THE CONSTRUCTOR FOR THE NFA HERE
        Scanner scr = null;
        NFA nfa = null;

        // scans the file for data
        try {
            scr = new Scanner(file);

        } catch (FileNotFoundException fnfe){
            System.out.print("File not here or made fam.");
        } 
        catch (Exception e) {
            // TODO: handle exception
            System.out.print("Something went wrong fam.");
        }

           // the first line of the file should have all the names of the Nodes in the DFA
           String line1 = scr.nextLine();
           System.out.println("line1: " + line1);
           ArrayList<Node> inputNodes = new ArrayList<Node>();
           for (int i = 0; i < line1.length(); i++) {
               char c = line1.charAt(i);
               if (c != '{' && c != ',' && c != '}' && c != ' ') {
                   // creates a new node based on the number
                   Node node = new Node(c);
                   System.out.println("New node ID: " + c);
                   inputNodes.add(node);
               } else if (c == '}') {
                   // this is the end of the line
                   break;
               }
           }

           // the second line of the file should have the characters in the language
           String line2 = scr.nextLine();
           String alphabet = "";
           for (int i = 0; i < line2.length(); i++) {
               char c = line2.charAt(i);
               if (c != '{' && c != ',' && c != '}' && c != ' ') {
                   // this means that it is a character in the language
                   if (!(alphabet.contains(""+c))) {
                       alphabet = alphabet + c;
                       System.out.println(alphabet);
                   }
               } else if (c == '}') {
                   // this is the end of the line
                   break;
               }
           }

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

           // REMEMBER TO MAKE ERROR CASES IF THERE ARE LESS THAN 5 LINES

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
                               if (node.getId() == c){
                                   transitionSource = node;
                               }
                           }
                           nodeCounter++;
                       } else if (nodeCounter == 1){
                           // these are the letters that are needed for the transition
                           for (Node node : inputNodes) {
                               if (node.getId() == c){
                                   transitionDestination = node;
                               }
                           }
                           nodeCounter++;
                       }
                       else{
                           System.out.println("Adding Transition from" + transitionSource.getId() + " to " +
                                transitionDestination.getId() + " with input " + c);
                           Transition newTransition = new Transition(transitionDestination, c);
                           transitionSource.addTransition(newTransition);
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

        // creates a new scanner for the output
        Scanner scrString = new Scanner(System.in);
        boolean end = false;
        String input, result;
        while (end != true) {

            System.out.println("To end the program, type in 'end'");
            System.out.println("Please type in a string to test");

            input = scrString.nextLine();
            input = input.trim();

            if (input.equalsIgnoreCase("end")) {
                end = true;
            }

            // TODO:
            // METHOD THAT RUNS THE INPUT THROUGH THE NFA
            if (nfa.checkAccepts(input)){
                result = "accepts";
            }
            else {
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