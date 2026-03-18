import java.util.*;

public class PathFinder {

    private static class Node {
        int row, col;
        int g, h;
        Node parent;

        Node(int row, int col, int g, int h, Node parent) {
            this.row = row;
            this.col = col;
            this.g = g;
            this.h = h;
            this.parent = parent;
        }

        int f() { return g + h; }
    }

    // Manhattan heuristic
    private static int heuristic(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    // Returns list of [row,col] from start to goal inclusive, or null if none found
    public static List<int[]> findPath(Map map, int sr, int sc, int gr, int gc) {
        PathResult r = findPathWithStats(map, sr, sc, gr, gc);
        return r == null ? null : r.path;
    }

    // New: return path result with metrics
    public static PathResult findPathWithStats(Map map, int sr, int sc, int gr, int gc) {
        if (!map.inBounds(sr, sc) || !map.inBounds(gr, gc)) return null;
        if (!map.canMoveTo(gr, gc)) return null;

        long startTime = System.nanoTime();
        int nodesExpanded = 0;

        // Use a map for g-scores to avoid needing grid sizes
        HashMap<Long, Integer> gScore = new HashMap<>();

        PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingInt((Node n) -> n.f()).thenComparingInt(n -> n.h));

        int h0 = heuristic(sr, sc, gr, gc);
        Node start = new Node(sr, sc, 0, h0, null);
        open.add(start);
        gScore.put(key(sr, sc), 0);

        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

        while (!open.isEmpty()) {
            Node cur = open.poll();
            nodesExpanded++;
            if (cur.row == gr && cur.col == gc) {
                // reconstruct
                List<int[]> path = new ArrayList<>();
                Node p = cur;
                while (p != null) {
                    path.add(new int[]{p.row, p.col});
                    p = p.parent;
                }
                Collections.reverse(path);
                long endTime = System.nanoTime();
                return new PathResult(path, endTime - startTime, nodesExpanded);
            }

            for (int[] d : dirs) {
                int nr = cur.row + d[0];
                int nc = cur.col + d[1];

                if (!map.inBounds(nr, nc)) continue;
                if (!map.canMoveTo(nr, nc)) continue;

                int tentativeG = cur.g + 1;
                long k = key(nr, nc);
                int prevG = gScore.getOrDefault(k, Integer.MAX_VALUE);
                if (tentativeG < prevG) {
                    gScore.put(k, tentativeG);
                    int h = heuristic(nr, nc, gr, gc);
                    Node next = new Node(nr, nc, tentativeG, h, cur);
                    open.add(next);
                }
            }
        }

        long endTime = System.nanoTime();
        return new PathResult(null, endTime - startTime, nodesExpanded);
    }

    private static long key(int r, int c) {
        return (((long)r) << 32) ^ (c & 0xffffffffL);
    }

}
