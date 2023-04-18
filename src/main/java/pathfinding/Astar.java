package pathfinding;
/*
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Astar {

    // A helper class to represent a node in the search graph
    private static class Node implements Comparable<Node> {
        public int x;
        public int y;
        public int g; // cost to reach this node from the start
        public int h; // heuristic estimate of cost to reach the goal
        public Node parent; // parent node in the search tree

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.g = Integer.MAX_VALUE;
            this.h = 0;
            this.parent = null;
        }

        // The total estimated cost of the node (f = g + h)
        public int f() {
            return g + h;
        }

        // Compare nodes based on their f value
        public int compareTo(Node other) {
            return Integer.compare(this.f(), other.f());
        }
    }

    public static List<int[]> findPath(int startX, int startY, int goalX, int goalY, int[][] network, HeuristicFunction heuristic) {
        // Create the start and goal nodes
        Node startNode = new Node(startX, startY);
        Node goalNode = new Node(goalX, goalY);

        // Initialize the open and closed sets
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        List<Node> closedSet = new ArrayList<>();
        openSet.add(startNode);

        // Initialize the cost to reach the start node to 0
        startNode.g = 0;

        // Continue searching until the open set is empty or the goal is found
        while (!openSet.isEmpty()) {
            // Get the node with the lowest f value from the open set
            Node currentNode = openSet.poll();

            // If the goal node has been reached, construct and return the path
            if (currentNode.x == goalNode.x && currentNode.y == goalNode.y) {
                List<int[]> path = new ArrayList<>();
                Node node = currentNode;
                while (node != null) {
                    path.add(new int[]{node.x, node.y});
                    node = node.parent;
                }
                Collections.reverse(path);
                return path;
            }

            // Generate the neighbors of the current node
            List<Node> neighbors = new ArrayList<>();
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    // Skip the current node and diagonals
                    if ((dx == 0 && dy == 0) || (dx != 0 && dy != 0)) {
                        continue;
                    }
                    int x = currentNode.x + dx;
                    int y = currentNode.y + dy;
                    // Skip nodes outside the bounds of the network
                    if (x < 0 || x >= network.length || y < 0 || y >= network[0].length) {
                        continue;
                    }
                    // Skip nodes that are walls
                    if (network[x][y] == WALL) {
                        continue;
                    }
                    // Create a new node for the neighbor
                    Node neighbor = new Node(x, y);
                    neighbors.add(neighbor);
                }
            }

            // Update the cost to reach each neighbor
            for (Node neighbor : neighbors) {
                int tentativeG = currentNode.g + 1;
                if (tentativeG < neighbor.g) {
                    // This path to the neighbor is
                }
            }
        }
        return null;
    }
}




public void moveInCol(PathField currentField, int end) {

        while (!(end == currentField.getCol())) {
        PathField previousField = currentField;
        if (end > currentField.getCol()) {
        maze.pacman().move(CommonField.Direction.R);
        } else if (end < currentField.getCol()) {
        maze.pacman().move(CommonField.Direction.L);
        }

        if (maze.getField(currentField.getCol(), currentField.getRow()) instanceof WallField) {
        currentField = previousField;
        break;
        }
        }

        }

        scene.setOnMousePressed(event -> {
        Node clickedNode = event.getPickResult().getIntersectedNode();
        Integer colIndex = GridPane.getColumnIndex(clickedNode);
        Integer rowIndex = GridPane.getRowIndex(clickedNode);
        System.out.println(colIndex + ":" + rowIndex);

        // todo astar

        PathField currentField = (PathField) maze.pacman().getField();
        // Field.FieldNode currentField = (Field.FieldNode) maze.pacman().getField();
        // AStarAlgorithm astar = new AStarAlgorithm((Network) maze);
        // astar.setStart(currentField);
        // astar.setEnd((Field.FieldNode) maze.getField(rowIndex,colIndex));
        // astar.solve();

            /*
            while (!(colIndex == currentField.getCol() && rowIndex == currentField.getRow())) {
                if (maze.getField(currentField.getCol(), currentField.getRow()) instanceof WallField) {
                    break;
                }   // todo search algoritmus
                if (colIndex > currentField.getCol()) {
                    maze.pacman().move(CommonField.Direction.R);
                }
                else if (colIndex < currentField.getCol()) {
                    maze.pacman().move(CommonField.Direction.L);
                }


                else if (rowIndex > currentField.getRow()) {
                    maze.pacman().move(CommonField.Direction.D);
                }
                else if (rowIndex < currentField.getRow()) {
                    maze.pacman().move(CommonField.Direction.U);
                }
                currentField = (PathField) maze.pacman().getField();
                try {
                    start(primaryStage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


        });
*/
