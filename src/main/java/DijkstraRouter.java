import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DijkstraRouter {
    private RoadMap roadMap;
    private Address start;
    private Address end;

    private Map<Address, Route> routeForAddress = new HashMap<>();
    private Map<Address, Integer> distanceTo = new HashMap<>();
    private Set<Address> startingAddresses = new HashSet<>();

    public DijkstraRouter(RoadMap aNetwork, Address start, Address end) {
        this.roadMap = aNetwork;
        this.start = start;
        this.end = end;
    }

    public Route shortestRoute() {
        while (!haveArrived()) {
            Road next = findNextRoad();
            if (next == null) return new Route();
            addStep(next);
        }
        return routeForAddress.get(end);
    }
    
    private boolean haveArrived() {
        return !routeForAddress.isEmpty() &&
                routeForAddress.containsKey(end);
    }

    private void addStep(Road road) {
        if (routeForAddress.isEmpty()) {
            addFirstRoute(road);
            return;
        }
        Route route = getRouteFor(road.getFrom());
        appendRoadToRoute(road, route);
    }

    private void addFirstRoute(Road road) {
        Route startingPoint = new Route();
        startingPoint.addRoad(new Road(start, start, 0));
        routeForAddress.put(start, startingPoint);
        distanceTo.put(start, 0);
        startingAddresses.add(start);

        appendRoadToRoute(road, new Route());
    }

    private Route getRouteFor(Address from) {
        Route route = routeForAddress.get(from);
        if (route.endsAt(from)) {
            return route;
        }
        return route.createRouteTo(from);
    }

    private void appendRoadToRoute(Road road, Route route) {
        Address from = road.getFrom();
        Address to = road.getTo();
        route.addRoad(road);
        routeForAddress.put(to, route);
        distanceTo.put(to, distanceTo.get(from) + road.getDistance());
        startingAddresses.add(to);
    }

    private Road findNextRoad() {
        if (routeForAddress.isEmpty()) {
            if (roadMap.getFrom(start).isEmpty()) {
                return null;
            }
            return roadMap.getFrom(start).stream().findAny().get();
        }

        int cheapestStep = 0;
        Road nextRoad = null;
        Set<Address> invalidStartingSpots = new HashSet<>();
        for (Address startingSpot : startingAddresses) {
            invalidStartingSpots.add(startingSpot);
            for (Road possible : roadMap.getFrom(startingSpot)) {
                if (!distanceTo.containsKey(possible.getTo())) {
                    invalidStartingSpots.remove(startingSpot);
                    if (nextRoad == null) {
                        nextRoad = possible;
                        cheapestStep = distanceTo.get(startingSpot) + possible.getDistance();
                        continue;
                    }
                    int candidateDistance = distanceTo.get(startingSpot) + possible.getDistance();
                    if (candidateDistance < cheapestStep) {
                        cheapestStep = candidateDistance;
                        nextRoad = possible;
                    }
                }
            }
        }
        startingAddresses.removeAll(invalidStartingSpots);
        return nextRoad;
    }

}
