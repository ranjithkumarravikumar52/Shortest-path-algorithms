package dijkstras;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class Graph {

    private List<Vertex> verticesInTheGraph;
    private Vertex tempVertex;
    private PriorityQueue<Vertex> minHeap;

    public Graph() {
        this.verticesInTheGraph = new ArrayList<>();
        tempVertex = new Vertex("temp");
        minHeap = new PriorityQueue<>();

    }

    public List<Vertex> getVerticesInTheGraph() {
        return verticesInTheGraph;
    }

    public void addVerticesToTheGraph(Vertex newVertex) {
        this.verticesInTheGraph.add(newVertex);
    }

    /**
     * @param sourceVertex
     *
     * initialization phase: Except the source, assign all the other vertices with predecessor values of temporary value
     * and min distance to infinity
     */
    public void initGraphForShortestPathAlgorithm(Vertex sourceVertex) {
         minHeap.add(sourceVertex);
        for (Vertex vertexIndex : this.getVerticesInTheGraph()) {
            if (!vertexIndex.getNameOfTheVertex().equals(sourceVertex.getNameOfTheVertex())) {
                vertexIndex.setPredecessorVertex(tempVertex);
                vertexIndex.setMinimumDistance(Integer.MAX_VALUE);
            }
        }
    }

    /**
     * @param sourceVertex
     * implementing dijkstra's algorithm here
     */
    public void findShortestPath(Vertex sourceVertex) {
        if (!minHeap.isEmpty()) {
            calculateEachNeighbor(sourceVertex);
            findShortestPath(minHeap.remove());
        } else {
            return; 
        }
    }

    private void calculateEachNeighbor(Vertex sourceVertex) {
        //get the neighbors, for each neighbors:
        for (Vertex neighborsVertexIndex : sourceVertex.getNeighborsList()) {
            int currentDistance = getDistance(sourceVertex, neighborsVertexIndex);
            if (currentDistance != -1 && currentDistance < neighborsVertexIndex.getMinimumDistance() && !neighborsVertexIndex.isIsVisited()) {
                minHeap.remove(neighborsVertexIndex);
                neighborsVertexIndex.setMinimumDistance(currentDistance);
                neighborsVertexIndex.setPredecessorVertex(sourceVertex);
                neighborsVertexIndex.setIsVisited(true);
                minHeap.add(neighborsVertexIndex);
            }
        }
    }

    private int getDistance(Vertex sourceVertex, Vertex neighborsVertex) {
        //find the distance from sourceVertex to neighborsVertex
        //sourceVertex has a list of all edges
        for (Edge edgeIndex : sourceVertex.getEdgeList()) {
            if (edgeIndex.getDestinationNode().getNameOfTheVertex().equals(neighborsVertex.getNameOfTheVertex())) {
                return edgeIndex.getWeight();
            }
        }
        return -1;
        //find the edge that is connected to neighborsVertex
        //return the value associated to that edge

    } 
    
    private void printHeap(){
        Iterator iter = minHeap.iterator(); 
        while(iter.hasNext()){
            System.out.print(iter.next()+" ");
        }
        System.out.println();
    } 

    public void printMinDistanceForAllVertices(){
        for(Vertex vertexIndex: this.verticesInTheGraph){
            System.out.println(vertexIndex);
        }
    }
}
