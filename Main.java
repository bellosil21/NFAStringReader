import java.io.*;
import java.util.*;
/**
 * main.java
 * 
 * Main Class for calling and creating a 
 * 
 * Authors: Dylan Pascua and Paul Patrick Bellosillo 
 * Date: December 2, 2019
 * 
 */
public class Main {
    public static void main(String[] args) {

        //the parser portion that creates the NFA 
        File file = new file("C:\Users\ASUS\Documents\GitHub\NFAStringReader\Test Cases\Test_Case_1.txt");

        //PUT THE CONSTRUCTOR FOR THE NFA HERE

        //scans the file for data
        try {
            Scanner scr = new Scanner(file);
            int lineCount = 0; 
            
            //the first line of the file should have all the names of the Nodes in the DFA
            String line1 = scr.nextLine();
            for(int i = 0 ; i <= line1.length(); i++){
                char c = line1.charAt(i);
                if(c != '{' || c != ',' || c != '}'){
                    //this means that this is is a Node
                }
                else if(c == '}'){
                    //this is the end of the line 
                    break; 
                }
            }

            //the second line of the file should have the characters in the language 
            String line2 = scr.nextLine();
            for(int i = 0 ; i <= line2.length(); i++){
                char c = line2.charAt(i);
                if(c != '{' || c != ',' || c != '}'){
                    //this means that it is a character in the language 
                }
                else if(c == '}'){
                    //this is the end of the line 
                    break; 
                }
            }

            //the third line of the file should have the start state 
            String line3 = scr.nextLine();
            for(int i = 0 ; i <= line3.length(); i++){
                char c = line3.charAt(i);
                if(c != '{' || c != ',' || c != '}'){
                    //this means that this is the start
                }
                else if(c == '}'){
                    //this is the end of the line 
                    break; 
                }
            }

            //the fourth line of the file should be the accepting state(s) 
            String line4 = scr.nextLine();
            for(int i = 0 ; i <= line4.length(); i++){
                char c = line4.charAt(i);
                if(c != '{' || c != ',' || c != '}'){
                    //this means that this is an accepting state 
                }
                else if(c == '}'){
                    //this is the end of the line 
                    break; 
                }
            }

            //REMEMBER TO MAKE ERROR CASES IF THERE ARE LESS THAN 5 LINES 

            //the next line should be the transitions  
            while(scr.hasNextLine()){
                String line = scr.nextLine(); 
                int nodeCounter = 0;
                for(int i = 0 ; i <= line.length(); i++){
                    char c = line1.charAt(i);
                    if(c != '{' || c != ',' || c != '}' ){
                        //there should be 2 Nodes per transition line
                        if(nodeCounter < 2){
                            //these are nodes 
                            nodeCounter++;
                        }
                        else{
                            //these are the letters that are needed for the transition 
                        }
                    }
                    else if(c == '}'){
                        //this is the end of the line 
                        break; 
                    }
                }
            }
            
        } catch (Exception e) {
            //TODO: handle exception
            System.out.print("Something went wrong fam.");
        }


    }
}