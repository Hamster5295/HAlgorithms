package org.hamster5295.halgorithms.algorithm.astar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoadMap {
    private boolean map[][];

    public int height, width;
    public int startX, startY, endX, endY;

    public RoadMap(int width, int height) {
        this.width = width;
        this.height = height;

        map = new boolean[width][height];
        for (boolean[] booleans : map) {
            Arrays.fill(booleans, false);
        }
    }

    public RoadMap startPoint(int x, int y) {
        startX = x;
        startY = y;
        return this;
    }

    public RoadMap endPoint(int x, int y) {
        endX = x;
        endY = y;
        return this;
    }

    public RoadMap obstacle(int x, int y) {
        map[x][y] = true;
        return this;
    }

    public boolean isEnd(Node n) {
        return n.x == endX && n.y == endY;
    }

    public boolean isObstacle(int x, int y) {
        return map[x][y];
    }

    public boolean isObstacle(Node n) {
        return map[n.x][n.y];
    }

    public List<Node> getSurroundingNodes(Node n) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((i != 0 || j != 0) && n.x + i >= 0 && n.x + i < width && n.y + j >= 0 && n.y + j < height) {

                    if (isObstacle(n.x, n.y+ j) && isObstacle(n.x+i, n.y)) continue;
                    nodes.add(new Node(n.x + i, n.y + j));
                }
            }
        }

        return nodes;
    }

    public float totalCost(Node n) {
        return n.travelledCost() + n.distance(endX, endY);
    }
}