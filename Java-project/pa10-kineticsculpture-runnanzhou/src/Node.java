import java.util.TreeSet;

import javafx.scene.paint.Color;


public class Node {
    // This class is to creat a node class which represent the onject of node.
    public int id; // identification of the node.
    public TreeSet<Node> neighbors; // data structure to store the neighbors.
    public String type; // the type of the node.
    public Color color;// Color of the node.
    public int x; // x value of the node.
    public int y; // y value of the node.

    /**
     * The constructor of the node.
     * 
     * @param int
     *            id, String type, int x, int y
     */
    public Node(int id, String type, int x, int y) {
        this.id = id;
        this.type = type;
        this.x = x;
        this.y = y;
    }


    /**
     * This method is to get the node's ID.
     * 
     * @return
     */
    public int getId() {
        return id;
    }
    
    /**
     * This method is to get all the neighbors.
     * 
     * @return
     */
    public TreeSet<Node> getNeighbors() {
        return neighbors;
    }
    
    /**
     * This method is to add node to the data structure.
     * 
     * @param neigh
     * @return
     */
    public TreeSet<Node> addNeighbor(Node neigh) {
        neighbors.add(neigh);
        return neighbors;
    }

}
