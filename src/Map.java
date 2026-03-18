public class Map {
    private char[][] grid;

    public Map(char[][] grid) {
        this.grid = grid;
    }

    public int[] getStart() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 'S') return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }

    public int[] getGoal() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 'G') return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }

    public boolean inBounds(int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    public boolean isObstacle(int row, int col) {
        if (!inBounds(row, col)) return true;
        return grid[row][col] == 'X';
    }

    public boolean isGoal(int row, int col) {
        if (!inBounds(row, col)) return false;
        return grid[row][col] == 'G';
    }

    public boolean canMoveTo(int row, int col) {
        if (!inBounds(row, col)) return false;
        return grid[row][col] != 'X';
    }

    public void printMap() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printMapWithDrone(Drone d) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == d.getRow() && j == d.getCol()) {
                    System.out.print('D');
                    System.out.print(' ');
                } else {
                    System.out.print(grid[i][j]);
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}