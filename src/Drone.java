public class Drone {

    // variables to store the drone's current position
    private int row;
    private int col;


    // constructor that initializes the drone at the start position
    public Drone(int startRow, int startCol) {
        this.row = startRow;
        this.col = startCol;
    }


    // function getRow()
    public int getRow() {
        return row;
    }

    // function getCol()
    public int getCol() {
        return col;
    }

    // function moveUp()
    public void moveUp() {
        row = row - 1;
    }

    // function moveDown()
    public void moveDown() {
        row = row + 1;
    }

    // function moveLeft()
    public void moveLeft() {
        col = col - 1;
    }

    // function moveRight()
    public void moveRight() {
        col = col + 1;
    }

    // function moveTo(newRow, newCol)
    public void moveTo(int newRow, int newCol) {
        row = newRow;
        col = newCol;
    }
}
