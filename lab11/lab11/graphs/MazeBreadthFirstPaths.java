package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /**
     * Conducts a breadth first search of the maze starting at the source.
     */
    private void bfs(int s) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        marked[s] = true;
        announce();

        while (!q.isEmpty()) {
            int u = q.remove();
            if (u == t) {
                targetFound = true;
                return;
            }
            for (int v : maze.adj(u)) {
                if (!marked[v]) {
                    q.add(v);
                    marked[v] = true;
                    edgeTo[v] = u;
                    distTo[v] = distTo[u] + 1;
                    announce();
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs(s);
    }
}

