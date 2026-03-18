import java.util.*;

public class BFSPathFinder {

    // Returns list of [row,col] from start to goal inclusive, or null if none found
    public static List<int[]> findPath(Map map, int sr, int sc, int gr, int gc) {
        PathResult pr = findPathWithStats(map, sr, sc, gr, gc);
        return pr == null ? null : pr.path;
    }

    // New: returns PathResult with metrics
    public static PathResult findPathWithStats(Map map, int sr, int sc, int gr, int gc) {
        if (!map.inBounds(sr, sc) || !map.inBounds(gr, gc)) return null;
        if (!map.canMoveTo(gr, gc)) return null;

        long startTime = System.nanoTime();
        int nodesExpanded = 0;

        Queue<long[]> q = new ArrayDeque<>();
        HashSet<Long> visited = new HashSet<>();
        HashMap<Long, Long> parent = new HashMap<>();

        long startKey = key(sr, sc);
        long goalKey = key(gr, gc);
        q.add(new long[]{sr, sc});
        visited.add(startKey);

        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

        boolean found = false;

        while (!q.isEmpty()) {
            long[] cur = q.remove();
            nodesExpanded++;
            int r = (int)cur[0];
            int c = (int)cur[1];
            long ck = key(r, c);
            if (ck == goalKey) { found = true; break; }

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (!map.inBounds(nr, nc)) continue;
                if (!map.canMoveTo(nr, nc)) continue;
                long nk = key(nr, nc);
                if (visited.contains(nk)) continue;
                visited.add(nk);
                parent.put(nk, ck);
                q.add(new long[]{nr, nc});
            }
        }

        if (!found) {
            long endTime = System.nanoTime();
            return new PathResult(null, endTime - startTime, nodesExpanded);
        }

        // reconstruct path
        List<int[]> path = new ArrayList<>();
        long cur = goalKey;
        while (true) {
            int rr = (int)(cur >> 32);
            int cc = (int)(cur & 0xffffffffL);
            path.add(new int[]{rr, cc});
            if (cur == startKey) break;
            Long p = parent.get(cur);
            if (p == null) break; // shouldn't happen
            cur = p;
        }
        Collections.reverse(path);
        long endTime = System.nanoTime();
        return new PathResult(path, endTime - startTime, nodesExpanded);
    }

    private static long key(int r, int c) {
        return (((long)r) << 32) ^ (c & 0xffffffffL);
    }
}
