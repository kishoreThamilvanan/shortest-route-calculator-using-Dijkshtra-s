import java.util.HashSet;
import java.util.LinkedList;

/**
 * node class which stores data of each city.
 */
public class Node {

    private String name;
    private HashSet<Edge> edges;// collection of all nodes that are adjacent to the current node
    private LinkedList<Edge> adjEdges;
    private Boolean visited;
    private LinkedList<String> path;
    private int distance;
  
    /**
     * default constructor
      */
    public Node() {
        this.name ="";
        this.edges = new HashSet<Edge>();
        adjEdges = new LinkedList<Edge>();
        this.visited = false;
        this.path = new LinkedList<String>();
        this.distance = 0;
    }

    /**
     * method returns all the adjacent edges of the node.
     * @return
     * Linkedlist of nodes
     */
    public LinkedList<Edge> getAdjEdges() {
        return adjEdges;
    }

    /**
     * Method sets the adjacent edges of the node.
     *
     * @param adjEdges
     *
     */
    public void setAdjEdges(LinkedList<Edge> adjEdges) {
        this.adjEdges = adjEdges;
    }

    /**
     * Mehtod returns the name of the node.
     *
     * @return
     * returns the name of the node.
     */
    public String getName() {
        return name;
    }

    /**
     * Mehtod sets the name of the node.
     *
     * @param name
     * user entered paramter as the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mehtod returns the hashset of all the edges.
     *
     * @return
     * hashset
     */
    public HashSet<Edge> getEdges() {
        return edges;
    }

    /**
     * Mehtod sets the hashset of all the edges.
     *
     * @param edges
     * user enetered hashset as a parameter.
     */
    public void setEdges(HashSet<Edge> edges) {
        this.edges = edges;
    }

    /**
     * Method returns the visited of the node.
     *
     * @return
     * returns the boolean value.
     */
    public Boolean getVisited() {
        return visited;
    }

    /**
     * Method sets the visited of the Node.
     *
     * @param visited
     * user enetered parameter for visited.
     */
    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    /**
     * Method returns the path of the node.
     *
     * @return
     * returns the linked list of path.
     */
    public LinkedList<String> getPath() {
        return path;
    }

    /**
     * Method sets the path of the node.
     *
     * @param path
     * linked list of node names as a path.
     */
    public void setPath(LinkedList<String> path) {
        this.path = path;
    }

    /**
     * method returns the distance of the node.
     *
     * @return
     * integer
     */
    public int getDistance() {
        return distance;
    }

    /**
     * method sets the distance of the node.
     *
     * @param distance
     * integer
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * method returns the string of the node.
     *
     * @return
     * string
     */
    public String toString(){
        return "City: "+name
                +"\nVisited: "+visited;
    }


}
