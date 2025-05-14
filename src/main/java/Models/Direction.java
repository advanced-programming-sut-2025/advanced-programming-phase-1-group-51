package Models;

public class Direction {
    private final int x;
    private final int y;

    public Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public static Direction directionManaging(String direction, int playerX, int playerY) {
        int directionX = playerX;
        int directionY = playerY;

        switch (direction.toLowerCase()) {
            case "up":
                directionY--;
                break;
            case "down":
                directionY++;
                break;
            case "left":
                directionX--;
                break;
            case "right":
                directionX++;
                break;
            case "up left":
                directionX--;
                directionY--;
                break;
            case "up right":
                directionX++;
                directionY--;
                break;
            case "down left":
                directionX--;
                directionY++;
                break;
            case "down right":
                directionX++;
                directionY++;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }

        return new Direction(directionX, directionY);
    }
}
