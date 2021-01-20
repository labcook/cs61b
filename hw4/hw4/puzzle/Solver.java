package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Solver {
    private class Node {
        private WorldState nowWorld;
        private int nowSteps;
        private Integer priority;
        private Node lastWorld;

        Node(WorldState nowWorld, Node lastWorld) {
            this.nowWorld = nowWorld;
            this.nowSteps = lastWorld == null ? 0 : lastWorld.nowSteps + 1;
            if (edCaches.containsKey(this.nowWorld)) {
                int ed = edCaches.get(this.nowWorld);
                this.priority = this.nowSteps + ed;
            } else {
                int ed = this.nowWorld.estimatedDistanceToGoal();
                edCaches.put(this.nowWorld, ed);
                this.priority = this.nowSteps + ed;
            }
            this.lastWorld = lastWorld;
        }
    }

    private class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.priority.compareTo(o2.priority);
        }
    }

    private Map<WorldState, Integer> edCaches = new HashMap<>();
    private int steps;
    private Stack<WorldState> path = new Stack<>();


    public Solver(WorldState initial) {
        MinPQ<Node> pq = new MinPQ<>(new NodeComparator());
        Node current = new Node(initial, null);

        while (!current.nowWorld.isGoal()) {
            for (WorldState nextWorld : current.nowWorld.neighbors()) {
                if (current.lastWorld == null || !nextWorld.equals(current.lastWorld.nowWorld)) {
                    pq.insert(new Node(nextWorld, current));
                    steps += 1;
                }
            }
            current = pq.delMin();
        }

        for (Node node = current; node != null; node = node.lastWorld) {
            path.push(node.nowWorld);
        }

    }

    public int moves() {
        return path.size() - 1;
    }

    public Iterable<WorldState> solution() {
        return path;
    }

}
