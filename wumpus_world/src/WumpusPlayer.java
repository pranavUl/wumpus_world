public class WumpusPlayer {

    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    private int direction;
    private boolean arrow;
    private boolean gold;
    private int colPosition;
    private int rowPosition;

    public WumpusPlayer() {
        this.direction = 0;
        this.gold = false;
        this.arrow = true;
    }

    public static int getNorth() {
        return NORTH;
    }

    public static int getEast() {
        return EAST;
    }

    public static int getSouth() {
        return SOUTH;
    }

    public static int getWest() {
        return WEST;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isArrow() {
        return arrow;
    }

    public void setArrow(boolean arrow) {
        this.arrow = arrow;
    }

    public boolean isGold() {
        return gold;
    }

    public void setGold(boolean gold) {
        this.gold = gold;
    }

    public int getColPosition() {
        return colPosition;
    }

    public void setColPosition(int colPosition) {
        this.colPosition = colPosition;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

}
