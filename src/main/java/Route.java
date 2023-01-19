import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Route {
    private List<Road> roads = new ArrayList();
    private Set<Address> addresses = new HashSet<>();

    private Integer distanceCache = 0;

    public int getDistance() {
        if (distanceCache == null) {
            distanceCache = calculateDistance();
        }
        return distanceCache;
    }
    private int calculateDistance() {
        int result = 0;
        for (Road road : roads) {
            result += road.getDistance();
        }
        return result;
    }

    public void addRoad(Road road) {
        roads.add(road);
        addresses.add(road.getFrom());
        addresses.add(road.getTo());
        distanceCache = null;
    }

    public boolean contains(Address address) {
        return addresses.contains(address);
    }

    public boolean equals(Object another) {
        if (another instanceof Route) {
            Route other = (Route) another;
            if (other.roads.size() == roads.size()) {
                for (int i = 0; i < roads.size(); i++) {
                    if (!other.roads.get(i).equals(roads.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public int hashcode() {
        int result = 1;
        for (int i = 0; i < roads.size(); i++) {
            result += i * roads.get(i).hashCode();
        }
        return result;
    }

    public boolean endsAt(Address address) {
        return !roads.isEmpty() &&
                roads.get(roads.size() - 1).getTo().equals(address);
    }

    public Route createRouteTo(Address end) {
        Route result = new Route();
        for (Road road : roads) {
            if (road.getFrom().equals(end)) break;
            result.addRoad(road);
        }
        return result;
    }
}
