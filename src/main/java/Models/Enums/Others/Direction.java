package Models.Enums.Others;


import Models.Position;

public enum Direction {

    UP(0,-1),
    DOWN(0,1),
    LEFT(-1,0),
    RIGHT(1,0),
    UP_LEFT(-1,-1),
    UP_RIGHT(1,-1),
    DOWN_LEFT(-1,1),
    DOWN_RIGHT(1,1),
    ;

    private int [] vector = new int [2];
    Direction(int x , int y){
        vector[0] = x;
        vector[1] = y;
    }

    public int[] getVector() {
        return vector;
    }

    public Position getCoordinate(Position coordinate){
        return new Position(coordinate.getX()+ vector[0], coordinate.getY() + vector[1]);
    }


}
