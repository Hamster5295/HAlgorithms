package org.hamster5295.halgorithms.algorithm.astar;

import org.hamster5295.halgorithms.utils.Vector2;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class MapView extends JPanel {

    public final int cellSize = 64, margin = 10, wayPointSize = 30;

    private final RoadMap map;
    private final ArrayList<Node> path = new ArrayList<>();

    public MapView(RoadMap map, Stack<Node> path) {
        super();
        this.map = map;
        while (path.size() > 0) {
            this.path.add(path.pop());
        }
    }

    @Override
    public void paint(Graphics g) {
        renderMap(g);
        renderObstacles(g);
        renderRoute(g);
        renderWayPoints(g);
    }

    private void renderMap(Graphics g) {
        for (int i = 0; i < map.width; i++) {
            for (int j = 0; j < map.height; j++) {
                g.setColor(new Color(0x3C8DC4));
                g.drawRect(margin + (cellSize + margin) * i, margin + (cellSize + margin) * j, cellSize, cellSize);
            }
        }
    }

    private void renderWayPoints(Graphics g) {
        g.setColor(new Color(0x09F768));
        Vector2 startCenter = getCellCenterPos(map.startX, map.startY);
        g.fillRect(startCenter.x - wayPointSize / 2, startCenter.y - wayPointSize / 2, wayPointSize, wayPointSize);

        g.setColor(new Color(0xFF9900));
        Vector2 endCenter = getCellCenterPos(map.endX, map.endY);
        g.fillRect(endCenter.x - wayPointSize / 2, endCenter.y - wayPointSize / 2, wayPointSize, wayPointSize);
    }

    private void renderObstacles(Graphics g) {
        g.setColor(new Color(0xCC8F33));
        for (int i = 0; i < map.width; i++) {
            for (int j = 0; j < map.height; j++) {
                if (map.isObstacle(new Node(i, j))) {
                    Vector2 center = getCellCenterPos(i, j);
                    g.fillRect(center.x - cellSize / 2, center.y - cellSize / 2, cellSize, cellSize);
                }
            }
        }
    }

    private void renderRoute(Graphics g) {
        Vector2 previous = new Vector2(map.startX, map.startY);
        g.setColor(new Color(0x00CCFF));
        for (Node n : path) {


            Vector2 from = getCellCenterPos(previous.x, previous.y), to = getCellCenterPos(n.x, n.y);

            g.drawLine(from.x, from.y, to.x, to.y);
            previous = new Vector2(n.x, n.y);
//            System.out.println(n.toString());
        }
    }

    private Vector2 getCellLeftTopCornerPos(int x, int y) {
        Vector2 pos = new Vector2(0, 0);
        pos.x += margin + x * (cellSize + margin);
        pos.y += margin + y * (cellSize + margin);
        return pos;
    }

    private Vector2 getCellCenterPos(int x, int y) {
        Vector2 pos = getCellLeftTopCornerPos(x, y);
        pos.x += cellSize / 2;
        pos.y += cellSize / 2;
        return pos;
    }

    @Override
    public int getWidth() {
        return map.width * (cellSize + margin) + margin * 2;
    }

    @Override
    public int getHeight() {
        return map.height * (cellSize + margin) + margin * 5;
    }
}
