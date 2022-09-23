package org.hamster5295.halgorithms.main;

import org.hamster5295.halgorithms.algorithm.astar.AStar;
import org.hamster5295.halgorithms.algorithm.astar.RoadMap;

public class Main {
    public static void main(String[] args) {
        AStar.run(new RoadMap(8, 5).startPoint(0, 0).endPoint(7, 4)
                .obstacle(6, 3)
                .obstacle(6, 2)
                .obstacle(6, 4)
//                .obstacle(4, 1)
                .obstacle(6, 1));
//                .obstacle(3, 0)
//                .obstacle(1, 2)
//                .obstacle(1, 1)
//                .obstacle(1, 0)
//                .obstacle(5, 2));
    }
}