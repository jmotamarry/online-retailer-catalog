// WeightedGraphNode.java
import java.util.*;

public class WeightedGraphNode {
    private String cityName;
    private Map<WeightedGraphNode, Edge> edges;
    private int distance; // Distance from the start node

    public WeightedGraphNode(String cityName) {
        this.cityName = cityName;
        this.edges = new HashMap<>();
        this.distance = Integer.MAX_VALUE; // Initially set distance to infinity
    }

    public String getCityName() {
        return cityName;
    }

    public Map<WeightedGraphNode, Edge> getEdges() {
        return edges;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    // Method to add an edge to another node
    public void addEdge(WeightedGraphNode neighbor, Edge edge) {
        edges.put(neighbor, edge);
    }

    // Method to remove an edge to another node
    public void removeEdge(WeightedGraphNode neighbor) {
        edges.remove(neighbor);
    }

    // Method to check if there is an edge to a specific node
    public boolean hasEdgeTo(WeightedGraphNode neighbor) {
        return edges.containsKey(neighbor);
    }
}
