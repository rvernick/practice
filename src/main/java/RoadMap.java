import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RoadMap {
    Map<Address, Set<Road>> fromMap = new HashMap<Address, Set<Road>>();
    Map<Address, Set<Road>> toMap = new HashMap<Address, Set<Road>>();

    public void addRoad(int fromX, int fromY, int toX, int toY, int dist) {
        Address from = new Address(fromX, fromY);
        Address to = new Address(toX, toY);
        Road road = new Road(from, to, dist);
        Road roundTrip = new Road(to, from, dist);
        addRoad(road);
        addRoad(roundTrip);
    }

    public void addRoad(Road road) {
        if (!fromMap.containsKey(road.getFrom())) {
            fromMap.put(road.getFrom(), new HashSet<>());
        }
        if (!toMap.containsKey(road.getTo())) {
            toMap.put(road.getTo(), new HashSet<>());
        }
        fromMap.get(road.getFrom()).add(road);
        toMap.get(road.getTo()).add(road);
    }

    public Set<Road> getFrom(Address from) {
        if (fromMap.containsKey(from)) return fromMap.get(from);
        return new HashSet<>();
    }
}
