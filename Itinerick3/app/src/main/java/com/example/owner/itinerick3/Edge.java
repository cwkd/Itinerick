package myalgorithm;

/**
 * Created by John on 4/12/2017.
 */

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
