package gameoflife;

public class Game {

    private final int rows;
    private final int columns;
    private final static int DEAD = 0;
    private final static int ALIVE = 1;

    private int[][] matrix;

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

    public boolean isAlive(int row, int column) {
        return matrix[row][column] == ALIVE;
    }

    public boolean isDead(int row, int column) {
        return matrix[row][column] == DEAD;
    }

    private boolean withinMatrix(int row, int column) {
        return row >= 0 && row < this.rows && column >= 0 && column < this.columns;
    }

    public void setLivingCell(int y, int x) {
        if(withinMatrix(y,x))
            matrix[y][x] = ALIVE;
        else
            throw new ArrayIndexOutOfBoundsException("Outside of matrix!");
    }

    public void buildNextGenMatrix() {

        int[][] nextMatrix = new int[rows][columns];

        for(int y = 0; y < rows; y++) {
            for(int x = 0; x < columns; x++) {

                if(aliveWithLessThanTwoLivingNeighbours(y,x))
                    nextMatrix[y][x] = DEAD;
                else if(aliveWithTwoOrThreeLivingNeighbours(y,x))
                    nextMatrix[y][x] = ALIVE;
                else if(aliveWithMoreThanThreeLivingNeighbours(y,x))
                    nextMatrix[y][x] = DEAD;
                else if(deadWithThreeLivingNeighbours(y,x))
                    nextMatrix[y][x] = ALIVE;
                else
                    nextMatrix[y][x] = matrix[y][x];
            }
        }
        matrix = nextMatrix.clone();
    }

    private boolean deadWithThreeLivingNeighbours(int y, int x) {

        int livingNeighbours = getLivingNeighbours(y,x);
        return isDead(y,x) && livingNeighbours == 3;
    }

    private boolean aliveWithMoreThanThreeLivingNeighbours(int y, int x) {

        int livingNeighbours = getLivingNeighbours(y,x);
        return isAlive(y,x) && livingNeighbours > 3;
    }

    private boolean aliveWithTwoOrThreeLivingNeighbours(int y, int x) {

        int livingNeighbours = getLivingNeighbours(y,x);
        return isAlive(y,x) && (livingNeighbours == 2 || livingNeighbours == 3);
    }

    private boolean aliveWithLessThanTwoLivingNeighbours(int y, int x) {

        int livingNeighbours = getLivingNeighbours(y,x);
        return isAlive(y,x) && livingNeighbours < 2;
    }
}
