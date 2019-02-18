
/*
 * 
 * COURSE: CSC210; Fall 2018; Section C
 * PA2 File: PA3Main.java
 * PURPOSE: This program is to print out the anagram of the given letter
 * It is using the back tracking method. For the user, what you should 
 * expect is the all the combination of anagram for the given letter.
 * User can also input the max number to restrict the size.
 *  
 * 
 * Command Line Usage:
 * java_PA3Main_infile_name given_word max;
 * 
 * Comman Line Usage:
 * dict1.txt barbarabush 0
 * 
 * 
 * ----------- EXAMPLE INPUT -------------
 * Input file:
 *   ---------    
 *   abashb 
 *   aura   
 *   bar    
 *   barb   
 *   bee    
 *   beg    
 *   blush  
 *   bog    
 *   bogus  
 *   ---------
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PA3Maincopy {

    public static void main(String[] args) {
        // This is the consise summary of the whole program.
        // parameter@:String[] args
        // return:
        Scanner input = inputFile(args);
        String word = args[1];
        int max = Integer.parseInt(args[2]);
        LetterInventory letterInv = new LetterInventory(args[1]);
        ArrayList<String> wordList = choices(letterInv, input, word);
        printLine(word, wordList);
        ArrayList<String> chosen = new ArrayList<String>();
        recursion(chosen, wordList, max, letterInv, word);

    }

    private static void printLine(String word, ArrayList<String> wordList) {
        // This is to print out the required format of the specification.
        // parameter@:String word ,ArrayList<String>wordList
        // return:
        System.out.println("Phrase to scramble: " + word);
        System.out.println();
        System.out.println("All words found in " + word + ":");
        System.out.println(wordList);
        System.out.println("");
        System.out.println("Anagrams for " + word + ":");
    }

    private static Scanner inputFile(String[] args) {
        // This is used to got the input file and catch the exception
        // parameter@:String[] args
        // return:input
        Scanner input = null;
        try {
            input = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return input;
    }

    public static ArrayList<String> choices(LetterInventory letterInv,
            Scanner input, String word) {
        // This is the choices of which is the word contained in the letterInv.
        // parameter@:LetterInventory letterInv,Scanner input, String word
        // return:wordList
        ArrayList<String> wordList = new ArrayList<String>();
        while (input.hasNextLine()) {
            String single = input.nextLine();

            if (letterInv.contains(single)) {
                wordList.add(single);
            }
        }
        input.close();
        return wordList;
    }

    public static void recursion(ArrayList<String> chosen,
            ArrayList<String> wordList, int max, LetterInventory letterInv,
            String word) {
        // This is the core function of the whole program
        // which is using recursion and backtracking method to got
        // the chosen list which is the answer.
        // parameter@:ArrayList<String> chosen,ArrayList<String> wordList, int
        // max, LetterInventory letterInv,String word
        // return:
        if (letterInv.size() == 0 && (chosen.size() <= max || max == 0)) {
            System.out.println(chosen);

        } else {

            for (int i = 0; i < wordList.size(); i++) {
                    if (letterInv.contains(wordList.get(i))) {
                        chosen.add(wordList.get(i));
                        letterInv.subtract(wordList.get(i));
                        recursion(chosen, wordList, max, letterInv, word);
                        chosen.remove(chosen.size() - 1);
                        letterInv.add(wordList.get(i));
                    }
                    continue;
            }
        }
    }
}
