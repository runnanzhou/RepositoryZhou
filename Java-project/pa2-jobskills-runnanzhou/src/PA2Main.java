
/* 
 * Author: Runnan Zhou
 * File:PA2Main.java
 * Assignment: Programming Assignment 2-JobSkills
 * Course: CSC210 Fall 2018 
 * Section: Friday 10am
 * Purpose: This program aim at read command line from Eclipse
 * and make decision based on the command input. Then compute
 * the number and use map data structure to store the data.
 * User who want to use this file should use the format input
 * as below and what to be expected is the number of the location
 * of the category.
 * Example Input shown as below:
 * --------------------------------------------------------------
 *  | Category,Location                       
 *  | CategoryX,Tel Aviv                      
 *  | CategoryX,Tel Aviv                      
 *  | CategoryY,Houston                       
 *  | CategoryX,Jonesboro 
 *  -------------------------------------------------------------
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PA2Main {
    public static void main(String[] args) {
        // The summary of the whole program
        // @parameter: args
        Scanner input = openFile(args);
        Map<String, Integer> job = new HashMap<String, Integer>();
        Map<String, Map<String, Integer>> location = new HashMap<String, Map<String, Integer>>();
        String firstLine = input.nextLine();

        creatSum(input, job, location);
        judgeInput(args, job, location);
    }

    private static void creatSum(Scanner input, Map<String, Integer> job,
            Map<String, Map<String, Integer>> location) {
        /*
         * This function is used to creat 2 maps. one of
         * them is a 2 dimenson map and one is one dimension map
         * 
         * @parameter:Scanner input, map job , map location
         */
        while (input.hasNextLine()) {

            String line = input.nextLine();
            String[] variable = line.trim().split(",");
            String cate = variable[2];
            String pos = variable[3];

            creat2dMap(location, cate, pos);

            creatSmall(job, cate);
        }
    }

    private static void judgeInput(String[] args, Map<String, Integer> job,
            Map<String, Map<String, Integer>> location) {
        /*
         * This function is to judge the input command line and based on the
         * input then make decision about what should be output. If the input
         * is invalid then output "Invalid Command"
         * 
         * @parameter: args, map job, map locaiton.
         */
        if (args[1].equals("CATCOUNT")) {
            System.out.println("Number of positions for each category");
            System.out.println("-------------------------------------");

            List<String> sortedKeys = new ArrayList<String>(job.keySet());
            Collections.sort(sortedKeys);
            for (int i = 0; i < sortedKeys.size(); i++) {
                String key1 = sortedKeys.get(i);
                System.out.println(key1 + ", " + job.get(key1));
            }
        } else if (args[1].equals("LOCATIONS")) {
            System.out.println("LOCATIONS for category: " + args[2]);
            System.out.println("-------------------------------------");

            List<String> sortedKeys2 = new ArrayList<String>(
                    location.get(args[2]).keySet());
            Collections.sort(sortedKeys2);
            for (int i = 0; i < sortedKeys2.size(); i++) {
                System.out.println(sortedKeys2.get(i) + ", "
                        + location.get(args[2]).get(sortedKeys2.get(i)));
            }

        } else {
            System.out.print("Invalid Command");
        }
    }

    private static void creatSmall(Map<String, Integer> job, String cate) {
        // put category into the map
        // @parameter:map job, string cate.
        if (job.get(cate) == null) {
            job.put(cate, 1);
        } else {
            job.put(cate, job.get(cate) + 1);
        }
    }

    private static void creat2dMap(Map<String, Map<String, Integer>> location,
            String cate, String pos) {
        // put data into the 2-dimension map
        // @parameter: map location, string cate, string pos
        if (location.get(cate) == null) {
            location.put(cate, new HashMap<>());
            location.get(cate).put(pos, 1);
        } else {
            if (location.get(cate).get(pos) == null) {
                location.get(cate).put(pos, 1);
            } else {
                location.get(cate).put(pos, location.get(cate).get(pos) + 1);

            }
        }
    }

    private static Scanner openFile(String[] args) {
        // open the file and catch exception when file not found.
        // @parameter:args
        Scanner input = null;
        try {
            input = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return input;
    }

}
