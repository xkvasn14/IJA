package pathfinding;

import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Pathfinding {

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    // Define the Field class
    static class Field {
        int x, y;
        static boolean WALL;

        Field(int x, int y, boolean WALL) {
            this.x = x;
            this.y = y;
            Field.WALL = WALL;
        }
    }

    static class Node implements Comparable<Node> {
        int x, y;
        int g, h;
        Node parent;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int f() {
            return g + h;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.f(), o.f());
        }
    }

    public static List<Direction> findPath(Field[][] network, int startX, int startY, int goalX, int goalY) {
        List<Direction> directions = new ArrayList<>();
        PriorityQueue<Node> openList = new PriorityQueue<>();
        List<Node> closedList = new ArrayList<>();

        Node startNode = new Node(startX, startY);
        Node goalNode = new Node(goalX, goalY);

        startNode.g = 0;
        startNode.h = heuristic(startNode, goalNode);

        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();

            if (currentNode.x == goalX && currentNode.y == goalY) {
                // Found goal node
                while (currentNode.parent != null) {
                    int dx = currentNode.x - currentNode.parent.x;
                    int dy = currentNode.y - currentNode.parent.y;

                    if (dx == -1) {
                        directions.add(Direction.LEFT);
                    } else if (dx == 1) {
                        directions.add(Direction.RIGHT);
                    } else if (dy == -1) {
                        directions.add(Direction.UP);
                    } else if (dy == 1) {
                        directions.add(Direction.DOWN);
                    }

                    currentNode = currentNode.parent;
                }

                Collections.reverse(directions);
                return directions;
            }

            closedList.add(currentNode);

            for (Node neighbor : getNeighbors(currentNode, network)) {
                if (closedList.contains(neighbor)) {
                    continue;
                }

                int tentativeGScore = currentNode.g + 1;

                if (!openList.contains(neighbor) || tentativeGScore < neighbor.g) {
                    neighbor.parent = currentNode;
                    neighbor.g = tentativeGScore;
                    neighbor.h = heuristic(neighbor, goalNode);

                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                    }
                }
            }
        }

        // No path found
        return null;
    }

    private static List<Node> getNeighbors(Node node, Field[][] network) {
        List<Node> neighbors = new ArrayList<>();

        int x = node.x;
        int y = node.y;

        if (x > 0 && network[x - 1][y].WALL) {
            neighbors.add(new Node(x - 1, y));
        }
        if (x < network.length - 1 && network[x + 1][y].WALL) {
            neighbors.add(new Node(x + 1, y));
        }
        if (y > 0 && network[x][y - 1].WALL) {
            neighbors.add(new Node(x, y - 1));
        }
        if (y < network[0].length - 1 && network[x][y + 1].WALL) {
            neighbors.add(new Node(x, y + 1));
        }

        return neighbors;
    }

    private static int heuristic(Node node1, Node node2) {
        return Math.abs(node1.x - node2.x) + Math.abs(node1.y - node2.y);
    }

}