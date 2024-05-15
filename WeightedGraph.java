// WeightedGraph.java
import java.util.*;

public class WeightedGraph {
    private Map<String, WeightedGraphNode> nodes;

    public WeightedGraph() {
        nodes = new HashMap<>();
    }

    // Method to add a new node to the graph
    public void addNode(WeightedGraphNode node) {
        if (!nodes.containsKey(node.getCityName().toLowerCase())) {
            nodes.put(node.getCityName().toLowerCase(), node);
        }
    }

    // Method to remove a node from the graph
    public void removeNode(WeightedGraphNode node) {
        nodes.remove(node.getCityName().toLowerCase());
    }

    // Method to add an edge between two nodes with a specified weight
    public void addEdge(WeightedGraphNode source, WeightedGraphNode destination, int days) {
        String sourceCity = source.getCityName().toLowerCase();
        String destinationCity = destination.getCityName().toLowerCase();
        
        // Check if the nodes exist in the graph
        if (nodes.containsKey(sourceCity) && nodes.containsKey(destinationCity)) {
            source = nodes.get(sourceCity);
            destination = nodes.get(destinationCity);
            
            if (!source.equals(destination) && !source.hasEdgeTo(destination)) {
                Edge edge = new Edge(days);
                source.addEdge(destination, edge);
            }
        }
    }

    // Method to remove an edge between two nodes
    public void removeEdge(WeightedGraphNode source, WeightedGraphNode destination) {
        if (source.hasEdgeTo(destination)) {
            source.removeEdge(destination);
        }
    }

    // Dijkstra's shortest path algorithm using priority queue
    public List<WeightedGraphNode> shortestPath(String startCity, String endCity) {
        startCity = startCity.toLowerCase(); // Convert to lower case
        endCity = endCity.toLowerCase(); // Convert to lower case

        if (!nodes.containsKey(startCity) || !nodes.containsKey(endCity)) {
            return null; // If start or end city is not in the graph, return null
        }

        Map<WeightedGraphNode, Long> distances = new HashMap<>();
        PriorityQueue<WeightedGraphNode> pq = new PriorityQueue<>(Comparator.comparingLong(node -> distances.get(node)));
        Map<WeightedGraphNode, WeightedGraphNode> previousNodes = new HashMap<>();

        WeightedGraphNode startNode = nodes.get(startCity);
        WeightedGraphNode endNode = nodes.get(endCity);

        // Initialize distances
        for (WeightedGraphNode node : nodes.values()) {
            if (node.equals(startNode)) {
                distances.put(node, 0L); // Use 0L for long type
            } else {
                distances.put(node, Long.MAX_VALUE / 2); // Use a value close to Long.MAX_VALUE
            }
            pq.add(node);
        }

        boolean endNodeFound = false; // Flag to track if end node is found

        while (!pq.isEmpty()) {
            WeightedGraphNode current = pq.poll();
            if (current.equals(endNode)) {
                endNodeFound = true;
            }
            if (endNodeFound && current.equals(endNode)) {
                break; // Found the shortest path to end node
            }
            for (Map.Entry<WeightedGraphNode, Edge> neighborEntry : current.getEdges().entrySet()) {
                WeightedGraphNode neighbor = neighborEntry.getKey();
                int weight = neighborEntry.getValue().getDays();
                long altDistance = distances.get(current) + weight; // Use long for altDistance
                if (altDistance < distances.get(neighbor)) {
                    distances.put(neighbor, altDistance);
                    previousNodes.put(neighbor, current);
                    pq.remove(neighbor);
                    pq.add(neighbor);
                }
            }
        }

        // Check if there is a path from startCity to endCity
        if (distances.get(endNode) == Long.MAX_VALUE / 2) {
            return null; // No path exists
        }

        // Reconstruct the shortest path
        List<WeightedGraphNode> path = new ArrayList<>();
        WeightedGraphNode currentNode = endNode;
        while (currentNode != null) {
            path.add(currentNode);
            currentNode = previousNodes.get(currentNode);
        }
        Collections.reverse(path);
        path.add(new WeightedGraphNode(String.valueOf(distances.get(endNode))));
        return path;
    }


    public void printGraph() {
        for (WeightedGraphNode node : nodes.values()) {
            System.out.print(node.getCityName() + ": ");
            for (Map.Entry<WeightedGraphNode, Edge> entry : node.getEdges().entrySet()) {
                WeightedGraphNode neighbor = entry.getKey();
                Edge edge = entry.getValue();
                System.out.print("(" + neighbor.getCityName() + ", " + edge.getDays() + ") ");
            }
            System.out.println();
        }
    }

}
