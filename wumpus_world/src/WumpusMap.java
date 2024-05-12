import java.util.Random;

public class WumpusMap {

    public static final int NUM_ROWS = 4;
    public static final int NUM_COLUMNS = 4;
    public static final int NUM_PITS = 10;

    private WumpusSquare[][] grid;
    private int ladderC;
    private int ladderR;


    public WumpusMap() {
        this.createMap();
        boolean[][] beenThereFill = new boolean[NUM_ROWS][NUM_COLUMNS];
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                beenThereFill[i][j] = false;
            }
        }

        while (!isSolvable(this.grid, beenThereFill, this.ladderC, this.ladderR)) {
            //System.out.println("\n\n" + this.toString());
            this.createMap();
            for (int i = 0; i < NUM_ROWS; i++) {
                for (int j = 0; j < NUM_COLUMNS; j++) {
                    beenThereFill[i][j] = false;
                }
            }
        }
        System.out.println("\n\n" + this.toString());
        //if (!isSolvable(this.grid, beenThereFill, this.ladderC, this.ladderR)) {
        //    System.out.println("\n\nnot solvable - " + this.ladderC + " " + this.ladderR);
        //}
        //System.out.println("\n\n" + this.toString());

    }

    public void createMap() {
        grid = new WumpusSquare[NUM_ROWS][NUM_COLUMNS];

        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                grid[i][j] = new WumpusSquare();
            }
        }

        Random rand = new Random();

        int randRow = rand.nextInt(NUM_ROWS);
        int randCol = rand.nextInt(NUM_COLUMNS);

        for (int i = 0; i < NUM_PITS; i++) {
            
            while (!this.grid[randRow][randCol].toString().equals("*")) {
                randRow = rand.nextInt(NUM_ROWS);
                randCol = rand.nextInt(NUM_COLUMNS);
            }
            grid[randRow][randCol].setPit(true);
            if (randRow != NUM_ROWS-1) {
                grid[randRow+1][randCol].setBreeze(true);
            }
            if (randRow != 0) {
                grid[randRow-1][randCol].setBreeze(true);
            }
            if (randCol != NUM_COLUMNS-1) {
                grid[randRow][randCol+1].setBreeze(true);
            }
            if (randCol != 0) {
                grid[randRow][randCol-1].setBreeze(true);
            }
        }

        randRow = rand.nextInt(NUM_ROWS);
        randCol = rand.nextInt(NUM_COLUMNS);
        while (grid[randRow][randCol].isPit() || grid[randRow][randCol].isLadder()) {
            randRow = rand.nextInt(NUM_ROWS);
            randCol = rand.nextInt(NUM_COLUMNS);
        }
        grid[randRow][randCol].setGold(true);

        randRow = rand.nextInt(NUM_ROWS);
        randCol = rand.nextInt(NUM_COLUMNS);
        while (grid[randRow][randCol].isPit() || grid[randRow][randCol].isLadder()) {
            randRow = rand.nextInt(NUM_ROWS);
            randCol = rand.nextInt(NUM_COLUMNS);
        }
        grid[randRow][randCol].setWumpus(true);
        grid[randRow][randCol].setStench(true);
        if (randRow != NUM_ROWS-1) {
            grid[randRow+1][randCol].setStench(true);
        }
        if (randRow != 0) {
            grid[randRow-1][randCol].setStench(true);
        }
        if (randCol != NUM_COLUMNS-1) {
            grid[randRow][randCol+1].setStench(true);
        }
        if (randCol != 0) {
            grid[randRow][randCol-1].setStench(true);
        }

        randRow = rand.nextInt(NUM_ROWS);
        randCol = rand.nextInt(NUM_COLUMNS);
        while (grid[randRow][randCol].isPit() || grid[randRow][randCol].isGold() || grid[randRow][randCol].isWumpus()) {
            randRow = rand.nextInt(NUM_ROWS);
            randCol = rand.nextInt(NUM_COLUMNS);
        }
        grid[randRow][randCol].setLadder(true);

        this.ladderC = randCol;
        this.ladderR = randRow;
    }

    public int getLadderC() {
        return ladderC;
    }

    public void setLadderC(int ladderC) {
        this.ladderC = ladderC;
    }

    public int getLadderR() {
        return ladderR;
    }

    public void setLadderR(int ladderR) {
        this.ladderR = ladderR;
    }

    public WumpusSquare getSquare(int r, int c) {
        if (c >= 0 && c < NUM_COLUMNS && r >= 0 && c < NUM_ROWS) {
            return grid[r][c];
        }
        else {
            return null;
        }
    }

    public String toString() {
        String map = "";

        for (WumpusSquare[] wsRow : grid) {
            for (WumpusSquare ws : wsRow) {
                map = map + ws.toString();
            }
            map = map + "\n";
        }

        return map;
    }

    public static boolean isSolvable(WumpusSquare[][] grid, boolean[][] beenThere, int col, int row) { //copied DS0 - Ch13
    
            if (col > grid[0].length-1) {
                return false;
            }
            else if (row > grid.length-1) {
                return false;
            }
            else if (col < 0) {
                return false;
            }
            else if (row < 0) {
                return false;
            }
            else if (beenThere[row][col]) {
                return false;
            }
    
            if (grid[row][col].toString().equals("P")) {
                return false;
            }
            if (grid[row][col].toString().equals("G") || grid[row][col].toString().equals("@")) {
                return true;
            }
            
            beenThere[row][col] = true;
            
            if (isSolvable(grid, beenThere, col+1, row) || isSolvable(grid, beenThere, col-1, row) || isSolvable(grid, beenThere, col, row+1) || isSolvable(grid, beenThere, col, row-1)) {
                return true;
            }
            return false;
    
    }

}