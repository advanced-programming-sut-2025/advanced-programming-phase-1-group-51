package Models.Maps;

import Models.ObjectsShownOnMap.ObjectOnMap;

public class Cells {

    private Position position;
    private ObjectOnMap objectOnCell;
    public boolean hasBeenPlowed = false;

    public Cells(Position position, ObjectOnMap objectOnCell) {
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

    public void setObjectOnCell(ObjectOnMap objectOnCell) {
        this.objectOnCell = objectOnCell;
    }

    public boolean isHasBeenPlowed() {
        return hasBeenPlowed;
    }

    public void setHasBeenPlowed(boolean hasBeenPlowed) {
        this.hasBeenPlowed = hasBeenPlowed;
    }
}
