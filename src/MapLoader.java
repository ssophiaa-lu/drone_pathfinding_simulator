import java.io.*;
import java.util.*;

public class MapLoader {

    public static char[][] loadMap(String filename) throws IOException {
        ArrayList<char[]> rows = new ArrayList<char[]>();

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = br.readLine()) != null) {
            String[] tokens = line.split(" ");
            char[] row = new char[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                row[i] = tokens[i].charAt(0);
            }
            rows.add(row);
        }
        br.close();

        // create the final 2D char grid with number of rows equal to the list size
        char[][] grid = new char[rows.size()][];
        // loop through each stored row in the list
        for (int i = 0; i < rows.size(); i++) {
            grid[i] = rows.get(i);
        }
        return grid;
    }




   
}
