package client;

public class Ship {
    private int x; // X-coordinate (column) of the top-left square
    private int y; // Y-coordinate (row) of the top-left square

    public Ship( int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}