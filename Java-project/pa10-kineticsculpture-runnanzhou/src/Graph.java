import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Graph {
    // This class is to store the node and edge into different data structures.

    public static ArrayList<Node> Nodelist = new ArrayList<>();
    // Data structure to store the ndoe
    public static ArrayList<Edge> Edgelist = new ArrayList<>();
    // Data structure to store the edge.
    public static Map<Integer, String> neighbor = new TreeMap<>();
    // Data structure to store the neighbor of node.
    public static int startNodeId;// Start node 's id.
    private static int TEXT_SIZE = 30;
    // The size of the textbox.
    private static int BOX_SIZE = 25;
    // The size of the box.

    /**
     * This method is to add node to the graph.
     * 
     * @param line
     */
    public static void addNode(String line) {
        line = line.replaceAll(" ", "");
        line = line.substring(0, line.indexOf(","))
                + line.substring(line.indexOf(",") + 1);
        int node_id = Integer.parseInt(line.substring(0, line.indexOf(":")));

        String type = line.substring(line.indexOf(":") + 1, line.indexOf("("));
        if (type.equals("input")) {
            startNodeId = node_id;
        }
        int x = Integer.parseInt(
                line.substring(line.indexOf("(") + 1, line.indexOf(",")));
        int y = Integer.parseInt(
                line.substring(line.indexOf(",") + 1, line.indexOf(")")));
        Node current = new Node(node_id, type, x, y);
        JavaFXView.initNode(node_id, x, y + TEXT_SIZE, BOX_SIZE);
        Nodelist.add(current);
    }

    /**
     * This method get the node id from the line.
     * 
     * @param command
     * @return
     */
    public static int getId(String line) {
        String[] factor = line.split(":");
        int id = Integer.parseInt(factor[0]);
        return id;
    }




    /**
     * This method is to add a edge to the graph.
     * 
     * @param line
     * @return
     */
    public static void addEdge(String line) {

        int start = Integer.parseInt(line.substring(0, 1));
        int end = Integer.parseInt(line.substring(5, 6));
        Edge current = new Edge(start, end);
        drawEdges(start, end);
        if (neighbor.containsKey(start)) {
            String newNeb = neighbor.get(start) + " " + end;
            neighbor.put(start, newNeb);
        } else {
            neighbor.put(start, String.valueOf(end));
        }
        Edgelist.add(current);
    }

    /**
     * This method is to draw a edge between two nodes.
     * 
     * @param start
     * @param end
     */
    public static void drawEdges(int start, int end) {
        int startX = getX(start);
        int startY = getY(start);
        int endX = getX(end);
        int endY = getY(end);
        for (Node node : Nodelist) {
            JavaFXView.initEdge(start, end, startX + BOX_SIZE,
                    startY + TEXT_SIZE + BOX_SIZE / 2, endX,
                    endY + TEXT_SIZE + BOX_SIZE / 2);

        }
    }

    /**
     * This method get the node's x value.
     * 
     * @param id
     * @return
     */
    public static int getX(int id) {
        for (Node node : Nodelist) {
            if (node.getId() == id) {
                return node.x;
            }
        }
        return 0;
    }

    /**
     * This method is to get the node's y value.
     * 
     * @param id
     * @return
     */
    public static int getY(int id) {
        for (Node node : Nodelist) {
            if (node.getId() == id) {
                return node.y;
            }
        }
        return 0;
    }
}
