package com.example;


import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Vertex implements Comparable<Vertex>
{
    public final String name;
    public Edge[] adjacencies;
    public double minCost = Double.POSITIVE_INFINITY;
    public double minTime = Double.POSITIVE_INFINITY;
    public String mode = "";
    public Vertex previous;
    public Vertex(String argName) { name = argName; }
    public String toString() { return name; }
    public int compareTo(Vertex other)
    {
        return Double.compare(minTime, other.minTime);
    }

}


class Edge
{
    public final Vertex target;
    public final double time;
    public final double cost;
    public final String mode;
    public Edge(Vertex argTarget, double argTime, double argCost, String argMode) {
        target = argTarget;
        time = argTime;
        cost = argCost;
        mode = argMode;
    }
}

public class MyClass
{
    public static void computePaths(Vertex source)
    {
        source.minTime = 0.;
        source.minCost = 0.;
        source.mode = "";
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies)
            {
                Vertex v = e.target;
                double cost = e.cost;
                double time = e.time;
                String mode = e.mode;
                double timeThroughU = u.minTime + time;
                double costThroughU = u.minCost + cost;
                if (timeThroughU < v.minTime) {
                    if (costThroughU<v.minCost){
                        vertexQueue.remove(v);
                        v.minCost = costThroughU;
                        v.minTime = timeThroughU;
                        v.mode = mode;
                        v.previous = u;
                        vertexQueue.add(v);
                    }
                }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args)
    {
        Vertex v0 = new Vertex("Marina Bay Sands");
        Vertex v1 = new Vertex("Singapore Flyer");
        Vertex v2 = new Vertex("Vivo City");
        Vertex v3 = new Vertex("Resorts World Sentosa");
        Vertex v4 = new Vertex("Buddha Tooth Temple");
        Vertex v5 = new Vertex("Zoo");
        v0.adjacencies = new Edge[]{ new Edge(v1,17,0.83,"Public Transport"), new Edge(v2,26,1.18,"Public Transport"), new Edge(v3,35,4.03,"Public Transport"),
                         new Edge(v4,19,0.88,"Public Transport"), new Edge(v5,84,1.96,"Public Transport"), new Edge(v1,3,3.22,"Taxi"), new Edge(v2,14,6.96,"Taxi"),
                         new Edge(v3,19,8.50,"Taxi"), new Edge(v4,8,4.98,"Taxi"), new Edge(v5,30,18.40,"Taxi"), new Edge(v1,14,0,"Foot"), new Edge(v2,69,0,"Foot"),
                         new Edge(v3,76,0,"Foot"), new Edge(v4,28,0,"Foot"), new Edge(v5,269,0,"Foot") };

        v1.adjacencies = new Edge[]{ new Edge(v0,17,0.83,"Public Transport"), new Edge(v2,31,1.26,"Public Transport"), new Edge(v3,38,4.03,"Public Transport"),
                         new Edge(v4,24,0.98,"Public Transport"), new Edge(v5,85,1.89,"Public Transport"), new Edge(v0,6,4.32,"Taxi"), new Edge(v2,13,7.84,"Taxi"),
                         new Edge(v3,18,9.38,"Taxi"), new Edge(v4,8,4.76,"Taxi"), new Edge(v5,29,18.18,"Taxi"), new Edge(v0,14,0,"Foot"), new Edge(v2,81,0,"Foot"),
                         new Edge(v3,88,0,"Foot"), new Edge(v4,39,0,"Foot"), new Edge(v5,264,0,"Foot")};

        v2.adjacencies = new Edge[]{ new Edge(v0,24,1.18,"Public Transport"), new Edge(v1,29,1.26,"Public Transport"), new Edge(v3,10,2.00,"Public Transport"),
                         new Edge(v4,18,0.98,"Public Transport"), new Edge(v5,85,1.99,"Public Transport"), new Edge(v0,12,8.30,"Taxi"), new Edge(v1,14,7.96,"Taxi"),
                         new Edge(v3,9,4.54,"Taxi"), new Edge(v4,11,6.42,"Taxi"), new Edge(v5,31,22.58,"Taxi"), new Edge(v0,69,0,"Foot"), new Edge(v1,81,0,"Foot"),
                         new Edge(v3,12,0,"Foot"), new Edge(v4,47,0,"Foot"), new Edge(v5,270,0,"Foot")};

        v3.adjacencies = new Edge[]{ new Edge(v0,33,1.18,"Public Transport"), new Edge(v1,38,1.26,"Public Transport"), new Edge(v2,10,0.00,"Public Transport"),
                         new Edge(v4,27,1.99,"Public Transport"), new Edge(v5,92,1.99,"Public Transport"), new Edge(v0,13,8.74,"Taxi"), new Edge(v1,14,8.40,"Taxi"),
                         new Edge(v2,4,3.22,"Taxi"), new Edge(v4,12,6.64,"Taxi"), new Edge(v5,32,22.80,"Taxi"), new Edge(v0,76,0,"Foot"), new Edge(v1,88,0,"Foot"),
                         new Edge(v2,12,0,"Foot"), new Edge(v4,55,0,"Foot"), new Edge(v5,285,0,"Foot"),};

        v4.adjacencies = new Edge[]{ new Edge(v0,18,0.88,"Public Transport"), new Edge(v1,23,0.98,"Public Transport"), new Edge(v2,19,0.98,"Public Transport"),
                         new Edge(v3,28,3.98,"Public Transport"), new Edge(v5,83,1.91,"Public Transport"), new Edge(v0,7,5.32,"Taxi"), new Edge(v1,8,4.76,"Taxi"),
                         new Edge(v2,9,4.98,"Taxi"), new Edge(v3,14,6.52,"Taxi"), new Edge(v5,30,18.40,"Taxi"), new Edge(v0,28,0,"Foot"), new Edge(v1,39,0,"Foot"),
                         new Edge(v2,47,0,"Foot"), new Edge(v3,55,0,"Foot"), new Edge(v5,264,0,"Foot")};

        v5.adjacencies = new Edge[]{ new Edge(v0,86,1.88,"Public Transport"), new Edge(v1,87,1.96,"Public Transport"), new Edge(v2,86,2.11,"Public Transport"),
                         new Edge(v3,96,4.99,"Public Transport"), new Edge(v4,84,1.91,"Public Transport"), new Edge(v0,32,22.48,"Taxi"), new Edge(v1,29,19.40,"Taxi"),
                         new Edge(v2,32,21.48,"Taxi"), new Edge(v3,36,23.68,"Taxi"), new Edge(v4,30,21.60,"Taxi"), new Edge(v0,269,0,"Foot"), new Edge(v1,264,0,"Foot"),
                         new Edge(v2,270,0,"Foot"), new Edge(v3,285,0,"Foot"), new Edge(v4,264,0,"Foot")};

        Vertex[] vertices = { v0, v1, v2, v3, v4,v5};

        computePaths(v0);
        for (Vertex v : vertices)
        {
            System.out.println("Time to " + v + ": " + v.minTime);
            System.out.println("Cost to " + v + ": " + v.minCost);
            System.out.println("Mode of Transport " + v + ": " + v.mode);
            List<Vertex> path = getShortestPathTo(v);
            System.out.println("Path: " + path);
        }
    }
}