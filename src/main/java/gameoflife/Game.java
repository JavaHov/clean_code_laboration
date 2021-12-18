package gameoflife;

public class Game {

    private final int rows;
    private final int columns;
    private final static int DEAD = 0;
    private final static int ALIVE = 1;

    private final int[][] matrix;

    public Game(int rows, int columns) {
        matrix = new int[rows][columns];
        this.rows = rows;
        this.columns = columns;
        initializeCleanMatrix();
    }

    private void initializeCleanMatrix() {

        for(int y = 0; y < rows; y++) {
            for(int x = 0; x < columns; x++) {
                matrix[y][x] = DEAD;
                System.out.print(matrix[y][x]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();

    }

    public int getLivingNeighbours(int row, int column) {
        int[][] areaToCheck = {{row-1, column-1},
                                {row-1, column},
                                {row-1, column+1},
                                {row, column-1},
                                {row, column+1},
                                {row+1, column-1},
                                {row+1, column},
                                {row+1, column+1}};

        int livingNeighbours = 0;

        for(int i = 0; i < areaToCheck.length; i++) {

            int rowToCheck = areaToCheck[i][0];
            int columnToCheck = areaToCheck[i][1];

            if(withinMatrix(rowToCheck, columnToCheck) && isAlive(rowToCheck, columnToCheck)){
                livingNeighbours++;
            }
        }
        return livingNeighbours;
    }

    private boolean isAlive(int row, int column) {
        return matrix[row][column] == ALIVE;
    }

    private boolean isDead(int row, int column) {
        return matrix[row][column] == DEAD;
    }

    private boolean withinMatrix(int row, int column) {
        return row >= 0 && row < this.rows && column >= 0 && column < this.columns;
    }

    public void setLivingCell(int y, int x) {
        matrix[y][x] = ALIVE;
    }
}
