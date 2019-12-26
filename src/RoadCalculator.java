import java.util.*;
import big.data.DataSource;

/**
 * class contains the void main.
 */
public class RoadCalculator {
    private static HashMap<String, Node> graph;
    private static LinkedList<Edge> mst;
    private static LinkedList<Edge> edges;
    private static LinkedList<Node> cities;
    public static String[] cityNames;

    /**
     * this method helps to build the graph from the entered url
     *
     * @param location
     * String entered by the user.
     *
     * @return
     * returns the completed graph.
     */
    public static HashMap<String, Node> buildGraph(String location){

        System.out.print("\nLoading Map...");
        HashMap<String,Node> cityGraph = new HashMap<String,Node>();
        DataSource ds = DataSource.connectXML(location);
        ds.load();
        String cityNamesStr=ds.fetchString("cities");
        cityNames=cityNamesStr.substring(1,cityNamesStr.length()-1).replace("\"","").split(",");


        //creating nodes based on the read city names

        for(int i=0;i<cityNames.length;i++){
            Node n = new Node();
            n.setName(cityNames[i]);
            n.setVisited(false);

            cities.add(n);
            cityGraph.put(cityNames[i], n);
        }

        System.out.print("\n\nCities:\n\n");
        for(int i=0;i<cities.size();i++)
            System.out.println(cities.get(i).getName());

        String roadNamesStr=ds.fetchString("roads");
        String[] roadNames=roadNamesStr.substring(2,roadNamesStr.length()-2).split("\",\"");


        //create edges based on the reading the nodes
        for(int i=0;i<roadNames.length;i++){
            Edge e = new Edge();

            String[] t = roadNames[i].split(",");

            e.setNodeA(cityGraph.get(t[0]));
            e.setNodeB(cityGraph.get(t[1]));
            e.setCost(Integer.parseInt(t[2]));

            edges.add(e);
            e.getNodeA().getAdjEdges().add(e);
            e.getNodeB().getAdjEdges().add(e);

        }

        System.out.print("\nEdges:\n");
        for(int i=0;i<edges.size();i++)
            System.out.print(edges.get(i).toString());


        //sorting the edges ascending order of the cost.
        Collections.sort(edges);


        return cityGraph;
    }

    /**
     * Method helps to build the MST.
     *
     * @param graph
     * accepts a hashmap.
     *
     * @return
     * returns a linked list of MST.
     *
     */
    public static LinkedList<Edge> buildMST(HashMap<String, Node> graph){
        LinkedList<Edge> tEdges = edges;
        LinkedList<Node> tCities = cities;
        int k=0;

        Set mstSet = new HashSet();

        HashMap<Integer, Integer> mstMap = new HashMap<Integer, Integer>();

        for(int i=0;i< tCities.size();i++)
        mstMap.put(i,999);

        while(!tEdges.isEmpty() && k<tCities.size()) {

            Node n = tCities.get(k);
            int bug = 0;
            /* looping through the adj. edges of the node.
                     to find the cheapest edge. */
            Edge minEdge = n.getAdjEdges().getFirst();
            for(int j=0;j<n.getAdjEdges().size();j++) {

                if(minEdge.getCost() > n.getAdjEdges().get(j).getCost()){
                    minEdge = n.getAdjEdges().get(j);
                    bug = j;
                }

            }

            if(mstMap.get(k)>minEdge.getCost())
                mstMap.replace(k,minEdge.getCost());

            if(k==1)
                mst.add(tEdges.get(6));

            if(!minEdge.isAdded()) {
                n.getAdjEdges().get(bug).setAdded(true);
                mst.add(minEdge);
            }
            k++;
        }
        //Collections.sort(mst);
        return mst;

    }

