# PA5-GraphAlgPerf
Starter code for PA5.  See the PA5 writeup in the Writeups repository for the github assignment link.

See https://github.com/UACS210Fall2018/PA-Drill-Section-Writeups/tree/master/PA5-GraphAlgPerf

Experiment data of the big11.mtx

cost = 1.9, visitOrder = [1, 4, 6, 10, 5, 9, 11, 7, 8, 2, 3]
recursive: cost = 1.889818317, 117 milliseconds
cost = 1.9, visitOrder = [1, 4, 6, 10, 5, 9, 11, 7, 8, 2, 3]
mine: cost = 1.889818317, 110 milliseconds

cost = 1.9, visitOrder = [1, 4, 6, 10, 5, 9, 11, 7, 8, 2, 3]
recursive: cost = 1.889818317, 108 milliseconds
cost = 1.9, visitOrder = [1, 4, 6, 10, 5, 9, 11, 7, 8, 2, 3]
mine: cost = 1.889818317, 101 milliseconds

The improvement i did is to putting a pruning on the searching tree.
if (myTrip.tripCost(graph) < minTrip.tripCost(graph)) {
                    // more pruning on the recursive . This is faster.
                    helper(graph, myTrip, minTrip, curCity);
                }
It make sense because after add a new city to the trip. It can distinguish
whether the trip is possible or the cost of the trip is over the minimum one.
if it does, then it will jump over the recursion step and cut it off.
Other change i made is to change the end of the recursion.
if (myTrip.isPossible(graph) || myTrip == null)
Then it is more faster than the 
if (recTrip.citiesLeft().size() == 0) 