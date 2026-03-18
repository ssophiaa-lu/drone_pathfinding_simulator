import java.util.*;

public class PathResult {
    public final List<int[]> path;
    public final long timeNanos;
    public final int nodesExpanded;

    public PathResult(List<int[]> path, long timeNanos, int nodesExpanded) {
        this.path = path;
        this.timeNanos = timeNanos;
        this.nodesExpanded = nodesExpanded;
    }

    public long timeMillis() { return timeNanos / 1_000_000; }

    public int pathLength() { return path == null ? -1 : path.size(); }
}
