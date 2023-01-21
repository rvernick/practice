package dijkstra;

public class Road {
    private Address from;
    private Address to;
    private int distance;

    public Road(Address from, Address to, int dist) {
        this.from = from;
        this.to = to;
        this.distance = dist;
    }

    public Address getFrom() {
        return from;
    }
    public Address getTo() {
        return to;
    }
    public Integer getDistance() {
        return distance;
    }

    public String toString() {
        return "dijkstra.Road " + from + " " + to + " " + distance;
    }
}
