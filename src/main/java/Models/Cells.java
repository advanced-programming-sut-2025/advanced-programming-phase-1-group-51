package Models;

public class Cells {

    private Position position;
    private Object objectOnCell;

    public Cells(Position position, Object objectOnCell) {
        this.position = position;
        this.objectOnCell = objectOnCell;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Object getObjectOnCell() {
        return objectOnCell;
    }

    public void setObjectOnCell(Object objectOnCell) {
        this.objectOnCell = objectOnCell;
    }
}
