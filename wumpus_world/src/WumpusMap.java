import java.util.Random;

public class WumpusMap {

    public static final int NUM_ROWS = 10;
    public static final int NUM_COLUMNS = 10;
    public static final int NUM_PITS = 10;

    private WumpusSquare[][] grid;
    private int ladderC;
    private int ladderR;


    public WumpusMap() {
        this.createMap();
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
            if (randRow != 9) {
                grid[randRow+1][randCol].setBreeze(true);
            }
            if (randRow != 0) {
                grid[randRow-1][randCol].setBreeze(true);
            }
            if (randCol != 9) {
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
        if (randRow != 9) {
            grid[randRow+1][randCol].setStench(true);
        }
        if (randRow != 0) {
            grid[randRow-1][randCol].setStench(true);
        }
        if (randCol != 9) {
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

    public WumpusSquare getSquare(int c, int r) {
        if (c >= 0 && c < NUM_ROWS && r >= 0 && c < NUM_COLUMNS) {
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

}