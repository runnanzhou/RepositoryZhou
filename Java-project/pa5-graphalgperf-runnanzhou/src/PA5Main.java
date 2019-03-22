
/*
 * 
 * COURSE: CSC210; Fall 2018;
 * PA5 File: PA5Main.java
 * PURPOSE: This program is to calculate the best choice of
 * the saleman's trip to every city in the state.It has three
 * options to do this task. One for recursion, one for heuristic
 * and one for mine approach. My approach is based on the 
 * recursive backtracking. With more pruning, it is faster than
 * the original method. What the user should expect the program
 * do is the cost of trip and the order of the city.
 * Command line and Input file was shown as below.
 *  
 * Command Line Usage:
 * PathTo/infile.mtx [HEURISTIC,BACKTRACK,MINE,TIME]
 * ----------- EXAMPLE INPUT -------------
 * Input file:
%%MatrixMarket matrix coordinate real general
%-------------------------------------------------------------------------------
% Driving distances between some cities in AZ.
% 1: Tucson
% 2: Phoenix
% 3: Prescott
% 4: Show Low
% 5: Flagstaff
% author: Michelle Strout
% kind: directed weighted graph
%-------------------------------------------------------------------------------
5 5 20
1 2 113.0
2 1 113.0
1 5 209.48
5 1 209.48
2 5 144.38
5 2 144.38
 *   ---------
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class PA5Main {
    /*
     * The class name is PA5Main. Which is the only class we use
     * in this program.
     */
    static double MAX = 10000.00;

    // State a constant
    public static void main(String[] args) throws FileNotFoundException {
        /*
         * The main program is the concise summary of the whole program.
         * 
         * @Params : String[] args
         * 
         * @Return void type
         */
        DGraph graph = readFile(args);
        if (args[1].equals("BACKTRACK")) {
            recursive(graph);
        } else if (args[1].equals("HEURISTIC")) {
            heuristic(graph);
        } else if (args[1].equals("MINE")) {
            mine(graph);
        } else if (args[1].equals("TIME")) {
            // Call the timing function which will show the time result.
            time(graph, "heuristic");
            time(graph, "recursive");
            time(graph, "mine");
        }
    }
    public static DGraph readFile(String[] args) throws FileNotFoundException {
        /*
         * The readFile method is to read the file and organize the information
         * which is provided by the inputfile.
         * 
         * @Params: String[] args
         * 
         * @Return DGraph graph
         */
        Scanner in = new Scanner(new File(args[0]));
        String file = in.nextLine();
        while (file.startsWith("%")) {
            file = in.nextLine();
        } // Jump over the line starting with "%"
        String[] factor = file.split(" +");
        int count = Integer.parseInt(factor[1]);
        DGraph graph = new DGraph(count);
        while (in.hasNext()) {
            file = in.nextLine();
            factor = file.split(" +");
            // creat the graph.
            graph.addEdge(Integer.parseInt(factor[0]),
                    Integer.parseInt(factor[1]), Double.valueOf(factor[2]));
        }
        return graph;
    }
    public static Trip recursive(DGraph graph) {
        /*
         * The recursive function include a help function which contribute
         * to the recursive backtracking. It new some trip and print results.
         * 
         * @Params: DGraph graph
         * 
         * @Return: Trip minTrip
         */
        Trip recTrip = new Trip(graph.getNumNodes());
        recTrip.chooseNextCity(1);
        Trip minTrip = new Trip(graph.getNumNodes());
        help(graph, recTrip, minTrip);// recursive
        System.out.println(minTrip.toString(graph));
        return minTrip;
    }
    public static Trip help(DGraph graph, Trip recTrip, Trip minTrip) {
        /*
         * The help function is the main body of the recursive backtracking
         * it use this method to calculate the result.
         * 
         * @Params:DGraph graph, Trip recTrip, Trip minTrip
         * 
         * @Return:Trip minTrip
         */
        if (recTrip.citiesLeft().size() == 0) {
            // The end of the recursive backtracking
            if (recTrip.tripCost(graph) < minTrip.tripCost(graph)) {
                minTrip.copyOtherIntoSelf(recTrip);
                return recTrip;
            }
        }
        if (recTrip.tripCost(graph) < minTrip.tripCost(graph)) {
            for (Integer city : recTrip.citiesLeft()) {
                // itertae the cities left
                recTrip.chooseNextCity(city);
                help(graph, recTrip, minTrip);
                recTrip.unchooseLastCity();
            }
        }
        return minTrip;
    }
    public static Trip heuristic(DGraph graph) {
        /*
         * It is the heuristic method which is faster than backtracking
         * but not the best solution of the trip. It cost less time
         * 
         * @Params: DGraph graph
         * 
         * @Return Trip heuTrip
         */
        Trip heuTrip = new Trip(graph.getNumNodes());
        heuTrip.chooseNextCity(1);
        int curCity = 1;
        int minCity = 0;
        for (int i = 2; i < graph.getNumNodes() + 1; i++) {
            double min = MAX;
            for (int n : graph.getNeighbors(curCity)) {
                if (heuTrip.isCityAvailable(n)) {
                    if (graph.getWeight(curCity, n) < min) {
                        min = graph.getWeight(curCity, n);
                        minCity = n;
                    } // make every step the most accurate choice .
                }
            }
            if (heuTrip.citiesLeft().contains(minCity)) {
                heuTrip.chooseNextCity(minCity);
            } // Judge whether the city is in the citiesleft.
            curCity = minCity;
        }
        System.out.println(heuTrip.toString(graph));
        return heuTrip;
    }
    public static Trip mine(DGraph graph) {
        /*
         * This is mine approach based on the recursive backtracking
         * which is more faster thant the original program because of
         * some changes.
         * 
         * @Params: DGraph graph
         * 
         * @Return:Trip minTrip
         */
        Trip myTrip = new Trip(graph.getNumNodes());
        myTrip.chooseNextCity(1);
        Integer curCity = 1;
        Trip minTrip = new Trip(graph.getNumNodes());
        helper(graph, myTrip, minTrip, curCity);
        System.out.println(minTrip.toString(graph));
        // Print the result
        return minTrip;
    }
    public static Trip helper(DGraph graph, Trip myTrip, Trip minTrip,
            Integer curCity) {
        /*
         * This is the main body of the backtracking. The main idea is similar
         * but more pruning was added. So it is faster.
         * 
         * @Params: DGraph graph, Trip myTrip, Trip minTrip,
         * Integer curCity
         * 
         * @Return:Trip myTrip
         */
        if (myTrip.isPossible(graph) || myTrip == null) {
            // Different end type of the recursive. This is faster.
            if (myTrip.tripCost(graph) < minTrip.tripCost(graph)) {
                minTrip.copyOtherIntoSelf(myTrip);
                return myTrip;
            }
        }
        if (myTrip.tripCost(graph) < minTrip.tripCost(graph)) {
            Map<Integer, Double> sortedCity = new TreeMap<Integer, Double>();
            for (Integer city : myTrip.citiesLeft()) {
                myTrip.chooseNextCity(city);
                curCity = city;
                if (myTrip.tripCost(graph) < minTrip.tripCost(graph)) {
                    // more pruning on the recursive . This is faster.
                    helper(graph, myTrip, minTrip, curCity);
                }
                myTrip.unchooseLastCity();
            }
        }
        return minTrip;
    }
    public static void time(DGraph graph, String method) {
        /*
         * The time method is to calculate the time cost of each function.
         * 
         * @Params:DGraph graph, String method
         * 
         * @Return void type
         */
        Trip trip = null;
        long startTime = System.nanoTime();
        if (method.equals("heuristic")) {
            trip = heuristic(graph);
        } else if (method.equals("recursive")) {
            trip = recursive(graph);
        } else {
            trip = mine(graph);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println(method + ": cost = " + trip.tripCost(graph) + ", "
                + duration + " milliseconds");
    }

}
