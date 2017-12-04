package myalgorithm;

/**
 * Created by John on 4/12/2017.
 */

class Vertex implements Comparable<Vertex>
{
    public final String name;
    public Edge[] adjacencies;
    public double minCost = Double.POSITIVE_INFINITY;
    public double minTime = Double.POSITIVE_INFINITY;
    public String mode = "";
    public double budget = 30;
    public Vertex previous;
    public Vertex(String argName) { name = argName; }
    public String toString() { return name; }
    public int compareTo(Vertex other)
    {
        return Double.compare(minTime, other.minTime);
    }

}
