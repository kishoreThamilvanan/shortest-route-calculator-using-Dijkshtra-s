/**
 * Class edge implements comparable. conatins the details of an edge
 */
public class Edge implements Comparable{
    private Node nodeA;
    private Node nodeB;
    private int cost;
    private boolean added;

    /**
     * default constructor.
     */
    public Edge() {
        this.nodeA = new Node();
        this.nodeB = new Node();
        this.cost = 0;
        added = false;
    }

    /**
     *  returns whether it is added or not.
     *
     * @return
     * boolean.
     */
    public boolean isAdded() {
        return added;
    }

    /**
     * method sets the added of the edge.
     *
     * @param added
     * sets a user entered boolean value.
     */
    public void setAdded(boolean added) {
        this.added = added;
    }

    /**
     * Mwthod returns node A.
     *
     * @return
     * returns a node.
     */
    public Node getNodeA() {
        return nodeA;
    }

    /**
     * Method sets the node A.
     *
     * @param nodeA
     * user entered node.
     */
    public void setNodeA(Node nodeA) {
        this.nodeA = nodeA;
    }

    /**
     * method returns the node B.
     *
     * @return
     * returns the node.
     */
    public Node getNodeB() {
        return nodeB;
    }

    /**
     * Method sets the node B.
     *
     * @param nodeb
     * user enetered node.
     */
    public void setNodeB(Node nodeb) {
        this.nodeB = nodeb;
    }

    /**
     * Method returns the cost of the edge.
     *
     * @return
     * returns an integer.
     */
    public int getCost() {
        return cost;
    }

    /**
     * method sets the cost of the edge.
     *
     * @param cost
     * user enetered integer.
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Mehtod returns the details of the edge in a string.
     *
     * @return
     * returns a string.
     */
    public String toString(){
        return "\n"+nodeA.getName()
                +" --> "+nodeB.getName()
                +" ["+cost+"]";
    }

    /**
     * method helps to sort the edges according to cost.
     *
     * @param otherEdge
     * user entered edge.
     *
     * @return
     * returns an integer.
     *
     */
    public int compareTo(Object otherEdge) {

        Edge e = (Edge)otherEdge;
        if(this.cost > e.cost)
            return 1;

        if(this.cost<e.cost)
            return -1;

        return 0;
    }
}
