package org.hamster5295.halgorithms.algorithm.astar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class AStar {
    public static void run(RoadMap map) {
        ArrayList<Node> openList = new ArrayList<>(), closedList = new ArrayList<>();
        Node currentNode;

        openList.add(new Node(map.startX, map.startY));
        currentNode = openList.get(0);

        while (!map.isEnd(currentNode)) {
            List<Node> nodes = map.getSurroundingNodes(currentNode);
            for (Node n : nodes) {
                if (map.isObstacle(n) || closedList.contains(n)) {
                    continue;
                } else if (openList.contains(n)) {
                    if (n.parent != null)
                        if (n.travelledCost() > currentNode.travelledCost() + currentNode.distance(n)) {
                            n.parent = currentNode;
                        }
                } else {
                    n.parent = currentNode;
                    openList.add(n);
                }
            }
            openList.remove(currentNode);
            closedList.add(currentNode);

            if (openList.size() > 0) {
                Node temp = openList.get(0);
                for (Node n : openList) {
                    if (map.totalCost(n) < map.totalCost(temp)) temp = n;
                }
                currentNode = temp;
            } else {
                System.out.println("没有路径!");
                return;
            }

        }

        ArrayList<Node> paths = new ArrayList<>();


        Stack<Node> stack = new Stack<>();
        while (currentNode.parent != null) {
            stack.push(currentNode);
            currentNode = currentNode.parent;
        }

//        System.out.println("路径:");
//        while (stack.size() > 0) {
//            System.out.println(stack.pop().toString());
//        }

        JFrame frame = new JFrame("A*寻路");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container c = frame.getContentPane();
        MapView m = new MapView(map, stack);
        c.add(m);
        frame.setBounds(100, 100, m.getWidth(), m.getHeight());
        frame.setVisible(true);
    }
}

class Node {
    public int x, y;
    public Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public float distance(Node node) {
        return distance(node.x, node.y);
    }

    public float distance(int targetX, int targetY) {
        int deltaX = Math.abs(targetX - x), deltaY = Math.abs(targetY - y);
        return 1.4f * Math.min(deltaX, deltaY) + Math.abs(deltaX - deltaY);
    }

    public float travelledCost() {
        Node current = this, temp = this;
        float cost = 0;
        while (temp.parent != null) {
            temp = temp.parent;
            cost += current.distance(temp);
            current = current.parent;
        }

        return cost;
    }

    @Override
    public String toString() {
        return "x = " + x + ", y = " + y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node)) return false;
        Node o = (Node) obj;
        return x == o.x && y == o.y;
    }

    //    public float totalCost(Node start, Node end) {
//        return distance(start) + distance(end);
//    }
//
//    public float totalCost(int startX, int startY, int endX, int endY) {
//        return distance(startX, startY) + distance(endX, endY);
//    }
}