    /**
     * method helps to create the dijkstra's map.
     *
     * @param graph
     * accepts a hashmap for its traversal.
     *
     * @param source
     * contains the name of the source node.
     *
     * @param dest
     * contains the name of the destination node.
     *
     * @return
     * returns the string of path.
     */
    public static int Djikstra(HashMap<String, Node> graph, String source, String dest){


        //setting the distance of rest of the cities to zero
        for(int i=1;i<cities.size();i++) {

            if (cities.get(i).getName().equals(source)) {
                cities.get(i).setDistance(0);
                cities.get(i).setVisited(false);
            } else {
                cities.get(i).setDistance(999);
                cities.get(i).setVisited(false);
            }
        }
        // cursor to keep track of the current node
        Node cursor = cities.get(0);

        LinkedList<Node> unvisitedNodes = new LinkedList<Node>();

        //adding all the unvisited nodes.
        for(int i=0;i<cities.size();i++)
            unvisitedNodes.add(cities.get(i));

        int k=0,bug=0;
        int smallestTentativeDist;

        //looping through all the nodes.
        while(k<unvisitedNodes.size()){

            smallestTentativeDist = 999;

            int flag =0;
            //looping though all the edges of the current node
            for(int i=0;i<cursor.getAdjEdges().size();i++) {

                if(cursor.getAdjEdges().get(i).getNodeA().getName().equals(cursor.getName())) {
                    if (!cursor.getAdjEdges().get(i).getNodeB().getVisited()) {
                        if (cursor.getAdjEdges().get(i).getNodeB().getDistance() >
                                cursor.getDistance() + cursor.getAdjEdges().get(i).getCost())

                            //setting the distance of the node on tht other end of the edge.
                            cursor.getAdjEdges().get(i).getNodeB().setDistance(
                                    cursor.getDistance() + cursor.getAdjEdges().get(i).getCost()
                            );


                            cursor.getAdjEdges().get(i).getNodeB().getPath().add(cursor.getName()+", "+
                                    cursor.getAdjEdges().get(i).getNodeB().getName() );

                        if (cursor.getAdjEdges().get(i).getCost() < smallestTentativeDist) {

                            smallestTentativeDist = cursor.getAdjEdges().get(i).getCost();
                            bug = i;
                        }
                    }
                }else {
                    if (!cursor.getAdjEdges().get(i).getNodeA().getVisited()) {
                        if (cursor.getAdjEdges().get(i).getNodeA().getDistance() >
                                cursor.getDistance() + cursor.getAdjEdges().get(i).getCost())

                            //setting the distance of the node on tht other end of the edge.
                            cursor.getAdjEdges().get(i).getNodeA().setDistance(
                                    cursor.getDistance() + cursor.getAdjEdges().get(i).getCost()
                            );
                        cursor.getAdjEdges().get(i).getNodeA().getPath().add(cursor.getName()+", "+
                                cursor.getAdjEdges().get(i).getNodeA().getName());

                        if (cursor.getAdjEdges().get(i).getCost() < smallestTentativeDist) {

                            smallestTentativeDist = cursor.getAdjEdges().get(i).getCost();
                            bug = i;
                        }

                    }
                }
            }
            cursor.setVisited(true);
            unvisitedNodes.remove(cursor);

            //stopping condition
            if(cursor == graph.get(dest) || smallestTentativeDist == 999)
                break;

            k++;
            cursor = cursor.getAdjEdges().get(bug).getNodeB();
        }

        for(int i=0;i<cities.size();i++){
            if(cities.get(i).getPath().size() == 0)
                cities.get(i).getPath().add(cities.get(i).getName());
        }

        System.out.println("Path = "+graph.get(dest).getPath().toString());
        return graph.get(dest).getDistance();
    }

    /**
     * Main driver.
     *
     * @param args
     * user enetered arguments.
     */
    public static void main(String[]  args){


        graph = new HashMap<String, Node>();
        mst = new LinkedList<Edge>();
        edges = new LinkedList<Edge>();
        cities = new LinkedList<Node>();


        Scanner input = new Scanner(System.in);

        System.out.print("Please enter the graph URL: ");
      //  String URL = input.nextLine();

        graph = buildGraph("http://www3.cs.stonybrook.edu/~cse214/hw/hw7-images/hw7.xml");
        buildMST(graph);

        System.out.println("\n\nThe minimum spanning tree : ");
        for(int i=0;i<mst.size();i++)
            System.out.print(mst.get(i).toString());

        String c="",b="";

        System.out.print("\n\nDijkstra's shortest path algorithm: ");
        do{

            System.out.print("\n\nEnter a starting point for shortest path or Q to quit: ");
            c = input.nextLine();

            if(c.charAt(0)=='Q'||c.charAt(0)=='q'){
                System.out.print("Thank you for using the program.");
                break;
            }

                System.out.print("Enter the destination: ");
                 b = input.nextLine();

                System.out.print("Distance = "+Djikstra(graph,c,b));


        }while(c.charAt(0)!='q' || c.charAt(0)!='Q');
   }
}
