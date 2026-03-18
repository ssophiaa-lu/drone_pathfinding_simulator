public class Main {

    public static void main(String[] args) throws Exception {

        // 1. load the grid from the map file
        char[][] grid = MapLoader.loadMap("Maps/map1.txt");

        // 2. create the Map object
        Map map = new Map(grid);

        // 3. get the start position
        int[] start = map.getStart();
        if (start[0] == -1) {
            System.out.println("Start position not found in map");
            return;
        }

        // 4. create the drone at the start
        Drone drone = new Drone(start[0], start[1]);

        // Print initial map with drone
        System.out.println("Initial map:");
        map.printMapWithDrone(drone);

        // Find goal
        int[] goal = map.getGoal();
        if (goal[0] == -1) {
            System.out.println("Goal not found in map");
            return;
        }

        // Determine start and goal
        int startRow = drone.getRow();
        int startCol = drone.getCol();
        int goalRow = goal[0];
        int goalCol = goal[1];

        // Benchmark parameters
    final int WARMUP = 10;
    final int RUNS = 1000;

        // Warm-up to let JVM optimize (not measured)
        for (int i = 0; i < WARMUP; i++) {
            BFSPathFinder.findPathWithStats(map, startRow, startCol, goalRow, goalCol);
            PathFinder.findPathWithStats(map, startRow, startCol, goalRow, goalCol);
        }

        // Measure BFS
        long totalBfsTime = 0L;
        long totalBfsNodes = 0L;
        long totalBfsPathLen = 0L;
        int bfsSuccess = 0;
        for (int i = 0; i < RUNS; i++) {
            PathResult r = BFSPathFinder.findPathWithStats(map, startRow, startCol, goalRow, goalCol);
            totalBfsTime += r.timeNanos;
            totalBfsNodes += r.nodesExpanded;
            if (r.path != null) {
                totalBfsPathLen += r.path.size();
                bfsSuccess++;
            }
        }

        // Measure A*
        long totalATime = 0L;
        long totalANodes = 0L;
        long totalAPathLen = 0L;
        int aSuccess = 0;
        for (int i = 0; i < RUNS; i++) {
            PathResult r = PathFinder.findPathWithStats(map, startRow, startCol, goalRow, goalCol);
            totalATime += r.timeNanos;
            totalANodes += r.nodesExpanded;
            if (r.path != null) {
                totalAPathLen += r.path.size();
                aSuccess++;
            }
        }

        double avgBfsTimeMs = (double) totalBfsTime / RUNS / 1_000_000.0;
        double avgBfsNodes = (double) totalBfsNodes / RUNS;
        double avgBfsPath = bfsSuccess == 0 ? -1 : (double) totalBfsPathLen / bfsSuccess;

        double avgATimeMs = (double) totalATime / RUNS / 1_000_000.0;
        double avgANodes = (double) totalANodes / RUNS;
        double avgAPath = aSuccess == 0 ? -1 : (double) totalAPathLen / aSuccess;

        System.out.println();
        System.out.println("Algorithm: BFS");
        System.out.println("Nodes explored: " + Math.round(avgBfsNodes));
        System.out.println("Path length: " + Math.round(avgBfsPath));
        System.out.println(String.format("Time: %.3f ms", avgBfsTimeMs));

        System.out.println();
        System.out.println("Algorithm: A*");
        System.out.println("Nodes explored: " + Math.round(avgANodes));
        System.out.println("Path length: " + Math.round(avgAPath));
        System.out.println(String.format("Time: %.3f ms", avgATimeMs));

    }
}