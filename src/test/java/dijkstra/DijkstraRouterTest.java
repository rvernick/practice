package dijkstra;

import dijkstra.DijkstraRouter;
import dijkstra.RoadMap;
import dijkstra.Route;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraRouterTest {

    @Test
    public void testToSamePlace() {
        DijkstraRouter router = new DijkstraRouter(new RoadMap(), new Address(0,0), new Address(0,0));
        Route route = router.shortestRoute();
        assertEquals(0, route.getDistance());
    }

    @Test
    public void testSinglePathRoute() {
        RoadMap roadMap = new RoadMap();
        roadMap.addRoad(0,0, 1,0, 3);
        Address start = new Address(0,0);
        Address end = new Address(1, 0);
        DijkstraRouter router = new DijkstraRouter(roadMap, start, end);
        Route route = router.shortestRoute();
        assertEquals(3, route.getDistance());
    }

    /**  dijkstra.RoadMap
     *   00 1 10 4 20   30
     *   1          1
     *   01 1 11 1 21 1 31
     *        1          5
     *   02   12 1 22 1 32
     *                   1
     *   03   13   23   33
     *
     */
    @Test
    public void testComplexPathRoute() {
        RoadMap roadMap = new RoadMap();
        Address start = new Address(0,0);
        Address end = new Address(3, 3);
        roadMap.addRoad(0,0, 1,0, 1);
        roadMap.addRoad(1,0, 2,0, 4);
        roadMap.addRoad(2,0, 2,1, 1);
        roadMap.addRoad(2,1, 3,1, 1);
        roadMap.addRoad(3,1, 3,2, 5);
        roadMap.addRoad(3,2, 3,3, 1);

        roadMap.addRoad(0,0, 0,1, 1);
        roadMap.addRoad(0,1, 1,1, 1);
        roadMap.addRoad(1,1, 1,2, 1);
        roadMap.addRoad(1,2, 2,2, 1);
        roadMap.addRoad(2,2, 3,2, 1);
        roadMap.addRoad(3,2, 3,3, 1);

        DijkstraRouter router = new DijkstraRouter(roadMap, start, end);
        Route route = router.shortestRoute();
        assertEquals(6, route.getDistance());
    }

    /**  dijkstra.RoadMap
     *   00 1 10 4 20   30
     *        1    1
     *   01 1 11 1 21 1 31
     *        1          5
     *   02   12 1 22 1 32
     *                   1
     *   03   13   23   33
     *
     */
    @Test
    public void testComplexPathRouteSingleStartRoad() {
        RoadMap roadMap = new RoadMap();
        Address start = new Address(0,0);
        Address end = new Address(3, 3);
        roadMap.addRoad(0,0, 1,0, 1);
        roadMap.addRoad(1,0, 2,0, 4);
        roadMap.addRoad(2,0, 2,1, 1);
        roadMap.addRoad(2,1, 3,1, 1);
        roadMap.addRoad(3,1, 3,2, 5);
        roadMap.addRoad(3,2, 3,3, 1);

        roadMap.addRoad(1,0, 1,1, 1);
        roadMap.addRoad(0,1, 1,1, 1);
        roadMap.addRoad(1,1, 1,2, 1);
        roadMap.addRoad(1,2, 2,2, 1);
        roadMap.addRoad(2,2, 3,2, 1);
        roadMap.addRoad(3,2, 3,3, 1);

        DijkstraRouter router = new DijkstraRouter(roadMap, start, end);
        Route route = router.shortestRoute();
        assertEquals(6, route.getDistance());
    }
}